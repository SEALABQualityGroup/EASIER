package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.lang.reflect.InvocationTargetException;

import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;

public class RefactoringActionFactory {

    public static RefactoringAction getRandomAction(UMLRSolution sol) {

        int extractedAction = JMetalRandom.getInstance().nextInt(0, 3);
        switch (extractedAction) {
            case 0:
                return new UMLCloneNode(sol.getModelPath().toString(),sol.getAvailableElements());
            case 1:
                return new UMLMvComponentToNN(sol.getModelPath().toString(),sol.getAvailableElements());
            case 2:
                return new UMLMvOperationToNCToNN(sol.getModelPath().toString(),sol.getAvailableElements());
            case 3:
                return new UMLMvOperationToComp(sol.getModelPath().toString(),sol.getAvailableElements());
            default:
                return null;
        }

/*		try {
			return (RefactoringAction) supportedRefactoringActions[JMetalRandom.getInstance().nextInt(0,
					supportedRefactoringActions.length - 1)].getDeclaredConstructor(UMLRSolution.class).newInstance(sol);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.err.println("Error in getRandomRefactoringAction.");
			e.printStackTrace();
		}
		return null;*/
    }

}
