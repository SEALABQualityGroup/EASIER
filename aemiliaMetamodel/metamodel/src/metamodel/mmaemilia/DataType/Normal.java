/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType;

import metamodel.mmaemilia.Headers.Const;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Normal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.DataType.Normal#getVar <em>Var</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.DataType.DataTypePackage#getNormal()
 * @model
 * @generated
 */
public interface Normal extends DataType {

	/**
	 * Returns the value of the '<em><b>Var</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Var</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Var</em>' reference.
	 * @see #setVar(Const)
	 * @see metamodel.mmaemilia.DataType.DataTypePackage#getNormal_Var()
	 * @model
	 * @generated
	 */
	Const getVar();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.DataType.Normal#getVar <em>Var</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Var</em>' reference.
	 * @see #getVar()
	 * @generated
	 */
	void setVar(Const value);
} // Normal
