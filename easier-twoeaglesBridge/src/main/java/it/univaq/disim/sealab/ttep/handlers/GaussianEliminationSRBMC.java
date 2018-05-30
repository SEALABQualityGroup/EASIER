package it.univaq.disim.sealab.ttep.handlers;

import java.util.concurrent.ExecutionException;

import it.univaq.disim.sealab.ttep.utility.Utility;


public class GaussianEliminationSRBMC{

	/* Parametro da passare a TTKernel per lanciare la funzionalita' opportuna */
	private final static String STATIONARY_REWARD_BASED_MEASURE_CALCULATOR_GAUSSIAN_ELIMINATION = "-s";
	
	private final static String[] EXTENSIONS = {"*.aem"};
	
	
	public Object execute_ase(String twoTowersKernelPath, String aemFilePath, String rewFilePath, String destinationFilePath) throws ExecutionException {
		
		// TwoTowers is called here
		/*
		 * source -> .aem file
		 * source_2 -> .rew file
		 * dest ->  destination file (.aem file without extension)
		 * twotowers -> /Users/daniele/git/sealabtools/panda-aemilia/TwoTowers/bin/TTKernel
		 * STATIONARY_REWARD_BASED_MEASURE_CALCULATOR_GAUSSIAN_ELIMINATION -> -s
		 */
		Utility.execute_ase(twoTowersKernelPath, STATIONARY_REWARD_BASED_MEASURE_CALCULATOR_GAUSSIAN_ELIMINATION, aemFilePath, rewFilePath, destinationFilePath);
		//Utility.printOutput(dest);
		return null;
	}

}
