//package it.univaq.disim.sealab.actions.uml;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
//import org.eclipse.emf.common.util.URI;
//import org.eclipse.emf.ecore.EPackage;
//import org.eclipse.emf.ecore.EcorePackage;
//import org.eclipse.emf.ecore.resource.ResourceSet;
//import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
//import org.eclipse.emf.ecore.util.EcoreUtil;
//import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
//import org.eclipse.papyrus.MARTE.MARTEPackage;
//import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.GQAM.GQAMPackage;
//import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.PAM.PAMPackage;
//import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.SAM.SAMPackage;
//import org.eclipse.uml2.uml.Model;
//import org.eclipse.uml2.uml.Node;
//import org.eclipse.uml2.uml.Stereotype;
//import org.eclipse.uml2.uml.UMLPackage;
//import org.eclipse.uml2.uml.UMLPlugin;
//import org.eclipse.uml2.uml.resource.UMLResource;
//import org.junit.Before;
//import org.junit.Test;
//import org.uma.jmetal.util.pseudorandom.JMetalRandom;
//
//import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLDeleteNodeRefactoringAction;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLMetamodelManager;
//
//public class TestDeleteNodeAction {
//
//	private Model model;
//
//	private UMLDeleteNodeRefactoringAction action;
//
//	private Map<URI, URI> uriMap;
//
//	private ResourceSet resourceSet;
//
//	@Before
//	public void setUp() throws Exception {
//		resourceSet = new ResourceSetImpl();
//		uriMap = resourceSet.getURIConverter().getURIMap();
//		model = getModel(Paths.get(getClass().getResource("/models/smartFloor/smartFloor2.uml").toURI()));
//
//	}
//
//	/**
//	 * UMLDeleteNodeRefactoringAction PRECONDITIONS & POSTCONDITIONS PRE: - The Node
//	 * must not contain deployed Components. POST: - The Node must not exist inside
//	 * the UML model.
//	 */
//
//	@Test
//	public void shouldRemoveNodeFromTheModel() {
//
//		this.action = new UMLDeleteNodeRefactoringAction();
//		this.action.setModel(model);
//
//		action.setUmlNodeToDel(getNode());
//		action.setCost(3);
//		action.setNumOfChanges(1);
//		this.action.execute();
//
//		assert (!getPackage().getOwnedElements().contains(action.getUmlNodeToDel()));
//
//	}
//
//	private org.eclipse.uml2.uml.Package getPackage() {
//
////		Model model = (Model) this.solution.getModel();
//		for (Object o : EcoreUtil.getObjectsByType(model.getOwnedElements(), UMLPackage.Literals.PACKAGE)) {
//			if (((org.eclipse.uml2.uml.Package) o).getName().equals(UMLMetamodelManager.DEPLOYMENT_VIEW))
//				return (org.eclipse.uml2.uml.Package) o;
//		}
//		return null;
//	}
//
//	public Node getNode() {
//		org.eclipse.uml2.uml.Package pkg = getPackage();
//
//		Collection<Object> listOfNodes = EcoreUtil.getObjectsByType(pkg.getOwnedElements(), UMLPackage.Literals.DEVICE);
//		List<Node> finalListOfNodes = new ArrayList<>();
//		for (Object obj : listOfNodes) {
//			Node ele = (Node) obj;
//			for (Stereotype st : ele.getAppliedStereotypes())
//				if (ele.isStereotypeApplied(st)) {
//					finalListOfNodes.add(ele);
//				}
//		}
//
//		if (finalListOfNodes.isEmpty()) {
//			new Throwable("Please check your MODEL! It seems not ready to be used");
//		}
//
//		return finalListOfNodes.get(JMetalRandom.getInstance().nextInt(0, finalListOfNodes.size() - 1));
//	}
//
//	public void packageRegistering() {
//
//		initGlobalRegistry();
//		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(Ecore2XMLPackage.eNS_URI, Ecore2XMLPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(MARTEPackage.eNS_URI, MARTEPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(GQAMPackage.eNS_URI, GQAMPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(SAMPackage.eNS_URI, SAMPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(PAMPackage.eNS_URI, PAMPackage.eINSTANCE);
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
//				UMLResource.Factory.INSTANCE);
//
//		/*
//		 * Needed to link the MARTE jar plugin to the system. It is requested in case of
//		 * a standalone application
//		 */
//		//TODO check if needed
//		String marteJarString = "jar:" + getClass()
//				.getResource("/libs/org.eclipse.papyrus.marte.static.profile_1.2.0.201803031506.jar").toString() + "!/";
//
//		URI marteJar = URI.createURI(marteJarString);
//		uriMap.put(URI.createURI("pathmap://Papyrus_PROFILES/"), marteJar.appendSegment("resources").appendSegment(""));
//
//		/*
//		 * Must add the pathmap of the Parent package to the UMLPlugin'
//		 * getEPackageNsURIToProfileLocationMap() The AnalysisModel package has been
//		 * added as follows
//		 */
//		String PROFILES_PATHMAP = "pathmap://Papyrus_PROFILES/"; //$NON-NLS-1$
//		String MARTE_PROFILE_URI = PROFILES_PATHMAP + "MARTE.profile.uml#_u8y4wAPMEdyuUt-4qHuVvQ";
//		URI marteProfileURI = URI.createURI(MARTE_PROFILE_URI);
//		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(MARTEPackage.eNS_URI, marteProfileURI);
//
//	}
//
//	public void initGlobalRegistry() {
//		EPackage.Registry.INSTANCE.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(Ecore2XMLPackage.eNS_URI, Ecore2XMLPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(MARTEPackage.eNS_URI, MARTEPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(GQAMPackage.eNS_URI, GQAMPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(SAMPackage.eNS_URI, SAMPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(PAMPackage.eNS_URI, PAMPackage.eINSTANCE);
//	}
//
//	public Model getModel(Path sourcePath) {
//		packageRegistering();
//
//		UMLResource umlResource = (UMLResource) resourceSet.getResource(URI.createFileURI(sourcePath.toString()), true);
//
//		return (Model) EcoreUtil.getObjectByType(umlResource.getContents(), UMLPackage.Literals.MODEL);
//	}
//
//}
