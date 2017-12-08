/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers;

import metamodel.mmaemilia.DataType.DataType;

import metamodel.mmaemilia.Expressions.Expression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Const Init</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.ConstInit#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.ConstInit#getInitConstData <em>Init Const Data</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.ConstInit#getInitConstExpr <em>Init Const Expr</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Headers.HeadersPackage#getConstInit()
 * @model
 * @generated
 */
public interface ConstInit extends EObject {
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
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getConstInit_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.ConstInit#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Init Const Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Const Data</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Const Data</em>' containment reference.
	 * @see #setInitConstData(DataType)
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getConstInit_InitConstData()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DataType getInitConstData();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.ConstInit#getInitConstData <em>Init Const Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init Const Data</em>' containment reference.
	 * @see #getInitConstData()
	 * @generated
	 */
	void setInitConstData(DataType value);

	/**
	 * Returns the value of the '<em><b>Init Const Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Const Expr</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Const Expr</em>' containment reference.
	 * @see #setInitConstExpr(Expression)
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getConstInit_InitConstExpr()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getInitConstExpr();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.ConstInit#getInitConstExpr <em>Init Const Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init Const Expr</em>' containment reference.
	 * @see #getInitConstExpr()
	 * @generated
	 */
	void setInitConstExpr(Expression value);

} // ConstInit
