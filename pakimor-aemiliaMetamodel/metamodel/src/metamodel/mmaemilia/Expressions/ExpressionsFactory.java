/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage
 * @generated
 */
public interface ExpressionsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExpressionsFactory eINSTANCE = metamodel.mmaemilia.Expressions.impl.ExpressionsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expression</em>'.
	 * @generated
	 */
	Expression createExpression();

	/**
	 * Returns a new object of class '<em>List Expr</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>List Expr</em>'.
	 * @generated
	 */
	ListExpr createListExpr();

	/**
	 * Returns a new object of class '<em>Array Expr</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array Expr</em>'.
	 * @generated
	 */
	ArrayExpr createArrayExpr();

	/**
	 * Returns a new object of class '<em>Record Expr</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Record Expr</em>'.
	 * @generated
	 */
	RecordExpr createRecordExpr();

	/**
	 * Returns a new object of class '<em>Ident Expr</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ident Expr</em>'.
	 * @generated
	 */
	IdentExpr createIdentExpr();

	/**
	 * Returns a new object of class '<em>Math Functions</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Math Functions</em>'.
	 * @generated
	 */
	MathFunctions createMathFunctions();

	/**
	 * Returns a new object of class '<em>Relational Expr</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Relational Expr</em>'.
	 * @generated
	 */
	RelationalExpr createRelationalExpr();

	/**
	 * Returns a new object of class '<em>Arith Expr</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Arith Expr</em>'.
	 * @generated
	 */
	ArithExpr createArithExpr();

	/**
	 * Returns a new object of class '<em>Pseudo random num gen</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pseudo random num gen</em>'.
	 * @generated
	 */
	Pseudo_random_num_gen createPseudo_random_num_gen();

	/**
	 * Returns a new object of class '<em>Boolean Expr</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Expr</em>'.
	 * @generated
	 */
	BooleanExpr createBooleanExpr();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExpressionsPackage getExpressionsPackage();

} //ExpressionsFactory
