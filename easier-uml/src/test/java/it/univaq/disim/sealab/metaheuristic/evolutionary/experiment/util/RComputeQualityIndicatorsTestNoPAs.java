package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.front.Front;
import org.uma.jmetal.util.front.impl.ArrayFront;
import org.uma.jmetal.util.front.util.FrontNormalizer;
import org.uma.jmetal.util.front.util.FrontUtils;
import org.uma.jmetal.util.point.PointSolution;

import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.factory.FactoryBuilder;

public class RComputeQualityIndicatorsTestNoPAs<S extends Solution> {

	List<GenericIndicator<S>> qIndicators;

	private String baseDir, objectivesDir, qiDir, referenceFrontDir;
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

		List<String> qualityIndicators = Arrays.asList("SPREAD", "IGD+", "IGD", "EPSILON", "HYPER_VOLUME",
				"GENERALIZED_SPREAD");

		qIndicators = new ArrayList<>();
		FactoryBuilder<UMLRSolution> factory = new FactoryBuilder<>();
		for (String qI : qualityIndicators) {
			GenericIndicator<S> ind = (GenericIndicator<S>) factory.createQualityIndicators(qI);
			if (ind != null)
				qIndicators.add(ind);
		}
	}

	private List<Path> extractReferenceParetos(String pName) throws IOException {
		List<Path> referenceFronts;

		Stream<Path> walk = Files.walk(Paths.get(referenceFrontDir));
		referenceFronts = walk.filter(Files::isRegularFile)
				.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName+"_")
						&& path.toString().contains("super_pareto__No_PAs"))
				.collect(Collectors.toList());
		walk.close();
		return referenceFronts;
	}

	private List<Path> extractReferenceFronts(String pName) throws IOException {
		List<Path> referenceFronts;

		Stream<Path> walk = Files.walk(Paths.get(objectivesDir));
		referenceFronts = walk.filter(Files::isRegularFile)
				.filter(path -> path.toString().endsWith(".csv") && path.toString().contains("FUN")
						&& path.toString().contains(pName+"_")	&& path.toString().contains("ProbPAs_0.00"))
				.collect(Collectors.toList());
		walk.close();
		return referenceFronts;
	}

	@Test
	public void computeQIperNoProbPasNoPasTest() throws FileNotFoundException {
		for (GenericIndicator<S> indicator : qIndicators) {
			for (final String pName : problemNames) {
				Path referenceFrontName = null;
				try (Stream<Path> walk = Files.walk(Paths.get(referenceFrontDir))) {
					referenceFrontName = walk.filter(Files::isRegularFile)
							.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
									&& path.toString().contains("super_pareto")
									&& path.toString().contains("ProbPAs_0.00"))
							.findFirst().orElse(null);
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString()), ",");
				FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
				Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

				indicator.setReferenceParetoFront(normalizedReferenceFront);

				for (String brf : brfs) {
					for (String e : evals) {
						List<Path> frontFileNames = null;
						try (Stream<Path> walk = Files.walk(Paths.get(objectivesDir))) {
							frontFileNames = walk.filter(Files::isRegularFile)
									.filter(path -> path.toString().endsWith(".csv") && path.toString().contains("FUN")
											&& path.toString().contains(pName)
											&& path.toString().contains("ProbPAs_0.00") && path.toString().contains(brf)
											&& path.toString().contains("MaxEval_" + e))
									.collect(Collectors.toList());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						String qualityIndicatorFile = String.format(
								"%s/%s__%s__BRF_%s__MaxEval_%s__ProbPAs_0.00__super_pareto__ProbPAs_0.00.csv", qiDir,
								indicator.getName(), pName, brf, e);

						resetFile(qualityIndicatorFile);
						writeQualityIndicator(frontFileNames, frontNormalizer, indicator, qualityIndicatorFile);

					}
				}
			}
		}

	}

	private void writeQualityIndicator(List<Path> frontFileNames, FrontNormalizer frontNormalizer,
			GenericIndicator<S> indicator, String qualityIndicatorFile) {
		double[] indicatorValues = new double[frontFileNames.size()];
		int run = 0;
		for (Path frontFileName : frontFileNames) {
			Front front = null;
			try {
				front = new ArrayFront(removeSolID(frontFileName.toString()), ",");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			Front normalizedFront = frontNormalizer.normalize(front);
			List<PointSolution> normalizedPopulation = FrontUtils.convertFrontToSolutionList(normalizedFront);
			indicatorValues[run] = indicator.evaluate((List<S>) normalizedPopulation);
			run++;
		}
		for (double indicatorValue : indicatorValues) {
			writeQualityIndicatorValueToFile(indicatorValue, qualityIndicatorFile);
		}
	}

	@Test
	public void computeQIperProblemNoPasTest() throws FileNotFoundException {
		for (GenericIndicator<S> indicator : qIndicators) {
			for (final String pName : problemNames) {
				Path referenceFrontName = null;
				try (Stream<Path> walk = Files.walk(Paths.get(referenceFrontDir))) {
					referenceFrontName = walk.filter(Files::isRegularFile)
							.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
									&& path.toString().contains("super_pareto__No_PAs"))
							.findFirst().orElse(null);
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString()), ",");
				FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
				Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

				indicator.setReferenceParetoFront(normalizedReferenceFront);

				for (String brf : brfs) {
					for (String e : evals) {
						List<Path> frontFileNames = null;
						try (Stream<Path> walk = Files.walk(Paths.get(objectivesDir))) {
							frontFileNames = walk.filter(Files::isRegularFile)
									.filter(path -> path.toString().endsWith(".csv") && path.toString().contains("FUN")
											&& path.toString().contains(pName)
											&& path.toString().contains("ProbPAs_0.00") && path.toString().contains(brf)
											&& path.toString().contains("MaxEval_" + e))
									.collect(Collectors.toList());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						String qualityIndicatorFile = String.format(
								"%s/%s__%s__BRF_%s__MaxEval_%s__ProbPAs_0.00__super_pareto__No_PAs.csv", qiDir,
								indicator.getName(), pName, brf, e);

						resetFile(qualityIndicatorFile);

						writeQualityIndicator(frontFileNames, frontNormalizer, indicator, qualityIndicatorFile);

					}
				}
			}
		}

	}

	@Test
	public void computeQIperBRFNoPasTest() throws FileNotFoundException {
		for (GenericIndicator<S> indicator : qIndicators) {
			for (final String pName : problemNames) {
				for (final String BRF : brfs) {
					Path referenceFrontName = null;
					try (Stream<Path> walk = Files.walk(Paths.get(referenceFrontDir))) {
						referenceFrontName = walk.filter(Files::isRegularFile)
								.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
										&& path.toString().contains(BRF) && path.toString().contains("No_PAs")
										&& path.toString().contains("super_pareto"))
								.findFirst().orElse(null);
					} catch (IOException e2) {
						e2.printStackTrace();
					}

					Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString()), ",");
					FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
					Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

					indicator.setReferenceParetoFront(normalizedReferenceFront);

					for (String brf : brfs) {
						for (String e : evals) {
							List<Path> frontFileNames = null;
							try (Stream<Path> walk = Files.walk(Paths.get(objectivesDir))) {
								frontFileNames = walk.filter(Files::isRegularFile)
										.filter(path -> path.toString().endsWith(".csv")
												&& path.toString().contains("FUN") && path.toString().contains(pName)
												&& path.toString().contains("ProbPAs_0.00")
												&& path.toString().contains(brf)
												&& path.toString().contains("MaxEval_" + e))
										.collect(Collectors.toList());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							String qualityIndicatorFile = String.format(
									"%s/%s__%s__BRF_%s__MaxEval_%s__ProbPAs_0.00__super_pareto__BRF_%s__No_PAs.csv",
									qiDir, indicator.getName(), pName, brf, e, BRF);

							resetFile(qualityIndicatorFile);
							writeQualityIndicator(frontFileNames, frontNormalizer, indicator, qualityIndicatorFile);

						}
					}
				}
			}
		}

	}

	@Test
	public void computeQIperEvalNoPasTest() throws FileNotFoundException {

		for (GenericIndicator<S> indicator : qIndicators) {
			for (final String pName : problemNames) {
				for (final String eval : evals) {

					Path referenceFrontName = null;
					try (Stream<Path> walk = Files.walk(Paths.get(referenceFrontDir))) {
						referenceFrontName = walk.filter(Files::isRegularFile)
								.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
										&& path.toString().contains("MaxEval_" + eval)
										&& path.toString().contains("No_PAs")
										&& path.toString().contains("super_pareto"))
								.findFirst().orElse(null);
					} catch (IOException e2) {
						e2.printStackTrace();
					}

					Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString()), ",");
					FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
					Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

					indicator.setReferenceParetoFront(normalizedReferenceFront);

					for (String brf : brfs) {
						for (String e : evals) {
							List<Path> frontFileNames = null;
							try (Stream<Path> walk = Files.walk(Paths.get(objectivesDir))) {
								frontFileNames = walk.filter(Files::isRegularFile)
										.filter(path -> path.toString().endsWith(".csv")
												&& path.toString().contains("FUN") && path.toString().contains(pName)
												&& path.toString().contains("ProbPAs_0.00")
												&& path.toString().contains(brf)
												&& path.toString().contains("MaxEval_" + e))
										.collect(Collectors.toList());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							String qualityIndicatorFile = String.format(
									"%s/%s__%s__BRF_%s__MaxEval_%s__ProbPAs_0.00__super_pareto__MaxEval_%s__No_PAs.csv",
									qiDir, indicator.getName(), pName, brf, e, eval);

							resetFile(qualityIndicatorFile);
							writeQualityIndicator(frontFileNames, frontNormalizer, indicator, qualityIndicatorFile);

						}
					}
				}

			}
		}

	}

	@Test
	public void computeQIperEvalPasTest() throws FileNotFoundException {

		for (GenericIndicator<S> indicator : qIndicators) {
			for (final String pName : problemNames) {
				for (final String eval : evals) {

					Path referenceFrontName = null;
					try (Stream<Path> walk = Files.walk(Paths.get(referenceFrontDir))) {
						referenceFrontName = walk.filter(Files::isRegularFile)
								.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName)
										&& path.toString().contains("MaxEval_" + eval)
										&& !path.toString().contains("No_PAs")
										&& path.toString().contains("super_pareto"))
								.findFirst().orElse(null);
					} catch (IOException e2) {
						e2.printStackTrace();
					}

					Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString()), ",");
					FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
					Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

					indicator.setReferenceParetoFront(normalizedReferenceFront);

					for (String brf : brfs) {
						for (String e : evals) {
							for (String pa : probPas) {
								List<Path> frontFileNames = null;
								try (Stream<Path> walk = Files.walk(Paths.get(objectivesDir))) {
									frontFileNames = walk.filter(Files::isRegularFile)
											.filter(path -> path.toString().endsWith(".csv")
													&& path.toString().contains("FUN")
													&& path.toString().contains(pName)
													&& path.toString().contains("ProbPAs_0." + pa)
													&& path.toString().contains(brf)
													&& path.toString().contains("MaxEval_" + e))
											.collect(Collectors.toList());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								String qualityIndicatorFile = String.format(
										"%s/%s__%s__BRF_%s__MaxEval_%s__ProbPAs_0.%s__super_pareto__MaxEval_%s__PA.csv",
										qiDir, indicator.getName(), pName, brf, e, pa, eval);

								resetFile(qualityIndicatorFile);
								writeQualityIndicator(frontFileNames, frontNormalizer, indicator, qualityIndicatorFile);

							}
						}
					}

				}
			}

		}
	}

	@SuppressWarnings("resource")
	@Test
	public void generateQiNoPAsCSV() throws IOException {
		String qualityIndicatorFile = String.format("%s/qi__no_pas.csv", qiDir); // qi__train-ticket.csv
		String header = "case_study,brf,max_eval,q_indicator,value";

		FileWriter os;
		os = new FileWriter(qualityIndicatorFile, false);
		os.write(header + "\n");

		for (GenericIndicator<S> indicator : qIndicators) {
			for (final String pName : problemNames) {

				Path referenceFrontName = null;

				referenceFrontName = extractReferenceParetos(pName).stream()
						.filter(path -> path.toString().contains("super_pareto__No_PAs")).findFirst().orElse(null);

				Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString()), ",");
				FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
				Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

				indicator.setReferenceParetoFront(normalizedReferenceFront);

				for (String brf : brfs) {
					for (String e : evals) {
						List<Path> frontFileNames = null;

						frontFileNames = extractReferenceFronts(pName).stream()
								.filter(path -> path.toString().contains("ProbPAs_0.00")
										&& path.toString().contains(brf) && path.toString().contains("MaxEval_" + e))
								.collect(Collectors.toList());

						double[] indicatorValues = new double[frontFileNames.size()];
						int run = 0;
						for (Path frontFileName : frontFileNames) {
							Front front = null;
							try {
								front = new ArrayFront(removeSolID(frontFileName.toString()), ",");
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							Front normalizedFront = frontNormalizer.normalize(front);
							List<PointSolution> normalizedPopulation = FrontUtils
									.convertFrontToSolutionList(normalizedFront);
							indicatorValues[run] = indicator.evaluate((List<S>) normalizedPopulation);
							run++;
						}

						String line = String.format("%s,%s,%s,%s,", pName, brf, e, indicator.getName());

						for (int i = 0; i < indicatorValues.length; i++) {
							try {
								os.write(line + indicatorValues[i] + "\n");
							} catch (IOException ex) {
								throw new JMetalException("Error writing indicator file" + ex);
							}
						}
//							os.write(line + indicatorValues[indicatorValues.length-1]+"\n");
					}
				}

			}

		}
		os.close();
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

	/**
	 * DA valutare come vengono calcolati gli indicatori sopra
	 * 
	 * @throws IOException
	 */
	@Test
	@Ignore
	public void findBestIndicatorFrontsTest() throws IOException {

		for (GenericIndicator<?> indicator : qIndicators) {
			for (final String pName : problemNames) {
				for (final String eval : evals) {
					for (String brf : brfs) {
						for (String e : evals) {
							for (String pa : probPas) {

								final String suffix = String.format("__BRF_%s__MaxEval_%s__ProbPAs_0.%s", brf, e, pa);

								String qualityIndicatorFile = String.format(
										"%s/%s__%s%s__super_pareto__MaxEval_%s__PAs.csv", qiDir, indicator.getName(),
										pName, suffix, eval);

								Path indicatorFile = Paths.get(qualityIndicatorFile);

								if (indicatorFile != null && Files.exists(indicatorFile)) {

									List<String> fileArray;
									fileArray = Files.readAllLines(indicatorFile, StandardCharsets.UTF_8);

									List<Pair<Double, Integer>> list = new ArrayList<>();

									for (int i = 0; i < fileArray.size(); i++) {
										Pair<Double, Integer> pair = new ImmutablePair<>(
												Double.parseDouble(fileArray.get(i)), i);
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

									String outputDirectory = qiDir;

									bestFunFileName = outputDirectory + "/BEST_" + indicator.getName() + "_FUN"
											+ suffix;
									bestVarFileName = outputDirectory + "/BEST_" + indicator.getName() + "_VAR"
											+ suffix;
									medianFunFileName = outputDirectory + "/MEDIAN_" + indicator.getName() + "_FUN"
											+ suffix;
									medianVarFileName = outputDirectory + "/MEDIAN_" + indicator.getName() + "_VAR"
											+ suffix;

									String bestFunFile = outputDirectory + "/FUN"
//											+ experiment.getOutputParetoFrontFileName()
											+ list.get(list.size() - 1).getRight();// + ".tsv";
									String bestVarFile = outputDirectory + "/VAR" // +
																					// experiment.getOutputParetoSetFileName()
											+ list.get(list.size() - 1).getRight();// + ".csv";

									if (indicator.isTheLowerTheIndicatorValueTheBetter()) {
										bestFunFile = outputDirectory + "/FUN" // +
																				// experiment.getOutputParetoFrontFileName()
												+ list.get(0).getRight();// + ".tsv";
										bestVarFile = outputDirectory + "/VAR" // +
																				// experiment.getOutputParetoSetFileName()
												+ list.get(0).getRight();// + ".tsv";

										bestFunFile += suffix + ".csv";
										bestVarFile += suffix + ".csv";

										Files.copy(Paths.get(bestFunFile), Paths.get(bestFunFileName),
												REPLACE_EXISTING);
										Files.copy(Paths.get(bestVarFile), Paths.get(bestVarFileName),
												REPLACE_EXISTING);

										int medianIndex = list.size() / 2;
										String medianFunFile = outputDirectory + "/FUN"
//											+ experiment.getOutputParetoFrontFileName()
												+ list.get(medianIndex).getRight() + suffix + ".csv";
										String medianVarFile = outputDirectory + "/VAR"
//											+ experiment.getOutputParetoSetFileName() + list.get(medianIndex).getRight()
												+ suffix + ".csv";

										Files.copy(Paths.get(medianFunFile), Paths.get(medianFunFileName),
												REPLACE_EXISTING);
										Files.copy(Paths.get(medianVarFile), Paths.get(medianVarFileName),
												REPLACE_EXISTING);
									}
								}
							}
						}
					}
				}
			}
		}
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
				if (!readLine.contains("perfQ")) {
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
}
