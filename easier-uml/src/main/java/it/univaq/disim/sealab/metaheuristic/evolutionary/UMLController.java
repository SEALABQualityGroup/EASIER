package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateWilcoxonTestTablesWithR;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExecuteAlgorithms;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperiment;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperimentBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.GenerateLatexTablesWithComputingTime;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RComputeQualityIndicators;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RGenerateReferenceParetoFront;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAII;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAIIBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RCrossover;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RMutation;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RSolutionListEvaluator;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.UMLRCrossover;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2Builder;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class UMLController implements Controller {

	public static boolean append = false;
	public static Logger logger_ = Logger.getLogger(UMLController.class.getName());
	public static FileHandler handler;

	private MetamodelManager metamodelManager;
	private Manager manager;
	private UMLRProblem<UMLRSolution> problem;
	private boolean cleaningTmp = false;

	/* Configurator class */
	private Configurator configurator;

	/* Evolutionary operators */
	private CrossoverOperator<UMLRSolution> crossoverOperator;
	private MutationOperator<UMLRSolution> mutationOperator;
	private SelectionOperator<List<UMLRSolution>, UMLRSolution> selectionOpertor;
	private SolutionListEvaluator<UMLRSolution> solutionListEvaluator;

	/* Source models */
	// private List<Path> sourceModels = new ArrayList<>();
	private List<SourceModel> sourceModels = new ArrayList<>();
	private List<ExperimentProblem<UMLRSolution>> problemList;

	private final String reportFilePath = "reportFailedSolution.csv";

	public UMLController() {
		manager = new UMLManager(new UMLMetamodelManager(this));
		manager.setController(this);
		// perfQuality = new PerformanceQualityEvaluator(manager.getOclManager());
		metamodelManager = manager.getMetamodelManager();
	}

	public UMLController(final Configurator config) {
		this();
		configurator = config;

		configurator.getTmpFolder().toFile().mkdirs();

		// Instantiates evolutionary operators
		crossoverOperator = new UMLRCrossover(config.getXoverProbabiliy(), this);
		mutationOperator = new RMutation(config.getMutationProbability(), config.getDistributionIndex());
		selectionOpertor = new BinaryTournamentSelection<UMLRSolution>(
				new RankingAndCrowdingDistanceComparator<UMLRSolution>());
		solutionListEvaluator = new RSolutionListEvaluator();
	}

	public UMLController setUp(Path path) {
		sourceModels.clear();
//		List<Path> modelsPath = new ArrayList<>();
//		if(configurator.getModelsPath().get(0).toFile().isFile()) {
//			//use the solution csv file to extract more models 
//			modelsPath.addAll(FileUtils.extractModelPaths(configurator.getModelsPath().get(0), configurator.getMaxWorseModels()));
//		}
//		else {
//			modelsPath.addAll(configurator.getModelsPath());
//		}
//		
//		// setting up the source models
//		for (Path path : modelsPath) {
//			generateSourceFiles(path);
		SourceModel model = new UMLSourceModel(path);
		model.setName("automatedGuidedVehicle");
		model.setExtension(".uml");
		// TODO calc numb of perfAntipattern of the source model
//			model.setNumberOfPerfAp(((AemiliaPerformanceQualityEvaluator) getPerfQuality())
//					.performanceAntipatternEvaluatorEpsilon(Paths.get(path.toString(), "model.mmaemilia"),
//							Paths.get(path.toString(), "aemilia-pas-checker.evl")));
//			model.setSourceModelPAs(
//					((AemiliaPerformanceQualityEvaluator) getPerfQuality()).performanceAntipatternEvaluator(
//							metamodelManager.getModel(Paths.get(path.toString(), "model.mmaemilia")),
//							Paths.get(path.toString(), "ocl", "detectionSingleValuePA.ocl")));

		// generates every needed files and updates the source model

		updateSourceModel(path);
		sourceModels.add(model);
//		}
		metamodelManager.setSourceModels(sourceModels);
		System.out.println("Setting up finished");
		return this;
	}
	
	public String getReportFileName() {
		return reportFilePath;
	}

	public CrossoverOperator<?> getXoverOperator() {
		return crossoverOperator;
	}

	public MutationOperator<?> getMutationOperator() {
		return mutationOperator;
	}

	private void generateSourceFiles(final Path source) {
	}

	public List<RProblem<UMLRSolution>> createProblems() {

		List<RProblem<UMLRSolution>> rProblems = new ArrayList<>();

		for (SourceModel src : sourceModels) {
			for (Integer l : configurator.getLength()) {
				for (Double w : configurator.getCloningWeight()) {
					for (Integer mc : configurator.getMaxCloning()) {
						if (mc == -1)
							mc = l; // whether mc is -1, mc will be the chromosome's length
						String pName = src.getFolderName() + "_Length_" + String.valueOf(l) + "_CloningWeight_"
								+ String.valueOf(w) + "_MaxCloning_" + String.valueOf(mc);
//						RProblem p = new UMLRProblem(src.getSourceFolder(), l, configurator.getActions(),
						RProblem p = new UMLRProblem(src, l, configurator.getActions(),
								configurator.getAllowedFailures(), configurator.getPopulationSize(), this);
						p.setCloningWeight(w).setMaxCloning(mc).setName(pName);
						rProblems.add(p);
					}
				}
			}
		}
		return rProblems;
	}

	public void runExperiment(final List<RProblem<UMLRSolution>> rProblems,
			final List<GenericIndicator<UMLRSolution>> qualityIndicators) {
		final int INDEPENDENT_RUNS = configurator.getIndependetRuns(); // should be 31 or 51
		final int CORES = 1;

		problemList = new ArrayList<>();

		rProblems.forEach(problem -> problemList.add(new ExperimentProblem<UMLRSolution>(problem)));

		List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algorithmList = configureAlgorithmList(problemList);

		Path referenceFrontDirectory = Paths.get(configurator.getOutputFolder().toString(), "referenceFront");

		Experiment<UMLRSolution, List<UMLRSolution>> experiment = new RExperimentBuilder<UMLRSolution, List<UMLRSolution>>(
				"Exp").setAlgorithmList(algorithmList).setProblemList(problemList)
						.setExperimentBaseDirectory(referenceFrontDirectory.toString())
						.setReferenceFrontDirectory(referenceFrontDirectory.toString())
						.setOutputParetoFrontFileName("FUN").setOutputParetoSetFileName("VAR")
						.setIndicatorList(qualityIndicators).setIndependentRuns(INDEPENDENT_RUNS)
						.setNumberOfCores(CORES).build();
		try {
			List<Entry<Algorithm<List<UMLRSolution>>, Long>> computingTimes = new RExecuteAlgorithms<UMLRSolution, List<UMLRSolution>>(
					(RExperiment<UMLRSolution, List<UMLRSolution>>) experiment, this).run().getComputingTimes();

			((RExperiment<UMLRSolution, List<UMLRSolution>>) experiment).setComputingTime(computingTimes);

			// List<AemiliaRSolution> pareto = new ArrayList<>();

			new RGenerateReferenceParetoFront(experiment).run();

			// for(Entry<Algorithm<List<AemiliaRSolution>>, Long> entry : computingTimes) {
			// saveParetoSolution(entry.getKey().getResult());
			// }
			//

			new GenerateLatexTablesWithComputingTime(experiment).run();

			new RComputeQualityIndicators<>(experiment).run();
			new GenerateWilcoxonTestTablesWithR<>(experiment).run();
			new GenerateBoxplotsWithR<>(experiment).run();
			new GenerateLatexTablesWithStatistics(experiment).run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * The algorithm list is composed of pairs {@link Algorithm} + {@link Problem}
	 * which form part of a {@link TaggedAlgorithm}, which is a decorator for class
	 * {@link Algorithm}.
	 *
	 * @param problemList
	 * @return
	 */
	/**
	 * The algorithm list is composed of pairs {@link Algorithm} + {@link Problem}
	 * which form part of a {@link ExperimentAlgorithm}, which is a decorator for
	 * class {@link Algorithm}.
	 */
	public List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> configureAlgorithmList(
			List<ExperimentProblem<UMLRSolution>> problemList) {

		List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algorithms = new ArrayList<>();

		for (int i = 0; i < problemList.size(); i++) {

			NSGAIIBuilder<UMLRSolution> customNSGABuilder = new CustomNSGAIIBuilder<UMLRSolution>(
					problemList.get(i).getProblem(), crossoverOperator, mutationOperator)
							.setMaxEvaluations(configurator.getMaxEvaluation() * configurator.getPopulationSize())
							.setPopulationSize(configurator.getPopulationSize())
							.setSolutionListEvaluator(solutionListEvaluator);

			NSGAII<UMLRSolution> algorithm = customNSGABuilder.build();
//			((CustomNSGAII<AemiliaRSolution>) algorithm).setName("NSGA_II");

			ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>> exp = new ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>(
					algorithm, algorithm.getName(), problemList.get(i).getTag());

			algorithms.add(exp);
		}

		for (int i = 0; i < problemList.size(); i++) {
			SPEA2Builder<UMLRSolution> spea2Builder = new CustomSPEA2Builder<UMLRSolution>(
					problemList.get(i).getProblem(), crossoverOperator, mutationOperator)
							.setSelectionOperator(selectionOpertor).setSolutionListEvaluator(solutionListEvaluator)
							.setMaxIterations(configurator.getMaxEvaluation())
							.setPopulationSize(configurator.getPopulationSize());

			SPEA2<UMLRSolution> algorithm = spea2Builder.build();
//			((CustomSPEA2<AemiliaRSolution>) algorithm).setName("SPEA2");

			ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>> exp = new ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>(
					algorithm, algorithm.getName(), problemList.get(i).getTag());
			algorithms.add(exp);
		}
		return algorithms;
	}

	public List<Path> getReferenceFront() {

		List<Path> refFront = new ArrayList<>();
		for (ExperimentProblem<UMLRSolution> problem : problemList) {
			refFront.add(
					Paths.get(configurator.getOutputFolder().toString(), "referenceFront", problem.getTag() + ".rf"));
		}
		return refFront;
	}

	public synchronized void generateAvailability(final List<String> solIDs) {
	}

	/**
	 * 
	 * @param targetFolder
	 */
	public synchronized void generateAvailability(final Path targetFolder) {
		// File availabilityDir = avaFolder;

	}

	private synchronized void saveParetoSolution(List<RSolution> paretoPop) {
//		it.univaq.disim.sealab.metaheuristic.utils.FileUtils.writeSolutionSetToCSV(paretoPop);
//		for (RSolution solution : paretoPop) {
//			final File srcDir = new File(solution.getMmaemiliaFolderPath());
//
//			final File destDir = Paths
//					.get(configurator.getOutputFolder().toString(), "pareto", String.valueOf(solution.getName()))
//					.toFile();
//			// File destDir = new File(getParetoFolder() + solution.getName());
//			try {
//				FileUtils.copyDirectory(srcDir, destDir);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		// TODO
	}

	private void cleanTmpFiles() {
		if (cleaningTmp) {
			try {
				FileUtils.cleanDirectory(configurator.getTmpFolder().toFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public UMLPerformanceQualityEvaluator getPerfQuality() {
//		return new UMLPerformanceQualityEvaluator(manager.getOclManager());
		// TODO
		return null;
	}

	public RProblem<UMLRSolution> getProblem() {
		return this.problem;
	}

	public void setProblem(UMLRProblem<UMLRSolution> p) {
		this.problem = p;
	}

	public Manager getManager() {
		return manager;
	}

	public Configurator getConfigurator() {
		return (this.configurator != null) ? this.configurator : new Configurator();
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	private void updateSourceModel(Path source) {
	}

	private class FileTypesFilter implements FileFilter {
		String[] types;

		FileTypesFilter(String[] types) {
			this.types = types;
		}

		public boolean accept(File f) {
			if (f.isDirectory())
				return true;
			for (String type : types) {
				if (f.getName().endsWith(type))
					return true;
			}
			return false;
		}
	}

	public void setFailureRatesPropertiesFile(String failureRatesPropertiesFile) {
	}

	public Path getPermanentTmpFolder() {
		return Paths.get(configurator.getOutputFolder().toString(), "tmp");
	}

	public Path getReportFilePath() {
		return configurator.getOutputFolder().resolve(reportFilePath);
	}
}
