/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType.impl;

import metamodel.mmaemilia.Behavior.BehaviorPackage;

import metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl;

import metamodel.mmaemilia.DataType.Array;
import metamodel.mmaemilia.DataType.DataType;
import metamodel.mmaemilia.DataType.DataTypeFactory;
import metamodel.mmaemilia.DataType.DataTypePackage;
import metamodel.mmaemilia.DataType.List;
import metamodel.mmaemilia.DataType.Normal;
import metamodel.mmaemilia.DataType.RangeInt;
import metamodel.mmaemilia.DataType.Real;
import metamodel.mmaemilia.DataType.Record;
import metamodel.mmaemilia.DataType.Special;
import metamodel.mmaemilia.DataType.SpecialType;

import metamodel.mmaemilia.Expressions.ExpressionsPackage;

import metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl;

import metamodel.mmaemilia.Headers.HeadersPackage;

import metamodel.mmaemilia.Headers.impl.HeadersPackageImpl;

import metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage;
import metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl;
import metamodel.mmaemilia.impl.mmaemiliaPackageImpl;

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
public class DataTypePackageImpl extends EPackageImpl implements DataTypePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass normalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specialEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass recordEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass realEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rangeIntEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum specialTypeEEnum = null;

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
	 * @see metamodel.mmaemilia.DataType.DataTypePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DataTypePackageImpl() {
		super(eNS_URI, DataTypeFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DataTypePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DataTypePackage init() {
		if (isInited) return (DataTypePackage)EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI);

		// Obtain or create and register package
		DataTypePackageImpl theDataTypePackage = (DataTypePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DataTypePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DataTypePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		mmaemiliaPackageImpl themmaemiliaPackage = (mmaemiliaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI) instanceof mmaemiliaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI) : mmaemiliaPackage.eINSTANCE);
		ExpressionsPackageImpl theExpressionsPackage = (ExpressionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) : ExpressionsPackage.eINSTANCE);
		HeadersPackageImpl theHeadersPackage = (HeadersPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI) instanceof HeadersPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI) : HeadersPackage.eINSTANCE);
		BehaviorPackageImpl theBehaviorPackage = (BehaviorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI) instanceof BehaviorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI) : BehaviorPackage.eINSTANCE);
		QueueingNetworkPackageImpl theQueueingNetworkPackage = (QueueingNetworkPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI) instanceof QueueingNetworkPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI) : QueueingNetworkPackage.eINSTANCE);

		// Create package meta-data objects
		theDataTypePackage.createPackageContents();
		themmaemiliaPackage.createPackageContents();
		theExpressionsPackage.createPackageContents();
		theHeadersPackage.createPackageContents();
		theBehaviorPackage.createPackageContents();
		theQueueingNetworkPackage.createPackageContents();

		// Initialize created meta-data
		theDataTypePackage.initializePackageContents();
		themmaemiliaPackage.initializePackageContents();
		theExpressionsPackage.initializePackageContents();
		theHeadersPackage.initializePackageContents();
		theBehaviorPackage.initializePackageContents();
		theQueueingNetworkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDataTypePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DataTypePackage.eNS_URI, theDataTypePackage);
		return theDataTypePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataType() {
		return dataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNormal() {
		return normalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNormal_Var() {
		return (EReference)normalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpecial() {
		return specialEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecial_Type() {
		return (EAttribute)specialEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInteger() {
		return integerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRecord() {
		return recordEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRecord_Field_decl_seq() {
		return (EReference)recordEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArray() {
		return arrayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArray_ArrayElemType() {
		return (EReference)arrayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArray_Length() {
		return (EReference)arrayEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getList() {
		return listEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getList_ListElemType() {
		return (EReference)listEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBoolean() {
		return booleanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReal() {
		return realEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRangeInt() {
		return rangeIntEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRangeInt_MinVal() {
		return (EReference)rangeIntEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRangeInt_MaxVal() {
		return (EReference)rangeIntEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRangeInt_MinVar() {
		return (EReference)rangeIntEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRangeInt_MaxVar() {
		return (EReference)rangeIntEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSpecialType() {
		return specialTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeFactory getDataTypeFactory() {
		return (DataTypeFactory)getEFactoryInstance();
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
		dataTypeEClass = createEClass(DATA_TYPE);

		normalEClass = createEClass(NORMAL);
		createEReference(normalEClass, NORMAL__VAR);

		specialEClass = createEClass(SPECIAL);
		createEAttribute(specialEClass, SPECIAL__TYPE);

		integerEClass = createEClass(INTEGER);

		recordEClass = createEClass(RECORD);
		createEReference(recordEClass, RECORD__FIELD_DECL_SEQ);

		arrayEClass = createEClass(ARRAY);
		createEReference(arrayEClass, ARRAY__ARRAY_ELEM_TYPE);
		createEReference(arrayEClass, ARRAY__LENGTH);

		listEClass = createEClass(LIST);
		createEReference(listEClass, LIST__LIST_ELEM_TYPE);

		booleanEClass = createEClass(BOOLEAN);

		realEClass = createEClass(REAL);

		rangeIntEClass = createEClass(RANGE_INT);
		createEReference(rangeIntEClass, RANGE_INT__MAX_VAL);
		createEReference(rangeIntEClass, RANGE_INT__MIN_VAL);
		createEReference(rangeIntEClass, RANGE_INT__MIN_VAR);
		createEReference(rangeIntEClass, RANGE_INT__MAX_VAR);

		// Create enums
		specialTypeEEnum = createEEnum(SPECIAL_TYPE);
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
		HeadersPackage theHeadersPackage = (HeadersPackage)EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI);
		ExpressionsPackage theExpressionsPackage = (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		normalEClass.getESuperTypes().add(this.getDataType());
		specialEClass.getESuperTypes().add(this.getDataType());
		integerEClass.getESuperTypes().add(this.getNormal());
		recordEClass.getESuperTypes().add(this.getNormal());
		arrayEClass.getESuperTypes().add(this.getNormal());
		listEClass.getESuperTypes().add(this.getNormal());
		booleanEClass.getESuperTypes().add(this.getNormal());
		realEClass.getESuperTypes().add(this.getNormal());
		rangeIntEClass.getESuperTypes().add(this.getInteger());

		// Initialize classes and features; add operations and parameters
		initEClass(dataTypeEClass, DataType.class, "DataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(normalEClass, Normal.class, "Normal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNormal_Var(), theHeadersPackage.getConst(), null, "var", null, 0, 1, Normal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(specialEClass, Special.class, "Special", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpecial_Type(), this.getSpecialType(), "type", null, 1, 1, Special.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerEClass, metamodel.mmaemilia.DataType.Integer.class, "Integer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(recordEClass, Record.class, "Record", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRecord_Field_decl_seq(), theHeadersPackage.getVar(), null, "field_decl_seq", null, 0, -1, Record.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arrayEClass, Array.class, "Array", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArray_ArrayElemType(), this.getNormal(), null, "arrayElemType", null, 1, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArray_Length(), theExpressionsPackage.getExpression(), null, "length", null, 1, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(listEClass, List.class, "List", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getList_ListElemType(), this.getNormal(), null, "listElemType", null, 1, 1, List.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanEClass, metamodel.mmaemilia.DataType.Boolean.class, "Boolean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(realEClass, Real.class, "Real", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rangeIntEClass, RangeInt.class, "RangeInt", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRangeInt_MaxVal(), theExpressionsPackage.getExpression(), null, "maxVal", null, 1, 1, RangeInt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRangeInt_MinVal(), theExpressionsPackage.getExpression(), null, "minVal", null, 1, 1, RangeInt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRangeInt_MinVar(), theHeadersPackage.getConst(), null, "minVar", null, 0, 1, RangeInt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRangeInt_MaxVar(), theHeadersPackage.getConst(), null, "maxVar", null, 0, 1, RangeInt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(specialTypeEEnum, SpecialType.class, "SpecialType");
		addEEnumLiteral(specialTypeEEnum, SpecialType.PRIO);
		addEEnumLiteral(specialTypeEEnum, SpecialType.RATE);
		addEEnumLiteral(specialTypeEEnum, SpecialType.WEIGHT);
	}

} //DataTypePackageImpl
