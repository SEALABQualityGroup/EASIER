package it.univaq.disim.sealab.metaheuristic;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.lab.experiment.util.ExperimentProblem;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class LauncherTest {

	String modelPath;

	@Before
	public void setUp() {

		modelPath = getClass().getResource("/models/model/automatedGuidedVehicle.uml").getFile();

	}
	
	@Test
	public void invokeSolverTest() {
		Path modelPath = Paths.get(getClass().getResource("/models/simplified-cocome/cocome.uml").getFile());
		Launcher.applyTransformation(modelPath);
		Launcher.invokeSolver(modelPath);  
	}

	@Test
	public void createProblemsTest() {
		int eval = 12;

		Launcher launcher = new Launcher();

		List<RProblem<UMLRSolution>> rProblems = Launcher.createProblems(Paths.get(modelPath), eval);
		
		assertTrue("model__BRF_1.23__1.23__1.23__1.23__MaxEval_12__ProbPAs_0.95__sb_none_sbth_3600000__Algo_nsgaii"
				.equals(rProblems.get(0).getName()));

	}

	@Test
	public void configureAlgorithmListTest() {

		int eval = 12;
		List<ExperimentProblem<UMLRSolution>> problemList = new ArrayList<>();
		Launcher.createProblems(Paths.get(modelPath), eval)
				.forEach(problem -> problemList.add(new ExperimentProblem<UMLRSolution>(problem)));

		List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algoList = Launcher
				.configureAlgorithmList(problemList, eval);

		assertTrue(algoList.size() == Configurator.eINSTANCE.getIndependetRuns());

	}

	@After
	public void tearDown() {
	}

}
