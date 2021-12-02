package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util;
//This program is free software: you can redistribute it and/or modify

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

//it under the terms of the GNU Lesser General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU Lesser General Public License for more details.
//
//You should have received a copy of the GNU Lesser General Public License
//along with this program.  If not, see <http://www.gnu.org/licenses/>.

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.uma.jmetal.lab.experiment.Experiment;
import org.uma.jmetal.lab.experiment.component.ExperimentComponent;
import org.uma.jmetal.lab.experiment.component.impl.ExecuteAlgorithms;
import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.lab.experiment.util.ExperimentProblem;
import org.uma.jmetal.qualityindicator.QualityIndicator;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.front.Front;
import org.uma.jmetal.util.front.impl.ArrayFront;
import org.uma.jmetal.util.front.util.FrontNormalizer;
import org.uma.jmetal.util.front.util.FrontUtils;
import org.uma.jmetal.util.point.PointSolution;

/**
 * This class computes the {@link QualityIndicator}s of an experiment. Once the
 * algorithms of an experiment have been executed through running an instance of
 * class {@link ExecuteAlgorithms}, the list of indicators in obtained from the
 * {@link ExperimentComponent #getIndicatorsList()} method. Then, for every
 * combination algorithm + problem, the indicators are applied to all the FUN
 * files and the resulting values are store in a file called as
 * {@link QualityIndicator #getName()}, which is located in the same directory
 * of the FUN files.
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class RComputeQualityIndicators<S extends Solution<?>, Result extends List<S>> implements ExperimentComponent {

	private final Experiment<S, Result> experiment;
	private Map<String, List<Double>> qualityIndicatorsMap;

	String qualityIndicatorFile;

	public RComputeQualityIndicators(Experiment<S, Result> experiment) {
		this.experiment = experiment;
	}

	@Override
	public void run() throws IOException {
		experiment.removeDuplicatedAlgorithms();
		resetIndicatorFiles();
		
		String suffix = "__" + experiment.getProblemList().get(0).getTag();

		for (GenericIndicator<S> indicator : experiment.getIndicatorList()) {
			JMetalLogger.logger.info("Computing indicator: " + indicator.getName());

			for (ExperimentAlgorithm<?, Result> algorithm : experiment.getAlgorithmList()) {
				String algorithmDirectory;
				algorithmDirectory = experiment.getExperimentBaseDirectory() + "/data/" + algorithm.getAlgorithmTag();
				for (ExperimentProblem<?> problem : experiment.getProblemList()) {
					String problemDirectory = algorithmDirectory + "/" + problem.getTag();

					String referenceFrontDirectory = experiment.getReferenceFrontDirectory();

					String referenceFrontName = referenceFrontDirectory + "/" + problem.getReferenceFront();

					JMetalLogger.logger.info("RF: " + referenceFrontName);

					Front referenceFront = new ArrayFront(referenceFrontName, ",");

					FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
					Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

					String qualityIndicatorFile = problemDirectory + "/" + indicator.getName() + suffix;

					indicator.setReferenceParetoFront(normalizedReferenceFront);

					double[] indicatorValues = new double[experiment.getIndependentRuns()];
					IntStream.range(0, experiment.getIndependentRuns()).forEach(run -> {
						String frontFileName = problemDirectory + "/" + experiment.getOutputParetoFrontFileName() + run
								+ "__" + problem.getTag() + ".csv";
						Front front = null;
						try {
							front = new ArrayFront(removeSolID(frontFileName), ",");
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						Front normalizedFront = frontNormalizer.normalize(front);
						List<PointSolution> normalizedPopulation = FrontUtils
								.convertFrontToSolutionList(normalizedFront);
						Double indicatorValue = indicator.evaluate((List<S>) normalizedPopulation);
						JMetalLogger.logger.info(indicator.getName() + ": " + indicatorValue);
						indicatorValues[run] = indicatorValue;
					});

					for (double indicatorValue : indicatorValues) {
						writeQualityIndicatorValueToFile(indicatorValue, qualityIndicatorFile);
					}
				}
			}
		}
		findBestIndicatorFronts(experiment);
		writeSummaryFile(experiment);
	}

	public Map<String, List<Double>> getIndicatorsMap() {
		return qualityIndicatorsMap;
	}

	public String removeSolID(String frontFileName) {

		File tmpFile;
		String tmpFileName = null;
		try {
			tmpFile = File.createTempFile("front", "");
			tmpFile.deleteOnExit();
			tmpFileName = tmpFile.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String readLine = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(frontFileName));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile))) {
			while ((readLine = reader.readLine()) != null) {
				if (!readLine.contains("solID")) {
//				} else {
					String line = readLine.split(",", 2)[1];
					writer.write(line);
					writer.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tmpFileName;
	}

	private void writeQualityIndicatorValueToFile(Double indicatorValue, String qualityIndicatorFile) {
		FileWriter os;
		try {
			os = new FileWriter(qualityIndicatorFile, true);
			os.write("" + indicatorValue + "\n");
			os.close();
		} catch (IOException ex) {
			throw new JMetalException("Error writing indicator file" + ex);
		}
	}

	/**
	 * Deletes the files containing the indicator values if the exist.
	 */
	private void resetIndicatorFiles() {
		for (GenericIndicator<S> indicator : experiment.getIndicatorList()) {
			for (ExperimentAlgorithm<?, Result> algorithm : experiment.getAlgorithmList()) {
				for (ExperimentProblem<?> problem : experiment.getProblemList()) {
					String algorithmDirectory;
					algorithmDirectory = experiment.getExperimentBaseDirectory() + "/data/"
							+ algorithm.getAlgorithmTag();
					String problemDirectory = algorithmDirectory + "/" + problem.getTag();
					String qualityIndicatorFile = problemDirectory + "/" + indicator.getName();

					resetFile(qualityIndicatorFile);
				}
			}
		}
	}

	/**
	 * Deletes a file or directory if it does exist
	 * 
	 * @param file
	 */
	private void resetFile(String file) {
		File f = new File(file);
		if (f.exists()) {
			JMetalLogger.logger.info("File " + file + " exist.");

			if (f.isDirectory()) {
				JMetalLogger.logger.info("File " + file + " is a directory. Deleting directory.");
				if (f.delete()) {
					JMetalLogger.logger.info("Directory successfully deleted.");
				} else {
					JMetalLogger.logger.info("Error deleting directory.");
				}
			} else {
				JMetalLogger.logger.info("File " + file + " is a file. Deleting file.");
				if (f.delete()) {
					JMetalLogger.logger.info("File succesfully deleted.");
				} else {
					JMetalLogger.logger.info("Error deleting file.");
				}
			}
		} else {
			JMetalLogger.logger.info("File " + file + " does NOT exist.");
		}
	}

	public void findBestIndicatorFronts(Experiment<?, Result> experiment) throws IOException {
		for (GenericIndicator<?> indicator : experiment.getIndicatorList()) {
			for (ExperimentAlgorithm<?, Result> algorithm : experiment.getAlgorithmList()) {
				String algorithmDirectory;
				algorithmDirectory = experiment.getExperimentBaseDirectory() + "/data/" + algorithm.getAlgorithmTag();

				for (ExperimentProblem<?> problem : experiment.getProblemList()) {
					String indicatorFileName = algorithmDirectory + "/" + problem.getTag() + "/" + indicator.getName() + "__" + problem.getTag();
					Path indicatorFile = Paths.get(indicatorFileName);
					if (indicatorFile != null && Files.exists(indicatorFile)) {

						List<String> fileArray;
						fileArray = Files.readAllLines(indicatorFile, StandardCharsets.UTF_8);

						List<Pair<Double, Integer>> list = new ArrayList<>();

						for (int i = 0; i < fileArray.size(); i++) {
							Pair<Double, Integer> pair = new ImmutablePair<>(Double.parseDouble(fileArray.get(i)), i);
							list.add(pair);
						}

						Collections.sort(list, new Comparator<Pair<Double, Integer>>() {
							@Override
							public int compare(Pair<Double, Integer> pair1, Pair<Double, Integer> pair2) {
								if (Math.abs(pair1.getLeft()) > Math.abs(pair2.getLeft())) {
									return 1;
								} else if (Math.abs(pair1.getLeft()) < Math.abs(pair2.getLeft())) {
									return -1;
								} else {
									return 0;
								}
							}
						});
						String bestFunFileName;
						String bestVarFileName;
						String medianFunFileName;
						String medianVarFileName;
						
						final String suffix = "__" + problem.getTag();

						String outputDirectory = algorithmDirectory + "/" + problem.getTag();

						bestFunFileName = outputDirectory + "/BEST_" + indicator.getName() + "_FUN" + suffix;
						bestVarFileName = outputDirectory + "/BEST_" + indicator.getName() + "_VAR" + suffix;
						medianFunFileName = outputDirectory + "/MEDIAN_" + indicator.getName() + "_FUN" + suffix;
						medianVarFileName = outputDirectory + "/MEDIAN_" + indicator.getName() + "_VAR" + suffix;

						String bestFunFile = outputDirectory + "/" + experiment.getOutputParetoFrontFileName()
								+ list.get(list.size() - 1).getRight();// + ".tsv";
						String bestVarFile = outputDirectory + "/" + experiment.getOutputParetoSetFileName()
								+ list.get(list.size() - 1).getRight();// + ".csv";

						if (indicator.isTheLowerTheIndicatorValueTheBetter()) {
							bestFunFile = outputDirectory + "/" + experiment.getOutputParetoFrontFileName()
									+ list.get(0).getRight();// + ".tsv";
							bestVarFile = outputDirectory + "/" + experiment.getOutputParetoSetFileName()
									+ list.get(0).getRight();// + ".tsv";

//							Files.copy(Paths.get(bestFunFile), Paths.get(bestFunFileName), REPLACE_EXISTING);
//							Files.copy(Paths.get(bestVarFile), Paths.get(bestVarFileName), REPLACE_EXISTING);
						}
						
//						else {
//							String bestFunFile = outputDirectory + "/" + experiment.getOutputParetoFrontFileName()
//									+ list.get(list.size() - 1).getRight() + ".tsv";
//							String bestVarFile = outputDirectory + "/" + experiment.getOutputParetoSetFileName()
//									+ list.get(list.size() - 1).getRight() + ".tsv";
//
//							Files.copy(Paths.get(bestFunFile), Paths.get(bestFunFileName), REPLACE_EXISTING);
//							Files.copy(Paths.get(bestVarFile), Paths.get(bestVarFileName), REPLACE_EXISTING);
//						}
						
						
						bestFunFile += suffix + ".csv";
						bestVarFile += suffix + ".csv";

						Files.copy(Paths.get(bestFunFile), Paths.get(bestFunFileName), REPLACE_EXISTING);
						Files.copy(Paths.get(bestVarFile), Paths.get(bestVarFileName), REPLACE_EXISTING);

						int medianIndex = list.size() / 2;
						String medianFunFile = outputDirectory + "/" + experiment.getOutputParetoFrontFileName()
								+ list.get(medianIndex).getRight() + suffix + ".csv";
						String medianVarFile = outputDirectory + "/" + experiment.getOutputParetoSetFileName()
								+ list.get(medianIndex).getRight() + suffix + ".csv";

						Files.copy(Paths.get(medianFunFile), Paths.get(medianFunFileName), REPLACE_EXISTING);
						Files.copy(Paths.get(medianVarFile), Paths.get(medianVarFileName), REPLACE_EXISTING);
					}
				}
			}
		}
	}

	private void writeSummaryFile(Experiment<S, Result> experiment) {
		JMetalLogger.logger.info("Writing org.uma.jmetal.experiment summary file");
		String headerOfCSVFile = "Algorithm,Problem,IndicatorName,ExecutionId,IndicatorValue";
		String csvFileName = this.experiment.getExperimentBaseDirectory() + "/QualityIndicatorSummary.csv";
		resetFile(csvFileName);

		try (FileWriter os = new FileWriter(csvFileName, true)) {
			os.write("" + headerOfCSVFile + "\n");

			for (GenericIndicator<?> indicator : experiment.getIndicatorList()) {
				for (ExperimentAlgorithm<?, Result> algorithm : experiment.getAlgorithmList()) {
					String algorithmDirectory;
					algorithmDirectory = experiment.getExperimentBaseDirectory() + "/data/"
							+ algorithm.getAlgorithmTag();

					for (ExperimentProblem<?> problem : experiment.getProblemList()) {
						String suffix = "__" + problem.getTag();
						String indicatorFileName = algorithmDirectory + "/" + problem.getTag() + "/"
								+ indicator.getName() + suffix;
						Path indicatorFile = Paths.get(indicatorFileName);
						if (indicatorFile == null) {
							throw new JMetalException("Indicator file " + indicator.getName() + " doesn't exist");
						}
						System.out.println("-----");
						System.out.println(indicatorFileName);

						List<String> fileArray;
						fileArray = Files.readAllLines(indicatorFile, StandardCharsets.UTF_8);
						System.out.println(fileArray);
						System.out.println("++++++");

						for (int i = 0; i < fileArray.size(); i++) {
							String row = algorithm.getAlgorithmTag() + "," + problem.getTag() + ","
									+ indicator.getName() + "," + i + "," + fileArray.get(i);
							os.write("" + row + "\n");
						}
					}
				}
			}
		} catch (IOException ex) {
			throw new JMetalException("Error writing indicator file" + ex);
		}
	}
}
