package it.univaq.disim.sealab.metaheuristic.evolutionary.pesaii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

import it.univaq.disim.sealab.metaheuristic.evolutionary.EasierAlgorithm;
import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class CustomPESA2<S extends RSolution<?>> extends PESA2<S> implements EasierAlgorithm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int _maxEvaluations;
	
	private long durationThreshold, iterationStartingTime;
	private float prematureConvergenceThreshold;

	private List<S> oldPopulation;


	public CustomPESA2(Problem<S> problem, int maxEvaluations, int populationSize, int archiveSize, int biSections,
			CrossoverOperator<S> crossoverOperator, MutationOperator<S> mutationOperator,
			SolutionListEvaluator<S> evaluator) {

		super((Problem<S>) problem, maxEvaluations, populationSize, archiveSize, biSections, crossoverOperator,
				mutationOperator, evaluator);
		_maxEvaluations = maxEvaluations;
		
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
	protected boolean isStoppingConditionReached() {
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
		iterationStartingTime = System.currentTimeMillis();
		oldPopulation = (List<S>) this.getPopulation(); // store the initial population
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
		return ((double) (population.size() - countedSameObjectives / population.size()) / population.size()) > prematureConvergenceThreshold;
	}	

	@Override
	public void run() {
		List<S> offspringPopulation;
		List<S> matingPopulation;

		if (!Files.exists(Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv"))) {

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(
					Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv").toString()))) {
				writer.write(
						"algorithm,problem_tag,execution_time(ms),total_memory_before(B),free_memory_before(B),total_memory_after(B),free_memory_after(B)");
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.setPopulation(createInitialPopulation());
		this.setPopulation(evaluatePopulation(this.getPopulation()));
		
		int _evaluations = this.getMaxPopulationSize();
		
		initProgress();
		while (!isStoppingConditionReached()) {

			System.out.println(this.getName());
			ProgressBar.showBar(_evaluations/getMaxPopulationSize(), _maxEvaluations/getMaxPopulationSize());

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

			String line = String.format("%s,%s,%s,%s,%s,%s,%s", this.getName(), this.getProblem().getName(),
					computingTime, totalBefore, freeBefore, totalAfter, freeAfter);

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(
					Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv").toString(), true))) {
				writer.append(line);
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			updateProgress();
			_evaluations += this.getMaxPopulationSize();

		}
	}

	public void clear() {
		for (S sol : this.getPopulation()) {
			sol.setParents(null, null);
		}
		this.getPopulation().clear();
	}

}
