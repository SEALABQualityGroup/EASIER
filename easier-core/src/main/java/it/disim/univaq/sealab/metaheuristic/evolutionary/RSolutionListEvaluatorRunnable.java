package it.disim.univaq.sealab.metaheuristic.evolutionary;

import org.uma.jmetal.problem.Problem;

public class RSolutionListEvaluatorRunnable implements Runnable {

	private RSolution solution;
	private Problem<RSolution> problem;

	public RSolutionListEvaluatorRunnable(Problem<RSolution> problem, RSolution solution) {
		this.problem = problem;
		this.solution = solution;
	}

	@Override
	public void run() {
		problem.evaluate(solution);
	}

}
