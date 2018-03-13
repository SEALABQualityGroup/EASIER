/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.ListExpr#getOperation <em>Operation</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.ListExpr#getList_operands <em>List operands</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getListExpr()
 * @model
 * @generated
 */
public interface ListExpr extends Expression {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' attribute.
	 * The literals are from the enumeration {@link metamodel.mmaemilia.Expressions.ListOpName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.ListOpName
	 * @see #setOperation(ListOpName)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getListExpr_Operation()
	 * @model required="true"
	 * @generated
	 */
	ListOpName getOperation();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.ListExpr#getOperation <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.ListOpName
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(ListOpName value);

	/**
	 * Returns the value of the '<em><b>List operands</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Expressions.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List operands</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>List operands</em>' containment reference list.
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getListExpr_List_operands()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getList_operands();

} // ListExpr
