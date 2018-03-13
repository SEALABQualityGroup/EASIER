/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers.impl;

import metamodel.mmaemilia.DataType.DataType;

import metamodel.mmaemilia.Expressions.Expression;

import metamodel.mmaemilia.Headers.ConstInit;
import metamodel.mmaemilia.Headers.HeadersPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Const Init</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.impl.ConstInitImpl#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.impl.ConstInitImpl#getInitConstData <em>Init Const Data</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.impl.ConstInitImpl#getInitConstExpr <em>Init Const Expr</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConstInitImpl extends EObjectImpl implements ConstInit {
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
	 * The cached value of the '{@link #getInitConstData() <em>Init Const Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitConstData()
	 * @generated
	 * @ordered
	 */
	protected DataType initConstData;

	/**
	 * The cached value of the '{@link #getInitConstExpr() <em>Init Const Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitConstExpr()
	 * @generated
	 * @ordered
	 */
	protected Expression initConstExpr;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstInitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HeadersPackage.Literals.CONST_INIT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, HeadersPackage.CONST_INIT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType getInitConstData() {
		return initConstData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitConstData(DataType newInitConstData, NotificationChain msgs) {
		DataType oldInitConstData = initConstData;
		initConstData = newInitConstData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HeadersPackage.CONST_INIT__INIT_CONST_DATA, oldInitConstData, newInitConstData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitConstData(DataType newInitConstData) {
		if (newInitConstData != initConstData) {
			NotificationChain msgs = null;
			if (initConstData != null)
				msgs = ((InternalEObject)initConstData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HeadersPackage.CONST_INIT__INIT_CONST_DATA, null, msgs);
			if (newInitConstData != null)
				msgs = ((InternalEObject)newInitConstData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HeadersPackage.CONST_INIT__INIT_CONST_DATA, null, msgs);
			msgs = basicSetInitConstData(newInitConstData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HeadersPackage.CONST_INIT__INIT_CONST_DATA, newInitConstData, newInitConstData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getInitConstExpr() {
		return initConstExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitConstExpr(Expression newInitConstExpr, NotificationChain msgs) {
		Expression oldInitConstExpr = initConstExpr;
		initConstExpr = newInitConstExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HeadersPackage.CONST_INIT__INIT_CONST_EXPR, oldInitConstExpr, newInitConstExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitConstExpr(Expression newInitConstExpr) {
		if (newInitConstExpr != initConstExpr) {
			NotificationChain msgs = null;
			if (initConstExpr != null)
				msgs = ((InternalEObject)initConstExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HeadersPackage.CONST_INIT__INIT_CONST_EXPR, null, msgs);
			if (newInitConstExpr != null)
				msgs = ((InternalEObject)newInitConstExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HeadersPackage.CONST_INIT__INIT_CONST_EXPR, null, msgs);
			msgs = basicSetInitConstExpr(newInitConstExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HeadersPackage.CONST_INIT__INIT_CONST_EXPR, newInitConstExpr, newInitConstExpr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HeadersPackage.CONST_INIT__INIT_CONST_DATA:
				return basicSetInitConstData(null, msgs);
			case HeadersPackage.CONST_INIT__INIT_CONST_EXPR:
				return basicSetInitConstExpr(null, msgs);
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
			case HeadersPackage.CONST_INIT__NAME:
				return getName();
			case HeadersPackage.CONST_INIT__INIT_CONST_DATA:
				return getInitConstData();
			case HeadersPackage.CONST_INIT__INIT_CONST_EXPR:
				return getInitConstExpr();
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
			case HeadersPackage.CONST_INIT__NAME:
				setName((String)newValue);
				return;
			case HeadersPackage.CONST_INIT__INIT_CONST_DATA:
				setInitConstData((DataType)newValue);
				return;
			case HeadersPackage.CONST_INIT__INIT_CONST_EXPR:
				setInitConstExpr((Expression)newValue);
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
			case HeadersPackage.CONST_INIT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case HeadersPackage.CONST_INIT__INIT_CONST_DATA:
				setInitConstData((DataType)null);
				return;
			case HeadersPackage.CONST_INIT__INIT_CONST_EXPR:
				setInitConstExpr((Expression)null);
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
			case HeadersPackage.CONST_INIT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case HeadersPackage.CONST_INIT__INIT_CONST_DATA:
				return initConstData != null;
			case HeadersPackage.CONST_INIT__INIT_CONST_EXPR:
				return initConstExpr != null;
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

} //ConstInitImpl
