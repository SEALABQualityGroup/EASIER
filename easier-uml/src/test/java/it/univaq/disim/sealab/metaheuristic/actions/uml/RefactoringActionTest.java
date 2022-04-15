package it.univaq.disim.sealab.metaheuristic.actions.uml;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class RefactoringActionTest {

    UMLRProblem<UMLRSolution> p;
    protected RefactoringAction action;
    protected UMLRSolution solution;

    protected String generatedCSV;

    protected int numberOfCSVField;
    protected String actionName;

    public void setUp() throws Exception {
        int allowedFailures = 100;
        int desired_length = 4;
        int populationSize = 4;

        String modelpath = getClass().getResource("/models/simplified-cocome/cocome.uml").getFile();
        p = new UMLRProblem<>(Paths.get(modelpath), desired_length, allowedFailures, populationSize);
        p.setName("simplied-cocome__test");
        solution = p.createSolution();

    }

    public void testToCSV() {
        generatedCSV = action.toCSV();
        System.out.println(generatedCSV);
        assertEquals(String.format("Expected length %s \t generated %s", numberOfCSVField, generatedCSV.split(",").length),numberOfCSVField, generatedCSV.split(",").length);
        assertEquals(String.format("Expected first entry %s \t generated %s", actionName, action.getName()), actionName, generatedCSV.split(",")[0]);
    }

    public void testEquals() {
        RefactoringAction action2 = action;
        assertEquals(action, action2);

        action2 = action.clone(solution);
        assertEquals(action,action2);
    }
}
