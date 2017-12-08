/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices.impl;

import mapmeasurestoindices.ActionMeasure;
import mapmeasurestoindices.AeiMeasure;
import mapmeasurestoindices.ArchiIntMeasure;
import mapmeasurestoindices.IndexType;
import mapmeasurestoindices.MapmeasurestoindicesFactory;
import mapmeasurestoindices.MapmeasurestoindicesPackage;
import mapmeasurestoindices.MeasureMapping;
import mapmeasurestoindices.MeasureToAction;
import mapmeasurestoindices.MeasureToArchiElemInstance;
import mapmeasurestoindices.RewMapping;

import metamodel.mmaemilia.Behavior.BehaviorPackage;

import metamodel.mmaemilia.Expressions.ExpressionsPackage;

import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MapmeasurestoindicesPackageImpl extends EPackageImpl implements MapmeasurestoindicesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measureMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measureToArchiElemInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measureToActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rewMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionMeasureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aeiMeasureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass archiIntMeasureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum indexTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MapmeasurestoindicesPackageImpl() {
		super(eNS_URI, MapmeasurestoindicesFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MapmeasurestoindicesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MapmeasurestoindicesPackage init() {
		if (isInited) return (MapmeasurestoindicesPackage)EPackage.Registry.INSTANCE.getEPackage(MapmeasurestoindicesPackage.eNS_URI);

		// Obtain or create and register package
		MapmeasurestoindicesPackageImpl theMapmeasurestoindicesPackage = (MapmeasurestoindicesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MapmeasurestoindicesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MapmeasurestoindicesPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		mmaemiliaPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMapmeasurestoindicesPackage.createPackageContents();

		// Initialize created meta-data
		theMapmeasurestoindicesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMapmeasurestoindicesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MapmeasurestoindicesPackage.eNS_URI, theMapmeasurestoindicesPackage);
		return theMapmeasurestoindicesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasureMapping() {
		return measureMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeasureMapping_MeasureName() {
		return (EAttribute)measureMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureMapping_Selector() {
		return (EReference)measureMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureMapping_Instances() {
		return (EReference)measureMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureMapping_Actions() {
		return (EReference)measureMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureMapping_ArchiInteractions() {
		return (EReference)measureMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasureToArchiElemInstance() {
		return measureToArchiElemInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasureToAction() {
		return measureToActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRewMapping() {
		return rewMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRewMapping_Mappings() {
		return (EReference)rewMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionMeasure() {
		return actionMeasureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionMeasure_Index() {
		return (EAttribute)actionMeasureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionMeasure_Action() {
		return (EReference)actionMeasureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAeiMeasure() {
		return aeiMeasureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAeiMeasure_Index() {
		return (EAttribute)aeiMeasureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAeiMeasure_Aei() {
		return (EReference)aeiMeasureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchiIntMeasure() {
		return archiIntMeasureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiIntMeasure_Index() {
		return (EAttribute)archiIntMeasureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiIntMeasure_ArchiInteraction() {
		return (EReference)archiIntMeasureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getIndexType() {
		return indexTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapmeasurestoindicesFactory getMapmeasurestoindicesFactory() {
		return (MapmeasurestoindicesFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		measureMappingEClass = createEClass(MEASURE_MAPPING);
		createEAttribute(measureMappingEClass, MEASURE_MAPPING__MEASURE_NAME);
		createEReference(measureMappingEClass, MEASURE_MAPPING__SELECTOR);
		createEReference(measureMappingEClass, MEASURE_MAPPING__INSTANCES);
		createEReference(measureMappingEClass, MEASURE_MAPPING__ACTIONS);
		createEReference(measureMappingEClass, MEASURE_MAPPING__ARCHI_INTERACTIONS);

		measureToArchiElemInstanceEClass = createEClass(MEASURE_TO_ARCHI_ELEM_INSTANCE);

		measureToActionEClass = createEClass(MEASURE_TO_ACTION);

		rewMappingEClass = createEClass(REW_MAPPING);
		createEReference(rewMappingEClass, REW_MAPPING__MAPPINGS);

		actionMeasureEClass = createEClass(ACTION_MEASURE);
		createEAttribute(actionMeasureEClass, ACTION_MEASURE__INDEX);
		createEReference(actionMeasureEClass, ACTION_MEASURE__ACTION);

		aeiMeasureEClass = createEClass(AEI_MEASURE);
		createEAttribute(aeiMeasureEClass, AEI_MEASURE__INDEX);
		createEReference(aeiMeasureEClass, AEI_MEASURE__AEI);

		archiIntMeasureEClass = createEClass(ARCHI_INT_MEASURE);
		createEAttribute(archiIntMeasureEClass, ARCHI_INT_MEASURE__INDEX);
		createEReference(archiIntMeasureEClass, ARCHI_INT_MEASURE__ARCHI_INTERACTION);

		// Create enums
		indexTypeEEnum = createEEnum(INDEX_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ExpressionsPackage theExpressionsPackage = (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI);
		BehaviorPackage theBehaviorPackage = (BehaviorPackage)EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI);
		mmaemiliaPackage themmaemiliaPackage = (mmaemiliaPackage)EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		measureToArchiElemInstanceEClass.getESuperTypes().add(this.getMeasureMapping());
		measureToActionEClass.getESuperTypes().add(this.getMeasureMapping());

		// Initialize classes and features; add operations and parameters
		initEClass(measureMappingEClass, MeasureMapping.class, "MeasureMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMeasureMapping_MeasureName(), ecorePackage.getEString(), "measureName", null, 1, 1, MeasureMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasureMapping_Selector(), theExpressionsPackage.getExpression(), null, "selector", null, 0, 1, MeasureMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasureMapping_Instances(), this.getAeiMeasure(), null, "instances", null, 0, -1, MeasureMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasureMapping_Actions(), this.getActionMeasure(), null, "actions", null, 0, -1, MeasureMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasureMapping_ArchiInteractions(), this.getArchiIntMeasure(), null, "archiInteractions", null, 0, -1, MeasureMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measureToArchiElemInstanceEClass, MeasureToArchiElemInstance.class, "MeasureToArchiElemInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(measureToActionEClass, MeasureToAction.class, "MeasureToAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rewMappingEClass, RewMapping.class, "RewMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRewMapping_Mappings(), this.getMeasureMapping(), null, "mappings", null, 0, -1, RewMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionMeasureEClass, ActionMeasure.class, "ActionMeasure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActionMeasure_Index(), this.getIndexType(), "index", null, 1, 1, ActionMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActionMeasure_Action(), theBehaviorPackage.getAction(), null, "action", null, 1, 1, ActionMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aeiMeasureEClass, AeiMeasure.class, "AeiMeasure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAeiMeasure_Index(), this.getIndexType(), "index", null, 1, 1, AeiMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAeiMeasure_Aei(), themmaemiliaPackage.getArchiElemInstance(), null, "aei", null, 1, 1, AeiMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(archiIntMeasureEClass, ArchiIntMeasure.class, "ArchiIntMeasure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArchiIntMeasure_Index(), this.getIndexType(), "index", null, 1, 1, ArchiIntMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchiIntMeasure_ArchiInteraction(), themmaemiliaPackage.getArchitecturalInteraction(), null, "archiInteraction", null, 1, 1, ArchiIntMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(indexTypeEEnum, IndexType.class, "IndexType");
		addEEnumLiteral(indexTypeEEnum, IndexType.RESPONSE_TIME);
		addEEnumLiteral(indexTypeEEnum, IndexType.UTILIZATION);
		addEEnumLiteral(indexTypeEEnum, IndexType.THROUGHPUT);

		// Create resource
		createResource(eNS_URI);
	}

} //MapmeasurestoindicesPackageImpl
