/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AT Header</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.AT_Header#getInitConst <em>Init Const</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Headers.HeadersPackage#getAT_Header()
 * @model
 * @generated
 */
public interface AT_Header extends EObject {
	/**
	 * Returns the value of the '<em><b>Init Const</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Headers.ConstInit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Const</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Const</em>' containment reference list.
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getAT_Header_InitConst()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConstInit> getInitConst();

} // AT_Header
