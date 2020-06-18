/**
 */
package logicalSpecification.actions.UML;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Move Operation Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getOpToMoveSVP <em>Op To Move SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getTargetCompSVP <em>Target Comp SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getAllOpsMVP <em>All Ops MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getAllCompsMVP <em>All Comps MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getAllTargetCompOpsMVP <em>All Target Comp Ops MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getUmlOpToMove <em>Uml Op To Move</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getUmlTargetComp <em>Uml Target Comp</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveOperationAction()
 * @model
 * @generated
 */
public interface UMLMoveOperationAction extends UMLMoveAction {
	/**
	 * Returns the value of the '<em><b>Op To Move SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Op To Move SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op To Move SVP</em>' reference.
	 * @see #setOpToMoveSVP(SingleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveOperationAction_OpToMoveSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getOpToMoveSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getOpToMoveSVP <em>Op To Move SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op To Move SVP</em>' reference.
	 * @see #getOpToMoveSVP()
	 * @generated
	 */
	void setOpToMoveSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Target Comp SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Comp SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Comp SVP</em>' reference.
	 * @see #setTargetCompSVP(SingleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveOperationAction_TargetCompSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getTargetCompSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getTargetCompSVP <em>Target Comp SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Comp SVP</em>' reference.
	 * @see #getTargetCompSVP()
	 * @generated
	 */
	void setTargetCompSVP(SingleValuedParameter value);

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
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveOperationAction_AllOpsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllOpsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getAllOpsMVP <em>All Ops MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Ops MVP</em>' reference.
	 * @see #getAllOpsMVP()
	 * @generated
	 */
	void setAllOpsMVP(MultipleValuedParameter value);

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
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveOperationAction_AllCompsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllCompsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getAllCompsMVP <em>All Comps MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Comps MVP</em>' reference.
	 * @see #getAllCompsMVP()
	 * @generated
	 */
	void setAllCompsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>All Target Comp Ops MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Target Comp Ops MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Target Comp Ops MVP</em>' reference.
	 * @see #setAllTargetCompOpsMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveOperationAction_AllTargetCompOpsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllTargetCompOpsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getAllTargetCompOpsMVP <em>All Target Comp Ops MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Target Comp Ops MVP</em>' reference.
	 * @see #getAllTargetCompOpsMVP()
	 * @generated
	 */
	void setAllTargetCompOpsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Uml Op To Move</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Op To Move</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Op To Move</em>' attribute.
	 * @see #setUmlOpToMove(Operation)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveOperationAction_UmlOpToMove()
	 * @model dataType="logicalSpecification.actions.UML.Operation" required="true"
	 * @generated
	 */
	Operation getUmlOpToMove();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getUmlOpToMove <em>Uml Op To Move</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Op To Move</em>' attribute.
	 * @see #getUmlOpToMove()
	 * @generated
	 */
	void setUmlOpToMove(Operation value);

	/**
	 * Returns the value of the '<em><b>Uml Target Comp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Target Comp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Target Comp</em>' attribute.
	 * @see #setUmlTargetComp(Component)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLMoveOperationAction_UmlTargetComp()
	 * @model dataType="logicalSpecification.actions.UML.Component" required="true"
	 * @generated
	 */
	Component getUmlTargetComp();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLMoveOperationAction#getUmlTargetComp <em>Uml Target Comp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Target Comp</em>' attribute.
	 * @see #getUmlTargetComp()
	 * @generated
	 */
	void setUmlTargetComp(Component value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log();\n\t\tController.logger_.info(getUmlTargetComp().toString());\n\t\tfor (Operation op : getUmlTargetComp().getOperations()) {\n\t\t\tController.logger_.info(op.toString());\n\t\t}'"
	 * @generated
	 */
	void log();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='getUmlOpToMove().setClass_(getUmlTargetComp());'"
	 * @generated
	 */
	void execute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; moveOpParams = new ArrayList&lt;&gt;();\n\n\t\tsetOpToMoveSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getOperationQuery(getUmlOpToMove())));\n\t\tmoveOpParams.add(getOpToMoveSVP());\n\n\t\tsetTargetCompSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getComponentQuery(getUmlTargetComp())));\n\t\tmoveOpParams.add(getTargetCompSVP());\n\n\t\tsetAllOpsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getAllOperationsQuery()));\n\t\tmoveOpParams.add(getAllOpsMVP());\n\n\t\tsetAllCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getAllComponentsQuery()));\n\t\tmoveOpParams.add(getAllCompsMVP());\n\n\t\tsetAllTargetCompOpsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getOperationsOfQuery(getUmlTargetComp())));\n\t\tmoveOpParams.add(getAllTargetCompOpsMVP());\n\n\t\tgetParameters().addAll(moveOpParams);'"
	 * @generated
	 */
	void setParameters();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();\n\t\tFOLSpecification specification = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createFOLSpectification(\"MoveOperationPreCondition\");\n\n\t\tExistsOperator existsOpInOperations = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getOpToMoveSVP(), getAllOpsMVP());\n\t\tExistsOperator existsTargetInComponents = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getTargetCompSVP(), getAllCompsMVP());\n\t\tExistsOperator existsOpInOpsOfTarget = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getOpToMoveSVP(), getAllTargetCompOpsMVP());\n\t\tNotOperator notExistsOpInOpsOfTarget = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createNotOperator(existsOpInOpsOfTarget);\n\n\t\tAndOperator andRoot = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\tandRoot.getArguments().add(existsOpInOperations);\n\t\tandRoot.getArguments().add(existsTargetInComponents);\n\t\tandRoot.getArguments().add(notExistsOpInOpsOfTarget);\n\n\t\tspecification.setRootOperator(andRoot);\n\t\tpreCondition.setConditionFormula(specification);\n\t\tsetPre(preCondition);'"
	 * @generated
	 */
	void createPreCondition();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();\n\t\tFOLSpecification specification = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createFOLSpectification(\"MoveOperationPostCondition\");\n\n\t\tExistsOperator existsOpInOperations = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getOpToMoveSVP(), getAllOpsMVP());\n\t\tExistsOperator existsTargetInComponents = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getTargetCompSVP(), getAllCompsMVP());\n\t\tExistsOperator existsOpInOpsOfTarget = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getOpToMoveSVP(), getAllTargetCompOpsMVP());\n\n\t\tAndOperator andRoot = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\tandRoot.getArguments().add(existsOpInOperations);\n\t\tandRoot.getArguments().add(existsTargetInComponents);\n\t\tandRoot.getArguments().add(existsOpInOpsOfTarget);\n\n\t\tspecification.setRootOperator(andRoot);\n\t\tpostCondition.setConditionFormula(specification);\n\t\tsetPost(postCondition);'"
	 * @generated
	 */
	void createPostCondition();

} // UMLMoveOperationAction
