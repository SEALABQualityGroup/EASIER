package it.univaq.disim.sealab.easier.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateWilcoxonTestTablesWithR;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import com.beust.jcommander.JCommander;

import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaController;
import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperimentBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RComputeQualityIndicators;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RGenerateLatexTablesWithStatistic;
import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RGenerateReferenceParetoFront;
import it.univaq.disim.sealab.metaheuristic.evolutionary.factory.FactoryBuilder;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class Helper implements Runnable {

	private Path aemPath;
	private Path rewPath;
	private Path outputPath;
	private String ttKernel;

	public Helper() {
	}

	public Helper(Path aemPath, Path rewFilePath, Path outputFilePath, String ttKernel) {
		this.aemPath = aemPath;
		this.rewPath = rewFilePath;
		this.outputPath = outputFilePath;
		this.ttKernel = ttKernel;
	}

	public static Set<File> listFilesRecursively(final File folder) {
		Set<File> files = new HashSet<File>();
		if (folder == null || folder.listFiles() == null) {
			return files;
		}
		for (File entry : folder.listFiles()) {
			if (entry.isFile() && entry.getName().endsWith(".aem")) {
				files.add(entry);
			} else if (entry.isDirectory()) {
				files.addAll(listFilesRecursively(entry));
			}
		}
		return files;
	}

	public static void main(String[] args) {

		Set<File> listOfFiles = listFilesRecursively(new File(args[0]));

		JCommander jc = new JCommander();
		Configurator config = new Configurator();
		jc.addObject(config);
		jc.parse(args);

		if (config.generateRF()) {

			AemiliaController ctr = new AemiliaController(config);
			List<Path> referenceFront;
			
			ctr.setUp();
			List<RProblem<AemiliaRSolution>> rProblems = ctr.createProblems();
			List<GenericIndicator<AemiliaRSolution>> qIndicators = new ArrayList<>();

			FactoryBuilder<AemiliaRSolution> factory = new FactoryBuilder<>();
			
			for (String qI : config.getQualityIndicators()) {
				GenericIndicator<AemiliaRSolution> ind = factory.createQualityIndicators(qI);
				if (ind != null)
					qIndicators.add(ind);
			}
			
			List<ExperimentProblem<AemiliaRSolution>> problemList = new ArrayList<>();

			rProblems.forEach(problem -> problemList.add(new ExperimentProblem<AemiliaRSolution>(problem)));

			List<ExperimentAlgorithm<AemiliaRSolution, List<AemiliaRSolution>>> algorithmList = ctr.configureAlgorithmList(problemList);

			Path referenceFrontDirectory = Paths.get(config.getOutputFolder().toString(), "referenceFront");

			Experiment<AemiliaRSolution, List<AemiliaRSolution>> experiment = new RExperimentBuilder<AemiliaRSolution, List<AemiliaRSolution>>("Exp")
					.setAlgorithmList(algorithmList).setProblemList(problemList)
					.setExperimentBaseDirectory(referenceFrontDirectory.toString())
					.setReferenceFrontDirectory(referenceFrontDirectory.toString()).setOutputParetoFrontFileName("FUN")
					.setOutputParetoSetFileName("VAR").setIndicatorList(qIndicators)
					.setIndependentRuns(config.getIndependetRuns()).setNumberOfCores(1).build();
			
			try {
				new RGenerateReferenceParetoFront(experiment).run();
				new RComputeQualityIndicators<>(experiment).run();
				new GenerateWilcoxonTestTablesWithR<>(experiment).run();
				new GenerateBoxplotsWithR<>(experiment).run();
				new RGenerateLatexTablesWithStatistic<>(experiment).run();
//				new GenerateLatexTablesWithComputingTime(experiment).run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {

			ExecutorService executor = Executors.newFixedThreadPool(listOfFiles.size());
			for (File file : listOfFiles) {
				final Path rewFilePath = Paths.get(file.toPath().getParent().toString(), "ava.rew");
				final Path outputFilePath = Paths.get(file.toPath().getParent().toString(), "ava_result");

				Runnable worker = new Helper(file.toPath(), rewFilePath, outputFilePath, args[1]);
				executor.execute(worker);
			}
		}
	}

	public void sorSRBMC() {
		try {
			Process process = new ProcessBuilder(this.ttKernel, "-t", this.aemPath.toString(), this.rewPath.toString(),
					outputPath.toString()).start();
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		sorSRBMC();
	}

}
