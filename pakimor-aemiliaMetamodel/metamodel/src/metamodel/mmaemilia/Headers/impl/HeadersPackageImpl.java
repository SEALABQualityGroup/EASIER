/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers.impl;

import metamodel.mmaemilia.Behavior.BehaviorPackage;

import metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl;

import metamodel.mmaemilia.DataType.DataTypePackage;

import metamodel.mmaemilia.DataType.impl.DataTypePackageImpl;

import metamodel.mmaemilia.Expressions.ExpressionsPackage;

import metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl;

import metamodel.mmaemilia.Headers.AT_Header;
import metamodel.mmaemilia.Headers.BehavHeader;
import metamodel.mmaemilia.Headers.Const;
import metamodel.mmaemilia.Headers.ConstInit;
import metamodel.mmaemilia.Headers.ET_Header;
import metamodel.mmaemilia.Headers.HeadersFactory;
import metamodel.mmaemilia.Headers.HeadersPackage;
import metamodel.mmaemilia.Headers.LeftSide;
import metamodel.mmaemilia.Headers.Local;
import metamodel.mmaemilia.Headers.RightSide;
import metamodel.mmaemilia.Headers.Var;
import metamodel.mmaemilia.Headers.VarInit;

import metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage;
import metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl;
import metamodel.mmaemilia.impl.mmaemiliaPackageImpl;

import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HeadersPackageImpl extends EPackageImpl implements HeadersPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aT_HeaderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constInitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eT_HeaderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behavHeaderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass leftSideEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rightSideEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varInitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass localEClass = null;

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
	 * @see metamodel.mmaemilia.Headers.HeadersPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private HeadersPackageImpl() {
		super(eNS_URI, HeadersFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link HeadersPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static HeadersPackage init() {
		if (isInited) return (HeadersPackage)EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI);

		// Obtain or create and register package
		HeadersPackageImpl theHeadersPackage = (HeadersPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof HeadersPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new HeadersPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		mmaemiliaPackageImpl themmaemiliaPackage = (mmaemiliaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI) instanceof mmaemiliaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI) : mmaemiliaPackage.eINSTANCE);
		DataTypePackageImpl theDataTypePackage = (DataTypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI) instanceof DataTypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI) : DataTypePackage.eINSTANCE);
		ExpressionsPackageImpl theExpressionsPackage = (ExpressionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) : ExpressionsPackage.eINSTANCE);
		BehaviorPackageImpl theBehaviorPackage = (BehaviorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI) instanceof BehaviorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI) : BehaviorPackage.eINSTANCE);
		QueueingNetworkPackageImpl theQueueingNetworkPackage = (QueueingNetworkPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI) instanceof QueueingNetworkPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI) : QueueingNetworkPackage.eINSTANCE);

		// Create package meta-data objects
		theHeadersPackage.createPackageContents();
		themmaemiliaPackage.createPackageContents();
		theDataTypePackage.createPackageContents();
		theExpressionsPackage.createPackageContents();
		theBehaviorPackage.createPackageContents();
		theQueueingNetworkPackage.createPackageContents();

		// Initialize created meta-data
		theHeadersPackage.initializePackageContents();
		themmaemiliaPackage.initializePackageContents();
		theDataTypePackage.initializePackageContents();
		theExpressionsPackage.initializePackageContents();
		theBehaviorPackage.initializePackageContents();
		theQueueingNetworkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theHeadersPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(HeadersPackage.eNS_URI, theHeadersPackage);
		return theHeadersPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAT_Header() {
		return aT_HeaderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAT_Header_InitConst() {
		return (EReference)aT_HeaderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstInit() {
		return constInitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstInit_Name() {
		return (EAttribute)constInitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstInit_InitConstData() {
		return (EReference)constInitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstInit_InitConstExpr() {
		return (EReference)constInitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getET_Header() {
		return eT_HeaderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getET_Header_Costant() {
		return (EReference)eT_HeaderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConst() {
		return constEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConst_Name() {
		return (EAttribute)constEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConst_ConstantData() {
		return (EReference)constEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBehavHeader() {
		return behavHeaderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavHeader_Left() {
		return (EReference)behavHeaderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavHeader_Right() {
		return (EReference)behavHeaderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLeftSide() {
		return leftSideEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLeftSide_InitVar() {
		return (EReference)leftSideEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLeftSide_VarDef() {
		return (EReference)leftSideEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRightSide() {
		return rightSideEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRightSide_LocalDef() {
		return (EReference)rightSideEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarInit() {
		return varInitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVarInit_Name() {
		return (EAttribute)varInitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVarInit_InitVarExpr() {
		return (EReference)varInitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVarInit_InitVarType() {
		return (EReference)varInitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVar() {
		return varEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVar_Name() {
		return (EAttribute)varEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVar_VarType() {
		return (EReference)varEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocal() {
		return localEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocal_Name() {
		return (EAttribute)localEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLocal_LocalType() {
		return (EReference)localEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeadersFactory getHeadersFactory() {
		return (HeadersFactory)getEFactoryInstance();
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
		aT_HeaderEClass = createEClass(AT_HEADER);
		createEReference(aT_HeaderEClass, AT_HEADER__INIT_CONST);

		constInitEClass = createEClass(CONST_INIT);
		createEAttribute(constInitEClass, CONST_INIT__NAME);
		createEReference(constInitEClass, CONST_INIT__INIT_CONST_DATA);
		createEReference(constInitEClass, CONST_INIT__INIT_CONST_EXPR);

		eT_HeaderEClass = createEClass(ET_HEADER);
		createEReference(eT_HeaderEClass, ET_HEADER__COSTANT);

		constEClass = createEClass(CONST);
		createEAttribute(constEClass, CONST__NAME);
		createEReference(constEClass, CONST__CONSTANT_DATA);

		behavHeaderEClass = createEClass(BEHAV_HEADER);
		createEReference(behavHeaderEClass, BEHAV_HEADER__LEFT);
		createEReference(behavHeaderEClass, BEHAV_HEADER__RIGHT);

		leftSideEClass = createEClass(LEFT_SIDE);
		createEReference(leftSideEClass, LEFT_SIDE__INIT_VAR);
		createEReference(leftSideEClass, LEFT_SIDE__VAR_DEF);

		rightSideEClass = createEClass(RIGHT_SIDE);
		createEReference(rightSideEClass, RIGHT_SIDE__LOCAL_DEF);

		varInitEClass = createEClass(VAR_INIT);
		createEAttribute(varInitEClass, VAR_INIT__NAME);
		createEReference(varInitEClass, VAR_INIT__INIT_VAR_EXPR);
		createEReference(varInitEClass, VAR_INIT__INIT_VAR_TYPE);

		varEClass = createEClass(VAR);
		createEAttribute(varEClass, VAR__NAME);
		createEReference(varEClass, VAR__VAR_TYPE);

		localEClass = createEClass(LOCAL);
		createEAttribute(localEClass, LOCAL__NAME);
		createEReference(localEClass, LOCAL__LOCAL_TYPE);
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
		DataTypePackage theDataTypePackage = (DataTypePackage)EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI);
		ExpressionsPackage theExpressionsPackage = (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(aT_HeaderEClass, AT_Header.class, "AT_Header", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAT_Header_InitConst(), this.getConstInit(), null, "initConst", null, 0, -1, AT_Header.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constInitEClass, ConstInit.class, "ConstInit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstInit_Name(), ecorePackage.getEString(), "name", null, 1, 1, ConstInit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstInit_InitConstData(), theDataTypePackage.getDataType(), null, "initConstData", null, 1, 1, ConstInit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstInit_InitConstExpr(), theExpressionsPackage.getExpression(), null, "initConstExpr", null, 1, 1, ConstInit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eT_HeaderEClass, ET_Header.class, "ET_Header", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getET_Header_Costant(), this.getConst(), null, "costant", null, 0, -1, ET_Header.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constEClass, Const.class, "Const", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConst_Name(), ecorePackage.getEString(), "name", null, 1, 1, Const.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConst_ConstantData(), theDataTypePackage.getDataType(), null, "constantData", null, 1, 1, Const.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(behavHeaderEClass, BehavHeader.class, "BehavHeader", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBehavHeader_Left(), this.getLeftSide(), null, "left", null, 0, 1, BehavHeader.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBehavHeader_Right(), this.getRightSide(), null, "right", null, 0, 1, BehavHeader.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(leftSideEClass, LeftSide.class, "LeftSide", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLeftSide_InitVar(), this.getVarInit(), null, "initVar", null, 0, -1, LeftSide.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLeftSide_VarDef(), this.getVar(), null, "varDef", null, 0, -1, LeftSide.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rightSideEClass, RightSide.class, "RightSide", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRightSide_LocalDef(), this.getLocal(), null, "localDef", null, 0, -1, RightSide.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(varInitEClass, VarInit.class, "VarInit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVarInit_Name(), ecorePackage.getEString(), "name", null, 1, 1, VarInit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVarInit_InitVarExpr(), theExpressionsPackage.getExpression(), null, "initVarExpr", null, 1, 1, VarInit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVarInit_InitVarType(), theDataTypePackage.getNormal(), null, "initVarType", null, 1, 1, VarInit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(varEClass, Var.class, "Var", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVar_Name(), ecorePackage.getEString(), "name", null, 1, 1, Var.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVar_VarType(), theDataTypePackage.getNormal(), null, "varType", null, 1, 1, Var.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(localEClass, Local.class, "Local", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLocal_Name(), ecorePackage.getEString(), "name", null, 1, 1, Local.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLocal_LocalType(), theDataTypePackage.getNormal(), null, "localType", null, 1, 1, Local.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //HeadersPackageImpl
