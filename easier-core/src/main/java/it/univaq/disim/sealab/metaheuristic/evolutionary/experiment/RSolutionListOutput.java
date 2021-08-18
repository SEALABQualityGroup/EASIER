package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.fileoutput.FileOutputContext;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public class RSolutionListOutput {

	private FileOutputContext varFileContext;
	private FileOutputContext funFileContext;
	private String varFileName = "VAR";
	private String funFileName = "FUN";
	private List<? extends RSolution<?>> solutionList;
	private List<Boolean> isObjectiveToBeMinimized;

	public RSolutionListOutput(List<? extends RSolution<?>> solutionList) {
		varFileContext = new DefaultFileOutputContext(varFileName);
		funFileContext = new DefaultFileOutputContext(funFileName);
		this.solutionList = solutionList;
		isObjectiveToBeMinimized = null;
	}

	public RSolutionListOutput setVarFileOutputContext(FileOutputContext fileContext) {
		varFileContext = fileContext;

		return this;
	}

	public RSolutionListOutput setFunFileOutputContext(FileOutputContext fileContext) {
		funFileContext = fileContext;

		return this;
	}

	public RSolutionListOutput setObjectiveMinimizingObjectiveList(List<Boolean> isObjectiveToBeMinimized) {
		this.isObjectiveToBeMinimized = isObjectiveToBeMinimized;

		return this;
	}

	public void print() {
		if (isObjectiveToBeMinimized == null) {
			printObjectivesToFile(funFileContext, solutionList);
		} else {
			printObjectivesToFile(funFileContext, solutionList, isObjectiveToBeMinimized);
		}
		printVariablesToFile(varFileContext, solutionList);
	}

	public void printVariablesToFile(FileOutputContext context, List<? extends Solution<?>> solutionList) {
		BufferedWriter bufferedWriter = context.getFileWriter();

		try {
			if (solutionList.size() > 0) {
				int numberOfVariables = solutionList.get(0).getNumberOfVariables();
				for (int i = 0; i < solutionList.size(); i++) {
					for (int j = 0; j < numberOfVariables - 1; j++) {
						bufferedWriter.write("" + solutionList.get(i).getVariable(j) + context.getSeparator());
					}
					bufferedWriter.write("" + solutionList.get(i).getVariable(numberOfVariables - 1));

					bufferedWriter.newLine();
				}
			}

			bufferedWriter.close();
		} catch (IOException e) {
			throw new JMetalException("Error writing data ", e);
		}
	}

	public void printObjectivesToFile(FileOutputContext context, List<? extends RSolution<?>> solutionList) {
		try (BufferedWriter bufferedWriter = context.getFileWriter()) {
			if (solutionList.size() > 0) {
				int numberOfObjectives = solutionList.get(0).getNumberOfObjectives();
				for (int i = 0; i < solutionList.size(); i++) {
					bufferedWriter.write(solutionList.get(i).getName() + context.getSeparator());
					for (int j = 0; j < numberOfObjectives - 1; j++) {
						bufferedWriter.write(solutionList.get(i).getObjective(j) + context.getSeparator());
					}
					bufferedWriter.write("" + solutionList.get(i).getObjective(numberOfObjectives - 1));
					bufferedWriter.newLine();
				}
			}
		} catch (IOException e) {
			throw new JMetalException("Error printing objectives to file: ", e);
		}
	}

	public void printObjectivesToFile(FileOutputContext context, List<? extends Solution<?>> solutionList,
			List<Boolean> minimizeObjective) {
		BufferedWriter bufferedWriter = context.getFileWriter();

		try {
			if (solutionList.size() > 0) {
				int numberOfObjectives = solutionList.get(0).getNumberOfObjectives();
				if (numberOfObjectives != minimizeObjective.size()) {
					throw new JMetalException(
							"The size of list minimizeObjective is not correct: " + minimizeObjective.size());
				}
				for (int i = 0; i < solutionList.size(); i++) {
					for (int j = 0; j < numberOfObjectives - 1; j++) {
						if (minimizeObjective.get(j)) {
							bufferedWriter.write(solutionList.get(i).getObjective(j) + context.getSeparator());
						} else {
							bufferedWriter.write(-1.0 * solutionList.get(i).getObjective(j) + context.getSeparator());
						}
					}
					bufferedWriter.write("" + -1.0 * solutionList.get(i).getObjective(numberOfObjectives - 1));

					bufferedWriter.newLine();
				}
			}

			bufferedWriter.close();
		} catch (IOException e) {
			throw new JMetalException("Error printing objecives to file: ", e);
		}
	}

	/*
	 * Wrappers for printing with default configuration
	 */
	public void printObjectivesToFile(String fileName) {
		printObjectivesToFile(new DefaultFileOutputContext(fileName), solutionList);
	}

	public void printObjectivesToFile(String fileName, String separator) {
		printObjectivesToFile(new DefaultFileOutputContext(fileName, separator), solutionList);
	}

	public void printObjectivesToFile(String fileName, List<Boolean> minimizeObjective) {
		printObjectivesToFile(new DefaultFileOutputContext(fileName), solutionList, minimizeObjective);
	}

	public void printObjectivesToFile(String fileName, List<Boolean> minimizeObjective, String separator) {
		printObjectivesToFile(new DefaultFileOutputContext(fileName, separator), solutionList, minimizeObjective);
	}

	public void printVariablesToFile(String fileName) {
		printVariablesToFile(new DefaultFileOutputContext(fileName), solutionList);
	}

	public void printVariablesToFile(String fileName, String separator) {
		printVariablesToFile(new DefaultFileOutputContext(fileName, separator), solutionList);
	}

}
