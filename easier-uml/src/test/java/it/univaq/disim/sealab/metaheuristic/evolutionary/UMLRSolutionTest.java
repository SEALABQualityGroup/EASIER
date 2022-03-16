package it.univaq.disim.sealab.metaheuristic.evolutionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.concurrent.TimeUnit;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.junit.After;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class UMLRSolutionTest {

	private UMLRSolution solution, solution2;
	UMLRProblem<RSolution<?>> p;

	private static void logInfo(Description description, String status, long nanos) {
		String testName = description.getMethodName();
		System.out.println(String.format("Test %s %s, spent %d %s", testName, status,
				TimeUnit.NANOSECONDS.toMillis(nanos), TimeUnit.MILLISECONDS.toString()));
	}

	@Rule
	public Stopwatch stopwatch = new Stopwatch() {
		@Override
		protected void succeeded(long nanos, Description description) {
			logInfo(description, "succeeded", nanos);
		}

		@Override
		protected void failed(long nanos, Throwable e, Description description) {
			logInfo(description, "failed", nanos);
		}

		@Override
		protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
			logInfo(description, "skipped", nanos);
		}

		@Override
		protected void finished(long nanos, Description description) {
			logInfo(description, "finished", nanos);
		}
	};

	@Before
	public void setUp() throws URISyntaxException {
		int allowedFailures = 100;
		int desired_length = 4;
		int populationSize = 4;
		
		String modelpath = getClass().getResource("/models/simplified-cocome/cocome.uml").getFile();
		UMLRProblem<RSolution<?>> p = new UMLRProblem<>(Paths.get(modelpath), desired_length, allowedFailures, populationSize);
		p.setName("simplied-cocome__test");
		solution = (UMLRSolution) p.createSolution();
		solution2 = (UMLRSolution) p.createSolution();
	}

	@Test
	public void isLocalOptmimalPointTrueTest() {
		solution.setPerfQ(0);
		solution2.setPerfQ(0);

		solution.reliability = 0;
		solution2.reliability = 0;

		solution.numPAs = 0;
		solution2.numPAs = 0;

		solution.getVariable(0).setNumOfChanges(0);
		solution2.getVariable(0).setNumOfChanges(0);

		assertTrue(solution.isLocalOptmimalPoint(solution2));
	}

	/*
	 * PerfQ of solution2 is greater than the perfQ of solution The test should
	 * return FALSE
	 */
	@Test
	public void isLocalOptmimalPointPerfQOutOfRangeShouldReturnFalseTest() {
		solution.setPerfQ(0);
		solution2.setPerfQ(0);

		solution.reliability = 0;
		solution2.reliability = 0;

		solution.numPAs = 0;
		solution2.numPAs = 4;

		solution.getVariable(0).setNumOfChanges(0);
		solution2.getVariable(0).setNumOfChanges(0);

		assertFalse(solution.isLocalOptmimalPoint(solution2));
	}

	@Test
	public void isLocalOptmimalPointSolutionWithinSolution2ShouldReturnTrueTest() {
		solution.setPerfQ(-10);
		solution2.setPerfQ(-10);

		solution.reliability = -10;
		solution2.reliability = -10;

		solution.numPAs = 1;
		solution2.numPAs = 0;

		solution.getVariable(0).setNumOfChanges(10);
		solution2.getVariable(0).setNumOfChanges(10);

		System.out
				.println(String.format("[Solution] perfQ: %s; rel: %s; numPas: %s; numChanges: %s", solution.getPerfQ(),
						solution.getReliability(), solution.getPAs(), solution.getVariable(0).getNumOfChanges()));
		System.out.println(
				String.format("[Solution2] perfQ: %s; rel: %s; numPas: %s; numChanges: %s", solution2.getPerfQ(),
						solution2.getReliability(), solution2.getPAs(), solution2.getVariable(0).getNumOfChanges()));

		assertTrue(solution.isLocalOptmimalPoint(solution2));
	}

	@Test
	public void coutingPAs() {
		solution.countingPAs();
		
		assertTrue(solution.getPAs() == 12);
	}

	@Test
	public void createRandomRefactoring() {
		solution.createRandomRefactoring();
	}

	@Test
	public void tryRandomPush() throws UnexpectedException, EolRuntimeException {
		solution.tryRandomPush();
	}

	@Test
	public void evaluatePerformance() {
		solution.evaluatePerformance();
	}

	@Test
	public void computeReliability() {
		solution.computeReliability();
	}

	@Test
	public void applyTransformationTest() {
		solution.applyTransformation();
		solution.invokeSolver();
	}

	@Test
	public void refactoringToCSV() throws IOException {
		solution.refactoringToCSV();
		LineNumberReader lnr = new LineNumberReader(
				new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("refactoring_composition.csv").toString()));
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == Configurator.eINSTANCE.getLength() + 1);
		Files.delete(Configurator.eINSTANCE.getOutputFolder().resolve("refactoring_composition.csv"));
	}
	
	@Test
	public void executeRefactoringTest() throws IOException {
		solution.executeRefactoring();
		LineNumberReader lnr = new LineNumberReader(
				new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("refactoring_stats.csv").toString()));
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == Configurator.eINSTANCE.getLength() + 1);
		Files.delete(Configurator.eINSTANCE.getOutputFolder().resolve("refactoring_stats.csv"));
	}

	@After
	public void tearDown() throws IOException {
		solution = null;
		if(Files.exists(Configurator.eINSTANCE.getOutputFolder().resolve("process_step_stats.csv")))
				Files.delete(Configurator.eINSTANCE.getOutputFolder().resolve("process_step_stats.csv"));
		
	}

}
