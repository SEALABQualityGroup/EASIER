package it.disim.univaq.sealab.metaheuristic.evolutionary;

import java.util.List;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

@SuppressWarnings("serial")
public class RSolutionListEvaluator implements SolutionListEvaluator<RSolution> {

	public RSolutionListEvaluator() {
	}

	@Override
	public List<RSolution> evaluate(List<RSolution> solutionList, Problem<RSolution> problem) {

		for (RSolution refactoringSolution : solutionList) {
			problem.evaluate(refactoringSolution);
		}

		return solutionList;
	}

	@Override
	public void shutdown() {
	}

}
