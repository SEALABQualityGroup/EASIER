package it.univaq.disim.sealab.metaheuristic.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileUtilsTest {

	@BeforeClass
	public static void setupClass() throws IOException {
		Files.createDirectories(Configurator.eINSTANCE.getOutputFolder());
	}

	@AfterClass
	public static void tearDownClass() throws IOException {
		Files.walk(Configurator.eINSTANCE.getOutputFolder())
	    .sorted(Comparator.reverseOrder())
	    .map(Path::toFile)
	    .forEach(File::delete);
		
	}

	@Test
	public void solutionDumpTestIfDumpFileContains2Lines() throws IOException {
		String line = "NSGA-II,problemTest,1,0.15,7,9,0.99";
		new FileUtils().solutionDumpToCSV(line);
		
		//Check the correct header
		String header = "algorithm,problem_tag,solID,perfQ,#changes,pas,reliability";
		assertEquals(header, extractLineFromFile(Configurator.eINSTANCE.getOutputFolder().resolve("solution_dump.csv")));
		assertEquals(7, header.split(",").length);
		
		LineNumberReader lnr = new LineNumberReader(
				new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("solution_dump.csv").toString()));
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == 2);

	}

	@Test
	public void searchBudgetDumpTestIfDumpFileContains2Lines() throws IOException {
		String line = "NSGAII,simplified-cocome__BRF_clone_1.23__moc_1.23__mcnn_1.23__moncnn_1.23__MaxEval_12__ProbPAs_0.00,byPrematureConvergence,1,12";
		new FileUtils().searchBudgetDumpToCSV(line);
		
		//Check the correct header
		String header = "algorithm,problem_tag,search_budget,iteration,max_iteration";
		assertEquals(header, extractLineFromFile(Configurator.eINSTANCE.getOutputFolder().resolve("search_budget_stats.csv")));
		assertEquals(5, header.split(",").length);
		
		LineNumberReader lnr = new LineNumberReader(
				new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("search_budget_stats.csv").toString()));
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == 2);
	}

	private String extractLineFromFile(Path file) throws IOException {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
			line = br.readLine(); // Read the first line, and it should be the header
		}
		assertNotEquals("", line);
		return line;
	}

	@Test
	public void algoPerfDumpTestIfDumpFileContains2Lines() throws IOException {
		String line = "PESA2,simplified-cocome__BRF_clone_1.23__moc_1.64__mcnn_1.45__moncnn_1.80__MaxEval_102__ProbPAs_0.95,86805,622854144,449879328,1363148800,969885368";
		new FileUtils().algoPerfStatsDumpToCSV(line);

		//Check the correct header
		String EXPECTED_HEADER = "algorithm,problem_tag,execution_time(ms),total_memory_before(B),free_memory_before(B),total_memory_after(B),free_memory_after(B)";
		String header = extractLineFromFile(Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv"));
		assertEquals(EXPECTED_HEADER, header);
		
		assertEquals(7, header.split(",").length);
		
		LineNumberReader lnr = new LineNumberReader(
				new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv").toString()));
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == 2);
	}

}
