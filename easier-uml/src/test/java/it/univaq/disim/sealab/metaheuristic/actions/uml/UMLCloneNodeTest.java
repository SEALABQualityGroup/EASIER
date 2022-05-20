package it.univaq.disim.sealab.metaheuristic.actions.uml;

import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.EasierException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UMLCloneNodeTest extends RefactoringActionTest {

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();

        oldAction = new UMLCloneNode(solution.getAvailableElements(), solution.getInitialElements());
        action = new UMLCloneNode(solution.getAvailableElements(), solution.getInitialElements());


    }

    @Test
    public void testConstructor() {
        String targetNode = action.getTargetElements().get(UMLRSolution.SupportedType.NODE.toString()).iterator().next();
        assertFalse(solution.getAvailableElements().values().stream().noneMatch(set -> set.contains(targetNode)), String.format("Expected target node %s belongs to the availableElements.", targetNode));

        String createdNode = action.getCreatedElements().get(UMLRSolution.SupportedType.NODE.toString()).iterator().next();
        assertTrue(solution.getAvailableElements().values().stream().noneMatch(set -> set.contains(createdNode)), String.format("Expected created node %s does not belong to the availableElements.", createdNode));
    }

    @Test
    public void testToCSV() {
//        String generatedCSV = action.toCSV();
//        System.out.println(generatedCSV);
        numberOfCSVField = 3;
        actionName = "UMLCloneNode";
        super.testToCSV();
    }

    @Test
    public void testExecute() throws URISyntaxException, EolModelLoadingException, EasierException {
        super.testExecute();
    }

    @Test
    public void testGetTargetType() {
        expectedType = UMLRSolution.SupportedType.NODE.toString();
        super.testGetTargetType();
    }

    @Test
    public void testEquals() {
        super.testEquals();
    }


    @Test
    public void testGetTargetElement() {
        expectedName = action.getTargetElements();
        super.testGetTargetElement();
    }

    @Test
    public void testClone() {
        super.testClone();
    }

    @Test
    public void testComputeArchitecturalChanges() throws URISyntaxException, EolModelLoadingException, EasierException {
        super.testComputeArchitecturalChanges();
    }
}