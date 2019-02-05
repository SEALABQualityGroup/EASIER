package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metamodel.mmaemilia.ArchitecturalInteraction;

public class SourceModel {

	private final static String PREFIX = "model";

	private int numPAs = -1;

	private Path sourceFolder;
	
	
	private Map<String, List<ArchitecturalInteraction>> sourceModelPAs = new HashMap<>();

	public SourceModel(final Path srcF) {
		this.sourceFolder = srcF;
	}
	
	public String getName() { return sourceFolder.getFileName().toString(); }

	public Path getModel() {
		return Paths.get(sourceFolder.toString(), PREFIX, ".mmaemilia");
	}

	public Path getAem() {
		return Paths.get(sourceFolder.toString(), PREFIX, ".aem");
	}

	public Path getRew() {
		return Paths.get(sourceFolder.toString(), PREFIX, ".rew");
	}

	public int getNumberOfPerfAp() {
		if(numPAs == -1) {
			numPAs = 0;
			for (String key : sourceModelPAs.keySet()) {
				numPAs += sourceModelPAs.get(key).size();
			}
		}
		return numPAs;
	}

	public void setNumberOfPerfAp(int _numPAs) {
		this.numPAs = _numPAs;
	}

	public Map<String, List<ArchitecturalInteraction>> getSourceModelPAs() {
		return sourceModelPAs;
	}

	public void setSourceModelPAs(Map<String, List<ArchitecturalInteraction>> sourceModelPAs) {
		this.sourceModelPAs = sourceModelPAs;
	}
	
	public Path getSourceFolder() { return sourceFolder; }

}
