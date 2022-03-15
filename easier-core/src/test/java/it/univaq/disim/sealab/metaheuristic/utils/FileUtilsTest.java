package it.univaq.disim.sealab.metaheuristic.utils;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileUtilsTest {
	
	@Before
	public void setup() {}

	@After
	public void tearDown() throws IOException {	}
	
	@Test
	public void solutionDumpTestIfDumpFileContains2Lines() throws IOException {
		String line = "NSGA-II,problemTest,1,0.15,7,9,0.99";
		//new FileUtils().solutionDumpToCSV(line);		
		LineNumberReader lnr = new LineNumberReader(new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("solution_dump.csv").toString()));
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == 2);
		Files.delete(Configurator.eINSTANCE.getOutputFolder().resolve("solution_dump.csv"));
		
	}
	
	@Test
	public void searchBudgetDumpTestIfDumpFileContains2Lines() throws IOException {
		String line = "NSGAII,simplified-cocome__BRF_clone_1.23__moc_1.23__mcnn_1.23__moncnn_1.23__MaxEval_12__ProbPAs_0.00,byPrematureConvergence,1,12";
		//new FileUtils().searchBudgetDumpToCSV(line);		
		LineNumberReader lnr = new LineNumberReader(new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("search_budget_stats.csv").toString()));
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == 2);
		Files.delete(Configurator.eINSTANCE.getOutputFolder().resolve("search_budget_stats.csv"));
	}

	@Test
	public void algoPerfDumpDumpTestIfDumpFileContains2Lines() throws IOException {
		String line = "PESA2,simplified-cocome__BRF_clone_1.23__moc_1.64__mcnn_1.45__moncnn_1.80__MaxEval_102__ProbPAs_0.95,86805,622854144,449879328,1363148800,969885368";
		//new FileUtils().algoPerfStatsDumpToCSV(line);		
		LineNumberReader lnr = new LineNumberReader(new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv").toString()));
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == 2);
		Files.delete(Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv"));
	}
	
}
