package it.univaq.disim.sealab.metaheuristic.evolutionary.rnsgaii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAII;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

import it.univaq.disim.sealab.metaheuristic.evolutionary.EasierAlgorithm;
import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class CustomRNSGAII<S extends RSolution<?>> extends RNSGAII<S> implements EasierAlgorithm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long durationThreshold, iterationStartingTime;
	private float prematureConvergenceThreshold;

	// It will be exploited to identify stagnant situation
	List<S> oldPopulation;

	public CustomRNSGAII(Problem<S> problem, int maxEvaluations, int populationSize, int matingPoolSize,
			int offspringPopulationSize, CrossoverOperator<S> crossoverOperator, MutationOperator<S> mutationOperator,
			SelectionOperator<List<S>, S> selectionOperator, SolutionListEvaluator<S> evaluator,
			List<Double> interestPoint, double epsilon) {

		super(problem, maxEvaluations, populationSize, matingPoolSize, offspringPopulationSize, crossoverOperator,
				mutationOperator, selectionOperator, evaluator, interestPoint, epsilon);

		durationThreshold = Configurator.eINSTANCE.getStoppingCriterionTimeThreshold();
		prematureConvergenceThreshold = Configurator.eINSTANCE.getStoppingCriterionPrematureConvergenceThreshold();
		oldPopulation = new ArrayList<S>();
	}

	@Override
	public void run() {
		durationMeasure.reset();
		durationMeasure.start();

		_run();

		durationMeasure.stop();
	}
	
	
	@Override
	protected void initProgress() {
		super.initProgress();
		iterationStartingTime = System.currentTimeMillis();
		oldPopulation = (List<S>) this.getPopulation(); // store the initial population
		this.getPopulation().forEach(s -> s.refactoringToCSV());
	}
	
	/**
	 * Support multiple stopping criteria. byTime the default computing threshold is
	 * set to 1 h byPrematureConvergence the default premature convergence is set to
	 * 3 consecutive populations with the same objectives byBoth using byTime and
	 * byPrematureConvergence classic using the number of evaluation
	 */
	@Override
	public boolean isStoppingConditionReached() {

		long currentComputingTime = System.currentTimeMillis() - iterationStartingTime;

		if (Configurator.eINSTANCE.isSearchBudgetByTime()) // byTime
			return super.isStoppingConditionReached() || currentComputingTime > durationThreshold;
		if (Configurator.eINSTANCE.isSearchBudgetByPrematureConvergence()) // byPrematureConvergence
			return super.isStoppingConditionReached() || isStagnantState();
		// computeStagnantState
		if (Configurator.eINSTANCE.isSearchBudgetByPrematureConvergenceAndTime()) // byBoth
			return super.isStoppingConditionReached() || isStagnantState() || currentComputingTime > durationThreshold;
		return super.isStoppingConditionReached(); // classic

	}
	
	public boolean isStagnantState() {

		int countedSameObjectives = 0;
		for (int i = 0; i < oldPopulation.size(); i++) {
			for (int j = 0; j < population.size(); j++) {
				if (!oldPopulation.get(i).isLocalOptmimalPoint(population.get(j))) {
					break;
				}
				countedSameObjectives++;
			}
		}

		// update oldPopulation to the current population
		oldPopulation = (List<S>) population;

		// check if all solutions within the joined list have the same objective values
		return ((double) (population.size() - countedSameObjectives / population.size())
				/ population.size()) > prematureConvergenceThreshold;
	}
	

	/**
	 * it comes from
	 */
	private void _run() {
		List<S> offspringPopulation;
		List<S> matingPopulation;

		this.setPopulation(createInitialPopulation());
		this.setPopulation(evaluatePopulation(this.getPopulation()));
		initProgress();
		while (!isStoppingConditionReached()) {

			System.out.println(this.getName());
			ProgressBar.showBar((int) (evaluations.get() / getMaxPopulationSize()),
					(maxEvaluations / getMaxPopulationSize()));

			long freeBefore = Runtime.getRuntime().freeMemory();
			long totalBefore = Runtime.getRuntime().totalMemory();

			long initTime = System.currentTimeMillis();

			matingPopulation = selection(this.getPopulation());
			offspringPopulation = reproduction(matingPopulation);
			offspringPopulation = evaluatePopulation(offspringPopulation);
			this.setPopulation(replacement(this.getPopulation(), offspringPopulation));

			long computingTime = System.currentTimeMillis() - initTime;

			long freeAfter = Runtime.getRuntime().freeMemory();
			long totalAfter = Runtime.getRuntime().totalMemory();

			new FileUtils().algoPerfStatsDumpToCSV(String.format("%s,%s,%s,%s,%s,%s,%s", this.getName(),
					this.getProblem().getName(), computingTime, totalBefore, freeBefore, totalAfter, freeAfter));

			updateProgress();
			populationToCSV();

		}
	}
	
	/*
	 * Prints to CSV each generated population
	 * "algorithm,problem_tag,solID,perfQ,#changes,pas,reliability"
	 * 
	 */
	public void populationToCSV() {
		for (RSolution<?> sol : population) {
			String line = this.getName() + ',' + this.getProblem().getName() + ',' + sol.objectiveToCSV();
			new FileUtils().solutionDumpToCSV(line);
		}
	}

	@Override
	public void clear() {
		for (S sol : this.getPopulation()) {
			sol.setParents(null, null);
		}
		this.getPopulation().clear();
	}

}
