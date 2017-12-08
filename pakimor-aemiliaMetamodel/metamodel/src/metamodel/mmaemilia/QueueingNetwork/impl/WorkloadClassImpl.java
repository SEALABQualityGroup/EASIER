/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.QueueingNetwork.impl;

import metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage;
import metamodel.mmaemilia.QueueingNetwork.TimeUnitsType;
import metamodel.mmaemilia.QueueingNetwork.WorkloadClass;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Workload Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl#getWorkloadID <em>Workload ID</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl#getThroughput <em>Throughput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl#getResidenceTime <em>Residence Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl#getUtilization <em>Utilization</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl#getQueueLength <em>Queue Length</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl#getServiceTime <em>Service Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl#getEReference1 <em>EReference1</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl#getTimeUnits <em>Time Units</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkloadClassImpl extends EObjectImpl implements WorkloadClass {
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
	 * The default value of the '{@link #getQueueLength() <em>Queue Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueueLength()
	 * @generated
	 * @ordered
	 */
	protected static final float QUEUE_LENGTH_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getQueueLength() <em>Queue Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueueLength()
	 * @generated
	 * @ordered
	 */
	protected float queueLength = QUEUE_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getServiceTime() <em>Service Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceTime()
	 * @generated
	 * @ordered
	 */
	protected static final float SERVICE_TIME_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getServiceTime() <em>Service Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceTime()
	 * @generated
	 * @ordered
	 */
	protected float serviceTime = SERVICE_TIME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEReference0() <em>EReference0</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference0()
	 * @generated
	 * @ordered
	 */
	protected WorkloadClass eReference0;

	/**
	 * The cached value of the '{@link #getEReference1() <em>EReference1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEReference1()
	 * @generated
	 * @ordered
	 */
	protected WorkloadClass eReference1;

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
	protected WorkloadClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QueueingNetworkPackage.Literals.WORKLOAD_CLASS;
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
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD_CLASS__WORKLOAD_ID, oldWorkloadID, workloadID));
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
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD_CLASS__THROUGHPUT, oldThroughput, throughput));
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
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD_CLASS__RESIDENCE_TIME, oldResidenceTime, residenceTime));
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
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD_CLASS__UTILIZATION, oldUtilization, utilization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getQueueLength() {
		return queueLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQueueLength(float newQueueLength) {
		float oldQueueLength = queueLength;
		queueLength = newQueueLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD_CLASS__QUEUE_LENGTH, oldQueueLength, queueLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getServiceTime() {
		return serviceTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceTime(float newServiceTime) {
		float oldServiceTime = serviceTime;
		serviceTime = newServiceTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD_CLASS__SERVICE_TIME, oldServiceTime, serviceTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkloadClass getEReference0() {
		if (eReference0 != null && eReference0.eIsProxy()) {
			InternalEObject oldEReference0 = (InternalEObject)eReference0;
			eReference0 = (WorkloadClass)eResolveProxy(oldEReference0);
			if (eReference0 != oldEReference0) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE0, oldEReference0, eReference0));
			}
		}
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkloadClass basicGetEReference0() {
		return eReference0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEReference0(WorkloadClass newEReference0) {
		WorkloadClass oldEReference0 = eReference0;
		eReference0 = newEReference0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE0, oldEReference0, eReference0));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkloadClass getEReference1() {
		if (eReference1 != null && eReference1.eIsProxy()) {
			InternalEObject oldEReference1 = (InternalEObject)eReference1;
			eReference1 = (WorkloadClass)eResolveProxy(oldEReference1);
			if (eReference1 != oldEReference1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE1, oldEReference1, eReference1));
			}
		}
		return eReference1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkloadClass basicGetEReference1() {
		return eReference1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEReference1(WorkloadClass newEReference1) {
		WorkloadClass oldEReference1 = eReference1;
		eReference1 = newEReference1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE1, oldEReference1, eReference1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, QueueingNetworkPackage.WORKLOAD_CLASS__TIME_UNITS, oldTimeUnits, timeUnits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QueueingNetworkPackage.WORKLOAD_CLASS__WORKLOAD_ID:
				return getWorkloadID();
			case QueueingNetworkPackage.WORKLOAD_CLASS__THROUGHPUT:
				return getThroughput();
			case QueueingNetworkPackage.WORKLOAD_CLASS__RESIDENCE_TIME:
				return getResidenceTime();
			case QueueingNetworkPackage.WORKLOAD_CLASS__UTILIZATION:
				return getUtilization();
			case QueueingNetworkPackage.WORKLOAD_CLASS__QUEUE_LENGTH:
				return getQueueLength();
			case QueueingNetworkPackage.WORKLOAD_CLASS__SERVICE_TIME:
				return getServiceTime();
			case QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE0:
				if (resolve) return getEReference0();
				return basicGetEReference0();
			case QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE1:
				if (resolve) return getEReference1();
				return basicGetEReference1();
			case QueueingNetworkPackage.WORKLOAD_CLASS__TIME_UNITS:
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
			case QueueingNetworkPackage.WORKLOAD_CLASS__WORKLOAD_ID:
				setWorkloadID((String)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__THROUGHPUT:
				setThroughput((Float)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__RESIDENCE_TIME:
				setResidenceTime((Float)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__UTILIZATION:
				setUtilization((Float)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__QUEUE_LENGTH:
				setQueueLength((Float)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__SERVICE_TIME:
				setServiceTime((Float)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE0:
				setEReference0((WorkloadClass)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE1:
				setEReference1((WorkloadClass)newValue);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__TIME_UNITS:
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
			case QueueingNetworkPackage.WORKLOAD_CLASS__WORKLOAD_ID:
				setWorkloadID(WORKLOAD_ID_EDEFAULT);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__THROUGHPUT:
				setThroughput(THROUGHPUT_EDEFAULT);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__RESIDENCE_TIME:
				setResidenceTime(RESIDENCE_TIME_EDEFAULT);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__UTILIZATION:
				setUtilization(UTILIZATION_EDEFAULT);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__QUEUE_LENGTH:
				setQueueLength(QUEUE_LENGTH_EDEFAULT);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__SERVICE_TIME:
				setServiceTime(SERVICE_TIME_EDEFAULT);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE0:
				setEReference0((WorkloadClass)null);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE1:
				setEReference1((WorkloadClass)null);
				return;
			case QueueingNetworkPackage.WORKLOAD_CLASS__TIME_UNITS:
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
			case QueueingNetworkPackage.WORKLOAD_CLASS__WORKLOAD_ID:
				return WORKLOAD_ID_EDEFAULT == null ? workloadID != null : !WORKLOAD_ID_EDEFAULT.equals(workloadID);
			case QueueingNetworkPackage.WORKLOAD_CLASS__THROUGHPUT:
				return throughput != THROUGHPUT_EDEFAULT;
			case QueueingNetworkPackage.WORKLOAD_CLASS__RESIDENCE_TIME:
				return residenceTime != RESIDENCE_TIME_EDEFAULT;
			case QueueingNetworkPackage.WORKLOAD_CLASS__UTILIZATION:
				return utilization != UTILIZATION_EDEFAULT;
			case QueueingNetworkPackage.WORKLOAD_CLASS__QUEUE_LENGTH:
				return queueLength != QUEUE_LENGTH_EDEFAULT;
			case QueueingNetworkPackage.WORKLOAD_CLASS__SERVICE_TIME:
				return serviceTime != SERVICE_TIME_EDEFAULT;
			case QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE0:
				return eReference0 != null;
			case QueueingNetworkPackage.WORKLOAD_CLASS__EREFERENCE1:
				return eReference1 != null;
			case QueueingNetworkPackage.WORKLOAD_CLASS__TIME_UNITS:
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
		result.append(", utilization: ");
		result.append(utilization);
		result.append(", queueLength: ");
		result.append(queueLength);
		result.append(", serviceTime: ");
		result.append(serviceTime);
		result.append(", timeUnits: ");
		result.append(timeUnits);
		result.append(')');
		return result.toString();
	}

} //WorkloadClassImpl
