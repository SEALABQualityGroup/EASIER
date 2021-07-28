package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.util.List;

import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public class RExperimentBuilder<S extends RSolution, Result> extends ExperimentBuilder<S, Result> {

	public RExperimentBuilder(String experimentName) {
		super(experimentName);
	}
	
	public RExperiment<S, Result> build() {
		return new RExperiment<S, Result>(this);
	}

}
