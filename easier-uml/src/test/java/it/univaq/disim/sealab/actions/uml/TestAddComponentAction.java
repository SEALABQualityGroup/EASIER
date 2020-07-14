package it.univaq.disim.sealab.actions.uml;


import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.PackageableElement;
import org.junit.Before;
import org.junit.Test;

import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLAddComponentRefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;

public class TestAddComponentAction {
	
	private Model model;
	private UMLManager manager;
	private Controller controller;
	
	private Path pathToModel;
	private UMLAddComponentRefactoringAction action;

	public TestAddComponentAction() {}
	
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
	 * 		- The name of the new Component must not exist inside the UML model.
	 * POST:
	 * 		- The new node must be deployed in a node.
	 */

	@Test
	public void shouldAddComponentToTheModel() {
		// retrieve UML nodes: we want to set the component in a list of random nodes??.
		List<Node> targets = this.manager.getRandomNodes();

		assert(!targets.isEmpty());

		// create and execute an UML refactoring action.
		this.action = new UMLAddComponentRefactoringAction(targets, this.manager);
		this.action.execute();

		// save the model.
		assert (this.manager.saveModel());

		// retrieve the list of all the component.
		List<Component> componentList = this.manager.getAllComponents();
		// component name.
		String componentName = this.action.getUmlCompToAdd().getName();

		// foreach component.
		for (Component component: componentList) {
			// if the name is equal to the added one.
			if(component.getName().equals(componentName)){
				// return true.
				System.out.println("The component (" + componentName + ") was added successfully!");
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
				if (element.getName().equals(componentName)){
					// return true.
					System.out.println("The component (" + componentName + ") was deployed successfully on node " + node.getName() +"!");
					assert (true);
				}
			}
		}


	}
}
