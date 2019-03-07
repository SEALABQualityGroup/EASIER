package it.univaq.disim.sealab.metaheuristic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.emf.ecore.EDataType.Internal.ConversionDelegate.Factory;
import org.uma.jmetal.qualityindicator.impl.Epsilon;
import org.uma.jmetal.qualityindicator.impl.GeneralizedSpread;
import org.uma.jmetal.qualityindicator.impl.GenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistancePlus;
import org.uma.jmetal.qualityindicator.impl.Spread;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.archive.impl.NonDominatedSolutionListArchive;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.front.Front;
import org.uma.jmetal.util.front.imp.ArrayFront;
import org.uma.jmetal.util.front.util.FrontUtils;
import org.uma.jmetal.util.point.util.PointSolution;
import org.uma.jmetal.util.solutionattribute.impl.GenericSolutionAttribute;

import com.beust.jcommander.JCommander;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.factory.FactoryBuilder;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class Launcher {

	// public static void multiModels(String configFiles) {
	//
	// Properties prop = new Properties();
	// try {
	// prop.load(getConfigFile(configFiles));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// Enumeration<String> enums = (Enumeration<String>) prop.propertyNames();
	// while (enums.hasMoreElements()) {
	// // String key = enums.nextElement();
	// String cfgFile = prop.getProperty(enums.nextElement());
	// Controller controller = new Controller(cfgFile);
	// try {
	// controller.run();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// public static void singleModel(String configFile) {
	// try {
	// new Controller(configFile).run();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public static void runExperiment(String configFile) {
	// Controller ctr = new Controller(configFile);
	// ctr.runExperiment();
	// ctr.generateAvailability();
	// }

	// public static void availability(final String twoTowersKernelPath, final
	// String targetFolder) {
	// File avaFolder = Paths.get(targetFolder, "availability").toFile();
	// //avaFolder.mkdirs();
	// new Controller().generateAvailability(twoTowersKernelPath, new
	// File(targetFolder), avaFolder);
	// }

	public static void main(String[] args) {

		JCommander jc = new JCommander();

		Configurator config = new Configurator();
		jc.addObject(config);
		jc.parse(args);

		Controller ctr = new Controller(config);
		List<Path> referenceFront;
		if (config.getReferenceFront() != null)
			referenceFront = config.getReferenceFront();
		else {
			ctr.setUp();
			List<RProblem> rProblems = ctr.createProblems();
			List<GenericIndicator<RSolution>> qIndicators = new ArrayList<>();

			FactoryBuilder<RSolution> factory = new FactoryBuilder<>();
			for (String qI : config.getQualityIndicators()) {
				GenericIndicator<RSolution> ind = factory.createQualityIndicators(qI);
				if (ind != null)
					qIndicators.add(ind);
			}

			ctr.runExperiment(rProblems, qIndicators);
			referenceFront = ctr.getReferenceFront();
		}

		if (config.hasAvailability()) {
			List<String> solIDs = FileUtils.getParetoSolIDs(referenceFront);
			ctr.generateAvailability(solIDs);
		}

		// jc.usage();
		//
		// jCommander.getModels().forEach(System.out::println);
		//
		// Arrays.stream(args)
		// .forEach(System.out::println);

		// List<String> argsList = Arrays.asList(args);
		// Controller.setSOR(argsList.contains("-sor"));
		//
		// if (argsList.contains("-sP")) {
		// try {
		// final String rPF =
		// "/Users/peo12/git/sealab/easier/easier-dataAnalyst/data/P_64_E_4480_X_0.8_M_0.2/referenceFront/P_64_E_4480_X_0.8_M_0.2";
		// final String sPF_folder =
		// "/Users/peo12/git/sealab/easier/easier-dataAnalyst/data/superpareto";
		// generateReferenceParetoFront(rPF, sPF_folder);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// if (argsList.contains("-m")) {
		// multiModels(argsList.get(argsList.indexOf("-m") + 1));
		// }
		//
		// if (argsList.contains("-s")) {
		// singleModel((argsList.get(argsList.indexOf("-s") + 1)));
		// }
		//
		// //Runs experiment
		// if (argsList.contains("-sE")) {
		// runExperiment((argsList.get(argsList.indexOf("-sE") + 1)));
		// }
		//
		// String twoTowersKernelPath = "";
		//
		// //uses a new TTKernel path
		// if (argsList.contains("-t")) {
		// twoTowersKernelPath = argsList.get(argsList.indexOf("-t") + 1);
		// }
		//
		// if (argsList.contains("-ava")) {
		// availability(twoTowersKernelPath, (argsList.get(argsList.indexOf("-ava") +
		// 1)));
		// }
	}

	public static void generateReferenceParetoFront(final String rPFile, final String sPF) throws Exception {

		// File referencePareto = new File(rPFile);

		File sPF_folder = new File(sPF);

		NonDominatedSolutionListArchive<PointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<PointSolution>();

		Set<File> solutions = FileUtils.listFilesRecursively(sPF_folder);

		// good way:
		Iterator<File> iterator = solutions.iterator();
		while (iterator.hasNext()) {
			File setElement = iterator.next();
			Front front = new ArrayFront(setElement.getPath());
			List<PointSolution> solutionList = FrontUtils.convertFrontToSolutionList(front);
			// GenericSolutionAttribute<PointSolution, String> solutionAttribute = new
			// GenericSolutionAttribute<PointSolution, String>();
			for (PointSolution solution : solutionList) {
				// solutionAttribute.setAttribute(solution, algorithm.getAlgorithmTag());
				nonDominatedSolutionArchive.add(solution);
			}
		}

		String referenceSetFileName = rPFile + ".rf";

		new SolutionListOutput(nonDominatedSolutionArchive.getSolutionList())
				.printObjectivesToFile(referenceSetFileName);

		// for (int i = 0; i < num_of_solutions; i++) {
		// String frontFileName = problemDirectory + "/" +
		// experiment.getOutputParetoFrontFileName() + i + ".tsv";
		// Front front = new ArrayFront(frontFileName);
		// List<PointSolution> solutionList =
		// FrontUtils.convertFrontToSolutionList(front);
		// GenericSolutionAttribute<PointSolution, String> solutionAttribute = new
		// GenericSolutionAttribute<PointSolution, String>();
		//
		// for (PointSolution solution : solutionList) {
		// solutionAttribute.setAttribute(solution, algorithm.getAlgorithmTag());
		// nonDominatedSolutionArchive.add(solution);
		// }
		// }
	}

	/**
	 * Print help on how to use the launcher from the command line.
	 */
	public static void printUsage() {
		System.out.println("Usage: [-options] folder\n" + "  (folder containing .aem files)\n"
				+ "where options include:\n" + "  -f		Availability (all operational components)\n"
				+ "  -d		Availability (at least one operational component)\n"
				+ "  -t <TTKernel> Set the path to the TTKernel executable\n" + "  -n		Number of threads\n"
				+ "  -sp	Skip the creation of .psm files if already present\n"
				+ "  -sa	Skip the creation of .ava files if already present\n"
				+ "  -to    Timeout (in hours) for the computation of solutions\n"
				+ "  -sor   use stochastic over-relaxation");
	}

	private static FileInputStream getConfigFile(String filename) throws FileNotFoundException {
		try {
			filename = new java.io.File(".").getCanonicalPath() + filename;
		} catch (IOException e) {
			e.printStackTrace();
		}
		// logger_.info("Config_file is set to " + filename);
		return new FileInputStream(filename);
	}
}
