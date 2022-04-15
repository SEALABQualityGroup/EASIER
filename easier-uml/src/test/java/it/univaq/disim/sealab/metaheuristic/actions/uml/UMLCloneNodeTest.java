package it.univaq.disim.sealab.metaheuristic.actions.uml;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

public class UMLCloneNodeTest extends RefactoringActionTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();

        action = new UMLCloneNode(solution);
    }

    @Test
    public void testToCSV() {
//        String generatedCSV = action.toCSV();
//        System.out.println(generatedCSV);
        numberOfCSVField = 3;
        actionName = "UMLCloneNode";
        super.testToCSV();
    }
}