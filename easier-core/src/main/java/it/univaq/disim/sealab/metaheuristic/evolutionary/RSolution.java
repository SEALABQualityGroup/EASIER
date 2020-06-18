package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.nio.file.Path;
import java.rmi.UnexpectedException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.ParserException;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.impl.AbstractGenericSolution;
import org.uma.jmetal.util.solutionattribute.impl.CrowdingDistance;
import org.uma.jmetal.util.solutionattribute.impl.DominanceRanking;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

@SuppressWarnings("serial")
public abstract class RSolution extends AbstractGenericSolution<RSequence, RProblem<?>> implements Solution<RSequence> {

	protected final int VARIABLE_INDEX = 0;
	protected UUID ID;
	protected static int SOLUTION_COUNTER = -1;
	protected EObject model;
	// private String mmaemiliaFileStr;

	public static int XOverCounter = 0;
	public static int MutationCounter = 0;

	protected boolean crossovered;
	protected boolean mutated;
	protected boolean refactored;

	protected RSolution[] parents = new RSolution[2];

	protected int name;
	protected ResourceSet resourceSet;
	protected String mmaemiliaFolderStr;

	protected Instant startingTime, endingTime;
	protected float perfQ;
	protected double changes;

	protected int numPAs = -1;

	protected Manager manager;
	protected Controller controller;

	protected MetamodelManager metamodelManager;
	protected Path folderPath;

	protected RSolution(RProblem p) throws ParserException, UnexpectedException {
		super(p);
		setName(++SOLUTION_COUNTER);
		ID = UUID.randomUUID();
		resetParents();
		init(p.getController());
		this.createRandomRefactoring(p.length_of_refactorings, p.number_of_actions, p.allowed_failures);

		crossovered = false;
		mutated = false;
		refactored = false;

	}

	public RSolution(RSolution s) {
		super(s.problem);
		setName(++SOLUTION_COUNTER);
		ID = UUID.randomUUID();

		resetParents();
		init(s.problem.getController());

		this.copyRefactoringVariable(s.getVariableValue(VARIABLE_INDEX), this);

		for (int i = 0; i < s.problem.getNumberOfObjectives(); i++) {
			this.setObjective(i, s.getObjective(i));
		}
		this.attributes = s.attributes;
		this.setAttribute(CrowdingDistance.class, s.getAttribute(CrowdingDistance.class));

		crossovered = false;
		mutated = false;
		refactored = false;
	}

	public RSolution(RSolution s1, RSolution s2, int point, boolean left) {
		super(s1.problem);
		setName(++SOLUTION_COUNTER);
		ID = UUID.randomUUID();

		init(s1.problem.getController());

		assert (!this.model.equals(s1.getModel()) && !this.model.equals(s2.getModel()));
		assert (s1.problem.equals(s2.problem));
		assert (s1.getNumberOfObjectives() == s2.getNumberOfObjectives());
		assert (s1.getNumberOfVariables() == s2.getNumberOfVariables());
		assert (s1.getLength() == s2.getLength());
		assert (point > 0 && point < s1.getLength());

		for (int i = 0; i < s1.problem.getNumberOfVariables(); i++) {
			if (i == VARIABLE_INDEX) {
				if (left) {
					this.setVariableValue(VARIABLE_INDEX, this.createChild(s1, s2, point));
				} else {
					this.setVariableValue(VARIABLE_INDEX, this.createChild(s2, s1, point));

				}
				assert (this.getLength() == s1.getLength() && s1.getLength() == s2.getLength());
			} else {
				try {
					throw new UnexpectedException("Should not happen");
				} catch (UnexpectedException e) {
					e.printStackTrace();
				}
			}

		}

		for (int i = 0; i < s1.problem.getNumberOfObjectives(); i++) {
			this.setObjective(i, s1.getObjective(i));
		}

		this.setAttribute(CrowdingDistance.class, 0.0);

		assert (this.getVariableValue(VARIABLE_INDEX).getLength() == s1.getVariableValue(VARIABLE_INDEX).getLength());
		assert (this.getVariableValue(0).getRefactoring().getActions().size() == this.getLength());

		crossovered = false;
		mutated = false;
		refactored = false;
	}

	protected abstract RSequence createChild(RSolution s1, RSolution s2, int point);

	public abstract void countingPAs();

	public abstract void updateThresholds();

	public abstract void updateModel(MetamodelManager metamodelManager);

	public abstract void updateModel();

	public abstract void invokeSolver();

	public abstract float evaluatePerformance();

	protected abstract void init(Controller controller);

	public abstract void createNewModel(Path modelFilePath);

	protected abstract void createRandomRefactoring(int l, int n, int a) throws UnexpectedException, ParserException;

	protected abstract void copyRefactoringVariable(RSequence variableValue, RSolution sol);

	/**
	 * Invoke the Transformation Class in order to generate .aem e .val files
	 * 
	 * @param metamodelManager
	 */
	public abstract void applyTransformation();

	/**
	 * Starting from the solution family each refactoring action should be applied
	 * in order to obtain a refactoring model on which a performance evaluation can
	 * be executed
	 * 
	 * @param metamodelManager
	 */
	public abstract void executeRefactoring(MetamodelManager metamodelManager);

	public abstract void executeRefactoring();

	protected void resetParents() {
		if (this.parents != null) {
			this.parents[0] = null;
			this.parents[1] = null;
		}
	}

	public List<Resource> getResources() {
		return this.getResourceSet().getResources();
	}

	@Override
	public String getVariableValueString(int index) {
		String strValue = "Solution ID : " + this.getName() + " ( " + getObjective(0) + ", " + getObjective(1) + ", "
				+ getObjective(2) + " )" + "\n\t";
		strValue += getVariableValue(index).toString();
		return strValue;
	}

	public RProblem getProblem() {
		return this.problem;
	}

	@Override
	public abstract Solution<RSequence> copy();

	public RefactoringAction getActionAt(int index) {
		return getVariableValue(VARIABLE_INDEX).get(index);
	}

	public int getLength() {
		return getVariableValue(VARIABLE_INDEX).getLength();
	}

	public boolean alter(int i) throws UnexpectedException, ParserException {
		return this.getVariableValue(VARIABLE_INDEX).alter(i, this.problem.number_of_actions);
	}

	public void resolve(MetamodelManager metamodelManager) {
		EasierLogger.logger_.info("Solving Solution #" + getName());
		startingTime = Instant.now();
		executeRefactoring();

		// applyTransformation();
		invokeSolver();

		// controller.awaitExecutor();
		updateModel();

		updateThresholds();

		// countingPAsOnAemiliaModel(controller.getPerfQuality(),
		// controller.getRuleFilePath(), this.getValPath(),
		// metamodelManager);
		countingPAs();

		perfQ = evaluatePerformance();

		FileUtils.simpleSolutionWriterToCSV(this);
		endingTime = Instant.now();
		EasierLogger.logger_.info("Solution #" + getName() + " solved");
	}

	public Duration getElapsedTime() {
		Duration elapsedTime = Duration.ZERO;
		try {
			elapsedTime = Duration.between(startingTime, endingTime);
		} catch (NullPointerException e) {
			elapsedTime = Duration.ZERO;
		}
		return elapsedTime;
	}

	@Override
	public String toString() {

		String result = "Variables: ";
		for (int i = 0; i < this.problem.getNumberOfVariables(); i++) {
			result += "" + this.getVariableValueString(i) + " ";
		}
		result += "Objectives: ";
		for (int i = 0; i < this.problem.getNumberOfObjectives(); i++) {
			result += "" + new DecimalFormat("#.##").format(this.getObjective(i)).replaceAll(",", ".") + " ";
		}
		result += "DominanceRanking: "
				+ new DecimalFormat("#.##").format(this.getAttribute(DominanceRanking.class)).replaceAll(",", ".");
		result += " CrowdingDistance: "
				+ new DecimalFormat("#.##").format(this.getAttribute(CrowdingDistance.class)).replaceAll(",", ".");
		result += "\n";
		return result;
	}

	public EObject getModel() {
		return model;
	}

	public void setModel(EObject model) {
		this.model = model;
	}

	public UUID getID() {
		return ID;
	}

	public void setID(UUID iD) {
		ID = iD;
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		assert (getResourceSet().getResources().isEmpty());
	}

	public boolean isCrossovered() {
		return crossovered;
	}

	public void setCrossovered(boolean crossovered) {
		this.crossovered = crossovered;
	}

	public boolean isMutated() {
		return mutated;
	}

	public void setMutated(boolean mutated) {
		this.mutated = mutated;
	}

	public RSolution[] getParents() {
		return parents;
	}

	public void setParents(RSolution parent1, RSolution parent2) {
		this.parents[0] = parent1;
		this.parents[1] = parent2;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public boolean isRefactored() {
		return refactored;
	}

	public void setRefactored(boolean refactored) {
		this.refactored = refactored;
	}

	public abstract void refreshModel();

	public int getPAs() {
		return numPAs;
	}

	public float getPerfQ() {
		return perfQ;
	}

	public void setPerfQ(float perfQ) {
		this.perfQ = perfQ;
	}

	public double getNumOfChanges() {
		changes = 0.0;
		Refactoring r = this.getVariableValue(0).getRefactoring();
		for (RefactoringAction action : r.getActions()) {
			changes += action.getNumOfChanges();
		}
		return changes;
	}

	public Manager getManager() {
		return this.manager;
	}

	public Controller getController() {
		return this.controller;
	}
}
