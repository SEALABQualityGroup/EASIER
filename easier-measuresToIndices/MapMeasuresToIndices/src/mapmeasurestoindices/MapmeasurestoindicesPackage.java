/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see mapmeasurestoindices.MapmeasurestoindicesFactory
 * @model kind="package"
 * @generated
 */
public interface MapmeasurestoindicesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "mapmeasurestoindices";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://mapmeasurestoindices/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mapmeasurestoindices";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MapmeasurestoindicesPackage eINSTANCE = mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl.init();

	/**
	 * The meta object id for the '{@link mapmeasurestoindices.impl.MeasureMappingImpl <em>Measure Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mapmeasurestoindices.impl.MeasureMappingImpl
	 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getMeasureMapping()
	 * @generated
	 */
	int MEASURE_MAPPING = 0;

	/**
	 * The feature id for the '<em><b>Measure Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_MAPPING__MEASURE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Selector</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_MAPPING__SELECTOR = 1;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_MAPPING__INSTANCES = 2;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_MAPPING__ACTIONS = 3;

	/**
	 * The feature id for the '<em><b>Archi Interactions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_MAPPING__ARCHI_INTERACTIONS = 4;

	/**
	 * The number of structural features of the '<em>Measure Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_MAPPING_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link mapmeasurestoindices.impl.MeasureToArchiElemInstanceImpl <em>Measure To Archi Elem Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mapmeasurestoindices.impl.MeasureToArchiElemInstanceImpl
	 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getMeasureToArchiElemInstance()
	 * @generated
	 */
	int MEASURE_TO_ARCHI_ELEM_INSTANCE = 1;

	/**
	 * The feature id for the '<em><b>Measure Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ARCHI_ELEM_INSTANCE__MEASURE_NAME = MEASURE_MAPPING__MEASURE_NAME;

	/**
	 * The feature id for the '<em><b>Selector</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ARCHI_ELEM_INSTANCE__SELECTOR = MEASURE_MAPPING__SELECTOR;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ARCHI_ELEM_INSTANCE__INSTANCES = MEASURE_MAPPING__INSTANCES;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ARCHI_ELEM_INSTANCE__ACTIONS = MEASURE_MAPPING__ACTIONS;

	/**
	 * The feature id for the '<em><b>Archi Interactions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ARCHI_ELEM_INSTANCE__ARCHI_INTERACTIONS = MEASURE_MAPPING__ARCHI_INTERACTIONS;

	/**
	 * The number of structural features of the '<em>Measure To Archi Elem Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ARCHI_ELEM_INSTANCE_FEATURE_COUNT = MEASURE_MAPPING_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link mapmeasurestoindices.impl.MeasureToActionImpl <em>Measure To Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mapmeasurestoindices.impl.MeasureToActionImpl
	 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getMeasureToAction()
	 * @generated
	 */
	int MEASURE_TO_ACTION = 2;

	/**
	 * The feature id for the '<em><b>Measure Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ACTION__MEASURE_NAME = MEASURE_MAPPING__MEASURE_NAME;

	/**
	 * The feature id for the '<em><b>Selector</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ACTION__SELECTOR = MEASURE_MAPPING__SELECTOR;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ACTION__INSTANCES = MEASURE_MAPPING__INSTANCES;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ACTION__ACTIONS = MEASURE_MAPPING__ACTIONS;

	/**
	 * The feature id for the '<em><b>Archi Interactions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ACTION__ARCHI_INTERACTIONS = MEASURE_MAPPING__ARCHI_INTERACTIONS;

	/**
	 * The number of structural features of the '<em>Measure To Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_TO_ACTION_FEATURE_COUNT = MEASURE_MAPPING_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link mapmeasurestoindices.impl.RewMappingImpl <em>Rew Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mapmeasurestoindices.impl.RewMappingImpl
	 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getRewMapping()
	 * @generated
	 */
	int REW_MAPPING = 3;

	/**
	 * The feature id for the '<em><b>Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REW_MAPPING__MAPPINGS = 0;

	/**
	 * The number of structural features of the '<em>Rew Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REW_MAPPING_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link mapmeasurestoindices.impl.ActionMeasureImpl <em>Action Measure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mapmeasurestoindices.impl.ActionMeasureImpl
	 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getActionMeasure()
	 * @generated
	 */
	int ACTION_MEASURE = 4;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_MEASURE__INDEX = 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_MEASURE__ACTION = 1;

	/**
	 * The number of structural features of the '<em>Action Measure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_MEASURE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link mapmeasurestoindices.impl.AeiMeasureImpl <em>Aei Measure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mapmeasurestoindices.impl.AeiMeasureImpl
	 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getAeiMeasure()
	 * @generated
	 */
	int AEI_MEASURE = 5;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AEI_MEASURE__INDEX = 0;

	/**
	 * The feature id for the '<em><b>Aei</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AEI_MEASURE__AEI = 1;

	/**
	 * The number of structural features of the '<em>Aei Measure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AEI_MEASURE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link mapmeasurestoindices.impl.ArchiIntMeasureImpl <em>Archi Int Measure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mapmeasurestoindices.impl.ArchiIntMeasureImpl
	 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getArchiIntMeasure()
	 * @generated
	 */
	int ARCHI_INT_MEASURE = 6;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_INT_MEASURE__INDEX = 0;

	/**
	 * The feature id for the '<em><b>Archi Interaction</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_INT_MEASURE__ARCHI_INTERACTION = 1;

	/**
	 * The number of structural features of the '<em>Archi Int Measure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_INT_MEASURE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link mapmeasurestoindices.IndexType <em>Index Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mapmeasurestoindices.IndexType
	 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getIndexType()
	 * @generated
	 */
	int INDEX_TYPE = 7;


	/**
	 * Returns the meta object for class '{@link mapmeasurestoindices.MeasureMapping <em>Measure Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measure Mapping</em>'.
	 * @see mapmeasurestoindices.MeasureMapping
	 * @generated
	 */
	EClass getMeasureMapping();

	/**
	 * Returns the meta object for the attribute '{@link mapmeasurestoindices.MeasureMapping#getMeasureName <em>Measure Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Measure Name</em>'.
	 * @see mapmeasurestoindices.MeasureMapping#getMeasureName()
	 * @see #getMeasureMapping()
	 * @generated
	 */
	EAttribute getMeasureMapping_MeasureName();

	/**
	 * Returns the meta object for the containment reference '{@link mapmeasurestoindices.MeasureMapping#getSelector <em>Selector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Selector</em>'.
	 * @see mapmeasurestoindices.MeasureMapping#getSelector()
	 * @see #getMeasureMapping()
	 * @generated
	 */
	EReference getMeasureMapping_Selector();

	/**
	 * Returns the meta object for the containment reference list '{@link mapmeasurestoindices.MeasureMapping#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Instances</em>'.
	 * @see mapmeasurestoindices.MeasureMapping#getInstances()
	 * @see #getMeasureMapping()
	 * @generated
	 */
	EReference getMeasureMapping_Instances();

	/**
	 * Returns the meta object for the containment reference list '{@link mapmeasurestoindices.MeasureMapping#getActions <em>Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actions</em>'.
	 * @see mapmeasurestoindices.MeasureMapping#getActions()
	 * @see #getMeasureMapping()
	 * @generated
	 */
	EReference getMeasureMapping_Actions();

	/**
	 * Returns the meta object for the containment reference list '{@link mapmeasurestoindices.MeasureMapping#getArchiInteractions <em>Archi Interactions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Archi Interactions</em>'.
	 * @see mapmeasurestoindices.MeasureMapping#getArchiInteractions()
	 * @see #getMeasureMapping()
	 * @generated
	 */
	EReference getMeasureMapping_ArchiInteractions();

	/**
	 * Returns the meta object for class '{@link mapmeasurestoindices.MeasureToArchiElemInstance <em>Measure To Archi Elem Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measure To Archi Elem Instance</em>'.
	 * @see mapmeasurestoindices.MeasureToArchiElemInstance
	 * @generated
	 */
	EClass getMeasureToArchiElemInstance();

	/**
	 * Returns the meta object for class '{@link mapmeasurestoindices.MeasureToAction <em>Measure To Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measure To Action</em>'.
	 * @see mapmeasurestoindices.MeasureToAction
	 * @generated
	 */
	EClass getMeasureToAction();

	/**
	 * Returns the meta object for class '{@link mapmeasurestoindices.RewMapping <em>Rew Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rew Mapping</em>'.
	 * @see mapmeasurestoindices.RewMapping
	 * @generated
	 */
	EClass getRewMapping();

	/**
	 * Returns the meta object for the containment reference list '{@link mapmeasurestoindices.RewMapping#getMappings <em>Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mappings</em>'.
	 * @see mapmeasurestoindices.RewMapping#getMappings()
	 * @see #getRewMapping()
	 * @generated
	 */
	EReference getRewMapping_Mappings();

	/**
	 * Returns the meta object for class '{@link mapmeasurestoindices.ActionMeasure <em>Action Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Measure</em>'.
	 * @see mapmeasurestoindices.ActionMeasure
	 * @generated
	 */
	EClass getActionMeasure();

	/**
	 * Returns the meta object for the attribute '{@link mapmeasurestoindices.ActionMeasure#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see mapmeasurestoindices.ActionMeasure#getIndex()
	 * @see #getActionMeasure()
	 * @generated
	 */
	EAttribute getActionMeasure_Index();

	/**
	 * Returns the meta object for the reference '{@link mapmeasurestoindices.ActionMeasure#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Action</em>'.
	 * @see mapmeasurestoindices.ActionMeasure#getAction()
	 * @see #getActionMeasure()
	 * @generated
	 */
	EReference getActionMeasure_Action();

	/**
	 * Returns the meta object for class '{@link mapmeasurestoindices.AeiMeasure <em>Aei Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Aei Measure</em>'.
	 * @see mapmeasurestoindices.AeiMeasure
	 * @generated
	 */
	EClass getAeiMeasure();

	/**
	 * Returns the meta object for the attribute '{@link mapmeasurestoindices.AeiMeasure#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see mapmeasurestoindices.AeiMeasure#getIndex()
	 * @see #getAeiMeasure()
	 * @generated
	 */
	EAttribute getAeiMeasure_Index();

	/**
	 * Returns the meta object for the reference '{@link mapmeasurestoindices.AeiMeasure#getAei <em>Aei</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Aei</em>'.
	 * @see mapmeasurestoindices.AeiMeasure#getAei()
	 * @see #getAeiMeasure()
	 * @generated
	 */
	EReference getAeiMeasure_Aei();

	/**
	 * Returns the meta object for class '{@link mapmeasurestoindices.ArchiIntMeasure <em>Archi Int Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Archi Int Measure</em>'.
	 * @see mapmeasurestoindices.ArchiIntMeasure
	 * @generated
	 */
	EClass getArchiIntMeasure();

	/**
	 * Returns the meta object for the attribute '{@link mapmeasurestoindices.ArchiIntMeasure#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see mapmeasurestoindices.ArchiIntMeasure#getIndex()
	 * @see #getArchiIntMeasure()
	 * @generated
	 */
	EAttribute getArchiIntMeasure_Index();

	/**
	 * Returns the meta object for the reference '{@link mapmeasurestoindices.ArchiIntMeasure#getArchiInteraction <em>Archi Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Archi Interaction</em>'.
	 * @see mapmeasurestoindices.ArchiIntMeasure#getArchiInteraction()
	 * @see #getArchiIntMeasure()
	 * @generated
	 */
	EReference getArchiIntMeasure_ArchiInteraction();

	/**
	 * Returns the meta object for enum '{@link mapmeasurestoindices.IndexType <em>Index Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Index Type</em>'.
	 * @see mapmeasurestoindices.IndexType
	 * @generated
	 */
	EEnum getIndexType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MapmeasurestoindicesFactory getMapmeasurestoindicesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link mapmeasurestoindices.impl.MeasureMappingImpl <em>Measure Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mapmeasurestoindices.impl.MeasureMappingImpl
		 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getMeasureMapping()
		 * @generated
		 */
		EClass MEASURE_MAPPING = eINSTANCE.getMeasureMapping();

		/**
		 * The meta object literal for the '<em><b>Measure Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURE_MAPPING__MEASURE_NAME = eINSTANCE.getMeasureMapping_MeasureName();

		/**
		 * The meta object literal for the '<em><b>Selector</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_MAPPING__SELECTOR = eINSTANCE.getMeasureMapping_Selector();

		/**
		 * The meta object literal for the '<em><b>Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_MAPPING__INSTANCES = eINSTANCE.getMeasureMapping_Instances();

		/**
		 * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_MAPPING__ACTIONS = eINSTANCE.getMeasureMapping_Actions();

		/**
		 * The meta object literal for the '<em><b>Archi Interactions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_MAPPING__ARCHI_INTERACTIONS = eINSTANCE.getMeasureMapping_ArchiInteractions();

		/**
		 * The meta object literal for the '{@link mapmeasurestoindices.impl.MeasureToArchiElemInstanceImpl <em>Measure To Archi Elem Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mapmeasurestoindices.impl.MeasureToArchiElemInstanceImpl
		 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getMeasureToArchiElemInstance()
		 * @generated
		 */
		EClass MEASURE_TO_ARCHI_ELEM_INSTANCE = eINSTANCE.getMeasureToArchiElemInstance();

		/**
		 * The meta object literal for the '{@link mapmeasurestoindices.impl.MeasureToActionImpl <em>Measure To Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mapmeasurestoindices.impl.MeasureToActionImpl
		 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getMeasureToAction()
		 * @generated
		 */
		EClass MEASURE_TO_ACTION = eINSTANCE.getMeasureToAction();

		/**
		 * The meta object literal for the '{@link mapmeasurestoindices.impl.RewMappingImpl <em>Rew Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mapmeasurestoindices.impl.RewMappingImpl
		 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getRewMapping()
		 * @generated
		 */
		EClass REW_MAPPING = eINSTANCE.getRewMapping();

		/**
		 * The meta object literal for the '<em><b>Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REW_MAPPING__MAPPINGS = eINSTANCE.getRewMapping_Mappings();

		/**
		 * The meta object literal for the '{@link mapmeasurestoindices.impl.ActionMeasureImpl <em>Action Measure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mapmeasurestoindices.impl.ActionMeasureImpl
		 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getActionMeasure()
		 * @generated
		 */
		EClass ACTION_MEASURE = eINSTANCE.getActionMeasure();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_MEASURE__INDEX = eINSTANCE.getActionMeasure_Index();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_MEASURE__ACTION = eINSTANCE.getActionMeasure_Action();

		/**
		 * The meta object literal for the '{@link mapmeasurestoindices.impl.AeiMeasureImpl <em>Aei Measure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mapmeasurestoindices.impl.AeiMeasureImpl
		 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getAeiMeasure()
		 * @generated
		 */
		EClass AEI_MEASURE = eINSTANCE.getAeiMeasure();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AEI_MEASURE__INDEX = eINSTANCE.getAeiMeasure_Index();

		/**
		 * The meta object literal for the '<em><b>Aei</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AEI_MEASURE__AEI = eINSTANCE.getAeiMeasure_Aei();

		/**
		 * The meta object literal for the '{@link mapmeasurestoindices.impl.ArchiIntMeasureImpl <em>Archi Int Measure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mapmeasurestoindices.impl.ArchiIntMeasureImpl
		 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getArchiIntMeasure()
		 * @generated
		 */
		EClass ARCHI_INT_MEASURE = eINSTANCE.getArchiIntMeasure();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_INT_MEASURE__INDEX = eINSTANCE.getArchiIntMeasure_Index();

		/**
		 * The meta object literal for the '<em><b>Archi Interaction</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_INT_MEASURE__ARCHI_INTERACTION = eINSTANCE.getArchiIntMeasure_ArchiInteraction();

		/**
		 * The meta object literal for the '{@link mapmeasurestoindices.IndexType <em>Index Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mapmeasurestoindices.IndexType
		 * @see mapmeasurestoindices.impl.MapmeasurestoindicesPackageImpl#getIndexType()
		 * @generated
		 */
		EEnum INDEX_TYPE = eINSTANCE.getIndexType();

	}

} //MapmeasurestoindicesPackage
