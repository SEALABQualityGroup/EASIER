package it.univaq.disim.sealab.actions.uml;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.uml2.uml.Model;
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
		controller = new UMLController();
		manager = new UMLManager(controller);
		manager.packageRegistering();
		pathToModel = Paths.get(getClass().getResource("/models/smartFloor/smartFloor2.uml").toURI());
		manager.loadModel(pathToModel);
		model = manager.getModel();
	}

	@Test
	public void shouldAddComponentToTheModel() {
		List<org.eclipse.uml2.uml.Node> targets = manager.getRandomNodes();
		assertTrue(!targets.isEmpty());
		action = new UMLAddComponentRefactoringAction(targets, manager);
		action.execute();
		//TODO add an assert to verify if the model contains the new component
		//manager.saveModel();
	}
}
