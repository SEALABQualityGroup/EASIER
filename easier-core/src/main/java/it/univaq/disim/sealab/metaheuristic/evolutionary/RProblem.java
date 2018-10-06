package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.ParserException;
import org.uma.jmetal.problem.impl.AbstractGenericProblem;

import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import metamodel.mmaemilia.ArchitecturalInteraction;

@SuppressWarnings("serial")
public class RProblem extends AbstractGenericProblem<RSolution> {

	// con final non funziona
	protected final EObject model;
	protected int length_of_refactorings;
	protected int number_of_actions;
	protected int allowed_failures;
	private Manager manager;
	private Controller controller;

	protected final int NUM_VAR = 1;
	protected final int NUM_OBJ = 3;
	protected final int VARIABLE_INDEX = 0;
	protected final int NUM_CON = 0;
	protected final int FIRST_OBJ = 0;
	protected final int SECOND_OBJ = 1;
	protected final int THIRD_OBJ = 2;

	private static int PROBLEM_COUNTER = 0;

	public RProblem(String modelUri, int desired_length, int n, int a, int population, Controller ctrl) {

		controller = ctrl;

		String name = "P_" + controller.getProperties().getProperty("populationSize") + "_E_"
				+ controller.getProperties().getProperty("maxEvaluations") + "_X_"
				+ controller.getProperties().getProperty("p_crossover") + "_M_"
				+ controller.getProperties().getProperty("p_mutation");

		this.setName(name);
		this.setNumberOfObjectives(NUM_OBJ);
		this.setNumberOfConstraints(NUM_CON);
		this.setNumberOfVariables(NUM_VAR);

		this.manager = controller.getManager();
		this.manager.getMetamodelManager().init(modelUri);
		this.manager.getMetamodelManager().setModelUri(modelUri);
		this.model = EcoreUtil.copy(this.manager.getMetamodelManager().getModel());
		assert (!this.model.equals(manager.getMetamodelManager().getModel()));
		this.length_of_refactorings = desired_length;
		this.allowed_failures = a;

		this.number_of_actions = n;

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

	@Override
	/**
	 * The third objective is related to performance evaluation. In this case
	 * 2towers solver is invoked in order to solve the refactoring model. Actually,
	 * the number of Performance Antipatterns (PAs) in the model has been used as
	 * objective for the fitness function.
	 * 
	 */
	public void evaluate(RSolution solution) {
//		AemiliaManager metamodelManager = (AemiliaManager) manager.getMetamodelManager();
		
//		solution.resolve(metamodelManager);

		for (int i = 0; i < this.getNumberOfObjectives(); i++) {
			if (i == FIRST_OBJ) {
				float quality = solution.getPerfQ();
				solution.getVariableValue(VARIABLE_INDEX).setPerfQuality(quality);
				solution.setObjective(i, -1 * solution.getVariableValue(VARIABLE_INDEX).getPerfQuality());
			} else if (i == SECOND_OBJ) {
				solution.getVariableValue(VARIABLE_INDEX).setNumOfChanges(solution.getNumOfChanges());
				solution.setObjective(i, solution.getVariableValue(VARIABLE_INDEX).getRefactoring().getNumOfChanges());
			} else if (i == THIRD_OBJ) {
				Controller.logger_
						.info("SOLUTION #" + solution.getName() + ": Total number of PAs --> " + solution.getPAs());
				solution.getVariableValue(VARIABLE_INDEX).setNumOfPAs(solution.getPAs());
				solution.setObjective(i, solution.getVariableValue(VARIABLE_INDEX).getNumOfPAs());
			} else {
				System.out.println("\n" + i);
				throw new RuntimeException("unexpected behaviour!!!");
			}
		}
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

	public void resetModel() {
		manager.getMetamodelManager().unloadModelResource();

		manager.getMetamodelManager().init(manager.getMetamodelManager().getModelUri());
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Controller getController() {
		return controller;
	}
}
