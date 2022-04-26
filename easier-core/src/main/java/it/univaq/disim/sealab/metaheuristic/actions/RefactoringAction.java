package it.univaq.disim.sealab.metaheuristic.actions;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface RefactoringAction extends Cloneable {

//	@Deprecated
//	public RefactoringAction clone(RSolution solution);
	public RefactoringAction clone();

	public String getTargetType();

	public Map<String, Set<String>> getTargetElements();

	public Map<String, Set<String>> getCreatedElements();
	public String toCSV();

	public  String getName();

	public void execute() throws RuntimeException;

	public double getArchitecturalChanges();

	public double computeArchitecturalChanges(Collection<?> modelContents);

}
