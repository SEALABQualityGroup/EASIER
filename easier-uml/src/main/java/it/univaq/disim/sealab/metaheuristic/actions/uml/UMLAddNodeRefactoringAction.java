package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.util.ArrayList;
import java.util.List;

//import org.eclipse.uml2.uml.AggregationKind;
//import org.eclipse.uml2.uml.Artifact;
//import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.Package;

//import org.uma.jmetal.util.pseudorandom.JMetalRandom;

//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.managers.Manager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.OclUMLStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import logicalSpecification.AndOperator;
//import logicalSpecification.ExistsOperator;
//import logicalSpecification.FOLSpecification;
//import logicalSpecification.ForAllOperator;
//import logicalSpecification.Parameter;
//import logicalSpecification.PostCondition;
//import logicalSpecification.PreCondition;
import logicalSpecification.actions.UML.impl.UMLAddNodeActionImpl;

public class UMLAddNodeRefactoringAction extends UMLAddNodeActionImpl {

	private Node umlNodeToAdd;
	private Package sourcePackage;
	private String nodeName; // name of the node to add.

	// private PreCondition preCondition;
	// private PostCondition postCondition;
	// private SingleValuedParameter nodeToAddSVP;
	// private MultipleValuedParameter neighborsMVP;
	// private MultipleValuedParameter compsToDeployMVP;
	// private MultipleValuedParameter allNodesMVP;
	// private MultipleValuedParameter allCompsMVP;
	// private MultipleValuedParameter allDeployedElemsMVP;
	//	private static Double MAX_VALUE = 100.0;


	public UMLAddNodeRefactoringAction(UMLManager umlManager, String nodeName) {

		//retrieve the nearest package from the model.
		this.sourcePackage = umlManager.getNodePackage();

		// set name.
		this.nodeName = nodeName;
		this.numOfChanges = 1;

		/*
		setParameters();
		createPreCondition();
		createPostCondition();
		*/
	}

	@Override
	public void execute() {
		// execute the action.


		// create new node and set name.
		this.umlNodeToAdd = UMLFactory.eINSTANCE.createNode();
		this.umlNodeToAdd.setName(this.nodeName);

		// set package.
		this.umlNodeToAdd.setPackage(this.sourcePackage);
		System.out.println("We want to add the node with name: " + this.umlNodeToAdd.getName()+ " in the package " + this.umlNodeToAdd.getPackage().getName() +  ".");

	}


	public Node getUmlNodeToAdd() {
		// return the uml node to add.
		return umlNodeToAdd;
	}
// private void setParameters(EList<Parameter> parameters) {
// 		getParameters().addAll(parameters);
// }

// public UMLAddNodeRefactoringAction cloneAction(){
// 		return new UMLAddNodeRefactoringAction(this);
// }


//	public void addDeployedComps() {
//		Artifact art;
//		for (Component comp : getUmlCompsToDeploy()) {
//			art = UMLFactory.eINSTANCE.createArtifact();
//			art.setName(comp.getName() + "_Artifact");
//			art.createManifestation(comp.getName() + "_Manifestation", comp);
//			Deployment deploy = umlNodeToAdd.createDeployment(comp.getName() + "_Deployment");
//			deploy.getDeployedArtifacts().add(art);
//			umlNodeToAdd.getDeployments().add(deploy);
//		}
//	}

//	public void addCommunicationPaths() {
//		for (Node node : umlNeighbors) {
//			/**
//			 * createCommunicationPath(boolean end1IsNavigable, AggregationKind
//			 * end1Aggregation, String end1Name, int end1Lower,int end1Upper, Node end1Node,
//			 * boolean end2IsNavigable,AggregationKind end2Aggregation, String end2Name, int
//			 * end2Lower,int end2Upper);
//			 */
//			umlNodeToAdd
//					.createCommunicationPath(true, AggregationKind.COMPOSITE_LITERAL, node.getName(), 1, 1, node, true,
//							AggregationKind.COMPOSITE_LITERAL, umlNodeToAdd.getName(), 1, 1)
//					.setName(umlNodeToAdd.getName() + "_cp_" + node.getName());
//		}
//	}

//	public EList<Node> getUmlNeighbors() {
//		return umlNeighbors;
//	}

//	public void setUmlNeighbors(List<Node> umlNeighbors) {
//		this.umlNeighbors = umlNeighbors;
//	}

//  public void setUmlNodeToAdd(Node umlNodeToAdd) {
//  	this.umlNodeToAdd = umlNodeToAdd;
//  }

//	public List<Component> getUmlCompsToDeploy() {
//		return umlCompsToDeploy;
//	}

//	public void setUmlCompsToDeploy(List<Component> umlCompsToDeploy) {
//		this.umlCompsToDeploy = umlCompsToDeploy;
//	}


//	@Override
//	public void setParameters() {
//		List<Parameter> addParams = new ArrayList<>();

//		if (umlNodeToAdd != null) {
//			// PAKIMOR _FIXME le add non dovrebbero avere come attributo l'oggetto da
//			// creare
//			setNodeToAddSVP(Manager.getInstance(UMLManager.getInstance())
//					.createSingleValueParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodeQuery(umlNodeToAdd)));
//			addParams.add(getNodeToAddSVP());
//		}

//		setNeighborsMVP(Manager.getInstance(UMLManager.getInstance())
//				.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodesQuery(this.umlNeighbors)));
//		addParams.add(getNeighborsMVP());

//		setCompsToDeployMVP(Manager.getInstance(UMLManager.getInstance())
//				.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getComponentsQuery(this.umlCompsToDeploy)));
//		addParams.add(getCompsToDeployMVP());

//		setAllNodesMVP(Manager.getInstance(UMLManager.getInstance())
//				.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));
//		addParams.add(getAllNodesMVP());

//		setAllCompsMVP(Manager.getInstance(UMLManager.getInstance())
//				.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllComponentsQuery()));
//		addParams.add(getAllCompsMVP());

//		if (getUmlNodeToAdd() != null) {
//			ArrayList<Node> nList = new ArrayList<Node>();
//			// PAKIMOR _FIXME qui facciamo la query dei componenti deploiati sul nodo
//			// appena creato ma e' vuota
//			nList.add(getUmlNodeToAdd());
//			setAllDeployedElemsMVP(Manager.getInstance(UMLManager.getInstance())
//					.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllDeployedElementsQuery(nList)));
//			addParams.add(getAllDeployedElemsMVP());
//		}

//		getParameters().addAll(addParams);
//	}

//	@Override
//	public void createPreCondition() {
//		PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();

//		FOLSpecification addPreSpec = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("AddNodePrecondition");

//		AndOperator addPreAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();

//		ExistsOperator addPreAndNotExists =
//		Manager.createExistsOperator(getNodeToAddSVP(), getAllNodesMVP());

//		NotOperator addPreAndNot =
//		Manager.createNotOperator(addPreAndNotExists);
//		addPreAnd.getArguments().add(addPreAndNot);

//		ForAllOperator addPreAndForall = Manager.getInstance(UMLManager.getInstance()).createForAllOperator(getNeighborsMVP());

//		ExistsOperator addPreAndForallExists = Manager.getInstance(UMLManager.getInstance())
//				.createExistsOperator(getAllNodesMVP());
//		addPreAndForall.setArgument(addPreAndForallExists);
//		addPreAnd.getArguments().add(addPreAndForall);
//		addPreSpec.setRootOperator(addPreAnd);

//		preCondition.setConditionFormula(addPreSpec);
//		setPre(preCondition);

//	}

//	@Override
//	public void createPostCondition() {
//		PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();

//		FOLSpecification addPostSpec = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("AddNodePostcondition");

//		AndOperator addPostAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();

//		ExistsOperator addPostAndExists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getNodeToAddSVP(),
//				getAllNodesMVP());
//		addPostAnd.getArguments().add(addPostAndExists);

//		ForAllOperator addPostAndForallNeighs = Manager.getInstance(UMLManager.getInstance())
//				.createForAllOperator(getNeighborsMVP());

//		AndOperator addPostAndForallNeighsAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();

//		ExistsOperator addPostAndForallNeighsAndExistsAllNodes = Manager.getInstance(UMLManager.getInstance())
//				.createExistsOperator(getAllNodesMVP());
//		addPostAndForallNeighsAnd.getArguments().add(addPostAndForallNeighsAndExistsAllNodes);

//		ExistsOperator addPostAndForallNeighsAndExistsNeighs = Manager.getInstance(UMLManager.getInstance())
//				.createExistsOperator(getNeighborsMVP());
//		addPostAndForallNeighsAnd.getArguments().add(addPostAndForallNeighsAndExistsNeighs);

//		addPostAndForallNeighs.setArgument(addPostAndForallNeighsAnd);

//		addPostAnd.getArguments().add(addPostAndForallNeighs);

//		ForAllOperator addPostAndForallDeplComps = Manager.getInstance(UMLManager.getInstance())
//				.createForAllOperator(getCompsToDeployMVP());

//		AndOperator addPostAndForallDeplCompsAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();

//		ExistsOperator addPostAndForallDeplCompsAndExistsAllNodes = Manager.getInstance(UMLManager.getInstance())
//				.createExistsOperator(getAllCompsMVP());
//		addPostAndForallDeplCompsAnd.getArguments().add(addPostAndForallDeplCompsAndExistsAllNodes);

//		ExistsOperator addPostAndForallDeplCompsAndExistsNeighs = Manager.getInstance(UMLManager.getInstance())
//				.createExistsOperator(getAllDeployedElemsMVP());
//		addPostAndForallDeplCompsAnd.getArguments().add(addPostAndForallDeplCompsAndExistsNeighs);

//		addPostAndForallDeplComps.setArgument(addPostAndForallDeplCompsAnd);

//		addPostAnd.getArguments().add(addPostAndForallDeplComps);

//		addPostSpec.setRootOperator(addPostAnd);
//		postCondition.setConditionFormula(addPostSpec);
//		setPost(postCondition);
//	}

//	@Override
//	public void setPreCondition() {
//	preCondition = Manager.createPreCondition();

//	FOLSpecification addPreSpec =
//	Manager.createFOLSpectification("AddNodePrecondition");

//	AndOperator addPreAnd = Manager.createAndOperator();

//	ExistsOperator addPreAndNotExists = Manager.createExistsOperator(
//	Manager.createSingleValueParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodeQuery(getNewNode())),
//	Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));

//	NotOperator addPreAndNot = Manager.createNotOperator(addPreAndNotExists);
//	addPreAnd.getArguments().add(addPreAndNot);

//	ForAllOperator addPreAndForall = Manager.createForAllOperator(
//	Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodesQuery(getNeighs())));

//	ExistsOperator addPreAndForallExists =
//	Manager.createExistsOperator(Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));
//	addPreAndForall.setArgument(addPreAndForallExists);
//	addPreAnd.getArguments().add(addPreAndForall);
//	addPreSpec.setRootOperator(addPreAnd);

//	preCondition.setConditionFormula(addPreSpec);
//	setPre(preCondition);
//
//	}

//	@Override
//	public void setPostCondition() {
//	postCondition = Manager.createPostCondition();

//	FOLSpecification addPostSpec =
//	Manager.createFOLSpectification("AddNodePostcondition");

//	AndOperator addPostAnd = Manager.createAndOperator();

//	ExistsOperator addPostAndExists = Manager.createExistsOperator(
//	Manager.createSingleValueParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodeQuery(getNewNode())),
//	Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));
//	addPostAnd.getArguments().add(addPostAndExists);

//	ForAllOperator addPostAndForallNeighs = Manager.createForAllOperator(
//	Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodesQuery(getNeighs())));

//	AndOperator addPostAndForallNeighsAnd = Manager.createAndOperator();

//	ExistsOperator addPostAndForallNeighsAndExistsAllNodes = Manager
//	.createExistsOperator(Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));
//	addPostAndForallNeighsAnd.getArguments().add(addPostAndForallNeighsAndExistsAllNodes);

//	ExistsOperator addPostAndForallNeighsAndExistsNeighs =
//	Manager.createExistsOperator(
//	Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodesQuery(getNeighs())));
//	addPostAndForallNeighsAnd.getArguments().add(addPostAndForallNeighsAndExistsNeighs);

//	addPostAndForallNeighs.setArgument(addPostAndForallNeighsAnd);

//	addPostAnd.getArguments().add(addPostAndForallNeighs);

//	ForAllOperator addPostAndForallDeplComps = Manager.createForAllOperator(
//	Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getComponentsQuery(getDeployedComps())));

//	AndOperator addPostAndForallDeplCompsAnd = Manager.createAndOperator();

//	ExistsOperator addPostAndForallDeplCompsAndExistsAllNodes = Manager
//	.createExistsOperator(Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllComponentsQuery()));
//	addPostAndForallDeplCompsAnd.getArguments().add(addPostAndForallDeplCompsAndExistsAllNodes);

//	si riferisce alle componenti deployate sul nuovo nodo (dopo averlo
//	aggiunto,
//	le deployedComps devono starci sopra)
//	ExistsOperator addPostAndForallDeplCompsAndExistsNeighs =
//	Manager.createExistsOperator(
//	Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getComponentsQuery(deployedComps)));

//	ArrayList<Node> nList = new ArrayList<Node>();
//	nList.add(getNewNode());
//	ExistsOperator addPostAndForallDeplCompsAndExistsNeighs =
//	Manager.createExistsOperator(
//	Manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllDeployedElementsQuery(nList)));
//	addPostAndForallDeplCompsAnd.getArguments().add(addPostAndForallDeplCompsAndExistsNeighs);

//	addPostAndForallDeplComps.setArgument(addPostAndForallDeplCompsAnd);

//	addPostAnd.getArguments().add(addPostAndForallDeplComps);

//	addPostSpec.setRootOperator(addPostAnd);
//	postCondition.setConditionFormula(addPostSpec);
//	setPost(postCondition);
//	}

//	public PostCondition getPostCondition() {
//		return postCondition;
//	}

//	public PreCondition getPreCondition() {
//		return preCondition;
//	}

//	SingleValuedParameter getNodeToAddSVP() {
//		return nodeToAddSVP;
//	}

//	void setNodeToAddSVP(SingleValuedParameter nodeToAddSVP) {
//		this.nodeToAddSVP = nodeToAddSVP;
//	}

//	MultipleValuedParameter getNeighborsMVP() {
//		return neighborsMVP;
//	}

//	void setNeighborsMVP(MultipleValuedParameter neighborsMVP) {
//		this.neighborsMVP = neighborsMVP;
//	}

//	MultipleValuedParameter getCompsToDeployMVP() {
//		return compsToDeployMVP;
//	}

//	void setCompsToDeployMVP(MultipleValuedParameter compsToDeployMVP) {
//		this.compsToDeployMVP = compsToDeployMVP;
//	}

//	MultipleValuedParameter getAllNodesMVP() {
//		return allNodesMVP;
//	}

//	void setAllNodesMVP(MultipleValuedParameter allNodesMVP) {
//		this.allNodesMVP = allNodesMVP;
//	}

//	MultipleValuedParameter getAllCompsMVP() {
//		return allCompsMVP;
//	}

//	void setAllCompsMVP(MultipleValuedParameter allCompsMVP) {
//		this.allCompsMVP = allCompsMVP;
//	}

//	MultipleValuedParameter getAllDeployedElemsMVP() {
//		return allDeployedElemsMVP;
//	}

//	void setAllDeployedElemsMVP(MultipleValuedParameter allDeployedElemsMVP) {
//		this.allDeployedElemsMVP = allDeployedElemsMVP;
//	}

}
