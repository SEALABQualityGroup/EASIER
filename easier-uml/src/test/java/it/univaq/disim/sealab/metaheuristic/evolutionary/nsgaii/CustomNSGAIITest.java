package it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RMutation;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.UMLRCrossover;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.UMLRSolutionListEvaluator;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class CustomNSGAIITest<S extends RSolution<?>> {
	UMLRProblem<UMLRSolution> p;

	List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algorithms = new ArrayList<>();
	final CrossoverOperator<UMLRSolution> crossoverOperator = new UMLRCrossover(
			Configurator.eINSTANCE.getXoverProbabiliy());
	final MutationOperator<UMLRSolution> mutationOperator = new RMutation<>(
			Configurator.eINSTANCE.getMutationProbability(), Configurator.eINSTANCE.getDistributionIndex());
	final SelectionOperator<List<UMLRSolution>, UMLRSolution> selectionOpertor = new BinaryTournamentSelection<UMLRSolution>(
			new RankingAndCrowdingDistanceComparator<UMLRSolution>());
	final SolutionListEvaluator<UMLRSolution> solutionListEvaluator = new UMLRSolutionListEvaluator<>();
	CustomNSGAII<UMLRSolution> algorithm;

	@BeforeClass
	public static void setUpClass() throws IOException  {
		Files.createDirectories(Configurator.eINSTANCE.getOutputFolder());
	}
	
 	
	@Before
	public void setUp(){
		int allowedFailures = 100;
		int desired_length = 4;
		int populationSize = 4;

		String modelpath = getClass().getResource("/models/train-ticket/train-ticket.uml").getFile();
		p = new UMLRProblem<>(Paths.get(modelpath), "problem_for_testing");

		NSGAIIBuilder<UMLRSolution> customNSGABuilder = new CustomNSGAIIBuilder<UMLRSolution>(p, crossoverOperator,
				mutationOperator, Configurator.eINSTANCE.getPopulationSize()).setMaxEvaluations(4)
				.setSolutionListEvaluator(solutionListEvaluator);

		algorithm = (CustomNSGAII<UMLRSolution>) customNSGABuilder.build();
	}

	@AfterClass
	public static void tearDownClass() throws IOException {
		/*Files.deleteIfExists(Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv"));
		Files.deleteIfExists(Configurator.eINSTANCE.getOutputFolder().resolve("solution_dump.csv"));
		Files.deleteIfExists(Configurator.eINSTANCE.getOutputFolder().resolve("refactoring_stats.csv"));
		Files.deleteIfExists(Configurator.eINSTANCE.getOutputFolder().resolve("process_step_stats.csv"));*/
		
		
		Files.walk(Configurator.eINSTANCE.getOutputFolder())
	    .sorted(Comparator.reverseOrder())
	    .map(Path::toFile)
	    .forEach(File::delete);
		
	}
	
	
	@Test
	public void isLocalOptimalPointSolutionWithListOfSolution() {
		List<UMLRSolution> solutions = new ArrayList<>();
		int i = 0;
		while (i < 2) {
			UMLRSolution sol = p.createSolution();
			sol.setPerfQ(-10);
			sol.setReliability(-10);
			sol.setPAs(0);
//			sol.getVariable(0).setNumOfChanges(10);
//			sol.getVariable(0).setNumOfChanges(10);
			solutions.add(sol);
			i++;
		}

		algorithm.setPopulation(solutions);
		algorithm.oldPopulation = solutions;

		assertFalse(algorithm.isStagnantState());
	}

	@Test
	public void isLocalOptimalPointSolutionWithListOfSolutionShouldReturnFalse() {
		List<UMLRSolution> solutions = new ArrayList<>();
		int i = 0;
		while (i < 2) {
			UMLRSolution sol = p.createSolution();
			sol.setPerfQ(-10);
			sol.setReliability(-10);
			sol.setPAs(0);
//			sol.getVariable(0).setNumOfChanges(10);
//			sol.getVariable(0).setNumOfChanges(10);
			solutions.add(sol);
			i++;
		}
		algorithm.setPopulation(solutions);

		solutions = new ArrayList<>();

		i = 0;
		while (i < 2) {
			UMLRSolution sol = p.createSolution();
			sol.setPerfQ(-10);
			sol.setReliability(-10);
			sol.setPAs(0);
			if (i % 2 == 0)
				sol.setPAs(10);
//			sol.getVariable(0).setNumOfChanges(10);
//			sol.getVariable(0).setNumOfChanges(10);
			solutions.add(sol);
			i++;
		}

		algorithm.oldPopulation = solutions;

		assertFalse(algorithm.isStagnantState());
	}

	@Test
	public void updateProgressTest() throws IOException {
		UMLRSolution sol = p.createSolution();
		sol.setPerfQ(-10);
		sol.setReliability(-10);
		sol.setPAs(0);
//		sol.getVariable(0).setNumOfChanges(10);
		algorithm.setPopulation(List.of(sol));

		algorithm.updateProgress();
		Path output = Configurator.eINSTANCE.getOutputFolder().resolve("algo_perf_stats.csv");
		assertTrue("The algo_perf_stats.csv should exist", Files.exists(output));
		
		String header = "algorithm,problem_tag,execution_time(ms),total_memory_before(B),free_memory_before(B),total_memory_after(B),free_memory_after(B)";
		try (BufferedReader br = new BufferedReader(new FileReader(output.toFile()))) {
		    String line = br.readLine();
		    assertEquals(header, line); //The first must be the header
		}

		output = Configurator.eINSTANCE.getOutputFolder().resolve("solution_dump.csv");
		assertTrue("The solution_dump.csv file should exist", Files.exists(output));
		header = "algorithm,problem_tag,solID,perfQ,#changes,pas,reliability";
		try (BufferedReader br = new BufferedReader(new FileReader(output.toFile()))) {
			String line = br.readLine();
			assertEquals(header, line); // The first must be the header
		}
		
		LineNumberReader lnr = new LineNumberReader(
				new FileReader(output.toFile()));
		
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == 2);
	}
	
	
	@Test
	public void runTest() throws IOException {
		algorithm.run();
	}
}
