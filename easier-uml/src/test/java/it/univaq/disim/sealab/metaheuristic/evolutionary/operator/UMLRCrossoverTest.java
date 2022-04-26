package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uma.jmetal.operator.crossover.CrossoverOperator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class UMLRCrossoverTest {

    UMLRCrossover xOver;

    // we must guarantee that the xOver operation will be executed
    double xOverProb = 1;

    @Before
    public void setUp() throws Exception {
        xOver = new UMLRCrossover(xOverProb);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void execute() {
        int allowedFailures = 100;
        int desired_length = 4;
        int populationSize = 4;

        Path modelPath = Paths.get(getClass().getResource("/models/simplified-cocome/cocome.uml").getPath());
        UMLRProblem<RSolution<?>> p = new UMLRProblem<>(modelPath,"simplied-cocome__test");

        UMLRSolution parent1 = (UMLRSolution) p.createSolution();
        UMLRSolution parent2 = (UMLRSolution) p.createSolution();

        List<UMLRSolution> population = xOver.execute(List.of(parent1, parent2));
        assertNotNull(population);

        if(population.get(0).isCrossovered())
            assertNotEquals(String.format("%s \t %s",parent1, population.get(0)), parent1, population.get(0));

        for(UMLRSolution sol : population){
            System.out.println(sol.getVariable(0).toCSV());
        }
    }
}