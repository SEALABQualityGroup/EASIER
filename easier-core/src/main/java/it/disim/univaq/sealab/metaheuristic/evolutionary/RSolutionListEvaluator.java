package it.disim.univaq.sealab.metaheuristic.evolutionary;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

@SuppressWarnings("serial")
public class RSolutionListEvaluator implements SolutionListEvaluator<RSolution> {

	public RSolutionListEvaluator() {
	}

	@Override
	public List<RSolution> evaluate(List<RSolution> solutionList, Problem<RSolution> problem) {
		ExecutorService executor = Executors.newFixedThreadPool(solutionList.size());
//		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		for (RSolution refactoringSolution : solutionList) {
			Runnable worker = new RSolutionListEvaluatorRunnable(problem, refactoringSolution);
            executor.execute(worker);
			
			//			problem.evaluate(refactoringSolution);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(10, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return solutionList;
	}

	@Override
	public void shutdown() {
	}

}
