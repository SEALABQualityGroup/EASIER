/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.impl;

import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.From;
import metamodel.mmaemilia.OutputInteraction;
import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>From</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.impl.FromImpl#getFromInstance <em>From Instance</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.FromImpl#getIsOutput <em>Is Output</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FromImpl extends EObjectImpl implements From {
	/**
	 * The cached value of the '{@link #getFromInstance() <em>From Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromInstance()
	 * @generated
	 * @ordered
	 */
	protected ArchiElemInstance fromInstance;

	/**
	 * The cached value of the '{@link #getIsOutput() <em>Is Output</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOutput()
	 * @generated
	 * @ordered
	 */
	protected OutputInteraction isOutput;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FromImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return mmaemiliaPackage.Literals.FROM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchiElemInstance getFromInstance() {
		if (fromInstance != null && fromInstance.eIsProxy()) {
			InternalEObject oldFromInstance = (InternalEObject)fromInstance;
			fromInstance = (ArchiElemInstance)eResolveProxy(oldFromInstance);
			if (fromInstance != oldFromInstance) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, mmaemiliaPackage.FROM__FROM_INSTANCE, oldFromInstance, fromInstance));
			}
		}
		return fromInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchiElemInstance basicGetFromInstance() {
		return fromInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromInstance(ArchiElemInstance newFromInstance) {
		ArchiElemInstance oldFromInstance = fromInstance;
		fromInstance = newFromInstance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.FROM__FROM_INSTANCE, oldFromInstance, fromInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputInteraction getIsOutput() {
		if (isOutput != null && isOutput.eIsProxy()) {
			InternalEObject oldIsOutput = (InternalEObject)isOutput;
			isOutput = (OutputInteraction)eResolveProxy(oldIsOutput);
			if (isOutput != oldIsOutput) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, mmaemiliaPackage.FROM__IS_OUTPUT, oldIsOutput, isOutput));
			}
		}
		return isOutput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputInteraction basicGetIsOutput() {
		return isOutput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOutput(OutputInteraction newIsOutput) {
		OutputInteraction oldIsOutput = isOutput;
		isOutput = newIsOutput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.FROM__IS_OUTPUT, oldIsOutput, isOutput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case mmaemiliaPackage.FROM__FROM_INSTANCE:
				if (resolve) return getFromInstance();
				return basicGetFromInstance();
			case mmaemiliaPackage.FROM__IS_OUTPUT:
				if (resolve) return getIsOutput();
				return basicGetIsOutput();
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
			case mmaemiliaPackage.FROM__FROM_INSTANCE:
				setFromInstance((ArchiElemInstance)newValue);
				return;
			case mmaemiliaPackage.FROM__IS_OUTPUT:
				setIsOutput((OutputInteraction)newValue);
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
			case mmaemiliaPackage.FROM__FROM_INSTANCE:
				setFromInstance((ArchiElemInstance)null);
				return;
			case mmaemiliaPackage.FROM__IS_OUTPUT:
				setIsOutput((OutputInteraction)null);
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
			case mmaemiliaPackage.FROM__FROM_INSTANCE:
				return fromInstance != null;
			case mmaemiliaPackage.FROM__IS_OUTPUT:
				return isOutput != null;
		}
		return super.eIsSet(featureID);
	}

} //FromImpl
