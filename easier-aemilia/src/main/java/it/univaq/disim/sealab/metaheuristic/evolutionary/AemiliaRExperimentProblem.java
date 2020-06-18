package it.univaq.disim.sealab.metaheuristic.evolutionary;

import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class AemiliaRExperimentProblem<S extends AemiliaRSolution> extends ExperimentProblem<S> {

	private AemiliaRProblem<S> problem;
	private String tag;

	public AemiliaRExperimentProblem(AemiliaRProblem<S> problem, String tag) {
		super(problem, tag);
	    this.problem = problem;
	    this.tag = tag;
	  }

	public AemiliaRExperimentProblem(AemiliaRProblem<S> problem) {
		super(problem, problem.getName());
	    this.problem = problem;
	    this.tag = problem.getName() ;
	  }

	public AemiliaRProblem<S> getProblem() {
		return problem;
	}

	public String getTag() {
		return tag;
	}

}
