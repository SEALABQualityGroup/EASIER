/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers.impl;

import java.util.Collection;

import metamodel.mmaemilia.Headers.Const;
import metamodel.mmaemilia.Headers.ET_Header;
import metamodel.mmaemilia.Headers.HeadersPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ET Header</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.impl.ET_HeaderImpl#getCostant <em>Costant</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ET_HeaderImpl extends EObjectImpl implements ET_Header {
	/**
	 * The cached value of the '{@link #getCostant() <em>Costant</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostant()
	 * @generated
	 * @ordered
	 */
	protected EList<Const> costant;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ET_HeaderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HeadersPackage.Literals.ET_HEADER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Const> getCostant() {
		if (costant == null) {
			costant = new EObjectContainmentEList<Const>(Const.class, this, HeadersPackage.ET_HEADER__COSTANT);
		}
		return costant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HeadersPackage.ET_HEADER__COSTANT:
				return ((InternalEList<?>)getCostant()).basicRemove(otherEnd, msgs);
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
			case HeadersPackage.ET_HEADER__COSTANT:
				return getCostant();
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
			case HeadersPackage.ET_HEADER__COSTANT:
				getCostant().clear();
				getCostant().addAll((Collection<? extends Const>)newValue);
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
			case HeadersPackage.ET_HEADER__COSTANT:
				getCostant().clear();
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
			case HeadersPackage.ET_HEADER__COSTANT:
				return costant != null && !costant.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ET_HeaderImpl
