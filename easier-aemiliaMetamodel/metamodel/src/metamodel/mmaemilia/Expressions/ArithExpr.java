/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arith Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.ArithExpr#getOperator <em>Operator</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.ArithExpr#getLeftExprArith <em>Left Expr Arith</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.ArithExpr#getRightExprArith <em>Right Expr Arith</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getArithExpr()
 * @model
 * @generated
 */
public interface ArithExpr extends Expression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link metamodel.mmaemilia.Expressions.ArithOpName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.ArithOpName
	 * @see #setOperator(ArithOpName)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getArithExpr_Operator()
	 * @model required="true"
	 * @generated
	 */
	ArithOpName getOperator();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.ArithExpr#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.ArithOpName
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(ArithOpName value);

	/**
	 * Returns the value of the '<em><b>Left Expr Arith</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Expr Arith</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Expr Arith</em>' containment reference.
	 * @see #setLeftExprArith(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getArithExpr_LeftExprArith()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getLeftExprArith();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.ArithExpr#getLeftExprArith <em>Left Expr Arith</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Expr Arith</em>' containment reference.
	 * @see #getLeftExprArith()
	 * @generated
	 */
	void setLeftExprArith(Expression value);

	/**
	 * Returns the value of the '<em><b>Right Expr Arith</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Expr Arith</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Expr Arith</em>' containment reference.
	 * @see #setRightExprArith(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getArithExpr_RightExprArith()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getRightExprArith();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.ArithExpr#getRightExprArith <em>Right Expr Arith</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Expr Arith</em>' containment reference.
	 * @see #getRightExprArith()
	 * @generated
	 */
	void setRightExprArith(Expression value);

} // ArithExpr
