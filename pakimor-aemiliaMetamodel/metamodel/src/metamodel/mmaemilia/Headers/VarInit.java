/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers;

import metamodel.mmaemilia.DataType.Normal;

import metamodel.mmaemilia.Expressions.Expression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Var Init</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.VarInit#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.VarInit#getInitVarExpr <em>Init Var Expr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.VarInit#getInitVarType <em>Init Var Type</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Headers.HeadersPackage#getVarInit()
 * @model
 * @generated
 */
public interface VarInit extends EObject {
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
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getVarInit_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.VarInit#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Init Var Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Var Expr</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Var Expr</em>' containment reference.
	 * @see #setInitVarExpr(Expression)
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getVarInit_InitVarExpr()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getInitVarExpr();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.VarInit#getInitVarExpr <em>Init Var Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init Var Expr</em>' containment reference.
	 * @see #getInitVarExpr()
	 * @generated
	 */
	void setInitVarExpr(Expression value);

	/**
	 * Returns the value of the '<em><b>Init Var Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Var Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Var Type</em>' containment reference.
	 * @see #setInitVarType(Normal)
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#getVarInit_InitVarType()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Normal getInitVarType();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Headers.VarInit#getInitVarType <em>Init Var Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init Var Type</em>' containment reference.
	 * @see #getInitVarType()
	 * @generated
	 */
	void setInitVarType(Normal value);

} // VarInit
