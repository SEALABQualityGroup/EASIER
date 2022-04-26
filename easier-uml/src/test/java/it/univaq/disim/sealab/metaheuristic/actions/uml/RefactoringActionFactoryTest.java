package it.univaq.disim.sealab.metaheuristic.actions;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.univaq.disim.sealab.metaheuristic.actions.uml.RefactoringActionFactory;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;

public class RefactoringActionFactoryTest {

	UMLRSolution sol;
	
	@Before
	public void init() {
		int allowedFailures = 100;
		int desired_length = 4;
		int populationSize = 4;
		
		String modelpath = getClass().getResource("/models/model/automatedGuidedVehicle.uml").getFile();
		UMLRProblem<RSolution<?>> p = new UMLRProblem<>(Paths.get(modelpath), desired_length, allowedFailures, populationSize);
		p.setName("agv__test");
		sol = (UMLRSolution) p.createSolution();
	}
	
	@Test
	public void getRandomActionTest() {
		
		
		Assert.assertNotNull(RefactoringActionFactory.getRandomAction(sol));
		
	}
	
}
