package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.archive.impl.NonDominatedSolutionListArchive;
import org.uma.jmetal.util.solutionattribute.impl.GenericSolutionAttribute;

import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class RGenerateReferenceParetoTest {

	@Test
	public void generateSuperReferenceParetoTest() throws IOException {

//		new RGenerateReferenceParetoFront(experiment).run();

		List<RPointSolution> ptList = new ArrayList<>();
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

//		String problemName = "train-ticket_Length_4_CloningWeight_1.5_MaxCloning_3_MaxEval_72";
//		String problemFolderName = "/mnt/store/research/easier/uml_case_studies/performance_comparison/nsga_72/";
		String problemFolderName = "/home/peo/workspaces/Easier_SEAA/paretos/seaa_jist_si";
		List<Path> referenceFronts;
		try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName))) {
			referenceFronts = walk.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".rf"))
					.collect(Collectors.toList());
		}
		for (Path front : referenceFronts) {

			try (BufferedReader br = new BufferedReader(new FileReader(front.toString()))) {

				String sCurrentLine;
				final int numObjs = 4;
				while ((sCurrentLine = br.readLine()) != null) {

					if (!sCurrentLine.contains("solID")) {

						String[] split = sCurrentLine.split(",");
						if (split.length == numObjs + 1) {

							ptList.add(new RPointSolution(numObjs).setID(Integer.parseInt(split[0]))
									.setPointSolution(Arrays.asList((Arrays.copyOfRange(split, 1, numObjs + 1)))));
						}
					}
				}

			} catch (IOException | NumberFormatException e) {
				e.printStackTrace();
			}
		}

		GenericSolutionAttribute<RPointSolution, String> solutionAttribute = new GenericSolutionAttribute<RPointSolution, String>();

		for (RPointSolution solution : ptList) {
			solutionAttribute.setAttribute(solution, "NSGAII");
			nonDominatedSolutionArchive.add(solution);
		}

		printObjectivesToFile(getFileWriter(String.format("%s/super-reference-pareto.rf", problemFolderName)),
				nonDominatedSolutionArchive.getSolutionList());
	}

	@Test
	public void generateParetoPerProblem() throws IOException {

		List<Path> funSolutions;

//		String[] problemName = { "simplified-cocome", "train-ticket"};
		String[] problemName = { "simplified-cocome"};

		String problemFolderName = "/home/peo/git/sealab/seaa2021_jist_si_data/data/objectives";
		String referenceParetoFolder = "/home/peo/git/sealab/seaa2021_jist_si_data/data/reference_paretos";

		String[] brfs = { "moc_1.64", "moc_1.23" };

		int[] evals = { 72, 82, 102 };
		int[] probPas = { 55, 80, 95, 0 };

		for (final String pName : problemName) {
			for (final int eval : evals) {
				for (final int probPa : probPas) {
					int objectives = 4;
					if (probPa == 0) {
						objectives = 3;
					}
					for (final String brf : brfs) {

						NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

						try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName))) {
							funSolutions = walk.filter(Files::isRegularFile)
									.filter(path -> path.toString().endsWith(".csv") && path.toString().contains("FUN")
											&& path.toString().contains(pName)
											&& path.toString().contains("" + eval)
											&& path.toString().contains("ProbPAs_0." + probPa)
											&& path.toString().contains(brf))
									.collect(Collectors.toList());
						}

						for (Path sol : funSolutions) {

							List<RPointSolution> ptSolutionList = generateRPointSolutionList(sol.toString(),
									objectives);
							GenericSolutionAttribute<RPointSolution, String> solutionAttribute = new GenericSolutionAttribute<RPointSolution, String>();

							for (RPointSolution solution : ptSolutionList) {
								solutionAttribute.setAttribute(solution, "NSGAII");
								nonDominatedSolutionArchive.add(solution);
							}
						}

						printObjectivesToFile(getFileWriter(String.format(
								"%s/%s__BRF_clone_1.23__%s__mcnn_1.45__moncnn_1.80__MaxEval_%d__ProbPAs_0.%d__Algo_nsgaii.rf",
								referenceParetoFolder, pName, brf, eval, probPa)),
								nonDominatedSolutionArchive.getSolutionList());
					}
				}
			}
		}
	}

	public List<RPointSolution> generateRPointSolutionList(String sol, int numObjs) {

		List<RPointSolution> ptList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(sol))) {

			String sCurrentLine;
//			final int numObjs = 4;
			while ((sCurrentLine = br.readLine()) != null) {

				if (!sCurrentLine.contains("solID")) {

					String[] split = sCurrentLine.split(",");

					ptList.add(new RPointSolution(numObjs).setID(Integer.parseInt(split[0]))
							.setPointSolution(Arrays.asList((Arrays.copyOfRange(split, 1, numObjs + 1)))));
				}
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		return ptList;
	}

	public BufferedWriter getFileWriter(String fileName) {
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			throw new JMetalException("Exception when calling method getFileWriter()", e);
		}
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

		return new BufferedWriter(outputStreamWriter);
	}

	public void printObjectivesToFile(BufferedWriter bufferedWriter, List<RPointSolution> solutionList) {

		String separator = ",";

		try {
			// prints the header of the file
			String header;
			if (Configurator.eINSTANCE.getObjectives() == 4)
				header = String.format("solID%sperfQ%s#changes%spas%sreliability", separator, separator, separator,
						separator);
			else
				header = String.format("solID%sperfQ%s#changes%sreliability", separator, separator, separator);
			bufferedWriter.write(header);
			bufferedWriter.newLine();
			if (solutionList.size() > 0) {
				int numberOfObjectives = solutionList.get(0).getNumberOfObjectives();
				for (int i = 0; i < solutionList.size(); i++) {
					bufferedWriter.write(solutionList.get(i).getID() + separator);
					for (int j = 0; j < numberOfObjectives - 1; j++) {
						bufferedWriter.write(solutionList.get(i).getObjective(j) + separator);
					}
					bufferedWriter.write("" + solutionList.get(i).getObjective(numberOfObjectives - 1));
					bufferedWriter.newLine();
				}
			}

			bufferedWriter.close();
		} catch (IOException e) {
			throw new JMetalException("Error printing objecives to file: ", e);
		}
	}

}
