package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.uma.jmetal.problem.Problem;

import it.univaq.disim.sealab.easier.uml.utils.UMLMemoryOptimizer;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class UMLRSolutionListEvaluator <S extends RSolution<?>>extends RSolutionListEvaluator<S> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<S> evaluate(List<S> solutionList, Problem<S> problem) {
//		ExecutorService executor = Executors.newFixedThreadPool(solutionList.size());
		ExecutorService executor = Executors.newFixedThreadPool(1);

		solutionList.stream().forEach(sol -> {
			sol.executeRefactoring();
			sol.applyTransformation();
			sol.invokeSolver();
			if(Configurator.eINSTANCE.getProbPas() != 0)
				sol.countingPAs();
			sol.evaluatePerformance();
			sol.computeReliability();
//			sol.computeScenarioRT();
			problem.evaluate(sol);
		});
		
		return solutionList;
	}

}
