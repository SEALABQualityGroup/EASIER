package it.univaq.disim.sealab.metaheuristic;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class LauncherTest {
	
	
	@Before
	public void setUp() {
	
	}
	
	@Test
	public void invokeSolverTest() {
		Path modelPath = Paths.get(getClass().getResource("/models/simplified-cocome/cocome.uml").getFile());
		Launcher.applyTransformation(modelPath);
		Launcher.invokeSolver(modelPath);  
	}
}
