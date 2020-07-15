package it.univaq.disim.sealab.metaheuristic.actions.uml;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.*;
//import org.uma.jmetal.util.pseudorandom.JMetalRandom;

//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.uml.OclUMLStringManager;
import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import logicalSpecification.AndOperator;
//import logicalSpecification.ExistsOperator;
//import logicalSpecification.FOLSpecification;
//import logicalSpecification.MultipleValuedParameter;
//import logicalSpecification.NotOperator;
//import logicalSpecification.Parameter;
//import logicalSpecification.PostCondition;
//import logicalSpecification.PreCondition;
//import logicalSpecification.SingleValuedParameter;
import logicalSpecification.actions.UML.impl.UMLDeleteComponentActionImpl;
import org.eclipse.uml2.uml.Package;
//import logicalSpecification.impl.ActionImpl;

public class UMLDeleteComponentRefactoringAction extends UMLDeleteComponentActionImpl {

	private Component umlCompToDel;
	private String umlCompToDelName;

    private UMLManager umlManager;

    //private SingleValuedParameter compToDelSVP;
    //private MultipleValuedParameter allCompsMVP;
    //private static Double MAX_VALUE = 100.0;

	public UMLDeleteComponentRefactoringAction(Component component, UMLManager umlManager) {

	    // set UML manager.
        this.umlManager = umlManager;

        // set component.
        this.umlCompToDel = component;

        // set name.
        this.umlCompToDelName = component.getName();


        /*
        setParameters();
        createPreCondition();
        createPostCondition();
         */

        this.numOfChanges = 1;
	}

    @Override
    public void execute() {
        // execute the action.

        System.out.println("We want to delete the component with name: " + this.umlCompToDelName + " in the package " + this.umlCompToDel.getPackage().getName() +  ".");


        //destroy component lifelines inside in the sequence.
        System.out.println("Here the list of all the Lifelines to delete: ");

        // PLEASE NOTE that we had to do this before to delete all the manifestations.

        // take all the component lifelines.
        List<Lifeline> lifelines = this.umlManager.getAllComponentLifelines(this.umlCompToDel);
        for(Lifeline lifeline: lifelines){
            System.out.println(" - " + lifeline.getName());
            // destroy lifeline
            lifeline.destroy();
        }


        // retrieve all manifestations.
        List<Manifestation> list_of_manifestations = this.umlManager.getAllManifestationsOf(this.umlCompToDel);
        // delete all manifestations.
        System.out.println("Here the list of all the Manifestations to delete: ");
		for (Manifestation man : list_of_manifestations) {
		    System.out.println(" - " + man.getName());
			if (man.getUtilizedElement().getNamespace() == getUmlCompToDel().getNamespace()) {
			    // destroy manifestation.
				man.destroy();
			}
        }

        System.out.println("Here the list of all the Artifacts to delete: ");
        // take all artifact.
		List<Artifact> artifactList = this.umlManager.getAllArtifacts();
		for (Artifact artifact: artifactList){
            // if the artifact has the same name.
            if(artifact.getName().equals(this.umlCompToDelName + "_Artifact")){
                System.out.println(" - " + artifact.getName());
                // delete the artifact.
                artifact.destroy();
            }
        }

		// destroy the _Deployments.

        System.out.println("Here the list of all the Deployments to delete: ");
        // take all nodes.
        List<Node> nodeList = this.umlManager.getAllNodes();
		for(Node node: nodeList){
		    // take all deployments of a node.
            List<Deployment> deploymentList = node.getDeployments();
            // we need to store all the indexes of the deployment occurrences.
            int index = 0;
            List<Integer> indexs = new ArrayList<>();
            for(Deployment deployment: deploymentList){
                // if it is not null and the name is equal to the component name.
                if(deployment.getName() != null && deployment.getName().equals(this.umlCompToDelName + "_Deployment")){
                    System.out.println(" - " + deployment.getName());
                    // store the index.
                    indexs.add(index);
                }
                else {
                    // increase index.
                    index++;
                }
            }

            // delete all deployments.
            for(Integer i: indexs){
                deploymentList.get(i).destroy();
            }
        }

        // destroy the component.
        this.umlCompToDel.destroy();

    }


    public Component getUmlCompToDel() {
        // return the umlCompToDel.
        return this.umlCompToDel;
    }

    public void setUmlCompToDel(Component component) {
        // set the umlCompToDel.
        this.umlCompToDel = component;
    }

    public String getUmlCompToDelName() {
	    // return the umlCompDelName.
        return umlCompToDelName;
    }

    public void setUmlCompToDelName(String umlCompToDelName) {
        // set the umlCompToDelName.
        this.umlCompToDelName = umlCompToDelName;
    }

//	private void createPostCondition(PostCondition post) {
//		setPost(post);
//	}

//	private void createPreCondition(PreCondition pre) {
//		setPre(pre);
//	}

//	private void setParameters(EList<Parameter> parameters) {
//		getParameters().addAll(parameters);
//	}

//	public UMLDeleteComponentRefactoringAction cloneAction(){
//		return new UMLDeleteComponentRefactoringAction(this);
//	}

//	@Override
//	public void createPostCondition() {
//		PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();
//
//		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("DeleteComponentPostCondition");
//		AndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		NotOperator notOperator = Manager.getInstance(UMLManager.getInstance()).createNotOperator();
//		ExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance()).getInstance(new UMLManager()).createExistsOperator(getCompToDelSVP(), getAllCompsMVP());
//		notOperator.setArgument(existsOperator);
//		preAnd.getArguments().add(notOperator);
//		specification.setRootOperator(preAnd);
//		postCondition.setConditionFormula(specification);
//		setPost(postCondition);
//	}

//	@Override
//	public void createPreCondition() {
//		PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();
//		FOLSpecification specification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification("DeleteComponentPreCondition");
//		AndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		ExistsOperator exists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getCompToDelSVP(), getAllCompsMVP());
//		preAnd.getArguments().add(exists);
//		specification.setRootOperator(preAnd);
//		preCondition.setConditionFormula(specification);
//		setPre(preCondition);
//	}

//	@Override
//	public void setParameters() {
//		List<Parameter> delCompParams = new ArrayList<>();

//		setCompToDelSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getComponentQuery(getUmlCompToDel())));
//		delCompParams.add(getCompToDelSVP());

//		setAllCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllComponentsQuery()));
//		delCompParams.add(getAllCompsMVP());

//		getParameters().addAll(delCompParams);
//	}

//	SingleValuedParameter getCompToDelSVP() {
//		return compToDelSVP;
//	}

//	void setCompToDelSVP(SingleValuedParameter compToDelSVP) {
//		this.compToDelSVP = compToDelSVP;
//	}

//	MultipleValuedParameter getAllCompsMVP() {
//		return allCompsMVP;
//	}

//	void setAllCompsMVP(MultipleValuedParameter allComps) {
//		this.allCompsMVP = allComps;
//	}

}
