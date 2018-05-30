package it.univaq.disim.sealab.metaheuristic.actions.aemilia;
//package it.univaq.disim.sealab.metaheuristic.actions.aemilia;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.List;
//
//import org.apache.commons.lang3.RandomUtils;
//import org.eclipse.emf.common.util.BasicEList;
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.emf.common.util.URI;
//import org.eclipse.emf.ecore.resource.Resource;
//import org.eclipse.emf.ecore.resource.ResourceSet;
//import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
//import org.eclipse.emf.ecore.util.EcoreUtil;
//import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import it.univaq.disim.sealab.aemiliaMod2text.main.Transformation;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.managers.Manager;
//import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaManager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaManager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaStringManager;
//import it.univaq.disim.sealab.twoeagles_bridge.TwoEaglesBridge;
//import logicalSpecification.Refactoring;
//import mapmeasurestoindices.MapmeasurestoindicesPackage;
//import metamodel.mmaemilia.AEmiliaSpecification;
//import metamodel.mmaemilia.ArchiElemInstance;
//import metamodel.mmaemilia.ArchitecturalInteraction;
//import metamodel.mmaemilia.Attachment;
//import metamodel.mmaemilia.From;
//import metamodel.mmaemilia.InputInteraction;
//import metamodel.mmaemilia.OutputInteraction;
//import metamodel.mmaemilia.To;
//import metamodel.mmaemilia.mmaemiliaFactory;
//import metamodel.mmaemilia.mmaemiliaPackage;
//import metamodel.mmaemilia.Expressions.IdentExpr;
//
//@Ignore
//public class AEmiliaCloneAEIRefactoringActionTest {
//
//	private AEmiliaSpecification aemiliaModel;
//	private ArchiElemInstance sourceAEI, sourceAEI2, sourceAEI3;
//	private ArchiElemInstance clonedAEI, clonedAEI2, clonedAEI3;
//	private ResourceSet resourceSet;
//	private Resource resource;
//
//	private EList<OutputInteraction> listOfOutputInteractions;
//	private EList<InputInteraction> listOfInputInteractions;
//	private EList<Attachment> listOfNewAttachments;
//
//	private static String AEMILIA_ABSOLUT_PATH = "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/FTA/FTA.mmaemilia";
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
//	public void getRandomAEITest() {
//		ArchiElemInstance randomElem = (ArchiElemInstance) oclManager
//				.evaluateQuery(OclAemiliaStringManager.getInstance().getAEIListQuery()).iterator().next();
//		assert (randomElem != null);
//	}
//
//	@SuppressWarnings("unchecked")
//	public HashSet<ArchiElemInstance> getRandomAEI() {
//		return (HashSet<ArchiElemInstance>) oclManager
//				.evaluateQuery(OclAemiliaStringManager.getInstance().getAEIListQuery());
//	}
//
//	
//	@Test
//	@Ignore
//	public void execute1Action() {
//
//		// clonedAEI = (ArchiElemInstance) EcoreUtil.copy(sourceAEI);
//
//		// Random random = new Random();
//
//		for (ArchiElemInstance aei : aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl()) {
//			if (aei.getInstanceName().equals("WSN")) {
//				sourceAEI = aei;
//				assert (sourceAEI.equals(aei));
//				break;
//			}
//		}
//		
//		assert(sourceAEI != null);
//		clonedAEI = (ArchiElemInstance) EcoreUtil.copy(sourceAEI);
//		clonedAEI2 = (ArchiElemInstance) EcoreUtil.copy(sourceAEI2);
//		clonedAEI3 = (ArchiElemInstance) EcoreUtil.copy(sourceAEI3);
//		
//		if (sourceAEI.getSelector() != null) {
//			setSelector(sourceAEI,clonedAEI);
//		} else {
//			clonedAEI.setInstanceName(clonedAEI.getInstanceName() + "_cloned_" + RandomUtils.nextInt(0, 100));
//		}
//		
//		
//		clonedAEI.setTypeOf(sourceAEI.getTypeOf());
//		aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl().add(clonedAEI);
//
//		cloningOutputInteractions(sourceAEI,clonedAEI);
//		cloningInputInteractions(sourceAEI,clonedAEI);
//		cloningArchitecturalInteraction(sourceAEI,clonedAEI);
//
//		String destionationPath = "/Users/peo12/Downloads/test.mmaemilia";
//		save(destionationPath);
//		assert (new File(destionationPath).exists());
//		applyTransformation();
//		invokeSolver();
//
//	}
//	
//	
//	@Test
//	@Ignore
//	public void execute2Action() {
//
//		for (ArchiElemInstance aei : aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl()) {
//			if (aei.getInstanceName().equals("WSN")) {
//				sourceAEI = aei;
//				assert (sourceAEI.equals(aei));
//				break;
//			}
//		}
//		
//		for (ArchiElemInstance aei : aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl()) {
//			if (aei.getInstanceName().equals("CHN")) {
//				sourceAEI2 = aei;
//				assert (sourceAEI2.equals(aei));
//				break;
//			}
//		}
//		
//		assert(sourceAEI != null);
//		clonedAEI = (ArchiElemInstance) EcoreUtil.copy(sourceAEI);
//		clonedAEI2 = (ArchiElemInstance) EcoreUtil.copy(sourceAEI2);
//		clonedAEI3 = (ArchiElemInstance) EcoreUtil.copy(sourceAEI3);
//		
//		if (sourceAEI.getSelector() != null) {
//			setSelector(sourceAEI,clonedAEI);
//		} else {
//			clonedAEI.setInstanceName(clonedAEI.getInstanceName() + "_cloned_" + RandomUtils.nextInt(0, 100));
//		}
//		
//		
//		clonedAEI.setTypeOf(sourceAEI.getTypeOf());
//		aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl().add(clonedAEI);
//
//		cloningOutputInteractions(sourceAEI,clonedAEI);
//		cloningInputInteractions(sourceAEI,clonedAEI);
//		cloningArchitecturalInteraction(sourceAEI,clonedAEI);
//
//		
//		if (sourceAEI2.getSelector() != null) {
//			setSelector(sourceAEI2,clonedAEI2);
//		} else {
//			clonedAEI2.setInstanceName(clonedAEI2.getInstanceName() + "_cloned_" + RandomUtils.nextInt(0, 100));
//		}
//
//		clonedAEI2.setTypeOf(sourceAEI2.getTypeOf());
//		aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl().add(clonedAEI2);
//
//		cloningOutputInteractions(sourceAEI2,clonedAEI2);
//		cloningInputInteractions(sourceAEI2,clonedAEI2);
//		cloningArchitecturalInteraction(sourceAEI2,clonedAEI2);
//		
//		String destionationPath = "/Users/peo12/Downloads/test.mmaemilia";
//		save(destionationPath);
//		assert (new File(destionationPath).exists());
//		applyTransformation();
//		invokeSolver();
//
//	}
//	
//	
//	
//	@Test
//	@Ignore
//	public void execute3Action() {
//
//		// clonedAEI = (ArchiElemInstance) EcoreUtil.copy(sourceAEI);
//
//		// Random random = new Random();
//
//		for (ArchiElemInstance aei : aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl()) {
//			if (aei.getInstanceName().equals("WSN")) {
//				sourceAEI = aei;
//				assert (sourceAEI.equals(aei));
//				break;
//			}
//		}
//		
//		for (ArchiElemInstance aei : aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl()) {
//			if (aei.getInstanceName().equals("CHN")) {
//				sourceAEI2 = aei;
//				assert (sourceAEI2.equals(aei));
//				break;
//			}
//		}
//		
//		for (ArchiElemInstance aei : aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl()) {
//			if (aei.getInstanceName().equals("CHN")) {
//				sourceAEI3 = aei;
//				assert (sourceAEI3.equals(aei));
//				break;
//			}
//		}
//		
//		assert(sourceAEI != null);
//		clonedAEI = (ArchiElemInstance) EcoreUtil.copy(sourceAEI);
//		clonedAEI2 = (ArchiElemInstance) EcoreUtil.copy(sourceAEI2);
//		clonedAEI3 = (ArchiElemInstance) EcoreUtil.copy(sourceAEI3);
//		
//		if (sourceAEI.getSelector() != null) {
//			setSelector(sourceAEI,clonedAEI);
//		} else {
//			clonedAEI.setInstanceName(clonedAEI.getInstanceName() + "_cloned_" + RandomUtils.nextInt(0, 100));
//		}
//		
//		
//		clonedAEI.setTypeOf(sourceAEI.getTypeOf());
//		aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl().add(clonedAEI);
//
//		cloningOutputInteractions(sourceAEI,clonedAEI);
//		cloningInputInteractions(sourceAEI,clonedAEI);
//		cloningArchitecturalInteraction(sourceAEI,clonedAEI);
//
//		
//		if (sourceAEI2.getSelector() != null) {
//			setSelector(sourceAEI2,clonedAEI2);
//		} else {
//			clonedAEI2.setInstanceName(clonedAEI2.getInstanceName() + "_cloned_" + RandomUtils.nextInt(0, 100));
//		}
//
//		clonedAEI2.setTypeOf(sourceAEI2.getTypeOf());
//		aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl().add(clonedAEI2);
//
//		cloningOutputInteractions(sourceAEI2,clonedAEI2);
//		cloningInputInteractions(sourceAEI2,clonedAEI2);
//		cloningArchitecturalInteraction(sourceAEI2,clonedAEI2);
//		
//		if (sourceAEI3.getSelector() != null) {
//			setSelector(sourceAEI3,clonedAEI3);
//		} else {
//			clonedAEI3.setInstanceName(clonedAEI2.getInstanceName() + "_cloned_" + RandomUtils.nextInt(0, 100));
//		}
//
//		clonedAEI3.setTypeOf(sourceAEI3.getTypeOf());
//		aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl().add(clonedAEI3);
//
//		cloningOutputInteractions(sourceAEI3,clonedAEI3);
//		cloningInputInteractions(sourceAEI3,clonedAEI3);
//		cloningArchitecturalInteraction(sourceAEI3,clonedAEI3);
//		
//		String destionationPath = "/Users/peo12/Downloads/test.mmaemilia";
//		save(destionationPath);
//		assert (new File(destionationPath).exists());
//		applyTransformation();
//		invokeSolver();
//
//	}
//
//	public void invokeSolver() {
//		Controller controller = Manager.getInstance(null).getController();
//
//		String mmaemiliaFolderPath = "/Users/peo12/Downloads";
//		String aemFilePath = mmaemiliaFolderPath + "/" + aemiliaModel.getArchiTypeDecl().getAtName() + "_result"
//				+ ".aem";
//
//		String rewFilePath = "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/FTA/fta_result.rew";
//		String outputFilePath = mmaemiliaFolderPath + "/" + aemiliaModel.getArchiTypeDecl().getAtName() + "_result";
//		Controller.logger_.info("outputFilePath: " + outputFilePath);
//
//		assert(new File(aemFilePath).exists());
//		assert(new File(rewFilePath).exists());
//		assert(!new File(outputFilePath).exists());
//		
//		TwoEaglesBridge ttbridge = new TwoEaglesBridge();
//		ttbridge.gaussianEliminationSRBMC(aemFilePath, rewFilePath, outputFilePath);
//		
//		if (!new File(aemFilePath + ".val").exists()) {
//			controller.checkTwoTowersOutput(outputFilePath);
//		}
//	}
//
//	public void applyTransformation() {
//		Controller controller = Manager.getInstance(null).getController();
//
//		String mmaemiliaFolderPath = "/Users/peo12/Downloads";
//		String mmaemiliaFilePath = mmaemiliaFolderPath + "/test.mmaemilia";
//
//		Transformation.GenerateAEMTransformation(mmaemiliaFilePath, mmaemiliaFolderPath);
//		Controller.logger_.info("mmamelia to aem completed");
//	}
//
//	public void save(String destionationPath) {
//		ResourceSet resourceSet = new ResourceSetImpl();
//		Resource res = resourceSet.createResource(Manager.getInstance(null).string2FileUri(destionationPath));
//		res.getContents().add(EcoreUtil.copy(aemiliaModel));
//
//		try {
//			res.save(null);
//		} catch (IOException ioe) {
//			System.err.println(ioe.getMessage());
//		}
//	}
//
//	private void cloningArchitecturalInteraction(ArchiElemInstance src, ArchiElemInstance clone) {
//		EList<ArchitecturalInteraction> listOfArchitecturalInteraction = aemiliaModel.getArchiTypeDecl()
//				.getAtDeclaration().getAiDecl();
//		EList<ArchitecturalInteraction> listOfClonedArchitecturalInteraction = new BasicEList<>();
//
//		for (ArchitecturalInteraction architecturalInteraction : listOfArchitecturalInteraction) {
//			if (architecturalInteraction.getFromInstance().getInstanceName().equals(src.getInstanceName())) {
//				ArchitecturalInteraction ai = EcoreUtil.copy(architecturalInteraction);
//				ai.setFromInstance(clone);
//				listOfClonedArchitecturalInteraction.add(ai);
//			}
//		}
//		if (!listOfClonedArchitecturalInteraction.isEmpty()) {
//			listOfArchitecturalInteraction.addAll(listOfClonedArchitecturalInteraction);
//			// aemiliaModel.getArchiTypeDecl()
//			// .getAtDeclaration().getAiDecl().addAll(listOfClonedArchitecturalInteraction);
//			assert(aemiliaModel.getArchiTypeDecl()
//					.getAtDeclaration().getAiDecl().containsAll(listOfClonedArchitecturalInteraction));
//		}
//	
//	}
//
//	public void cloningInputInteractions(ArchiElemInstance src, ArchiElemInstance clone) {
//		EList<Attachment> listOfAtts = getAttachmentsOfAEI(src);
//		EList<InputInteraction> listOFII = src.getTypeOf().getIiDecl();
//
//		for (InputInteraction in : listOFII) {
//			for (Attachment att : listOfAtts) {
//				// if (listOfOI.contains(att.getStart().getIsOutput())) {
//				if (in.getIntName().equals(att.getEnd().getIsInput().getIntName())) {
//					att.getStart().getIsOutput().setType(metamodel.mmaemilia.InteractionType.OR);
//
//					Attachment newAtt = mmaemiliaFactory.eINSTANCE.createAttachment();
//
//					From from = mmaemiliaFactory.eINSTANCE.createFrom();
//					from.setFromInstance(att.getStart().getFromInstance());
//					from.setIsOutput(att.getStart().getIsOutput());
//
//					To to = mmaemiliaFactory.eINSTANCE.createTo();
//					to.setToInstance(clone);
//					to.setIsInput(att.getEnd().getIsInput());
//
//					newAtt.setStart(from);
//					newAtt.setEnd(to);
//
//					aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAttDecl().add(newAtt);
//
//				}
//			}
//		}
//	}
//
//	private EList<Attachment> getAttachmentsOfAEI(ArchiElemInstance aei) {
//		EList<Attachment> listOfAttachment = aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAttDecl();
//		EList<Attachment> listOfAtts = new BasicEList<Attachment>();
//		for (Attachment att : listOfAttachment) {
//			if (att.getEnd().getToInstance().getInstanceName().equals(aei.getInstanceName())
//					|| att.getStart().getFromInstance().getInstanceName().equals(aei.getInstanceName())) {
//				listOfAtts.add(att);
//			}
//			// if (att.getEnd().getToInstance().equals(aei) ||
//			// att.getStart().getFromInstance().equals(aei)) {
//			// listOfAtts.add(att);
//			// }
//		}
//		return listOfAtts;
//	}
//
//	public void cloningOutputInteractions(ArchiElemInstance src, ArchiElemInstance clone) {
//		EList<Attachment> listOfAtts = getAttachmentsOfAEI(src);
//		EList<OutputInteraction> listOfOI = src.getTypeOf().getOiDecl();
//		// EList<Attachment> clonedAtts = new BasicEList<Attachment>();
//		for (OutputInteraction out : listOfOI) {
//			for (Attachment att : listOfAtts) {
//				// if (listOfOI.contains(att.getStart().getIsOutput())) {
//				if (out.getIntName().equals(att.getStart().getIsOutput().getIntName())) {
//					att.getEnd().getIsInput().setType(metamodel.mmaemilia.InteractionType.OR);
//
//					Attachment newAtt = mmaemiliaFactory.eINSTANCE.createAttachment();
//
//					From from = mmaemiliaFactory.eINSTANCE.createFrom();
//					from.setFromInstance(clone);
//					from.setIsOutput(att.getStart().getIsOutput());
//
//					To to = mmaemiliaFactory.eINSTANCE.createTo();
//					to.setToInstance(att.getEnd().getToInstance());
//					to.setIsInput(att.getEnd().getIsInput());
//
//					newAtt.setStart(from);
//					newAtt.setEnd(to);
//
//					aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAttDecl().add(newAtt);
//
//				}
//			}
//		}
//	}
//
//	private void setSelector(ArchiElemInstance src, ArchiElemInstance clone) {
//		List<ArchiElemInstance> listOfAEIes = aemiliaModel.getArchiTypeDecl().getAtDeclaration().getAeiDecl();
//		int selector = 0;
//		for (ArchiElemInstance aei : listOfAEIes) {
//			if (aei.getTypeOf() == src.getTypeOf()) {
//				int aeiSelector = Integer.valueOf(((IdentExpr) aei.getSelector()).getName());
//				if (aeiSelector > selector) {
//					selector = aeiSelector;
//				}
//			}
//		}
//		((IdentExpr) clone.getSelector()).setName(String.valueOf(selector + 1));
//	}
//
//}
