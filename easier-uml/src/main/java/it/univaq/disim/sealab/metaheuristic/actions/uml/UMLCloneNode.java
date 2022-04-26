/**
 * It moves a component from a node to another one
 */
package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

import it.univaq.disim.sealab.epsilon.EpsilonStandalone;
import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.metaheuristic.actions.RefactoringAction;
import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLRSolution;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Node;

public class UMLCloneNode implements RefactoringAction {

	private final static Path eolModulePath;

	private final static double BRF = 1.23;

	private String sourceModelPath;
	private double numOfChanges;
	private final String name;

	Map<String, Set<String>> targetElements = new HashMap<>();
	Map<String, Set<String>> createdElements = new HashMap<>();

	static {
		eolModulePath = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "..",
				"easier-refactoringLibrary", "easier-ref-operations", "clone_node.eol");
	}

	public UMLCloneNode(String modelPath, Map<String, Set<String>> availableElements) {
		this();

		sourceModelPath = modelPath;
		Set<String> availableNode = availableElements.get(UMLRSolution.SupportedType.NODE.toString());
		Set<String> targetElement = new HashSet<>();
		targetElement.add(availableNode.stream().skip(new Random().nextInt(availableNode.size())).findFirst().orElse(null));
		targetElements.put(UMLRSolution.SupportedType.NODE.toString(), targetElement);
		String clonedNode = targetElement.iterator().next() + "_" + generateHash();
		createdElements.put(UMLRSolution.SupportedType.NODE.toString(),
				Set.of(clonedNode));
	}

	public UMLCloneNode() {
		name = "UMLCloneNode";
	}

	public double computeArchitecturalChanges(Collection<?> modelContents) {

		Node targetObject =
				(Node) modelContents.stream().filter(Node.class::isInstance).
						map(NamedElement.class::cast).
						filter(ne -> ne.getName().equals(targetElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next())).findFirst().get();

		int cpSize = targetObject.getCommunicationPaths().size();

		int artSize = (int) targetObject.getDeployments().stream().mapToLong(d -> d.getDeployedArtifacts().size()).sum();

		return (cpSize + artSize);

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

			executor.setParameter(targetElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next(), "String", "targetNodeName");
			executor.setParameter(createdElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next(), "String", "clonedNodeName");

			executor.execute();
		} catch (EolRuntimeException e) {
			String message = String.format("Error in execution the eolmodule %s%n", eolModulePath);
			message += e.getMessage();
			throw new RuntimeException(message);
		}catch (URISyntaxException e) {
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
		return UMLRSolution.SupportedType.NODE.toString();
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
		return "Cloning --> " + targetElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next() + " " +
				"with -->  " + createdElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next();
	}
	
	public String toCSV() {
		return String.format("UMLCloneNode,%s,%s,",
				targetElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next(),
				createdElements.get(UMLRSolution.SupportedType.NODE.toString()).iterator().next());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getArchitecturalChanges() {
		return numOfChanges;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UMLCloneNode other = (UMLCloneNode) obj;
		if (sourceModelPath == null) {
			if (other.sourceModelPath != null)
				return false;
		}

		if (!targetElements.equals(other.targetElements))
			return false;

		for (String k : createdElements.keySet()) {
			if (!other.createdElements.get(k).iterator().next().equals(createdElements.get(k).iterator().next())) {
				return false;
			}
		}

		return true;
	}
}
