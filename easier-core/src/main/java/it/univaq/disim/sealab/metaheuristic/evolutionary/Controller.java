package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.qualityindicator.impl.Epsilon;
import org.uma.jmetal.qualityindicator.impl.GeneralizedSpread;
import org.uma.jmetal.qualityindicator.impl.GenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistancePlus;
import org.uma.jmetal.qualityindicator.impl.Spread;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.runner.AbstractAlgorithmRunner;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.ComputeQualityIndicators;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoFront;
import org.uma.jmetal.util.experiment.component.GenerateWilcoxonTestTablesWithR;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.aemiliaMod2text.main.Transformation;
import it.univaq.disim.sealab.metaheuristic.availability.AemiliaAvailabilityManager;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExecuteAlgorithms;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAII;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAIIBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RCrossover;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RMutation;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RSolutionListEvaluator;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2Builder;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.twoeagles_bridge.TwoEaglesBridge;
import metamodel.mmaemilia.ArchitecturalInteraction;

public class Controller extends AbstractAlgorithmRunner {

	public static boolean append = false;
	public static Logger logger_ = Logger.getLogger(Controller.class.getName());
	public static FileHandler handler;

	// private static MetamodelManager metamodelManager;
	private MetamodelManager metamodelManager;
	private Manager manager;
	private AemiliaAvailabilityManager availabilityManager;

	private Properties prop;

	// private ExecutorService executor;

	// private String basePath, sourceFolder;
	// private PerformanceQualityEvaluator perfQuality;

	// private int length, numberOfActions, maxEvaluations, populationSize,
	// allowedFailures, independentRuns, numberOfPAs;

	// private double crossoverProbability, mutationProbability, distribution_index;
	// private double[] workloadRange;
	// private String ruleFilePath, ruleTemplateFilePath;
	private RProblem problem;
	// private String outputFolder, tmpFolder, paretoFolder, logFolder,
	// availabilityFolder;
	// private String twoTowersKernelPath;
	// private int maxCloning;
	// private Instant startingTime, endingTime;
	// private Map<String, List<ArchitecturalInteraction>> sourceModelPAs;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd.HH.mm.ss");
	// private String sourceModelPath;
	// private String sourceAemPath;
	// private String sourceValPath;
	// private String sourceRewPath;
	// private String sourceRewmappingPath;
	// private String sourceBasePath;

	private boolean cleaningTmp = false;
	private static boolean SOR = false;

	// private Timestamp timestamp;
	// private double cloningWeight;
	// private double constChangesWeight;
	private String failureRatesPropertiesFile;
	// private String sourceOclFolder;

	/* Configurator class */
	private Configurator configurator;

	/* Evolutionary operators */
	private CrossoverOperator<RSolution> crossoverOperator;
	private MutationOperator<RSolution> mutationOperator;
	private SelectionOperator<List<RSolution>, RSolution> selectionOpertor;
	private SolutionListEvaluator<RSolution> solutionListEvaluator;

	/* Source models */
	// private List<Path> sourceModels = new ArrayList<>();
	private List<SourceModel> sourceModels = new ArrayList<>();

	public Controller() {
		manager = new Manager(new AemiliaManager(this));
		manager.setController(this);
		availabilityManager = new AemiliaAvailabilityManager(this);
		// perfQuality = new PerformanceQualityEvaluator(manager.getOclManager());
		metamodelManager = manager.getMetamodelManager();
	}

	public Controller(final Configurator config) {
		this();
		configurator = config;

		configurator.getTmpFolder().toFile().mkdirs();

		// Instantiates evolutionary operators
		crossoverOperator = new RCrossover(config.getXoverProbabiliy(), this);
		mutationOperator = new RMutation(config.getMutationProbability(), config.getDistributionIndex());
		selectionOpertor = new BinaryTournamentSelection<RSolution>(
				new RankingAndCrowdingDistanceComparator<RSolution>());
		solutionListEvaluator = new RSolutionListEvaluator();

		// setting up the source models
		for (Path path : config.getModelsPath()) {
			SourceModel model = new SourceModel(path);
			model.setSourceModelPAs(getPerfQuality().performanceAntipatternEvaluator(
					metamodelManager.getModel(Paths.get(path.toString(), "model.mmaemilia")),
					Paths.get(path.toString(), "ocl", "detectionSingleValuePA.ocl")));

			// generates every needed files and updates the source model
			generateSourceFiles(path);
			updateSourceModel(path);
			sourceModels.add(model);
		}
		metamodelManager.setSourceModels(sourceModels);
		System.out.println("Setting up finished");
	}

	public CrossoverOperator<?> getXoverOperator() {
		return crossoverOperator;
	}

	public MutationOperator<?> getMutationOperator() {
		return mutationOperator;
	}

	private void generateSourceFiles(final Path source) {
		final Path sourceAemPath = source.resolve("model.aem");
		final Path sourceRewPath = source.resolve("model.rew");
		final Path sourceValPath = source.resolve("model.val");
		final Path sourceModelPath = source.resolve("model.mmaemilia");

		if (!Files.exists(sourceModelPath)) {
			System.out.println("The mmaemilia file must exist!!!");
			return;
		}

		if (!Files.exists(sourceAemPath)) {
			metamodelManager.packageRegistering();
			Transformation.GenerateAEMTransformation(sourceModelPath, source);
			Controller.logger_.info("generation of source files completed!");
		}

		if (!Files.exists(sourceRewPath)) {
			Transformation.GenerateREWTransformation(sourceModelPath, source);
			Controller.logger_.info("mmamelia to rew completed");
		}

		if (!Files.exists(sourceValPath)) {
			if (configurator.isSor())
				((AemiliaManager) metamodelManager).sorSRBMC(sourceAemPath, sourceRewPath, source.resolve("ttresult"));
			else
				((AemiliaManager) metamodelManager).gaussianEliminationSRBMC(sourceAemPath, sourceRewPath,
						source.resolve("ttresult"));

			if (!checkSourceVal(source)) {
				checkTwoTowersOutput(source.resolve("ttresult"));
				System.out.println("The val file must exist, please check TTkernel output file!!!!");
				return; // TODO manage the exception
			}
		}

		// Renaming of generated files by M2T transformation
		File folder = source.toFile();
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("aem")) {
				File newFile = sourceAemPath.toFile();
				// File newFile = new File(sourceAemPath);
				file.renameTo(newFile);
			} else if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("rew")) {
				// File newFile = new File(sourceRewPath);
				File newFile = sourceRewPath.toFile();
				file.renameTo(newFile);
			} else if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("val")) {
				// File newFile = new File(sourceValPath);
				File newFile = sourceValPath.toFile();
				file.renameTo(newFile);
			}
		}

		folder = null;
		listOfFiles = null;
		folder = source.toFile();
		listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("val")) {
				File newFile = sourceValPath.toFile();
				file.renameTo(newFile);
			}
		}
	}

	public List<RProblem> createProblems() {

		List<RProblem> rProblems = new ArrayList<>();

		for (SourceModel src : sourceModels) {
			RProblem p = new RProblem(src.getSourceFolder(), configurator.getLength(), configurator.getActions(),
					configurator.getAllowedFailures(), configurator.getPopulationSize(), this);
			p.setName(src.getName());
			rProblems.add(p);
		}

		return rProblems;
	}

	public void runExperiment(final List<RProblem> rProblems, List<GenericIndicator<RSolution>> qualityIndicators) {
		final int INDEPENDENT_RUNS = configurator.getIndependetRuns(); // should be 31 or 51
		final int CORES = 1;

		List<ExperimentProblem<RSolution>> problemList = new ArrayList<>();

		rProblems.forEach(problem -> problemList.add(new ExperimentProblem<>(problem)));

		List<ExperimentAlgorithm<RSolution, List<RSolution>>> algorithmList = configureAlgorithmList(problemList);

		Path referenceFrontDirectory = Paths.get(configurator.getOutputFolder().toString(), "referenceFront");

		Experiment<RSolution, List<RSolution>> experiment = new ExperimentBuilder<RSolution, List<RSolution>>("Exp")
				.setAlgorithmList(algorithmList).setProblemList(problemList)
				.setExperimentBaseDirectory(referenceFrontDirectory.toString())
				.setReferenceFrontDirectory(referenceFrontDirectory.toString()).setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR")
				.setIndicatorList(qualityIndicators)
				.setIndependentRuns(INDEPENDENT_RUNS).setNumberOfCores(CORES).build();
		try {
			new RExecuteAlgorithms<RSolution, List<RSolution>>(experiment, this).run();
			new GenerateReferenceParetoFront(experiment).run();
			new ComputeQualityIndicators<>(experiment).run();
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
	private List<ExperimentAlgorithm<RSolution, List<RSolution>>> configureAlgorithmList(
			List<ExperimentProblem<RSolution>> problemList) {

		List<ExperimentAlgorithm<RSolution, List<RSolution>>> algorithms = new ArrayList<>();

		for (int i = 0; i < problemList.size(); i++) {

			CustomNSGAIIBuilder<RSolution> customNSGABuilder = new CustomNSGAIIBuilder<RSolution>(
					problemList.get(i).getProblem(), crossoverOperator, mutationOperator);

			customNSGABuilder.setMaxEvaluations(configurator.getMaxEvaluation());
			customNSGABuilder.setPopulationSize(configurator.getPopulationSize());
			customNSGABuilder.setSolutionListEvaluator(solutionListEvaluator);

			NSGAII<RSolution> algorithm = customNSGABuilder.build();
			((CustomNSGAII<RSolution>) algorithm).setName("NSGA_II");

			ExperimentAlgorithm<RSolution, List<RSolution>> exp = new CustomExperimentAlgorithm<RSolution, List<RSolution>>(
					algorithm, problemList.get(i).getTag(), i);
			algorithms.add(exp);
		}

		for (int i = 0; i < problemList.size(); i++) {
			@SuppressWarnings("unchecked")
			CustomSPEA2Builder<RSolution> spea2Builder = (CustomSPEA2Builder<RSolution>) new CustomSPEA2Builder(
					problemList.get(i).getProblem(), crossoverOperator, mutationOperator)
							.setSelectionOperator(selectionOpertor).setSolutionListEvaluator(solutionListEvaluator)
							.setMaxIterations(
									Math.toIntExact(configurator.getMaxEvaluation() / configurator.getPopulationSize()))
							.setPopulationSize(configurator.getPopulationSize());

			CustomSPEA2<RSolution> algorithm = (CustomSPEA2<RSolution>) spea2Builder.build();
			algorithm.setName("SPEA_2");
			ExperimentAlgorithm<RSolution, List<RSolution>> exp = new CustomExperimentAlgorithm<RSolution, List<RSolution>>(
					algorithm, problemList.get(i).getTag(), i);
			algorithms.add(exp);
		}
		return algorithms;
	}

	public synchronized void generateAvailability() {
		File availabilityDir = Paths.get(configurator.getOutputFolder().toString(), "availability").toFile();
		availabilityDir.mkdirs();
		try {
			String[] types = { "mmaemilia" };
			FileFilter filter = new FileTypesFilter(types);
			File paretoFolder = Paths.get(configurator.getOutputFolder().toString(), "pareto").toFile();
			paretoFolder.mkdirs();
			FileUtils.copyDirectory(paretoFolder, availabilityDir, filter);

		} catch (IOException e) {
			e.printStackTrace();
		}
		availabilityManager.setFolder(availabilityDir);
		availabilityManager.doAvailability();
	}

	public synchronized void generateAvailability(final Path twoTowersKernelPath, final File targetFolder,
			final File avaFolder) {
		// File availabilityDir = avaFolder;
		try {
			String[] types = { "mmaemilia" };
			FileFilter filter = new FileTypesFilter(types);
			FileUtils.copyDirectory(targetFolder, avaFolder, filter);

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

	// private synchronized void saveParetoSolution(List<RSolution> paretoPop) {
	// it.univaq.disim.sealab.metaheuristic.utils.FileUtils.writeSolutionSetToCSV(paretoPop);
	// for (RSolution solution : paretoPop) {
	// File srcDir = new File(solution.getMmaemiliaFolderPath());
	// File destDir = new File(getParetoFolder() + solution.getName());
	// try {
	// FileUtils.copyDirectory(srcDir, destDir);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }

	private void cleanTmpFiles() {
		if (cleaningTmp) {
			try {
				FileUtils.cleanDirectory(configurator.getTmpFolder().toFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// private FileInputStream getConfigFile(String filename) throws
	// FileNotFoundException {
	// try {
	// filename = new java.io.File(".").getCanonicalPath() + filename;
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return new FileInputStream(filename);
	// }

	public PerformanceQualityEvaluator getPerfQuality() {
		return new PerformanceQualityEvaluator(manager.getOclManager());
	}

	// public String getRuleFilePath() {
	// return ruleFilePath;
	// }

	// public void setRuleFilePath(String ruleFilePath) {
	// this.ruleFilePath = ruleFilePath;
	// }
	//
	// public String getBasePath() {
	// return basePath;
	// }
	//
	// public void setBasePath(String basePath) {
	// this.basePath = basePath;
	// }

	public RProblem getProblem() {
		return this.problem;
	}

	public void setProblem(RProblem p) {
		this.problem = p;
	}

	// public String getOutputFolder() {
	// return outputFolder;
	// }
	//
	// public void setOutputFolder(String outputFolder) {
	// this.outputFolder = outputFolder;
	// }
	//
	// public String getTmpFolder() {
	// return tmpFolder;
	// }

	// public void setTmpFolder(String tmp) {
	// this.tmpFolder = tmp;
	// }
	//
	// public String getTwoTowersKernelPath() {
	// return twoTowersKernelPath;
	// }
	//
	// public void setTwoTowersKernelPath(String twoTowersKernelPath) {
	// this.twoTowersKernelPath = twoTowersKernelPath;
	// }

	public void checkTwoTowersOutput(Path valFilePath) {
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(valFilePath.toFile());
			br = new BufferedReader(fr);
			String sCurrentLine = "";

			while ((sCurrentLine = br.readLine()) != null) {
				logger_.warning(sCurrentLine);
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

	// public String getSourceRewPath() {
	// return sourceRewPath;
	// }
	//
	// public String getSourceValPath() {
	// return sourceValPath;
	// }
	//
	// public void setSourceRewPath(String sourceRewPath) {
	// this.sourceRewPath = sourceRewPath;
	// }

	public Manager getManager() {
		return manager;
	}

	public Configurator getConfigurator() {
		return this.configurator;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	// public int getMaxCloning() {
	// return maxCloning;
	// }
	//
	// public void setMaxCloning(final int maxCloning) {
	// this.maxCloning = maxCloning;
	// }

	private void updateSourceModel(Path source) {
		final Path sourceAemPath = source.resolve("model.aem");
		final Path sourceRewPath = source.resolve("model.rew");
		final Path sourceValPath = source.resolve("model.val");
		final Path sourceRewmappingPath = source.resolve("model.rewmapping");
		final Path sourceModelPath = source.resolve("model.mmaemilia");

		if (!Files.exists(sourceRewmappingPath)) {
			((AemiliaManager) metamodelManager).aemiliaModelUpdate(sourceValPath, sourceRewPath, sourceRewmappingPath,
					sourceModelPath, null);
			metamodelManager.refreshModel(sourceModelPath);
			logger_.info("source model " + sourceModelPath + " UPDATED!!");
		} else {
			logger_.info("source model " + sourceModelPath + " already UPDATED!!");
		}

		if (Files.exists(sourceRewmappingPath)) {
			checkSourceVal(sourceValPath);
			((AemiliaManager) metamodelManager).aemiliaModelUpdate(sourceValPath, sourceRewPath, sourceRewmappingPath,
					sourceModelPath, null);
			((AemiliaManager) metamodelManager).refreshModel(sourceModelPath);
			logger_.info("source model UPDATED!!");
		} else {
			logger_.info("source model already UPDATED!!");
		}
	}

	// public String getSourceModelPath() {
	// return sourceModelPath;
	// }
	//
	// public void setSourceModelPath(final String sourceModelPath) {
	// this.sourceModelPath = sourceModelPath;
	// }

	// @Deprecated
	// private void checkSourceVal() {
	// if (!new File(sourceValPath).exists()) {
	// ((AemiliaManager)
	// manager.getMetamodelManager()).gaussianEliminationSRBMC(sourceAemPath,
	// sourceRewPath,
	// sourceFolder + "/ttresult");
	// if (!new File(sourceValPath).exists()) {
	// checkTwoTowersOutput(sourceFolder + "/ttresult");
	// }
	// }
	// }

	private boolean checkSourceVal(final Path sourcePath) {
		return Files.exists(sourcePath);
	}

	// public String getRuleTemplateFilePath() {
	// return ruleTemplateFilePath;
	// }
	//
	// public void setRuleTemplateFilePath(final String ruleTemplateFilePath) {
	// this.ruleTemplateFilePath = ruleTemplateFilePath;
	// }

	// public double getWorkloadRange() {
	// if (workloadRange != null)
	// return JMetalRandom.getInstance().nextDouble(workloadRange[0],
	// workloadRange[1]);
	// return -1;
	// }
	//
	// public String getLogFolder() {
	// return logFolder;
	// }
	//
	// public void setLogFolder(final String logFolder) {
	// this.logFolder = logFolder;
	// }
	//
	// public void setAvailabilityFolder(final String availabilityFolder) {
	// this.availabilityFolder = availabilityFolder;
	// }

	// public String getParetoFolder() {
	// return paretoFolder;
	// }
	//
	// public void setParetoFolder(final String paretoFolder) {
	// this.paretoFolder = paretoFolder;
	// }

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

	// public double getCloningWeight() {
	// return cloningWeight;
	// }
	//
	// public double getConstChangesWeight() {
	// return constChangesWeight;
	// }

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
}
