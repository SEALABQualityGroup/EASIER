package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UMLRCrossoverTest {

    UMLRCrossover xOver;

    // we must guarantee that the xOver operation will be executed
    double xOverProb = 1;
    Path modelPath;

    @BeforeEach
    public void setUp() throws Exception {
        xOver = new UMLRCrossover(xOverProb);
        modelPath = Paths.get(getClass().getResource("/models/simplified-cocome/cocome.uml").getPath());
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void execute() {

        Path modelPath = Paths.get(getClass().getResource("/models/simplified-cocome/cocome.uml").getPath());
        UMLRProblem<RSolution<?>> p = new UMLRProblem<>(modelPath,"simplied-cocome__test");

        UMLRSolution parent1 = new UMLRSolution(modelPath, "simplied-cocome__test");
        parent1.createRandomRefactoring();
        UMLRSolution parent2 = new UMLRSolution(modelPath, "simplied-cocome__test");
        parent2.createRandomRefactoring();

        List<UMLRSolution> population = xOver.execute(List.of(parent1, parent2));
        assertNotNull(population);

        if(population.get(0).isCrossover())
            assertNotEquals(parent1, population.get(0),String.format("%s \t %s",parent1, population.get(0)));

        for(UMLRSolution sol : population){
            System.out.println(sol.getVariable(0).toCSV());
        }
    }

    @Test
    void independentSequence() {
        UMLRSolution sol = new UMLRSolution(modelPath, "simplied-cocome__test");
        sol.createRandomRefactoring();
        xOver.independentSequence(sol);
    }
}