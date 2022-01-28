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
import java.util.stream.Collector;
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

public class RGenerateSuperReferenceParetoTest {

	private String baseDir, objectivesDir, qiDir, referenceFrontDir, algoSuffix;
	private String[] problemNames, evals, brfs, probPas;

	@Before
	public void setup() {

		baseDir = "/home/peo/git/sealab/seaa2021_jist_si_data/data";
		objectivesDir = baseDir + "/objectives";
		qiDir = baseDir + "/quality_indicators";
		referenceFrontDir = baseDir + "/reference_paretos";

		problemNames = new String[] { "train-ticket", "simplified-cocome", "simplified-cocome-newrel" };
		probPas = new String[] { "95", "80", "55" };
		evals = new String[] { "72", "82", "102" };
		brfs = new String[] { "moc_1.64", "moc_1.23" };

		algoSuffix = "__Algo_nsgaii";

	}

	private NonDominatedSolutionListArchive<RPointSolution> extractNonDominatedArchive(List<Path> paths,
			boolean filtering) {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();
		for (Path path : paths) {

			List<RPointSolution> ptSolutionList = generateRPointSolutionList(path.toString(), 4, filtering);
			GenericSolutionAttribute<RPointSolution, String> solutionAttribute = new GenericSolutionAttribute<RPointSolution, String>();

			for (RPointSolution solution : ptSolutionList) {
				solutionAttribute.setAttribute(solution, "NSGAII");
				nonDominatedSolutionArchive.add(solution);
			}
		}
		return nonDominatedSolutionArchive;
	}

	private List<Path> extractReferenceFronts(String pName) throws IOException {
		List<Path> referenceFronts;

		Stream<Path> walk = Files.walk(Paths.get(referenceFrontDir));
		referenceFronts = walk
				.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".rf")
						&& path.toString().contains(pName + "_") && !path.toString().contains("super_pareto"))
				.collect(Collectors.toList());
		walk.close();
		return referenceFronts;
	}

	@Test
	public void generateSuperReferenceParetoGroupByProbPasTest() throws IOException {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		List<Path> referenceFronts;

		for (final String pName : problemNames) {
			for (final String probPa : probPas) {

				referenceFronts = extractReferenceFronts(pName).stream()
						.filter(path -> path.toString().contains("ProbPAs_0." + probPa)).collect(Collectors.toList());

				nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts, false);

				printObjectivesToFile(getFileWriter(String.format("%s/%s__super_pareto__ProbPAs_0.%s%s.rf",
						referenceFrontDir, pName, probPa, algoSuffix)), nonDominatedSolutionArchive.getSolutionList());
			}
		}
	}

	@Test
	public void generateSuperReferenceParetoGroupByProblemPasTest() throws IOException {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		List<Path> referenceFronts;

		for (final String pName : problemNames) {

			referenceFronts = extractReferenceFronts(pName).stream().filter(
					path -> !path.toString().contains("optimal_pareto") && !path.toString().contains("ProbPAs_0.00"))
					.collect(Collectors.toList());

			nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts, false);

			printObjectivesToFile(
					getFileWriter(String.format("%s/%s__super_pareto__PAs%s.rf", referenceFrontDir, pName, algoSuffix)),
					nonDominatedSolutionArchive.getSolutionList());
		}
	}

	@Test
	public void generateSuperReferenceParetoGroupByBRFPasTest() throws IOException {
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		List<Path> referenceFronts;

		for (final String pName : problemNames) {
			for (final String brf : brfs) {
				referenceFronts = extractReferenceFronts(pName).stream()
						.filter(path -> !path.toString().contains("ProbPAs_0.00") && path.toString().contains(brf))
						.collect(Collectors.toList());

				nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts, false);

				printObjectivesToFile(getFileWriter(String.format("%s/%s__super_pareto__BRF_%s__PAs%s.rf",
						referenceFrontDir, pName, brf, algoSuffix)), nonDominatedSolutionArchive.getSolutionList());
			}
		}
	}

	@Test
	public void generateSuperReferenceParetoGroupByEvalPasTest() throws IOException {

		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		List<Path> referenceFronts;

		for (final String pName : problemNames) {
			for (final String eval : evals) {

				referenceFronts = extractReferenceFronts(pName).stream()
						.filter(path -> !path.toString().contains("ProbPAs_0.00") && path.toString().contains(eval))
						.collect(Collectors.toList());

				nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts, false);

				printObjectivesToFile(getFileWriter(String.format("%s/%s__super_pareto__MaxEval_%s__PAs%s.rf",
						referenceFrontDir, pName, eval, algoSuffix)), nonDominatedSolutionArchive.getSolutionList());
			}
		}
	}

	@Test
	public void generateSuperReferenceParetoAcrossEvalTest() throws IOException {

		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		List<Path> referenceFronts;

		List<String> auxList = new ArrayList<String>(Arrays.asList(probPas));
		auxList.add("00");
		probPas = null;
		probPas = auxList.toArray(String[]::new);

		for (final String pName : problemNames) {
			for (String brf : brfs) {
				for (String pas : probPas) {

					referenceFronts = extractReferenceFronts(pName).stream().filter(
							path -> path.toString().contains("ProbPAs_0." + pas) && path.toString().contains(brf))
							.collect(Collectors.toList());

					nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts, false);

					printObjectivesToFile(
							getFileWriter(String.format("%s/%s__super_pareto__BRF_%s_ProbPAs_0.%s%s.rf",
									referenceFrontDir, pName, brf, pas, algoSuffix)),
							nonDominatedSolutionArchive.getSolutionList());
				}
			}
		}
	}

	@Test
	public void generateOptimalParetoTest() throws IOException {

		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

		List<Path> referenceFronts;

		for (final String pName : problemNames) {
			referenceFronts = extractReferenceFronts(pName);

			nonDominatedSolutionArchive = extractNonDominatedArchive(referenceFronts, true);

			printObjectivesToFile(
					getFileWriter(String.format("%s/%s__optimal_pareto%s.rf", referenceFrontDir, pName, algoSuffix)),
					nonDominatedSolutionArchive.getSolutionList());
		}
	}

	public List<RPointSolution> generateRPointSolutionList(String sol, int numObjs, boolean filtering) {

		List<RPointSolution> ptList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(sol))) {

			String sCurrentLine;
			int indexPAs = -1;
			while ((sCurrentLine = br.readLine()) != null) {

				int numberObjectives = sCurrentLine.split(",").length - 1;

				if (!sCurrentLine.contains("solID")) {

					String[] split = sCurrentLine.split(",");

					if (filtering && numberObjectives == 4) {
						split[indexPAs] = split[indexPAs + 1];
						numberObjectives--;
					}

					ptList.add(new RPointSolution(numberObjectives).setID(Integer.parseInt(split[0]))
							.setPointSolution(Arrays.asList((Arrays.copyOfRange(split, 1, numberObjectives + 1)))));
				} else {
					String[] split = sCurrentLine.split(",");
					indexPAs = Arrays.asList(split).indexOf("pas");
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

			if (solutionList.size() > 0) {
				// prints the header of the file
				int numberOfObjectives = solutionList.get(0).getNumberOfObjectives();
				String header = "solID,perfQ,#changes,pas,reliability";
//				int pasIndex = Arrays.asList(header.split(",")).indexOf("pas") - 1;
				if (numberOfObjectives == 3)
					header = "solID,perfQ,#changes,reliability";

				bufferedWriter.write(header);
				bufferedWriter.newLine();
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
