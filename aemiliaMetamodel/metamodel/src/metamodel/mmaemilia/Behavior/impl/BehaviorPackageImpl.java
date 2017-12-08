/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior.impl;

import metamodel.mmaemilia.Behavior.Action;
import metamodel.mmaemilia.Behavior.ActionInput;
import metamodel.mmaemilia.Behavior.ActionOutput;
import metamodel.mmaemilia.Behavior.ActionProcess;
import metamodel.mmaemilia.Behavior.ActionRate;
import metamodel.mmaemilia.Behavior.ActionType;
import metamodel.mmaemilia.Behavior.BehavEquation;
import metamodel.mmaemilia.Behavior.BehavProcess;
import metamodel.mmaemilia.Behavior.Behavior;
import metamodel.mmaemilia.Behavior.BehaviorFactory;
import metamodel.mmaemilia.Behavior.BehaviorPackage;
import metamodel.mmaemilia.Behavior.ChoiceProcess;
import metamodel.mmaemilia.Behavior.ProcessTerm;
import metamodel.mmaemilia.Behavior.RateExp;
import metamodel.mmaemilia.Behavior.RateInf;
import metamodel.mmaemilia.Behavior.RatePas;
import metamodel.mmaemilia.Behavior.Stop;

import metamodel.mmaemilia.DataType.DataTypePackage;

import metamodel.mmaemilia.DataType.impl.DataTypePackageImpl;

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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BehaviorPackageImpl extends EPackageImpl implements BehaviorPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behaviorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behavEquationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processTermEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stopEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass choiceProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behavProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionOutputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionRateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rateExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rateInfEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ratePasEClass = null;

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
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BehaviorPackageImpl() {
		super(eNS_URI, BehaviorFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link BehaviorPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BehaviorPackage init() {
		if (isInited) return (BehaviorPackage)EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI);

		// Obtain or create and register package
		BehaviorPackageImpl theBehaviorPackage = (BehaviorPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BehaviorPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BehaviorPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		mmaemiliaPackageImpl themmaemiliaPackage = (mmaemiliaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI) instanceof mmaemiliaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI) : mmaemiliaPackage.eINSTANCE);
		DataTypePackageImpl theDataTypePackage = (DataTypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI) instanceof DataTypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI) : DataTypePackage.eINSTANCE);
		ExpressionsPackageImpl theExpressionsPackage = (ExpressionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) : ExpressionsPackage.eINSTANCE);
		HeadersPackageImpl theHeadersPackage = (HeadersPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI) instanceof HeadersPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI) : HeadersPackage.eINSTANCE);
		QueueingNetworkPackageImpl theQueueingNetworkPackage = (QueueingNetworkPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI) instanceof QueueingNetworkPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI) : QueueingNetworkPackage.eINSTANCE);

		// Create package meta-data objects
		theBehaviorPackage.createPackageContents();
		themmaemiliaPackage.createPackageContents();
		theDataTypePackage.createPackageContents();
		theExpressionsPackage.createPackageContents();
		theHeadersPackage.createPackageContents();
		theQueueingNetworkPackage.createPackageContents();

		// Initialize created meta-data
		theBehaviorPackage.initializePackageContents();
		themmaemiliaPackage.initializePackageContents();
		theDataTypePackage.initializePackageContents();
		theExpressionsPackage.initializePackageContents();
		theHeadersPackage.initializePackageContents();
		theQueueingNetworkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBehaviorPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BehaviorPackage.eNS_URI, theBehaviorPackage);
		return theBehaviorPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBehavior() {
		return behaviorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavior_Equations() {
		return (EReference)behaviorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBehavEquation() {
		return behavEquationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBehavEquation_Name() {
		return (EAttribute)behavEquationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavEquation_BHeader() {
		return (EReference)behavEquationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavEquation_Pt() {
		return (EReference)behavEquationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessTerm() {
		return processTermEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessTerm_Condition() {
		return (EReference)processTermEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStop() {
		return stopEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChoiceProcess() {
		return choiceProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChoiceProcess_Processes() {
		return (EReference)choiceProcessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionProcess() {
		return actionProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionProcess_Process() {
		return (EReference)actionProcessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionProcess_Act() {
		return (EReference)actionProcessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBehavProcess() {
		return behavProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavProcess_EqCall() {
		return (EReference)behavProcessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavProcess_Exprs() {
		return (EReference)behavProcessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAction() {
		return actionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_Name() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_ActThroughtput() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_ActUtilization() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_ActResponseTime() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAction_Type() {
		return (EReference)actionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAction_Rate() {
		return (EReference)actionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAction_Is() {
		return (EReference)actionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAction_Belongs() {
		return (EReference)actionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_ActThDistr() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_ActRespTimeDistr() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_ActUtilDistr() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAction_Elem() {
		return (EReference)actionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionType() {
		return actionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionInput() {
		return actionInputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionInput_Var() {
		return (EAttribute)actionInputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionOutput() {
		return actionOutputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionOutput_ExprOutput() {
		return (EReference)actionOutputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionRate() {
		return actionRateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRateExp() {
		return rateExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRateExp_Expr() {
		return (EReference)rateExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRateInf() {
		return rateInfEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRateInf_Inf_priority() {
		return (EReference)rateInfEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRateInf_Inf_weight() {
		return (EReference)rateInfEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRatePas() {
		return ratePasEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRatePas_Priority() {
		return (EReference)ratePasEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRatePas_Weight() {
		return (EReference)ratePasEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorFactory getBehaviorFactory() {
		return (BehaviorFactory)getEFactoryInstance();
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
		behaviorEClass = createEClass(BEHAVIOR);
		createEReference(behaviorEClass, BEHAVIOR__EQUATIONS);

		behavEquationEClass = createEClass(BEHAV_EQUATION);
		createEAttribute(behavEquationEClass, BEHAV_EQUATION__NAME);
		createEReference(behavEquationEClass, BEHAV_EQUATION__BHEADER);
		createEReference(behavEquationEClass, BEHAV_EQUATION__PT);

		processTermEClass = createEClass(PROCESS_TERM);
		createEReference(processTermEClass, PROCESS_TERM__CONDITION);

		stopEClass = createEClass(STOP);

		choiceProcessEClass = createEClass(CHOICE_PROCESS);
		createEReference(choiceProcessEClass, CHOICE_PROCESS__PROCESSES);

		actionProcessEClass = createEClass(ACTION_PROCESS);
		createEReference(actionProcessEClass, ACTION_PROCESS__PROCESS);
		createEReference(actionProcessEClass, ACTION_PROCESS__ACT);

		behavProcessEClass = createEClass(BEHAV_PROCESS);
		createEReference(behavProcessEClass, BEHAV_PROCESS__EQ_CALL);
		createEReference(behavProcessEClass, BEHAV_PROCESS__EXPRS);

		actionEClass = createEClass(ACTION);
		createEAttribute(actionEClass, ACTION__NAME);
		createEAttribute(actionEClass, ACTION__ACT_THROUGHTPUT);
		createEAttribute(actionEClass, ACTION__ACT_UTILIZATION);
		createEAttribute(actionEClass, ACTION__ACT_RESPONSE_TIME);
		createEReference(actionEClass, ACTION__TYPE);
		createEReference(actionEClass, ACTION__RATE);
		createEReference(actionEClass, ACTION__IS);
		createEReference(actionEClass, ACTION__BELONGS);
		createEAttribute(actionEClass, ACTION__ACT_TH_DISTR);
		createEAttribute(actionEClass, ACTION__ACT_RESP_TIME_DISTR);
		createEAttribute(actionEClass, ACTION__ACT_UTIL_DISTR);
		createEReference(actionEClass, ACTION__ELEM);

		actionTypeEClass = createEClass(ACTION_TYPE);

		actionInputEClass = createEClass(ACTION_INPUT);
		createEAttribute(actionInputEClass, ACTION_INPUT__VAR);

		actionOutputEClass = createEClass(ACTION_OUTPUT);
		createEReference(actionOutputEClass, ACTION_OUTPUT__EXPR_OUTPUT);

		actionRateEClass = createEClass(ACTION_RATE);

		rateExpEClass = createEClass(RATE_EXP);
		createEReference(rateExpEClass, RATE_EXP__EXPR);

		rateInfEClass = createEClass(RATE_INF);
		createEReference(rateInfEClass, RATE_INF__INF_PRIORITY);
		createEReference(rateInfEClass, RATE_INF__INF_WEIGHT);

		ratePasEClass = createEClass(RATE_PAS);
		createEReference(ratePasEClass, RATE_PAS__PRIORITY);
		createEReference(ratePasEClass, RATE_PAS__WEIGHT);
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
		mmaemiliaPackage themmaemiliaPackage = (mmaemiliaPackage)EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		stopEClass.getESuperTypes().add(this.getProcessTerm());
		choiceProcessEClass.getESuperTypes().add(this.getProcessTerm());
		actionProcessEClass.getESuperTypes().add(this.getProcessTerm());
		behavProcessEClass.getESuperTypes().add(this.getProcessTerm());
		actionInputEClass.getESuperTypes().add(this.getActionType());
		actionOutputEClass.getESuperTypes().add(this.getActionType());
		rateExpEClass.getESuperTypes().add(this.getActionRate());
		rateInfEClass.getESuperTypes().add(this.getActionRate());
		ratePasEClass.getESuperTypes().add(this.getActionRate());

		// Initialize classes and features; add operations and parameters
		initEClass(behaviorEClass, Behavior.class, "Behavior", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBehavior_Equations(), this.getBehavEquation(), null, "equations", null, 0, -1, Behavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(behavEquationEClass, BehavEquation.class, "BehavEquation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBehavEquation_Name(), ecorePackage.getEString(), "name", null, 1, 1, BehavEquation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBehavEquation_BHeader(), theHeadersPackage.getBehavHeader(), null, "bHeader", null, 1, 1, BehavEquation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBehavEquation_Pt(), this.getProcessTerm(), null, "pt", null, 1, 1, BehavEquation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processTermEClass, ProcessTerm.class, "ProcessTerm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProcessTerm_Condition(), theExpressionsPackage.getExpression(), null, "condition", null, 0, 1, ProcessTerm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stopEClass, Stop.class, "Stop", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(choiceProcessEClass, ChoiceProcess.class, "ChoiceProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChoiceProcess_Processes(), this.getProcessTerm(), null, "processes", null, 0, -1, ChoiceProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionProcessEClass, ActionProcess.class, "ActionProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionProcess_Process(), this.getProcessTerm(), null, "process", null, 1, 1, ActionProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActionProcess_Act(), this.getAction(), null, "act", null, 1, 1, ActionProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(behavProcessEClass, BehavProcess.class, "BehavProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBehavProcess_EqCall(), this.getBehavEquation(), null, "eqCall", null, 1, 1, BehavProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBehavProcess_Exprs(), theExpressionsPackage.getExpression(), null, "exprs", null, 0, -1, BehavProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionEClass, Action.class, "Action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAction_Name(), ecorePackage.getEString(), "name", null, 1, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_ActThroughtput(), ecorePackage.getEFloat(), "actThroughtput", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_ActUtilization(), ecorePackage.getEFloat(), "actUtilization", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_ActResponseTime(), ecorePackage.getEFloat(), "actResponseTime", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Type(), this.getActionType(), null, "type", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Rate(), this.getActionRate(), null, "rate", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Is(), themmaemiliaPackage.getLocalInteraction(), null, "is", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Belongs(), themmaemiliaPackage.getElemType(), null, "belongs", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_ActThDistr(), ecorePackage.getEString(), "actThDistr", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_ActRespTimeDistr(), ecorePackage.getEString(), "actRespTimeDistr", "", 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_ActUtilDistr(), ecorePackage.getEString(), "actUtilDistr", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Elem(), themmaemiliaPackage.getElem(), null, "elem", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionTypeEClass, ActionType.class, "ActionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(actionInputEClass, ActionInput.class, "ActionInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActionInput_Var(), ecorePackage.getEString(), "var", null, 1, -1, ActionInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionOutputEClass, ActionOutput.class, "ActionOutput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionOutput_ExprOutput(), theExpressionsPackage.getExpression(), null, "exprOutput", null, 0, -1, ActionOutput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionRateEClass, ActionRate.class, "ActionRate", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rateExpEClass, RateExp.class, "RateExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRateExp_Expr(), theExpressionsPackage.getExpression(), null, "expr", null, 1, 1, RateExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rateInfEClass, RateInf.class, "RateInf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRateInf_Inf_priority(), theExpressionsPackage.getExpression(), null, "inf_priority", null, 0, 1, RateInf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRateInf_Inf_weight(), theExpressionsPackage.getExpression(), null, "inf_weight", null, 0, 1, RateInf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ratePasEClass, RatePas.class, "RatePas", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRatePas_Priority(), theExpressionsPackage.getExpression(), null, "priority", null, 0, 1, RatePas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRatePas_Weight(), theExpressionsPackage.getExpression(), null, "weight", null, 0, 1, RatePas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //BehaviorPackageImpl
