package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.assertNotNull;

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
import org.checkerframework.checker.fenum.qual.FenumBottom;
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

public class RComputeQualityIndicatorsTest<S extends Solution> {

	List<GenericIndicator<S>> qIndicators;

	private String baseDir, objectivesDir, qiDir, referenceFrontDir;
	private String[] problemNames, evals, brfs, probPas, algoSuffixs, search_budgets;

	@Before
	public void setup() {

		baseDir = "/home/peo/git/sealab/2022-seaa-data/data/by_time";
		objectivesDir = baseDir + "/objectives";
		qiDir = baseDir + "/quality_indicators";
		referenceFrontDir = baseDir + "/reference_paretos";

		problemNames = new String[] { "train-ticket", "simplified-cocome" };// , "simplified-cocome-newrel" };
		probPas = new String[] { "95" };// , "80", "55" };
		evals = new String[] { "72", "82", "102" };
		brfs = new String[] { "moc_1.64" };// , "moc_1.23" };

		algoSuffixs = new String[] { "nsgaii", "spea2", "pesa2" };
		search_budgets = new String[] { "sb_900000", "sb_3600000", "sb_1800000" };

		List<String> qualityIndicators = Arrays.asList("IGD+", "EPSILON", "HYPER_VOLUME", "GENERALIZED_SPREAD");

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
				.filter(path -> path.toString().endsWith(".rf") && path.toString().contains(pName + "_")
						&& path.toString().contains("super_pareto"))// && !path.toString().contains("No_PAs"))
				.collect(Collectors.toList());
		walk.close();
		return referenceFronts;
	}

	private List<Path> extractReferenceFronts(String pName) throws IOException {
		List<Path> referenceFronts;

		Stream<Path> walk = Files.walk(Paths.get(objectivesDir));
		referenceFronts = walk
				.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".csv")
						&& path.toString().contains("FUN") && path.toString().contains(pName + "_"))
				.collect(Collectors.toList());
		walk.close();
		return referenceFronts;
	}

	@Ignore
	@Test
	public void computeQIperProbPasPasTest() throws IOException {
		for (GenericIndicator<S> indicator : qIndicators) {
			for (final String pName : problemNames) {
				for (String probPa : probPas) {
					Path referenceFrontName = null;

					referenceFrontName = extractReferenceParetos(pName).stream()
							.filter(path -> path.toString().contains("ProbPAs_0." + probPa)).findFirst().orElse(null);

					Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString(), false), ",");
					FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
					Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

					indicator.setReferenceParetoFront(normalizedReferenceFront);

					for (String brf : brfs) {
						for (String e : evals) {
							for (String pa : probPas) {
								List<Path> frontFileNames = null;

								frontFileNames = extractReferenceFronts(pName).stream()
										.filter(path -> path.toString().contains("ProbPAs_0." + pa)
												&& path.toString().contains(brf)
												&& path.toString().contains("MaxEval_" + e))
										.collect(Collectors.toList());

								String qualityIndicatorFile = String.format(
										"%s/%s__%s__BRF_%s__MaxEval_%s__ProbPAs_0.%s__super_pareto__ProbPAs_0.%s.csv",
										qiDir, indicator.getName(), pName, brf, e, pa, probPa);

								resetFile(qualityIndicatorFile);

								writeQualityIndicator(frontFileNames, frontNormalizer, indicator, qualityIndicatorFile);

							}
						}
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
				front = new ArrayFront(removeSolID(frontFileName.toString(), false), ",");
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

	@Ignore
	@Test
	public void computeQIperProblemPasTest() throws IOException {
		for (GenericIndicator<S> indicator : qIndicators) {
			for (final String pName : problemNames) {
				Path referenceFrontName = null;

				referenceFrontName = extractReferenceParetos(pName).stream()
						.filter(path -> path.toString().contains("super_pareto__PAs")).findFirst().orElse(null);

				Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString(), false), ",");
				FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
				Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

				indicator.setReferenceParetoFront(normalizedReferenceFront);

				for (String brf : brfs) {
					for (String e : evals) {
						for (String pa : probPas) {
							List<Path> frontFileNames = null;

							frontFileNames = extractReferenceFronts(pName).stream()
									.filter(path -> path.toString().contains("ProbPAs_0." + pa)
											&& path.toString().contains(brf)
											&& path.toString().contains("MaxEval_" + e))
									.collect(Collectors.toList());

							String qualityIndicatorFile = String.format(
									"%s/%s__%s__BRF_%s__MaxEval_%s__ProbPAs_0.%s__super_pareto__PAs.csv", qiDir,
									indicator.getName(), pName, brf, e, pa);

							resetFile(qualityIndicatorFile);
							writeQualityIndicator(frontFileNames, frontNormalizer, indicator, qualityIndicatorFile);

						}
					}
				}
			}
		}
	}

	@Ignore
	@Test
	public void computeQIperBRFPasTest() throws IOException {
		for (GenericIndicator<S> indicator : qIndicators) {
			for (final String pName : problemNames) {
				for (final String BRF : brfs) {
					Path referenceFrontName = null;

					referenceFrontName = extractReferenceParetos(pName).stream()
							.filter(path -> path.toString().contains(BRF)).findFirst().orElse(null);

					Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString(), false), ",");
					FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
					Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

					indicator.setReferenceParetoFront(normalizedReferenceFront);

					for (String brf : brfs) {
						for (String e : evals) {
							for (String pa : probPas) {
								List<Path> frontFileNames = null;

								frontFileNames = extractReferenceFronts(pName).stream()
										.filter(path -> path.toString().contains("ProbPAs_0." + pa)
												&& path.toString().contains(brf)
												&& path.toString().contains("MaxEval_" + e))
										.collect(Collectors.toList());

								String qualityIndicatorFile = String.format(
										"%s/%s__%s__BRF_%s__MaxEval_%s__ProbPAs_0.%s__super_pareto__BRF_%s__PAs.csv",
										qiDir, indicator.getName(), pName, brf, e, pa, BRF);

								resetFile(qualityIndicatorFile);
								writeQualityIndicator(frontFileNames, frontNormalizer, indicator, qualityIndicatorFile);

							}
						}
					}
				}
			}
		}

	}

	@Test
	@Ignore
	public void computeQIperEvalPasTest() throws IOException {
		for (GenericIndicator<S> indicator : qIndicators) {
			for (final String pName : problemNames) {
				for (final String eval : evals) {

					Path referenceFrontName = null;

					referenceFrontName = extractReferenceParetos(pName).stream()
							.filter(path -> path.toString().contains("MaxEval_" + eval)).findFirst().orElse(null);

					Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString(), false), ",");
					FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
					Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

					indicator.setReferenceParetoFront(normalizedReferenceFront);

					for (String brf : brfs) {
						for (String e : evals) {
							for (String pa : probPas) {
								List<Path> frontFileNames = null;

								frontFileNames = extractReferenceFronts(pName).stream()
										.filter(path -> path.toString().contains("ProbPAs_0." + pa)
												&& path.toString().contains(brf)
												&& path.toString().contains("MaxEval_" + e))
										.collect(Collectors.toList());

								String qualityIndicatorFile = String.format(
										"%s/%s__%s__BRF_%s__MaxEval_%s__ProbPAs_0.%s__super_pareto__MaxEval_%s__PAs.csv",
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

	private double[] extractIndicatorValues(FrontNormalizer frontNormalizer, List<Path> frontFileNames,
			GenericIndicator<S> indicator, boolean filtering) {
		double[] indicatorValues = new double[frontFileNames.size()];
		int run = 0;
		for (Path frontFileName : frontFileNames) {
			Front front = null;
			int solID;
			try {
				front = new ArrayFront(removeSolID(frontFileName.toString(), filtering), ",");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			Front normalizedFront = frontNormalizer.normalize(front);
			List<PointSolution> normalizedPopulation = FrontUtils.convertFrontToSolutionList(normalizedFront);
			indicatorValues[run] = indicator.evaluate((List<S>) normalizedPopulation);
			run++;
		}

		return indicatorValues;
	}

	private void writeQualityIndicatorValues(FrontNormalizer frontNormalizer, Front normalizedReferenceFront,
			List<Path> frontFileNames, String line, String qualityIndicatorFile, boolean filtering) throws IOException {
		FileWriter os;
		os = new FileWriter(qualityIndicatorFile, true);
//		os.write(header + "\n");

		for (GenericIndicator<S> indicator : qIndicators) {
			indicator.setReferenceParetoFront(normalizedReferenceFront);

			double[] indicatorValues = extractIndicatorValues(frontNormalizer, frontFileNames, indicator, filtering);

			String row = line + indicator.getName() + ",";

			for (int i = 0; i < indicatorValues.length; i++) {
				try {
					os.write(row + indicatorValues[i] + "\n");
				} catch (IOException ex) {
					throw new JMetalException("Error writing indicator file" + ex);
				}
			}
		}
		os.close();
	}

	@Test
	public void generateQiOptimalParetoCSV() throws IOException {
		String qualityIndicatorFile = String.format("%s/qi__optimal.csv", qiDir); // qi__train-ticket.csv
		String header = "case_study,brf,max_eval,prob_pas,search_budget,algorithm,q_indicator,value";

		FileWriter os;
		os = new FileWriter(qualityIndicatorFile, false);
		os.write(header + "\n");
		os.close();

		Path optimalFrontName;

		for (final String pName : problemNames) {
			Stream<Path> walk = Files.walk(Paths.get(baseDir + "/optimal_paretos/"));
			optimalFrontName = walk
					.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".rf")
							&& path.toString().contains("optimal_pareto") && path.toString().contains(pName + "_"))
					.findFirst().orElse(null);
			walk.close();

			Front referenceFront = new ArrayFront(removeSolID(optimalFrontName.toString(), false), ",");
			FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
			Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

//			List<String> auxList = new ArrayList<String>(Arrays.asList(probPas));
//			auxList.add("00");
//			probPas = null;
//			probPas = auxList.toArray(String[]::new);

			for (String suffix : algoSuffixs) {
				for (String sBudget : search_budgets) {
					for (String e : evals) {
//						for (String pa : probPas) {
						List<Path> frontFileNames = null;

						frontFileNames = extractReferenceFronts(pName).stream()
								.filter(path -> path.toString().contains("ProbPAs_0." + probPas[0])
										&& path.toString().contains(brfs[0]) && path.toString().contains("MaxEval_" + e)
										&& path.toString().contains("Algo_"+suffix)
										&& path.toString().contains(sBudget))
								.collect(Collectors.toList());

						String line = String.format("%s,%s,%s,%s,%s,%s,", pName, brfs[0], e, probPas[0], sBudget, suffix);
						writeQualityIndicatorValues(frontNormalizer, normalizedReferenceFront, frontFileNames, line,
								qualityIndicatorFile, false);

					}
//					}
				}
			}

		}

	}

	@Test
	public void generateQiAcrossMaxEvalParetoCSV() throws IOException {
		String header = "case_study,brf,max_eval,prob_pas,q_indicator,value";

		Path referenceFrontFile;

		List<String> auxList = new ArrayList<String>(Arrays.asList(probPas));
		auxList.add("00");
		probPas = null;
		probPas = auxList.toArray(String[]::new);

		for (String BRF : brfs) {
			for (String pas : probPas) {
				String qualityIndicatorFile = String.format("%s/qi__BRF_%s_ProbPAs_0.%s.csv", qiDir, BRF, pas); // qi__train-ticket.csv
				FileWriter os;
				os = new FileWriter(qualityIndicatorFile, false);
				os.write(header + "\n");
				os.close();
				for (final String pName : problemNames) {
					Stream<Path> walk = Files.walk(Paths.get(referenceFrontDir));
					referenceFrontFile = walk.filter(Files::isRegularFile)
							.filter(path -> path.toString().endsWith(".rf") && path.toString().contains("super_pareto")
									&& path.toString().contains(pName + "_")
									&& path.toString().contains("ProbPAs_0." + pas) && path.toString().contains(BRF))
							.findFirst().orElse(null);
					walk.close();

					Front referenceFront = new ArrayFront(removeSolID(referenceFrontFile.toString(), true), ",");
					FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
					Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

					for (String brf : brfs) {
						for (String e : evals) {
							for (String pa : probPas) {
								List<Path> frontFileNames = null;

								frontFileNames = extractReferenceFronts(pName).stream()
										.filter(path -> path.toString().contains("ProbPAs_0." + pa)
												&& path.toString().contains(brf)
												&& path.toString().contains("MaxEval_" + e))
										.collect(Collectors.toList());

								String line = String.format("%s,%s,%s,%s,", pName, brf, e, pa);
								writeQualityIndicatorValues(frontNormalizer, normalizedReferenceFront, frontFileNames,
										line, qualityIndicatorFile, true);

							}
						}
					}
				}

			}

		}
	}

	@SuppressWarnings("resource")
	@Test
	public void generateQiPAsCSV() throws IOException {
		String qualityIndicatorFile = String.format("%s/qi.csv", qiDir); // qi__train-ticket.csv
		String header = "case_study,brf,max_eval,prob_pas,q_indicator,value";

		FileWriter os;
		os = new FileWriter(qualityIndicatorFile, false);
		os.write(header + "\n");
		os.close();

		for (final String pName : problemNames) {
			Path referenceFrontName = null;

			referenceFrontName = extractReferenceParetos(pName).stream()
					.filter(path -> path.toString().contains("super_pareto__PAs")).findFirst().orElse(null);

			Front referenceFront = new ArrayFront(removeSolID(referenceFrontName.toString(), false), ",");
			FrontNormalizer frontNormalizer = new FrontNormalizer(referenceFront);
			Front normalizedReferenceFront = frontNormalizer.normalize(referenceFront);

			for (String brf : brfs) {
				for (String e : evals) {
					for (String pa : probPas) {
						List<Path> frontFileNames = null;

						frontFileNames = extractReferenceFronts(pName).stream()
								.filter(path -> path.toString().contains("ProbPAs_0." + pa)
										&& path.toString().contains(brf) && path.toString().contains("MaxEval_" + e))
								.collect(Collectors.toList());

						String line = String.format("%s,%s,%s,%s,", pName, brf, e, pa);
						writeQualityIndicatorValues(frontNormalizer, normalizedReferenceFront, frontFileNames, line,
								qualityIndicatorFile, false);

					}
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

	public String removeSolID(String frontFileName, boolean filtering) {

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
		int pasIndex = -1;
		try (BufferedReader reader = new BufferedReader(new FileReader(frontFileName));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile))) {
			while ((readLine = reader.readLine()) != null) {
				if (!readLine.contains("perfQ")) {
					List<String> splittedLine = new ArrayList<String>(Arrays.asList(readLine.split(",")));
					splittedLine.remove(0); // remove SolID
					int numObjectives = splittedLine.size();
					if (filtering && numObjectives == 4)
						splittedLine.remove(pasIndex); // remove Pas in case of filtering
					String line = String.join(",", splittedLine);
					writer.write(line);
					writer.newLine();
				} else {
					pasIndex = Arrays.asList(readLine.split(",")).indexOf("pas") - 1;
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
