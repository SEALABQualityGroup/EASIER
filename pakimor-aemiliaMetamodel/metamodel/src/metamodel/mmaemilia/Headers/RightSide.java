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
 * A representation of the model object '<em><b>Right Side</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.RightSide#getLocalDef <em>Local Def</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Headers.HeadersPackage#getRightSide()
 * @model
 * @generated
 */
public interface RightSide extends EObject {
	/**
	 * Returns the value of the '<em><b>Local Def</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Headers.Local}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Def</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Def</em>' containment reference list.
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getRightSide_LocalDef()
	 * @model containment="true"
	 * @generated
	 */
	EList<Local> getLocalDef();

} // RightSide
