package it.univaq.disim.sealab.metaheuristic.evolutionary;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.uma.jmetal.problem.impl.AbstractGenericProblem;

import it.univaq.disim.sealab.metaheuristic.managers.Manager;

@SuppressWarnings("serial")
public abstract class RProblem<S extends RSolution> extends AbstractGenericProblem<S> {

//	protected final EObject model;
	protected int length_of_refactorings;
	protected int number_of_actions;
	protected int allowed_failures;
	protected final int NUM_VAR = 1;
	protected final int NUM_OBJ = 4;
	protected final int VARIABLE_INDEX = 0;
	protected final int NUM_CON = 0;
	
	protected final int FIRST_OBJ = 0;
	protected final int SECOND_OBJ = 1;
	protected final int THIRD_OBJ = 2;
	protected final int FOURTH_OBJ = 3;
	
//	private Manager manager;
	private Controller controller;
	
	protected Path sourceFolderPath; 
	protected Path sourceModelPath;
	
	private int maxCloning = 3;
	private double cloningWeight = 1.5;

	public RProblem(Path srcFolderPath, Path srcModelPath, int desired_length, int length, int allowedFailures, int populationSize, Controller ctrl) {

		controller = ctrl;

		//Enables the generation of worsen models
		if(controller.getConfigurator().isWorsen())
			this.setNumberOfObjectives(1);
		else
			this.setNumberOfObjectives(NUM_OBJ);
		
		this.setNumberOfConstraints(NUM_CON);
		this.setNumberOfVariables(NUM_VAR);

//		this.manager = controller.getManager();
		
		this.sourceFolderPath = srcFolderPath;
		this.sourceModelPath = srcModelPath;
		
		// no longer used
//		this.model = EcoreUtil.copy(this.manager.getMetamodelManager().getModel(sourceModelPath));
		
		this.length_of_refactorings = desired_length;
		this.allowed_failures = allowedFailures;
		this.number_of_actions = length;
	
	}

//	public EObject getModel() {
//		return model;
//	}
	
	public abstract Path getSourceModelPath();

	public int getLength_of_refactorings() {
		return length_of_refactorings;
	}

	public void setLength_of_refactorings(int length_of_refactorings) {
		this.length_of_refactorings = length_of_refactorings;
	}
	
	
	public void setName(String n) {
		super.setName(n);
	}

//	public Manager getManager() {
//		return manager;
//	}
//
//	public void setManager(Manager manager) {
//		this.manager = manager;
//	}

	public Controller getController() {
		return controller;
	}
	
	public RProblem setSourceModelFolderPath(Path sourceModelPath) {this.sourceFolderPath = sourceModelPath; return this;}
	public RProblem setMaxCloning(final int mc) { maxCloning=mc; return this;}
	public RProblem setCloningWeight(final double cw) { cloningWeight=cw; return this;}
	
	public int getMaxCloning() { return maxCloning; }
	public double getCloningWeight() { return cloningWeight; }
	
	@Override
	public String toString() { return this.getName(); }
}
