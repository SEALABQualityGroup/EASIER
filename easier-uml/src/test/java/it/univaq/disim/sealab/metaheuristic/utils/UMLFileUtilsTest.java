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
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;


public class UMLFileUtilsTest {

	UMLRSolution sol;
	UMLRProblem<RSolution<?>> p;

	@Before
	public void setup() {
		int allowedFailures = 100;
		int desired_length = 4;
		int populationSize = 4;
		
		String modelpath = getClass().getResource("/models/simplified-cocome/cocome.uml").getFile();
		UMLRProblem<RSolution<?>> p = new UMLRProblem<>(Paths.get(modelpath),"simplied-cocome__test");
		sol = (UMLRSolution) p.createSolution();
	}	
	
	@BeforeClass
	public static void beforeClass() throws IOException {
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
	public void backAnnotationErrorLogToCSVTest() throws IOException {

		String expectedLine = "1,error_message," + sol.getVariable(0).toString();
		new FileUtils().backAnnotationErrorLogToCSV(expectedLine);

		String line = "";
		String header = "";
		Path file = Configurator.eINSTANCE.getOutputFolder().resolve("back_annotation_error_log.csv");
		// Check the correct header

		String EXPECTED_HEADER = "solID,message,actions";
		try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {

			header = br.readLine();
			line = br.readLine(); // Read the first line, and it should be the header
		}

		assertNotEquals("", header);
		assertEquals(EXPECTED_HEADER, header);
		assertEquals(3, header.split(",").length);

		assertNotEquals("", line);
//		assertEquals(4, line.split(",")[2].split(";").length);

		LineNumberReader lnr = new LineNumberReader(new FileReader(
				Configurator.eINSTANCE.getOutputFolder().resolve("back_annotation_error_log.csv").toString()));
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == 2);
	}

}
