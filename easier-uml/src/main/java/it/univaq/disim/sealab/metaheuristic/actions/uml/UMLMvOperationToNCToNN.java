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
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
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
import logicalSpecification.Parameter;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import logicalSpecification.actions.UML.impl.UMLMoveOperationActionImpl;

public class UMLMvOperationToNCToNN extends UMLMoveOperationActionImpl implements RefactoringAction {

	private final static Path eolModulePath;

	private final UMLRSolution solution;

//	private Operation targetObject;

	static {
		eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-epsilon", "src", "main", "resources", "refactoring-lib", "mv_op_nc_nn.eol");
	}

	public static Path getEolModulePath() {
		return eolModulePath;
	}

	public UMLMvOperationToNCToNN(RSolution sol) {
		this.solution = (UMLRSolution) sol;

		umlOpToMove = getRandomOperation();
		umlTargetComp = (Component) umlOpToMove.getOwner(); //it shall be the new component. 

		setParameters();
		createPreCondition();
		createPostCondition();

	}

	// retrieves a random operation from the source model
	private Operation getRandomOperation() {

		/*
		 * org.eclipse.uml2.uml.Package staticView = (org.eclipse.uml2.uml.Package)
		 * EcoreUtil .getObjectsByType(((Model) solution.getModel()).getOwnedElements(),
		 * UMLPackage.Literals.PACKAGE) .stream().filter(pkg -> ((NamedElement)
		 * pkg).getName().equals("static_view"));
		 */

		org.eclipse.uml2.uml.Package dynamicView = null;
		for (Object pkg : EcoreUtil.getObjectsByType(solution.getIModel().allContents(), UMLPackage.Literals.PACKAGE)) {
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

		// returns a random message from a random interaction. Moreover, the message has not to be a "reply" message
		do {
			msg = ((Interaction) uc.getOwnedBehaviors().get(0)).getMessages().get(JMetalRandom.getInstance().nextInt(0,
					((Interaction) uc.getOwnedBehaviors().get(0)).getMessages().size() - 1));
		} while (msg.getMessageSort().toString().equals("reply"));

		return (Operation) msg.getSignature();

//		return (Operation) interaction.getMessages().get(JMetalRandom.getInstance().nextInt(0, interaction.getMessages().size()-1)).getSignature();

		/*
		 * //List of all components within the model List<Object> cmps = new
		 * ArrayList<>( EcoreUtil.getObjectsByType(staticView.getOwnedElements(),
		 * UMLPackage.Literals.COMPONENT));
		 * 
		 * //List of components whose getOperations is not empty List<Component>
		 * cmpsWithOps = new ArrayList<>(); for (Object cmp : cmps) { if (!((Component)
		 * cmp).getAllOperations().isEmpty()) { cmpsWithOps.add((Component) cmp); } }
		 * 
		 * //TODO this safety check shall be moved to precondition of this action if
		 * (cmpsWithOps.isEmpty()) {
		 * System.err.println("Any components has at least one operation"); return null;
		 * }
		 * 
		 * Component cmp = (Component)
		 * cmpsWithOps.get(JMetalRandom.getInstance().nextInt(0, cmpsWithOps.size() -
		 * 1));
		 * 
		 * EList<Operation> randomComponentOperations = cmp.getOperations();
		 * 
		 * return randomComponentOperations .get(JMetalRandom.getInstance().nextInt(0,
		 * randomComponentOperations.size() - 1));
		 */
	}

	@Override
	public void execute() {
//		super.execute();

		EOLStandalone executor = new EOLStandalone();
		executor.setModel(solution.getIModel());
		executor.setSource(eolModulePath);
		executor.setParameter(umlOpToMove, "UML!Operation", "self");

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
				.createFOLSpectification("MvOperationToNCToNNPreCondition");

		ExistsOperator existsOpInOperations = solution.getManager().createExistsInCollectionOperator(getOpToMoveSVP(),
				getAllOpsMVP());

		ExistsOperator existsTargetInComponents = solution.getManager()
				.createExistsInCollectionOperator(getTargetCompSVP(), getAllCompsMVP());
		ExistsOperator existsOpInOpsOfTarget = solution.getManager().createExistsInCollectionOperator(getOpToMoveSVP(),
				getAllTargetCompOpsMVP());

		AndOperator andRoot = solution.getManager().createAndOperator();
		andRoot.getArguments().add(existsOpInOperations);
		andRoot.getArguments().add(existsTargetInComponents);
		andRoot.getArguments().add(existsOpInOpsOfTarget);

		specification.setRootOperator(andRoot);
		preCondition.setConditionFormula(specification);
		setPre(preCondition);
	}

	@Override
	public void createPostCondition() {
		PostCondition postCondition = solution.getManager().createPostCondition();
		FOLSpecification specification = solution.getManager()
				.createFOLSpectification("MvOperationToNCToNNPostCondition");

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
		UMLMvOperationToNCToNN cloned = new UMLMvOperationToNCToNN(solution);

		cloned.setNumOfChanges(this.getNumOfChanges());
		cloned.setCost(this.getCost());
		cloned.setName(this.getName());

		cloned.parameters = this.getParameters();
		cloned.pre = this.getPre();
		cloned.post = this.getPost();

		return cloned;
	}
	
	public boolean equals(Object op) {
		
		if(op.getClass() != this.getClass())
			return false;
		if(!this.getUmlOpToMove().equals(((UMLMvOperationToNCToNN)op).getUmlOpToMove()))
			return false;
		return true;
		
	}

//	public void setSolution(RSolution sol) {
//		this.solution = sol;
//	}

}
