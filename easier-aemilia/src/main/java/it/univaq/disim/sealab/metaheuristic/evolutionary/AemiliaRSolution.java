package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.ParserException;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.solutionattribute.impl.CrowdingDistance;

import it.univaq.disim.sealab.aemiliaMod2text.main.Transformation;
import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.aemilia.AEmiliaConstChangesRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaMetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaStringManager;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.mmaemiliaPackage;
import metamodel.mmaemilia.Headers.ConstInit;
import utils.AemiliaFileUtils;
import utils.ThresholdUtils;

@SuppressWarnings("serial")
public class AemiliaRSolution extends RSolution {

	private AemiliaRSolution[] parents = new AemiliaRSolution[2];

	private Map<String, List<ArchitecturalInteraction>> mapOfPAs;

	private AemiliaMetamodelManager metamodelManager;
	private Path mmaemiliaFilePath;
	private Path valFilePath;

	protected AemiliaRSolution(AemiliaRProblem<?> p) throws ParserException, UnexpectedException {
		super(p);
	}

	public AemiliaRSolution(AemiliaRSolution s) {
		super(s);
	}

	public AemiliaRSolution(AemiliaRSolution s1, AemiliaRSolution s2, int point, boolean left) {
		super(s1, s2, point, left);
	}

	protected void init(Controller controller) {
		this.controller = controller;
		manager = new AemiliaManager();
		manager.setController(controller);

		setResourceSet(new ResourceSetImpl());

		manager.setMetamodelManager(new AemiliaMetamodelManager(controller));
		manager.setOclManager(manager.getMetamodelManager().getOclManager());
		manager.setOclStringManager(OclStringManager.getInstance(new OclAemiliaStringManager()));

		metamodelManager = (AemiliaMetamodelManager) manager.getMetamodelManager();
		metamodelManager.setProblem(problem);

		folderPath = Paths.get(controller.getConfigurator().getTmpFolder().toString(),
				String.valueOf((getName() / 100)), String.valueOf(getName()));
		mmaemiliaFilePath = Paths.get(folderPath.toString(), getName() + metamodelManager.getMetamodelFileExtension());

		try {
			org.apache.commons.io.FileUtils.copyFile(((AemiliaRProblem<?>) this.problem).getSourceModelPath().toFile(),
					mmaemiliaFilePath.toFile());
			// Files.copy(this.problem.getSourceModelPath(), target);
		} catch (IOException e) {
			System.out.println("[ERROR] The problem's model copy generated an error!!!");
			e.printStackTrace();
		}

		System.out.println("Problem's model copied!!!");

		// copyModel(this.problem.getSourceModelPath(), mmaemiliaFilePath);
		createNewModel(mmaemiliaFilePath);
	}

	@Override
	public void createNewModel(Path modelFilePath) {
		Resource res = getResourceSet().getResource(Manager.string2Uri(modelFilePath.toString()), true);
		assert (res.getContents().get(0).equals((AEmiliaSpecification) EcoreUtil.getObjectByType(res.getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION)));
		this.model = (AEmiliaSpecification) EcoreUtil.getObjectByType(res.getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
	}

	protected void createRandomRefactoring(int l, int n, int a) throws UnexpectedException, ParserException {
		AemiliaRSequence seq = new AemiliaRSequence(l, n, a, this);
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

	protected void copyRefactoringVariable(RSequence variableValue, RSolution sol) {
		AemiliaRSequence newSeq = new AemiliaRSequence((AemiliaRSequence) variableValue, (AemiliaRSolution) sol);

		this.setVariableValue(VARIABLE_INDEX, newSeq);
	}

	@Override
	public Solution<RSequence> copy() {
		return new AemiliaRSolution(this);
	}

	protected AemiliaRSequence createChild(RSolution s1, RSolution s2, int point) {

		AemiliaRSequence seq = new AemiliaRSequence(this);

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
			EasierLogger.logger_.warning("POINT SIZE ERROR: " + Integer.toString(point));
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

	public void resolve(AemiliaMetamodelManager metamodelManager) {
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

		AemiliaFileUtils.simpleSolutionWriterToCSV(this);
		endingTime = Instant.now();
		EasierLogger.logger_.info("Solution #" + getName() + " solved");
	}

	public void updateThresholds() {
		ThresholdUtils.uptodateSingleValueThresholds(folderPath, mmaemiliaFilePath, valFilePath, metamodelManager,
				controller);
	}

	public void updateModel(MetamodelManager metamodelManager) {

		Path rewFilePath = ((AemiliaRProblem<?>) problem).getSourceRewFilePath();

		Path rewMappingFilePath = Paths.get(folderPath.toString(),
				((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName() + "_result"
						+ AemiliaMetamodelManager.getRewmappingFileExtension());

		((AemiliaMetamodelManager) metamodelManager).aemiliaModelUpdate(valFilePath, rewFilePath, rewMappingFilePath,
				this.mmaemiliaFilePath, this);
	}

	public void updateModel() {

		Path rewFilePath = ((AemiliaRProblem<?>) problem).getSourceRewFilePath();

		Path rewMappingFilePath = Paths.get(folderPath.toString(),
				((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName() + "_result"
						+ AemiliaMetamodelManager.getRewmappingFileExtension());

		Path valFilePath = Paths.get(folderPath.toString(),
				((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName() + "_result"
						+ ((AemiliaMetamodelManager) metamodelManager).getModelFileExtension() + ".val");

		metamodelManager.aemiliaModelUpdate(valFilePath, rewFilePath, rewMappingFilePath, this.mmaemiliaFilePath, this);
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
	public void countingPAs() {
		refreshModel();
		mapOfPAs = ((AemiliaPerformanceQualityEvaluator) controller.getPerfQuality()).performanceAntipatternEvaluator(
				this.getModel(), Paths.get(folderPath.toString(), "detectionSingleValuePA.ocl"));
		if (mapOfPAs != null && !mapOfPAs.keySet().isEmpty()) {
			numPAs++;
			for (String paName : mapOfPAs.keySet()) {
				numPAs += mapOfPAs.get(paName).size();
			}
		}
	}

	public void invokeSolver() {
		Path aemFilePath = Paths.get(folderPath.toString(),
				((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName() + "_result"
						+ ((AemiliaMetamodelManager) metamodelManager).getModelFileExtension());

		Path rewFilePath = ((AemiliaRProblem<?>) problem).getSourceRewFilePath();
		Path outputFilePath = Paths.get(folderPath.toString(),
				((AEmiliaSpecification) getModel()).getArchiTypeDecl().getAtName() + "_result");

		if (AemiliaController.isSor()) {
			metamodelManager.sorSRBMC(aemFilePath, rewFilePath, outputFilePath);
		} else {
			metamodelManager.gaussianEliminationSRBMC(aemFilePath, rewFilePath, outputFilePath);
		}

		if (!Files.exists(Paths.get(aemFilePath.toString() + ".val"))) {
			((AemiliaController) controller).checkTwoTowersOutput(outputFilePath);
		} else {
			this.valFilePath = Paths.get(aemFilePath.toString() + ".val");
		}
	}

	public float evaluatePerformance() {
		AemiliaMetamodelManager metamodelManager = (AemiliaMetamodelManager) manager.getMetamodelManager();

		// String valFilePath = mmaemiliaFolderStr + ((AEmiliaSpecification)
		// getModel()).getArchiTypeDecl().getAtName()
		// + "_result" + ((AemiliaMetamodelManager)
		// metamodelManager).getModelFileExtension() +
		// ".val";

		perfQ = 0;
		if (!Files.exists(valFilePath)) {
			EasierLogger.logger_.warning("ERROR while evaluating PerfQ of Solution #" + this.getName() + ": "
					+ valFilePath + " doesn't exist.");
			perfQ = Float.MAX_VALUE;
		} else {
			perfQ = ((AemiliaPerformanceQualityEvaluator) controller.getPerfQuality())
					.performanceQuality(((AemiliaRProblem<?>) problem).getSourceValPath(), valFilePath);

		}
		return perfQ;
	}

	/**
	 * Invoke the Transformation Class in order to generate .aem e .val files
	 * 
	 * @param metamodelManager
	 */
	// private void applyTransformation(AemiliaMetamodelManager metamodelManager) {
	// String mmaemiliaFilePath = mmaemiliaFolderPath + getName() +
	// metamodelManager.getMetamodelFileExtension();
	// Transformation.GenerateAEMTransformation(mmaemiliaFilePath,
	// mmaemiliaFolderPath);
	// }

	public void applyTransformation() {
		Transformation.GenerateAEMTransformation(this.mmaemiliaFilePath, this.folderPath);
	}

	/**
	 * Starting from the solution family each refactoring action should be applied
	 * in order to obtain a refactoring model on which a performance evaluation can
	 * be executed
	 * 
	 * @param metamodelManager
	 */
	public synchronized void executeRefactoring(MetamodelManager metamodelManager) {
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

	public void setModel(EObject model) {
		this.model = model;
	}

	public AemiliaRSolution[] getParents() {
		return parents;
	}

	public void setParents(AemiliaRSolution parent1, AemiliaRSolution parent2) {
		this.parents[0] = parent1;
		this.parents[1] = parent2;
	}

	public Map<String, List<ArchitecturalInteraction>> getMapOfPAs() {
		return mapOfPAs;
	}

	public void setMapOfPAs(Map<String, List<ArchitecturalInteraction>> mapOfPAs) {
		this.mapOfPAs = mapOfPAs;
	}

	public void refreshModel() {
		getResourceSet().getResources().get(0).unload();
		this.createNewModel(mmaemiliaFilePath);
	}

	public int getPAs() {
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
		return mmaemiliaFolderStr;
	}

	public Path getMmaemiliaFilePath() {
		return mmaemiliaFilePath;
	}

	public void setMmaemiliaFolderPath(String mmaemiliaFolderPath) {
		this.mmaemiliaFolderStr = mmaemiliaFolderPath;
	}

	public Manager getManager() {
		return this.manager;
	}

	public Controller getController() {
		return this.controller;
	}

}
