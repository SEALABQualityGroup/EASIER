package it.univaq.disim.sealab.metaheuristic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.qualityindicator.impl.GenericIndicator;

import com.beust.jcommander.JCommander;

import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.factory.FactoryBuilder;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class Launcher {

	public static void main(String[] args) {

		JCommander jc = new JCommander();

		Configurator config = new Configurator();
		jc.addObject(config);
		jc.parse(args);

		List<Path> referenceFront = new ArrayList<>();

		UMLController ctr = new UMLController(config);
		if (config.getReferenceFront() != null)
			referenceFront = config.getReferenceFront();
		else {
			List<Path> modelsPath = new ArrayList<>();

			if (config.getModelsPath().get(0).toFile().isFile()) {
				// use the solution csv file to extract more models
				modelsPath
						.addAll(FileUtils.extractModelPaths(config.getModelsPath().get(0), config.getMaxWorseModels()));
			} else {
				modelsPath.addAll(config.getModelsPath());
			}
			int i = 1;
			for (Path m : modelsPath) {
				System.out.println("Number of source model");
				ProgressBar.showBar(i, modelsPath.size());
				ctr.setUp(m);
				List<RProblem<UMLRSolution>> rProblems = ctr.createProblems();
				List<GenericIndicator<UMLRSolution>> qIndicators = new ArrayList<>();

				FactoryBuilder<UMLRSolution> factory = new FactoryBuilder<>();
				for (String qI : config.getQualityIndicators()) {
					GenericIndicator<UMLRSolution> ind = factory.createQualityIndicators(qI);
					if (ind != null)
						qIndicators.add(ind);
				}
				ctr.runExperiment(rProblems, qIndicators);
				referenceFront = ctr.getReferenceFront();
			}
			i++;
		}
	} // calculates the availability, if set in the config file

//		if (config.hasAvailability()) {
//			List<String> solIDs = UMLFileUtils.getParetoSolIDs(referenceFront);
//			ctr.generateAvailability(solIDs);
//		}

//	}

//	public static void generateReferenceParetoFront(final String rPFile, final String sPF) throws Exception {
//
//		// File referencePareto = new File(rPFile);
//
//		File sPF_folder = new File(sPF);
//
//		NonDominatedSolutionListArchive<PointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<PointSolution>();
//
//		Set<File> solutions = UMLFileUtils.listFilesRecursively(sPF_folder);
//
//		// good way:
//		Iterator<File> iterator = solutions.iterator();
//		while (iterator.hasNext()) {
//			File setElement = iterator.next();
//			Front front = new ArrayFront(setElement.getPath());
//			List<PointSolution> solutionList = FrontUtils.convertFrontToSolutionList(front);
//			// GenericSolutionAttribute<PointSolution, String> solutionAttribute = new
//			// GenericSolutionAttribute<PointSolution, String>();
//			for (PointSolution solution : solutionList) {
//				// solutionAttribute.setAttribute(solution, algorithm.getAlgorithmTag());
//				nonDominatedSolutionArchive.add(solution);
//			}
//		}
//
//		String referenceSetFileName = rPFile + ".rf";
//
//		new SolutionListOutput(nonDominatedSolutionArchive.getSolutionList())
//				.printObjectivesToFile(referenceSetFileName);
//
//		// for (int i = 0; i < num_of_solutions; i++) {
//		// String frontFileName = problemDirectory + "/" +
//		// experiment.getOutputParetoFrontFileName() + i + ".tsv";
//		// Front front = new ArrayFront(frontFileName);
//		// List<PointSolution> solutionList =
//		// FrontUtils.convertFrontToSolutionList(front);
//		// GenericSolutionAttribute<PointSolution, String> solutionAttribute = new
//		// GenericSolutionAttribute<PointSolution, String>();
//		//
//		// for (PointSolution solution : solutionList) {
//		// solutionAttribute.setAttribute(solution, algorithm.getAlgorithmTag());
//		// nonDominatedSolutionArchive.add(solution);
//		// }
//		// }
//	}

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
