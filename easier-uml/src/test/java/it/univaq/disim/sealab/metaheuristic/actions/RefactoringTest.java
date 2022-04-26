package it.univaq.disim.sealab.metaheuristic.actions;

import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLCloneNode;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvComponentToNN;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvOperationToComp;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvOperationToNCToNN;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RefactoringTest {

    Refactoring refactoring;
    UMLRSolution solution;

    @BeforeEach
    public void setUp() {
        int allowedFailures = 100;
        int desired_length = 4;
        int populationSize = 4;

        String modelpath = getClass().getResource("/models/simplified-cocome/cocome.uml").getFile();
        UMLRProblem<UMLRSolution> p = new UMLRProblem<>(Paths.get(modelpath), "simplied-cocome__test");

        solution = p.createSolution();
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(solution.getModelPath());
    }

    @Test
    public void testExecute(){
        Refactoring refactoring = new Refactoring();
        RefactoringAction clone = new UMLCloneNode(solution.getModelPath().toString(), solution.getAvailableElements());
        RefactoringAction mvopncnn = new UMLMvOperationToNCToNN(solution.getModelPath().toString(),
                solution.getAvailableElements());
        RefactoringAction movopc = new UMLMvOperationToComp(solution.getModelPath().toString(),
                solution.getAvailableElements());
        RefactoringAction mvcpnn = new UMLMvComponentToNN(solution.getModelPath().toString(), solution.getAvailableElements());
        refactoring.getActions().addAll(List.of(clone, mvopncnn, movopc, mvcpnn));
        refactoring.execute();
    }



    @Test
    public void testClone(){
        Refactoring cloned = refactoring.clone();
        assertEquals(refactoring, cloned);
    }

    @Test
    public void testCloneDeprecated() {
        Refactoring cloneRefactoring = refactoring.clone();
        assertEquals(refactoring, cloneRefactoring);
    }

    @Test
    public void testEquals() {
        assertEquals(refactoring, refactoring);
        Refactoring otherRefactoring = new Refactoring();

        RefactoringAction[] actions = new RefactoringAction[4];

        int i =0;
        for(RefactoringAction action : refactoring.getActions()){
            actions[i] = action;
            i++;
        }

        otherRefactoring.getActions().addAll(List.of(actions[0], actions[2], actions[1],actions[3]));
        assertNotEquals(refactoring, otherRefactoring, "Expected two refactorings with different action order");


    }
    @Test
    /*
      It should find a multiple occurrence of  the first
      refactoring action.
      The refactoring has been built synthetically.
     */
    public void testFindMultipleOccurrenceWithMultiOccurrences() {
        Refactoring refactoring = new Refactoring();
        RefactoringAction clone = new UMLCloneNode(solution.getModelPath().toString(), solution.getAvailableElements());
        RefactoringAction clone1 = clone.clone();
        RefactoringAction mvopncnn = new UMLMvOperationToNCToNN(solution.getModelPath().toString(),
                solution.getAvailableElements());
        RefactoringAction movopc = new UMLMvOperationToComp(solution.getModelPath().toString(),
                solution.getAvailableElements());
        RefactoringAction mvcpnn = new UMLMvComponentToNN(solution.getModelPath().toString(), solution.getAvailableElements());
        refactoring.getActions().addAll(List.of(clone, clone1, movopc, mvcpnn));
        assertTrue(refactoring.hasMultipleOccurrence(), String.format("Expected a multiple occurrence"));
    }


}
