package it.univaq.disim.sealab.metaheuristic.managers;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLCloneNode;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvComponentToNN;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvOperationToComp;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvOperationToNCToNN;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import logicalSpecification.PreCondition;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ParserException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerTest {

    private UMLRSolution solution, solution2;
    UMLRProblem<RSolution<?>> p;
    List<RefactoringAction> actions;

    @Before
    public void setUp() throws Exception {

        int allowedFailures = 100;
        int desired_length = 4;
        int populationSize = 4;

        String modelpath = getClass().getResource("/models/simplified-cocome/cocome.uml").getFile();
        p = new UMLRProblem<>(Paths.get(modelpath), "simplied-cocome__test");
        solution = (UMLRSolution) p.createSolution();

        actions = new ArrayList<>();
        actions.add(new UMLCloneNode(solution.getModelPath().toString(), solution.getAvailableElements()));
        actions.add(new UMLMvComponentToNN(solution.getModelPath().toString(), solution.getAvailableElements()));
        actions.add(new UMLMvOperationToComp(solution.getModelPath().toString(), solution.getAvailableElements()));
        actions.add(new UMLMvOperationToNCToNN(solution.getModelPath().toString(), solution.getAvailableElements()));

    }

    @After
    public void tearDown() throws Exception {
        actions.clear();
        actions = null;
    }

    private List<RefactoringAction> extractActions(UMLRSolution solution) {

        List<RefactoringAction> actions = new ArrayList<>();
        actions.add(new UMLCloneNode(solution.getModelPath().toString(), solution.getAvailableElements()));
        actions.add(new UMLMvComponentToNN(solution.getModelPath().toString(), solution.getAvailableElements()));
        actions.add(new UMLMvOperationToComp(solution.getModelPath().toString(), solution.getAvailableElements()));
        actions.add(new UMLMvOperationToNCToNN(solution.getModelPath().toString(), solution.getAvailableElements()));

        return actions;
    }

//    @Test
//    public void testEvaluateFOLWithOneAction() throws ParserException {
////      Refactoring refactoring = solution.getVariable(0);
//
//        for (RefactoringAction action : actions) {
//
//            Refactoring refactoring = new Refactoring();
//            refactoring.setSolutionID(solution.getName());
//            refactoring.getActions().add(action);
//
//            PreCondition pre = Manager.calculatePreCondition(refactoring);
//            boolean checkFormula = Manager.evaluateFOL(pre.getConditionFormula(), (EObject) solution.getDirtyIModel().allContents().toArray()[0]);
//            assertTrue(String.format("Expected a valid refactoring with only one %s action.", action.getName()), checkFormula);
//            System.out.printf("Valid refactoring with actions \t %s %n", action.getName());
//        }
//    }

//    @Test
//    public void testEvaluateFOLWithTwoActions() throws ParserException {
//
//        for (RefactoringAction action : actions) {
//            for (RefactoringAction action2 : actions) {
//                Refactoring refactoring = new Refactoring();
//                refactoring.setSolutionID(solution.getName());
//                refactoring.getActions().add(action);
//                refactoring.getActions().add(action2);
//
//                PreCondition pre = Manager.calculatePreCondition(refactoring);
//                boolean checkFormula = Manager.evaluateFOL(pre.getConditionFormula(), (EObject) solution.getDirtyIModel().allContents().toArray()[0]);
//                assertTrue(String.format("Expected a valid refactoring with actions \t %s \t %s%n", action.getName(), action2.getName()), checkFormula);
//                System.out.printf("Valid refactoring with actions \t %s \t %s%n", action.getName(), action2.getName());
//            }
//        }
//    }

//    @Test
//    public void testEvaluateFOLWithThreeActions() throws ParserException {
//
//        for (RefactoringAction action : actions) {
//            for (RefactoringAction action2 : actions) {
//                for (RefactoringAction action3 : actions) {
//                    Refactoring refactoring = new Refactoring();
//                    refactoring.setSolutionID(solution.getName());
//                    refactoring.getActions().add(action);
//                    refactoring.getActions().add(action2);
//                    refactoring.getActions().add(action3);
//
//                    PreCondition pre = Manager.calculatePreCondition(refactoring);
//                    boolean checkFormula = Manager.evaluateFOL(pre.getConditionFormula(), (EObject) solution.getDirtyIModel().allContents().toArray()[0]);
//                    assertTrue(String.format("Expected a valid refactoring with actions \t %s \t %s \t %s%n", action.getName(), action2.getName(), action3.getName()), checkFormula);
//                    System.out.printf("Valid refactoring with actions \t %s \t %s \t %s%n", action.getName(), action2.getName(), action3.getName());
//                }
//            }
//        }
//    }

//    @Test
//    public void testEvaluateFOLWithFourActions() throws ParserException {
//
//        for (RefactoringAction action : actions) {
//            for (RefactoringAction action2 : actions) {
//                for (RefactoringAction action3 : actions) {
//                    for (RefactoringAction action4 : actions) {
//                        Refactoring refactoring = new Refactoring();
//                        refactoring.setSolutionID(solution.getName());
//                        refactoring.getActions().add(action);
//                        refactoring.getActions().add(action2);
//                        refactoring.getActions().add(action3);
//                        refactoring.getActions().add(action4);
//
//                        PreCondition pre = Manager.calculatePreCondition(refactoring);
//                        boolean checkFormula = Manager.evaluateFOL(pre.getConditionFormula(), (EObject) solution.getDirtyIModel().allContents().toArray()[0]);
//                        assertTrue(String.format("Expected a valid refactoring with actions \t %s \t %s \t %s \t %s%n", action.getName(), action2.getName(), action3.getName(), action4.getName()), checkFormula);
//                        System.out.printf("Valid refactoring with actions \t %s \t %s \t %s \t %s%n", action.getName(), action2.getName(), action3.getName(), action4.getName());
//                    }
//                }
//            }
//        }
//    }

//    @Test
//    public void testEvaluateFOLWithSolution() throws ParserException {
//        PreCondition pre = Manager.calculatePreCondition(solution.getVariable(0));
//        boolean checkFormula = Manager.evaluateFOL(pre.getConditionFormula(), (EObject) solution.getDirtyIModel().allContents().toArray()[0]);
//        RefactoringAction action = solution.getActionAt(0);
//        RefactoringAction action2 = solution.getActionAt(1);
//        RefactoringAction action3 = solution.getActionAt(2);
//        RefactoringAction action4 = solution.getActionAt(3);
//        assertTrue(String.format("Expected a valid refactoring with actions \t %s \t %s \t %s \t %s%n", action.getName(), action2.getName(), action3.getName(), action4.getName()), checkFormula);
//        System.out.printf("Valid refactoring with actions \t %s \t %s \t %s \t %s%n", action.getName(), action2.getName(), action3.getName(), action4.getName());
//    }


    @Test
    public void testEvaluateFOLWithXoveredSolutions() throws ParserException {
        UMLRSolution solution1 = (UMLRSolution) p.createSolution();

        RefactoringAction action = new UMLMvComponentToNN();
    }

}