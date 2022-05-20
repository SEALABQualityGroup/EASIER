package it.univaq.disim.sealab.metaheuristic.actions;

import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import it.univaq.disim.sealab.metaheuristic.utils.EasierException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface RefactoringAction extends Cloneable {

	public RefactoringAction clone();

	public String getTargetType();

	public Map<String, Set<String>> getTargetElements();

	public Map<String, Set<String>> getCreatedElements();
	public String toCSV();

	public  String getName();

	public void execute(EasierUmlModel model) throws EasierException;

	public double getArchitecturalChanges();

	public double computeArchitecturalChanges(Collection<?> modelContents) throws EasierException;

	public void setIndependent(Map<String, Set<String>> initialElements);

	public boolean isIndependent();

}
