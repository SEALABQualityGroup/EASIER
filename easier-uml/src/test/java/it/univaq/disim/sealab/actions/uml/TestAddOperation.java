package it.univaq.disim.sealab.actions.uml;

import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLAddOperationRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TestAddOperation {

    private Model model;
    private UMLManager manager;
    private Controller controller;
    private Path pathToModel;

    private UMLAddOperationRefactoringAction action;

    public TestAddOperation(){}

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
     * UMLAddOperationRefactoringAction PRECONDITIONS & POSTCONDITIONS
     * PRE:
     * 		- The name of the new Operation must not exist inside the Component.
     * POST:
     * 		- The Operation has to be added to the selected Component.
     */

    @Test
    public void shouldAddOperationToTheComponent() {

        // use this.
        this.model.getOwnedElements();

        // get random component: the first one.
        Component targetComponent = this.manager.getAllComponents().get(0);

        // create and execute an UML refactoring action.
        this.action = new UMLAddOperationRefactoringAction(targetComponent, this.manager);
        action.execute();

        // save the model.
        assert (this.manager.saveModel());

        // retrieve component list.
        List<Component> componentList = this.manager.getAllComponents();
        // retrieve operation name.
        String operationName = this.action.getOperationToAddName();

        // foreach component inside the model.
        for(Component component: componentList){
            // if we found the same component.
            if(component.getName().equals(targetComponent.getName())){
                // retrieve the list of all component operations.
                List<Operation> operationList = component.getAllOperations();
                // foreach operation.
                for(Operation operation: operationList){
                    // if the operation is the same.
                    if(operation.getName().equals(operationName)){
                        System.out.println("We successfully add the operation (" + operationName + ") inside the component " + component.getName() +"!");
                        break;
                    }
                }
            }
        }

    }

}
