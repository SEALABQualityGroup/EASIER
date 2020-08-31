//package it.univaq.disim.sealab.actions.uml;
//
//import it.univaq.disim.sealab.metaheuristic.actions.uml.UMLDeleteComponentRefactoringAction;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;
//import it.univaq.disim.sealab.metaheuristic.evolutionary.UMLController;
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import org.eclipse.emf.ecore.util.EcoreUtil;
//import org.eclipse.uml2.uml.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.net.URISyntaxException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class TestDeleteComponentAction {
//
//    private Model model;
//    private UMLManager manager;
//    private Controller controller;
//    private Path pathToModel;
//
//    private UMLDeleteComponentRefactoringAction action;
//
//    public TestDeleteComponentAction() {}
//
//    @Before
//    public void setUp() throws URISyntaxException {
//        // create UML Manager.
//        this.controller = new UMLController();
//        this.manager = new UMLManager(this.controller);
//
//        this.manager.packageRegistering();
//
//        // uml model.
//        this.pathToModel = Paths.get(getClass().getResource("/models/smartFloor/smartFloor2.uml").toURI());
//        // load the model.
//        this.manager.loadModel(this.pathToModel);
//
//        this.model = this.manager.getModel();
//        System.out.println("UML document name: "  + this.model.getName());
//    }
//
//
//
//    /**
//     * UMLDeleteComponentRefactoringAction PRECONDITIONS & POSTCONDITIONS
//     * PRE:
//     * 		- The target Component must not receive lifeline messages (when a Component a “send a message” to the target Component).
//     * POST:
//     * 	    - All the Component Manifestation Component must not exist inside the UML model.
//     *      - All the Component Artifacts Component must not exist inside the UML model.
//     *      - All the Component Deployments Component must not exist inside the UML model.
//     *      - The Component must not exist inside the UML model (sequence and deployment diagram).
//     */
//
//    @Test
//    public void shouldRemoveComponentFromTheModel() {
//        // get random component.
//        Component component = this.manager.getAllComponents().get(0);
//
//        // there is one empty node?
//        assert component != null;
//
//        // create and execute an UML refactoring action.
//        this.action = new UMLDeleteComponentRefactoringAction(component, this.manager);
//        this.action.execute();
//
//        // save the model.
//        assert (this.manager.saveModel());
//
//
//
//        // retrieve component name.
//        String componentToDelName = this.action.getUmlCompToDelName();
//
//
//        // loop on sequence to see if lifelines were deleted.
//
//        // take all the component lifelines.
//        List<Lifeline> lifelines = this.manager.getAllComponentLifelines(component);
//        if(!lifelines.isEmpty()){
//            System.out.println(" The lifelines have not been deleted.");
//            assert false;
//        }
//        System.out.println("All the lifelines component (" + componentToDelName + ") ha been deleted!");
//
//
//
//        //loop on manifestations to check if were deleted.
//
//        // retrieve all manifestations.
//        List<Manifestation> list_of_manifestations = this.manager.getAllManifestationsOf(component);
//        // delete all manifestations.
//        for (Manifestation man : list_of_manifestations) {
//            if (man.getUtilizedElement().getNamespace() == component.getNamespace()) {
//                System.out.println(" The manifestation " + man.getName() + "has not been deleted.");
//                // we couldn't delete the manifestation.
//                assert false;
//            }
//        }
//        System.out.println("All the instances deployed component (" + componentToDelName + ") have been deleted!");
//
//
//
//        // check if all the artifact has been deleted.
//
//        // take all artifact.
//        List<Artifact> artifactList = this.manager.getAllArtifacts();
//        for (Artifact artifact: artifactList){
//            // if the artifact has the same name.
//            if(artifact.getName().equals(componentToDelName + "_Artifact")){
//                System.out.println(" The artifact " + artifact.getName() + "has not been deleted.");
//                // we couldn't delete the artifact.
//                assert false;
//            }
//        }
//        System.out.println("All the artifacts of the component (" + componentToDelName + ") have been deleted!");
//
//
//        // check if all the deployment has been deleted.
//
//        // takes all nodes.
//        List<Node> nodes = this.manager.getAllNodes();
//        // foreach node.
//        for(Node node: nodes){
//            // take all deployments of a node.
//            List<Deployment> deploymentList = node.getDeployments();
//            for(Deployment deployment: deploymentList){
//                // if it is not null and the name is equal to the component name.
//                if(deployment.getName() != null && deployment.getName().equals(componentToDelName + "_Deployment")){
//                    System.out.println(" The deployment " + deployment.getName() + "has not been deleted.");
//                }
//            }
//        }
//
//        System.out.println("All the  deployments of component (" + componentToDelName + ") have been deleted!");
//
//
//        // check if the component was deleted.
//
//
//        // take the list of all the components.
//        List<Component> componentList = this.manager.getAllComponents();
//        // loop all the component.
//        for (Component c: componentList){
//            // if is equals.
//            if (c.getName().equals(componentToDelName)){
//                System.out.println("The component (" + componentToDelName + ") has not been deleted!");
//                // we couldn't delete the component.
//                assert false;
//            }
//        }
//
//        System.out.println("The  component (" + componentToDelName + ") has been deleted!");
//
//
//    }
//
//}
