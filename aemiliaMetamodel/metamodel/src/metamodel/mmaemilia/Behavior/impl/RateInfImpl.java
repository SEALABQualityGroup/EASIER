/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior.impl;

import metamodel.mmaemilia.Behavior.BehaviorPackage;
import metamodel.mmaemilia.Behavior.RateInf;

import metamodel.mmaemilia.Expressions.Expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rate Inf</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.RateInfImpl#getInf_priority <em>Inf priority</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.RateInfImpl#getInf_weight <em>Inf weight</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RateInfImpl extends ActionRateImpl implements RateInf {
	/**
	 * The cached value of the '{@link #getInf_priority() <em>Inf priority</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInf_priority()
	 * @generated
	 * @ordered
	 */
	protected Expression inf_priority;

	/**
	 * The cached value of the '{@link #getInf_weight() <em>Inf weight</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInf_weight()
	 * @generated
	 * @ordered
	 */
	protected Expression inf_weight;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RateInfImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BehaviorPackage.Literals.RATE_INF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getInf_priority() {
		return inf_priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInf_priority(Expression newInf_priority, NotificationChain msgs) {
		Expression oldInf_priority = inf_priority;
		inf_priority = newInf_priority;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehaviorPackage.RATE_INF__INF_PRIORITY, oldInf_priority, newInf_priority);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInf_priority(Expression newInf_priority) {
		if (newInf_priority != inf_priority) {
			NotificationChain msgs = null;
			if (inf_priority != null)
				msgs = ((InternalEObject)inf_priority).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.RATE_INF__INF_PRIORITY, null, msgs);
			if (newInf_priority != null)
				msgs = ((InternalEObject)newInf_priority).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.RATE_INF__INF_PRIORITY, null, msgs);
			msgs = basicSetInf_priority(newInf_priority, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.RATE_INF__INF_PRIORITY, newInf_priority, newInf_priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getInf_weight() {
		return inf_weight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInf_weight(Expression newInf_weight, NotificationChain msgs) {
		Expression oldInf_weight = inf_weight;
		inf_weight = newInf_weight;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehaviorPackage.RATE_INF__INF_WEIGHT, oldInf_weight, newInf_weight);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInf_weight(Expression newInf_weight) {
		if (newInf_weight != inf_weight) {
			NotificationChain msgs = null;
			if (inf_weight != null)
				msgs = ((InternalEObject)inf_weight).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.RATE_INF__INF_WEIGHT, null, msgs);
			if (newInf_weight != null)
				msgs = ((InternalEObject)newInf_weight).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.RATE_INF__INF_WEIGHT, null, msgs);
			msgs = basicSetInf_weight(newInf_weight, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.RATE_INF__INF_WEIGHT, newInf_weight, newInf_weight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BehaviorPackage.RATE_INF__INF_PRIORITY:
				return basicSetInf_priority(null, msgs);
			case BehaviorPackage.RATE_INF__INF_WEIGHT:
				return basicSetInf_weight(null, msgs);
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
			case BehaviorPackage.RATE_INF__INF_PRIORITY:
				return getInf_priority();
			case BehaviorPackage.RATE_INF__INF_WEIGHT:
				return getInf_weight();
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
			case BehaviorPackage.RATE_INF__INF_PRIORITY:
				setInf_priority((Expression)newValue);
				return;
			case BehaviorPackage.RATE_INF__INF_WEIGHT:
				setInf_weight((Expression)newValue);
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
			case BehaviorPackage.RATE_INF__INF_PRIORITY:
				setInf_priority((Expression)null);
				return;
			case BehaviorPackage.RATE_INF__INF_WEIGHT:
				setInf_weight((Expression)null);
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
			case BehaviorPackage.RATE_INF__INF_PRIORITY:
				return inf_priority != null;
			case BehaviorPackage.RATE_INF__INF_WEIGHT:
				return inf_weight != null;
		}
		return super.eIsSet(featureID);
	}

} //RateInfImpl
