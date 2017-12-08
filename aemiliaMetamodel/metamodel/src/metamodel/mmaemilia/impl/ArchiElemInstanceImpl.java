/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.impl;

import java.util.Collection;

import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.Elem;
import metamodel.mmaemilia.ElemType;
import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.QueueingNetwork.WorkloadClass;
import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Archi Elem Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getInstanceName <em>Instance Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getActualParam <em>Actual Param</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getTypeOf <em>Type Of</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getThroughput <em>Throughput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getUtilization <em>Utilization</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getResponseTime <em>Response Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getInstanceUtilDistr <em>Instance Util Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getInstanceThDistr <em>Instance Th Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getInstanceResTimeDistr <em>Instance Res Time Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getQueueLength <em>Queue Length</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getServiceTime <em>Service Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getClasses <em>Classes</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getElem <em>Elem</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl#getSelector <em>Selector</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArchiElemInstanceImpl extends EObjectImpl implements ArchiElemInstance {
	/**
	 * The default value of the '{@link #getInstanceName() <em>Instance Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceName()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTANCE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInstanceName() <em>Instance Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceName()
	 * @generated
	 * @ordered
	 */
	protected String instanceName = INSTANCE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getActualParam() <em>Actual Param</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualParam()
	 * @generated
	 * @ordered
	 */
	protected EList<String> actualParam;

	/**
	 * The cached value of the '{@link #getTypeOf() <em>Type Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeOf()
	 * @generated
	 * @ordered
	 */
	protected ElemType typeOf;

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
	 * The cached value of the '{@link #getInstanceUtilDistr() <em>Instance Util Distr</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceUtilDistr()
	 * @generated
	 * @ordered
	 */
	protected EList<String> instanceUtilDistr;

	/**
	 * The cached value of the '{@link #getInstanceThDistr() <em>Instance Th Distr</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceThDistr()
	 * @generated
	 * @ordered
	 */
	protected EList<String> instanceThDistr;

	/**
	 * The cached value of the '{@link #getInstanceResTimeDistr() <em>Instance Res Time Distr</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceResTimeDistr()
	 * @generated
	 * @ordered
	 */
	protected EList<String> instanceResTimeDistr;

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
	 * The cached value of the '{@link #getClasses() <em>Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkloadClass> classes;

	/**
	 * The cached value of the '{@link #getElem() <em>Elem</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElem()
	 * @generated
	 * @ordered
	 */
	protected Elem elem;

	/**
	 * The cached value of the '{@link #getSelector() <em>Selector</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelector()
	 * @generated
	 * @ordered
	 */
	protected Expression selector;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArchiElemInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return mmaemiliaPackage.Literals.ARCHI_ELEM_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInstanceName() {
		return instanceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanceName(String newInstanceName) {
		String oldInstanceName = instanceName;
		instanceName = newInstanceName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_NAME, oldInstanceName, instanceName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getActualParam() {
		if (actualParam == null) {
			actualParam = new EDataTypeUniqueEList<String>(String.class, this, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ACTUAL_PARAM);
		}
		return actualParam;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElemType getTypeOf() {
		if (typeOf != null && typeOf.eIsProxy()) {
			InternalEObject oldTypeOf = (InternalEObject)typeOf;
			typeOf = (ElemType)eResolveProxy(oldTypeOf);
			if (typeOf != oldTypeOf) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__TYPE_OF, oldTypeOf, typeOf));
			}
		}
		return typeOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElemType basicGetTypeOf() {
		return typeOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeOf(ElemType newTypeOf) {
		ElemType oldTypeOf = typeOf;
		typeOf = newTypeOf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__TYPE_OF, oldTypeOf, typeOf));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__THROUGHPUT, oldThroughput, throughput));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__UTILIZATION, oldUtilization, utilization));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__RESPONSE_TIME, oldResponseTime, responseTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getInstanceUtilDistr() {
		if (instanceUtilDistr == null) {
			instanceUtilDistr = new EDataTypeUniqueEList<String>(String.class, this, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_UTIL_DISTR);
		}
		return instanceUtilDistr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getInstanceThDistr() {
		if (instanceThDistr == null) {
			instanceThDistr = new EDataTypeUniqueEList<String>(String.class, this, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_TH_DISTR);
		}
		return instanceThDistr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getInstanceResTimeDistr() {
		if (instanceResTimeDistr == null) {
			instanceResTimeDistr = new EDataTypeUniqueEList<String>(String.class, this, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_RES_TIME_DISTR);
		}
		return instanceResTimeDistr;
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__QUEUE_LENGTH, oldQueueLength, queueLength));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SERVICE_TIME, oldServiceTime, serviceTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WorkloadClass> getClasses() {
		if (classes == null) {
			classes = new EObjectContainmentEList<WorkloadClass>(WorkloadClass.class, this, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__CLASSES);
		}
		return classes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Elem getElem() {
		return elem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElem(Elem newElem, NotificationChain msgs) {
		Elem oldElem = elem;
		elem = newElem;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM, oldElem, newElem);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElem(Elem newElem) {
		if (newElem != elem) {
			NotificationChain msgs = null;
			if (elem != null)
				msgs = ((InternalEObject)elem).eInverseRemove(this, mmaemiliaPackage.ELEM__INSTANCE, Elem.class, msgs);
			if (newElem != null)
				msgs = ((InternalEObject)newElem).eInverseAdd(this, mmaemiliaPackage.ELEM__INSTANCE, Elem.class, msgs);
			msgs = basicSetElem(newElem, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM, newElem, newElem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getSelector() {
		return selector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSelector(Expression newSelector, NotificationChain msgs) {
		Expression oldSelector = selector;
		selector = newSelector;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SELECTOR, oldSelector, newSelector);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelector(Expression newSelector) {
		if (newSelector != selector) {
			NotificationChain msgs = null;
			if (selector != null)
				msgs = ((InternalEObject)selector).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SELECTOR, null, msgs);
			if (newSelector != null)
				msgs = ((InternalEObject)newSelector).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SELECTOR, null, msgs);
			msgs = basicSetSelector(newSelector, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SELECTOR, newSelector, newSelector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM:
				if (elem != null)
					msgs = ((InternalEObject)elem).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM, null, msgs);
				return basicSetElem((Elem)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__CLASSES:
				return ((InternalEList<?>)getClasses()).basicRemove(otherEnd, msgs);
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM:
				return basicSetElem(null, msgs);
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SELECTOR:
				return basicSetSelector(null, msgs);
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
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_NAME:
				return getInstanceName();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ACTUAL_PARAM:
				return getActualParam();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__TYPE_OF:
				if (resolve) return getTypeOf();
				return basicGetTypeOf();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__THROUGHPUT:
				return getThroughput();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__UTILIZATION:
				return getUtilization();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__RESPONSE_TIME:
				return getResponseTime();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_UTIL_DISTR:
				return getInstanceUtilDistr();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_TH_DISTR:
				return getInstanceThDistr();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_RES_TIME_DISTR:
				return getInstanceResTimeDistr();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__QUEUE_LENGTH:
				return getQueueLength();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SERVICE_TIME:
				return getServiceTime();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__CLASSES:
				return getClasses();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM:
				return getElem();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SELECTOR:
				return getSelector();
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
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_NAME:
				setInstanceName((String)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ACTUAL_PARAM:
				getActualParam().clear();
				getActualParam().addAll((Collection<? extends String>)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__TYPE_OF:
				setTypeOf((ElemType)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__THROUGHPUT:
				setThroughput((Float)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__UTILIZATION:
				setUtilization((Float)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__RESPONSE_TIME:
				setResponseTime((Float)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_UTIL_DISTR:
				getInstanceUtilDistr().clear();
				getInstanceUtilDistr().addAll((Collection<? extends String>)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_TH_DISTR:
				getInstanceThDistr().clear();
				getInstanceThDistr().addAll((Collection<? extends String>)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_RES_TIME_DISTR:
				getInstanceResTimeDistr().clear();
				getInstanceResTimeDistr().addAll((Collection<? extends String>)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__QUEUE_LENGTH:
				setQueueLength((Float)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SERVICE_TIME:
				setServiceTime((Float)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__CLASSES:
				getClasses().clear();
				getClasses().addAll((Collection<? extends WorkloadClass>)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM:
				setElem((Elem)newValue);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SELECTOR:
				setSelector((Expression)newValue);
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
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_NAME:
				setInstanceName(INSTANCE_NAME_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ACTUAL_PARAM:
				getActualParam().clear();
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__TYPE_OF:
				setTypeOf((ElemType)null);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__THROUGHPUT:
				setThroughput(THROUGHPUT_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__UTILIZATION:
				setUtilization(UTILIZATION_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__RESPONSE_TIME:
				setResponseTime(RESPONSE_TIME_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_UTIL_DISTR:
				getInstanceUtilDistr().clear();
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_TH_DISTR:
				getInstanceThDistr().clear();
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_RES_TIME_DISTR:
				getInstanceResTimeDistr().clear();
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__QUEUE_LENGTH:
				setQueueLength(QUEUE_LENGTH_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SERVICE_TIME:
				setServiceTime(SERVICE_TIME_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__CLASSES:
				getClasses().clear();
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM:
				setElem((Elem)null);
				return;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SELECTOR:
				setSelector((Expression)null);
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
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_NAME:
				return INSTANCE_NAME_EDEFAULT == null ? instanceName != null : !INSTANCE_NAME_EDEFAULT.equals(instanceName);
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ACTUAL_PARAM:
				return actualParam != null && !actualParam.isEmpty();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__TYPE_OF:
				return typeOf != null;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__THROUGHPUT:
				return throughput != THROUGHPUT_EDEFAULT;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__UTILIZATION:
				return utilization != UTILIZATION_EDEFAULT;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__RESPONSE_TIME:
				return responseTime != RESPONSE_TIME_EDEFAULT;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_UTIL_DISTR:
				return instanceUtilDistr != null && !instanceUtilDistr.isEmpty();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_TH_DISTR:
				return instanceThDistr != null && !instanceThDistr.isEmpty();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__INSTANCE_RES_TIME_DISTR:
				return instanceResTimeDistr != null && !instanceResTimeDistr.isEmpty();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__QUEUE_LENGTH:
				return queueLength != QUEUE_LENGTH_EDEFAULT;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SERVICE_TIME:
				return serviceTime != SERVICE_TIME_EDEFAULT;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__CLASSES:
				return classes != null && !classes.isEmpty();
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM:
				return elem != null;
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE__SELECTOR:
				return selector != null;
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
		result.append(" (instanceName: ");
		result.append(instanceName);
		result.append(", actualParam: ");
		result.append(actualParam);
		result.append(", throughput: ");
		result.append(throughput);
		result.append(", utilization: ");
		result.append(utilization);
		result.append(", responseTime: ");
		result.append(responseTime);
		result.append(", instanceUtilDistr: ");
		result.append(instanceUtilDistr);
		result.append(", instanceThDistr: ");
		result.append(instanceThDistr);
		result.append(", InstanceResTimeDistr: ");
		result.append(instanceResTimeDistr);
		result.append(", queueLength: ");
		result.append(queueLength);
		result.append(", serviceTime: ");
		result.append(serviceTime);
		result.append(')');
		return result.toString();
	}

} //ArchiElemInstanceImpl
