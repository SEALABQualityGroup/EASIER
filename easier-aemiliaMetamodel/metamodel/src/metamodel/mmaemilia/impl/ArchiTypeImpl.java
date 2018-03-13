/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.impl;

import java.util.Collection;
import metamodel.mmaemilia.ArchiElemTypes;
import metamodel.mmaemilia.ArchiTopology;
import metamodel.mmaemilia.ArchiType;

import metamodel.mmaemilia.Headers.AT_Header;

import metamodel.mmaemilia.QueueingNetwork.Workload;
import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Archi Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiTypeImpl#getAetDeclaration <em>Aet Declaration</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiTypeImpl#getAtDeclaration <em>At Declaration</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiTypeImpl#getAtName <em>At Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiTypeImpl#getHeader <em>Header</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiTypeImpl#getThroughput <em>Throughput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiTypeImpl#getResidenceTime <em>Residence Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiTypeImpl#getUtilization <em>Utilization</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiTypeImpl#getQueueLength <em>Queue Length</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiTypeImpl#getServiceTime <em>Service Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiTypeImpl#getWorkloads <em>Workloads</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArchiTypeImpl extends EObjectImpl implements ArchiType {
	/**
	 * The cached value of the '{@link #getAetDeclaration() <em>Aet Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAetDeclaration()
	 * @generated
	 * @ordered
	 */
	protected ArchiElemTypes aetDeclaration;

	/**
	 * The cached value of the '{@link #getAtDeclaration() <em>At Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtDeclaration()
	 * @generated
	 * @ordered
	 */
	protected ArchiTopology atDeclaration;

	/**
	 * The default value of the '{@link #getAtName() <em>At Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtName()
	 * @generated
	 * @ordered
	 */
	protected static final String AT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAtName() <em>At Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtName()
	 * @generated
	 * @ordered
	 */
	protected String atName = AT_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHeader() <em>Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeader()
	 * @generated
	 * @ordered
	 */
	protected AT_Header header;

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
	 * The cached value of the '{@link #getWorkloads() <em>Workloads</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkloads()
	 * @generated
	 * @ordered
	 */
	protected EList<Workload> workloads;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArchiTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return mmaemiliaPackage.Literals.ARCHI_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchiElemTypes getAetDeclaration() {
		return aetDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAetDeclaration(ArchiElemTypes newAetDeclaration, NotificationChain msgs) {
		ArchiElemTypes oldAetDeclaration = aetDeclaration;
		aetDeclaration = newAetDeclaration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__AET_DECLARATION, oldAetDeclaration, newAetDeclaration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAetDeclaration(ArchiElemTypes newAetDeclaration) {
		if (newAetDeclaration != aetDeclaration) {
			NotificationChain msgs = null;
			if (aetDeclaration != null)
				msgs = ((InternalEObject)aetDeclaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ARCHI_TYPE__AET_DECLARATION, null, msgs);
			if (newAetDeclaration != null)
				msgs = ((InternalEObject)newAetDeclaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ARCHI_TYPE__AET_DECLARATION, null, msgs);
			msgs = basicSetAetDeclaration(newAetDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__AET_DECLARATION, newAetDeclaration, newAetDeclaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchiTopology getAtDeclaration() {
		return atDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAtDeclaration(ArchiTopology newAtDeclaration, NotificationChain msgs) {
		ArchiTopology oldAtDeclaration = atDeclaration;
		atDeclaration = newAtDeclaration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__AT_DECLARATION, oldAtDeclaration, newAtDeclaration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAtDeclaration(ArchiTopology newAtDeclaration) {
		if (newAtDeclaration != atDeclaration) {
			NotificationChain msgs = null;
			if (atDeclaration != null)
				msgs = ((InternalEObject)atDeclaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ARCHI_TYPE__AT_DECLARATION, null, msgs);
			if (newAtDeclaration != null)
				msgs = ((InternalEObject)newAtDeclaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ARCHI_TYPE__AT_DECLARATION, null, msgs);
			msgs = basicSetAtDeclaration(newAtDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__AT_DECLARATION, newAtDeclaration, newAtDeclaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAtName() {
		return atName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAtName(String newAtName) {
		String oldAtName = atName;
		atName = newAtName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__AT_NAME, oldAtName, atName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AT_Header getHeader() {
		return header;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHeader(AT_Header newHeader, NotificationChain msgs) {
		AT_Header oldHeader = header;
		header = newHeader;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__HEADER, oldHeader, newHeader);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeader(AT_Header newHeader) {
		if (newHeader != header) {
			NotificationChain msgs = null;
			if (header != null)
				msgs = ((InternalEObject)header).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ARCHI_TYPE__HEADER, null, msgs);
			if (newHeader != null)
				msgs = ((InternalEObject)newHeader).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ARCHI_TYPE__HEADER, null, msgs);
			msgs = basicSetHeader(newHeader, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__HEADER, newHeader, newHeader));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__THROUGHPUT, oldThroughput, throughput));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__RESIDENCE_TIME, oldResidenceTime, residenceTime));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__UTILIZATION, oldUtilization, utilization));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__QUEUE_LENGTH, oldQueueLength, queueLength));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_TYPE__SERVICE_TIME, oldServiceTime, serviceTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Workload> getWorkloads() {
		if (workloads == null) {
			workloads = new EObjectContainmentEList<Workload>(Workload.class, this, mmaemiliaPackage.ARCHI_TYPE__WORKLOADS);
		}
		return workloads;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case mmaemiliaPackage.ARCHI_TYPE__AET_DECLARATION:
				return basicSetAetDeclaration(null, msgs);
			case mmaemiliaPackage.ARCHI_TYPE__AT_DECLARATION:
				return basicSetAtDeclaration(null, msgs);
			case mmaemiliaPackage.ARCHI_TYPE__HEADER:
				return basicSetHeader(null, msgs);
			case mmaemiliaPackage.ARCHI_TYPE__WORKLOADS:
				return ((InternalEList<?>)getWorkloads()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case mmaemiliaPackage.ARCHI_TYPE__AET_DECLARATION:
				return getAetDeclaration();
			case mmaemiliaPackage.ARCHI_TYPE__AT_DECLARATION:
				return getAtDeclaration();
			case mmaemiliaPackage.ARCHI_TYPE__AT_NAME:
				return getAtName();
			case mmaemiliaPackage.ARCHI_TYPE__HEADER:
				return getHeader();
			case mmaemiliaPackage.ARCHI_TYPE__THROUGHPUT:
				return getThroughput();
			case mmaemiliaPackage.ARCHI_TYPE__RESIDENCE_TIME:
				return getResidenceTime();
			case mmaemiliaPackage.ARCHI_TYPE__UTILIZATION:
				return getUtilization();
			case mmaemiliaPackage.ARCHI_TYPE__QUEUE_LENGTH:
				return getQueueLength();
			case mmaemiliaPackage.ARCHI_TYPE__SERVICE_TIME:
				return getServiceTime();
			case mmaemiliaPackage.ARCHI_TYPE__WORKLOADS:
				return getWorkloads();
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
			case mmaemiliaPackage.ARCHI_TYPE__AET_DECLARATION:
				setAetDeclaration((ArchiElemTypes)newValue);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__AT_DECLARATION:
				setAtDeclaration((ArchiTopology)newValue);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__AT_NAME:
				setAtName((String)newValue);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__HEADER:
				setHeader((AT_Header)newValue);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__THROUGHPUT:
				setThroughput((Float)newValue);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__RESIDENCE_TIME:
				setResidenceTime((Float)newValue);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__UTILIZATION:
				setUtilization((Float)newValue);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__QUEUE_LENGTH:
				setQueueLength((Float)newValue);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__SERVICE_TIME:
				setServiceTime((Float)newValue);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__WORKLOADS:
				getWorkloads().clear();
				getWorkloads().addAll((Collection<? extends Workload>)newValue);
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
			case mmaemiliaPackage.ARCHI_TYPE__AET_DECLARATION:
				setAetDeclaration((ArchiElemTypes)null);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__AT_DECLARATION:
				setAtDeclaration((ArchiTopology)null);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__AT_NAME:
				setAtName(AT_NAME_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__HEADER:
				setHeader((AT_Header)null);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__THROUGHPUT:
				setThroughput(THROUGHPUT_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__RESIDENCE_TIME:
				setResidenceTime(RESIDENCE_TIME_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__UTILIZATION:
				setUtilization(UTILIZATION_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__QUEUE_LENGTH:
				setQueueLength(QUEUE_LENGTH_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__SERVICE_TIME:
				setServiceTime(SERVICE_TIME_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_TYPE__WORKLOADS:
				getWorkloads().clear();
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
			case mmaemiliaPackage.ARCHI_TYPE__AET_DECLARATION:
				return aetDeclaration != null;
			case mmaemiliaPackage.ARCHI_TYPE__AT_DECLARATION:
				return atDeclaration != null;
			case mmaemiliaPackage.ARCHI_TYPE__AT_NAME:
				return AT_NAME_EDEFAULT == null ? atName != null : !AT_NAME_EDEFAULT.equals(atName);
			case mmaemiliaPackage.ARCHI_TYPE__HEADER:
				return header != null;
			case mmaemiliaPackage.ARCHI_TYPE__THROUGHPUT:
				return throughput != THROUGHPUT_EDEFAULT;
			case mmaemiliaPackage.ARCHI_TYPE__RESIDENCE_TIME:
				return residenceTime != RESIDENCE_TIME_EDEFAULT;
			case mmaemiliaPackage.ARCHI_TYPE__UTILIZATION:
				return utilization != UTILIZATION_EDEFAULT;
			case mmaemiliaPackage.ARCHI_TYPE__QUEUE_LENGTH:
				return queueLength != QUEUE_LENGTH_EDEFAULT;
			case mmaemiliaPackage.ARCHI_TYPE__SERVICE_TIME:
				return serviceTime != SERVICE_TIME_EDEFAULT;
			case mmaemiliaPackage.ARCHI_TYPE__WORKLOADS:
				return workloads != null && !workloads.isEmpty();
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
		result.append(" (atName: ");
		result.append(atName);
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
		result.append(')');
		return result.toString();
	}

} //ArchiTypeImpl
