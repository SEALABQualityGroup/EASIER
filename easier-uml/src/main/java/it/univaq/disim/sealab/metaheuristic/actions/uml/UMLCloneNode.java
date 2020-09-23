/**
 * It moves a component from a node to another one
 */
package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.UMLFactory;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
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

	private final UMLRSolution solution;

	private Node targetObject, umlClonedNode;

	private SingleValuedParameter umlNodeToCloneSVP, umlClonedNodeSVP;
//	private MultipleValuedParameter targetNodesMVP;

	static {
		eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-epsilon", "src", "main", "resources", "refactoring-lib", "clone_node.eol");
	}

	public static Path getEolModulePath() {
		return eolModulePath;
	}

	public UMLCloneNode(RSolution sol) {
		this.solution = (UMLRSolution) sol;

		targetObject = getRandomNode();

		umlClonedNode = createNewNode();

		setParameters();
		createPreCondition();
		createPostCondition();

	}

	private Node createNewNode() {

		org.eclipse.uml2.uml.Package deploymentView = null;
		// Retrieves the deployment view package
		for (Object pkg : EcoreUtil.getObjectsByType(solution.getIModel().allContents(), UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Package
					&& "deployment_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
				deploymentView = (org.eclipse.uml2.uml.Package) pkg;
				break;
			}
		}

		Node node = UMLFactory.eINSTANCE.createNode();
		node.setName(targetObject.getName() + "_addByEASIER");

		deploymentView.getPackagedElements().add(node);

		return node;

	}

	// Returns a random Node to be cloned
	private Node getRandomNode() {

		org.eclipse.uml2.uml.Package deploymentView = null;
		org.eclipse.uml2.uml.Model rootPackage = null;

		for (Object pkg : EcoreUtil.getObjectsByType(solution.getIModel().allContents(), UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Model) {
				rootPackage = (org.eclipse.uml2.uml.Model) pkg;
				break;
			}
		}

		// Retrieves the deployment view package
		/*
		 * EcoreUtil .getObjectsByType(solution.getIModel().allContents(),
		 * UMLPackage.Literals.PACKAGE).stream()
		 * .map(org.eclipse.uml2.uml.Package.class::cast).filter(pkg ->
		 * "deployment_view".equals(pkg.getName())) .findAny().orElse(null);
		 */
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

		final IModel contextModel = solution.getIModel();
		executor.setModel(contextModel);
		executor.setSource(eolModulePath);

		// fills variable within the eol module
		executor.setParameter(umlClonedNode, "UML!Node", "self");
		executor.setParameter(targetObject, "UML!Node", "target");

		try {
			executor.execute();
		} catch (Exception e) {
			System.err.println("Error in execution the eolmodule " + eolModulePath);
			e.printStackTrace();
		}
	}

	@Override
	public void createPreCondition() {
		PreCondition preCondition = solution.getManager().createPreCondition();

		FOLSpecification specification = solution.getManager().createFOLSpectification("CloneNodePreCondition");

		ExistsOperator existsTargetInNodes = solution.getManager().createExistsInCollectionOperator(umlNodeToCloneSVP,
				getAllNodesMVP());

		AndOperator andRoot = solution.getManager().createAndOperator();
		andRoot.getArguments().add(existsTargetInNodes);

		specification.setRootOperator(andRoot);
		preCondition.setConditionFormula(specification);
		setPre(preCondition);
	}

	@Override
	public void createPostCondition() {
		PostCondition postCondition = solution.getManager().createPostCondition();
		FOLSpecification specification = solution.getManager().createFOLSpectification("CloneNodePostCondition");

		ExistsOperator existsTargetInNodes = solution.getManager().createExistsInCollectionOperator(umlNodeToCloneSVP,
				getAllNodesMVP());

		ExistsOperator existsClonedInNodes = solution.getManager().createExistsInCollectionOperator(umlClonedNodeSVP,
				getAllNodesMVP());

		AndOperator andRoot = solution.getManager().createAndOperator();
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
		umlNodeToCloneSVP = solution.getManager().createSingleValueParameter(
				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getNodeQuery(targetObject));

		umlClonedNodeSVP = solution.getManager().createSingleValueParameter(
				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getNodeQuery(umlClonedNode));

		params.add(umlNodeToCloneSVP);
		params.add(umlClonedNodeSVP);

		setAllNodesMVP(solution.getManager().createMultipleValuedParameter(
				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getAllNodesQuery()));

		params.add(getAllNodesMVP());
		getParameters().addAll(params);
	}

	@Override
	public RefactoringAction clone(RSolution solution) {

		UMLCloneNode cloned = new UMLCloneNode(solution);

		cloned.setNumOfChanges(this.getNumOfChanges());
		cloned.setCost(this.getCost());
		cloned.setName(this.getName());

		cloned.parameters = this.getParameters();
		cloned.pre = this.getPre();
		cloned.post = this.getPost();

		return cloned;

	}

	public boolean equals(Object op) {
		if (op.getClass() != this.getClass())
			return false;

		if (!this.targetObject.equals(((UMLCloneNode) op).getUmlNodeToClone()))
			return false;
//		
//		if(!this.targetObject.getName().contains("addByEASIER_addByEASIER")) //it is a safety check. 
//			return false;
		return true;
	}

	public Node getUmlNodeToClone() {
		return this.targetObject;
	}

}
