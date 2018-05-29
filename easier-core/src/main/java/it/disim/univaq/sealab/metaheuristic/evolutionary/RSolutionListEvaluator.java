package it.disim.univaq.sealab.metaheuristic.evolutionary;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

@SuppressWarnings("serial")
public class RSolutionListEvaluator implements SolutionListEvaluator<RSolution> {

	private ExecutorService executor;

	public RSolutionListEvaluator() {
	}

	@Override
	public List<RSolution> evaluate(List<RSolution> solutionList, Problem<RSolution> problem) {
		ExecutorService executor = Executors.newFixedThreadPool(solutionList.size());
		((RProblem) problem).getController().setExecutor(executor);

		for (RSolution sol : solutionList) {
			sol.executeRefactoring();
			sol.applyTransformation();
		}

		for (RSolution refactoringSolution : solutionList) {
			if (executor != null) {
				Runnable worker = new InvokeSolverRunnable(refactoringSolution);
				executor.execute(worker);
			} else {
				problem.evaluate(refactoringSolution);
			}
		}

		executor.shutdown();
		try {
			executor.awaitTermination(10, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (RSolution sol : solutionList) {
			sol.updateModel();
			sol.updateThresholds();
			sol.countingPAsOnAemiliaModel();
			sol.evaluatePerformance();
			sol.getController().simpleSolutionWriterToCSV(sol);
			problem.evaluate(sol);
		}

		return solutionList;
	}

	@Override
	public void shutdown() {
	}

	public void setExecutor(ExecutorService exc) {
		executor = exc;
	}

	// for (RSolution refactoringSolution : solutionList) {
	// if (executor != null) {
	// Runnable worker = new RSolutionListEvaluatorRunnable(problem,
	// refactoringSolution);
	// executor.execute(worker);
	// } else {
	// problem.evaluate(refactoringSolution);
	// }
	// }

}
