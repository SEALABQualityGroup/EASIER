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
 * A representation of the model object '<em><b>To</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.To#getToInstance <em>To Instance</em>}</li>
 *   <li>{@link metamodel.mmaemilia.To#getIsInput <em>Is Input</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.mmaemiliaPackage#getTo()
 * @model
 * @generated
 */
public interface To extends EObject {
	/**
	 * Returns the value of the '<em><b>To Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Instance</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Instance</em>' reference.
	 * @see #setToInstance(ArchiElemInstance)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getTo_ToInstance()
	 * @model required="true"
	 * @generated
	 */
	ArchiElemInstance getToInstance();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.To#getToInstance <em>To Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Instance</em>' reference.
	 * @see #getToInstance()
	 * @generated
	 */
	void setToInstance(ArchiElemInstance value);

	/**
	 * Returns the value of the '<em><b>Is Input</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Input</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Input</em>' reference.
	 * @see #setIsInput(InputInteraction)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getTo_IsInput()
	 * @model required="true"
	 * @generated
	 */
	InputInteraction getIsInput();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.To#getIsInput <em>Is Input</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Input</em>' reference.
	 * @see #getIsInput()
	 * @generated
	 */
	void setIsInput(InputInteraction value);

} // To
