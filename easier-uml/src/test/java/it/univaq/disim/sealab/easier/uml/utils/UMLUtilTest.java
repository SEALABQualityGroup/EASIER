package it.univaq.disim.sealab.easier.uml.utils;

import it.univaq.disim.sealab.easier.utils.uml.UMLUtil;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.UMLPackage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UMLUtilTest {

    String modelPath;

    @BeforeEach
    void setUp() {
        modelPath = getClass().getResource("/models/simplified-cocome/cocome.uml").getPath();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetElementsInPackageShouldReturnMessage() {
        List<Object> elementsInPackage = UMLUtil.getElementsInPackage(modelPath, UMLPackage.Literals.MESSAGE);
        assertTrue(elementsInPackage.stream().allMatch(Message.class::isInstance));
    }
}