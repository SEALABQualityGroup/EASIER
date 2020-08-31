//package it.univaq.disim.sealab.metaheuristic.actions.uml;
//
//import java.util.ArrayList;
//import java.util.List;
//
////import org.eclipse.emf.common.util.EList;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import metamodel.mmaemilia.Elem;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.uml2.uml.*;
////import org.uma.jmetal.util.pseudorandom.JMetalRandom;
//
////import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
////import it.univaq.disim.sealab.metaheuristic.managers.Manager;
////import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
////import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.OclUMLStringManager;
////import logicalSpecification.AndOperator;
////import logicalSpecification.ExistsOperator;
////import logicalSpecification.FOLSpecification;
////import logicalSpecification.MultipleValuedParameter;
////import logicalSpecification.NotOperator;
////import logicalSpecification.Parameter;
////import logicalSpecification.PostCondition;
////import logicalSpecification.PreCondition;
////import logicalSpecification.SingleValuedParameter;
//import logicalSpecification.actions.UML.impl.UMLMoveOperationActionImpl;
//import org.eclipse.uml2.uml.internal.impl.InteractionImpl;
//import org.eclipse.uml2.uml.internal.impl.MessageOccurrenceSpecificationImpl;
////import logicalSpecification.impl.ActionImpl;
//
//public class UMLMoveOperationRefactoringAction extends UMLMoveOperationActionImpl {
//
//    private Operation umlOpToMove;
//	private Component umlCompTarget;
//	private String    targetComponentName;
//    private Component umlCompOrigin;
//    private String    OriginComponentName;
//
//    // uml manager.
//    private UMLManager umlManager;
//
//    //	private SingleValuedParameter opToMoveSVP;
//    //	private SingleValuedParameter targetCompSVP;
//    //	private MultipleValuedParameter allOpsMVP;
//    //	private MultipleValuedParameter allCompsMVP;
//    //	private MultipleValuedParameter allTargetCompOpsMVP;
//    //	private static Double MAX_VALUE = 100.0;
//
//
//	public UMLMoveOperationRefactoringAction(Operation targetOp, Component origin , Component target, UMLManager manager) {
//
//
//	    // set operation
//        this.umlOpToMove = targetOp;
//
//        // set component
//        this.umlCompTarget = target;
//
//        // set targetComponentName.
//        this.targetComponentName = target.getName();
//
//        // set origin component
//        this.umlCompOrigin = origin;
//
//        // set originComponentName.
//        this.OriginComponentName = origin.getName();
//
//        // set manager
//        this.umlManager = manager;
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
//
//    @Override
//    public void execute() {
//        System.out.println("We want to move the operation with name: " + this.umlOpToMove.getName() + ", from the component " + this.OriginComponentName +  ", to the component " + this.targetComponentName +  ".");
//
//        // target lifeline.
//        Lifeline targetLifeline = null;
//        // actual interaction.
//        Interaction interaction = null;
//
//        // take all the component lifelines.
//        List<Lifeline> lifelines = this.umlManager.getAllComponentLifelines(this.umlCompOrigin);
//
//        // foreach lifeline
//        for (Lifeline lifeline: lifelines){
//
//            // create new return message.
//            Message returnMsg = UMLFactory.eINSTANCE.createMessage();
//
//            //update actual interaction.
//            interaction = lifeline.getInteraction();
//
//            //foreach message inside the lifeline.
//            for(Message message: interaction.getMessages()){
//
//                // signature is operation Impl.
//                Object o = message.getSignature();
//                if(o instanceof Operation) {
//
//                    // if the signature is the operation
//                    if(o.equals(this.umlOpToMove)){
//
//                        // print send and received (to move) event .
//                        System.out.println(" - old send event: " + message.getSendEvent().getName());
//                        System.out.println(" - old received event: " + message.getReceiveEvent().getName());
//
//                        // target component lifelines.
//                        List<Lifeline> umlTargetCompLifelines = this.umlManager.getAllComponentLifelines(this.umlCompTarget);
//                        for(Lifeline lf : umlTargetCompLifelines){
//
//                            // if interaction is the same we found the lifeline.
//                            if(lf.getInteraction().equals(interaction)){
//                                // set target lifeline
//                                targetLifeline = lf;
//                            }
//                        }
//
//                        // in the current sequence diagram there isn't the target lifeline.
//                        if (targetLifeline == null) {
//                            System.out.println("\n***A new lifeline was created.***\n");
//                            // create new lifeline.
//                            targetLifeline = UMLFactory.eINSTANCE.createLifeline();
//                            // set name
//                            targetLifeline.setName(this.targetComponentName + "_Lifeline");
//
//                            //set interaction.
//                            targetLifeline.setInteraction(interaction);
//                            // set e container.
//                            Property targetProp = interaction.createOwnedAttribute(this.umlCompTarget.getName(),this.umlCompTarget);
//                            // set represents.
//                            targetLifeline.setRepresents(targetProp);
//
//                        }
//                        // if is not an asynch call (does not have a BES).
//                        if(!message.getMessageSort().equals(MessageSort.ASYNCH_CALL_LITERAL)) {
//
//                            // create the BehaviorExecutionSpecification for the fragment (lifeline time of living).
//                            BehaviorExecutionSpecification beh = null;
//
//                            // foreach fragment of the actual interaction.
//                            for (InteractionFragment fragment : interaction.getFragments()) {
//                                if (fragment instanceof BehaviorExecutionSpecification) {
//
//                                    //if is the message fragment.
//                                    if (((BehaviorExecutionSpecification) fragment).getStart().equals(message.getReceiveEvent())) {
//                                        // set it (move to the target lifeline).
//                                        targetLifeline.getCoveredBys().add(fragment);
//                                        beh = (BehaviorExecutionSpecification) fragment;
//                                    }
//                                }
//                            }
//
//                            // set name.
//                            beh.setName(beh.getName() + "_Moved");
//
//                            // set the new receiveEvent.
//                            MessageOccurrenceSpecification receiveEvent = (MessageOccurrenceSpecification) message.getReceiveEvent();
//                            receiveEvent.setCovered(targetLifeline);
//                            // set behavior start.
//                            beh.setStart(receiveEvent);
//                            // add id to the target lifeline.
//                            targetLifeline.getCoveredBys().add(receiveEvent);
//                            System.out.println("BES start was set to: " + receiveEvent.getName());
//
//                            MessageOccurrenceSpecification sendEvent = null;
//                            // if it is not null.
//                            if(beh.getFinish() != null){
//                                sendEvent = (MessageOccurrenceSpecification) beh.getFinish();
//                                sendEvent.setCovered(targetLifeline);
//                                sendEvent.setName(receiveEvent.getName() + "_ReturnMSG");
//                                // add id to the target lifeline.
//                                targetLifeline.getCoveredBys().add(sendEvent);
//                                System.out.println("BES end was set to: " + sendEvent.getName());
//                            }
//                            else {
//                                System.out.println("***************");
//                                System.out.println("MALFORMED INPUT: beh.getFinish() == null");
//                                System.out.println("***************");
//                            }
//
//                            // retrieve return message.
//                            for (Message msg : interaction.getMessages()) {
//                                if (msg.getSendEvent() != null && msg.getSendEvent().equals(sendEvent)) {
//                                    // this is the return message
//                                    returnMsg = msg;
//                                }
//                            }
//                            if (returnMsg == null) {
//                                // set new return message.
//                                returnMsg = UMLFactory.eINSTANCE.createMessage();
//                                returnMsg.setMessageSort(MessageSort.REPLY_LITERAL);
//                                returnMsg.setName(message.getName() + "_ReturnMSG");
//                                returnMsg.setSendEvent(sendEvent);
//
//                                //create.
//                                MessageOccurrenceSpecification returnMSGreceiveEvent = UMLFactory.eINSTANCE.createMessageOccurrenceSpecification();
//
//                                // set target lifeline (where the return msg ends).
//                                Lifeline targetLlf = ((MessageOccurrenceSpecificationImpl) message.getSendEvent()).getCovered();
//                                returnMSGreceiveEvent.setCovered(targetLlf);
//                                returnMSGreceiveEvent.setName(message.getName() + "_ReturnMSG");
//                                returnMsg.setReceiveEvent(returnMSGreceiveEvent);
//                                targetLlf.getCoveredBys().add(returnMSGreceiveEvent);
//                                System.out.println("\n***A new returnMSG was created.***\n");
//                            }
//                        }
//                        else {// it is an Asynch msg
//                            System.out.println("***************");
//                            System.out.println("MALFORMED INPUT: ASYNCH MSG");
//                            System.out.println("***************");
//                        }
//
//                        // takes all from origin lifeline
//                        List<InteractionFragment> events = lifeline.getCoveredBys();
//                        // if it is empty.
//                        if(events.isEmpty()){
//                            // destroy it.
//                            System.out.println("The lifeline is empty: we destroy it!");
//                            lifeline.destroy();
//                        }
//
//                        System.out.println("\n - new send event: " + message.getSendEvent().getName());
//                        System.out.println(" - new received event: " + message.getReceiveEvent().getName());
//                    }
//                }
//            }
//            returnMsg.setInteraction(interaction); //set interactions
//        }
//
//        // changing the operation class we change the component ownership: the operation becomes of another component.
//        this.umlOpToMove.setClass_(this.umlCompTarget);
//    }
//
//
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
////	public UMLMoveOperationRefactoringAction cloneAction(){
////		return new UMLMoveOperationRefactoringAction(this);
////	}
//
////	@Override
////	public void createPostCondition() {
////		// TODO Auto-generated method stub
////		PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();
////		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("MoveOperationPostCondition");
////		
////		ExistsOperator existsOpInOperations = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getOpToMoveSVP(), getAllOpsMVP());
////		ExistsOperator existsTargetInComponents = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getTargetCompSVP(), getAllCompsMVP());
////		ExistsOperator existsOpInOpsOfTarget = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getOpToMoveSVP(), getAllTargetCompOpsMVP());
//
////		AndOperator andRoot = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
////		andRoot.getArguments().add(existsOpInOperations);
////		andRoot.getArguments().add(existsTargetInComponents);
////		andRoot.getArguments().add(existsOpInOpsOfTarget);
//
////		specification.setRootOperator(andRoot);
////		postCondition.setConditionFormula(specification);
////		setPost(postCondition);
////	}
//
////	@Override
////	public void createPreCondition() {
////		// TODO Auto-generated method stub
////		PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();
////		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("MoveOperationPreCondition");
//
////		ExistsOperator existsOpInOperations = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getOpToMoveSVP(), getAllOpsMVP());
////		ExistsOperator existsTargetInComponents = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getTargetCompSVP(), getAllCompsMVP());
////		ExistsOperator existsOpInOpsOfTarget = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getOpToMoveSVP(), getAllTargetCompOpsMVP());
////		NotOperator notExistsOpInOpsOfTarget = Manager.getInstance(UMLManager.getInstance()).createNotOperator(existsOpInOpsOfTarget);
//
////		AndOperator andRoot = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
////		andRoot.getArguments().add(existsOpInOperations);
////		andRoot.getArguments().add(existsTargetInComponents);
////		andRoot.getArguments().add(notExistsOpInOpsOfTarget);
//
////		specification.setRootOperator(andRoot);
////		preCondition.setConditionFormula(specification);
////		setPre(preCondition);
////	}
//
////	@Override
////	public void setParameters() {
////		// TODO Auto-generated method stub
////		List<Parameter> moveOpParams = new ArrayList<>();
//
////		setOpToMoveSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getOperationQuery(getUmlOpToMove())));
////		moveOpParams.add(getOpToMoveSVP());
//
////		setTargetCompSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getComponentQuery(getUmlTargetComp())));
////		moveOpParams.add(getTargetCompSVP());
//
//
////		setAllOpsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllOperationsQuery()));
////		moveOpParams.add(getAllOpsMVP());
//
////		setAllCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllComponentsQuery()));
////		moveOpParams.add(getAllCompsMVP());
//
////		setAllTargetCompOpsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getOperationsOfQuery(getUmlTargetComp())));
////		moveOpParams.add(getAllTargetCompOpsMVP());
//
////		getParameters().addAll(moveOpParams);
////	}
//
////	SingleValuedParameter getOpToMoveSVP() {
////		return opToMoveSVP;
////	}
//
////	void setOpToMoveSVP(SingleValuedParameter opToMoveSVP) {
////		this.opToMoveSVP = opToMoveSVP;
////	}
//
////	SingleValuedParameter getTargetCompSVP() {
////		return targetCompSVP;
////	}
//
////	void setTargetCompSVP(SingleValuedParameter targetCompSVP) {
////		this.targetCompSVP = targetCompSVP;
////	}
//
////	MultipleValuedParameter getAllOpsMVP() {
////		return allOpsMVP;
////	}
//
////	void setAllOpsMVP(MultipleValuedParameter allOps) {
////		this.allOpsMVP = allOps;
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
////	MultipleValuedParameter getAllTargetCompOpsMVP() {
////		return allTargetCompOpsMVP;
////	}
//
////	void setAllTargetCompOpsMVP(MultipleValuedParameter allTargetCompOps) {
////		this.allTargetCompOpsMVP = allTargetCompOps;
////	}
//
////	private Operation getUmlOpToMove() {
////		return umlOpToMove;
////	}
//
////	private void setUmlOpToMove(Operation umlOpToMove) {
////		this.umlOpToMove = umlOpToMove;
////	}
//
////	private Component getUmlCompTarget() {
////		return umlCompTarget;
////	}
//
////	private void setUmlCompTarget(Component umlCompTarget) {
////		this.umlCompTarget = umlCompTarget;
////	}
//
//}
