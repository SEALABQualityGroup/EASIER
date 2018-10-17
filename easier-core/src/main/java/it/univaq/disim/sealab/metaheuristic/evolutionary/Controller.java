package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.moead.AbstractMOEAD;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.DifferentialEvolutionCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.qualityindicator.impl.Epsilon;
import org.uma.jmetal.qualityindicator.impl.GenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistancePlus;
import org.uma.jmetal.qualityindicator.impl.Spread;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.runner.AbstractAlgorithmRunner;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.ComputeQualityIndicators;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoFront;
import org.uma.jmetal.util.experiment.component.GenerateWilcoxonTestTablesWithR;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.aemiliaMod2text.main.Transformation;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.availability.AemiliaAvailabilityManager;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import it.univaq.disim.sealab.metaheuristic.utils.CSVUtils;
import it.univaq.disim.sealab.metaheuristic.utils.ThresholdUtils;
import it.univaq.from_aemilia_to_qn_plug_in.handlers.GeneratoreModelloAemilia;
import logicalSpecification.actions.AEmilia.AEmiliaCloneAEIAction;
import logicalSpecification.actions.AEmilia.AEmiliaConstChangesAction;
import metamodel.mmaemilia.ArchitecturalInteraction;

public class Controller extends AbstractAlgorithmRunner {

	public static boolean append = false;
	public static Logger logger_ = Logger.getLogger(Controller.class.getName());
	public static FileHandler handler;

	private static MetamodelManager metamodelManager;
	private Manager manager;
	private AemiliaAvailabilityManager availabilityManager;

	private Properties prop;

	private ExecutorService executor;

	private String basePath, sourceFolder;
	private PerformanceQualityEvaluator perfQuality;

	private int length, number_of_actions, maxEvaluations, populationSize, allowed_failures;

	private double crossoverProbability, mutationProbability, distribution_index;
	private double[] workloadRange;
	private String ruleFilePath, ruleTemplateFilePath;
	private RProblem problem;
	private String outputFolder, tmpFolder, paretoFolder, logFolder, availabilityFolder;
	private String twoTowersKernelPath;
	private int maxCloning;
	private Instant startingTime, endingTime;
	private Map<String, List<ArchitecturalInteraction>> sourceModelPAs;
	private int numberOfPAs;

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

	public Controller() {
		manager = new Manager(new AemiliaManager(this));
		manager.setController(this);
		availabilityManager = new AemiliaAvailabilityManager(this);
		perfQuality = new PerformanceQualityEvaluator(manager.getOclManager());
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

		sourceModelPAs = perfQuality.performanceAntipatternEvaluator(metamodelManager.getModel(), ruleFilePath);
		numberOfPAs = 0;
		for (String key : sourceModelPAs.keySet()) {
			numberOfPAs += sourceModelPAs.get(key).size();
		}

		this.problem = new RProblem(sourceBasePath, length, number_of_actions, allowed_failures, populationSize, this);
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

		this.problem = new RProblem(sourceBasePath, length, number_of_actions, allowed_failures, populationSize, this);

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

		// String csvProFile = getParetoFolder() + getProblem().getName() +
		// "properties.csv";
		writePropertiesToCSV(getParetoFolder() + getProblem().getName() + "_properties.csv");

		try {
			handler = new FileHandler(this.getLogFolder() + "default.log", append);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger_.addHandler(handler);

		CrossoverOperator<RSolution> crossover = new RCrossover(crossoverProbability, this);
		MutationOperator<RSolution> mutation = new RMutation(mutationProbability, distribution_index);
		SelectionOperator<List<RSolution>, RSolution> selection = new BinaryTournamentSelection<RSolution>(
				new RankingAndCrowdingDistanceComparator<RSolution>());
		SolutionListEvaluator<RSolution> ev = new RSolutionListEvaluator();
		Algorithm<List<RSolution>> algorithm = new CustomNSGAII<RSolution>(problem, maxEvaluations, populationSize,
				crossover, mutation, selection, ev);

		long[] id_s = new long[1];
		id_s[0] = java.lang.Thread.currentThread().getId();

		logger_.info(algorithm.getClass().toString());

//		FileWriter solutionFileWriter = new FileWriter(getParetoFolder() + getProblem().getName() + "_solutions.csv",
//				true);
//		List<String> line = new ArrayList<String>();
//		line.add("name");
//		line.add("PAs");
//		line.add("perfQ");
//		line.add("numOfChanges");
		// CSVUtils.writeLine(solutionFileWriter, line);
		CSVUtils.writeLine(getParetoFolder() + getProblem().getName() + "_solutions.csv", Arrays.asList("name", "PAs", "perfQ", "#changes"));
//		solutionFileWriter.flush();
//		solutionFileWriter.close();

		algorithm.run();
		List<RSolution> population = algorithm.getResult();

		((CustomNSGAII<RSolution>) algorithm).computeCrowdingDistances();
		population = algorithm.getResult();

		Collections.sort(population, new RankingAndCrowdingDistanceComparator<RSolution>());
		Collections.reverse(population);

		// printFinalSolutionSet(population);
		// if (!referenceParetoFront.equals("")) {
		// printQualityIndicators(population, referenceParetoFront);
		// }

		// logger_.info("Number of non-dominated solutions (sorted by Crowding): " +
		// population.size());

		endingTime = Instant.now();
		Duration totalTime = Duration.between(startingTime, endingTime);

		// logger_.info("Total execution time: " + totalTime.toString().replaceAll(",",
		// ".") + " seconds");

		// try {
//		line = null;
		writeSolutionSetToCSV(population);
		// resultFileWriter.flush();
		// resultFileWriter.close();
		saveParetoSolution(population);
		generateAvailability(population);
		cleanTmpFiles();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		getProperties().setProperty("Total Elapsed Time", totalTime.toString());
		logger_.info("Execution ended with no problem in: " + totalTime.toString());

		for (Handler handle : logger_.getHandlers()) {
			handle.flush();
			handle.close();
		}
	}

	public void testExperiment() {
		final int INDEPENDENT_RUNS = 2; // should be 31 or 51
		final int CORES = 1;

		// List<String> referenceFrontFileNames =
		// Arrays.asList(this.problem.getName()+".Custom_NSGA_II_a.rf");
		List<ExperimentProblem<RSolution>> problemList = new ArrayList<>();
		problemList.add(new ExperimentProblem<>(this.problem)); // experiment.tag=problem.getName()

		List<ExperimentAlgorithm<RSolution, List<RSolution>>> algorithmList = configureAlgorithmList(problemList);

		String referenceFrontDirectory = getParetoFolder() + "referenceFront";

		Experiment<RSolution, List<RSolution>> experiment = new ExperimentBuilder<RSolution, List<RSolution>>("Exp_FTA")
				.setAlgorithmList(algorithmList).setProblemList(problemList)
				.setExperimentBaseDirectory(referenceFrontDirectory).setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR").setReferenceFrontDirectory(referenceFrontDirectory)
				// .setReferenceFrontFileNames(referenceFrontFileNames)

				.setIndicatorList(Arrays.asList(new Epsilon<RSolution>(), new Spread<RSolution>(),
						new GenerationalDistance<RSolution>(), new PISAHypervolume<RSolution>(),
						new InvertedGenerationalDistance<RSolution>(),
						new InvertedGenerationalDistancePlus<RSolution>()))

				.setIndependentRuns(INDEPENDENT_RUNS).setNumberOfCores(CORES).build();

		try {
			new ExecuteAlgorithms<>(experiment).run();
			new GenerateReferenceParetoFront(experiment).run();
			new ComputeQualityIndicators<>(experiment).run();
			new GenerateWilcoxonTestTablesWithR<>(experiment).run();
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
	public List<ExperimentAlgorithm<RSolution, List<RSolution>>> configureAlgorithmList(
			List<ExperimentProblem<RSolution>> problemList) {

		List<ExperimentAlgorithm<RSolution, List<RSolution>>> algorithms = new ArrayList<>();

		// final CrossoverOperator<RSolution> crossover = new
		// RCrossover(crossoverProbability, this);
		final MutationOperator<RSolution> mutation = new RMutation(mutationProbability, distribution_index);
		final SelectionOperator<List<RSolution>, RSolution> selection = new BinaryTournamentSelection<RSolution>(
				new RankingAndCrowdingDistanceComparator<RSolution>());
		final SolutionListEvaluator<RSolution> ev = new RSolutionListEvaluator();

		// for (int i = 0; i < problemList.size(); i++) {
		// double mutationProbability = 1.0 /
		// problemList.get(i).getProblem().getNumberOfVariables();
		// double mutationDistributionIndex = 20.0;
		//
		// Algorithm<List<RSolution>> algorithm = new SMPSOBuilder(
		// (DoubleProblem) problemList.get(i).getProblem(),
		// new CrowdingDistanceArchive<DoubleSolution>(100))
		// .setMutation(new PolynomialMutation(mutationProbability,
		// mutationDistributionIndex))
		// .setMaxIterations(250).setSwarmSize(100)
		// .setSolutionListEvaluator(new
		// SequentialSolutionListEvaluator<DoubleSolution>())
		// .build();
		// algorithms.add(new ExperimentAlgorithm<>(algorithm, problemList.get(i),
		// run));
		// }

		// for (int i = 0; i < problemList.size(); i++) {
		//
		// // final CrossoverOperator<RSolution> crossover = new
		// // RCrossover(crossoverProbability, this);
		//
		// crossoverProbability = crossoverProbability - (i/10);
		//
		// CustomNSGAIIBuilder<RSolution> customNSGABuilder = new
		// CustomNSGAIIBuilder<RSolution>(
		// problemList.get(i).getProblem(),
		// new RCrossover(crossoverProbability, this),
		// mutation);
		//
		// customNSGABuilder.setMaxEvaluations(this.maxEvaluations);
		// customNSGABuilder.setPopulationSize(this.populationSize);
		// customNSGABuilder.setSolutionListEvaluator(new RSolutionListEvaluator());
		//
		// NSGAII<RSolution> algorithm = customNSGABuilder.build();
		// ((CustomNSGAII<RSolution>)algorithm).setName("Custom_NSGA_II_b");
		//
		// ExperimentAlgorithm<RSolution, List<RSolution>> exp = new
		// CustomExperimentAlgorithm<RSolution, List<RSolution>>(
		// algorithm, problemList.get(i).getTag(), i);
		// algorithms.add(exp);
		// }

		for (int i = 0; i < problemList.size(); i++) {

			// final CrossoverOperator<RSolution> crossover = new
			// RCrossover(crossoverProbability, this);
			CustomNSGAIIBuilder<RSolution> customNSGABuilder = new CustomNSGAIIBuilder<RSolution>(
					problemList.get(i).getProblem(), new RCrossover(crossoverProbability, this), mutation);

			customNSGABuilder.setMaxEvaluations(this.maxEvaluations);
			customNSGABuilder.setPopulationSize(this.populationSize);
			customNSGABuilder.setSolutionListEvaluator(new RSolutionListEvaluator());

			NSGAII<RSolution> algorithm = customNSGABuilder.build();
			((CustomNSGAII<RSolution>) algorithm).setName("Custom_NSGA_II");

			ExperimentAlgorithm<RSolution, List<RSolution>> exp = new CustomExperimentAlgorithm<RSolution, List<RSolution>>(
					algorithm, problemList.get(i).getTag(), i);
			algorithms.add(exp);
		}

		// for (int i = 0; i < problemList.size(); i++) {
		// Algorithm<List<RSolution>> algorithm = new
		// MOEADBuilder(problemList.get(i).getProblem(),
		// MOEADBuilder.Variant.MOEAD)
		// .setCrossover(new DifferentialEvolutionCrossover(1.0, 0.5, "rand/1/bin"))
		// .setMutation(new PolynomialMutation(
		// 1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
		// .setMaxEvaluations(25000).setPopulationSize(100).setResultPopulationSize(100)
		// .setNeighborhoodSelectionProbability(0.9).setMaximumNumberOfReplacedSolutions(2)
		// .setNeighborSize(20).setFunctionType(AbstractMOEAD.FunctionType.TCHE).build();
		// algorithms.add(new ExperimentAlgorithm<>(algorithm, problemList.get(i), i));
		// }
		return algorithms;
	}

	private synchronized void generateAvailability(List<RSolution> paretoPop) {
		File availabilityDir = new File(availabilityFolder);
		try {

			String[] types = { "mmaemilia" };
			FileFilter filter = new FileTypesFilter(types);
			FileUtils.copyDirectory(new File(getParetoFolder()), availabilityDir, filter);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		availabilityManager.setFolder(availabilityDir);
		availabilityManager.doAvailability();
	}

	private synchronized void saveParetoSolution(List<RSolution> paretoPop) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setProperties(InputStream inputStream) {
		logger_.info("Set running properties");
		prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString()
				.indexOf("jdwp") >= 0;

		setTwoTowersKernelPath(prop.getProperty("ttKernel"));
		// logger_.info("twoTowersKernelPath is set to " + getTwoTowersKernelPath());

		sourceBasePath = getBasePath() + prop.getProperty("sourceBasePath");

		sourceValPath = sourceBasePath + ".val";
		sourceRewPath = sourceBasePath + ".rew";
		sourceRewmappingPath = sourceBasePath + ".rewmapping";
		sourceAemPath = sourceBasePath + ".aem";
		sourceModelPath = sourceBasePath + ".mmaemilia";

		if (sourceFolder == null || sourceFolder.isEmpty()) {
			sourceFolder = getBasePath() + prop.getProperty("sourceFolder");
		}
		// logger_.info("sourceFolder is set to " + sourceFolder);

		if (new File(sourceAemPath).exists() && !new File(sourceModelPath).exists()) {
			GeneratoreModelloAemilia genModel = new GeneratoreModelloAemilia();
			FileInputStream aemFileInputStream;
			try {
				aemFileInputStream = new FileInputStream(new File(sourceAemPath));
				genModel.execute_ase(aemFileInputStream, sourceModelPath, sourceModelPath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (!new File(sourceAemPath).exists() || !new File(sourceRewPath).exists()
				|| !new File(sourceValPath).exists()) {
			generateSourceFiles();
		}

		if (!new File(sourceValPath).exists()) {
			// logger_.warning("SourceValFilePath: " + sourceValPath + " DOES NOT EXIST");
			// logger_.info("sourceValPath is set to default value");
			((AemiliaManager) metamodelManager)
					.setSourceValFilePath(getBasePath() + "/src/main/resources/models/AemiliaModels/BoA/BoA.aem.val");
		} else {
			((AemiliaManager) metamodelManager).setSourceValFilePath(sourceValPath);
			// logger_.info("SourceValFilePath: " + sourceValPath);
		}

		if (!new File(sourceRewPath).exists()) {
			// logger_.warning("NOT EXISTS SourceRewFilePath: " + sourceValPath);
			// logger_.info("sourceRewPath is set to default value");
			((AemiliaManager) metamodelManager)
					.setSourceValFilePath(getBasePath() + "/src/main/resources/models/AemiliaModels/BoA/BoA.rew");
		} // else {
			// logger_.info("SourceRewFilePath: " + sourceRewPath);
			// }

		if (sourceFolder == null || sourceFolder.isEmpty()) {
			sourceFolder = getBasePath() + prop.getProperty("sourceFolder");
		}
		// logger_.info("sourceFolder is set to " + sourceFolder);

		length = Integer.parseInt(prop.getProperty("length"));
		// logger_.info("length is set to " + length);

		number_of_actions = Integer.parseInt(prop.getProperty("number_of_actions"));
		// logger_.info("number_of_actions is set to " + number_of_actions);

		crossoverProbability = Double.parseDouble(prop.getProperty("p_crossover"));
		// logger_.info("crossoverProbability is set to " + crossoverProbability);

		mutationProbability = Double.parseDouble(prop.getProperty("p_mutation"));
		// logger_.info("mutationProbability is set to " + mutationProbability);

		distribution_index = Double.parseDouble(prop.getProperty("d_index_mutation"));
		// logger_.info("distribution_index is set to " + distribution_index);

		maxEvaluations = Integer.parseInt(prop.getProperty("maxEvaluations"));
		// logger_.info("maxEvaluations is set to " + maxEvaluations);

		populationSize = Integer.parseInt(prop.getProperty("populationSize"));
		// logger_.info("populationSize is set to " + populationSize);

		allowed_failures = Integer.parseInt(prop.getProperty("allowed_failures"));
		// logger_.info("allowed_failures is set to " + allowed_failures);

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

		// logger_.info("outputFolder is set to " + getOutputFolder());

		sourceOclFolder = getBasePath() + prop.getProperty("sourceOclFolder");

		setRuleTemplateFilePath(getBasePath() + prop.getProperty("rule_template_file_path"));
		// logger_.info("rule_template_file_path is set to " +
		// getRuleTemplateFilePath());

		setRuleFilePath(getBasePath() + prop.getProperty("rule_file_path"));
		if (!new File(ruleFilePath).exists()) {
			ThresholdUtils.uptodateSingleValueThresholds(sourceOclFolder, sourceModelPath, sourceValPath,
					(AemiliaManager) metamodelManager, this);
		}
		// logger_.info("rule_file_path is set to " + getRuleFilePath());
		setMaxCloning(Integer.valueOf(prop.getProperty("maxCloning")));
		// logger_.info("max cloning is set to " + getMaxCloning());
		// logger_.info("Starting number of elements: " + populationSize);
		// logger_.info("Debug mode: " + isDebug);

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

		logger_.info("Running properties set with no error");
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
		// logger_.info("Config_file is set to " + filename);
		return new FileInputStream(filename);
	}

	public PerformanceQualityEvaluator getPerfQuality() {
		return perfQuality;
	}

	public void setPerfQuality(PerformanceQualityEvaluator perfQuality) {
		this.perfQuality = perfQuality;
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

	public synchronized void writeSolutionSetToCSV(List<RSolution> population) {
		logger_.info("Writing CSV");
		try {
			List<String> line = new ArrayList<String>();
			// line = Arrays.asList("SolID", "PerQ", "#Changes", "#PAs");
			line.add("SolID");
			line.add("PerQ");
			line.add("#Changes");
			line.add("#PAs");
			for (int i = 0; i < number_of_actions; i++) {
				line.add("ActionTarget");
				line.add("FoC/Null");
			}
			// CSVUtils.writeLine(analyzableCSV, line);
			CSVUtils.writeLine(getParetoFolder() + getProblem().getName() + "_analyzableResults.csv", line);

			for (RSolution solution : population) {
				writeSolutionToCSV(solution);
			}
			// analyzableCSV.flush();
			// analyzableCSV.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger_.info("CSV written");
	}

	private void writePropertiesToCSV(String csvFilePath) {
		try {
			FileWriter resultFileWriter = new FileWriter(csvFilePath);
			for (Object key : getProperties().keySet()) {
				List<String> contents = new ArrayList<>();
				contents.addAll(Arrays.asList(key.toString(), getProperties().getProperty(key.toString())));
				CSVUtils.writeLine(resultFileWriter, contents);
			}
			resultFileWriter.flush();
			resultFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeSolutionToCSV(RSolution solution) throws IOException {
		Refactoring ref = solution.getVariableValue(0).getRefactoring();
		ArrayList<String> line = new ArrayList<String>();

		line.add((maxEvaluations / populationSize) + "-" + populationSize + ":" + solution.getName());
		line.add(String.valueOf(solution.getPerfQ()));
		line.add(String.valueOf(solution.getNumOfChanges()));
		line.add(String.valueOf(solution.getPAs()));

		for (RefactoringAction action : ref.getActions()) {
			if (action.getName() == null) {
				action.setName(action.getClass().getSimpleName());
			}
			String target = action instanceof AEmiliaConstChangesAction
					? ((AEmiliaConstChangesAction) action).getSourceConstInit().getName()
					: ((AEmiliaCloneAEIAction) action).getSourceAEI().getInstanceName();
			String factor = action instanceof AEmiliaConstChangesAction
					? Double.toString(((AEmiliaConstChangesAction) action).getFactorOfChange())
					: "NULL";

			line.addAll(Arrays.asList(target, factor));

		}
		CSVUtils.writeLine(getParetoFolder() + getProblem().getName() + "_pareto.csv", line);
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
			// br = new BufferedReader(new FileReader(FILENAME));
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

	public void copyModel(String destinationPath) {
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

	public void setMaxCloning(int maxCloning) {
		this.maxCloning = maxCloning;
	}

	private void updateSourceModel() {
		// String rewMappingFilePath = sourceFolder +
		// AemiliaManager.getRewmappingFileExtension();
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

	public void setSourceModelPath(String sourceModelPath) {
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

	public void simpleSolutionWriterToCSV(RSolution rSolution) {
		try {
			FileWriter solutionWriter = new FileWriter(getParetoFolder() + getProblem().getName() + "_solutions.csv",
					true);
			List<String> line = new ArrayList<String>();
			line.add(String.valueOf(rSolution.getName()));
			line.add(String.valueOf(rSolution.getPAs()));
			line.add(String.valueOf(rSolution.getPerfQ()));
			line.add(String.valueOf(rSolution.getNumOfChanges()));
			CSVUtils.writeLine(solutionWriter, line);
			solutionWriter.flush();
			solutionWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getRuleTemplateFilePath() {
		return ruleTemplateFilePath;
	}

	public void setRuleTemplateFilePath(String ruleTemplateFilePath) {
		this.ruleTemplateFilePath = ruleTemplateFilePath;
	}

	public double getWorkloadRange() {
		if (workloadRange != null)
			return JMetalRandom.getInstance().nextDouble(workloadRange[0], workloadRange[1]);
		return -1;
	}

	public void awaitExecutor() {
		if (executor != null) {
			try {
				executor.awaitTermination(10, TimeUnit.HOURS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setExecutor(ExecutorService exc) {
		this.executor = exc;
	}

	public String getLogFolder() {
		return logFolder;
	}

	public void setLogFolder(String logFolder) {
		this.logFolder = logFolder;
	}

	public void setAvailabilityFolder(String availabilityFolder) {
		this.availabilityFolder = availabilityFolder;
	}

	public String getParetoFolder() {
		return paretoFolder;
	}

	public void setParetoFolder(String paretoFolder) {
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

	public static void setSOR(boolean sorValue) {
		SOR = sorValue;
	}

	public static boolean isSor() {
		return SOR;
	}

}
