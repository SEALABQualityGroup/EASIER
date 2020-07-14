package it.univaq.disim.sealab.actions.uml;

import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLAddNodeRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLDeleteNodeRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TestDeleteNodeAction {

    private Model model;
    private UMLManager manager;
    private Controller controller;
    private Path pathToModel;

    private UMLDeleteNodeRefactoringAction action;

    public TestDeleteNodeAction() {}

    @Before
    public void setUp() throws URISyntaxException {
        // create UML Manager.
        this.controller = new UMLController();
        this.manager = new UMLManager(this.controller);

        this.manager.packageRegistering();

        // uml model.
        this.pathToModel = Paths.get(getClass().getResource("/models/smartFloor/smartFloor2.uml").toURI());
        // load the model.
        this.manager.loadModel(this.pathToModel);

        this.model = this.manager.getModel();
        System.out.println("UML document name: "  + this.model.getName());
    }

    /**
     * UMLDeleteNodeRefactoringAction PRECONDITIONS & POSTCONDITIONS
     * PRE:
     * 		- The Node must not contain deployed Components.
     * POST:
     * 	    - The Node must not exist inside the UML model.
     */

    @Test
    public void shouldRemoveNodeFromTheModel() {

        // retrieve all node inside the model.
        List<Node> nodeList = this.manager.getAllNodes();
        // target (empty node).
        Node targetNode = null;

        // find one empty node.
        for(Node node: nodeList){
            // if it is empty.
            if (node.getDeployedElements().isEmpty()){
                // set target node.
                targetNode = node;
                if(targetNode.getPackage() != null){
                    System.out.println("We found empty node called: " + targetNode.getName() + " inside package called: " + targetNode.getPackage().getName());
                }
                else {
                    System.out.println("We found empty node called: " + targetNode.getName());
                }
                // we found it, break.
                break;
            }
        }

        // there is one empty node?
        assert targetNode != null;

        // create and execute an UML refactoring action.
        this.action =  new UMLDeleteNodeRefactoringAction(targetNode, this.manager);
        this.action.execute();

        // save the model.
        assert (this.manager.saveModel());

        // the name of the deleted node.
        String nodeToDelName = this.action.getUmlNodeToDelName();
        boolean result = true;

        // retrieve again all node inside the model.
        List<Node> nodeListUpdated = this.manager.getAllNodes();

        // check if we deleted it.
        for(Node node: nodeListUpdated){
            // if is the same.
            if(node.getName().equals(nodeToDelName)){
                System.out.println("The node ( " + nodeToDelName + ") has not been deleted!");
                // we couldn't delete the node.
                result = false;
                assert false;
            }
        }

        if(result){
            System.out.println("The node (" + nodeToDelName + " was deleted successfully!");
        }
    }


}
