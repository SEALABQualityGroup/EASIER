package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.nio.file.Path;
import java.rmi.UnexpectedException;

import org.eclipse.ocl.ParserException;
import org.uma.jmetal.solution.Solution;

import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;

public class AemiliaRProblem<S extends AemiliaRSolution> extends RProblem<S> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Path sourceValPath;
	
	public AemiliaRProblem(Path srcFolderPath, int desired_length, int length, int allowedFailures, int populationSize,
			Controller ctrl) {
		super(srcFolderPath, srcFolderPath.resolve("model.mmaemilia"), desired_length, length, allowedFailures, populationSize, ctrl);
		
		this.sourceValPath = srcFolderPath.resolve("model.val");
		this.sourceModelPath = srcFolderPath.resolve("model.mmaemilia");
	}
	
	public Path getSourceValPath() { return sourceValPath; }
	
	@Override
	public S createSolution() {

		try {
			return (S) new AemiliaRSolution(this);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnexpectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Path getSourceModelPath() {
		return sourceFolderPath.resolve("model.mmaemilia");
	}
	
	public Path getSourceRewFilePath() { return sourceFolderPath.resolve("model.rew"); }

	@Override
	/**
	 * The third objective is related to performance evaluation. In this case
	 * 2towers solver is invoked in order to solve the refactoring model. Actually,
	 * the number of Performance Antipatterns (PAs) in the model has been used as
	 * objective for the fitness function.
	 * 
	 */
	public void evaluate(S s) {
		
		AemiliaRSolution solution = (AemiliaRSolution)s;

		for (int i = 0; i < this.getNumberOfObjectives(); i++) {
			if (i == FIRST_OBJ) {
				float quality = solution.getPerfQ();
				solution.getVariableValue(VARIABLE_INDEX).setPerfQuality(quality);
				solution.setObjective(i, solution.getVariableValue(VARIABLE_INDEX).getPerfQuality());
			} else if (i == SECOND_OBJ) {
				solution.getVariableValue(VARIABLE_INDEX).setNumOfChanges(solution.getNumOfChanges());
				solution.setObjective(i, solution.getVariableValue(VARIABLE_INDEX).getRefactoring().getNumOfChanges());
			} else if (i == THIRD_OBJ) {
				EasierLogger.logger_
						.info("SOLUTION #" + solution.getName() + ": Total number of PAs --> " + solution.getPAs());
				solution.getVariableValue(VARIABLE_INDEX).setNumOfPAs(solution.getPAs());
				solution.setObjective(i, solution.getVariableValue(VARIABLE_INDEX).getNumOfPAs());
			} else {
				System.out.println("\n" + i);
				throw new RuntimeException("unexpected behaviour!!!");
			}
		}
	}
}
