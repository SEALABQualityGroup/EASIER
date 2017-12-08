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
 * A representation of the model object '<em><b>Array Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.ArrayExpr#getOperation <em>Operation</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.ArrayExpr#getArray_operands <em>Array operands</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getArrayExpr()
 * @model
 * @generated
 */
public interface ArrayExpr extends Expression {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' attribute.
	 * The literals are from the enumeration {@link metamodel.mmaemilia.Expressions.ArrayOpName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.ArrayOpName
	 * @see #setOperation(ArrayOpName)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getArrayExpr_Operation()
	 * @model required="true"
	 * @generated
	 */
	ArrayOpName getOperation();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.ArrayExpr#getOperation <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.ArrayOpName
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(ArrayOpName value);

	/**
	 * Returns the value of the '<em><b>Array operands</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Expressions.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array operands</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array operands</em>' containment reference list.
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getArrayExpr_Array_operands()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getArray_operands();

} // ArrayExpr
