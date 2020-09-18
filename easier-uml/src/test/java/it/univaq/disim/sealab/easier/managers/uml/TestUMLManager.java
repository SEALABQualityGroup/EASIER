package it.univaq.disim.sealab.easier.managers.uml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Device;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UMLPlugin;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;
import org.junit.Before;
import org.junit.Test;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;

public class TestUMLManager {

	private Model umlModel;
	private UMLMetamodelManager umlManager;
	private Controller umlController;

	private EmfModel umlIModel;

	ResourceSet RESOURCE_SET;
	private final ExtensibleURIConverterImpl converter = new ExtensibleURIConverterImpl();

	private final URI ulProfile = URI.createURI("DAM.profile.uml");
	private final URI upProfile = URI.createFileURI(
			"/home/peo/git/sealab/easier/easier-uml/src/test/resources/models/test_dam/MARTE.MARTE_AnalysisModel.GQAM.profile.uml");

	private final URI ulNewModel = URI.createURI("NewModel.uml");
	private final URI upNewModel = URI
			.createFileURI("/home/peo/git/sealab/easier/easier-uml/src/test/resources/models/test_dam/test-dam.uml");

	private Path pathToModel;

	@Before
	public void setUp() throws URISyntaxException, EolModelLoadingException {
//		umlController = new UMLController();
//		umlManager = new UMLMetamodelManager(umlController);
//		umlManager.packageRegistering();
		pathToModel = Paths
				.get("/home/peo/git/sealab/easier/easier-uml/src/test/resources/models/test_dam/test-dam.uml");
//		umlManager.loadModel(pathToModel);
//		umlModel = umlManager.getModel();

		umlIModel = EpsilonStandalone.createUmlModel("UML", pathToModel, UMLPackage.eNS_URI, true, false);

		RESOURCE_SET = new ResourceSetImpl();
		UMLResourcesUtil.init(RESOURCE_SET);

		converter.getURIMap().put(ulProfile, upProfile);
		converter.getURIMap().put(ulNewModel, upNewModel);

	}
	
	
	//https://www.eclipse.org/forums/index.php/t/1102293/
	@Test
	public void foundInInternet() {
		Model model = (Model) RESOURCE_SET.getResource(converter.normalize(ulNewModel), true).getContents().get(0);
		Profile profile = (Profile) RESOURCE_SET.getResource(converter.normalize(ulProfile), true).getContents().get(0);
//		profile.allOwnedElements().forEach(System.out::println);
		
//		model.applyProfile(profile);

		org.eclipse.uml2.uml.Component myClass = (org.eclipse.uml2.uml.Component) model.getOwnedType("TEST");
		
		System.out.println(myClass.getName());
		myClass.getAppliedStereotypes().forEach(System.out::println);
		
		
//		profile.getPackagedElements().forEach(p -> p.allOwnedElements().forEach(System.out::println));
		
		Stereotype s = profile.getOwnedStereotype("GaExecHost");
		
		
		Component cp = (Component) UMLFactory.eINSTANCE.create(UMLPackage.Literals.COMPONENT);
		cp.setName("CAZZO");
		
		model.getPackagedElements().add(cp);
		cp.applyStereotype(s);
		
		System.out.println(cp.getName());
		cp.getAppliedStereotypes().forEach(System.out::println);
		
		
		cp.setValue(s, "Name", "Class with stereotype");

		try {
			Resource newModel = RESOURCE_SET.createResource(converter.normalize(ulNewModel));
			newModel.getContents().add(model);
			newModel.save(null);
		} catch (Exception e) {
		}
	}

	@Test
	public void shouldReturnGaExecHostStereotype() {

//		org.eclipse.uml2.uml.Package deploymentView = null;
//		for (Object pkg : EcoreUtil.getObjectsByType(umlIModel.allContents(), UMLPackage.Literals.PACKAGE)) {
//			if (pkg instanceof org.eclipse.uml2.uml.Package
//					&& "deployment_view".equals(((org.eclipse.uml2.uml.Package) pkg).getName())) {
//				deploymentView = (org.eclipse.uml2.uml.Package) pkg;
//				break;
//			}
//		}
//		
		List<Object> cmps = new ArrayList<>(
				EcoreUtil.getObjectsByType(umlIModel.allContents(), UMLPackage.Literals.COMPONENT));

		cmps.stream().map(Component.class::cast).forEach(cp -> cp.getAppliedStereotypes().forEach(System.out::println));

//		UMLPlugin.getEPackageNsURIToProfileLocationMap().entrySet().forEach(System.out::println);
//
//		umlIModel.getResource().getResourceSet().getURIConverter().getURIMap().entrySet()
//				.forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

		for (Object n : cmps) {
			((Component) n).getAppliedStereotypes().forEach(System.out::println);
			if (!((Component) n).getAppliedStereotypes().isEmpty()) {
				System.out.println(n.toString());
			}
		}
	}

	@Test
	public void shallReturnTheProfile() {

	}

	@Test
	public void shallApplyStereotypes() {

		List<Object> cmps = new ArrayList<>(
				EcoreUtil.getObjectsByType(umlIModel.allContents(), UMLPackage.Literals.COMPONENT));

		cmps.stream().map(Component.class::cast).forEach(cp -> cp.getAppliedStereotypes().forEach(System.out::println));

		Stereotype st = ((Component) cmps.get(0)).getAppliedStereotypes().get(0);
		cmps.stream().map(Component.class::cast).filter(cp -> !cp.getAppliedStereotypes().isEmpty()).findFirst()
				.orElse(null).applyStereotype(st);
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