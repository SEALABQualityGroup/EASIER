package it.univaq.disim.sealab.availability.aemilia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Jama.Matrix;

/**
 * Computes stationary distribution and availability
 * indices from a CTMC obtained by parsing a .PSM file.
 */
public class Analysis {

	private Parser parser;

	private Matrix transitionMatrix;

	private Matrix stationaryDistribution;

	private double fullyOperationalAvailability;

	private double degradedAvailability;

	/**
	 * Creates a new instance of the Analysis object
	 * related to an Aemilia specification.
	 * @param aemFile Aemilia file
	 */
	public Analysis(final File aemFile) {
		this.parser = new Parser(aemFile);
	}

	/**
	 * Returns the parses associated to this Analysis.
	 * @return the associated parser
	 */
	public Parser getParser() {
		return parser;
	}

	/**
	 * Set the TTKernel executable path
	 * @param ttkernel TTKernel path
	 */
	public void setTTKernel(final File ttkernel) {
		parser.setTTKernel(ttkernel);
	}

	/**
	 * Generate the .PSM file
	 * @throws IOException
	 */
	public void generatePSM() throws IOException {
		parser.generatePSM();
	}

	/**
	 * Parses the .PSM file to get the transition matrix.
	 */
	public void parse() {
		transitionMatrix = parser.parsePSM();
	}

	/**
	 * Balance the matrix as flux equations.
	 */
	private void balanceMatrix() {
		for (int i = 0; i < transitionMatrix.getRowDimension(); i++) {
			double sum = 0;
			for (int j = 0; j < transitionMatrix.getColumnDimension() - 1; j++) {
				sum += transitionMatrix.get(i, j);
			}
			transitionMatrix.set(i, i, -sum);
			sum = 0;
		}
		for (int i = 0; i < transitionMatrix.getRowDimension(); i++) {
			transitionMatrix.set(i, transitionMatrix.getColumnDimension() - 1, 1.0);
		}
	}

	/**
	 * Computes the stationary distribution of the CTMC.
	 * @return the stationary distribution
	 */
	public Matrix getStationaryDistribution() {
		if (stationaryDistribution == null) {
			balanceMatrix();
			final Matrix b = new Matrix(new double[1][transitionMatrix.getColumnDimension()]);
			b.set(0, b.getColumnDimension() - 1, 1.0);
			stationaryDistribution = transitionMatrix.solveTranspose(b);
			// Make the transition matrix eligible for GC
			transitionMatrix = null;
			System.gc();
		}
		return stationaryDistribution;
	}

	/**
	 * Computes the availability of the system considering the system
	 * available when no failures occur in any component.
	 * @return steady state availability
	 */
	public double getFullyOperationalAvailability() {
		if (fullyOperationalAvailability == 0) {
			int i = 0;
			for (Iterator<String> states = parser.getStates().iterator(); states.hasNext(); i++) {
				if (!states.next().contains(".fail")) {
					fullyOperationalAvailability += stationaryDistribution.get(i, 0);
				}
			}
		}
		return fullyOperationalAvailability;
	}

	/**
	 * Computes the availability of the system considering the systems
	 * available until all its components fail.
	 * @return steady state availability
	 */
	public double getDegradedAvailability() {
		if (degradedAvailability == 0) {
			int i = 0;
			for (Iterator<String> states = parser.getStates().iterator(); states.hasNext(); i++) {
				if (states.next().matches(".*\\.(?!fail).*")) {
					degradedAvailability += stationaryDistribution.get(i, 0);
				}
			}
		}
		return degradedAvailability;
	}

	/**
	 * Read the fully operational availability from file.
	 * @param avaFile File containing availability measures
	 * @return fully operational availability
	 */
	public double readFullyOperationalAvailability(final File avaFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(avaFile))) {
			Pattern p = Pattern.compile("Availability \\(all operational components\\): ([0-9\\.]+)");
			String line;
			Matcher m;
			while ((line = br.readLine()) != null) {
				m = p.matcher(line);
				if (m.find()) {
					fullyOperationalAvailability = Double.parseDouble(m.group(1));
					return fullyOperationalAvailability;
				}
			}
		} catch (IOException e) {
			System.err.println("Unable to read from file: " + avaFile);
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Read the degraded availability from file.
	 * @param avaFile File containing availability measures
	 * @return degraded availability
	 */
	public double readDegradedAvailability(final File avaFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(avaFile))) {
			Pattern p = Pattern.compile("Availability \\(at least one operational component\\): ([0-9\\.]+)");
			String line;
			Matcher m;
			while ((line = br.readLine()) != null) {
				m = p.matcher(line);
				if (m.find()) {
					degradedAvailability = Double.parseDouble(m.group(1));
					return degradedAvailability;
				}
			}
		} catch (IOException e) {
			System.err.println("Unable to read from file: " + avaFile);
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Make the parser, transition matrix and
	 * stationary distribution eligible for GC.
	 */
	public void clean() {
		parser = null;
		transitionMatrix = null;
		stationaryDistribution = null;
		System.gc();
	}
}
