/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior.impl;

import metamodel.mmaemilia.Behavior.BehavEquation;
import metamodel.mmaemilia.Behavior.BehaviorPackage;
import metamodel.mmaemilia.Behavior.ProcessTerm;

import metamodel.mmaemilia.Headers.BehavHeader;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Behav Equation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.BehavEquationImpl#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.BehavEquationImpl#getBHeader <em>BHeader</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.impl.BehavEquationImpl#getPt <em>Pt</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BehavEquationImpl extends EObjectImpl implements BehavEquation {
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
	 * The cached value of the '{@link #getBHeader() <em>BHeader</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBHeader()
	 * @generated
	 * @ordered
	 */
	protected BehavHeader bHeader;

	/**
	 * The cached value of the '{@link #getPt() <em>Pt</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPt()
	 * @generated
	 * @ordered
	 */
	protected ProcessTerm pt;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BehavEquationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BehaviorPackage.Literals.BEHAV_EQUATION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.BEHAV_EQUATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavHeader getBHeader() {
		return bHeader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBHeader(BehavHeader newBHeader, NotificationChain msgs) {
		BehavHeader oldBHeader = bHeader;
		bHeader = newBHeader;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehaviorPackage.BEHAV_EQUATION__BHEADER, oldBHeader, newBHeader);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBHeader(BehavHeader newBHeader) {
		if (newBHeader != bHeader) {
			NotificationChain msgs = null;
			if (bHeader != null)
				msgs = ((InternalEObject)bHeader).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.BEHAV_EQUATION__BHEADER, null, msgs);
			if (newBHeader != null)
				msgs = ((InternalEObject)newBHeader).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.BEHAV_EQUATION__BHEADER, null, msgs);
			msgs = basicSetBHeader(newBHeader, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.BEHAV_EQUATION__BHEADER, newBHeader, newBHeader));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessTerm getPt() {
		return pt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPt(ProcessTerm newPt, NotificationChain msgs) {
		ProcessTerm oldPt = pt;
		pt = newPt;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehaviorPackage.BEHAV_EQUATION__PT, oldPt, newPt);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPt(ProcessTerm newPt) {
		if (newPt != pt) {
			NotificationChain msgs = null;
			if (pt != null)
				msgs = ((InternalEObject)pt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.BEHAV_EQUATION__PT, null, msgs);
			if (newPt != null)
				msgs = ((InternalEObject)newPt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BehaviorPackage.BEHAV_EQUATION__PT, null, msgs);
			msgs = basicSetPt(newPt, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.BEHAV_EQUATION__PT, newPt, newPt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BehaviorPackage.BEHAV_EQUATION__BHEADER:
				return basicSetBHeader(null, msgs);
			case BehaviorPackage.BEHAV_EQUATION__PT:
				return basicSetPt(null, msgs);
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
			case BehaviorPackage.BEHAV_EQUATION__NAME:
				return getName();
			case BehaviorPackage.BEHAV_EQUATION__BHEADER:
				return getBHeader();
			case BehaviorPackage.BEHAV_EQUATION__PT:
				return getPt();
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
			case BehaviorPackage.BEHAV_EQUATION__NAME:
				setName((String)newValue);
				return;
			case BehaviorPackage.BEHAV_EQUATION__BHEADER:
				setBHeader((BehavHeader)newValue);
				return;
			case BehaviorPackage.BEHAV_EQUATION__PT:
				setPt((ProcessTerm)newValue);
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
			case BehaviorPackage.BEHAV_EQUATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BehaviorPackage.BEHAV_EQUATION__BHEADER:
				setBHeader((BehavHeader)null);
				return;
			case BehaviorPackage.BEHAV_EQUATION__PT:
				setPt((ProcessTerm)null);
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
			case BehaviorPackage.BEHAV_EQUATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BehaviorPackage.BEHAV_EQUATION__BHEADER:
				return bHeader != null;
			case BehaviorPackage.BEHAV_EQUATION__PT:
				return pt != null;
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
		result.append(')');
		return result.toString();
	}

} //BehavEquationImpl
