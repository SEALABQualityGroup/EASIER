//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
// package org.uma.jmetal.util.experiment.component;

package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.archive.impl.NonDominatedSolutionListArchive;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentComponent;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.front.Front;
import org.uma.jmetal.util.front.imp.ArrayFront;
import org.uma.jmetal.util.front.util.FrontUtils;
import org.uma.jmetal.util.point.util.PointSolution;
import org.uma.jmetal.util.solutionattribute.impl.GenericSolutionAttribute;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

/**
 * This class computes a reference Pareto front from a set of files. Once the
 * algorithms of an experiment have been executed through running an instance of
 * class {@link ExecuteAlgorithms}, all the obtained fronts of all the
 * algorithms are gathered per problem; then, the dominated solutions are
 * removed and the final result is a file per problem containing the reference
 * Pareto front.
 *
 * By default, the files are stored in a directory called "referenceFront",
 * which is located in the experiment base directory. Each front is named
 * following the scheme "problemName.rf".
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class RGenerateReferenceParetoFront implements ExperimentComponent {
	private final Experiment<?, ?> experiment;

	public RGenerateReferenceParetoFront(Experiment<?, ?> experimentConfiguration) {
		this.experiment = experimentConfiguration;
		experiment.removeDuplicatedAlgorithms();
	}

	/**
	 * The run() method creates de output directory and compute the fronts
	 */
	@Override
	public void run() throws IOException {

		String outputDirectoryName = experiment.getReferenceFrontDirectory();

		File outputDirectory = createOutputDirectory(outputDirectoryName);

		List<String> referenceFrontFileNames = new LinkedList<>();
		for (ExperimentProblem<?> problem : experiment.getProblemList()) {
			NonDominatedSolutionListArchive<RPointSolution> nonDominatedSolutionArchive = new NonDominatedSolutionListArchive<RPointSolution>();

			for (ExperimentAlgorithm<?, ?> algorithm : experiment.getAlgorithmList()) {
				String problemDirectory = experiment.getExperimentBaseDirectory() + "/data/"
						+ algorithm.getAlgorithmTag() + "/" + problem.getTag();

				for (int i = 0; i < experiment.getIndependentRuns(); i++) {

					String frontFileName = problemDirectory + "/" + experiment.getOutputParetoFrontFileName() + i
							+ ".tsv";

					String varFileName = problemDirectory + "/" + experiment.getOutputParetoSetFileName() + i + ".tsv";

					if (new File(varFileName).exists()) {

						List<RPointSolution> ptSolutionList = generateRPointSolutionList(varFileName);
						// List<PointSolution> solutionList = generateSolutionList(frontFileName,
						// varFileName);

						// GenericSolutionAttribute<PointSolution, String> solutionAttribute = new
						// GenericSolutionAttribute<PointSolution, String>();
						GenericSolutionAttribute<RPointSolution, String> solutionAttribute = new GenericSolutionAttribute<RPointSolution, String>();

						for (RPointSolution solution : ptSolutionList) {
							solutionAttribute.setAttribute(solution, algorithm.getAlgorithmTag());
							nonDominatedSolutionArchive.add(solution);
						}
					}
				}
			}

			String referenceSetFileName = outputDirectoryName + "/" + problem.getTag() + ".rf";
			referenceFrontFileNames.add(problem.getTag() + ".rf");

			new RSolutionListOutput(nonDominatedSolutionArchive.getSolutionList())
					.printObjectivesToFile(referenceSetFileName);

			writeFilesWithTheSolutionsContributedByEachAlgorithm(outputDirectoryName, problem.getProblem(),
					nonDominatedSolutionArchive.getSolutionList());
		}

		experiment.setReferenceFrontFileNames(referenceFrontFileNames);
	}

	private List<RPointSolution> generateRPointSolutionList(String varFileName) {

		List<RPointSolution> ptList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(varFileName))) {

			String sCurrentLine;
			final String strID = ":";
			final String strObjStart = "(";
			final String strObjEnd = ")";
			final String separator = ",";
			while ((sCurrentLine = br.readLine()) != null) {

				if (sCurrentLine.contains(strID)) {
					ptList.add(
							new RPointSolution()
									.setID(Integer
											.parseInt(
													sCurrentLine.substring(sCurrentLine.indexOf(strID) + 2,
															sCurrentLine.indexOf(strObjStart) - 1)))
									.setPointSolution(
											Arrays.asList(
													sCurrentLine
															.substring(sCurrentLine.indexOf(strObjStart) + 1,
																	sCurrentLine.indexOf(strObjEnd))
															.split(separator))));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ptList;
	}

	private List<PointSolution> generateSolutionList(String frontFileName, String varFileName) {
		Front front;
		try {
			front = new ArrayFront(frontFileName);
			List<PointSolution> solutionList = FrontUtils.convertFrontToSolutionList(front);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private File createOutputDirectory(String outputDirectoryName) {
		File outputDirectory;
		outputDirectory = new File(outputDirectoryName);
		if (!outputDirectory.exists()) {
			boolean result = new File(outputDirectoryName).mkdir();
			JMetalLogger.logger.info("Creating " + outputDirectoryName + ". Status = " + result);
		}

		return outputDirectory;
	}

	private void writeFilesWithTheSolutionsContributedByEachAlgorithm(String outputDirectoryName, Problem<?> problem,
			List<RPointSolution> nonDominatedSolutions) throws IOException {
		GenericSolutionAttribute<RPointSolution, String> solutionAttribute = new GenericSolutionAttribute<RPointSolution, String>();

		for (ExperimentAlgorithm<?, ?> algorithm : experiment.getAlgorithmList()) {
			List<RPointSolution> solutionsPerAlgorithm = new ArrayList<>();
			for (RPointSolution solution : nonDominatedSolutions) {
				if (algorithm.getAlgorithmTag().equals(solutionAttribute.getAttribute(solution))) {
					solutionsPerAlgorithm.add(solution);
				}
			}

			new RSolutionListOutput(solutionsPerAlgorithm).printObjectivesToFile(
					outputDirectoryName + "/" + problem.getName() + "." + algorithm.getAlgorithmTag() + ".rf");
		}
	}
}
