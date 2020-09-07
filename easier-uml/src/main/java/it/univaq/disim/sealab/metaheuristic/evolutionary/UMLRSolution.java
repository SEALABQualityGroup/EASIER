package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.ocl.ParserException;
import org.eclipse.uml2.uml.UMLPackage;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.solutionattribute.impl.CrowdingDistance;

import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.etl.ETLStandalone;
import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;

@SuppressWarnings("serial")
public class UMLRSolution extends RSolution {

//	private UMLRSolution[] parents = new UMLRSolution[2];

	private UMLMetamodelManager metamodelManager;
//	private Path modelPath;

//	private int name;
//	private static int SOLUTION_COUNTER = 0;
	private UUID ID;
	private final int VARIABLE_INDEX = 0;

//	private Manager manager;
//	private Controller controller;

	private Path folderPath;
//	private ResourceSet resourceSet;
//	private EObject model;
	
	private IModel iModel;

	protected UMLRSolution(UMLRProblem<?> p) throws ParserException, UnexpectedException {
		super(p);
		setName();
		ID = UUID.randomUUID();
		resetParents();
		init(p.getController());
//		crossovered = false;
//		mutated = false;
//		refactored = false;

		try {
			this.createRandomRefactoring(p.length_of_refactorings, p.number_of_actions, p.allowed_failures);
		} catch (UnexpectedException e) {
			System.err.println("Error in genereting a refactoring sequence of a new Solution with:");
			System.err.println("lenght --> " + p.length_of_refactorings + "Number of action -->" + p.number_of_actions
					+ "Allowed failures -->" + p.allowed_failures);
			e.printStackTrace();
		}
	}

	public UMLRSolution(UMLRSolution s) {
		super(s.getProblem());
		setName();
		ID = UUID.randomUUID();

		resetParents();
		init(s.problem.getController());

		this.copyRefactoringVariable(s.getVariableValue(VARIABLE_INDEX), this);

		for (int i = 0; i < s.problem.getNumberOfObjectives(); i++) {
			this.setObjective(i, s.getObjective(i));
		}

		this.attributes = s.attributes;
		this.setAttribute(CrowdingDistance.class, s.getAttribute(CrowdingDistance.class));

//		crossovered = false;
//		mutated = false;
//		refactored = false;
	}

	public UMLRSolution(UMLRSolution s1, UMLRSolution s2, int point, boolean left) {
		super(s1.getProblem());

		setName();
		ID = UUID.randomUUID();

		init(s1.problem.getController());

		for (int i = 0; i < s1.problem.getNumberOfVariables(); i++) {
			if (i == VARIABLE_INDEX) {
				if (left) {
					this.setVariableValue(VARIABLE_INDEX, this.createChild(s1, s2, point));
				} else {
					this.setVariableValue(VARIABLE_INDEX, this.createChild(s2, s1, point));

				}
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

//		crossovered = false;
//		mutated = false;
//		refactored = false;
	}

//	protected void resetParents() {
//		if (this.parents != null) {
//			this.parents[0] = null;
//			this.parents[1] = null;
//		}
//	}

	protected void init(Controller controller) {
		this.controller = controller;
		manager = new UMLManager();
		manager.setController(controller);

		parents = new UMLRSolution[2];

		setResourceSet(new ResourceSetImpl());

		manager.setMetamodelManager(new UMLMetamodelManager(controller));
		manager.setOclManager(manager.getMetamodelManager().getOclManager());
		manager.setOclStringManager(UMLOclStringManager.getInstance());

		metamodelManager = (UMLMetamodelManager) manager.getMetamodelManager();
		metamodelManager.setProblem(problem);

		folderPath = Paths.get(controller.getConfigurator().getTmpFolder().toString(),
				String.valueOf((getName() / 100)), String.valueOf(getName()));
		modelPath = Paths.get(folderPath.toString(), getName() + metamodelManager.getMetamodelFileExtension());

		try {
			org.apache.commons.io.FileUtils.copyDirectory(this.problem.getSourceModelPath().getParent().toFile(), modelPath.getParent().toFile()); 
			org.apache.commons.io.FileUtils.copyFile(this.problem.getSourceModelPath().toFile(), modelPath.toFile());
			
			try {
				iModel = EOLStandalone.createUmlModel("UML", modelPath, UMLPackage.eNS_URI, true, true);
			} catch (EolModelLoadingException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Files.copy(this.problem.getSourceModelPath(), target);
		} catch (IOException e) {
			System.out.println("[ERROR] The problem's model copy generated an error!!!");
			e.printStackTrace();
		}

		// copyModel(this.problem.getSourceModelPath(), mmaemiliaFilePath);
		createNewModel(modelPath);
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public void createNewModel(Path modelFilePath) {
		try {
//			res = getResourceSet().getResource(URI.createFileURI(modelFilePath.toString()), true);
//			this.model = (AEmiliaSpecification) EcoreUtil.getObjectByType(res.getContents(),
//					mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
			this.model = metamodelManager.getModel(modelFilePath);
		} catch (Exception e) {
			System.err.println("Error in creating the model for Solution #" + this.getName());
			System.err.println(this.getVariableValue(0).toString());
			e.printStackTrace();
		}
	}

	protected void createRandomRefactoring(int l, int n, int a) throws UnexpectedException, ParserException {
		UMLRSequence seq = new UMLRSequence(l, n, a, this);
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

	public RProblem<UMLRSolution> getProblem() {
		return (RProblem<UMLRSolution>) this.problem;
	}

	protected void copyRefactoringVariable(RSequence variableValue, RSolution sol) {
		UMLRSequence newSeq = new UMLRSequence((UMLRSequence) variableValue, (UMLRSolution) sol);

		this.setVariableValue(VARIABLE_INDEX, newSeq);
	}

	@Override
	public Solution<RSequence> copy() {
		return new UMLRSolution(this);
	}

	protected UMLRSequence createChild(UMLRSolution s1, UMLRSolution s2, int point) {

		UMLRSequence seq = new UMLRSequence(this);

		try {
			for (int j = 0; j < point; j++) {
				RefactoringAction _new = s1.getActionAt(j).clone(this);
				// _new.setSolution(this);
				seq.insert(j, _new);
			}
			for (int j = point; j < s2.getLength(); j++) {
				RefactoringAction _new = s2.getActionAt(j).clone(this);
				// _new.setSolution(this);
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

//	@Deprecated
//	public void resolve(UMLMetamodelManager metamodelManager) {
//		EasierLogger.logger_.info("Solving Solution #" + getName());
//		startingTime = Instant.now();
//		executeRefactoring();
//
//		// applyTransformation();
//		invokeSolver();
//
//		// controller.awaitExecutor();
//		updateModel();
//
//		updateThresholds();
//
//		// countingPAsOnUMLModel(controller.getPerfQuality(),
//		// controller.getRuleFilePath(), this.getValPath(),
//		// metamodelManager);
//		countingPAs();
//
//		perfQ = evaluatePerformance();
//
//		UMLFileUtils.simpleSolutionWriterToCSV(this);
//		endingTime = Instant.now();
//		EasierLogger.logger_.info("Solution #" + getName() + " solved");
//		// TODO
//	}

	@Deprecated
	public void updateThresholds() {
		// TODO thersholds will be automatically calculate by the EVL engine.
	}

	public void updateModel() {
		// TODO understand if it needed
	}

	/**
	 * This method counts the number of Performance Antipatterns (PAs)
	 * 
	 */
	public void countingPAs() {
		// TODO invoke EVL module to calculate PAs
	}

	public void invokeSolver() {
		// TODO invoke LQN solver
	}

	public float evaluatePerformance() {
		// TODO decide how to calculate perfQ, if it needed
		return -1;
	}

	public void applyTransformation() {
		// TODO Link to Vincenzo's engine
		try {
//			new ETLStandalone().setModel(this.getModelPath()).execute();
			new ETLStandalone(this.modelPath.getParent()).setModel(this.iModel).execute();
//			metamodelManager.save(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void executeRefactoring() {
		Refactoring ref = this.getVariableValue(0).getRefactoring();

		for (RefactoringAction a : ref.getActions()) {

			try {
				a.execute();
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
		this.setRefactored();
//		metamodelManager.save(this);
		// Controller.logger_.info("Model of Solution #" + this.getName() + " has been
		// SAVED!");
	}

	public void setModel(EObject model) {
		this.model = model;
	}

//	public UMLRSolution[] getParents() {
//		return parents;
//	}

//	public void setParents(UMLRSolution parent1, UMLRSolution parent2) {
//		this.parents[0] = parent1;
//		this.parents[1] = parent2;
//	}

	public void refreshModel() {
		getResourceSet().getResources().forEach(resource -> resource.unload());
//		get(0).unload();
//		this.createNewModel(mmaemiliaFilePath);
		this.model = metamodelManager.getModel(modelPath);
	}

//	public ResourceSet getResourceSet() {
//		return resourceSet;
//	}

//	public Manager getManager() {
//		return this.manager;
//	}
//
//	public Controller getController() {
//		return this.controller;
//	}
//
//	@Override
//	public int getName() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

//	@Override
//	public EObject getModel() {
//		return model;
//	}

	@Override
	public void setParents(RSolution parent1, RSolution parent2) {
		this.parents[0] = (UMLRSolution) parent1;
		this.parents[1] = (UMLRSolution) parent2;

	}
	
	public IModel getIModel() {
		return iModel;
	}

//	@Override
//	public List<Resource> getResources() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
