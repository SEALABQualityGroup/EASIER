package it.univaq.disim.sealab.metaheuristic.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import logicalSpecification.PostCondition;
import logicalSpecification.PreCondition;
import org.eclipse.emf.common.util.EList;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public class Refactoring implements Cloneable{

    private List<RefactoringAction> actions;
    //	private logicalSpecification.Refactoring _refactoring;
    private int solutionID = -1;

    public Refactoring() {
//		setRefactoring(LogicalSpecificationFactory.eINSTANCE.createRefactoring());
        actions = new ArrayList<>();
    }

    public Refactoring(Refactoring rfSource){
        this();
        this.solutionID = rfSource.solutionID;
        for(RefactoringAction a : rfSource.getActions()){
            this.getActions().add(a.clone());
        }
    }

    @Override
    public Refactoring clone(){
        try {
            return (Refactoring) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSolutionID(int id) {
        solutionID = id;
    }

    public List<RefactoringAction> getActions() {
        return actions;
    }

    public void setActions(EList<RefactoringAction> actions) {
        this.actions = actions;
    }

    public void execute(){
        for(RefactoringAction action : actions){
            action.execute();
        }
    }

    public boolean hasMultipleOccurrence() {

        int refactoringLength = this.getActions().size();
        List<RefactoringAction> actions = this.getActions();
        for (int i = 0; i < refactoringLength; i++) {
            RefactoringAction a = actions.get(i);
            for (int j = i + 1; j < refactoringLength; j++) {
                RefactoringAction a2 = actions.get(j);
                if (a.equals(a2)) {
//                    System.out.printf("found multiple occurrences of %s%n", a.getName());
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Refactoring other = (Refactoring) obj;
        if (actions == null && other.actions != null) {
            return false;
        } else {
            if (actions.size() != other.actions.size())
                return false;
            for (int i = 0; i < actions.size(); i++) {
                if (!actions.get(i).equals(other.actions.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prints for each action a semicolon separated line
     * solID,action.toCSV()
     *
     * @return
     */
    public String toCSV() {
        StringBuilder strBuilder = new StringBuilder();
        final int solutionID = this.solutionID;
        for (int i = 0; i < getActions().size() - 1; i++) {
            strBuilder.append(solutionID).append(",").append(getActions().get(i).toCSV()).append("\n");
        }
        strBuilder.append(solutionID).append(",").append(getActions().get(getActions().size() - 1).toCSV());
        return strBuilder.toString();
    }

    /**
     * Prints a single semicolon line with actions belong to the refactoring
     *
     * @return
     */
    @Override
    public String toString() {
        return getActions().stream().map(RefactoringAction::toCSV)
                .collect(Collectors.joining(";"));
    }

}
