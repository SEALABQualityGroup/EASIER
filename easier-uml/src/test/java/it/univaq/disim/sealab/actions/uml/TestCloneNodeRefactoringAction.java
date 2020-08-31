//package it.univaq.disim.sealab.actions.uml;
//
//import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLCloneComponentRefactoringAction;
//import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLCloneNodeRefactoringAction;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.uml2.uml.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.net.URISyntaxException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class TestCloneNodeRefactoringAction {
//
//    private Model model;
//    private UMLManager manager;
//    private Controller controller;
//    private Path pathToModel;
//
//    private UMLCloneNodeRefactoringAction action;
//
//    public  TestCloneNodeRefactoringAction(){}
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
//     * UMLCloneNodeRefactoringAction PRECONDITIONS & POSTCONDITIONS
//     * PRE:
//     * 		- None.
//     * POST:
//     * 		- A copy of the selected Node has to be created inside the UML model.
//     *      - Each Component deployed inside of the selected Node has to be deployed inside the copied Node.
//     *  	- Each Communication Path, of the selected Node, must be cloned.
//     * 	    - Each nested Node of the selected Node, must be added to the cloned Node.
//     */
//
//    @Test
//    public void shouldCloneNode() {
//
//
//        // take node to clone.
//        Node nodeToClone = this.manager.getAllNodes().get(5);
//
//        assert nodeToClone != null;
//
//        // create and execute an UML refactoring action.
//        this.action = new UMLCloneNodeRefactoringAction(nodeToClone, manager);
//        action.execute();
//
//        // save the model.
//        assert (this.manager.saveModel());
//
//        // retrieve clone node.
//        Node clonedNode = this.action.getClonedNode();
//        System.out.println("\n********************");
//
//
//        // check nested nodes.
//        System.out.println("\ngetNestedNodes:");
//        EList<Node> nodeToCloneNestedNodes = nodeToClone.getNestedNodes();
//
//        int index = 0;
//        for (Node nestedNode: clonedNode.getNestedNodes()) {
//
//            if(nestedNode.getName().equals(nodeToCloneNestedNodes.get(index).getName())){
//                System.out.println(" - Are equal!");
//            }
//            else {
//                System.out.println(" - Are not equal!");
//                assert false;
//            }
//            // increase index.
//            index++;
//        }
//
//
//        // check deployed elements.
//
//        // reset index.
//        index = 0;
//        // retrieve deployed elements.
//        List<PackageableElement> deployedElements = nodeToClone.getDeployedElements();
//        System.out.println("getDeployedElements:");
//        // foreach deployed element.
//        for (PackageableElement element : clonedNode.getDeployedElements()) {
//            // if is a component.
//            if(element instanceof Component){
//                if(element.equals(deployedElements.get(index))){
//                    System.out.println(" - Are equal!");
//                }
//                else {
//                    System.out.println(" - Are not equal!");
//                    assert false;
//                }
//            }
//            // update index.
//            index++;
//        }
//
//        // reset index.
//        index = 0;
//        System.out.println("getCommunicationPaths:");
//        // retrieve communication Paths.
//        List<CommunicationPath> communicationPaths = nodeToClone.getCommunicationPaths();
//        for(CommunicationPath communicationPath : clonedNode.getCommunicationPaths()){
//            if(communicationPath.getName().replace("_Cloned", "").equals(communicationPaths.get(index).getName())){
//                System.out.println(" - Are equal!");
//            }
//            else {
//                System.out.println(" - Are not equal!");
//                assert false;
//            }
//            // update index.
//            index++;
//        }
//
//
//    }
//}
