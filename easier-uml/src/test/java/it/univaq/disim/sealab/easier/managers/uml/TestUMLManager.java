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
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;

public class TestUMLManager {

	private final ExtensibleURIConverterImpl converter = new ExtensibleURIConverterImpl();
	private final URI ulNewModel = URI.createURI("NewModel.uml");
	private final URI upNewModel = URI
			.createFileURI("/home/peo/git/sealab/easier/easier-uml/src/test/resources/models/test_dam/test-dam.uml");
	
	private EmfModel umlIModel;

	private Path pathToModel;

	@Before
	public void setUp() throws URISyntaxException, EolModelLoadingException {
		pathToModel = Paths
				.get("/home/peo/git/sealab/easier/easier-uml/src/test/resources/models/test_dam/test-dam.uml");

		umlIModel = EpsilonStandalone.createUmlModel("UML", pathToModel, UMLPackage.eNS_URI, true, false);
	}

	
	/**
	 * This test is able to load an EmfModel and then read and apply a stereotype to an Element.
	 * https://www.eclipse.org/forums/index.php/t/1102293/
	 */
	@Test
	public void foundInInternet() {
		//Load the model from the EmfModel
		Model model = (Model) umlIModel.allContents().toArray()[0];
		org.eclipse.uml2.uml.Component myClass = (org.eclipse.uml2.uml.Component) model.getOwnedType("TEST");

		System.out.println(myClass.getName());
		myClass.getAppliedStereotypes().forEach(System.out::println);

		//Loads the GQAM from the EmfModel
		Profile profile = ((EasierUmlModel) umlIModel).getGQAM();
		Stereotype s = profile.getOwnedStereotype("GaExecHost");

		Component cp = (Component) UMLFactory.eINSTANCE.create(UMLPackage.Literals.COMPONENT);
		cp.setName("NewComponent");
		model.getPackagedElements().add(cp);
		
		//Applies the stereotype to the new component
		cp.applyStereotype(s);

		System.out.println(cp.getName());
		cp.getAppliedStereotypes().forEach(System.out::println);

		//TODO check the followin code
		cp.setValue(s, "Name", "Class with stereotype");

		try {
			//Should save the model to a new file
			Resource newModel = umlIModel.getResource().getResourceSet().createResource(converter.normalize(ulNewModel));
			newModel.getContents().add(model);
			newModel.save(null);
		} catch (Exception e) {
		}
	}

	@Test
	public void shouldReturnGaExecHostStereotype() {

		List<Object> cmps = new ArrayList<>(
				EcoreUtil.getObjectsByType(umlIModel.allContents(), UMLPackage.Literals.COMPONENT));

		cmps.stream().map(Component.class::cast).forEach(cp -> cp.getAppliedStereotypes().forEach(System.out::println));

		for (Object n : cmps) {
			((Component) n).getAppliedStereotypes().forEach(System.out::println);
			if (!((Component) n).getAppliedStereotypes().isEmpty()) {
				System.out.println(n.toString());
			}
		}
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
}