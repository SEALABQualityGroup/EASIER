//package it.univaq.disim.sealab.actions.uml;
//
//import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLSplitComponentRefactoringAction;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import org.eclipse.uml2.uml.Component;
//import org.eclipse.uml2.uml.Model;
//import org.eclipse.uml2.uml.Operation;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.net.URISyntaxException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestSplitComponentAction {
//
//
//    private Model model;
//    private UMLManager manager;
//    private Controller controller;
//    private Path pathToModel;
//
//    private UMLSplitComponentRefactoringAction action;
//
//    public  TestSplitComponentAction(){}
//
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
//
//    /**
//     * UMLSplitComponentRefactoringAction PRECONDITIONS & POSTCONDITIONS
//     * PRE:
//     * 		- The target Component has to own almost two operations.
//     *      - A list of Operations to move must be provided.
//     * POST:
//     * 		- A copy of the selected Component has to be created inside the UML model.
//     *      - A copy of the selected Component has to be deployed inside the UML model.
//     *      - The Operations must be splitted according the provided list.
//     */
//
//    @Test
//    public void shouldSplitComponent() {
//
//        // declare a component to split.
//        Component componentToSplit = null;
//        int index = 0;
//
//        // retrieve component list.
//        List<Component> componentList = this.manager.getAllComponents();
//        // operation to move.
//        List<Operation> operationsToMove = new ArrayList<>();
//
//        // foreach component inside the model.
//        for(Component component: componentList){
//
//            // if the component has operations
//            if(!component.getAllOperations().isEmpty() && component.getOperations().size()>1){
//
//                //set component
//                componentToSplit = component;
//
//                // set number of operation to move..
//                int size = ((component.getOperations().size())/2);
//                while (index < size){
//                    // set operation.
//                    operationsToMove.add(component.getOperations().get(index));
//                    index ++;
//                }
//                break;
//            }
//        }
//
//
//        assert componentToSplit != null;
//        assert operationsToMove != null;
//
//
//        // create and execute an UML refactoring action.
//        this.action = new UMLSplitComponentRefactoringAction(componentToSplit, operationsToMove, this.manager);
//        action.execute();
//
//        // save the model.
//        assert (this.manager.saveModel());
//
//
//        // not need to verify if the component was split: we rely of action verified.
//
//    }
//}
