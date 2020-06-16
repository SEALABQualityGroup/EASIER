package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.rmi.UnexpectedException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ParserException;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.AEmiliaCloneAEIRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.AEmiliaConstChangesRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.AEmiliaRemoveClonedAEIRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaMetamodelManager;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import logicalSpecification.Action;
import logicalSpecification.FOLSpecification;

public class AemiliaRSequence extends RSequence {

//	private Refactoring refactoring;

	// private Resource modelRefactoredResource;

//	private AemiliaRSolution solution;

//	private int numOfPAs;
//	private double numOfChanges;
//	private float perfQuality;

//	private Manager manager;
//	private MetamodelManager metamodelManager;
//	private Controller controller;

	// private EObject model;

	public AemiliaRSequence(AemiliaRSolution solution) {
		super(solution);
	}

	public AemiliaRSequence(int length, int number_of_actions, int allowed_failures, AemiliaRSolution solution)
			throws UnexpectedException {

		super(length, number_of_actions, allowed_failures, solution);

	}

	@Override
	protected boolean tryRandomPush(int n) throws UnexpectedException {
		Refactoring temporary_ref = this.refactoring.clone(getSolution());

		RefactoringAction candidate;
		do {
			candidate = manager.getMetamodelManager().getRandomAction(n, this);
		} while (candidate == null);

		// candidate.setModel(this.getModel());

		temporary_ref.getActions().add(candidate);

		if (this.isFeasible(temporary_ref)) {
			// this.insert(candidate);

			int i = 0;
			boolean found = false;
			while (i < temporary_ref.getActions().size() && !found) {
				RefactoringAction a = temporary_ref.getActions().get(i);
				if (a instanceof AEmiliaCloneAEIRefactoringAction) {
					if (!metamodelManager.isApplicable(a, temporary_ref.getSolution().getVariableValue(0)))
						found = true;
				}
				i++;
			}
			temporary_ref = null;
			if (!found) {
				this.insert(candidate);
				// temporary_ref = null;
				return true;
			}
			return false;

		} else {
			candidate = null;
			temporary_ref = null;
			return false;
		}
	}

	@Override
	protected boolean isFeasible(Refactoring tr) {//throws ParserException {
		// Controller controller = Manager.getInstance(null).getController();
		int maxCloning = 0;
		for (RefactoringAction a : tr.getActions()) {
			if (a instanceof AEmiliaCloneAEIRefactoringAction) {
				maxCloning++;
			}
		}
		if (maxCloning > solution.getProblem().getMaxCloning()) {
			EasierLogger.logger_
					.warning("Too much clone actions for Solution #" + this.getSolution().getName() + "!");
			return false;
		}

		//can be removed it is done within the constructor
		/*for (RefactoringAction action : tr.getActions()) {
			action.setParameters();
			action.createPreCondition();
			action.createPostCondition();
		}*/

		for (RefactoringAction ac : tr.getActions()) {
			int counter = 0;
			if (ac instanceof AEmiliaCloneAEIRefactoringAction) {
				int j = 0;
				while (j < tr.getActions().size()) {
					Action a2 = tr.getActions().get(j);
					if (a2 instanceof AEmiliaCloneAEIRefactoringAction) {
						if (((AEmiliaCloneAEIRefactoringAction) a2).getSourceAEI().getInstanceName()
								.equals(((AEmiliaCloneAEIRefactoringAction) ac).getSourceAEI().getInstanceName()))
							counter++;
					}
					j++;
				}
			}
			if (counter > 1) {
				EasierLogger.logger_.warning("Too much clones of the same AEI in Solution #" + this.getSolution().getName() + "!");
				return false;
			}
		}
		
		for (RefactoringAction ac : tr.getActions()) {
			int counter = 0;
			if (ac instanceof AEmiliaRemoveClonedAEIRefactoringAction) {
				int j = 0;
				while (j < tr.getActions().size()) {
					Action a2 = tr.getActions().get(j);
					if (a2 instanceof AEmiliaRemoveClonedAEIRefactoringAction) {
						if (((AEmiliaRemoveClonedAEIRefactoringAction) a2).getSourceAEI().getInstanceName()
								.equals(((AEmiliaRemoveClonedAEIRefactoringAction) ac).getSourceAEI().getInstanceName()))
							counter++;
					}
					j++;
				}
			}
			if (counter > 1) {
				EasierLogger.logger_.warning("Too much remove clones of the same AEI in Solution #" + this.getSolution().getName() + "!");
				return false;
			}
		}

		boolean found = false;
		for (RefactoringAction a : tr.getActions()) {
			if (a instanceof AEmiliaConstChangesRefactoringAction) {
				int j = 0;
				while (j < tr.getActions().size() && !found) {
					Action a2 = tr.getActions().get(j);
					if (a2 instanceof AEmiliaConstChangesRefactoringAction) {
						if (j != tr.getActions().indexOf(a))
							if (((AEmiliaConstChangesRefactoringAction) a2).getSourceConstInit().getName()
									.equals(((AEmiliaConstChangesRefactoringAction) a).getSourceConstInit().getName()))
								found = true;
					}
					j++;
				}
			}
		}
		if (found) {
			EasierLogger.logger_.warning(
					"Multi-modification of the same constant for Solution #" + this.getSolution().getName() + "!");
			return false;
		}

		FOLSpecification app = manager.calculatePreCondition(tr).getConditionFormula();
		boolean fol = false;
		try {
			fol = manager.evaluateFOL(app, this.getSolution().getModel());
		} catch (ParserException e) {
			EasierLogger.logger_.info("Precondition of Solution # " + this.getSolution().getName() + " has generated a Parser Exception!");
			e.printStackTrace();
		}

		if (!fol) {
			EasierLogger.logger_.info("Precondition of Solution # " + this.getSolution().getName() + " is false!");
		}

		return fol;
	}

	public AemiliaRSequence(AemiliaRSequence seq) {
		super(seq);
	}

	public AemiliaRSequence(AemiliaRSequence seq, AemiliaRSolution solution) {
		super(seq, solution);
	}

//	public boolean isFeasible() {
//		try {
//			return this.isFeasible(this.refactoring);
//		} catch (ParserException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	public boolean alter(int position, int n) throws UnexpectedException, ParserException {

		// Refactoring temporary_ref =
		// LogicalSpecificationFactory.eINSTANCE.createRefactoring();
		Refactoring temporary_ref = this.refactoring.clone(getSolution());

		// Action candidate = Manager.getTautologyRandomAction(n, this);

		RefactoringAction candidate;
		do {
			candidate = metamodelManager.getRandomAction(n, this);
		} while (candidate == null);

		// Action candidate =
		// Manager.getInstance(null).getMetamodelManager().getRandomAction(n);

		temporary_ref.getActions().set(position, candidate);
		if (this.isFeasible(temporary_ref)) {
			// if(Manager.evaluateFOL(Manager.calculatePreCondition(temporary_ref).getConditionFormula())){
			// this.replace(position, (Action) EcoreUtil.copy(candidate));

			int i = 0;
			boolean found = false;
			while (i < temporary_ref.getActions().size() && !found) {
				RefactoringAction a = temporary_ref.getActions().get(i);
				if (a instanceof AEmiliaCloneAEIRefactoringAction) {
					// AemiliaMetamodelManager metamodelManager = (AemiliaMetamodelManager)
					// Manager.getInstance(null).getMetamodelManager();
					// if(!metamodelManager.isApplicable(((AEmiliaCloneAEIRefactoringAction) a),
					// temporary_ref.getSolution().getVariableValue(0)) ||
					// metamodelManager.isInExcluding(((AEmiliaCloneAEIRefactoringAction) a),
					// temporary_ref.getSolution().getVariableValue(0),
					// temporary_ref.getActions().indexOf(((AEmiliaCloneAEIRefactoringAction) a))))
					if (!((AemiliaMetamodelManager) metamodelManager).isApplicable(((AEmiliaCloneAEIRefactoringAction) a),
							temporary_ref.getSolution().getVariableValue(0)))
						found = true;
				}
				/// MODIFIED WRT LAST COMMIT
				// if(a instanceof AEmiliaConstChangesRefactoringAction) {
				// AemiliaMetamodelManager metamodelManager = (AemiliaMetamodelManager)
				// Manager.getInstance(null).getMetamodelManager();
				//// if(!metamodelManager.isApplicable(((AEmiliaConstChangesRefactoringAction)
				// a), temporary_ref.getSolution().getVariableValue(0)) ||
				//// metamodelManager.isInExcluding(((AEmiliaConstChangesRefactoringAction) a),
				// temporary_ref.getSolution().getVariableValue(0),
				// temporary_ref.getActions().indexOf(((AEmiliaConstChangesRefactoringAction)
				// a))))
				// if(!metamodelManager.isApplicable(((AEmiliaConstChangesRefactoringAction) a),
				// temporary_ref.getSolution().getVariableValue(0)))
				// found = true;
				// }
				/// END MODIFIED WRT LAST COMMIT
				i++;
			}
			temporary_ref = null;
			if (!found) {
				this.replace(position, candidate);
				// temporary_ref = null;
				return true;
			}
			return false;
		} else {
			candidate = null;
			temporary_ref = null;
			return false;
		}

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

	// public Resource getModelRefactoredResource() {
	// return modelRefactoredResource;
	// }
	//
	// public void setModelRefactoredResource(Resource modelRefactoredResource) {
	// this.modelRefactoredResource = modelRefactoredResource;
	// }
	//
	// public void setModelRefactoredResource(String modelURI) {
	//
	// this.modelRefactoredResource =
	// UMLManager.getInstance().createModelResource(modelURI, true);
	// }

	public int getNumOfPAs() {
		return numOfPAs;
	}

	public void setNumOfPAs(int numOfPAs) {
		this.numOfPAs = numOfPAs;
	}

	public EObject getModel() {
		return this.solution.getModel();
	}

	// public void setModel(EObject model) {
	// this.model = model;
	// }

	public float getPerfQuality() {
		return perfQuality;
	}

	public void setPerfQuality(float perfQuality) {
		this.perfQuality = perfQuality;
	}

//	public AemiliaRSolution getSolution() {
//		return solution;
//	}
//
//	public void setSolution(AemiliaRSolution solution) {
//		this.solution = solution;
//	}

	public double getNumOfChanges() {
		return numOfChanges;
	}

	public void setNumOfChanges(double d) {
		this.numOfChanges = d;
	}
}
