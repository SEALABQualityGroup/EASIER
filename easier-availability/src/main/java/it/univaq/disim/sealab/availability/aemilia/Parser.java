package it.univaq.disim.sealab.availability.aemilia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Jama.Matrix;
import net.axiak.runtime.SpawnRuntime;

/**
 * Generates a .PSM from .AEM file (using TTKernel)
 * contaning an Aemilia specification.
 * Parses the .PSM to build a CTMC.
 */
public class Parser {
	
	final static private String CONFIG_FILE = "config.properties";
	
	private File aemFile;
	
	private File ttkernel;

	private Properties prop = new Properties();

	private ArrayList<String> states;
	
	/**
	 * Creates an instance of PSMParser.
	 * @param aemFile Aemilia file
	 */
	public Parser(final File aemFile) {
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
	public void setTTKernel(final File ttkernel) {
		this.ttkernel = ttkernel;
	}

	/**
	 * Returns the states labels.
	 * @return
	 */
	public ArrayList<String> getStates() {
		return states;
	}
	
	/**
	 * Get the name of the .PSM file.
	 * @return File object corresponding to the .PSM file
	 */
	public File getPSMFile() {
		return new File(aemFile.getAbsolutePath() + ".psm");
	}
	
	/**
	 * Loads config parameters from CONFIG_FILE.
	 * @throws IOException 
	 */
	private void loadConfig() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);
		if (is != null) {
			prop.load(is);
		} else {
			throw new FileNotFoundException("Property file not found: " + CONFIG_FILE);
		}
		this.ttkernel = new File(prop.getProperty("ttkernel"));
	}
	
	/**
	 * Generates a .PSM containing a CTMC from an Aemilia specification.
	 * (uses TTKernel)
	 * @throws IOException 
	 */
	public void generatePSM() throws IOException {
		if (aemFile.exists()) {
			final String filename = aemFile.getAbsolutePath();
			try {
				SpawnRuntime.getInstance().exec(
						String.format("%s -g %s %s.tmp", ttkernel, filename, filename))
				.waitFor();
			} catch (InterruptedException e) {
				System.err.println("The execution of TTKernel was interrupted.");
				e.printStackTrace();
			}			
		} else {
			throw new FileNotFoundException("Aemilia file not found: " + aemFile);
		}
	}
	
	/**
	 * Parse the .PSM file from the end to get
	 * total number of states in the CTMC.
	 * @return number of states
	 * 	(0 if the method is unable to parse the file)
	 */
	public int getNumberOfStates() {
		final File psmFile = getPSMFile();
		
		try (RandomAccessFile file = new RandomAccessFile(psmFile, "r")) {
			
			// Counts the number of '>'
			int counter = 0;
			
			// Move to the end of the file
			long pos = file.length() - 1;
			file.seek(pos);
			
			// Start reading backward until we find 3 '\n>'
			for (char c1,c2; pos >=0 && counter < 2; pos -= 1) {
				file.seek(pos);
				c1 = (char) file.read();
				c2 = (char) file.read();
				if (c1 == '\n' && c2 == '>') {
					counter++;
				}
			}
			
			// Now read the entire line that should
			// contain the last global state number
			Matcher m = Pattern.compile("([0-9]+) states").matcher(file.readLine());
			if (m.find()) {
				return Integer.parseInt(m.group(1));
			}
		} catch (IOException e) {
			System.err.println("Unable to read from file: " + psmFile);
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Parses a .PSM file into a matrix.
	 * @return CTMC transition matrix
	 */
	public Matrix parsePSM() {
		
		try {
			if (ttkernel == null) {
				loadConfig();
			}
			generatePSM();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		final File psmFile = getPSMFile();
		
		final Pattern globalStatePattern = Pattern.compile("Global state ([^:]+):");
		final Pattern localStatePattern = Pattern.compile("Local state of ([^:]+):");
		final Pattern portPattern = Pattern.compile("<[^\\.]+(\\.[^, ]+)");
		final Pattern transitionPattern = Pattern.compile("Rate:\\s+([0-9]+\\.?[0-9]*)");
		final Pattern targetStatePattern = Pattern.compile("Derivative global state:\\s+([0-9]+)");
		
		states = new ArrayList<String>();
		
		int numberOfStates = getNumberOfStates();
		
		Matrix matrix = new Matrix(new double[numberOfStates][numberOfStates+1]);
		
		try (final BufferedReader br = new BufferedReader(new FileReader(psmFile))) {			
			String line;
			int sourceState = 0;
			String sourceStateLabel = "";
			double rate = 0;
			boolean firstTransition = true;
			Matcher m;
			while ((line = br.readLine()) != null) {
				
				// Global state
				m = globalStatePattern.matcher(line);
				if (m.find()) {
					sourceState = Integer.parseInt(m.group(1));
					sourceStateLabel = sourceState + ": ";
					firstTransition = true;
					continue;
				}
				
				// Local state
				m = localStatePattern.matcher(line);
				if (m.find()) {
					sourceStateLabel += m.group(1);
					continue;
				}
				
				// Element port
				m = portPattern.matcher(line);
				if (m.find()) {
					sourceStateLabel += m.group(1) + ", ";
					continue;
				}
				
				// Transition
				m = transitionPattern.matcher(line);
				if (m.find()) {
					if (firstTransition) {
						firstTransition = false;
						states.add(sourceStateLabel.substring(0, sourceStateLabel.length() - 2));
					}
					rate = Double.parseDouble(m.group(1));
					continue;
				}
				
				// Target state
				m = targetStatePattern.matcher(line);
				if (m.find()) {
					matrix.set(sourceState - 1, Integer.parseInt(m.group(1)) - 1, rate);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + psmFile);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to read: " + psmFile);
			e.printStackTrace();
		}

		return matrix;
	}
}
