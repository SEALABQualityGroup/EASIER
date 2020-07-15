package it.univaq.disim.sealab.metaheuristic.actions.uml;

import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.uml2.uml.*;

import java.util.ArrayList;
import java.util.List;

public class UMLCloneComponentRefactoringAction  {


    //uml comp to clone
    private Component umlCompToClone;
    private String umlCompToCloneName;

    //uml clone comp.
    private Component umlCompClone;
    private String umlCompCloneName;

    // nodes where we will deploy the cloned components.
    private List<Node> umlTargetNodes;




    public UMLCloneComponentRefactoringAction(Component umlCompToClone, List<Node> targetNodes){

        // set umlCompToClone.
        this.umlCompToClone = umlCompToClone;
        // set name
        this.umlCompToCloneName = umlCompToClone.getName();

        // set target nodes.
        this.umlTargetNodes = targetNodes;

         /*
        setParameters();
        createPreCondition();
        createPostCondition();
         */
    }


    public void execute() {
        // execute the action.

        //set new name.
        this.umlCompCloneName =  umlCompToClone.getName() + "_Cloned";

        //clone component.
        this.umlCompClone = UMLFactory.eINSTANCE.createComponent();

        // set new name.
        this.umlCompClone.setName(this.umlCompCloneName);

        // set packaged elements
        for(PackageableElement packageableElement: this.umlCompToClone.getPackagedElements()){

            // set packaged elements: set name and eclass(eClass - The Ecore class of the Packageable Element to create).
            this.umlCompClone.createPackagedElement(packageableElement.getName(),packageableElement.eClass());
        }


        //set interface realizations
        for(InterfaceRealization interfaceRealization: this.umlCompToClone.getInterfaceRealizations()){

            // create new interface with name and contract.
            this.umlCompClone.createInterfaceRealization(interfaceRealization.getName(),interfaceRealization.getContract());
        }

        //set owned operations
        for(Operation operation: this.umlCompToClone.getOwnedOperations()){
            EList<Type> types = new BasicEList<Type>();

            for(Parameter parameter: operation.getOwnedParameters()){
                types.add(parameter.getType());
            }
            // create owned operation.
            this.umlCompClone.createOwnedOperation(operation.getName(),operation.getKeywords() , types);
        }

        // set package.
        this.umlCompClone.setPackage(this.umlCompToClone.getPackage());

        System.out.println("We want clone the component: " + this.umlCompToCloneName);
        System.out.println("The new component will be called: " + this.umlCompCloneName);

        // deploy it on target nodes.
        this.deployOn(this.umlTargetNodes);
    }


    private void deployOn(List<Node> targets) {
        // deploy a component to some target nodes.
        for (Node target : targets) {
            // create new artifact
            Artifact art = UMLFactory.eINSTANCE.createArtifact();
            // set name.
            art.setName(this.umlCompClone.getName() + "_Artifact");
            // Manifestation is an abstraction relationship which represents concrete physical rendering of one or more
            // model elements by an artifact. An artifact manifests one or more model elements.
            art.createManifestation(this.umlCompClone.getName() + "_Manifestation", this.umlCompClone);
            art.setPackage(target.getPackage());
            Deployment deploy = target.createDeployment(this.umlCompClone.getName() + "_Deployment");
            deploy.setName(this.umlCompClone.getName() + "_Deployment");
            deploy.getDeployedArtifacts().add(art);
            target.getDeployments().add(deploy);
        }
    }


    public Component getUmlCompToClone() {
        // get getUmlCompToClone.
        return umlCompToClone;
    }

    public void setUmlCompToClone(Component umlCompToClone) {
        // set getUmlCompToClone.
        this.umlCompToClone = umlCompToClone;
    }

    public String getUmlCompToCloneName() {
        // get getUmlCompToCloneName.
        return umlCompToCloneName;
    }

    public void setUmlCompToCloneName(String umlCompToCloneName) {
        // set getUmlCompToCloneName.
        this.umlCompToCloneName = umlCompToCloneName;
    }

    public String getUmlCompCloneName() {
        // set getUmlCompCloneName.
        return umlCompCloneName;
    }

    public void setUmlCompCloneName(String umlCompCloneName) {
        // set setUmlCompCloneName.
        this.umlCompCloneName = umlCompCloneName;
    }

    public Component getUmlCompClone() {
        // get getUmlCompClone.
        return umlCompClone;
    }

    public void setUmlCompClone(Component umlCompClone) {
        // set getUmlCompClone.
        this.umlCompClone = umlCompClone;
    }


}
