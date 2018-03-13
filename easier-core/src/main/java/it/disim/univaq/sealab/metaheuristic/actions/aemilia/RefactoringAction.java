package it.disim.univaq.sealab.metaheuristic.actions.aemilia;

import it.disim.univaq.sealab.metaheuristic.evolutionary.RSolution;
import logicalSpecification.Action;

public interface RefactoringAction extends Action{
	
	public RefactoringAction clone(RSolution solution);
}
