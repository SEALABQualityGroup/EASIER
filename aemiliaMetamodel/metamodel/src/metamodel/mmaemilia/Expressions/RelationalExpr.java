/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relational Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.RelationalExpr#getOperator <em>Operator</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.RelationalExpr#getLeftExprRel <em>Left Expr Rel</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.RelationalExpr#getRightExprRel <em>Right Expr Rel</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getRelationalExpr()
 * @model
 * @generated
 */
public interface RelationalExpr extends Expression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link metamodel.mmaemilia.Expressions.RelatOpName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.RelatOpName
	 * @see #setOperator(RelatOpName)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getRelationalExpr_Operator()
	 * @model required="true"
	 * @generated
	 */
	RelatOpName getOperator();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.RelationalExpr#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.RelatOpName
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(RelatOpName value);

	/**
	 * Returns the value of the '<em><b>Left Expr Rel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Expr Rel</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Expr Rel</em>' containment reference.
	 * @see #setLeftExprRel(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getRelationalExpr_LeftExprRel()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getLeftExprRel();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.RelationalExpr#getLeftExprRel <em>Left Expr Rel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Expr Rel</em>' containment reference.
	 * @see #getLeftExprRel()
	 * @generated
	 */
	void setLeftExprRel(Expression value);

	/**
	 * Returns the value of the '<em><b>Right Expr Rel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Expr Rel</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Expr Rel</em>' containment reference.
	 * @see #setRightExprRel(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getRelationalExpr_RightExprRel()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getRightExprRel();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.RelationalExpr#getRightExprRel <em>Right Expr Rel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Expr Rel</em>' containment reference.
	 * @see #getRightExprRel()
	 * @generated
	 */
	void setRightExprRel(Expression value);

} // RelationalExpr
