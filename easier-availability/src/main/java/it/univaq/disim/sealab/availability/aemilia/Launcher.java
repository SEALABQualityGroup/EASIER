package it.univaq.disim.sealab.availability.aemilia;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Launcher for the availability analysis that can be called
 * both from the command line and programmatically.
 */
public class Launcher {

//	private static File ttkernel;
//
//	/** Number of threads */
//	public static int NUMBER_OF_THREADS = 0;
//
//	/** Timeout for the computation of solutions */
//	public static int TIMEOUT = 0;
//
//	/**
//	 * Print help on how to use the launcher from the command line.
//	 */
//	public static void printUsage() {
//		System.out.println("Usage: [-options] folder\n"
//				+ "  (folder containing .aem files)\n"
//				+ "where options include:\n"
//				+ "  -f		Availability (all operational components)\n"
//				+ "  -d		Availability (at least one operational component)\n"
//				+ "  -t <TTKernel> Set the path to the TTKernel executable\n"
//				+ "  -n		Number of threads\n"
//				+ "  -sp	Skip the creation of .psm files if already present\n"
//				+ "  -sa	Skip the creation of .ava files if already present\n"
//				+ "  -to    Timeout (in hours) for the computation of solutions\n"
//				+ "  -sor   use stochastic over-relaxation");
//	}
//
//	/**
//	 * Check if the folder exists and we have write permissions.
//	 * @param folder the folder to check
//	 * @return true if the folder exists and we have write permissions
//	 */
//	private static boolean checkFolder(final File folder) {
//		if (!folder.isDirectory()) {
//			System.err.println(folder + " is not a directory.");
//			return false;
//		}
//		if (!folder.canRead()) {
//			System.err.println("No permissions to read from " + folder);
//			return false;
//		}
//		if (!folder.canWrite()) {
//			System.err.println("No permissions to write in " + folder);
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * Recursively walk through subdirectories listing Aemilia files.
//	 * @param folder starting folder
//	 * @return array of aemilia file paths
//	 */
//	private static Set<File> listFilesRecursively(final File folder) {
//		Set<File> files = new HashSet<File>();
//		if (folder == null || folder.listFiles() == null){
//	        return files;
//	    }
//		for (File entry : folder.listFiles()) {
//	        if (entry.isFile() && entry.getName().endsWith(".aem")) {
//	        	files.add(entry);
//	        } else if (entry.isDirectory()) {
//	        	files.addAll(listFilesRecursively(entry));
//	        }
//	    }
//		return files;
//	}
//
//	/**
//	 * Perform the availability analysis on all
//	 * the Aemilia files in a folder.
//	 * @param folder the folder containing aemilia files
//	 */
//	public static void performAnalysis(final File folder) {
//
//		// Check if the folder exists and we have write permissions
//		if (!checkFolder(folder)) {
//			return;
//		}
//
//		// Start to parse and compute the solutions
//		final Set<File> aemFiles = listFilesRecursively(folder);
//		final File[] files = aemFiles.toArray(new File[aemFiles.size()]);
//		final AnalysisRunnable[] workers = new AnalysisRunnable[files.length];
//		ExecutorService executor = Executors.newFixedThreadPool(
//				NUMBER_OF_THREADS == 0 ? files.length : NUMBER_OF_THREADS);
//		for (int i = 0; i < files.length; i++) {
//			workers[i] = new AnalysisRunnable(files[i]);
//			if (ttkernel != null) {
//				workers[i].setTTKernel(ttkernel);
//			}
//			executor.execute(workers[i]);
//		}
//		executor.shutdown();
//		try {
//			if (TIMEOUT > 0 && !executor.awaitTermination(TIMEOUT, TimeUnit.HOURS)) {
//				executor.shutdownNow();
//			} else {
//				executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//			}
//		} catch (InterruptedException e) {
//			System.err.println("Analysis execution was interrupted.");
//			executor.shutdownNow();
//			Thread.currentThread().interrupt();
//			e.printStackTrace();
//		} catch (OutOfMemoryError e) {
//			Matcher m = Pattern.compile("pool-[0-9]-thread-([0-9]+)").matcher(e.getMessage());
//			if (m.find()) {
//				System.err.println("Out of memory: " + workers[Integer.parseInt(m.group(1))].getAemFile());
//			} else {
//				System.err.println(e.getMessage());
//			}
//		}
//
//		for (int i = 0; i < files.length; i++) {
//			if (AnalysisRunnable.AVAILABILITY_FULL) {
//				System.out.println(
//						files[i]
//						+ " - Availability (all operational components): "
//						+ workers[i].getAnalysis().getFullyOperationalAvailability());
//			}
//
//			if (AnalysisRunnable.AVAILABILITY_DEGRADED) {
//				System.out.println(
//						files[i]
//						+ " - Availability (at least one operational component): "
//						+ workers[i].getAnalysis().getDegradedAvailability());
//			}
//		}
//	}
//
//	/**
//	 * Perform the availability analysis on all
//	 * the Aemilia files in a folder.
//	 * @param folder the folder containing aemilia files
//	 * @param ttkernel TTKernel file
//	 */
//	public static void performAnalysis(final File folder, final File ttkernel) {
//		Launcher.ttkernel = ttkernel;
//		performAnalysis(folder);
//	}
//
//	/**
//	 * Invoked when executed from the command line.
//	 * @param args launch without args to get help
//	 */
//	public static void main(String[] args) {
//		if (args.length < 2) {
//			printUsage();
//			return;
//		}
//
//		List<String> argsList = Arrays.asList(args);
//		if (!argsList.contains("-f") && !argsList.contains("-d")) {
//			System.err.println("Please select an availability index (use -f, -d or both).");
//			printUsage();
//			return;
//		}
//
//		AnalysisRunnable.AVAILABILITY_FULL = argsList.contains("-f");
//		AnalysisRunnable.AVAILABILITY_DEGRADED = argsList.contains("-d");
//		AnalysisRunnable.SKIP_PSM_CREATION = argsList.contains("-sp");
//		AnalysisRunnable.SKIP_AVA_CREATION = argsList.contains("-sa");
//		AnalysisRunnable.SOR = argsList.contains("-sor");
//
//		if (argsList.contains("-t")) {
//			ttkernel = new File(argsList.get(argsList.indexOf("-t") + 1));
//		}
//
//		if (argsList.contains("-n")) {
//			NUMBER_OF_THREADS = Integer.parseInt(argsList.get(argsList.indexOf("-n") + 1));
//		}
//
//		if (argsList.contains("-to")) {
//			TIMEOUT = Integer.parseInt(argsList.get(argsList.indexOf("-to") + 1));
//		}
//
//		final File folder = new File(args[args.length - 1]);
//
//		performAnalysis(folder);
//	}
}
