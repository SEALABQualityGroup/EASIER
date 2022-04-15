package it.univaq.disim.sealab.metaheuristic.actions.uml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UMLMvOperationToCompTest extends RefactoringActionTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();

        action = new UMLMvOperationToComp(solution);
    }

    @Test
    public void testToCSV() {
        numberOfCSVField = 3;
        actionName = "Move_Operation_Component";
        super.testToCSV();
    }
}