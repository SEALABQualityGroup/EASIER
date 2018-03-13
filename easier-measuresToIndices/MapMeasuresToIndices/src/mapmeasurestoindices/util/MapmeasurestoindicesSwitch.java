/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices.util;

import mapmeasurestoindices.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

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
 * @see mapmeasurestoindices.MapmeasurestoindicesPackage
 * @generated
 */
public class MapmeasurestoindicesSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MapmeasurestoindicesPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapmeasurestoindicesSwitch() {
		if (modelPackage == null) {
			modelPackage = MapmeasurestoindicesPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case MapmeasurestoindicesPackage.MEASURE_MAPPING: {
				MeasureMapping measureMapping = (MeasureMapping)theEObject;
				T result = caseMeasureMapping(measureMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapmeasurestoindicesPackage.MEASURE_TO_ARCHI_ELEM_INSTANCE: {
				MeasureToArchiElemInstance measureToArchiElemInstance = (MeasureToArchiElemInstance)theEObject;
				T result = caseMeasureToArchiElemInstance(measureToArchiElemInstance);
				if (result == null) result = caseMeasureMapping(measureToArchiElemInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapmeasurestoindicesPackage.MEASURE_TO_ACTION: {
				MeasureToAction measureToAction = (MeasureToAction)theEObject;
				T result = caseMeasureToAction(measureToAction);
				if (result == null) result = caseMeasureMapping(measureToAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapmeasurestoindicesPackage.REW_MAPPING: {
				RewMapping rewMapping = (RewMapping)theEObject;
				T result = caseRewMapping(rewMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapmeasurestoindicesPackage.ACTION_MEASURE: {
				ActionMeasure actionMeasure = (ActionMeasure)theEObject;
				T result = caseActionMeasure(actionMeasure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapmeasurestoindicesPackage.AEI_MEASURE: {
				AeiMeasure aeiMeasure = (AeiMeasure)theEObject;
				T result = caseAeiMeasure(aeiMeasure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapmeasurestoindicesPackage.ARCHI_INT_MEASURE: {
				ArchiIntMeasure archiIntMeasure = (ArchiIntMeasure)theEObject;
				T result = caseArchiIntMeasure(archiIntMeasure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measure Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measure Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasureMapping(MeasureMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measure To Archi Elem Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measure To Archi Elem Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasureToArchiElemInstance(MeasureToArchiElemInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measure To Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measure To Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasureToAction(MeasureToAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rew Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rew Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRewMapping(RewMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Measure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Measure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionMeasure(ActionMeasure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Aei Measure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Aei Measure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAeiMeasure(AeiMeasure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Archi Int Measure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Archi Int Measure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArchiIntMeasure(ArchiIntMeasure object) {
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
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //MapmeasurestoindicesSwitch
