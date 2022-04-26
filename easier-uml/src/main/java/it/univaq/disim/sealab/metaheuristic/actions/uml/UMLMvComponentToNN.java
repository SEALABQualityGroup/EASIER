/**
 * It moves a component from a node to another one
 */
package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.UMLFactory;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import logicalSpecification.actions.UML.UMLPackage;

public class UMLMvComponentToNN implements RefactoringAction {

    private final static Path eolModulePath;

    private final static double BFR = 1.23;
    private double numOfChanges;


    private String name;

    private String sourceModelPath;

    static {
        eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
                "easier-refactoringLibrary", "easier-ref-operations", "mv_comp_nn.eol");
    }

    public UMLMvComponentToNN() {
        name = "Move_Component_New_Node";
    }

    Map<String, Set<String>> targetElements = new HashMap<>();
    Map<String, Set<String>> createdElements = new HashMap<>();

    public UMLMvComponentToNN(String sourceModel, Map<String, Set<String>> availableElements) {

        sourceModelPath = sourceModel;
        Set<String> availableComponents = availableElements.get(UMLRSolution.SupportedType.COMPONENT.toString());
        Set<String> targetElement = new HashSet<>();
        targetElement.add(availableComponents.stream().skip(new Random().nextInt(availableComponents.size())).findFirst().orElse(null));
        targetElements.put(UMLRSolution.SupportedType.COMPONENT.toString(), targetElement);

        Set<String> createdNodeElements = new HashSet<>();
        createdNodeElements.add("New-Node_" + generateHash());
        createdElements.put(UMLRSolution.SupportedType.NODE.toString(), createdNodeElements);
    }

    public double getNumOfChanges() {
        return numOfChanges;
    }

    public double computeArchitecturalChanges(Collection<?> modelContents) {

        Component compToMove =
                (Component) modelContents.stream().filter(Component.class::isInstance)
                        .map(NamedElement.class::cast).filter(ne -> ne.getName()
                                .equals(targetElements.get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next())).findFirst().get();


        int intUsage = compToMove.getUsedInterfaces().size();
        int intReal = compToMove.getInterfaceRealizations().size();
        int ops = compToMove.getOperations().size();

        return (intUsage + intReal + ops);
    }

    private String generateHash() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        return new Random().ints(leftLimit, rightLimit + 1).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    @Override
    public void execute() throws RuntimeException {
        EOLStandalone executor = new EOLStandalone();

        EasierUmlModel contextModel;
        try {
            contextModel = EpsilonStandalone.createUmlModel(sourceModelPath);
            contextModel.setStoredOnDisposal(true);

            executor.setModel(contextModel);
            executor.setSource(eolModulePath);

            // fills variable within the eol module
            executor.setParameter(targetElements.get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next(),
                    "String",
                    "targetComponentName");
            executor.setParameter(createdElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next(),
                    "String",
                    "newNodeName");
            executor.execute();
        } catch (EolRuntimeException e) {
            String message = String.format("Error in execution the eolmodule %s%n", eolModulePath);
            message += e.getMessage();
            throw new RuntimeException(message);
        } catch (URISyntaxException e) {
            String message = String.format("ERROR while reading the model \t %s %n", sourceModelPath);
            throw new RuntimeException(message);
        }


        executor.clearMemory();
        executor = null;
    }

    @Override
    public RefactoringAction clone() {
        try {
            return (RefactoringAction) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getTargetType() {
        return UMLRSolution.SupportedType.COMPONENT.toString();
    }

    @Override
    public Map<String, Set<String>> getTargetElements() {
        return targetElements;
    }

    @Override
    public Map<String, Set<String>> getCreatedElements() {
        return createdElements;
    }

    @Override
    public double getArchitecturalChanges() {
        return numOfChanges;
    }

    @Override
    public String toString() {
        return "Moving --> " + targetElements.get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next() +
                " to --> " + createdElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next();
    }

    public String toCSV() {
        return String.format("Move_Component_New_Node,%s,%s,",
                targetElements.get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next(),
                createdElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UMLMvComponentToNN other = (UMLMvComponentToNN) obj;
        if (sourceModelPath == null && other.sourceModelPath != null)
            return false;

        if (!targetElements.equals(other.targetElements))
            return false;

        for (String k : createdElements.keySet()) {
            for (String elemName : createdElements.get(k)) {
                if (!other.createdElements.get(k).contains(elemName))
                    return false;
            }
        }
        return true;
    }

}
