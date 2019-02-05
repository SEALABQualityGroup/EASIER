package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.rmi.UnexpectedException;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ParserException;

import it.univaq.disim.sealab.metaheuristic.actions.aemilia.AEmiliaCloneAEIRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.AEmiliaConstChangesRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import logicalSpecification.Action;
import logicalSpecification.FOLSpecification;

public class RSequence {

	private Refactoring refactoring;

	// private Resource modelRefactoredResource;

	private RSolution solution;

	private int numOfPAs;
	private double numOfChanges;
	private float perfQuality;

	private Manager manager;
	private MetamodelManager metamodelManager;
	private Controller controller;

	// private EObject model;

	public RSequence(RSolution solution) {
		this.solution = solution;
		manager = this.solution.getManager();
		controller = this.solution.getController();
		metamodelManager = manager.getMetamodelManager();
		this.refactoring = new Refactoring(getSolution());
		this.refactoring.setName(Integer.toString(Manager.REFACTORING_COUNTER++));
	}

	public RSequence(int length, int number_of_actions, int allowed_failures, RSolution solution)
			throws ParserException, UnexpectedException {
		this.solution = solution;
		this.refactoring = new Refactoring(getSolution());
		this.refactoring.setName(Integer.toString(Manager.REFACTORING_COUNTER++));

		this.manager = solution.getManager();
		this.controller = solution.getController();
		this.metamodelManager = manager.getMetamodelManager();

		assert (this.refactoring.getActions().size() == 0);
		int num_failures = 0;

		while (this.refactoring.getActions().size() < length) {
			if (!this.tryRandomPush(number_of_actions))
				num_failures++;

			if (num_failures >= allowed_failures) {
				// START OVER
				num_failures = 0;
				this.refactoring = null;
				// this.refactoring = LogicalSpecificationFactory.eINSTANCE.createRefactoring();
				this.refactoring = new Refactoring(getSolution());
				this.refactoring.setName(Integer.toString(Manager.REFACTORING_COUNTER++));
				assert (this.refactoring.getActions().size() == 0);
			}
			// throw new RuntimeException("\nUnable to generate sequence due to
			// preconditions");
		}
		if (this.refactoring.getActions().size() != length) {
			throw new RuntimeException("Unable to fill initial population");
		}
	}

	private boolean tryRandomPush(int n) throws UnexpectedException, ParserException {
		Refactoring temporary_ref = this.refactoring.clone(getSolution());

		// assert (temporary_ref.equals(this.refactoring));
		assert (temporary_ref.getName().equals(this.refactoring.getName()));

		RefactoringAction candidate;
		do {
			candidate = manager.getMetamodelManager().getRandomAction(n, this);
		} while (candidate == null);

		assert (candidate != null);
		// candidate.setModel(this.getModel());

		temporary_ref.getActions().add(candidate);

		if (this.isFeasible(temporary_ref)) {
			// this.insert(candidate);

			int i = 0;
			boolean found = false;
			while (i < temporary_ref.getActions().size() && !found) {
				RefactoringAction a = temporary_ref.getActions().get(i);
				if (a instanceof AEmiliaCloneAEIRefactoringAction) {
					// AemiliaManager metamodelManager = (AemiliaManager)
					// Manager.getInstance(null).getMetamodelManager();
					if (!((AemiliaManager) metamodelManager).isApplicable(((AEmiliaCloneAEIRefactoringAction) a),
							temporary_ref.getSolution().getVariableValue(0)))
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

			// temporary_ref = null;
			// return true;
		} else {
			candidate = null;
			temporary_ref = null;
			return false;
		}
	}

	private boolean isFeasible(Refactoring tr) throws ParserException {
		// Controller controller = Manager.getInstance(null).getController();
		int maxCloning = 0;
		for (RefactoringAction a : tr.getActions()) {
			if (a instanceof AEmiliaCloneAEIRefactoringAction) {
				maxCloning++;
			}
		}
		if (maxCloning > controller.getConfigurator().getMaxCloning()) {
			Controller.logger_.warning("Too much clone actions for Solution #" + this.getSolution().getName() + "!");
			return false;
		}

		for (RefactoringAction action : tr.getActions()) {
			action.setParameters();
			action.createPreCondition();
			action.createPostCondition();
		}

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
				Controller.logger_.warning("Too much clones in Solution #" + this.getSolution().getName() + "!");
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
			Controller.logger_.warning(
					"Multi-modification of the same constant for Solution #" + this.getSolution().getName() + "!");
			return false;
		}

		FOLSpecification app = manager.calculatePreCondition(tr).getConditionFormula();
		boolean fol = manager.evaluateFOL(app, this.getSolution().getModel());

		if (!fol) {
			Controller.logger_.info("Precondition of Solution # " + this.getSolution().getName() + " is false!");
		}

		return fol;
	}

	public RSequence(RSequence seq) {
		// this.refactoring = LogicalSpecificationFactory.eINSTANCE.createRefactoring();
		// this.refactoring = (Refactoring) EcoreUtil.copy(variable.getRefactoring());

		this.refactoring = seq.getRefactoring().clone(getSolution());
		this.refactoring.setName(Integer.toString(RandomUtils.nextInt(0, 100)));
		// this.setModel(EcoreUtil.copy(variable.getModel()));
		// this.refactoring = variable.getRefactoring().cloneRefactoring();
		assert (this.refactoring.equals(seq.getRefactoring()));

	}

	public RSequence(RSequence seq, RSolution solution) {
		// this.refactoring = LogicalSpecificationFactory.eINSTANCE.createRefactoring();
		// this.refactoring = (Refactoring) EcoreUtil.copy(seq.getRefactoring());
		this.solution = solution;
		this.refactoring = seq.getRefactoring().clone(solution);

		manager = this.solution.getManager();
		controller = this.solution.getController();
		metamodelManager = manager.getMetamodelManager();

		assert (seq.refactoring.getActions().size() == 4);

		// for(RefactoringAction a : seq.refactoring.getActions()){
		// this.refactoring.getActions().add( ((RefactoringAction)a).clone(solution));
		// }

		assert (this.refactoring.getActions().size() == 4);
		assert (solution.getModel() != null);
		// this.setModel(solution.getModel());

		// this.refactoring = variable.getRefactoring().cloneRefactoring();
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
		try {
			return this.isFeasible(this.refactoring);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean alter(int position, int n) throws UnexpectedException, ParserException {

		// Refactoring temporary_ref =
		// LogicalSpecificationFactory.eINSTANCE.createRefactoring();
		Refactoring temporary_ref = this.refactoring.clone(getSolution());
		assert (temporary_ref.equals(this.refactoring));

		// Action candidate = Manager.getTautologyRandomAction(n, this);

		RefactoringAction candidate;
		do {
			candidate = metamodelManager.getRandomAction(n, this);
		} while (candidate == null);

		// Action candidate =
		// Manager.getInstance(null).getMetamodelManager().getRandomAction(n);
		assert (candidate != null);
		// assert
		// (Manager.getInstance(null).evaluateFOL(candidate.getPre().getConditionFormula()));
		// assert
		// (Manager.getInstance(null).evaluateFOL(candidate.getPost().getConditionFormula()));

		temporary_ref.getActions().set(position, candidate);
		if (this.isFeasible(temporary_ref)) {
			// if(Manager.evaluateFOL(Manager.calculatePreCondition(temporary_ref).getConditionFormula())){
			// this.replace(position, (Action) EcoreUtil.copy(candidate));

			int i = 0;
			boolean found = false;
			while (i < temporary_ref.getActions().size() && !found) {
				RefactoringAction a = temporary_ref.getActions().get(i);
				if (a instanceof AEmiliaCloneAEIRefactoringAction) {
					// AemiliaManager metamodelManager = (AemiliaManager)
					// Manager.getInstance(null).getMetamodelManager();
					// if(!metamodelManager.isApplicable(((AEmiliaCloneAEIRefactoringAction) a),
					// temporary_ref.getSolution().getVariableValue(0)) ||
					// metamodelManager.isInExcluding(((AEmiliaCloneAEIRefactoringAction) a),
					// temporary_ref.getSolution().getVariableValue(0),
					// temporary_ref.getActions().indexOf(((AEmiliaCloneAEIRefactoringAction) a))))
					if (!((AemiliaManager) metamodelManager).isApplicable(((AEmiliaCloneAEIRefactoringAction) a),
							temporary_ref.getSolution().getVariableValue(0)))
						found = true;
				}
				/// MODIFIED WRT LAST COMMIT
				// if(a instanceof AEmiliaConstChangesRefactoringAction) {
				// AemiliaManager metamodelManager = (AemiliaManager)
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

	public RSolution getSolution() {
		return solution;
	}

	public void setSolution(RSolution solution) {
		this.solution = solution;
	}

	public double getNumOfChanges() {
		return numOfChanges;
	}

	public void setNumOfChanges(double d) {
		this.numOfChanges = d;
	}
}
