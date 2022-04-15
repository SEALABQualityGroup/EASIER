package it.univaq.disim.sealab.metaheuristic;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.lab.experiment.util.ExperimentProblem;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

import static org.junit.Assert.*;

public class LauncherTest {

    Path modelPath;

    @Before
    public void setUp() {

        modelPath = Paths.get(getClass().getResource("/models/model/automatedGuidedVehicle.uml").getFile());

    }

    @Test
    public void createProblemsTest() {
        int eval = 12;
        RProblem<UMLRSolution> rProblem = Launcher.createProblems(modelPath, eval);
        String expectedProblemName = "model__BRF_1.23__1.23__1.23__1.23__MaxEval_12__ProbPAs_0.95__sb_none_sbth_3600000__Algo_nsgaii";
        assertNotNull("Created a null problem.", rProblem);
        assertEquals(String.format("Exptected problem name %s \t generated %s", expectedProblemName, rProblem.getName()),
                expectedProblemName, rProblem.getName());
    }

    @Test
    public void configureAlgorithmListTest() {
        int eval = 12;
        List<ExperimentProblem<UMLRSolution>> problemList = new ArrayList<>();
        problemList.add(new ExperimentProblem<UMLRSolution>(Launcher.createProblems(modelPath, eval)));

        List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> algoList = Launcher
                .configureAlgorithmList(problemList, eval);

        assertEquals(String.format("Expected %s \t found %s", Configurator.eINSTANCE.getIndependetRuns(), algoList.size()),
                Configurator.eINSTANCE.getIndependetRuns(), algoList.size());

    }

    @After
    public void tearDown() {
    }

}
