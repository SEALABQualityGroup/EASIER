package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.net.URISyntaxException;
import java.nio.file.Path;
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

public class UMLRSolutionTest {

	
	private UMLRSolution solution;
	
	 private static void logInfo(Description description, String status, long nanos) {
         String testName = description.getMethodName();
         System.out.println(String.format("Test %s %s, spent %d %s",
                                   testName, status, TimeUnit.NANOSECONDS.toMillis(nanos), TimeUnit.MILLISECONDS.toString() ));
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
		int number_of_action = 5;
		
		Path modelpath = Paths.get(getClass().getResource("/models/simplified-cocome/cocome.uml").toURI());
		UMLRProblem<RSolution<?>> p = new UMLRProblem<>(modelpath, desired_length, number_of_action, allowedFailures, populationSize);
		solution = (UMLRSolution) p.createSolution();
	}
	
	
	@Test
	public void coutingPAs() {
		solution.countingPAs();
		System.out.println(solution.getPAs());
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
	
	
	@After
	public void tearDown() {
		solution = null;
	}

}
