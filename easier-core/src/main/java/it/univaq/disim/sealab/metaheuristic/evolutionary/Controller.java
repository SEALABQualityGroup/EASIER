package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
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
import org.uma.jmetal.runner.AbstractAlgorithmRunner;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoFront;
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
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import it.univaq.disim.sealab.metaheuristic.utils.CSVUtils;
import it.univaq.disim.sealab.metaheuristic.utils.ThresholdUtils;
import it.univaq.disim.sealab.twoeagles_bridge.TwoEaglesBridge;
import it.univaq.from_aemilia_to_qn_plug_in.handlers.GeneratoreModelloAemilia;
import metamodel.mmaemilia.ArchitecturalInteraction;

public class Controller extends AbstractAlgorithmRunner {

	public static boolean append = false;
	public static Logger logger_ = Logger.getLogger(Controller.class.getName());
	public static FileHandler handler;

	private static MetamodelManager metamodelManager;
	private Manager manager;
	private AemiliaAvailabilityManager availabilityManager;

	private Properties prop;

	// private ExecutorService executor;

	private String basePath, sourceFolder;
	// private PerformanceQualityEvaluator perfQuality;

	private int length, numberOfActions, maxEvaluations, populationSize, allowedFailures, independentRuns, numberOfPAs;

	private double crossoverProbability, mutationProbability, distribution_index;
	private double[] workloadRange;
	private String ruleFilePath, ruleTemplateFilePath;
	private RProblem problem;
	private String outputFolder, tmpFolder, paretoFolder, logFolder, availabilityFolder;
	private String twoTowersKernelPath;
	private int maxCloning;
	private Instant startingTime, endingTime;
	private Map<String, List<ArchitecturalInteraction>> sourceModelPAs;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd.HH.mm.ss");
	private String sourceModelPath;
	private String sourceAemPath;
	private String sourceValPath;
	private String sourceRewPath;
	private String sourceRewmappingPath;
	private String sourceBasePath;

	private boolean cleaningTmp = false;
	private static boolean SOR = false;

	private Timestamp timestamp;
	private double cloningWeight;
	private double constChangesWeight;
	private String failureRatesPropertiesFile;
	private String sourceOclFolder;

	private CrossoverOperator<RSolution> crossoverOperator;
	private MutationOperator<RSolution> mutationOperator;
	private SelectionOperator<List<RSolution>, RSolution> selectionOpertor;
	private SolutionListEvaluator<RSolution> solutionListEvaluator;

	public Controller() {
		manager = new Manager(new AemiliaManager(this));
		manager.setController(this);
		availabilityManager = new AemiliaAvailabilityManager(this);
		// perfQuality = new PerformanceQualityEvaluator(manager.getOclManager());
		metamodelManager = manager.getMetamodelManager();
	}

	public Controller(String propertiesFile) {
		this();
		InputStream cfgInputStream = null;

		try {
			handler = new FileHandler("default.log", append);
			setBasePath(new java.io.File(".").getCanonicalPath());
			cfgInputStream = getConfigFile(propertiesFile);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}

		logger_.addHandler(handler);

		// logger_.info("Logger Name: " + logger_.getName());
		// logger_.warning("Can cause IOException");

		setProperties(cfgInputStream);

		if (sourceModelPath == null || sourceModelPath.isEmpty())
			sourceModelPath = sourceFolder + ((AemiliaManager) metamodelManager).getMetamodelFileExtension();
		((AemiliaManager) metamodelManager).setAemiliaModelFilePath(sourceModelPath);

		updateSourceModel();

		sourceModelPAs = getPerfQuality().performanceAntipatternEvaluator(metamodelManager.getModel(), ruleFilePath);
		this.numberOfPAs = 0;
		for (String key : sourceModelPAs.keySet()) {
			this.numberOfPAs += sourceModelPAs.get(key).size();
		}

		// this.problem = new RProblem(sourceBasePath, length, numberOfActions,
		// allowedFailures, populationSize, this);

		crossoverOperator = new RCrossover(crossoverProbability, this);
		mutationOperator = new RMutation(mutationProbability, distribution_index);
		selectionOpertor = new BinaryTournamentSelection<RSolution>(
				new RankingAndCrowdingDistanceComparator<RSolution>());
		solutionListEvaluator = new RSolutionListEvaluator();
	}

	private void generateSourceFiles() {

		if (!new File(sourceAemPath).exists()) {
			metamodelManager.packageRegistering();
			Transformation.GenerateAEMTransformation(sourceModelPath, sourceFolder);
			Controller.logger_.info("generation of source files completed!");
		}
		if (!new File(sourceRewPath).exists()) {
			Transformation.GenerateREWTransformation(sourceModelPath, sourceFolder);
			Controller.logger_.info("mmamelia to rew completed");
		}

		File folder = new File(sourceFolder);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("aem")) {
				File newFile = new File(sourceAemPath);
				file.renameTo(newFile);
			}
			if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("rew")) {
				File newFile = new File(sourceRewPath);
				file.renameTo(newFile);
			}
			if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("val")) {
				File newFile = new File(sourceValPath);
				file.renameTo(newFile);
			}
		}
		folder = null;
		listOfFiles = null;
		checkSourceVal();
		folder = new File(sourceFolder);
		listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile() && FilenameUtils.getExtension(file.getAbsolutePath()).equals("val")) {
				File newFile = new File(sourceValPath);
				file.renameTo(newFile);
			}
		}
	}

	public void run() throws IOException {

		logger_.getHandlers()[0].flush();
		logger_.getHandlers()[0].close();

		startingTime = Instant.now();

		this.problem = new RProblem(sourceBasePath, length, numberOfActions, allowedFailures, populationSize, this);

		timestamp = new Timestamp(System.currentTimeMillis());

		setTmpFolder(tmpFolder + this.getProblem().getName() + "__" + sdf.format(timestamp) + "__" + File.separator);
		setParetoFolder(
				paretoFolder + this.getProblem().getName() + "__" + sdf.format(timestamp) + "__" + File.separator);
		setLogFolder(logFolder + this.getProblem().getName() + "__" + sdf.format(timestamp) + "__" + File.separator);
		setAvailabilityFolder(availabilityFolder + this.getProblem().getName() + "__" + sdf.format(timestamp) + "__"
				+ File.separator);

		new File(getTmpFolder()).mkdirs();
		new File(getParetoFolder()).mkdirs();
		new File(getLogFolder()).mkdirs();

		System.setOut(new PrintStream(new File(this.getLogFolder() + "output.log")));
		System.setErr(new PrintStream(new File(this.getLogFolder() + "error.log")));

		// it.univaq.disim.sealab.metaheuristic.utils.FileUtils.writePropertiesToCSV(getParetoFolder()
		// + getProblem().getName() + "_properties.csv");

		try {
			handler = new FileHandler(this.getLogFolder() + "default.log", append);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		logger_.addHandler(handler);

		Algorithm<List<RSolution>> algorithm = new CustomNSGAII<RSolution>(problem, maxEvaluations, populationSize,
				crossoverOperator, mutationOperator, selectionOpertor, solutionListEvaluator);

		long[] id_s = new long[1];
		id_s[0] = java.lang.Thread.currentThread().getId();

		logger_.info(algorithm.getClass().toString());

		CSVUtils.writeLine(getParetoFolder() + getProblem().getName() + "_solutions.csv",
				Arrays.asList("name", "PAs", "perfQ", "#changes"));

		algorithm.run();
		List<RSolution> population = algorithm.getResult();

		((CustomNSGAII<RSolution>) algorithm).computeCrowdingDistances();
		population = algorithm.getResult();

		Collections.sort(population, new RankingAndCrowdingDistanceComparator<RSolution>());
		Collections.reverse(population);

		endingTime = Instant.now();
		final Duration totalTime = Duration.between(startingTime, endingTime);

		it.univaq.disim.sealab.metaheuristic.utils.FileUtils.writeSolutionSetToCSV(population);
		saveParetoSolution(population);
		generateAvailability();
		cleanTmpFiles();

		getProperties().setProperty("Total Elapsed Time", totalTime.toString());
		logger_.info("Execution ended with no problem in: " + totalTime.toString());

		for (Handler handle : logger_.getHandlers()) {
			handle.flush();
			handle.close();
		}
	}

	public void runExperiment() {
		final int INDEPENDENT_RUNS = this.independentRuns; // should be 31 or 51
		final int CORES = 1;

		List<ExperimentProblem<RSolution>> problemList = new ArrayList<>();
		RProblem p_BGCS = new RProblem(sourceBasePath, length, numberOfActions, allowedFailures, populationSize, this);
		p_BGCS.setName("BGCS");

		problemList.add(new ExperimentProblem<>(p_BGCS));

		List<ExperimentAlgorithm<RSolution, List<RSolution>>> algorithmList = configureAlgorithmList(problemList);

		String referenceFrontDirectory = getParetoFolder() + File.separator + "referenceFront";

		Experiment<RSolution, List<RSolution>> experiment = new ExperimentBuilder<RSolution, List<RSolution>>("Exp")
				.setAlgorithmList(algorithmList).setProblemList(problemList)
				.setExperimentBaseDirectory(referenceFrontDirectory).setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR").setReferenceFrontDirectory(referenceFrontDirectory)
				// .setIndicatorList(Arrays.asList(new Epsilon<RSolution>(), new
				// Spread<RSolution>(),
				// new GenerationalDistance<RSolution>(), new PISAHypervolume<RSolution>(),
				// new InvertedGenerationalDistance<RSolution>(), new
				// GeneralizedSpread<RSolution>(),
				// new InvertedGenerationalDistancePlus<RSolution>()))
				.setIndependentRuns(INDEPENDENT_RUNS).setNumberOfCores(CORES).build();
		try {
			new RExecuteAlgorithms<RSolution, List<RSolution>>(experiment, this).run();
			new GenerateReferenceParetoFront(experiment).run();
			// new ComputeQualityIndicators<>(experiment).run();
			// new GenerateWilcoxonTestTablesWithR<>(experiment).run();
			// new GenerateBoxplotsWithR<>(experiment).run();
			// new GenerateLatexTablesWithStatistics(experiment).run();
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

			customNSGABuilder.setMaxEvaluations(this.maxEvaluations);
			customNSGABuilder.setPopulationSize(this.populationSize);
			customNSGABuilder.setSolutionListEvaluator(solutionListEvaluator);

			NSGAII<RSolution> algorithm = customNSGABuilder.build();
			((CustomNSGAII<RSolution>) algorithm).setName("NSGA_II");

			ExperimentAlgorithm<RSolution, List<RSolution>> exp = new CustomExperimentAlgorithm<RSolution, List<RSolution>>(
					algorithm, problemList.get(i).getTag(), i);
			algorithms.add(exp);
		}

		// for (int i = 0; i < problemList.size(); i++) {
		// @SuppressWarnings("unchecked")
		// CustomSPEA2Builder<RSolution> spea2Builder = (CustomSPEA2Builder<RSolution>)
		// new CustomSPEA2Builder(
		// problemList.get(i).getProblem(), crossoverOperator, mutationOperator)
		// .setSelectionOperator(selectionOpertor).setSolutionListEvaluator(solutionListEvaluator)
		//// .setMaxIterations(Math.toIntExact(this.maxEvaluations /
		// this.populationSize))
		// .setMaxIterations(100)
		// .setPopulationSize(this.populationSize);
		//
		// CustomSPEA2<RSolution> algorithm = (CustomSPEA2<RSolution>)
		// spea2Builder.build();
		// algorithm.setName("SPEA_2");
		// ExperimentAlgorithm<RSolution, List<RSolution>> exp = new
		// CustomExperimentAlgorithm<RSolution, List<RSolution>>(
		// algorithm, problemList.get(i).getTag(), i);
		// algorithms.add(exp);
		// }
		return algorithms;
	}
	
	public synchronized void generateAvailability() {
		File availabilityDir = new File(availabilityFolder);
		try {
			String[] types = { "mmaemilia" };
			FileFilter filter = new FileTypesFilter(types);
			FileUtils.copyDirectory(new File(getParetoFolder()), availabilityDir, filter);

		} catch (IOException e) {
			e.printStackTrace();
		}
		availabilityManager.setFolder(availabilityDir);
		availabilityManager.doAvailability();
	}

	public synchronized void generateAvailability(final String twoTowersKernelPath, final File targetFolder,
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

		tt.sorSRBMC(aemFile.iterator().next().getAbsolutePath(),
				Paths.get(avaFolder.getAbsolutePath(), "ava.rew").toString(),
				Paths.get(avaFolder.getAbsolutePath(), "result").toString());
	}

	private synchronized void saveParetoSolution(List<RSolution> paretoPop) {
		it.univaq.disim.sealab.metaheuristic.utils.FileUtils.writeSolutionSetToCSV(paretoPop);
		for (RSolution solution : paretoPop) {
			File srcDir = new File(solution.getMmaemiliaFolderPath());
			File destDir = new File(getParetoFolder() + solution.getName());
			try {
				FileUtils.copyDirectory(srcDir, destDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void cleanTmpFiles() {
		if (cleaningTmp) {
			try {
				FileUtils.cleanDirectory(new File(tmpFolder));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void setProperties(InputStream inputStream) {
		logger_.info("Set properties is running");
		prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setTwoTowersKernelPath(prop.getProperty("ttKernel"));

		sourceBasePath = getBasePath() + prop.getProperty("sourceBasePath");

		sourceValPath = sourceBasePath + ".val";
		sourceRewPath = sourceBasePath + ".rew";
		sourceRewmappingPath = sourceBasePath + ".rewmapping";
		sourceAemPath = sourceBasePath + ".aem";
		sourceModelPath = sourceBasePath + ".mmaemilia";

		if (sourceFolder == null || sourceFolder.isEmpty()) {
			sourceFolder = getBasePath() + prop.getProperty("sourceFolder");
		}

		if (new File(sourceAemPath).exists() && !new File(sourceModelPath).exists()) {
			GeneratoreModelloAemilia genModel = new GeneratoreModelloAemilia();
			FileInputStream aemFileInputStream;
			try {
				aemFileInputStream = new FileInputStream(new File(sourceAemPath));
				genModel.execute_ase(aemFileInputStream, sourceModelPath, sourceModelPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		if (!new File(sourceAemPath).exists() || !new File(sourceRewPath).exists()
				|| !new File(sourceValPath).exists()) {
			generateSourceFiles();
		}

		if (!new File(sourceValPath).exists()) {
			logger_.warning("[WARNING] SourceValFilePath: " + sourceValPath + " DOES NOT EXIST!!! ");
			((AemiliaManager) metamodelManager)
					.setSourceValFilePath(getBasePath() + "/src/main/resources/models/AemiliaModels/BoA/BoA.aem.val");
		} else {
			((AemiliaManager) metamodelManager).setSourceValFilePath(sourceValPath);
		}

		if (!new File(sourceRewPath).exists()) {
			((AemiliaManager) metamodelManager)
					.setSourceValFilePath(getBasePath() + "/src/main/resources/models/AemiliaModels/BoA/BoA.rew");
		}

		if (!new File(sourceRewPath).exists()) {
			logger_.warning("[WARNING] SourceRewFilePath: " + sourceValPath + "DOES NOT EXIST!!!");
			// logger_.info("sourceRewPath is set to default value");
			((AemiliaManager) metamodelManager)
					.setSourceValFilePath(getBasePath() + "/src/main/resources/models/AemiliaModels/BoA/BoA.rew");
		}

		if (sourceFolder == null || sourceFolder.isEmpty()) {
			sourceFolder = getBasePath() + prop.getProperty("sourceFolder");
		}

		length = Integer.parseInt(prop.getProperty("length"));

		numberOfActions = Integer.parseInt(prop.getProperty("number_of_actions"));

		crossoverProbability = Double.parseDouble(prop.getProperty("p_crossover"));

		mutationProbability = Double.parseDouble(prop.getProperty("p_mutation"));

		distribution_index = Double.parseDouble(prop.getProperty("d_index_mutation"));

		maxEvaluations = Integer.parseInt(prop.getProperty("maxEvaluations"));

		populationSize = Integer.parseInt(prop.getProperty("populationSize"));

		allowedFailures = Integer.parseInt(prop.getProperty("allowed_failures"));

		if (prop.getProperty("logFolder").endsWith(File.separator))
			setLogFolder(prop.getProperty("logFolder"));
		else
			setLogFolder(prop.getProperty("logFolder") + File.separator);

		if (prop.getProperty("tmpFolder").endsWith(File.separator))
			setTmpFolder(prop.getProperty("tmpFolder"));
		else
			setTmpFolder(prop.getProperty("tmpFolder") + File.separator);

		if (prop.getProperty("paretoFolder").endsWith(File.separator))
			setParetoFolder(prop.getProperty("paretoFolder"));
		else
			setParetoFolder(prop.getProperty("paretoFolder") + File.separator);

		if (prop.getProperty("availabilityFolder").endsWith(File.separator))
			availabilityFolder = prop.getProperty("availabilityFolder");
		else
			availabilityFolder = prop.getProperty("availabilityFolder") + File.separator;

		new File(tmpFolder).mkdirs();
		new File(paretoFolder).mkdirs();
		new File(logFolder).mkdirs();
		new File(availabilityFolder).mkdirs();

		sourceOclFolder = getBasePath() + prop.getProperty("sourceOclFolder");

		setRuleTemplateFilePath(getBasePath() + prop.getProperty("rule_template_file_path"));

		setRuleFilePath(getBasePath() + prop.getProperty("rule_file_path"));
		if (!new File(ruleFilePath).exists()) {
			ThresholdUtils.uptodateSingleValueThresholds(sourceOclFolder, sourceModelPath, sourceValPath,
					(AemiliaManager) metamodelManager, this);
		}
		setMaxCloning(Integer.valueOf(prop.getProperty("maxCloning")));

		if (prop.getProperty("workloadRange") != null) {
			String[] workloadRangeString = prop.getProperty("workloadRange").split(";");
			workloadRange = new double[2];
			workloadRange[0] = Double.parseDouble(workloadRangeString[0]);
			workloadRange[1] = Double.parseDouble(workloadRangeString[1]);
		}
		cleaningTmp = Boolean.parseBoolean(prop.getProperty("cleaningTmp", Boolean.toString(false)));
		cloningWeight = Double.parseDouble(prop.getProperty("cloningWeight", Double.toString(1.3)));
		constChangesWeight = Double.parseDouble(prop.getProperty("constChangesWeight", Double.toString(1)));

		failureRatesPropertiesFile = prop.getProperty("failureRatesPropertiesFile");
		independentRuns = Integer.parseInt(prop.getProperty("independent_runs", Integer.toString(31)));

		logger_.info("[INFO] properties has been set.");
	}

	public Properties getProperties() {
		return this.prop;
	}

	private FileInputStream getConfigFile(String filename) throws FileNotFoundException {
		try {
			filename = new java.io.File(".").getCanonicalPath() + filename;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new FileInputStream(filename);
	}

	public PerformanceQualityEvaluator getPerfQuality() {
		return new PerformanceQualityEvaluator(manager.getOclManager());
	}

	public String getRuleFilePath() {
		return ruleFilePath;
	}

	public void setRuleFilePath(String ruleFilePath) {
		this.ruleFilePath = ruleFilePath;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public RProblem getProblem() {
		return this.problem;
	}

	public void setProblem(RProblem p) {
		this.problem = p;
	}

	public String getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	public String getTmpFolder() {
		return tmpFolder;
	}

	public void setTmpFolder(String tmp) {
		this.tmpFolder = tmp;
	}

	public String getTwoTowersKernelPath() {
		return twoTowersKernelPath;
	}

	public void setTwoTowersKernelPath(String twoTowersKernelPath) {
		this.twoTowersKernelPath = twoTowersKernelPath;
	}

	public void checkTwoTowersOutput(String valFilePath) {
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(valFilePath);
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

	public void copyModel(final String destinationPath) {
		File source = new File(sourceModelPath);
		File dest = new File(destinationPath);
		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getSourceRewPath() {
		return sourceRewPath;
	}

	public String getSourceValPath() {
		return sourceValPath;
	}

	public void setSourceRewPath(String sourceRewPath) {
		this.sourceRewPath = sourceRewPath;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public int getMaxCloning() {
		return maxCloning;
	}

	public void setMaxCloning(final int maxCloning) {
		this.maxCloning = maxCloning;
	}

	private void updateSourceModel() {
		if (!new File(sourceRewmappingPath).exists()) {
			checkSourceVal();
			((AemiliaManager) metamodelManager).aemiliaModelUpdate(sourceValPath, sourceRewPath, sourceRewmappingPath,
					sourceModelPath, null);
			((AemiliaManager) metamodelManager).refreshModel(sourceModelPath);
			logger_.info("source model UPDATED!!");
		} else {
			logger_.info("source model already UPDATED!!");
		}
	}

	public String getSourceModelPath() {
		return sourceModelPath;
	}

	public void setSourceModelPath(final String sourceModelPath) {
		this.sourceModelPath = sourceModelPath;
	}

	private void checkSourceVal() {
		if (!new File(sourceValPath).exists()) {
			((AemiliaManager) manager.getMetamodelManager()).gaussianEliminationSRBMC(sourceAemPath, sourceRewPath,
					sourceFolder + "/ttresult");
			if (!new File(sourceValPath).exists()) {
				checkTwoTowersOutput(sourceFolder + "/ttresult");
			}
		}
	}

	public String getRuleTemplateFilePath() {
		return ruleTemplateFilePath;
	}

	public void setRuleTemplateFilePath(final String ruleTemplateFilePath) {
		this.ruleTemplateFilePath = ruleTemplateFilePath;
	}

	public double getWorkloadRange() {
		if (workloadRange != null)
			return JMetalRandom.getInstance().nextDouble(workloadRange[0], workloadRange[1]);
		return -1;
	}

	public String getLogFolder() {
		return logFolder;
	}

	public void setLogFolder(final String logFolder) {
		this.logFolder = logFolder;
	}

	public void setAvailabilityFolder(final String availabilityFolder) {
		this.availabilityFolder = availabilityFolder;
	}

	public String getParetoFolder() {
		return paretoFolder;
	}

	public void setParetoFolder(final String paretoFolder) {
		this.paretoFolder = paretoFolder;
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

	public double getCloningWeight() {
		return cloningWeight;
	}

	public double getConstChangesWeight() {
		return constChangesWeight;
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
	
	public String getPermanentTmpFolder() {
		
		return getProperties().getProperty("permanentTmpFolder");
	}

}
