package it.univaq.disim.sealab.availability.aemilia;

import java.io.File;

import Jama.Matrix;

/**
 * Thread to build the CTMC and compute the stationary distribution.
 */
public class AnalysisRunnable implements Runnable {
	
	private Analysis analysis;

	private File aemFile;
	
	private File ttkernel;
	
	private Matrix stationaryDistribution;

	/**
	 * Creates a new instance of the thread.
	 * @param aemFile Aemilia file
	 */
	public AnalysisRunnable(final File aemFile) {
		this.aemFile = aemFile;
	}

	/**
	 * Returns the configured TTKernel path
	 * @return TTKernel path
	 */
	public File getTTKernel() {
		return ttkernel;
	}

	/**
	 * Set the TTKernel path
	 * @param ttkernel TTKernel path
	 */
	public void setTTKernel(File ttkernel) {
		this.ttkernel = ttkernel;
	}
	
	/**
	 * Returns the instance of Analysis
	 * corresponding to this thread.
	 */
	public Analysis getAnalysis() {
		return analysis;
	}
	
	/**
	 * Return the stationary distribution.
	 * @return stationary distribution
	 */
	public Matrix getStationaryDistribution() {
		return stationaryDistribution;
	}
	
	/**
	 * Thread body.
	 */
	@Override
	public void run() {
		analysis = new Analysis(aemFile);
		
		// Parse the Aemilia specification
		if (ttkernel == null) {
			analysis.parse();
		} else {
			analysis.parse(ttkernel);
		}
		
		// Compute the stationary distribution
		stationaryDistribution = analysis.getStationaryDistribution();
	}

}
