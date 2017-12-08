/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior.impl;

import java.util.Collection;

import metamodel.mmaemilia.Behavior.BehavEquation;
import metamodel.mmaemilia.Behavior.BehavProcess;
import metamodel.mmaemilia.Behavior.BehaviorPackage;

import metamodel.mmaemilia.Expressions.Expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Behav Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.BehavProcessImpl#getEqCall <em>Eq Call</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.BehavProcessImpl#getExprs <em>Exprs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BehavProcessImpl extends ProcessTermImpl implements BehavProcess {
	/**
	 * The cached value of the '{@link #getEqCall() <em>Eq Call</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEqCall()
	 * @generated
	 * @ordered
	 */
	protected BehavEquation eqCall;

	/**
	 * The cached value of the '{@link #getExprs() <em>Exprs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExprs()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> exprs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BehavProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BehaviorPackage.Literals.BEHAV_PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavEquation getEqCall() {
		if (eqCall != null && eqCall.eIsProxy()) {
			InternalEObject oldEqCall = (InternalEObject)eqCall;
			eqCall = (BehavEquation)eResolveProxy(oldEqCall);
			if (eqCall != oldEqCall) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehaviorPackage.BEHAV_PROCESS__EQ_CALL, oldEqCall, eqCall));
			}
		}
		return eqCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavEquation basicGetEqCall() {
		return eqCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEqCall(BehavEquation newEqCall) {
		BehavEquation oldEqCall = eqCall;
		eqCall = newEqCall;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.BEHAV_PROCESS__EQ_CALL, oldEqCall, eqCall));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getExprs() {
		if (exprs == null) {
			exprs = new EObjectContainmentEList<Expression>(Expression.class, this, BehaviorPackage.BEHAV_PROCESS__EXPRS);
		}
		return exprs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BehaviorPackage.BEHAV_PROCESS__EXPRS:
				return ((InternalEList<?>)getExprs()).basicRemove(otherEnd, msgs);
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
			case BehaviorPackage.BEHAV_PROCESS__EQ_CALL:
				if (resolve) return getEqCall();
				return basicGetEqCall();
			case BehaviorPackage.BEHAV_PROCESS__EXPRS:
				return getExprs();
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
			case BehaviorPackage.BEHAV_PROCESS__EQ_CALL:
				setEqCall((BehavEquation)newValue);
				return;
			case BehaviorPackage.BEHAV_PROCESS__EXPRS:
				getExprs().clear();
				getExprs().addAll((Collection<? extends Expression>)newValue);
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
			case BehaviorPackage.BEHAV_PROCESS__EQ_CALL:
				setEqCall((BehavEquation)null);
				return;
			case BehaviorPackage.BEHAV_PROCESS__EXPRS:
				getExprs().clear();
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
			case BehaviorPackage.BEHAV_PROCESS__EQ_CALL:
				return eqCall != null;
			case BehaviorPackage.BEHAV_PROCESS__EXPRS:
				return exprs != null && !exprs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BehavProcessImpl
