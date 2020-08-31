//package it.univaq.disim.sealab.metaheuristic.actions.uml;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.eclipse.emf.ecore.util.EcoreUtil;
////import org.uma.jmetal.util.pseudorandom.JMetalRandom;
//import org.eclipse.uml2.uml.Artifact;
//import org.eclipse.uml2.uml.Component;
//import org.eclipse.uml2.uml.Deployment;
//import org.eclipse.uml2.uml.Lifeline;
//import org.eclipse.uml2.uml.Manifestation;
//import org.eclipse.uml2.uml.Model;
//import org.eclipse.uml2.uml.Node;
//import org.eclipse.uml2.uml.Package;
//import org.eclipse.uml2.uml.UMLPackage;
//import org.uma.jmetal.util.pseudorandom.JMetalRandom;
//
//import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclStringManager;
////import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
////import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
////import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.OclUMLStringManager;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;
//import logicalSpecification.AndOperator;
//import logicalSpecification.ExistsOperator;
//import logicalSpecification.FOLSpecification;
//import logicalSpecification.NotOperator;
//import logicalSpecification.Parameter;
//import logicalSpecification.PostCondition;
//import logicalSpecification.PreCondition;
////import logicalSpecification.AndOperator;
////import logicalSpecification.ExistsOperator;
////import logicalSpecification.FOLSpecification;
////import logicalSpecification.MultipleValuedParameter;
////import logicalSpecification.NotOperator;
////import logicalSpecification.Parameter;
////import logicalSpecification.PostCondition;
////import logicalSpecification.PreCondition;
////import logicalSpecification.SingleValuedParameter;
//import logicalSpecification.actions.UML.impl.UMLDeleteComponentActionImpl;
//
//public class UMLDeleteComponentRefactoringAction extends UMLDeleteComponentActionImpl implements RefactoringAction {
//
////	private Component umlCompToDel;
////	private String umlCompToDelName;
//
////    private UMLManager umlManager;
//
//	// private SingleValuedParameter compToDelSVP;
//	// private MultipleValuedParameter allCompsMVP;
//	// private static Double MAX_VALUE = 100.0;
//
//	private RSolution solution;
//
//	public UMLDeleteComponentRefactoringAction(RSolution sol) {
//		this.solution = sol;
//
//		this.numOfChanges = 1;
//		this.cost = 1;
//
//		setParameters();
//		createPreCondition();
//		createPostCondition();
//	}
//
//	private Component findRandomSourceComponent() {
//
//		Package pkg = ((UMLMetamodelManager) this.solution.getManager().getMetamodelManager())
//				.findPackage(UMLMetamodelManager.STATIC_VIEW);
//
//		return (Component) EcoreUtil.getObjectsByType(pkg.getOwnedElements(), UMLPackage.Literals.COMPONENT)
//				.toArray()[JMetalRandom.getInstance().nextInt(0,
//						EcoreUtil.getObjectsByType(pkg.getOwnedElements(), UMLPackage.Literals.COMPONENT).size() - 1)];
//
//	}
//
//	
//	//Return component lifelines used in all Scenarios
//	private List<Lifeline> getComponentLifelines(Component cmp){
//		
//		
//		return null;
//	}
//
//	
//	/*
//	 * TODO
//	 * Move all incoming messages, and thus moving the relative operation in the static view.
//	 * If an operation is used by more than one component, it will be moved randomly to a component
//	 * that uses it.
//	 */
//	@Override
//	public void execute() {
//		// execute the action.
//		Component umlCompToDel = findRandomSourceComponent();
//
//		System.out.println("We want to delete the component with name: " + umlCompToDel.getName() + " in the package "
//				+ umlCompToDel.getPackage().getName() + ".");
//
//		// destroy component lifelines inside in the sequence.
//		System.out.println("Here the list of all the Lifelines to delete: ");
//
//		// PLEASE NOTE that we had to do this before to delete all the manifestations.
//
//		// take all the component lifelines.
//		List<Lifeline> lifelines = getComponentLifelines(umlCompToDel); 
//				
////		((UMLMetamodelManager) this.solution.getManager().getMetamodelManager()).getAllComponentLifelines(umlCompToDel);
//		for (Lifeline lifeline : lifelines) {
//			System.out.println(" - " + lifeline.getName());
//			// destroy lifeline
//			lifeline.destroy();
//		}
//
//		// retrieve all manifestations.
//		List<Manifestation> list_of_manifestations = ((UMLMetamodelManager) this.solution.getManager()
//				.getMetamodelManager()).getAllManifestationsOf(this.umlCompToDel);
//		// delete all manifestations.
//		System.out.println("Here the list of all the Manifestations to delete: ");
//		for (Manifestation man : list_of_manifestations) {
//			System.out.println(" - " + man.getName());
//			if (man.getUtilizedElement().getNamespace() == getUmlCompToDel().getNamespace()) {
//				// destroy manifestation.
//				man.destroy();
//			}
//		}
//
//		System.out.println("Here the list of all the Artifacts to delete: ");
//		// take all artifact.
//		List<Artifact> artifactList = ((UMLMetamodelManager) this.solution.getManager().getMetamodelManager())
//				.getAllArtifacts();
//		for (Artifact artifact : artifactList) {
//			// if the artifact has the same name.
//			if (artifact.getName().equals(umlCompToDel.getName() + "_Artifact")) {
//				System.out.println(" - " + artifact.getName());
//				// delete the artifact.
//				artifact.destroy();
//			}
//		}
//
//		// destroy the _Deployments.
//
//		System.out.println("Here the list of all the Deployments to delete: ");
//		// take all nodes.
//		List<Node> nodeList = ((UMLMetamodelManager) this.solution.getManager().getMetamodelManager()).getAllNodes();
//		for (Node node : nodeList) {
//			// take all deployments of a node.
//			List<Deployment> deploymentList = node.getDeployments();
//			// we need to store all the indexes of the deployment occurrences.
//			int index = 0;
//			List<Integer> indexs = new ArrayList<>();
//			for (Deployment deployment : deploymentList) {
//				// if it is not null and the name is equal to the component name.
//				if (deployment.getName() != null
//						&& deployment.getName().equals(umlCompToDel.getName() + "_Deployment")) {
//					System.out.println(" - " + deployment.getName());
//					// store the index.
//					indexs.add(index);
//				} else {
//					// increase index.
//					index++;
//				}
//			}
//
//			// delete all deployments.
//			for (Integer i : indexs) {
//				deploymentList.get(i).destroy();
//			}
//		}
//
//		// destroy the component.
//		this.umlCompToDel.destroy();
//
//	}
//
//	public Component getUmlCompToDel() {
//		// return the umlCompToDel.
//		return this.umlCompToDel;
//	}
//
//	public void setUmlCompToDel(Component component) {
//		// set the umlCompToDel.
//		this.umlCompToDel = component;
//	}
//
////	public String getUmlCompToDelName() {
////		// return the umlCompDelName.
////		return umlCompToDelName;
////	}
////
////	public void setUmlCompToDelName(String umlCompToDelName) {
////		// set the umlCompToDelName.
////		this.umlCompToDelName = umlCompToDelName;
////	}
//
////	private void createPostCondition(PostCondition post) {
////		setPost(post);
////	}
//
////	private void createPreCondition(PreCondition pre) {
////		setPre(pre);
////	}
//
////	private void setParameters(EList<Parameter> parameters) {
////		getParameters().addAll(parameters);
////	}
//
////	public UMLDeleteComponentRefactoringAction cloneAction(){
////		return new UMLDeleteComponentRefactoringAction(this);
////	}
//
//	@Override
//	public void createPostCondition() {
//		PostCondition postCondition = this.solution.getManager().createPostCondition();
//
//		FOLSpecification specification = this.solution.getManager()
//				.createFOLSpectification("DeleteComponentPostCondition");
//		AndOperator preAnd = this.solution.getManager().createAndOperator();
//		NotOperator notOperator = this.solution.getManager().createNotOperator();
//		ExistsOperator existsOperator = this.solution.getManager().createExistsInCollectionOperator(getCompToDelSVP(),
//				getAllCompsMVP());
//		notOperator.setArgument(existsOperator);
//		preAnd.getArguments().add(notOperator);
//		specification.setRootOperator(preAnd);
//		postCondition.setConditionFormula(specification);
//		setPost(postCondition);
//	}
//
//	@Override
//	public void createPreCondition() {
//		PreCondition preCondition = this.solution.getManager().createPreCondition();
//		FOLSpecification specification = this.solution.getManager()
//				.createFOLSpectification("DeleteComponentPreCondition");
//		AndOperator preAnd = this.solution.getManager().createAndOperator();
//		ExistsOperator exists = this.solution.getManager().createExistsInCollectionOperator(getCompToDelSVP(),
//				getAllCompsMVP());
//		preAnd.getArguments().add(exists);
//		specification.setRootOperator(preAnd);
//		preCondition.setConditionFormula(specification);
//		setPre(preCondition);
//	}
//
//	@Override
//	public void setParameters() {
//		List<Parameter> delCompParams = new ArrayList<>();
//
//		setCompToDelSVP(this.solution.getManager()
//				.createSingleValueParameter(((UMLOclStringManager) this.solution.getManager().getOclStringManager())
//						.getComponentQuery(getUmlCompToDel())));
//
//		delCompParams.add(getCompToDelSVP());
//
//		setAllCompsMVP(this.solution.getManager().createMultipleValuedParameter(
//				((UMLOclStringManager) this.solution.getManager().getOclStringManager()).getAllComponentsQuery()));
//
//		delCompParams.add(getAllCompsMVP());
//
//		getParameters().addAll(delCompParams);
//	}
//
//	@Override
//	public RefactoringAction clone(RSolution solution) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setSolution(RSolution sol) {
//		// TODO Auto-generated method stub
//		
//	}
//
////	SingleValuedParameter getCompToDelSVP() {
////		return compToDelSVP;
////	}
//
////	void setCompToDelSVP(SingleValuedParameter compToDelSVP) {
////		this.compToDelSVP = compToDelSVP;
////	}
//
////	MultipleValuedParameter getAllCompsMVP() {
////		return allCompsMVP;
////	}
//
////	void setAllCompsMVP(MultipleValuedParameter allComps) {
////		this.allCompsMVP = allComps;
////	}
//
//}
