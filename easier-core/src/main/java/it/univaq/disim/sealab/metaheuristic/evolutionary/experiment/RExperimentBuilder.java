package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class RExperimentBuilder<S extends Solution<?>, Result> extends ExperimentBuilder<S, Result>{

//	private final String experimentName;
//	private List<ExperimentAlgorithm<S, Result>> algorithmList;
//	private List<ExperimentProblem<S>> problemList;
//	private List<String> referenceFrontFileNames;
//	private String referenceFrontDirectory;
//	private String experimentBaseDirectory;
//	private String outputParetoFrontFileName;
//	private String outputParetoSetFileName;
//	private int independentRuns;
//
//	private List<GenericIndicator<S>> indicatorList;
//
//	private int numberOfCores;
	
	public RExperimentBuilder(String experimentName) {
		super(experimentName);
//	    this.experimentName = experimentName ;
//	    this.independentRuns = 1 ;
//	    this.numberOfCores = 1 ;
//	    this.referenceFrontFileNames = null ;
//	    this.referenceFrontDirectory = null ;
	  }

	public RExperimentBuilder<S, Result> setAlgorithmList(List<ExperimentAlgorithm<S, Result>> algorithmList) {
//		this.algorithmList = new ArrayList<>(algorithmList);
		super.setAlgorithmList(algorithmList);
		return this;
	}

	public RExperimentBuilder<S, Result> setProblemList(List<ExperimentProblem<S>> problemList) {
//		this.problemList = problemList;
		super.setProblemList(problemList);
		return this;
	}

	public RExperimentBuilder<S, Result> setExperimentBaseDirectory(String experimentBaseDirectory) {
//		this.experimentBaseDirectory = experimentBaseDirectory + "/" + experimentName;
		super.setExperimentBaseDirectory(experimentBaseDirectory);
		return this;
	}

	public RExperimentBuilder<S, Result> setReferenceFrontDirectory(String referenceFrontDirectory) {
//		this.referenceFrontDirectory = referenceFrontDirectory;
		super.setReferenceFrontDirectory(referenceFrontDirectory);
		return this;
	}

	public RExperimentBuilder<S, Result> setReferenceFrontFileNames(List<String> referenceFrontFileNames) {
//		this.referenceFrontFileNames = referenceFrontFileNames;
		super.setReferenceFrontFileNames(referenceFrontFileNames);
		return this;
	}

	public RExperimentBuilder<S, Result> setIndicatorList(List<GenericIndicator<S>> indicatorList) {
//		this.indicatorList = indicatorList;
		super.setIndicatorList(indicatorList);
		return this;
	}

	public RExperimentBuilder<S, Result> setOutputParetoFrontFileName(String outputParetoFrontFileName) {
//		this.outputParetoFrontFileName = outputParetoFrontFileName;
		super.setOutputParetoFrontFileName(outputParetoFrontFileName);
		return this;
	}

	public RExperimentBuilder<S, Result> setOutputParetoSetFileName(String outputParetoSetFileName) {
//		this.outputParetoSetFileName = outputParetoSetFileName;
		super.setOutputParetoSetFileName(outputParetoSetFileName);
		return this;
	}

	public RExperimentBuilder<S, Result> setIndependentRuns(int independentRuns) {
//		this.independentRuns = independentRuns;
		super.setIndependentRuns(independentRuns);
		return this;
	}

	public RExperimentBuilder<S, Result> setNumberOfCores(int numberOfCores) {
//		this.numberOfCores = numberOfCores;
		super.setNumberOfCores(numberOfCores);
		return this;
	}

	public RExperiment<S, Result> build() {
		return new RExperiment<S, Result>(this);
	}

//	/* Getters */
//	public String getExperimentName() {
//		return experimentName;
//	}
//
//	public List<ExperimentAlgorithm<S, Result>> getAlgorithmList() {
//		return algorithmList;
//	}
//
//	public List<ExperimentProblem<S>> getProblemList() {
//		return problemList;
//	}
//
//	public String getExperimentBaseDirectory() {
//		return experimentBaseDirectory;
//	}
//
//	public String getOutputParetoFrontFileName() {
//		return outputParetoFrontFileName;
//	}
//
//	public String getOutputParetoSetFileName() {
//		return outputParetoSetFileName;
//	}
//
//	public int getIndependentRuns() {
//		return independentRuns;
//	}
//
//	public int getNumberOfCores() {
//		return numberOfCores;
//	}
//
//	public List<String> getReferenceFrontFileNames() {
//		return referenceFrontFileNames;
//	}
//
//	public String getReferenceFrontDirectory() {
//		return referenceFrontDirectory;
//	}
//
//	public List<GenericIndicator<S>> getIndicatorList() {
//		return indicatorList;
//	}
}
