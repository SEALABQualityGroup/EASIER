package it.univaq.disim.sealab.metaheuristic.evolutionary.factory;

import it.univaq.disim.sealab.metaheuristic.Launcher;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RSolutionListEvaluator;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.UMLRCrossover;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.UMLRMutation;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.lab.experiment.util.ExperimentProblem;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FactoryBuilderTest {

    Path modelPath;

    @Before
    public void setUp() throws Exception {
        modelPath = Paths.get(getClass().getResource("/models/model/automatedGuidedVehicle.uml").getFile());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createQualityIndicatorTest(){
        GenericIndicator<UMLRSolution> indicator = new FactoryBuilder<UMLRSolution>().createQualityIndicators("HYPER_VOLUME");
        assertNotNull("Expected non null indicator",indicator);
        String expectedName = "HV";
        assertEquals(String.format("Expected %s \t created %s",expectedName, indicator.getName()), expectedName, indicator.getName());
    }

    @Test
    public void configureAlgorithmListTest() {
        int eval = 12;
        List<ExperimentProblem<UMLRSolution>> problemList = new ArrayList<>();

        problemList.add(new ExperimentProblem<UMLRSolution>(Launcher.createProblems(modelPath, eval)));

        ExperimentProblem<UMLRSolution> experimentProblem = problemList.get(0);
        CrossoverOperator<UMLRSolution> crossoverOperator = new UMLRCrossover(Configurator.eINSTANCE.getXoverProbabiliy());
        SolutionListEvaluator<UMLRSolution> solutionListEvaluator = new RSolutionListEvaluator<>();
        MutationOperator<UMLRSolution> mutationOperator = new UMLRMutation(Configurator.eINSTANCE.getMutationProbability(), Configurator.eINSTANCE.getDistributionIndex());
        String algo = "nsgaii";

        List<ExperimentAlgorithm<UMLRSolution, List<UMLRSolution>>> experimentAlgorithms = new FactoryBuilder<UMLRSolution>().configureAlgorithmList(
                experimentProblem, eval, crossoverOperator, solutionListEvaluator, mutationOperator, algo
        );

        assertFalse("Expected a not empty experiment algorithm list", experimentAlgorithms.isEmpty());

    }

}