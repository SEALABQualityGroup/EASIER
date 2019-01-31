package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment;

import java.io.File;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class RExecuteAlgorithms<S extends Solution, Result> extends ExecuteAlgorithms<S, Result> {

	  private Experiment<S, Result> experiment;
	  private final Controller controller;

	  /** Constructor */
	  public RExecuteAlgorithms(Experiment<S, Result> configuration, final Controller ctr) {
		  super(configuration);
		  this.experiment = configuration;
		  this.controller = ctr;
	  }

	  @Override
	  public void run() {
	    JMetalLogger.logger.info("ExecuteAlgorithms: Preparing output directory");
	    prepareOutputDirectory() ;

	    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
	            "" + this.experiment.getNumberOfCores());

	    for (int i = 0; i < experiment.getIndependentRuns(); i++) {
	      final int id = i ;

	      experiment.getAlgorithmList()
	              .parallelStream()
	              .forEach(algorithm -> algorithm.runAlgorithm(id, experiment)) ;
	      
	      FileUtils.moveTmpFile(controller.getTmpFolder(), controller.getPermanentTmpFolder());
	      
	    }
	  }



	  private void prepareOutputDirectory() {
	    if (experimentDirectoryDoesNotExist()) {
	      createExperimentDirectory() ;
	    }
	  }

	  private boolean experimentDirectoryDoesNotExist() {
	    boolean result;
	    File experimentDirectory;

	    experimentDirectory = new File(experiment.getExperimentBaseDirectory());
	    if (experimentDirectory.exists() && experimentDirectory.isDirectory()) {
	      result = false;
	    } else {
	      result = true;
	    }

	    return result;
	  }

	  private void createExperimentDirectory() {
	    File experimentDirectory;
	    experimentDirectory = new File(experiment.getExperimentBaseDirectory());

	    if (experimentDirectory.exists()) {
	      experimentDirectory.delete() ;
	    }

	    boolean result ;
	    result = new File(experiment.getExperimentBaseDirectory()).mkdirs() ;
	    if (!result) {
	      throw new JMetalException("Error creating experiment directory: " +
	          experiment.getExperimentBaseDirectory()) ;
	    }
	  }

}
