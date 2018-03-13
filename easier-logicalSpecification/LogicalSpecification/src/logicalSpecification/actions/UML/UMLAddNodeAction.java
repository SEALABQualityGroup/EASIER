/**
 */
package logicalSpecification.actions.UML;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.emf.common.util.EList;

import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Node;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Node Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddNodeAction#getNodeToAddSVP <em>Node To Add SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddNodeAction#getNeighborsMVP <em>Neighbors MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddNodeAction#getCompsToDeployMVP <em>Comps To Deploy MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddNodeAction#getAllCompsMVP <em>All Comps MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddNodeAction#getAllNodesMVP <em>All Nodes MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddNodeAction#getAllDeployedElemsMVP <em>All Deployed Elems MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddNodeAction#getUmlNodeToAdd <em>Uml Node To Add</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddNodeAction#getUmlNeighbors <em>Uml Neighbors</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddNodeAction#getUmlCompsToDeploy <em>Uml Comps To Deploy</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddNodeAction#getUmlSourcePackage <em>Uml Source Package</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction()
 * @model
 * @generated
 */
public interface UMLAddNodeAction extends UMLAddAction {
	/**
	 * Returns the value of the '<em><b>Node To Add SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node To Add SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node To Add SVP</em>' reference.
	 * @see #setNodeToAddSVP(SingleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction_NodeToAddSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getNodeToAddSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddNodeAction#getNodeToAddSVP <em>Node To Add SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node To Add SVP</em>' reference.
	 * @see #getNodeToAddSVP()
	 * @generated
	 */
	void setNodeToAddSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Neighbors MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Neighbors MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Neighbors MVP</em>' reference.
	 * @see #setNeighborsMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction_NeighborsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getNeighborsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddNodeAction#getNeighborsMVP <em>Neighbors MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Neighbors MVP</em>' reference.
	 * @see #getNeighborsMVP()
	 * @generated
	 */
	void setNeighborsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Comps To Deploy MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comps To Deploy MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comps To Deploy MVP</em>' reference.
	 * @see #setCompsToDeployMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction_CompsToDeployMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getCompsToDeployMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddNodeAction#getCompsToDeployMVP <em>Comps To Deploy MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comps To Deploy MVP</em>' reference.
	 * @see #getCompsToDeployMVP()
	 * @generated
	 */
	void setCompsToDeployMVP(MultipleValuedParameter value);

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
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction_AllCompsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllCompsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddNodeAction#getAllCompsMVP <em>All Comps MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Comps MVP</em>' reference.
	 * @see #getAllCompsMVP()
	 * @generated
	 */
	void setAllCompsMVP(MultipleValuedParameter value);

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
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction_AllNodesMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllNodesMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddNodeAction#getAllNodesMVP <em>All Nodes MVP</em>}' reference.
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
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction_AllDeployedElemsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllDeployedElemsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddNodeAction#getAllDeployedElemsMVP <em>All Deployed Elems MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Deployed Elems MVP</em>' reference.
	 * @see #getAllDeployedElemsMVP()
	 * @generated
	 */
	void setAllDeployedElemsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Uml Node To Add</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Node To Add</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Node To Add</em>' attribute.
	 * @see #setUmlNodeToAdd(Node)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction_UmlNodeToAdd()
	 * @model dataType="logicalSpecification.actions.UML.Node" required="true"
	 * @generated
	 */
	Node getUmlNodeToAdd();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddNodeAction#getUmlNodeToAdd <em>Uml Node To Add</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Node To Add</em>' attribute.
	 * @see #getUmlNodeToAdd()
	 * @generated
	 */
	void setUmlNodeToAdd(Node value);

	/**
	 * Returns the value of the '<em><b>Uml Neighbors</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Neighbors</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Neighbors</em>' attribute list.
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction_UmlNeighbors()
	 * @model dataType="logicalSpecification.actions.UML.Node"
	 * @generated
	 */
	EList<Node> getUmlNeighbors();

	/**
	 * Returns the value of the '<em><b>Uml Comps To Deploy</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Component}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Comps To Deploy</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Comps To Deploy</em>' attribute list.
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction_UmlCompsToDeploy()
	 * @model dataType="logicalSpecification.actions.UML.Component"
	 * @generated
	 */
	EList<Component> getUmlCompsToDeploy();

	/**
	 * Returns the value of the '<em><b>Uml Source Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Source Package</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Source Package</em>' attribute.
	 * @see #setUmlSourcePackage(org.eclipse.uml2.uml.Package)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddNodeAction_UmlSourcePackage()
	 * @model dataType="logicalSpecification.actions.UML.Package" required="true"
	 * @generated
	 */
	org.eclipse.uml2.uml.Package getUmlSourcePackage();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddNodeAction#getUmlSourcePackage <em>Uml Source Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Source Package</em>' attribute.
	 * @see #getUmlSourcePackage()
	 * @generated
	 */
	void setUmlSourcePackage(org.eclipse.uml2.uml.Package value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log();\n\t\tif (getUmlNodeToAdd() != null) {\n\t\t\tController.logger_.info(getUmlNodeToAdd().getName());\n\t\t\tController.logger_.info(\"--------Deployment----------\");\n\t\t\tfor (Deployment depl : this.umlNodeToAdd.getDeployments())\n\t\t\t\tController.logger_.info(depl.toString());\n\t\t\tController.logger_.info(\"--------CommunicationPath----------\");\n\t\t\tfor (CommunicationPath cp : umlNodeToAdd.getCommunicationPaths())\n\t\t\t\tController.logger_.info(cp.toString());\n\t\t}'"
	 * @generated
	 */
	void log();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='umlNodeToAdd = UMLFactory.eINSTANCE.createNode();\n\t\tumlNodeToAdd.setName(\"newNode\" + Math.random());\n\t\tumlNodeToAdd.setPackage(umlSourcePackage);\n\n\t\taddCommunicationPaths();\n\n\t\taddDeployedComps();'"
	 * @generated
	 */
	void execute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='Artifact art;\n\t\tfor (Component comp : getUmlCompsToDeploy()) {\n\t\t\tart = UMLFactory.eINSTANCE.createArtifact();\n\t\t\tart.setName(comp.getName() + \"_Artifact\");\n\t\t\tart.createManifestation(comp.getName() + \"_Manifestation\", comp);\n\t\t\tDeployment deploy = umlNodeToAdd.createDeployment(comp.getName() + \"_Deployment\");\n\t\t\tdeploy.getDeployedArtifacts().add(art);\n\t\t\tumlNodeToAdd.getDeployments().add(deploy);\n\t\t}'"
	 * @generated
	 */
	void addDeployedComps();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='for (Node node : umlNeighbors) {\n\t\t\t/**\n\t\t\t * createCommunicationPath(boolean end1IsNavigable, AggregationKind\n\t\t\t * end1Aggregation, String end1Name, int end1Lower,int end1Upper,\n\t\t\t * Node end1Node, boolean end2IsNavigable,AggregationKind\n\t\t\t * end2Aggregation, String end2Name, int end2Lower,int end2Upper);\n\t\t\t \052/\n\t\t\tumlNodeToAdd\n\t\t\t\t\t.createCommunicationPath(true, AggregationKind.COMPOSITE_LITERAL, node.getName(), 1, 1, node, true,\n\t\t\t\t\t\t\tAggregationKind.COMPOSITE_LITERAL, umlNodeToAdd.getName(), 1, 1)\n\t\t\t\t\t.setName(umlNodeToAdd.getName() + \"_cp_\" + node.getName());\n\t\t}'"
	 * @generated
	 */
	void addCommunicationPaths();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; addParams = new ArrayList&lt;&gt;();\n\t\t\n\t\t\t\tif (umlNodeToAdd != null) {\n\t\t\t\t\t// FIXME le add non dovrebbero avere come attributo l\'oggetto da\n\t\t\t\t\t// creare\n\t\t\t\t\tsetNodeToAddSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodeQuery(umlNodeToAdd)));\n\t\t\t\t\taddParams.add(getNodeToAddSVP());\n\t\t\t\t}\n\t\t\n\t\t\t\tsetNeighborsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getNodesQuery(this.umlNeighbors)));\n\t\t\t\taddParams.add(getNeighborsMVP());\n\t\t\n\t\t\t\tsetCompsToDeployMVP(\n\t\t\t\t\t\tManager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getComponentsQuery(this.umlCompsToDeploy)));\n\t\t\t\taddParams.add(getCompsToDeployMVP());\n\t\t\n\t\t\t\tsetAllNodesMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));\n\t\t\t\taddParams.add(getAllNodesMVP());\n\t\t\n\t\tsetAllCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllComponentsQuery()));\n\t\t\t\taddParams.add(getAllCompsMVP());\n\t\t\n\t\t\t\tif (getUmlNodeToAdd() != null) {\n\t\t\t\t\tArrayList&lt;Node&gt; nList = new ArrayList&lt;Node&gt;();\n\t\t\t\t\t// FIXME qui facciamo la query dei componenti deploiati sul nodo\n\t\t\t\t\t// appena creato ma e\' vuota\n\t\t\t\t\tnList.add(getUmlNodeToAdd());\n\t\t\t\t\tsetAllDeployedElemsMVP(\n\t\t\t\t\t\t\tManager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllDeployedElementsQuery(nList)));\n\t\t\t\t\taddParams.add(getAllDeployedElemsMVP());\n\t\t\t\t}\n\t\t\n\t\t\t\tgetParameters().addAll(addParams);'"
	 * @generated
	 */
	void setParameters();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();\n\t\t\n\t\t\t\tFOLSpecification addPreSpec = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification(\"AddNodePrecondition\");\n\t\t\n\t\t\t\tAndOperator addPreAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\t\n\t\t\t\t// ExistsOperator addPreAndNotExists =\n\t\t\t\t// Manager.createExistsOperator(getNodeToAddSVP(), getAllNodesMVP());\n\t\t\n\t\t\t\t// NotOperator addPreAndNot =\n\t\t\t\t// Manager.createNotOperator(addPreAndNotExists);\n\t\t\t\t// addPreAnd.getArguments().add(addPreAndNot);\n\t\t\n\t\t\t\tForAllOperator addPreAndForall = Manager.getInstance(UMLManager.getInstance()).createForAllOperator(getNeighborsMVP());\n\t\t\n\t\t\t\tExistsOperator addPreAndForallExists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getAllNodesMVP());\n\t\t\t\taddPreAndForall.setArgument(addPreAndForallExists);\n\t\t\t\taddPreAnd.getArguments().add(addPreAndForall);\n\t\t\t\taddPreSpec.setRootOperator(addPreAnd);\n\t\t\n\t\t\t\tpreCondition.setConditionFormula(addPreSpec);\n\t\t\t\tsetPre(preCondition);'"
	 * @generated
	 */
	void createPreCondition();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();\n\t\t\n\t\t\t\tFOLSpecification addPostSpec = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification(\"AddNodePostcondition\");\n\t\t\n\t\t\t\tAndOperator addPostAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\t\n\t\t\t\tExistsOperator addPostAndExists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getNodeToAddSVP(), getAllNodesMVP());\n\t\t\t\taddPostAnd.getArguments().add(addPostAndExists);\n\t\t\n\t\t\t\tForAllOperator addPostAndForallNeighs = Manager.getInstance(UMLManager.getInstance()).createForAllOperator(getNeighborsMVP());\n\t\t\n\t\t\t\tAndOperator addPostAndForallNeighsAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\t\n\t\t\t\tExistsOperator addPostAndForallNeighsAndExistsAllNodes = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getAllNodesMVP());\n\t\t\t\taddPostAndForallNeighsAnd.getArguments().add(addPostAndForallNeighsAndExistsAllNodes);\n\t\t\n\t\t\t\tExistsOperator addPostAndForallNeighsAndExistsNeighs = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getNeighborsMVP());\n\t\t\t\taddPostAndForallNeighsAnd.getArguments().add(addPostAndForallNeighsAndExistsNeighs);\n\t\t\n\t\t\t\taddPostAndForallNeighs.setArgument(addPostAndForallNeighsAnd);\n\t\t\n\t\t\t\taddPostAnd.getArguments().add(addPostAndForallNeighs);\n\t\t\n\t\t\t\tForAllOperator addPostAndForallDeplComps = Manager.getInstance(UMLManager.getInstance()).createForAllOperator(getCompsToDeployMVP());\n\t\t\n\t\t\t\tAndOperator addPostAndForallDeplCompsAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\t\n\t\t\t\tExistsOperator addPostAndForallDeplCompsAndExistsAllNodes = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getAllCompsMVP());\n\t\t\t\taddPostAndForallDeplCompsAnd.getArguments().add(addPostAndForallDeplCompsAndExistsAllNodes);\n\t\t\n\t\t\t\tExistsOperator addPostAndForallDeplCompsAndExistsNeighs = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t\t\t.createExistsOperator(getAllDeployedElemsMVP());\n\t\t\t\taddPostAndForallDeplCompsAnd.getArguments().add(addPostAndForallDeplCompsAndExistsNeighs);\n\t\t\n\t\t\t\taddPostAndForallDeplComps.setArgument(addPostAndForallDeplCompsAnd);\n\t\t\n\t\t\t\taddPostAnd.getArguments().add(addPostAndForallDeplComps);\n\t\t\n\t\t\t\taddPostSpec.setRootOperator(addPostAnd);\n\t\t\t\tpostCondition.setConditionFormula(addPostSpec);\n\t\t\t\tsetPost(postCondition);'"
	 * @generated
	 */
	void createPostCondition();

} // UMLAddNodeAction
