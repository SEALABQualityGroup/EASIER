/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers;

import metamodel.mmaemilia.DataType.Normal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Var</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.Var#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.Var#getVarType <em>Var Type</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Headers.HeadersPackage#getVar()
 * @model
 * @generated
 */
public interface Var extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getVar_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.Var#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Var Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Var Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Var Type</em>' containment reference.
	 * @see #setVarType(Normal)
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getVar_VarType()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Normal getVarType();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.Var#getVarType <em>Var Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Var Type</em>' containment reference.
	 * @see #getVarType()
	 * @generated
	 */
	void setVarType(Normal value);

} // Var
