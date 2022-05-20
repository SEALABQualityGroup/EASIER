package it.univaq.disim.sealab.metaheuristic.actions.uml;

import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.EasierException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UMLMvComponentToNNTest extends RefactoringActionTest {

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();

        oldAction = new UMLMvComponentToNN(solution.getAvailableElements(), solution.getInitialElements());
        action = new UMLMvComponentToNN(solution.getAvailableElements(), solution.getInitialElements());
    }

    @Test
    public void testConstructor() {
        String targetComponent = action.getTargetElements().get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next();
        String createdElement = action.getCreatedElements().get(UMLRSolution.SupportedType.NODE.toString()).iterator().next();
        String availableElements = solution.getAvailableElements().get(UMLRSolution.SupportedType.NODE.toString()).toString();
        System.out.printf("Expected %s \t found %s", availableElements, createdElement);
        assertTrue(solution.getAvailableElements().values().stream().noneMatch(set -> set.contains(createdElement)), "Expected created node not in the available elements");
    }

    @Test
    public void testToCSV() {
        numberOfCSVField = 3;
        actionName = "Move_Component_New_Node";
        super.testToCSV();

    }

    @Test
    public void testExecute() throws URISyntaxException, EolModelLoadingException, EasierException {
        super.testExecute();
    }

    @Test
    public void testGetTargetType() {
        expectedType = UMLRSolution.SupportedType.COMPONENT.toString();
        super.testGetTargetType();
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