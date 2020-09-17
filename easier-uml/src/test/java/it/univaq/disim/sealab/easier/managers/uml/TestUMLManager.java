package it.univaq.disim.sealab.easier.managers.uml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.uml2.uml.Device;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UMLPlugin;
import org.junit.Before;
import org.junit.Test;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;

public class TestUMLManager {

	private Model umlModel;
	private UMLMetamodelManager umlManager;
	private Controller umlController;

	private EmfModel umlIModel;

	private Path pathToModel;

	public TestUMLManager() {
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setUp() throws URISyntaxException, EolModelLoadingException {
//		umlController = new UMLController();
//		umlManager = new UMLMetamodelManager(umlController);
//		umlManager.packageRegistering();
		pathToModel = Paths.get("/home/peo/git/sealab/uml2lqn/org.univaq.uml2lqn/UMLModel/automatedGuidedVehicle.uml");
//		umlManager.loadModel(pathToModel);
//		umlModel = umlManager.getModel();

		umlIModel = EpsilonStandalone.createUmlModel("UML", pathToModel, UMLPackage.eNS_URI, true, false);
	}

	@Test
	public void shouldReturnGaExecHostStereotype() {

		org.eclipse.uml2.uml.Package deploymentView = null;
		for (Object pkg : EcoreUtil.getObjectsByType(umlIModel.allContents(), UMLPackage.Literals.PACKAGE)) {
			if (pkg instanceof org.eclipse.uml2.uml.Package
					&& "deployment_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
				deploymentView = (org.eclipse.uml2.uml.Package) pkg;
				break;
			}
		}
		List<Object> nodes = new ArrayList<>(
				EcoreUtil.getObjectsByType(deploymentView.getOwnedElements(), UMLPackage.Literals.NODE));

		UMLPlugin.getEPackageNsURIToProfileLocationMap().entrySet().forEach(System.out::println);

		
		umlIModel.getResource().getResourceSet().getURIConverter().getURIMap().entrySet()
				.forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

		for (Object n : nodes) {
			System.out.println(((Node) n).getAppliedStereotypes().toString());
			if (!((Node) n).getAppliedStereotypes().isEmpty()) {
				System.out.println(n.toString());
			}
		}
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
			for (Object comp : EcoreUtil.getObjectsByType(((Element) pkg).getOwnedElements(),
					UMLPackage.Literals.DEVICE)) {
				System.out.println(comp.toString());
				System.out.println("\t " + ((Device) comp).getAppliedStereotypes());
			}
		}
	}

}