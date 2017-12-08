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
 * A representation of the model object '<em><b>Attachment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Attachment#getEnd <em>End</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Attachment#getStart <em>Start</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.mmaemiliaPackage#getAttachment()
 * @model
 * @generated
 */
public interface Attachment extends EObject {
	/**
	 * Returns the value of the '<em><b>End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' containment reference.
	 * @see #setEnd(To)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getAttachment_End()
	 * @model containment="true" required="true"
	 * @generated
	 */
	To getEnd();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Attachment#getEnd <em>End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' containment reference.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(To value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' containment reference.
	 * @see #setStart(From)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getAttachment_Start()
	 * @model containment="true" required="true"
	 * @generated
	 */
	From getStart();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Attachment#getStart <em>Start</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' containment reference.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(From value);

} // Attachment
