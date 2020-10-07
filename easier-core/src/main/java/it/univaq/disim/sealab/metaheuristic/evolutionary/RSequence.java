package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.rmi.UnexpectedException;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ParserException;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import logicalSpecification.Action;
import logicalSpecification.FOLSpecification;

public abstract class RSequence {

	protected Refactoring refactoring;

	protected RSolution solution;

	protected int numOfPAs;
	protected double numOfChanges;
	protected double perfQuality;
	protected double reliability;

	protected Manager manager;
	protected MetamodelManager metamodelManager;
	protected Controller controller;

	public RSequence(RSolution solution) {
		this.solution = solution;
		manager = this.solution.getManager();
		controller = this.solution.getController();
		metamodelManager = manager.getMetamodelManager();
		this.refactoring = new Refactoring(getSolution());
		this.refactoring.setName(Integer.toString(Manager.REFACTORING_COUNTER++));
	}

	public RSequence(int length, int number_of_actions, int allowed_failures, RSolution solution)
			throws UnexpectedException {
		this.solution = solution;
		this.refactoring = new Refactoring(solution);
		this.refactoring.setName(Integer.toString(Manager.REFACTORING_COUNTER++));

		this.manager = solution.getManager();
		this.controller = solution.getController();
		this.metamodelManager = manager.getMetamodelManager();

		int num_failures = 0;

		while (this.refactoring.getActions().size() < length) {
			if (!this.tryRandomPush(number_of_actions))
				num_failures++;
			//System.out.println("Actual number of failure ");
			//ProgressBar.showBar(num_failures, allowed_failures);
			if (num_failures >= allowed_failures) {
				// START OVER
				num_failures = 0;
				for (RefactoringAction a : this.refactoring.getActions()) {
					a.cleanUp();
				}
				this.refactoring = null;
				this.refactoring = new Refactoring(solution);
				this.refactoring.setName(Integer.toString(Manager.REFACTORING_COUNTER++));
			}
		}
		if (this.refactoring.getActions().size() != length) {
			throw new RuntimeException("Unable to fill initial population");
		}
	}

	protected abstract boolean tryRandomPush(int n) throws UnexpectedException;

	protected abstract boolean isFeasible(Refactoring tr);

	public RSequence(RSequence seq) {
		this.refactoring = seq.getRefactoring().clone(getSolution());
		this.refactoring.setName(Integer.toString(RandomUtils.nextInt(0, 100)));

	}

	public RSequence(RSequence seq, RSolution solution) {
		this.solution = solution;
		this.refactoring = seq.getRefactoring().clone(solution);

		manager = this.solution.getManager();
		controller = this.solution.getController();
		metamodelManager = manager.getMetamodelManager();

	}

	public Refactoring getRefactoring() {
		return this.refactoring;
	}

	public void setRefactorings(Refactoring r) {
		this.refactoring = r;
	}

	public void insert(RefactoringAction a) {
		this.refactoring.getActions().add(a);
	}

	public void insert(int i, RefactoringAction a) {
		this.refactoring.getActions().add(i, a);

	}

	public void replace(int i, RefactoringAction a) {
		this.refactoring.getActions().remove(i);
		this.refactoring.getActions().add(i, a);
	}

	public RefactoringAction get(int index) {
		return this.getRefactoring().getActions().get(index);
	}

	public int getLength() {
		return this.getRefactoring().getActions().size();
	}

	public boolean isFeasible() {
		return this.isFeasible(this.refactoring);
	}

	public abstract boolean alter(int position, int n) throws UnexpectedException, ParserException;

	@Override
	public String toString() {
		String strValue = "";
		for (Action el : this.getRefactoring().getActions()) {
			strValue += el.toString() + "\n\t";
		}
		return strValue;
	}

	public void print() {
		for (Action el : this.getRefactoring().getActions()) {
			System.out.println(el);
		}
	}

	public int getNumOfPAs() {
		return numOfPAs;
	}

	public void setNumOfPAs(int numOfPAs) {
		this.numOfPAs = numOfPAs;
	}

	public EObject getModel() {
		return this.solution.getModel();
	}

	public double getPerfQuality() {
		return perfQuality;
	}

	public void setPerfQuality(double perfQuality) {
		this.perfQuality = perfQuality;
	}

	public RSolution getSolution() {
		return solution;
	}

	public void setSolution(RSolution solution) {
		this.solution = solution;
	}

	public double getNumOfChanges() {
		return numOfChanges;
	}

	public void setNumOfChanges(final double d) {
		this.numOfChanges = d;
	}
	
	public void setReliability(final double r) {
		reliability = r;
	}
}
