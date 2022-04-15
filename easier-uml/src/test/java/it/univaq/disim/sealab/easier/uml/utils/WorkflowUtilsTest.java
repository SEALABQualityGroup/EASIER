package it.univaq.disim.sealab.easier.uml.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.Assert.*;

public class WorkflowUtilsTest {

    private Path modelPath;

    @Before
    public void setUp() throws Exception {
        modelPath = Paths.get(getClass().getResource("/models/simplified-cocome/cocome.uml").getFile());
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(modelPath.getParent().resolve("output.xml"));
        Files.deleteIfExists(modelPath.getParent().resolve("output.xml.bak"));
        Files.deleteIfExists(modelPath.getParent().resolve("output.lqxo"));
        Files.deleteIfExists(modelPath.getParent().resolve("output.out"));
    }

    @Test
    public void applyTransformation() {
        new WorkflowUtils().applyTransformation(modelPath);
        Path lqnModelPath = modelPath.getParent().resolve("output.xml");
        assertTrue(Files.exists(lqnModelPath));

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(lqnModelPath.toFile()));
            assertNotEquals(String.format("Expected not empty %s file. ", lqnModelPath), br.readLine(), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test(expected = Test.None.class)
    public void invokeSolver() throws Exception {
        new WorkflowUtils().applyTransformation(modelPath);
        Path solverOutcome = modelPath.getParent().resolve("output.lqxo");
        new WorkflowUtils().invokeSolver(modelPath.getParent());
        assertTrue(Files.exists(solverOutcome)); // check whether the file output.lqxo exists
        try (BufferedReader br = new BufferedReader(new FileReader(solverOutcome.toFile()))) {
            // check whether the file output.lqxo is not empty
            assertNotEquals(String.format("Expected not empty %s file. ", solverOutcome), br.readLine(), null);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Test(expected = Test.None.class)
    public void backAnnotation() throws Exception {
        new WorkflowUtils().applyTransformation(modelPath);
        new WorkflowUtils().invokeSolver(modelPath.getParent());
        new WorkflowUtils().backAnnotation(modelPath);
    }
}