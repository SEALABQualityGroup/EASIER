/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior.impl;

import metamodel.mmaemilia.Behavior.*;

import org.eclipse.emf.ecore.EClass;
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
public class BehaviorFactoryImpl extends EFactoryImpl implements BehaviorFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BehaviorFactory init() {
		try {
			BehaviorFactory theBehaviorFactory = (BehaviorFactory)EPackage.Registry.INSTANCE.getEFactory(BehaviorPackage.eNS_URI);
			if (theBehaviorFactory != null) {
				return theBehaviorFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BehaviorFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorFactoryImpl() {
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
			case BehaviorPackage.BEHAVIOR: return createBehavior();
			case BehaviorPackage.BEHAV_EQUATION: return createBehavEquation();
			case BehaviorPackage.PROCESS_TERM: return createProcessTerm();
			case BehaviorPackage.STOP: return createStop();
			case BehaviorPackage.CHOICE_PROCESS: return createChoiceProcess();
			case BehaviorPackage.ACTION_PROCESS: return createActionProcess();
			case BehaviorPackage.BEHAV_PROCESS: return createBehavProcess();
			case BehaviorPackage.ACTION: return createAction();
			case BehaviorPackage.ACTION_TYPE: return createActionType();
			case BehaviorPackage.ACTION_INPUT: return createActionInput();
			case BehaviorPackage.ACTION_OUTPUT: return createActionOutput();
			case BehaviorPackage.RATE_EXP: return createRateExp();
			case BehaviorPackage.RATE_INF: return createRateInf();
			case BehaviorPackage.RATE_PAS: return createRatePas();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Behavior createBehavior() {
		BehaviorImpl behavior = new BehaviorImpl();
		return behavior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavEquation createBehavEquation() {
		BehavEquationImpl behavEquation = new BehavEquationImpl();
		return behavEquation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessTerm createProcessTerm() {
		ProcessTermImpl processTerm = new ProcessTermImpl();
		return processTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stop createStop() {
		StopImpl stop = new StopImpl();
		return stop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChoiceProcess createChoiceProcess() {
		ChoiceProcessImpl choiceProcess = new ChoiceProcessImpl();
		return choiceProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionProcess createActionProcess() {
		ActionProcessImpl actionProcess = new ActionProcessImpl();
		return actionProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavProcess createBehavProcess() {
		BehavProcessImpl behavProcess = new BehavProcessImpl();
		return behavProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Action createAction() {
		ActionImpl action = new ActionImpl();
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionType createActionType() {
		ActionTypeImpl actionType = new ActionTypeImpl();
		return actionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionInput createActionInput() {
		ActionInputImpl actionInput = new ActionInputImpl();
		return actionInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionOutput createActionOutput() {
		ActionOutputImpl actionOutput = new ActionOutputImpl();
		return actionOutput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RateExp createRateExp() {
		RateExpImpl rateExp = new RateExpImpl();
		return rateExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RateInf createRateInf() {
		RateInfImpl rateInf = new RateInfImpl();
		return rateInf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RatePas createRatePas() {
		RatePasImpl ratePas = new RatePasImpl();
		return ratePas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorPackage getBehaviorPackage() {
		return (BehaviorPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BehaviorPackage getPackage() {
		return BehaviorPackage.eINSTANCE;
	}

} //BehaviorFactoryImpl
