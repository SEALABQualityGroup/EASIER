package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.util.List;
import java.util.Map;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.lab.experiment.Experiment;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public class RExperiment<S extends RSolution<?>, Result extends List<S>> extends Experiment<S, Result> {

	private List<Map.Entry<Algorithm<Result>, long[]>> computingTimes;
	
	public RExperiment(RExperimentBuilder<S, Result> builder) {
		super(builder);
		computingTimes = null;
	}
	
	public void setComputingTime(final List<Map.Entry<Algorithm<Result>, long[]>> ct) {
		computingTimes = ct;
	}
	
	public List<Map.Entry<Algorithm<Result>, long[]>> getComputingTimes() { return computingTimes; }
}
