/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior.impl;

import java.util.Collection;

import metamodel.mmaemilia.Behavior.Action;
import metamodel.mmaemilia.Behavior.ActionRate;
import metamodel.mmaemilia.Behavior.ActionType;
import metamodel.mmaemilia.Behavior.BehaviorPackage;

import metamodel.mmaemilia.Elem;
import metamodel.mmaemilia.ElemType;
import metamodel.mmaemilia.LocalInteraction;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getActThroughtput <em>Act Throughtput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getActUtilization <em>Act Utilization</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getActResponseTime <em>Act Response Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getType <em>Type</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getRate <em>Rate</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getIs <em>Is</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getBelongs <em>Belongs</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getActThDistr <em>Act Th Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getActRespTimeDistr <em>Act Resp Time Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getActUtilDistr <em>Act Util Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionImpl#getElem <em>Elem</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActionImpl extends EObjectImpl implements Action {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getActThroughtput() <em>Act Throughtput</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActThroughtput()
	 * @generated
	 * @ordered
	 */
	protected static final float ACT_THROUGHTPUT_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getActThroughtput() <em>Act Throughtput</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActThroughtput()
	 * @generated
	 * @ordered
	 */
	protected float actThroughtput = ACT_THROUGHTPUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getActUtilization() <em>Act Utilization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActUtilization()
	 * @generated
	 * @ordered
	 */
	protected static final float ACT_UTILIZATION_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getActUtilization() <em>Act Utilization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActUtilization()
	 * @generated
	 * @ordered
	 */
	protected float actUtilization = ACT_UTILIZATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getActResponseTime() <em>Act Response Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActResponseTime()
	 * @generated
	 * @ordered
	 */
	protected static final float ACT_RESPONSE_TIME_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getActResponseTime() <em>Act Response Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActResponseTime()
	 * @generated
	 * @ordered
	 */
	protected float actResponseTime = ACT_RESPONSE_TIME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ActionType type;

	/**
	 * The cached value of the '{@link #getRate() <em>Rate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRate()
	 * @generated
	 * @ordered
	 */
	protected ActionRate rate;

	/**
	 * The cached value of the '{@link #getIs() <em>Is</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIs()
	 * @generated
	 * @ordered
	 */
	protected LocalInteraction is;

	/**
	 * The cached value of the '{@link #getBelongs() <em>Belongs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBelongs()
	 * @generated
	 * @ordered
	 */
	protected ElemType belongs;

	/**
	 * The cached value of the '{@link #getActThDistr() <em>Act Th Distr</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActThDistr()
	 * @generated
	 * @ordered
	 */
	protected EList<String> actThDistr;

	/**
	 * The cached value of the '{@link #getActRespTimeDistr() <em>Act Resp Time Distr</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActRespTimeDistr()
	 * @generated
	 * @ordered
	 */
	protected EList<String> actRespTimeDistr;

	/**
	 * The cached value of the '{@link #getActUtilDistr() <em>Act Util Distr</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActUtilDistr()
	 * @generated
	 * @ordered
	 */
	protected EList<String> actUtilDistr;

	/**
	 * The cached value of the '{@link #getElem() <em>Elem</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElem()
	 * @generated
	 * @ordered
	 */
	protected Elem elem;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BehaviorPackage.Literals.ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getActThroughtput() {
		return actThroughtput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActThroughtput(float newActThroughtput) {
		float oldActThroughtput = actThroughtput;
		actThroughtput = newActThroughtput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__ACT_THROUGHTPUT, oldActThroughtput, actThroughtput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getActUtilization() {
		return actUtilization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActUtilization(float newActUtilization) {
		float oldActUtilization = actUtilization;
		actUtilization = newActUtilization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__ACT_UTILIZATION, oldActUtilization, actUtilization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getActResponseTime() {
		return actResponseTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActResponseTime(float newActResponseTime) {
		float oldActResponseTime = actResponseTime;
		actResponseTime = newActResponseTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__ACT_RESPONSE_TIME, oldActResponseTime, actResponseTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(ActionType newType, NotificationChain msgs) {
		ActionType oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ActionType newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.ACTION__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.ACTION__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionRate getRate() {
		return rate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRate(ActionRate newRate, NotificationChain msgs) {
		ActionRate oldRate = rate;
		rate = newRate;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__RATE, oldRate, newRate);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRate(ActionRate newRate) {
		if (newRate != rate) {
			NotificationChain msgs = null;
			if (rate != null)
				msgs = ((InternalEObject)rate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.ACTION__RATE, null, msgs);
			if (newRate != null)
				msgs = ((InternalEObject)newRate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.ACTION__RATE, null, msgs);
			msgs = basicSetRate(newRate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__RATE, newRate, newRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalInteraction getIs() {
		return is;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIs(LocalInteraction newIs, NotificationChain msgs) {
		LocalInteraction oldIs = is;
		is = newIs;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__IS, oldIs, newIs);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIs(LocalInteraction newIs) {
		if (newIs != is) {
			NotificationChain msgs = null;
			if (is != null)
				msgs = ((InternalEObject)is).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.ACTION__IS, null, msgs);
			if (newIs != null)
				msgs = ((InternalEObject)newIs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.ACTION__IS, null, msgs);
			msgs = basicSetIs(newIs, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__IS, newIs, newIs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElemType getBelongs() {
		if (belongs != null && belongs.eIsProxy()) {
			InternalEObject oldBelongs = (InternalEObject)belongs;
			belongs = (ElemType)eResolveProxy(oldBelongs);
			if (belongs != oldBelongs) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehaviorPackage.ACTION__BELONGS, oldBelongs, belongs));
			}
		}
		return belongs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElemType basicGetBelongs() {
		return belongs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBelongs(ElemType newBelongs) {
		ElemType oldBelongs = belongs;
		belongs = newBelongs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__BELONGS, oldBelongs, belongs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getActThDistr() {
		if (actThDistr == null) {
			actThDistr = new EDataTypeUniqueEList<String>(String.class, this, BehaviorPackage.ACTION__ACT_TH_DISTR);
		}
		return actThDistr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getActRespTimeDistr() {
		if (actRespTimeDistr == null) {
			actRespTimeDistr = new EDataTypeUniqueEList.Unsettable<String>(String.class, this, BehaviorPackage.ACTION__ACT_RESP_TIME_DISTR);
		}
		return actRespTimeDistr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetActRespTimeDistr() {
		if (actRespTimeDistr != null) ((InternalEList.Unsettable<?>)actRespTimeDistr).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetActRespTimeDistr() {
		return actRespTimeDistr != null && ((InternalEList.Unsettable<?>)actRespTimeDistr).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getActUtilDistr() {
		if (actUtilDistr == null) {
			actUtilDistr = new EDataTypeUniqueEList<String>(String.class, this, BehaviorPackage.ACTION__ACT_UTIL_DISTR);
		}
		return actUtilDistr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Elem getElem() {
		if (elem != null && elem.eIsProxy()) {
			InternalEObject oldElem = (InternalEObject)elem;
			elem = (Elem)eResolveProxy(oldElem);
			if (elem != oldElem) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehaviorPackage.ACTION__ELEM, oldElem, elem));
			}
		}
		return elem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Elem basicGetElem() {
		return elem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElem(Elem newElem) {
		Elem oldElem = elem;
		elem = newElem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION__ELEM, oldElem, elem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BehaviorPackage.ACTION__TYPE:
				return basicSetType(null, msgs);
			case BehaviorPackage.ACTION__RATE:
				return basicSetRate(null, msgs);
			case BehaviorPackage.ACTION__IS:
				return basicSetIs(null, msgs);
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
			case BehaviorPackage.ACTION__NAME:
				return getName();
			case BehaviorPackage.ACTION__ACT_THROUGHTPUT:
				return getActThroughtput();
			case BehaviorPackage.ACTION__ACT_UTILIZATION:
				return getActUtilization();
			case BehaviorPackage.ACTION__ACT_RESPONSE_TIME:
				return getActResponseTime();
			case BehaviorPackage.ACTION__TYPE:
				return getType();
			case BehaviorPackage.ACTION__RATE:
				return getRate();
			case BehaviorPackage.ACTION__IS:
				return getIs();
			case BehaviorPackage.ACTION__BELONGS:
				if (resolve) return getBelongs();
				return basicGetBelongs();
			case BehaviorPackage.ACTION__ACT_TH_DISTR:
				return getActThDistr();
			case BehaviorPackage.ACTION__ACT_RESP_TIME_DISTR:
				return getActRespTimeDistr();
			case BehaviorPackage.ACTION__ACT_UTIL_DISTR:
				return getActUtilDistr();
			case BehaviorPackage.ACTION__ELEM:
				if (resolve) return getElem();
				return basicGetElem();
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
			case BehaviorPackage.ACTION__NAME:
				setName((String)newValue);
				return;
			case BehaviorPackage.ACTION__ACT_THROUGHTPUT:
				setActThroughtput((Float)newValue);
				return;
			case BehaviorPackage.ACTION__ACT_UTILIZATION:
				setActUtilization((Float)newValue);
				return;
			case BehaviorPackage.ACTION__ACT_RESPONSE_TIME:
				setActResponseTime((Float)newValue);
				return;
			case BehaviorPackage.ACTION__TYPE:
				setType((ActionType)newValue);
				return;
			case BehaviorPackage.ACTION__RATE:
				setRate((ActionRate)newValue);
				return;
			case BehaviorPackage.ACTION__IS:
				setIs((LocalInteraction)newValue);
				return;
			case BehaviorPackage.ACTION__BELONGS:
				setBelongs((ElemType)newValue);
				return;
			case BehaviorPackage.ACTION__ACT_TH_DISTR:
				getActThDistr().clear();
				getActThDistr().addAll((Collection<? extends String>)newValue);
				return;
			case BehaviorPackage.ACTION__ACT_RESP_TIME_DISTR:
				getActRespTimeDistr().clear();
				getActRespTimeDistr().addAll((Collection<? extends String>)newValue);
				return;
			case BehaviorPackage.ACTION__ACT_UTIL_DISTR:
				getActUtilDistr().clear();
				getActUtilDistr().addAll((Collection<? extends String>)newValue);
				return;
			case BehaviorPackage.ACTION__ELEM:
				setElem((Elem)newValue);
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
			case BehaviorPackage.ACTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BehaviorPackage.ACTION__ACT_THROUGHTPUT:
				setActThroughtput(ACT_THROUGHTPUT_EDEFAULT);
				return;
			case BehaviorPackage.ACTION__ACT_UTILIZATION:
				setActUtilization(ACT_UTILIZATION_EDEFAULT);
				return;
			case BehaviorPackage.ACTION__ACT_RESPONSE_TIME:
				setActResponseTime(ACT_RESPONSE_TIME_EDEFAULT);
				return;
			case BehaviorPackage.ACTION__TYPE:
				setType((ActionType)null);
				return;
			case BehaviorPackage.ACTION__RATE:
				setRate((ActionRate)null);
				return;
			case BehaviorPackage.ACTION__IS:
				setIs((LocalInteraction)null);
				return;
			case BehaviorPackage.ACTION__BELONGS:
				setBelongs((ElemType)null);
				return;
			case BehaviorPackage.ACTION__ACT_TH_DISTR:
				getActThDistr().clear();
				return;
			case BehaviorPackage.ACTION__ACT_RESP_TIME_DISTR:
				unsetActRespTimeDistr();
				return;
			case BehaviorPackage.ACTION__ACT_UTIL_DISTR:
				getActUtilDistr().clear();
				return;
			case BehaviorPackage.ACTION__ELEM:
				setElem((Elem)null);
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
			case BehaviorPackage.ACTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BehaviorPackage.ACTION__ACT_THROUGHTPUT:
				return actThroughtput != ACT_THROUGHTPUT_EDEFAULT;
			case BehaviorPackage.ACTION__ACT_UTILIZATION:
				return actUtilization != ACT_UTILIZATION_EDEFAULT;
			case BehaviorPackage.ACTION__ACT_RESPONSE_TIME:
				return actResponseTime != ACT_RESPONSE_TIME_EDEFAULT;
			case BehaviorPackage.ACTION__TYPE:
				return type != null;
			case BehaviorPackage.ACTION__RATE:
				return rate != null;
			case BehaviorPackage.ACTION__IS:
				return is != null;
			case BehaviorPackage.ACTION__BELONGS:
				return belongs != null;
			case BehaviorPackage.ACTION__ACT_TH_DISTR:
				return actThDistr != null && !actThDistr.isEmpty();
			case BehaviorPackage.ACTION__ACT_RESP_TIME_DISTR:
				return isSetActRespTimeDistr();
			case BehaviorPackage.ACTION__ACT_UTIL_DISTR:
				return actUtilDistr != null && !actUtilDistr.isEmpty();
			case BehaviorPackage.ACTION__ELEM:
				return elem != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", actThroughtput: ");
		result.append(actThroughtput);
		result.append(", actUtilization: ");
		result.append(actUtilization);
		result.append(", actResponseTime: ");
		result.append(actResponseTime);
		result.append(", actThDistr: ");
		result.append(actThDistr);
		result.append(", actRespTimeDistr: ");
		result.append(actRespTimeDistr);
		result.append(", actUtilDistr: ");
		result.append(actUtilDistr);
		result.append(')');
		return result.toString();
	}

} //ActionImpl
