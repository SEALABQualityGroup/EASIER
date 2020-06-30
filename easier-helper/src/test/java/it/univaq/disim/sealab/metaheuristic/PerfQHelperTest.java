package it.univaq.disim.sealab.metaheuristic;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class PerfQHelperTest {

	private PerfQHelper perfQHelper;

	@Before
	public void init() {
		perfQHelper = new PerfQHelper();
	}

	@Test
	public void testPerfQ() {

		Path paretoFile = Paths.get(
				"/mnt/store/research/easier/worse-to-better/easier_better_20200618_1131/referenceFront/worse_Length_4_CloningWeight_1.5_MaxCloning_3.rf");
		Path sourceValPath = Paths.get("/home/peo/git/sealab/easier/easier-aemilia/src/main/resources/models/FTA/workload_5/model.val");
		
		perfQHelper.extractSoutionFromPareto(sourceValPath, paretoFile);

	}

}
