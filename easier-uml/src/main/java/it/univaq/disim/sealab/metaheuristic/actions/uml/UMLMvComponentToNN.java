/**
 * It moves a component from a node to another one
 */
package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.uml2.uml.Component;
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
import logicalSpecification.actions.UML.UMLPackage;
import logicalSpecification.actions.UML.impl.UMLMoveComponentActionImpl;

public class UMLMvComponentToNN extends UMLMoveComponentActionImpl implements RefactoringAction {

	private final static Path eolModulePath;

	private final static double VALUE_COST = 1.23;

	private final UMLRSolution solution;

	static {
		eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-refactoringLibrary", "easier-ref-operations", "mv_comp_nn.eol");
	}

	public static Path getEolModulePath() {
		return eolModulePath;
	}

	public UMLMvComponentToNN(RSolution sol) {
		this.solution = (UMLRSolution) sol;

		umlTargetNodes = new BasicEList<>();

		umlCompToMove = getRandomComponent();
		umlTargetNodes.add(createNewNode());

		cost = calculateCost();
		numOfChanges = cost;

		setParameters();
		createPreCondition();
		createPostCondition();

	}

	private double calculateCost() {

		int intUsage = umlCompToMove.getUsedInterfaces().size();
		int intReal = umlCompToMove.getInterfaceRealizations().size();
		int ops = umlCompToMove.getOperations().size();

		return (intUsage + intReal + ops) * VALUE_COST;
	}

	// Creates a new random Node on which the component will be deployed on
	private Node createNewNode() {

		org.eclipse.uml2.uml.Package deploymentView = null;
		for (Object pkg : EcoreUtil.getObjectsByType(solution.getDirtyIModel().allContents(),
				UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Package
					&& "deployment_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
				deploymentView = (org.eclipse.uml2.uml.Package) pkg;
				break;
			}
		}

		Node target = UMLFactory.eINSTANCE.createNode();
		target.setName("New-Node_" + generateHash());
		deploymentView.getPackagedElements().add(target);
		return target;
	}

	private String generateHash() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
//	    Random random = new Random();

		return new Random().ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	// retrieves a random Component from the source model
	private Component getRandomComponent() {

		org.eclipse.uml2.uml.Package staticView = null;
		for (Object pkg : EcoreUtil.getObjectsByType(solution.getDirtyIModel().allContents(),
				UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Package
					&& "static_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
				staticView = (org.eclipse.uml2.uml.Package) pkg;
				break;
			}
		}

		Component cmp;
		do {
			List<Component> comps = new ArrayList<>(
					EcoreUtil.getObjectsByType(staticView.getOwnedElements(), UMLPackage.Literals.COMPONENT));
			cmp = comps.get(JMetalRandom.getInstance().nextInt(0, comps.size() - 1));
		} while (cmp == null);
		return cmp;
	}

	@Override
	public void execute() {
		EOLStandalone executor = new EOLStandalone();

		final IModel contextModel = solution.getIModel();
		executor.setModel(contextModel);
		executor.setSource(eolModulePath);

		// fills variable within the eol module
		executor.setParameter(umlCompToMove.getName(), "String", "targetComponentName");
		executor.setParameter(umlTargetNodes.get(0).getName(), "String", "newNodeName");

//		executor.setParameter(umlCompToMove, "UML!Component", "targetComponent");
//		executor.setParameter(umlTargetNodes.get(0), "UML!Node", "targetNode");

		try {
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
				.createFOLSpectification("MvComponentToNNPreCondition");

		ExistsOperator existsTargetInComponents = solution.getController().getManager()
				.createExistsInCollectionOperator(getCompToMoveSVP(), getAllCompsMVP());

		AndOperator andRoot = solution.getController().getManager().createAndOperator();
		andRoot.getArguments().add(existsTargetInComponents);

		specification.setRootOperator(andRoot);
		preCondition.setConditionFormula(specification);
		setPre(preCondition);
	}

	@Override
	public void createPostCondition() {
		PostCondition postCondition = solution.getController().getManager().createPostCondition();
		FOLSpecification specification = solution.getController().getManager()
				.createFOLSpectification("MvComponentToNNPostCondition");

		ExistsOperator existsTargetInComponents = solution.getController().getManager()
				.createExistsInCollectionOperator(getCompToMoveSVP(), getAllCompsMVP());

		AndOperator andRoot = solution.getController().getManager().createAndOperator();
		andRoot.getArguments().add(existsTargetInComponents);

		// Verifies whether target nodes are created in the model
		ExistsOperator existsTargetNodes = solution.getController().getManager()
				.createExistsOperator(getTargetNodesMVP());
		andRoot.getArguments().add(existsTargetNodes);

		specification.setRootOperator(andRoot);
		postCondition.setConditionFormula(specification);
		setPost(postCondition);

	}

	@Override
	public void setParameters() {
		List<Parameter> params = new ArrayList<>();

		setCompToMoveSVP(solution.getController().getManager()
				.createSingleValueParameter(UMLOclStringManager.getInstance().getComponentQuery(umlCompToMove)));
		params.add(getCompToMoveSVP());

//		setAllOpsMVP(solution.getManager().createMultipleValuedParameter(
//				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
//						.getAllOperationsQuery()));
//		params.add(getAllOpsMVP());

		setAllCompsMVP(solution.getController().getManager()
				.createMultipleValuedParameter(UMLOclStringManager.getInstance().getAllComponentsQuery()));

		// Sets the target node
		setTargetNodesMVP(solution.getController().getManager()
				.createMultipleValuedParameter(UMLOclStringManager.getInstance().getNodesQuery(umlTargetNodes)));

		params.add(getAllCompsMVP());

		getParameters().addAll(params);
	}

	@Override
	public RefactoringAction clone(RSolution solution) {

		UMLMvComponentToNN cloned = new UMLMvComponentToNN(solution);

		cloned.setNumOfChanges(this.getNumOfChanges());
		cloned.setCost(this.getCost());
		cloned.setName(this.getName());

		cloned.cleanUp();
		cloned.setUmlCompToMove(this.umlCompToMove);
		cloned.setUmlTargetNodes(this.umlTargetNodes);

		cloned.parameters = this.getParameters();
		cloned.pre = this.getPre();
		cloned.post = this.getPost();

		return cloned;

	}

	public void setUmlCompToMove(Component c) {
		this.umlCompToMove = c;
	}

	public void setUmlTargetNodes(List<Node> n) {
		umlTargetNodes.clear();
		umlTargetNodes.addAll(n);
	}

	public boolean equals(Object op) {
		if (op.getClass() != this.getClass())
			return false;
		final UMLMvComponentToNN act = (UMLMvComponentToNN) op;
		if (!this.getUmlCompToMove().getName().equals(act.getUmlCompToMove().getName())
				|| !this.umlTargetNodes.get(0).getName().equals(act.getUmlTargetNodes().get(0).getName()))
			return false;
		return true;
	}

	public void cleanUp() {
		try {
			this.solution.getDirtyIModel().deleteElement(umlTargetNodes.get(0));
		} catch (EolRuntimeException e) {
			System.err.println("[ERROR] the cleanUp method has generated an error, while removing the node --> "
					+ umlTargetNodes.get(0).getName());
			e.printStackTrace();
		}
	}

	@Override
	public void freeMemory() {
		parameters.clear();
		pre = null;
		post = null;
	}

	@Override
	public String toString() {
		String nodes = "";
		for (Node n : umlTargetNodes)
			nodes += " " + n.getName();
		return "Moving --> " + umlCompToMove.getName() + " to --> " + nodes;
	}

}
