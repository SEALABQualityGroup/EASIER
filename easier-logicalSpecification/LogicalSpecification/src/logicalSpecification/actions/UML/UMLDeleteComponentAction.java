/**
 */
package logicalSpecification.actions.UML;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.uml2.uml.Component;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delete Component Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.actions.UML.UMLDeleteComponentAction#getCompToDelSVP <em>Comp To Del SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLDeleteComponentAction#getAllCompsMVP <em>All Comps MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLDeleteComponentAction#getUmlCompToDel <em>Uml Comp To Del</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteComponentAction()
 * @model
 * @generated
 */
public interface UMLDeleteComponentAction extends UMLDeleteAction {
	/**
	 * Returns the value of the '<em><b>Comp To Del SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comp To Del SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comp To Del SVP</em>' reference.
	 * @see #setCompToDelSVP(SingleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteComponentAction_CompToDelSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getCompToDelSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLDeleteComponentAction#getCompToDelSVP <em>Comp To Del SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comp To Del SVP</em>' reference.
	 * @see #getCompToDelSVP()
	 * @generated
	 */
	void setCompToDelSVP(SingleValuedParameter value);

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
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteComponentAction_AllCompsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllCompsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLDeleteComponentAction#getAllCompsMVP <em>All Comps MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Comps MVP</em>' reference.
	 * @see #getAllCompsMVP()
	 * @generated
	 */
	void setAllCompsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Uml Comp To Del</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Comp To Del</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Comp To Del</em>' attribute.
	 * @see #setUmlCompToDel(Component)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLDeleteComponentAction_UmlCompToDel()
	 * @model dataType="logicalSpecification.actions.UML.Component" required="true"
	 * @generated
	 */
	Component getUmlCompToDel();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLDeleteComponentAction#getUmlCompToDel <em>Uml Comp To Del</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Comp To Del</em>' attribute.
	 * @see #getUmlCompToDel()
	 * @generated
	 */
	void setUmlCompToDel(Component value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log();\n\t\tController.logger_.info(this.toString());'"
	 * @generated
	 */
	void log();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Manifestation&gt; list_of_manifestations = ((UMLManager) Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.getMetamodelManager()).getAllManifestationsOf(getUmlCompToDel());\n\t\tfor (Manifestation man : list_of_manifestations) {\n\t\t\tif (man.getUtilizedElement().getNamespace() == getUmlCompToDel().getNamespace()) {\n\t\t\t\tman.destroy();\n\t\t\t}\n\t\t}\n\t\tumlCompToDel.destroy();'"
	 * @generated
	 */
	void execute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; delCompParams = new ArrayList&lt;&gt;();\n\n\t\tsetCompToDelSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getComponentQuery(getUmlCompToDel())));\n\t\tdelCompParams.add(getCompToDelSVP());\n\n\t\tsetAllCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getAllComponentsQuery()));\n\t\tdelCompParams.add(getAllCompsMVP());\n\n\t\tgetParameters().addAll(delCompParams);'"
	 * @generated
	 */
	void setParameters();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();\n\t\tFOLSpecification specification = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createFOLSpectification(\"DeleteComponentPreCondition\");\n\t\tAndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\tExistsOperator exists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getCompToDelSVP(),\n\t\t\t\tgetAllCompsMVP());\n\t\tpreAnd.getArguments().add(exists);\n\t\tspecification.setRootOperator(preAnd);\n\t\tpreCondition.setConditionFormula(specification);\n\t\tsetPre(preCondition);'"
	 * @generated
	 */
	void createPreCondition();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();\n\n\t\tFOLSpecification specification = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createFOLSpectification(\"DeleteComponentPostCondition\");\n\t\tAndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\tNotOperator notOperator = Manager.getInstance(UMLManager.getInstance()).createNotOperator();\n\t\tExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance())\n\t\t\t\t.createExistsOperator(getCompToDelSVP(), getAllCompsMVP());\n\t\tnotOperator.setArgument(existsOperator);\n\t\tpreAnd.getArguments().add(notOperator);\n\t\tspecification.setRootOperator(preAnd);\n\t\tpostCondition.setConditionFormula(specification);\n\t\tsetPost(postCondition);'"
	 * @generated
	 */
	void createPostCondition();

} // UMLDeleteComponentAction
