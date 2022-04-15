package it.univaq.disim.sealab.metaheuristic.actions.uml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UMLMvOperationToNCToNNTest extends RefactoringActionTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();

        action = new UMLMvOperationToNCToNN(solution);
    }

    @Test
    public void testToCSV() {
        numberOfCSVField = 4;
        actionName = "Move_Operation_New_Component_New_Node";
        super.testToCSV();
    }
}