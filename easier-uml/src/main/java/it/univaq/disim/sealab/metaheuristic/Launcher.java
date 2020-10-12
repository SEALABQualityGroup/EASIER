package it.univaq.disim.sealab.metaheuristic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.qualityindicator.impl.GenericIndicator;

import com.beust.jcommander.JCommander;

import it.univaq.disim.sealab.metaheuristic.evolutionary.ProgressBar;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.factory.FactoryBuilder;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class Launcher {

	public static void main(String[] args) {

		JCommander jc = new JCommander();

		Configurator config = new Configurator();
		jc.addObject(config);
		jc.parse(args);

		List<Path> referenceFront = new ArrayList<>();

		UMLController ctr = new UMLController(config);
		if (config.getReferenceFront() != null)
			referenceFront = config.getReferenceFront();
		
		else {
			List<Path> modelsPath = new ArrayList<>();

			if (config.getModelsPath().get(0).toFile().isFile()) {
				// use the solution csv file to extract more models
				modelsPath
						.addAll(FileUtils.extractModelPaths(config.getModelsPath().get(0), config.getMaxWorseModels()));
			} else {
				modelsPath.addAll(config.getModelsPath());
			}
			int i = 1;
			for (Path m : modelsPath) {
				System.out.println("Number of source model");
				ProgressBar.showBar(i, modelsPath.size());
				ctr.setUp(m);
				List<RProblem<UMLRSolution>> rProblems = ctr.createProblems();
				List<GenericIndicator<UMLRSolution>> qIndicators = new ArrayList<>();

				FactoryBuilder<UMLRSolution> factory = new FactoryBuilder<>();
				for (String qI : config.getQualityIndicators()) {
					GenericIndicator<UMLRSolution> ind = factory.createQualityIndicators(qI);
					if (ind != null)
						qIndicators.add(ind);
				}
				
				
				ctr.runExperiment(rProblems, qIndicators);
				referenceFront = ctr.getReferenceFront();
			}
			i++;
		}
	} 
}
