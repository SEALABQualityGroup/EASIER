package it.univaq.disim.sealab.metaheuristic.evolutionary.spea2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
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

public class CustomSPEA2<S extends RSolution<?>> extends SPEA2<S> implements EasierAlgorithm {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long durationThreshold, iterationStartingTime;
	private float prematureConvergenceThreshold;

	List<S> oldPopulation;

	private long freeBefore;

	private long totalBefore;

	private long initTime;
	
	/**
	 * Constructor
	 * distance to the k-th individual
	 * k = sqrt(population.size()), but a value of k = 1 is recommended. 
	 */
	public CustomSPEA2(Problem<S> problem, int maxIterations, int populationSize,
			CrossoverOperator<S> crossoverOperator, MutationOperator<S> mutationOperator,
			SelectionOperator<List<S>, S> selectionOperator, SolutionListEvaluator<S> evaluator) {
	
		// k = sqrt(population.size()), but a value of k = 1 is recommended. 
		super(problem, maxIterations, populationSize, crossoverOperator, mutationOperator, selectionOperator, evaluator, 1);
		
		durationThreshold = Configurator.eINSTANCE.getStoppingCriterionTimeThreshold();
		prematureConvergenceThreshold = Configurator.eINSTANCE.getStoppingCriterionPrematureConvergenceThreshold();
		oldPopulation = new ArrayList<S>();
		
	}
	
	
	/**
	 * Support multiple stopping criteria.
	 * byTime the default computing threshold is set to 1 h
	 * byPrematureConvergence the default premature convergence is set to 3 consecutive populations with the same objectives
	 * byBoth using byTime and byPrematureConvergence
	 * classic using the number of evaluation
	 */
	@Override
	public boolean isStoppingConditionReached() {
		System.out.println(getName());
		ProgressBar.showBar(iterations, maxIterations);
		long currentComputingTime = System.currentTimeMillis() - iterationStartingTime;

		if (Configurator.eINSTANCE.isSearchBudgetByTime()) // byTime
			return super.isStoppingConditionReached() || currentComputingTime > durationThreshold;
		if (Configurator.eINSTANCE.isSearchBudgetByPrematureConvergence()) //byPrematureConvergence
			return super.isStoppingConditionReached() || isStagnantState();
		if (Configurator.eINSTANCE.isSearchBudgetByPrematureConvergenceAndTime()) // byBoth
			return super.isStoppingConditionReached() || isStagnantState() || currentComputingTime > durationThreshold;
		return super.isStoppingConditionReached();
	}
	
	@Override
	protected void initProgress() {
		super.initProgress();
		this.getPopulation().forEach(s -> s.refactoringToCSV());

		iterationStartingTime = System.currentTimeMillis();
		freeBefore = Runtime.getRuntime().freeMemory();
		totalBefore = Runtime.getRuntime().totalMemory();
		initTime = System.currentTimeMillis();
		
		oldPopulation = (List<S>) this.getPopulation(); // store the initial population
	}

	@Override
	public void updateProgress() {
		super.updateProgress();

		long computingTime = System.currentTimeMillis() - initTime;
		long freeAfter = Runtime.getRuntime().freeMemory();
		long totalAfter = Runtime.getRuntime().totalMemory();

		new FileUtils().algoPerfStatsDumpToCSV(String.format("%s,%s,%s,%s,%s,%s,%s", this.getName(),
				this.getProblem().getName(), computingTime, totalBefore, freeBefore, totalAfter, freeAfter));

		// Store the checkpoint
		totalBefore = totalAfter;
		freeBefore = freeAfter;
		initTime = computingTime;

		populationToCSV();
		System.out.println(this.getName());
		ProgressBar.showBar(iterations / getMaxPopulationSize(), maxIterations / getMaxPopulationSize());
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
		oldPopulation = new ArrayList<S>(population);

		// check if all solutions within the joined list have the same objective values
		return ((double) (population.size() - countedSameObjectives / population.size()) / population.size()) > prematureConvergenceThreshold;
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
	public void run() {
		/*
		List<S> offspringPopulation;
		List<S> matingPopulation;
		
		this.setPopulation(createInitialPopulation());
		this.setPopulation(evaluatePopulation(this.getPopulation()));
		initProgress();
		while (!isStoppingConditionReached()) {

			// MOVED into initProgress
//			long freeBefore = Runtime.getRuntime().freeMemory();
//			long totalBefore = Runtime.getRuntime().totalMemory();
//
//			long initTime = System.currentTimeMillis();
			
			matingPopulation = selection(this.getPopulation());
			offspringPopulation = reproduction(matingPopulation);
			offspringPopulation = evaluatePopulation(offspringPopulation);
			this.setPopulation(replacement(this.getPopulation(), offspringPopulation));
			
			// MOVED into updateProgress
//			long computingTime = System.currentTimeMillis() - initTime;
//
//			long freeAfter = Runtime.getRuntime().freeMemory();
//			long totalAfter = Runtime.getRuntime().totalMemory();

//			String line = String.format("%s,%s,%s,%s,%s,%s,%s", this.getName(), this.getProblem().getName(), computingTime,
//					totalBefore, freeBefore, totalAfter, freeAfter);

//			new FileUtils().algoPerfStatsDumpToCSV(String.format("%s,%s,%s,%s,%s,%s,%s", this.getName(), this.getProblem().getName(),
//					computingTime, totalBefore, freeBefore, totalAfter, freeAfter));

//			updateProgress();
//			populationToCSV();

		}*/
		
		super.run();
		
		/* prints the number of iterations until the search budget is not reached. 
		 * !!!Attn!!! 
		 * iteration -1
		 * is required because iterations has been updated just before checking the stopping criteria
		 * !!!Attn!!! 
		 */
		new FileUtils().searchBudgetDumpToCSV(String.format("%s,%s,%s,%s,%s", this.getName(), this.getProblem().getName(),
				Configurator.eINSTANCE.getSearchBudgetType(), iterations-1, maxIterations));
	}

	public void clear() {
		
		for(S  sol : this.getPopulation()) {
			sol.setParents(null, null);
		}
		
		for(S  sol : this.archive) {
			sol.setParents(null, null);
		}
		
		this.getPopulation().clear();
		this.archive.clear();
	}

}
