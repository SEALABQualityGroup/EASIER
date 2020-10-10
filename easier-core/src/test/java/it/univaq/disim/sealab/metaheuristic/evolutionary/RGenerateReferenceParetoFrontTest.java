package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.beans.BeanProperty;

import org.junit.Before;
import org.junit.Test;

import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util.RGenerateReferenceParetoFront;

public class RGenerateReferenceParetoFrontTest {

	private RGenerateReferenceParetoFront _claz;
	
	@Before
	public void init() {
		_claz = new RGenerateReferenceParetoFront();
	}
	
	
	@Test
	public void generateRPointSolutionList() {
		
		_claz.generateRPointSolutionList("/tmp/easier-output/referenceFront/Exp/data/NSGAII/agv_Length_4_CloningWeight_1.5_MaxCloning_3/VAR0.tsv");
		
	}
	
	
}
