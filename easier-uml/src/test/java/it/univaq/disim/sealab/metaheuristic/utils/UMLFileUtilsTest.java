package it.univaq.disim.sealab.metaheuristic.utils;

import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRProblem;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;

public class UMLFileUtilsTest {

	UMLRSolution sol;
	UMLRProblem<RSolution<?>> p;

	@Before
	public void setup() {
		int allowedFailures = 100;
		int desired_length = 4;
		int populationSize = 4;

		String modelpath = getClass().getResource("/models/simplified-cocome/cocome.uml").getFile();
		p = new UMLRProblem<>(Paths.get(modelpath), desired_length, allowedFailures, populationSize);
		sol = (UMLRSolution) p.createSolution();

		p.evaluate(sol);
	}

}
