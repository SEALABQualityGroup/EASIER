package it.univaq.disim.sealab.metaheuristic;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
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
import org.uma.jmetal.lab.experiment.component.impl.GenerateBoxplotsWithR;
import org.uma.jmetal.lab.experiment.component.impl.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.lab.experiment.component.impl.GenerateWilcoxonTestTablesWithR;
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
import it.univaq.disim.sealab.easier.uml.utils.XMLUtil;
import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.epsilon.etl.ETLStandalone;
import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperiment;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperimentAlgorithm;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperimentBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.UMLRExecuteAlgorithms;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.GenerateLatexTablesWithComputingTime;
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

	private static Map<String, List<Double>> qualityIndicatorsMap;

	static {
		qualityIndicatorsMap = new HashMap<>();
	}

	public static void main(String[] args) {

		JCommander jc = new JCommander();

		jc.addObject(Configurator.eINSTANCE);
		jc.parse(args);

		List<Path> referenceFront = new ArrayList<>();
		double qThreshold = 0.1;

		if (Configurator.eINSTANCE.getReferenceFront() != null)
			referenceFront = Configurator.eINSTANCE.getReferenceFront();

		else {
			List<Path> modelsPath = new ArrayList<>();

			/*
			 * if (Configurator.eINSTANCE.getModelsPath().get(0).toFile().isFile()) { // use
			 * the solution csv file to extract more models
			 * modelsPath.addAll(FileUtils.extractModelPaths(Configurator.eINSTANCE.
			 * getModelsPath().get(0), Configurator.eINSTANCE.getMaxWorseModels())); } else
			 * { modelsPath.addAll(Configurator.eINSTANCE.getModelsPath()); }
			 */
			modelsPath.addAll(Configurator.eINSTANCE.getModelsPath());
			int i = 1;
			int[] eval = Configurator.eINSTANCE.getMaxEvaluation().stream().mapToInt(e -> e).toArray();

			for (Path m : modelsPath) {
				System.out.println("Number of source model");
				ProgressBar.showBar(i, modelsPath.size());
				for (int j = 0; j < eval.length; j++) {
					List<RProblem<UMLRSolution>> rProblems = createProblems(m, eval[j]);

					if (!m.getParent().resolve("output.xml").toFile().exists()) {
						Path sourceModelPath = m;
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
					referenceFront = runExperiment(rProblems, qIndicators, eval[j]);
					new UMLMemoryOptimizer().cleanup();
					System.gc();
				}
				i++;
			}
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
		} catch (EolModelLoadingException | URISyntaxException e) {
			System.err.println("Error in runnig the ETL transformation");
			e.printStackTrace();
		}

		executor = new ETLStandalone(sourceModelPath.getParent());
		executor.setSource(Paths.get(uml2lqnModule, "uml2lqn.etl"));
		executor.setModel(uml);
		executor.setModel(executor.createXMLModel("LQN", sourceModelPath.getParent().resolve("output.xml"),
				org.eclipse.emf.common.util.URI.createFileURI(Paths.get(uml2lqnModule, "lqnxsd", "lqn.xsd").toString()),
				false, true));

		try {
			executor.execute();
		} catch (EolRuntimeException e) {
			System.err.println("Error in runnig the ETL transformation");
			e.printStackTrace();
		}
		executor.clearMemory();

		// finally {
		new UMLMemoryOptimizer().cleanup();
		uml = null;
		executor = null;
		// }
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
		EasierUmlModel uml = null;

		try {
			uml = EpsilonStandalone.createUmlModel(sourceModelPath.toString());
		} catch (URISyntaxException | EolRuntimeException e) {
			e.printStackTrace();
		}

		bckAnn.setModel(uml);

		String uml2lqnModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-uml2lqn", "org.univaq.uml2lqn").toString();

		bckAnn.setSource(Paths.get(uml2lqnModule, "backAnnotation.eol"));

		// Points to lqn schema file and stores pacakges into the global package
		// registry
		XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
		String schema = Paths.get(uml2lqnModule, "lqnxsd", "lqn.xsd").toString();
//		String schema = Configurator.eINSTANCE.getUml2Lqn().resolve("org.univaq.uml2lqn").resolve("lqnxsd")
//				.resolve("lqn.xsd").toString();
		Collection<EObject> generatedPackages = xsdEcoreBuilder
				.generate(org.eclipse.emf.common.util.URI.createURI(schema));
		for (EObject generatedEObject : generatedPackages) {
			if (generatedEObject instanceof EPackage) {
				EPackage generatedPackage = (EPackage) generatedEObject;
				EPackage.Registry.INSTANCE.put(generatedPackage.getNsURI(), generatedPackage);
			}
		}
		bckAnn.setModel(bckAnn.createPlainXMLModel("LQXO", sourceModelPath.getParent().resolve("output.lqxo"), true,
				false, true));

		try {
			bckAnn.execute();
		} catch (EolRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bckAnn.clearMemory();
		new UMLMemoryOptimizer().cleanup();
		bckAnn = null;

	}

	public static List<Path> runExperiment(final List<RProblem<UMLRSolution>> rProblems,
			final List<GenericIndicator<UMLRSolution>> qualityIndicators, int eval) {
		final int INDEPENDENT_RUNS = Configurator.eINSTANCE.getIndependetRuns(); // should be 31 or 51
		final int CORES = 1;

		List<Path> refFront = new ArrayList<>();

		List<ExperimentProblem<UMLRSolution>> problemList = new ArrayList<>();

		rProblems.forEach(problem -> problemList.add(new ExperimentProblem<UMLRSolution>(problem)));

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
			qualityIndicatorsMap = qualityIndicator.getIndicatorsMap();

//			new GenerateLatexTablesWithComputingTime(experiment).run();
//			new GenerateWilcoxonTestTablesWithR<>(experiment).run();
//			new GenerateBoxplotsWithR<>(experiment).run();
//			new GenerateLatexTablesWithStatistics(experiment).run();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return refFront;

	}

	public static List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> configureAlgorithmList(
			List<ExperimentProblem<UMLRSolution>> problemList, int eval) {

		List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algorithms = new ArrayList<>();
		final CrossoverOperator<UMLRSolution> crossoverOperator = new UMLRCrossover<>(
				Configurator.eINSTANCE.getXoverProbabiliy());
		final MutationOperator<UMLRSolution> mutationOperator = new RMutation<>(
				Configurator.eINSTANCE.getMutationProbability(), Configurator.eINSTANCE.getDistributionIndex());
		final SelectionOperator<List<UMLRSolution>, UMLRSolution> selectionOpertor = new BinaryTournamentSelection<UMLRSolution>(
				new RankingAndCrowdingDistanceComparator<UMLRSolution>());
		final SolutionListEvaluator<UMLRSolution> solutionListEvaluator = new UMLRSolutionListEvaluator<>();

		for (String algo : Configurator.eINSTANCE.getAlgorithms()) {
			for (int i = 0; i < problemList.size(); i++) {
//			for (int j : Configurator.eINSTANCE.getMaxEvaluation()) {
				if ("NSGA-II".equals(algo)) {
					for (int j = 0; j < Configurator.eINSTANCE.getIndependetRuns(); j++) {

						NSGAIIBuilder<UMLRSolution> customNSGABuilder = new CustomNSGAIIBuilder<UMLRSolution>(
								problemList.get(i).getProblem(), crossoverOperator, mutationOperator,
								Configurator.eINSTANCE.getPopulationSize())
										.setMaxEvaluations(eval * Configurator.eINSTANCE.getPopulationSize())
										.setSolutionListEvaluator(solutionListEvaluator);

						NSGAII<UMLRSolution> algorithm = customNSGABuilder.build();

						ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>> exp = new RExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>(
								algorithm, algorithm.getName(), problemList.get(i), j);

						algorithms.add(exp);
					}
				} else if ("SPEA2".equals(algo)) {

					for (int j = 0; j < Configurator.eINSTANCE.getIndependetRuns(); j++) {
						SPEA2Builder<UMLRSolution> spea2Builder = new CustomSPEA2Builder<UMLRSolution>(
								problemList.get(i).getProblem(), crossoverOperator, mutationOperator)
										.setSelectionOperator(selectionOpertor)
										.setSolutionListEvaluator(solutionListEvaluator).setMaxIterations(eval)
										.setPopulationSize(Configurator.eINSTANCE.getPopulationSize());

						SPEA2<UMLRSolution> algorithm = spea2Builder.build();
						ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>> exp = new RExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>(
								algorithm, algorithm.getName(), problemList.get(i), j);
						algorithms.add(exp);
					}

				} else if ("R-NSGA".equals(algo)) {

					if (Configurator.eINSTANCE.getReferencePoints().size()
							% Configurator.eINSTANCE.getObjectives() == 0) {

						for (int j = 0; j < Configurator.eINSTANCE.getIndependetRuns(); j++) {
							RNSGAIIBuilder<UMLRSolution> rnsgaBuilder = new CustomRNSGAIIBuilder<UMLRSolution>(
									problemList.get(i).getProblem(), crossoverOperator, mutationOperator,
									Configurator.eINSTANCE.getReferencePoints(), Configurator.eINSTANCE.getEpsilon())
											.setPopulationSize(Configurator.eINSTANCE.getPopulationSize())
											.setMatingPoolSize(Configurator.eINSTANCE.getPopulationSize())
											.setOffspringPopulationSize(Configurator.eINSTANCE.getPopulationSize())
											.setSolutionListEvaluator(solutionListEvaluator)
											.setMaxEvaluations(eval * Configurator.eINSTANCE.getPopulationSize());

							RNSGAII<UMLRSolution> algorithm = rnsgaBuilder.build();
							ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>> exp = new RExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>(
									algorithm, algorithm.getName(), problemList.get(i), j);
							algorithms.add(exp);
						}
					} else {
						throw new RuntimeException("Reference points must be multiple of the number of objectives!!!");

					}

				} else if ("PESA2".equals(algo)) {
					// as reported at
					// https://github.com/jMetal/jMetal/blob/master/jmetal-algorithm/src/main/java/org/uma/jmetal/algorithm/multiobjective/pesa2/PESA2Builder.java
					// we set biSection to 5, and populationSize = archiveSize
					int biSections = 5;
					for (int j = 0; j < Configurator.eINSTANCE.getIndependetRuns(); j++) {
						PESA2Builder<UMLRSolution> pesaBuilder = new CustomPESA2Builder<UMLRSolution>(
								problemList.get(i).getProblem(), crossoverOperator, mutationOperator)
										.setPopulationSize(Configurator.eINSTANCE.getPopulationSize())
										.setArchiveSize(Configurator.eINSTANCE.getPopulationSize())
										.setBisections(biSections)
										.setMaxEvaluations(eval * Configurator.eINSTANCE.getPopulationSize())
										.setSolutionListEvaluator(solutionListEvaluator);
						PESA2<UMLRSolution> algorithm = pesaBuilder.build();
						ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>> exp = new RExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>(
								algorithm, algorithm.getName(), problemList.get(i), j);
						algorithms.add(exp);

					}
				}
			}
		}

		return algorithms;
	}

	public static List<RProblem<UMLRSolution>> createProblems(Path modelPath, int eval) {

		List<RProblem<UMLRSolution>> rProblems = new ArrayList<>();
		
		float probPas = Configurator.eINSTANCE.getProbPas();

//		for (Integer eval : Configurator.eINSTANCE.getMaxEvaluation()) {
		for (Integer l : Configurator.eINSTANCE.getLength()) {
			for (Double w : Configurator.eINSTANCE.getCloningWeight()) {
				for (Integer mc : Configurator.eINSTANCE.getMaxCloning()) {
					if (mc == -1)
						mc = l; // whether mc is -1, mc will be the chromosome's length
//						String pName = modelPath.getName(modelPath.getNameCount() - 2) + "_Length_" + String.valueOf(l)
//								+ "_CloningWeight_" + String.valueOf(w) + "_MaxCloning_" + String.valueOf(mc);

					String brf = Configurator.eINSTANCE.getBrfList().toString().replace(":", "_").replace(",", "__")
							.replace(" ", "").replace("[", "").replace("]", "");
					String pName = String.format("%s__BRF_%s__MaxEval_%d__ProbPAs_%.2f",
							modelPath.getName(modelPath.getNameCount() - 2), brf, eval, probPas);
//					String pName = String.format("%s_Length_%d_CloningWeight_%.1f_MaxCloning_%d_MaxEval_%d",
//							modelPath.getName(modelPath.getNameCount() - 2), l, w, mc, eval);

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
