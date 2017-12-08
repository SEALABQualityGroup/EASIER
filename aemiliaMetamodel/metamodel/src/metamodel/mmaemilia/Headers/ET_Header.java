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
 * A representation of the model object '<em><b>ET Header</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.ET_Header#getCostant <em>Costant</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Headers.HeadersPackage#getET_Header()
 * @model
 * @generated
 */
public interface ET_Header extends EObject {
	/**
	 * Returns the value of the '<em><b>Costant</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Headers.Const}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Costant</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Costant</em>' containment reference list.
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getET_Header_Costant()
	 * @model containment="true"
	 * @generated
	 */
	EList<Const> getCostant();

} // ET_Header
