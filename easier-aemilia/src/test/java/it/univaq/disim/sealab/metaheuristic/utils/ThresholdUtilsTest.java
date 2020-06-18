package it.univaq.disim.sealab.metaheuristic.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.beust.jcommander.JCommander;

import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaController;
import it.univaq.disim.sealab.metaheuristic.managers.aemilia.AemiliaMetamodelManager;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import it.univaq.disim.sealab.metaheuristic.utils.ThresholdUtils;

public class ThresholdUtilsTest {
	
	private AemiliaController controller;
	
	@Before
	public void init() {
		
		String[] args = {"@/home/peo/git/sealab/easier/easier-core/config.ini"};
		
		JCommander jc = new JCommander();

		Configurator config = new Configurator();
		jc.addObject(config);
		jc.parse(args);

		controller = new AemiliaController(config);
	}

	@Test
	public void testSavingEVLFile() {
 
		
		Path mmaemiliaFilePath = Paths.get("/home/peo/git/sealab/easier/easier-aemilia/src/main/resources/models/FTA/new_rate3/model.mmaemilia");
		Path valFilePath = Paths.get("/home/peo/git/sealab/easier/easier-aemilia/src/main/resources/models/FTA/new_rate3/model.val");
		
		ThresholdUtils.uptodateSingleValueThresholds(Paths.get("/tmp/easier/"), mmaemiliaFilePath, valFilePath,
				(AemiliaMetamodelManager) controller.getManager().getMetamodelManager(), controller);
	}

}
