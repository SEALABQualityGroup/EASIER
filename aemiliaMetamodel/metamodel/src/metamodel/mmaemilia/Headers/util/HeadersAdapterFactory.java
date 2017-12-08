/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers.util;

import metamodel.mmaemilia.Headers.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Headers.HeadersPackage
 * @generated
 */
public class HeadersAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static HeadersPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeadersAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = HeadersPackage.eINSTANCE;
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
	protected HeadersSwitch<Adapter> modelSwitch =
		new HeadersSwitch<Adapter>() {
			@Override
			public Adapter caseAT_Header(AT_Header object) {
				return createAT_HeaderAdapter();
			}
			@Override
			public Adapter caseConstInit(ConstInit object) {
				return createConstInitAdapter();
			}
			@Override
			public Adapter caseET_Header(ET_Header object) {
				return createET_HeaderAdapter();
			}
			@Override
			public Adapter caseConst(Const object) {
				return createConstAdapter();
			}
			@Override
			public Adapter caseBehavHeader(BehavHeader object) {
				return createBehavHeaderAdapter();
			}
			@Override
			public Adapter caseLeftSide(LeftSide object) {
				return createLeftSideAdapter();
			}
			@Override
			public Adapter caseRightSide(RightSide object) {
				return createRightSideAdapter();
			}
			@Override
			public Adapter caseVarInit(VarInit object) {
				return createVarInitAdapter();
			}
			@Override
			public Adapter caseVar(Var object) {
				return createVarAdapter();
			}
			@Override
			public Adapter caseLocal(Local object) {
				return createLocalAdapter();
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
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Headers.AT_Header <em>AT Header</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Headers.AT_Header
	 * @generated
	 */
	public Adapter createAT_HeaderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Headers.ConstInit <em>Const Init</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Headers.ConstInit
	 * @generated
	 */
	public Adapter createConstInitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Headers.ET_Header <em>ET Header</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Headers.ET_Header
	 * @generated
	 */
	public Adapter createET_HeaderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Headers.Const <em>Const</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Headers.Const
	 * @generated
	 */
	public Adapter createConstAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Headers.BehavHeader <em>Behav Header</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Headers.BehavHeader
	 * @generated
	 */
	public Adapter createBehavHeaderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Headers.LeftSide <em>Left Side</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Headers.LeftSide
	 * @generated
	 */
	public Adapter createLeftSideAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Headers.RightSide <em>Right Side</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Headers.RightSide
	 * @generated
	 */
	public Adapter createRightSideAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Headers.VarInit <em>Var Init</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Headers.VarInit
	 * @generated
	 */
	public Adapter createVarInitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Headers.Var <em>Var</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Headers.Var
	 * @generated
	 */
	public Adapter createVarAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Headers.Local <em>Local</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Headers.Local
	 * @generated
	 */
	public Adapter createLocalAdapter() {
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

} //HeadersAdapterFactory
