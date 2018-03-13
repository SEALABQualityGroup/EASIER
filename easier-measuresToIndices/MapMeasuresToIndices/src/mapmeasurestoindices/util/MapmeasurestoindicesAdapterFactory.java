/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices.util;

import mapmeasurestoindices.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see mapmeasurestoindices.MapmeasurestoindicesPackage
 * @generated
 */
public class MapmeasurestoindicesAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MapmeasurestoindicesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapmeasurestoindicesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = MapmeasurestoindicesPackage.eINSTANCE;
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
	protected MapmeasurestoindicesSwitch<Adapter> modelSwitch =
		new MapmeasurestoindicesSwitch<Adapter>() {
			@Override
			public Adapter caseMeasureMapping(MeasureMapping object) {
				return createMeasureMappingAdapter();
			}
			@Override
			public Adapter caseMeasureToArchiElemInstance(MeasureToArchiElemInstance object) {
				return createMeasureToArchiElemInstanceAdapter();
			}
			@Override
			public Adapter caseMeasureToAction(MeasureToAction object) {
				return createMeasureToActionAdapter();
			}
			@Override
			public Adapter caseRewMapping(RewMapping object) {
				return createRewMappingAdapter();
			}
			@Override
			public Adapter caseActionMeasure(ActionMeasure object) {
				return createActionMeasureAdapter();
			}
			@Override
			public Adapter caseAeiMeasure(AeiMeasure object) {
				return createAeiMeasureAdapter();
			}
			@Override
			public Adapter caseArchiIntMeasure(ArchiIntMeasure object) {
				return createArchiIntMeasureAdapter();
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
	 * Creates a new adapter for an object of class '{@link mapmeasurestoindices.MeasureMapping <em>Measure Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see mapmeasurestoindices.MeasureMapping
	 * @generated
	 */
	public Adapter createMeasureMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link mapmeasurestoindices.MeasureToArchiElemInstance <em>Measure To Archi Elem Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see mapmeasurestoindices.MeasureToArchiElemInstance
	 * @generated
	 */
	public Adapter createMeasureToArchiElemInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link mapmeasurestoindices.MeasureToAction <em>Measure To Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see mapmeasurestoindices.MeasureToAction
	 * @generated
	 */
	public Adapter createMeasureToActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link mapmeasurestoindices.RewMapping <em>Rew Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see mapmeasurestoindices.RewMapping
	 * @generated
	 */
	public Adapter createRewMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link mapmeasurestoindices.ActionMeasure <em>Action Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see mapmeasurestoindices.ActionMeasure
	 * @generated
	 */
	public Adapter createActionMeasureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link mapmeasurestoindices.AeiMeasure <em>Aei Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see mapmeasurestoindices.AeiMeasure
	 * @generated
	 */
	public Adapter createAeiMeasureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link mapmeasurestoindices.ArchiIntMeasure <em>Archi Int Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see mapmeasurestoindices.ArchiIntMeasure
	 * @generated
	 */
	public Adapter createArchiIntMeasureAdapter() {
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

} //MapmeasurestoindicesAdapterFactory
