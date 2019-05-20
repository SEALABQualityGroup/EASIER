package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExecuteAlgorithms;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperiment;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperimentBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RGenerateReferenceParetoFront;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAII;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAIIBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RCrossover;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RMutation;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RSolutionListEvaluator;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2Builder;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class UMLController implements Controller{

	public static boolean append = false;
	public static Logger logger_ = Logger.getLogger(UMLController.class.getName());
	public static FileHandler handler;

	private MetamodelManager metamodelManager;
	private Manager manager;
	private RProblem problem;
	private boolean cleaningTmp = false;

	/* Configurator class */
	private Configurator configurator;

	/* Evolutionary operators */
	private CrossoverOperator<RSolution> crossoverOperator;
	private MutationOperator<RSolution> mutationOperator;
	private SelectionOperator<List<RSolution>, RSolution> selectionOpertor;
	private SolutionListEvaluator<RSolution> solutionListEvaluator;

	/* Source models */
	// private List<Path> sourceModels = new ArrayList<>();
	private List<SourceModel> sourceModels = new ArrayList<>();
	private List<ExperimentProblem<RSolution>> problemList;

	public UMLController() {
		manager = new Manager(new UMLManager(this));
		manager.setController(this);
		// perfQuality = new PerformanceQualityEvaluator(manager.getOclManager());
		metamodelManager = manager.getMetamodelManager();
	}

	public UMLController(final Configurator config) {
		this();
		configurator = config;

		configurator.getTmpFolder().toFile().mkdirs();

		// Instantiates evolutionary operators
		crossoverOperator = new RCrossover(config.getXoverProbabiliy(), this);
		mutationOperator = new RMutation(config.getMutationProbability(), config.getDistributionIndex());
		selectionOpertor = new BinaryTournamentSelection<RSolution>(
				new RankingAndCrowdingDistanceComparator<RSolution>());
		solutionListEvaluator = new RSolutionListEvaluator();
	}

	public UMLController setUp() {
		// setting up the source models
		for (Path path : configurator.getModelsPath()) {
			SourceModel model = new SourceModel(path);
			model.setSourceModelPAs(getPerfQuality().performanceAntipatternEvaluator(
					metamodelManager.getModel(Paths.get(path.toString(), "model.mmaemilia")),
					Paths.get(path.toString(), "ocl", "detectionSingleValuePA.ocl")));

			// generates every needed files and updates the source model
			generateSourceFiles(path);
			updateSourceModel(path);
			sourceModels.add(model);
		}
		metamodelManager.setSourceModels(sourceModels);
		System.out.println("Setting up finished");
		return this;
	}

	public CrossoverOperator<?> getXoverOperator() {
		return crossoverOperator;
	}

	public MutationOperator<?> getMutationOperator() {
		return mutationOperator;
	}

	private void generateSourceFiles(final Path source) {}

	public List<RProblem> createProblems() {

		List<RProblem> rProblems = new ArrayList<>();

		for (SourceModel src : sourceModels) {
			for (Integer l : configurator.getLength()) {
				for (Double w : configurator.getCloningWeight()) {
					for (Integer mc : configurator.getMaxCloning()) {
						if (mc == -1)
							mc = l; // whether mc is -1, mc will be the chromosome's length
						String pName = src.getName() + "_Length_" + String.valueOf(l) + "_CloningWeight_"
								+ String.valueOf(w) + "_MaxCloning_" + String.valueOf(mc);
						RProblem p = new RProblem(src.getSourceFolder(), l, configurator.getActions(),
								configurator.getAllowedFailures(), configurator.getPopulationSize(), this);
						p.setCloningWeight(w).setMaxCloning(mc).setName(pName);
						rProblems.add(p);
					}
				}
			}
		}
		return rProblems;
	}

	public void runExperiment(final List<RProblem> rProblems,
			final List<GenericIndicator<RSolution>> qualityIndicators) {
		final int INDEPENDENT_RUNS = configurator.getIndependetRuns(); // should be 31 or 51
		final int CORES = 1;

		problemList = new ArrayList<>();

		rProblems.forEach(problem -> problemList.add(new ExperimentProblem<>(problem)));

		List<ExperimentAlgorithm<RSolution, List<RSolution>>> algorithmList = configureAlgorithmList(problemList);

		Path referenceFrontDirectory = Paths.get(configurator.getOutputFolder().toString(), "referenceFront");

		RExperiment<RSolution, List<RSolution>> experiment = new RExperimentBuilder<RSolution, List<RSolution>>("Exp")
				.setAlgorithmList(algorithmList).setProblemList(problemList)
				.setExperimentBaseDirectory(referenceFrontDirectory.toString())
				.setReferenceFrontDirectory(referenceFrontDirectory.toString()).setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR").setIndicatorList(qualityIndicators)
				.setIndependentRuns(INDEPENDENT_RUNS).setNumberOfCores(CORES).build();
		try {
			List<Entry<Algorithm<List<RSolution>>, Long>> computingTimes = new RExecuteAlgorithms<RSolution, List<RSolution>>(
					experiment, this).run().getComputingTimes();
			experiment.setComputingTime(computingTimes);

			// List<RSolution> pareto = new ArrayList<>();

			new RGenerateReferenceParetoFront(experiment).run();

			// for(Entry<Algorithm<List<RSolution>>, Long> entry : computingTimes) {
			// saveParetoSolution(entry.getKey().getResult());
			// }
			//

			// new ComputeQualityIndicators<>(experiment).run();
			// new GenerateWilcoxonTestTablesWithR<>(experiment).run();
			// new GenerateBoxplotsWithR<>(experiment).run();
			// new GenerateLatexTablesWithStatistics(experiment).run();
			// new GenerateLatexTablesWithComputingTime(experiment).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The algorithm list is composed of pairs {@link Algorithm} + {@link Problem}
	 * which form part of a {@link TaggedAlgorithm}, which is a decorator for class
	 * {@link Algorithm}.
	 *
	 * @param problemList
	 * @return
	 */
	/**
	 * The algorithm list is composed of pairs {@link Algorithm} + {@link Problem}
	 * which form part of a {@link ExperimentAlgorithm}, which is a decorator for
	 * class {@link Algorithm}.
	 */
	public List<ExperimentAlgorithm<RSolution, List<RSolution>>> configureAlgorithmList(
			List<ExperimentProblem<RSolution>> problemList) {

		List<ExperimentAlgorithm<RSolution, List<RSolution>>> algorithms = new ArrayList<>();

		for (int i = 0; i < problemList.size(); i++) {

			CustomNSGAIIBuilder<RSolution> customNSGABuilder = new CustomNSGAIIBuilder<RSolution>(
					problemList.get(i).getProblem(), crossoverOperator, mutationOperator);

			customNSGABuilder.setMaxEvaluations(configurator.getMaxEvaluation());
			customNSGABuilder.setPopulationSize(configurator.getPopulationSize());
			customNSGABuilder.setSolutionListEvaluator(solutionListEvaluator);

			NSGAII<RSolution> algorithm = customNSGABuilder.build();
			((CustomNSGAII<RSolution>) algorithm).setName("NSGA_II");

			ExperimentAlgorithm<RSolution, List<RSolution>> exp = new CustomExperimentAlgorithm<RSolution, List<RSolution>>(
					algorithm, problemList.get(i).getTag(), i);
			algorithms.add(exp);
		}

		for (int i = 0; i < problemList.size(); i++) {
			@SuppressWarnings("unchecked")
			CustomSPEA2Builder<RSolution> spea2Builder = (CustomSPEA2Builder<RSolution>) new CustomSPEA2Builder(
					problemList.get(i).getProblem(), crossoverOperator, mutationOperator)
							.setSelectionOperator(selectionOpertor).setSolutionListEvaluator(solutionListEvaluator)
							.setMaxIterations(
									Math.toIntExact(configurator.getMaxEvaluation() / configurator.getPopulationSize()))
							.setPopulationSize(configurator.getPopulationSize());

			CustomSPEA2<RSolution> algorithm = (CustomSPEA2<RSolution>) spea2Builder.build();
			algorithm.setName("SPEA2");
			ExperimentAlgorithm<RSolution, List<RSolution>> exp = new CustomExperimentAlgorithm<RSolution, List<RSolution>>(
					algorithm, problemList.get(i).getTag(), i);
			algorithms.add(exp);
		}
		return algorithms;
	}

	public List<Path> getReferenceFront() {

		List<Path> refFront = new ArrayList<>();
		for (ExperimentProblem<RSolution> problem : problemList) {
			refFront.add(
					Paths.get(configurator.getOutputFolder().toString(), "referenceFront", problem.getTag() + ".rf"));
		}
		return refFront;
	}

	public synchronized void generateAvailability(final List<String> solIDs) {
	}

	/**
	 * 
	 * @param targetFolder
	 */
	public synchronized void generateAvailability(final Path targetFolder) {
		// File availabilityDir = avaFolder;
		
	}

	private synchronized void saveParetoSolution(List<RSolution> paretoPop) {
		it.univaq.disim.sealab.metaheuristic.utils.FileUtils.writeSolutionSetToCSV(paretoPop);
		for (RSolution solution : paretoPop) {
			final File srcDir = new File(solution.getMmaemiliaFolderPath());

			final File destDir = Paths
					.get(configurator.getOutputFolder().toString(), "pareto", String.valueOf(solution.getName()))
					.toFile();
			// File destDir = new File(getParetoFolder() + solution.getName());
			try {
				FileUtils.copyDirectory(srcDir, destDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void cleanTmpFiles() {
		if (cleaningTmp) {
			try {
				FileUtils.cleanDirectory(configurator.getTmpFolder().toFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public PerformanceQualityEvaluator getPerfQuality() {
		return new PerformanceQualityEvaluator(manager.getOclManager());
	}

	public RProblem getProblem() {
		return this.problem;
	}

	public void setProblem(RProblem p) {
		this.problem = p;
	}

	public Manager getManager() {
		return manager;
	}

	public Configurator getConfigurator() {
		return this.configurator;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	private void updateSourceModel(Path source) {}


	private class FileTypesFilter implements FileFilter {
		String[] types;

		FileTypesFilter(String[] types) {
			this.types = types;
		}

		public boolean accept(File f) {
			if (f.isDirectory())
				return true;
			for (String type : types) {
				if (f.getName().endsWith(type))
					return true;
			}
			return false;
		}
	}

	public void setFailureRatesPropertiesFile(String failureRatesPropertiesFile) {}

	public Path getPermanentTmpFolder() {
		return Paths.get(configurator.getOutputFolder().toString(), "tmp");
	}
}
