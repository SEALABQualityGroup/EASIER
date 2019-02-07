package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class RExecuteAlgorithms<S extends Solution<?>, Result> {

	private Experiment<S, Result> experiment;
	private final Controller controller;
	private List<Map.Entry<Algorithm<Result>, Long>> computingTimes;

	/** Constructor */
	public RExecuteAlgorithms(Experiment<S, Result> configuration, final Controller ctr) {
		// super(configuration);
		this.experiment = configuration;
		this.controller = ctr;
	}

	public RExecuteAlgorithms<S, Result> run() {
		JMetalLogger.logger.info("ExecuteAlgorithms: Preparing output directory");
		prepareOutputDirectory();

		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
				"" + this.experiment.getNumberOfCores());

		computingTimes = new ArrayList<>();

		for (int i = 0; i < experiment.getIndependentRuns(); i++) {
			final int id = i;

			// experiment.getAlgorithmList().parallelStream().forEach(algorithm ->
			// algorithm.runAlgorithm(id, experiment));
			computingTimes.addAll(experiment.getAlgorithmList().parallelStream()
					.map(algorithm -> getComputingTime(algorithm, id)).collect(Collectors.toList()));

			FileUtils.moveTmpFile(controller.getConfigurator().getTmpFolder(), controller.getPermanentTmpFolder());

		}
		return this;
	}
	

	private Map.Entry<Algorithm<Result>, Long> getComputingTime(ExperimentAlgorithm<S, Result> algorithm, int id) {
		long initTime = System.currentTimeMillis();
		algorithm.runAlgorithm(id, this.experiment);
		System.out.println(this.experiment.getProblemList().get(id).toString());
		long computingTime = System.currentTimeMillis() - initTime;
		return new AbstractMap.SimpleEntry<Algorithm<Result>, Long>(algorithm.getAlgorithm(), computingTime);// new
	}

	private void prepareOutputDirectory() {
		if (experimentDirectoryDoesNotExist()) {
			createExperimentDirectory();
		}
	}

	private boolean experimentDirectoryDoesNotExist() {
		boolean result;
		File experimentDirectory;

		experimentDirectory = new File(experiment.getExperimentBaseDirectory());
		if (experimentDirectory.exists() && experimentDirectory.isDirectory()) {
			result = false;
		} else {
			result = true;
		}

		return result;
	}

	private void createExperimentDirectory() {
		File experimentDirectory;
		experimentDirectory = new File(experiment.getExperimentBaseDirectory());

		if (experimentDirectory.exists()) {
			experimentDirectory.delete();
		}

		boolean result;
		result = new File(experiment.getExperimentBaseDirectory()).mkdirs();
		if (!result) {
			throw new JMetalException(
					"Error creating experiment directory: " + experiment.getExperimentBaseDirectory());
		}
	}

	public List<Map.Entry<Algorithm<Result>, Long>> getComputingTimes() {
		return computingTimes;
	}
}