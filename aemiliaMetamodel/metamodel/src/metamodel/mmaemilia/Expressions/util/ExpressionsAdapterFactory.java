/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.util;

import metamodel.mmaemilia.Expressions.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage
 * @generated
 */
public class ExpressionsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ExpressionsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ExpressionsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionsSwitch<Adapter> modelSwitch =
		new ExpressionsSwitch<Adapter>() {
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseListExpr(ListExpr object) {
				return createListExprAdapter();
			}
			@Override
			public Adapter caseArrayExpr(ArrayExpr object) {
				return createArrayExprAdapter();
			}
			@Override
			public Adapter caseRecordExpr(RecordExpr object) {
				return createRecordExprAdapter();
			}
			@Override
			public Adapter caseIdentExpr(IdentExpr object) {
				return createIdentExprAdapter();
			}
			@Override
			public Adapter caseMathFunctions(MathFunctions object) {
				return createMathFunctionsAdapter();
			}
			@Override
			public Adapter caseRelationalExpr(RelationalExpr object) {
				return createRelationalExprAdapter();
			}
			@Override
			public Adapter caseArithExpr(ArithExpr object) {
				return createArithExprAdapter();
			}
			@Override
			public Adapter casePseudo_random_num_gen(Pseudo_random_num_gen object) {
				return createPseudo_random_num_genAdapter();
			}
			@Override
			public Adapter caseBooleanExpr(BooleanExpr object) {
				return createBooleanExprAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Expressions.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Expressions.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Expressions.ListExpr <em>List Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Expressions.ListExpr
	 * @generated
	 */
	public Adapter createListExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Expressions.ArrayExpr <em>Array Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Expressions.ArrayExpr
	 * @generated
	 */
	public Adapter createArrayExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Expressions.RecordExpr <em>Record Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Expressions.RecordExpr
	 * @generated
	 */
	public Adapter createRecordExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Expressions.IdentExpr <em>Ident Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Expressions.IdentExpr
	 * @generated
	 */
	public Adapter createIdentExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Expressions.MathFunctions <em>Math Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Expressions.MathFunctions
	 * @generated
	 */
	public Adapter createMathFunctionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Expressions.RelationalExpr <em>Relational Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Expressions.RelationalExpr
	 * @generated
	 */
	public Adapter createRelationalExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Expressions.ArithExpr <em>Arith Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Expressions.ArithExpr
	 * @generated
	 */
	public Adapter createArithExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen <em>Pseudo random num gen</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Expressions.Pseudo_random_num_gen
	 * @generated
	 */
	public Adapter createPseudo_random_num_genAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Expressions.BooleanExpr <em>Boolean Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Expressions.BooleanExpr
	 * @generated
	 */
	public Adapter createBooleanExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ExpressionsAdapterFactory
