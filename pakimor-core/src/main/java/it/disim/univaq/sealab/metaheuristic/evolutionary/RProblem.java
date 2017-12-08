package it.disim.univaq.sealab.metaheuristic.evolutionary;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.ParserException;
import org.uma.jmetal.problem.impl.AbstractGenericProblem;

import it.disim.univaq.sealab.metaheuristic.managers.Manager;
import it.disim.univaq.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import metamodel.mmaemilia.ArchitecturalInteraction;

@SuppressWarnings("serial")
public class RProblem extends AbstractGenericProblem<RSolution> {

	// con final non funziona
	protected final EObject model;
	protected int length_of_refactorings;
	protected int number_of_actions;
	protected int allowed_failures;
	private Manager manager;

	protected final int NUM_VAR = 1;
	protected final int NUM_OBJ = 3;
	protected final int VARIABLE_INDEX = 0;
	protected final int NUM_CON = 0;
	protected final int FIRST_OBJ = 0;
	protected final int SECOND_OBJ = 1;
	protected final int THIRD_OBJ = 2;

	private static int PROBLEM_COUNTER = 0;

	public RProblem(String modelUri, int desired_length, int n, int a, int population, Manager manager) {

		Controller controller = manager.getController();

		String name = "P_" + controller.getProperties().getProperty("populationSize")
				+ "_E_" +controller.getProperties().getProperty("maxEvaluations") +
				"_X_" + controller.getProperties().getProperty("p_crossover") +
				"_M_"+ controller.getProperties().getProperty("p_mutation") ;

		this.setName(name);
		this.setNumberOfObjectives(NUM_OBJ);
		this.setNumberOfConstraints(NUM_CON);
		this.setNumberOfVariables(NUM_VAR);

		this.manager = manager;
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

	// public void setModel(Object model) {
	// this.model = model;
	// }

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

		Manager manager = Manager.getInstance(null);
		AemiliaManager metamodelManager = (AemiliaManager) manager.getMetamodelManager();
		Controller controller = manager.getController();

		// EObject solutionModel = metamodelManager.getModel();
		// solution.setModel(this.model);
		solution.resolve(metamodelManager);

		for (int i = 0; i < this.getNumberOfObjectives(); i++) {
			if (i == FIRST_OBJ) {
				// solution.getVariableValue(VARIABLE_INDEX).getRefactoring()
				// .setCost(Manager.calculateCost(solution.getVariableValue(VARIABLE_INDEX).getRefactoring()));
				// solution.setObjective(i,
				// solution.getVariableValue(VARIABLE_INDEX).getRefactoring().getCost());
				float quality = solution.evaluatePerformance();
				solution.getVariableValue(VARIABLE_INDEX).setPerfQuality(quality);
				// solution.setObjective(i,
				// solution.getVariableValue(VARIABLE_INDEX).getNumOfPAs());
				solution.setObjective(i, -1 * solution.getVariableValue(VARIABLE_INDEX).getPerfQuality());

			} else if (i == SECOND_OBJ) {
				solution.getVariableValue(VARIABLE_INDEX).setNumOfChanges(
						Manager.calculateNumOfChanges(solution.getVariableValue(VARIABLE_INDEX).getRefactoring()));
				solution.setObjective(i, solution.getVariableValue(VARIABLE_INDEX).getRefactoring().getNumOfChanges());
			} else if (i == THIRD_OBJ) {
				// Manager.completeAntipatternDetection(antipatterns, umlModelUri);
				// PAKIMOR _FIXME
				// far parire TT

				Map<String, List<ArchitecturalInteraction>> mapOfPAs = solution.countingPAsOnAemiliaModel(
						controller.getPerfQuality(), controller.getRuleFilePath(),
						solution.getValPath(), metamodelManager);

				int numOfPAs = 0;
				for (String paName : mapOfPAs.keySet()) {
					numOfPAs += mapOfPAs.get(paName).size();
				}

				Controller.logger_.info("SOLUTION #" + solution.getName() + ": Total number of PAs --> " + numOfPAs);

				solution.getVariableValue(VARIABLE_INDEX).setNumOfPAs(numOfPAs);
				// solution.setObjective(i, numOfPAs);
				solution.setObjective(i, solution.getVariableValue(VARIABLE_INDEX).getNumOfPAs());

				// controller.writeToCSV(solution);
			} else {
				System.out.println("\n" + i);
				throw new RuntimeException("unexpected behav");
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

}
