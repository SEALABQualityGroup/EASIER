package it.univaq.disim.sealab.actions.uml;

import static org.junit.Assert.*;
import org.junit.Test;

import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLMvOperationToNCToNN;

public class MvOperationToNCToNnTest {
	
	
	@Test
	public void getEolModule() {
	
		assertTrue(UMLMvOperationToNCToNN.getEolModulePath().toFile().exists());
		assertNotNull("eol module not loaded!!", UMLMvOperationToNCToNN.getEolModulePath());
	}

}
