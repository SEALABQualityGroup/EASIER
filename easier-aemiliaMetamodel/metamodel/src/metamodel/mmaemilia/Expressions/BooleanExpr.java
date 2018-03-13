/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.BooleanExpr#getOperator <em>Operator</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.BooleanExpr#getLeftExprBool <em>Left Expr Bool</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.BooleanExpr#getRightExprBool <em>Right Expr Bool</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getBooleanExpr()
 * @model
 * @generated
 */
public interface BooleanExpr extends Expression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link metamodel.mmaemilia.Expressions.BoolOpName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.BoolOpName
	 * @see #setOperator(BoolOpName)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getBooleanExpr_Operator()
	 * @model required="true"
	 * @generated
	 */
	BoolOpName getOperator();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.BooleanExpr#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.BoolOpName
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(BoolOpName value);

	/**
	 * Returns the value of the '<em><b>Left Expr Bool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Expr Bool</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Expr Bool</em>' containment reference.
	 * @see #setLeftExprBool(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getBooleanExpr_LeftExprBool()
	 * @model containment="true"
	 * @generated
	 */
	Expression getLeftExprBool();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.BooleanExpr#getLeftExprBool <em>Left Expr Bool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Expr Bool</em>' containment reference.
	 * @see #getLeftExprBool()
	 * @generated
	 */
	void setLeftExprBool(Expression value);

	/**
	 * Returns the value of the '<em><b>Right Expr Bool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Expr Bool</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Expr Bool</em>' containment reference.
	 * @see #setRightExprBool(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getBooleanExpr_RightExprBool()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getRightExprBool();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.BooleanExpr#getRightExprBool <em>Right Expr Bool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Expr Bool</em>' containment reference.
	 * @see #getRightExprBool()
	 * @generated
	 */
	void setRightExprBool(Expression value);

} // BooleanExpr
