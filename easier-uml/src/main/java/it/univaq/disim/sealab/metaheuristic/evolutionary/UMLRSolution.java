package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import it.univaq.disim.sealab.easier.uml.utils.WorkflowUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.ocl.ParserException;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UseCase;
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
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import it.univaq.disim.sealab.metaheuristic.utils.FileUtils;
import it.univaq.sealab.umlreliability.MissingTagException;
import it.univaq.sealab.umlreliability.Reliability;
import it.univaq.sealab.umlreliability.UMLReliability;
import it.univaq.sealab.umlreliability.model.UMLModelPapyrus;
import logicalSpecification.Action;
import logicalSpecification.FOLSpecification;

/**
 * @author peo
 */
public class UMLRSolution extends RSolution<Refactoring> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private UUID ID;

    private Path folderPath;

    private EasierUmlModel dirtyIModel;

    private double[] scenarioRTs;

    private String algorithm;

    private final static String refactoringLibraryModule, uml2lqnModule;
    private final static String GQAM_NAMESPACE;

    private Map<String, Map<String, Double>> extractFuzzyValues;

    static {
        refactoringLibraryModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
                "easier-refactoringLibrary", "evl", "AP-UML-MARTE.evl").toString();
        uml2lqnModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
                "easier-uml2lqn", "org.univaq.uml2lqn").toString();

        GQAM_NAMESPACE = "MARTE::MARTE_AnalysisModel::GQAM::";
    }

    public UMLRSolution(int numberOfVariables, int numberOfObjectives, RProblem<?> p) {
        super(numberOfVariables, numberOfObjectives, p);

        setName();
        ID = UUID.randomUUID();
        resetParents();
        init();
        this.createRandomRefactoring();
    }

    public UMLRSolution(UMLRSolution s) {
        super(s.getNumberOfVariables(), s.getNumberOfObjectives());

        this.allowed_failures = s.allowed_failures;
        this.length_of_refactorings = s.length_of_refactorings;
        this.sourceModelPath = s.sourceModelPath;
        this.problemName = s.getProblemName();

        setName();
        ID = UUID.randomUUID();

        resetParents();
        init();

        this.copyRefactoringVariable((Refactoring) s.getVariable(VARIABLE_INDEX));

        for (int i = 0; i < s.getNumberOfObjectives(); i++) {
            this.setObjective(i, s.getObjective(i));
        }

        this.attributes = s.attributes;
        this.setAttribute(CrowdingDistance.class, s.getAttribute(CrowdingDistance.class));

    }

    public UMLRSolution(UMLRSolution s1, UMLRSolution s2, int point, boolean left) {
        super(s1.getNumberOfVariables(), s1.getNumberOfObjectives());

        this.allowed_failures = s1.allowed_failures;
        this.length_of_refactorings = s1.length_of_refactorings;
        this.sourceModelPath = s1.sourceModelPath;
        this.problemName = s1.getProblemName();

        setName();
        ID = UUID.randomUUID();

        init();

        for (int i = 0; i < s1.getNumberOfVariables(); i++) {
            if (i == VARIABLE_INDEX) {
                if (left) {
                    this.createChild(s1, s2, point);
                } else {
                    this.createChild(s2, s1, point);
                }
            } else {
                try {
                    throw new UnexpectedException("Should not happen");
                } catch (UnexpectedException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < s1.getNumberOfObjectives(); i++) {
            this.setObjective(i, s1.getObjective(i));
        }

        this.setAttribute(CrowdingDistance.class, 0.0);
    }

    protected void init() {

        parents = new UMLRSolution[2];
        scenarioRTs = new double[3];

        Refactoring refactoring = new Refactoring();
        refactoring.setSolutionID(this.getName());
        this.setVariable(0, refactoring);

        folderPath = Paths.get(Configurator.eINSTANCE.getTmpFolder().toString(), String.valueOf((getName() / 100)),
                String.valueOf(getName()));
        modelPath = folderPath.resolve(getName() + ".uml");

        algorithm = this.problemName.split("__")[0];

        try {
            org.apache.commons.io.FileUtils.copyFile(sourceModelPath.toFile(), modelPath.toFile());

            try {
                dirtyIModel = EOLStandalone.createUmlModel(modelPath.toString());
            } catch (EolModelLoadingException | URISyntaxException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("[ERROR] The problem's model copy generated an error!!!");
            e.printStackTrace();
        }

    }

    protected void createRandomRefactoring() {

        int num_failures = 0;

        do {
            try {
                if (!tryRandomPush())
                    num_failures++;
                if (num_failures >= allowed_failures) {
                    // START OVER
                    num_failures = 0;
                    Refactoring refactoring = new Refactoring();
                    refactoring.setSolutionID(this.getName());
                    this.setVariable(VARIABLE_INDEX, refactoring);
                    // EasierLogger.logger_.warning(String.format("Allowed failures '%s' reached...
                    // Created an empty refactoring!", problem.allowed_failures));
                }
            } catch (UnexpectedException | EolRuntimeException e) {
                e.printStackTrace();
            }
        } while (((Refactoring) getVariable(VARIABLE_INDEX)).getActions().size() < length_of_refactorings);
        this.setAttribute(CrowdingDistance.class, 0.0);
    }

    protected boolean tryRandomPush() throws UnexpectedException, EolRuntimeException {

        Refactoring temporary_ref = ((Refactoring) getVariable(VARIABLE_INDEX)).clone(this);

        RefactoringAction candidate;
        do {
            candidate = RefactoringActionFactory.getRandomAction(this);
        } while (candidate == null);

        temporary_ref.getActions().add(candidate);

        if (this.isFeasible(temporary_ref)) {
            ((Refactoring) getVariable(VARIABLE_INDEX)).getActions().add(candidate);
            return true;
        } else {
            dirtyIModel.deleteElement(candidate);
        }
        return false;

    }

    public boolean isFeasible() {
        return isFeasible(getVariable(VARIABLE_INDEX));
    }

    public boolean isFeasible(Refactoring tr) {

        // Finds multiple modification of the same target element
        for (RefactoringAction a : tr.getActions()) {
            int j = 0;
            while (j < tr.getActions().size()) {
                Action a2 = tr.getActions().get(j);
                if (a.equals(a2) && j != tr.getActions().indexOf(a)) {

                    return false;
                }
                j++;
            }
        }

        FOLSpecification app = Manager.calculatePreCondition(tr).getConditionFormula();
        try {
            return Manager.evaluateFOL(app, (EObject) dirtyIModel.allContents().toArray()[0]);
        } catch (ParserException e) {
            EasierLogger.logger_
                    .info("Precondition of Solution # " + this.getName() + " has generated a Parser Exception!");
            e.printStackTrace();
        }

        return false;
    }

    protected void copyRefactoringVariable(Refactoring variableValue) {
        this.setVariable(VARIABLE_INDEX, variableValue.clone(this));
    }

    @Override
    public Solution<Refactoring> copy() {
        return new UMLRSolution(this);
    }

    protected void createChild(UMLRSolution s1, UMLRSolution s2, int point) {

//		Refactoring ref = new Refactoring();

        try {
            for (int j = 0; j < point; j++) {
                RefactoringAction _new = s1.getActionAt(j).clone(this);
                this.getVariable(0).getActions().add(j, _new);
//				ref.getActions().add(j, _new);
            }
            for (int j = point; j < s2.getVariable(VARIABLE_INDEX).getActions().size(); j++) {
                RefactoringAction _new = s2.getActionAt(j).clone(this);
                this.getVariable(0).getActions().add(j, _new);
//				ref.getActions().add(j, _new);
            }
//			this.setVariable(VARIABLE_INDEX, ref);
        } catch (IndexOutOfBoundsException e) {
            EasierLogger.logger_.warning("POINT SIZE ERROR: " + Integer.toString(point));
            e.printStackTrace();
        }
    }

    @Override
    public boolean alter(int i) {

        Refactoring temporary_ref = getVariable(VARIABLE_INDEX).clone(this);

        RefactoringAction candidate;
        do {
            candidate = RefactoringActionFactory.getRandomAction(this);
        } while (candidate == null);

        temporary_ref.getActions().set(i, candidate);

        if (this.isFeasible(temporary_ref)) {
            temporary_ref = null;
            getVariable(VARIABLE_INDEX).getActions().remove(i);
            getVariable(VARIABLE_INDEX).getActions().add(i, candidate);
            return true;
        } else {
            try {
                dirtyIModel.deleteElement(candidate);
            } catch (EolRuntimeException e) {
                e.printStackTrace();
            }
            temporary_ref = null;
        }
        return false;

    }

    /**
     * This method counts the number of Performance Antipatterns (PAs) invoking
     * the PADRE perf-detection file
     */
    public void countingPAs() {

        System.out.print("Counting PAs (it may take a while) ... ");
        long startTime = System.currentTimeMillis();
        EVLStandalone pasCounter = new EVLStandalone();
//		EasierUmlModel uml = null;
        try (EasierUmlModel uml = EpsilonStandalone.createUmlModel(modelPath.toString())) {
//			uml = EpsilonStandalone.createUmlModel(modelPath.toString());
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
        new FileUtils().processStepStatsDumpToCSV(String.format("%s,%s,%s,counting_pas,%s,%s", "NSGA", problemName, getName(), getName(), duration));
        System.out.println(String.format(" execution time: %s ms", duration));

//		uml = null;
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
//            throw new RuntimeException(e);
//            final String lqnError = new BufferedReader(new InputStreamReader(process.getErrorStream())).lines()
//                    .map(act -> act.toString()).collect(Collectors.joining(","));
//            System.err.println("Solution # " + this.name);
//            System.err.println(lqnError);
//            getVariable(VARIABLE_INDEX).getActions().forEach(System.out::println);

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

        /*
        System.out.print("Invoking LQN Solver ... ");// Remove comments for the real invocation");

        long startTime = System.currentTimeMillis();

        Path lqnSolverPath = Configurator.eINSTANCE.getSolver();
        Path lqnModelPath = this.folderPath.resolve("output.xml");

        XMLUtil.conformanceChecking(lqnModelPath);

        // to allow cycles in the lqn model
        final String params = "-P cycles=yes";

        Process process = null;
        try {
            process = new ProcessBuilder(lqnSolverPath.toString(), params, lqnModelPath.toString()).start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            if (!this.folderPath.resolve("output.lqxo").toFile().exists()) {
                throw new Exception("[ERROR] the lqn solver has genered an error.");
            }
        } catch (Exception e) {
            final String lqnError = new BufferedReader(new InputStreamReader(process.getErrorStream())).lines()
                    .map(act -> act.toString()).collect(Collectors.joining(","));
            System.err.println("Solution # " + this.name);
            System.err.println(lqnError);
            getVariable(VARIABLE_INDEX).getActions().forEach(System.out::println);

            String line = this.name + "," + lqnError + "," + getVariable(VARIABLE_INDEX).toString();

            new FileUtils().failedSolutionLogToCSV(line);

//            final Path reportFilePath = Configurator.eINSTANCE.getOutputFolder().resolve("reportFailedSolution.csv");
//            try (BufferedWriter bw = new BufferedWriter(new FileWriter(reportFilePath.toFile(), true))) {
//                String line = String.valueOf(this.name) + ";";
//
//                line += lqnError;
//                line += ";";
//                line += getVariable(VARIABLE_INDEX).getActions().stream().map(act -> act.toString())
//                        .collect(Collectors.joining(","));
//                line += "\n";
//                bw.append(line);
//            } catch (IOException e1) {
//                System.out.println(e1.getMessage());
//            }
            e.printStackTrace();
        }

        process = null;

        long duration = System.currentTimeMillis() - startTime;
        new FileUtils().processStepStatsDumpToCSV(String.format("%s,%s,%s,invoke_solver,%s", "NSGA", problemName, getName(), duration));
        System.out.println(String.format("invokeSolver execution time: %s", duration));
        System.out.println("done");

        System.out.print("Invoking the back annotator... ");
        startTime = System.currentTimeMillis();
        backAnnotation();
        duration = System.currentTimeMillis() - startTime;
        new FileUtils().processStepStatsDumpToCSV(String.format("%s,%s,%s,lqn2uml,%s", "NSGA", problemName, getName(), duration));
        System.out.println(String.format("backAnnotation execution time: %s", duration));
        System.out.println("done");
         */
    }

    // Vincenzo's solution
    /*
     * public static PlainXmlModel loadXMLModel(String name, String model, String
     * path) throws EolModelLoadingException {
     *
     * PlainXmlModel resource = new PlainXmlModel();
     * ResourceFactoryRegistryImpl.INSTANCE.getExtensionToFactoryMap() .put("xml",
     * new GenericXMLResourceFactoryImpl());
     *
     * StringProperties properties = new StringProperties();
     * properties.put(PlainXmlModel.PROPERTY_NAME, name);
     * properties.put(PlainXmlModel.PROPERTY_FILE, model);
     * properties.put(PlainXmlModel.PROPERTY_READONLOAD, true);
     * properties.put("type", "xml");
     *
     * resource.load(properties); return resource; }
     *
     * String xmlModel =
     * Paths.get(MinimalExample.class.getResource("/output/agv.xml") .toURI())
     * .toString();
     *
     * String schema = Paths.get(MinimalExample.class.getResource("/lqnxsd/lqn.xsd")
     * .toURI()) .toString();
     *
     * XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder(); Collection<EObject>
     * generatedPackages = xsdEcoreBuilder.generate(URI.createURI(schema));
     *
     * // register the packages loaded from XSD for (EObject generatedEObject :
     * generatedPackages) { if (generatedEObject instanceof EPackage) { EPackage
     * generatedPackage = (EPackage) generatedEObject;
     * EPackage.Registry.INSTANCE.put(generatedPackage.getNsURI(),
     * generatedPackage); } }
     *
     * PlainXmlModel resource = loadXMLModel("LQN", xmlModel, "/output/agv.xml");
     */
    /*
    private void backAnnotation() {

        EOLStandalone bckAnn = new EOLStandalone();

        try (EasierUmlModel uml = EpsilonStandalone.createUmlModel(modelPath.toString())) {
//			EasierUmlModel uml = EpsilonStandalone.createUmlModel(modelPath.toString());

            bckAnn.setModel(uml);

            bckAnn.setSource(Paths.get(uml2lqnModule, "backAnnotation.eol"));

            // Points to lqn schema file and stores pacakges into the global package
            // registry
            XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
//			String schema = Configurator.eINSTANCE.getUml2Lqn().resolve("org.univaq.uml2lqn").resolve("lqnxsd")
//					.resolve("lqn.xsd").toString();
            String schema = Paths.get(uml2lqnModule, "lqnxsd", "lqn.xsd").toString();
            Collection<EObject> generatedPackages = xsdEcoreBuilder
                    .generate(org.eclipse.emf.common.util.URI.createURI(schema));
            for (EObject generatedEObject : generatedPackages) {
                if (generatedEObject instanceof EPackage) {
                    EPackage generatedPackage = (EPackage) generatedEObject;
                    EPackage.Registry.INSTANCE.put(generatedPackage.getNsURI(), generatedPackage);
                }
            }
            bckAnn.setModel(bckAnn.createPlainXMLModel("LQXO", folderPath.resolve("output.lqxo"), true, false, true));

            bckAnn.execute();

        } catch (URISyntaxException | EolRuntimeException e) {
            String line = this.name + "," + e.getMessage() + "," + getVariable(VARIABLE_INDEX).toString();

            new FileUtils().backAnnotationErrorLogToCSV(line);

//            final Path reportFilePath = Configurator.eINSTANCE.getOutputFolder().resolve("reportFailedSolution.csv");
//            try (BufferedWriter bw = new BufferedWriter(
//                    new FileWriter(reportFilePath.getParent().resolve("backAnnErrorLog.csv").toFile(), true))) {
//                String line = String.valueOf(this.name) + ";";
//
//                line += e.getMessage();
//                line += ";";
//                line += getVariable(VARIABLE_INDEX).getActions().stream().map(act -> act.toString())
//                        .collect(Collectors.joining(","));
//                line += "\n";
//                bw.append(line);
//            } catch (IOException e1) {
//                System.out.println(e1.getMessage());
//            }
            e.printStackTrace();
        }
        bckAnn.clearMemory();
        new UMLMemoryOptimizer().cleanup();
        bckAnn = null;
    }*/

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
     *
     * @return
     * @throws EolModelElementTypeNotFoundException
     * @url{https://doi.org/10.1109/ICSA-C.2018.00006}
     */
    private double perfQ() {

        /*
         * The updated model can have more nodes than the source node since original
         * nodes can be cloned. The benefits of cloning nodes is taken into account by
         * the performance model. For this reason, the perfQ analyzes only the
         * performance metrics of the nodes common among the models
         */
//		EasierUmlModel source = null;

        // The lists used to store the elements of both models
        List<EObject> sourceElements = new ArrayList<EObject>();

        // The elements of the source model;
        List<EObject> nodes = null;
        List<EObject> scenarios = null;
        try (EasierUmlModel source = (EasierUmlModel) EpsilonStandalone.createUmlModel(sourceModelPath.toString());
             EasierUmlModel uml = EpsilonStandalone.createUmlModel(modelPath.toString())) {
//			source = (EasierUmlModel) EpsilonStandalone.createUmlModel(sourceModelPath.toString());
            nodes = (List<EObject>) source.getAllOfType("Node");
            scenarios = (List<EObject>) source.getAllOfType("UseCase");


            // The function considers only the elements having the stereotypes GaExecHosta
            // and GaScenario
            nodes = filterByStereotype(nodes, GQAM_NAMESPACE + "GaExecHost");
            scenarios = filterByStereotype(scenarios, GQAM_NAMESPACE + "GaScenario");
            sourceElements.addAll(scenarios);
            sourceElements.addAll(nodes);
//        } catch (EolModelLoadingException | URISyntaxException | EolModelElementTypeNotFoundException e) {
//            e.printStackTrace();
//        }

            int numberOfMetrics = 0;
//		EasierUmlModel uml = null;
//        try (EasierUmlModel uml = EpsilonStandalone.createUmlModel(modelPath.toString())) {
//			uml = EpsilonStandalone.createUmlModel(modelPath.toString());

            // for each elements of the source model, it is picked the element with the same
            // id in the refactored one

            // Variable representing the perfQ value
            double value = 0d;
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
//			uml = null;
            return value / numberOfMetrics;
        } catch (Exception e) {
            System.err
                    .println(String.format("Solution # '%s' has thrown an error while computing PerfQ!!!", this.name));
            e.printStackTrace();
        }
        return 0;
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
//        EasierUmlModel uml = null;
        try (EasierUmlModel uml = EpsilonStandalone.createUmlModel(modelPath.toString())) {
//            uml = EpsilonStandalone.createUmlModel(modelPath.toString());
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
        /*
        System.out.print("Applying transformation in ... ");
        long startTime = System.currentTimeMillis();
//        EasierUmlModel uml = null;
        ETLStandalone executor = null;
        try (EasierUmlModel uml = EpsilonStandalone.createUmlModel(this.modelPath.toString())) {
//            uml = EpsilonStandalone.createUmlModel(this.modelPath.toString());

            executor = new ETLStandalone(this.modelPath.getParent());
            executor.setSource(Paths.get(uml2lqnModule, "uml2lqn.etl"));
            executor.setModel(uml);
            executor.setModel(
                    executor.createXMLModel(
                            "LQN", this.modelPath.getParent().resolve("output.xml"), org.eclipse.emf.common.util.URI
                                    .createFileURI(Paths.get(uml2lqnModule, "lqnxsd", "lqn.xsd").toString()),
                            false, true));

            executor.execute();
            executor.clearMemory();

        } catch (URISyntaxException | EolRuntimeException e) {
            System.err.println("Error when running the ETL transformation");

            String eolError = ((EolRuntimeException) e).getReason() + " at line " + ((EolRuntimeException) e).getLine();
            String line = this.name + "," + eolError + "," + getVariable(VARIABLE_INDEX).toString();

            new FileUtils().etlErrorLogToCSv(line);

//            final Path reportFilePath = Configurator.eINSTANCE.getOutputFolder().resolve("reportFailedSolution.csv");
//            try (BufferedWriter bw = new BufferedWriter(
//                    new FileWriter(reportFilePath.getParent().resolve("etlErrorLog.csv").toFile(), true))) {
//                String line = String.valueOf(this.name) + ";";
//
//                line += ((EolRuntimeException) e).getReason() + " at line " + ((EolRuntimeException) e).getLine();
//                line += ";";
//                line += getVariable(VARIABLE_INDEX).getActions().stream().map(act -> act.toString())
//                        .collect(Collectors.joining(","));
//                line += "\n";
//                bw.append(line);
//            } catch (IOException e1) {
//                System.out.println(e1.getMessage());
//            }
            e.printStackTrace();
        }
        new UMLMemoryOptimizer().cleanup();
//        uml = null;
        executor = null;

        long duration = System.currentTimeMillis() - startTime;
        new FileUtils().processStepStatsDumpToCSV(String.format("%s,%s,%s,uml2lqn,%s", algorithm, problemName, getName(), duration));
        System.out.println(String.format("%s done", duration));
         */
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

    public EasierUmlModel getDirtyIModel() {
        return dirtyIModel;
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
//            final Path reportFilePath = Configurator.eINSTANCE.getOutputFolder().resolve("reportFailedSolution.csv");
//            try (BufferedWriter bw = new BufferedWriter(
//                    new FileWriter(reportFilePath.getParent().resolve("relErrorLog.csv").toFile(), true))) {
//                String line = String.valueOf(this.name) + ";";
//                line += e.getMessage() + ";";
//                line += ((Refactoring) getVariable(VARIABLE_INDEX)).getActions().stream().map(act -> act.toString())
//                        .collect(Collectors.joining(","));
//                line += "\n";
//                bw.append(line);
//
//            } catch (IOException e1) {
//                System.out.println(e1.getMessage());
//            }
        }
        long durationTime = System.currentTimeMillis() - initTime;
        new FileUtils().processStepStatsDumpToCSV(String.format("%s,%s,reliability,%s", algorithm, problemName, durationTime));

        new UMLMemoryOptimizer().cleanup();
        uml = null;
        System.out.println("done");

    }

    @Override
    public void freeMemory() {
        if (dirtyIModel != null) {
            dirtyIModel.dispose();
        }
        System.out.println(String.format("Solution '%s' cleaned", this.name));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((ID == null) ? 0 : ID.hashCode());
        result = prime * result + VARIABLE_INDEX;
        result = prime * result + ((dirtyIModel == null) ? 0 : dirtyIModel.hashCode());
        result = prime * result + ((folderPath == null) ? 0 : folderPath.hashCode());
        result = prime * result + ((getVariable(VARIABLE_INDEX) == null) ? 0 : getVariable(VARIABLE_INDEX).hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        UMLRSolution other = (UMLRSolution) obj;
        if (ID == null) {
            if (other.ID != null)
                return false;
        } else if (!ID.equals(other.ID))
            return false;
        if (dirtyIModel == null) {
            if (other.dirtyIModel != null)
                return false;
        } else if (!dirtyIModel.equals(other.dirtyIModel))
            return false;
        if (folderPath == null) {
            if (other.folderPath != null)
                return false;
        } else if (!folderPath.equals(other.folderPath))
            return false;
        if (getVariable(VARIABLE_INDEX) == null) {
            if (other.getVariable(VARIABLE_INDEX) != null)
                return false;
        } else if (!getVariable(VARIABLE_INDEX).equals(other.getVariable(VARIABLE_INDEX)))
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

}
