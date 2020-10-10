package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.impl.AbstractEvolutionaryAlgorithm;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class RExecuteAlgorithms<S extends RSolution, Result> {

	private Experiment<S, Result> experiment;
	private final Controller controller;
	private List<Map.Entry<Algorithm<Result>, Long>> computingTimes;

	/** Constructor */
	public RExecuteAlgorithms(RExperiment<S, Result> exp, final Controller ctr) {
		// super(configuration);
		this.experiment = exp;
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

			System.out.println("Indepentent Runs");
			ProgressBar.showBar(i + 1, experiment.getIndependentRuns());

			// experiment.getAlgorithmList().parallelStream().forEach(algorithm ->
			// algorithm.runAlgorithm(id, experiment));
			// TODO if parallelStream is set, it throws NPE after a while
			computingTimes.addAll(experiment.getAlgorithmList().stream()
					.map(algorithm -> getComputingTime(algorithm, id)).collect(Collectors.toList()));

			FileUtils.moveTmpFile(controller.getConfigurator().getTmpFolder(), controller.getPermanentTmpFolder());
			
			for(ExperimentAlgorithm<S, Result> alg: experiment.getAlgorithmList()) {
				RProblem<?> p =(RProblem<?>) ((AbstractEvolutionaryAlgorithm<Solution<S>, Result>)alg.getAlgorithm()).getProblem();
				p.getSolutions().forEach(sol -> sol.freeMemory());
				//Shall remove old solutions
				((List<S>)alg.getAlgorithm().getResult()).clear();
				p.getSolutions().clear();
				System.gc();
				EasierLogger.logger_.info(String.format("GarbageCollector has been invoked for '%s'!", alg.getAlgorithmTag()));
			}
			EasierLogger.logger_.info("No longer used Solutions have been removed!");
		}
		return this;
	}

	private Map.Entry<Algorithm<Result>, Long> getComputingTime(ExperimentAlgorithm<S, Result> algorithm, int id) {
		long initTime = System.currentTimeMillis();
		algorithm.runAlgorithm(id, this.experiment);
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
		setReportFilePath();
		if (!result) {
			throw new JMetalException(
					"Error creating experiment directory: " + experiment.getExperimentBaseDirectory());
		}
	}

	private Path setReportFilePath() {

		Path tmp = controller.getConfigurator().getOutputFolder().resolve(controller.getReportFileName());
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

	public List<Map.Entry<Algorithm<Result>, Long>> getComputingTimes() {
		return computingTimes;
	}
}
