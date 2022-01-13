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

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.archive.impl.NonDominatedSolutionListArchive;
import org.uma.jmetal.util.solutionattribute.impl.GenericSolutionAttribute;

import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.factory.FactoryBuilder;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class RGenerateReferenceParetoTest {
			

	private String baseDir, objectivesDir, qiDir, referenceFrontDir, problemFolderName;
	private String[] problemNames, evals, brfs, probPas;

	@Before
	public void setup() {

		problemFolderName = "/home/peo/git/sealab/seaa2021_jist_si_data/data/reference_paretos/";
		baseDir = "/home/peo/git/sealab/seaa2021_jist_si_data/data";
		objectivesDir = baseDir + "/objectives";
		qiDir = baseDir + "/quality_indicators";
		referenceFrontDir = baseDir + "/reference_paretos";

		problemNames = new String[] { "train-ticket", "simplified-cocome", "simplified-cocome-newrel" };
		probPas = new String[] { "95", "80", "55" };
		evals = new String[] { "72", "82", "102" };
		brfs = new String[] { "moc_1.64", "moc_1.23" };

	}
	
	private NonDominatedSolutionListArchive<RPointSolution> extractNonDominatedArchive(List<Path> paths) {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();
		for (Path path : paths) {

			List<RPointSolution> ptSolutionList = generateRPointSolutionList(path.toString(), 4);
			GenericSolutionAttribute<RPointSolution, String> solutionAttribute = new GenericSolutionAttribute<RPointSolution, String>();

			for (RPointSolution solution : ptSolutionList) {
				solutionAttribute.setAttribute(solution, "NSGAII");
				nonDominatedSolutionArchive.add(solution);
			}
		}
		return nonDominatedSolutionArchive;
	}

	@Test
	public void generateSuperReferenceParetoGroupByProbPasTest() throws IOException {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		List<Path> referenceFronts;

		for (final String pName : problemNames) {
			for (final String probPa : probPas) {
				int objectives = 4;
				if ("00".equals(probPa))
					objectives = 3;

				try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName))) {
					referenceFronts = walk.filter(Files::isRegularFile)
							.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
									&& path.toString().contains("ProbPAs_0." + probPa)
									&& !path.toString().contains("super_pareto"))
							.collect(Collectors.toList());
				}

				nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts);

				printObjectivesToFile(getFileWriter(
						String.format("%s/%s__super_pareto__ProbPAs_0.%s.rf", problemFolderName, pName, probPa)),
						nonDominatedSolutionArchive.getSolutionList());
			}
		}
	}

	@Test
	public void generateSuperReferenceParetoGroupByProblemPasTest() throws IOException {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		List<Path> referenceFronts;

		for (final String pName : problemNames) {
			int objectives = 4;

			try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName))) {
				referenceFronts = walk.filter(Files::isRegularFile)
						.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
								&& !path.toString().contains("ProbPAs_0.00")
								&& !path.toString().contains("super_pareto"))
						.collect(Collectors.toList());
			}

			nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts);

			printObjectivesToFile(
					getFileWriter(
							String.format("%s/%s__super_pareto__PAs.rf", problemFolderName, pName)),
					nonDominatedSolutionArchive.getSolutionList());
		}
	}

	@Test
	public void generateSuperReferenceParetoGroupByBRFPasTest() throws IOException {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		List<Path> referenceFronts;

		for (final String pName : problemNames) {
			for (final String brf : brfs) {
				int objectives = 4;
				try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName))) {
					referenceFronts = walk.filter(Files::isRegularFile)
							.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
									&& path.toString().contains(brf) && !path.toString().contains("ProbPAs_0.00")
									&& !path.toString().contains("super_pareto"))
							.collect(Collectors.toList());
				}

				nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts);

				printObjectivesToFile(
						getFileWriter(
								String.format("%s/%s__super_pareto__BRF_%s__PAs.rf", problemFolderName, pName, brf)),
						nonDominatedSolutionArchive.getSolutionList());
			}
		}
	}

	@Test
	public void generateSuperReferenceParetoGroupByEvalPasTest() throws IOException {

		List<RPointSolution> ptList = new ArrayList<>();
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		List<Path> referenceFronts;

		for (final String pName : problemNames) {
			for (final String eval : evals) {
				int objectives = 4;
				try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName))) {
					referenceFronts = walk.filter(Files::isRegularFile)
							.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
									&& path.toString().contains(eval) && !path.toString().contains("ProbPAs_0.00")
									&& !path.toString().contains("super_pareto"))
							.collect(Collectors.toList());
				}

				nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts);

				printObjectivesToFile(getFileWriter(
						String.format("%s/%s__super_pareto__MaxEval_%s__PAs.rf", problemFolderName, pName, eval)),
						nonDominatedSolutionArchive.getSolutionList());
			}
		}
	}

	public List<RPointSolution> generateRPointSolutionList(String sol, int numObjs) {

		List<RPointSolution> ptList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(sol))) {

			String sCurrentLine;
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

		try {
			// prints the header of the file
			String header = "solID,perfQ,#changes,pas,reliability";
			bufferedWriter.write(header);
			bufferedWriter.newLine();
			if (solutionList.size() > 0) {
				int numberOfObjectives = solutionList.get(0).getNumberOfObjectives();
				for (int i = 0; i < solutionList.size(); i++) {
					bufferedWriter.write(solutionList.get(i).getID() + ",");
					for (int j = 0; j < numberOfObjectives - 1; j++) {
						bufferedWriter.write(solutionList.get(i).getObjective(j) + ",");
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
