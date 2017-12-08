/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behav Header</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.BehavHeader#getLeft <em>Left</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.BehavHeader#getRight <em>Right</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Headers.HeadersPackage#getBehavHeader()
 * @model
 * @generated
 */
public interface BehavHeader extends EObject {
	/**
	 * Returns the value of the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left</em>' containment reference.
	 * @see #setLeft(LeftSide)
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getBehavHeader_Left()
	 * @model containment="true"
	 * @generated
	 */
	LeftSide getLeft();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.BehavHeader#getLeft <em>Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' containment reference.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(LeftSide value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' containment reference.
	 * @see #setRight(RightSide)
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getBehavHeader_Right()
	 * @model containment="true"
	 * @generated
	 */
	RightSide getRight();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.BehavHeader#getRight <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' containment reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(RightSide value);

} // BehavHeader
