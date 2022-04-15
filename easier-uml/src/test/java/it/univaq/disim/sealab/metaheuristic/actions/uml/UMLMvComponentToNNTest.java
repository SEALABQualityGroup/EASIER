package it.univaq.disim.sealab.metaheuristic.actions.uml;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UMLMvComponentToNNTest extends RefactoringActionTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();

        action = new UMLMvComponentToNN(solution);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToCSV() {
        numberOfCSVField = 3;
        actionName = "Move_Component_New_Node";
        super.testToCSV();

    }

}