package it.univaq.disim.sealab.metaheuristic;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import it.univaq.disim.sealab.easier.uml.utils.WorkflowUtils;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2Builder;
import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAII;
import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.lab.experiment.ExperimentBuilder;
import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.lab.experiment.util.ExperimentProblem;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

import com.beust.jcommander.JCommander;

import it.univaq.disim.sealab.easier.uml.utils.UMLMemoryOptimizer;
import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperiment;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperimentAlgorithm;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperimentBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.UMLRExecuteAlgorithms;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RComputeQualityIndicators;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RGenerateReferenceParetoFront;
import it.univaq.disim.sealab.metaheuristic.evolutionary.factory.FactoryBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAIIBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RMutation;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.UMLRCrossover;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.UMLRSolutionListEvaluator;
import it.univaq.disim.sealab.metaheuristic.evolutionary.pesaii.CustomPESA2Builder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.rnsgaii.CustomRNSGAIIBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2Builder;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class Launcher {

	public static void main(String[] args) throws Exception {

		JCommander jc = new JCommander();

		jc.addObject(Configurator.eINSTANCE);
		jc.parse(args);

		List<Path> referenceFront = new ArrayList<>();
		double qThreshold = 0.1;

		if (Configurator.eINSTANCE.getReferenceFront() != null)
			referenceFront = Configurator.eINSTANCE.getReferenceFront();

		else {

			List<Path> modelsPath = new ArrayList<>(Configurator.eINSTANCE.getModelsPath());
			int i = 1;
			int[] eval = Configurator.eINSTANCE.getMaxEvaluation().stream().mapToInt(e -> e).toArray();

			for (Path m : modelsPath) {
				System.out.println("Number of source model");
				ProgressBar.showBar(i, modelsPath.size());
				List<RProblem<UMLRSolution>> rProblems	= new ArrayList<>();
				for (int j = 0; j < eval.length; j++) {
					rProblems.add(createProblems(m, eval[j]));

					if (!m.getParent().resolve("output.xml").toFile().exists()) {
						new WorkflowUtils().applyTransformation(m);
						new WorkflowUtils().invokeSolver(m);
					}
					List<GenericIndicator<UMLRSolution>> qIndicators = new ArrayList<>();
					FactoryBuilder<UMLRSolution> factory = new FactoryBuilder<>();
					for (String qI : Configurator.eINSTANCE.getQualityIndicators()) {
						GenericIndicator<UMLRSolution> ind = factory.createQualityIndicators(qI);
						if (ind != null)
							qIndicators.add(ind);
					}
					referenceFront = runExperiment(rProblems, qIndicators, eval[j]);
					new UMLMemoryOptimizer().cleanup();
					System.gc();
				}
				i++;
			}
		}
	}

	public static List<Path> runExperiment(final List<RProblem<UMLRSolution>> rProblems,
			final List<GenericIndicator<UMLRSolution>> qualityIndicators, int eval) {
		final int INDEPENDENT_RUNS = Configurator.eINSTANCE.getIndependetRuns(); // should be 31 or 51
		final int CORES = 1;

		List<Path> refFront = new ArrayList<>();

		List<ExperimentProblem<UMLRSolution>> problemList = new ArrayList<>();

		rProblems.forEach(problem -> problemList.add(new ExperimentProblem<>(problem)));

		List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algorithmList = configureAlgorithmList(problemList,
				eval);

		Path referenceFrontDirectory = Paths.get(Configurator.eINSTANCE.getOutputFolder().toString(), "referenceFront");

		List<String> tags = new ArrayList<>();

		if (Configurator.eINSTANCE.generateRF())
			problemList.forEach(p -> tags.add(p.getTag() + ".rf"));
		else
			problemList.forEach(p -> tags.add("super-reference-pareto.rf"));

		for (String tag : tags) {
			refFront.add(Paths.get(Configurator.eINSTANCE.getOutputFolder().toString(), "referenceFront", tag));
		}

		ExperimentBuilder<UMLRSolution, List<UMLRSolution>> experimentBuilder = new RExperimentBuilder<UMLRSolution, List<UMLRSolution>>(
				"Exp").setAlgorithmList(algorithmList).setProblemList(problemList)
						.setExperimentBaseDirectory(referenceFrontDirectory.toString())
						.setReferenceFrontDirectory(referenceFrontDirectory.toString())
						.setIndependentRuns(INDEPENDENT_RUNS).setNumberOfCores(CORES)
						.setOutputParetoFrontFileName("FUN").setOutputParetoSetFileName("VAR")
						.setIndicatorList(qualityIndicators);

		RExperiment<UMLRSolution, List<UMLRSolution>> experiment = ((RExperimentBuilder<UMLRSolution, List<UMLRSolution>>) experimentBuilder)
				.setReferenceFrontFileNames(tags).build();
		try {
			List<Entry<Algorithm<List<UMLRSolution>>, long[]>> computingTimes = new UMLRExecuteAlgorithms<UMLRSolution, List<UMLRSolution>>(
					experiment).run().getComputingTimes();

			experiment.setComputingTime(computingTimes);

			if (Configurator.eINSTANCE.generateRF())
				new RGenerateReferenceParetoFront(experiment).run();

			RComputeQualityIndicators<UMLRSolution, List<UMLRSolution>> qualityIndicator = new RComputeQualityIndicators<UMLRSolution, List<UMLRSolution>>(
					experiment);
			qualityIndicator.run();


		} catch (Exception e) {
			e.printStackTrace();
		}

		return refFront;

	}

	public static List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> configureAlgorithmList(
			List<ExperimentProblem<UMLRSolution>> problemList, int eval) {

		List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algorithms = new ArrayList<>();
		FactoryBuilder<UMLRSolution> fBuilder = new FactoryBuilder<>();
		final CrossoverOperator<UMLRSolution> crossoverOperator = new UMLRCrossover(
				Configurator.eINSTANCE.getXoverProbabiliy());
		final SolutionListEvaluator<UMLRSolution> solutionListEvaluator = new UMLRSolutionListEvaluator<>();

		String algo = Configurator.eINSTANCE.getAlgorithm();

		for(ExperimentProblem<UMLRSolution> expProblem : problemList) {
			algorithms.addAll(fBuilder.configureAlgorithmList(expProblem,eval,crossoverOperator,solutionListEvaluator, algo));
		}

		return algorithms;

	}

	public static RProblem<UMLRSolution> createProblems(Path modelPath, int eval) {

		double probPas = Configurator.eINSTANCE.getProbPas();

		String brf = Configurator.eINSTANCE.getBrfList().toString().replace(":", "_").replace(",", "__")
				.replace(" ", "").replace("[", "").replace("]", "");
		String pName = String.format("%s__BRF_%s__MaxEval_%d__ProbPAs_%.2f__sb_%s_sbth_%s__Algo_%s",
				modelPath.getName(modelPath.getNameCount() - 2), brf, eval, probPas,
				Configurator.eINSTANCE.getSearchBudget(), Configurator.eINSTANCE.getSearchBudgetThreshold(),
				Configurator.eINSTANCE.getAlgorithm());

		return new UMLRProblem<>(modelPath, pName);
	}

}
