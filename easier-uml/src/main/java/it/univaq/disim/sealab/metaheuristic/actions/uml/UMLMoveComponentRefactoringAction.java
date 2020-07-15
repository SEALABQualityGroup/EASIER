package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import org.eclipse.uml2.uml.*;
//import org.uma.jmetal.util.pseudorandom.JMetalRandom;

//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.managers.Manager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.OclUMLStringManager;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import logicalSpecification.AndOperator;
//import logicalSpecification.ExistsOperator;
//import logicalSpecification.FOLSpecification;
//import logicalSpecification.ForAllOperator;
//import logicalSpecification.NotOperator;
//import logicalSpecification.Parameter;
//import logicalSpecification.PostCondition;
//import logicalSpecification.PreCondition;
import logicalSpecification.actions.UML.impl.UMLMoveComponentActionImpl;

public class UMLMoveComponentRefactoringAction extends UMLMoveComponentActionImpl {

	// target component.
	private Component umlCompToMove;
	// target component name.
	private String umlCompToMoveName;

	// target nodes.
	private List<Node> umlTargetNodes;
	// origin nodes.
	private List<Node> umlOriginNodes;

	// manager
	private UMLManager umlManager;

	private List<Artifact> umlArtifacts = new ArrayList<Artifact>();

//	private SingleValuedParameter compToMoveSVP;
//	private MultipleValuedParameter targetNodesMVP;
//	private MultipleValuedParameter artifactsMVP;
//	private MultipleValuedParameter allCompsMVP;
//	private MultipleValuedParameter allTargetsMVP;
//	private MultipleValuedParameter allNodesMVP;
//	private MultipleValuedParameter allDeployedElemsMVP;
//	private static Double MAX_VALUE = 100.0;

	public UMLMoveComponentRefactoringAction(Component component, List<Node> targets, List<Node> origin, UMLManager umlManager) {

		// set target component.
		this.umlCompToMove = component;
		// set name.
		this.umlCompToMoveName = component.getName();

		// set target nodes.
		this.umlTargetNodes = targets;

		// set origin nodes.
		this.umlOriginNodes = origin;

		// set uml manager.
		this.umlManager = umlManager;
		/*
		setParameters();
		createPreCondition();
		createPostCondition();
		 */

		this.numOfChanges = 1;
	}


	@Override
	public void execute() {
		// execute the action.

		System.out.println("We want to move the component " + this.umlCompToMoveName + " from nodes:");
		for(Node node: this.umlOriginNodes){
			System.out.println(" - " + node.getName());
		}
		System.out.println("to nodes: ");
		for(Node node: this.umlTargetNodes){
			System.out.println(" - " + node.getName());
		}

		// retrieve all manifestations.
		List<Manifestation> list_of_manifestations = this.umlManager.getAllManifestationsOf(this.umlCompToMove);
		// delete all manifestations.
		System.out.println("Here the list of all the Manifestations to move: ");
		for (Manifestation man : list_of_manifestations) {
			System.out.println(" - " + man.getName());
			if (man.getUtilizedElement().getNamespace() == this.umlCompToMove.getNamespace()) {
				// destroy manifestation.
				man.destroy();
			}
		}

		System.out.println("Here the list of all the Artifacts to move: ");
		// take all artifact.
		List<Artifact> artifactList = this.umlManager.getAllArtifacts();
		for (Artifact artifact: artifactList){
			// if the artifact has the same name.
			if(artifact.getName().equals(this.umlCompToMove.getName() + "_Artifact")){
				System.out.println(" - " + artifact.getName());
				// delete the artifact.
				artifact.destroy();
			}
		}

		// destroy the _Deployments.

		System.out.println("Here the list of all the Deployments to move: ");
		// take all nodes.
		List<Node> nodeList = this.umlManager.getAllNodes();
		for(Node node: nodeList){
			// take all deployments of a node.
			List<Deployment> deploymentList = node.getDeployments();
			// we need to store all the indexes of the deployment occurrences.
			int index = 0;
			List<Integer> indexs = new ArrayList<>();
			for(Deployment deployment: deploymentList){
				// if it is not null and the name is equal to the component name.
				if(deployment.getName() != null && deployment.getName().equals(this.umlCompToMoveName + "_Deployment")){
					System.out.println(" - " + deployment.getName());
					// store the index.
					indexs.add(index);
				}
				else {
					// increase index.
					index++;
				}
			}

			// delete all deployments.
			for(Integer i: indexs){
				deploymentList.get(i).destroy();
			}
		}

		// deploy on target nodes.
		this.deployOn(this.umlTargetNodes);

	}


	private void deployOn(List<Node> targets) {
		// deploy a component to some target nodes.
		for (Node target : targets) {
			// create new artifact
			Artifact art = UMLFactory.eINSTANCE.createArtifact();
			// set name.
			art.setName(this.umlCompToMove.getName() + "_Artifact");
			// Manifestation is an abstraction relationship which represents concrete physical rendering of one or more
			// model elements by an artifact. An artifact manifests one or more model elements.
			art.createManifestation(this.umlCompToMove.getName() + "_Manifestation", this.umlCompToMove);
			art.setPackage(target.getPackage());
			Deployment deploy = target.createDeployment(this.umlCompToMove.getName() + "_Deployment");
			deploy.setName(this.umlCompToMove.getName() + "_Deployment");
			deploy.getDeployedArtifacts().add(art);
			target.getDeployments().add(deploy);
		}
	}



	public Component getUmlCompToMove() {
		// get umlCompToMove.
		return umlCompToMove;
	}

	public void setUmlCompToMove(Component component) {
		// set umlCompToMove.
		this.umlCompToMove = component;
	}

	public List<Node> getUmlTargetNodes() {
		// get umlTargetNodes.
		return umlTargetNodes;
	}

	public void setUmlTargetNodes(List<Node> targets) {
		// set umlTargetNodes.
		this.umlTargetNodes = targets;
	}


//	private void createPostCondition(PostCondition post) {
//		setPost(post);
//	}

//	private void createPreCondition(PreCondition pre) {
//		setPre(pre);
//	}

//	private void setParameters(EList<Parameter> parameters) {
//		getParameters().addAll(parameters);
//	}

//	public UMLMoveComponentRefactoringAction cloneAction(){
//		return new UMLMoveComponentRefactoringAction(this);
//	}


//	@Override
//	public void createPostCondition() {
//		PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();

//		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("MoveComponentPostCondition");

//		ExistsOperator existsComponentInComponents = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getCompToMoveSVP(), getAllCompsMVP());

//		ForAllOperator forAllNode = Manager.getInstance(UMLManager.getInstance()).createForAllOperator(getTargetNodesMVP());
//		ExistsOperator existsTargetInNodes = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getAllNodesMVP());
//		ExistsOperator componentExists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getCompToMoveSVP(), getAllDeployedElemsMVP());
//		AndOperator andOperator = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		andOperator.getArguments().add(existsTargetInNodes);
//		andOperator.getArguments().add(componentExists);
//		forAllNode.setArgument(andOperator);

//		AndOperator andRoot = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		andRoot.getArguments().add(existsComponentInComponents);
//		andRoot.getArguments().add(forAllNode);

//		specification.setRootOperator(andRoot);
//		postCondition.setConditionFormula(specification);
//		setPost(postCondition);
//	}

//	@Override
//	public void createPreCondition() {
//		PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();

//		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("MoveComponentPreCondition");

//		ExistsOperator existsComponentInComponents = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getCompToMoveSVP(), getAllCompsMVP());

//		ForAllOperator forAllNode = Manager.getInstance(UMLManager.getInstance()).createForAllOperator(getTargetNodesMVP());
//		ExistsOperator existsTargetInNodes = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getAllNodesMVP());
//		NotOperator componentNotOperator = Manager.getInstance(UMLManager.getInstance()).createNotOperator();

//		//TODO getAllDeployedElems è l'insieme delle Component deployate sui target Nodes. Non va bene: la condition dice che 
//				// su ogni target Node ci deve stare la Component spostata. Con l'unione basta che stia su uno dei target...
//		ExistsOperator componentExists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getCompToMoveSVP(), getAllDeployedElemsMVP());

//		componentNotOperator.setArgument(componentExists);
//		AndOperator andOperator = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		andOperator.getArguments().add(existsTargetInNodes);
//		andOperator.getArguments().add(componentNotOperator);
//		forAllNode.setArgument(andOperator);

//		AndOperator andRoot = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		andRoot.getArguments().add(existsComponentInComponents);
//		andRoot.getArguments().add(forAllNode);

//		specification.setRootOperator(andRoot);
//		preCondition.setConditionFormula(specification);
//		setPre(preCondition);
//	}

//	@Override
//	public void setParameters() {
//		// TODO Auto-generated method stub
//		List<Parameter> moveCompParams = new ArrayList<>();

//		setCompToMoveSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getComponentQuery(getUmlCompToMove())));
//		moveCompParams.add(getCompToMoveSVP());

//		setTargetNodesMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodesQuery(getUmlTargetNodes())));
//		moveCompParams.add(getTargetNodesMVP());

//		setAllArtifactsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllArtifactsQuery()));
//		moveCompParams.add(getAllArtifactsMVP());

//		setAllCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllComponentsQuery()));
//		moveCompParams.add(getAllCompsMVP());

//		setAllTargetsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodesQuery(getUmlTargetNodes())));
//		moveCompParams.add(getAllTargetsMVP());

//		setAllNodesMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));
//		moveCompParams.add(getAllNodesMVP());

//		setAllDeployedElemsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllDeployedElementsQuery(getUmlTargetNodes())));
//		moveCompParams.add(getAllDeployedElemsMVP());

//		getParameters().addAll(moveCompParams);
//	}

//	SingleValuedParameter getCompToMoveSVP() {
//		return compToMoveSVP;
//	}

//	void setCompToMoveSVP(SingleValuedParameter compToMoveSVP) {
//		this.compToMoveSVP = compToMoveSVP;
//	}

//	MultipleValuedParameter getTargetNodesMVP() {
//		return targetNodesMVP;
//	}

//	void setTargetNodesMVP(MultipleValuedParameter targetNodesMVP) {
//		this.targetNodesMVP = targetNodesMVP;
//	}

//	MultipleValuedParameter getArtifactsMVP() {
//		return artifactsMVP;
//	}

//	void setArtifactsMVP(MultipleValuedParameter artifactsMVP) {
//		this.artifactsMVP = artifactsMVP;
//	}

//	MultipleValuedParameter getAllCompsMVP() {
//		return allCompsMVP;
//	}

//	void setAllCompsMVP(MultipleValuedParameter allCompsMVP) {
//		this.allCompsMVP = allCompsMVP;
//	}

//	MultipleValuedParameter getAllTargetsMVP() {
//		return allTargetsMVP;
//	}

//	void setAllTargetsMVP(MultipleValuedParameter allTargetsMVP) {
//		this.allTargetsMVP = allTargetsMVP;
//	}

//	MultipleValuedParameter getAllNodesMVP() {
//		return allNodesMVP;
//	}

//	void setAllNodesMVP(MultipleValuedParameter allNodesMVP) {
//		this.allNodesMVP = allNodesMVP;
//	}

//	MultipleValuedParameter getAllDeployedElemsMVP() {
//		return allDeployedElemsMVP;
//	}

//	void setAllDeployedElemsMVP(MultipleValuedParameter allDeployedElemsMVP) {
//		this.allDeployedElemsMVP = allDeployedElemsMVP;
//	}

}
