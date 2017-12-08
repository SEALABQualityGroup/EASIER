/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices.impl;

import mapmeasurestoindices.*;

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
public class MapmeasurestoindicesFactoryImpl extends EFactoryImpl implements MapmeasurestoindicesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MapmeasurestoindicesFactory init() {
		try {
			MapmeasurestoindicesFactory theMapmeasurestoindicesFactory = (MapmeasurestoindicesFactory)EPackage.Registry.INSTANCE.getEFactory("http://mapmeasurestoindices/1.0"); 
			if (theMapmeasurestoindicesFactory != null) {
				return theMapmeasurestoindicesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MapmeasurestoindicesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapmeasurestoindicesFactoryImpl() {
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
			case MapmeasurestoindicesPackage.MEASURE_MAPPING: return createMeasureMapping();
			case MapmeasurestoindicesPackage.MEASURE_TO_ARCHI_ELEM_INSTANCE: return createMeasureToArchiElemInstance();
			case MapmeasurestoindicesPackage.MEASURE_TO_ACTION: return createMeasureToAction();
			case MapmeasurestoindicesPackage.REW_MAPPING: return createRewMapping();
			case MapmeasurestoindicesPackage.ACTION_MEASURE: return createActionMeasure();
			case MapmeasurestoindicesPackage.AEI_MEASURE: return createAeiMeasure();
			case MapmeasurestoindicesPackage.ARCHI_INT_MEASURE: return createArchiIntMeasure();
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
			case MapmeasurestoindicesPackage.INDEX_TYPE:
				return createIndexTypeFromString(eDataType, initialValue);
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
			case MapmeasurestoindicesPackage.INDEX_TYPE:
				return convertIndexTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasureMapping createMeasureMapping() {
		MeasureMappingImpl measureMapping = new MeasureMappingImpl();
		return measureMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasureToArchiElemInstance createMeasureToArchiElemInstance() {
		MeasureToArchiElemInstanceImpl measureToArchiElemInstance = new MeasureToArchiElemInstanceImpl();
		return measureToArchiElemInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasureToAction createMeasureToAction() {
		MeasureToActionImpl measureToAction = new MeasureToActionImpl();
		return measureToAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RewMapping createRewMapping() {
		RewMappingImpl rewMapping = new RewMappingImpl();
		return rewMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionMeasure createActionMeasure() {
		ActionMeasureImpl actionMeasure = new ActionMeasureImpl();
		return actionMeasure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AeiMeasure createAeiMeasure() {
		AeiMeasureImpl aeiMeasure = new AeiMeasureImpl();
		return aeiMeasure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchiIntMeasure createArchiIntMeasure() {
		ArchiIntMeasureImpl archiIntMeasure = new ArchiIntMeasureImpl();
		return archiIntMeasure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IndexType createIndexTypeFromString(EDataType eDataType, String initialValue) {
		IndexType result = IndexType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIndexTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapmeasurestoindicesPackage getMapmeasurestoindicesPackage() {
		return (MapmeasurestoindicesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MapmeasurestoindicesPackage getPackage() {
		return MapmeasurestoindicesPackage.eINSTANCE;
	}

} //MapmeasurestoindicesFactoryImpl
