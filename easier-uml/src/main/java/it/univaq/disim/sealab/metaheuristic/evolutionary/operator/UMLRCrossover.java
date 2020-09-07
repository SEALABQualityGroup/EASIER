package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ocl.ParserException;
import org.uma.jmetal.util.JMetalException;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;

@SuppressWarnings("serial")
public class UMLRCrossover<S extends UMLRSolution> extends RCrossover<S> {

	// private double crossoverProbability;
	// private JMetalRandom randomGenerator;
	// private Controller controller;

	/** Constructor */
	public UMLRCrossover(double crossoverProbability, Controller ctrl) {
		super(crossoverProbability, ctrl);
	}

	@Override
	public List<S> execute(List<S> solutions) {
		if (solutions == null) {
			throw new JMetalException("Null parameter");
		} else if (solutions.size() != 2) {
			throw new JMetalException("There must be two parents instead of " + solutions.size());
		}

		return doCrossover(crossoverProbability, solutions.get(0), solutions.get(1));
	}

	/**
	 * Perform the crossover operation.
	 *
	 * @param probability Crossover setProbability
	 * @param parent1     The first parent
	 * @param parent2     The second parent
	 * @return An array containing the two offspring
	 * @throws ParserException
	 */
	public List<S> doCrossover(double probability, UMLRSolution parent1, UMLRSolution parent2) {

		List<S> offspring = new ArrayList<>(2);
		S parent1copy = (S) parent1.copy();
		offspring.add(parent1copy);

		S parent2copy = (S) parent2.copy();
		offspring.add(parent2copy);

		if (randomGenerator.nextDouble() < probability) {
			// 1. Get the total number of bits
			int length = parent1.getLength();

			// 2. Calculate the point to make the crossover
			int crossoverPoint;
			crossoverPoint = randomGenerator.nextInt(1, length - 1);

			// 3. The variable containing the sequence

			int variable = 0;
			/*
			 * int bitsAccount = parent1.getVariableValue(variable).getBinarySetLength();
			 * 
			 * while (bitsAccount < (crossoverPoint + 1)) { variable++; bitsAccount +=
			 * parent1.getVariableValue(variable).getBinarySetLength() ; }
			 */
			// 4. Compute the crossover point

			// 5. Apply the crossover to the variable;

			UMLRSolution offspring1 = new UMLRSolution(parent1, parent2, crossoverPoint, true);
			offspring1.setParents(parent1, parent2);

			UMLRSolution offspring2 = new UMLRSolution(parent1, parent2, crossoverPoint, false);
			offspring2.setParents(parent2, parent1);

			if (offspring1.getVariableValue(0).isFeasible() && offspring2.getVariableValue(0).isFeasible()) {
				if (isApplicable(offspring1)) {
					offspring.set(0, (S) offspring1);
				}

				if (isApplicable(offspring2)) {
					offspring.set(1, (S) offspring1);
				}
			}

			/*
			 * if (offspring1.getVariableValue(0).isFeasible()) { int i = 0; boolean found =
			 * false; while (i < offspring1.getLength() && !found) { RefactoringAction a =
			 * offspring1.getActionAt(i); if (a instanceof AEmiliaCloneAEIRefactoringAction)
			 * { MetamodelManager metamodelManager =
			 * controller.getManager().getMetamodelManager(); if
			 * (!metamodelManager.isApplicable(((AEmiliaCloneAEIRefactoringAction) a),
			 * offspring1.getVariableValue(0))) found = true; } i++; } if (!found)
			 * offspring.set(0, (S) offspring1); }
			 * 
			 * if (offspring2.getVariableValue(0).isFeasible()) { int i = 0; boolean found =
			 * false; while (i < offspring2.getLength() && !found) { RefactoringAction a =
			 * offspring2.getActionAt(i); if (a instanceof AEmiliaCloneAEIRefactoringAction)
			 * { UMLMetamodelManager metamodelManager = (UMLMetamodelManager)
			 * controller.getManager() .getMetamodelManager(); if
			 * (!metamodelManager.isApplicable(((RefactoringAction) a),
			 * offspring2.getVariableValue(0))) found = true; }
			 * 
			 * i++; } if (!found) offspring.set(1, (S) offspring2); }
			 */

		}

		if (!offspring.get(0).equals(parent1copy)) {
//			offspring.get(0).setCrossovered(false);
//		else {
			offspring.get(0).setCrossovered();
			UMLRSolution.XOverCounter++;
		}

		if (!offspring.get(1).equals(parent2copy)) {
//			offspring.get(1).setCrossovered(false);
//		else {
			offspring.get(1).setCrossovered();
			UMLRSolution.XOverCounter++;
		}

		return offspring;
	}

	/**
	 * Two parents are required to apply this operator.
	 * 
	 * @return
	 */
	public int getNumberOfParents() {
		return 2;
	}
	
	
	//TODO set specific condition to verify whether the refactoring sequence is applicable or not after it has been crossoveredn
	private boolean isApplicable(RSolution solution) {
//		for (int i = 0; i < solution.getLength(); i++) {
//			RefactoringAction a = solution.getActionAt(i);
//			if (a instanceof AEmiliaCloneAEIRefactoringAction) {
//				MetamodelManager metamodelManager = controller.getManager().getMetamodelManager();
//				if (!metamodelManager.isApplicable(a, solution.getVariableValue(0)))
//					return true;
//			}
//		}
		return true;
	}

}
