package it.univaq.disim.sealab.metaheuristic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
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

import it.univaq.disim.sealab.easier.uml.utils.UMLMemoryOptimizer;
import it.univaq.disim.sealab.easier.uml.utils.XMLUtil;
import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.epsilon.etl.ETLStandalone;
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
				
				if(!m.resolve("output.xml").toFile().exists()) {
					Path sourceModelPath = m.resolve("train-ticket.uml");
					applyTransformation(sourceModelPath);
					invokeSolver(sourceModelPath);
				}
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
	
	
	private static void applyTransformation(Path sourceModelPath) {

		System.out.print("Applying transformation ... ");
		EasierUmlModel uml = null;
		ETLStandalone executor = null;
		String uml2lqnModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-uml2lqn", "org.univaq.uml2lqn").toString();
		try {
			uml = EpsilonStandalone.createUmlModel(sourceModelPath.toString());

			executor = new ETLStandalone(sourceModelPath.getParent());
			executor.setSource(Paths.get(uml2lqnModule, "uml2lqn.etl"));
			executor.setModel(uml);
			executor.setModel(
					executor.createXMLModel(
							"LQN", sourceModelPath.getParent().resolve("output.xml"), org.eclipse.emf.common.util.URI
									.createFileURI(Paths.get(uml2lqnModule, "lqnxsd", "lqn.xsd").toString()),
							false, true));

			executor.execute();
			executor.clearMemory();


		} catch (Exception e) {
			System.err.println("Error in runnig the ETL transformation");
			e.printStackTrace();
		}finally {
			UMLMemoryOptimizer.cleanup();
			uml = null;
			executor = null;
		}
		System.out.println("done");
	}
	
	private static void invokeSolver(Path sourceModelPath) {
		System.out.print("Invoking LQN Solver ... ");// Remove comments for the real invocation");

		Path lqnSolverPath = Configurator.eINSTANCE.getSolver();
		Path lqnModelPath = sourceModelPath.getParent().resolve("output.xml");

		XMLUtil.conformanceChecking(lqnModelPath);

		// to allow cycles in the lqn model
		final String params = "-P cycles=yes";

		Process process = null;
		try {
			process = new ProcessBuilder(lqnSolverPath.toString(), params, lqnModelPath.toString()).start();
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		

		process = null;

		System.out.println("done");

		System.out.print("Invoking the back annotator... ");
		backAnnotation(sourceModelPath);
		System.out.println("done");
	}
	
	
	private static void backAnnotation(Path sourceModelPath) {

		EOLStandalone bckAnn = new EOLStandalone();

		try {
			EasierUmlModel uml = EpsilonStandalone.createUmlModel(sourceModelPath.toString());

			bckAnn.setModel(uml);

			String uml2lqnModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
					"easier-uml2lqn", "org.univaq.uml2lqn").toString();
			
			bckAnn.setSource(Paths.get(uml2lqnModule, "backAnnotation.eol"));

			// Points to lqn schema file and stores pacakges into the global package
			// registry
			XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
			String schema = Configurator.eINSTANCE.getUml2Lqn().resolve("org.univaq.uml2lqn").resolve("lqnxsd")
					.resolve("lqn.xsd").toString();
			Collection<EObject> generatedPackages = xsdEcoreBuilder
					.generate(org.eclipse.emf.common.util.URI.createURI(schema));
			for (EObject generatedEObject : generatedPackages) {
				if (generatedEObject instanceof EPackage) {
					EPackage generatedPackage = (EPackage) generatedEObject;
					EPackage.Registry.INSTANCE.put(generatedPackage.getNsURI(), generatedPackage);
				}
			}
			bckAnn.setModel(bckAnn.createPlainXMLModel("LQXO", sourceModelPath.getParent().resolve("output.lqxo"), true, false, true));
			bckAnn.execute();

		} catch (URISyntaxException | EolRuntimeException e) {
			e.printStackTrace();
		}finally {
			bckAnn.clearMemory();
			UMLMemoryOptimizer.cleanup();
			bckAnn = null;
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
