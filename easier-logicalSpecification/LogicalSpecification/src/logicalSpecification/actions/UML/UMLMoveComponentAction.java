/**
 */
package logicalSpecification.actions.UML;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.emf.common.util.EList;

import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Node;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Move Component Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getCompToMoveSVP <em>Comp To Move SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getTargetNodesMVP <em>Target Nodes MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getAllArtifactsMVP <em>All Artifacts MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getAllCompsMVP <em>All Comps MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getAllTargetsMVP <em>All Targets MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getAllNodesMVP <em>All Nodes MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getAllDeployedElemsMVP <em>All Deployed Elems MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getUmlCompToMove <em>Uml Comp To Move</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getUmlTargetNodes <em>Uml Target Nodes</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getUmlArtifacts <em>Uml Artifacts</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction()
 * @model
 * @generated
 */
public interface UMLMoveComponentAction extends UMLMoveAction {
	/**
	 * Returns the value of the '<em><b>Comp To Move SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comp To Move SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comp To Move SVP</em>' reference.
	 * @see #setCompToMoveSVP(SingleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction_CompToMoveSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getCompToMoveSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getCompToMoveSVP <em>Comp To Move SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comp To Move SVP</em>' reference.
	 * @see #getCompToMoveSVP()
	 * @generated
	 */
	void setCompToMoveSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Target Nodes MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Nodes MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Nodes MVP</em>' reference.
	 * @see #setTargetNodesMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction_TargetNodesMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getTargetNodesMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getTargetNodesMVP <em>Target Nodes MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Nodes MVP</em>' reference.
	 * @see #getTargetNodesMVP()
	 * @generated
	 */
	void setTargetNodesMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>All Artifacts MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Artifacts MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Artifacts MVP</em>' reference.
	 * @see #setAllArtifactsMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction_AllArtifactsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllArtifactsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getAllArtifactsMVP <em>All Artifacts MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Artifacts MVP</em>' reference.
	 * @see #getAllArtifactsMVP()
	 * @generated
	 */
	void setAllArtifactsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>All Comps MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Comps MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Comps MVP</em>' reference.
	 * @see #setAllCompsMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction_AllCompsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllCompsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getAllCompsMVP <em>All Comps MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Comps MVP</em>' reference.
	 * @see #getAllCompsMVP()
	 * @generated
	 */
	void setAllCompsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>All Targets MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Targets MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Targets MVP</em>' reference.
	 * @see #setAllTargetsMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction_AllTargetsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllTargetsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getAllTargetsMVP <em>All Targets MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Targets MVP</em>' reference.
	 * @see #getAllTargetsMVP()
	 * @generated
	 */
	void setAllTargetsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>All Nodes MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Nodes MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Nodes MVP</em>' reference.
	 * @see #setAllNodesMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction_AllNodesMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllNodesMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getAllNodesMVP <em>All Nodes MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Nodes MVP</em>' reference.
	 * @see #getAllNodesMVP()
	 * @generated
	 */
	void setAllNodesMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>All Deployed Elems MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Deployed Elems MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Deployed Elems MVP</em>' reference.
	 * @see #setAllDeployedElemsMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction_AllDeployedElemsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllDeployedElemsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getAllDeployedElemsMVP <em>All Deployed Elems MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Deployed Elems MVP</em>' reference.
	 * @see #getAllDeployedElemsMVP()
	 * @generated
	 */
	void setAllDeployedElemsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Uml Comp To Move</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Comp To Move</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Comp To Move</em>' attribute.
	 * @see #setUmlCompToMove(Component)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction_UmlCompToMove()
	 * @model dataType="logicalSpecification.actions.UML.Component" required="true"
	 * @generated
	 */
	Component getUmlCompToMove();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveComponentAction#getUmlCompToMove <em>Uml Comp To Move</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Comp To Move</em>' attribute.
	 * @see #getUmlCompToMove()
	 * @generated
	 */
	void setUmlCompToMove(Component value);

	/**
	 * Returns the value of the '<em><b>Uml Target Nodes</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Target Nodes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Target Nodes</em>' attribute list.
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction_UmlTargetNodes()
	 * @model dataType="logicalSpecification.actions.UML.Node" required="true"
	 * @generated
	 */
	EList<Node> getUmlTargetNodes();

	/**
	 * Returns the value of the '<em><b>Uml Artifacts</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Artifact}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Artifacts</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Artifacts</em>' attribute list.
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveComponentAction_UmlArtifacts()
	 * @model dataType="logicalSpecification.actions.UML.Artifact"
	 * @generated
	 */
	EList<Artifact> getUmlArtifacts();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='Controller.logger_.info(umlCompToMove.toString());\n\t\tfor (Node target : umlTargetNodes) {\n\t\t\tfor (Deployment dep : target.getDeployments()) {\n\t\t\t\tController.logger_.info(dep.toString());\n\t\t\t}\n\t\t}\n\t\tfor (Artifact art : getUmlArtifacts()) {\n\t\t\tController.logger_.info(art.toString());\n\t\t\tController.logger_.info(Integer.toString(art.getManifestations().size()));\n\t\t}'"
	 * @generated
	 */
	void log();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='moveDeployments();'"
	 * @generated
	 */
	void execute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Manifestation&gt; manifestations = new ArrayList&lt;Manifestation&gt;();\n\n\t\tmanifestations = ((UMLManager) Manager.getInstance(UMLManager.getInstance()).getMetamodelManager())\n\t\t\t\t.getAllManifestations();\n\n\t\tfor (Manifestation man : manifestations) {\n\t\t\tif (man.getUtilizedElement().getQualifiedName().equals(umlCompToMove.getQualifiedName())) {\n\t\t\t\tman.destroy();\n\t\t\t}\n\t\t}\n\n\t\tumlArtifacts = ((UMLManager) Manager.getInstance(UMLManager.getInstance()).getMetamodelManager())\n\t\t\t\t.getAllArtifacts();\n\n\t\tfor (Artifact art : umlArtifacts) {\n\t\t\tif (art.getManifestations().isEmpty()) {\n\t\t\t\tart.destroy();\n\t\t\t}\n\t\t}\n\n\t\tdeployOn();'"
	 * @generated
	 */
	void moveDeployments();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='Artifact art;\n\t\tfor (Node target : umlTargetNodes) {\n\t\t\tart = UMLFactory.eINSTANCE.createArtifact();\n\t\t\tart.setName(umlCompToMove.getName() + \"_Artifact\");\n\t\t\tart.createManifestation(umlCompToMove.getName() + \"_Manifestation\", umlCompToMove);\n\t\t\tDeployment deploy = target.createDeployment(umlCompToMove.getName() + \"_Deployment\");\n\t\t\tdeploy.getDeployedArtifacts().add(art);\n\t\t\ttarget.getDeployments().add(deploy);\n\t\t}'"
	 * @generated
	 */
	void deployOn();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; moveCompParams = new ArrayList&lt;&gt;();\n\n\t\tsetCompToMoveSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getComponentQuery(getUmlCompToMove())));\n\t\tmoveCompParams.add(getCompToMoveSVP());\n\n\t\tsetTargetNodesMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getNodesQuery(getUmlTargetNodes())));\n\t\tmoveCompParams.add(getTargetNodesMVP());\n\n\t\tsetAllArtifactsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getAllArtifactsQuery()));\n\t\tmoveCompParams.add(getAllArtifactsMVP());\n\n\t\tsetAllCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getAllComponentsQuery()));\n\t\tmoveCompParams.add(getAllCompsMVP());\n\n\t\tsetAllTargetsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getNodesQuery(getUmlTargetNodes())));\n\t\tmoveCompParams.add(getAllTargetsMVP());\n\n\t\tsetAllNodesMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));\n\t\tmoveCompParams.add(getAllNodesMVP());\n\n\t\tsetAllDeployedElemsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getAllDeployedElementsQuery(getUmlTargetNodes())));\n\t\tmoveCompParams.add(getAllDeployedElemsMVP());\n\n\t\tgetParameters().addAll(moveCompParams);'"
	 * @generated
	 */
	void setParameters();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();\n\n\t\tFOLSpecification specification = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createFOLSpectification(\"MoveComponentPreCondition\");\n\n\t\tExistsOperator existsComponentInComponents = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getCompToMoveSVP(), getAllCompsMVP());\n\n\t\tForAllOperator forAllNode = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createForAllOperator(getTargetNodesMVP());\n\t\tExistsOperator existsTargetInNodes = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getAllNodesMVP());\n\t\tNotOperator componentNotOperator = Manager.getInstance(UMLManager.getInstance()).createNotOperator();\n\n\t\t// TODO getAllDeployedElems \350 l\'insieme delle Component deployate sui target\n\t\t// Nodes. Non va bene: la condition dice che\n\t\t// su ogni target Node ci deve stare la Component spostata. Con l\'unione basta\n\t\t// che stia su uno dei target...\n\t\tExistsOperator componentExists = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getCompToMoveSVP(), getAllDeployedElemsMVP());\n\n\t\tcomponentNotOperator.setArgument(componentExists);\n\t\tAndOperator andOperator = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\tandOperator.getArguments().add(existsTargetInNodes);\n\t\tandOperator.getArguments().add(componentNotOperator);\n\t\tforAllNode.setArgument(andOperator);\n\n\t\tAndOperator andRoot = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\tandRoot.getArguments().add(existsComponentInComponents);\n\t\tandRoot.getArguments().add(forAllNode);\n\n\t\tspecification.setRootOperator(andRoot);\n\t\tpreCondition.setConditionFormula(specification);\n\t\tsetPre(preCondition);'"
	 * @generated
	 */
	void createPreCondition();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();\n\n\t\tFOLSpecification specification = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createFOLSpectification(\"MoveComponentPostCondition\");\n\n\t\tExistsOperator existsComponentInComponents = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getCompToMoveSVP(), getAllCompsMVP());\n\n\t\tForAllOperator forAllNode = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createForAllOperator(getTargetNodesMVP());\n\t\tExistsOperator existsTargetInNodes = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getAllNodesMVP());\n\t\tExistsOperator componentExists = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getCompToMoveSVP(), getAllDeployedElemsMVP());\n\t\tAndOperator andOperator = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\tandOperator.getArguments().add(existsTargetInNodes);\n\t\tandOperator.getArguments().add(componentExists);\n\t\tforAllNode.setArgument(andOperator);\n\n\t\tAndOperator andRoot = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\tandRoot.getArguments().add(existsComponentInComponents);\n\t\tandRoot.getArguments().add(forAllNode);\n\n\t\tspecification.setRootOperator(andRoot);\n\t\tpostCondition.setConditionFormula(specification);\n\t\tsetPost(postCondition);'"
	 * @generated
	 */
	void createPostCondition();

} // UMLMoveComponentAction
