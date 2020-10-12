/**
 * It moves a component from a node to another one
 */
package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.DeployedArtifact;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.UMLFactory;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclStringManager;
import logicalSpecification.AndOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.Parameter;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.SingleValuedParameter;
import logicalSpecification.actions.UML.UMLPackage;
import logicalSpecification.actions.UML.impl.UMLAddNodeActionImpl;

public class UMLCloneNode extends UMLAddNodeActionImpl implements RefactoringAction {

	private final static Path eolModulePath;

	private final static double VALUE_COST = 1.23;

	private UMLRSolution solution;

	private final Path sourceModelPath;

	private Node targetObject, umlClonedNode;

	private SingleValuedParameter umlNodeToCloneSVP, umlClonedNodeSVP;
//	private MultipleValuedParameter targetNodesMVP;

	static {
		eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-refactoringLibrary", "easier-ref-operations", "clone_node.eol");
	}

	public static Path getEolModulePath() {
		return eolModulePath;
	}

	public UMLCloneNode(RSolution sol) {
		this.solution = (UMLRSolution) sol;

		sourceModelPath = sol.getModelPath();

		targetObject = getRandomNode();

		umlClonedNode = createNewNode();

		cost = calculateCost();
		numOfChanges = cost;

		setParameters();
		createPreCondition();
		createPostCondition();

	}

	private double calculateCost() {

		int cpSize = targetObject.getCommunicationPaths().size();

		int artSize = 0;

		targetObject.getDeployments().stream().flatMap(d -> d.getDeployedArtifacts().stream()).count();

		return (cpSize + artSize) * VALUE_COST;

	}

	private String generateHash() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
//	    Random random = new Random();

		return new Random().ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	private Node createNewNode() {

		org.eclipse.uml2.uml.Package deploymentView = null;
		// Retrieves the deployment view package
		for (Object pkg : EcoreUtil.getObjectsByType(solution.getDirtyIModel().allContents(),
				UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Package
					&& "deployment_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
				deploymentView = (org.eclipse.uml2.uml.Package) pkg;
				break;
			}
		}

		Node node = UMLFactory.eINSTANCE.createNode();
		node.setName(targetObject.getName() + "_" + generateHash());

		deploymentView.getPackagedElements().add(node);

		return node;

	}

	// Returns a random Node to be cloned
	private Node getRandomNode() {

		org.eclipse.uml2.uml.Package deploymentView = null;
		org.eclipse.uml2.uml.Model rootPackage = null;

		for (Object pkg : EcoreUtil.getObjectsByType(solution.getDirtyIModel().allContents(),
				UMLPackage.Literals.PACKAGE)) {
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
		return (Node) nodes.get(JMetalRandom.getInstance().nextInt(0, nodes.size() - 1));
	}

	@Override
	public void execute() {
		EOLStandalone executor = new EOLStandalone();

//		final IModel contextModel = solution.getIModel(); // M0

		EasierUmlModel contextModel;
		try {
			contextModel = EpsilonStandalone.createUmlModel("UML", sourceModelPath, null, true, true);

			executor.setModel(contextModel);
			executor.setSource(eolModulePath);

			// fills variable within the eol module

			executor.setParameter(targetObject.getName(), "String", "targetNodeName");
			executor.setParameter(umlClonedNode.getName(), "String", "clonedNodeName");

//		executor.setParameter(umlClonedNode, "UML!Node", "clonedNode"); // vedere come passare la stringa
//		executor.setParameter(targetObject, "UML!Node", "targetNode");

			executor.execute();
		} catch (Exception e) {
			System.err.println("Error in execution the eolmodule " + eolModulePath);
			e.printStackTrace();
		}

		executor.clearMemory();
		executor = null;
	}

	@Override
	public void createPreCondition() {
		PreCondition preCondition = solution.getController().getManager().createPreCondition();

		FOLSpecification specification = solution.getController().getManager()
				.createFOLSpectification("CloneNodePreCondition");

		ExistsOperator existsTargetInNodes = solution.getController().getManager()
				.createExistsInCollectionOperator(umlNodeToCloneSVP, getAllNodesMVP());

		AndOperator andRoot = solution.getController().getManager().createAndOperator();
		andRoot.getArguments().add(existsTargetInNodes);

		specification.setRootOperator(andRoot);
		preCondition.setConditionFormula(specification);
		setPre(preCondition);
	}

	@Override
	public void createPostCondition() {
		PostCondition postCondition = solution.getController().getManager().createPostCondition();
		FOLSpecification specification = solution.getController().getManager()
				.createFOLSpectification("CloneNodePostCondition");

		ExistsOperator existsTargetInNodes = solution.getController().getManager()
				.createExistsInCollectionOperator(umlNodeToCloneSVP, getAllNodesMVP());

		ExistsOperator existsClonedInNodes = solution.getController().getManager()
				.createExistsInCollectionOperator(umlClonedNodeSVP, getAllNodesMVP());

		AndOperator andRoot = solution.getController().getManager().createAndOperator();
		andRoot.getArguments().add(existsTargetInNodes);
		andRoot.getArguments().add(existsClonedInNodes);

		specification.setRootOperator(andRoot);
		postCondition.setConditionFormula(specification);
		setPost(postCondition);

	}

	@Override
	public void setParameters() {
		List<Parameter> params = new ArrayList<>();

		// checks wheter the random node exists within the model
		umlNodeToCloneSVP = solution.getController().getManager()
				.createSingleValueParameter(UMLOclStringManager.getInstance().getNodeQuery(targetObject));

		umlClonedNodeSVP = solution.getController().getManager()
				.createSingleValueParameter(UMLOclStringManager.getInstance().getNodeQuery(umlClonedNode));

		params.add(umlNodeToCloneSVP);
		params.add(umlClonedNodeSVP);

		setAllNodesMVP(solution.getController().getManager()
				.createMultipleValuedParameter(UMLOclStringManager.getInstance().getAllNodesQuery()));

		params.add(getAllNodesMVP());
		getParameters().addAll(params);
	}

	@Override
	public RefactoringAction clone(RSolution solution) {

		UMLCloneNode cloned = new UMLCloneNode(solution);

		cloned.setNumOfChanges(this.getNumOfChanges());
		cloned.setCost(this.getCost());
		cloned.setName(this.getName());

		cloned.cleanUp();
		cloned.setTargetObject(this.targetObject);
		cloned.setUmlClonedNode(this.umlClonedNode);

		cloned.parameters = this.getParameters();
		cloned.pre = this.getPre();
		cloned.post = this.getPost();

		return cloned;

	}

	public boolean equals(Object op) {
		if (op.getClass() != this.getClass())
			return false;

		final UMLCloneNode act = (UMLCloneNode) op;
//		if (!this.targetObject.equals(act.getTargetObject()))
//			return false;

		if (!this.targetObject.getName().equals(act.getTargetObject().getName())
				|| this.umlClonedNode.getName().equals(act.getClonedNode().getName()))
			return false;
//		
//		if(!this.targetObject.getName().contains("addByEASIER_addByEASIER")) //it is a safety check. 
//			return false;
		return true;
	}

	public Node getTargetObject() {
		return this.targetObject;
	}

	public Node getClonedNode() {
		return this.umlClonedNode;
	}

	public void setUmlClonedNode(Node n) {
		this.umlClonedNode = n;
	}

	public void setTargetObject(Node n) {
		this.targetObject = n;
	}

	public void cleanUp() {
		try {
			this.solution.getDirtyIModel().deleteElement(umlClonedNode);
		} catch (EolRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void freeMemory() {
		CacheAdapter.getInstance().clear(targetObject.eResource());
		CacheAdapter.getInstance().clear(umlClonedNode.eResource());
		targetObject = null;
		umlClonedNode = null;
		parameters.clear();
		pre = null;
		post = null;
		this.solution = null;
	}

	@Override
	public String toString() {
		return "Cloning --> " + targetObject.getName() + " with -->  " + umlClonedNode.getName();
	}

}
