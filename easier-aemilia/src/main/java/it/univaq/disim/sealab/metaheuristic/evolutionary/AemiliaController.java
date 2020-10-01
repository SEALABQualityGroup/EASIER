package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
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

import it.univaq.disim.sealab.epsilon.EpsilonHelper;
import it.univaq.disim.sealab.metaheuristic.availability.AemiliaAvailabilityManager;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExecuteAlgorithms;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperiment;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperimentBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.GenerateLatexTablesWithComputingTime;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RComputeQualityIndicators;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RGenerateReferenceParetoFront;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAII;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAIIBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.AemiliaRCrossover;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RMutation;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RSolutionListEvaluator;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2Builder;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaMetamodelManager;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;
import it.univaq.disim.sealab.twoeagles_bridge.TwoEaglesBridge;
import it.univaq.disim.sealab.metaheuristic.utils.AemiliaFileUtils;
import it.univaq.disim.sealab.metaheuristic.utils.ThresholdUtils;

public class AemiliaController implements Controller {

	public static boolean append = false;

	public static FileHandler handler;

	public static Path METAMODEL_PATH;

	private MetamodelManager metamodelManager;
	private Manager manager;
	private AemiliaAvailabilityManager availabilityManager;
	private AemiliaRProblem<AemiliaRSolution> problem;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd.HH.mm.ss");
	private boolean cleaningTmp = false;
	private static boolean SOR = false;
	private String failureRatesPropertiesFile;

	/* Configurator class */
	private Configurator configurator;

	/* Evolutionary operators */
	private CrossoverOperator<AemiliaRSolution> crossoverOperator;
	private MutationOperator<AemiliaRSolution> mutationOperator;
	private SelectionOperator<List<AemiliaRSolution>, AemiliaRSolution> selectionOpertor;

	private SolutionListEvaluator<AemiliaRSolution> solutionListEvaluator;

	/* Source models */
	// private List<Path> sourceModels = new ArrayList<>();
	private List<SourceModel> sourceModels = new ArrayList<>();
	private List<ExperimentProblem<AemiliaRSolution>> problemList;

	public AemiliaController() {
		manager = new AemiliaManager(new AemiliaMetamodelManager(this));
		manager.setController(this);
		availabilityManager = new AemiliaAvailabilityManager(this);
		// perfQuality = new PerformanceQualityEvaluator(manager.getOclManager());
		metamodelManager = manager.getMetamodelManager();
	}

	public AemiliaController(final Configurator config) {
		this();
		configurator = config;

		configurator.getTmpFolder().toFile().mkdirs();

		METAMODEL_PATH = configurator.getMetamodelPath();

		// Instantiates evolutionary operators
		crossoverOperator = new AemiliaRCrossover<AemiliaRSolution>(config.getXoverProbabiliy(), this);

		mutationOperator = new RMutation<AemiliaRSolution>(config.getMutationProbability(),
				config.getDistributionIndex());

		selectionOpertor = new BinaryTournamentSelection<AemiliaRSolution>(
				new RankingAndCrowdingDistanceComparator<AemiliaRSolution>());

		solutionListEvaluator = new RSolutionListEvaluator<AemiliaRSolution>();
	}

	public AemiliaController setUp(Path path) {
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
			generateSourceFiles(path);
			AemiliaSourceModel model = new AemiliaSourceModel(path);
			model.setNumberOfPerfAp(((AemiliaPerformanceQualityEvaluator) getPerfQuality())
					.performanceAntipatternEvaluatorEpsilon(Paths.get(path.toString(), "model.mmaemilia"),
							Paths.get(path.toString(), "aemilia-pas-checker.evl")));
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

	public CrossoverOperator<?> getXoverOperator() {
		return crossoverOperator;
	}

	public MutationOperator<?> getMutationOperator() {
		return mutationOperator;
	}

	/**
	 * Source is the containing folder
	 * 
	 * @param source
	 */
	private void generateSourceFiles(final Path source) {
		final Path sourceAemPath = source.resolve("model.aem");
		final Path sourceRewPath = source.resolve("model.rew");
		final Path sourceValPath = source.resolve("model.val");
		final Path sourceModelPath = source.resolve("model.mmaemilia");
//		final Path sourceOCLPath = source.resolve("ocl/detectionSingleValuePA.ocl");
		final Path sourceEVLPath = source.resolve("aemilia-pas-checker.evl");

		if (!Files.exists(sourceModelPath) && Files.exists(sourceAemPath)) {
			new TwoEaglesBridge().aemiliaModelGeneration(sourceAemPath, sourceModelPath);
//			System.out.println("The mmaemilia file must exist!!!");
//			return;
		}

		// Generates the aem file from the emf model
		if (!Files.exists(sourceAemPath)) {
			metamodelManager.packageRegistering();

			EpsilonHelper.generateAemFile(sourceModelPath, source);
//			Transformation.GenerateAEMTransformation(sourceModelPath, source);
//			EasierLogger.logger_.info("generation of source files completed!");
		}

		// TODO Generates the REW file from the emf model
		if (!Files.exists(sourceRewPath)) {
			// EpsilonHelper.generateRewFile(sourceModelPath, source);
//			Transformation.GenerateREWTransformation(sourceModelPath, source);
			EasierLogger.logger_.warning("REW generation not yet supported");
		}

		if (!Files.exists(sourceValPath)) {
			if (configurator.isSor())
				((AemiliaMetamodelManager) metamodelManager).sorSRBMC(sourceAemPath, sourceRewPath,
						source.resolve("ttresult"));
			else
				((AemiliaMetamodelManager) metamodelManager).gaussianEliminationSRBMC(sourceAemPath, sourceRewPath,
						source.resolve("ttresult"));

//			org.apache.commons.io.FileUtils.moveFile(source.resolve("model.aem.val").toFile(),sourceValPath.toFile());
			source.resolve("model.aem.val").toFile().renameTo(sourceValPath.toFile());

			if (!checkSourceVal(sourceValPath)) {
				checkTwoTowersOutput(source.resolve("ttresult"));
				System.err.println("The val file must exist, please check TTkernel output file!!!!");
				return;
			}
		}

		if (!Files.exists(sourceEVLPath)) {
			try {
				// copy the EVL template into the solution folder
				org.apache.commons.io.FileUtils.copyFile(configurator.getEVLTemplate().toFile(),
						sourceEVLPath.toFile());
				// fix paths in the copied EVL file
				FileUtils.fillTemplateKeywords(configurator.getEVLTemplate(), sourceEVLPath,
						Stream.of(
								new Object[][] { { "basepath", configurator.getEVLTemplate().getParent().toString() } })
								.collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1])));

				// copy the threshold file into the source folder
				ThresholdUtils.uptodateSingleValueThresholds(source, sourceModelPath, sourceValPath,
						(AemiliaMetamodelManager) metamodelManager, this);

//				EasierLogger.logger_.info("The EVL Template File has been filled and copied");
			} catch (IOException e) {
				System.err.println("Error in copying the evl template file to the source folder.");
				e.printStackTrace();
			}
		}

		// is no longer needed
		// Renaming of generated files by M2T transformation
//		File folder = source.toFile();
//		File[] listOfFiles = folder.listFiles();
//
//		for (File file : listOfFiles) {
//			if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("aem")) {
//				File newFile = sourceAemPath.toFile();
//				// File newFile = new File(sourceAemPath);
//				file.renameTo(newFile);
//			} else if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("rew")) {
//				// File newFile = new File(sourceRewPath);
//				File newFile = sourceRewPath.toFile();
//				file.renameTo(newFile);
//			} else if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("val")) {
//				// File newFile = new File(sourceValPath);
//				File newFile = sourceValPath.toFile();
//				file.renameTo(newFile);
//			}
//		}

//		folder = null;
//		listOfFiles = null;
//		folder = source.toFile();
//		listOfFiles = folder.listFiles();
//		for (File file : listOfFiles) {
//			if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("val")) {
//				File newFile = sourceValPath.toFile();
//				file.renameTo(newFile);
//			}
//		}

		// TODO is no longer used
//		if (!Files.exists(sourceOCLPath)) {
//			ThresholdUtils.uptodateSingleValueThresholds(sourceOCLPath.getParent(), sourceModelPath, sourceValPath,
//					(AemiliaMetamodelManager) metamodelManager, this);
//		}
	}

	public List<RProblem<AemiliaRSolution>> createProblems() {

		List<RProblem<AemiliaRSolution>> rProblems = new ArrayList<>();

		for (SourceModel src : sourceModels) {
			for (int l : configurator.getLength()) {
				for (double w : configurator.getCloningWeight()) {
					for (int mc : configurator.getMaxCloning()) {
						if (mc == -1)
							mc = l; // whether mc is -1, mc will be the chromosome's length
						String pName = src.getFolderName() + "_Length_" + String.valueOf(l) + "_CloningWeight_"
								+ String.valueOf(w) + "_MaxCloning_" + String.valueOf(mc);
						if (configurator.isWorsen())
							pName += "_Worse_";

						AemiliaRProblem<AemiliaRSolution> p = new AemiliaRProblem<AemiliaRSolution>(
								src.getSourceFolder(), l, configurator.getActions(), configurator.getAllowedFailures(),
								configurator.getPopulationSize(), this);
						p.setCloningWeight(w).setMaxCloning(mc).setName(pName);
						rProblems.add(p);
					}
				}
			}
		}
		return rProblems;
	}

	public void runExperiment(final List<RProblem<AemiliaRSolution>> rProblems,
			final List<GenericIndicator<AemiliaRSolution>> qualityIndicators) {
		final int INDEPENDENT_RUNS = configurator.getIndependetRuns(); // should be 31 or 51
		final int CORES = 1;

		problemList = new ArrayList<>();

		rProblems.forEach(problem -> problemList.add(new ExperimentProblem<AemiliaRSolution>(problem)));

		List<ExperimentAlgorithm<AemiliaRSolution, List<AemiliaRSolution>>> algorithmList = configureAlgorithmList(
				problemList);

		Path referenceFrontDirectory = Paths.get(configurator.getOutputFolder().toString(), "referenceFront");

		Experiment<AemiliaRSolution, List<AemiliaRSolution>> experiment = new RExperimentBuilder<AemiliaRSolution, List<AemiliaRSolution>>(
				"Exp").setAlgorithmList(algorithmList).setProblemList(problemList)
						.setExperimentBaseDirectory(referenceFrontDirectory.toString())
						.setReferenceFrontDirectory(referenceFrontDirectory.toString())
						.setOutputParetoFrontFileName("FUN").setOutputParetoSetFileName("VAR")
						.setIndicatorList(qualityIndicators).setIndependentRuns(INDEPENDENT_RUNS)
						.setNumberOfCores(CORES).build();
		try {
			List<Entry<Algorithm<List<AemiliaRSolution>>, Long>> computingTimes = new RExecuteAlgorithms<AemiliaRSolution, List<AemiliaRSolution>>(
					(RExperiment<AemiliaRSolution, List<AemiliaRSolution>>) experiment, this).run().getComputingTimes();

			((RExperiment<AemiliaRSolution, List<AemiliaRSolution>>) experiment).setComputingTime(computingTimes);

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
	public List<ExperimentAlgorithm<AemiliaRSolution, List<AemiliaRSolution>>> configureAlgorithmList(
			List<ExperimentProblem<AemiliaRSolution>> problemList) {

		List<ExperimentAlgorithm<AemiliaRSolution, List<AemiliaRSolution>>> algorithms = new ArrayList<>();

		for (int i = 0; i < problemList.size(); i++) {

			NSGAIIBuilder<AemiliaRSolution> customNSGABuilder = new CustomNSGAIIBuilder<AemiliaRSolution>(
					problemList.get(i).getProblem(), crossoverOperator, mutationOperator)
							.setMaxEvaluations(configurator.getMaxEvaluation() * configurator.getPopulationSize())
							.setPopulationSize(configurator.getPopulationSize())
							.setSolutionListEvaluator(solutionListEvaluator);

			NSGAII<AemiliaRSolution> algorithm = customNSGABuilder.build();
//			((CustomNSGAII<AemiliaRSolution>) algorithm).setName("NSGA_II");

			ExperimentAlgorithm<AemiliaRSolution, List<AemiliaRSolution>> exp = new ExperimentAlgorithm<AemiliaRSolution, List<AemiliaRSolution>>(
					algorithm, algorithm.getName(), problemList.get(i).getTag());

			algorithms.add(exp);
		}

		for (int i = 0; i < problemList.size(); i++) {
			SPEA2Builder<AemiliaRSolution> spea2Builder = new CustomSPEA2Builder<AemiliaRSolution>(
					problemList.get(i).getProblem(), crossoverOperator, mutationOperator)
							.setSelectionOperator(selectionOpertor).setSolutionListEvaluator(solutionListEvaluator)
							.setMaxIterations(configurator.getMaxEvaluation())
							.setPopulationSize(configurator.getPopulationSize());

			SPEA2<AemiliaRSolution> algorithm = spea2Builder.build();
//			((CustomSPEA2<AemiliaRSolution>) algorithm).setName("SPEA2");

			ExperimentAlgorithm<AemiliaRSolution, List<AemiliaRSolution>> exp = new ExperimentAlgorithm<AemiliaRSolution, List<AemiliaRSolution>>(
					algorithm, algorithm.getName(), problemList.get(i).getTag());
			algorithms.add(exp);
		}
		return algorithms;
	}

	public List<Path> getReferenceFront() {

		List<Path> refFront = new ArrayList<>();
		for (ExperimentProblem<AemiliaRSolution> problem : problemList) {
			refFront.add(
					Paths.get(configurator.getOutputFolder().toString(), "referenceFront", problem.getTag() + ".rf"));
		}
		return refFront;
	}

	public synchronized void generateAvailability(final List<String> solIDs) {
		final Path avaPath = Paths.get(configurator.getOutputFolder().toString(), "availability");
		// File availabilityDir = avaPath.toFile();
		avaPath.toFile().mkdirs();
		for (String id : solIDs) {
			try {
				String[] types = { "mmaemilia" };
				FileFilter filter = new FileTypesFilter(types);

				Path solIDFolder = Paths.get(getPermanentTmpFolder().toString(),
						String.valueOf(Integer.valueOf(id) / 100), id);
				Path destDir = Paths.get(avaPath.toString(), id);
				// File paretoFolder = Paths.get(configurator.getOutputFolder().toString(),
				// "pareto").toFile();
				// paretoFolder.mkdirs();
				org.apache.commons.io.FileUtils.copyDirectory(solIDFolder.toFile(), destDir.toFile(), filter);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// availabilityManager.setFolder(availabilityDir);
		availabilityManager.doAvailability(avaPath);
	}

	/**
	 * 
	 * @param targetFolder
	 */
	public synchronized void generateAvailability(final Path targetFolder) {
		// File availabilityDir = avaFolder;
		final Path avaFolder = configurator.getOutputFolder().resolve("availability");
		try {
			String[] types = { "mmaemilia" };
			FileFilter filter = new FileTypesFilter(types);
			org.apache.commons.io.FileUtils.copyDirectory(targetFolder.toFile(), avaFolder.toFile(), filter);

		} catch (IOException e) {
			e.printStackTrace();
		}
		availabilityManager.doAvailability(avaFolder);

		Set<File> aemFile = it.univaq.disim.sealab.metaheuristic.utils.FileUtils.listFilesRecursively(avaFolder,
				".aem");

		final TwoEaglesBridge tt = new TwoEaglesBridge();

		tt.setTwoTowersKernelPath(configurator.getSolver());

		tt.sorSRBMC(aemFile.iterator().next().toPath(), Paths.get(avaFolder.toString(), "ava.rew"),
				Paths.get(avaFolder.toString(), "result"));
	}

	@Deprecated
	public synchronized void generateAvailability(final Path twoTowersKernelPath, final File targetFolder,
			final File avaFolder) {
		// File availabilityDir = avaFolder;
		try {
			String[] types = { "mmaemilia" };
			FileFilter filter = new FileTypesFilter(types);
			org.apache.commons.io.FileUtils.copyDirectory(targetFolder, avaFolder, filter);

		} catch (IOException e) {
			e.printStackTrace();
		}
		availabilityManager.setFolder(avaFolder);
		availabilityManager.doAvailability(avaFolder);

		Set<File> aemFile = it.univaq.disim.sealab.metaheuristic.utils.FileUtils.listFilesRecursively(avaFolder,
				".aem");

		TwoEaglesBridge tt = new TwoEaglesBridge();

		tt.setTwoTowersKernelPath(twoTowersKernelPath);

		tt.sorSRBMC(aemFile.iterator().next().toPath(), Paths.get(avaFolder.toString(), "ava.rew"),
				Paths.get(avaFolder.toString(), "result"));
	}

	private synchronized void saveParetoSolution(List<AemiliaRSolution> paretoPop) {
		AemiliaFileUtils.writeSolutionSetToCSV(paretoPop);
		for (RSolution solution : paretoPop) {
			final File srcDir = new File(((AemiliaRSolution) solution).getMmaemiliaFolderPath());

			final File destDir = Paths
					.get(configurator.getOutputFolder().toString(), "pareto", String.valueOf(solution.getName()))
					.toFile();
			// File destDir = new File(getParetoFolder() + solution.getName());
			try {
				org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void cleanTmpFiles() {
		if (cleaningTmp) {
			try {
				org.apache.commons.io.FileUtils.cleanDirectory(configurator.getTmpFolder().toFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public PerformanceQualityEvaluator getPerfQuality() {
		return new AemiliaPerformanceQualityEvaluator(manager.getOclManager());
	}

	public RProblem<AemiliaRSolution> getProblem() {
		return this.problem;
	}

	public void setProblem(RProblem<AemiliaRSolution> p) {
		this.problem = (AemiliaRProblem<AemiliaRSolution>) p;
	}

	//TODO change valFilePath to twotowers result file
	public void checkTwoTowersOutput(Path ttResultFilePath) {
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(ttResultFilePath.toFile());
			br = new BufferedReader(fr);
			String sCurrentLine = "";

			while ((sCurrentLine = br.readLine()) != null) {
				EasierLogger.logger_.warning(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public Manager getManager() {
		return manager;
	}

	public Configurator getConfigurator() {
		return this.configurator;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	private void updateSourceModel(Path source) {
		final Path sourceAemPath = source.resolve("model.aem");
		final Path sourceRewPath = source.resolve("model.rew");
		final Path sourceValPath = source.resolve("model.val");
		final Path sourceRewmappingPath = source.resolve("model.rewmapping");
		final Path sourceModelPath = source.resolve("model.mmaemilia");

		if (!Files.exists(sourceRewmappingPath)) {
			((AemiliaMetamodelManager) metamodelManager).aemiliaModelUpdate(sourceValPath, sourceRewPath,
					sourceRewmappingPath, sourceModelPath, null);
//			metamodelManager.refreshModel(sourceModelPath);

		} // else {
//			EasierLogger.logger_.info("source model " + sourceModelPath + " already UPDATED!!");
//		}

		if (Files.exists(sourceRewmappingPath)) {
			checkSourceVal(sourceValPath);
			((AemiliaMetamodelManager) metamodelManager).aemiliaModelUpdate(sourceValPath, sourceRewPath,
					sourceRewmappingPath, sourceModelPath, null);
			((AemiliaMetamodelManager) metamodelManager).refreshModel(sourceModelPath);
//			EasierLogger.logger_.info("source model " + sourceModelPath + " updated!!");
		} else {
//			EasierLogger.logger_.info("source model " + sourceModelPath + " already updated!!");
		}
	}

	private boolean checkSourceVal(final Path sourcePath) {
		return Files.exists(sourcePath);
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

	public String getFailureRatesPropertiesFile() {
		if (failureRatesPropertiesFile != null)
			return failureRatesPropertiesFile;
		return "/Users/peo12/git/sealab/easier/easier-availability/src/main/resources/failureRates.properties";
	}

	public void setFailureRatesPropertiesFile(String failureRatesPropertiesFile) {
		this.failureRatesPropertiesFile = failureRatesPropertiesFile;
	}

	public static void setSOR(final boolean sorValue) {
		SOR = sorValue;
	}

	public static boolean isSor() {
		return SOR;
	}

	public Path getPermanentTmpFolder() {
		return Paths.get(configurator.getOutputFolder().toString(), "tmp");
	}

	@Override
	public String getReportFileName() {
		return "";
	}

//	public void generateWorseModels() {
//		List<RProblem<AemiliaRSolution>> problems = createProblems();
//
//		for (RProblem<AemiliaRSolution> rProblem : problems) {
//			rProblem.createSolution();
//		}
//
//	}
}
