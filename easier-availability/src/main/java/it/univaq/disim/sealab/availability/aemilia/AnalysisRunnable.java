package it.univaq.disim.sealab.availability.aemilia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Thread to build the CTMC and compute the stationary distribution.
 */
public class AnalysisRunnable implements Runnable {

	private Analysis analysis;

	/** Availability (all operational components) */
	public static boolean AVAILABILITY_FULL = false;

	/** Availability (at least one operational components) */
	public static boolean AVAILABILITY_DEGRADED = false;

	private File aemFile;

	private File ttkernel;

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
	 * Write the availability results to file.
	 */
	public void writeResults() {
		final File results = new File(aemFile.getAbsolutePath() + ".ava");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(results))) {
			writer.write(String.format("%s - Availability (all operational components): %f\n",
					aemFile, analysis.getFullyOperationalAvailability()));
			writer.write(String.format("%s - Availability (at least one operational components): %f\n",
					aemFile, analysis.getFullyOperationalAvailability()));
		} catch (Exception e) {
			System.err.println("Unable to write results to " + results);
			e.printStackTrace();
		}
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
		analysis.getStationaryDistribution();

		// Compute the fully operational availability
		if (AVAILABILITY_FULL) {
			analysis.getFullyOperationalAvailability();
		}

		// Compute the degraded availability
		if (AVAILABILITY_DEGRADED) {
			analysis.getDegradedAvailability();
		}

		// Write the results to file
		writeResults();

		analysis.clean();
	}

}
