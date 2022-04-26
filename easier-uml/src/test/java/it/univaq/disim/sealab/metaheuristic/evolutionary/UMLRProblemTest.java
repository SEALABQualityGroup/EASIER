package it.univaq.disim.sealab.metaheuristic.evolutionary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class UMLRProblemTest {

    UMLRProblem<UMLRSolution> problem;

    @BeforeEach
    void setUp() {
        problem =
                new UMLRProblem<>(Paths.get(getClass().getResource("/models/simplified-cocome/cocome.uml").getFile())
                        , "simplied-cocome__test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createSolution() {
        UMLRSolution sol = problem.createSolution();

        assertNotNull(sol, "Expected non null solution");
        assertTrue(sol.isFeasible(), "Expected a feasible solution");
    }

    @Test
    void evaluate() {
        UMLRSolution sol = problem.createSolution();
        problem.evaluate(sol);

        assertEquals(0, sol.getPAs());
        assertEquals(0d, sol.getReliability());
        assertEquals(0, sol.getArchitecturalChanges());
        assertEquals(0d, sol.getPerfQ());

    }
}