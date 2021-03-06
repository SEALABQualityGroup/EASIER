package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

import it.univaq.disim.sealab.metaheuristic.evolutionary.InvokeSolverRunnable;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

@SuppressWarnings("serial")
public class RSolutionListEvaluator<S extends RSolution> implements SolutionListEvaluator<S> {

	public RSolutionListEvaluator() {
	}

	@Override
	public List<S> evaluate(List<S> solutionList, Problem<S> problem) {
		ExecutorService executor = Executors.newFixedThreadPool(solutionList.size());

		for (S sol : solutionList) {
			sol.executeRefactoring();
			sol.applyTransformation();
		}

		for (S refactoringSolution : solutionList) {
			if (executor != null) {
				Runnable worker = new InvokeSolverRunnable(refactoringSolution);
				executor.execute(worker);
			} else {
				//check if it needs other methods before evaluating the solution see the loop below
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
		
		// Ignore solution with perfQ == 0
		ListIterator<S> iter = solutionList.listIterator();
		while(iter.hasNext()){
			S sol = iter.next();
		    if(sol.evaluatePerformance() == 0){
		        iter.remove();
		    }
		    else {
				sol.updateModel();
				sol.updateThresholds();
				sol.countingPAs();
				FileUtils.simpleSolutionWriterToCSV(sol);
				problem.evaluate(sol);
			}
		    
		}

		//TODO check whether the solutionList is empty
		
//		for (S sol : solutionList) {
//			//Verify if it works
//			if (sol.evaluatePerformance() == 0)
//				solutionList.remove(sol);
//			else {
//				sol.updateModel();
//				sol.updateThresholds();
//				sol.countingPAs();
//				FileUtils.simpleSolutionWriterToCSV(sol);
//				problem.evaluate(sol);
//			}
//		}
		return solutionList;
	}

	@Override
	public void shutdown() {
	}

}
