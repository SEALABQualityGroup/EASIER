package it.univaq.disim.sealab.metaheuristic;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateWilcoxonTestTablesWithR;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import com.beust.jcommander.JCommander;

import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExecuteAlgorithms;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperiment;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperimentBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.UMLRExecuteAlgorithms;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.GenerateLatexTablesWithComputingTime;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RComputeQualityIndicators;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RGenerateReferenceParetoFront;
import it.univaq.disim.sealab.metaheuristic.evolutionary.factory.FactoryBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAIIBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RMutation;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RSolutionListEvaluator;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.UMLRCrossover;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2Builder;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class Launcher {

	public static void main(String[] args) {

		JCommander jc = new JCommander();

		jc.addObject(Configurator.eINSTANCE);
		jc.parse(args);

		List<Path> referenceFront = new ArrayList<>();

		if (Configurator.eINSTANCE.getReferenceFront() != null)
			referenceFront = Configurator.eINSTANCE.getReferenceFront();

		else {
			List<Path> modelsPath = new ArrayList<>();

			if (Configurator.eINSTANCE.getModelsPath().get(0).toFile().isFile()) {
				// use the solution csv file to extract more models
				modelsPath.addAll(FileUtils.extractModelPaths(Configurator.eINSTANCE.getModelsPath().get(0),
						Configurator.eINSTANCE.getMaxWorseModels()));
			} else {
				modelsPath.addAll(Configurator.eINSTANCE.getModelsPath());
			}
			int i = 1;
			for (Path m : modelsPath) {
				System.out.println("Number of source model");
				ProgressBar.showBar(i, modelsPath.size());
				List<RProblem<UMLRSolution>> rProblems = createProblems(m);
				List<GenericIndicator<UMLRSolution>> qIndicators = new ArrayList<>();

				FactoryBuilder<UMLRSolution> factory = new FactoryBuilder<>();
				for (String qI : Configurator.eINSTANCE.getQualityIndicators()) {
					GenericIndicator<UMLRSolution> ind = factory.createQualityIndicators(qI);
					if (ind != null)
						qIndicators.add(ind);
				}

				referenceFront = runExperiment(rProblems, qIndicators);

			}
			i++;
		}
	}

	public static List<Path> runExperiment(final List<RProblem<UMLRSolution>> rProblems,
			final List<GenericIndicator<UMLRSolution>> qualityIndicators) {
		final int INDEPENDENT_RUNS = Configurator.eINSTANCE.getIndependetRuns(); // should be 31 or 51
		final int CORES = 1;

		List<ExperimentProblem<UMLRSolution>> problemList = new ArrayList<>();

		rProblems.forEach(problem -> problemList.add(new ExperimentProblem<UMLRSolution>(problem)));

		List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algorithmList = configureAlgorithmList(problemList);

		Path referenceFrontDirectory = Paths.get(Configurator.eINSTANCE.getOutputFolder().toString(), "referenceFront");

		Experiment<UMLRSolution, List<UMLRSolution>> experiment = new RExperimentBuilder<UMLRSolution, List<UMLRSolution>>(
				"Exp").setAlgorithmList(algorithmList).setProblemList(problemList)
						.setExperimentBaseDirectory(referenceFrontDirectory.toString())
						.setReferenceFrontDirectory(referenceFrontDirectory.toString())
						.setOutputParetoFrontFileName("FUN").setOutputParetoSetFileName("VAR")
						.setIndicatorList(qualityIndicators).setIndependentRuns(INDEPENDENT_RUNS)
						.setNumberOfCores(CORES).build();
		try {
			List<Entry<Algorithm<List<UMLRSolution>>, Long>> computingTimes = new UMLRExecuteAlgorithms<UMLRSolution, List<UMLRSolution>>(
					(RExperiment<UMLRSolution, List<UMLRSolution>>) experiment).run().getComputingTimes();

			((RExperiment<UMLRSolution, List<UMLRSolution>>) experiment).setComputingTime(computingTimes);

			new RGenerateReferenceParetoFront(experiment).run();
			new GenerateLatexTablesWithComputingTime(experiment).run();
			new RComputeQualityIndicators<>(experiment).run();
			new GenerateWilcoxonTestTablesWithR<>(experiment).run();
			new GenerateBoxplotsWithR<>(experiment).run();
			new GenerateLatexTablesWithStatistics(experiment).run();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Path> refFront = new ArrayList<>();
		List<String> tags = new ArrayList<>();
		problemList.forEach(p -> tags.add(p.getTag()));
		for (String tag : tags) {
			refFront.add(Paths.get(Configurator.eINSTANCE.getOutputFolder().toString(), "referenceFront", tag + ".rf"));
		}

		return refFront;

	}

	public static List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> configureAlgorithmList(
			List<ExperimentProblem<UMLRSolution>> problemList) {

		List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algorithms = new ArrayList<>();
		final CrossoverOperator<UMLRSolution> crossoverOperator = new UMLRCrossover<>(
				Configurator.eINSTANCE.getXoverProbabiliy());
		final MutationOperator<UMLRSolution> mutationOperator = new RMutation<>(
				Configurator.eINSTANCE.getMutationProbability(), Configurator.eINSTANCE.getDistributionIndex());
		final SelectionOperator<List<UMLRSolution>, UMLRSolution> selectionOpertor = new BinaryTournamentSelection<UMLRSolution>(
				new RankingAndCrowdingDistanceComparator<UMLRSolution>());
		final SolutionListEvaluator<UMLRSolution> solutionListEvaluator = new RSolutionListEvaluator<>();

		for (int i = 0; i < problemList.size(); i++) {

			NSGAIIBuilder<UMLRSolution> customNSGABuilder = new CustomNSGAIIBuilder<UMLRSolution>(
					problemList.get(i).getProblem(), crossoverOperator, mutationOperator).setMaxEvaluations(
							Configurator.eINSTANCE.getMaxEvaluation() * Configurator.eINSTANCE.getPopulationSize())
							.setPopulationSize(Configurator.eINSTANCE.getPopulationSize())
							.setSolutionListEvaluator(solutionListEvaluator);

			NSGAII<UMLRSolution> algorithm = customNSGABuilder.build();
			ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>> exp = new ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>(
					algorithm, algorithm.getName(), problemList.get(i).getTag());

			algorithms.add(exp);
		}

		for (int i = 0; i < problemList.size(); i++) {
			SPEA2Builder<UMLRSolution> spea2Builder = new CustomSPEA2Builder<UMLRSolution>(
					problemList.get(i).getProblem(), crossoverOperator, mutationOperator)
							.setSelectionOperator(selectionOpertor).setSolutionListEvaluator(solutionListEvaluator)
							.setMaxIterations(Configurator.eINSTANCE.getMaxEvaluation())
							.setPopulationSize(Configurator.eINSTANCE.getPopulationSize());

			SPEA2<UMLRSolution> algorithm = spea2Builder.build();
			ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>> exp = new ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>(
					algorithm, algorithm.getName(), problemList.get(i).getTag());
			algorithms.add(exp);
		}
		return algorithms;
	}

	public static List<RProblem<UMLRSolution>> createProblems(Path modelPath) {

		List<RProblem<UMLRSolution>> rProblems = new ArrayList<>();

//		for (Path src : modelsPath) {
		for (Integer l : Configurator.eINSTANCE.getLength()) {
			for (Double w : Configurator.eINSTANCE.getCloningWeight()) {
				for (Integer mc : Configurator.eINSTANCE.getMaxCloning()) {
					if (mc == -1)
						mc = l; // whether mc is -1, mc will be the chromosome's length
					String pName = modelPath.getFileName() + "_Length_" + String.valueOf(l) + "_CloningWeight_"
							+ String.valueOf(w) + "_MaxCloning_" + String.valueOf(mc);
					UMLRProblem<UMLRSolution> p = new UMLRProblem<>(modelPath, l, Configurator.eINSTANCE.getActions(),
							Configurator.eINSTANCE.getAllowedFailures(), Configurator.eINSTANCE.getPopulationSize());
					p.setCloningWeight(w).setMaxCloning(mc).setName(pName);
					rProblems.add(p);
				}
			}
		}
//		}
		return rProblems;
	}

}
