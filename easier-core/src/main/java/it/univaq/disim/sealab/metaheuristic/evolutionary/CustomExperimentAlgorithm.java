package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class CustomExperimentAlgorithm<S extends Solution<?>, Result> extends ExperimentAlgorithm<S, Result> {

	public CustomExperimentAlgorithm(Algorithm<Result> algorithm, String problemTag, int run) {
		super(algorithm, problemTag, "prova");
		// TODO Auto-generated constructor stub
	}

	public CustomExperimentAlgorithm(Algorithm<List<RSolution>> algorithm,
			ExperimentProblem<RSolution> experimentProblem, int run) {
		this((Algorithm<Result>) algorithm, "test", run);
	}
}
