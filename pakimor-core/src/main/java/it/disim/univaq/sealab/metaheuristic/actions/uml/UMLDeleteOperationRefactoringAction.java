//package it.disim.univaq.sealab.metaheuristic.actions.uml;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.uml2.uml.Operation;
//import org.uma.jmetal.util.pseudorandom.JMetalRandom;
//
//import it.disim.univaq.sealab.metaheuristic.evolutionary.Controller;
//import it.disim.univaq.sealab.metaheuristic.managers.Manager;
//import it.disim.univaq.sealab.metaheuristic.managers.ocl.OclStringManager;
//import it.disim.univaq.sealab.metaheuristic.managers.ocl.uml.OclUMLStringManager;
//import it.disim.univaq.sealab.metaheuristic.managers.uml.UMLManager;
//import logicalSpecification.AndOperator;
//import logicalSpecification.ExistsOperator;
//import logicalSpecification.FOLSpecification;
//import logicalSpecification.MultipleValuedParameter;
//import logicalSpecification.NotOperator;
//import logicalSpecification.Parameter;
//import logicalSpecification.PostCondition;
//import logicalSpecification.PreCondition;
//import logicalSpecification.SingleValuedParameter;
//import logicalSpecification.actions.UML.impl.UMLDeleteOperationActionImpl;
//import logicalSpecification.impl.ActionImpl;
//
//public class UMLDeleteOperationRefactoringAction extends UMLDeleteOperationActionImpl {
//
////	private Operation umlOpToDel;
////	
////	private SingleValuedParameter opToDelSVP;
////	private MultipleValuedParameter allOpsMVP;
//	
//	private static Double MAX_VALUE = 100.0;
//
//	public UMLDeleteOperationRefactoringAction(Operation operation){
//		setUmlOpToDel(operation);
//		setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
//		setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
//		setParameters();
//		createPreCondition();
//		createPostCondition();
//	}
//	
////	private UMLDeleteOperationRefactoringAction(UMLDeleteOperationRefactoringAction actionToCopy) {
////		setUmlOpToDel(actionToCopy.getUmlOpToDel());
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
////	public UMLDeleteOperationRefactoringAction cloneAction(){
////		return new UMLDeleteOperationRefactoringAction(this);
////	}
//	
//	@Override
//	public void execute() {
//		getUmlOpToDel().destroy();
//	}
//
//	@Override
//	public void log(){
//		Controller.logger_.info("UMLDeleteOperationRefactoringAction");
////		Controller.logger_.info(getUmlOpToDel().getName());
//	}
//	
//	@Override
//	public void createPostCondition() {
//		PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();
//		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("DeleteOperationPostCondition");
//
//		AndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		
//		NotOperator notOperator = Manager.getInstance(UMLManager.getInstance()).createNotOperator();
//		ExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getOpToDelSVP(), getAllOpsMVP());
//		notOperator.setArgument(existsOperator);
//		
//		preAnd.getArguments().add(notOperator);
//
//		specification.setRootOperator(preAnd);
//		
//		postCondition.setConditionFormula(specification);
//		
//		setPost(postCondition);
//
//	}
//
//	@Override
//	public void createPreCondition() {
//		PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();
//		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("DeleteOperationPreCondition");
//		
//		AndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		
//		ExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getOpToDelSVP(), getAllOpsMVP());
//		
//		preAnd.getArguments().add(existsOperator);
//		
//		specification.setRootOperator(preAnd);
//		preCondition.setConditionFormula(specification);
//		
//		setPre(preCondition);
//	}
//
//	@Override
//	public void setParameters() {
//		// TODO Auto-generated method stub
//		List<Parameter> delOpParams = new ArrayList<>();
//		
//		setOpToDelSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getOperationQuery(getUmlOpToDel())));
//		delOpParams.add(getOpToDelSVP());
//		
//		
//		setAllOpsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllOperationsQuery()));
//		delOpParams.add(getAllOpsMVP());
//		
//		getParameters().addAll(delOpParams);
//	}
//	
////	public Operation getUmlOpToDel() {
////		return umlOpToDel;
////	}
////
////	public void setUmlOpToDel(Operation operation) {
////		this.umlOpToDel = operation;
////	}
////
////	SingleValuedParameter getOpToDelSVP() {
////		return opToDelSVP;
////	}
////
////	void setOpToDelSVP(SingleValuedParameter opToDelSVP) {
////		this.opToDelSVP = opToDelSVP;
////	}
////
////	MultipleValuedParameter getAllOpsMVP() {
////		return allOpsMVP;
////	}
////
////	void setAllOpsMVP(MultipleValuedParameter allOpsMVP) {
////		this.allOpsMVP = allOpsMVP;
////	}
//
//}
