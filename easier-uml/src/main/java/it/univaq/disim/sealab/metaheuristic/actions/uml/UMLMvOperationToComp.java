package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import it.univaq.disim.sealab.metaheuristic.utils.EasierException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.eclipse.ocl.ecore.CollectionLiteralExp;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.UMLPackage;

public class UMLMvOperationToComp implements RefactoringAction {

    private final static String eolModulePath;

    private final static double BFR = 1.23;

    private String sourceModelPath;
    private double numOfChanges;
    private String name;

    private long msgs;

    private boolean isIndependent = true;

    Map<String, Set<String>> targetElements = new HashMap<>();
    Map<String, Set<String>> createdElements = new HashMap<>();

    static {
        eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
                "easier-refactoringLibrary", "easier-ref-operations", "mv_op_comp.eol").toString();
    }

    public UMLMvOperationToComp() {
        this.name = "moc";
    }

    public UMLMvOperationToComp(Map<String, Set<String>> availableElements, Map<String, Set<String>> initialElements) {
        this();
        Set<String> availableOperations = availableElements.get(UMLRSolution.SupportedType.OPERATION.toString());
        String targetOperationName = availableOperations.stream().skip(new Random().nextInt(availableOperations.size())).findFirst().orElse(null);

        targetElements.put(UMLRSolution.SupportedType.OPERATION.toString(),
                new HashSet<>() {{
                    add(targetOperationName);
                }});

        setIndependent(initialElements);
        Set<String> availableComponents = availableElements.get(UMLRSolution.SupportedType.COMPONENT.toString());
        targetElements.put(UMLRSolution.SupportedType.COMPONENT.toString(),
                new HashSet<>() {{
                    add(availableComponents.stream().skip(new Random().nextInt(availableComponents.size())).findFirst().orElse(null));
                }});

    }

    public double computeArchitecturalChanges(Collection<?> modelContents) {
        String opToMove = targetElements.get(UMLRSolution.SupportedType.OPERATION.toString()).iterator().next();

        long msgs = modelContents.stream().filter(Message.class::isInstance)
                .map(Message.class::cast).filter(msg -> !msg.getMessageSort().toString().equals("reply"))
                .filter(m -> opToMove.equals(m.getSignature().getName())).count();

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

    @Override
    public void execute(EasierUmlModel contextModel) throws EasierException {

        EOLStandalone executor = new EOLStandalone();

        try {
//            EasierUmlModel contextModel = EpsilonStandalone.createUmlModel(sourceModelPath);
//            contextModel.setStoredOnDisposal(true);

            executor.setModel(contextModel);
            executor.setSource(Paths.get(eolModulePath));
            executor.setParameter(getTargetElements().get(UMLRSolution.SupportedType.OPERATION.toString()).iterator().next()
                    , "String",
                    "targetOperationName");
            executor.setParameter(getTargetElements().get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next()
                    , "String",
                    "targetComponentName");

            executor.execute();
        } catch (EolRuntimeException e) {
            String message = String.format("Error in execution the eolmodule %s%n", eolModulePath);
//            message += String.format("No Node called \t %s %n", targetObject.getName());
            message += e.getMessage();
            throw new RuntimeException(message);
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


    @Override
    public String toString() {
        return "Move Operation --> " + targetElements.get(UMLRSolution.SupportedType.OPERATION.toString()).iterator().next() + " to" +
                " Component " +
                "-->  " + targetElements.get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next();
    }

    @Override
    public double getArchitecturalChanges() {
        return numOfChanges;
    }


    public String toCSV() {
        return String.format("Move_Operation_Component,%s,%s,",
                targetElements.get(UMLRSolution.SupportedType.OPERATION.toString()).iterator().next(),
                targetElements.get(UMLRSolution.SupportedType.COMPONENT.toString()).iterator().next());
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
        UMLMvOperationToComp other = (UMLMvOperationToComp) obj;
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
