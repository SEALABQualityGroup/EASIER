/*
 * package it.univaq.disim.sealab.metaheuristic.actions.uml;
 * 
 * import org.eclipse.uml2.uml.Component; import org.eclipse.uml2.uml.Operation;
 * 
 * import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction; import
 * it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager; import
 * logicalSpecification.AndOperator; import logicalSpecification.ExistsOperator;
 * import logicalSpecification.FOLSpecification; import
 * logicalSpecification.LogicalSpecificationFactory; import
 * logicalSpecification.PostCondition; import
 * logicalSpecification.actions.UML.impl.UMLAddOperationActionImpl;
 * 
 * public class UMLAddOperationRefactoringAction extends
 * UMLAddOperationActionImpl implements RefactoringAction{
 * 
 * private Operation operationToAdd; private String operationToAddName; private
 * Component targetUMLComp;
 * 
 * // private SingleValuedParameter newOpSVP; // private SingleValuedParameter
 * targetCompSVP; // private MultipleValuedParameter allComps; // private
 * MultipleValuedParameter targetCompOps; // private static Double MAX_VALUE =
 * 100.0;
 * 
 * public UMLAddOperationRefactoringAction(Component targetUMLComp, UMLManager
 * umlManager) {
 * 
 * // set uml component. this.targetUMLComp = targetUMLComp;
 * 
 * this.numOfChanges = 1;
 * 
 * setParameters(); createPreCondition(); createPostCondition(); }
 * 
 * @Override public void execute() { // execute the action.
 * 
 * // set name. this.operationToAddName = "newOp" + Math.random();
 * 
 * // create new operation. this.operationToAdd =
 * this.targetUMLComp.createOwnedOperation(this.operationToAddName, null, null);
 * 
 * System.out.println("We want to add the operation with name: " +
 * this.operationToAdd.getName()+ " in the component " +
 * this.targetUMLComp.getName() + "."); }
 * 
 * private Component findRandomComponent() {
 * 
 * }
 * 
 * public Component getTargetUMLComp() { // return the targetUMLComp. return
 * this.targetUMLComp; }
 * 
 * public void setTargetUMLComp(Component targetUMLComp) { // set the
 * targetUMLComp. this.targetUMLComp = targetUMLComp; }
 * 
 * public String getOperationToAddName() { // return the operationToAddName;
 * return operationToAddName; }
 * 
 * public void setOperationToAddName(String operationToAddName) { // set the
 * operationToAddName; this.operationToAddName = operationToAddName; }
 * 
 * 
 * // private void createPostCondition(PostCondition post) { // setPost(post);
 * // }
 * 
 * // private void createPreCondition(PreCondition pre) { // setPre(pre); // }
 * 
 * // private void setParameters(EList<Parameter> parameters) { //
 * getParameters().addAll(parameters); // }
 * 
 * // public UMLAddOperationRefactoringAction cloneAction(){ // return new
 * UMLAddOperationRefactoringAction(this); // }
 * 
 * 
 * 
 * @Override public void createPostCondition() { // TODO Auto-generated method
 * stub PostCondition postCondition =
 * LogicalSpecificationFactory.eINSTANCE.createPostCondition(); FOLSpecification
 * addPreSpecification =
 * manager.createFOLSpectification("AddOperationPostCondition"); AndOperator
 * addPreAnd = manager.createAndOperator();
 * 
 * ExistsOperator addPreAndExists1 =
 * manager.createExistsOperator(getTargetCompSVP(), getAllCompsMVP());
 * addPreAnd.getArguments().add(addPreAndExists1);
 * 
 * ExistsOperator addPreAndExists2 =
 * manager.createExistsOperator(getOpToAddSVP(), getTargetCompOpsMVP());
 * addPreAnd.getArguments().add(addPreAndExists2);
 * 
 * addPreSpecification.setRootOperator(addPreAnd);
 * postCondition.setConditionFormula(addPreSpecification);
 * setPost(postCondition); }
 * 
 * // @Override // public void createPreCondition() { // PreCondition
 * preCondition = LogicalSpecificationFactory.eINSTANCE.createPreCondition(); //
 * FOLSpecification addPreSpecification =
 * Manager.getInstance(UMLManager.getInstance()).createFOLSpectification(
 * "AddOperationPreCondition"); // AndOperator addPreAnd =
 * Manager.getInstance(UMLManager.getInstance()).createAndOperator();
 * 
 * // ExistsOperator addPreAndExists =
 * Manager.getInstance(UMLManager.getInstance()).createExistsOperator(
 * getTargetCompSVP(), // getAllCompsMVP()); //
 * addPreAnd.getArguments().add(addPreAndExists);
 * 
 * // NotOperator addPreAndNot = Manager.createNotOperator(); // ExistsOperator
 * addPreAndNotExists = // Manager.createExistsOperator(getNewOpSVP(),
 * getTargetCompOps()); // addPreAndNot.setArgument(addPreAndNotExists); //
 * addPreAnd.getArguments().add(addPreAndNot); // //
 * addPreSpecification.setRootOperator(addPreAnd); //
 * preCondition.setConditionFormula(addPreSpecification); //
 * setPre(preCondition); // }
 * 
 * // @Override // public void setPostCondition() { //TODO Auto-generated method
 * stub // PreCondition preCondition = //
 * LogicalSpecificationFactory.eINSTANCE.createPreCondition(); //
 * FOLSpecification addPreSpecification = //
 * Manager.createFOLSpectification("AddOperationPreCondition"); // AndOperator
 * addPreAnd = Manager.createAndOperator(); // // ExistsOperator
 * addPreAndExists1 = // Manager.createExistsOperator(getTargetComponent(), //
 * Manager.createMultipleValuedParameter(OclStringManager.getAllComponentsQuery(
 * ))); // addPreAnd.getArguments().add(addPreAndExists1);
 * 
 * // ExistsOperator addPreAndExists2 =
 * Manager.createExistsOperator(getNewOpSVP(), //
 * Manager.createMultipleValuedParameter(OclStringManager.getOperationsOfQuery(
 * getTargetUMLComp()))); // addPreAnd.getArguments().add(addPreAndExists2);
 * 
 * // addPreSpecification.setRootOperator(addPreAnd); //
 * preCondition.setConditionFormula(addPreSpecification); //
 * setPre(preCondition); // }
 * 
 * // @Override // public void setPreCondition() { // PreCondition preCondition
 * = // LogicalSpecificationFactory.eINSTANCE.createPreCondition(); //
 * FOLSpecification addPreSpecification = //
 * Manager.createFOLSpectification("AddOperationPreCondition"); // AndOperator
 * addPreAnd = Manager.createAndOperator();
 * 
 * // ExistsOperator addPreAndExists = //
 * Manager.createExistsOperator(getTargetComponent(), //
 * Manager.createMultipleValuedParameter(OclStringManager.getAllComponentsQuery(
 * ))); // addPreAnd.getArguments().add(addPreAndExists);
 * 
 * // NotOperator addPreAndNot = Manager.createNotOperator(); // ExistsOperator
 * addPreAndNotExists = // Manager.createExistsOperator(getNewOpSVP(), //
 * Manager.createMultipleValuedParameter(OclStringManager.getOperationsOfQuery(
 * getTargetUMLComp()))); // addPreAndNot.setArgument(addPreAndNotExists); //
 * addPreAnd.getArguments().add(addPreAndNot);
 * 
 * // addPreSpecification.setRootOperator(addPreAnd); //
 * preCondition.setConditionFormula(addPreSpecification); //
 * setPre(preCondition); // }
 * 
 * // @Override // public void setParameters() { // // TODO Auto-generated
 * method stub // // ACTION add PARAMETERS // List<Parameter> addParams = new
 * ArrayList<>(); // // PAKIMOR _FIXME le add non dovrebbero avere come
 * attributo l'oggetto da creare // // if (umlOpToAdd != null) { //
 * setOpToAddSVP(Manager.getInstance(UMLManager.getInstance()) //
 * .createSingleValueParameter(((OclUMLStringManager)
 * OclStringManager.getInstance(new
 * OclUMLStringManager())).getOperationQuery(umlOpToAdd))); //
 * addParams.add(getOpToAddSVP()); // } // //
 * setTargetCompSVP(Manager.getInstance(UMLManager.getInstance()) //
 * .createSingleValueParameter(((OclUMLStringManager)
 * OclStringManager.getInstance(new
 * OclUMLStringManager())).getComponentQuery(getUmlTargetComp()))); //
 * addParams.add(getTargetCompSVP()); // //
 * setAllCompsMVP(Manager.getInstance(UMLManager.getInstance()) //
 * .createMultipleValuedParameter(((OclUMLStringManager)
 * OclStringManager.getInstance(new
 * OclUMLStringManager())).getAllComponentsQuery())); //
 * addParams.add(getAllCompsMVP()); // //
 * setTargetCompOpsMVP(Manager.getInstance(UMLManager.getInstance()) //
 * .createMultipleValuedParameter(((OclUMLStringManager)
 * OclStringManager.getInstance(new
 * OclUMLStringManager())).getOperationsOfQuery(getUmlTargetComp()))); //
 * addParams.add(getTargetCompOpsMVP()); // //
 * getParameters().addAll(addParams); // }
 * 
 * // SingleValuedParameter getNewOpSVP() { // return newOpSVP; // }
 * 
 * // void setNewOpSVP(SingleValuedParameter newOpSVP) { // this.newOpSVP =
 * newOpSVP; // }
 * 
 * // SingleValuedParameter getTargetCompSVP() { // return targetCompSVP; // }
 * 
 * // void setTargetCompSVP(SingleValuedParameter targetCompSVP) { //
 * this.targetCompSVP = targetCompSVP; // }
 * 
 * // private MultipleValuedParameter getAllComps() { // return allComps; // }
 * 
 * // private void setAllComps(MultipleValuedParameter allComps) { //
 * this.allComps = allComps; // }
 * 
 * // private MultipleValuedParameter getTargetCompOps() { // return
 * targetCompOps; // } // // private void
 * setTargetCompOps(MultipleValuedParameter targetCompOps) { //
 * this.targetCompOps = targetCompOps; // }
 * 
 * }
 */