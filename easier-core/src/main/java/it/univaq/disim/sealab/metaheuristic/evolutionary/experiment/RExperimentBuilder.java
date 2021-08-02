package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.lab.experiment.ExperimentBuilder;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public class RExperimentBuilder<S extends RSolution<?>, Result extends List<S>> extends ExperimentBuilder<S, Result> {

	private List<String> referenceFrontFileNames = new ArrayList<>();
	
	public RExperimentBuilder(String experimentName) {
		super(experimentName);
	}
	
	public RExperiment<S, Result> build() {
		return new RExperiment<S, Result>(this);
	}
	
	public RExperimentBuilder<S, Result> setReferenceFrontFileNames(List<String> tags){
		this.referenceFrontFileNames = tags; 
		return this;
	}

}
