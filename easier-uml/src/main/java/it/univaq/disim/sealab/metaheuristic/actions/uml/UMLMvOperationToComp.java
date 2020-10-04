package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.exception.NotPositiveException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.Message;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclStringManager;
import logicalSpecification.Action;
import logicalSpecification.AndOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.NotOperator;
import logicalSpecification.Parameter;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.actions.UML.impl.UMLMoveOperationActionImpl;


public class UMLMvOperationToComp extends UMLMoveOperationActionImpl implements RefactoringAction {

	private final static Path eolModulePath;

	private final static double VALUE_COST = 1.23;

	private final UMLRSolution solution;

//	private Operation targetObject;

	static {
		eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-refactoringLibrary", "easier-ref-operations", "mv_op_comp.eol");
	}

	public static Path getEolModulePath() {
		return eolModulePath;
	}

	public UMLMvOperationToComp(RSolution sol) {
		this.solution = (UMLRSolution) sol;

		umlOpToMove = getRandomOperation();
		umlTargetComp = getRandomComponent();

		cost = calculateCost();

		setParameters();
		createPreCondition();
		createPostCondition();

	}

	private double calculateCost() {
		long msgs = this.solution.getDirtyIModel().allContents().stream().filter(Message.class::isInstance)
				.map(Message.class::cast).filter(m -> umlOpToMove.equals(m.getSignature())).count();
		return msgs * VALUE_COST;
	}

	// retrieves a random operation from the source model
	private Operation getRandomOperation() {
		org.eclipse.uml2.uml.Package dynamicView = getPackage("dynamic_view");

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

	/**
	 * Returns the specific package identified by @param
	 * 
	 * @param pkgName is the name of the package
	 * @return
	 */
	private org.eclipse.uml2.uml.Package getPackage(final String pkgName) {

		org.eclipse.uml2.uml.Model rootPackage = null;
		for (Object pkg : EcoreUtil.getObjectsByType(solution.getDirtyIModel().allContents(), UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Model) {
				rootPackage = (org.eclipse.uml2.uml.Model) pkg;
				break;
			}
		}

		org.eclipse.uml2.uml.Package returnPackage = null;
		for (Object pkg : EcoreUtil.getObjectsByType(rootPackage.getOwnedElements(), UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Package
					&& pkgName.equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
				returnPackage = (org.eclipse.uml2.uml.Package) pkg;
				break;
			}
		}
		return returnPackage;
	}

	private Component getRandomComponent() {

		Package staticView = getPackage("static_view");

		List<Object> cmps = new ArrayList<>(
				EcoreUtil.getObjectsByType(staticView.getOwnedElements(), UMLPackage.Literals.COMPONENT));
		Component cmp = null;
		do {
			cmp = (Component) cmps.get(JMetalRandom.getInstance().nextInt(0, cmps.size() - 1));
			System.out.println(cmp.toString());
		} while (cmp.getOperations().contains(umlOpToMove));

		return cmp;
	}

	@Override
	public void execute() {

		EOLStandalone executor = new EOLStandalone();
		executor.setModel(solution.getIModel());
		executor.setSource(eolModulePath);
		executor.setParameter(umlOpToMove.getName(), "String", "targetOperationName").setParameter(umlTargetComp.getName(), "String",
				"targetComponentName");

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

		FOLSpecification specification = solution.getManager()
				.createFOLSpectification("MvOperationToComponentPreCondition");

		ExistsOperator existsOpInOperations = solution.getManager().createExistsInCollectionOperator(getOpToMoveSVP(),
				getAllOpsMVP());

		ExistsOperator existsTargetInComponents = solution.getManager()
				.createExistsInCollectionOperator(getTargetCompSVP(), getAllCompsMVP());

		ExistsOperator existsOpInOpsOfTarget = solution.getManager().createExistsInCollectionOperator(getOpToMoveSVP(),
				getAllTargetCompOpsMVP());

		NotOperator notExistsOpInOpsOfTarget = solution.getManager().createNotOperator(existsOpInOpsOfTarget);

		AndOperator andRoot = solution.getManager().createAndOperator();
		andRoot.getArguments().add(existsOpInOperations);
		andRoot.getArguments().add(existsTargetInComponents);
		andRoot.getArguments().add(notExistsOpInOpsOfTarget);

		specification.setRootOperator(andRoot);
		preCondition.setConditionFormula(specification);
		setPre(preCondition);
	}

	@Override
	public void createPostCondition() {
		PostCondition postCondition = solution.getManager().createPostCondition();
		FOLSpecification specification = solution.getManager()
				.createFOLSpectification("MvOperationToComponentPostCondition");

//		ExistsOperator existsCompInComponents = solution.getManager().createExistsInCollectionOperator(getTargetCompSVP(),
//				getAllOpsMVP());
		ExistsOperator existsTargetInComponents = solution.getManager()
				.createExistsInCollectionOperator(getTargetCompSVP(), getAllCompsMVP());
		ExistsOperator existsOpInOpsOfTarget = solution.getManager().createExistsInCollectionOperator(getOpToMoveSVP(),
				getAllTargetCompOpsMVP());

		AndOperator andRoot = solution.getManager().createAndOperator();
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

		setOpToMoveSVP(solution.getManager().createSingleValueParameter(
				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getOperationQuery(getUmlOpToMove())));
		moveOpParams.add(getOpToMoveSVP());

		setTargetCompSVP(solution.getManager().createSingleValueParameter(
				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getComponentQuery(getUmlTargetComp())));
		moveOpParams.add(getTargetCompSVP());

		setAllOpsMVP(solution.getManager().createMultipleValuedParameter(
				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getAllOperationsQuery()));
		moveOpParams.add(getAllOpsMVP());

		setAllCompsMVP(solution.getManager().createMultipleValuedParameter(
				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getAllComponentsQuery()));
		moveOpParams.add(getAllCompsMVP());

		setAllTargetCompOpsMVP(solution.getManager().createMultipleValuedParameter(
				(((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getOperationsOfQuery(getUmlTargetComp()))));
		moveOpParams.add(getAllTargetCompOpsMVP());

		getParameters().addAll(moveOpParams);
	}

	@Override
	public RefactoringAction clone(RSolution solution) {
		UMLMvOperationToComp cloned = new UMLMvOperationToComp(solution);

		cloned.setNumOfChanges(this.getNumOfChanges());
		cloned.setCost(this.getCost());
		cloned.setName(this.getName());

		
		cloned.cleanUp();
		cloned.umlOpToMove = this.umlOpToMove;
		cloned.umlTargetComp = this.umlTargetComp;

		cloned.parameters = this.getParameters();
		cloned.pre = this.getPre();
		cloned.post = this.getPost();

		return cloned;
	}

	public boolean equals(Object op) {

		if (op.getClass() != this.getClass())
			return false;
		if (!this.getUmlOpToMove().equals(((UMLMvOperationToComp) op).getUmlOpToMove()))
			return false;
		return true;

	}

	//the action doesn't create new element in the model
	public void cleanUp() {}

	@Override
	public String toString() {
		return "Move Operation --> " + umlOpToMove.getName() + " to Component -->  " + umlTargetComp.getName();
	}

}
