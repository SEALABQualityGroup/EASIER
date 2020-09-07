/**
 * It moves a component from a node to another one
 */
package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.UMLFactory;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.UmlModel;
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
import logicalSpecification.actions.UML.impl.UMLMoveOperationActionImpl;

public class UMLMvComponentToNN extends UMLMoveComponentActionImpl implements RefactoringAction {

	private final static Path eolModulePath;

	private final UMLRSolution solution;

	private Component targetObject;
	private Node targetNode;

	static {
		eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-epsilon", "src", "main", "resources", "refactoring-lib", "mv_comp_nn.eol");
	}

	public static Path getEolModulePath() {
		return eolModulePath;
	}

	public UMLMvComponentToNN(RSolution sol) {
		this.solution = (UMLRSolution) sol;

		targetObject = getRandomComponent();

		targetNode = getRandomNode();

		setParameters();
		createPreCondition();
		createPostCondition();

	}

	// Creates a new random Node on which the component will be deployed on
	private Node getRandomNode() {

		org.eclipse.uml2.uml.Package deploymentView = null;
		// Retrieves the deployment view package
		/*
		 * EcoreUtil .getObjectsByType(solution.getIModel().allContents(),
		 * UMLPackage.Literals.PACKAGE).stream()
		 * .map(org.eclipse.uml2.uml.Package.class::cast).filter(pkg ->
		 * "deployment_view".equals(pkg.getName())) .findAny().orElse(null);
		 */
		for (Object pkg : EcoreUtil.getObjectsByType(solution.getIModel().allContents(), UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Package && "deployment_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
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
		// Retrieves the static view package
		/*
		 * org.eclipse.uml2.uml.Package staticView = EcoreUtil
		 * .getObjectsByType(solution.getIModel().allContents(),
		 * UMLPackage.Literals.PACKAGE).stream()
		 * .map(org.eclipse.uml2.uml.Package.class::cast).filter(pkg ->
		 * "static_view".equals(pkg.getName())) .findAny().orElse(null);
		 */
		for (Object pkg : EcoreUtil.getObjectsByType(solution.getIModel().allContents(), UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Package && "static_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
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
		executor.setParameter(targetObject, "UML!Component", "self");
		executor.setParameter(targetNode, "UML!Node", "target");

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

		FOLSpecification specification = solution.getManager().createFOLSpectification("MvComponentToNNPreCondition");

		ExistsOperator existsTargetInComponents = solution.getManager()
				.createExistsInCollectionOperator(getCompToMoveSVP(), getAllCompsMVP());

		AndOperator andRoot = solution.getManager().createAndOperator();
		andRoot.getArguments().add(existsTargetInComponents);

		specification.setRootOperator(andRoot);
		preCondition.setConditionFormula(specification);
		setPre(preCondition);
	}

	@Override
	public void createPostCondition() {
		PostCondition postCondition = solution.getManager().createPostCondition();
		FOLSpecification specification = solution.getManager().createFOLSpectification("MvComponentToNNPostCondition");

		ExistsOperator existsTargetInComponents = solution.getManager()
				.createExistsInCollectionOperator(getCompToMoveSVP(), getAllCompsMVP());

		AndOperator andRoot = solution.getManager().createAndOperator();
		andRoot.getArguments().add(existsTargetInComponents);

		// Verifies whether target nodes are created in the model
		ExistsOperator existsTargetNodes = solution.getManager().createExistsOperator(getTargetNodesMVP());
		andRoot.getArguments().add(existsTargetNodes);

		specification.setRootOperator(andRoot);
		postCondition.setConditionFormula(specification);
		setPost(postCondition);

	}

	@Override
	public void setParameters() {
		List<Parameter> params = new ArrayList<>();

		setCompToMoveSVP(solution.getManager().createSingleValueParameter(
				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getComponentQuery(targetObject)));
		params.add(getCompToMoveSVP());

//		setAllOpsMVP(solution.getManager().createMultipleValuedParameter(
//				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
//						.getAllOperationsQuery()));
//		params.add(getAllOpsMVP());

		setAllCompsMVP(solution.getManager().createMultipleValuedParameter(
				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getAllComponentsQuery()));

		// Sets the target node
		setTargetNodesMVP(solution.getManager().createMultipleValuedParameter(
				((UMLOclStringManager) this.solution.getManager().getMetamodelManager().getOclStringManager())
						.getNodesQuery(Arrays.asList(targetNode))));

		params.add(getAllCompsMVP());

		getParameters().addAll(params);
	}

	@Override
	public RefactoringAction clone(RSolution solution) {

		UMLMvComponentToNN cloned = new UMLMvComponentToNN(solution);

		cloned.setNumOfChanges(this.getNumOfChanges());
		cloned.setCost(this.getCost());
		cloned.setName(this.getName());

		cloned.parameters = this.getParameters();
		cloned.pre = this.getPre();
		cloned.post = this.getPost();

		return cloned;

	}

}
