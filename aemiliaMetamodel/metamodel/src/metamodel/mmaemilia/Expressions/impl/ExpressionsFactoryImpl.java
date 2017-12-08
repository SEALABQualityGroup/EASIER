/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.impl;

import metamodel.mmaemilia.Expressions.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExpressionsFactoryImpl extends EFactoryImpl implements ExpressionsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExpressionsFactory init() {
		try {
			ExpressionsFactory theExpressionsFactory = (ExpressionsFactory)EPackage.Registry.INSTANCE.getEFactory(ExpressionsPackage.eNS_URI);
			if (theExpressionsFactory != null) {
				return theExpressionsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ExpressionsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ExpressionsPackage.EXPRESSION: return createExpression();
			case ExpressionsPackage.LIST_EXPR: return createListExpr();
			case ExpressionsPackage.ARRAY_EXPR: return createArrayExpr();
			case ExpressionsPackage.RECORD_EXPR: return createRecordExpr();
			case ExpressionsPackage.IDENT_EXPR: return createIdentExpr();
			case ExpressionsPackage.MATH_FUNCTIONS: return createMathFunctions();
			case ExpressionsPackage.RELATIONAL_EXPR: return createRelationalExpr();
			case ExpressionsPackage.ARITH_EXPR: return createArithExpr();
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN: return createPseudo_random_num_gen();
			case ExpressionsPackage.BOOLEAN_EXPR: return createBooleanExpr();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ExpressionsPackage.BOOL_OP_NAME:
				return createBoolOpNameFromString(eDataType, initialValue);
			case ExpressionsPackage.MATH_FUNC_NAME:
				return createMathFuncNameFromString(eDataType, initialValue);
			case ExpressionsPackage.RECORD_OP_NAME:
				return createRecordOpNameFromString(eDataType, initialValue);
			case ExpressionsPackage.RELAT_OP_NAME:
				return createRelatOpNameFromString(eDataType, initialValue);
			case ExpressionsPackage.IDENTIFIER_TYPE:
				return createIdentifierTypeFromString(eDataType, initialValue);
			case ExpressionsPackage.ARITH_OP_NAME:
				return createArithOpNameFromString(eDataType, initialValue);
			case ExpressionsPackage.ARRAY_OP_NAME:
				return createArrayOpNameFromString(eDataType, initialValue);
			case ExpressionsPackage.PSEUDO_NUM_NAME:
				return createPseudoNumNameFromString(eDataType, initialValue);
			case ExpressionsPackage.LIST_OP_NAME:
				return createListOpNameFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ExpressionsPackage.BOOL_OP_NAME:
				return convertBoolOpNameToString(eDataType, instanceValue);
			case ExpressionsPackage.MATH_FUNC_NAME:
				return convertMathFuncNameToString(eDataType, instanceValue);
			case ExpressionsPackage.RECORD_OP_NAME:
				return convertRecordOpNameToString(eDataType, instanceValue);
			case ExpressionsPackage.RELAT_OP_NAME:
				return convertRelatOpNameToString(eDataType, instanceValue);
			case ExpressionsPackage.IDENTIFIER_TYPE:
				return convertIdentifierTypeToString(eDataType, instanceValue);
			case ExpressionsPackage.ARITH_OP_NAME:
				return convertArithOpNameToString(eDataType, instanceValue);
			case ExpressionsPackage.ARRAY_OP_NAME:
				return convertArrayOpNameToString(eDataType, instanceValue);
			case ExpressionsPackage.PSEUDO_NUM_NAME:
				return convertPseudoNumNameToString(eDataType, instanceValue);
			case ExpressionsPackage.LIST_OP_NAME:
				return convertListOpNameToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression createExpression() {
		ExpressionImpl expression = new ExpressionImpl();
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListExpr createListExpr() {
		ListExprImpl listExpr = new ListExprImpl();
		return listExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayExpr createArrayExpr() {
		ArrayExprImpl arrayExpr = new ArrayExprImpl();
		return arrayExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecordExpr createRecordExpr() {
		RecordExprImpl recordExpr = new RecordExprImpl();
		return recordExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentExpr createIdentExpr() {
		IdentExprImpl identExpr = new IdentExprImpl();
		return identExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MathFunctions createMathFunctions() {
		MathFunctionsImpl mathFunctions = new MathFunctionsImpl();
		return mathFunctions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationalExpr createRelationalExpr() {
		RelationalExprImpl relationalExpr = new RelationalExprImpl();
		return relationalExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithExpr createArithExpr() {
		ArithExprImpl arithExpr = new ArithExprImpl();
		return arithExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Pseudo_random_num_gen createPseudo_random_num_gen() {
		Pseudo_random_num_genImpl pseudo_random_num_gen = new Pseudo_random_num_genImpl();
		return pseudo_random_num_gen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanExpr createBooleanExpr() {
		BooleanExprImpl booleanExpr = new BooleanExprImpl();
		return booleanExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BoolOpName createBoolOpNameFromString(EDataType eDataType, String initialValue) {
		BoolOpName result = BoolOpName.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBoolOpNameToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MathFuncName createMathFuncNameFromString(EDataType eDataType, String initialValue) {
		MathFuncName result = MathFuncName.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMathFuncNameToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecordOpName createRecordOpNameFromString(EDataType eDataType, String initialValue) {
		RecordOpName result = RecordOpName.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRecordOpNameToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelatOpName createRelatOpNameFromString(EDataType eDataType, String initialValue) {
		RelatOpName result = RelatOpName.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRelatOpNameToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentifierType createIdentifierTypeFromString(EDataType eDataType, String initialValue) {
		IdentifierType result = IdentifierType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIdentifierTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithOpName createArithOpNameFromString(EDataType eDataType, String initialValue) {
		ArithOpName result = ArithOpName.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertArithOpNameToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayOpName createArrayOpNameFromString(EDataType eDataType, String initialValue) {
		ArrayOpName result = ArrayOpName.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertArrayOpNameToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PseudoNumName createPseudoNumNameFromString(EDataType eDataType, String initialValue) {
		PseudoNumName result = PseudoNumName.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPseudoNumNameToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListOpName createListOpNameFromString(EDataType eDataType, String initialValue) {
		ListOpName result = ListOpName.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertListOpNameToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsPackage getExpressionsPackage() {
		return (ExpressionsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ExpressionsPackage getPackage() {
		return ExpressionsPackage.eINSTANCE;
	}

} //ExpressionsFactoryImpl
