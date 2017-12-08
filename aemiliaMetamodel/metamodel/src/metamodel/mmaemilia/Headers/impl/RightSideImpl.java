/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers.impl;

import java.util.Collection;

import metamodel.mmaemilia.Headers.HeadersPackage;
import metamodel.mmaemilia.Headers.Local;
import metamodel.mmaemilia.Headers.RightSide;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Right Side</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.impl.RightSideImpl#getLocalDef <em>Local Def</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RightSideImpl extends EObjectImpl implements RightSide {
	/**
	 * The cached value of the '{@link #getLocalDef() <em>Local Def</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalDef()
	 * @generated
	 * @ordered
	 */
	protected EList<Local> localDef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RightSideImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HeadersPackage.Literals.RIGHT_SIDE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Local> getLocalDef() {
		if (localDef == null) {
			localDef = new EObjectContainmentEList<Local>(Local.class, this, HeadersPackage.RIGHT_SIDE__LOCAL_DEF);
		}
		return localDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HeadersPackage.RIGHT_SIDE__LOCAL_DEF:
				return ((InternalEList<?>)getLocalDef()).basicRemove(otherEnd, msgs);
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
			case HeadersPackage.RIGHT_SIDE__LOCAL_DEF:
				return getLocalDef();
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
			case HeadersPackage.RIGHT_SIDE__LOCAL_DEF:
				getLocalDef().clear();
				getLocalDef().addAll((Collection<? extends Local>)newValue);
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
			case HeadersPackage.RIGHT_SIDE__LOCAL_DEF:
				getLocalDef().clear();
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
			case HeadersPackage.RIGHT_SIDE__LOCAL_DEF:
				return localDef != null && !localDef.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RightSideImpl
