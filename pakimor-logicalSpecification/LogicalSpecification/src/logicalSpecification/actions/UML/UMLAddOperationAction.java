/**
 */
package logicalSpecification.actions.UML;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Operation Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddOperationAction#getOpToAddSVP <em>Op To Add SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddOperationAction#getTargetCompSVP <em>Target Comp SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddOperationAction#getAllCompsMVP <em>All Comps MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddOperationAction#getTargetCompOpsMVP <em>Target Comp Ops MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddOperationAction#getUmlOpToAdd <em>Uml Op To Add</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddOperationAction#getUmlTargetComp <em>Uml Target Comp</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddOperationAction()
 * @model
 * @generated
 */
public interface UMLAddOperationAction extends UMLAddAction {
	/**
	 * Returns the value of the '<em><b>Op To Add SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Op To Add SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op To Add SVP</em>' reference.
	 * @see #setOpToAddSVP(SingleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddOperationAction_OpToAddSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getOpToAddSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddOperationAction#getOpToAddSVP <em>Op To Add SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op To Add SVP</em>' reference.
	 * @see #getOpToAddSVP()
	 * @generated
	 */
	void setOpToAddSVP(SingleValuedParameter value);

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
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddOperationAction_TargetCompSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getTargetCompSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddOperationAction#getTargetCompSVP <em>Target Comp SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Comp SVP</em>' reference.
	 * @see #getTargetCompSVP()
	 * @generated
	 */
	void setTargetCompSVP(SingleValuedParameter value);

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
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddOperationAction_AllCompsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllCompsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddOperationAction#getAllCompsMVP <em>All Comps MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Comps MVP</em>' reference.
	 * @see #getAllCompsMVP()
	 * @generated
	 */
	void setAllCompsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Target Comp Ops MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Comp Ops MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Comp Ops MVP</em>' reference.
	 * @see #setTargetCompOpsMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddOperationAction_TargetCompOpsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getTargetCompOpsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddOperationAction#getTargetCompOpsMVP <em>Target Comp Ops MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Comp Ops MVP</em>' reference.
	 * @see #getTargetCompOpsMVP()
	 * @generated
	 */
	void setTargetCompOpsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Uml Op To Add</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Op To Add</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Op To Add</em>' attribute.
	 * @see #setUmlOpToAdd(Operation)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddOperationAction_UmlOpToAdd()
	 * @model dataType="logicalSpecification.actions.UML.Operation" required="true"
	 * @generated
	 */
	Operation getUmlOpToAdd();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddOperationAction#getUmlOpToAdd <em>Uml Op To Add</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Op To Add</em>' attribute.
	 * @see #getUmlOpToAdd()
	 * @generated
	 */
	void setUmlOpToAdd(Operation value);

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
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddOperationAction_UmlTargetComp()
	 * @model dataType="logicalSpecification.actions.UML.Component" required="true"
	 * @generated
	 */
	Component getUmlTargetComp();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddOperationAction#getUmlTargetComp <em>Uml Target Comp</em>}' attribute.
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
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log();\n\t\tif (umlOpToAdd != null)\n\t\t\tController.logger_.info(umlOpToAdd.toString());\n\n\t\tfor (Operation op : umlTargetComp.getOperations()) {\n\t\t\tController.logger_.info(op.getName());\n\t\t}'"
	 * @generated
	 */
	void log();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='\n\t\t/**\n\t\t * Operation createOwnedOperation(String name, EList&lt;String&gt;\n\t\t * parameterNames, EList&lt;Type&gt; parameterTypes, Type returnType);\n\t\t * \n\t\t \052/\n\t\tumlOpToAdd = umlTargetComp.createOwnedOperation(\"newOp\" + Math.random(), null, null);\n'"
	 * @generated
	 */
	void execute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='// ACTION add PARAMETERS\n\t\tList&lt;Parameter&gt; addParams = new ArrayList&lt;&gt;();\n\t\t// FIXME le add non dovrebbero avere come attributo l\'oggetto da creare\n\n\t\tif (umlOpToAdd != null) {\n\t\t\tsetOpToAddSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(\n\t\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t\t.getOperationQuery(umlOpToAdd)));\n\t\t\taddParams.add(getOpToAddSVP());\n\t\t}\n\n\t\tsetTargetCompSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getComponentQuery(getUmlTargetComp())));\n\t\taddParams.add(getTargetCompSVP());\n\n\t\tsetAllCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getAllComponentsQuery()));\n\t\taddParams.add(getAllCompsMVP());\n\n\t\tsetTargetCompOpsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getOperationsOfQuery(getUmlTargetComp())));\n\t\taddParams.add(getTargetCompOpsMVP());\n\n\t\tgetParameters().addAll(addParams);'"
	 * @generated
	 */
	void setParameters();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PreCondition preCondition = LogicalSpecificationFactory.eINSTANCE.createPreCondition();\n\t\tFOLSpecification addPreSpecification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification(\"AddOperationPreCondition\");\n\t\tAndOperator addPreAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\n\t\tExistsOperator addPreAndExists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getTargetCompSVP(), getAllCompsMVP());\n\t\taddPreAnd.getArguments().add(addPreAndExists);\n\n\t\t// NotOperator addPreAndNot = Manager.createNotOperator();\n\t\t// ExistsOperator addPreAndNotExists =\n\t\t// Manager.createExistsOperator(getNewOpSVP(), getTargetCompOps());\n\t\t// addPreAndNot.setArgument(addPreAndNotExists);\n\t\t// addPreAnd.getArguments().add(addPreAndNot);\n\n\t\taddPreSpecification.setRootOperator(addPreAnd);\n\t\tpreCondition.setConditionFormula(addPreSpecification);\n\t\tsetPre(preCondition);'"
	 * @generated
	 */
	void createPreCondition();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PostCondition postCondition = LogicalSpecificationFactory.eINSTANCE.createPostCondition();\n\t\tFOLSpecification addPreSpecification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification(\"AddOperationPostCondition\");\n\t\tAndOperator addPreAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\n\t\tExistsOperator addPreAndExists1 = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getTargetCompSVP(), getAllCompsMVP());\n\t\taddPreAnd.getArguments().add(addPreAndExists1);\n\n\t\tExistsOperator addPreAndExists2 = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getOpToAddSVP(), getTargetCompOpsMVP());\n\t\taddPreAnd.getArguments().add(addPreAndExists2);\n\n\t\taddPreSpecification.setRootOperator(addPreAnd);\n\t\tpostCondition.setConditionFormula(addPreSpecification);\n\t\tsetPost(postCondition);'"
	 * @generated
	 */
	void createPostCondition();

} // UMLAddOperationAction
