package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.*;
import java.util.stream.Collectors;

import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLCloneNode;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvComponentToNN;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvOperationToNCToNN;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;


import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.eclipse.uml2.uml.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;


public class UMLRSolutionTest {

    private UMLRSolution solution, solution2;

    UMLRProblem<RSolution<?>> p;


//    private static void logInfo(Description description, String status, long nanos) {
//        String testName = description.getMethodName();
//        System.out.println(String.format("Test %s %s, spent %d %s", testName, status,
//                TimeUnit.NANOSECONDS.toMillis(nanos), TimeUnit.MILLISECONDS.toString()));
//    }

//    @Rule
//    public Stopwatch stopwatch = new Stopwatch() {
//        @Override
//        protected void succeeded(long nanos, Description description) {
//            logInfo(description, "succeeded", nanos);
//        }
//
//        @Override
//        protected void failed(long nanos, Throwable e, Description description) {
//            logInfo(description, "failed", nanos);
//        }
//
//        @Override
//        protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
//            logInfo(description, "skipped", nanos);
//        }
//
//        @Override
//        protected void finished(long nanos, Description description) {
//            logInfo(description, "finished", nanos);
//        }
//    };

    @BeforeAll
    public static void beforeClass() throws IOException {
        Files.createDirectories(Configurator.eINSTANCE.getOutputFolder());
    }

    @AfterAll
    public static void tearDownClass() throws IOException {
        Files.walk(Configurator.eINSTANCE.getOutputFolder())
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @BeforeEach
    public void setUp() throws URISyntaxException {
//        MockitoAnnotations.openMocks(this);
        int allowedFailures = 100;
        int desired_length = 4;
        int populationSize = 4;

        String modelpath = getClass().getResource("/models/simplified-cocome/cocome.uml").getFile();
        p = new UMLRProblem<>(Paths.get(modelpath),"simplied-cocome__test");

        solution = (UMLRSolution) p.createSolution();
    }

    @AfterEach
    public void tearDown() throws IOException {
        solution = null;
//        if (Files.exists(Configurator.eINSTANCE.getOutputFolder().resolve("process_step_stats.csv")))
//            Files.delete(Configurator.eINSTANCE.getOutputFolder().resolve("process_step_stats.csv"));

    }

    @Test
    /*
      The tested refactoring has been built synthetically.
      It has 2 feasible refactoring actions
     */
    public void testIsFeasibleShouldReturnTrue() {

        Refactoring refactoring = new Refactoring();
        RefactoringAction action1 = new UMLCloneNode(solution.getModelPath().toString(), solution.getAvailableElements());
        RefactoringAction action2 = new UMLMvOperationToNCToNN(solution.getModelPath().toString(), solution.getAvailableElements());
        refactoring.getActions().add(action1);
        refactoring.getActions().add(action2);
        solution.setVariable(0, refactoring);
        assertTrue(solution.isFeasible(), "It is expected a feasible refactoring.");

    }

    @Test
    public void testIsFeasibleOfCopy() {
        UMLRSolution solutionCopy = new UMLRSolution(solution);
        assertEquals(solution.isFeasible(), solutionCopy.isFeasible(), "Expected the same result of isFeasibleNew of the solution and its copy");
    }

    @Test
    public void testIsFeasibleShouldFail() {

        RefactoringAction failedAction = new UMLCloneNode(solution.getModelPath().toString(), solution.getAvailableElements());
        failedAction.getTargetElements().get(UMLRSolution.SupportedType.NODE.toString()).clear();
        failedAction.getTargetElements().get(UMLRSolution.SupportedType.NODE.toString()).add("FailedNode");
        solution.getVariable(0).getActions().set(0, failedAction);
        assertFalse(solution.isFeasible(), "Expected a unfeasible solution.");
    }

    @Test
    public void testInit() {

        Set<String> componentNames = solution.getComponents();
//                solution.getDirtyIModel().allContents().stream().filter(Component.class::isInstance).map(NamedElement.class::cast).
//                        map(NamedElement::getName).collect(Collectors.toList());

        assertFalse(componentNames.isEmpty());
        assertFalse(solution.getAvailableElements().get(UMLRSolution.SupportedType.COMPONENT.toString()).stream().
                map(String.class::cast).noneMatch(componentNames::contains));

        Set<String> operationNames = solution.getOperations();
//                getDirtyIModel().allContents().stream().filter(Message.class::isInstance).map(Message.class::cast).
//                filter(msg -> !msg.getMessageSort().toString().equals("reply")).map(Message::getSignature).
//                map(NamedElement::getName).collect(Collectors.toSet());

        assertFalse(operationNames.isEmpty());
        assertFalse(solution.getAvailableElements().get(UMLRSolution.SupportedType.OPERATION.toString()).stream().
                map(String.class::cast).noneMatch(operationNames::contains));

        Set<String> nodeNames = solution.getNodes();
//                solution.getDirtyIModel().allContents().stream().filter(Node.class::isInstance).
//                        map(Node.class::cast).map(NamedElement::getName).collect(Collectors.toSet());
        assertFalse(nodeNames.isEmpty());
        assertFalse(solution.getAvailableElements().get(UMLRSolution.SupportedType.NODE.toString()).stream().
                map(String.class::cast).noneMatch(nodeNames::contains));


    }


    @Test
    public void testSetRefactoring() {
        Map<String, Set<String>> expectedCreatedElements = new HashMap<>();
        expectedCreatedElements.put(UMLRSolution.SupportedType.NODE.toString(), new HashSet<>());
        expectedCreatedElements.put(UMLRSolution.SupportedType.COMPONENT.toString(), new HashSet<>());
        expectedCreatedElements.put(UMLRSolution.SupportedType.OPERATION.toString(), new HashSet<>());

        solution.init();
        Refactoring ref = new Refactoring();
        RefactoringAction clone = new UMLCloneNode(solution.getModelPath().toString(), solution.getAvailableElements());
        RefactoringAction mvopncnn = new UMLMvOperationToNCToNN(solution.getModelPath().toString(), solution.getAvailableElements());
        RefactoringAction clone1 = new UMLCloneNode(solution.getModelPath().toString(), solution.getAvailableElements());
        RefactoringAction mvcpnn = new UMLMvComponentToNN(solution.getModelPath().toString(), solution.getAvailableElements());
        ref.getActions().addAll(List.of(clone, mvopncnn, clone1, mvcpnn));
        solution.setRefactoring(ref);

        for (RefactoringAction refactoringAction : Arrays.asList(clone, mvopncnn, clone1, mvcpnn)) {
            refactoringAction.getCreatedElements().forEach((k, v) -> expectedCreatedElements.get(k).addAll(v));
        }

        assertEquals(expectedCreatedElements, solution.createdRefactoringElement, "Expected the same created element map");

    }

    @ParameterizedTest
    @CsvSource({"true,1", "true,2", "true,3", "false,0", "false,2", "false,3"})
    void testConstructorForXover(boolean left, int point) {

        UMLRSolution solution1 = (UMLRSolution) p.createSolution();

        List<UMLRSolution> children = solution.createChildren(solution1, point);
        children.forEach(c -> assertTrue(c.isFeasible(), "It is expected a feasible child solution"));

    }

    @Disabled
    @Test
    public void isLocalOptmimalPointTrueTest() {
        solution2 = (UMLRSolution) p.createSolution();
        solution.setPerfQ(0);
        solution2.setPerfQ(0);

        solution.reliability = 0;
        solution2.reliability = 0;

        solution.numPAs = 0;
        solution2.numPAs = 0;

//        solution.getVariable(0).setNumOfChanges(0);
//        solution2.getVariable(0).setNumOfChanges(0);

        assertTrue(solution.isLocalOptmimalPoint(solution2));
    }

    /*
     * PerfQ of solution2 is greater than the perfQ of solution The test should
     * return FALSE
     */
    @Disabled
    @Test
    public void isLocalOptmimalPointPerfQOutOfRangeShouldReturnFalseTest() {
        solution2 = (UMLRSolution) p.createSolution();
        solution.setPerfQ(0);
        solution2.setPerfQ(0);

        solution.reliability = 0;
        solution2.reliability = 0;

        solution.numPAs = 0;
        solution2.numPAs = 4;

//        solution.getVariable(0).setNumOfChanges(0);
//        solution2.getVariable(0).setNumOfChanges(0);

        assertFalse(solution.isLocalOptmimalPoint(solution2));
    }

    @Disabled
    @Test
    public void isLocalOptmimalPointSolutionWithinSolution2ShouldReturnTrueTest() {
        solution2 = (UMLRSolution) p.createSolution();
        solution.setPerfQ(-10);
        solution2.setPerfQ(-10);

        solution.reliability = -10;
        solution2.reliability = -10;

        solution.numPAs = 1;
        solution2.numPAs = 0;

//        solution.getVariable(0).setNumOfChanges(10);
//        solution2.getVariable(0).setNumOfChanges(10);

//        solution2.getReliability(), solution2.getPAs(), solution2.getVariable(0).getNumOfChanges());

        assertTrue(solution.isLocalOptmimalPoint(solution2));
    }

    @Test
    public void countingPAs() {
        solution.countingPAs();

        assertEquals(13d, solution.getPAs(), 1, String.format("Expected 12 PAs \t found: %s.", solution.getPAs()));
    }

    @Test
    public void createRandomRefactoring() {
        solution.createRandomRefactoring();

        assertFalse(solution.getVariable(0).hasMultipleOccurrence());
    }

    @Test
    public void testTryRandomPush() throws UnexpectedException, EolRuntimeException {

        solution.targetRefactoringElement.clear();
        solution.createdRefactoringElement.clear();
        solution.init();

        Refactoring ref = new Refactoring();
        RefactoringAction clone = new UMLCloneNode(solution.getModelPath().toString(), solution.getAvailableElements());
        RefactoringAction mvopncnn = new UMLMvOperationToNCToNN(solution.getModelPath().toString(), solution.getAvailableElements());
        RefactoringAction clone1 = new UMLCloneNode(solution.getModelPath().toString(), solution.getAvailableElements());
        RefactoringAction mvcpnn = new UMLMvComponentToNN(solution.getModelPath().toString(), solution.getAvailableElements());
        ref.getActions().add(clone);//, mvopncnn, clone1, mvcpnn));
        solution.setVariable(0, ref);

        solution.targetRefactoringElement.get(UMLRSolution.SupportedType.NODE.toString()).
                add(clone.getCreatedElements().get(UMLRSolution.SupportedType.NODE.toString()).iterator().next());

        solution.tryRandomPush();

        assertTrue(solution.getAvailableElements().values().stream().flatMap(Set::stream)
                .anyMatch(clone.getCreatedElements().get(UMLRSolution.SupportedType.NODE.toString())::contains));
    }

    @Test
    public void evaluatePerformance() {
        solution.evaluatePerformance();
    }

    @Test
    public void computeReliability() {
        solution.computeReliability();
    }

    @Test
    public void refactoringToCSV() throws IOException {
        solution.refactoringToCSV();
        LineNumberReader lnr = new LineNumberReader(
                new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("refactoring_composition.csv").toString()));
        long readLine = lnr.lines().count();
        //number of refactoring action + the header
        assertEquals(Configurator.eINSTANCE.getLength() + 1, readLine, String.format("Expected %s lines \t found: %s.", Configurator.eINSTANCE.getLength(), readLine));
    }

    @Test
    public void testExecuteRefactoring() throws IOException {
        solution.executeRefactoring();
        LineNumberReader lnr = new LineNumberReader(
                new FileReader(Configurator.eINSTANCE.getOutputFolder().resolve("refactoring_stats.csv").toString()));
        long readLine = lnr.lines().count();
        //number of refactoring action + the header
        assertEquals(Configurator.eINSTANCE.getLength() + 1, readLine);
    }

    @Test
    public void testEquals() {

        assertEquals(solution, solution);

        solution2 = (UMLRSolution) p.createSolution();
        assertNotEquals(solution, solution2);

        solution2 = (UMLRSolution) solution.copy();
        assertEquals(solution, solution2);
    }

    @Test
    public void copyRefactoringVariable() {
        UMLRSolution cloneSolution = (UMLRSolution) solution.copy();
        assertEquals(solution, cloneSolution);

        cloneSolution.copyRefactoringVariable(solution.getVariable(0));
        assertEquals(solution.getVariable(0), cloneSolution.getVariable(0));
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 0, 4})
    public void createChild(int point) {
        UMLRSolution solution2 = (UMLRSolution) p.createSolution();
        UMLRSolution childSolution = solution.createChild(solution2, point);

        childSolution.setVariable(0, new Refactoring()); // clear the old refactoring
        // clear created element and target element maps
        childSolution.createdRefactoringElement.clear();
        childSolution.targetRefactoringElement.clear();
        childSolution.createChild(solution, solution2, point);

        assertFalse(childSolution.isFeasible());

        for (int i = 0; i < point; i++) {
            assertEquals(childSolution.getActionAt(i), solution.getActionAt(i));
        }
        for (int i = point; i < solution.refactoringLength; i++) {
            assertEquals(childSolution.getActionAt(i), solution2.getActionAt(i));
        }
    }

    @RepeatedTest(5)
    void alter(TestInfo testInfo) {

        int alterPoint = 2;
        solution.alter(alterPoint);

        assertTrue(solution.isFeasible(), "Expected a feasible solution after the alter operation.");

    }


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    public void doAlter(int point) {

        RefactoringAction candidate = ((point == 0) ? solution.getActionAt(point + 1) : solution.getActionAt(point - 1));
        assertFalse(solution.doAlter(point, candidate), String.format("Expected unfeasible solution %s%n",
                solution.toString()));

        assertTrue(solution.isFeasible());


    }
}
