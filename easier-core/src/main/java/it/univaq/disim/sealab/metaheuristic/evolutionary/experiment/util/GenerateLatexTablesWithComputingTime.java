// TODO COMMENTS

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.

/**
 * This class computes a number of statistical values (mean, median, standard
 * deviation, interquartile range) from the indicator files generated after
 * executing {@link ExecuteAlgorithms} and {@link ComputeQualityIndicators}.
 * After reading the data files and calculating the values, a Latex file is
 * created containing an script that generates tables with the best and second
 * best values per indicator. The name of the file is
 * {@link Experiment #getExperimentName()}.tex, which is located by default in
 * the directory {@link Experiment #getExperimentBaseDirectory()}/latex
 *
 * Although the maximum, minimum, and total number of items are also computed,
 * no tables are generated with them (this is a pending work).
 *
 * @author Daniele Di Pompeo <dipompeodaniele@gmail.com> inspired by 
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.uma.jmetal.algorithm.impl.AbstractGeneticAlgorithm;
import org.uma.jmetal.lab.experiment.Experiment;
import org.uma.jmetal.lab.experiment.component.ExperimentComponent;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalLogger;

import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperiment;

public class GenerateLatexTablesWithComputingTime implements ExperimentComponent {

	private static final String DEFAULT_LATEX_DIRECTORY = "latex";

	private final RExperiment<?, ?> experiment;

	public GenerateLatexTablesWithComputingTime(Experiment<?, ?> configuration) {
		this.experiment = (RExperiment<?, ?>) configuration;
	}
	
	@Override
	public void run() throws IOException {
		generateLatexScript(this.experiment.getComputingTimes());
	}

	private void generateLatexScript(List<?> list) throws IOException {
		String latexDirectoryName = experiment.getExperimentBaseDirectory() + "/" + DEFAULT_LATEX_DIRECTORY;
		File latexOutput;
		latexOutput = new File(latexDirectoryName);
		if (!latexOutput.exists()) {
			JMetalLogger.logger.info("Creating " + latexDirectoryName + " directory");
		}
		// System.out.println("Experiment name: " + experimentName_);
		String latexFile = latexDirectoryName + "/" + experiment.getExperimentName() + "_comp_time.tex";
		printHeaderLatexCommands(latexFile);
		printData(latexFile, "Computing Time");
		printEndLatexCommands(latexFile);
		
		latexFile = latexDirectoryName + "/" + experiment.getExperimentName() + "mean_comp_time.tex";
		printHeaderLatexCommands(latexFile);
		printDataMean(latexFile, "Maen Computing Time");
		printEndLatexCommands(latexFile);
		
		
	}

	void printHeaderLatexCommands(String fileName) throws IOException {
		FileWriter os = new FileWriter(fileName, false);
		os.write("\\documentclass{article}" + "\n");
		os.write("\\title{" + experiment.getExperimentName() + "Computing Time}" + "\n");
		os.write("\\usepackage{colortbl}" + "\n");
		os.write("\\usepackage[table*]{xcolor}" + "\n");
		os.write("\\xdefinecolor{gray95}{gray}{0.65}" + "\n");
		os.write("\\xdefinecolor{gray25}{gray}{0.8}" + "\n");
		os.write("\\author{D. Di Pompeo}" + "\n");
		os.write("\\begin{document}" + "\n");
		os.write("\\maketitle" + "\n");
		os.write("\\section{Tables}" + "\n");

		os.close();
	}

	void printEndLatexCommands(String fileName) throws IOException {
		FileWriter os = new FileWriter(fileName, true);
		os.write("\\end{document}" + "\n");
		os.close();
	}
	
	private void printDataMean(String latexFile, String caption) throws IOException {
		// Generate header of the table
		FileWriter os = new FileWriter(latexFile, true);
		os.write("\n");
		os.write("\\begin{table}" + "\n");
		os.write("\\caption{" + experiment.getExperimentName() + ". " + caption + "}" + "\n");
		os.write("\\label{table: " + experiment.getExperimentName() + "}" + "\n");
		os.write("\\centering" + "\n");
		os.write("\\begin{tabular}{lll}\n");

		// calculate the number of columns
//		for (int k = 0; k < experiment.getAlgorithmList().size(); k++) {
//			os.write("l");
//		}
//		os.write("}\n");
		os.write("\\hline \n");

//		// write table head
		
		os.write("Problem & Algorithm & Computing Time \\\\ \n");
		
//		for (int i = -1; i < experiment.getAlgorithmList().size(); i++) {
//			if (i == -1) {
//				os.write(" & ");
//			} else if (i == (experiment.getAlgorithmList().size() - 1)) {
//				os.write(" " + experiment.getAlgorithmList().get(i).getAlgorithmTag().replace("_", "\\_") + "\\\\"
//						+ "\n");
//			} else {
//				os.write("" + experiment.getAlgorithmList().get(i).getAlgorithmTag().replace("_", "\\_") + " & ");
//			}
//		}
		os.write("\\hline \n");
		
		
		final int ctSize = experiment.getComputingTimes().size();
		
		for(int i=0; i < ctSize; i++) {
			String str = ((AbstractGeneticAlgorithm<Solution<?>, ?>)experiment.getComputingTimes().get(i).getKey()).getProblem().getName().replace("_", "\\_") + " & ";
			str += ((AbstractGeneticAlgorithm<Solution<?>, ?>)experiment.getComputingTimes().get(i).getKey()).getName().replace("_", "\\_") + " & ";
			str += experiment.getComputingTimes().get(i).getValue().toString();
			os.write(str + "\\\\" + "\n");
		}
		os.close();
	}

	private void printData(String latexFile, String caption) throws IOException {
		// Generate header of the table
		FileWriter os = new FileWriter(latexFile, true);
		os.write("\n");
		os.write("\\begin{table}" + "\n");
		os.write("\\caption{" + experiment.getExperimentName() + ". " + caption + "}" + "\n");
		os.write("\\label{table: " + experiment.getExperimentName() + "}" + "\n");
		os.write("\\centering" + "\n");
		os.write("\\begin{tabular}{lll}\n");

		os.write("\\hline \n");

//		// write table head
		
		os.write("Problem & Algorithm & Computing Time \\\\ \n");

		os.write("\\hline \n");
		
		
		final int ctSize = experiment.getComputingTimes().size();
		
		for(int i=0; i < ctSize; i++) {
			String str = ((AbstractGeneticAlgorithm<Solution<?>, ?>)experiment.getComputingTimes().get(i).getKey()).getProblem().getName().replace("_", "\\_") + " & ";
			str += ((AbstractGeneticAlgorithm<Solution<?>, ?>)experiment.getComputingTimes().get(i).getKey()).getName().replace("_", "\\_") + " & ";
			str += experiment.getComputingTimes().get(i).getValue().toString();
			os.write(str + "\\\\" + "\n");
		}
		


		// close table
		os.write("\\hline" + "\n");
		os.write("\\end{tabular}" + "\n");
		os.write("\\end{table}" + "\n");
		os.close();
	}


}