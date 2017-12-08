/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers.impl;

import metamodel.mmaemilia.DataType.Normal;

import metamodel.mmaemilia.Expressions.Expression;

import metamodel.mmaemilia.Headers.HeadersPackage;
import metamodel.mmaemilia.Headers.VarInit;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Var Init</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.impl.VarInitImpl#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.impl.VarInitImpl#getInitVarExpr <em>Init Var Expr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.impl.VarInitImpl#getInitVarType <em>Init Var Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VarInitImpl extends EObjectImpl implements VarInit {
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
	 * The cached value of the '{@link #getInitVarExpr() <em>Init Var Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitVarExpr()
	 * @generated
	 * @ordered
	 */
	protected Expression initVarExpr;

	/**
	 * The cached value of the '{@link #getInitVarType() <em>Init Var Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitVarType()
	 * @generated
	 * @ordered
	 */
	protected Normal initVarType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VarInitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HeadersPackage.Literals.VAR_INIT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, HeadersPackage.VAR_INIT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getInitVarExpr() {
		return initVarExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitVarExpr(Expression newInitVarExpr, NotificationChain msgs) {
		Expression oldInitVarExpr = initVarExpr;
		initVarExpr = newInitVarExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HeadersPackage.VAR_INIT__INIT_VAR_EXPR, oldInitVarExpr, newInitVarExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitVarExpr(Expression newInitVarExpr) {
		if (newInitVarExpr != initVarExpr) {
			NotificationChain msgs = null;
			if (initVarExpr != null)
				msgs = ((InternalEObject)initVarExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HeadersPackage.VAR_INIT__INIT_VAR_EXPR, null, msgs);
			if (newInitVarExpr != null)
				msgs = ((InternalEObject)newInitVarExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HeadersPackage.VAR_INIT__INIT_VAR_EXPR, null, msgs);
			msgs = basicSetInitVarExpr(newInitVarExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HeadersPackage.VAR_INIT__INIT_VAR_EXPR, newInitVarExpr, newInitVarExpr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Normal getInitVarType() {
		return initVarType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitVarType(Normal newInitVarType, NotificationChain msgs) {
		Normal oldInitVarType = initVarType;
		initVarType = newInitVarType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HeadersPackage.VAR_INIT__INIT_VAR_TYPE, oldInitVarType, newInitVarType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitVarType(Normal newInitVarType) {
		if (newInitVarType != initVarType) {
			NotificationChain msgs = null;
			if (initVarType != null)
				msgs = ((InternalEObject)initVarType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HeadersPackage.VAR_INIT__INIT_VAR_TYPE, null, msgs);
			if (newInitVarType != null)
				msgs = ((InternalEObject)newInitVarType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HeadersPackage.VAR_INIT__INIT_VAR_TYPE, null, msgs);
			msgs = basicSetInitVarType(newInitVarType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HeadersPackage.VAR_INIT__INIT_VAR_TYPE, newInitVarType, newInitVarType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HeadersPackage.VAR_INIT__INIT_VAR_EXPR:
				return basicSetInitVarExpr(null, msgs);
			case HeadersPackage.VAR_INIT__INIT_VAR_TYPE:
				return basicSetInitVarType(null, msgs);
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
			case HeadersPackage.VAR_INIT__NAME:
				return getName();
			case HeadersPackage.VAR_INIT__INIT_VAR_EXPR:
				return getInitVarExpr();
			case HeadersPackage.VAR_INIT__INIT_VAR_TYPE:
				return getInitVarType();
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
			case HeadersPackage.VAR_INIT__NAME:
				setName((String)newValue);
				return;
			case HeadersPackage.VAR_INIT__INIT_VAR_EXPR:
				setInitVarExpr((Expression)newValue);
				return;
			case HeadersPackage.VAR_INIT__INIT_VAR_TYPE:
				setInitVarType((Normal)newValue);
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
			case HeadersPackage.VAR_INIT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case HeadersPackage.VAR_INIT__INIT_VAR_EXPR:
				setInitVarExpr((Expression)null);
				return;
			case HeadersPackage.VAR_INIT__INIT_VAR_TYPE:
				setInitVarType((Normal)null);
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
			case HeadersPackage.VAR_INIT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case HeadersPackage.VAR_INIT__INIT_VAR_EXPR:
				return initVarExpr != null;
			case HeadersPackage.VAR_INIT__INIT_VAR_TYPE:
				return initVarType != null;
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

} //VarInitImpl
