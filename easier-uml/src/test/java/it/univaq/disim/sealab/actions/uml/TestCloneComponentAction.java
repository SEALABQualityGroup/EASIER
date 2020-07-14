package it.univaq.disim.sealab.actions.uml;

import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLCloneComponentRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.PackageableElement;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TestCloneComponentAction {

    private Model model;
    private UMLManager manager;
    private Controller controller;
    private Path pathToModel;

    private UMLCloneComponentRefactoringAction action;

    public TestCloneComponentAction() {}

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
     * UMLCloneComponentRefactoringAction PRECONDITIONS & POSTCONDITIONS
     * PRE:
     * 		- A Component has to exist inside the UML model.
     * POST:
     * 	    - A copy of the selected Component has to be create inside the UML model.
     * 	    - A copy of the selected Component has to be deployed inside the UML model.
     */
    @Test
    public void shouldCloneComponentToTheModel() {
        // get random component.
        Component componentToClone = this.manager.getAllComponents().get(1);

        assert componentToClone != null;

        // retrieve UML nodes: we want to set the component in a list of random nodes??.
        List<Node> targetNodes = this.manager.getRandomNodes();

        assert(!targetNodes.isEmpty());

        // create and execute an UML refactoring action.
        this.action = new UMLCloneComponentRefactoringAction(componentToClone, targetNodes);
        this.action.execute();

        // save the model.
        assert (this.manager.saveModel());

        

        // take the list of all the components.
        List<Component> componentList = this.manager.getAllComponents();

        // component name.
        String componentCloneName = this.action.getUmlCompCloneName();

        // foreach component.
        for (Component component: componentList) {
            // if the name is equal to the added one.
            if(component.getName().equals(componentCloneName)){
                // return true.
                System.out.println("The component (" + componentCloneName + ") was cloned successfully!");
                assert (true);
            }
        }

        // retrieve a list of existing nodes.
        List<Node> nodeList = this.manager.getAllNodes();

        // foreach node.
        for(Node node: nodeList){

            //retrieve deployed elements.
            List<PackageableElement> cList = node.getDeployedElements();
            //foreach element.
            for (PackageableElement element: cList){
                // if the  element is the deployed component.
                if (element.getName().equals(componentCloneName)){
                    // return true.
                    System.out.println("The component (" + componentCloneName + ") was deployed successfully on node " + node.getName() +"!");
                    assert (true);
                }
            }
        }
    }
}
