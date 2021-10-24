package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.uma.jmetal.util.archive.impl.NonDominatedSolutionListArchive;
import org.uma.jmetal.util.solutionattribute.impl.GenericSolutionAttribute;

public class RGenerateReferenceParetoTest {

	@Test
	public void generateSuperReferenceParetoTest() throws IOException {

//		new RGenerateReferenceParetoFront(experiment).run();

		List<RPointSolution> ptList = new ArrayList<>();
		List<String> referenceFronts;
		NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

//		String problemName = "train-ticket_Length_4_CloningWeight_1.5_MaxCloning_3_MaxEval_72";
//		String problemFolderName = "/mnt/store/research/easier/uml_case_studies/performance_comparison/nsga_72/";
		String problemFolderName = "/mnt/store/research/easier/uml_case_studies/performance_comparison";
		try (Stream<Path> walk = Files.walk(Paths.get(problemFolderName))){
				//String.format("%s/%s/referenceFront", problemFolderName, problemName)))) {
			referenceFronts = walk.filter(p -> !Files.isDirectory(p)) // not a directory
					.map(p -> p.toString())// .toLowerCase()) // convert path to string
					.filter(f -> f.endsWith("csv") && f.contains("reference_pareto")) // check end with
					.collect(Collectors.toList()); // collect all matched to a List
		}

		for (String front : referenceFronts) {

			try (BufferedReader br = new BufferedReader(new FileReader(front))) {

				String sCurrentLine;
				final int numObjs = 4;
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
		}

		GenericSolutionAttribute<RPointSolution, String> solutionAttribute = new GenericSolutionAttribute<RPointSolution, String>();

		for (RPointSolution solution : ptList) {
			solutionAttribute.setAttribute(solution, "NSGAII");
			nonDominatedSolutionArchive.add(solution);
		}

//		new RSolutionListOutput(nonDominatedSolutionArchive.getSolutionList()).printObjectivesToFile(problemFolderName+"/super-reference-pareto.csv");
//				String.format("/mnt/store/research/easier/uml_case_studies/tuning/%s/referenceFront/super-reference-pareto.rf",problemName));

	}

	@Test
	public void generateParetoPerProblem() throws IOException {

		List<String> funSolutions;

		String[] problemName = { "pesa_" };
		String problemFolderName = "/mnt/store/research/easier/uml_case_studies/performance_comparison";

		int[] evals = { 72, 82, 102 };

		for (int j = 0; j < problemName.length; j++) {

			for (int i = 0; i < evals.length; i++) {

				String solutionFolder = String.format(
						"%s/%s%d/Exp/data/PESA2/train-ticket_Length_4_CloningWeight_1.5_MaxCloning_3_MaxEval_%d",
						problemFolderName, problemName[j], evals[i],evals[i]);

				NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

				try (Stream<Path> walk = Files.walk(Paths.get(solutionFolder))) {
					funSolutions = walk.filter(p -> !Files.isDirectory(p)) // not a directory
							.map(p -> p.toString())// .toLowerCase()) // convert path to string
							.filter(f -> f.endsWith("csv") && f.contains("FUN") && !f.contains("IGD+")) // check end
																										// with
							.collect(Collectors.toList()); // collect all matched to a List
				}

				for (String sol : funSolutions) {

					List<RPointSolution> ptSolutionList = generateRPointSolutionList(sol);
					GenericSolutionAttribute<RPointSolution, String> solutionAttribute = new GenericSolutionAttribute<RPointSolution, String>();

					for (RPointSolution solution : ptSolutionList) {
						solutionAttribute.setAttribute(solution, "PESA2");
						nonDominatedSolutionArchive.add(solution);
					}
				}

//				new RSolutionListOutput(nonDominatedSolutionArchive.getSolutionList()).printObjectivesToFile(String
//						.format("%s/%s%d/train-ticket_Length_4_CloningWeight_1.5_MaxCloning_3_MaxEval_%d_gen.rf",
//								problemFolderName, problemName[j], evals[i], evals[i]));
			}
		}
//		}
	}

	public List<RPointSolution> generateRPointSolutionList(String sol) {

		List<RPointSolution> ptList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(sol))) {

			String sCurrentLine;
			final int numObjs = 4;
			while ((sCurrentLine = br.readLine()) != null) {

				String[] split = sCurrentLine.split(",");

				ptList.add(new RPointSolution(numObjs).setID(Integer.parseInt(split[0]))
						.setPointSolution(Arrays.asList((Arrays.copyOfRange(split, 1, numObjs + 1)))));
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
		return ptList;
	}

}
