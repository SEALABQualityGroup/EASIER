/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.QueueingNetwork.impl;

import metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage;
import metamodel.mmaemilia.QueueingNetwork.TimeUnitsType;
import metamodel.mmaemilia.QueueingNetwork.Workload;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Workload</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadImpl#getWorkloadID <em>Workload ID</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadImpl#getThroughput <em>Throughput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadImpl#getResidenceTime <em>Residence Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadImpl#getTimeUnits <em>Time Units</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkloadImpl extends EObjectImpl implements Workload {
	/**
	 * The default value of the '{@link #getWorkloadID() <em>Workload ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkloadID()
	 * @generated
	 * @ordered
	 */
	protected static final String WORKLOAD_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkloadID() <em>Workload ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkloadID()
	 * @generated
	 * @ordered
	 */
	protected String workloadID = WORKLOAD_ID_EDEFAULT;

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
	 * The default value of the '{@link #getResidenceTime() <em>Residence Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResidenceTime()
	 * @generated
	 * @ordered
	 */
	protected static final float RESIDENCE_TIME_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getResidenceTime() <em>Residence Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResidenceTime()
	 * @generated
	 * @ordered
	 */
	protected float residenceTime = RESIDENCE_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeUnits() <em>Time Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeUnits()
	 * @generated
	 * @ordered
	 */
	protected static final TimeUnitsType TIME_UNITS_EDEFAULT = TimeUnitsType.DAY;

	/**
	 * The cached value of the '{@link #getTimeUnits() <em>Time Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeUnits()
	 * @generated
	 * @ordered
	 */
	protected TimeUnitsType timeUnits = TIME_UNITS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkloadImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QueueingNetworkPackage.Literals.WORKLOAD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkloadID() {
		return workloadID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkloadID(String newWorkloadID) {
		String oldWorkloadID = workloadID;
		workloadID = newWorkloadID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD__WORKLOAD_ID, oldWorkloadID, workloadID));
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
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD__THROUGHPUT, oldThroughput, throughput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getResidenceTime() {
		return residenceTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResidenceTime(float newResidenceTime) {
		float oldResidenceTime = residenceTime;
		residenceTime = newResidenceTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD__RESIDENCE_TIME, oldResidenceTime, residenceTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeUnitsType getTimeUnits() {
		return timeUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeUnits(TimeUnitsType newTimeUnits) {
		TimeUnitsType oldTimeUnits = timeUnits;
		timeUnits = newTimeUnits == null ? TIME_UNITS_EDEFAULT : newTimeUnits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD__TIME_UNITS, oldTimeUnits, timeUnits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QueueingNetworkPackage.WORKLOAD__WORKLOAD_ID:
				return getWorkloadID();
			case QueueingNetworkPackage.WORKLOAD__THROUGHPUT:
				return getThroughput();
			case QueueingNetworkPackage.WORKLOAD__RESIDENCE_TIME:
				return getResidenceTime();
			case QueueingNetworkPackage.WORKLOAD__TIME_UNITS:
				return getTimeUnits();
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
			case QueueingNetworkPackage.WORKLOAD__WORKLOAD_ID:
				setWorkloadID((String)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD__THROUGHPUT:
				setThroughput((Float)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD__RESIDENCE_TIME:
				setResidenceTime((Float)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD__TIME_UNITS:
				setTimeUnits((TimeUnitsType)newValue);
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
			case QueueingNetworkPackage.WORKLOAD__WORKLOAD_ID:
				setWorkloadID(WORKLOAD_ID_EDEFAULT);
				return;
			case QueueingNetworkPackage.WORKLOAD__THROUGHPUT:
				setThroughput(THROUGHPUT_EDEFAULT);
				return;
			case QueueingNetworkPackage.WORKLOAD__RESIDENCE_TIME:
				setResidenceTime(RESIDENCE_TIME_EDEFAULT);
				return;
			case QueueingNetworkPackage.WORKLOAD__TIME_UNITS:
				setTimeUnits(TIME_UNITS_EDEFAULT);
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
			case QueueingNetworkPackage.WORKLOAD__WORKLOAD_ID:
				return WORKLOAD_ID_EDEFAULT == null ? workloadID != null : !WORKLOAD_ID_EDEFAULT.equals(workloadID);
			case QueueingNetworkPackage.WORKLOAD__THROUGHPUT:
				return throughput != THROUGHPUT_EDEFAULT;
			case QueueingNetworkPackage.WORKLOAD__RESIDENCE_TIME:
				return residenceTime != RESIDENCE_TIME_EDEFAULT;
			case QueueingNetworkPackage.WORKLOAD__TIME_UNITS:
				return timeUnits != TIME_UNITS_EDEFAULT;
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
		result.append(" (workloadID: ");
		result.append(workloadID);
		result.append(", throughput: ");
		result.append(throughput);
		result.append(", residenceTime: ");
		result.append(residenceTime);
		result.append(", timeUnits: ");
		result.append(timeUnits);
		result.append(')');
		return result.toString();
	}

} //WorkloadImpl
