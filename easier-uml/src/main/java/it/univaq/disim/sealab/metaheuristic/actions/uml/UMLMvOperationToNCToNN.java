package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.Message;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclStringManager;
import logicalSpecification.AndOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.NotOperator;
import logicalSpecification.Parameter;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.actions.UML.impl.UMLMoveOperationActionImpl;

public class UMLMvOperationToNCToNN extends UMLMoveOperationActionImpl implements RefactoringAction {

	private final static Path eolModulePath;

	private final static double VALUE_COST = 1.80;

	private final UMLRSolution solution;

	private Node umlTargetNode;

//	private Operation targetObject;

	static {
		eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-refactoringLibrary", "easier-ref-operations", "mv_op_nc_nn.eol");
	}

	public static Path getEolModulePath() {
		return eolModulePath;
	}

	public UMLMvOperationToNCToNN(RSolution sol) {
		this.solution = (UMLRSolution) sol;

		umlOpToMove = getRandomOperation();
		umlTargetComp = createNewComponent();
		umlTargetNode = createNewNode();

		cost = calculateCost();
		numOfChanges = cost;

		setParameters();
		createPreCondition();
		createPostCondition();

	}

	private double calculateCost() {
		long msgs = this.solution.getDirtyIModel().allContents().stream().filter(Message.class::isInstance)
				.map(Message.class::cast).filter(m -> umlOpToMove.equals(m.getSignature())).count();
		return msgs * VALUE_COST;
	}

	// creates a new Node within the model
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

		Node newNode = UMLFactory.eINSTANCE.createNode();
		deploymentView.getPackagedElements().add(newNode);

		newNode.setName("New-Node_" + generateHash());
		return newNode;
	}

	// creates a new Component within the model
	private Component createNewComponent() {

		org.eclipse.uml2.uml.Package staticView = null;
		for (Object pkg : EcoreUtil.getObjectsByType(solution.getDirtyIModel().allContents(),
				UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Package
					&& "static_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
				staticView = (org.eclipse.uml2.uml.Package) pkg;
				break;
			}
		}

		Component newComp = UMLFactory.eINSTANCE.createComponent();
		staticView.getPackagedElements().add(newComp);

		newComp.setName("New-Component_" + generateHash());
		return newComp;
	}

	private String generateHash() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
//	    Random random = new Random();

		return new Random().ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	// retrieves a random operation from the source model
	private Operation getRandomOperation() {
		org.eclipse.uml2.uml.Package dynamicView = null;
		for (Object pkg : EcoreUtil.getObjectsByType(solution.getDirtyIModel().allContents(),
				UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Package
					&& "dynamic_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
				dynamicView = (org.eclipse.uml2.uml.Package) pkg;
				break;
			}
		}

		List<Object> usecases = new ArrayList<>(
				EcoreUtil.getObjectsByType(dynamicView.getOwnedElements(), UMLPackage.Literals.USE_CASE));

		UseCase uc = (UseCase) usecases.get(JMetalRandom.getInstance().nextInt(0, usecases.size() - 1));

		Message msg = null;

		// returns a random message from a random interaction. Moreover, the message has
		// not to be a "reply" message
		do {
			msg = ((Interaction) uc.getOwnedBehaviors().get(0)).getMessages().get(JMetalRandom.getInstance().nextInt(0,
					((Interaction) uc.getOwnedBehaviors().get(0)).getMessages().size() - 1));
		} while (msg.getMessageSort().toString().equals("reply"));

		return (Operation) msg.getSignature();
	}

	@Override
	public void execute() {
//		super.execute();

		EOLStandalone executor = new EOLStandalone();
		executor.setModel(solution.getIModel());
		executor.setSource(eolModulePath);

		executor.setParameter(umlOpToMove.getName(), "String", "targetOperationName");
		executor.setParameter(umlTargetComp.getName(), "String", "newComponentName");
		executor.setParameter(umlTargetNode.getName(), "String", "newNodeName");

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
				.createFOLSpectification("MvOperationToNCToNNPreCondition");

		ExistsOperator existsOpInOperations = solution.getController().getManager()
				.createExistsInCollectionOperator(getOpToMoveSVP(), getAllOpsMVP());

		ExistsOperator existsTargetInComponents = solution.getController().getManager()
				.createExistsInCollectionOperator(getTargetCompSVP(), getAllCompsMVP());
		ExistsOperator existsOpInOpsOfTarget = solution.getController().getManager()
				.createExistsInCollectionOperator(getOpToMoveSVP(), getAllTargetCompOpsMVP());

		NotOperator notExistsOpInOpsOfTarget = solution.getController().getManager()
				.createNotOperator(existsOpInOpsOfTarget);

		AndOperator andRoot = solution.getController().getManager().createAndOperator();
		andRoot.getArguments().add(existsOpInOperations);
		andRoot.getArguments().add(existsTargetInComponents);
		andRoot.getArguments().add(notExistsOpInOpsOfTarget);

		specification.setRootOperator(andRoot);
		preCondition.setConditionFormula(specification);
		setPre(preCondition);
	}

	@Override
	public void createPostCondition() {
		PostCondition postCondition = solution.getController().getManager().createPostCondition();
		FOLSpecification specification = solution.getController().getManager()
				.createFOLSpectification("MvOperationToNCToNNPostCondition");

//		ExistsOperator existsCompInComponents = solution.getController().getManager().createExistsInCollectionOperator(getTargetCompSVP(),
//				getAllOpsMVP());
		ExistsOperator existsTargetInComponents = solution.getController().getManager()
				.createExistsInCollectionOperator(getTargetCompSVP(), getAllCompsMVP());
		ExistsOperator existsOpInOpsOfTarget = solution.getController().getManager()
				.createExistsInCollectionOperator(getOpToMoveSVP(), getAllTargetCompOpsMVP());

		AndOperator andRoot = solution.getController().getManager().createAndOperator();
//		andRoot.getArguments().add(existsOpInOperations);
		andRoot.getArguments().add(existsTargetInComponents);
		andRoot.getArguments().add(existsOpInOpsOfTarget);

		specification.setRootOperator(andRoot);
		postCondition.setConditionFormula(specification);
		setPost(postCondition);

	}

	@Override
	public void setParameters() {
		List<Parameter> moveOpParams = new ArrayList<>();

		setOpToMoveSVP(solution.getController().getManager()
				.createSingleValueParameter(UMLOclStringManager.getInstance().getOperationQuery(getUmlOpToMove())));
		moveOpParams.add(getOpToMoveSVP());

		setTargetCompSVP(solution.getController().getManager()
				.createSingleValueParameter(UMLOclStringManager.getInstance().getComponentQuery(getUmlTargetComp())));
		moveOpParams.add(getTargetCompSVP());

		setAllOpsMVP(solution.getController().getManager()
				.createMultipleValuedParameter(UMLOclStringManager.getInstance().getAllOperationsQuery()));
		moveOpParams.add(getAllOpsMVP());

		setAllCompsMVP(solution.getController().getManager()
				.createMultipleValuedParameter(UMLOclStringManager.getInstance().getAllComponentsQuery()));
		moveOpParams.add(getAllCompsMVP());

		setAllTargetCompOpsMVP(solution.getController().getManager().createMultipleValuedParameter(
				UMLOclStringManager.getInstance().getOperationsOfQuery(getUmlTargetComp())));
		moveOpParams.add(getAllTargetCompOpsMVP());

		getParameters().addAll(moveOpParams);
	}

	@Override
	public RefactoringAction clone(RSolution solution) {
		UMLMvOperationToNCToNN cloned = new UMLMvOperationToNCToNN(solution);

		cloned.setNumOfChanges(this.getNumOfChanges());
		cloned.setCost(this.getCost());
		cloned.setName(this.getName());

		cloned.cleanUp();
		cloned.umlOpToMove = this.umlOpToMove;
		cloned.umlTargetComp = this.umlTargetComp;
		cloned.umlTargetNode = this.umlTargetNode;

		cloned.parameters = this.getParameters();
		cloned.pre = this.getPre();
		cloned.post = this.getPost();

		return cloned;
	}

	public boolean equals(Object op) {
		if (op.getClass() != this.getClass())
			return false;

		final UMLMvOperationToNCToNN act = (UMLMvOperationToNCToNN) op;

		if (!this.getUmlOpToMove().getName().equals(act.getUmlOpToMove().getName())
				|| !this.umlTargetComp.getName().equals(act.getUmlTargetComp().getName())
				|| !this.umlTargetNode.getName().equals(act.getUmlTargetNode().getName()))
			return false;

		return true;
	}

	public Node getUmlTargetNode() {
		return umlTargetNode;
	}

	public void cleanUp() {
		try {
			this.solution.getDirtyIModel().deleteElement(umlTargetNode);
			this.solution.getDirtyIModel().deleteElement(umlTargetComp);
		} catch (EolRuntimeException e) {
			System.err.println("[ERROR] the cleanUp method has generated an error while removing Node --> "
					+ umlTargetNode.getName() + " and Component " + umlTargetComp.getName());
			e.printStackTrace();
		}
	}

	@Override
	public void freeMemory() {
		parameters.clear();
		pre = null;
		post = null;
	}

	public String toString() {
		return "Move Operation --> " + umlOpToMove.getName() + " to New Component --> " + umlTargetComp.getName()
				+ " deployed to a New Node -->" + umlTargetNode.getName();
	}

}
