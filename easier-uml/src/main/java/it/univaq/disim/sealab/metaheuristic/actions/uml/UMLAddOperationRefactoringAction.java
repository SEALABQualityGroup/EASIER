package it.univaq.disim.sealab.metaheuristic.actions.uml;
//package it.univaq.disim.sealab.metaheuristic.actions.uml;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.uml2.uml.Component;
//import org.eclipse.uml2.uml.Operation;
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
//import logicalSpecification.LogicalSpecificationFactory;
//import logicalSpecification.MultipleValuedParameter;
//import logicalSpecification.Parameter;
//import logicalSpecification.PostCondition;
//import logicalSpecification.PreCondition;
//import logicalSpecification.SingleValuedParameter;
//import logicalSpecification.actions.UML.impl.UMLAddOperationActionImpl;
//import logicalSpecification.impl.ActionImpl;
//
//public class UMLAddOperationRefactoringAction extends UMLAddOperationActionImpl {
//
//	// private Operation operationToAdd;
//	// private Component targetUMLComp;
//	//
//	// private SingleValuedParameter newOpSVP;
//	// private SingleValuedParameter targetCompSVP;
//	// private MultipleValuedParameter allComps;
//	// private MultipleValuedParameter targetCompOps;
//
//	private static Double MAX_VALUE = 100.0;
//
//	public UMLAddOperationRefactoringAction(Component targetUMLComp) {
//		setUmlTargetComp(targetUMLComp);
//		setParameters();
//		createPreCondition();
//		createPostCondition();
//		setCost(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
//		setNumOfChanges(JMetalRandom.getInstance().getRandomGenerator().nextDouble(1, MAX_VALUE));
//	}
//
//	// private UMLAddOperationRefactoringAction(UMLAddOperationRefactoringAction
//	// actionToCopy) {
//	// setTargetUMLComp(actionToCopy.getTargetUMLComp());
//	//
//	// setCost(actionToCopy.getCost());
//	// setNumOfChanges(actionToCopy.getNumOfChanges());
//	// setParameters(actionToCopy.getParameters());
//	// createPreCondition(actionToCopy.getPre());
//	// createPostCondition(actionToCopy.getPost());
//	// }
//	//
//	// private void createPostCondition(PostCondition post) {
//	// setPost(post);
//	// }
//	//
//	// private void createPreCondition(PreCondition pre) {
//	// setPre(pre);
//	// }
//	//
//	// private void setParameters(EList<Parameter> parameters) {
//	// getParameters().addAll(parameters);
//	// }
//	//
//	// public UMLAddOperationRefactoringAction cloneAction(){
//	// return new UMLAddOperationRefactoringAction(this);
//	// }
//
//	@Override
//	public void execute() {
//
//		/**
//		 * Operation createOwnedOperation(String name, EList<String> parameterNames,
//		 * EList<Type> parameterTypes, Type returnType);
//		 * 
//		 */
//		umlOpToAdd = umlTargetComp.createOwnedOperation("newOp" + Math.random(), null, null);
//
//	}
//
//	@Override
//	public void log() {
//		Controller.logger_.info("UMLAddOperationRefactoringAction");
//		// if(operationToAdd != null)
//		// Controller.logger_.info(operationToAdd.toString());
//		//
//		// for (Operation op : targetUMLComp.getOperations()){
//		// Controller.logger_.info(op.getName());
//		// }
//
//	}
//
//	// public Component getTargetUMLComp() {
//	// return targetUMLComp;
//	// }
//	//
//	// public void setTargetUMLComp(Component targetUMLComp) {
//	// this.targetUMLComp = targetUMLComp;
//	// }
//
//	@Override
//	public void createPostCondition() {
//		// TODO Auto-generated method stub
//		PostCondition postCondition = LogicalSpecificationFactory.eINSTANCE.createPostCondition();
//		FOLSpecification addPreSpecification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("AddOperationPostCondition");
//		AndOperator addPreAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//
//		ExistsOperator addPreAndExists1 = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getTargetCompSVP(),
//				getAllCompsMVP());
//		addPreAnd.getArguments().add(addPreAndExists1);
//
//		ExistsOperator addPreAndExists2 = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getOpToAddSVP(),
//				getTargetCompOpsMVP());
//		addPreAnd.getArguments().add(addPreAndExists2);
//
//		addPreSpecification.setRootOperator(addPreAnd);
//		postCondition.setConditionFormula(addPreSpecification);
//		setPost(postCondition);
//	}
//
//	@Override
//	public void createPreCondition() {
//		PreCondition preCondition = LogicalSpecificationFactory.eINSTANCE.createPreCondition();
//		FOLSpecification addPreSpecification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("AddOperationPreCondition");
//		AndOperator addPreAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//
//		ExistsOperator addPreAndExists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getTargetCompSVP(),
//				getAllCompsMVP());
//		addPreAnd.getArguments().add(addPreAndExists);
//
//		// NotOperator addPreAndNot = Manager.createNotOperator();
//		// ExistsOperator addPreAndNotExists =
//		// Manager.createExistsOperator(getNewOpSVP(), getTargetCompOps());
//		// addPreAndNot.setArgument(addPreAndNotExists);
//		// addPreAnd.getArguments().add(addPreAndNot);
//
//		addPreSpecification.setRootOperator(addPreAnd);
//		preCondition.setConditionFormula(addPreSpecification);
//		setPre(preCondition);
//	}
//
//	// @Override
//	// public void setPostCondition() {
//	// // TODO Auto-generated method stub
//	// PreCondition preCondition =
//	// LogicalSpecificationFactory.eINSTANCE.createPreCondition();
//	// FOLSpecification addPreSpecification =
//	// Manager.createFOLSpectification("AddOperationPreCondition");
//	// AndOperator addPreAnd = Manager.createAndOperator();
//	//
//	// ExistsOperator addPreAndExists1 =
//	// Manager.createExistsOperator(getTargetComponent(),
//	// Manager.createMultipleValuedParameter(OclStringManager.getAllComponentsQuery()));
//	// addPreAnd.getArguments().add(addPreAndExists1);
//	//
//	// ExistsOperator addPreAndExists2 = Manager.createExistsOperator(getNewOpSVP(),
//	// Manager.createMultipleValuedParameter(OclStringManager.getOperationsOfQuery(getTargetUMLComp())));
//	// addPreAnd.getArguments().add(addPreAndExists2);
//	//
//	// addPreSpecification.setRootOperator(addPreAnd);
//	// preCondition.setConditionFormula(addPreSpecification);
//	// setPre(preCondition);
//	// }
//	//
//	// @Override
//	// public void setPreCondition() {
//	// PreCondition preCondition =
//	// LogicalSpecificationFactory.eINSTANCE.createPreCondition();
//	// FOLSpecification addPreSpecification =
//	// Manager.createFOLSpectification("AddOperationPreCondition");
//	// AndOperator addPreAnd = Manager.createAndOperator();
//	//
//	// ExistsOperator addPreAndExists =
//	// Manager.createExistsOperator(getTargetComponent(),
//	// Manager.createMultipleValuedParameter(OclStringManager.getAllComponentsQuery()));
//	// addPreAnd.getArguments().add(addPreAndExists);
//	//
//	// NotOperator addPreAndNot = Manager.createNotOperator();
//	// ExistsOperator addPreAndNotExists =
//	// Manager.createExistsOperator(getNewOpSVP(),
//	// Manager.createMultipleValuedParameter(OclStringManager.getOperationsOfQuery(getTargetUMLComp())));
//	// addPreAndNot.setArgument(addPreAndNotExists);
//	// addPreAnd.getArguments().add(addPreAndNot);
//	//
//	// addPreSpecification.setRootOperator(addPreAnd);
//	// preCondition.setConditionFormula(addPreSpecification);
//	// setPre(preCondition);
//	// }
//
//	@Override
//	public void setParameters() {
//		// TODO Auto-generated method stub
//		// ACTION add PARAMETERS
//		List<Parameter> addParams = new ArrayList<>();
//		// PAKIMOR _FIXME le add non dovrebbero avere come attributo l'oggetto da creare
//
//		if (umlOpToAdd != null) {
//			setOpToAddSVP(Manager.getInstance(UMLManager.getInstance())
//					.createSingleValueParameter(((UMLOclStringManager) OclStringManager.getInstance(new UMLOclStringManager())).getOperationQuery(umlOpToAdd)));
//			addParams.add(getOpToAddSVP());
//		}
//
//		setTargetCompSVP(Manager.getInstance(UMLManager.getInstance())
//				.createSingleValueParameter(((UMLOclStringManager) OclStringManager.getInstance(new UMLOclStringManager())).getComponentQuery(getUmlTargetComp())));
//		addParams.add(getTargetCompSVP());
//
//		setAllCompsMVP(Manager.getInstance(UMLManager.getInstance())
//				.createMultipleValuedParameter(((UMLOclStringManager) OclStringManager.getInstance(new UMLOclStringManager())).getAllComponentsQuery()));
//		addParams.add(getAllCompsMVP());
//
//		setTargetCompOpsMVP(Manager.getInstance(UMLManager.getInstance())
//				.createMultipleValuedParameter(((UMLOclStringManager) OclStringManager.getInstance(new UMLOclStringManager())).getOperationsOfQuery(getUmlTargetComp())));
//		addParams.add(getTargetCompOpsMVP());
//
//		getParameters().addAll(addParams);
//	}
//
//	// SingleValuedParameter getNewOpSVP() {
//	// return newOpSVP;
//	// }
//	//
//	// void setNewOpSVP(SingleValuedParameter newOpSVP) {
//	// this.newOpSVP = newOpSVP;
//	// }
//	//
//	// SingleValuedParameter getTargetCompSVP() {
//	// return targetCompSVP;
//	// }
//	//
//	// void setTargetCompSVP(SingleValuedParameter targetCompSVP) {
//	// this.targetCompSVP = targetCompSVP;
//	// }
//	//
//	// private MultipleValuedParameter getAllComps() {
//	// return allComps;
//	// }
//	//
//	// private void setAllComps(MultipleValuedParameter allComps) {
//	// this.allComps = allComps;
//	// }
//	//
//	// private MultipleValuedParameter getTargetCompOps() {
//	// return targetCompOps;
//	// }
//	//
//	// private void setTargetCompOps(MultipleValuedParameter targetCompOps) {
//	// this.targetCompOps = targetCompOps;
//	// }
//
//}
