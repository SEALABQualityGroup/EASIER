package it.univaq.disim.sealab.metaheuristic.actions.uml;

import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.EasierException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class RefactoringActionTest {

    UMLRProblem<UMLRSolution> p;
    protected RefactoringAction action, oldAction;
    protected UMLRSolution solution;

    protected String generatedCSV;

    protected int numberOfCSVField;
    protected String actionName;
    protected String expectedType;
    protected Map<String, Set<String>> expectedName;

    public void setUp() throws Exception {
        int allowedFailures = 100;
        int desired_length = 4;
        int populationSize = 4;

        String modelpath = getClass().getResource("/models/simplified-cocome/cocome.uml").getFile();
        p = new UMLRProblem<>(Paths.get(modelpath), "simplied-cocome__test");
        solution = p.createSolution();

    }

    public void testToCSV() {
        generatedCSV = action.toCSV();
        System.out.println(generatedCSV);
        assertEquals(numberOfCSVField, generatedCSV.split(",").length,
                String.format("Expected length %s \t generated %s", numberOfCSVField, generatedCSV.split(",").length));
        assertEquals(actionName, generatedCSV.split(",")[0], String.format("Expected first entry %s \t generated %s", actionName, action.getName()));
    }

    public void testEquals() {
        RefactoringAction action2 = action;
        assertEquals(action, action2);

        action2 = action.clone();
        assertEquals(action, action2);

    }

    public void testExecute() throws URISyntaxException, EolModelLoadingException, EasierException {
        EasierUmlModel model = EOLStandalone.createUmlModel(solution.getModelPath().toString());
        action.execute(model);
    }


    public void testGetTargetType() {
        assertEquals(expectedType, action.getTargetType(), String.format("Expected target type %s \t found %s",
                expectedType, action.getTargetType()));
    }

    public void testGetTargetElement() {
        assertEquals(expectedName, action.getTargetElements(), String.format("Expected target name %s \t found %s",
                expectedName, action.getTargetType()));
    }

    public void testClone() {
//        RefactoringAction clonedAction = action.clone(solution);
        RefactoringAction clonedAction = (RefactoringAction) action.clone();
        assertEquals(action, clonedAction);
    }

    void testComputeArchitecturalChanges() throws URISyntaxException, EolModelLoadingException, EasierException {

        Collection<?> modelContents =
                EOLStandalone.createUmlModel(solution.getModelPath().toString()).allContents();

        double archChanges = action.computeArchitecturalChanges(modelContents);
        System.out.println(""+archChanges);

        assertNotEquals(0, archChanges, "Expected arcChanges != 0");

    }
}
