package it.disim.univaq.sealab.metaheuristic.actions.aemilia;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import it.disim.univaq.sealab.metaheuristic.evolutionary.RSolution;
import logicalSpecification.LogicalSpecificationFactory;
import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;

public class Refactoring {

	private List<RefactoringAction> actions;
	private logicalSpecification.Refactoring _refactoring;
	
	private RSolution solution;
	
	public Refactoring(RSolution solution) {
		setRefactoring(LogicalSpecificationFactory.eINSTANCE.createRefactoring());
		this.setSolution(solution);
		actions = new ArrayList<>();
	}
	
	public Refactoring clone(RSolution solution) {
		Refactoring newRefactoring = new Refactoring(solution);
		
		newRefactoring.setCost(this.getCost());
		newRefactoring.setName(this.getName());
		newRefactoring.setPost(this.getPost());
		newRefactoring.setPre(this.getPre());
		
		for(RefactoringAction a : this.getActions()) {
			newRefactoring.getActions().add(a.clone(this.getSolution()));
		}
		
		return newRefactoring;
	}

	public void setPre(PreCondition pre) {
		this.getRefactoring().setPre(pre);
	}

	public void setPost(PostCondition post) {
		this.getRefactoring().setPost(post);
	}

	public void setName(String name) {
		this.getRefactoring().setName(name);
	}

	public void setCost(Double cost) {
		this.getRefactoring().setCost(cost);
	}

	public PreCondition getPre() {
		return this.getRefactoring().getPre();
	}

	public PostCondition getPost() {
		return this.getRefactoring().getPost();
	}

	public String getName() {
		return this._refactoring.getName();
	}

	public Double getCost() {
		return this.getRefactoring().getCost();
	}

	public List<RefactoringAction> getActions() {
		return actions;
	}

	public void setActions(EList<RefactoringAction> actions) {
		this.actions = actions;
	}

	public logicalSpecification.Refactoring getRefactoring() {
		return _refactoring;
	}

	public void setRefactoring(logicalSpecification.Refactoring _refactoring) {
		this._refactoring = _refactoring;
	}

	public RSolution getSolution() {
		return solution;
	}

	public void setSolution(RSolution solution) {
		this.solution = solution;
	}

	public void setNumOfChanges(double calculateNumOfChanges) {
		this.getRefactoring().setNumOfChanges(calculateNumOfChanges);
	}
	
	public Double getNumOfChanges() {
		return this.getRefactoring().getNumOfChanges();
	}
	
}