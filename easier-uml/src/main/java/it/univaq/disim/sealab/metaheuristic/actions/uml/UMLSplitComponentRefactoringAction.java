package it.univaq.disim.sealab.metaheuristic.actions.uml;

import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;

import java.util.List;

public class UMLSplitComponentRefactoringAction {

    // component to split.
    private Component componentToSplit;
    private String componentToSplitName;

    //operation to move.
    private List<Operation> operationsToMove;

    // list of node where we will deploy our splitted component.
    private List<Node> nodes;

    // manager.
    private UMLManager umlManager;


    public UMLSplitComponentRefactoringAction(Component componentToSplit, List<Operation> operationsToMove, UMLManager umlManager){

        // set component.
        this.componentToSplit = componentToSplit;
        // set name.
        this.componentToSplitName = componentToSplit.getName();

        // set operation to move.
        this.operationsToMove = operationsToMove;

        // set umlManager.
        this.umlManager = umlManager;

        // set field
        this.nodes = this.umlManager.getRandomNodes();

    }

    public void execute() {
        // execute the action.

        System.out.println("*** We want to split the node " + this.componentToSplitName + ". ***");

        // clono componente.
        UMLCloneComponentRefactoringAction umlCloneComponentRefactoringAction = new UMLCloneComponentRefactoringAction(this.componentToSplit, this.nodes);
        umlCloneComponentRefactoringAction.execute();

        // set cloned component.
        Component clonedComponent = umlCloneComponentRefactoringAction.getUmlCompClone();


        System.out.println("\nWe want delete the following operations (from the cloned component): ");
        // delete all operation not moved from the splitted component.
        for(Operation clonedCompOp: clonedComponent.getOperations()){
            boolean toDelete = true;

            for(Operation op: this.operationsToMove){

                // if the current op is not a moved op.
                if(op.getName().equals(clonedCompOp.getName())){
                    // set it as not to delete.
                    toDelete = false;
                }
            }
            if(toDelete){
                // arrives here is it is not contained inside the list of op to move.
                System.out.println(" ° " + clonedCompOp.getName());
                // delete op.
                UMLDeleteOperationRefactoringAction umlDeleteOperationRefactoringAction = new UMLDeleteOperationRefactoringAction(clonedCompOp,clonedComponent.getName(),this.umlManager);
                umlDeleteOperationRefactoringAction.execute();
            }
        }

        System.out.println("\nWe want delete the following operations (from the first component): ");
        // loop op to move.
        for (Operation operation: this.operationsToMove){
            System.out.println(" ° " + operation.getName());

            // deleting all operations.
            UMLDeleteOperationRefactoringAction umlDeleteOperationRefactoringAction = new UMLDeleteOperationRefactoringAction(operation,this.componentToSplitName,this.umlManager);
            umlDeleteOperationRefactoringAction.execute();
        }


    }

    public Component getComponentToSplit() {
        // get componentToSplit.
        return componentToSplit;
    }

    public void setComponentToSplit(Component componentToSplit) {
        // set componentToSplit.
        this.componentToSplit = componentToSplit;
    }

    public String getComponentToSplitName() {
        // get componentToSplitName.
        return componentToSplitName;
    }

    public void setComponentToSplitName(String componentToSplitName) {
        // set componentToSplitName.
        this.componentToSplitName = componentToSplitName;
    }

    public List<Operation> getOperationsToMove() {
        // get operationsToMove.
        return operationsToMove;
    }

    public void setOperationsToMove(List<Operation> operationsToMove) {
        // set operationsToMove.
        this.operationsToMove = operationsToMove;
    }

}
