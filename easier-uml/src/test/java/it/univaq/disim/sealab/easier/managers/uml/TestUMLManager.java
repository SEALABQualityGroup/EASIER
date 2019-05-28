package it.univaq.disim.sealab.easier.managers.uml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Device;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.junit.Before;
import org.junit.Test;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;

public class TestUMLManager {
	
	
	private Model umlModel;
	private UMLMetamodelManager umlManager;
	private Controller umlController;
	
	private Path pathToModel;

	public TestUMLManager() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void setUp() throws URISyntaxException {
		umlController = new UMLController();
		umlManager = new UMLMetamodelManager(umlController);
		umlManager.packageRegistering();
		pathToModel = Paths.get(getClass().getResource("/models/smartFloor/smartFloor2.uml").toURI());
		umlManager.loadModel(pathToModel);
		umlModel = umlManager.getModel();
	}
	
	@Test
	public void shouldReturnANonNullModel() {
		assertNotNull(umlModel);
	}
	
	@Test
	public void shouldReturnZeroStereotype() {
		assertTrue(umlModel.getAppliedStereotypes().isEmpty());
	}
	
	@Test
	public void printDevicesStereotypes() {
		for (Object pkg : EcoreUtil.getObjectsByType(umlModel.getOwnedElements(), UMLPackage.Literals.PACKAGE)) {
			System.out.println(pkg.toString());
			for(Object comp : EcoreUtil.getObjectsByType(((Element) pkg).getOwnedElements(), UMLPackage.Literals.DEVICE)) {
				System.out.println(comp.toString());
				System.out.println("\t "+((Device)comp).getAppliedStereotypes());
			}
		}
	}

}