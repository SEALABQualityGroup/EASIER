package it.disim.univaq.sealab.metaheuristic.managers.aemilia;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import it.disim.univaq.sealab.metaheuristic.managers.Manager;
import it.disim.univaq.sealab.metaheuristic.managers.ocl.OclManager;
import it.disim.univaq.sealab.metaheuristic.managers.ocl.aemilia.OclAemiliaManager;
import it.univaq.from_aemilia_to_qn_plug_in.handlers.GeneratoreModelloAemilia;
import mapmeasurestoindices.MapmeasurestoindicesPackage;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.Attachment;
import metamodel.mmaemilia.ElemType;
import metamodel.mmaemilia.InputInteraction;
import metamodel.mmaemilia.OutputInteraction;
import metamodel.mmaemilia.mmaemiliaPackage;
import metamodel.mmaemilia.Behavior.BehaviorFactory;
import metamodel.mmaemilia.Behavior.ChoiceProcess;
import metamodel.mmaemilia.Behavior.ProcessTerm;

public class AemiliaManagerTest {

	private AEmiliaSpecification targetModel;
	private ResourceSet resourceSet;
	private Resource resource;

	private String AEMILIA_ABSOLUT_PATH = "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/FTA/FTA.mmaemilia";

	@Before
	public void init() {
		packageRegistering();
		
		AEMILIA_ABSOLUT_PATH = getClass().getResource("/models/AemiliaModels/choiceExample/modCompAemilia.aem").getPath();
		
		//assertEquals("/Users/peo12/git/sealab/easier/easier-core/src/test/resources/models/AemiliaModels/choiceExample/modCompAemilia.aem", AEMILIA_ABSOLUT_PATH);
		
		createAemiliaEcoreFile(AEMILIA_ABSOLUT_PATH);
		
		String aemiliaEcore = getClass().getResource("/models/AemiliaModels/choiceExample/modCompAemilia.mmaemilia").getPath();
		
		resource = getResource(aemiliaEcore);
		targetModel = (AEmiliaSpecification) EcoreUtil.getObjectByType(resource.getContents(),
				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
	}
	
	private void createAemiliaEcoreFile(String sourceAemPath) {
		GeneratoreModelloAemilia genModel = new GeneratoreModelloAemilia();
		FileInputStream aemFileInputStream;
		try {
			aemFileInputStream = new FileInputStream(new File(sourceAemPath));
			genModel.execute_ase(aemFileInputStream, null, "/Users/peo12/git/sealab/easier/easier-core/src/test/resources/models/AemiliaModels/choiceExample/modCompAemilia.mmaemilia");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void packageRegistering() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("rewmapping", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("mmaemilia", new XMIResourceFactoryImpl());
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(MapmeasurestoindicesPackage.eINSTANCE.getNsURI(),
				MapmeasurestoindicesPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(mmaemiliaPackage.eINSTANCE.getNsURI(), mmaemiliaPackage.eINSTANCE);

	}
	
	private Resource getResource(String ameliaAbsolutePath) {
		URI uri = URI.createFileURI(ameliaAbsolutePath);
		resource = resourceSet.getResource(uri, true);
		return resource;
	}
	
	@Test
	public void addAvailabilityChoice() {
		
		for(ElemType aet : targetModel.getArchiTypeDecl().getAetDeclaration().getEtDeclaration()) {
			
			ChoiceProcess availabilityChoice = BehaviorFactory.eINSTANCE.createChoiceProcess();
			
			ProcessTerm pt = BehaviorFactory.eINSTANCE.createProcessTerm();
			
			availabilityChoice.getProcesses().add(aet.getBehaviorDecl().getEquations().get(0).getPt());
			
		}
	}
	
}
