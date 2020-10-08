package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.ObjectInputStream.GetField;
import java.nio.file.Path;
import java.rmi.UnexpectedException;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.ParserException;
import org.uma.jmetal.solution.impl.AbstractGenericSolution;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;

public abstract class RSolution extends AbstractGenericSolution<RSequence, RProblem<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Path modelPath;
	
	protected boolean refactored;
	protected boolean crossovered;
	protected boolean mutated;
	
	protected ResourceSet resourceSet;
	protected Manager manager;
	protected Controller controller;
	
	protected EObject model;
	
	protected static int SOLUTION_COUNTER = 0;
	
	protected int name;
	
	protected double perfQ;
	protected int numPAs;
	protected double reliability;
	
	protected RSolution[] parents;
	
	public static int MutationCounter = 0;
	public static int XOverCounter = 0;

	protected RSolution(RProblem<?> problem) {
		super(problem);
		crossovered = false;
		mutated = false;
		refactored = false;
	}

//	public abstract Controller getController();

	public abstract RProblem<?> getProblem();

	public abstract int getLength();

//	public abstract EObject getModel();

//	public abstract void setParents(RSolution s1, RSolution s2);

	public abstract void updateModel();

	public abstract void updateThresholds();

	public abstract void countingPAs();

	public abstract double evaluatePerformance();

	public abstract void executeRefactoring();

	public abstract void applyTransformation();
	public abstract void computeReliability();

	public abstract boolean alter(int i) throws UnexpectedException, ParserException;

//	public abstract Manager getManager();

	public abstract void invokeSolver();

//	public abstract List<Resource> getResources();
	
	public abstract RefactoringAction getActionAt(int index);

	public Path getModelPath() {
		return modelPath;
	}
	
	public double getReliability() {
		return reliability;
	}
	
	public void setRefactored() {
		this.refactored = true;
	}
	
	public boolean isRefactored() {
		return refactored;
	}
	
	public void setCrossovered() {
		this.crossovered = true;
		XOverCounter++;
	}

	public void setMutated() {
		this.mutated = true;
		MutationCounter++;
	}
	
	public boolean isMutated() {
		return mutated;
	}

	public boolean isCrossovered() {
		return crossovered;
	}
	
	public double getPerfQ() {
		// perfQ = evaluatePerformance();
		return perfQ;
	}

	public void setPerfQ(float perfQ) {
		this.perfQ = perfQ;
	}

	public double getNumOfChanges() {
		double changes = 0.0;
		Refactoring r = this.getVariableValue(0).getRefactoring();
		for (RefactoringAction action : r.getActions()) {
			changes += action.getNumOfChanges();
		}
		return changes;
	}
	
	public int getPAs() {
		return numPAs;
	}
	
	public int getName() {
		return name;
	}

	public Manager getManager() {
		return this.manager;
	}

	public Controller getController() {
		return this.controller;
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	public static synchronized int getCounter() {
		return SOLUTION_COUNTER++;
	}

	public void setName() {
		this.name = getCounter();
	}
	
	public EObject getModel() {
		return model;
	}
	
	public List<Resource> getResources() {
		return this.getResourceSet().getResources();
	}
	
	public void save() {
		manager.getMetamodelManager().save(this);
	}
	
	protected void resetParents() {
		if (this.parents != null) {
			this.parents[0] = null;
			this.parents[1] = null;
		}
	}
	
	public RSolution[] getParents() {
		return parents;
	}
	
	public void setParents(RSolution parent1, RSolution parent2) {
		this.parents[0] = parent1;
		this.parents[1] = parent2;
	}
	
	public boolean equals(Object sol) {
		
		if(this.getName() == (((RSolution)sol).getName()))
			return true;
		
		if(this.getVariableValue(0).getRefactoring().equals(((RSolution)sol).getVariableValue(0).getRefactoring()))
			return true;
		
		return false;
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
