package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.ParserException;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.impl.AbstractGenericSolution;
import org.uma.jmetal.util.solutionattribute.impl.CrowdingDistance;
import org.uma.jmetal.util.solutionattribute.impl.DominanceRanking;

import it.univaq.disim.sealab.aemiliaMod2text.main.Transformation;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.AEmiliaCloneAEIRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.AEmiliaConstChangesRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaStringManager;
import it.univaq.disim.sealab.metaheuristic.utils.ThresholdUtils;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.mmaemiliaPackage;
import metamodel.mmaemilia.Expressions.IdentExpr;
import metamodel.mmaemilia.Headers.ConstInit;

@SuppressWarnings("serial")
public class RSolution extends AbstractGenericSolution<RSequence, RProblem> implements Solution<RSequence> {

	private final int VARIABLE_INDEX = 0;
	private UUID ID;
	private static int SOLUTION_COUNTER = -1;
	private EObject model;
	private String mmaemiliaFilePath;

	public static int XOverCounter = 0;
	public static int MutationCounter = 0;

	private boolean crossovered;
	private boolean mutated;
	private boolean refactored;

	private RSolution[] parents = new RSolution[2];

	public static Logger logger_ = Logger.getLogger(RSolution.class.getName());

	private int name;
	private ResourceSet resourceSet;
	private String mmaemiliaFolderPath;

	private Instant startingTime, endingTime;
	private Map<String, List<ArchitecturalInteraction>> mapOfPAs;
	private String valFilePath;
	private float perfQ;
	private double changes;

	private int numPAs = -1;

	private Manager manager;
	private Controller controller;

	private AemiliaManager metamodelManager;

	protected RSolution(RProblem p) throws ParserException, UnexpectedException {
		super(p);
		setName(++SOLUTION_COUNTER);
		ID = UUID.randomUUID();
		resetParents();
		init(p.getController());
		// qui si potrebbe rendere la seq di lunghezza random
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

	private void resetParents() {
		getParents()[0] = null;
		getParents()[1] = null;
	}

	private void init(Controller controller) {
		this.controller = controller;
		manager = new Manager();
		manager.setController(controller);
		setResourceSet(new ResourceSetImpl());

		manager.setMetamodelManager(new AemiliaManager(controller));
		manager.setOclManager(manager.getMetamodelManager().getOclManager());
		manager.setOclStringManager(OclStringManager.getInstance(new OclAemiliaStringManager()));

		metamodelManager = (AemiliaManager) manager.getMetamodelManager();
		mmaemiliaFolderPath = controller.getTmpFolder() + (getName() / 100) + File.separator + getName()
				+ File.separator;
		mmaemiliaFilePath = mmaemiliaFolderPath + getName() + metamodelManager.getMetamodelFileExtension();

		controller.copyModel(mmaemiliaFilePath);
		createNewModel(mmaemiliaFilePath);
	}

	public void createNewModel(String modelFilePath) {
		Resource res = getResourceSet().getResource(Manager.string2Uri(modelFilePath), true);
		assert (res.getContents().get(0).equals((AEmiliaSpecification) EcoreUtil.getObjectByType(res.getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION)));
		this.model = (AEmiliaSpecification) EcoreUtil.getObjectByType(res.getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
	}

	public List<Resource> getResources() {
		return this.getResourceSet().getResources();
	}

	private void createRandomRefactoring(int l, int n, int a) throws UnexpectedException, ParserException {
		RSequence seq = new RSequence(l, n, a, this);
		this.setVariableValue(VARIABLE_INDEX, seq);
		this.setAttribute(CrowdingDistance.class, 0.0);
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

	private void copyRefactoringVariable(RSequence variableValue, RSolution sol) {
		RSequence newSeq = new RSequence(variableValue, sol);

		this.setVariableValue(VARIABLE_INDEX, newSeq);
	}

	@Override
	public Solution<RSequence> copy() {
		return new RSolution(this);
	}

	private RSequence createChild(RSolution s1, RSolution s2, int point) {

		RSequence seq = new RSequence(this);

		assert (s1.getLength() == 4);
		assert (s2.getLength() == 4);

		try {
			for (int j = 0; j < point; j++) {
				RefactoringAction _new = s1.getActionAt(j).clone(this);
				// _new.setSolution(this);
				assert (_new.equals(s1.getActionAt(j)));
				seq.insert(j, _new);
			}
			for (int j = point; j < s2.getLength(); j++) {
				RefactoringAction _new = s2.getActionAt(j).clone(this);
				// _new.setSolution(this);
				assert (_new.equals(s2.getActionAt(j)));
				seq.insert(j, _new);
			}
			return seq;
		} catch (IndexOutOfBoundsException e) {
			Controller.logger_.warning("POINT SIZE ERROR: " + Integer.toString(point));
			e.printStackTrace();
			return null;
		}
	}

	public RefactoringAction getActionAt(int index) {
		return getVariableValue(VARIABLE_INDEX).get(index);
	}

	public int getLength() {
		return getVariableValue(VARIABLE_INDEX).getLength();
	}

	public boolean alter(int i) throws UnexpectedException, ParserException {
		return this.getVariableValue(VARIABLE_INDEX).alter(i, this.problem.number_of_actions);
	}

	public void resolve(AemiliaManager metamodelManager) {
		Controller.logger_.info("Solving Solution #" + getName());
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
		countingPAsOnAemiliaModel();

		perfQ = evaluatePerformance();

		controller.simpleSolutionWriterToCSV(this);
		endingTime = Instant.now();
		Controller.logger_.info("Solution #" + getName() + " solved");
	}

	// public void updateThresholds(AemiliaManager metamodelManager) {
	// ThresholdUtils.uptodateSingleValueThresholds(mmaemiliaFolderPath,
	// mmaemiliaFilePath, valFilePath,
	// metamodelManager, controller);
	// }

	public void updateThresholds() {
		ThresholdUtils.uptodateSingleValueThresholds(mmaemiliaFolderPath, mmaemiliaFilePath, valFilePath,
				metamodelManager, controller);
	}

	public void updateModel(AemiliaManager metamodelManager) {
		String rewFilePath = controller.getSourceRewPath();

		String rewMappingFilePath = mmaemiliaFolderPath
				+ ((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName() + "_result"
				+ AemiliaManager.getRewmappingFileExtension();

		metamodelManager.aemiliaModelUpdate(valFilePath, rewFilePath, rewMappingFilePath, mmaemiliaFilePath, this);
	}

	public void updateModel() {
		String rewFilePath = controller.getSourceRewPath();

		String rewMappingFilePath = mmaemiliaFolderPath
				+ ((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName() + "_result"
				+ AemiliaManager.getRewmappingFileExtension();

		metamodelManager.aemiliaModelUpdate(valFilePath, rewFilePath, rewMappingFilePath, mmaemiliaFilePath, this);
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

	/**
	 * This method counts the number of Performance Antipatterns (PAs)
	 * 
	 * 
	 * @param perfQuality
	 * @param ruleFilePath
	 * @param valFilePath
	 * @param metamodelManager
	 * @return
	 */
	// private Map<String, List<ArchitecturalInteraction>>
	// countingPAsOnAemiliaModel(PerformanceQuality perfQuality,
	// String ruleFilePath, String valFilePath, AemiliaManager metamodelManager) {
	//
	// refreshModel();
	// ruleFilePath = mmaemiliaFolderPath + "detectionSingleValuePA.ocl";
	//
	// mapOfPAs = perfQuality.performanceAntipatternEvaluator(this.getModel(),
	// ruleFilePath);
	// if (mapOfPAs != null && !mapOfPAs.keySet().isEmpty()) {
	// numPAs++;
	// for (String paName : mapOfPAs.keySet()) {
	// numPAs += mapOfPAs.get(paName).size();
	// }
	// }
	//
	// return mapOfPAs;
	// }

	public Map<String, List<ArchitecturalInteraction>> countingPAsOnAemiliaModel() {

		refreshModel();
		String ruleFilePath = mmaemiliaFolderPath + "detectionSingleValuePA.ocl";

		mapOfPAs = controller.getPerfQuality().performanceAntipatternEvaluator(this.getModel(), ruleFilePath);
		if (mapOfPAs != null && !mapOfPAs.keySet().isEmpty()) {
			numPAs++;
			for (String paName : mapOfPAs.keySet()) {
				numPAs += mapOfPAs.get(paName).size();
			}
		}

		return mapOfPAs;
	}

	/**
	 * Invokes the TwoTowers Solver
	 * 
	 * @param metamodelManager
	 */
	public void invokeSolver(AemiliaManager metamodelManager) {
		String aemFilePath = mmaemiliaFolderPath + ((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName()
				+ "_result" + ((AemiliaManager) metamodelManager).getModelFileExtension();

		String rewFilePath = controller.getSourceRewPath();

		String outputFilePath = mmaemiliaFolderPath + ((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName()
				+ "_result";

		metamodelManager.gaussianEliminationSRBMC(aemFilePath, rewFilePath, outputFilePath);

		if (!new File(aemFilePath + ".val").exists()) {
			controller.checkTwoTowersOutput(outputFilePath);
		} else {
			setValPath(aemFilePath + ".val");
		}
	}

	public void invokeSolver() {
		String aemFilePath = mmaemiliaFolderPath + ((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName()
				+ "_result" + ((AemiliaManager) metamodelManager).getModelFileExtension();

		String rewFilePath = controller.getSourceRewPath();

		String outputFilePath = mmaemiliaFolderPath + ((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName()
				+ "_result";

		if (Controller.isSor()) {
			metamodelManager.sorSRBMC(aemFilePath, rewFilePath, outputFilePath);
		} else {
			metamodelManager.gaussianEliminationSRBMC(aemFilePath, rewFilePath, outputFilePath);
		}

		if (!new File(aemFilePath + ".val").exists()) {
			controller.checkTwoTowersOutput(outputFilePath);
		} else {
			setValPath(aemFilePath + ".val");
		}
	}

	public float evaluatePerformance() {
		AemiliaManager metamodelManager = (AemiliaManager) manager.getMetamodelManager();

		String valFilePath = mmaemiliaFolderPath + ((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName()
				+ "_result" + ((AemiliaManager) metamodelManager).getModelFileExtension() + ".val";

		perfQ = 0;
		if (!new File(valFilePath).exists()) {
			perfQ = Float.MAX_VALUE;
			Controller.logger_.warning("ERROR while evaluating PerfQ of Solution #" + this.getName() + ": "
					+ valFilePath + " doesn't exist.");
		} else {
			perfQ = controller.getPerfQuality().performanceQuality(controller.getSourceValPath(), valFilePath);

		}
		// Controller.logger_.info("Solution #" + this.getName() + ": PerformanceQuality
		// --> " + perfQ);

		return perfQ;
	}

	/**
	 * Invoke the Transformation Class in order to generate .aem e .val files
	 * 
	 * @param metamodelManager
	 */
	// private void applyTransformation(AemiliaManager metamodelManager) {
	// String mmaemiliaFilePath = mmaemiliaFolderPath + getName() +
	// metamodelManager.getMetamodelFileExtension();
	// Transformation.GenerateAEMTransformation(mmaemiliaFilePath,
	// mmaemiliaFolderPath);
	// }

	public void applyTransformation() {
		String mmaemiliaFilePath = mmaemiliaFolderPath + getName() + metamodelManager.getMetamodelFileExtension();
		Transformation.GenerateAEMTransformation(mmaemiliaFilePath, mmaemiliaFolderPath);
	}

	/**
	 * Starting from the solution family each refactoring action should be applied
	 * in order to obtain a refactoring model on which a performance evaluation can
	 * be executed
	 * 
	 * @param metamodelManager
	 */
	public synchronized void executeRefactoring(AemiliaManager metamodelManager) {
		Refactoring ref = this.getVariableValue(0).getRefactoring();

		for (RefactoringAction a : ref.getActions()) {
			assert (a.getModel() != null);
			assert (a.getModel().equals(this.getModel()));

			if (a instanceof AEmiliaConstChangesRefactoringAction) {
				ConstInit sourceCost = ((AEmiliaConstChangesRefactoringAction) a).getSourceConstInit();
				assert (sourceCost != null);
				((AEmiliaConstChangesRefactoringAction) a).generateFactorOfChange();
			}
			try {
				assert (this.getModel().equals(a.getModel()));
				assert (this.getModel().equals(this.getVariableValue(0).getModel()));
				assert (this.getVariableValue(0).getModel().equals(a.getModel()));
				a.execute();
				this.setRefactored(true);
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
		metamodelManager.save(this);
		// Controller.logger_.info("Model of Solution #" + this.getName() + " has been
		// SAVED!");
	}

	public void executeRefactoring() {
		Refactoring ref = this.getVariableValue(0).getRefactoring();

		for (RefactoringAction a : ref.getActions()) {
			assert (a.getModel() != null);
			assert (a.getModel().equals(this.getModel()));

			if (a instanceof AEmiliaConstChangesRefactoringAction) {
				ConstInit sourceCost = ((AEmiliaConstChangesRefactoringAction) a).getSourceConstInit();
				assert (sourceCost != null);
				((AEmiliaConstChangesRefactoringAction) a).generateFactorOfChange();
			}
			try {
				assert (this.getModel().equals(a.getModel()));
				assert (this.getModel().equals(this.getVariableValue(0).getModel()));
				assert (this.getVariableValue(0).getModel().equals(a.getModel()));
				a.execute();
				this.setRefactored(true);
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
		metamodelManager.save(this);
		// Controller.logger_.info("Model of Solution #" + this.getName() + " has been
		// SAVED!");
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
		// return this.getResources().get(0).getContents().get(0);

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

	public Map<String, List<ArchitecturalInteraction>> getMapOfPAs() {
		return mapOfPAs;
	}

	public void setMapOfPAs(Map<String, List<ArchitecturalInteraction>> mapOfPAs) {
		this.mapOfPAs = mapOfPAs;
	}

	public String getValPath() {
		return valFilePath;
	}

	public void setValPath(String valPath) {
		this.valFilePath = valPath;
	}

	public void refreshModel() {
		getResourceSet().getResources().get(0).unload();
		this.createNewModel(mmaemiliaFilePath);
	}

	public int getPAs() {
		// Map<String, List<ArchitecturalInteraction>> mapOfPAs =
		// this.countingPAsOnAemiliaModel(
		// controller.getPerfQuality(), controller.getRuleFilePath(), this.getValPath(),
		// metamodelManager);

		// int numOfPAs = 0;
		// for (String paName : mapOfPAs.keySet()) {
		// numOfPAs += mapOfPAs.get(paName).size();
		// }
		return numPAs;
	}

	public float getPerfQ() {
		// perfQ = evaluatePerformance();
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

	public String getMmaemiliaFolderPath() {
		return mmaemiliaFolderPath;
	}

	public String getMmaemiliaFilePath() {
		return mmaemiliaFilePath;
	}

	public void setMmaemiliaFolderPath(String mmaemiliaFolderPath) {
		this.mmaemiliaFolderPath = mmaemiliaFolderPath;
	}

	public Manager getManager() {
		return this.manager;
	}

	public Controller getController() {
		return this.controller;
	}
}
