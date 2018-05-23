package it.disim.univaq.sealab.metaheuristic.managers.uml;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.MARTE.MARTEFactory;
import org.eclipse.papyrus.MARTE.MARTEPackage;
import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.GQAM.GQAMFactory;
import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.GQAM.GQAMPackage;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Manifestation;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.profile.standard.StandardPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.disim.univaq.sealab.metaheuristic.actions.aemilia.RefactoringAction;
import it.disim.univaq.sealab.metaheuristic.evolutionary.Controller;
import it.disim.univaq.sealab.metaheuristic.evolutionary.RSequence;
import it.disim.univaq.sealab.metaheuristic.managers.Manager;
import it.disim.univaq.sealab.metaheuristic.managers.MetamodelManager;
import it.disim.univaq.sealab.metaheuristic.managers.ocl.OclManager;
import it.disim.univaq.sealab.metaheuristic.managers.ocl.uml.OclUMLManager;
import it.disim.univaq.sealab.metaheuristic.managers.ocl.uml.OclUMLStringManager;
import logicalSpecification.Action;
import logicalSpecification.actions.UML.UMLAddComponentAction;
import logicalSpecification.actions.UML.UMLAddNodeAction;
import logicalSpecification.actions.UML.UMLAddOperationAction;
import logicalSpecification.actions.UML.UMLDeleteComponentAction;
import logicalSpecification.actions.UML.UMLDeleteNodeAction;
import logicalSpecification.actions.UML.UMLDeleteOperationAction;
import logicalSpecification.actions.UML.UMLFactory;
import logicalSpecification.actions.UML.UMLMoveComponentAction;
import logicalSpecification.actions.UML.UMLMoveOperationAction;

public class UMLManager extends MetamodelManager {

	
	// private static final String REFACTORED_MODEL_PATH =
	// "/src/main/resources/models/refactored/BGCS/BGCS_";
	private Model model;

	// private ResourceSet set;
	// private Resource resource;
	private UMLResource umlResource;

	// private OclUMLManager oclUMLManager;
	// private OclUMLStringManager oclUMLStringManager;
	
	private static UMLManager instance;
	
	private Controller controller;
	private Manager manager;

//	private static class ManagerHolder {
//		private static final UMLManager INSTANCE = new UMLManager();
//	}
//
//	public static UMLManager getInstance() {
//		if(instance == null)
//			instance = ManagerHolder.INSTANCE;
//		REFACTORED_MODEL_BASE_PATH = "/src/main/resources/models/refactored/BGCS/BGCS_";
//		if (instance.getOclManager() == null) {
//			instance.setOclManager(OclUMLManager.getInstance());
//		}
//		if (instance.getOclStringManager() == null) {
//			instance.setOclStringManager(OclUMLStringManager.getInstance());
//		}
//		return instance;
//	}
	
	public UMLManager(Controller ctrl) {
		controller = ctrl;
		manager = controller.getManager();
	}

	// public Action getUMLRandomAction(int length) throws UnexpectedException {
	public Action getRandomAction(int length) throws UnexpectedException {

		int index = JMetalRandom.getInstance().getRandomGenerator().nextInt(0, length - 1);

		switch (index) {

		case 0:
			return getRandomAddNodeAction();
		case 1:
			return getRandomAddComponentAction();
		case 2:
			return getRandomAddOperationAction();
		case 3:
			return getRandomMoveOpertionAction();
		case 4:
			return getRandomMoveComponentAction();
		case 5:
			return getRandomDeleteOperationAction();
		case 6:
			return getRandomDeleteComponentAction();
		case 7:
			return getRandomDeleteNodeAction();
		default:
			throw new UnexpectedException("");
		}
	}

	private Action getRandomDeleteNodeAction() {
		Node node = this.getRandomNode();
		// Action action = new UMLDeleteNodeRefactoringAction(node);

		UMLDeleteNodeAction action = UMLFactory.eINSTANCE.createUMLDeleteNodeAction();
		action.setUmlNodeToDel(node);
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));

		return action;
	}

	private Action getRandomAddNodeAction() {
		List<Node> neighs = this.getRandomNodes();
		List<Component> deployedComps = this.getRandomComponents();
		UMLAddNodeAction action = (UMLAddNodeAction) UMLFactory.eINSTANCE.createUMLAddNodeAction();
		action.getUmlNeighbors().addAll(neighs);
		action.getUmlCompsToDeploy().addAll(deployedComps);
		action.setUmlSourcePackage(this.getNodePackage());
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();

		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));

		// Action action = new UMLAddNodeRefactoringAction(targets,
		// list_of_components);
		return action;
	}

	private Action getRandomAddOperationAction() {
		Component targetUMLComp = getRandomComponent();
		// Action action = new UMLAddOperationRefactoringAction(component);
		UMLAddOperationAction action = UMLFactory.eINSTANCE.createUMLAddOperationAction();
		action.setUmlTargetComp(targetUMLComp);
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));

		return action;
	}

	private Action getRandomDeleteComponentAction() {
		Component component = getRandomComponent();
		// Action action = new UMLDeleteComponentRefactoringAction(component);

		UMLDeleteComponentAction action = UMLFactory.eINSTANCE.createUMLDeleteComponentAction();
		action.setUmlCompToDel(component);
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));

		return action;
	}

	private Action getRandomDeleteOperationAction() {
		Operation operation = this.getRandomOperation();
		// Action action = new UMLDeleteOperationRefactoringAction(operation);

		UMLDeleteOperationAction action = UMLFactory.eINSTANCE.createUMLDeleteOperationAction();

		action.setUmlOpToDel(operation);
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();

		return action;
	}

	private Action getRandomMoveComponentAction() {
		Component component = getRandomComponent();
		List<Node> targets = getRandomNodes();
		// Action action = new UMLMoveComponentRefactoringAction(component,
		// list_of_nodes);

		UMLMoveComponentAction action = UMLFactory.eINSTANCE.createUMLMoveComponentAction();
		action.setUmlCompToMove(component);
		action.getUmlTargetNodes().addAll(targets);
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		return action;
	}

	private Action getRandomMoveOpertionAction() {
		Operation context = getRandomOperation();
		Component target = getRandomComponent();
		// Action action = new UMLMoveOperationRefactoringAction(operation,
		// target);
		UMLMoveOperationAction action = UMLFactory.eINSTANCE.createUMLMoveOperationAction();
		action.setUmlOpToMove(context);
		action.setUmlTargetComp(target);
		action.setParameters();
		action.createPreCondition();
		action.createPostCondition();
		action.setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		action.setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));

		return action;
	}

	private Action getRandomAddComponentAction() {
		EList<Node> targets = getRandomNodes();
		UMLAddComponentAction action = UMLFactory.eINSTANCE.createUMLAddComponentAction();
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

		Resource resource = getResourceSet().getResource(manager.string2FileUri(getModelUri()), true);
		return resource;
	}

	// public void unloadModelResource() {
	// if (getUmlResource() != null) { // unload previous resources if existing
	// // unload every resource in the resourceSet including profiles
	// for (Iterator<Resource> i = getResourceSet().getResources().iterator();
	// i.hasNext();) {
	// Resource current = (Resource) i.next();
	// current.unload();
	// i.remove();
	// }
	// }
	//
	// }

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
		setResourceSet(new ResourceSetImpl());
		getResourceSet().getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		getResourceSet().getPackageRegistry().put(StandardPackage.eNS_URI, StandardPackage.eINSTANCE);
		getResourceSet().getPackageRegistry().put(org.eclipse.papyrus.MARTE.MARTEPackage.eNS_URI,
				MARTEPackage.eINSTANCE);
		getResourceSet().getPackageRegistry()
				.put(org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.GQAM.GQAMPackage.eNS_URI, GQAMPackage.eINSTANCE);
		getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
				MARTEFactory.eINSTANCE);
		getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
				GQAMFactory.eINSTANCE);
		getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
				UMLResource.Factory.INSTANCE);
	}

	public void init(String modelUri) {
		setModelUri(modelUri);
		packageRegistering();
		getOclManager().inizialize(getResourceSet());
		setUmlResource((UMLResource) getResourceSet()
				.getResource(manager.string2FileUri(getModelUri()), true));
		model = (Model) EcoreUtil.getObjectByType(getUmlResource().getContents(), UMLPackage.Literals.MODEL);
	}

	@SuppressWarnings("null")
	public EList<Artifact> getAllArtifacts() {
		EList<Artifact> list_of_artifacts = new BasicEList<Artifact>();
		String query = ((OclUMLStringManager) getOclStringManager()).getAllArtifactsQuery();
		HashSet<Object> hashSetQuery = (HashSet<Object>) getOclManager().evaluateQuery(query);
		for (Object object : hashSetQuery) {
			if (object instanceof Artifact)
				list_of_artifacts.add((Artifact) object);
		}
		return list_of_artifacts;
	}

	@SuppressWarnings("null")
	public EList<Manifestation> getAllManifestations() {
		EList<Manifestation> list_of_manifestations = new BasicEList<Manifestation>();
		String query = ((OclUMLStringManager) getOclStringManager()).getAllManifestationsQuery();
		HashSet<Object> hashSetQuery = (HashSet<Object>) getOclManager().evaluateQuery(query);
		for (Object object : hashSetQuery) {
			if (object instanceof Manifestation)
				list_of_manifestations.add((Manifestation) object);
		}
		return list_of_manifestations;
	}

	public List<Manifestation> getAllManifestationsOf(Component component) {
		List<Manifestation> list_of_manifestations = new ArrayList<Manifestation>();
		String query = ((OclUMLStringManager) getOclStringManager()).getAllManifestationsOfQuery(component);
		HashSet<Object> hashSetQuery = (HashSet<Object>) getOclManager().evaluateQuery(query);
		for (Object object : hashSetQuery) {
			if (object instanceof Manifestation)
				list_of_manifestations.add((Manifestation) object);
		}
		return list_of_manifestations;
	}

	public List<Node> getAllNodes() {
		List<Node> list_of_nodes = new ArrayList<Node>();
		String query = ((OclUMLStringManager) getOclStringManager()).getAllNodesQuery();
		HashSet<Object> hashSetQuery = (HashSet<Object>) getOclManager().evaluateQuery(query);
		for (Object object : hashSetQuery) {
			if (object instanceof Node)
				list_of_nodes.add((Node) object);
		}
		return list_of_nodes;
	}

	public List<Component> getRandomComponents() {
		int upperBound = (int) Double.parseDouble(((HashSet<Object>) getOclManager()
				.evaluateQuery(((OclUMLStringManager) getOclStringManager()).countComponentsQuery())).iterator().next()
						.toString());
		int[] bounds = generateRandomInterval(upperBound);
		List<Component> list_of_components = new ArrayList<Component>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager().evaluateQuery(
				((OclUMLStringManager) getOclStringManager()).generateRandomComponentsQuery(bounds[0], bounds[1]));
		for (Object object : hashSet) {
			if (object instanceof Component)
				list_of_components.add((Component) object);
		}
		return list_of_components;
	}

	public List<Component> getAllComponents() {
		List<Component> list_of_components = new ArrayList<Component>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager()
				.evaluateQuery(((OclUMLStringManager) getOclStringManager()).getAllComponentsQuery());
		for (Object object : hashSet) {
			if (object instanceof Component)
				list_of_components.add((Component) object);
		}
		return list_of_components;
	}

	@SuppressWarnings("null")
	public EList<Node> getRandomNodes() {
		int upperBound = (int) Double.parseDouble(((HashSet<Object>) getOclManager()
				.evaluateQuery(((OclUMLStringManager) getOclStringManager()).countNodesQuery())).iterator().next()
						.toString());
		int[] bounds = generateRandomInterval(upperBound);
		// List<Node> list_of_nodes = new ArrayList<Node>();
		EList<Node> list_of_nodes = new BasicEList<Node>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager().evaluateQuery(
				((OclUMLStringManager) getOclStringManager()).generateRandomNodesQuery(bounds[0], bounds[1]));
		for (Object object : hashSet) {
			if (object instanceof Node)
				list_of_nodes.add((Node) object);
		}
		return list_of_nodes;
	}

	public Package getComponentPackage() {
		Package pack = getAllComponents().get(0).getNearestPackage();
		return pack;
	}

	private static int[] generateRandomInterval(int upperBound) {
		assert (upperBound > 0);
		int bounds[] = new int[2];
		bounds[0] = JMetalRandom.getInstance().getRandomGenerator().nextInt(1, upperBound);
		bounds[1] = JMetalRandom.getInstance().getRandomGenerator().nextInt(bounds[0], upperBound);
		assert (bounds[1] - bounds[0] < 0);
		return bounds;
	}

	public Component getRandomComponent() {
		int upperBound = (int) Double.parseDouble(((HashSet<Object>) getOclManager()
				.evaluateQuery(((OclUMLStringManager) getOclStringManager()).countComponentsQuery())).iterator().next()
						.toString());
		int[] bounds = generateRandomInterval(upperBound);
		List<Component> list_of_components = new ArrayList<Component>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager().evaluateQuery(
				((OclUMLStringManager) getOclStringManager()).generateRandomComponentsQuery(bounds[0], bounds[1]));
		for (Object object : hashSet) {
			if (object instanceof Component)
				list_of_components.add((Component) object);
		}
		assert (list_of_components.size() > 0);
		int[] randomInterval = generateRandomInterval(list_of_components.size());
		return list_of_components.get(randomInterval[0] - 1);
	}

	public Operation getRandomOperation() {
		int upperBound = (int) Double.parseDouble(((HashSet<Object>) getOclManager()
				.evaluateQuery(((OclUMLStringManager) getOclStringManager()).countOperationsQuery())).iterator().next()
						.toString());
		int[] bounds = generateRandomInterval(upperBound);
		List<Operation> list_of_operations = new ArrayList<Operation>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager().evaluateQuery(
				((OclUMLStringManager) getOclStringManager()).generateRandomOperationsQuery(bounds[0], bounds[1]));
		for (Object object : hashSet) {
			if (object instanceof Operation)
				list_of_operations.add((Operation) object);
		}
		return list_of_operations.get(generateRandomInterval(list_of_operations.size())[0] - 1);
	}

	public Node getRandomNode() {
		int upperBound = (int) Double.parseDouble(((HashSet<Object>) getOclManager()
				.evaluateQuery(((OclUMLStringManager) getOclStringManager()).countNodesQuery())).iterator().next()
						.toString());
		int[] bounds = generateRandomInterval(upperBound);
		List<Node> list_of_nodes = new ArrayList<Node>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager().evaluateQuery(
				((OclUMLStringManager) getOclStringManager()).generateRandomNodesQuery(bounds[0], bounds[1]));
		for (Object object : hashSet) {
			if (object instanceof Node)
				list_of_nodes.add((Node) object);
		}
		return list_of_nodes.get(generateRandomInterval(list_of_nodes.size())[0] - 1);
	}

	public List<Component> getDeploymentsOf(List<Node> targets) {
		List<Component> list_of_components = new ArrayList<Component>();
		HashSet<Object> hashSet = (HashSet<Object>) getOclManager()
				.evaluateQuery(((OclUMLStringManager) getOclStringManager()).getAllDeployedElementsQuery(targets));
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
		if(oclManager == null) {
			oclManager = new OclUMLManager(controller);
		}
		return oclManager;
	}

	@Override
	public void setModel(EObject model) {
		this.model = (Model) model;
	}

	@Override
	public RefactoringAction getRandomAction(int n, RSequence seq) throws UnexpectedException {
		// TODO Auto-generated method stub
		return null;
	}
}