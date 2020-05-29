/**
 */
package logicalSpecification.antipatterns.performance.impl;

import java.lang.reflect.InvocationTargetException;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import logicalSpecification.antipatterns.performance.PafPA;
import logicalSpecification.antipatterns.performance.PerformancePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.uml2.uml.Operation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Paf PA</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.PafPAImpl#getF_resDemandMVP <em>Fres Demand MVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.PafPAImpl#getF_opProbExecSVP <em>Fop Prob Exec SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.PafPAImpl#getF_hwUtilSVP <em>Fhw Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.PafPAImpl#getF_serviceThroughputSVP <em>Fservice Throughput SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.PafPAImpl#getT_maxResDemandMVP <em>Tmax Res Demand MVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.PafPAImpl#getT_opProbExecSVP <em>Top Prob Exec SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.PafPAImpl#getT_maxHwUtilSVP <em>Tmax Hw Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.PafPAImpl#getT_minServiceThroughputSVP <em>Tmin Service Throughput SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.PafPAImpl#getContextualElement <em>Contextual Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PafPAImpl extends PerformanceAntipatternImpl implements PafPA {
	/**
	 * The cached value of the '{@link #getF_resDemandMVP() <em>Fres Demand MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getF_resDemandMVP()
	 * @generated
	 * @ordered
	 */
	protected MultipleValuedParameter f_resDemandMVP;

	/**
	 * The cached value of the '{@link #getF_opProbExecSVP() <em>Fop Prob Exec SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getF_opProbExecSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter f_opProbExecSVP;

	/**
	 * The cached value of the '{@link #getF_hwUtilSVP() <em>Fhw Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getF_hwUtilSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter f_hwUtilSVP;

	/**
	 * The cached value of the '{@link #getF_serviceThroughputSVP() <em>Fservice Throughput SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getF_serviceThroughputSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter f_serviceThroughputSVP;

	/**
	 * The cached value of the '{@link #getT_maxResDemandMVP() <em>Tmax Res Demand MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getT_maxResDemandMVP()
	 * @generated
	 * @ordered
	 */
	protected MultipleValuedParameter t_maxResDemandMVP;

	/**
	 * The cached value of the '{@link #getT_opProbExecSVP() <em>Top Prob Exec SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getT_opProbExecSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter t_opProbExecSVP;

	/**
	 * The cached value of the '{@link #getT_maxHwUtilSVP() <em>Tmax Hw Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getT_maxHwUtilSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter t_maxHwUtilSVP;

	/**
	 * The cached value of the '{@link #getT_minServiceThroughputSVP() <em>Tmin Service Throughput SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getT_minServiceThroughputSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter t_minServiceThroughputSVP;

	/**
	 * The default value of the '{@link #getContextualElement() <em>Contextual Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextualElement()
	 * @generated
	 * @ordered
	 */
	protected static final Operation CONTEXTUAL_ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContextualElement() <em>Contextual Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextualElement()
	 * @generated
	 * @ordered
	 */
	protected Operation contextualElement = CONTEXTUAL_ELEMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PafPAImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerformancePackage.Literals.PAF_PA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultipleValuedParameter getF_resDemandMVP() {
		if (f_resDemandMVP != null && f_resDemandMVP.eIsProxy()) {
			InternalEObject oldF_resDemandMVP = (InternalEObject)f_resDemandMVP;
			f_resDemandMVP = (MultipleValuedParameter)eResolveProxy(oldF_resDemandMVP);
			if (f_resDemandMVP != oldF_resDemandMVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PAF_PA__FRES_DEMAND_MVP, oldF_resDemandMVP, f_resDemandMVP));
			}
		}
		return f_resDemandMVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultipleValuedParameter basicGetF_resDemandMVP() {
		return f_resDemandMVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setF_resDemandMVP(MultipleValuedParameter newF_resDemandMVP) {
		MultipleValuedParameter oldF_resDemandMVP = f_resDemandMVP;
		f_resDemandMVP = newF_resDemandMVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PAF_PA__FRES_DEMAND_MVP, oldF_resDemandMVP, f_resDemandMVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getF_opProbExecSVP() {
		if (f_opProbExecSVP != null && f_opProbExecSVP.eIsProxy()) {
			InternalEObject oldF_opProbExecSVP = (InternalEObject)f_opProbExecSVP;
			f_opProbExecSVP = (SingleValuedParameter)eResolveProxy(oldF_opProbExecSVP);
			if (f_opProbExecSVP != oldF_opProbExecSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PAF_PA__FOP_PROB_EXEC_SVP, oldF_opProbExecSVP, f_opProbExecSVP));
			}
		}
		return f_opProbExecSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetF_opProbExecSVP() {
		return f_opProbExecSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setF_opProbExecSVP(SingleValuedParameter newF_opProbExecSVP) {
		SingleValuedParameter oldF_opProbExecSVP = f_opProbExecSVP;
		f_opProbExecSVP = newF_opProbExecSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PAF_PA__FOP_PROB_EXEC_SVP, oldF_opProbExecSVP, f_opProbExecSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getF_hwUtilSVP() {
		if (f_hwUtilSVP != null && f_hwUtilSVP.eIsProxy()) {
			InternalEObject oldF_hwUtilSVP = (InternalEObject)f_hwUtilSVP;
			f_hwUtilSVP = (SingleValuedParameter)eResolveProxy(oldF_hwUtilSVP);
			if (f_hwUtilSVP != oldF_hwUtilSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PAF_PA__FHW_UTIL_SVP, oldF_hwUtilSVP, f_hwUtilSVP));
			}
		}
		return f_hwUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetF_hwUtilSVP() {
		return f_hwUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setF_hwUtilSVP(SingleValuedParameter newF_hwUtilSVP) {
		SingleValuedParameter oldF_hwUtilSVP = f_hwUtilSVP;
		f_hwUtilSVP = newF_hwUtilSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PAF_PA__FHW_UTIL_SVP, oldF_hwUtilSVP, f_hwUtilSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getF_serviceThroughputSVP() {
		if (f_serviceThroughputSVP != null && f_serviceThroughputSVP.eIsProxy()) {
			InternalEObject oldF_serviceThroughputSVP = (InternalEObject)f_serviceThroughputSVP;
			f_serviceThroughputSVP = (SingleValuedParameter)eResolveProxy(oldF_serviceThroughputSVP);
			if (f_serviceThroughputSVP != oldF_serviceThroughputSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PAF_PA__FSERVICE_THROUGHPUT_SVP, oldF_serviceThroughputSVP, f_serviceThroughputSVP));
			}
		}
		return f_serviceThroughputSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetF_serviceThroughputSVP() {
		return f_serviceThroughputSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setF_serviceThroughputSVP(SingleValuedParameter newF_serviceThroughputSVP) {
		SingleValuedParameter oldF_serviceThroughputSVP = f_serviceThroughputSVP;
		f_serviceThroughputSVP = newF_serviceThroughputSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PAF_PA__FSERVICE_THROUGHPUT_SVP, oldF_serviceThroughputSVP, f_serviceThroughputSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultipleValuedParameter getT_maxResDemandMVP() {
		if (t_maxResDemandMVP != null && t_maxResDemandMVP.eIsProxy()) {
			InternalEObject oldT_maxResDemandMVP = (InternalEObject)t_maxResDemandMVP;
			t_maxResDemandMVP = (MultipleValuedParameter)eResolveProxy(oldT_maxResDemandMVP);
			if (t_maxResDemandMVP != oldT_maxResDemandMVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PAF_PA__TMAX_RES_DEMAND_MVP, oldT_maxResDemandMVP, t_maxResDemandMVP));
			}
		}
		return t_maxResDemandMVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultipleValuedParameter basicGetT_maxResDemandMVP() {
		return t_maxResDemandMVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setT_maxResDemandMVP(MultipleValuedParameter newT_maxResDemandMVP) {
		MultipleValuedParameter oldT_maxResDemandMVP = t_maxResDemandMVP;
		t_maxResDemandMVP = newT_maxResDemandMVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PAF_PA__TMAX_RES_DEMAND_MVP, oldT_maxResDemandMVP, t_maxResDemandMVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getT_opProbExecSVP() {
		if (t_opProbExecSVP != null && t_opProbExecSVP.eIsProxy()) {
			InternalEObject oldT_opProbExecSVP = (InternalEObject)t_opProbExecSVP;
			t_opProbExecSVP = (SingleValuedParameter)eResolveProxy(oldT_opProbExecSVP);
			if (t_opProbExecSVP != oldT_opProbExecSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PAF_PA__TOP_PROB_EXEC_SVP, oldT_opProbExecSVP, t_opProbExecSVP));
			}
		}
		return t_opProbExecSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetT_opProbExecSVP() {
		return t_opProbExecSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setT_opProbExecSVP(SingleValuedParameter newT_opProbExecSVP) {
		SingleValuedParameter oldT_opProbExecSVP = t_opProbExecSVP;
		t_opProbExecSVP = newT_opProbExecSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PAF_PA__TOP_PROB_EXEC_SVP, oldT_opProbExecSVP, t_opProbExecSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getT_maxHwUtilSVP() {
		if (t_maxHwUtilSVP != null && t_maxHwUtilSVP.eIsProxy()) {
			InternalEObject oldT_maxHwUtilSVP = (InternalEObject)t_maxHwUtilSVP;
			t_maxHwUtilSVP = (SingleValuedParameter)eResolveProxy(oldT_maxHwUtilSVP);
			if (t_maxHwUtilSVP != oldT_maxHwUtilSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PAF_PA__TMAX_HW_UTIL_SVP, oldT_maxHwUtilSVP, t_maxHwUtilSVP));
			}
		}
		return t_maxHwUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetT_maxHwUtilSVP() {
		return t_maxHwUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setT_maxHwUtilSVP(SingleValuedParameter newT_maxHwUtilSVP) {
		SingleValuedParameter oldT_maxHwUtilSVP = t_maxHwUtilSVP;
		t_maxHwUtilSVP = newT_maxHwUtilSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PAF_PA__TMAX_HW_UTIL_SVP, oldT_maxHwUtilSVP, t_maxHwUtilSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getT_minServiceThroughputSVP() {
		if (t_minServiceThroughputSVP != null && t_minServiceThroughputSVP.eIsProxy()) {
			InternalEObject oldT_minServiceThroughputSVP = (InternalEObject)t_minServiceThroughputSVP;
			t_minServiceThroughputSVP = (SingleValuedParameter)eResolveProxy(oldT_minServiceThroughputSVP);
			if (t_minServiceThroughputSVP != oldT_minServiceThroughputSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PAF_PA__TMIN_SERVICE_THROUGHPUT_SVP, oldT_minServiceThroughputSVP, t_minServiceThroughputSVP));
			}
		}
		return t_minServiceThroughputSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetT_minServiceThroughputSVP() {
		return t_minServiceThroughputSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setT_minServiceThroughputSVP(SingleValuedParameter newT_minServiceThroughputSVP) {
		SingleValuedParameter oldT_minServiceThroughputSVP = t_minServiceThroughputSVP;
		t_minServiceThroughputSVP = newT_minServiceThroughputSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PAF_PA__TMIN_SERVICE_THROUGHPUT_SVP, oldT_minServiceThroughputSVP, t_minServiceThroughputSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getContextualElement() {
		return contextualElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContextualElement(Operation newContextualElement) {
		Operation oldContextualElement = contextualElement;
		contextualElement = newContextualElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PAF_PA__CONTEXTUAL_ELEMENT, oldContextualElement, contextualElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParametersForPartialDetection(final double T_maxResDemand, final double T_opProbExec) {
		List<Parameter> blobParams = new ArrayList<>();
		
				setF_resDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(
						OclStringManager.getInstance(null).getResDemandQuery(getContextualElement())));
				blobParams.add(getF_resDemandMVP());
		
				setT_maxResDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(String.valueOf(T_maxResDemand)));
				blobParams.add(getT_maxResDemandMVP());
		
				setF_opProbExecSVP(Manager.getInstance(null).createSingleValueParameter(
						OclStringManager.getInstance(null).getOpProbExecQuery(getContextualElement())));
				blobParams.add(getF_opProbExecSVP());
		
				setT_opProbExecSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_opProbExec)));
				blobParams.add(getT_opProbExecSVP());
		
				if (getF_hwUtilSVP() != null)
					if (getF_hwUtilSVP().getResolvingExpr() != null || !getF_hwUtilSVP().getResolvingExpr().equals(""))
						getF_hwUtilSVP().setResolvingExpr(null);
		
				if (getT_maxHwUtilSVP() != null)
					if (getT_maxHwUtilSVP().getResolvingExpr() != null || !getT_maxHwUtilSVP().getResolvingExpr().equals(""))
						getT_maxHwUtilSVP().setResolvingExpr(null);
		
				if (getF_serviceThroughputSVP() != null)
					if (getF_serviceThroughputSVP().getResolvingExpr() != null
							|| !getF_serviceThroughputSVP().getResolvingExpr().equals(""))
						getF_serviceThroughputSVP().setResolvingExpr(null);
		
				if (getT_minServiceThroughputSVP() != null)
					if (getT_minServiceThroughputSVP().getResolvingExpr() != null
							|| !getT_minServiceThroughputSVP().getResolvingExpr().equals(""))
						getT_minServiceThroughputSVP().setResolvingExpr(null);
		
				getParameters().addAll(blobParams);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParametersForFullDetection(final double T_maxResDemand, final double T_opProbExec, final double T_maxHwUtil, final double T_minServiceThroughput) {
		List<Parameter> blobParams = new ArrayList<>();
		
				setF_resDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(
						OclStringManager.getInstance(null).getResDemandQuery(getContextualElement())));
				blobParams.add(getF_resDemandMVP());
		
				setT_maxResDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(String.valueOf(T_maxResDemand)));
				blobParams.add(getT_maxResDemandMVP());
		
				setF_opProbExecSVP(Manager.getInstance(null).createSingleValueParameter(
						OclStringManager.getInstance(null).getOpProbExecQuery(getContextualElement())));
				blobParams.add(getF_opProbExecSVP());
		
				setT_opProbExecSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_opProbExec)));
				blobParams.add(getT_opProbExecSVP());
		
				setF_hwUtilSVP(Manager.getInstance(null)
						.createSingleValueParameter(OclStringManager.getInstance(null).getHwUtilQuery(getContextualElement())));
				blobParams.add(getF_hwUtilSVP());
		
				setT_maxHwUtilSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxHwUtil)));
				blobParams.add(getT_maxHwUtilSVP());
		
				setF_serviceThroughputSVP(Manager.getInstance(null).createSingleValueParameter(
						OclStringManager.getInstance(null).getServiceThroughputQuery(getContextualElement())));
				blobParams.add(getF_serviceThroughputSVP());
		
				setT_minServiceThroughputSVP(
						Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_minServiceThroughput)));
				blobParams.add(getT_minServiceThroughputSVP());
		
				getParameters().addAll(blobParams);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createFormula() {
		FOLSpecification pafCF = LogicalSpecificationFactory.eINSTANCE.createFOLSpecification();
				pafCF.setName("PafFormula");
		
				AndOperator pafCFAnd = LogicalSpecificationFactory.eINSTANCE.createAndOperator();
		
				GreaterEqualOperator pafCFAndGeq = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();
				pafCFAndGeq.setLhs(getF_resDemandMVP());
				pafCFAndGeq.setRhs(getT_maxResDemandMVP());
				pafCFAnd.getArguments().add(pafCFAndGeq);
		
				EqualOperator pafCFAndEq = LogicalSpecificationFactory.eINSTANCE.createEqualOperator();
				pafCFAndEq.setLhs(getF_opProbExecSVP());
				pafCFAndEq.setRhs(getT_opProbExecSVP());
				pafCFAnd.getArguments().add(pafCFAndEq);
		
				OrOperator pafCFAndOr = LogicalSpecificationFactory.eINSTANCE.createOrOperator();
				GreaterEqualOperator pafCFAndOrGeq = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();
				pafCFAndOrGeq.setLhs(getF_hwUtilSVP());
				pafCFAndOrGeq.setRhs(getT_maxHwUtilSVP());
				LessOperator pafCFAndOrLe = LogicalSpecificationFactory.eINSTANCE.createLessOperator();
				pafCFAndOrLe.setLhs(getF_serviceThroughputSVP());
				pafCFAndOrLe.setRhs(getT_minServiceThroughputSVP());
				pafCFAndOr.getArguments().add(pafCFAndOrGeq);
				pafCFAndOr.getArguments().add(pafCFAndOrLe);
				pafCFAnd.getArguments().add(pafCFAndOr);
		
				pafCF.setRootOperator(pafCFAnd);
				setFormula(pafCF);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int partialDetection(final double T_maxResDemand, final double T_opProbExec) {
		int occurrences = 0;
				setParametersForPartialDetection(T_maxResDemand, T_opProbExec);
				createFormula();
				List<Element> operations = ((Model)Manager.getInstance(UMLManager.getInstance())).getModel().getNestedPackages().get(0).getNestedPackages()
						.get(0).allOwnedElements();
				for (Element o : operations) {
					if (o instanceof Operation && o.getAppliedStereotype("GaAcqStep") != null) {
						try {
							boolean app = Manager.getInstance(null)
									.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Operation) o);
							if (app)
								occurrences++;
						} catch (ParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				return occurrences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int fullDetection(final double T_maxResDemand, final double T_opProbExec, final double T_maxHwUtil, final double T_minServiceThroughput) {
		int occurrences = 0;
				setParametersForFullDetection(T_maxResDemand, T_opProbExec, T_maxHwUtil, T_minServiceThroughput);
				createFormula();
				List<Element> operations = ((Model) Manager.getInstance(UMLManager.getInstance())).getModel()
						.getNestedPackages().get(0).getNestedPackages().get(0).allOwnedElements();
				for (Element o : operations) {
					if (o instanceof Operation && o.getAppliedStereotype("GaAcqStep") != null) {
						try {
							boolean app = Manager.getInstance(null)
									.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Operation) o);
							if (app)
								occurrences++;
						} catch (ParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				return occurrences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void log() {
		super.log();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PerformancePackage.PAF_PA__FRES_DEMAND_MVP:
				if (resolve) return getF_resDemandMVP();
				return basicGetF_resDemandMVP();
			case PerformancePackage.PAF_PA__FOP_PROB_EXEC_SVP:
				if (resolve) return getF_opProbExecSVP();
				return basicGetF_opProbExecSVP();
			case PerformancePackage.PAF_PA__FHW_UTIL_SVP:
				if (resolve) return getF_hwUtilSVP();
				return basicGetF_hwUtilSVP();
			case PerformancePackage.PAF_PA__FSERVICE_THROUGHPUT_SVP:
				if (resolve) return getF_serviceThroughputSVP();
				return basicGetF_serviceThroughputSVP();
			case PerformancePackage.PAF_PA__TMAX_RES_DEMAND_MVP:
				if (resolve) return getT_maxResDemandMVP();
				return basicGetT_maxResDemandMVP();
			case PerformancePackage.PAF_PA__TOP_PROB_EXEC_SVP:
				if (resolve) return getT_opProbExecSVP();
				return basicGetT_opProbExecSVP();
			case PerformancePackage.PAF_PA__TMAX_HW_UTIL_SVP:
				if (resolve) return getT_maxHwUtilSVP();
				return basicGetT_maxHwUtilSVP();
			case PerformancePackage.PAF_PA__TMIN_SERVICE_THROUGHPUT_SVP:
				if (resolve) return getT_minServiceThroughputSVP();
				return basicGetT_minServiceThroughputSVP();
			case PerformancePackage.PAF_PA__CONTEXTUAL_ELEMENT:
				return getContextualElement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PerformancePackage.PAF_PA__FRES_DEMAND_MVP:
				setF_resDemandMVP((MultipleValuedParameter)newValue);
				return;
			case PerformancePackage.PAF_PA__FOP_PROB_EXEC_SVP:
				setF_opProbExecSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.PAF_PA__FHW_UTIL_SVP:
				setF_hwUtilSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.PAF_PA__FSERVICE_THROUGHPUT_SVP:
				setF_serviceThroughputSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.PAF_PA__TMAX_RES_DEMAND_MVP:
				setT_maxResDemandMVP((MultipleValuedParameter)newValue);
				return;
			case PerformancePackage.PAF_PA__TOP_PROB_EXEC_SVP:
				setT_opProbExecSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.PAF_PA__TMAX_HW_UTIL_SVP:
				setT_maxHwUtilSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.PAF_PA__TMIN_SERVICE_THROUGHPUT_SVP:
				setT_minServiceThroughputSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.PAF_PA__CONTEXTUAL_ELEMENT:
				setContextualElement((Operation)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PerformancePackage.PAF_PA__FRES_DEMAND_MVP:
				setF_resDemandMVP((MultipleValuedParameter)null);
				return;
			case PerformancePackage.PAF_PA__FOP_PROB_EXEC_SVP:
				setF_opProbExecSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.PAF_PA__FHW_UTIL_SVP:
				setF_hwUtilSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.PAF_PA__FSERVICE_THROUGHPUT_SVP:
				setF_serviceThroughputSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.PAF_PA__TMAX_RES_DEMAND_MVP:
				setT_maxResDemandMVP((MultipleValuedParameter)null);
				return;
			case PerformancePackage.PAF_PA__TOP_PROB_EXEC_SVP:
				setT_opProbExecSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.PAF_PA__TMAX_HW_UTIL_SVP:
				setT_maxHwUtilSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.PAF_PA__TMIN_SERVICE_THROUGHPUT_SVP:
				setT_minServiceThroughputSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.PAF_PA__CONTEXTUAL_ELEMENT:
				setContextualElement(CONTEXTUAL_ELEMENT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PerformancePackage.PAF_PA__FRES_DEMAND_MVP:
				return f_resDemandMVP != null;
			case PerformancePackage.PAF_PA__FOP_PROB_EXEC_SVP:
				return f_opProbExecSVP != null;
			case PerformancePackage.PAF_PA__FHW_UTIL_SVP:
				return f_hwUtilSVP != null;
			case PerformancePackage.PAF_PA__FSERVICE_THROUGHPUT_SVP:
				return f_serviceThroughputSVP != null;
			case PerformancePackage.PAF_PA__TMAX_RES_DEMAND_MVP:
				return t_maxResDemandMVP != null;
			case PerformancePackage.PAF_PA__TOP_PROB_EXEC_SVP:
				return t_opProbExecSVP != null;
			case PerformancePackage.PAF_PA__TMAX_HW_UTIL_SVP:
				return t_maxHwUtilSVP != null;
			case PerformancePackage.PAF_PA__TMIN_SERVICE_THROUGHPUT_SVP:
				return t_minServiceThroughputSVP != null;
			case PerformancePackage.PAF_PA__CONTEXTUAL_ELEMENT:
				return CONTEXTUAL_ELEMENT_EDEFAULT == null ? contextualElement != null : !CONTEXTUAL_ELEMENT_EDEFAULT.equals(contextualElement);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case PerformancePackage.PAF_PA___SET_PARAMETERS_FOR_PARTIAL_DETECTION__DOUBLE_DOUBLE:
				setParametersForPartialDetection((Double)arguments.get(0), (Double)arguments.get(1));
				return null;
			case PerformancePackage.PAF_PA___SET_PARAMETERS_FOR_FULL_DETECTION__DOUBLE_DOUBLE_DOUBLE_DOUBLE:
				setParametersForFullDetection((Double)arguments.get(0), (Double)arguments.get(1), (Double)arguments.get(2), (Double)arguments.get(3));
				return null;
			case PerformancePackage.PAF_PA___CREATE_FORMULA:
				createFormula();
				return null;
			case PerformancePackage.PAF_PA___PARTIAL_DETECTION__DOUBLE_DOUBLE:
				return partialDetection((Double)arguments.get(0), (Double)arguments.get(1));
			case PerformancePackage.PAF_PA___FULL_DETECTION__DOUBLE_DOUBLE_DOUBLE_DOUBLE:
				return fullDetection((Double)arguments.get(0), (Double)arguments.get(1), (Double)arguments.get(2), (Double)arguments.get(3));
			case PerformancePackage.PAF_PA___LOG:
				log();
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (contextualElement: ");
		result.append(contextualElement);
		result.append(')');
		return result.toString();
	}

} //PafPAImpl
