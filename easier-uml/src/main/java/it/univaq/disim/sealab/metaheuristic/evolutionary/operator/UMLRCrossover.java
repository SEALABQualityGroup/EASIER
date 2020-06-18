package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import java.util.List;

import org.eclipse.ocl.ParserException;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

@SuppressWarnings("serial")
public class UMLRCrossover extends RCrossover {

	// private double crossoverProbability;
	// private JMetalRandom randomGenerator;
	// private Controller controller;

	/** Constructor */
	public UMLRCrossover(double crossoverProbability, Controller ctrl) {
		super(crossoverProbability, ctrl);
	}

	/**
	 * Perform the crossover operation.
	 *
	 * @param probability
	 *            Crossover setProbability
	 * @param parent1
	 *            The first parent
	 * @param parent2
	 *            The second parent
	 * @return An array containing the two offspring
	 * @throws ParserException
	 */
	@Override
	public List<RSolution> doCrossover(double probability, RSolution parent1, RSolution parent2) {

//		List<RSolution> offspring = new ArrayList<>(2);
//		UMLRSolution parent1copy = (UMLRSolution) parent1.copy();
//		offspring.add(parent1copy);
//		assert (offspring.get(0).getModel().equals(parent1.getModel()));
//
//		UMLRSolution parent2copy = (UMLRSolution) parent2.copy();
//		offspring.add(parent2copy);
//
//		if (randomGenerator.nextDouble() < probability) {
//			// 1. Get the total number of bits
//			int length = parent1.getLength();
//
//			// 2. Calculate the point to make the crossover
//			int crossoverPoint;
//			crossoverPoint = randomGenerator.nextInt(1, length - 1);
//
//			// 3. The variable containing the sequence
//
//			int variable = 0;
//			/*
//			 * int bitsAccount = parent1.getVariableValue(variable).getBinarySetLength();
//			 * 
//			 * while (bitsAccount < (crossoverPoint + 1)) { variable++; bitsAccount +=
//			 * parent1.getVariableValue(variable).getBinarySetLength() ; }
//			 */
//			// 4. Compute the crossover point
//
//			assert (parent1.getVariableValue(variable).getLength() == parent2.getVariableValue(variable).getLength());
//
//			// 5. Apply the crossover to the variable;
//
//			assert (parent1 != null);
//			assert (parent2 != null);
//			UMLRSolution offspring1 = new UMLRSolution((UMLRSolution) parent1, (UMLRSolution) parent2,
//					crossoverPoint, true);
//			offspring1.setParents(parent1, parent2);
//
//			if (offspring1.getVariableValue(0).isFeasible()) {
//				int i = 0;
//				boolean found = false;
//				while (i < offspring1.getLength() && !found) {
//					RefactoringAction a = offspring1.getActionAt(i);
//					if (a instanceof AEmiliaCloneAEIRefactoringAction) {
//						UMLMetamodelManager metamodelManager = (UMLMetamodelManager) controller.getManager()
//								.getMetamodelManager();
//						if (!metamodelManager.isApplicable(((AEmiliaCloneAEIRefactoringAction) a),
//								offspring1.getVariableValue(0)))
//							found = true;
//					}
//					i++;
//				}
//				if (!found)
//					offspring.set(0, offspring1);
//			}
//
//			assert (offspring1.getModel().equals(offspring1.getModel()));
//
//			UMLRSolution offspring2 = new UMLRSolution((UMLRSolution) parent1, (UMLRSolution) parent2,
//					crossoverPoint, false);
//			offspring2.setParents(parent2, parent1);
//
//			if (offspring2.getVariableValue(0).isFeasible()) {
//				int i = 0;
//				boolean found = false;
//				while (i < offspring2.getLength() && !found) {
//					RefactoringAction a = offspring2.getActionAt(i);
//					if (a instanceof AEmiliaCloneAEIRefactoringAction) {
//						UMLMetamodelManager metamodelManager = (UMLMetamodelManager) controller.getManager()
//								.getMetamodelManager();
//						if (!metamodelManager.isApplicable(((AEmiliaCloneAEIRefactoringAction) a),
//								offspring2.getVariableValue(0)))
//							found = true;
//					}
//
//					i++;
//				}
//				if (!found)
//					offspring.set(1, offspring2);
//			}
//
//			assert (offspring.size() == 2);
//
//		}
//
//		if (offspring.get(0).equals(parent1copy))
//			offspring.get(0).setCrossovered(false);
//		else {
//			offspring.get(0).setCrossovered(true);
//			UMLRSolution.XOverCounter++;
//		}
//
//		if (offspring.get(1).equals(parent2copy))
//			offspring.get(1).setCrossovered(false);
//		else {
//			offspring.get(1).setCrossovered(true);
//			UMLRSolution.XOverCounter++;
//		}
//
//		if (!offspring.get(0).isCrossovered() && !offspring.get(1).isCrossovered())
//			EasierLogger.logger_.info("Crossover left solution unchanged");
//		else {
//			EasierLogger.logger_.info("Crossover is done");
//		}
//
//		return offspring;
		//TODO
		return null;
	}

	/**
	 * Two parents are required to apply this operator.
	 * 
	 * @return
	 */
	public int getNumberOfParents() {
		return 2;
	}

}
