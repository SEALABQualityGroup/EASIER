//package it.univaq.disim.sealab.metaheuristic.actions.uml;
//
//import java.util.ArrayList;
//import java.util.List;
//
////import org.eclipse.emf.common.util.EList;
//import org.eclipse.uml2.uml.*;
////import org.uma.jmetal.util.pseudorandom.JMetalRandom;
//
////import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
////import it.univaq.disim.sealab.metaheuristic.managers.Manager;
////import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
////import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.OclUMLStringManager;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
////import logicalSpecification.AndOperator;
////import logicalSpecification.ExistsOperator;
////import logicalSpecification.FOLSpecification;
////import logicalSpecification.MultipleValuedParameter;
////import logicalSpecification.NotOperator;
////import logicalSpecification.Parameter;
////import logicalSpecification.PostCondition;
////import logicalSpecification.PreCondition;
////import logicalSpecification.SingleValuedParameter;
//import logicalSpecification.actions.UML.impl.UMLDeleteOperationActionImpl;
////import logicalSpecification.impl.ActionImpl;
//
//public class UMLDeleteOperationRefactoringAction extends UMLDeleteOperationActionImpl {
//
//	private Operation umlOpToDel;
//	private String umlOpToDelName;
//	private String targetComponentName;
//	// uml manager.
//	private UMLManager umlManager;
//
//    //private SingleValuedParameter opToDelSVP;
//    //private MultipleValuedParameter allOpsMVP;
//    //private static Double MAX_VALUE = 100.0;
//
//	public UMLDeleteOperationRefactoringAction(Operation operation, String targetComponentName, UMLManager umlManager){
//
//	    // set operation.
//        this.umlOpToDel = operation;
//
//        // set umlOpToDelName.
//        this.umlOpToDelName = operation.getName();
//
//        // set targetComponentName.
//        this.targetComponentName = targetComponentName;
//
//        // set uml manager.
//        this.umlManager = umlManager;
//
//        this.numOfChanges = 1;
//
//		/*
//		setParameters();
//		createPreCondition();
//		createPostCondition();
//		*/
//	}
//
//    @Override
//    public void execute() {
//        // execute the action.
//
//        System.out.println("We want to delete the operation with name: " + this.umlOpToDelName+ " in the component " + this.targetComponentName +  ".");
//
//        List<Component> componentList = this.umlManager.getAllComponents();
//        Component targetComponent = null;
//        for(Component component: componentList){
//            if(component.getName().equals(this.targetComponentName)){
//                // set component.
//                targetComponent = component;
//                break;
//            }
//        }
//
//        if (targetComponent != null) {
//
//            System.out.println("Here the list of all messages to destroy: ");
//            // search inside sequence.
//            List<Interaction> interactionList = this.umlManager.getAllInteractions();
//
//            //foreach interaction.
//            for(Interaction interaction: interactionList){
//
//                List<Message> messages = interaction.getMessages();
//                // we need to store all the indexes of the deployment occurrences.
//                int index = 0;
//                List<Integer> indexs = new ArrayList<>();
//
//                //foreach message of the interaction.
//                for(Message message: messages){
//
//                    if (message.getSignature() != null && message.getSignature().equals(this.umlOpToDel)) {
//                        // replace spaces.
//                        System.out.println(" - " + message.getSignature().getName().replace(" ",""));
//                        // store the index.
//                        indexs.add(index);
//                    }
//                    else {
//                        // increase index.
//                        index++;
//                    }
//                }
//                // delete all messages.
//                for(Integer i: indexs){
//                    System.out.println("The operation " + messages.get(i).getSignature().getName() + " has been destroyed.");
//                    messages.get(i).destroy();
//                }
//
//            }
//        }
//
//
//        // Object assignment is reference to real object.
//        this.umlOpToDel.destroy();
//    }
//
//    public String getUmlOpToDelName() {
//	    // return umlOpToDelName.
//        return umlOpToDelName;
//    }
//
//    public void setUmlOpToDelName(String umlOpToDelName) {
//        // set umlOpToDelName.
//        this.umlOpToDelName = umlOpToDelName;
//    }
//
////  private void createPostCondition(PostCondition post) {
////  	setPost(post);
////  }
//
////  private void createPreCondition(PreCondition pre) {
////  	setPre(pre);
////  }
//
////  private void setParameters(EList<Parameter> parameters) {
////  	getParameters().addAll(parameters);
////  }
//
////  public UMLDeleteOperationRefactoringAction cloneAction(){
////  	return new UMLDeleteOperationRefactoringAction(this);
////  }
//
//
////	@Override
////	public void createPostCondition() {
////		PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();
////		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("DeleteOperationPostCondition");
////
////		AndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
////		
////		NotOperator notOperator = Manager.getInstance(UMLManager.getInstance()).createNotOperator();
////		ExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getOpToDelSVP(), getAllOpsMVP());
////		notOperator.setArgument(existsOperator);
////		
////		preAnd.getArguments().add(notOperator);
////
////		specification.setRootOperator(preAnd);
////		
////		postCondition.setConditionFormula(specification);
////		
////		setPost(postCondition);
////
////	}
//
////	@Override
////	public void createPreCondition() {
////		PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();
////		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("DeleteOperationPreCondition");
////		
////		AndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
////		
////		ExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getOpToDelSVP(), getAllOpsMVP());
////		
////		preAnd.getArguments().add(existsOperator);
////		
////		specification.setRootOperator(preAnd);
////		preCondition.setConditionFormula(specification);
////		
////		setPre(preCondition);
////	}
//
////	@Override
////	public void setParameters() {
////		// TODO Auto-generated method stub
////		List<Parameter> delOpParams = new ArrayList<>();
////		
////		setOpToDelSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getOperationQuery(getUmlOpToDel())));
////		delOpParams.add(getOpToDelSVP());
////		
////		
////		setAllOpsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllOperationsQuery()));
////		delOpParams.add(getAllOpsMVP());
////		
////		getParameters().addAll(delOpParams);
////	}
//
////	public Operation getUmlOpToDel() {
////		return umlOpToDel;
////	}
//
////  public void setUmlOpToDel(Operation operation) {
////  	this.umlOpToDel = operation;
////  }
//
////  SingleValuedParameter getOpToDelSVP() {
////  	return opToDelSVP;
////  }
//
////  void setOpToDelSVP(SingleValuedParameter opToDelSVP) {
////  	this.opToDelSVP = opToDelSVP;
////  }
//
////  MultipleValuedParameter getAllOpsMVP() {
////  	return allOpsMVP;
////  }
//
////  void setAllOpsMVP(MultipleValuedParameter allOpsMVP) {
////  	this.allOpsMVP = allOpsMVP;
////  }
//
//}
