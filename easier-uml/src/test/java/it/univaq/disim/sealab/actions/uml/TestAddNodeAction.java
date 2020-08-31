//package it.univaq.disim.sealab.actions.uml;
//
//import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLAddNodeRefactoringAction;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import org.eclipse.uml2.uml.Model;
//import org.eclipse.uml2.uml.Node;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.net.URISyntaxException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class TestAddNodeAction {
//
//    private Model model;
//    private UMLManager manager;
//    private Controller controller;
//    private Path pathToModel;
//
//    private UMLAddNodeRefactoringAction action;
//
//    public TestAddNodeAction() {}
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
//     * UMLAddNodeRefactoringAction PRECONDITIONS & POSTCONDITIONS
//     * PRE:
//     * 		- The name of the new Node must not exist inside the UML model
//     * POST:
//     * 		- None.
//     */
//
//    @Test
//    public void shouldAddNodeToTheModel() {
//
//        String nodeName = "newNode" + Math.random();
//
//        // create and execute an UML refactoring action.
//        this.action = new UMLAddNodeRefactoringAction(this.manager, nodeName);
//        action.execute();
//
//        // save the model.
//        assert (this.manager.saveModel());
//
//        // retrieve a list of existing nodes.
//        List<Node> nodeList = this.manager.getAllNodes();
//
//        // foreach node.
//        for (org.eclipse.uml2.uml.Node node : nodeList){
//            if (node.getName().equals(nodeName)){
//                // return true.
//                System.out.println("The node (" + nodeName + ") was added successfully!");
//                assert (true);
//            }
//        }
//    }
//
//}
