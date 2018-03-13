/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.impl;

import java.util.Collection;

import metamodel.mmaemilia.Interaction;
import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interaction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.impl.InteractionImpl#getThroughput <em>Throughput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.InteractionImpl#getUtilization <em>Utilization</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.InteractionImpl#getResponseTime <em>Response Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.InteractionImpl#getThroughtputDistr <em>Throughtput Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.InteractionImpl#getRespTimeDistr <em>Resp Time Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.InteractionImpl#getUtilDistr <em>Util Distr</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InteractionImpl extends EObjectImpl implements Interaction {
	/**
	 * The default value of the '{@link #getThroughput() <em>Throughput</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThroughput()
	 * @generated
	 * @ordered
	 */
	protected static final float THROUGHPUT_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getThroughput() <em>Throughput</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThroughput()
	 * @generated
	 * @ordered
	 */
	protected float throughput = THROUGHPUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getUtilization() <em>Utilization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUtilization()
	 * @generated
	 * @ordered
	 */
	protected static final float UTILIZATION_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getUtilization() <em>Utilization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUtilization()
	 * @generated
	 * @ordered
	 */
	protected float utilization = UTILIZATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getResponseTime() <em>Response Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponseTime()
	 * @generated
	 * @ordered
	 */
	protected static final float RESPONSE_TIME_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getResponseTime() <em>Response Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponseTime()
	 * @generated
	 * @ordered
	 */
	protected float responseTime = RESPONSE_TIME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getThroughtputDistr() <em>Throughtput Distr</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThroughtputDistr()
	 * @generated
	 * @ordered
	 */
	protected EList<String> throughtputDistr;

	/**
	 * The cached value of the '{@link #getRespTimeDistr() <em>Resp Time Distr</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRespTimeDistr()
	 * @generated
	 * @ordered
	 */
	protected EList<String> respTimeDistr;

	/**
	 * The cached value of the '{@link #getUtilDistr() <em>Util Distr</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUtilDistr()
	 * @generated
	 * @ordered
	 */
	protected EList<String> utilDistr;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InteractionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return mmaemiliaPackage.Literals.INTERACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getThroughput() {
		return throughput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThroughput(float newThroughput) {
		float oldThroughput = throughput;
		throughput = newThroughput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.INTERACTION__THROUGHPUT, oldThroughput, throughput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getUtilization() {
		return utilization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUtilization(float newUtilization) {
		float oldUtilization = utilization;
		utilization = newUtilization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.INTERACTION__UTILIZATION, oldUtilization, utilization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getResponseTime() {
		return responseTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResponseTime(float newResponseTime) {
		float oldResponseTime = responseTime;
		responseTime = newResponseTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.INTERACTION__RESPONSE_TIME, oldResponseTime, responseTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getThroughtputDistr() {
		if (throughtputDistr == null) {
			throughtputDistr = new EDataTypeUniqueEList<String>(String.class, this, mmaemiliaPackage.INTERACTION__THROUGHTPUT_DISTR);
		}
		return throughtputDistr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getRespTimeDistr() {
		if (respTimeDistr == null) {
			respTimeDistr = new EDataTypeUniqueEList<String>(String.class, this, mmaemiliaPackage.INTERACTION__RESP_TIME_DISTR);
		}
		return respTimeDistr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getUtilDistr() {
		if (utilDistr == null) {
			utilDistr = new EDataTypeUniqueEList<String>(String.class, this, mmaemiliaPackage.INTERACTION__UTIL_DISTR);
		}
		return utilDistr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case mmaemiliaPackage.INTERACTION__THROUGHPUT:
				return getThroughput();
			case mmaemiliaPackage.INTERACTION__UTILIZATION:
				return getUtilization();
			case mmaemiliaPackage.INTERACTION__RESPONSE_TIME:
				return getResponseTime();
			case mmaemiliaPackage.INTERACTION__THROUGHTPUT_DISTR:
				return getThroughtputDistr();
			case mmaemiliaPackage.INTERACTION__RESP_TIME_DISTR:
				return getRespTimeDistr();
			case mmaemiliaPackage.INTERACTION__UTIL_DISTR:
				return getUtilDistr();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case mmaemiliaPackage.INTERACTION__THROUGHPUT:
				setThroughput((Float)newValue);
				return;
			case mmaemiliaPackage.INTERACTION__UTILIZATION:
				setUtilization((Float)newValue);
				return;
			case mmaemiliaPackage.INTERACTION__RESPONSE_TIME:
				setResponseTime((Float)newValue);
				return;
			case mmaemiliaPackage.INTERACTION__THROUGHTPUT_DISTR:
				getThroughtputDistr().clear();
				getThroughtputDistr().addAll((Collection<? extends String>)newValue);
				return;
			case mmaemiliaPackage.INTERACTION__RESP_TIME_DISTR:
				getRespTimeDistr().clear();
				getRespTimeDistr().addAll((Collection<? extends String>)newValue);
				return;
			case mmaemiliaPackage.INTERACTION__UTIL_DISTR:
				getUtilDistr().clear();
				getUtilDistr().addAll((Collection<? extends String>)newValue);
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
			case mmaemiliaPackage.INTERACTION__THROUGHPUT:
				setThroughput(THROUGHPUT_EDEFAULT);
				return;
			case mmaemiliaPackage.INTERACTION__UTILIZATION:
				setUtilization(UTILIZATION_EDEFAULT);
				return;
			case mmaemiliaPackage.INTERACTION__RESPONSE_TIME:
				setResponseTime(RESPONSE_TIME_EDEFAULT);
				return;
			case mmaemiliaPackage.INTERACTION__THROUGHTPUT_DISTR:
				getThroughtputDistr().clear();
				return;
			case mmaemiliaPackage.INTERACTION__RESP_TIME_DISTR:
				getRespTimeDistr().clear();
				return;
			case mmaemiliaPackage.INTERACTION__UTIL_DISTR:
				getUtilDistr().clear();
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
			case mmaemiliaPackage.INTERACTION__THROUGHPUT:
				return throughput != THROUGHPUT_EDEFAULT;
			case mmaemiliaPackage.INTERACTION__UTILIZATION:
				return utilization != UTILIZATION_EDEFAULT;
			case mmaemiliaPackage.INTERACTION__RESPONSE_TIME:
				return responseTime != RESPONSE_TIME_EDEFAULT;
			case mmaemiliaPackage.INTERACTION__THROUGHTPUT_DISTR:
				return throughtputDistr != null && !throughtputDistr.isEmpty();
			case mmaemiliaPackage.INTERACTION__RESP_TIME_DISTR:
				return respTimeDistr != null && !respTimeDistr.isEmpty();
			case mmaemiliaPackage.INTERACTION__UTIL_DISTR:
				return utilDistr != null && !utilDistr.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (throughput: ");
		result.append(throughput);
		result.append(", utilization: ");
		result.append(utilization);
		result.append(", responseTime: ");
		result.append(responseTime);
		result.append(", throughtputDistr: ");
		result.append(throughtputDistr);
		result.append(", respTimeDistr: ");
		result.append(respTimeDistr);
		result.append(", utilDistr: ");
		result.append(utilDistr);
		result.append(')');
		return result.toString();
	}

} //InteractionImpl
