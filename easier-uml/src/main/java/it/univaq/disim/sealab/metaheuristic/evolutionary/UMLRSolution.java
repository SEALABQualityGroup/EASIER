package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.epsilon.emc.emf.CachedResourceSet;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.ocl.ParserException;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.impl.NodeImpl;
import org.eclipse.uml2.uml.internal.impl.UseCaseImpl;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.solutionattribute.impl.CrowdingDistance;

import it.univaq.disim.sealab.easier.uml.utils.XMLUtil;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.epsilon.etl.ETLStandalone;
import it.univaq.disim.sealab.epsilon.evl.EVLStandalone;
import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.UMLRCrossover;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import it.univaq.sealab.umlreliability.Component;
import it.univaq.sealab.umlreliability.Link;
import it.univaq.sealab.umlreliability.Reliability;
import it.univaq.sealab.umlreliability.Scenario;
import it.univaq.sealab.umlreliability.UMLModelPapyrus;
import it.univaq.sealab.umlreliability.UMLReliability;

/**
 * @author peo
 *
 */
@SuppressWarnings("serial")
public class UMLRSolution extends RSolution {

//	private UMLRSolution[] parents = new UMLRSolution[2];

	private UMLMetamodelManager metamodelManager;
//	private Path modelPath;

//	private int name;
//	private static int SOLUTION_COUNTER = 0;
	private UUID ID;
	private final int VARIABLE_INDEX = 0;

//	private Manager manager;
//	private Controller controller;

	private Path folderPath;
//	private ResourceSet resourceSet;
//	private EObject model;

	private EasierUmlModel iModel, dirtyIModel;

	private final static Path refactoringLibraryModule, uml2lqnModule;

	static {
		refactoringLibraryModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-refactoringLibrary", "evl", "AP-UML-MARTE.evl");
		uml2lqnModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-uml2lqn", "org.univaq.uml2lqn");
	}

	protected UMLRSolution(UMLRProblem<?> p) throws ParserException, UnexpectedException {
		super(p);
		setName();
		ID = UUID.randomUUID();
		resetParents();
		init(p.getController());
//		crossovered = false;
//		mutated = false;
//		refactored = false;

		try {
			this.createRandomRefactoring(p.length_of_refactorings, p.number_of_actions, p.allowed_failures);
		} catch (UnexpectedException e) {
			System.err.println("Error in genereting a refactoring sequence of a new Solution with:");
			System.err.println("lenght --> " + p.length_of_refactorings + "Number of action -->" + p.number_of_actions
					+ "Allowed failures -->" + p.allowed_failures);
			e.printStackTrace();
		}
	}

	public UMLRSolution(UMLRSolution s) {
		super(s.getProblem());
		setName();
		ID = UUID.randomUUID();

		resetParents();
		init(s.problem.getController());

		this.copyRefactoringVariable(s.getVariableValue(VARIABLE_INDEX), this);

		for (int i = 0; i < s.problem.getNumberOfObjectives(); i++) {
			this.setObjective(i, s.getObjective(i));
		}

		this.attributes = s.attributes;
		this.setAttribute(CrowdingDistance.class, s.getAttribute(CrowdingDistance.class));

//		crossovered = false;
//		mutated = false;
//		refactored = false;
	}

	public UMLRSolution(UMLRSolution s1, UMLRSolution s2, int point, boolean left) {
		super(s1.getProblem());

		setName();
		ID = UUID.randomUUID();

		init(s1.problem.getController());

		for (int i = 0; i < s1.problem.getNumberOfVariables(); i++) {
			if (i == VARIABLE_INDEX) {
				if (left) {
					this.setVariableValue(VARIABLE_INDEX, this.createChild(s1, s2, point));
				} else {
					this.setVariableValue(VARIABLE_INDEX, this.createChild(s2, s1, point));

				}
			} else {
				try {
					throw new UnexpectedException("Should not happen");
				} catch (UnexpectedException e) {
					e.printStackTrace();
				}
			}

		}

		for (int i = 0; i < s1.problem.getNumberOfObjectives(); i++) {
			this.setObjective(i, s1.getObjective(i));
		}

		this.setAttribute(CrowdingDistance.class, 0.0);

//		crossovered = false;
//		mutated = false;
//		refactored = false;
	}

//	protected void resetParents() {
//		if (this.parents != null) {
//			this.parents[0] = null;
//			this.parents[1] = null;
//		}
//	}

	protected void init(Controller controller) {
		this.controller = controller;
		manager = new UMLManager();
		manager.setController(controller);

		parents = new UMLRSolution[2];

		setResourceSet(new ResourceSetImpl());

		manager.setMetamodelManager(new UMLMetamodelManager(controller));
		manager.setOclManager(manager.getMetamodelManager().getOclManager());
		manager.setOclStringManager(UMLOclStringManager.getInstance());

		metamodelManager = (UMLMetamodelManager) manager.getMetamodelManager();
		metamodelManager.setProblem(problem);

		folderPath = Paths.get(controller.getConfigurator().getTmpFolder().toString(),
				String.valueOf((getName() / 100)), String.valueOf(getName()));
		modelPath = Paths.get(folderPath.toString(), getName() + metamodelManager.getMetamodelFileExtension());

		try {
//			org.apache.commons.io.FileUtils.copyDirectory(this.problem.getSourceModelPath().getParent().toFile(),
//					modelPath.getParent().toFile());
			org.apache.commons.io.FileUtils.copyFile(this.problem.getSourceModelPath().toFile(), modelPath.toFile());

			try {

				// it is a temp models employed to identify refactoring actions
				dirtyIModel = EOLStandalone.createUmlModel("UML", modelPath, UMLPackage.eNS_URI, true, true);
				// shall clear the cache, it also removes loaded profiles... HANDLE WITH CARE
				CachedResourceSet.getCache().clear();
				// it is the real model on which refactoring action will be applied
				iModel = EOLStandalone.createUmlModel("UML", modelPath, UMLPackage.eNS_URI, true, true);

			} catch (EolModelLoadingException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Files.copy(this.problem.getSourceModelPath(), target);
		} catch (IOException e) {
			System.out.println("[ERROR] The problem's model copy generated an error!!!");
			e.printStackTrace();
		}

		// copyModel(this.problem.getSourceModelPath(), mmaemiliaFilePath);
		createNewModel(modelPath);
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public void createNewModel(Path modelFilePath) {
		try {
//			res = getResourceSet().getResource(URI.createFileURI(modelFilePath.toString()), true);
//			this.model = (AEmiliaSpecification) EcoreUtil.getObjectByType(res.getContents(),
//					mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
			this.model = metamodelManager.getModel(modelFilePath);
		} catch (Exception e) {
			System.err.println("Error in creating the model for Solution #" + this.getName());
			System.err.println(this.getVariableValue(0).toString());
			e.printStackTrace();
		}
	}

	protected void createRandomRefactoring(int l, int n, int a) throws UnexpectedException, ParserException {
		UMLRSequence seq = new UMLRSequence(l, n, a, this);
		this.setVariableValue(VARIABLE_INDEX, seq);
		this.setAttribute(CrowdingDistance.class, 0.0);
	}

	@Override
	public String getVariableValueString(int index) {
		String strValue = "Solution ID : " + this.getName() + " ( " + getObjective(0) + ", " + getObjective(1) + ", "
				+ getObjective(2) + " )" + "\n\t";
		strValue += getVariableValue(index).toString();
		return strValue;
	}

	public RProblem<UMLRSolution> getProblem() {
		return (RProblem<UMLRSolution>) this.problem;
	}

	protected void copyRefactoringVariable(RSequence variableValue, RSolution sol) {
		UMLRSequence newSeq = new UMLRSequence((UMLRSequence) variableValue, (UMLRSolution) sol);

		this.setVariableValue(VARIABLE_INDEX, newSeq);
	}

	@Override
	public Solution<RSequence> copy() {
		return new UMLRSolution(this);
	}

	protected UMLRSequence createChild(UMLRSolution s1, UMLRSolution s2, int point) {

		UMLRSequence seq = new UMLRSequence(this);

		try {
			for (int j = 0; j < point; j++) {
				RefactoringAction _new = s1.getActionAt(j).clone(this);
				// _new.setSolution(this);
				seq.insert(j, _new);
			}
			for (int j = point; j < s2.getLength(); j++) {
				RefactoringAction _new = s2.getActionAt(j).clone(this);
				// _new.setSolution(this);
				seq.insert(j, _new);
			}
			return seq;
		} catch (IndexOutOfBoundsException e) {
			EasierLogger.logger_.warning("POINT SIZE ERROR: " + Integer.toString(point));
			e.printStackTrace();
			return null;
		}
	}

	public RefactoringAction getActionAt(int index) {
		return getVariableValue(VARIABLE_INDEX).get(index);
	}

	public int getLength() {
		return getVariableValue(VARIABLE_INDEX).getLength();
	}

	public boolean alter(int i) throws UnexpectedException, ParserException {
		return this.getVariableValue(VARIABLE_INDEX).alter(i, this.problem.number_of_actions);
	}

	@Deprecated
	public void updateThresholds() {
		// TODO thersholds will be automatically calculate by the EVL engine.
	}

	public void updateModel() {
		// TODO understand if it needed
	}

	/**
	 * This method counts the number of Performance Antipatterns (PAs) Shall invoke
	 * the PADRE perf-detection file
	 */
	public void countingPAs() {

		EVLStandalone pasCounter = new EVLStandalone();

		pasCounter.setSource(refactoringLibraryModule);
		pasCounter.setModel(iModel);

		numPAs = pasCounter.getPAs();
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
		// TODO invoke LQN solver
		System.out.println("Invoking LQN Solver .....");// Remove comments for the real invocation");

		Path lqnSolverPath = this.manager.getController().getConfigurator().getSolver();
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
			((RSequence) this.getVariableValue(0)).getRefactoring().getActions().forEach(System.out::println);
			// this.iModel.dispose();
			// TODO write a report
			final Path reportFilePath = ((UMLController) this.controller).getReportFilePath();
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(reportFilePath.toFile(), true))) {
				String line = String.valueOf(this.name) + ";";

				line += lqnError;
				line += ";";
				line += ((RSequence) this.getVariableValue(0)).getRefactoring().getActions().stream()
						.map(act -> act.toString()).collect(Collectors.joining(","));
				line += "\n";
				bw.append(line);
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
			e.printStackTrace();
		}

		System.out.println("..... done");

		System.out.println("Invoking the back annotator...");
		backAnnotation();
		System.out.println("... done");
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
	private void backAnnotation() {
//		System.out.println("WARNING the back annotator is not yet invoked ... please remove the following comments");

		EOLStandalone bckAnn = new EOLStandalone();
		bckAnn.setModel(iModel);

		bckAnn.setSource(
				controller.getConfigurator().getUml2Lqn().resolve("org.univaq.uml2lqn").resolve("backAnnotation.eol"));

		// Points to lqn schema file and stores pacakges into the global package
		// registry
		XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
		String schema = controller.getConfigurator().getUml2Lqn().resolve("org.univaq.uml2lqn").resolve("lqnxsd")
				.resolve("lqn.xsd").toString();
		Collection<EObject> generatedPackages = xsdEcoreBuilder
				.generate(org.eclipse.emf.common.util.URI.createURI(schema));
		for (EObject generatedEObject : generatedPackages) {
			if (generatedEObject instanceof EPackage) {
				EPackage generatedPackage = (EPackage) generatedEObject;
				EPackage.Registry.INSTANCE.put(generatedPackage.getNsURI(), generatedPackage);
			}
		}

		bckAnn.setModel(bckAnn.createPlainXMLModel("LQXO", folderPath.resolve("output.lqxo"), true, false, true));

		try {
			bckAnn.execute();
		} catch (EolRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public float evaluatePerformance() {
		// TODO decide how to calculate perfQ, if it needed
		System.out.println("PerfQ is not yet decided... Please consider how to compute this objective");
		return perfQ = perfQ();
	}

	/**
	 *
	 * @param source
	 * @param refactored
	 * @return
	 * @throws EolModelElementTypeNotFoundException
	 */
	private float perfQ() {
		/*
		 * The updated model can have more nodes than the source node since original
		 * nodes can be cloned. The benefits of cloning nodes is taken into account by
		 * the performance model. For this reason, the perfQ analyzes only the
		 * performance metrics of the nodes common among the models
		 */

		EasierUmlModel source = ((UMLRProblem<?>) getProblem()).getSourceModel();

		// The lists used to store the elements of both models
		List<EObject> sourceElements = new ArrayList<EObject>();
		List<EObject> refactoredElements = new ArrayList<EObject>();

		// The elements of the source model;
		List<EObject> nodes = null;
		List<EObject> scenarios = null;
		try {
			nodes = (List<EObject>) source.getAllOfType("Node");
			scenarios = (List<EObject>) source.getAllOfType("UseCase");
		} catch (EolModelElementTypeNotFoundException e) {
			e.printStackTrace();
		}

		// Variable representing the perfQ value
		float value = 0f;

		// The function considers only the elements having the stereotypes GaExecHosta
		// and GaScenario
		nodes = filterByStereotype(nodes, "GQAM::GaExecHost");
		scenarios = filterByStereotype(scenarios, "GQAM::GaScenario");

		sourceElements.addAll(scenarios);
		sourceElements.addAll(nodes);

		// Lists needed to store the values of the performance metrics
		ArrayList<Double> sourceMetrics = new ArrayList<Double>();
		ArrayList<Double> refactoredMetrics = new ArrayList<Double>();

		// for each elements of the source model, it is picked the element with the same
		// id in the refactored one
		for (EObject element : sourceElements) {
			String id = getXmiId(source, element);

			EObject correspondingElement = (EObject) iModel.getElementById(id);
			refactoredElements.add(correspondingElement);

			// for each model element, it is collected the performance metric and added to
			// the vector
			sourceMetrics.addAll(getMetrics(element));
			refactoredMetrics.addAll(getMetrics(correspondingElement));
		}

		// This loop calculates the perfQ value
		Iterator<Double> it1 = sourceMetrics.iterator();
		Iterator<Double> it2 = refactoredMetrics.iterator();

		while (it1.hasNext() && it2.hasNext()) {

			Double sourceValue = it1.next();
			Double refactoredValue = it2.next();

			value += (sourceValue - refactoredValue) / (sourceValue + refactoredValue);
		}

		return value / sourceMetrics.size();

	}

	public static ArrayList<Double> getMetrics(EObject element) {

		ArrayList<Double> metrics = new ArrayList<Double>();

		if (element instanceof UseCaseImpl) {

			UseCaseImpl scenario = (UseCaseImpl) element;
			Stereotype stereotype = scenario.getAppliedStereotype("GQAM::GaScenario");

			EList<?> throughput = (EList<?>) scenario.getValue(stereotype, "throughput");
			EList<?> respT = (EList<?>) scenario.getValue(stereotype, "respT");

			if (throughput.isEmpty())
				throw new java.lang.RuntimeException("Throughput not found for the UseCase: " + scenario.getName());

			if (respT.isEmpty())
				throw new java.lang.RuntimeException("ResponseTime not found for the UseCase: " + scenario.getName());

			metrics.add((Double.parseDouble(throughput.get(0).toString())));
			metrics.add(-1 * (Double.parseDouble(respT.get(0).toString())));

		} else if (element instanceof NodeImpl) {

			NodeImpl node = (NodeImpl) element;
			Stereotype stereotype = node.getAppliedStereotype("GQAM::GaExecHost");
			EList<?> value = ((EList<?>) node.getValue(stereotype, "utilization"));

			if (value.isEmpty())
				throw new java.lang.RuntimeException("Utilization not found for the Node: " + node.getName());

			metrics.add(-1 * Double.parseDouble(value.get(0).toString()));
		}

		return metrics;

	}

	public static List<EObject> filterByStereotype(Collection<EObject> elements, String stereotypeNamespace) {
		return elements.stream().filter(e -> ((Element) e).getAppliedStereotype(stereotypeNamespace) != null)
				.collect(Collectors.toList());
	}

	public static String getXmiId(EmfModel model, EObject element) {
		return ((XMLResource) model.getResource()).getID(element);
	}

	/**
	 * Invokes the ETL engine in order to run the UML2LQN transformation.
	 */
	public void applyTransformation() {
		ETLStandalone executor = new ETLStandalone(this.modelPath.getParent());
		executor.setSource(uml2lqnModule.resolve("uml2lqn.etl"));
		executor.setModel(this.iModel);
		executor.setModel(
				executor.createXMLModel(
						"LQN", this.modelPath.getParent().resolve("output.xml"), org.eclipse.emf.common.util.URI
								.createFileURI(uml2lqnModule.resolve("lqnxsd").resolve("lqn.xsd").toString()),
						false, true));
		try {
			executor.execute();
		} catch (Exception e) {
			System.err.println("Error in runnig the ETL transformation");

			final Path reportFilePath = ((UMLController) this.controller).getReportFilePath();
			try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(reportFilePath.getParent().resolve("etlErrorLog.csv").toFile(), true))) {
				String line = String.valueOf(this.name) + ";";

				line += ((EolRuntimeException) e).getReason() + " at line " + ((EolRuntimeException) e).getLine();
				line += ";";
				line += ((RSequence) this.getVariableValue(0)).getRefactoring().getActions().stream()
						.map(act -> act.toString()).collect(Collectors.joining(","));
				line += "\n";
				bw.append(line);
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}

//			System.err.println("iModel stored!");

			e.printStackTrace();
		}
		executor.getModel().stream().filter(m -> "LQN".equals(m.getName())).findAny().orElse(null).dispose();
	}

	public void executeRefactoring() {
		Refactoring ref = this.getVariableValue(0).getRefactoring();

		for (RefactoringAction a : ref.getActions()) {

			try {
				a.execute();
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
		this.setRefactored();
		// Controller.logger_.info("Model of Solution #" + this.getName() + " has been
		// SAVED!");
	}

	public void setModel(EObject model) {
		this.model = model;
	}

	@Override
	public EObject getModel() {
		return (EObject) iModel.allContents().toArray()[0];
	}

	public EObject getDirtyModel() {
		return (EObject) dirtyIModel.allContents().toArray()[0];
	}

	public void refreshModel() {
		getResourceSet().getResources().forEach(resource -> resource.unload());
//		get(0).unload();
//		this.createNewModel(mmaemiliaFilePath);
		this.model = metamodelManager.getModel(modelPath);
	}

	public EasierUmlModel getIModel() {
		return iModel;
	}

	public EasierUmlModel getDirtyIModel() {
		return dirtyIModel;
	}

	@Override
	public void save() {
		iModel.dispose();
		try {
			iModel = EOLStandalone.createUmlModel("UML", modelPath, UMLPackage.eNS_URI, true, true);
		} catch (EolModelLoadingException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void computeReliability() {

		// stores the in memory model to a file
		if (iModel.isLoaded()) {
			iModel.dispose();
		}

		final UMLReliability uml = new UMLReliability(new UMLModelPapyrus(modelPath.toString()).getModel());
		final List<Scenario> scenarios = uml.getScenarios();
		final List<Component> components = uml.getComponents();
		final List<Link> links = uml.getLinks();

		reliability = new Reliability(scenarios, components, links).compute();

		// reloads the stored model
		// TODO verify is it is needed
		try {
			iModel.load();
		} catch (EolModelLoadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
