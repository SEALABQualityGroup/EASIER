package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public class UMLRExecuteAlgorithms<S extends RSolution<?>, Result extends List<S>> extends RExecuteAlgorithms<S, Result> {

	public UMLRExecuteAlgorithms(RExperiment<S, Result> exp) {
		super(exp);
	}

	@Override
	protected Map.Entry<Algorithm<Result>, long[]> getComputingTime(RExperimentAlgorithm<S, Result> algorithm) {
//		Entry<Algorithm<Result>, long[]> computingTime = super.getComputingTime(algorithm, id);
		Entry<Algorithm<Result>, long[]> computingTime = super.getComputingTime(algorithm);
		//UMLMemoryOptimizer.cleanup();
		System.gc();
		return computingTime;
	}

}
