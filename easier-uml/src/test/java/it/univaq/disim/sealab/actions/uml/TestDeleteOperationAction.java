//package it.univaq.disim.sealab.actions.uml;
//
//import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLDeleteOperationRefactoringAction;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import org.eclipse.uml2.uml.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.net.URISyntaxException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class TestDeleteOperationAction {
//
//    private Model model;
//    private UMLManager manager;
//    private Controller controller;
//    private Path pathToModel;
//
//    private UMLDeleteOperationRefactoringAction action;
//
//    public TestDeleteOperationAction(){}
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
//     * UMLDeleteOperationRefactoringAction PRECONDITIONS & POSTCONDITIONS
//     * PRE:
//     * 		- The target UML Component has to contain the desired UML Operation..
//     * POST:
//     * 		- The Operation must not exist inside the UML Model.
//     */
//    @Test
//    public void shouldDeleteOperation() {
//
//        // operation to delete.
//        Operation targetOperation = null;
//        String targetComponentName = "";
//        boolean result = true;
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
//                targetComponentName = component.getName();
//            }
//
//        }
//
//        assert!(targetOperation == null);
//
//        // create and execute an UML refactoring action.
//        this.action = new UMLDeleteOperationRefactoringAction(targetOperation, targetComponentName, this.manager);
//        action.execute();
//
//        // save the model.
//        assert (this.manager.saveModel());
//
//
//        // search inside sequence.
//        List<Interaction> interactionList = this.manager.getAllInteractions();
//        String targetOpName = this.action.getUmlOpToDelName();
//
//        //foreach interaction.
//        for(Interaction interaction: interactionList){
//
//            //foreach message of the interaction.
//            for(Message message: interaction.getMessages()){
//
//                // I have to do so because I don't have the object: it has been destroyed.
//                if(message.getName() != null &&
//                        (message.getName().replace(" ","").equals(targetOpName) ||
//                         message.getName().equals(targetOpName)) ) {
//                    // was not possible to delete.
//                    System.out.println("The message (" + message.getName() + ") has not been deleted!");
//                    assert false;
//                }
//            }
//        }
//        System.out.println("The operation (" + targetOpName + ") has been destroyed from all lifelines!");
//
//
//
//        // check if we delete the operation.
//
//        // retrieve component list.
//        List<Component> componentListUpdated = this.manager.getAllComponents();
//
//        // retrieve operation name.
//        String targetOperationName = this.action.getUmlOpToDelName();
//
//        // foreach component inside the model.
//        for(Component component: componentListUpdated){
//            // if true, we found the target component.
//            if(component.getName().equals(targetComponentName)){
//
//                // retrieve the list of all component operations.
//                List<Operation> operationList = component.getAllOperations();
//
//                // foreach operation.
//                for(Operation operation: operationList){
//                    // if operation is the same.
//                    if(operation.getName().equals(targetOperationName)){
//                        // we couldn't delete the node.
//                        System.out.println("The operation (" + targetOperationName + ") has not been deleted!");
//                        result = false;
//                        assert false;
//                    }
//                }
//            }
//        }
//
//        if(result){
//            System.out.println("The operation (" + targetOperationName + ") in the component " + targetComponentName +" was deleted successfully!");
//        }
//
//    }
//}
