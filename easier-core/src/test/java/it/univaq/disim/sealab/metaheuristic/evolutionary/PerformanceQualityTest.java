package it.univaq.disim.sealab.metaheuristic.evolutionary;
//package it.univaq.disim.sealab.metaheuristic.evolutionary;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.eclipse.emf.common.util.TreeIterator;
//import org.eclipse.emf.common.util.URI;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.ecore.resource.Resource;
//import org.eclipse.emf.ecore.resource.ResourceSet;
//import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
//import org.eclipse.emf.ecore.util.EcoreUtil;
//import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
//import org.eclipse.ocl.ParserException;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import it.univaq.disim.sealab.metaheuristic.managers.Manager;
//import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaManager;
//import it.univaq.disim.sealab.ttep.val.ValParser;
//import it.univaq.disim.sealab.ttep.val.classes.MeasureValue;
//import it.univaq.disim.sealab.ttep.val.classes.ValSpec;
//import mapmeasurestoindices.IndexType;
//import mapmeasurestoindices.MapmeasurestoindicesPackage;
//import metamodel.mmaemilia.AEmiliaSpecification;
//import metamodel.mmaemilia.ArchitecturalInteraction;
//import metamodel.mmaemilia.mmaemiliaPackage;
//import utilities.MetodiVari;
//
//public class PerformanceQualityTest {
//
//	private ResourceSet resourceSet;
//	private Resource resource;
//	private ValSpec originalValSpec;
//
//	private String sourceValFile = "";
//	private String refactoredValFile;
//	private ValSpec valSpecToBeEvaluated;
//	private Map<MeasureValue, Float> qualityMap;
//
//	private Manager manager;
//	private AemiliaManager aemiliaManager;
//	private OclManager oclManager;
//	private AEmiliaSpecification aemiliaModel;
//
//	private static String AEMILIA_ABSOLUT_PATH = "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/BoA/BoA2.mmaemilia";
//
//	@Before
//	public void setUp() throws Exception {
//
//		// performanceAvaluatorInit();
//
//		performanceAntipatternsEvaluatorInit();
//	}
//
//	private void performanceAntipatternsEvaluatorInit() {
//		packageRegistering();
//
//		manager = Manager.getInstance(new AemiliaManager());
//		aemiliaManager = (AemiliaManager) manager.getMetamodelManager();
//		oclManager = aemiliaManager.getOclManager();
//		resource = getResource(AEMILIA_ABSOLUT_PATH);
//		aemiliaModel = (AEmiliaSpecification) EcoreUtil.getObjectByType(resource.getContents(),
//				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
//		// setREFACTORED_MODEL_BASE_PATH("/src/main/resources/models/refactored/");
//		manager.setModel(aemiliaModel);
//	}
//
//	private void performanceAvaluatorInit() {
//		sourceValFile = "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/BoA/BoA.aem.val";
//		refactoredValFile = "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/refactored/33/boa_result.aem.val";
//		originalValSpec = getValSpec(sourceValFile);
//		valSpecToBeEvaluated = getValSpec(refactoredValFile);
//		qualityMap = new HashMap<>();
//	}
//
//	public void packageRegistering() {
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("rewmapping", new XMIResourceFactoryImpl());
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("mmaemilia", new XMIResourceFactoryImpl());
//		resourceSet = new ResourceSetImpl();
//		resourceSet.getPackageRegistry().put(MapmeasurestoindicesPackage.eINSTANCE.getNsURI(),
//				MapmeasurestoindicesPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(mmaemiliaPackage.eINSTANCE.getNsURI(), mmaemiliaPackage.eINSTANCE);
//
//	}
//
//	public Resource getResource(String ameliaAbsolutePath) {
//		URI uri = URI.createFileURI(ameliaAbsolutePath);
//		resource = resourceSet.getResource(uri, true);
//		return resource;
//	}
//
//	@Test
//	@Ignore
//	public void performanceQualityTest() {
//		for (MeasureValue mes : originalValSpec.getMeasures()) {
//			for (MeasureValue refMes : valSpecToBeEvaluated.getMeasures()) {
//				if (mes.getMeasure().equals(refMes.getMeasure())) {
//					calculateQuality(mes, refMes);
//				}
//
//			}
//		}
//
//		System.out.println(calcuateWholeQuality());
//	}
//
//	private Float calcuateWholeQuality() {
//		float overallQuality = 0;
//		for (float val : qualityMap.values()) {
//			overallQuality += val;
//		}
//		return overallQuality;
//	}
//
//	private void calculateQuality(MeasureValue sourceMes, MeasureValue refMes) {
//		Float quality = (refMes.getValue() - sourceMes.getValue()) / sourceMes.getValue();
//		if (sourceMes.getMeasure().contains(IndexType.UTILIZATION.getLiteral()) && quality < 0) {
//			quality *= -1;
//		}
//		qualityMap.put(sourceMes, quality);
//	}
//
//	private ValSpec getValSpec(String valFilePath) {
//		FileInputStream valFileInputStream = null;
//		try {
//			valFileInputStream = new FileInputStream(valFilePath);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		}
//		ValParser valParser = new ValParser(valFileInputStream);
//
//		ValSpec valSpec = null;
//		try {
//			valSpec = valParser.ValSpec();
//		} catch (it.univaq.disim.sealab.ttep.val.ParseException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//		return valSpec;
//	}
//
//	@Test
//	public void performanceAntipatternEvaluator() {
//		int numOfPAs = 0;
//
//		List<Object> contextualArchiInteractions = new ArrayList<Object>();
//
//		TreeIterator<EObject> eAllContents = aemiliaManager.getModel().eAllContents();
//
//		while (eAllContents.hasNext()) {
//			EObject next = eAllContents.next();
//			if (next instanceof ArchitecturalInteraction) { // && next.eContainer() instanceof ElemType) {
//				contextualArchiInteractions.add(next);
//			}
//		}
//
//		((OclAemiliaManager) oclManager).countPAsFromOCLFromFile("src/main/resources/ocl/detectionSingleValuePA.ocl",
//				contextualArchiInteractions);
//
//		System.out.println("num of PAs : " + numOfPAs);
//	}
//
//}
