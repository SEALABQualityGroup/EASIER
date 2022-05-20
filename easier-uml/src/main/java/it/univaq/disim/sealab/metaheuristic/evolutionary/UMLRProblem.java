package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.nio.file.Path;

import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;

public class UMLRProblem<S extends RSolution<?>> extends RProblem<S> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UMLRProblem(Path srcModelPath, String name) {
		super(srcModelPath);
		this.setName(name);
	}

//	public UMLRProblem(SourceModel srcModel, int desired_length, int allowedFailures, int populationSize) {
//		super(srcModel.getSourceFolder(), srcModel.getModel(), desired_length, allowedFailures, populationSize);
//
//	}

	@Override
	public S createSolution() {
		UMLRSolution sol = new UMLRSolution(sourceModelPath, getName());
		sol.createRandomRefactoring();
//		return (S) new UMLRSolution(sourceModelPath, getName());
		return (S) sol;
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

		solution.setObjective(0, (-1 * solution.getPerfQ())); // to be maximized
		solution.setObjective(1, solution.getArchitecturalChanges());
		if(Configurator.eINSTANCE.getProbPas() != 0) {
			solution.setObjective(2, solution.getPAs());
			solution.setObjective(3, (-1 * solution.getReliability())); // to be maximized
		}else {
			solution.setObjective(2, (-1 * solution.getReliability())); // to be maximized
		}

		EasierLogger.logger_.info(String.format("Objectives of Solution # %s have been set.", solution.getName()));

	}
}
