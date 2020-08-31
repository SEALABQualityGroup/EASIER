package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.ObjectInputStream.GetField;
import java.nio.file.Path;
import java.rmi.UnexpectedException;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.ParserException;
import org.uma.jmetal.solution.impl.AbstractGenericSolution;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;

public abstract class RSolution extends AbstractGenericSolution<RSequence, RProblem<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Path modelPath;

	protected RSolution(RProblem<?> problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	public abstract Controller getController();

	public abstract RProblem<?> getProblem();

	public abstract int getName();

	public abstract float getPerfQ();

	public abstract double getNumOfChanges();

	public abstract int getPAs();

	public abstract int getLength();

	public abstract void setCrossovered(boolean v);

	public abstract void setMutated(boolean v);

	public abstract boolean isCrossovered();

	public abstract EObject getModel();

	public abstract void setParents(RSolution s1, RSolution s2);

	public abstract void updateModel();

	public abstract void updateThresholds();

	public abstract void countingPAs();

	public abstract float evaluatePerformance();

	public abstract void executeRefactoring();

	public abstract void applyTransformation();

	public abstract boolean alter(int i) throws UnexpectedException, ParserException;

	public abstract Manager getManager();

	public abstract void invokeSolver();

	public abstract List<Resource> getResources();
	
	public abstract RefactoringAction getActionAt(int index);

	public Path getModelPath() {
		return modelPath;
	}

//	@Override
//	public double getObjective(int j) {
//		return (j == 0 && !getController().getConfigurator().isWorsen()) ? (-1 * super.getObjective(j))
//				: super.getObjective(j);
//		/*
//		 * if(j == 0 && !getController().getConfigurator().isWorsen()) return (-1 *
//		 * super.getObjective(j)); return super.getObjective(j);
//		 */
//	}
}
