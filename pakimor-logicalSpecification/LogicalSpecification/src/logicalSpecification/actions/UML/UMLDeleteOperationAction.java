/**
 */
package logicalSpecification.actions.UML;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.uml2.uml.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delete Operation Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.actions.UML.UMLDeleteOperationAction#getOpToDelSVP <em>Op To Del SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLDeleteOperationAction#getAllOpsMVP <em>All Ops MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLDeleteOperationAction#getUmlOpToDel <em>Uml Op To Del</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteOperationAction()
 * @model
 * @generated
 */
public interface UMLDeleteOperationAction extends UMLDeleteAction {
	/**
	 * Returns the value of the '<em><b>Op To Del SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Op To Del SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op To Del SVP</em>' reference.
	 * @see #setOpToDelSVP(SingleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteOperationAction_OpToDelSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getOpToDelSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLDeleteOperationAction#getOpToDelSVP <em>Op To Del SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op To Del SVP</em>' reference.
	 * @see #getOpToDelSVP()
	 * @generated
	 */
	void setOpToDelSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>All Ops MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Ops MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Ops MVP</em>' reference.
	 * @see #setAllOpsMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteOperationAction_AllOpsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllOpsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLDeleteOperationAction#getAllOpsMVP <em>All Ops MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Ops MVP</em>' reference.
	 * @see #getAllOpsMVP()
	 * @generated
	 */
	void setAllOpsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Uml Op To Del</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Op To Del</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Op To Del</em>' attribute.
	 * @see #setUmlOpToDel(Operation)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteOperationAction_UmlOpToDel()
	 * @model dataType="logicalSpecification.actions.UML.Operation" required="true"
	 * @generated
	 */
	Operation getUmlOpToDel();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLDeleteOperationAction#getUmlOpToDel <em>Uml Op To Del</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Op To Del</em>' attribute.
	 * @see #getUmlOpToDel()
	 * @generated
	 */
	void setUmlOpToDel(Operation value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log(); \n\t\tController.logger_.info(getUmlOpToDel().toString());'"
	 * @generated
	 */
	void log();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='getUmlOpToDel().destroy();'"
	 * @generated
	 */
	void execute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; delOpParams = new ArrayList&lt;&gt;();\n\n\t\tsetOpToDelSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getOperationQuery(getUmlOpToDel())));\n\t\tdelOpParams.add(getOpToDelSVP());\n\n\t\tsetAllOpsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getAllOperationsQuery()));\n\t\tdelOpParams.add(getAllOpsMVP());\n\n\t\tgetParameters().addAll(delOpParams);'"
	 * @generated
	 */
	void setParameters();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();\n\t\tFOLSpecification specification = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createFOLSpectification(\"DeleteOperationPreCondition\");\n\n\t\tAndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\n\t\tExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getOpToDelSVP(), getAllOpsMVP());\n\n\t\tpreAnd.getArguments().add(existsOperator);\n\n\t\tspecification.setRootOperator(preAnd);\n\t\tpreCondition.setConditionFormula(specification);\n\n\t\tsetPre(preCondition);'"
	 * @generated
	 */
	void createPreCondition();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();\n\t\tFOLSpecification specification = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createFOLSpectification(\"DeleteOperationPostCondition\");\n\n\t\tAndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\n\t\tNotOperator notOperator = Manager.getInstance(UMLManager.getInstance()).createNotOperator();\n\t\tExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getOpToDelSVP(), getAllOpsMVP());\n\t\tnotOperator.setArgument(existsOperator);\n\n\t\tpreAnd.getArguments().add(notOperator);\n\n\t\tspecification.setRootOperator(preAnd);\n\n\t\tpostCondition.setConditionFormula(specification);\n\n\t\tsetPost(postCondition);'"
	 * @generated
	 */
	void createPostCondition();

} // UMLDeleteOperationAction
