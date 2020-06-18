package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.nio.file.Path;

import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;

public class AemiliaRProblem<S extends AemiliaRSolution> extends RProblem<S> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Path sourceValPath;

	public AemiliaRProblem(Path srcFolderPath, int desired_length, int length, int allowedFailures, int populationSize,
			Controller ctrl) {
		super(srcFolderPath, srcFolderPath.resolve("model.mmaemilia"), desired_length, length, allowedFailures,
				populationSize, ctrl);

		this.sourceValPath = srcFolderPath.resolve("model.val");
		this.sourceModelPath = srcFolderPath.resolve("model.mmaemilia");
	}

	public Path getSourceValPath() {
		return sourceValPath;
	}

	@SuppressWarnings("unchecked")
	@Override
	public S createSolution() {
		
		AemiliaRSolution sol= null;
		
//		try {
		do{
			sol = new AemiliaRSolution((RProblem<AemiliaRSolution>) this);
		}while(sol == null);
		
//		} catch (ParserException e) {
//			e.printStackTrace();
//		} catch (UnexpectedException e) {
//			e.printStackTrace();
//		}
		return (S) sol;
	}

	public Path getSourceModelPath() {
		return sourceFolderPath.resolve("model.mmaemilia");
	}

	public Path getSourceRewFilePath() {
		return sourceFolderPath.resolve("model.rew");
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

		AemiliaRSolution solution = (AemiliaRSolution) s;

		for (int i = 0; i < this.getNumberOfObjectives(); i++) {
			// If worsen is enabled, perfQ should be the only objective
			if (i == FIRST_OBJ) {
				EasierLogger.logger_
						.info("SOLUTION #" + solution.getName() + ": PerfQ --> " + solution.getPerfQ());
				//if is worse perfQ must not be changed 
				//if not it must be multiply by -1 to maximize the perfQ
				float quality = (getController().getConfigurator().isWorsen()) ? solution.getPerfQ()
						: (-1 * solution.getPerfQ());
				solution.getVariableValue(VARIABLE_INDEX).setPerfQuality(quality);
				solution.setObjective(i, quality);
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
