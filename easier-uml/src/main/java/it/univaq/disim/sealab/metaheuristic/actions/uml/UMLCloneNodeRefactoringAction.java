//package it.univaq.disim.sealab.metaheuristic.actions.uml;
//
//import it.univaq.disim.sealab.metaheuristic.managers.uml.UMLManager;
//import org.eclipse.uml2.uml.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UMLCloneNodeRefactoringAction {
//
//    // node to clone.
//    private Node nodeToClone;
//    private String nodeToCloneName;
//
//    // cloned node.
//    private Node clonedNode;
//    private String nodeCloneName;
//
//    // manager.
//    private UMLManager umlManager;
//
//    public UMLCloneNodeRefactoringAction(Node nodeToClone, UMLManager umlManager){
//
//        // set node.
//        this.nodeToClone = nodeToClone;
//        this.clonedNode = null;
//
//        // set nodeToClone name.
//        this.nodeToCloneName = nodeToClone.getName();
//        // set clone Node name.
//        this.nodeCloneName = nodeToClone.getName() + "_Cloned";
//
//        // set umlManager.
//        this.umlManager = umlManager;
//
//        /*
//		setParameters();
//		createPreCondition();
//		createPostCondition();
//		*/
//    }
//
//
//    public void execute() {
//        // execute the action.
//        System.out.println("We want to clone the node " + this.nodeToCloneName + ".");
//
//        // create new node.
//        UMLAddNodeRefactoringAction addNodeRefactoringAction = new UMLAddNodeRefactoringAction(this.umlManager, this.nodeCloneName);
//        addNodeRefactoringAction.execute();
//
//
//        // retrieve cloned node.
//        this.clonedNode = addNodeRefactoringAction.getUmlNodeToAdd();
//
//
//        System.out.println("\ngetNestedNodes:");
//        //foreach nested node.
//        for(Node nestedNode :this.nodeToClone.getNestedNodes()){
//            System.out.println(" - " + nestedNode);
//            //insert it.
//            this.clonedNode.createNestedNode(nestedNode.getName(),nestedNode.eClass());
//        }
//
//        System.out.println("getDeployedElements:");
//        // foreach deployed elements.
//        for(PackageableElement packageableElement :this.nodeToClone.getDeployedElements()){
//            System.out.println(" - " + packageableElement);
//            // if is a component.
//            if(packageableElement instanceof Component){
//                // add it to the cloned node.
//                List<Node> nodes = new ArrayList<>();
//                nodes.add(this.clonedNode);
//                // deploy it.
//                this.deployOn(nodes, (Component) packageableElement);
//            }
//            // if is an artifact.
//            if(packageableElement instanceof Artifact){
//                System.out.println("is an Artifact");
//            }
//        }
//
//
//        System.out.println("getCommunicationPaths:");
//
//        // retrieve communication Paths.
//        List<CommunicationPath> communicationPaths = this.nodeToClone.getCommunicationPaths();
//        // foreach communication path.
//        for(CommunicationPath communicationPath : communicationPaths){
//            System.out.println(" - " + communicationPath);
//
//            // create new communication path.
//            CommunicationPath communicationPathCLoned = UMLFactory.eINSTANCE.createCommunicationPath();
//            if(communicationPath.getName()!= null){
//                communicationPathCLoned.setName(communicationPath.getName() + "_Cloned");
//            }
//            else {
//                // set a dummy name.
//                communicationPath.setName("");
//                communicationPathCLoned.setName(communicationPath.getName() + "_Cloned");
//            }
//
//            // add it to the current package.
//            this.clonedNode.getPackage().createPackagedElement(communicationPathCLoned.getName(),communicationPathCLoned.eClass());
//
//            // create communication path end-start.
//            Property cpClonedEnd = UMLFactory.eINSTANCE.createProperty();
//            Property cpEnd = UMLFactory.eINSTANCE.createProperty();
//
//            cpClonedEnd.setType(this.clonedNode);
//
//            for(Property p : communicationPath.getMemberEnds()){
//                if(!p.getType().equals(this.nodeToClone)){
//                    // set name and type.
//                    cpEnd.setType(p.getType());
//                    cpEnd.setName(p.getName());
//                }
//            }
//
//            // added owned end and set name.
//            communicationPathCLoned.createOwnedEnd(cpClonedEnd.getName(),cpClonedEnd.getDatatype()).setName(this.nodeCloneName);
//            communicationPathCLoned.createOwnedEnd(cpEnd.getName(),cpEnd.getType()).setName(cpEnd.getName());
//            //set type.
//            communicationPathCLoned.getOwnedEnd(cpClonedEnd.getName(),cpClonedEnd.getDatatype()).setType(this.clonedNode);
//            //set type.
//            communicationPathCLoned.getOwnedEnd(cpEnd.getName(),cpEnd.getType()).setType(cpEnd.getType());
//        }
//
//    }
//
//
//    private void deployOn(List<Node> targets, Component component) {
//        // deploy a component to some target nodes.
//        for (Node target : targets) {
//            // create new artifact
//            Artifact art = UMLFactory.eINSTANCE.createArtifact();
//            // set name.
//            art.setName(component.getName() + "_Artifact");
//            // Manifestation is an abstraction relationship which represents concrete physical rendering of one or more
//            // model elements by an artifact. An artifact manifests one or more model elements.
//            art.createManifestation(component.getName() + "_Manifestation", component);
//            art.setPackage(target.getPackage());
//            Deployment deploy = target.createDeployment(component.getName() + "_Deployment");
//            deploy.setName(component.getName() + "_Deployment");
//            deploy.getDeployedArtifacts().add(art);
//            target.getDeployments().add(deploy);
//        }
//    }
//
//
//
//    public Node getNodeToClone() {
//        // get nodeToClone.
//        return nodeToClone;
//    }
//
//    public void setNodeToClone(Node nodeToClone) {
//        // set nodeToClone.
//        this.nodeToClone = nodeToClone;
//    }
//
//    public String getNodeToCloneName() {
//        // get nodeToCloneName.
//        return nodeToCloneName;
//    }
//
//    public void setNodeToCloneName(String nodeToCloneName) {
//        // set nodeToCloneName.
//        this.nodeToCloneName = nodeToCloneName;
//    }
//
//    public Node getClonedNode() {
//        // get clonedNode.
//        return clonedNode;
//    }
//
//    public void setClonedNode(Node clonedNode) {
//        // set clonedNode.
//        this.clonedNode = clonedNode;
//    }
//
//}
