package it.univaq.disim.sealab.metaheuristic.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beust.jcommander.Parameter;

public class Configurator {
	
	@Parameter
	private List<String> parameters = new ArrayList<>();

	@Parameter(names = { "-h", "--help" }, help = true)
	private boolean help;
	
	@Parameter(names = {"-sor"}, description="Use stochastic over-relaxation", arity = 0)
	private boolean sor = false;
	
	@Parameter(names = {"-e", "--experiment"}, description="Execute experiment")
	private boolean experiment = true;
	
	@Parameter(names = {"-m", "--models"}, required = true, description="List of models")
	private List<String> modelsPath = new ArrayList<>();
	
	@Parameter(names = {"-tk", "--ttKernel"}, description = "Set the TTKernel solver path")
	private String ttKernel;
	
	@Parameter(names = {"-p", "--pareto"}, description = "Give the Reference pareto front file path")
	private String paretoFront;
	
	@Parameter(names = {"--maxCloning"}, description = "Set the max number of clones within a sequence")
	private List<Integer> maxCloning = Arrays.asList(3);
	
	@Parameter(names = {"--cloningWeight"}, description = "Set the architectural distance weight for a clone action")
	private List<Double> cloningWeight = Arrays.asList(1.5);
	
	@Parameter(names = {"--constChangesWeight"}, description = "Set the architectural distance weight for a const change action")
	private double constChangesWeight=1;
	
	@Parameter(names = {"-r" , "--independent_runs"}, description = "Set the number of independent runs")
	private int independetRuns = 31;
	
	@Parameter(names= {"--cleaningTmp"}, description = "Set to true for removing all temporary files")
	private boolean cleaningTmp = false;
	
	@Parameter(names = {"-outF", "--outputFolder"}, required = true, description = "Set the output root folder")
	private String outputFolder;
	
	@Parameter(names = {"-maxEval", "--maxEvaluation"}, required = true, description = "Set the maximum evaluations")
	private int maxEval;
	
	@Parameter(names = {"-popSize", "--populationSize"}, required = true, description = "Set the population size")
	private int popSize;
	
	@Parameter(names = {"-xover", "--xoverProb"}, description = "Set the crossover probability")
	private double xover=0.8;
	
	@Parameter(names = {"-mutation", "--mutationProb"}, description = "Set the mutation probability")
	private double mutation=0.2;

	@Parameter(names = {"-dIndex", "--distributionIndex"}, description = "Set the distribution index for the mutation operator")
	private double distributionIndex=20;
	
	@Parameter(names = {"-l", "--sequenceLength"}, description = "Set the length of a sequence")
	private List<Integer> length = Arrays.asList(4);
	
	@Parameter(names = {"-af", "--allowedFaiulures"}, description = "Set the maximunm number of failures")
	private int aw = 1000;
	
	@Parameter(names = {"-a", "--actions"}, description = "Set the number of failures")
	private int actions = 5;
	
	@Parameter(names = {"-tmpF", "--tempFolder"}, required = true, description = "It is the temporary file folder")
	private String tmpF;

	@Parameter(names = {"-oclTemplate", "--oclTemplateFolder"}, required = true, description = "It is the ocl rule template file")
	private String oclTemplate;
	
	@Parameter(names = {"-ava", "--availability"}, description = "Enables availability calculattion over pareto solutions")
	private boolean ava = false;
	
	@Parameter(names = {"-wr", "--workloadRange"}, description = "Enables workload rage")
	private int wr = -1;
	
	@Parameter(names = {"-algo", "--algorithm"}, required = true, description="List of algorithms")
	private List<String> algorithms = new ArrayList<>();
	
	@Parameter(names = {"-qI", "--qualityIndicator"}, required = true, description="List of quality indicators")
	private List<String> qI = new ArrayList<>();
	
	public List<String> getQualityIndicators() { return qI; }
	
	public List<String> getAlgorithms() { return algorithms; }
	
	public int getWorkloadRange() { return wr; }
	
	public boolean hasAvailability() { return ava; }
	
	public Path getOclTemplate() { return Paths.get(oclTemplate); }
	
	public Path getTmpFolder() { return Paths.get(tmpF); }
	
	public int getActions() { return actions; }
	
	public int getAllowedFailures() { return aw; }
	
	public List<Integer> getLength() { return length; }
	
	public double getXoverProbabiliy() { return xover; }

	public double getMutationProbability() { return mutation; }
	
	public int getPopulationSize() { return popSize; }

	public int getMaxEvaluation() { return maxEval; }

	public Path getOutputFolder() { return Paths.get(outputFolder); }

	public int getIndependetRuns() { return independetRuns; }
	
	public double getConstChangesWeight() { return constChangesWeight; }
	
	public List<Double> getCloningWeight() { return cloningWeight; }
	
	public List<Integer> getMaxCloning() { return maxCloning; }
	
	public boolean getExperiment() { return experiment; }
	
	public boolean isSor() { return sor; }
	
	public List<Path> getModelsPath() { 
		List<Path> paths = new ArrayList<>();
		modelsPath.forEach(m->paths.add(Paths.get(m)));
		return paths; 
	}
	
	public Path getTTKernel() { return Paths.get(ttKernel); }

	public double getDistributionIndex() { return 0;}
	

}