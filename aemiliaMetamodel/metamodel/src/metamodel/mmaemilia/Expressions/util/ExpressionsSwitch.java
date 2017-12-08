/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.util;

import java.util.List;

import metamodel.mmaemilia.Expressions.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage
 * @generated
 */
public class ExpressionsSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ExpressionsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsSwitch() {
		if (modelPackage == null) {
			modelPackage = ExpressionsPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ExpressionsPackage.EXPRESSION: {
				Expression expression = (Expression)theEObject;
				T result = caseExpression(expression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.LIST_EXPR: {
				ListExpr listExpr = (ListExpr)theEObject;
				T result = caseListExpr(listExpr);
				if (result == null) result = caseExpression(listExpr);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.ARRAY_EXPR: {
				ArrayExpr arrayExpr = (ArrayExpr)theEObject;
				T result = caseArrayExpr(arrayExpr);
				if (result == null) result = caseExpression(arrayExpr);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.RECORD_EXPR: {
				RecordExpr recordExpr = (RecordExpr)theEObject;
				T result = caseRecordExpr(recordExpr);
				if (result == null) result = caseExpression(recordExpr);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.IDENT_EXPR: {
				IdentExpr identExpr = (IdentExpr)theEObject;
				T result = caseIdentExpr(identExpr);
				if (result == null) result = caseExpression(identExpr);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.MATH_FUNCTIONS: {
				MathFunctions mathFunctions = (MathFunctions)theEObject;
				T result = caseMathFunctions(mathFunctions);
				if (result == null) result = caseExpression(mathFunctions);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.RELATIONAL_EXPR: {
				RelationalExpr relationalExpr = (RelationalExpr)theEObject;
				T result = caseRelationalExpr(relationalExpr);
				if (result == null) result = caseExpression(relationalExpr);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.ARITH_EXPR: {
				ArithExpr arithExpr = (ArithExpr)theEObject;
				T result = caseArithExpr(arithExpr);
				if (result == null) result = caseExpression(arithExpr);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN: {
				Pseudo_random_num_gen pseudo_random_num_gen = (Pseudo_random_num_gen)theEObject;
				T result = casePseudo_random_num_gen(pseudo_random_num_gen);
				if (result == null) result = caseExpression(pseudo_random_num_gen);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.BOOLEAN_EXPR: {
				BooleanExpr booleanExpr = (BooleanExpr)theEObject;
				T result = caseBooleanExpr(booleanExpr);
				if (result == null) result = caseExpression(booleanExpr);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpression(Expression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Expr</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Expr</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseListExpr(ListExpr object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array Expr</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array Expr</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArrayExpr(ArrayExpr object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Record Expr</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Record Expr</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRecordExpr(RecordExpr object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ident Expr</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ident Expr</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdentExpr(IdentExpr object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Math Functions</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Math Functions</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMathFunctions(MathFunctions object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relational Expr</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relational Expr</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationalExpr(RelationalExpr object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arith Expr</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arith Expr</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArithExpr(ArithExpr object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pseudo random num gen</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pseudo random num gen</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePseudo_random_num_gen(Pseudo_random_num_gen object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Expr</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Expr</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanExpr(BooleanExpr object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ExpressionsSwitch
