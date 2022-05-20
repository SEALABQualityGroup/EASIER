package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class UMLRSolutionListEvaluatorTest {

    UMLRSolution sol;

    UMLRProblem<UMLRSolution> problem;

    UMLRSolutionListEvaluator<UMLRSolution> solutionListEvaluator;

    @BeforeEach
    void setUp() throws IOException {

        problem = new UMLRProblem<>(Paths.get(getClass().getResource("/models/simplified-cocome/cocome.uml").getFile()),
                "simplied-cocome__test");
        sol = problem.createSolution();

        solutionListEvaluator = new UMLRSolutionListEvaluator<>();

        Files.createDirectories(Configurator.eINSTANCE.getOutputFolder());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(Configurator.eINSTANCE.getOutputFolder())
	    .sorted(Comparator.reverseOrder())
	    .map(Path::toFile)
	    .forEach(File::delete);
    }

    @Test
    void evaluate() {

        solutionListEvaluator.evaluate(new ArrayList<>() {{
            add(sol);
        }}, problem);

        assertNotEquals(0, sol.getPAs(), "Expected PAs != 0");
        assertNotEquals(0, sol.getReliability(), "Expected reliability != 0");
        assertNotEquals(0, sol.getArchitecturalChanges(), "Expected architectural changes != 0");
        assertNotEquals(0, sol.getPerfQ(), "Expected perfq != 0");

    }

    @Test
    void testEvaluate() {
    }
}