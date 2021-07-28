package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.uml2.common.util.CacheAdapter;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;

import it.univaq.disim.sealab.easier.uml.utils.UMLMemoryOptimizer;
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
	protected Map.Entry<Algorithm<Result>, long[]> getComputingTime(ExperimentAlgorithm<S, Result> algorithm, int id) {
		Entry<Algorithm<Result>, long[]> computingTime = super.getComputingTime(algorithm, id);
		//UMLMemoryOptimizer.cleanup();
		System.gc();
		return computingTime;
	}

}
