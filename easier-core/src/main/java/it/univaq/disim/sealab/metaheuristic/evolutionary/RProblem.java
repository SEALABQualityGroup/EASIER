package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.nio.file.Path;
import java.rmi.UnexpectedException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.ParserException;
import org.uma.jmetal.problem.impl.AbstractGenericProblem;

import it.univaq.disim.sealab.metaheuristic.managers.Manager;

@SuppressWarnings("serial")
public class RProblem extends AbstractGenericProblem<RSolution> {

	protected final EObject model;
	protected int length_of_refactorings;
	protected int number_of_actions;
	protected int allowed_failures;
	protected final int NUM_VAR = 1;
	protected final int NUM_OBJ = 3;
	protected final int VARIABLE_INDEX = 0;
	protected final int NUM_CON = 0;
	protected final int FIRST_OBJ = 0;
	protected final int SECOND_OBJ = 1;
	protected final int THIRD_OBJ = 2;
	
	private Manager manager;
	private Controller controller;
	
	private Path sourceFolderPath; 
	private Path sourceValPath;
	private Path sourceModelPath;
	
	private int maxCloning = 3;
	private double cloningWeight = 1.5;

	public RProblem(Path srcFolderPath, int desired_length, int length, int allowedFailures, int populationSize, Controller ctrl) {

		controller = ctrl;

		this.setNumberOfObjectives(NUM_OBJ);
		this.setNumberOfConstraints(NUM_CON);
		this.setNumberOfVariables(NUM_VAR);

		this.manager = controller.getManager();
//		this.manager.getMetamodelManager().init(modelUri);
//		this.manager.getMetamodelManager().setModelUri(modelUri);
		
		this.sourceFolderPath = srcFolderPath;
		this.sourceValPath = srcFolderPath.resolve("model.val");
		this.sourceModelPath = srcFolderPath.resolve("model.mmaemilia");
		
		this.model = EcoreUtil.copy(this.manager.getMetamodelManager().getModel(sourceModelPath));
		
		assert (!this.model.equals(manager.getMetamodelManager().getModel()));
		
		this.length_of_refactorings = desired_length;
		this.allowed_failures = allowedFailures;
		this.number_of_actions = length;
	
	}

	public EObject getModel() {
		return model;
	}

	public int getLength_of_refactorings() {
		return length_of_refactorings;
	}

	public void setLength_of_refactorings(int length_of_refactorings) {
		this.length_of_refactorings = length_of_refactorings;
	}
	
	public Path getSourceValPath() { return sourceValPath; }

	@Override
	/**
	 * The third objective is related to performance evaluation. In this case
	 * 2towers solver is invoked in order to solve the refactoring model. Actually,
	 * the number of Performance Antipatterns (PAs) in the model has been used as
	 * objective for the fitness function.
	 * 
	 */
	public void evaluate(RSolution solution) {

		for (int i = 0; i < this.getNumberOfObjectives(); i++) {
			if (i == FIRST_OBJ) {
				float quality = solution.getPerfQ();
				solution.getVariableValue(VARIABLE_INDEX).setPerfQuality(quality);
				solution.setObjective(i, solution.getVariableValue(VARIABLE_INDEX).getPerfQuality());
			} else if (i == SECOND_OBJ) {
				solution.getVariableValue(VARIABLE_INDEX).setNumOfChanges(solution.getNumOfChanges());
				solution.setObjective(i, solution.getVariableValue(VARIABLE_INDEX).getRefactoring().getNumOfChanges());
			} else if (i == THIRD_OBJ) {
				AEmiliaController.logger_
						.info("SOLUTION #" + solution.getName() + ": Total number of PAs --> " + solution.getPAs());
				solution.getVariableValue(VARIABLE_INDEX).setNumOfPAs(solution.getPAs());
				solution.setObjective(i, solution.getVariableValue(VARIABLE_INDEX).getNumOfPAs());
			} else {
				System.out.println("\n" + i);
				throw new RuntimeException("unexpected behaviour!!!");
			}
		}
	}
	
	public void setName(String n) {
		super.setName(n);
	}

	@Override
	public RSolution createSolution() {

		try {
			return new RSolution(this);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnexpectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

//	public void resetModel() {
//		manager.getMetamodelManager().unloadModelResource();
//
//		manager.getMetamodelManager().init(manager.getMetamodelManager().getModelUri());
//	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Controller getController() {
		return controller;
	}
	
//	@Deprecated
//	public RProblem(String modelUri, int desired_length, int n, int a, int population, Controller ctrl) {
//
//		controller = ctrl;
//
////		String name = "P_" + controller.getProperties().getProperty("populationSize") + "_E_"
////				+ controller.getProperties().getProperty("maxEvaluations") + "_X_"
////				+ controller.getProperties().getProperty("p_crossover") + "_M_"
////				+ controller.getProperties().getProperty("p_mutation");
//
////		this.setName(name);
//		this.setNumberOfObjectives(NUM_OBJ);
//		this.setNumberOfConstraints(NUM_CON);
//		this.setNumberOfVariables(NUM_VAR);
//
//		this.manager = controller.getManager();
//		this.manager.getMetamodelManager().init(modelUri);
//		this.manager.getMetamodelManager().setModelUri(modelUri);
//		this.model = EcoreUtil.copy(this.manager.getMetamodelManager().getModel());
//		assert (!this.model.equals(manager.getMetamodelManager().getModel()));
//		this.length_of_refactorings = desired_length;
//		this.allowed_failures = a;
//
//		this.number_of_actions = n;
//	
//	}

	public Path getSourceModelPath() {
		return sourceFolderPath.resolve("model.mmaemilia");
	}
	
	public Path getSourceRewFilePath() { return sourceFolderPath.resolve("model.rew"); }

	public void setSourceModelFolderPath(Path sourceModelPath) {
		this.sourceFolderPath = sourceModelPath;
	}
	
	public int getMaxCloning() { return maxCloning; }
	public RProblem setMaxCloning(final int mc) { maxCloning=mc; return this;}
	public RProblem setCloningWeight(final double cw) { cloningWeight=cw; return this;}
	
	public int getCloningWeight() { return maxCloning; }
	
	@Override
	public String toString() { return this.getName(); }
}
