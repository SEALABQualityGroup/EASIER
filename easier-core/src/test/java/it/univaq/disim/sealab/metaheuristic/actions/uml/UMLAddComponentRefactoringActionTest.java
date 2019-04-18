package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.nio.file.Paths;

import org.eclipse.uml2.uml.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;

public class UMLAddComponentRefactoringActionTest {

	private UMLManager umlManager;
	private Model model;
	private final String absolutePath = "/Users/nando/git/sealab/easier/easier-core/src/test/resources/UML/BGCS/BGCS.uml";
	private UMLAddComponentRefactoringAction action;

	@Before
	public void setUp() {
		Controller ctrl = new UMLController();
		umlManager = new UMLManager(ctrl);
		umlManager.packageRegistering();
		model = umlManager.getModel(Paths.get(absolutePath));
		action = new UMLAddComponentRefactoringAction(null, umlManager);
	}

	
	//check if the first element of the source model is an instance of  UML Model
	@Ignore
	@Test
	public void shouldReturnModel() {
		assert (model instanceof Model);
	}

	@Test
	public void shouldAddAComponentToTheSourceModel() {
		
		action.execute();
	
	}

	@Ignore
	@After
	public void tearDown() {
		System.out.println("Test ended!");
	}

}
