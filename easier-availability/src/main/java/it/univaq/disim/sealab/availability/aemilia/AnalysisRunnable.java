package it.univaq.disim.sealab.availability.aemilia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Thread to build the CTMC and compute the stationary distribution.
 */
public class AnalysisRunnable implements Runnable {

	private Analysis analysis;

	private File aemFile;

	private File ttkernel;

	/** Availability (all operational components) */
	public static boolean AVAILABILITY_FULL = false;

	/** Availability (at least one operational component) */
	public static boolean AVAILABILITY_DEGRADED = false;

	/** Skip the creation of .psm files if already present */
	public static boolean SKIP_PSM_CREATION = false;

	/** Skip the creation of .ava files if already present */
	public static boolean SKIP_AVA_CREATION = false;

	/** Use stochastic over-relaxation instead of Gaussian elimination */
	public static boolean SOR = false;

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
	 * Returns the .AEM file
	 * @return File object corresponding to the .AEM file
	 */
	public File getAemFile() {
		return aemFile;
	}

	/**
	 * Returns the .AVA file containing
	 * availability measures.
	 * @return File object corresponding to the .AVA file
	 */
	public File getAvaFile() {
		return new File(aemFile.getAbsolutePath() + ".ava");
	}

	/**
	 * Write the availability results to file.
	 */
	public void writeResults() {
		final File results = getAvaFile();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(results))) {
			writer.write(String.format("%s - Availability (all operational components): %.10f\n",
					aemFile, analysis.getFullyOperationalAvailability()));
			writer.write(String.format("%s - Availability (at least one operational component): %.10f\n",
					aemFile, analysis.getDegradedAvailability()));
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
		System.out.println("Started computing: " + aemFile);
		final long startTime = System.nanoTime();
		analysis = new Analysis(aemFile);

		if (ttkernel != null) {
			analysis.setTTKernel(ttkernel);
		}

		// Generate the .psm file
		if (!SKIP_PSM_CREATION ||
			!analysis.getParser().getPSMFile().exists()) {
			try {
				analysis.generatePSM();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				return;
			}
		}

		// Check if we need to compute availability
		if (SKIP_AVA_CREATION && getAvaFile().exists()) {
			if (AVAILABILITY_FULL) {
				analysis.readFullyOperationalAvailability(getAvaFile());
			}
			if (AVAILABILITY_DEGRADED) {
				analysis.readDegradedAvailability(getAvaFile());
			}
			return;
		}

		if (SOR) { // Stochastic over-relaxation
			// Parse global and local states
			analysis.getParser().parsePSMStates();

			// Compute the stationary distribution
			try {
				analysis.getStationaryDistributionSOR();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				return;
			}
		} else { // Gaussian elimination
			// Parse the Aemilia specification
			analysis.parse();

			// Compute the stationary distribution
			analysis.getStationaryDistribution();
		}

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
		System.out.println(aemFile + " completed in " + (System.nanoTime() - startTime));
	}

}
