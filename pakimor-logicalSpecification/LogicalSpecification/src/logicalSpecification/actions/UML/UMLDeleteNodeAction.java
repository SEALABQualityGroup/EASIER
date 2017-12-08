/**
 */
package logicalSpecification.actions.UML;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.uml2.uml.Node;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delete Node Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.actions.UML.UMLDeleteNodeAction#getNodeToDelSVP <em>Node To Del SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLDeleteNodeAction#getAllNodesMVP <em>All Nodes MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLDeleteNodeAction#getUmlNodeToDel <em>Uml Node To Del</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteNodeAction()
 * @model
 * @generated
 */
public interface UMLDeleteNodeAction extends UMLDeleteAction {
	/**
	 * Returns the value of the '<em><b>Node To Del SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node To Del SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node To Del SVP</em>' reference.
	 * @see #setNodeToDelSVP(SingleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteNodeAction_NodeToDelSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getNodeToDelSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLDeleteNodeAction#getNodeToDelSVP <em>Node To Del SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node To Del SVP</em>' reference.
	 * @see #getNodeToDelSVP()
	 * @generated
	 */
	void setNodeToDelSVP(SingleValuedParameter value);

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
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteNodeAction_AllNodesMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllNodesMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLDeleteNodeAction#getAllNodesMVP <em>All Nodes MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Nodes MVP</em>' reference.
	 * @see #getAllNodesMVP()
	 * @generated
	 */
	void setAllNodesMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Uml Node To Del</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Node To Del</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Node To Del</em>' attribute.
	 * @see #setUmlNodeToDel(Node)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteNodeAction_UmlNodeToDel()
	 * @model dataType="logicalSpecification.actions.UML.Node" required="true"
	 * @generated
	 */
	Node getUmlNodeToDel();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLDeleteNodeAction#getUmlNodeToDel <em>Uml Node To Del</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Node To Del</em>' attribute.
	 * @see #getUmlNodeToDel()
	 * @generated
	 */
	void setUmlNodeToDel(Node value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log(); \n\t\tController.logger_.info(getUmlNodeToDel().getName());'"
	 * @generated
	 */
	void log();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='umlNodeToDel.destroy();'"
	 * @generated
	 */
	void execute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; delNodeParams = new ArrayList&lt;&gt;();\n\n\t\tsetNodeToDelSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getNodeQuery(getUmlNodeToDel())));\n\t\tdelNodeParams.add(getNodeToDelSVP());\n\n\t\tsetAllNodesMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));\n\t\tdelNodeParams.add(getAllNodesMVP());\n\n\t\tgetParameters().addAll(delNodeParams);'"
	 * @generated
	 */
	void setParameters();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();\n\n\t\tFOLSpecification specification = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createFOLSpectification(\"DeleteNodePreCondition\");\n\n\t\tAndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\n\t\tExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getNodeToDelSVP(), getAllNodesMVP());\n\n\t\tpreAnd.getArguments().add(existsOperator);\n\n\t\tspecification.setRootOperator(preAnd);\n\t\tpreCondition.setConditionFormula(specification);\n\t\tsetPre(preCondition);'"
	 * @generated
	 */
	void createPreCondition();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();\n\t\tFOLSpecification specification = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createFOLSpectification(\"DeleteNodePostCondition\");\n\n\t\tAndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\n\t\tNotOperator notOperator = Manager.getInstance(UMLManager.getInstance()).createNotOperator();\n\t\tExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getNodeToDelSVP(), getAllNodesMVP());\n\n\t\tnotOperator.setArgument(existsOperator);\n\t\tpreAnd.getArguments().add(notOperator);\n\n\t\tspecification.setRootOperator(preAnd);\n\t\tpostCondition.setConditionFormula(specification);\n\t\tsetPost(postCondition);'"
	 * @generated
	 */
	void createPostCondition();

} // UMLDeleteNodeAction
