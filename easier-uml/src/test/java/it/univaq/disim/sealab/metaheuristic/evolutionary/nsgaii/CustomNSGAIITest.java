package it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
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
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;

public class CustomNSGAIITest<S extends RSolution<?>> {
	UMLRProblem<UMLRSolution> p;

	List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algorithms = new ArrayList<>();
	final CrossoverOperator<UMLRSolution> crossoverOperator = new UMLRCrossover<>(
			Configurator.eINSTANCE.getXoverProbabiliy());
	final MutationOperator<UMLRSolution> mutationOperator = new RMutation<>(
			Configurator.eINSTANCE.getMutationProbability(), Configurator.eINSTANCE.getDistributionIndex());
	final SelectionOperator<List<UMLRSolution>, UMLRSolution> selectionOpertor = new BinaryTournamentSelection<UMLRSolution>(
			new RankingAndCrowdingDistanceComparator<UMLRSolution>());
	final SolutionListEvaluator<UMLRSolution> solutionListEvaluator = new UMLRSolutionListEvaluator<>();
	NSGAII<UMLRSolution> algorithm;

	@Before
	public void setUp() {
		int allowedFailures = 100;
		int desired_length = 4;
		int populationSize = 4;
		int number_of_action = 5;

		String modelpath = getClass().getResource("/models/train-ticket/train-ticket.uml").getFile();
		p = new UMLRProblem<>(Paths.get(modelpath), desired_length, number_of_action, allowedFailures, populationSize);
		p.setName("problem_for_testing");

		NSGAIIBuilder<UMLRSolution> customNSGABuilder = new CustomNSGAIIBuilder<UMLRSolution>(p, crossoverOperator,
				mutationOperator, Configurator.eINSTANCE.getPopulationSize()).setMaxEvaluations(72)
						.setSolutionListEvaluator(solutionListEvaluator);

		algorithm = customNSGABuilder.build();
	}

	@Test
	public void isLocalOptimalPointSolutionWithListOfSolution() {
		List<UMLRSolution> solutions = new ArrayList<UMLRSolution>();
		int i = 0;
		while (i < 10) {
			UMLRSolution sol = (UMLRSolution) p.createSolution();
			sol.setPerfQ(-10);
			sol.setReliability(-10);
			sol.setPAs(0);
			sol.getVariable(0).setNumOfChanges(10);
			sol.getVariable(0).setNumOfChanges(10);
			solutions.add(sol);
			i++;
		}

		algorithm.setPopulation(solutions);
		((CustomNSGAII<UMLRSolution>) algorithm).oldPopulation = solutions;

		assertFalse(((CustomNSGAII<UMLRSolution>) algorithm).isStagnantState());
	}

	@Test
	public void isLocalOptimalPointSolutionWithListOfSolutionShouldReturnFalse() {
		List<UMLRSolution> solutions = new ArrayList<UMLRSolution>();
		int i = 0;
		while (i < 10) {
			UMLRSolution sol = (UMLRSolution) p.createSolution();
			sol.setPerfQ(-10);
			sol.setReliability(-10);
			sol.setPAs(0);
			sol.getVariable(0).setNumOfChanges(10);
			sol.getVariable(0).setNumOfChanges(10);
			solutions.add(sol);
			i++;
		}
		algorithm.setPopulation(solutions);

		solutions = new ArrayList<UMLRSolution>();

		i = 0;
		while (i < 10) {
			UMLRSolution sol = (UMLRSolution) p.createSolution();
			sol.setPerfQ(-10);
			sol.setReliability(-10);
			sol.setPAs(0);
			if (i % 2 == 0)
				sol.setPAs(10);
			sol.getVariable(0).setNumOfChanges(10);
			sol.getVariable(0).setNumOfChanges(10);
			solutions.add(sol);
			i++;
		}

		((CustomNSGAII<UMLRSolution>) algorithm).oldPopulation = solutions;

		assertTrue(((CustomNSGAII<UMLRSolution>) algorithm).isStagnantState());
	}
	
	
	@Test
	public void populationToCsVTest() throws IOException {
		UMLRSolution sol = p.createSolution();
		sol.setPerfQ(-10);
		sol.setReliability(-10);
		sol.setPAs(0);
		sol.getVariable(0).setNumOfChanges(10);
		algorithm.setPopulation(List.of(sol));
		
		((CustomNSGAII<UMLRSolution>) algorithm).populationToCSV();
		
		LineNumberReader lnr = new LineNumberReader(new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("solution_dump.csv").toString()));
		lnr.lines().count();
		assertTrue(lnr.getLineNumber() == 2);
		Files.delete(Configurator.eINSTANCE.getOutputFolder().resolve("solution_dump.csv"));
	}
	
}
