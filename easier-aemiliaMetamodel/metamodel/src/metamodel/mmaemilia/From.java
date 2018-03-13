/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>From</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.From#getFromInstance <em>From Instance</em>}</li>
 *   <li>{@link metamodel.mmaemilia.From#getIsOutput <em>Is Output</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.mmaemiliaPackage#getFrom()
 * @model
 * @generated
 */
public interface From extends EObject {
	/**
	 * Returns the value of the '<em><b>From Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Instance</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Instance</em>' reference.
	 * @see #setFromInstance(ArchiElemInstance)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getFrom_FromInstance()
	 * @model required="true"
	 * @generated
	 */
	ArchiElemInstance getFromInstance();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.From#getFromInstance <em>From Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Instance</em>' reference.
	 * @see #getFromInstance()
	 * @generated
	 */
	void setFromInstance(ArchiElemInstance value);

	/**
	 * Returns the value of the '<em><b>Is Output</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Output</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Output</em>' reference.
	 * @see #setIsOutput(OutputInteraction)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getFrom_IsOutput()
	 * @model required="true"
	 * @generated
	 */
	OutputInteraction getIsOutput();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.From#getIsOutput <em>Is Output</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Output</em>' reference.
	 * @see #getIsOutput()
	 * @generated
	 */
	void setIsOutput(OutputInteraction value);

} // From
