package it.univaq.disim.sealab.metaheuristic.actions.uml;
//package it.univaq.disim.sealab.metaheuristic.actions.uml;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.uml2.uml.Component;
//import org.eclipse.uml2.uml.Manifestation;
//import org.uma.jmetal.util.pseudorandom.JMetalRandom;
//
//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.managers.Manager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.UMLOclStringManager;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import logicalSpecification.AndOperator;
//import logicalSpecification.ExistsOperator;
//import logicalSpecification.FOLSpecification;
//import logicalSpecification.MultipleValuedParameter;
//import logicalSpecification.NotOperator;
//import logicalSpecification.Parameter;
//import logicalSpecification.PostCondition;
//import logicalSpecification.PreCondition;
//import logicalSpecification.SingleValuedParameter;
//import logicalSpecification.actions.UML.impl.UMLDeleteComponentActionImpl;
//import logicalSpecification.impl.ActionImpl;
//
//public class UMLDeleteComponentRefactoringAction extends UMLDeleteComponentActionImpl {
//
////	private Component umlCompToDel;
////	
////	private SingleValuedParameter compToDelSVP;
////	private MultipleValuedParameter allCompsMVP;
//	
//	private static Double MAX_VALUE = 100.0;
//
//	public UMLDeleteComponentRefactoringAction(Component component) {
//		setUmlCompToDel(component);
//		setParameters();
//		createPreCondition();
//		createPostCondition();
//		setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
//		setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
//	}
//	
////	private UMLDeleteComponentRefactoringAction(UMLDeleteComponentRefactoringAction actionToCopy) {
////		setUmlCompToDel(actionToCopy.getUmlCompToDel());
////		setCost(actionToCopy.getCost());
////		setNumOfChanges(actionToCopy.getNumOfChanges());
////		setParameters(actionToCopy.getParameters());
////		createPreCondition(actionToCopy.getPre());
////		createPostCondition(actionToCopy.getPost());
////	}
////
////	private void createPostCondition(PostCondition post) {
////		setPost(post);
////	}
////
////	private void createPreCondition(PreCondition pre) {
////		setPre(pre);
////	}
////
////	private void setParameters(EList<Parameter> parameters) {
////		getParameters().addAll(parameters);
////	}
////	
////	public UMLDeleteComponentRefactoringAction cloneAction(){
////		return new UMLDeleteComponentRefactoringAction(this);
////	}
//
//	@Override
//	public void execute() {
//		List<Manifestation> list_of_manifestations = ((UMLManager) Manager.getInstance(UMLManager.getInstance()).getMetamodelManager()).getAllManifestationsOf(getUmlCompToDel());
//		for (Manifestation man : list_of_manifestations) {
//			if (man.getUtilizedElement().getNamespace() == getUmlCompToDel().getNamespace()) {
//				man.destroy();
//			}
//		}
//		umlCompToDel.destroy();
//	}
//
//	@Override
//	public void log() {
//		Controller.logger_.info("UMLDeleteComponentRefactoringAction");
////		Controller.logger_.info(getUmlCompToDel().getName());
//	}
//
////	public Component getUmlCompToDel() {
////		return umlCompToDel;
////	}
////
////	public void setUmlCompToDel(Component component) {
////		this.umlCompToDel = component;
////	}
//
//	@Override
//	public void createPostCondition() {
//		PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();
//
//		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("DeleteComponentPostCondition");
//		AndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		NotOperator notOperator = Manager.getInstance(UMLManager.getInstance()).createNotOperator();
//		ExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance()).getInstance(new UMLManager()).createExistsOperator(getCompToDelSVP(), getAllCompsMVP());
//		notOperator.setArgument(existsOperator);
//		preAnd.getArguments().add(notOperator);
//		specification.setRootOperator(preAnd);
//		postCondition.setConditionFormula(specification);
//		setPost(postCondition);
//	}
//
//	@Override
//	public void createPreCondition() {
//		PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();
//		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("DeleteComponentPreCondition");
//		AndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		ExistsOperator exists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getCompToDelSVP(), getAllCompsMVP());
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
//		setCompToDelSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(((UMLOclStringManager) OclStringManager.getInstance(new UMLOclStringManager())).getComponentQuery(getUmlCompToDel())));
//		delCompParams.add(getCompToDelSVP());
//		
//		setAllCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((UMLOclStringManager) OclStringManager.getInstance(new UMLOclStringManager())).getAllComponentsQuery()));
//		delCompParams.add(getAllCompsMVP());
//		
//		getParameters().addAll(delCompParams);
//	}
//
////	SingleValuedParameter getCompToDelSVP() {
////		return compToDelSVP;
////	}
////
////	void setCompToDelSVP(SingleValuedParameter compToDelSVP) {
////		this.compToDelSVP = compToDelSVP;
////	}
////
////	MultipleValuedParameter getAllCompsMVP() {
////		return allCompsMVP;
////	}
////
////	void setAllCompsMVP(MultipleValuedParameter allComps) {
////		this.allCompsMVP = allComps;
////	}
//
//}
