package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.lang.reflect.InvocationTargetException;

import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSequence;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public class RefactoringActionFactory {

	//TODO check if it can be improved
	private static Class<?>[] supportedRefactoringActions = {UMLMvOperationToNCToNN.class};//, UMLMvComponentToNN.class};

	public static RefactoringAction getRandomAction(RSolution sol) {

		try {
			return (RefactoringAction) supportedRefactoringActions[JMetalRandom.getInstance().nextInt(0,
					supportedRefactoringActions.length - 1)].getDeclaredConstructor(RSolution.class).newInstance(sol);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.err.println("Error in getRandomRefactoringAction.");
			e.printStackTrace();
		}
		return null;
	}

}
