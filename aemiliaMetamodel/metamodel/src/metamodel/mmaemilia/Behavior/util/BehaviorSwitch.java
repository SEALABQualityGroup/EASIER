/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior.util;

import java.util.List;

import metamodel.mmaemilia.Behavior.*;

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
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage
 * @generated
 */
public class BehaviorSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BehaviorPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorSwitch() {
		if (modelPackage == null) {
			modelPackage = BehaviorPackage.eINSTANCE;
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
			case BehaviorPackage.BEHAVIOR: {
				Behavior behavior = (Behavior)theEObject;
				T result = caseBehavior(behavior);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.BEHAV_EQUATION: {
				BehavEquation behavEquation = (BehavEquation)theEObject;
				T result = caseBehavEquation(behavEquation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.PROCESS_TERM: {
				ProcessTerm processTerm = (ProcessTerm)theEObject;
				T result = caseProcessTerm(processTerm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.STOP: {
				Stop stop = (Stop)theEObject;
				T result = caseStop(stop);
				if (result == null) result = caseProcessTerm(stop);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.CHOICE_PROCESS: {
				ChoiceProcess choiceProcess = (ChoiceProcess)theEObject;
				T result = caseChoiceProcess(choiceProcess);
				if (result == null) result = caseProcessTerm(choiceProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.ACTION_PROCESS: {
				ActionProcess actionProcess = (ActionProcess)theEObject;
				T result = caseActionProcess(actionProcess);
				if (result == null) result = caseProcessTerm(actionProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.BEHAV_PROCESS: {
				BehavProcess behavProcess = (BehavProcess)theEObject;
				T result = caseBehavProcess(behavProcess);
				if (result == null) result = caseProcessTerm(behavProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.ACTION: {
				Action action = (Action)theEObject;
				T result = caseAction(action);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.ACTION_TYPE: {
				ActionType actionType = (ActionType)theEObject;
				T result = caseActionType(actionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.ACTION_INPUT: {
				ActionInput actionInput = (ActionInput)theEObject;
				T result = caseActionInput(actionInput);
				if (result == null) result = caseActionType(actionInput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.ACTION_OUTPUT: {
				ActionOutput actionOutput = (ActionOutput)theEObject;
				T result = caseActionOutput(actionOutput);
				if (result == null) result = caseActionType(actionOutput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.ACTION_RATE: {
				ActionRate actionRate = (ActionRate)theEObject;
				T result = caseActionRate(actionRate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.RATE_EXP: {
				RateExp rateExp = (RateExp)theEObject;
				T result = caseRateExp(rateExp);
				if (result == null) result = caseActionRate(rateExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.RATE_INF: {
				RateInf rateInf = (RateInf)theEObject;
				T result = caseRateInf(rateInf);
				if (result == null) result = caseActionRate(rateInf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.RATE_PAS: {
				RatePas ratePas = (RatePas)theEObject;
				T result = caseRatePas(ratePas);
				if (result == null) result = caseActionRate(ratePas);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behavior</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behavior</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehavior(Behavior object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behav Equation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behav Equation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehavEquation(BehavEquation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Term</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Term</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessTerm(ProcessTerm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stop</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stop</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStop(Stop object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Choice Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Choice Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChoiceProcess(ChoiceProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionProcess(ActionProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behav Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behav Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehavProcess(BehavProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAction(Action object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionType(ActionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionInput(ActionInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Output</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Output</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionOutput(ActionOutput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Rate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Rate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionRate(ActionRate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rate Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rate Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRateExp(RateExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rate Inf</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rate Inf</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRateInf(RateInf object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rate Pas</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rate Pas</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRatePas(RatePas object) {
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

} //BehaviorSwitch
