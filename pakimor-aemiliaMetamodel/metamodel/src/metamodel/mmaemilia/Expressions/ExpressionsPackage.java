/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Expressions.ExpressionsFactory
 * @model kind="package"
 * @generated
 */
public interface ExpressionsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Expressions";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://expressions.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Expressions";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExpressionsPackage eINSTANCE = metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl.init();

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionImpl
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.impl.ListExprImpl <em>List Expr</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.impl.ListExprImpl
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getListExpr()
	 * @generated
	 */
	int LIST_EXPR = 1;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPR__OPERATION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>List operands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPR__LIST_OPERANDS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>List Expr</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.impl.ArrayExprImpl <em>Array Expr</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.impl.ArrayExprImpl
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getArrayExpr()
	 * @generated
	 */
	int ARRAY_EXPR = 2;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_EXPR__OPERATION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Array operands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_EXPR__ARRAY_OPERANDS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Array Expr</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_EXPR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.impl.RecordExprImpl <em>Record Expr</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.impl.RecordExprImpl
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getRecordExpr()
	 * @generated
	 */
	int RECORD_EXPR = 3;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECORD_EXPR__OPERATION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Record operands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECORD_EXPR__RECORD_OPERANDS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Record Expr</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECORD_EXPR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.impl.IdentExprImpl <em>Ident Expr</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.impl.IdentExprImpl
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getIdentExpr()
	 * @generated
	 */
	int IDENT_EXPR = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENT_EXPR__NAME = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENT_EXPR__TYPE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Ident Expr</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENT_EXPR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.impl.MathFunctionsImpl <em>Math Functions</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.impl.MathFunctionsImpl
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getMathFunctions()
	 * @generated
	 */
	int MATH_FUNCTIONS = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATH_FUNCTIONS__NAME = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Left Expr Math</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATH_FUNCTIONS__LEFT_EXPR_MATH = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Expr Math</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATH_FUNCTIONS__RIGHT_EXPR_MATH = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Math Functions</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATH_FUNCTIONS_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.impl.RelationalExprImpl <em>Relational Expr</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.impl.RelationalExprImpl
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getRelationalExpr()
	 * @generated
	 */
	int RELATIONAL_EXPR = 6;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_EXPR__OPERATOR = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Left Expr Rel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_EXPR__LEFT_EXPR_REL = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Expr Rel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_EXPR__RIGHT_EXPR_REL = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Relational Expr</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_EXPR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.impl.ArithExprImpl <em>Arith Expr</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.impl.ArithExprImpl
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getArithExpr()
	 * @generated
	 */
	int ARITH_EXPR = 7;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITH_EXPR__OPERATOR = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Left Expr Arith</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITH_EXPR__LEFT_EXPR_ARITH = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Expr Arith</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITH_EXPR__RIGHT_EXPR_ARITH = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Arith Expr</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITH_EXPR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.impl.Pseudo_random_num_genImpl <em>Pseudo random num gen</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.impl.Pseudo_random_num_genImpl
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getPseudo_random_num_gen()
	 * @generated
	 */
	int PSEUDO_RANDOM_NUM_GEN = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_RANDOM_NUM_GEN__NAME = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>First Num Gen Op</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Second Num Gen Op</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Third Num Gen Op</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Pseudo random num gen</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_RANDOM_NUM_GEN_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.impl.BooleanExprImpl <em>Boolean Expr</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.impl.BooleanExprImpl
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getBooleanExpr()
	 * @generated
	 */
	int BOOLEAN_EXPR = 9;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXPR__OPERATOR = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Left Expr Bool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXPR__LEFT_EXPR_BOOL = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Expr Bool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXPR__RIGHT_EXPR_BOOL = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Boolean Expr</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_EXPR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.BoolOpName <em>Bool Op Name</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.BoolOpName
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getBoolOpName()
	 * @generated
	 */
	int BOOL_OP_NAME = 10;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.MathFuncName <em>Math Func Name</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.MathFuncName
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getMathFuncName()
	 * @generated
	 */
	int MATH_FUNC_NAME = 11;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.RecordOpName <em>Record Op Name</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.RecordOpName
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getRecordOpName()
	 * @generated
	 */
	int RECORD_OP_NAME = 12;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.RelatOpName <em>Relat Op Name</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.RelatOpName
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getRelatOpName()
	 * @generated
	 */
	int RELAT_OP_NAME = 13;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.IdentifierType <em>Identifier Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.IdentifierType
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getIdentifierType()
	 * @generated
	 */
	int IDENTIFIER_TYPE = 14;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.ArithOpName <em>Arith Op Name</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.ArithOpName
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getArithOpName()
	 * @generated
	 */
	int ARITH_OP_NAME = 15;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.ArrayOpName <em>Array Op Name</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.ArrayOpName
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getArrayOpName()
	 * @generated
	 */
	int ARRAY_OP_NAME = 16;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.PseudoNumName <em>Pseudo Num Name</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.PseudoNumName
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getPseudoNumName()
	 * @generated
	 */
	int PSEUDO_NUM_NAME = 17;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Expressions.ListOpName <em>List Op Name</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Expressions.ListOpName
	 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getListOpName()
	 * @generated
	 */
	int LIST_OP_NAME = 18;


	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Expressions.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see metamodel.mmaemilia.Expressions.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Expressions.ListExpr <em>List Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Expr</em>'.
	 * @see metamodel.mmaemilia.Expressions.ListExpr
	 * @generated
	 */
	EClass getListExpr();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Expressions.ListExpr#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation</em>'.
	 * @see metamodel.mmaemilia.Expressions.ListExpr#getOperation()
	 * @see #getListExpr()
	 * @generated
	 */
	EAttribute getListExpr_Operation();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.Expressions.ListExpr#getList_operands <em>List operands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>List operands</em>'.
	 * @see metamodel.mmaemilia.Expressions.ListExpr#getList_operands()
	 * @see #getListExpr()
	 * @generated
	 */
	EReference getListExpr_List_operands();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Expressions.ArrayExpr <em>Array Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Expr</em>'.
	 * @see metamodel.mmaemilia.Expressions.ArrayExpr
	 * @generated
	 */
	EClass getArrayExpr();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Expressions.ArrayExpr#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation</em>'.
	 * @see metamodel.mmaemilia.Expressions.ArrayExpr#getOperation()
	 * @see #getArrayExpr()
	 * @generated
	 */
	EAttribute getArrayExpr_Operation();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.Expressions.ArrayExpr#getArray_operands <em>Array operands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Array operands</em>'.
	 * @see metamodel.mmaemilia.Expressions.ArrayExpr#getArray_operands()
	 * @see #getArrayExpr()
	 * @generated
	 */
	EReference getArrayExpr_Array_operands();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Expressions.RecordExpr <em>Record Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Record Expr</em>'.
	 * @see metamodel.mmaemilia.Expressions.RecordExpr
	 * @generated
	 */
	EClass getRecordExpr();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Expressions.RecordExpr#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation</em>'.
	 * @see metamodel.mmaemilia.Expressions.RecordExpr#getOperation()
	 * @see #getRecordExpr()
	 * @generated
	 */
	EAttribute getRecordExpr_Operation();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.Expressions.RecordExpr#getRecord_operands <em>Record operands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Record operands</em>'.
	 * @see metamodel.mmaemilia.Expressions.RecordExpr#getRecord_operands()
	 * @see #getRecordExpr()
	 * @generated
	 */
	EReference getRecordExpr_Record_operands();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Expressions.IdentExpr <em>Ident Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ident Expr</em>'.
	 * @see metamodel.mmaemilia.Expressions.IdentExpr
	 * @generated
	 */
	EClass getIdentExpr();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Expressions.IdentExpr#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.IdentExpr#getName()
	 * @see #getIdentExpr()
	 * @generated
	 */
	EAttribute getIdentExpr_Name();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Expressions.IdentExpr#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see metamodel.mmaemilia.Expressions.IdentExpr#getType()
	 * @see #getIdentExpr()
	 * @generated
	 */
	EAttribute getIdentExpr_Type();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Expressions.MathFunctions <em>Math Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Math Functions</em>'.
	 * @see metamodel.mmaemilia.Expressions.MathFunctions
	 * @generated
	 */
	EClass getMathFunctions();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Expressions.MathFunctions#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.MathFunctions#getName()
	 * @see #getMathFunctions()
	 * @generated
	 */
	EAttribute getMathFunctions_Name();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.MathFunctions#getLeftExprMath <em>Left Expr Math</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Expr Math</em>'.
	 * @see metamodel.mmaemilia.Expressions.MathFunctions#getLeftExprMath()
	 * @see #getMathFunctions()
	 * @generated
	 */
	EReference getMathFunctions_LeftExprMath();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.MathFunctions#getRightExprMath <em>Right Expr Math</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Expr Math</em>'.
	 * @see metamodel.mmaemilia.Expressions.MathFunctions#getRightExprMath()
	 * @see #getMathFunctions()
	 * @generated
	 */
	EReference getMathFunctions_RightExprMath();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Expressions.RelationalExpr <em>Relational Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relational Expr</em>'.
	 * @see metamodel.mmaemilia.Expressions.RelationalExpr
	 * @generated
	 */
	EClass getRelationalExpr();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Expressions.RelationalExpr#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see metamodel.mmaemilia.Expressions.RelationalExpr#getOperator()
	 * @see #getRelationalExpr()
	 * @generated
	 */
	EAttribute getRelationalExpr_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.RelationalExpr#getLeftExprRel <em>Left Expr Rel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Expr Rel</em>'.
	 * @see metamodel.mmaemilia.Expressions.RelationalExpr#getLeftExprRel()
	 * @see #getRelationalExpr()
	 * @generated
	 */
	EReference getRelationalExpr_LeftExprRel();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.RelationalExpr#getRightExprRel <em>Right Expr Rel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Expr Rel</em>'.
	 * @see metamodel.mmaemilia.Expressions.RelationalExpr#getRightExprRel()
	 * @see #getRelationalExpr()
	 * @generated
	 */
	EReference getRelationalExpr_RightExprRel();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Expressions.ArithExpr <em>Arith Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arith Expr</em>'.
	 * @see metamodel.mmaemilia.Expressions.ArithExpr
	 * @generated
	 */
	EClass getArithExpr();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Expressions.ArithExpr#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see metamodel.mmaemilia.Expressions.ArithExpr#getOperator()
	 * @see #getArithExpr()
	 * @generated
	 */
	EAttribute getArithExpr_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.ArithExpr#getLeftExprArith <em>Left Expr Arith</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Expr Arith</em>'.
	 * @see metamodel.mmaemilia.Expressions.ArithExpr#getLeftExprArith()
	 * @see #getArithExpr()
	 * @generated
	 */
	EReference getArithExpr_LeftExprArith();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.ArithExpr#getRightExprArith <em>Right Expr Arith</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Expr Arith</em>'.
	 * @see metamodel.mmaemilia.Expressions.ArithExpr#getRightExprArith()
	 * @see #getArithExpr()
	 * @generated
	 */
	EReference getArithExpr_RightExprArith();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen <em>Pseudo random num gen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pseudo random num gen</em>'.
	 * @see metamodel.mmaemilia.Expressions.Pseudo_random_num_gen
	 * @generated
	 */
	EClass getPseudo_random_num_gen();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getName()
	 * @see #getPseudo_random_num_gen()
	 * @generated
	 */
	EAttribute getPseudo_random_num_gen_Name();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getFirstNumGenOp <em>First Num Gen Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>First Num Gen Op</em>'.
	 * @see metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getFirstNumGenOp()
	 * @see #getPseudo_random_num_gen()
	 * @generated
	 */
	EReference getPseudo_random_num_gen_FirstNumGenOp();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getSecondNumGenOp <em>Second Num Gen Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Second Num Gen Op</em>'.
	 * @see metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getSecondNumGenOp()
	 * @see #getPseudo_random_num_gen()
	 * @generated
	 */
	EReference getPseudo_random_num_gen_SecondNumGenOp();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getThirdNumGenOp <em>Third Num Gen Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Third Num Gen Op</em>'.
	 * @see metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getThirdNumGenOp()
	 * @see #getPseudo_random_num_gen()
	 * @generated
	 */
	EReference getPseudo_random_num_gen_ThirdNumGenOp();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Expressions.BooleanExpr <em>Boolean Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Expr</em>'.
	 * @see metamodel.mmaemilia.Expressions.BooleanExpr
	 * @generated
	 */
	EClass getBooleanExpr();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Expressions.BooleanExpr#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see metamodel.mmaemilia.Expressions.BooleanExpr#getOperator()
	 * @see #getBooleanExpr()
	 * @generated
	 */
	EAttribute getBooleanExpr_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.BooleanExpr#getLeftExprBool <em>Left Expr Bool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Expr Bool</em>'.
	 * @see metamodel.mmaemilia.Expressions.BooleanExpr#getLeftExprBool()
	 * @see #getBooleanExpr()
	 * @generated
	 */
	EReference getBooleanExpr_LeftExprBool();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Expressions.BooleanExpr#getRightExprBool <em>Right Expr Bool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Expr Bool</em>'.
	 * @see metamodel.mmaemilia.Expressions.BooleanExpr#getRightExprBool()
	 * @see #getBooleanExpr()
	 * @generated
	 */
	EReference getBooleanExpr_RightExprBool();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.Expressions.BoolOpName <em>Bool Op Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Bool Op Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.BoolOpName
	 * @generated
	 */
	EEnum getBoolOpName();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.Expressions.MathFuncName <em>Math Func Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Math Func Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.MathFuncName
	 * @generated
	 */
	EEnum getMathFuncName();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.Expressions.RecordOpName <em>Record Op Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Record Op Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.RecordOpName
	 * @generated
	 */
	EEnum getRecordOpName();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.Expressions.RelatOpName <em>Relat Op Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Relat Op Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.RelatOpName
	 * @generated
	 */
	EEnum getRelatOpName();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.Expressions.IdentifierType <em>Identifier Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Identifier Type</em>'.
	 * @see metamodel.mmaemilia.Expressions.IdentifierType
	 * @generated
	 */
	EEnum getIdentifierType();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.Expressions.ArithOpName <em>Arith Op Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Arith Op Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.ArithOpName
	 * @generated
	 */
	EEnum getArithOpName();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.Expressions.ArrayOpName <em>Array Op Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Array Op Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.ArrayOpName
	 * @generated
	 */
	EEnum getArrayOpName();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.Expressions.PseudoNumName <em>Pseudo Num Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Pseudo Num Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.PseudoNumName
	 * @generated
	 */
	EEnum getPseudoNumName();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.Expressions.ListOpName <em>List Op Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>List Op Name</em>'.
	 * @see metamodel.mmaemilia.Expressions.ListOpName
	 * @generated
	 */
	EEnum getListOpName();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExpressionsFactory getExpressionsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionImpl
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.impl.ListExprImpl <em>List Expr</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.impl.ListExprImpl
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getListExpr()
		 * @generated
		 */
		EClass LIST_EXPR = eINSTANCE.getListExpr();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIST_EXPR__OPERATION = eINSTANCE.getListExpr_Operation();

		/**
		 * The meta object literal for the '<em><b>List operands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_EXPR__LIST_OPERANDS = eINSTANCE.getListExpr_List_operands();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.impl.ArrayExprImpl <em>Array Expr</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.impl.ArrayExprImpl
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getArrayExpr()
		 * @generated
		 */
		EClass ARRAY_EXPR = eINSTANCE.getArrayExpr();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY_EXPR__OPERATION = eINSTANCE.getArrayExpr_Operation();

		/**
		 * The meta object literal for the '<em><b>Array operands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_EXPR__ARRAY_OPERANDS = eINSTANCE.getArrayExpr_Array_operands();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.impl.RecordExprImpl <em>Record Expr</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.impl.RecordExprImpl
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getRecordExpr()
		 * @generated
		 */
		EClass RECORD_EXPR = eINSTANCE.getRecordExpr();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECORD_EXPR__OPERATION = eINSTANCE.getRecordExpr_Operation();

		/**
		 * The meta object literal for the '<em><b>Record operands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECORD_EXPR__RECORD_OPERANDS = eINSTANCE.getRecordExpr_Record_operands();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.impl.IdentExprImpl <em>Ident Expr</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.impl.IdentExprImpl
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getIdentExpr()
		 * @generated
		 */
		EClass IDENT_EXPR = eINSTANCE.getIdentExpr();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENT_EXPR__NAME = eINSTANCE.getIdentExpr_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENT_EXPR__TYPE = eINSTANCE.getIdentExpr_Type();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.impl.MathFunctionsImpl <em>Math Functions</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.impl.MathFunctionsImpl
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getMathFunctions()
		 * @generated
		 */
		EClass MATH_FUNCTIONS = eINSTANCE.getMathFunctions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MATH_FUNCTIONS__NAME = eINSTANCE.getMathFunctions_Name();

		/**
		 * The meta object literal for the '<em><b>Left Expr Math</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATH_FUNCTIONS__LEFT_EXPR_MATH = eINSTANCE.getMathFunctions_LeftExprMath();

		/**
		 * The meta object literal for the '<em><b>Right Expr Math</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATH_FUNCTIONS__RIGHT_EXPR_MATH = eINSTANCE.getMathFunctions_RightExprMath();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.impl.RelationalExprImpl <em>Relational Expr</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.impl.RelationalExprImpl
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getRelationalExpr()
		 * @generated
		 */
		EClass RELATIONAL_EXPR = eINSTANCE.getRelationalExpr();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONAL_EXPR__OPERATOR = eINSTANCE.getRelationalExpr_Operator();

		/**
		 * The meta object literal for the '<em><b>Left Expr Rel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONAL_EXPR__LEFT_EXPR_REL = eINSTANCE.getRelationalExpr_LeftExprRel();

		/**
		 * The meta object literal for the '<em><b>Right Expr Rel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONAL_EXPR__RIGHT_EXPR_REL = eINSTANCE.getRelationalExpr_RightExprRel();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.impl.ArithExprImpl <em>Arith Expr</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.impl.ArithExprImpl
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getArithExpr()
		 * @generated
		 */
		EClass ARITH_EXPR = eINSTANCE.getArithExpr();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARITH_EXPR__OPERATOR = eINSTANCE.getArithExpr_Operator();

		/**
		 * The meta object literal for the '<em><b>Left Expr Arith</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARITH_EXPR__LEFT_EXPR_ARITH = eINSTANCE.getArithExpr_LeftExprArith();

		/**
		 * The meta object literal for the '<em><b>Right Expr Arith</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARITH_EXPR__RIGHT_EXPR_ARITH = eINSTANCE.getArithExpr_RightExprArith();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.impl.Pseudo_random_num_genImpl <em>Pseudo random num gen</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.impl.Pseudo_random_num_genImpl
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getPseudo_random_num_gen()
		 * @generated
		 */
		EClass PSEUDO_RANDOM_NUM_GEN = eINSTANCE.getPseudo_random_num_gen();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PSEUDO_RANDOM_NUM_GEN__NAME = eINSTANCE.getPseudo_random_num_gen_Name();

		/**
		 * The meta object literal for the '<em><b>First Num Gen Op</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP = eINSTANCE.getPseudo_random_num_gen_FirstNumGenOp();

		/**
		 * The meta object literal for the '<em><b>Second Num Gen Op</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP = eINSTANCE.getPseudo_random_num_gen_SecondNumGenOp();

		/**
		 * The meta object literal for the '<em><b>Third Num Gen Op</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP = eINSTANCE.getPseudo_random_num_gen_ThirdNumGenOp();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.impl.BooleanExprImpl <em>Boolean Expr</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.impl.BooleanExprImpl
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getBooleanExpr()
		 * @generated
		 */
		EClass BOOLEAN_EXPR = eINSTANCE.getBooleanExpr();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_EXPR__OPERATOR = eINSTANCE.getBooleanExpr_Operator();

		/**
		 * The meta object literal for the '<em><b>Left Expr Bool</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_EXPR__LEFT_EXPR_BOOL = eINSTANCE.getBooleanExpr_LeftExprBool();

		/**
		 * The meta object literal for the '<em><b>Right Expr Bool</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_EXPR__RIGHT_EXPR_BOOL = eINSTANCE.getBooleanExpr_RightExprBool();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.BoolOpName <em>Bool Op Name</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.BoolOpName
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getBoolOpName()
		 * @generated
		 */
		EEnum BOOL_OP_NAME = eINSTANCE.getBoolOpName();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.MathFuncName <em>Math Func Name</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.MathFuncName
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getMathFuncName()
		 * @generated
		 */
		EEnum MATH_FUNC_NAME = eINSTANCE.getMathFuncName();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.RecordOpName <em>Record Op Name</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.RecordOpName
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getRecordOpName()
		 * @generated
		 */
		EEnum RECORD_OP_NAME = eINSTANCE.getRecordOpName();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.RelatOpName <em>Relat Op Name</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.RelatOpName
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getRelatOpName()
		 * @generated
		 */
		EEnum RELAT_OP_NAME = eINSTANCE.getRelatOpName();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.IdentifierType <em>Identifier Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.IdentifierType
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getIdentifierType()
		 * @generated
		 */
		EEnum IDENTIFIER_TYPE = eINSTANCE.getIdentifierType();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.ArithOpName <em>Arith Op Name</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.ArithOpName
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getArithOpName()
		 * @generated
		 */
		EEnum ARITH_OP_NAME = eINSTANCE.getArithOpName();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.ArrayOpName <em>Array Op Name</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.ArrayOpName
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getArrayOpName()
		 * @generated
		 */
		EEnum ARRAY_OP_NAME = eINSTANCE.getArrayOpName();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.PseudoNumName <em>Pseudo Num Name</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.PseudoNumName
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getPseudoNumName()
		 * @generated
		 */
		EEnum PSEUDO_NUM_NAME = eINSTANCE.getPseudoNumName();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Expressions.ListOpName <em>List Op Name</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Expressions.ListOpName
		 * @see metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl#getListOpName()
		 * @generated
		 */
		EEnum LIST_OP_NAME = eINSTANCE.getListOpName();

	}

} //ExpressionsPackage
