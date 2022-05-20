package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class RMutationTest {

    UMLRSolution solution;

    @BeforeEach
    void setUp() {
        String modelPath = getClass().getResource("/models/simplified-cocome/cocome.uml").getPath();
        solution = new UMLRSolution(Paths.get(modelPath), "simplified-cocome__test");
        solution.createRandomRefactoring();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void execute() {
        RMutation<UMLRSolution> mutationOperator = new UMLRMutation(1, Configurator.eINSTANCE.getDistributionIndex());
        mutationOperator.execute(solution);
    }
}