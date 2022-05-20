/**
 * It moves a component from a node to another one
 */
package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import it.univaq.disim.sealab.metaheuristic.utils.EasierException;
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

public class UMLMvComponentToNN implements RefactoringAction {

    private final static Path eolModulePath;

    private final static double BFR = 1.23;
    private double numOfChanges;

    private boolean isIndependent = true;


    private String name;


    static {
        eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
                "easier-refactoringLibrary", "easier-ref-operations", "mv_comp_nn.eol");
    }

    public UMLMvComponentToNN() {
        name = "mcnn";
    }

    Map<String, Set<String>> targetElements = new HashMap<>();
    Map<String, Set<String>> createdElements = new HashMap<>();

    public UMLMvComponentToNN(Map<String, Set<String>> availableElements, Map<String,
            Set<String>> initialElements) {
        this();

        Set<String> availableComponents = availableElements.get(UMLRSolution.SupportedType.COMPONENT.toString());
        Set<String> targetElement = new HashSet<>();
        targetElement.add(availableComponents.stream().skip(new Random().nextInt(availableComponents.size())).findFirst().orElse(null));
        targetElements.put(UMLRSolution.SupportedType.COMPONENT.toString(), targetElement);
        setIndependent(initialElements);
        Set<String> createdNodeElements = new HashSet<>();
        createdNodeElements.add("New-Node_" + generateHash());
        createdElements.put(UMLRSolution.SupportedType.NODE.toString(), createdNodeElements);
    }

    public double getNumOfChanges() {
        return numOfChanges;
    }

    public double computeArchitecturalChanges(Collection<?> modelContents) throws EasierException {

        Component compToMove =
                (Component) modelContents.stream().filter(Component.class::isInstance)
                        .map(NamedElement.class::cast).filter(ne -> ne.getName()
                                .equals(targetElements.get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next())).findFirst().orElse(null);

        if(compToMove == null)
            throw  new EasierException("Error when computing the architectural changes of "+this.getName());


        int intUsage = compToMove.getUsedInterfaces().size();
        int intReal = compToMove.getInterfaceRealizations().size();
        int ops = compToMove.getOperations().size();

        return (intUsage + intReal + ops);
    }

    @Override
    public void setIndependent(Map<String, Set<String>> initialElements) {
        Set<String> candidateTargetValues =
                this.getTargetElements().values().stream().flatMap(Set::stream).collect(Collectors.toSet());
        Set<String> flattenSourceElement =
                initialElements.values().stream().flatMap(Set::stream).collect(Collectors.toSet());

        if (!flattenSourceElement.containsAll(candidateTargetValues))
            isIndependent = false;
    }

    @Override
    public boolean isIndependent() {
        return isIndependent;
    }

    private String generateHash() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        return new Random().ints(leftLimit, rightLimit + 1).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    @Override
    public void execute(EasierUmlModel contextModel) throws EasierException {
        EOLStandalone executor = new EOLStandalone();

        try {
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
            String message = String.format("Error in execution the eolmodule %s%n ", eolModulePath);
            message += e.getMessage();
            throw new EasierException(message);
        }

        executor.clearMemory();
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
