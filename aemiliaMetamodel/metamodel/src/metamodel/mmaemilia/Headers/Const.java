/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers;

import metamodel.mmaemilia.DataType.DataType;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Const</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.Const#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.Const#getConstantData <em>Constant Data</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Headers.HeadersPackage#getConst()
 * @model
 * @generated
 */
public interface Const extends EObject {
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
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getConst_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.Const#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Constant Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constant Data</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constant Data</em>' containment reference.
	 * @see #setConstantData(DataType)
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getConst_ConstantData()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DataType getConstantData();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.Const#getConstantData <em>Constant Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constant Data</em>' containment reference.
	 * @see #getConstantData()
	 * @generated
	 */
	void setConstantData(DataType value);

} // Const
