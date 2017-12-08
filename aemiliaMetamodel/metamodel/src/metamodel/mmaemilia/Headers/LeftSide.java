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
 * A representation of the model object '<em><b>Left Side</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.LeftSide#getInitVar <em>Init Var</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.LeftSide#getVarDef <em>Var Def</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Headers.HeadersPackage#getLeftSide()
 * @model
 * @generated
 */
public interface LeftSide extends EObject {
	/**
	 * Returns the value of the '<em><b>Init Var</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Headers.VarInit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Var</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Var</em>' containment reference list.
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getLeftSide_InitVar()
	 * @model containment="true"
	 * @generated
	 */
	EList<VarInit> getInitVar();

	/**
	 * Returns the value of the '<em><b>Var Def</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Headers.Var}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Var Def</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Var Def</em>' containment reference list.
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getLeftSide_VarDef()
	 * @model containment="true"
	 * @generated
	 */
	EList<Var> getVarDef();

} // LeftSide
