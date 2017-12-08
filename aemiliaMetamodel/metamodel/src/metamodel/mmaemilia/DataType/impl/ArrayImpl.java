/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType.impl;

import java.lang.Integer;

import java.util.Collection;

import metamodel.mmaemilia.DataType.Array;
import metamodel.mmaemilia.DataType.DataTypePackage;
import metamodel.mmaemilia.DataType.Normal;

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
 * An implementation of the model object '<em><b>Array</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.DataType.impl.ArrayImpl#getArrayElemType <em>Array Elem Type</em>}</li>
 *   <li>{@link metamodel.mmaemilia.DataType.impl.ArrayImpl#getLength <em>Length</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArrayImpl extends NormalImpl implements Array {
	/**
	 * The cached value of the '{@link #getArrayElemType() <em>Array Elem Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayElemType()
	 * @generated
	 * @ordered
	 */
	protected Normal arrayElemType;

	/**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected Expression length;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DataTypePackage.Literals.ARRAY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Normal getArrayElemType() {
		return arrayElemType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArrayElemType(Normal newArrayElemType, NotificationChain msgs) {
		Normal oldArrayElemType = arrayElemType;
		arrayElemType = newArrayElemType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DataTypePackage.ARRAY__ARRAY_ELEM_TYPE, oldArrayElemType, newArrayElemType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayElemType(Normal newArrayElemType) {
		if (newArrayElemType != arrayElemType) {
			NotificationChain msgs = null;
			if (arrayElemType != null)
				msgs = ((InternalEObject)arrayElemType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DataTypePackage.ARRAY__ARRAY_ELEM_TYPE, null, msgs);
			if (newArrayElemType != null)
				msgs = ((InternalEObject)newArrayElemType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DataTypePackage.ARRAY__ARRAY_ELEM_TYPE, null, msgs);
			msgs = basicSetArrayElemType(newArrayElemType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataTypePackage.ARRAY__ARRAY_ELEM_TYPE, newArrayElemType, newArrayElemType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLength() {
		return length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLength(Expression newLength, NotificationChain msgs) {
		Expression oldLength = length;
		length = newLength;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DataTypePackage.ARRAY__LENGTH, oldLength, newLength);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLength(Expression newLength) {
		if (newLength != length) {
			NotificationChain msgs = null;
			if (length != null)
				msgs = ((InternalEObject)length).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DataTypePackage.ARRAY__LENGTH, null, msgs);
			if (newLength != null)
				msgs = ((InternalEObject)newLength).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DataTypePackage.ARRAY__LENGTH, null, msgs);
			msgs = basicSetLength(newLength, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataTypePackage.ARRAY__LENGTH, newLength, newLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DataTypePackage.ARRAY__ARRAY_ELEM_TYPE:
				return basicSetArrayElemType(null, msgs);
			case DataTypePackage.ARRAY__LENGTH:
				return basicSetLength(null, msgs);
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
			case DataTypePackage.ARRAY__ARRAY_ELEM_TYPE:
				return getArrayElemType();
			case DataTypePackage.ARRAY__LENGTH:
				return getLength();
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
			case DataTypePackage.ARRAY__ARRAY_ELEM_TYPE:
				setArrayElemType((Normal)newValue);
				return;
			case DataTypePackage.ARRAY__LENGTH:
				setLength((Expression)newValue);
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
			case DataTypePackage.ARRAY__ARRAY_ELEM_TYPE:
				setArrayElemType((Normal)null);
				return;
			case DataTypePackage.ARRAY__LENGTH:
				setLength((Expression)null);
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
			case DataTypePackage.ARRAY__ARRAY_ELEM_TYPE:
				return arrayElemType != null;
			case DataTypePackage.ARRAY__LENGTH:
				return length != null;
		}
		return super.eIsSet(featureID);
	}

} //ArrayImpl
