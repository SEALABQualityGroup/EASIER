//package it.disim.univaq.sealab.metaheuristic.actions.aemilia;
//
//import java.io.IOException;
//
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.emf.common.util.URI;
//import org.eclipse.emf.ecore.resource.Resource;
//import org.eclipse.emf.ecore.resource.ResourceSet;
//import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
//import org.eclipse.emf.ecore.util.EcoreUtil;
//import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
//import org.junit.Before;
//import org.junit.Test;
//
//import it.disim.univaq.sealab.metaheuristic.managers.Manager;
//import it.disim.univaq.sealab.metaheuristic.managers.aemilia.AemiliaManager;
//import it.disim.univaq.sealab.metaheuristic.managers.ocl.OclManager;
//import it.disim.univaq.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaManager;
//import it.disim.univaq.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaStringManager;
//import mapmeasurestoindices.MapmeasurestoindicesPackage;
//import metamodel.mmaemilia.AEmiliaSpecification;
//import metamodel.mmaemilia.ArchiElemInstance;
//import metamodel.mmaemilia.Attachment;
//import metamodel.mmaemilia.InputInteraction;
//import metamodel.mmaemilia.OutputInteraction;
//import metamodel.mmaemilia.mmaemiliaPackage;
//import metamodel.mmaemilia.DataType.Integer;
//import metamodel.mmaemilia.Expressions.IdentExpr;
//import metamodel.mmaemilia.Headers.Const;
//import metamodel.mmaemilia.Headers.ConstInit;
//
//public class AEmiliaMoveInputInteractionActionTest {
//
//	private AEmiliaSpecification aemiliaModel;
//	
//	private Const sourceConst;
//	
//	private ResourceSet resourceSet;
//	private Resource resource;
//
//	private InputInteraction targetInteraction;
//
//	private static String AEMILIA_ABSOLUT_PATH = "/Users/daniele/git/geneticAlgorithm/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/BoA/BoA2.mmaemilia";
//
//	private OclManager oclManager;
//	private Manager manager;
//
//	@Before
//	public void init() {
//		// getOclManager().inizialize(getResourceSet());
//		oclManager = new OclAemiliaManager();
//		manager = Manager.getInstance(new AemiliaManager());
//		packageRegistering();
//		resource = getResource(AEMILIA_ABSOLUT_PATH);
//		aemiliaModel = (AEmiliaSpecification) EcoreUtil.getObjectByType(resource.getContents(),
//				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
//		// setREFACTORED_MODEL_BASE_PATH("/src/main/resources/models/refactored/");
//		manager.setModel(aemiliaModel);
//	}
//
//	public void packageRegistering() {
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("rewmapping", new XMIResourceFactoryImpl());
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("mmaemilia", new XMIResourceFactoryImpl());
//		resourceSet = new ResourceSetImpl();
//		resourceSet.getPackageRegistry().put(MapmeasurestoindicesPackage.eINSTANCE.getNsURI(),
//				MapmeasurestoindicesPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(mmaemiliaPackage.eINSTANCE.getNsURI(), mmaemiliaPackage.eINSTANCE);
//	}
//
//	public Resource getResource(String ameliaAbsolutePath) {
//		URI uri = URI.createFileURI(ameliaAbsolutePath);
//		resource = resourceSet.getResource(uri, true);
//		return resource;
//	}
//
//	public ArchiElemInstance getRandomAEI() {
//		ArchiElemInstance randomElem = (ArchiElemInstance) oclManager
//				.evaluateQuery(OclAemiliaStringManager.getInstance().getAEIListQuery()).iterator().next();
//		return randomElem;
//	}
//
//	@Test
//	public void execute() {
//	}
//}
