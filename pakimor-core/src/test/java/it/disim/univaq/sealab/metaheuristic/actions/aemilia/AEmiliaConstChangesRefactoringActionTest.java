//package it.disim.univaq.sealab.metaheuristic.actions.aemilia;
//
//import java.io.IOException;
//import java.util.Random;
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
//import metamodel.mmaemilia.mmaemiliaPackage;
//import metamodel.mmaemilia.DataType.Integer;
//import metamodel.mmaemilia.DataType.Special;
//import metamodel.mmaemilia.DataType.SpecialType;
//import metamodel.mmaemilia.Expressions.IdentExpr;
//import metamodel.mmaemilia.Headers.ConstInit;
//
//public class AEmiliaConstChangesRefactoringActionTest {
//	private AEmiliaSpecification aemiliaModel;
//
//	private ConstInit sourceConstInit;
//
//	private double factorOfChange;
//
//	private ResourceSet resourceSet;
//	private Resource resource;
//
//	private static String AEMILIA_ABSOLUT_PATH = "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/BoA/BoA2.mmaemilia";
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
//		sourceConstInit = getRandomConstInit();
//		
//		if (sourceConstInit.getInitConstData() instanceof Integer && sourceConstInit.getName().contains("_size")) {
//			System.out.println("size");
//			int val = java.lang.Integer.parseInt(((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
//			((IdentExpr) sourceConstInit.getInitConstExpr()).setName(String.valueOf(((Double)(val * getFactorOfChange_impl())).intValue())	);
//		} else if (sourceConstInit.getInitConstData() instanceof Special
//				&& ((Special) sourceConstInit.getInitConstData()).getType() == SpecialType.RATE) {
//			System.out.println("rate");
//			double val = java.lang.Double.parseDouble(((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
//			((IdentExpr) sourceConstInit.getInitConstExpr()).setName(String.format("%.12f", ((val * getFactorOfChange_impl()))));
//		} else if (sourceConstInit.getInitConstData() instanceof Special
//				&& ((Special) sourceConstInit).getType() == SpecialType.WEIGHT) {
//			System.out.println("weight");
//			double val = java.lang.Double.parseDouble(((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
//			((IdentExpr) sourceConstInit.getInitConstExpr()).setName(String.format("%.12f", ((val * getFactorOfChange_impl()))));
//		} else {
//			System.out.println("Not supported type: " + sourceConstInit.getInitConstData().toString());
//		}
//	}
//	
//	
////	@Test
////	public void execute() {
////		sourceConstInit = getRandomConstInit();
////
////		if (sourceConstInit.getInitConstData() instanceof Integer && sourceConstInit.getName().contains("_size")) {
////			System.out.println("size");
////			int val = java.lang.Integer.parseInt(((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
////			((IdentExpr) sourceConstInit.getInitConstExpr()).setName(String.valueOf((val * getFactorOfChange())));
////		} else if (sourceConstInit.getInitConstData() instanceof Special && ((Special)sourceConstInit).getType() == SpecialType.RATE) {
////			System.out.println("rate");
////			double val = java.lang.Double.parseDouble(((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
////			((IdentExpr) sourceConstInit.getInitConstExpr()).setName(String.format("%.12f", ((val * getFactorOfChange_impl()))));
////		} else if (sourceConstInit.getInitConstData() instanceof Special && ((Special)sourceConstInit).getType() == SpecialType.WEIGHT) {
////			System.out.println("weight");
////			double val = java.lang.Double.parseDouble(((IdentExpr) sourceConstInit.getInitConstExpr()).getName());
//////			((IdentExpr) sourceConstInit.getInitConstExpr()).setName(String.valueOf((val * getFactorOfChange())));
////			((IdentExpr) sourceConstInit.getInitConstExpr()).setName(String.format("%.12f", ((val * getFactorOfChange_impl()))));
////			
////		} else {
////			System.out.println("Not supported type: "+sourceConstInit.getInitConstData().toString());
//////		}
////
////		try {
////			resource.save(null);
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
//	
//	public double getFactorOfChange_impl() {
//		double rangeMin = 0.5;
//		double rangeMax = 2;
//
//		Random r = new Random();
//		factorOfChange = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
//		return factorOfChange;
//	}
//
//	public ConstInit getRandomConstInit() {
//		EList<ConstInit> listOfConsts = aemiliaModel.getArchiTypeDecl().getHeader().getInitConst();
//		double rangeMin = 0;
//		double rangeMax = listOfConsts.size() - 1;
//
//		Random r = new Random();
//		factorOfChange = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
//
//		return listOfConsts.get((int) factorOfChange);
//	}
//
//	public void setSourceConstInit(ConstInit constInit) {
//		sourceConstInit = constInit;
//	}
//
//	public double getFactorOfChange() {
//		double rangeMin = 0.5;
//		double rangeMax = 2;
//
//		Random r = new Random();
//		factorOfChange = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
//		return factorOfChange;
//	}
//}
