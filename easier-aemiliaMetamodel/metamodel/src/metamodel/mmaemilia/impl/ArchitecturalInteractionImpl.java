/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.impl;

import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.LocalInteraction;
import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Architectural Interaction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.impl.ArchitecturalInteractionImpl#getIs_A <em>Is A</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchitecturalInteractionImpl#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ArchitecturalInteractionImpl#getFromInstance <em>From Instance</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArchitecturalInteractionImpl extends InteractionImpl implements ArchitecturalInteraction {
	/**
	 * The cached value of the '{@link #getIs_A() <em>Is A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIs_A()
	 * @generated
	 * @ordered
	 */
	protected LocalInteraction is_A;

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
	 * The cached value of the '{@link #getFromInstance() <em>From Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromInstance()
	 * @generated
	 * @ordered
	 */
	protected ArchiElemInstance fromInstance;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArchitecturalInteractionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return mmaemiliaPackage.Literals.ARCHITECTURAL_INTERACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalInteraction getIs_A() {
		if (is_A != null && is_A.eIsProxy()) {
			InternalEObject oldIs_A = (InternalEObject)is_A;
			is_A = (LocalInteraction)eResolveProxy(oldIs_A);
			if (is_A != oldIs_A) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, mmaemiliaPackage.ARCHITECTURAL_INTERACTION__IS_A, oldIs_A, is_A));
			}
		}
		return is_A;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalInteraction basicGetIs_A() {
		return is_A;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIs_A(LocalInteraction newIs_A) {
		LocalInteraction oldIs_A = is_A;
		is_A = newIs_A;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHITECTURAL_INTERACTION__IS_A, oldIs_A, is_A));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHITECTURAL_INTERACTION__NAME, oldName, name));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, mmaemiliaPackage.ARCHITECTURAL_INTERACTION__FROM_INSTANCE, oldFromInstance, fromInstance));
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
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ARCHITECTURAL_INTERACTION__FROM_INSTANCE, oldFromInstance, fromInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__IS_A:
				if (resolve) return getIs_A();
				return basicGetIs_A();
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__NAME:
				return getName();
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__FROM_INSTANCE:
				if (resolve) return getFromInstance();
				return basicGetFromInstance();
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
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__IS_A:
				setIs_A((LocalInteraction)newValue);
				return;
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__NAME:
				setName((String)newValue);
				return;
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__FROM_INSTANCE:
				setFromInstance((ArchiElemInstance)newValue);
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
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__IS_A:
				setIs_A((LocalInteraction)null);
				return;
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__FROM_INSTANCE:
				setFromInstance((ArchiElemInstance)null);
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
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__IS_A:
				return is_A != null;
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION__FROM_INSTANCE:
				return fromInstance != null;
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

} //ArchitecturalInteractionImpl
