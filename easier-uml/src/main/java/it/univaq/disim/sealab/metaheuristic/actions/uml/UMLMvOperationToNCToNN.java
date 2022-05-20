package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import it.univaq.disim.sealab.metaheuristic.utils.EasierException;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;

public class UMLMvOperationToNCToNN implements RefactoringAction {

    private final static String eolModulePath;

    private final static double BRF = 1.80;

    private String name;
    private double numOfChanges;

    private long msgs;

    private boolean isIndependent = true;

    Map<String, Set<String>> targetElements = new HashMap<>();
    Map<String, Set<String>> createdElements = new HashMap<>();

    static {
        eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
                "easier-refactoringLibrary", "easier-ref-operations", "mv_op_nc_nn.eol").toString();
    }

    public UMLMvOperationToNCToNN(){
        name = "moncnn";
    }

    public UMLMvOperationToNCToNN(Map<String, Set<String>> availableElements, Map<String,
            Set<String>> initialElements) {
        this();

        Set<String> availableOperations = availableElements.get(UMLRSolution.SupportedType.OPERATION.toString());

        Set<String> targetElement = new HashSet<>();
        targetElement.add(availableOperations.stream().skip(new Random().nextInt(availableOperations.size())).findFirst().orElse(null));
        targetElements.put(UMLRSolution.SupportedType.OPERATION.toString(), targetElement);

        setIndependent(initialElements);
        Set<String> createdElements = new HashSet<>();
        createdElements.add("New-Node_" + generateHash());
        this.createdElements.put(UMLRSolution.SupportedType.NODE.toString(), Set.copyOf(createdElements));
        createdElements.clear();
        createdElements.add("New-Component_" + generateHash());
        this.createdElements.put(UMLRSolution.SupportedType.COMPONENT.toString(), Set.copyOf(createdElements));

    }

    public double getNumOfChanges() {
        return numOfChanges;
    }

    public double computeArchitecturalChanges(Collection<?> modelContents) {
        String opToMove = targetElements.get(UMLRSolution.SupportedType.OPERATION.toString()).iterator().next();
		long msgs = modelContents.stream().filter(Message.class::isInstance)
				.map(Message.class::cast).filter(m -> !m.getMessageSort().toString().equals("reply")).filter(m -> opToMove.equals(m.getSignature().getName())).count();

        return msgs;
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
    public void execute(EasierUmlModel contextModel ) throws RuntimeException, EasierException {

        EOLStandalone executor = new EOLStandalone();

        try {
//            EasierUmlModel contextModel = EpsilonStandalone.createUmlModel(sourceModelPath);
//            contextModel.setStoredOnDisposal(true);

            executor.setModel(contextModel);
            executor.setSource(Paths.get(eolModulePath));

            executor.setParameter(targetElements.get(UMLRSolution.SupportedType.OPERATION.toString()).iterator().next(),
                    "String",
                    "targetOperationName");
            executor.setParameter(createdElements.get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next(),
                    "String",
                    "newComponentName");
            executor.setParameter(createdElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next(),
                    "String",
                    "newNodeName");

            executor.execute();
        } catch (EolRuntimeException e) {
            String message = String.format("Error in execution the eolmodule %s%n", eolModulePath);
//            message += String.format("No Node called \t %s %n", targetObject.getName());
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
        return UMLRSolution.SupportedType.OPERATION.toString();
    }

    @Override
    public Map<String, Set<String>> getTargetElements() {
        return targetElements;
    }

    @Override
    public Map<String, Set<String>> getCreatedElements() {
        return createdElements;
    }

    public String toString() {
        return "Move Operation --> " + targetElements.get(UMLRSolution.SupportedType.OPERATION.toString()).iterator().next() + " " +
                "to " +
                "New Component --> " + createdElements.get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next()
                + " deployed to a New Node -->" + createdElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next();
    }

    @Override
    public double getArchitecturalChanges() {
        return numOfChanges;
    }

    public String toCSV() {
        return String.format("Move_Operation_New_Component_New_Node,%s,%s,%s",
                targetElements.get(UMLRSolution.SupportedType.OPERATION.toString()).iterator().next(),
                createdElements.get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next(),
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
        UMLMvOperationToNCToNN other = (UMLMvOperationToNCToNN) obj;

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
