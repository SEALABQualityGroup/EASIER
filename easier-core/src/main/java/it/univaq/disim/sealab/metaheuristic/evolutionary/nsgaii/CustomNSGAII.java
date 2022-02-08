package it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

import it.univaq.disim.sealab.metaheuristic.evolutionary.EasierAlgorithm;
import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

@SuppressWarnings("serial")
public class CustomNSGAII<S extends RSolution<?>> extends NSGAII<S> implements EasierAlgorithm {

	private long durationThreshold, iterationStartingTime;
	private int localMinima, prematureConvergenceThreshold;

	private List<RSolution<?>> oldPopulation;

	/**
	 * Constructor matingPopulationSize = offspringPopulationSize = populationSize
	 * as used in NSGAIIBuilder
	 */
	public CustomNSGAII(Problem<S> problem, int maxIterations, int populationSize,
			CrossoverOperator<S> crossoverOperator, MutationOperator<S> mutationOperator,
			SelectionOperator<List<S>, S> selectionOperator, SolutionListEvaluator<S> evaluator) {
		super((Problem<S>) problem, maxIterations, populationSize, populationSize, populationSize, crossoverOperator,
				mutationOperator, selectionOperator, evaluator);

		durationThreshold = Configurator.eINSTANCE.getStoppingCriterionTimeThreshold();
		prematureConvergenceThreshold = Configurator.eINSTANCE.getStoppingCriterionPrematureConvergenceThreshold();
		oldPopulation = new ArrayList<RSolution<?>>();
		localMinima = 0;

	}

	/**
	 * Support multiple stopping criteria. byTime the default computing threshold is
	 * set to 1 h byPrematureConvergence the default premature convergence is set to
	 * 3 consecutive populations with the same objectives byBoth using byTime and
	 * byPrematureConvergence classic using the number of evaluation
	 */
	@Override
	protected boolean isStoppingConditionReached() {

		long currentComputingTime = System.currentTimeMillis() - iterationStartingTime;

		if (Configurator.eINSTANCE.isSearchBudgetByTime()) // byTime
			return super.isStoppingConditionReached() || currentComputingTime > durationThreshold;
		if (Configurator.eINSTANCE.isSearchBudgetByPrematureConvergence()) // byPrematureConvergence
			return super.isStoppingConditionReached() || localMinima > prematureConvergenceThreshold;
		if (Configurator.eINSTANCE.isSearchBudgetByPrematureConvergenceAndTime()) // byBoth
			return super.isStoppingConditionReached() || localMinima > prematureConvergenceThreshold
					|| currentComputingTime > durationThreshold;
		return super.isStoppingConditionReached(); // classic

	}

	@Override
	protected void initProgress() {
		super.initProgress();
		iterationStartingTime = System.currentTimeMillis();
		oldPopulation = (List<RSolution<?>>) this.getPopulation(); // store the initial population
	}

	@Override
	protected void updateProgress() {
		super.updateProgress();

		if (Configurator.eINSTANCE.isSearchBudgetByPrematureConvergence()
				|| Configurator.eINSTANCE.isSearchBudgetByPrematureConvergenceAndTime()) {

			// create a joined list of the current population and the old one
			List<RSolution<?>> joinedPopulation = new ArrayList<>(oldPopulation);
			joinedPopulation.addAll(population);

			int countedSameObjectives = 0;
			for (int i = 0; i < joinedPopulation.size() - 1; i++) {
				if (!joinedPopulation.get(i).isLocalOptmimalPoint(joinedPopulation.get(i + 1))) {
					localMinima = 0;
					break;
				}
				countedSameObjectives++;
			}

			// update oldPopulation to the current population
			oldPopulation = (List<RSolution<?>>) population;

			// check if all solutions within the joined list have the same objective values
//			if (countedSameObjectives == joinedPopulation.size() - 1)
			float percentageOfLocalSolution = 0.50f;
			if (countedSameObjectives / joinedPopulation.size() > percentageOfLocalSolution)
				localMinima++;

		}
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
		
		if (!Files.exists(Configurator.eINSTANCE.getOutputFolder().resolve("search_budget_stats.csv"))) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(
					Configurator.eINSTANCE.getOutputFolder().resolve("search_budget_stats.csv").toString()))) {
				writer.write("algorithm,problem_tag,search_busget,iteration,max_iteration");
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.setPopulation(createInitialPopulation());
		this.setPopulation(evaluatePopulation(this.getPopulation()));
		initProgress();
		while (!isStoppingConditionReached()) {

			System.out.println(this.getName());
			ProgressBar.showBar((evaluations / getMaxPopulationSize()), (maxEvaluations / getMaxPopulationSize()));

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

		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(
				Configurator.eINSTANCE.getOutputFolder().resolve("search_budget_stats.csv").toString(), true))) {
			String line = String.format("%s,%s,%s,%s,%s", this.getName(), this.getProblem().getName(),
					Configurator.eINSTANCE.getSearchBudgetType(), evaluations, maxEvaluations);
			writer.write(line);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getDescription() {
		return "Nondominated Sorting Genetic Algorithm version II. Version using measures";
	}

	public void clear() {
		for (S sol : this.getPopulation()) {
			sol.setParents(null, null);
		}
		this.getPopulation().clear();
	}

}
