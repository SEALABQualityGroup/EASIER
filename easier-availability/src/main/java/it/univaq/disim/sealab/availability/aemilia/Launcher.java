package it.univaq.disim.sealab.availability.aemilia;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Launcher for the availability analysis that can be called
 * both from the command line and programmatically.
 */
public class Launcher {
	
	private static File ttkernel;
	
	/** Availability (all operational components) */
	public static boolean AVAILABILITY_FULL = false;
	
	/** Availability (at least one operational components) */
	public static boolean AVAILABILITY_DEGRADED = false;
	
	/**
	 * Print help on how to use the launcher from the command line.
	 */
	public static void printUsage() {
		System.out.println("Usage: [-options] folder\n"
				+ "  (folder containing .aem files)\n"
				+ "where options include:\n"
				+ "  -f		Availability (all operational components)\n"
				+ "  -d		Availability (at least one operational components)\n"
				+ "  -t <TTKernel> Set the path to the TTKernel executable");
	}
	
	/**
	 * Perform the availability analysis on all
	 * the Aemilia files in a folder.
	 * @param folder the folder containing aemilia files
	 */
	public static void performAnalysis(final File folder) {
		// Start to parse and compute the solutions
		final File[] files = folder.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".aem");
	        }
	    });
		final AnalysisRunnable[] workers = new AnalysisRunnable[files.length];
		ExecutorService executor = Executors.newFixedThreadPool(files.length);
		for (int i = 0; i < files.length; i++) {
			workers[i] = new AnalysisRunnable(files[i]);
			if (ttkernel != null) {
				workers[i].setTTKernel(ttkernel);
			}
			executor.execute(workers[i]);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			System.err.println("Analysis execution was interrupted.");
			e.printStackTrace();
		}
		
		for (int i = 0; i < files.length; i++) {
			if (AVAILABILITY_FULL) {
				System.out.println(
						files[i].getName()
						+ " - Availability (all operational components): "
						+ workers[i].getAnalysis().getFullyOperationalAvailability());
			}
			
			if (AVAILABILITY_DEGRADED) {
				System.out.println(
						files[i].getName()
						+ " - Availability (at least one operational components): "
						+ workers[i].getAnalysis().getDegradedAvailability());
			}
		}
	}
	
	/**
	 * Perform the availability analysis on all
	 * the Aemilia files in a folder.
	 * @param folder the folder containing aemilia files
	 * @param ttkernel TTKernel file
	 */
	public static void performAnalysis(final File folder, final File ttkernel) {
		Launcher.ttkernel = ttkernel;
		performAnalysis(folder);
	}
	
	/**
	 * Invoked when executed from the command line.
	 * @param args launch without args to get help
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			printUsage();
			return;
		}
		
		final File folder = new File(args[args.length - 1]);
		if (!folder.isDirectory()) {
			System.err.println(folder + " is not a directory.");
			return;
		}
		
		List<String> argsList = Arrays.asList(args);
		if (!argsList.contains("-f") && !argsList.contains("-d")) {
			System.err.println("Please select an availability index.");
			printUsage();
			return;
		}
		
		if (argsList.contains("-f")) {
			AVAILABILITY_FULL = true;
		}
		
		if (argsList.contains("-d")) {
			AVAILABILITY_DEGRADED = true;
		}
		
		if (argsList.contains("-t")) {
			ttkernel = new File(argsList.get(argsList.indexOf("-t") + 1));
		}
		
		performAnalysis(folder);
	}
}
