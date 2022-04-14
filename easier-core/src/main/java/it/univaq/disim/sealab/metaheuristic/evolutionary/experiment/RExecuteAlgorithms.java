package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.lab.experiment.Experiment;
import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.JMetalLogger;

import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class RExecuteAlgorithms<S extends RSolution<?>, Result extends List<S>> {

	protected RExperiment<S, Result> experiment;
	protected List<Map.Entry<Algorithm<Result>, long[]>> computingTimes;

	/** Constructor */
	public RExecuteAlgorithms(RExperiment<S, Result> exp) {
		this.experiment = exp;
	}

	public RExecuteAlgorithms<S, Result> run() {
		JMetalLogger.logger.info("ExecuteAlgorithms: Preparing output directory");
		prepareOutputDirectory();

		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
				"" + this.experiment.getNumberOfCores());

		computingTimes = new ArrayList<>();

//		for (int i = 0; i < experiment.getIndependentRuns(); i++) {
//			final int id = i;

//			System.out.println("Indepentent Runs");
//			ProgressBar.showBar(i + 1, experiment.getIndependentRuns());

		// experiment.getAlgorithmList().parallelStream().forEach(algorithm ->
		// algorithm.runAlgorithm(id, experiment));
		// TODO if parallelStream is set, it throws NPE after a while
		computingTimes.addAll(experiment.getAlgorithmList().stream()
//					.map(algorithm -> getComputingTime((RExperimentAlgorithm<S, Result> )algorithm, id)).collect(Collectors.toList()));
				.map(algorithm -> getComputingTime((RExperimentAlgorithm<S, Result>) algorithm))
				.collect(Collectors.toList()));

		FileUtils.moveTmpFile(Configurator.eINSTANCE.getTmpFolder(),
				Paths.get(Configurator.eINSTANCE.getOutputFolder().toString(), "tmp"));

		// removes old solutions
//			for(ExperimentAlgorithm<S, Result> expAlg : experiment.getAlgorithmList()) {
//				((EasierAlgorithm)expAlg.getAlgorithm()).clear();
//			}

//		}

		return this;
	}

	/**
	 * It returns an Algorithm and its computational time along with total and free
	 * memory stats It flushes experiment data to "algo-perf-stats.csv" file within
	 * the output folder
	 * 
	 * @param algorithm
	 * @param id
	 * @return
	 */
	protected Map.Entry<Algorithm<Result>, long[]> getComputingTime(RExperimentAlgorithm<S, Result> algorithm) {
		long total = Runtime.getRuntime().totalMemory();
		long initTime = System.currentTimeMillis();

//		algorithm.runAlgorithm(id, this.experiment);
//		algorithm.runAlgorithm(this.experiment, id);
		algorithm.runAlgorithm(this.experiment);
		long computingTime = System.currentTimeMillis() - initTime;
		long free = Runtime.getRuntime().freeMemory();

//		if(!Files.exists(Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv"))) {
//			
//			try(BufferedWriter writer = new BufferedWriter(new FileWriter(Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv").toString()))){
//		    	writer.write("algorithm,problem_tag,execution_time(ms),total_memory(B),free_memory(B)");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		String line = String.format("%s,%s,%s,%s,%s\n", algorithm.getAlgorithmTag(), algorithm.getProblemTag(), computingTime, total, free);
//		
//	    try(BufferedWriter writer = new BufferedWriter(new FileWriter(Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv").toString()))){
//	    	writer.append(line);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

//		((EasierAlgorithm)algorithm.getAlgorithm()).clear();
		return new AbstractMap.SimpleEntry<Algorithm<Result>, long[]>(algorithm.getAlgorithm(),
				new long[] { computingTime, total, free });// new
	}

	protected void prepareOutputDirectory() {
		Path expBaseDir = Paths.get(experiment.getExperimentBaseDirectory());
		if (!Files.exists(expBaseDir)) {
			createExperimentDirectory();
		}
	}

	/*
	 * private boolean experimentDirectoryDoesNotExist() { boolean result; File
	 * experimentDirectory;
	 * 
	 * experimentDirectory = new File(experiment.getExperimentBaseDirectory()); if
	 * (experimentDirectory.exists() && experimentDirectory.isDirectory()) { result
	 * = false; } else { result = true; }
	 * 
	 * return result; }
	 */

	/**
	 * First empty tmp and experiment base DIRs,
	 * then remove directories
	 * finally create both directories
	 */
	private void createExperimentDirectory() {
		try {
			Files.walk(Paths.get(experiment.getExperimentBaseDirectory())).sorted(Comparator.reverseOrder())
					.map(Path::toFile).forEach(File::delete);
			Files.walk(Configurator.eINSTANCE.getTmpFolder()).sorted(Comparator.reverseOrder()).map(Path::toFile)
					.forEach(File::delete);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			Files.createDirectories(Paths.get(experiment.getExperimentBaseDirectory()));
			Files.createDirectories(Configurator.eINSTANCE.getTmpFolder());
		} catch (IOException e) {
			throw new JMetalException(String.format("Error creating experiment and temp directories: %s \t %s",
					experiment.getExperimentBaseDirectory(), Configurator.eINSTANCE.getTmpFolder()));
		}
	}

	private Path setReportFilePath() {

		Path tmp = Configurator.eINSTANCE.getOutputFolder()
				.resolve(Configurator.eINSTANCE.getOutputFolder().resolve("reportFailedSolution.csv"));
		Path etlErrorLog = tmp.getParent().resolve("etlErrorLog.csv");
		Path relErrorLog = tmp.getParent().resolve("relErrorLog.csv");
		Path backErrorLog = tmp.getParent().resolve("backAnnErrorLog.csv");

		String header = "solID;lqn_solver_message;actions\n";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp.toFile(), true))) {
			bw.append(header);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		header = "solID;message;actions\n";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(etlErrorLog.toFile(), true))) {
			bw.append(header);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		header = "solID;message;actions\n";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(relErrorLog.toFile(), true))) {
			bw.append(header);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(backErrorLog.toFile(), true))) {
			bw.append(header);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return tmp;
	}

	public List<Map.Entry<Algorithm<Result>, long[]>> getComputingTimes() {
		return computingTimes;
	}
}
