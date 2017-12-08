/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior.util;

import metamodel.mmaemilia.Behavior.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage
 * @generated
 */
public class BehaviorAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BehaviorPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = BehaviorPackage.eINSTANCE;
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
	protected BehaviorSwitch<Adapter> modelSwitch =
		new BehaviorSwitch<Adapter>() {
			@Override
			public Adapter caseBehavior(Behavior object) {
				return createBehaviorAdapter();
			}
			@Override
			public Adapter caseBehavEquation(BehavEquation object) {
				return createBehavEquationAdapter();
			}
			@Override
			public Adapter caseProcessTerm(ProcessTerm object) {
				return createProcessTermAdapter();
			}
			@Override
			public Adapter caseStop(Stop object) {
				return createStopAdapter();
			}
			@Override
			public Adapter caseChoiceProcess(ChoiceProcess object) {
				return createChoiceProcessAdapter();
			}
			@Override
			public Adapter caseActionProcess(ActionProcess object) {
				return createActionProcessAdapter();
			}
			@Override
			public Adapter caseBehavProcess(BehavProcess object) {
				return createBehavProcessAdapter();
			}
			@Override
			public Adapter caseAction(Action object) {
				return createActionAdapter();
			}
			@Override
			public Adapter caseActionType(ActionType object) {
				return createActionTypeAdapter();
			}
			@Override
			public Adapter caseActionInput(ActionInput object) {
				return createActionInputAdapter();
			}
			@Override
			public Adapter caseActionOutput(ActionOutput object) {
				return createActionOutputAdapter();
			}
			@Override
			public Adapter caseActionRate(ActionRate object) {
				return createActionRateAdapter();
			}
			@Override
			public Adapter caseRateExp(RateExp object) {
				return createRateExpAdapter();
			}
			@Override
			public Adapter caseRateInf(RateInf object) {
				return createRateInfAdapter();
			}
			@Override
			public Adapter caseRatePas(RatePas object) {
				return createRatePasAdapter();
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
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.Behavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.Behavior
	 * @generated
	 */
	public Adapter createBehaviorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.BehavEquation <em>Behav Equation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.BehavEquation
	 * @generated
	 */
	public Adapter createBehavEquationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.ProcessTerm <em>Process Term</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.ProcessTerm
	 * @generated
	 */
	public Adapter createProcessTermAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.Stop <em>Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.Stop
	 * @generated
	 */
	public Adapter createStopAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.ChoiceProcess <em>Choice Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.ChoiceProcess
	 * @generated
	 */
	public Adapter createChoiceProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.ActionProcess <em>Action Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.ActionProcess
	 * @generated
	 */
	public Adapter createActionProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.BehavProcess <em>Behav Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.BehavProcess
	 * @generated
	 */
	public Adapter createBehavProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.Action
	 * @generated
	 */
	public Adapter createActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.ActionType <em>Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.ActionType
	 * @generated
	 */
	public Adapter createActionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.ActionInput <em>Action Input</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.ActionInput
	 * @generated
	 */
	public Adapter createActionInputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.ActionOutput <em>Action Output</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.ActionOutput
	 * @generated
	 */
	public Adapter createActionOutputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.ActionRate <em>Action Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.ActionRate
	 * @generated
	 */
	public Adapter createActionRateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.RateExp <em>Rate Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.RateExp
	 * @generated
	 */
	public Adapter createRateExpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.RateInf <em>Rate Inf</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.RateInf
	 * @generated
	 */
	public Adapter createRateInfAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Behavior.RatePas <em>Rate Pas</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Behavior.RatePas
	 * @generated
	 */
	public Adapter createRatePasAdapter() {
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

} //BehaviorAdapterFactory
