/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.impl;

import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.Behavior.Behavior;

import metamodel.mmaemilia.Elem;
import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Elem</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.impl.ElemImpl#getBehaviorDecl <em>Behavior Decl</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ElemImpl#getInstance <em>Instance</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ElemImpl extends EObjectImpl implements Elem {
	/**
	 * The cached value of the '{@link #getBehaviorDecl() <em>Behavior Decl</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehaviorDecl()
	 * @generated
	 * @ordered
	 */
	protected Behavior behaviorDecl;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return mmaemiliaPackage.Literals.ELEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Behavior getBehaviorDecl() {
		return behaviorDecl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBehaviorDecl(Behavior newBehaviorDecl, NotificationChain msgs) {
		Behavior oldBehaviorDecl = behaviorDecl;
		behaviorDecl = newBehaviorDecl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ELEM__BEHAVIOR_DECL, oldBehaviorDecl, newBehaviorDecl);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBehaviorDecl(Behavior newBehaviorDecl) {
		if (newBehaviorDecl != behaviorDecl) {
			NotificationChain msgs = null;
			if (behaviorDecl != null)
				msgs = ((InternalEObject)behaviorDecl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ELEM__BEHAVIOR_DECL, null, msgs);
			if (newBehaviorDecl != null)
				msgs = ((InternalEObject)newBehaviorDecl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ELEM__BEHAVIOR_DECL, null, msgs);
			msgs = basicSetBehaviorDecl(newBehaviorDecl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ELEM__BEHAVIOR_DECL, newBehaviorDecl, newBehaviorDecl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchiElemInstance getInstance() {
		if (eContainerFeatureID() != mmaemiliaPackage.ELEM__INSTANCE) return null;
		return (ArchiElemInstance)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInstance(ArchiElemInstance newInstance, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newInstance, mmaemiliaPackage.ELEM__INSTANCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstance(ArchiElemInstance newInstance) {
		if (newInstance != eInternalContainer() || (eContainerFeatureID() != mmaemiliaPackage.ELEM__INSTANCE && newInstance != null)) {
			if (EcoreUtil.isAncestor(this, newInstance))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInstance != null)
				msgs = ((InternalEObject)newInstance).eInverseAdd(this, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM, ArchiElemInstance.class, msgs);
			msgs = basicSetInstance(newInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ELEM__INSTANCE, newInstance, newInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case mmaemiliaPackage.ELEM__INSTANCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetInstance((ArchiElemInstance)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case mmaemiliaPackage.ELEM__BEHAVIOR_DECL:
				return basicSetBehaviorDecl(null, msgs);
			case mmaemiliaPackage.ELEM__INSTANCE:
				return basicSetInstance(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case mmaemiliaPackage.ELEM__INSTANCE:
				return eInternalContainer().eInverseRemove(this, mmaemiliaPackage.ARCHI_ELEM_INSTANCE__ELEM, ArchiElemInstance.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case mmaemiliaPackage.ELEM__BEHAVIOR_DECL:
				return getBehaviorDecl();
			case mmaemiliaPackage.ELEM__INSTANCE:
				return getInstance();
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
			case mmaemiliaPackage.ELEM__BEHAVIOR_DECL:
				setBehaviorDecl((Behavior)newValue);
				return;
			case mmaemiliaPackage.ELEM__INSTANCE:
				setInstance((ArchiElemInstance)newValue);
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
			case mmaemiliaPackage.ELEM__BEHAVIOR_DECL:
				setBehaviorDecl((Behavior)null);
				return;
			case mmaemiliaPackage.ELEM__INSTANCE:
				setInstance((ArchiElemInstance)null);
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
			case mmaemiliaPackage.ELEM__BEHAVIOR_DECL:
				return behaviorDecl != null;
			case mmaemiliaPackage.ELEM__INSTANCE:
				return getInstance() != null;
		}
		return super.eIsSet(featureID);
	}

} //ElemImpl
