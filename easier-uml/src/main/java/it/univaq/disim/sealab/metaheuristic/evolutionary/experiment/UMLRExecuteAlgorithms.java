package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.eclipse.uml2.common.util.CacheAdapter;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;

import it.univaq.disim.sealab.metaheuristic.evolutionary.EasierAlgorithm;
import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class UMLRExecuteAlgorithms<S extends RSolution, Result> extends RExecuteAlgorithms<S, Result> {

	public UMLRExecuteAlgorithms(RExperiment<S, Result> exp) {
		super(exp);
	}

	@Override
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

			FileUtils.moveTmpFile(Configurator.eINSTANCE.getTmpFolder(),
					Paths.get(Configurator.eINSTANCE.getOutputFolder().toString(), "tmp"));

			// removes old solutions
			for (ExperimentAlgorithm<S, Result> expAlg : experiment.getAlgorithmList()) {
				((EasierAlgorithm) expAlg.getAlgorithm()).clear();
			}

			cleanup();

		}

		return this;
	}
	
	private void cleanup() {
		org.eclipse.uml2.common.util.CacheAdapter ca = org.eclipse.uml2.common.util.CacheAdapter.getInstance();
		ca.clear();
		String field = System.getProperty("org.eclipse.uml2.common.util.CacheAdapter.ThreadLocal") == null ? "INSTANCE" : "THREAD_LOCAL";
		
		try {
			java.lang.reflect.Field instance = ca.getClass().getDeclaredField(field);
			instance.setAccessible(true);
			instance.set(ca, null);
			
			System.gc();
			Method createCache = CacheAdapter.class.getDeclaredMethod("createCacheAdapter");
			createCache.setAccessible(true);
			instance.set(ca, createCache.invoke(ca));
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
