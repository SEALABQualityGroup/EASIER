/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.impl;

import metamodel.mmaemilia.Behavior.BehaviorPackage;

import metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl;

import metamodel.mmaemilia.DataType.DataTypePackage;

import metamodel.mmaemilia.DataType.impl.DataTypePackageImpl;

import metamodel.mmaemilia.Expressions.ArithExpr;
import metamodel.mmaemilia.Expressions.ArithOpName;
import metamodel.mmaemilia.Expressions.ArrayExpr;
import metamodel.mmaemilia.Expressions.ArrayOpName;
import metamodel.mmaemilia.Expressions.BoolOpName;
import metamodel.mmaemilia.Expressions.BooleanExpr;
import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Expressions.ExpressionsFactory;
import metamodel.mmaemilia.Expressions.ExpressionsPackage;
import metamodel.mmaemilia.Expressions.IdentExpr;
import metamodel.mmaemilia.Expressions.IdentifierType;
import metamodel.mmaemilia.Expressions.ListExpr;
import metamodel.mmaemilia.Expressions.ListOpName;
import metamodel.mmaemilia.Expressions.MathFuncName;
import metamodel.mmaemilia.Expressions.MathFunctions;
import metamodel.mmaemilia.Expressions.PseudoNumName;
import metamodel.mmaemilia.Expressions.Pseudo_random_num_gen;
import metamodel.mmaemilia.Expressions.RecordExpr;
import metamodel.mmaemilia.Expressions.RecordOpName;
import metamodel.mmaemilia.Expressions.RelatOpName;
import metamodel.mmaemilia.Expressions.RelationalExpr;

import metamodel.mmaemilia.Headers.HeadersPackage;

import metamodel.mmaemilia.Headers.impl.HeadersPackageImpl;

import metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage;
import metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl;
import metamodel.mmaemilia.impl.mmaemiliaPackageImpl;

import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExpressionsPackageImpl extends EPackageImpl implements ExpressionsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listExprEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayExprEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass recordExprEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass identExprEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mathFunctionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationalExprEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arithExprEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pseudo_random_num_genEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanExprEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum boolOpNameEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum mathFuncNameEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum recordOpNameEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum relatOpNameEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum identifierTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum arithOpNameEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum arrayOpNameEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum pseudoNumNameEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum listOpNameEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ExpressionsPackageImpl() {
		super(eNS_URI, ExpressionsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ExpressionsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ExpressionsPackage init() {
		if (isInited) return (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI);

		// Obtain or create and register package
		ExpressionsPackageImpl theExpressionsPackage = (ExpressionsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExpressionsPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		mmaemiliaPackageImpl themmaemiliaPackage = (mmaemiliaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI) instanceof mmaemiliaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI) : mmaemiliaPackage.eINSTANCE);
		DataTypePackageImpl theDataTypePackage = (DataTypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI) instanceof DataTypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI) : DataTypePackage.eINSTANCE);
		HeadersPackageImpl theHeadersPackage = (HeadersPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI) instanceof HeadersPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI) : HeadersPackage.eINSTANCE);
		BehaviorPackageImpl theBehaviorPackage = (BehaviorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI) instanceof BehaviorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI) : BehaviorPackage.eINSTANCE);
		QueueingNetworkPackageImpl theQueueingNetworkPackage = (QueueingNetworkPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI) instanceof QueueingNetworkPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI) : QueueingNetworkPackage.eINSTANCE);

		// Create package meta-data objects
		theExpressionsPackage.createPackageContents();
		themmaemiliaPackage.createPackageContents();
		theDataTypePackage.createPackageContents();
		theHeadersPackage.createPackageContents();
		theBehaviorPackage.createPackageContents();
		theQueueingNetworkPackage.createPackageContents();

		// Initialize created meta-data
		theExpressionsPackage.initializePackageContents();
		themmaemiliaPackage.initializePackageContents();
		theDataTypePackage.initializePackageContents();
		theHeadersPackage.initializePackageContents();
		theBehaviorPackage.initializePackageContents();
		theQueueingNetworkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theExpressionsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExpressionsPackage.eNS_URI, theExpressionsPackage);
		return theExpressionsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpression() {
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getListExpr() {
		return listExprEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getListExpr_Operation() {
		return (EAttribute)listExprEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListExpr_List_operands() {
		return (EReference)listExprEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayExpr() {
		return arrayExprEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayExpr_Operation() {
		return (EAttribute)arrayExprEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayExpr_Array_operands() {
		return (EReference)arrayExprEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRecordExpr() {
		return recordExprEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRecordExpr_Operation() {
		return (EAttribute)recordExprEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRecordExpr_Record_operands() {
		return (EReference)recordExprEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIdentExpr() {
		return identExprEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentExpr_Name() {
		return (EAttribute)identExprEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentExpr_Type() {
		return (EAttribute)identExprEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMathFunctions() {
		return mathFunctionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMathFunctions_Name() {
		return (EAttribute)mathFunctionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMathFunctions_LeftExprMath() {
		return (EReference)mathFunctionsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMathFunctions_RightExprMath() {
		return (EReference)mathFunctionsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelationalExpr() {
		return relationalExprEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelationalExpr_Operator() {
		return (EAttribute)relationalExprEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationalExpr_LeftExprRel() {
		return (EReference)relationalExprEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationalExpr_RightExprRel() {
		return (EReference)relationalExprEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArithExpr() {
		return arithExprEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArithExpr_Operator() {
		return (EAttribute)arithExprEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArithExpr_LeftExprArith() {
		return (EReference)arithExprEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArithExpr_RightExprArith() {
		return (EReference)arithExprEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPseudo_random_num_gen() {
		return pseudo_random_num_genEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPseudo_random_num_gen_Name() {
		return (EAttribute)pseudo_random_num_genEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPseudo_random_num_gen_FirstNumGenOp() {
		return (EReference)pseudo_random_num_genEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPseudo_random_num_gen_SecondNumGenOp() {
		return (EReference)pseudo_random_num_genEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPseudo_random_num_gen_ThirdNumGenOp() {
		return (EReference)pseudo_random_num_genEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanExpr() {
		return booleanExprEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanExpr_Operator() {
		return (EAttribute)booleanExprEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBooleanExpr_LeftExprBool() {
		return (EReference)booleanExprEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBooleanExpr_RightExprBool() {
		return (EReference)booleanExprEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBoolOpName() {
		return boolOpNameEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMathFuncName() {
		return mathFuncNameEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRecordOpName() {
		return recordOpNameEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRelatOpName() {
		return relatOpNameEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getIdentifierType() {
		return identifierTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getArithOpName() {
		return arithOpNameEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getArrayOpName() {
		return arrayOpNameEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPseudoNumName() {
		return pseudoNumNameEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getListOpName() {
		return listOpNameEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsFactory getExpressionsFactory() {
		return (ExpressionsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		expressionEClass = createEClass(EXPRESSION);

		listExprEClass = createEClass(LIST_EXPR);
		createEAttribute(listExprEClass, LIST_EXPR__OPERATION);
		createEReference(listExprEClass, LIST_EXPR__LIST_OPERANDS);

		arrayExprEClass = createEClass(ARRAY_EXPR);
		createEAttribute(arrayExprEClass, ARRAY_EXPR__OPERATION);
		createEReference(arrayExprEClass, ARRAY_EXPR__ARRAY_OPERANDS);

		recordExprEClass = createEClass(RECORD_EXPR);
		createEAttribute(recordExprEClass, RECORD_EXPR__OPERATION);
		createEReference(recordExprEClass, RECORD_EXPR__RECORD_OPERANDS);

		identExprEClass = createEClass(IDENT_EXPR);
		createEAttribute(identExprEClass, IDENT_EXPR__NAME);
		createEAttribute(identExprEClass, IDENT_EXPR__TYPE);

		mathFunctionsEClass = createEClass(MATH_FUNCTIONS);
		createEAttribute(mathFunctionsEClass, MATH_FUNCTIONS__NAME);
		createEReference(mathFunctionsEClass, MATH_FUNCTIONS__LEFT_EXPR_MATH);
		createEReference(mathFunctionsEClass, MATH_FUNCTIONS__RIGHT_EXPR_MATH);

		relationalExprEClass = createEClass(RELATIONAL_EXPR);
		createEAttribute(relationalExprEClass, RELATIONAL_EXPR__OPERATOR);
		createEReference(relationalExprEClass, RELATIONAL_EXPR__LEFT_EXPR_REL);
		createEReference(relationalExprEClass, RELATIONAL_EXPR__RIGHT_EXPR_REL);

		arithExprEClass = createEClass(ARITH_EXPR);
		createEAttribute(arithExprEClass, ARITH_EXPR__OPERATOR);
		createEReference(arithExprEClass, ARITH_EXPR__LEFT_EXPR_ARITH);
		createEReference(arithExprEClass, ARITH_EXPR__RIGHT_EXPR_ARITH);

		pseudo_random_num_genEClass = createEClass(PSEUDO_RANDOM_NUM_GEN);
		createEAttribute(pseudo_random_num_genEClass, PSEUDO_RANDOM_NUM_GEN__NAME);
		createEReference(pseudo_random_num_genEClass, PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP);
		createEReference(pseudo_random_num_genEClass, PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP);
		createEReference(pseudo_random_num_genEClass, PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP);

		booleanExprEClass = createEClass(BOOLEAN_EXPR);
		createEAttribute(booleanExprEClass, BOOLEAN_EXPR__OPERATOR);
		createEReference(booleanExprEClass, BOOLEAN_EXPR__LEFT_EXPR_BOOL);
		createEReference(booleanExprEClass, BOOLEAN_EXPR__RIGHT_EXPR_BOOL);

		// Create enums
		boolOpNameEEnum = createEEnum(BOOL_OP_NAME);
		mathFuncNameEEnum = createEEnum(MATH_FUNC_NAME);
		recordOpNameEEnum = createEEnum(RECORD_OP_NAME);
		relatOpNameEEnum = createEEnum(RELAT_OP_NAME);
		identifierTypeEEnum = createEEnum(IDENTIFIER_TYPE);
		arithOpNameEEnum = createEEnum(ARITH_OP_NAME);
		arrayOpNameEEnum = createEEnum(ARRAY_OP_NAME);
		pseudoNumNameEEnum = createEEnum(PSEUDO_NUM_NAME);
		listOpNameEEnum = createEEnum(LIST_OP_NAME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		listExprEClass.getESuperTypes().add(this.getExpression());
		arrayExprEClass.getESuperTypes().add(this.getExpression());
		recordExprEClass.getESuperTypes().add(this.getExpression());
		identExprEClass.getESuperTypes().add(this.getExpression());
		mathFunctionsEClass.getESuperTypes().add(this.getExpression());
		relationalExprEClass.getESuperTypes().add(this.getExpression());
		arithExprEClass.getESuperTypes().add(this.getExpression());
		pseudo_random_num_genEClass.getESuperTypes().add(this.getExpression());
		booleanExprEClass.getESuperTypes().add(this.getExpression());

		// Initialize classes and features; add operations and parameters
		initEClass(expressionEClass, Expression.class, "Expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(listExprEClass, ListExpr.class, "ListExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getListExpr_Operation(), this.getListOpName(), "operation", null, 1, 1, ListExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListExpr_List_operands(), this.getExpression(), null, "list_operands", null, 0, -1, ListExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arrayExprEClass, ArrayExpr.class, "ArrayExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArrayExpr_Operation(), this.getArrayOpName(), "operation", null, 1, 1, ArrayExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayExpr_Array_operands(), this.getExpression(), null, "array_operands", null, 0, -1, ArrayExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(recordExprEClass, RecordExpr.class, "RecordExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRecordExpr_Operation(), this.getRecordOpName(), "operation", null, 1, 1, RecordExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRecordExpr_Record_operands(), this.getExpression(), null, "record_operands", null, 0, -1, RecordExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(identExprEClass, IdentExpr.class, "IdentExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIdentExpr_Name(), ecorePackage.getEString(), "name", null, 1, 1, IdentExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIdentExpr_Type(), this.getIdentifierType(), "type", null, 1, 1, IdentExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mathFunctionsEClass, MathFunctions.class, "MathFunctions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMathFunctions_Name(), this.getMathFuncName(), "name", null, 1, 1, MathFunctions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMathFunctions_LeftExprMath(), this.getExpression(), null, "leftExprMath", null, 1, 1, MathFunctions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMathFunctions_RightExprMath(), this.getExpression(), null, "rightExprMath", null, 0, 1, MathFunctions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relationalExprEClass, RelationalExpr.class, "RelationalExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRelationalExpr_Operator(), this.getRelatOpName(), "operator", null, 1, 1, RelationalExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelationalExpr_LeftExprRel(), this.getExpression(), null, "leftExprRel", null, 1, 1, RelationalExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelationalExpr_RightExprRel(), this.getExpression(), null, "rightExprRel", null, 1, 1, RelationalExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arithExprEClass, ArithExpr.class, "ArithExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArithExpr_Operator(), this.getArithOpName(), "operator", null, 1, 1, ArithExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArithExpr_LeftExprArith(), this.getExpression(), null, "leftExprArith", null, 1, 1, ArithExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArithExpr_RightExprArith(), this.getExpression(), null, "rightExprArith", null, 1, 1, ArithExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pseudo_random_num_genEClass, Pseudo_random_num_gen.class, "Pseudo_random_num_gen", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPseudo_random_num_gen_Name(), this.getPseudoNumName(), "name", null, 1, 1, Pseudo_random_num_gen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPseudo_random_num_gen_FirstNumGenOp(), this.getExpression(), null, "firstNumGenOp", null, 1, 1, Pseudo_random_num_gen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPseudo_random_num_gen_SecondNumGenOp(), this.getExpression(), null, "secondNumGenOp", null, 0, 1, Pseudo_random_num_gen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPseudo_random_num_gen_ThirdNumGenOp(), this.getExpression(), null, "thirdNumGenOp", null, 0, 1, Pseudo_random_num_gen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanExprEClass, BooleanExpr.class, "BooleanExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanExpr_Operator(), this.getBoolOpName(), "operator", null, 1, 1, BooleanExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBooleanExpr_LeftExprBool(), this.getExpression(), null, "leftExprBool", null, 0, 1, BooleanExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBooleanExpr_RightExprBool(), this.getExpression(), null, "rightExprBool", null, 1, 1, BooleanExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(boolOpNameEEnum, BoolOpName.class, "BoolOpName");
		addEEnumLiteral(boolOpNameEEnum, BoolOpName.NOT);
		addEEnumLiteral(boolOpNameEEnum, BoolOpName.AND);
		addEEnumLiteral(boolOpNameEEnum, BoolOpName.OR);

		initEEnum(mathFuncNameEEnum, MathFuncName.class, "MathFuncName");
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.MOD);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.ABS);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.CEIL);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.FLOOR);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.MIN);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.MAX);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.POWER);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.EPOWER);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.LOGE);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.LOG10);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.SQRT);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.SIN);
		addEEnumLiteral(mathFuncNameEEnum, MathFuncName.COS);

		initEEnum(recordOpNameEEnum, RecordOpName.class, "RecordOpName");
		addEEnumLiteral(recordOpNameEEnum, RecordOpName.RECORD_CONS);
		addEEnumLiteral(recordOpNameEEnum, RecordOpName.GET);
		addEEnumLiteral(recordOpNameEEnum, RecordOpName.PUT);

		initEEnum(relatOpNameEEnum, RelatOpName.class, "RelatOpName");
		addEEnumLiteral(relatOpNameEEnum, RelatOpName.GREATER);
		addEEnumLiteral(relatOpNameEEnum, RelatOpName.GREATER_EQUAL);
		addEEnumLiteral(relatOpNameEEnum, RelatOpName.LESS);
		addEEnumLiteral(relatOpNameEEnum, RelatOpName.LESS_EQUAL);
		addEEnumLiteral(relatOpNameEEnum, RelatOpName.EQUAL);
		addEEnumLiteral(relatOpNameEEnum, RelatOpName.NOT_EQUAL);

		initEEnum(identifierTypeEEnum, IdentifierType.class, "IdentifierType");
		addEEnumLiteral(identifierTypeEEnum, IdentifierType.TYPED_IDENT);
		addEEnumLiteral(identifierTypeEEnum, IdentifierType.NUMERIC_CONST);
		addEEnumLiteral(identifierTypeEEnum, IdentifierType.TRUTH_VAL);

		initEEnum(arithOpNameEEnum, ArithOpName.class, "ArithOpName");
		addEEnumLiteral(arithOpNameEEnum, ArithOpName.PLUS);
		addEEnumLiteral(arithOpNameEEnum, ArithOpName.MINUS);
		addEEnumLiteral(arithOpNameEEnum, ArithOpName.DIV);
		addEEnumLiteral(arithOpNameEEnum, ArithOpName.MULT);

		initEEnum(arrayOpNameEEnum, ArrayOpName.class, "ArrayOpName");
		addEEnumLiteral(arrayOpNameEEnum, ArrayOpName.ARRAY_CONS);
		addEEnumLiteral(arrayOpNameEEnum, ArrayOpName.READ);
		addEEnumLiteral(arrayOpNameEEnum, ArrayOpName.WRITE);

		initEEnum(pseudoNumNameEEnum, PseudoNumName.class, "PseudoNumName");
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.DUNIFORM);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.GAMMA);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.CUNIFORM);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.ERLANG);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.EXPONENTIAL);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.WEIBULL);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.BETA);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.NORMAL);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.PARETO);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.BPARETO);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.BERNOULLI);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.BINOMIAL);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.POISSON);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.NEG_BINOMIAL);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.GEOMETRIC);
		addEEnumLiteral(pseudoNumNameEEnum, PseudoNumName.PASCAL);

		initEEnum(listOpNameEEnum, ListOpName.class, "ListOpName");
		addEEnumLiteral(listOpNameEEnum, ListOpName.LIST_CONS);
		addEEnumLiteral(listOpNameEEnum, ListOpName.FIRST);
		addEEnumLiteral(listOpNameEEnum, ListOpName.TAIL);
		addEEnumLiteral(listOpNameEEnum, ListOpName.CONCAT);
		addEEnumLiteral(listOpNameEEnum, ListOpName.INSERT);
		addEEnumLiteral(listOpNameEEnum, ListOpName.REMOVE);
		addEEnumLiteral(listOpNameEEnum, ListOpName.LENGHT);
	}

} //ExpressionsPackageImpl
