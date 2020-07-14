package it.univaq.disim.sealab.actions.uml;

import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMoveComponentRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestMoveComponentAction {

    private Model model;
    private UMLManager manager;
    private Controller controller;
    private Path pathToModel;

    private UMLMoveComponentRefactoringAction action;

    public  TestMoveComponentAction(){}


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
     * UMLAddComponentRefactoringAction PRECONDITIONS & POSTCONDITIONS
     * PRE:
     * 		- None.
     * POST:
     * 		- The new component must be deployed in the target nodes.
     */
    @Test
    public void shouldMoveComponent() {

        // retrieve UML nodes: we want to set the component in a list of random nodes.
        List<Node> targets = this.manager.getRandomNodes();

        assert (!targets.isEmpty());

        // get random component.
        Component targetComponent = this.manager.getAllComponents().get(5);

        assert targetComponent != null;

        // set origin nodes.
        List<Node> originNodes = new ArrayList<>();

        // foreach node
        for (Node node : this.manager.getAllNodes()) {

            // foreach deployment.
            for (Deployment deployment : node.getDeployments()) {

                // if the name is equal.
                if (deployment.getName() != null && (deployment.getName().equals(targetComponent.getName() + "_Deployment") || deployment.getName().equals(targetComponent.getName()))) {
                    // we found it: add the node to the origin.
                    originNodes.add(node);
                }
            }
        }

        // create and execute an UML refactoring action.
        this.action = new UMLMoveComponentRefactoringAction(targetComponent, targets, originNodes, this.manager);
        this.action.execute();

        // save the model.
        assert (this.manager.saveModel());


        // check if the component was moved to the actual nodes.

        // for each original node, check if it is also a target node.
        boolean isTheSameNode = false;
        for (Node node : originNodes) {

            // reset value.
            isTheSameNode = false;

            // check if it is also a target node.
            for (Node targetNode : targets) {
                if (node.getName().equals(targetNode.getName())) {
                    System.out.println("The origin node " + node.getName() + " is also a target node.");
                    isTheSameNode = true;
                }
            }
            // if the origin is not a target node.
            if(!isTheSameNode){
                // search here:

                // foreach deployment inside the original nodes.
                for (Deployment deployment : node.getDeployments()) {
                    // if the name is equal.
                    if (deployment.getName() != null && (deployment.getName().equals(targetComponent.getName() + "_Deployment") || deployment.getName().equals(targetComponent.getName()))) {
                        // we found it: something went wrong.
                        System.out.println("The component " + targetComponent.getName() + " in still on the origin node " + node.getName() + " !");
                        assert false;
                    }
                }
            }
        }

        for (Node targetNode : targets) {

            // foreach deployment inside the target nodes.
            for (Deployment deployment : targetNode.getDeployments()) {

                // if the name is equal.
                if (deployment.getName() != null && (deployment.getName().equals(targetComponent.getName() + "_Deployment") || deployment.getName().equals(targetComponent.getName()))) {
                    // we found it: something went wrong.
                    System.out.println("The component " + targetComponent.getName() + " was deployed on the target node " + targetNode.getName() + " !");
                }
            }
        }
    }




}
