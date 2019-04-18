package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.evolutionary.AEmiliaController;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.OclUMLStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import logicalSpecification.AndOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.ForAllOperator;
import logicalSpecification.LogicalSpecificationFactory;
import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.NotOperator;
import logicalSpecification.Parameter;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.SingleValuedParameter;
import logicalSpecification.actions.UML.impl.UMLAddComponentActionImpl;

public class UMLAddComponentRefactoringAction extends UMLAddComponentActionImpl {

	private List<Node> umlTargetNodes;
	private Package umlSourcePackage;
	private Component umlCompToAdd;

	private SingleValuedParameter compToAddSVP;
	private MultipleValuedParameter targetNodesMVP;
	private MultipleValuedParameter deployedCompsMVP;
	private MultipleValuedParameter allCompsMVP;
	private MultipleValuedParameter allNodesMVP;

	private static Double MAX_VALUE = 100.0;

	// PAKIMOR _FIXME
	public UMLAddComponentRefactoringAction(EList<Node> targets, UMLManager umlManager) {
		
		//It is a safety guard
		if(targets == null)
			targets = umlManager.getRandomNodes();
		
		//set the target nodes on which the new component will be deployed
		getUmlTargetNodes().addAll(targets);
		
		//retrieve the package named "Static View" from the model
		setUmlSourcePackage(umlManager.getComponentPackage());
		
		//Ignore these, they are just number for now.
		setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
		
		/*
		setParameters();
		createPreCondition();
		createPostCondition();
		*/
	}
	
	//Used to clone the Action
	// TODO check if it works
	private UMLAddComponentRefactoringAction(UMLAddComponentRefactoringAction umlAddComponentRefactoringAction, UMLManager umlManager) {
		setUmlTargetNodes(umlAddComponentRefactoringAction.getUmlTargetNodes());
		setUmlSourcePackage(umlManager.getComponentPackage());
		setCost(umlAddComponentRefactoringAction.getCost());
		setNumOfChanges(umlAddComponentRefactoringAction.getNumOfChanges());
		setParameters(umlAddComponentRefactoringAction.getParameters());
		createPreCondition(umlAddComponentRefactoringAction.getPre());
		createPostCondition(umlAddComponentRefactoringAction.getPost());
	}

	private void createPostCondition(PostCondition post) {
		setPost(post);
	}

	private void createPreCondition(PreCondition pre) {
		setPre(pre);
	}

	private void setParameters(EList<Parameter> parameters) {
		getParameters().addAll(parameters);
	}

//	public UMLAddComponentRefactoringAction cloneAction() {
//		return new UMLAddComponentRefactoringAction(this);
//	}

	@Override
	public void execute() {
		setUmlCompToAdd(UMLFactory.eINSTANCE.createComponent());
		getUmlCompToAdd().setName("newComp" + Math.random());
		getUmlCompToAdd().setPackage(getUmlSourcePackage());
		setParameters();
		deployOn(getUmlTargetNodes());
	}

	private void deployOn(List<Node> targets) {
		Artifact art;
		for (Node target : targets) {
			art = UMLFactory.eINSTANCE.createArtifact();
			art.setName(getUmlCompToAdd().getName() + "_Artifact");
			art.createManifestation(getUmlCompToAdd().getName() + "_Manifestation", getUmlCompToAdd());
			Deployment deploy = target.createDeployment(getUmlCompToAdd().getName() + "_Deployment");
			deploy.getDeployedArtifacts().add(art);
			target.getDeployments().add(deploy);
		}
	}

	@Override
	public void log() {
		AEmiliaController.logger_.info("UMLAddComponentRefactoringAction");
		if (getUmlCompToAdd() != null)
			AEmiliaController.logger_.info(getUmlCompToAdd().toString());
	}

	public EList<Node> getUmlTargetNodes() {
		return (EList<Node>) umlTargetNodes;
	}

	public void setUmlTargetNodes(EList<Node> umlTargetNodes) {
		this.umlTargetNodes = umlTargetNodes;
	}

	public Package getUmlSourcePackage() {
		return umlSourcePackage;
	}

	public void setUmlSourcePackage(Package sourcePackage) {
		this.umlSourcePackage = sourcePackage;
	}

	public void setNumberOfComponents(int number_of_components) {
	}

	@Override
	public void setParameters() {
////		 ACTION add PARAMETERS
//		List<Parameter> addParams = new ArrayList<>();
////		 PAKIMOR _FIXME le add non dovrebbero avere come attributo l'oggetto da creare
//
//		 setCompToAddSVP(manager.createSingleValueParameter("Component.allInstances()->select(c | c.name = 'Pippo Node')"));
//		 addParams.add(getCompToAddSVP());
//
//		if (umlCompToAdd != null) {
//			setCompToAddSVP(manager.createSingleValueParameter(
//					((OclUMLStringManager) OclStringManger.getInstance(new OclUMLStringManager())).getComponentQuery(umlCompToAdd)));
//			addParams.add(getCompToAddSVP());
//		}
//
//		setTargetNodesMVP(manager.getInstance(null)
//				.createMultipleValuedParameter(((OclUMLStringManager) OclStringManger.getInstance(new OclUMLStringManager())).getNodesQuery(umlTargetNodes)));
//		addParams.add(getTargetNodesMVP());
//
//		 List<Component> list_of_random_components = new ArrayList<Component>();
//		 list_of_random_components = Oclmanager.getRandomComponents();
//		 setDeployedCompsMVP(manager.createMultipleValuedParameter(OclStringManger.getComponentsQuery(list_of_random_components)));
//		 addParams.add(getDeployedCompsMVP());
//
//		setDeployedCompsMVP(manager
//				.createMultipleValuedParameter(((OclUMLStringManager) OclStringManger.getInstance(new OclUMLStringManager())).getAllDeployedElementsQuery(getUmlTargetNodes())));
//		addParams.add(getDeployedCompsMVP());
//
//		setAllNodesMVP(manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManger.getInstance(new OclUMLStringManager())).getAllNodesQuery()));
//		addParams.add(getAllNodesMVP());
//
//		setAllCompsMVP(manager.createMultipleValuedParameter(((OclUMLStringManager) OclStringManger.getInstance(new OclUMLStringManager())).getAllComponentsQuery()));
//		addParams.add(getAllCompsMVP());
//
//		getParameters().addAll(addParams);
	}

	@Override
	public void createPreCondition() {
//		// creo la preCondition
//		PreCondition preCondition = LogicalSpecificationFactory.eINSTANCE.createPreCondition();
//
//		FOLSpecification addPreSpecification = manager
//				.createFOLSpectification("AddComponentPreCondition");
//
//		AndOperator addPreAnd = manager.createAndOperator();
//
//		NotOperator addPreAndNot = manager.createNotOperator();
//		ExistsOperator addPreAndNotExists = manager.createExistsOperator(getCompToAddSVP(), getAllCompsMVP());
//
//		addPreAndNot.setArgument(addPreAndNotExists);
//		addPreAnd.getArguments().add(addPreAndNot);
//
//		ForAllOperator addPreAndForall = manager
//				.createForAllOperator(getTargetNodesMVP());
//		ExistsOperator addPreAndForallExists = manager
//				.createExistsOperator(getAllNodesMVP());
//		addPreAndForall.setArgument(addPreAndForallExists);
//		addPreAnd.getArguments().add(addPreAndForall);
//
//		addPreSpecification.setRootOperator(addPreAnd);
//		preCondition.setConditionFormula(addPreSpecification);
//		setPre(preCondition);
	}

	@Override
	public void createPostCondition() {
//		PostCondition addPost = LogicalSpecificationFactory.eINSTANCE.createPostCondition();
//
//		FOLSpecification addPostSpec = manager.createFOLSpectification("AddNodePostcondition");
//
//		AndOperator addPostAnd = manager.createAndOperator();
//
//		ExistsOperator addPostAndExistsInC = manager.createExistsOperator(getCompToAddSVP(), getAllCompsMVP());
//
//		addPostAnd.getArguments().add(addPostAndExistsInC);
//
//		ForAllOperator addPostAndForallTargets = manager.createForAllOperator(getTargetNodesMVP());
//		AndOperator addPostAndForallTargetsAnd = manager.createAndOperator();
//		ExistsOperator addPostAndForallTargetsExists = manager.createExistsOperator(getAllNodesMVP());
//		addPostAndForallTargetsAnd.getArguments().add(addPostAndForallTargetsExists);
//
//		/* TODO getDeployedComps è l'insieme delle Component deployate sui target Nodes.
//		 Non va bene: la condition dice che
//		 su ogni target Node ci deve stare la Component aggiunta. Con l'unione basta
//		 che stia su uno dei target...*/
//		ExistsOperator addPostAndExistsInD = manager.createExistsOperator(getCompToAddSVP(), getDeployedCompsMVP());
//
//		addPostAndForallTargetsAnd.getArguments().add(addPostAndExistsInD);
//		addPostAndForallTargets.setArgument(addPostAndForallTargetsAnd);
//		addPostAnd.getArguments().add(addPostAndForallTargets);
//
//		addPostSpec.setRootOperator(addPostAnd);
//		addPost.setConditionFormula(addPostSpec);
//		setPost(addPost);

	}

	public void setPreCondition() {
//		// creo la preCondition
//		PreCondition preCondition = LogicalSpecificationFactory.eINSTANCE.createPreCondition();
//
//		FOLSpecification addPreSpecification = manager.createFOLSpectification("AddComponentPreCondition");
//
//		AndOperator addPreAnd = manager.createAndOperator();
//
//		// creo la condizione NOT della preCondition
//		NotOperator addPreAndNot = manager.createNotOperator();
//		ExistsOperator addPreAndNotExists = manager.createExistsOperator(getCompToAddSVP(),
//				manager.createMultipleValuedParameter(OclStringManger.getAllComponentsQuery()));
//
//		addPreAndNot.setArgument(addPreAndNotExists);
//		addPreAnd.getArguments().add(addPreAndNot);
//
//		// creo la forALL sui nodi
//		ForAllOperator addPreAndForall = manager.createForAllOperator(getTargetNodesMVP());
//		ExistsOperator addPreAndForallExists = Manager
//				.createExistsOperator(manager.createMultipleValuedParameter(OclStringManger.getAllNodesQuery()));
//		addPreAndForall.setArgument(addPreAndForallExists);
//		addPreAnd.getArguments().add(addPreAndForall);
//
//		addPreSpecification.setRootOperator(addPreAnd);
//		preCondition.setConditionFormula(addPreSpecification);
//		// setto la preCond della Action
//		setPre(preCondition);
	}

	public void setPostCondition() {
//		PostCondition addPost = LogicalSpecificationFactory.eINSTANCE.createPostCondition();
//
//		FOLSpecification addPostSpec = manager.createFOLSpectification("AddNodePostcondition");
//
//		AndOperator addPostAnd = manager.createAndOperator();
//
//		ExistsOperator addPostAndExistsInC = manager.createExistsOperator(getCompToAddSVP(),
//				manager.createMultipleValuedParameter(OclStringManger.getAllComponentsQuery()));
//
//		addPostAnd.getArguments().add(addPostAndExistsInC);
//
//		ForAllOperator addPostAndForallTargets = manager.createForAllOperator(getTargetNodesMVP());
//		AndOperator addPostAndForallTargetsAnd = manager.createAndOperator();
//		ExistsOperator addPostAndForallTargetsExists = Manager
//				.createExistsOperator(manager.createMultipleValuedParameter(OclStringmanager.getAllNodesQuery()));
//		addPostAndForallTargetsAnd.getArguments().add(addPostAndForallTargetsExists);
//		ExistsOperator addPostAndExistsInD = manager.createExistsOperator(getCompToAddSVP(), Manager
//				.createMultipleValuedParameter(OclStringManger.getAllDeployedElementsQuery(getUmlTargetNodes())));
//		addPostAndForallTargetsAnd.getArguments().add(addPostAndExistsInD);
//
//		addPostAndForallTargets.setArgument(addPostAndForallTargetsAnd);
//		addPostAnd.getArguments().add(addPostAndForallTargets);
//
//		addPostSpec.setRootOperator(addPostAnd);
//		addPost.setConditionFormula(addPostSpec);
//		setPost(addPost);

	}

	public Component getUmlCompToAdd() {
		return umlCompToAdd;
	}

	public void setUmlCompToAdd(Component umlCompToAdd) {
		this.umlCompToAdd = umlCompToAdd;
	}

	SingleValuedParameter getCompToAddSVP() {
		return componentToAddSVP;
	}

	void setCompToAddSVP(SingleValuedParameter compToAddSVP) {
		this.componentToAddSVP = compToAddSVP;
	}

	public MultipleValuedParameter getTargetNodesMVP() {
		return targetNodesMVP;
	}

	public void setTargetNodesMVP(MultipleValuedParameter targetNodesMVP) {
		this.targetNodesMVP = targetNodesMVP;
	}

	public MultipleValuedParameter getAllCompsMVP() {
		return allCompsMVP;
	}

	public void setAllCompsMVP(MultipleValuedParameter allCompsMVP) {
		this.allCompsMVP = allCompsMVP;
	}

	public MultipleValuedParameter getAllNodesMVP() {
		return allNodesMVP;
	}

	public void setAllNodesMVP(MultipleValuedParameter allNodesMVP) {
		this.allNodesMVP = allNodesMVP;
	}

	public MultipleValuedParameter getDeployedCompsMVP() {
		return deployedCompsMVP;
	}

	public void setDeployedCompsMVP(MultipleValuedParameter deployedCompsMVP) {
		this.deployedCompsMVP = deployedCompsMVP;
	}

}
