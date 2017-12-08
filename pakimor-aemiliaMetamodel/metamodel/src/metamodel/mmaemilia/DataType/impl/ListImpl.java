/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType.impl;

import java.util.Collection;

import metamodel.mmaemilia.DataType.DataTypePackage;
import metamodel.mmaemilia.DataType.List;
import metamodel.mmaemilia.DataType.Normal;

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
 * An implementation of the model object '<em><b>List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.DataType.impl.ListImpl#getListElemType <em>List Elem Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ListImpl extends NormalImpl implements List {
	/**
	 * The cached value of the '{@link #getListElemType() <em>List Elem Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListElemType()
	 * @generated
	 * @ordered
	 */
	protected Normal listElemType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DataTypePackage.Literals.LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Normal getListElemType() {
		return listElemType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetListElemType(Normal newListElemType, NotificationChain msgs) {
		Normal oldListElemType = listElemType;
		listElemType = newListElemType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DataTypePackage.LIST__LIST_ELEM_TYPE, oldListElemType, newListElemType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListElemType(Normal newListElemType) {
		if (newListElemType != listElemType) {
			NotificationChain msgs = null;
			if (listElemType != null)
				msgs = ((InternalEObject)listElemType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DataTypePackage.LIST__LIST_ELEM_TYPE, null, msgs);
			if (newListElemType != null)
				msgs = ((InternalEObject)newListElemType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DataTypePackage.LIST__LIST_ELEM_TYPE, null, msgs);
			msgs = basicSetListElemType(newListElemType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataTypePackage.LIST__LIST_ELEM_TYPE, newListElemType, newListElemType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DataTypePackage.LIST__LIST_ELEM_TYPE:
				return basicSetListElemType(null, msgs);
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
			case DataTypePackage.LIST__LIST_ELEM_TYPE:
				return getListElemType();
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
			case DataTypePackage.LIST__LIST_ELEM_TYPE:
				setListElemType((Normal)newValue);
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
			case DataTypePackage.LIST__LIST_ELEM_TYPE:
				setListElemType((Normal)null);
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
			case DataTypePackage.LIST__LIST_ELEM_TYPE:
				return listElemType != null;
		}
		return super.eIsSet(featureID);
	}

} //ListImpl
