package it.univaq.disim.sealab.epsilon;


import static org.junit.Assert.assertNotNull;

import java.nio.file.Paths;
import java.util.Random;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.UMLFactory;
import org.junit.Before;
import org.junit.Test;

import it.univaq.disim.sealab.epsilon.refactoring.EOLStandalone;

public class EOLStandaloneTest {

	private EOLStandalone eolStandalone;
	private EmfModel createEmfModelByURI;

	@Before
	public void init() {
		eolStandalone = new EOLStandalone();
		eolStandalone.setModel(Paths.get(getClass().getResource("/e-shopper/cococciashop.uml").getFile()))
				.setSource(Paths.get(getClass().getResource("/refactoring-lib/mv_comp_nn.eol").getFile()));

		createEmfModelByURI = (EmfModel) eolStandalone.getModel();
	}

	@Test
	public void testPrintln() throws Exception {

//		EOLStandalone eolStandalone = new EOLStandalone();
//		eolStandalone.setModel(Paths.get(getClass().getResource("/e-shopper/cococciashop.uml").getFile()))
//				.setSource(Paths.get(getClass().getResource("/refactoring-lib/mv_op_nc_nn.eol").getFile()));

//		EmfModel createEmfModelByURI = (EmfModel) eolStandalone.getModel();

		eolStandalone
				.setParameter((EObject) createEmfModelByURI.getAllOfType("Operation").toArray()[0], "UML!Operation", "self")
				.execute();

	}
	
	private String generateHash() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
//	    Random random = new Random();
	 
	    return new Random().ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	}

	@Test
	public void getTargetElementFromModule() throws Exception {

		//Extracts the Products component.
		Component self = createEmfModelByURI.getAllOfType("Component").stream().map(Component.class::cast). 
				filter(c -> "products".equals(c.getName())).findAny().orElse(null);
		
		assertNotNull(self); //simple safety check!
		
		Node target = UMLFactory.eINSTANCE.createNode();
		target.setName("NEW_Node_" + generateHash());
		//Adds the new node to the deployment package
		createEmfModelByURI.getAllOfType("Node").stream().map(Node.class::cast).findFirst().orElse(null).getPackage().getOwnedElements().add(target);
//				createEmfModelByURI.getAllOfType("Node").stream().map(Node.class::cast).findFirst().orElse(null).getPackage();
//				map(Component.class::cast). 
//				filter(c -> "products".equals(c.getName())).findAny()
//				.orElse(null);
		

		eolStandalone.setParameter(self, "UML!Component", "self").execute();
		eolStandalone.setParameter(target, "UML!Node", "target").execute();

		System.out.println(eolStandalone.getTarget()); //get the target obj from the eolModule

		assertNotNull(eolStandalone.getTarget());

	}

}
