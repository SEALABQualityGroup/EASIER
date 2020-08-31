//package it.univaq.disim.sealab.actions.uml;
//
//import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMoveOperationRefactoringAction;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import org.eclipse.uml2.uml.*;
//import org.eclipse.uml2.uml.internal.impl.MessageOccurrenceSpecificationImpl;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.net.URISyntaxException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class TestMoveOperationAction {
//
//    private Model model;
//    private UMLManager manager;
//    private Controller controller;
//    private Path pathToModel;
//
//    private UMLMoveOperationRefactoringAction action;
//
//    public  TestMoveOperationAction(){}
//
//    @Before
//    public void setUp() throws URISyntaxException {
//
//        // create UML Manager.
//        this.controller = new UMLController();
//        this.manager = new UMLManager(this.controller);
//
//        this.manager.packageRegistering();
//
//        // uml model.
//        this.pathToModel = Paths.get(getClass().getResource("/models/smartFloor/smartFloor2.uml").toURI());
//        // load the model.
//        this.manager.loadModel(this.pathToModel);
//
//        this.model = this.manager.getModel();
//        System.out.println("UML document name: "  + this.model.getName());
//    }
//
//
//    /**
//     * UMLMoveOperationRefactoringAction PRECONDITIONS & POSTCONDITIONS
//     * PRE:
//     * 		- The Operation name must not exist inside target Component (where we want to move the chosen operation).
//     *      - The Operation must not be an asynchronous call (in the sequence diagram).
//     *      - The Sequence Diagram in which occurs the Operation have to be “well-formed”.
//     * POST:
//     * 		- The Operation must not exist inside the original Component and its lifelines
//     *      - The Operation must be moved, in each Sequence Diagram in which occurs, to the target “Component Lifeline”.
//     */
//    @Test
//    public void shouldMoveOperation() {
//
//        // operation to delete.
//        Operation targetOperation = null;
//        // origin component.
//        Component originComponent = null;
//        String originComponentName = "";
//        // target component.
//        Component targetComponent = null;
//        String targetComponentName = "";
//
//        // retrieve component list.
//        List<Component> componentList = this.manager.getAllComponents();
//
//        // foreach component inside the model.
//        for(Component component: componentList){
//
//            // if the component has operations
//            if(!component.getAllOperations().isEmpty()){
//                // get first.
//                targetOperation = component.getAllOperations().get(0);
//                // set name.
//                originComponent = component;
//                originComponentName = component.getName();
//            }
//        }
//
//        // retrieve target component: foreach component inside the model.
//        for(Component component: componentList){
//
//            // if the component is different respect the origin one.
//            if(!component.getName().equals(originComponentName)){
//                // set component and  name.
//                targetComponent = component;
//                targetComponentName = component.getName();
//            }
//        }
//
//        assert!(targetOperation == null);
//        assert!(targetComponent == null);
//        assert!(originComponent == null);
//
//        // create and execute an UML refactoring action.
//        this.action = new UMLMoveOperationRefactoringAction(targetOperation, originComponent ,targetComponent, manager);
//        action.execute();
//
//        // save the model.
//        assert (this.manager.saveModel());
//
//
//        // retrieve component list.
//        List<Component> componentListUpdated = this.manager.getAllComponents();
//
//        // foreach component inside the model.
//        for(Component component: componentListUpdated){
//
//            // if the component is the target component.
//            if(component.getName().equals(targetComponentName)){
//                // retrieve operation list.
//                List<Operation> operationList = component.getAllOperations();
//                // foreach operation inside the model.
//                for(Operation operation: operationList){
//                    // if is the target operation: OK!
//                    if (operation.getName().equals(targetOperation.getName())){
//                        System.out.println("The operation (" + targetOperation.getName() + ") has been successfully moved from the component " + originComponentName +" to the component " + targetComponentName +"!");
//                        break;
//                    }
//                }
//
//            }
//            // if the component is the origin component.
//            if(component.getName().equals(originComponentName)) {
//                // retrieve operation list.
//                List<Operation> operationList = component.getAllOperations();
//                // foreach operation inside the model.
//                for(Operation operation: operationList){
//                    // if is the target operation: ERROR, assert false.
//                    if (operation.getName().equals(targetOperation.getName())){
//                        System.out.println(operation.getName() + " " + targetOperation.getName());
//                        assert false;
//                    }
//                }
//            }
//        }
//
//        // search inside sequence.
//        List<Interaction> interactionList = this.manager.getAllInteractions();
//        boolean esito = false;
//
//        //foreach interaction.
//        for(Interaction interaction: interactionList){
//
//            // reset.
//            esito = false;
//
//            //foreach message of the interaction.
//            for(Message message: interaction.getMessages()){
//
//                // signature is operation Impl.
//                Object o = message.getSignature();
//                if(o instanceof Operation) {
//                    // if the signature is the operation.
//                    if(o.equals(targetOperation)){
//
//                        // retrieve target lifeline.
//                        Lifeline targetLifeline = ((MessageOccurrenceSpecificationImpl) message.getReceiveEvent()).getCovered();
//
//                        // retrieve all the lifeline of the target component.
//                        List<Lifeline> targetComponentLifelines = this.manager.getAllComponentLifelines(targetComponent);
//
//                        // foreach lifeline of the target component
//                        for(Lifeline lf : targetComponentLifelines){
//
//                            if(lf.getRepresents().equals(targetLifeline.getRepresents())){
//                                System.out.println("The operation " + targetOperation.getName() + " was added to the " + targetLifeline.getName() + " lifeline!");
//                                esito = true;
//                            }
//
//                        }
//                        if(!esito){
//                            System.out.println("Was not possible to remove the operation " + message.getName() + " from the " + originComponent.getName() + " lifeline!");
//                            assert false;
//                        }
//                    }
//                }
//
//            }
//        }
//
//
//
//
//
//
//    }
//
//
//}
