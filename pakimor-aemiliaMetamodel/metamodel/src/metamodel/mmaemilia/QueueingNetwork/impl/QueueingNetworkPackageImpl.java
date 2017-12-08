/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.QueueingNetwork.impl;

import metamodel.mmaemilia.mmaemiliaPackage;
import metamodel.mmaemilia.Behavior.BehaviorPackage;
import metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl;
import metamodel.mmaemilia.DataType.DataTypePackage;
import metamodel.mmaemilia.DataType.impl.DataTypePackageImpl;
import metamodel.mmaemilia.Expressions.ExpressionsPackage;
import metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl;
import metamodel.mmaemilia.Headers.HeadersPackage;
import metamodel.mmaemilia.Headers.impl.HeadersPackageImpl;
import metamodel.mmaemilia.QueueingNetwork.QueueingNetworkFactory;
import metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage;
import metamodel.mmaemilia.QueueingNetwork.TimeUnitsType;
import metamodel.mmaemilia.QueueingNetwork.Workload;
import metamodel.mmaemilia.QueueingNetwork.WorkloadClass;
import metamodel.mmaemilia.impl.mmaemiliaPackageImpl;

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
public class QueueingNetworkPackageImpl extends EPackageImpl implements QueueingNetworkPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workloadEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workloadClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum timeUnitsTypeEEnum = null;

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
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private QueueingNetworkPackageImpl() {
		super(eNS_URI, QueueingNetworkFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link QueueingNetworkPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static QueueingNetworkPackage init() {
		if (isInited) return (QueueingNetworkPackage)EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI);

		// Obtain or create and register package
		QueueingNetworkPackageImpl theQueueingNetworkPackage = (QueueingNetworkPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof QueueingNetworkPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new QueueingNetworkPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		mmaemiliaPackageImpl themmaemiliaPackage = (mmaemiliaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI) instanceof mmaemiliaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI) : mmaemiliaPackage.eINSTANCE);
		DataTypePackageImpl theDataTypePackage = (DataTypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI) instanceof DataTypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI) : DataTypePackage.eINSTANCE);
		ExpressionsPackageImpl theExpressionsPackage = (ExpressionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) : ExpressionsPackage.eINSTANCE);
		HeadersPackageImpl theHeadersPackage = (HeadersPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI) instanceof HeadersPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI) : HeadersPackage.eINSTANCE);
		BehaviorPackageImpl theBehaviorPackage = (BehaviorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI) instanceof BehaviorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI) : BehaviorPackage.eINSTANCE);

		// Create package meta-data objects
		theQueueingNetworkPackage.createPackageContents();
		themmaemiliaPackage.createPackageContents();
		theDataTypePackage.createPackageContents();
		theExpressionsPackage.createPackageContents();
		theHeadersPackage.createPackageContents();
		theBehaviorPackage.createPackageContents();

		// Initialize created meta-data
		theQueueingNetworkPackage.initializePackageContents();
		themmaemiliaPackage.initializePackageContents();
		theDataTypePackage.initializePackageContents();
		theExpressionsPackage.initializePackageContents();
		theHeadersPackage.initializePackageContents();
		theBehaviorPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theQueueingNetworkPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(QueueingNetworkPackage.eNS_URI, theQueueingNetworkPackage);
		return theQueueingNetworkPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkload() {
		return workloadEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkload_WorkloadID() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkload_Throughput() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkload_ResidenceTime() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkload_TimeUnits() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkloadClass() {
		return workloadClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkloadClass_WorkloadID() {
		return (EAttribute)workloadClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkloadClass_Throughput() {
		return (EAttribute)workloadClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkloadClass_ResidenceTime() {
		return (EAttribute)workloadClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkloadClass_Utilization() {
		return (EAttribute)workloadClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkloadClass_QueueLength() {
		return (EAttribute)workloadClassEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkloadClass_ServiceTime() {
		return (EAttribute)workloadClassEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkloadClass_EReference0() {
		return (EReference)workloadClassEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkloadClass_EReference1() {
		return (EReference)workloadClassEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkloadClass_TimeUnits() {
		return (EAttribute)workloadClassEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTimeUnitsType() {
		return timeUnitsTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueueingNetworkFactory getQueueingNetworkFactory() {
		return (QueueingNetworkFactory)getEFactoryInstance();
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
		workloadEClass = createEClass(WORKLOAD);
		createEAttribute(workloadEClass, WORKLOAD__WORKLOAD_ID);
		createEAttribute(workloadEClass, WORKLOAD__THROUGHPUT);
		createEAttribute(workloadEClass, WORKLOAD__RESIDENCE_TIME);
		createEAttribute(workloadEClass, WORKLOAD__TIME_UNITS);

		workloadClassEClass = createEClass(WORKLOAD_CLASS);
		createEAttribute(workloadClassEClass, WORKLOAD_CLASS__WORKLOAD_ID);
		createEAttribute(workloadClassEClass, WORKLOAD_CLASS__THROUGHPUT);
		createEAttribute(workloadClassEClass, WORKLOAD_CLASS__RESIDENCE_TIME);
		createEAttribute(workloadClassEClass, WORKLOAD_CLASS__UTILIZATION);
		createEAttribute(workloadClassEClass, WORKLOAD_CLASS__QUEUE_LENGTH);
		createEAttribute(workloadClassEClass, WORKLOAD_CLASS__SERVICE_TIME);
		createEReference(workloadClassEClass, WORKLOAD_CLASS__EREFERENCE0);
		createEReference(workloadClassEClass, WORKLOAD_CLASS__EREFERENCE1);
		createEAttribute(workloadClassEClass, WORKLOAD_CLASS__TIME_UNITS);

		// Create enums
		timeUnitsTypeEEnum = createEEnum(TIME_UNITS_TYPE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(workloadEClass, Workload.class, "Workload", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkload_WorkloadID(), ecorePackage.getEString(), "workloadID", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_Throughput(), ecorePackage.getEFloat(), "throughput", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_ResidenceTime(), ecorePackage.getEFloat(), "residenceTime", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_TimeUnits(), this.getTimeUnitsType(), "timeUnits", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workloadClassEClass, WorkloadClass.class, "WorkloadClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkloadClass_WorkloadID(), ecorePackage.getEString(), "workloadID", null, 0, 1, WorkloadClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkloadClass_Throughput(), ecorePackage.getEFloat(), "throughput", null, 0, 1, WorkloadClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkloadClass_ResidenceTime(), ecorePackage.getEFloat(), "residenceTime", null, 0, 1, WorkloadClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkloadClass_Utilization(), ecorePackage.getEFloat(), "utilization", null, 0, 1, WorkloadClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkloadClass_QueueLength(), ecorePackage.getEFloat(), "queueLength", null, 0, 1, WorkloadClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkloadClass_ServiceTime(), ecorePackage.getEFloat(), "serviceTime", null, 0, 1, WorkloadClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkloadClass_EReference0(), this.getWorkloadClass(), null, "EReference0", null, 0, 1, WorkloadClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkloadClass_EReference1(), this.getWorkloadClass(), null, "EReference1", null, 0, 1, WorkloadClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkloadClass_TimeUnits(), this.getTimeUnitsType(), "timeUnits", null, 0, 1, WorkloadClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(timeUnitsTypeEEnum, TimeUnitsType.class, "TimeUnitsType");
		addEEnumLiteral(timeUnitsTypeEEnum, TimeUnitsType.DAY);
		addEEnumLiteral(timeUnitsTypeEEnum, TimeUnitsType.HR);
		addEEnumLiteral(timeUnitsTypeEEnum, TimeUnitsType.MIN);
		addEEnumLiteral(timeUnitsTypeEEnum, TimeUnitsType.SEC);
		addEEnumLiteral(timeUnitsTypeEEnum, TimeUnitsType.MS);
		addEEnumLiteral(timeUnitsTypeEEnum, TimeUnitsType.NS);
	}

} //QueueingNetworkPackageImpl
