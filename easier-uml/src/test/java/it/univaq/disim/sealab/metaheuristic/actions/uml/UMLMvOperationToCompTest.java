package it.univaq.disim.sealab.metaheuristic.actions.uml;

import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.EasierException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UMLMvOperationToCompTest extends RefactoringActionTest {

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();

        oldAction = new UMLMvOperationToComp(solution.getAvailableElements(),
                solution.getInitialElements());
        action = new UMLMvOperationToComp(solution.getAvailableElements(),
                solution.getInitialElements());
    }

    @Test
    public void testConstructor() {
        String targetOperation =
                action.getTargetElements().get(UMLRSolution.SupportedType.OPERATION.toString()).iterator().next();
        assertFalse(solution.getAvailableElements().values().stream().noneMatch(set -> set.contains(targetOperation)),
                String.format("Expected target node %s belongs to the availableElements.", targetOperation));

    }

    @Test
    public void testToCSV() {
        numberOfCSVField = 3;
        actionName = "Move_Operation_Component";
        super.testToCSV();
    }

    @Test
    public void testGetTargetType() {
        expectedType = UMLRSolution.SupportedType.OPERATION.toString();
        super.testGetTargetType();
    }

    @Test
    public void testExecute() throws URISyntaxException, EolModelLoadingException, EasierException {
        super.testExecute();
    }

    @Test
    public void testClone() {
        super.testClone();
    }

    @Test
    public void testGetTargetElement() {
        expectedName = action.getTargetElements();
        super.testGetTargetElement();
    }

    @Test
    public void testComputeArchitecturalChanges() throws URISyntaxException, EolModelLoadingException, EasierException {
        super.testComputeArchitecturalChanges();
    }
}