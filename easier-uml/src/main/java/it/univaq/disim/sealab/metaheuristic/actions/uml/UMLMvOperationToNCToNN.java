package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;

import it.univaq.disim.sealab.epsilon.refactoring.EOLStandalone;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclStringManager;
import logicalSpecification.AndOperator;
import logicalSpecification.ExistsOperator;
import logicalSpecification.FOLSpecification;
import logicalSpecification.Parameter;
import logicalSpecification.PostCondition;
import logicalSpecification.actions.UML.UMLPackage;
import logicalSpecification.actions.UML.impl.UMLMoveOperationActionImpl;

public class UMLMvOperationToNCToNN extends UMLMoveOperationActionImpl implements RefactoringAction {

	private final static Path eolModulePath;

	private final RSolution solution;

	private Component targetObject;

	static {
		eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-epsilon", "src", "main", "resources", "refactoring-lib", "mv_comp_nn.eol");
	}

	public static Path getEolModulePath() {
		return eolModulePath;
	}

	public UMLMvOperationToNCToNN(RSolution sol) {
		this.solution = sol;

		targetObject = getRandomComponent();

		setParameters();
		createPreCondition();
		createPostCondition();

	}

	// retrieves a random operation from the source model
	private Component getRandomComponent() {

		org.eclipse.uml2.uml.Package staticView = (org.eclipse.uml2.uml.Package) EcoreUtil.getObjectsByType(((Model) solution.getModel()).getOwnedElements(),
				UMLPackage.Literals.PACKAGE).stream().filter(pkg -> ((NamedElement) pkg).getName().equals("staticView"));
		
		
		Collection<Object> set = EcoreUtil.getObjectsByType(((Model) solution.getModel()).getOwnedElements(),
				UMLPackage.Literals.COMPONENT);
		return (Component) set.stream().skip(new Random().nextInt(set.size())).findFirst().orElse(null);

	}

	@Override
	public void execute() {
		super.execute();

		EOLStandalone executor = new EOLStandalone();
		executor.setModel(solution.getModelPath());
		executor.setSource(eolModulePath);
		executor.setParameter(targetObject, "UML!Operation", "self");

	}

	@Override
	public void createPostCondition() {
		// TODO Auto-generated method stub
		PostCondition postCondition = solution.getManager().createPostCondition();

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
		postCondition.setConditionFormula(specification);
		setPost(postCondition);
	}

	@Override
	public void createPreCondition() {
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

//	public void setSolution(RSolution sol) {
//		this.solution = sol;
//	}

}
