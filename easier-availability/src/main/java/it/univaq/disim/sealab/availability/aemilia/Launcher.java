package it.univaq.disim.sealab.availability.aemilia;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	 * Check if the folder exists and we have write permissions.
	 * @param folder the folder to check
	 * @return true if the folder exists and we have write permissions
	 */
	private static boolean checkFolder(final File folder) {
		if (!folder.isDirectory()) {
			System.err.println(folder + " is not a directory.");
			return false;
		}		
		if (!folder.canRead()) {
			System.err.println("No permissions to read from " + folder);
			return false;
		}		
		if (!folder.canWrite()) {
			System.err.println("No permissions to write in " + folder);
			return false;
		}		
		return true;
	}
	
	/**
	 * Recursively walk through subdirectories listing Aemilia files.
	 * @param folder starting folder
	 * @return array of aemilia file paths
	 */
	private static Set<File> listFilesRecursively(final File folder) {
		Set<File> files = new HashSet<File>();
		if(folder == null || folder.listFiles() == null){
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
	
	/**
	 * Perform the availability analysis on all
	 * the Aemilia files in a folder.
	 * @param folder the folder containing aemilia files
	 */
	public static void performAnalysis(final File folder) {
		
		// Check if the folder exists and we have write permissions
		if (!checkFolder(folder)) {
			return;
		}
		
		// Start to parse and compute the solutions
		final Set<File> aemFiles = listFilesRecursively(folder);
		final File[] files = aemFiles.toArray(new File[aemFiles.size()]);
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
						files[i]
						+ " - Availability (all operational components): "
						+ workers[i].getAnalysis().getFullyOperationalAvailability());
			}
			
			if (AVAILABILITY_DEGRADED) {
				System.out.println(
						files[i]
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
		
		List<String> argsList = Arrays.asList(args);
		if (!argsList.contains("-f") && !argsList.contains("-d")) {
			System.err.println("Please select an availability index (use -f, -d or both).");
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

		final File folder = new File(args[args.length - 1]);
		
		performAnalysis(folder);
	}
}
