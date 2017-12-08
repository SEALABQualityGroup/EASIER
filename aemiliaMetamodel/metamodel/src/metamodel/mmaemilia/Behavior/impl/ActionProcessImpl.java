/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior.impl;

import metamodel.mmaemilia.Behavior.Action;
import metamodel.mmaemilia.Behavior.ActionProcess;
import metamodel.mmaemilia.Behavior.BehaviorPackage;
import metamodel.mmaemilia.Behavior.ProcessTerm;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionProcessImpl#getProcess <em>Process</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.ActionProcessImpl#getAct <em>Act</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActionProcessImpl extends ProcessTermImpl implements ActionProcess {
	/**
	 * The cached value of the '{@link #getProcess() <em>Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcess()
	 * @generated
	 * @ordered
	 */
	protected ProcessTerm process;

	/**
	 * The cached value of the '{@link #getAct() <em>Act</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAct()
	 * @generated
	 * @ordered
	 */
	protected Action act;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BehaviorPackage.Literals.ACTION_PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessTerm getProcess() {
		return process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcess(ProcessTerm newProcess, NotificationChain msgs) {
		ProcessTerm oldProcess = process;
		process = newProcess;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION_PROCESS__PROCESS, oldProcess, newProcess);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcess(ProcessTerm newProcess) {
		if (newProcess != process) {
			NotificationChain msgs = null;
			if (process != null)
				msgs = ((InternalEObject)process).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.ACTION_PROCESS__PROCESS, null, msgs);
			if (newProcess != null)
				msgs = ((InternalEObject)newProcess).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.ACTION_PROCESS__PROCESS, null, msgs);
			msgs = basicSetProcess(newProcess, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION_PROCESS__PROCESS, newProcess, newProcess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Action getAct() {
		return act;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAct(Action newAct, NotificationChain msgs) {
		Action oldAct = act;
		act = newAct;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION_PROCESS__ACT, oldAct, newAct);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAct(Action newAct) {
		if (newAct != act) {
			NotificationChain msgs = null;
			if (act != null)
				msgs = ((InternalEObject)act).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.ACTION_PROCESS__ACT, null, msgs);
			if (newAct != null)
				msgs = ((InternalEObject)newAct).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.ACTION_PROCESS__ACT, null, msgs);
			msgs = basicSetAct(newAct, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ACTION_PROCESS__ACT, newAct, newAct));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BehaviorPackage.ACTION_PROCESS__PROCESS:
				return basicSetProcess(null, msgs);
			case BehaviorPackage.ACTION_PROCESS__ACT:
				return basicSetAct(null, msgs);
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
			case BehaviorPackage.ACTION_PROCESS__PROCESS:
				return getProcess();
			case BehaviorPackage.ACTION_PROCESS__ACT:
				return getAct();
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
			case BehaviorPackage.ACTION_PROCESS__PROCESS:
				setProcess((ProcessTerm)newValue);
				return;
			case BehaviorPackage.ACTION_PROCESS__ACT:
				setAct((Action)newValue);
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
			case BehaviorPackage.ACTION_PROCESS__PROCESS:
				setProcess((ProcessTerm)null);
				return;
			case BehaviorPackage.ACTION_PROCESS__ACT:
				setAct((Action)null);
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
			case BehaviorPackage.ACTION_PROCESS__PROCESS:
				return process != null;
			case BehaviorPackage.ACTION_PROCESS__ACT:
				return act != null;
		}
		return super.eIsSet(featureID);
	}

} //ActionProcessImpl
