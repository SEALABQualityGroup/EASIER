package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.univaq.disim.sealab.easier.uml.utils.WorkflowUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.uml2.uml.*;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.solutionattribute.impl.CrowdingDistance;

import it.univaq.disim.sealab.easier.uml.utils.UMLMemoryOptimizer;
import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.epsilon.evl.EVLStandalone;
import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.RefactoringActionFactory;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;
import it.univaq.sealab.umlreliability.MissingTagException;
import it.univaq.sealab.umlreliability.Reliability;
import it.univaq.sealab.umlreliability.UMLReliability;
import it.univaq.sealab.umlreliability.model.UMLModelPapyrus;

/**
 * @author peo
 */
public class UMLRSolution extends RSolution<Refactoring> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Path folderPath;

    private double[] scenarioRTs;

    private String algorithm;

    private final static String refactoringLibraryModule, uml2lqnModule;
    private final static String GQAM_NAMESPACE;

    Map<String, Set<String>> targetRefactoringElement;
    Map<String, Set<String>> createdRefactoringElement;

    public enum SupportedType {
        NODE {
            @Override
            public String toString() {
                return "node";
            }
        },
        COMPONENT {
            @Override
            public String toString() {
                return "component";
            }
        },
        OPERATION {
            @Override
            public String toString() {
                return "operation";
            }
        }
    }

    private Map<String, Map<String, Double>> extractFuzzyValues;

    static {
        refactoringLibraryModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
                "easier-refactoringLibrary", "evl", "AP-UML-MARTE.evl").toString();
        uml2lqnModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
                "easier-uml2lqn", "org.univaq.uml2lqn").toString();

        GQAM_NAMESPACE = "MARTE::MARTE_AnalysisModel::GQAM::";
    }

    public UMLRSolution(Path sourceModelPath, String problemName) {
        super(sourceModelPath, problemName);

        init();
        this.createRandomRefactoring();
    }

    public UMLRSolution(UMLRSolution s) {
        this(s.sourceModelPath, s.problemName);

        // to clear the created refactoring while constructing the object
        initMap();

        for (String k : s.targetRefactoringElement.keySet()) {
            targetRefactoringElement.get(k).addAll(s.targetRefactoringElement.get(k));
        }

        for (String k : s.createdRefactoringElement.keySet()) {
            createdRefactoringElement.get(k).addAll(s.createdRefactoringElement.get(k));
        }

        this.setVariable(0, s.getVariable(0).clone());

        for (int i = 0; i < s.getNumberOfObjectives(); i++) {
            this.setObjective(i, s.getObjective(i));
        }

        this.attributes = s.attributes;
        this.setAttribute(CrowdingDistance.class, s.getAttribute(CrowdingDistance.class));

    }

    protected UMLRSolution createChild(UMLRSolution parent, int point) {

        UMLRSolution child = new UMLRSolution(this.sourceModelPath, this.problemName);
        child.allowedFailures = this.allowedFailures;
        child.refactoringLength = this.refactoringLength;

        child.setParents(this, parent);

        for (String k : this.createdRefactoringElement.keySet()) {
            this.createdRefactoringElement.get(k).forEach(v -> child.createdRefactoringElement.get(k).add(v));
            parent.createdRefactoringElement.get(k).forEach(v -> child.createdRefactoringElement.get(k).add(v));
        }

        for (String k : this.targetRefactoringElement.keySet()) {
            this.targetRefactoringElement.get(k).forEach(v -> child.targetRefactoringElement.get(k).add(v));
            parent.targetRefactoringElement.get(k).forEach(v -> child.targetRefactoringElement.get(k).add(v));
        }

        for (int j = 0; j < point; j++) {
            RefactoringAction _new = this.getActionAt(j).clone();
            child.getVariable(0).getActions().add(j, _new);
        }
        for (int j = point; j < refactoringLength; j++) {
            RefactoringAction _new = parent.getActionAt(j).clone();
            child.getVariable(0).getActions().add(j, _new);
        }
        if (child.isFeasible())
            return child;
        return null;

    }


    /**
     * Create two children from the calling object (this) and the parent param.
     * <ul>
     *     <li>Child 1: first point actions from this, and the last length - point actions from parent </li>
     *     <li>Child 2: first point actions from parent, and the last length - point actions from this </li>
     *
     * </ul>
     *
     * @param parent exploited during the mating process
     * @param point  is the point where split the chromosome of this and parent
     * @return an ArrayList of two UMLRSolutions generated by the mating process
     */
    public List<UMLRSolution> createChildren(UMLRSolution parent, int point) {
        List<UMLRSolution> children = new ArrayList<>(2);
        children.add(this.createChild(parent, point));
        children.add(parent.createChild(this, point));

        if (children.stream().anyMatch(Objects::isNull))
            throw new RuntimeException("At least one null child has been created");

        return children;
    }

    /**
     * Initialize the internal maps for storing available and generated elements
     */
    void initMap() {
        targetRefactoringElement = new HashMap<>();
        createdRefactoringElement = new HashMap<>();
        for (String k : List.of(SupportedType.NODE.toString(), SupportedType.COMPONENT.toString(),
                SupportedType.OPERATION.toString())) {
            this.targetRefactoringElement.put(k, new HashSet<>());
            this.createdRefactoringElement.put(k, new HashSet<>());
        }
    }

    protected void init() {

        parents = new UMLRSolution[2];
        scenarioRTs = new double[3];

        this.setName();

        initMap();
        Refactoring refactoring = new Refactoring();
        refactoring.setSolutionID(this.name);
        this.setVariable(0, refactoring);

        folderPath = Paths.get(Configurator.eINSTANCE.getTmpFolder().toString(), String.valueOf((getName() / 100)),
                String.valueOf(getName()));
        modelPath = folderPath.resolve(getName() + ".uml");

        algorithm = this.problemName.split("__")[0];

        try {
            org.apache.commons.io.FileUtils.copyFile(sourceModelPath.toFile(), modelPath.toFile());
            targetRefactoringElement.put(SupportedType.NODE.toString(), new HashSet<>(getNodes()));
            targetRefactoringElement.put(SupportedType.COMPONENT.toString(), new HashSet<>(getComponents()));
            targetRefactoringElement.put(SupportedType.OPERATION.toString(), new HashSet<>(getOperations()));
        } catch (IOException e) {
            System.out.println("[ERROR] The problem's model copy generated an error!!!");
            e.printStackTrace();
        } catch (RuntimeException eRun) {
            System.out.println(eRun.getMessage());
        }
    }

    Set<String> getOperations() {

        try (EasierUmlModel dirtyIModel = EOLStandalone.createUmlModel(modelPath.toString())) {
            org.eclipse.uml2.uml.Package dynamicView = null;
            for (Object pkg : EcoreUtil.getObjectsByType(dirtyIModel.allContents(), UMLPackage.Literals.PACKAGE)) {
                if (pkg instanceof org.eclipse.uml2.uml.Package
                        && "dynamic_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
                    dynamicView = (org.eclipse.uml2.uml.Package) pkg;
                    break;
                }
            }

            List<Object> objects = new ArrayList<>(
                    EcoreUtil.getObjectsByType(dynamicView.getOwnedElements(), UMLPackage.Literals.USE_CASE));

            List<UseCase> usecases = objects.stream().map(UseCase.class::cast).collect(Collectors.toList());

            List<Interaction> interactions = usecases.stream().map(UseCase::getOwnedBehaviors).flatMap(List::stream).
                    map(Interaction.class::cast).collect(Collectors.toList());

            return interactions.stream().map(Interaction::getMessages).flatMap(List::stream).
                    map(Message.class::cast).filter(msg -> !msg.getMessageSort().toString().equals("reply")).
                    map(Message::getSignature).map(NamedElement::getName).collect(Collectors.toSet());
        } catch (EolModelLoadingException | URISyntaxException e) {
            throw new RuntimeException("Error while loading the model.");
        }
    }


    Set<String> getComponents() {


        try (EasierUmlModel model = EOLStandalone.createUmlModel(modelPath.toString())) {
            org.eclipse.uml2.uml.Package staticView = null;
            for (Object pkg : EcoreUtil.getObjectsByType(model.allContents(),
                    UMLPackage.Literals.PACKAGE)) {
                if (pkg instanceof org.eclipse.uml2.uml.Package
                        && "static_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
                    staticView = (org.eclipse.uml2.uml.Package) pkg;
                    break;
                }
            }

            List<Component> comps = new ArrayList<>(
                    EcoreUtil.getObjectsByType(staticView.getOwnedElements(), UMLPackage.Literals.COMPONENT));
            return comps.stream().map(component -> (Component) component).map(NamedElement::getName).collect(Collectors.toSet());
        } catch (URISyntaxException | EolModelLoadingException e) {
            throw new RuntimeException(e);
        }
    }

    Set<String> getNodes() {
        try (EasierUmlModel model = EOLStandalone.createUmlModel(modelPath.toString())) {
            org.eclipse.uml2.uml.Package deploymentView = null;
            org.eclipse.uml2.uml.Model rootPackage = null;

            for (Object pkg : EcoreUtil.getObjectsByType(model.allContents(), UMLPackage.Literals.PACKAGE)) {
                if (pkg instanceof org.eclipse.uml2.uml.Model) {
                    rootPackage = (org.eclipse.uml2.uml.Model) pkg;
                    break;
                }
            }

            for (Object pkg : EcoreUtil.getObjectsByType(rootPackage.getOwnedElements(), UMLPackage.Literals.PACKAGE)) {
                if (pkg instanceof org.eclipse.uml2.uml.Package
                        && "deployment_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
                    deploymentView = (org.eclipse.uml2.uml.Package) pkg;
                    break;
                }
            }
            List<Object> nodes = new ArrayList<>(
                    EcoreUtil.getObjectsByType(deploymentView.getOwnedElements(), UMLPackage.Literals.NODE));
            return nodes.stream().map(node -> (Node) node).map(NamedElement::getName).collect(Collectors.toSet());
        } catch (URISyntaxException | EolModelLoadingException e) {
            throw new RuntimeException(e);
        }
    }

    protected void createRandomRefactoring() {

        int num_failures = 0;

        do {
            try {
                if (!tryRandomPush())
                    num_failures++;
                if (num_failures >= allowedFailures) {
                    throw new RuntimeException(String.format("Exceed %s failures \t %s ", allowedFailures, num_failures));
                }
            } catch (UnexpectedException | EolRuntimeException e) {
                e.printStackTrace();
            }
        } while (getVariable(VARIABLE_INDEX).getActions().size() < refactoringLength);
        this.setAttribute(CrowdingDistance.class, 0.0);
    }

    boolean tryRandomPush() throws UnexpectedException, EolRuntimeException {


        RefactoringAction candidate;
        do {
            candidate = RefactoringActionFactory.getRandomAction(this);
        } while (candidate == null);

        getVariable(0).getActions().add(candidate);
        if (!this.isFeasible()) {
            getVariable(0).getActions().remove(candidate);
            return false;
        }

        // Add created element to the available element list
        // for the next refactoring action
        for (String k : candidate.getCreatedElements().keySet()) {
            candidate.getCreatedElements().get(k).forEach(createdElement -> {
                targetRefactoringElement.get(k).add(createdElement);
                createdRefactoringElement.get(k).add(createdElement);
            });

        }
        return true;
    }

    public boolean isFeasible() {
        return isFeasible(getVariable(VARIABLE_INDEX));
    }

    private boolean isFeasible(Refactoring tr) {

        if (tr.hasMultipleOccurrence())
            return false;

        for (RefactoringAction action : tr.getActions()) {
            Set<String> actionTargetElements =
                    action.getTargetElements().values().stream().flatMap(Set::stream).map(String.class::cast).collect(Collectors.toSet());
            if (getAvailableElements().values().stream().flatMap(Set::stream).noneMatch(actionTargetElements::contains)) {
                return false;
            }
        }

        return true;
    }

    protected void copyRefactoringVariable(Refactoring refactoring) {
        this.setVariable(VARIABLE_INDEX, refactoring.clone());
    }

    @Override
    public Solution<Refactoring> copy() {
        return new UMLRSolution(this);
    }

    protected void createChild(UMLRSolution s1, UMLRSolution s2, int point) {

        try {
            for (int j = 0; j < point; j++) {
                RefactoringAction _new = s1.getActionAt(j).clone();
                this.getVariable(0).getActions().add(j, _new);
            }
            for (int j = point; j < s2.getVariable(VARIABLE_INDEX).getActions().size(); j++) {
                RefactoringAction _new = s2.getActionAt(j).clone();
                this.getVariable(0).getActions().add(j, _new);
            }
        } catch (IndexOutOfBoundsException e) {
            EasierLogger.logger_.warning("POINT SIZE ERROR: " + Integer.toString(point));
            e.printStackTrace();
        }
    }

    boolean doAlter(int i, RefactoringAction candidate) {

        // save the original solution and substitute it with the new one
        RefactoringAction preAlterAction = getVariable(0).getActions().get(i);

        // Stash the created and target element of the action target of the alter operation
        // for a possible rollback
        Map<String, Set<String>> preAlterCreatedElement = new HashMap<>();
        Map<String, Set<String>> preAlterTargetElement = new HashMap<>();
        for (String k : preAlterAction.getCreatedElements().keySet()) {
            Set<String> tmpSet = this.createdRefactoringElement.get(k);
            tmpSet =
                    tmpSet.stream().filter(preAlterAction.getCreatedElements().get(k)::contains).collect(Collectors.toSet());
            preAlterCreatedElement.put(k, Set.copyOf(tmpSet));
            this.createdRefactoringElement.get(k).removeAll(tmpSet);
        }

        for (String k : preAlterAction.getTargetElements().keySet()) {
            Set<String> tmpSet = this.targetRefactoringElement.get(k);
            tmpSet =
                    tmpSet.stream().filter(preAlterAction.getTargetElements().get(k)::contains).collect(Collectors.toSet());
            preAlterTargetElement.put(k, Set.copyOf(tmpSet));
            this.targetRefactoringElement.get(k).removeAll(tmpSet);
        }

        getVariable(0).getActions().set(i, candidate);

        if (!this.isFeasible()) {
            // restore the old solution
            getVariable(0).getActions().set(i, preAlterAction);

            for (String k : preAlterAction.getCreatedElements().keySet()) {
                this.createdRefactoringElement.get(k).add(preAlterAction.getCreatedElements().get(k).iterator().next());
            }
            for (String k : preAlterAction.getTargetElements().keySet()) {
                this.targetRefactoringElement.get(k).add(preAlterAction.getTargetElements().get(k).iterator().next());
            }

            return false;
        }

        return true;
    }


    @Override
    public boolean alter(int i) {

        RefactoringAction candidate;
        do {
            candidate = RefactoringActionFactory.getRandomAction(this);
        } while (candidate == null);

        return doAlter(i, candidate);

    }

    /**
     * This method counts the number of Performance Antipatterns (PAs) invoking
     * the PADRE perf-detection file
     */
    public void countingPAs() {

        System.out.print("Counting PAs (it may take a while) ... ");
        long startTime = System.currentTimeMillis();
        EVLStandalone pasCounter = new EVLStandalone();
        try (EasierUmlModel uml = EpsilonStandalone.createUmlModel(modelPath.toString())) {
            pasCounter.setModel(uml);

            pasCounter.setSource(Paths.get(refactoringLibraryModule));

            // set the prob to be perf antipatterns
            double fuzzyThreshold = Configurator.eINSTANCE.getProbPas();
            pasCounter.setParameter(fuzzyThreshold, "float", "prob_to_be_pa");

            extractFuzzyValues = pasCounter.extractFuzzyValues();
        } catch (EolModelLoadingException | URISyntaxException e) {
            e.printStackTrace();
        }

        int nPas = 0;
        // Count performance antipatterns and build a string for the next csv storing
        for (String pas : extractFuzzyValues.keySet()) {
            Map<String, Double> mPaf = extractFuzzyValues.get(pas);
            numPAs += mPaf.keySet().size();
            for (String targetElement : mPaf.keySet()) {
                double fuzzy = mPaf.get(targetElement);
                new FileUtils().performanceAntipatternDumpToCSV(String.format("%s,%s,%s,%.4f", this.name, pas, targetElement, fuzzy));
            }
        }

        long duration = System.currentTimeMillis() - startTime;
        new FileUtils().processStepStatsDumpToCSV(String.format("%s,%s,%s,counting_pas,%s,%s", algorithm, problemName,
                getName(), getName(), duration));
        System.out.printf(" execution time: %s ms%n", duration);

        pasCounter.clearMemory();
        new UMLMemoryOptimizer().cleanup();
        pasCounter = null;
    }

    /*
     * From the ANT scripts target name="ChangeRoot" depends="LoadModels">
     * <epsilon.xml.loadModel name="PlainLQN" file="${output}/${name}.xml"
     * read="true" store="true"/> <epsilon.eol src="changeRoot.eol"> <model
     * ref="PlainLQN"/> </epsilon.eol>
     *
     * <epsilon.storeModel model="PlainLQN"/> <!-- <eclipse.refreshLocal
     * resource="${output}/output.xml" depth="infinite"/> -->
     *
     * </target>
     *
     * <target name="Solver" depends="ChangeRoot"> <exec
     * executable="${executableAbsPath}"> <arg value="${output}/${name}.xml"/>
     * </exec> </target>
     */
    public void invokeSolver() {

        long startTime = System.currentTimeMillis();
        try {
            new WorkflowUtils().invokeSolver(this.folderPath);
        } catch (Exception e) {
            String line = this.name + "," + e.getMessage() + "," + getVariable(VARIABLE_INDEX).toString();
            new FileUtils().failedSolutionLogToCSV(line);
        }

        long duration = System.currentTimeMillis() - startTime;
        new FileUtils().processStepStatsDumpToCSV(String.format("%s,%s,%s,invoke_solver,%s", "NSGA", problemName, getName(), duration));
        System.out.println(String.format("invokeSolver execution time: %s", duration));
        System.out.println("done");


        System.out.print("Invoking the back annotator... ");
        startTime = System.currentTimeMillis();
        new WorkflowUtils().backAnnotation(modelPath);
        duration = System.currentTimeMillis() - startTime;
        new FileUtils().processStepStatsDumpToCSV(String.format("%s,%s,%s,lqn2uml,%s", "NSGA", problemName, getName(), duration));
        System.out.println(String.format("backAnnotation execution time: %s", duration));
        System.out.println("done");

    }

    public double evaluatePerformance() {
        System.out.print("Counting perfQ ... ");
        long initTime = System.currentTimeMillis();
        perfQ = perfQ();
        long durationTime = System.currentTimeMillis() - initTime;
        new FileUtils().processStepStatsDumpToCSV(String.format("%s,%s,%s,perfQ,%s", algorithm, problemName, getName(), durationTime));
        System.out.println("done");
        return perfQ;
    }

    /**
     * Compute the performance quality indicator as described in
     * https://doi.org/10.1109/ICSA.2018.00020
     *
     * @return
     * @throws EolModelElementTypeNotFoundException
     */
    private double perfQ() {

        /*
         * The updated model can have more nodes than the source node since original
         * nodes can be cloned. The benefits of cloning nodes is taken into account by
         * the performance model. For this reason, the perfQ analyzes only the
         * performance metrics of the nodes common among the models
         */

        // The lists used to store the elements of both models
        List<EObject> sourceElements = new ArrayList<EObject>();

        // The elements of the source model;
        List<EObject> nodes = null;
        List<EObject> scenarios = null;
        try (EasierUmlModel source = (EasierUmlModel) EpsilonStandalone.createUmlModel(sourceModelPath.toString());
             EasierUmlModel uml = EpsilonStandalone.createUmlModel(modelPath.toString())) {
            nodes = (List<EObject>) source.getAllOfType("Node");
            scenarios = (List<EObject>) source.getAllOfType("UseCase");


            // The function considers only the elements having the stereotypes GaExecHosta
            // and GaScenario
            nodes = filterByStereotype(nodes, GQAM_NAMESPACE + "GaExecHost");
            scenarios = filterByStereotype(scenarios, GQAM_NAMESPACE + "GaScenario");
            sourceElements.addAll(scenarios);
            sourceElements.addAll(nodes);

            int numberOfMetrics = 0;


            // Variable representing the perfQ value
            double value = 0d;
            // for each elements of the source model, it is picked the element with the same
            // id in the refactored one
            for (EObject element : sourceElements) {
                String id = getXmiId(source, element);
                EObject correspondingElement = (EObject) uml.getElementById(id);

                if (element instanceof UseCase) {
                    value += -1 * computePerfQValue((Element) element, (Element) correspondingElement, "GaScenario",
                            "respT");
                    value += computePerfQValue((Element) element, (Element) correspondingElement, "GaScenario",
                            "throughput");
                    numberOfMetrics += 2;
                } else if (element instanceof Node) {
                    value += -1 * computePerfQValue((Element) element, (Element) correspondingElement, "GaExecHost",
                            "utilization");
                    numberOfMetrics++;
                }
            }
            uml.dispose();
            new UMLMemoryOptimizer().cleanup();
            return value / numberOfMetrics;
        } catch (Exception e) {
            System.err
                    .println(String.format("Solution # '%s' has thrown an error while computing PerfQ!!!", this.name));
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public void computeArchitecturalChanges() {

        try(EasierUmlModel model = EOLStandalone.createUmlModel(modelPath.toString())){

            for(RefactoringAction action : getVariable(0).getActions()){

                double brf = Configurator.eINSTANCE.getBRF(action.getName());

                for(String k : action.getTargetElements().keySet()){
                    action.computeArchitecturalChanges(model.allContents());
                }

                double aw = 0d;

            }

        }catch (URISyntaxException | EolModelLoadingException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void computeScenarioRT() {

        /*
         * The updated model can have more nodes than the source node since original
         * nodes can be cloned. The benefits of cloning nodes is taken into account by
         * the performance model. For this reason, the perfQ analyzes only the
         * performance metrics of the nodes common among the models
         */

        // Represent the reference point index of each UML scenario
        final int rebook_index = 0;
        final int update_user_index = 1;
        final int login_index = 2;

        int scenarioIndex = 0;

        // The elements of the source model;
        List<EObject> scenarios = null;

        // The function considers only the elements having the stereotypes GaScenario
        try (EasierUmlModel uml = EpsilonStandalone.createUmlModel(modelPath.toString())) {
            scenarios = (List<EObject>) uml.getAllOfType("UseCase");
            scenarios = filterByStereotype(scenarios, GQAM_NAMESPACE + "GaScenario");

            // for each elements of the source model, it is picked the element with the same
            // id in the refactored one
            for (EObject element : scenarios) {
                Stereotype stereotype = ((Element) element).getAppliedStereotype(GQAM_NAMESPACE + "GaScenario");
                EList<?> values = (EList<?>) ((Element) element).getValue(stereotype, "respT");

                if (!values.isEmpty()) {
                    if ("Rebook a ticket".equals(((UseCase) element).getName())) {
                        scenarioIndex = rebook_index;
                    } else if ("Update user details".equals(((UseCase) element).getName())) {
                        scenarioIndex = update_user_index;
                    } else if ("Login".equals(((UseCase) element).getName())) {
                        scenarioIndex = login_index;
                    } else {
                        throw new RuntimeException("Scenario name does not support yet!");
                    }

                    scenarioRTs[scenarioIndex] = Double.parseDouble(values.get(0).toString());
                }

            }
            uml.dispose();
            new UMLMemoryOptimizer().cleanup();
        } catch (Exception e) {
            System.err.println(String.format("Solution # '%s' has trown an error while computing PerfQ!!!", this.name));
            e.printStackTrace();
        }
    }

    public double[] getScenarioRTs() {
        return scenarioRTs;
    }

    private double computePerfQValue(final Element source, final Element ref, final String stereotypeName,
                                     final String tag) {

        Stereotype stereotype = source.getAppliedStereotype(GQAM_NAMESPACE + stereotypeName);
        EList<?> values = (EList<?>) source.getValue(stereotype, tag);

        double sourceValue = 0d;
        if (!values.isEmpty())
            sourceValue = Double.parseDouble(values.get(0).toString());

        stereotype = ref.getAppliedStereotype(GQAM_NAMESPACE + stereotypeName);
        values = (EList<?>) ref.getValue(stereotype, tag);

        double refValue = 0d;
        if (!values.isEmpty())
            refValue = Double.parseDouble(values.get(0).toString());

        return (refValue + sourceValue) == 0 ? 0d : (refValue - sourceValue) / (refValue + sourceValue);
    }

    private List<EObject> filterByStereotype(Collection<EObject> elements, String stereotypeNamespace) {
        return elements.stream().filter(e -> ((Element) e).getAppliedStereotype(stereotypeNamespace) != null)
                .collect(Collectors.toList());
    }

    private String getXmiId(EmfModel model, EObject element) {
        return ((XMLResource) model.getResource()).getID(element);
    }

    /**
     * Invokes the ETL engine in order to run the UML2LQN transformation.
     */
    public void applyTransformation() {

        new WorkflowUtils().applyTransformation(this.modelPath);
    }

    public void executeRefactoring() {
        final Refactoring ref = getVariable(VARIABLE_INDEX);

        for (RefactoringAction a : ref.getActions()) {

            long initialTime = System.nanoTime();
            try {
                a.execute();
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();// TODO: handle exception
            }
            long durationTime = System.nanoTime() - initialTime;
            new FileUtils().refactoringStatsDumpToCSV(String.format("%s,%s", a.toCSV(), durationTime));
        }
        this.setRefactored();
        new UMLMemoryOptimizer().cleanup();
    }

    @Override
    public void computeReliability() {

        System.out.print("Computing reliability ... ");
        // stores the in memory model to a file
        UMLReliability uml = null;
        long initTime = System.currentTimeMillis();
        try {
            uml = new UMLReliability(new UMLModelPapyrus(modelPath.toString()).getModel());
            reliability = new Reliability(uml.getScenarios()).compute();

            ResourceSet rs = uml.getModel().eResource().getResourceSet();
            while (rs.getResources().size() > 0) {
                Resource res = rs.getResources().get(0);
                res.eAdapters().clear();
                res.unload();
                rs.getResources().remove(res);
            }
        } catch (MissingTagException e) {
            System.err.println("Error in computing the reliability");
            String line = this.name + "," + e.getMessage() + "," + getVariable(VARIABLE_INDEX).toString();

            new FileUtils().reliabilityErrorLogToCSV(line);
        }
        long durationTime = System.currentTimeMillis() - initTime;
        new FileUtils().processStepStatsDumpToCSV(String.format("%s,%s,reliability,%s", algorithm, problemName, durationTime));

        new UMLMemoryOptimizer().cleanup();
        uml = null;
        System.out.println("done");

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        UMLRSolution other = (UMLRSolution) obj;

        for (String k : createdRefactoringElement.keySet()) {
            if (createdRefactoringElement.get(k).size() != other.createdRefactoringElement.get(k).size())
                return false;
            for (String e : createdRefactoringElement.get(k)) {
                if (!other.createdRefactoringElement.get(k).contains(e))
                    return false;
            }
        }
        for (String k : targetRefactoringElement.keySet()) {
            if (targetRefactoringElement.get(k).size() != other.targetRefactoringElement.get(k).size())
                return false;
            for (String e : targetRefactoringElement.get(k)) {
                if (!other.targetRefactoringElement.get(k).contains(e))
                    return false;
            }
        }
        if (folderPath == null ^ other.folderPath == null)
            return false;
        return true;
    }

    // Set Reliability.
    // If the rel param is greater than 1 reliability is set to 1
    public void setReliability(double rel) {
        this.reliability = rel < 1 ? rel : 1;
    }

    public void setPAs(int pas) {
        this.numPAs = pas;
    }

    void setRefactoring(Refactoring ref) {
        initMap();
        for (RefactoringAction action : ref.getActions()) {
            for (String k : action.getTargetElements().keySet()) {
                action.getTargetElements().get(k).forEach(v -> this.targetRefactoringElement.get(k).add(v));
            }
            for (String k : action.getCreatedElements().keySet()) {
                action.getCreatedElements().get(k).forEach(v -> this.createdRefactoringElement.get(k).add(v));
            }
        }

    }

    /**
     * Returns the immutable map of elements which have been
     * targeted of any feasible refactoring action
     *
     * @return the immutable map of available target elements for new refactoring actions
     */
    public Map<String, Set<String>> getAvailableElements() {
        return Map.copyOf(targetRefactoringElement);
    }

}
