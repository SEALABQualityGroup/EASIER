package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.rmi.UnexpectedException;

import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.ocl.ParserException;
import org.uma.jmetal.solution.Solution;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;

public class UMLRProblem<S extends RSolution> extends RProblem<S> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final EasierUmlModel sourceIModel;

	public UMLRProblem(Path srcFolderPath, int desired_length, int length, int allowedFailures, int populationSize,
			Controller ctrl) {
		super(srcFolderPath, srcFolderPath.resolve("model.uml"), desired_length, length, allowedFailures,
				populationSize, ctrl);

		sourceIModel = setSourceModel(srcFolderPath.resolve("automatedGuidedVehicle.uml"));

//		sourceFolderPath = srcFolderPath;
	}

	private EasierUmlModel setSourceModel(Path sourceModelPath) {
		EasierUmlModel tmpModel = null;

		try {
			tmpModel = (EasierUmlModel) EpsilonStandalone.createUmlModel("UML", sourceModelPath, null, true, false);
		} catch (EolModelLoadingException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmpModel;
	}

	public UMLRProblem(SourceModel srcModel, int desired_length, int length, int allowedFailures, int populationSize,
			Controller ctrl) {
		super(srcModel.getSourceFolder(), srcModel.getModel(), desired_length, length, allowedFailures, populationSize,
				ctrl);

		sourceIModel = setSourceModel(srcModel.getModel());
	}

	public EasierUmlModel getSourceModel() {
		return sourceIModel;
	}

	@Override
	public S createSolution() {

		try {
			UMLRSolution sol = new UMLRSolution(this);
			solutions.add(sol);
			return (S) sol;
		} catch (ParserException | UnexpectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		catch (UnexpectedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;

	}

	public Path getSourceModelPath() {
		return sourceModelPath;
//		return sourceFolderPath.resolve("model.uml");
	}

	@Override
	/**
	 * The third objective is related to performance evaluation. In this case
	 * 2towers solver is invoked in order to solve the refactoring model. Actually,
	 * the number of Performance Antipatterns (PAs) in the model has been used as
	 * objective for the fitness function.
	 * 
	 */
	public void evaluate(S s) {

		UMLRSolution solution = (UMLRSolution) s;

		for (int i = 0; i < this.getNumberOfObjectives(); i++) {
			if (i == FIRST_OBJ) {
				final double quality = solution.getPerfQ();
				solution.getVariableValue(VARIABLE_INDEX).setPerfQuality(quality);
				solution.setObjective(i, quality);
			} else if (i == SECOND_OBJ) {
				final double numOfChanges = solution.getNumOfChanges();
				solution.getVariableValue(VARIABLE_INDEX).setNumOfChanges(numOfChanges);
				solution.setObjective(i, numOfChanges);
			} else if (i == THIRD_OBJ) {
				final int pas = solution.getPAs();
				EasierLogger.logger_
						.info("SOLUTION #" + solution.getName() + ": Total number of PAs --> " + pas);
				solution.getVariableValue(VARIABLE_INDEX).setNumOfPAs(pas);
				solution.setObjective(i, pas);
			} else if (i == FOURTH_OBJ) {
				final double reliability = solution.getReliability();
				EasierLogger.logger_
						.info("SOLUTION #" + solution.getName() + ": Total reliability --> " + reliability);
				solution.getVariableValue(VARIABLE_INDEX).setReliability(reliability);
				solution.setObjective(i, reliability);
			} else {
				System.out.println("\n" + i);
				throw new RuntimeException("unexpected behaviour!!!");
			}
		}
	}
}
