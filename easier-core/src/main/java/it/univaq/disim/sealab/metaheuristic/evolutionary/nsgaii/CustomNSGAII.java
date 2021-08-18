package it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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

	
	/**
	 * Constructor
	 * matingPopulationSize = offspringPopulationSize = populationSize as used in NSGAIIBuilder
	 */
	public CustomNSGAII(Problem<S> problem, int maxIterations, int populationSize,
			CrossoverOperator<S> crossoverOperator, MutationOperator<S> mutationOperator,
			SelectionOperator<List<S>, S> selectionOperator, SolutionListEvaluator <S> evaluator) {
		super((Problem<S>)problem, maxIterations, populationSize, populationSize, populationSize, crossoverOperator, mutationOperator, selectionOperator,
				evaluator);
	}
	
//	@Override protected boolean isStoppingConditionReached() {
//		
//		return evaluations > maxEvaluations;
//	}
	
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
		initProgress();
		while (!isStoppingConditionReached()) {

			System.out.println(this.getName());
			ProgressBar.showBar((evaluations/getMaxPopulationSize()), (maxEvaluations/getMaxPopulationSize()));
			
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

			String line = String.format("%s,%s,%s,%s,%s,%s,%s", this.getName(), this.getProblem().getName(), computingTime,
					totalBefore, freeBefore, totalAfter, freeAfter);

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(
					Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv").toString(), true))) {
				writer.append(line);
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			updateProgress();

		}
	}
	
	
	@Override
	public String getDescription() {
		return "Nondominated Sorting Genetic Algorithm version II. Version using measures";
	}
	
	public void clear() {
		for(S  sol : this.getPopulation()) {
			sol.setParents(null, null);
		}
		this.getPopulation().clear();
	}
	
}
