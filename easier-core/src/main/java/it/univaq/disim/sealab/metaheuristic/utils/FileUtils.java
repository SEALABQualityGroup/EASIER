package it.univaq.disim.sealab.metaheuristic.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.text.StringSubstitutor;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import logicalSpecification.actions.AEmilia.AEmiliaCloneAEIAction;
import logicalSpecification.actions.AEmilia.AEmiliaConstChangesAction;
import metamodel.mmaemilia.AEmiliaSpecification;

public class FileUtils {

	public FileUtils() {}

	/**
	 * Recursively walk through sub-directories listing Aemilia files.
	 * 
	 * @param folder
	 *            starting folder
	 * @return array of aemilia file paths
	 */
	public static Set<File> listFilesRecursively(final File folder) {
		Set<File> files = new HashSet<File>();
		if (folder == null || folder.listFiles() == null) {
			return files;
		}
		for (File entry : folder.listFiles()) {
			if (entry.isFile() && entry.getName().endsWith(".tsv")) {
				files.add(entry);
			} else if (entry.isDirectory()) {
				files.addAll(listFilesRecursively(entry));
			}
		}
		return files;
	}

	/**
	 * Recursively walk through sub-directories listing Aemilia files.
	 * 
	 * @param folder
	 *            starting folder
	 * @return array of aemilia file paths
	 */
	public static Set<File> listFilesRecursively(final Path folder, String extension) {
		Set<File> files = new HashSet<File>();
		if (folder == null || folder.toFile().listFiles() == null) {
			return files;
		}
		for (File entry : folder.toFile().listFiles()) {
			if (entry.isFile() && entry.getName().endsWith(extension)) {
				files.add(entry);
			} else if (entry.isDirectory()) {
				files.addAll(listFilesRecursively(entry));
			}
		}
		return files;
	}
	
	/**
	 * Recursively walk through subdirectories listing Aemilia files.
	 * 
	 * @param folder
	 *            starting folder
	 * @return array of aemilia file paths
	 */
	@Deprecated
	public static Set<File> listFilesRecursively(final File folder, String extension) {
		Set<File> files = new HashSet<File>();
		if (folder == null || folder.listFiles() == null) {
			return files;
		}
		for (File entry : folder.listFiles()) {
			if (entry.isFile() && entry.getName().endsWith(extension)) {
				files.add(entry);
			} else if (entry.isDirectory()) {
				files.addAll(listFilesRecursively(entry));
			}
		}
		return files;
	}

	public static void simpleSolutionWriterToCSV(RSolution rSolution) {
		try (FileWriter fw = new FileWriter(
				Paths.get(rSolution.getController().getConfigurator().getOutputFolder().toString(),  
						rSolution.getProblem().getName() + "_solutions.csv").toFile(),
				true)) {
			List<String> line = new ArrayList<String>();
			line.add(String.valueOf(rSolution.getName()));
			line.add(String.valueOf(rSolution.getPerfQ()));
			line.add(String.valueOf(rSolution.getNumOfChanges()));
			line.add(String.valueOf(rSolution.getPAs()));
			CSVUtils.writeLine(fw, line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static synchronized void writeSolutionSetToCSV(List<RSolution> population) {
//		EasierLogger.logger_.info("Writing CSV");
//		for (RSolution solution : population) {
//			try (FileWriter fw = new FileWriter(
//					Paths.get(solution.getController().getConfigurator().getOutputFolder().toString(),
//							solution.getProblem().getName() + "_analyzableResults.csv").toFile(),
//					true)) {
//				List<String> line = new ArrayList<String>();
//				line.add("SolID");
//				line.add("PerQ");
//				line.add("#Changes");
//				line.add("#PAs");
//				int numberOfActions = solution.getVariableValue(0).getLength();
//				for (int i = 0; i < numberOfActions; i++) {
//					line.add("ActionTarget");
//					line.add("FoC/Null");
//				}
//				CSVUtils.writeLine(fw, line);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			writeSolutionToCSV(solution);
//			writeAnalyzableFile(solution);
//		}
//		EasierLogger.logger_.info("Writing CSV done");
//	}

//	public static void writeSolutionToCSV(RSolution solution) {
//		Refactoring ref = solution.getVariableValue(0).getRefactoring();
//		try (FileWriter fw = new FileWriter(
//				Paths.get(solution.getController().getConfigurator().getOutputFolder().toString(),
//						solution.getProblem().getName() + "_results.csv").toFile(),
//				true)) {
//			CSVUtils.writeLine(fw, Arrays.asList("#SOL:" + Integer.toString(solution.getName())));
//			CSVUtils.writeLine(fw, Arrays.asList("Parents", "Refactored", "Crossovered", "Mutated", "PerfQ", "#Changes",
//					"#Pas", "ExeTime"));
//
//			List<String> line = new ArrayList<String>();
//			String parents = "-,-";
//			if (solution.getParents()[0] != null && solution.getParents()[1] != null) {
//				parents = Integer.toString(solution.getParents()[0].getName()) + ", "
//						+ Integer.toString(solution.getParents()[1].getName());
//			}
//			line.add(parents);
//			line.add(Boolean.toString(solution.isRefactored()));
//			line.add(Boolean.toString(solution.isCrossovered()));
//			line.add(Boolean.toString(solution.isMutated()));
//			line.add(Float.toString(solution.getVariableValue(0).getPerfQuality()));
//			line.add(Double.toString(solution.getVariableValue(0).getNumOfChanges()));
//			line.add(Integer.toString(solution.getVariableValue(0).getNumOfPAs()));
//			CSVUtils.writeLine(fw, line);
//			CSVUtils.writeLine(fw, Arrays.asList("ACTIONS"));
//			CSVUtils.writeLine(fw, Arrays.asList("Type", "#Chang", "Target", "Factor"));
//
//			for (RefactoringAction action : ref.getActions()) {
//				line = null;
//				line = new ArrayList<String>();
//				if (action.getName() == null)
//					action.setName(action.getClass().getSimpleName());
//
//				String target = action instanceof AEmiliaConstChangesAction
//						? ((AEmiliaConstChangesAction) action).getSourceConstInit().getName()
//						: ((AEmiliaCloneAEIAction) action).getSourceAEI().getInstanceName();
//				String factor = action instanceof AEmiliaConstChangesAction
//						? Double.toString(((AEmiliaConstChangesAction) action).getFactorOfChange())
//						: "NULL";
//
//				line = Arrays.asList(action.getName(), Double.toString(action.getNumOfChanges()), target, factor);
//
//				CSVUtils.writeLine(fw, line);
//
//			}
//			CSVUtils.writeLine(fw, Arrays.asList("ANTIPATTERNS"));
//			CSVUtils.writeLine(fw, Arrays.asList("Key", "ContextElem"));
//
//			Map<String, List<ArchitecturalInteraction>> mapOfPAs = solution.getMapOfPAs();
//			try {
//				for (String key : mapOfPAs.keySet()) {
//					List<ArchitecturalInteraction> listOfContextElems = mapOfPAs.get(key);
//					for (ArchitecturalInteraction ai : listOfContextElems) {
//						CSVUtils.writeLine(fw, Arrays.asList(key, ai.getName()));
//					}
//				}
//			} catch (NullPointerException e) {
//				System.err.println("Solution #: " + solution.getName() + " has null mapOfPAs");
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static void writeAnalyzableFile(final RSolution solution) {
		Controller controller = solution.getController();

		try (FileWriter analyzableCSV = new FileWriter(
				Paths.get(controller.getConfigurator().getOutputFolder().toString(), "pareto",
						solution.getProblem().getName() + "_analyzableResults.csv").toFile(),
				true)) {

			List<String> line = new ArrayList<String>();
			String solID = "( "
					+ controller.getConfigurator().getMaxEvaluation() / controller.getConfigurator().getPopulationSize()
					+ "-" + controller.getConfigurator().getPopulationSize() + " ) " + ":" + solution.getName();
			line.add(solID);
			line.add(String.valueOf(solution.getPerfQ()));
			line.add(String.valueOf(solution.getNumOfChanges()));
			line.add(String.valueOf(solution.getPAs()));

			final Refactoring ref = solution.getVariableValue(0).getRefactoring();

			for (RefactoringAction action : ref.getActions()) {
				if (action.getName() == null)
					action.setName(action.getClass().getSimpleName());

				String target = action instanceof AEmiliaConstChangesAction
						? ((AEmiliaConstChangesAction) action).getSourceConstInit().getName()
						: ((AEmiliaCloneAEIAction) action).getSourceAEI().getInstanceName();
				String factor = action instanceof AEmiliaConstChangesAction
						? Double.toString(((AEmiliaConstChangesAction) action).getFactorOfChange())
						: "NULL";
				line.addAll(Arrays.asList(target, factor));

			}
			CSVUtils.writeLine(analyzableCSV, line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void moveTmpFile(final Path sourceFolder, final Path destFolder) {
		destFolder.toFile().mkdirs();
		try {
			org.apache.commons.io.FileUtils.copyDirectory(sourceFolder.toFile(), destFolder.toFile());
		} catch (IOException e) {
			EasierLogger.logger_.warning("[WARNING] Copy tmp folder failed!!!");
			e.printStackTrace();
			return;
		}
		try {
			org.apache.commons.io.FileUtils.cleanDirectory(sourceFolder.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static List<String> getParetoSolIDs(final List<Path> paretoReferenceFront){
		List<String> solIDs = new ArrayList<>();
		for(Path path : paretoReferenceFront) {
			try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					solIDs.add(sCurrentLine.split(" ")[0]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return solIDs;
	}
	
	public static void fillTemplateKeywords(final Path sourceFile, final Path destination, final Map<String, String> keywords) {
		try {
			String templateString = fileToString(sourceFile, Charset.defaultCharset());
			StringSubstitutor sub = new StringSubstitutor(keywords);
			String resolvedString = sub.replace(templateString);

			File f = destination.toFile();
			f.getParentFile().mkdirs();
			f.createNewFile();

			PrintWriter out = new PrintWriter(destination.toFile());
			out.print(resolvedString);
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String fileToString(Path path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(path);
		return new String(encoded, encoding);
	}

	@Deprecated
	public static String fileToString(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
