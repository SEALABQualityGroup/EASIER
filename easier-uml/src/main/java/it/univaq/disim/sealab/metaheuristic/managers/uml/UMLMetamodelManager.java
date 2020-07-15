package it.univaq.disim.sealab.metaheuristic.managers.uml;

import java.io.IOException;
import java.nio.file.Path;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.papyrus.MARTE.MARTEPackage;
import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.GQAM.GQAMPackage;
import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.PAM.PAMPackage;
import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.SAM.SAMPackage;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Manifestation;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UMLPlugin;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLAddComponentRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLAddNodeRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLAddOperationRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLDeleteComponentRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLDeleteNodeRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLDeleteOperationRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMoveComponentRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMoveOperationRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSequence;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclStringManager;
import logicalSpecification.Action;
import logicalSpecification.actions.UML.UMLAddComponentAction;
import logicalSpecification.actions.UML.UMLDeleteComponentAction;
import logicalSpecification.actions.UML.UMLDeleteOperationAction;
import logicalSpecification.actions.UML.UMLFactory;
import logicalSpecification.actions.UML.UMLMoveComponentAction;
import logicalSpecification.actions.UML.UMLMoveOperationAction;

public class UMLMetamodelManager extends MetamodelManager {

	// private static final String REFACTORED_MODEL_PATH =
	// "/src/main/resources/models/refactored/BGCS/BGCS_";
	private Model model;

	// private ResourceSet set;
	// private Resource resource;
	private UMLResource umlResource;

	// private UMLOclManager oclUMLManager;
	// private UMLOclStringManager oclUMLStringManager;

	private static UMLMetamodelManager instance;

	private UMLController controller;
	private Manager manager;

	private Map<URI, URI> uriMap;	
	
	public final static String DEPLOYMENT_VIEW = "Deployment View";
	public final static String STATIC_VIEW = "Static View";
	public final static String DYNAMIC_VIEW = "Dynamic View";

	public UMLMetamodelManager(Controller ctrl) {
		super();
		controller = (UMLController) ctrl;
		manager = controller.getManager();
		uriMap = resourceSet.getURIConverter().getURIMap();
	}

	@Override
	public RefactoringAction getRandomAction(int length, RSequence seq) throws UnexpectedException {

		int index = JMetalRandom.getInstance().getRandomGenerator().nextInt(0, length - 1);
		switch (index) {

		case 0:
			return getRandomAddNodeAction(seq);
		case 1:
			return getRandomAddComponentAction(seq);
		case 2:
			return getRandomAddOperationAction(seq);
		case 3:
			return getRandomMoveOpertionAction(seq);
		case 4:
			return getRandomMoveComponentAction(seq);
		case 5:
			return getRandomDeleteOperationAction(seq);
		case 6:
			return getRandomDeleteComponentAction(seq);
		case 7:
			return getRandomDeleteNodeAction(seq);
		default:
			throw new UnexpectedException("");
		}
	}

	private RefactoringAction getRandomDeleteNodeAction(RSequence seq) {
//		Node node = this.getRandomNode();
		// Action action = new UMLDeleteNodeRefactoringAction(node);

//		UMLDeleteNodeAction action = UMLFactory.eINSTANCE.createUMLDeleteNodeAction();
//		UMLDeleteNodeRefactoringAction action = new UMLDeleteNodeRefactoringAction(node);
//		action.setUmlNodeToDel(node);
//		action.setParameters();
//		action.createPreCondition();
//		action.createPostCondition();
//		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
//		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));

		return new UMLDeleteNodeRefactoringAction(seq.getSolution());
	}

	private RefactoringAction getRandomAddNodeAction(RSequence seq) {
		
		UMLAddNodeRefactoringAction action = new UMLAddNodeRefactoringAction((UMLRSolution) seq.getSolution());
		
		List<Node> neighs = this.getRandomNodes();
		List<Component> deployedComps = this.getRandomComponents();
//		UMLAddNodeRefactoringAction action = new UMLAddNodeRefactoringAction(this.)
		action.getUmlNeighbors().addAll(neighs);
		action.getUmlCompsToDeploy().addAll(deployedComps);
		action.setUmlSourcePackage(this.getNodePackage());


		// Action action = new UMLAddNodeRefactoringAction(targets,
		// list_of_components);
		return action;
	}

	private RefactoringAction getRandomAddOperationAction(RSequence seq) {
		Component targetUMLComp = getRandomComponent();
		// Action action = new UMLAddOperationRefactoringAction(component);
		UMLAddOperationRefactoringAction action = new UMLAddOperationRefactoringAction(targetUMLComp);
		action.setUmlTargetComp(targetUMLComp);
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));

		return action;
	}

	private RefactoringAction getRandomDeleteComponentAction(RSequence seq) {
		Component component = getRandomComponent();
		// Action action = new UMLDeleteComponentRefactoringAction(component);

		UMLDeleteComponentRefactoringAction action = new UMLDeleteComponentRefactoringAction(component);
		action.setUmlCompToDel(component);
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));

		return action;
	}

	private RefactoringAction getRandomDeleteOperationAction(RSequence seq) {
		Operation operation = this.getRandomOperation();
		// Action action = new UMLDeleteOperationRefactoringAction(operation);

		UMLDeleteOperationRefactoringAction action = new UMLDeleteOperationRefactoringAction(operation);

		action.setUmlOpToDel(operation);
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();

		return action;
	}

	private RefactoringAction getRandomMoveComponentAction(RSequence seq) {
		Component component = getRandomComponent();
		List<Node> targets = getRandomNodes();
		// Action action = new UMLMoveComponentRefactoringAction(component,
		// list_of_nodes);

		UMLMoveComponentRefactoringAction action = new UMLMoveComponentRefactoringAction(component, targets);
		action.setUmlCompToMove(component);
		action.getUmlTargetNodes().addAll(targets);
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		return action;
	}

	private RefactoringAction getRandomMoveOpertionAction(RSequence seq) {
		Operation context = getRandomOperation();
		Component target = getRandomComponent();
		// Action action = new UMLMoveOperationRefactoringAction(operation,
		// target);
		UMLMoveOperationRefactoringAction action = new UMLMoveOperationRefactoringAction(context, target)
				;
		action.setUmlOpToMove(context);
		action.setUmlTargetComp(target);
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));

		return action;
	}

	private RefactoringAction getRandomAddComponentAction(RSequence seq) {
		List<Node> targets = getRandomNodes();
		UMLAddComponentRefactoringAction action = new UMLAddComponentRefactoringAction(targets, seq.getSolution().getManager().getMetamodelManager());
		// Action action = new UMLAddComponentRefactoringAction(list_of_nodes);
		action.getUmlTargetNodes().addAll(targets);
		action.setUmlSourcePackage(getComponentPackage());
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		return action;
	}

	@Override
	public Model getModel() {
		return model;
	}

	public Resource createModelResource() {
		// ResourceSet set = new ResourceSetImpl();
		// set.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		// set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
		// UMLResource.Factory.INSTANCE);

		Resource resource = getResourceSet().getResource(manager.string2FileUri(getModelUri().toString()), true);
		return resource;
	}

	public void saveRefactoredModel(String destinationPath) {
		unloadModelResource();

		// UMLResource res = (UMLResource) this.getResourceSet()
		Resource res = this.getResourceSet()
				// .createResource(Manager.getInstance(this).string2FileUri(destinationPath+"."+
				// UMLResource.FILE_EXTENSION));
				.createResource(manager.string2FileUri(destinationPath));

		res.getContents().add(getModel());

		try {
			res.save(null);
			System.out.println("Done.");
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}

	public Resource createModelResource(String umlModelUri) {
		ResourceSet set = new ResourceSetImpl();
		set.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
				UMLResource.Factory.INSTANCE);
		Resource resource = set.getResource(manager.string2FileUri(umlModelUri), true);
		return resource;
	}

	public Resource createModelResource(String umlModelUri, boolean loadOnDemand) {
		ResourceSet set = new ResourceSetImpl();
		set.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
				UMLResource.Factory.INSTANCE);
		Resource resource = set.getResource(manager.string2FileUri(umlModelUri), loadOnDemand);
		return resource;
	}

	public void packageRegistering() {
		initLocalRegistry();
		initGlobalRegistry();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
				UMLResource.Factory.INSTANCE);

		/*
		 * Needed to link the MARTE jar plugin to the system. It is requested in case of
		 * a standalone application
		 */
		String marteJarString = "jar:" + getClass()
				.getResource("/libs/org.eclipse.papyrus.marte.static.profile_1.2.0.201803031506.jar").toString() + "!/";

		URI marteJar = URI.createURI(marteJarString);
		uriMap.put(URI.createURI("pathmap://Papyrus_PROFILES/"), marteJar.appendSegment("resources").appendSegment(""));

		/*
		 * Must add the pathmap of the Parent package to the UMLPlugin'
		 * getEPackageNsURIToProfileLocationMap() The AnalysisModel package has been
		 * added as follows
		 */
		String PROFILES_PATHMAP = "pathmap://Papyrus_PROFILES/"; //$NON-NLS-1$
		// FROM model.uml #_4bV20APMEdyuUt-4qHuVvQ
		// MARTE FROM MARTE.profile.uml #_zaC5cAPHEdyeNfbOYuD9pg
		// GQAM FROM MARTE.profile.uml #_4bV20APMEdyuUt-4qHuVvQ
		// MARTE_AnlysisModel FROM MARTE.profile.uml #_u8y4wAPMEdyuUt-4qHuVvQ
		String MARTE_PROFILE_URI = PROFILES_PATHMAP + "MARTE.profile.uml#_u8y4wAPMEdyuUt-4qHuVvQ";
		URI marteProfileURI = URI.createURI(MARTE_PROFILE_URI);
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(MARTEPackage.eNS_URI, marteProfileURI);

	}

	public void initLocalRegistry() {
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(Ecore2XMLPackage.eNS_URI, Ecore2XMLPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(MARTEPackage.eNS_URI, MARTEPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(GQAMPackage.eNS_URI, GQAMPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(SAMPackage.eNS_URI, SAMPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(PAMPackage.eNS_URI, PAMPackage.eINSTANCE);
	}

	public void initGlobalRegistry() {
		EPackage.Registry.INSTANCE.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Ecore2XMLPackage.eNS_URI, Ecore2XMLPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(MARTEPackage.eNS_URI, MARTEPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(GQAMPackage.eNS_URI, GQAMPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(SAMPackage.eNS_URI, SAMPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(PAMPackage.eNS_URI, PAMPackage.eINSTANCE);
	}

	public void init(Path modelUri) {
		setModelUri(modelUri);
		packageRegistering();
		getOclManager().inizialize(getResourceSet());
		setUmlResource(
				(UMLResource) getResourceSet().getResource(manager.string2FileUri(getModelUri().toString()), true));
		model = (Model) EcoreUtil.getObjectByType(getUmlResource().getContents(), UMLPackage.Literals.MODEL);
	}

	@SuppressWarnings("null")
	public EList<Artifact> getAllArtifacts() {
		EList<Artifact> list_of_artifacts = new BasicEList<Artifact>();
		String query = ((UMLOclStringManager) getOclStringManager()).getAllArtifactsQuery();
		HashSet<Object> hashSetQuery = (HashSet<Object>) getOclManager().evaluateOCL(query, getModel());
				
		for (Object object : hashSetQuery) {
			if (object instanceof Artifact)
				list_of_artifacts.add((Artifact) object);
		}
		return list_of_artifacts;
	}

	@SuppressWarnings("null")
	public EList<Manifestation> getAllManifestations() {
		EList<Manifestation> list_of_manifestations = new BasicEList<Manifestation>();
		String query = ((UMLOclStringManager) getOclStringManager()).getAllManifestationsQuery();
		HashSet<Object> hashSetQuery = (HashSet<Object>) getOclManager().evaluateOCL(query, getModel());
		for (Object object : hashSetQuery) {
			if (object instanceof Manifestation)
				list_of_manifestations.add((Manifestation) object);
		}
		return list_of_manifestations;
	}

	public List<Manifestation> getAllManifestationsOf(Component component) {
		List<Manifestation> list_of_manifestations = new ArrayList<Manifestation>();
		String query = ((UMLOclStringManager) getOclStringManager()).getAllManifestationsOfQuery(component);
		HashSet<Object> hashSetQuery = (HashSet<Object>) getOclManager().evaluateOCL(query, getModel());
		for (Object object : hashSetQuery) {
			if (object instanceof Manifestation)
				list_of_manifestations.add((Manifestation) object);
		}
		return list_of_manifestations;
	}

	public List<Node> getAllNodes() {
		List<Node> list_of_nodes = new ArrayList<Node>();
		String query = ((UMLOclStringManager) getOclStringManager()).getAllNodesQuery();
		HashSet<Object> hashSetQuery = (HashSet<Object>) getOclManager().evaluateOCL(query, getModel());
		for (Object object : hashSetQuery) {
			if (object instanceof Node)
				list_of_nodes.add((Node) object);
		}
		return list_of_nodes;
	}

	public List<Component> getRandomComponents() {
		int upperBound = (int) Double.parseDouble(((HashSet<Object>) getOclManager()
				.evaluateOCL(((UMLOclStringManager) getOclStringManager()).countComponentsQuery(), getModel())).iterator().next()
						.toString());
		int[] bounds = generateRandomInterval(upperBound);
		List<Component> list_of_components = new ArrayList<Component>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager().evaluateOCL(
				((UMLOclStringManager) getOclStringManager()).generateRandomComponentsQuery(bounds[0], bounds[1]), getModel());
		for (Object object : hashSet) {
			if (object instanceof Component)
				list_of_components.add((Component) object);
		}
		return list_of_components;
	}

	public List<Component> getAllComponents() {
		List<Component> list_of_components = new ArrayList<Component>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager()
				.evaluateOCL(((UMLOclStringManager) getOclStringManager()).getAllComponentsQuery(), getModel());
		for (Object object : hashSet) {
			if (object instanceof Component)
				list_of_components.add((Component) object);
		}
		return list_of_components;
	}



	private Package findPackage(String packageName) {
		for (Object pkg : EcoreUtil.getObjectsByType(model.getOwnedElements(), UMLPackage.Literals.PACKAGE)) {
			if (((Package) pkg).getName().equals(packageName))
				return (Package) pkg;
		}
		return null;
	}

	public List<Integer> getRandomElement(List<Integer> list, int totalItems) {
		JMetalRandom rand = JMetalRandom.getInstance();

		// create a temporary list for storing
		// selected element
		List<Integer> newList = new ArrayList<>();
		for (int i = 0; i < totalItems; i++) {

			// take a raundom index between 0 to size
			// of given List
			int randomIndex = rand.nextInt(0, list.size()-1);

			// add element in temporary list
			newList.add(list.get(randomIndex));
		}
		return newList;
	}

	public List<Node> getRandomNodes() {
		Package pkg = findPackage(DEPLOYMENT_VIEW);
		Collection<Object> listOfNodes = EcoreUtil.getObjectsByType(pkg.getOwnedElements(), UMLPackage.Literals.DEVICE);
		List<Node> finalListOfNodes = new ArrayList<>();
		for (Object obj : listOfNodes) {
			Node ele = (Node) obj;
			for (Stereotype st : ele.getAppliedStereotypes())
				if (ele.isStereotypeApplied(st)) {
					finalListOfNodes.add(ele);
				}
		}

		if (finalListOfNodes.isEmpty()) {
			System.out.println("Please check your MODEL! It seems not ready to be used");
			return null;
		}

//		int[] bounds = generateRandomInterval(finalListOfNodes.size());
		
		
		
		List<Node> list_of_nodes = new ArrayList<Node>();

		for (int i=0; i<JMetalRandom.getInstance().nextInt(0, finalListOfNodes.size());i++) {
			list_of_nodes.add(finalListOfNodes.get(i));
		}
		return list_of_nodes;
	}

	public Package getComponentPackage() {
//		Package pack = getAllComponents().get(0).getNearestPackage();
		return findPackage(STATIC_VIEW);
//		return pack;
	}

	private int[] generateRandomInterval(int upperBound) {
		int bounds[] = new int[2];
		JMetalRandom.getInstance().setSeed(10L);
		bounds[0] = JMetalRandom.getInstance().getRandomGenerator().nextInt(-1, upperBound - 1);
		bounds[1] = JMetalRandom.getInstance().getRandomGenerator().nextInt(bounds[0], upperBound);
		if(bounds[0] > bounds[1]) {
			int aux = bounds[1];
			bounds[1] = bounds[0];
			bounds[0] = aux;
		}
		return bounds;
	}

	public Component getRandomComponent() {
		int upperBound = (int) Double.parseDouble(((HashSet<Object>) getOclManager()
				.evaluateOCL(((UMLOclStringManager) getOclStringManager()).countComponentsQuery(), getModel())).iterator().next()
						.toString());
		int[] bounds = generateRandomInterval(upperBound);
		List<Component> list_of_components = new ArrayList<Component>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager().evaluateOCL(
				((UMLOclStringManager) getOclStringManager()).generateRandomComponentsQuery(bounds[0], bounds[1]), getModel());
		for (Object object : hashSet) {
			if (object instanceof Component)
				list_of_components.add((Component) object);
		}
		int[] randomInterval = generateRandomInterval(list_of_components.size());
		return list_of_components.get(randomInterval[0] - 1);
	}

	public Operation getRandomOperation() {
		int upperBound = (int) Double.parseDouble(((HashSet<Object>) getOclManager()
				.evaluateOCL(((UMLOclStringManager) getOclStringManager()).countOperationsQuery(), getModel())).iterator().next()
						.toString());
		int[] bounds = generateRandomInterval(upperBound);
		List<Operation> list_of_operations = new ArrayList<Operation>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager().evaluateOCL(
				((UMLOclStringManager) getOclStringManager()).generateRandomOperationsQuery(bounds[0], bounds[1]), getModel());
		for (Object object : hashSet) {
			if (object instanceof Operation)
				list_of_operations.add((Operation) object);
		}
		return list_of_operations.get(generateRandomInterval(list_of_operations.size())[0] - 1);
	}

	public Node getRandomNode() {
		int upperBound = (int) Double.parseDouble(((HashSet<Object>) getOclManager()
				.evaluateOCL(((UMLOclStringManager) getOclStringManager()).countNodesQuery(), getModel())).iterator().next()
						.toString());
		int[] bounds = generateRandomInterval(upperBound);
		List<Node> list_of_nodes = new ArrayList<Node>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager().evaluateOCL(
				((UMLOclStringManager) getOclStringManager()).generateRandomNodesQuery(bounds[0], bounds[1]), getModel());
		for (Object object : hashSet) {
			if (object instanceof Node)
				list_of_nodes.add((Node) object);
		}
		return list_of_nodes.get(generateRandomInterval(list_of_nodes.size())[0] - 1);
	}

	public List<Component> getDeploymentsOf(List<Node> targets) {
		List<Component> list_of_components = new ArrayList<Component>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager()
				.evaluateOCL(((UMLOclStringManager) getOclStringManager()).getAllDeployedElementsQuery(targets), getModel());
		for (Object object : hashSet) {
			if (object instanceof Component)
				list_of_components.add((Component) object);
		}
		return null;
	}

	public Package getNodePackage() {
		Package pack = getAllNodes().get(0).getNearestPackage();
		return pack;
	}

	public UMLResource getUmlResource() {
		return umlResource;
	}

	public void setUmlResource(UMLResource umlResource) {
		this.umlResource = umlResource;
	}

	@Override
	public String getModelFileExtension() {
		return "." + UMLResource.FILE_EXTENSION;
	}

	@Override
	public String getMetamodelFileExtension() {
		return "." + UMLResource.FILE_EXTENSION;
	}

	@Override
	public OclManager getOclManager() {
		if (oclManager == null) {
			oclManager = new UMLOclManager(this);
		}
		return oclManager;
	}

	@Override
	public void setModel(EObject model) {
		this.model = (Model) model;
	}

	

	@Override
	public Model getModel(Path sourcePath) {
		 this.packageRegistering();

		URI uri = Manager.string2Uri(sourcePath.toString());

		// Load the requested resource
		Resource resource = getResourceSet().getResource(uri, true);
		// Get the first (should be only) package from it

		org.eclipse.uml2.uml.Package package_ = (org.eclipse.uml2.uml.Package) EcoreUtil
				.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);

		UMLResource res = (UMLResource) getResourceSet().getResource(Manager.string2Uri(sourcePath.toString()), true);

		res.getContents().get(0);

		this.model = createModel(sourcePath);

		return this.model;
	}

	public Model loadModel(Path pathToModel) {

		Resource resource = resourceSet.getResource(URI.createURI(pathToModel.toString()), true);
		Model m = (Model) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.MODEL);

		return model = m;
	}

	private Model createModel(Path sourcePath) {

		UMLResource umlResource = (UMLResource) getResourceSet()
				.getResource(URI.createFileURI(sourcePath.toString()), true);
		return (Model) EcoreUtil.getObjectByType(umlResource.getContents(), UMLPackage.Literals.MODEL);
	}

	@Override
	public void createNewResourceSet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshModel(Path sourceModelPath) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean saveModel() {
		try {
			model.eResource().save(null);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public OclStringManager getOclStringManager() {
		return this.oclStringManager;
				
	}

	@Override
	public boolean isApplicable(RefactoringAction act, RSequence sequence) {
		// TODO Auto-generated method stub
		return false;
	}

}