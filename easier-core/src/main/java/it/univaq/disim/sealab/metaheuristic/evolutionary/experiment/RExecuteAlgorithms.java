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
import java.util.stream.Stream;

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

		// TODO if parallelStream is set, it throws NPE after a while
		computingTimes.addAll(experiment.getAlgorithmList().stream()
				.map(algorithm -> getComputingTime((RExperimentAlgorithm<S, Result>) algorithm))
				.collect(Collectors.toList()));

		FileUtils.moveTmpFile(Configurator.eINSTANCE.getTmpFolder(),
				Paths.get(Configurator.eINSTANCE.getOutputFolder().toString(), "tmp"));
		return this;
	}

	/**
	 * It returns an Algorithm and its computational time along with total and free
	 * memory stats It flushes experiment data to "algo-perf-stats.csv" file within
	 * the output folder
	 * 
	 * @param algorithm
	 * @return
	 */
	protected Map.Entry<Algorithm<Result>, long[]> getComputingTime(RExperimentAlgorithm<S, Result> algorithm) {
		long total = Runtime.getRuntime().totalMemory();
		long initTime = System.currentTimeMillis();
		algorithm.runAlgorithm(this.experiment);
		long computingTime = System.currentTimeMillis() - initTime;
		long free = Runtime.getRuntime().freeMemory();
		return new AbstractMap.SimpleEntry<Algorithm<Result>, long[]>(algorithm.getAlgorithm(),
				new long[] { computingTime, total, free });// new
	}

	protected void prepareOutputDirectory() {
		Path expBaseDir = Paths.get(experiment.getExperimentBaseDirectory());
		if (!Files.exists(expBaseDir)) {
			createExperimentDirectory();
		}
	}

	/**
	 * First empty tmp and experiment base DIRs,
	 * then remove directories
	 * finally create both directories
	 */
	private void createExperimentDirectory() {

		try(Stream<Path> walker = Files.walk(Paths.get(experiment.getExperimentBaseDirectory()))) {
			walker.sorted(Comparator.reverseOrder())
					.map(Path::toFile).forEach(File::delete);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try(Stream<Path> walker = Files.walk(Configurator.eINSTANCE.getTmpFolder())){
			walker.sorted(Comparator.reverseOrder()).map(Path::toFile)
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

	public List<Map.Entry<Algorithm<Result>, long[]>> getComputingTimes() {
		return computingTimes;
	}
}
