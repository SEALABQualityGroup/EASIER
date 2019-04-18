package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.nio.file.Path;

import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public interface Controller {

	
	public Manager getManager();
	public Configurator getConfigurator();
	public Path getPermanentTmpFolder();
	public PerformanceQualityEvaluator getPerfQuality();
}
