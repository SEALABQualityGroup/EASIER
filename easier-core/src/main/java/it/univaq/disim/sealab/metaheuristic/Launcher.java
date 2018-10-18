package it.univaq.disim.sealab.metaheuristic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;

public class Launcher {

	public static void multiModels(String configFiles) {

		Properties prop = new Properties();
		try {
			prop.load(getConfigFile(configFiles));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Enumeration<String> enums = (Enumeration<String>) prop.propertyNames();
		while (enums.hasMoreElements()) {
			// String key = enums.nextElement();
			String cfgFile = prop.getProperty(enums.nextElement());
			Controller controller = new Controller(cfgFile);
			try {
				controller.run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void singleModel(String configFile) {
		try {
			new Controller(configFile).run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void runExperiment(String configFile) {
		new Controller(configFile).runExperiment();
	}

	public static void main(String[] args) {
		List<String> argsList = Arrays.asList(args);
		Controller.setSOR(argsList.contains("-sor"));

		if (argsList.contains("-m")) {
			multiModels(argsList.get(argsList.indexOf("-m") + 1));
		}

		if (argsList.contains("-s")) {
			singleModel((argsList.get(argsList.indexOf("-s") + 1)));
		}

		if (argsList.contains("-sE")) {
			runExperiment((argsList.get(argsList.indexOf("-sE") + 1)));
		}
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
