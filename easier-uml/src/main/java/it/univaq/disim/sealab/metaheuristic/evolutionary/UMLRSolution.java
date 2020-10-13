package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.ocl.ParserException;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.impl.NodeImpl;
import org.eclipse.uml2.uml.internal.impl.UseCaseImpl;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.solutionattribute.impl.CrowdingDistance;

import it.univaq.disim.sealab.easier.uml.utils.XMLUtil;
import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.epsilon.etl.ETLStandalone;
import it.univaq.disim.sealab.epsilon.evl.EVLStandalone;
import it.univaq.disim.sealab.metaheuristic.actions.Refactoring;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.RefactoringActionFactory;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.EasierLogger;
import it.univaq.sealab.umlreliability.MissingTagException;
import it.univaq.sealab.umlreliability.Reliability;
import it.univaq.sealab.umlreliability.UMLReliability;
import it.univaq.sealab.umlreliability.model.UMLModelPapyrus;
import logicalSpecification.Action;
import logicalSpecification.FOLSpecification;

/**
 * @author peo
 *
 */
@SuppressWarnings("serial")
public class UMLRSolution extends RSolution {

	private UUID ID;

	private Path folderPath;

	private EasierUmlModel dirtyIModel;

	private final static String refactoringLibraryModule, uml2lqnModule;
	private final static String GQAM_NAMESPACE;


	static {
		refactoringLibraryModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-refactoringLibrary", "evl", "AP-UML-MARTE.evl").toString();
		uml2lqnModule = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-uml2lqn", "org.univaq.uml2lqn").toString();

		GQAM_NAMESPACE = "MARTE::MARTE_AnalysisModel::GQAM::";
		cleanedSolutionsIntegers = new ArrayList<>();
	}

	protected UMLRSolution(UMLRProblem<?> p) {
		super(p);
		setName();
		ID = UUID.randomUUID();
		resetParents();
		init();
		this.createRandomRefactoring(p.length_of_refactorings, p.number_of_actions, p.allowed_failures);
	}

	public UMLRSolution(UMLRSolution s) {
		super(s.getProblem());
		setName();
		ID = UUID.randomUUID();

		resetParents();
		init();

		this.copyRefactoringVariable(s.getVariableValue(VARIABLE_INDEX), this);

		for (int i = 0; i < s.problem.getNumberOfObjectives(); i++) {
			this.setObjective(i, s.getObjective(i));
		}

		this.attributes = s.attributes;
		this.setAttribute(CrowdingDistance.class, s.getAttribute(CrowdingDistance.class));

	}

	public UMLRSolution(UMLRSolution s1, UMLRSolution s2, int point, boolean left) {
		super(s1.getProblem());

		setName();
		ID = UUID.randomUUID();

		init();

		for (int i = 0; i < s1.problem.getNumberOfVariables(); i++) {
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

		for (int i = 0; i < s1.problem.getNumberOfObjectives(); i++) {
			this.setObjective(i, s1.getObjective(i));
		}

		this.setAttribute(CrowdingDistance.class, 0.0);
	}

	protected void init() {

		parents = new UMLRSolution[2];

		this.setVariableValue(0,new Refactoring());

		folderPath = Paths.get(Configurator.eINSTANCE.getTmpFolder().toString(), String.valueOf((getName() / 100)),
				String.valueOf(getName()));
		modelPath = folderPath.resolve(getName() + ".uml");

		try {
			org.apache.commons.io.FileUtils.copyFile(this.problem.getSourceModelPath().toFile(), modelPath.toFile());

			try {
				dirtyIModel = EOLStandalone.createUmlModel("UML", modelPath, UMLPackage.eNS_URI, true, false);
			} catch (EolModelLoadingException | URISyntaxException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			System.out.println("[ERROR] The problem's model copy generated an error!!!");
			e.printStackTrace();
		}

	}

	protected void createRandomRefactoring(int l, int n, int a) {
		
		
		do {
			try {
				tryRandomPush(n);
			} catch (UnexpectedException | EolRuntimeException e) {
				e.printStackTrace();
			}
		} while (getVariableValue(VARIABLE_INDEX).getActions().size() < l);
		this.setAttribute(CrowdingDistance.class, 0.0);
	}

	protected void tryRandomPush(int n) throws UnexpectedException, EolRuntimeException {
		Refactoring temporary_ref = getVariableValue(VARIABLE_INDEX).clone(this);

		RefactoringAction candidate;
		do {
			candidate = RefactoringActionFactory.getRandomAction(this);
		} while (candidate == null);

		temporary_ref.getActions().add(candidate);

		if (this.isFeasible(temporary_ref)) {
			getVariableValue(VARIABLE_INDEX).getActions().add(candidate);
		} else {
			dirtyIModel.deleteElement(candidate);
		}
	}

	public boolean isFeasible() {
		return isFeasible(getVariableValue(VARIABLE_INDEX));
	}

	public boolean isFeasible(Refactoring tr) {

		// Finds multiple modification of the same target element
		for (RefactoringAction a : tr.getActions()) {
			int j = 0;
			while (j < tr.getActions().size()) {
				Action a2 = tr.getActions().get(j);
				if (a.equals(a2) && j != tr.getActions().indexOf(a)) {
					EasierLogger.logger_
							.warning("Multi-modification of the same operation for Solution #" + this.getName() + "!");
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

	/**
	 * Prints a VAR file
	 */
	@Override
	public String getVariableValueString(int index) {
		String strValue = this.getName() + ";";

		List<Double> objs = new ArrayList<>();
		for (int i = 0; i < getNumberOfObjectives(); i++) {
			objs.add(getObjective(i));
		}

		strValue += objs.stream().map(o -> String.valueOf(o)).collect(Collectors.joining(";"));
		strValue += ";";
		strValue += this.getVariableValue(0).getActions().stream().map(act -> act.toString())
				.collect(Collectors.joining(","));
		return strValue;
	}

	protected void copyRefactoringVariable(Refactoring variableValue, RSolution sol) {
		this.setVariableValue(VARIABLE_INDEX, variableValue.clone(this));
	}

	@Override
	public Solution<Refactoring> copy() {
		return new UMLRSolution(this);
	}

	protected void createChild(UMLRSolution s1, UMLRSolution s2, int point) {

		Refactoring ref = new Refactoring();

		try {
			for (int j = 0; j < point; j++) {
				RefactoringAction _new = s1.getActionAt(j).clone(this);
				ref.getActions().add(j, _new);
			}
			for (int j = point; j < s2.getVariableValue(VARIABLE_INDEX).getActions().size(); j++) {
				RefactoringAction _new = s2.getActionAt(j).clone(this);
				ref.getActions().add(j, _new);
			}
			this.setVariableValue(VARIABLE_INDEX, ref);
		} catch (IndexOutOfBoundsException e) {
			EasierLogger.logger_.warning("POINT SIZE ERROR: " + Integer.toString(point));
			e.printStackTrace();
		}
	}

	@Override
	public boolean alter(int i) {

		Refactoring temporary_ref = getVariableValue(VARIABLE_INDEX).clone(this);

		RefactoringAction candidate;
		do {
			candidate = RefactoringActionFactory.getRandomAction(this);
		} while (candidate == null);

		temporary_ref.getActions().set(i, candidate);

		if (this.isFeasible(temporary_ref)) {
			temporary_ref = null;
			getVariableValue(VARIABLE_INDEX).getActions().remove(i);
			getVariableValue(VARIABLE_INDEX).getActions().add(i, candidate);
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
	 * This method counts the number of Performance Antipatterns (PAs) Shall invoke
	 * the PADRE perf-detection file
	 */
	public void countingPAs() {

		System.out.print("Counting PAs (it may take a while) ... ");
		EVLStandalone pasCounter = new EVLStandalone();
		try {
			EasierUmlModel uml = EpsilonStandalone.createUmlModel("UML", modelPath, null, true, false);
			pasCounter.setModel(uml);
			pasCounter.setSource(Paths.get(refactoringLibraryModule));

			numPAs = pasCounter.getPAs();

		} catch (EolModelLoadingException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pasCounter.clearMemory();
			pasCounter = null;
		}
		System.out.println("done");
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
		System.out.print("Invoking LQN Solver ... ");// Remove comments for the real invocation");

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
			getVariableValue(VARIABLE_INDEX).getActions().forEach(System.out::println);
			final Path reportFilePath = Configurator.eINSTANCE.getOutputFolder().resolve("reportFailedSolution.csv");
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(reportFilePath.toFile(), true))) {
				String line = String.valueOf(this.name) + ";";

				line += lqnError;
				line += ";";
				line += getVariableValue(VARIABLE_INDEX).getActions().stream().map(act -> act.toString())
						.collect(Collectors.joining(","));
				line += "\n";
				bw.append(line);
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
			e.printStackTrace();
		}
		
		process = null;

		System.out.println("done");

		System.out.print("Invoking the back annotator... ");
		backAnnotation();
		System.out.println("done");
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

		EOLStandalone bckAnn = new EOLStandalone();

		try {
			EasierUmlModel uml = EpsilonStandalone.createUmlModel("UML", modelPath, null, true, false);

			bckAnn.setModel(uml);

			bckAnn.setSource(Paths.get(uml2lqnModule, "backAnnotation.eol"));

			// Points to lqn schema file and stores pacakges into the global package
			// registry
			XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
			String schema = Configurator.eINSTANCE.getUml2Lqn().resolve("org.univaq.uml2lqn").resolve("lqnxsd")
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

			bckAnn.execute();

			bckAnn.clearMemory();
			bckAnn = null;

		} catch (URISyntaxException | EolRuntimeException e) {
			final Path reportFilePath = Configurator.eINSTANCE.getOutputFolder().resolve("reportFailedSolution.csv");
			try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(reportFilePath.getParent().resolve("backAnnErrorLog.csv").toFile(), true))) {
				String line = String.valueOf(this.name) + ";";

				line += e.getMessage();
				line += ";";
				line += getVariableValue(VARIABLE_INDEX).getActions().stream().map(act -> act.toString())
						.collect(Collectors.joining(","));
				line += "\n";
				bw.append(line);
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
			e.printStackTrace();
		}
		
		bckAnn.clearMemory();
		bckAnn = null;

	}

	public double evaluatePerformance() {
		System.out.print("Counting perfQ ... ");
		perfQ = perfQ();
		System.out.println("done");
		return perfQ;
	}

	/**
	 *
	 * @param source
	 * @param refactored
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

		EasierUmlModel source = null;

		// The lists used to store the elements of both models
		List<EObject> sourceElements = new ArrayList<EObject>();
		List<EObject> refactoredElements = new ArrayList<EObject>();

		// The elements of the source model;
		List<EObject> nodes = null;
		List<EObject> scenarios = null;
		try {
			source = (EasierUmlModel) EpsilonStandalone.createUmlModel("UML", problem.sourceModelPath, null, true,
					false);
			nodes = (List<EObject>) source.getAllOfType("Node");
			scenarios = (List<EObject>) source.getAllOfType("UseCase");
		} catch (EolModelLoadingException | URISyntaxException | EolModelElementTypeNotFoundException e) {
			e.printStackTrace();
		}

		// Variable representing the perfQ value
		double value = 0d;

		// The function considers only the elements having the stereotypes GaExecHosta
		// and GaScenario
		nodes = filterByStereotype(nodes, GQAM_NAMESPACE + "GaExecHost");
		scenarios = filterByStereotype(scenarios, GQAM_NAMESPACE + "GaScenario");

		sourceElements.addAll(scenarios);
		sourceElements.addAll(nodes);

		// Lists needed to store the values of the performance metrics
		ArrayList<Double> sourceMetrics = new ArrayList<Double>();
		ArrayList<Double> refactoredMetrics = new ArrayList<Double>();

		try {
			EasierUmlModel uml = EpsilonStandalone.createUmlModel("UML", modelPath, null, true, false);

			// for each elements of the source model, it is picked the element with the same
			// id in the refactored one
			for (EObject element : sourceElements) {
				String id = getXmiId(source, element);

				EObject correspondingElement = (EObject) uml.getElementById(id);
				refactoredElements.add(correspondingElement);

				// for each model element, it is collected the performance metric and added to
				// the vector

				sourceMetrics.addAll(getMetrics(element));
				refactoredMetrics.addAll(getMetrics(correspondingElement));

			}
		} catch (Exception e) {
			System.err.println(String.format("Solution # '%s' has trown an error while computing PerfQ!!!", this.name));
			e.printStackTrace();
		}

		// This loop calculates the perfQ value
		Iterator<Double> it1 = sourceMetrics.iterator();
		Iterator<Double> it2 = refactoredMetrics.iterator();

		while (it1.hasNext() && it2.hasNext()) {

			Double sourceValue = it1.next();
			Double refactoredValue = it2.next();

			if ((sourceValue + refactoredValue) == 0)
				value += 0d;
			else
				value += (sourceValue - refactoredValue) / (sourceValue + refactoredValue);
		}

		return value / sourceMetrics.size();

	}

	public ArrayList<Double> getMetrics(EObject element) throws Exception {

		ArrayList<Double> metrics = new ArrayList<Double>();

		if (element instanceof UseCaseImpl) {

			UseCaseImpl scenario = (UseCaseImpl) element;
			Stereotype stereotype = scenario.getAppliedStereotype(GQAM_NAMESPACE + "GaScenario");

			EList<?> throughput = (EList<?>) scenario.getValue(stereotype, "throughput");
			EList<?> respT = (EList<?>) scenario.getValue(stereotype, "respT");

			if (throughput.isEmpty())
				throw new java.lang.Exception("Throughput not found for the UseCase: " + scenario.getName());

			if (respT.isEmpty())
				throw new java.lang.Exception("ResponseTime not found for the UseCase: " + scenario.getName());

			metrics.add((Double.parseDouble(throughput.get(0).toString())));
			metrics.add(-1 * (Double.parseDouble(respT.get(0).toString())));

			scenario = null;
			stereotype = null;
			throughput.clear();
			respT.clear();

		} else if (element instanceof NodeImpl) {

			NodeImpl node = (NodeImpl) element;
			Stereotype stereotype = node.getAppliedStereotype(GQAM_NAMESPACE + "GaExecHost");
			EList<?> value = ((EList<?>) node.getValue(stereotype, "utilization"));

			if (value.isEmpty()) {
				metrics.add(0d); // TODO TBC after fixing the backAnnotation
				// throw new java.lang.Exception("Utilization not found for the Node: " +
				// node.getName());
			} else {
				metrics.add(-1 * Double.parseDouble(value.get(0).toString()));
			}

			node = null;
			stereotype = null;
			value.clear();
		}

		return metrics;

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

		System.out.print("Applying transformation ... ");

		try {
			EasierUmlModel uml = EpsilonStandalone.createUmlModel("UML", this.modelPath, null, true, false);

			ETLStandalone executor = new ETLStandalone(this.modelPath.getParent());
			executor.setSource(Paths.get(uml2lqnModule, "uml2lqn.etl"));
			executor.setModel(uml);
			executor.setModel(
					executor.createXMLModel(
							"LQN", this.modelPath.getParent().resolve("output.xml"), org.eclipse.emf.common.util.URI
									.createFileURI(Paths.get(uml2lqnModule, "lqnxsd", "lqn.xsd").toString()),
							false, true));

			executor.execute();
			executor.clearMemory();

		} catch (Exception e) {
			System.err.println("Error in runnig the ETL transformation");

			final Path reportFilePath = Configurator.eINSTANCE.getOutputFolder().resolve("reportFailedSolution.csv");
			try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(reportFilePath.getParent().resolve("etlErrorLog.csv").toFile(), true))) {
				String line = String.valueOf(this.name) + ";";

				line += ((EolRuntimeException) e).getReason() + " at line " + ((EolRuntimeException) e).getLine();
				line += ";";
				line += getVariableValue(VARIABLE_INDEX).getActions().stream().map(act -> act.toString())
						.collect(Collectors.joining(","));
				line += "\n";
				bw.append(line);
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
			e.printStackTrace();
		}
		System.out.println("done");
	}

	public void executeRefactoring() {
		final Refactoring ref = getVariableValue(VARIABLE_INDEX);

		for (RefactoringAction a : ref.getActions()) {

			try {
				a.execute();
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
		this.setRefactored();

	}

	public EasierUmlModel getDirtyIModel() {
		return dirtyIModel;
	}

	@Override
	public void computeReliability() {

		System.out.print("Computing reliability ... ");
		// stores the in memory model to a file
		try {
			final UMLReliability uml = new UMLReliability(new UMLModelPapyrus(modelPath.toString()).getModel());
			reliability = new Reliability(uml.getScenarios()).compute();
			
			ResourceSet rs = uml.getModel().eResource().getResourceSet();
			while (rs.getResources().size() > 0) {
				Resource res = rs.getResources().get(0);
				res.eAdapters().clear();
				res.unload();
				rs.getResources().remove(res);
			}
			CacheAdapter.getInstance().clear();
			
		} catch (MissingTagException e) {
			System.err.println("Error in computing the reliability");

			final Path reportFilePath = Configurator.eINSTANCE.getOutputFolder().resolve("reportFailedSolution.csv");
			try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(reportFilePath.getParent().resolve("relErrorLog.csv").toFile(), true))) {
				String line = String.valueOf(this.name) + ";";
				line += e.getMessage() + ";";
				line += getVariableValue(VARIABLE_INDEX).getActions().stream().map(act -> act.toString())
						.collect(Collectors.joining(","));
				line += "\n";
				bw.append(line);

			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
		}

		System.out.println("done");

	}

	@Override
	public void freeMemory() {
		try {
			if (dirtyIModel != null) {
				dirtyIModel.dispose();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
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
		result = prime * result + ((getVariableValue(VARIABLE_INDEX) == null) ? 0 : getVariableValue(VARIABLE_INDEX).hashCode());
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
		if (getVariableValue(VARIABLE_INDEX) == null) {
			if (other.getVariableValue(VARIABLE_INDEX) != null)
				return false;
		} else if (!getVariableValue(VARIABLE_INDEX).equals(other.getVariableValue(VARIABLE_INDEX)))
			return false;
		return true;
	}
	
	@Override
	public void freeMemory() {
		try {
			if(dirtyIModel != null) {
				dirtyIModel.clearCache();
				dirtyIModel = null;
			}
			if(iModel != null) {
				iModel.clearCache();
				iModel = null;
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		this.getVariableValue(0).getRefactoring().getActions().forEach(a -> a.freeMemory());
		System.out.println(String.format("Solution '%s' cleaned", this.name));
		cleanedSolutionsIntegers.add(this.name);
	}

}
