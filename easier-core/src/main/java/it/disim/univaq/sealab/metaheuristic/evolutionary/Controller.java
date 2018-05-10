package it.disim.univaq.sealab.metaheuristic.evolutionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
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
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.runner.AbstractAlgorithmRunner;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.disim.univaq.sealab.aemiliaMod2text.main.Transformation;
import it.disim.univaq.sealab.metaheuristic.actions.aemilia.Refactoring;
import it.disim.univaq.sealab.metaheuristic.actions.aemilia.RefactoringAction;
import it.disim.univaq.sealab.metaheuristic.managers.Manager;
import it.disim.univaq.sealab.metaheuristic.managers.MetamodelManager;
import it.disim.univaq.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import it.disim.univaq.sealab.metaheuristic.utils.CSVUtils;
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

	private Properties prop;

	private String basePath, sourceFolder, configFile;
	private PerformanceQuality perfQuality;

	private int length, number_of_actions, maxEvaluations, populationSize, allowed_failures;

	private double crossoverProbability, mutationProbability, distribution_index;
	private double[] workloadRange = new double[2];
	private String ruleFilePath, ruleTemplateFilePath;
	private RProblem problem;
	private String destionationFolderPath;
	private FileWriter resultFileWriter, solutionFileWriter;
	private String outputFolder;
	private String destinationFolder;
	private String twoTowersKernelPath;
	private List<String> csvResultHeader = new ArrayList<>();
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

	// private static String BASENAME =
	// "/src/main/resources/models/refactored/BGCS/BGCS_";
	// private static String EXTENSION = ".uml";

	public Controller(String[] args) {

		manager = Manager.getInstance(new AemiliaManager());
		manager.setController(this);

		setPerfQuality(new PerformanceQuality());
		// logger_ = ;
		InputStream inputStream = null;

		try {
			handler = new FileHandler("default.log", append);
			setBasePath(new java.io.File(".").getCanonicalPath());
			inputStream = getConfigFile(args[0]);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger_.addHandler(handler);

		metamodelManager = manager.getMetamodelManager();

		logger_.info("Logger Name: " + logger_.getName());
		logger_.warning("Can cause IOException");

		if (args[0] != null) {
			configFile = getBasePath() + args[0];
		}

		if (args.length == 2) {
			sourceModelPath = args[1];
		}

		setProperties(inputStream);
		if (sourceModelPath == null || sourceModelPath.isEmpty())
			sourceModelPath = sourceFolder + ((AemiliaManager) metamodelManager).getMetamodelFileExtension();
		((AemiliaManager) metamodelManager).setAemiliaModelFilePath(sourceModelPath);

		updateSourceModel();

		sourceModelPAs = perfQuality.performanceAntipatternEvaluator(metamodelManager.getModel(), ruleFilePath);
		numberOfPAs = 0;
		for (String key : sourceModelPAs.keySet()) {
			numberOfPAs += sourceModelPAs.get(key).size();
		}
	}

	private void generateSourceFiles() {

		// String mmaemiliaFolderPath =
		// "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/EDS";
		// sourceAemPath =
		// "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/BGCS/EDS.aem";

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
		RProblem problem = new RProblem(sourceBasePath, length, number_of_actions, allowed_failures, populationSize,
				manager);
		this.setProblem(problem);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		setDestinationFolder(outputFolder + this.getProblem().getName() + "__" + sdf.format(timestamp) + "__");

		this.setDestinationFolderPath(getDestinationFolder());

		new File(getDestinationFolder()).mkdirs();

		System.setOut(new PrintStream(new File(this.getDestinationFolderPath() + "/output.log")));
		System.setErr(new PrintStream(new File(this.getDestinationFolderPath() + "/error.log")));

		String csvProFile = destionationFolderPath + "properties.csv";
		writePropertiesToCSV(csvProFile);

		try {
			handler = new FileHandler(this.getDestinationFolderPath() + "/default.log", append);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger_.addHandler(handler);

		CrossoverOperator<RSolution> crossover = new RCrossover(crossoverProbability);
		MutationOperator<RSolution> mutation = new RMutation(mutationProbability, distribution_index);
		SelectionOperator<List<RSolution>, RSolution> selection = new BinaryTournamentSelection<RSolution>(
				new RankingAndCrowdingDistanceComparator<RSolution>());

		SolutionListEvaluator<RSolution> ev = new RSolutionListEvaluator();

		Algorithm<List<RSolution>> algorithm = new CustomNSGAII<RSolution>(problem, maxEvaluations, populationSize,
				crossover, mutation, selection, ev);

		long[] id_s = new long[1];
		id_s[0] = java.lang.Thread.currentThread().getId();
		// long initTime = getCpuTime(id_s);

		logger_.info(algorithm.getClass().toString());

		solutionFileWriter = new FileWriter(destionationFolderPath + "solutions.csv", true);
		List<String> line = new ArrayList<String>();
		line.add("name");
		line.add("PAs");
		line.add("perfQ");
		line.add("numOfChanges");
		CSVUtils.writeLine(solutionFileWriter, line);
		solutionFileWriter.flush();
		solutionFileWriter.close();

		algorithm.run();
		List<RSolution> population = algorithm.getResult();

		// long estimatedTime = getCpuTime(id_s) - initTime;

		((CustomNSGAII<RSolution>) algorithm).computeCrowdingDistances();
		population = algorithm.getResult();

		Collections.sort(population, new RankingAndCrowdingDistanceComparator<RSolution>());
		Collections.reverse(population);

		logger_.info("Number of non-dominated solutions (sorted by Crowding): " + population.size());

		// logger_.info("Estimated total execution time: "
		// + new DecimalFormat("#.##").format(estimatedTime * Math.pow(10,
		// -9)).replaceAll(",", ".") + " seconds");

		endingTime = Instant.now();
		Duration totalTime = Duration.between(startingTime, endingTime);

		logger_.info("Total execution time: " + totalTime.toString().replaceAll(",", ".") + " seconds");

		try {
			resultFileWriter = new FileWriter(destionationFolderPath + "results.csv");
			line = null;
			line = new ArrayList<String>();
			line.add("Popul");
			line.add("Evals");
			line.add("PCross");
			line.add("PMutat");
			line.add("#Pareto");
			line.add("#Crossover");
			line.add("#Mutation");
			line.add("ExeTime");
			line.add("OriginalPAs");
			CSVUtils.writeLine(resultFileWriter, line);
			line = null;
			line = new ArrayList<String>();
			line.add(String.valueOf(populationSize));
			line.add(String.valueOf(maxEvaluations));
			line.add(String.valueOf(crossoverProbability));
			line.add(String.valueOf(mutationProbability));
			line.add(String.valueOf(population.size()));
			line.add(String.valueOf(RSolution.XOverCounter));
			line.add(String.valueOf(RSolution.MutationCounter));
			line.add(totalTime.toString().replaceAll(",", "."));
			line.add(String.valueOf(numberOfPAs));
			CSVUtils.writeLine(resultFileWriter, line);
			for (String key : sourceModelPAs.keySet()) {

				List<ArchitecturalInteraction> listOfContextElems = sourceModelPAs.get(key);
				for (ArchitecturalInteraction ai : listOfContextElems) {
					line = null;
					line = new ArrayList<String>();
					line = Arrays.asList(key, ai.getName());
					CSVUtils.writeLine(resultFileWriter, line);
				}
			}

			// CSVUtils.writeLine(resultFileWriter, line);
			line = null;

			writeSolutionSetToCSV(population);
			resultFileWriter.flush();
			// TODO: handle exception
		} catch (IOException e) {
			// // TODO Auto-generated catch block
			e.printStackTrace();
		}

		getProperties().setProperty("Total Elapsed Time", totalTime.toString());
		logger_.getHandlers()[0].flush();
		logger_.getHandlers()[0].close();

	}

	private void setProperties(InputStream inputStream) {
		prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString()
				.indexOf("jdwp") >= 0;

		if (isDebug)
			logger_.info("Reading Model: " + configFile);

		// model_file_name = getBasePath();

		setTwoTowersKernelPath(prop.getProperty("ttKernel"));
		logger_.info("twoTowersKernelPath is set to " + getTwoTowersKernelPath());

		sourceBasePath = getBasePath() + prop.getProperty("sourceBasePath");

		sourceValPath = sourceBasePath + ".val";
		sourceRewPath = sourceBasePath + ".rew";
		sourceRewmappingPath = sourceBasePath + ".rewmapping";
		sourceAemPath = sourceBasePath + ".aem";
		sourceModelPath = sourceBasePath + ".mmaemilia";

		if (sourceFolder == null || sourceFolder.isEmpty()) {
			sourceFolder = getBasePath() + prop.getProperty("sourceFolder");
		}
		logger_.info("sourceFolder is set to " + sourceFolder);

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

		// = getBasePath() + "/src/main/resources/models/AemiliaModels/BoA/BoA.aem.val";

		if (!new File(sourceValPath).exists()) {
			logger_.warning("SourceValFilePath: " + sourceValPath + " DOES NOT EXIST");
			logger_.info("sourceValPath is set to default value");
			((AemiliaManager) metamodelManager)
					.setSourceValFilePath(getBasePath() + "/src/main/resources/models/AemiliaModels/BoA/BoA.aem.val");
		} else {
			((AemiliaManager) metamodelManager).setSourceValFilePath(sourceValPath);
			logger_.info("SourceValFilePath: " + sourceValPath);
		}

		if (!new File(sourceRewPath).exists()) {
			logger_.warning("NOT EXISTS SourceRewFilePath: " + sourceValPath);
			logger_.info("sourceRewPath is set to default value");
			((AemiliaManager) metamodelManager)
					.setSourceValFilePath(getBasePath() + "/src/main/resources/models/AemiliaModels/BoA/BoA.rew");
		} else {
			logger_.info("SourceRewFilePath: " + sourceRewPath);
		}

		if (sourceFolder == null || sourceFolder.isEmpty()) {
			sourceFolder = getBasePath() + prop.getProperty("sourceFolder");
		}
		logger_.info("sourceFolder is set to " + sourceFolder);

		length = Integer.parseInt(prop.getProperty("length"));
		logger_.info("length is set to " + length);

		number_of_actions = Integer.parseInt(prop.getProperty("number_of_actions"));
		logger_.info("number_of_actions is set to " + number_of_actions);

		crossoverProbability = Double.parseDouble(prop.getProperty("p_crossover"));
		logger_.info("crossoverProbability is set to " + crossoverProbability);

		mutationProbability = Double.parseDouble(prop.getProperty("p_mutation"));
		logger_.info("mutationProbability is set to " + mutationProbability);

		distribution_index = Double.parseDouble(prop.getProperty("d_index_mutation"));
		logger_.info("distribution_index is set to " + distribution_index);

		maxEvaluations = Integer.parseInt(prop.getProperty("maxEvaluations"));
		logger_.info("maxEvaluations is set to " + maxEvaluations);

		populationSize = Integer.parseInt(prop.getProperty("populationSize"));
		logger_.info("populationSize is set to " + populationSize);

		allowed_failures = Integer.parseInt(prop.getProperty("allowed_failures"));
		logger_.info("allowed_failures is set to " + allowed_failures);

		if (prop.getProperty("outputFolder").endsWith(File.separator))
			setOutputFolder(prop.getProperty("outputFolder"));
		else
			setOutputFolder(prop.getProperty("outputFolder") + File.separator);
		logger_.info("outputFolder is set to " + getOutputFolder());

		setRuleFilePath(getBasePath() + prop.getProperty("rule_file_path"));
		logger_.info("rule_file_path is set to " + getRuleFilePath());
		
		setRuleTemplateFilePath(getBasePath() + prop.getProperty("rule_template_file_path"));
		logger_.info("rule_template_file_path is set to " + getRuleTemplateFilePath());

		setMaxCloning(Integer.valueOf(prop.getProperty("maxCloning")));
		logger_.info("max cloning is set to " + getMaxCloning());

		logger_.info("Starting number of elements: " + populationSize);
		logger_.info("Debug mode: " + isDebug);
		
		String[] workloadRangeString = prop.getProperty("workloadRange").split(";");
		workloadRange[0] = Double.parseDouble(workloadRangeString[0]);
		workloadRange[1] = Double.parseDouble(workloadRangeString[1]);
		
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
		logger_.info("Config_file is set to " + filename);
		return new FileInputStream(filename);
	}

	private static void createNewModelFile(String refModelURI) {
		String path;
		try {
			path = new java.io.File(".").getCanonicalPath() + refModelURI + "."
					+ Manager.getInstance(null).getMetamodelManager().getMetamodelFileExtension();
			File newFile = new File(path);
			newFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static long getCpuTime(long[] ids) {
		/** Get CPU time in nanoseconds. */

		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		if (!bean.isThreadCpuTimeSupported())
			return 0L;
		long time = 0L;
		for (long i : ids) {
			// System.out.println(i);
			long t = bean.getThreadCpuTime(ids[(int) i - 1]);
			if (t != -1)
				time += t;
		}
		return time;

	}

	public PerformanceQuality getPerfQuality() {
		return perfQuality;
	}

	public void setPerfQuality(PerformanceQuality perfQuality) {
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

	public void writeSolutionSetToCSV(List<RSolution> population) {

		logger_.info("Writing CSV");

		try {
			for (RSolution solution : population) {
				writeSolutionToCSV(solution);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDestinationFolderPath(String path) {
		destionationFolderPath = path + "/";
	}

	public String getDestinationFolderPath() {
		return this.destionationFolderPath;
	}

	public void writeToCSV(RSolution solution) {

		// String csvFilePath = destionationFolderPath + ref.getName() + ".csv";
		try {
			writeSolutionToCSV(solution);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writePropertiesToCSV(String csvFilePath) {
		// List<String> header = new ArrayList<>();

		FileWriter writer;
		try {
			writer = new FileWriter(csvFilePath);
			for (Object key : getProperties().keySet()) {
				List<String> contents = new ArrayList<>();
				// header.add(key.toString());
				contents.addAll(Arrays.asList(key.toString(), getProperties().getProperty(key.toString())));
				// writeToCSVFile(writer, header);
				writeToCSVFile(writer, contents);
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeSolutionToCSV(RSolution solution) throws IOException {
		Refactoring ref = solution.getVariableValue(0).getRefactoring();

		List<String> line = new ArrayList<String>();
		line.add("#SOL:" + Integer.toString(solution.getName()));
		CSVUtils.writeLine(resultFileWriter, line);

		line = null;
		line = new ArrayList<String>();
		line.add("Parents");
		line.add("Refactored");
		line.add("Crossovered");
		line.add("Mutated");
		line.add("PerfQ");
		line.add("#Changes");
		line.add("#Pas");
		line.add("ExeTime");
		CSVUtils.writeLine(resultFileWriter, line);

		line = null;
		line = new ArrayList<String>();
		String parents = "-,-";
		if (solution.getParents()[0] != null && solution.getParents()[1] != null) {
			parents = Integer.toString(solution.getParents()[0].getName()) + ", "
					+ Integer.toString(solution.getParents()[1].getName());
		}
		line.add(parents);
		line.add(Boolean.toString(solution.isRefactored()));
		line.add(Boolean.toString(solution.isCrossovered()));
		line.add(Boolean.toString(solution.isMutated()));
		line.add(Float.toString(solution.getVariableValue(0).getPerfQuality()));
		line.add(Double.toString(solution.getVariableValue(0).getNumOfChanges()));
		line.add(Integer.toString(solution.getVariableValue(0).getNumOfPAs()));
		line.add(solution.getElapsedTime().toString());
		CSVUtils.writeLine(resultFileWriter, line);

		line = null;
		line = new ArrayList<String>();
		line.add("ACTIONS");
		CSVUtils.writeLine(resultFileWriter, line);

		line = null;
		line = new ArrayList<String>();
		line = Arrays.asList("Type", "#Chang", "Target", "Factor");
		CSVUtils.writeLine(resultFileWriter, line);

		for (RefactoringAction action : ref.getActions()) {
			line = null;
			line = new ArrayList<String>();
			if (action.getName() == null)
				action.setName(action.getClass().getSimpleName());

			String target = action instanceof AEmiliaConstChangesAction
					? ((AEmiliaConstChangesAction) action).getSourceConstInit().getName()
					: ((AEmiliaCloneAEIAction) action).getSourceAEI().getInstanceName();
			String factor = action instanceof AEmiliaConstChangesAction
					? Double.toString(((AEmiliaConstChangesAction) action).getFactorOfChange())
					: "NULL";

			line = Arrays.asList(action.getName(), Double.toString(action.getNumOfChanges()), target, factor);

			CSVUtils.writeLine(resultFileWriter, line);

		}

		line = null;
		line = new ArrayList<String>();
		line.add("ANTIPATTERNS");
		CSVUtils.writeLine(resultFileWriter, line);
		line = null;
		line = new ArrayList<String>();
		line = Arrays.asList("Key", "ContextElem");
		CSVUtils.writeLine(resultFileWriter, line);

		Map<String, List<ArchitecturalInteraction>> mapOfPAs = solution.getMapOfPAs();

		for (String key : mapOfPAs.keySet()) {

			List<ArchitecturalInteraction> listOfContextElems = mapOfPAs.get(key);
			for (ArchitecturalInteraction ai : listOfContextElems) {
				line = null;
				line = new ArrayList<String>();
				line = Arrays.asList(key, ai.getName());
				CSVUtils.writeLine(resultFileWriter, line);
			}
		}

	}

	private void writeToCSVFile(FileWriter writer, List<String> contents) throws IOException {
		CSVUtils.writeLine(writer, contents);
	}

	public String getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	public String getDestinationFolder() {
		return destinationFolder;
	}

	public void setDestinationFolder(String destinationFolder) {
		this.destinationFolder = destinationFolder;
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
	// public static String getBASENAME() {
	// return BASENAME;
	// }
	//
	// public static void setBASENAME(String bASENAME) {
	// BASENAME = bASENAME;
	// }

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
					sourceModelPath);
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
			solutionFileWriter = new FileWriter(destionationFolderPath + "solutions.csv", true);
			List<String> line = new ArrayList<String>();
			line.add(String.valueOf(rSolution.getName()));
			line.add(String.valueOf(rSolution.getPAs()));
			line.add(String.valueOf(rSolution.getPerfQ()));
			line.add(String.valueOf(rSolution.getNumOfChanges()));
			CSVUtils.writeLine(solutionFileWriter, line);
			solutionFileWriter.flush();
			solutionFileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}

	}

	public String getRuleTemplateFilePath() {
		return ruleTemplateFilePath;
	}

	public void setRuleTemplateFilePath(String ruleTemplateFilePath) {
		this.ruleTemplateFilePath = ruleTemplateFilePath;
	}
	
	public double getWorkloadRange() {
		return JMetalRandom.getInstance().nextDouble(workloadRange[0], workloadRange[1]);
	}

}
