package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ParserException;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvComponentToNN;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvOperationToComp;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvOperationToNCToNN;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import logicalSpecification.Action;
import logicalSpecification.FOLSpecification;

public class UMLRSequence extends RSequence {

//	private Refactoring refactoring;

	// private Resource modelRefactoredResource;

//	private UMLRSolution solution;

//	private int numOfPAs;
//	private double numOfChanges;
//	private float perfQuality;

//	private Manager manager;
//	private MetamodelManager metamodelManager;
//	private Controller controller;

	// private EObject model;

	public UMLRSequence(UMLRSolution solution) {
		super(solution);
	}

	public UMLRSequence(int length, int number_of_actions, int allowed_failures, UMLRSolution solution)
			throws ParserException, UnexpectedException {

		super(length, number_of_actions, allowed_failures, solution);

	}

	@Override
	protected boolean tryRandomPush(int n) throws UnexpectedException {
		Refactoring temporary_ref = this.refactoring.clone(getSolution());

		RefactoringAction candidate;
		do {
			candidate = manager.getMetamodelManager().getRandomAction(n, this);
		} while (candidate == null);

		temporary_ref.getActions().add(candidate);

		if (this.isFeasible(temporary_ref)) {
			this.insert(candidate);
			return true;
		} else {
			candidate.cleanUp();
			candidate = null;
		}
		//temporary_ref.cleanUp();
		temporary_ref = null;
		return false;
	}

	@Override
	protected boolean isFeasible(Refactoring tr) {
		int maxCloning = 0;

		boolean found = false;
		// Finds multiple modification of the same target element
		for (RefactoringAction a : tr.getActions()) {
			int j = 0;
			while (j < tr.getActions().size() && !found) {
				Action a2 = tr.getActions().get(j);
				if (a.equals(a2) && j != tr.getActions().indexOf(a)) {
					EasierLogger.logger_.warning("Multi-modification of the same operation for Solution #"
							+ this.getSolution().getName() + "!");
					return false;
				}
				j++;
			}
		}

		FOLSpecification app = manager.calculatePreCondition(tr).getConditionFormula();
		//System.out.println("Precondition of a Sol# " + solution.name + " refactoring ");
		boolean fol = false;
		try {
			fol = manager.evaluateFOL(app, ((UMLRSolution) this.getSolution()).getDirtyModel());
		} catch (ParserException e) {
			EasierLogger.logger_.info("Precondition of Solution # " + this.getSolution().getName()
					+ " has generated a Parser Exception!");
			e.printStackTrace();
		}

		if (!fol) {
			//System.out.println("Refactoring sequence");
			//System.out.println(getRefactoring().toString());
		}
		//System.out.println("Precondition of Solution # " + this.getSolution().getName() + " is " + fol);

		return fol;
	}

	public UMLRSequence(UMLRSequence seq) {
		super(seq);
	}

	public UMLRSequence(UMLRSequence seq, UMLRSolution solution) {
		super(seq, solution);
	}

//	public boolean isFeasible() {
//		try {
//			return this.isFeasible(this.refactoring);
//		} catch (ParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}

	public boolean alter(int position, int n) throws UnexpectedException, ParserException {

		Refactoring temporary_ref = this.refactoring.clone(getSolution());

		RefactoringAction candidate;
		do {
			candidate = metamodelManager.getRandomAction(n, this);
		} while (candidate == null);

		temporary_ref.getActions().set(position, candidate);

		if (this.isFeasible(temporary_ref)) {
			boolean found = false;
			temporary_ref = null;
			if (!found) {
				this.replace(position, candidate);
				// temporary_ref = null;
				return true;
			}
		} else {
			candidate.cleanUp();
			candidate = null;
			temporary_ref = null;
		}
		return false;
	}

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

}
