package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.managers.Manager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;
import logicalSpecification.AndOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.NotOperator;
import logicalSpecification.Parameter;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.actions.UML.impl.UMLDeleteNodeActionImpl;

public class UMLDeleteNodeRefactoringAction extends UMLDeleteNodeActionImpl implements RefactoringAction {

	// node to delete
//	private Node umlNodeToDel;
	private String umlNodeToDelName;
	private Package sourcePackage;

	private RSolution solution;

	// private SingleValuedParameter nodeToDelSVP;
	// private MultipleValuedParameter allNodesMVP;
	// private static Double MAX_VALUE = 100.0;

	public UMLDeleteNodeRefactoringAction(RSolution sol) {
		this();
		this.solution = sol;

		setUmlNodeToDel();
		// retrieve a package from the model.
		this.sourcePackage = getPackage();
		// set package
		this.umlNodeToDel.setPackage(this.sourcePackage);
		this.numOfChanges = 1;
		setParameters();
		createPreCondition();
		createPostCondition();
	}

	public UMLDeleteNodeRefactoringAction() {
	}

	private Package getPackage() {

		Model model = (Model) this.getModel();
		for (Object o : EcoreUtil.getObjectsByType(model.getOwnedElements(), UMLPackage.Literals.PACKAGE)) {
			if (((Package) o).getName().equals(UMLMetamodelManager.DEPLOYMENT_VIEW))
				return (Package) o;
		}
		return null;
	}

	public void setUmlNodeToDel() {
		Package pkg = getPackage();

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
			new Throwable("Please check your MODEL! It seems not ready to be used");
		}

		this.umlNodeToDel = finalListOfNodes.get(JMetalRandom.getInstance().nextInt(0, finalListOfNodes.size() - 1));
	}

	@Override
	public void execute() {

		// Move artifact randomly
		Collection<Object> listOfNodes = EcoreUtil.getObjectsByType(getPackage().allOwnedElements(),
				UMLPackage.Literals.NODE);
		listOfNodes.remove(this.umlNodeToDel);
		listOfNodes.removeAll(this.umlNodeToDel.getDeployments());

		int depSize = this.umlNodeToDel.getDeployments().size();
		for (int i = 0; i < depSize; i++) {
			((Node) listOfNodes.toArray()[JMetalRandom.getInstance().nextInt(0, listOfNodes.size() - 1)])
					.getDeployments().add(this.umlNodeToDel.getDeployments().get(i));
		}

		// Move nestedNodes randomly
		depSize = this.umlNodeToDel.getNestedNodes().size();
		for (int i = 0; i < depSize; i++) {
			((Node) listOfNodes.toArray()[JMetalRandom.getInstance().nextInt(0, listOfNodes.size() - 1)])
					.getNestedNodes().add(this.umlNodeToDel.getNestedNodes().get(i));
		}

		// Remove Communication Paths
		depSize = this.umlNodeToDel.getCommunicationPaths().size();
		for (int i = 0; i < depSize; i++) {
			this.umlNodeToDel.getCommunicationPaths().get(i).destroy();
		}

		// delete node.
		this.umlNodeToDel.destroy();
	}

	public void setUmlNodeToDel(Node n) {
		this.umlNodeToDel = n;
	}

	public Model getModel() {
		return (model == null) ? (Model) this.solution.getModel() : (Model) model;
	}

	public void setModel(Model m) {
		this.model = m;
	}

	public Node getUmlNodeToDel() {
		// return umlNodeToDel.
		return this.umlNodeToDel;
	}

	public String getUmlNodeToDelName() {
		// return umlNodeToDelName.
		return this.umlNodeToDelName;
	}

//	private UMLDeleteNodeRefactoringAction(UMLDeleteNodeRefactoringAction actionToCopy) {
//		setUmlNodeToDel(actionToCopy.getUmlNodeToDel());

//		setCost(actionToCopy.getCost());
//		setNumOfChanges(actionToCopy.getNumOfChanges());
//		setParameters(actionToCopy.getParameters());
//		createPreCondition(actionToCopy.getPre());
//		createPostCondition(actionToCopy.getPost());
//	}

//	private void createPostCondition(PostCondition post) {
//		setPost(post);
//	}

//	private void createPreCondition(PreCondition pre) {
//		setPre(pre);
//	}

//	private void setParameters(EList<Parameter> parameters) {
//		getParameters().addAll(parameters);
//	}

//	public UMLDeleteNodeRefactoringAction cloneAction(){
//		return new UMLDeleteNodeRefactoringAction(this);
//	}

	@Override
	public void createPostCondition() {
		Manager manager = this.solution.getManager();

		PostCondition postCondition = manager.createPostCondition();
		FOLSpecification specification = manager.createFOLSpectification("DeleteNodePostCondition");

		AndOperator preAnd = manager.createAndOperator();

		NotOperator notOperator = manager.createNotOperator();
		ExistsOperator existsOperator = manager.createExistsInCollectionOperator(getNodeToDelSVP(), getAllNodesMVP());

		notOperator.setArgument(existsOperator);
		preAnd.getArguments().add(notOperator);

		specification.setRootOperator(preAnd);
		postCondition.setConditionFormula(specification);
		setPost(postCondition);
	}

	@Override
	public void createPreCondition() {

		Manager manager = this.solution.getManager();

		PreCondition preCondition = manager.createPreCondition();

		FOLSpecification specification = manager.createFOLSpectification("DeleteNodePreCondition");

		AndOperator preAnd = manager.createAndOperator();

		ExistsOperator existsOperator = manager.createExistsInCollectionOperator(getNodeToDelSVP(), getAllNodesMVP());

		preAnd.getArguments().add(existsOperator);

		specification.setRootOperator(preAnd);
		preCondition.setConditionFormula(specification);
		setPre(preCondition);
	}

	@Override
	public void setParameters() {

		Manager manager = this.solution.getManager();
		UMLOclStringManager strManager = (UMLOclStringManager) this.solution.getManager().getOclStringManager();

		setNodeToDelSVP(manager.createSingleValueParameter(strManager.getNodeQuery(getUmlNodeToDel())));
		setAllNodesMVP(manager.createMultipleValuedParameter(strManager.getAllNodesQuery()));

		getParameters().add(getNodeToDelSVP());
		getParameters().add(getAllNodesMVP());
	}

	@Override
	public RefactoringAction clone(RSolution solution) {
		// TODO Auto-generated method stub
		System.err.println("TODO");
		return null;
	}

	@Override
	public void setSolution(RSolution sol) {
		this.solution = sol;
	}

//	public void setUmlNodeToDel(Node node) {
//		this.umlNodeToDel = node;
//	}

//	SingleValuedParameter getNodeToDelSVP() {
//		return nodeToDelSVP;
//	}

//	void setNodeToDelSVP(SingleValuedParameter nodeToDelSVP) {
//		this.nodeToDelSVP = nodeToDelSVP;
//	}

//	MultipleValuedParameter getAllNodesMVP() {
//		return allNodesMVP;
//	}

//	void setAllNodesMVP(MultipleValuedParameter allNodesMVP) {
//		this.allNodesMVP = allNodesMVP;
//	}

}
