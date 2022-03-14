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

import org.junit.Ignore;
import org.junit.Test;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.archive.impl.NonDominatedSolutionListArchive;
import org.uma.jmetal.util.solutionattribute.impl.GenericSolutionAttribute;

import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class RGenerateReferenceParetoNoPasTest {

	String newRel = "-newrel";

	private NonDominatedSolutionListArchive<RPointSolution> extractNonDominatedArchive(List<Path> paths,
			int objectives) {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();
		for (Path path : paths) {

			List<RPointSolution> ptSolutionList = generateRPointSolutionList(path.toString(), objectives);
			GenericSolutionAttribute<RPointSolution, String> solutionAttribute = new GenericSolutionAttribute<RPointSolution, String>();

			for (RPointSolution solution : ptSolutionList) {
				solutionAttribute.setAttribute(solution, "NSGAII");
				nonDominatedSolutionArchive.add(solution);
			}
		}
		return nonDominatedSolutionArchive;
	}

	@Test
	public void generateSuperReferenceParetoGroupByProblemNoPasTest() throws IOException {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		String problemFolderName = "/home/peo/git/sealab/seaa2021_jist_si_data/data/reference_paretos/";
		List<Path> referenceFronts;

		String[] problemName = { "simplified-cocome", "train-ticket" };
		String[] evals = { "72", "82", "102" };
		String[] probPas = { "55", "80", "95", "00" };

		String[] brfs = { "moc_1.64", "moc_1.23" };

		for (final String pName : problemName) {
			int objectives = 3;

			try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName))) {
				referenceFronts = walk.filter(Files::isRegularFile)
						.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
								&& path.toString().contains("ProbPAs_0.00")
								&& !path.toString().contains("super_pareto"))
						.collect(Collectors.toList());
			}

			nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts, objectives);

			printObjectivesToFile(
					getFileWriter(String.format("%s/%s__super_pareto__No_PAs.rf", problemFolderName, pName)),
					nonDominatedSolutionArchive.getSolutionList(), objectives);
		}
	}

	@Test
	public void generateSuperReferenceParetoGroupByBRFNoPasTest() throws IOException {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		String problemFolderName = "/home/peo/git/sealab/seaa2021_jist_si_data/data/reference_paretos/";
		List<Path> referenceFronts;

		String[] problemName = { "simplified-cocome", "train-ticket" };
		String[] evals = { "72", "82", "102" };
		String[] probPas = { "55", "80", "95", "00" };

		String[] brfs = { "moc_1.64", "moc_1.23" };

		for (final String pName : problemName) {
			for (final String brf : brfs) {
				int objectives = 3;
				try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName))) {
					referenceFronts = walk.filter(Files::isRegularFile)
							.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
									&& path.toString().contains(brf) && path.toString().contains("ProbPAs_0.00")
									&& !path.toString().contains("super_pareto"))
							.collect(Collectors.toList());
				}

				nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts, objectives);

				printObjectivesToFile(
						getFileWriter(
								String.format("%s/%s__super_pareto__BRF_%s__No_PAs.rf", problemFolderName, pName, brf)),
						nonDominatedSolutionArchive.getSolutionList(), objectives);
			}
		}
	}

	@Test
	public void generateSuperReferenceParetoGroupByEvalNoPasTest() throws IOException {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		String problemFolderName = "/home/peo/git/sealab/seaa2021_jist_si_data/data/reference_paretos/";
		List<Path> referenceFronts;

		String[] problemName = { "simplified-cocome", "train-ticket" };
		String[] evals = { "72", "82", "102" };
		String[] probPas = { "55", "80", "95", "00" };

		String[] brfs = { "moc_1.64", "moc_1.23" };

		for (final String pName : problemName) {
			for (final String eval : evals) {
				int objectives = 3;
				try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName))) {
					referenceFronts = walk.filter(Files::isRegularFile)
							.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
									&& path.toString().contains(eval) && path.toString().contains("ProbPAs_0.00")
									&& !path.toString().contains("super_pareto"))
							.collect(Collectors.toList());
				}

				nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts, objectives);

				printObjectivesToFile(getFileWriter(
						String.format("%s/%s__super_pareto__MaxEval_%s__No_PAs.rf", problemFolderName, pName, eval)),
						nonDominatedSolutionArchive.getSolutionList(), objectives);
			}
		}
	}

	@Test
	public void generateParetoPerProblem() throws IOException {

		List<Path> funSolutions;

		String[] problemName = { "simplified-cocome", "train-ticket" };

		String problemFolderName = "/home/peo/git/sealab/2022-seaa-data/data/by_time/";
		String referenceParetoFolder = problemFolderName + "/reference_paretos";

		String[] algoSuffixs = new String[] { "__Algo_nsgaii", "__Algo_spea2", "__Algo_pesa2" };
		String[] searchBudgets = new String[] { "sb_900000", "sb_3600000", "sb_1800000" };

		String[] brfs = { "moc_1.64" };// , "moc_1.23" };

		int[] evals = { 72, 82, 102 };
//		int[] probPas = { 55, 80, 95, 0 };
		int[] probPas = { 95 };

		for (final String pName : problemName) {
			for (final int eval : evals) {
//				for (final int probPa : probPas) {
					int objectives = 4;
//					if (probPa == 0) {
//						objectives = 3;
//					}
//					for (final String brf : brfs) {
				for (String sBudget : searchBudgets) {
					for (String suffix : algoSuffixs) {

						NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

						try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName+"/objectives"))) {
							funSolutions = walk.filter(Files::isRegularFile)
									.filter(path -> path.toString().endsWith(".csv") && path.toString().contains("FUN")
											&& path.toString().contains(pName) && path.toString().contains("" + eval)
											&& path.toString().contains("ProbPAs_0." + probPas[0])
											&& path.toString().contains(sBudget) && path.toString().contains(suffix))
									.collect(Collectors.toList());
						}

						for (Path sol : funSolutions) {

							List<RPointSolution> ptSolutionList = generateRPointSolutionList(sol.toString(),
									objectives);
							GenericSolutionAttribute<RPointSolution, String> solutionAttribute = new GenericSolutionAttribute<RPointSolution, String>();

							for (RPointSolution solution : ptSolutionList) {
								solutionAttribute.setAttribute(solution, suffix);
								nonDominatedSolutionArchive.add(solution);
							}
						}

						printObjectivesToFile(getFileWriter(String.format(
								"%s/%s__BRF_%s__MaxEval_%d__ProbPAs_0.%d__%s%s.rf",
								referenceParetoFolder, pName, brfs[0], eval, probPas[0], sBudget, suffix)),
								nonDominatedSolutionArchive.getSolutionList(), objectives);
					}
//					}
//					}
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

	public void printObjectivesToFile(BufferedWriter bufferedWriter, List<RPointSolution> solutionList,
			int objectives) {

		String separator = ",";

		try {
			// prints the header of the file
			String header;
			if (objectives == 4)
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
