package it.univaq.disim.sealab.metaheuristic.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class FileUtilsTest {
	
	private Path csvWithSolutions;
	
	@Before
	public void init() {
		csvWithSolutions = Paths.get("/mnt/store/research/easier/worse-to-better/easier_worse_20200617_2000/workload_5_Length_4_CloningWeight_1.5_MaxCloning_3_Worsen__solutions.csv");
	}
	
	@Test
	public void extractModelPathsTest() {
		FileUtils.extractModelPaths(csvWithSolutions, 3);
	}

}
