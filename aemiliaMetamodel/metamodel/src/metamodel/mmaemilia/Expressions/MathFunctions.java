/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Functions</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.MathFunctions#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.MathFunctions#getLeftExprMath <em>Left Expr Math</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.MathFunctions#getRightExprMath <em>Right Expr Math</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getMathFunctions()
 * @model
 * @generated
 */
public interface MathFunctions extends Expression {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The literals are from the enumeration {@link metamodel.mmaemilia.Expressions.MathFuncName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.MathFuncName
	 * @see #setName(MathFuncName)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getMathFunctions_Name()
	 * @model required="true"
	 * @generated
	 */
	MathFuncName getName();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.MathFunctions#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.MathFuncName
	 * @see #getName()
	 * @generated
	 */
	void setName(MathFuncName value);

	/**
	 * Returns the value of the '<em><b>Left Expr Math</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Expr Math</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Expr Math</em>' containment reference.
	 * @see #setLeftExprMath(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getMathFunctions_LeftExprMath()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getLeftExprMath();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.MathFunctions#getLeftExprMath <em>Left Expr Math</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Expr Math</em>' containment reference.
	 * @see #getLeftExprMath()
	 * @generated
	 */
	void setLeftExprMath(Expression value);

	/**
	 * Returns the value of the '<em><b>Right Expr Math</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Expr Math</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Expr Math</em>' containment reference.
	 * @see #setRightExprMath(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getMathFunctions_RightExprMath()
	 * @model containment="true"
	 * @generated
	 */
	Expression getRightExprMath();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.MathFunctions#getRightExprMath <em>Right Expr Math</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Expr Math</em>' containment reference.
	 * @see #getRightExprMath()
	 * @generated
	 */
	void setRightExprMath(Expression value);

} // MathFunctions
