/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see metamodel.mmaemilia.Behavior.BehaviorFactory
 * @model kind="package"
 * @generated
 */
public interface BehaviorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Behavior";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://behavior.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Behavior";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BehaviorPackage eINSTANCE = metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl.init();

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.BehaviorImpl <em>Behavior</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getBehavior()
	 * @generated
	 */
	int BEHAVIOR = 0;

	/**
	 * The feature id for the '<em><b>Equations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR__EQUATIONS = 0;

	/**
	 * The number of structural features of the '<em>Behavior</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.BehavEquationImpl <em>Behav Equation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.BehavEquationImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getBehavEquation()
	 * @generated
	 */
	int BEHAV_EQUATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAV_EQUATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>BHeader</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAV_EQUATION__BHEADER = 1;

	/**
	 * The feature id for the '<em><b>Pt</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAV_EQUATION__PT = 2;

	/**
	 * The number of structural features of the '<em>Behav Equation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAV_EQUATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.ProcessTermImpl <em>Process Term</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.ProcessTermImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getProcessTerm()
	 * @generated
	 */
	int PROCESS_TERM = 2;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TERM__CONDITION = 0;

	/**
	 * The number of structural features of the '<em>Process Term</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TERM_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.StopImpl <em>Stop</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.StopImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getStop()
	 * @generated
	 */
	int STOP = 3;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP__CONDITION = PROCESS_TERM__CONDITION;

	/**
	 * The number of structural features of the '<em>Stop</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_FEATURE_COUNT = PROCESS_TERM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.ChoiceProcessImpl <em>Choice Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.ChoiceProcessImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getChoiceProcess()
	 * @generated
	 */
	int CHOICE_PROCESS = 4;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_PROCESS__CONDITION = PROCESS_TERM__CONDITION;

	/**
	 * The feature id for the '<em><b>Processes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_PROCESS__PROCESSES = PROCESS_TERM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Choice Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_PROCESS_FEATURE_COUNT = PROCESS_TERM_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.ActionProcessImpl <em>Action Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.ActionProcessImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getActionProcess()
	 * @generated
	 */
	int ACTION_PROCESS = 5;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_PROCESS__CONDITION = PROCESS_TERM__CONDITION;

	/**
	 * The feature id for the '<em><b>Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_PROCESS__PROCESS = PROCESS_TERM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Act</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_PROCESS__ACT = PROCESS_TERM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Action Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_PROCESS_FEATURE_COUNT = PROCESS_TERM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.BehavProcessImpl <em>Behav Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.BehavProcessImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getBehavProcess()
	 * @generated
	 */
	int BEHAV_PROCESS = 6;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAV_PROCESS__CONDITION = PROCESS_TERM__CONDITION;

	/**
	 * The feature id for the '<em><b>Eq Call</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAV_PROCESS__EQ_CALL = PROCESS_TERM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Exprs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAV_PROCESS__EXPRS = PROCESS_TERM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Behav Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAV_PROCESS_FEATURE_COUNT = PROCESS_TERM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.ActionImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Act Throughtput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACT_THROUGHTPUT = 1;

	/**
	 * The feature id for the '<em><b>Act Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACT_UTILIZATION = 2;

	/**
	 * The feature id for the '<em><b>Act Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACT_RESPONSE_TIME = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TYPE = 4;

	/**
	 * The feature id for the '<em><b>Rate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__RATE = 5;

	/**
	 * The feature id for the '<em><b>Is</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__IS = 6;

	/**
	 * The feature id for the '<em><b>Belongs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__BELONGS = 7;

	/**
	 * The feature id for the '<em><b>Act Th Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACT_TH_DISTR = 8;

	/**
	 * The feature id for the '<em><b>Act Resp Time Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACT_RESP_TIME_DISTR = 9;

	/**
	 * The feature id for the '<em><b>Act Util Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACT_UTIL_DISTR = 10;

	/**
	 * The feature id for the '<em><b>Elem</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ELEM = 11;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.ActionTypeImpl <em>Action Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.ActionTypeImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getActionType()
	 * @generated
	 */
	int ACTION_TYPE = 8;

	/**
	 * The number of structural features of the '<em>Action Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.ActionInputImpl <em>Action Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.ActionInputImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getActionInput()
	 * @generated
	 */
	int ACTION_INPUT = 9;

	/**
	 * The feature id for the '<em><b>Var</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_INPUT__VAR = ACTION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Action Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_INPUT_FEATURE_COUNT = ACTION_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.ActionOutputImpl <em>Action Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.ActionOutputImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getActionOutput()
	 * @generated
	 */
	int ACTION_OUTPUT = 10;

	/**
	 * The feature id for the '<em><b>Expr Output</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OUTPUT__EXPR_OUTPUT = ACTION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Action Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OUTPUT_FEATURE_COUNT = ACTION_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.ActionRateImpl <em>Action Rate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.ActionRateImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getActionRate()
	 * @generated
	 */
	int ACTION_RATE = 11;

	/**
	 * The number of structural features of the '<em>Action Rate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_RATE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.RateExpImpl <em>Rate Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.RateExpImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getRateExp()
	 * @generated
	 */
	int RATE_EXP = 12;

	/**
	 * The feature id for the '<em><b>Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_EXP__EXPR = ACTION_RATE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Rate Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_EXP_FEATURE_COUNT = ACTION_RATE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.RateInfImpl <em>Rate Inf</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.RateInfImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getRateInf()
	 * @generated
	 */
	int RATE_INF = 13;

	/**
	 * The feature id for the '<em><b>Inf priority</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_INF__INF_PRIORITY = ACTION_RATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Inf weight</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_INF__INF_WEIGHT = ACTION_RATE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Rate Inf</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_INF_FEATURE_COUNT = ACTION_RATE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.Behavior.impl.RatePasImpl <em>Rate Pas</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.Behavior.impl.RatePasImpl
	 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getRatePas()
	 * @generated
	 */
	int RATE_PAS = 14;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_PAS__PRIORITY = ACTION_RATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_PAS__WEIGHT = ACTION_RATE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Rate Pas</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_PAS_FEATURE_COUNT = ACTION_RATE_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.Behavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior</em>'.
	 * @see metamodel.mmaemilia.Behavior.Behavior
	 * @generated
	 */
	EClass getBehavior();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.Behavior.Behavior#getEquations <em>Equations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Equations</em>'.
	 * @see metamodel.mmaemilia.Behavior.Behavior#getEquations()
	 * @see #getBehavior()
	 * @generated
	 */
	EReference getBehavior_Equations();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.BehavEquation <em>Behav Equation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behav Equation</em>'.
	 * @see metamodel.mmaemilia.Behavior.BehavEquation
	 * @generated
	 */
	EClass getBehavEquation();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Behavior.BehavEquation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see metamodel.mmaemilia.Behavior.BehavEquation#getName()
	 * @see #getBehavEquation()
	 * @generated
	 */
	EAttribute getBehavEquation_Name();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.BehavEquation#getBHeader <em>BHeader</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>BHeader</em>'.
	 * @see metamodel.mmaemilia.Behavior.BehavEquation#getBHeader()
	 * @see #getBehavEquation()
	 * @generated
	 */
	EReference getBehavEquation_BHeader();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.BehavEquation#getPt <em>Pt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pt</em>'.
	 * @see metamodel.mmaemilia.Behavior.BehavEquation#getPt()
	 * @see #getBehavEquation()
	 * @generated
	 */
	EReference getBehavEquation_Pt();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.ProcessTerm <em>Process Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Term</em>'.
	 * @see metamodel.mmaemilia.Behavior.ProcessTerm
	 * @generated
	 */
	EClass getProcessTerm();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.ProcessTerm#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see metamodel.mmaemilia.Behavior.ProcessTerm#getCondition()
	 * @see #getProcessTerm()
	 * @generated
	 */
	EReference getProcessTerm_Condition();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.Stop <em>Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stop</em>'.
	 * @see metamodel.mmaemilia.Behavior.Stop
	 * @generated
	 */
	EClass getStop();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.ChoiceProcess <em>Choice Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Choice Process</em>'.
	 * @see metamodel.mmaemilia.Behavior.ChoiceProcess
	 * @generated
	 */
	EClass getChoiceProcess();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.Behavior.ChoiceProcess#getProcesses <em>Processes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Processes</em>'.
	 * @see metamodel.mmaemilia.Behavior.ChoiceProcess#getProcesses()
	 * @see #getChoiceProcess()
	 * @generated
	 */
	EReference getChoiceProcess_Processes();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.ActionProcess <em>Action Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Process</em>'.
	 * @see metamodel.mmaemilia.Behavior.ActionProcess
	 * @generated
	 */
	EClass getActionProcess();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.ActionProcess#getProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process</em>'.
	 * @see metamodel.mmaemilia.Behavior.ActionProcess#getProcess()
	 * @see #getActionProcess()
	 * @generated
	 */
	EReference getActionProcess_Process();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.ActionProcess#getAct <em>Act</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Act</em>'.
	 * @see metamodel.mmaemilia.Behavior.ActionProcess#getAct()
	 * @see #getActionProcess()
	 * @generated
	 */
	EReference getActionProcess_Act();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.BehavProcess <em>Behav Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behav Process</em>'.
	 * @see metamodel.mmaemilia.Behavior.BehavProcess
	 * @generated
	 */
	EClass getBehavProcess();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.Behavior.BehavProcess#getEqCall <em>Eq Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Eq Call</em>'.
	 * @see metamodel.mmaemilia.Behavior.BehavProcess#getEqCall()
	 * @see #getBehavProcess()
	 * @generated
	 */
	EReference getBehavProcess_EqCall();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.Behavior.BehavProcess#getExprs <em>Exprs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exprs</em>'.
	 * @see metamodel.mmaemilia.Behavior.BehavProcess#getExprs()
	 * @see #getBehavProcess()
	 * @generated
	 */
	EReference getBehavProcess_Exprs();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Behavior.Action#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getName()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Name();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Behavior.Action#getActThroughtput <em>Act Throughtput</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Act Throughtput</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getActThroughtput()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_ActThroughtput();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Behavior.Action#getActUtilization <em>Act Utilization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Act Utilization</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getActUtilization()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_ActUtilization();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Behavior.Action#getActResponseTime <em>Act Response Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Act Response Time</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getActResponseTime()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_ActResponseTime();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.Action#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getType()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Type();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.Action#getRate <em>Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rate</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getRate()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Rate();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.Action#getIs <em>Is</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Is</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getIs()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Is();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.Behavior.Action#getBelongs <em>Belongs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Belongs</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getBelongs()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Belongs();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.Behavior.Action#getActThDistr <em>Act Th Distr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Act Th Distr</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getActThDistr()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_ActThDistr();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.Behavior.Action#getActRespTimeDistr <em>Act Resp Time Distr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Act Resp Time Distr</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getActRespTimeDistr()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_ActRespTimeDistr();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.Behavior.Action#getActUtilDistr <em>Act Util Distr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Act Util Distr</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getActUtilDistr()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_ActUtilDistr();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.Behavior.Action#getElem <em>Elem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Elem</em>'.
	 * @see metamodel.mmaemilia.Behavior.Action#getElem()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Elem();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.ActionType <em>Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Type</em>'.
	 * @see metamodel.mmaemilia.Behavior.ActionType
	 * @generated
	 */
	EClass getActionType();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.ActionInput <em>Action Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Input</em>'.
	 * @see metamodel.mmaemilia.Behavior.ActionInput
	 * @generated
	 */
	EClass getActionInput();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.Behavior.ActionInput#getVar <em>Var</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Var</em>'.
	 * @see metamodel.mmaemilia.Behavior.ActionInput#getVar()
	 * @see #getActionInput()
	 * @generated
	 */
	EAttribute getActionInput_Var();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.ActionOutput <em>Action Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Output</em>'.
	 * @see metamodel.mmaemilia.Behavior.ActionOutput
	 * @generated
	 */
	EClass getActionOutput();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.Behavior.ActionOutput#getExprOutput <em>Expr Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expr Output</em>'.
	 * @see metamodel.mmaemilia.Behavior.ActionOutput#getExprOutput()
	 * @see #getActionOutput()
	 * @generated
	 */
	EReference getActionOutput_ExprOutput();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.ActionRate <em>Action Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Rate</em>'.
	 * @see metamodel.mmaemilia.Behavior.ActionRate
	 * @generated
	 */
	EClass getActionRate();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.RateExp <em>Rate Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rate Exp</em>'.
	 * @see metamodel.mmaemilia.Behavior.RateExp
	 * @generated
	 */
	EClass getRateExp();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.RateExp#getExpr <em>Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expr</em>'.
	 * @see metamodel.mmaemilia.Behavior.RateExp#getExpr()
	 * @see #getRateExp()
	 * @generated
	 */
	EReference getRateExp_Expr();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.RateInf <em>Rate Inf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rate Inf</em>'.
	 * @see metamodel.mmaemilia.Behavior.RateInf
	 * @generated
	 */
	EClass getRateInf();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.RateInf#getInf_priority <em>Inf priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Inf priority</em>'.
	 * @see metamodel.mmaemilia.Behavior.RateInf#getInf_priority()
	 * @see #getRateInf()
	 * @generated
	 */
	EReference getRateInf_Inf_priority();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.RateInf#getInf_weight <em>Inf weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Inf weight</em>'.
	 * @see metamodel.mmaemilia.Behavior.RateInf#getInf_weight()
	 * @see #getRateInf()
	 * @generated
	 */
	EReference getRateInf_Inf_weight();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Behavior.RatePas <em>Rate Pas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rate Pas</em>'.
	 * @see metamodel.mmaemilia.Behavior.RatePas
	 * @generated
	 */
	EClass getRatePas();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.RatePas#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Priority</em>'.
	 * @see metamodel.mmaemilia.Behavior.RatePas#getPriority()
	 * @see #getRatePas()
	 * @generated
	 */
	EReference getRatePas_Priority();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Behavior.RatePas#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Weight</em>'.
	 * @see metamodel.mmaemilia.Behavior.RatePas#getWeight()
	 * @see #getRatePas()
	 * @generated
	 */
	EReference getRatePas_Weight();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BehaviorFactory getBehaviorFactory();

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
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.BehaviorImpl <em>Behavior</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getBehavior()
		 * @generated
		 */
		EClass BEHAVIOR = eINSTANCE.getBehavior();

		/**
		 * The meta object literal for the '<em><b>Equations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR__EQUATIONS = eINSTANCE.getBehavior_Equations();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.BehavEquationImpl <em>Behav Equation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.BehavEquationImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getBehavEquation()
		 * @generated
		 */
		EClass BEHAV_EQUATION = eINSTANCE.getBehavEquation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEHAV_EQUATION__NAME = eINSTANCE.getBehavEquation_Name();

		/**
		 * The meta object literal for the '<em><b>BHeader</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAV_EQUATION__BHEADER = eINSTANCE.getBehavEquation_BHeader();

		/**
		 * The meta object literal for the '<em><b>Pt</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAV_EQUATION__PT = eINSTANCE.getBehavEquation_Pt();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.ProcessTermImpl <em>Process Term</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.ProcessTermImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getProcessTerm()
		 * @generated
		 */
		EClass PROCESS_TERM = eINSTANCE.getProcessTerm();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_TERM__CONDITION = eINSTANCE.getProcessTerm_Condition();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.StopImpl <em>Stop</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.StopImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getStop()
		 * @generated
		 */
		EClass STOP = eINSTANCE.getStop();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.ChoiceProcessImpl <em>Choice Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.ChoiceProcessImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getChoiceProcess()
		 * @generated
		 */
		EClass CHOICE_PROCESS = eINSTANCE.getChoiceProcess();

		/**
		 * The meta object literal for the '<em><b>Processes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHOICE_PROCESS__PROCESSES = eINSTANCE.getChoiceProcess_Processes();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.ActionProcessImpl <em>Action Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.ActionProcessImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getActionProcess()
		 * @generated
		 */
		EClass ACTION_PROCESS = eINSTANCE.getActionProcess();

		/**
		 * The meta object literal for the '<em><b>Process</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_PROCESS__PROCESS = eINSTANCE.getActionProcess_Process();

		/**
		 * The meta object literal for the '<em><b>Act</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_PROCESS__ACT = eINSTANCE.getActionProcess_Act();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.BehavProcessImpl <em>Behav Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.BehavProcessImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getBehavProcess()
		 * @generated
		 */
		EClass BEHAV_PROCESS = eINSTANCE.getBehavProcess();

		/**
		 * The meta object literal for the '<em><b>Eq Call</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAV_PROCESS__EQ_CALL = eINSTANCE.getBehavProcess_EqCall();

		/**
		 * The meta object literal for the '<em><b>Exprs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAV_PROCESS__EXPRS = eINSTANCE.getBehavProcess_Exprs();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.ActionImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__NAME = eINSTANCE.getAction_Name();

		/**
		 * The meta object literal for the '<em><b>Act Throughtput</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__ACT_THROUGHTPUT = eINSTANCE.getAction_ActThroughtput();

		/**
		 * The meta object literal for the '<em><b>Act Utilization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__ACT_UTILIZATION = eINSTANCE.getAction_ActUtilization();

		/**
		 * The meta object literal for the '<em><b>Act Response Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__ACT_RESPONSE_TIME = eINSTANCE.getAction_ActResponseTime();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__TYPE = eINSTANCE.getAction_Type();

		/**
		 * The meta object literal for the '<em><b>Rate</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__RATE = eINSTANCE.getAction_Rate();

		/**
		 * The meta object literal for the '<em><b>Is</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__IS = eINSTANCE.getAction_Is();

		/**
		 * The meta object literal for the '<em><b>Belongs</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__BELONGS = eINSTANCE.getAction_Belongs();

		/**
		 * The meta object literal for the '<em><b>Act Th Distr</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__ACT_TH_DISTR = eINSTANCE.getAction_ActThDistr();

		/**
		 * The meta object literal for the '<em><b>Act Resp Time Distr</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__ACT_RESP_TIME_DISTR = eINSTANCE.getAction_ActRespTimeDistr();

		/**
		 * The meta object literal for the '<em><b>Act Util Distr</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__ACT_UTIL_DISTR = eINSTANCE.getAction_ActUtilDistr();

		/**
		 * The meta object literal for the '<em><b>Elem</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__ELEM = eINSTANCE.getAction_Elem();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.ActionTypeImpl <em>Action Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.ActionTypeImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getActionType()
		 * @generated
		 */
		EClass ACTION_TYPE = eINSTANCE.getActionType();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.ActionInputImpl <em>Action Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.ActionInputImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getActionInput()
		 * @generated
		 */
		EClass ACTION_INPUT = eINSTANCE.getActionInput();

		/**
		 * The meta object literal for the '<em><b>Var</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_INPUT__VAR = eINSTANCE.getActionInput_Var();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.ActionOutputImpl <em>Action Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.ActionOutputImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getActionOutput()
		 * @generated
		 */
		EClass ACTION_OUTPUT = eINSTANCE.getActionOutput();

		/**
		 * The meta object literal for the '<em><b>Expr Output</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_OUTPUT__EXPR_OUTPUT = eINSTANCE.getActionOutput_ExprOutput();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.ActionRateImpl <em>Action Rate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.ActionRateImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getActionRate()
		 * @generated
		 */
		EClass ACTION_RATE = eINSTANCE.getActionRate();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.RateExpImpl <em>Rate Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.RateExpImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getRateExp()
		 * @generated
		 */
		EClass RATE_EXP = eINSTANCE.getRateExp();

		/**
		 * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RATE_EXP__EXPR = eINSTANCE.getRateExp_Expr();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.RateInfImpl <em>Rate Inf</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.RateInfImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getRateInf()
		 * @generated
		 */
		EClass RATE_INF = eINSTANCE.getRateInf();

		/**
		 * The meta object literal for the '<em><b>Inf priority</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RATE_INF__INF_PRIORITY = eINSTANCE.getRateInf_Inf_priority();

		/**
		 * The meta object literal for the '<em><b>Inf weight</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RATE_INF__INF_WEIGHT = eINSTANCE.getRateInf_Inf_weight();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.Behavior.impl.RatePasImpl <em>Rate Pas</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.Behavior.impl.RatePasImpl
		 * @see metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl#getRatePas()
		 * @generated
		 */
		EClass RATE_PAS = eINSTANCE.getRatePas();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RATE_PAS__PRIORITY = eINSTANCE.getRatePas_Priority();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RATE_PAS__WEIGHT = eINSTANCE.getRatePas_Weight();

	}

} //BehaviorPackage
