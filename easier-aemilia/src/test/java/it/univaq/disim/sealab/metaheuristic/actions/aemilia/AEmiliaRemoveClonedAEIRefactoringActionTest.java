package it.univaq.disim.sealab.metaheuristic.actions.aemilia;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Test;

import it.univaq.disim.sealab.epsilon.EpsilonHelper;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaMetamodelManager;
import metamodel.mmaemilia.AEmiliaSpecification;

public class AEmiliaRemoveClonedAEIRefactoringActionTest {

	private AemiliaMetamodelManager mmManger;
	private AEmiliaRemoveClonedAEIRefactoringAction action;
	private AEmiliaSpecification model;

	@Before
	public void init() {

		mmManger = new AemiliaMetamodelManager(null);
		mmManger.packageRegistering();
		model = mmManger.getModel(Paths.get(getClass().getResource("/FTA_cloned/model.mmaemilia").getPath()));
		
		System.out.println(((AEmiliaSpecification)model).eResource().getURI().toFileString());

	}

	@Test
	public void checkRemovingAction() throws IOException {

		action = new AEmiliaRemoveClonedAEIRefactoringAction(model);
		action.execute();
		((AEmiliaSpecification)action.getModel()).eResource().setURI(URI.createFileURI("/tmp/easier/removed_clone.mmaemilia"));
		((AEmiliaSpecification)action.getModel()).eResource().save(null);
		

	}
	
	@Test
	public void checkGeneratedClonedFile() throws IOException {
		EpsilonHelper.generateAemFile(Paths.get("/tmp/easier/removed_clone.mmaemilia"), Paths.get("/tmp/easier/removed_clone.aem"));
		
		try {
			Process process = new ProcessBuilder("/home/peo/git/sealab/easier/easier-twoTowersSolver/bin/TTKernel",
					"-a", "/tmp/easier/removed_clone.aem", "/tmp/easier/removed_clone_results").start();
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		String expectedOutput = "0 errors, 0 warnings.    [0 sec]";
		String output = new String(Files.readAllBytes(Paths.get("/tmp/easier/removed_clone_results")), StandardCharsets.UTF_8);
		
		assertEquals(expectedOutput, output);
	}
	
	public void gaussianEliminationSRBMC(Path aemFilePath, Path rewFilePath, Path outputFilePath) {
		
	}

}
