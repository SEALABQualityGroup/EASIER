package it.univaq.disim.sealab.metaheuristic.utils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileUtils {

	public FileUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Recursively walk through subdirectories listing Aemilia files.
	 * @param folder starting folder
	 * @return array of aemilia file paths
	 */
	public static Set<File> listFilesRecursively(final File folder) {
		Set<File> files = new HashSet<File>();
		if (folder == null || folder.listFiles() == null){
	        return files;
	    }
		for (File entry : folder.listFiles()) {
	        if (entry.isFile() && entry.getName().endsWith(".tsv")) {
	        	files.add(entry);
	        } else if (entry.isDirectory()) {
	        	files.addAll(listFilesRecursively(entry));
	        }
	    }
		return files;
	}
	
	/**
	 * Recursively walk through subdirectories listing Aemilia files.
	 * @param folder starting folder
	 * @return array of aemilia file paths
	 */
	public static Set<File> listFilesRecursively(final File folder, String extension) {
		Set<File> files = new HashSet<File>();
		if (folder == null || folder.listFiles() == null){
	        return files;
	    }
		for (File entry : folder.listFiles()) {
	        if (entry.isFile() && entry.getName().endsWith(extension)) {
	        	files.add(entry);
	        } else if (entry.isDirectory()) {
	        	files.addAll(listFilesRecursively(entry));
	        }
	    }
		return files;
	}
	
}
