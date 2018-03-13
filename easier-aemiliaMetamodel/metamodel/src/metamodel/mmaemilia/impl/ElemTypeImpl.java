/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.impl;

import java.util.Collection;

import metamodel.mmaemilia.Behavior.Behavior;

import metamodel.mmaemilia.ElemType;

import metamodel.mmaemilia.Headers.ET_Header;

import metamodel.mmaemilia.InputInteraction;
import metamodel.mmaemilia.OutputInteraction;
import metamodel.mmaemilia.QueueingNetwork.WorkloadClass;
import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Elem Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.impl.ElemTypeImpl#getIiDecl <em>Ii Decl</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ElemTypeImpl#getOiDecl <em>Oi Decl</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ElemTypeImpl#getEtName <em>Et Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ElemTypeImpl#getElemHeader <em>Elem Header</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ElemTypeImpl#getBehaviorDecl <em>Behavior Decl</em>}</li>
 *   <li>{@link metamodel.mmaemilia.impl.ElemTypeImpl#getClasses <em>Classes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ElemTypeImpl extends EObjectImpl implements ElemType {
	/**
	 * The cached value of the '{@link #getIiDecl() <em>Ii Decl</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIiDecl()
	 * @generated
	 * @ordered
	 */
	protected EList<InputInteraction> iiDecl;

	/**
	 * The cached value of the '{@link #getOiDecl() <em>Oi Decl</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOiDecl()
	 * @generated
	 * @ordered
	 */
	protected EList<OutputInteraction> oiDecl;

	/**
	 * The default value of the '{@link #getEtName() <em>Et Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEtName()
	 * @generated
	 * @ordered
	 */
	protected static final String ET_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEtName() <em>Et Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEtName()
	 * @generated
	 * @ordered
	 */
	protected String etName = ET_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getElemHeader() <em>Elem Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElemHeader()
	 * @generated
	 * @ordered
	 */
	protected ET_Header elemHeader;

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
	 * The cached value of the '{@link #getClasses() <em>Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkloadClass> classes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElemTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return mmaemiliaPackage.Literals.ELEM_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InputInteraction> getIiDecl() {
		if (iiDecl == null) {
			iiDecl = new EObjectContainmentEList<InputInteraction>(InputInteraction.class, this, mmaemiliaPackage.ELEM_TYPE__II_DECL);
		}
		return iiDecl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OutputInteraction> getOiDecl() {
		if (oiDecl == null) {
			oiDecl = new EObjectContainmentEList<OutputInteraction>(OutputInteraction.class, this, mmaemiliaPackage.ELEM_TYPE__OI_DECL);
		}
		return oiDecl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEtName() {
		return etName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEtName(String newEtName) {
		String oldEtName = etName;
		etName = newEtName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ELEM_TYPE__ET_NAME, oldEtName, etName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ET_Header getElemHeader() {
		return elemHeader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElemHeader(ET_Header newElemHeader, NotificationChain msgs) {
		ET_Header oldElemHeader = elemHeader;
		elemHeader = newElemHeader;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ELEM_TYPE__ELEM_HEADER, oldElemHeader, newElemHeader);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElemHeader(ET_Header newElemHeader) {
		if (newElemHeader != elemHeader) {
			NotificationChain msgs = null;
			if (elemHeader != null)
				msgs = ((InternalEObject)elemHeader).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ELEM_TYPE__ELEM_HEADER, null, msgs);
			if (newElemHeader != null)
				msgs = ((InternalEObject)newElemHeader).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ELEM_TYPE__ELEM_HEADER, null, msgs);
			msgs = basicSetElemHeader(newElemHeader, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ELEM_TYPE__ELEM_HEADER, newElemHeader, newElemHeader));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ELEM_TYPE__BEHAVIOR_DECL, oldBehaviorDecl, newBehaviorDecl);
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
				msgs = ((InternalEObject)behaviorDecl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ELEM_TYPE__BEHAVIOR_DECL, null, msgs);
			if (newBehaviorDecl != null)
				msgs = ((InternalEObject)newBehaviorDecl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - mmaemiliaPackage.ELEM_TYPE__BEHAVIOR_DECL, null, msgs);
			msgs = basicSetBehaviorDecl(newBehaviorDecl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, mmaemiliaPackage.ELEM_TYPE__BEHAVIOR_DECL, newBehaviorDecl, newBehaviorDecl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WorkloadClass> getClasses() {
		if (classes == null) {
			classes = new EObjectContainmentEList<WorkloadClass>(WorkloadClass.class, this, mmaemiliaPackage.ELEM_TYPE__CLASSES);
		}
		return classes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case mmaemiliaPackage.ELEM_TYPE__II_DECL:
				return ((InternalEList<?>)getIiDecl()).basicRemove(otherEnd, msgs);
			case mmaemiliaPackage.ELEM_TYPE__OI_DECL:
				return ((InternalEList<?>)getOiDecl()).basicRemove(otherEnd, msgs);
			case mmaemiliaPackage.ELEM_TYPE__ELEM_HEADER:
				return basicSetElemHeader(null, msgs);
			case mmaemiliaPackage.ELEM_TYPE__BEHAVIOR_DECL:
				return basicSetBehaviorDecl(null, msgs);
			case mmaemiliaPackage.ELEM_TYPE__CLASSES:
				return ((InternalEList<?>)getClasses()).basicRemove(otherEnd, msgs);
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
			case mmaemiliaPackage.ELEM_TYPE__II_DECL:
				return getIiDecl();
			case mmaemiliaPackage.ELEM_TYPE__OI_DECL:
				return getOiDecl();
			case mmaemiliaPackage.ELEM_TYPE__ET_NAME:
				return getEtName();
			case mmaemiliaPackage.ELEM_TYPE__ELEM_HEADER:
				return getElemHeader();
			case mmaemiliaPackage.ELEM_TYPE__BEHAVIOR_DECL:
				return getBehaviorDecl();
			case mmaemiliaPackage.ELEM_TYPE__CLASSES:
				return getClasses();
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
			case mmaemiliaPackage.ELEM_TYPE__II_DECL:
				getIiDecl().clear();
				getIiDecl().addAll((Collection<? extends InputInteraction>)newValue);
				return;
			case mmaemiliaPackage.ELEM_TYPE__OI_DECL:
				getOiDecl().clear();
				getOiDecl().addAll((Collection<? extends OutputInteraction>)newValue);
				return;
			case mmaemiliaPackage.ELEM_TYPE__ET_NAME:
				setEtName((String)newValue);
				return;
			case mmaemiliaPackage.ELEM_TYPE__ELEM_HEADER:
				setElemHeader((ET_Header)newValue);
				return;
			case mmaemiliaPackage.ELEM_TYPE__BEHAVIOR_DECL:
				setBehaviorDecl((Behavior)newValue);
				return;
			case mmaemiliaPackage.ELEM_TYPE__CLASSES:
				getClasses().clear();
				getClasses().addAll((Collection<? extends WorkloadClass>)newValue);
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
			case mmaemiliaPackage.ELEM_TYPE__II_DECL:
				getIiDecl().clear();
				return;
			case mmaemiliaPackage.ELEM_TYPE__OI_DECL:
				getOiDecl().clear();
				return;
			case mmaemiliaPackage.ELEM_TYPE__ET_NAME:
				setEtName(ET_NAME_EDEFAULT);
				return;
			case mmaemiliaPackage.ELEM_TYPE__ELEM_HEADER:
				setElemHeader((ET_Header)null);
				return;
			case mmaemiliaPackage.ELEM_TYPE__BEHAVIOR_DECL:
				setBehaviorDecl((Behavior)null);
				return;
			case mmaemiliaPackage.ELEM_TYPE__CLASSES:
				getClasses().clear();
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
			case mmaemiliaPackage.ELEM_TYPE__II_DECL:
				return iiDecl != null && !iiDecl.isEmpty();
			case mmaemiliaPackage.ELEM_TYPE__OI_DECL:
				return oiDecl != null && !oiDecl.isEmpty();
			case mmaemiliaPackage.ELEM_TYPE__ET_NAME:
				return ET_NAME_EDEFAULT == null ? etName != null : !ET_NAME_EDEFAULT.equals(etName);
			case mmaemiliaPackage.ELEM_TYPE__ELEM_HEADER:
				return elemHeader != null;
			case mmaemiliaPackage.ELEM_TYPE__BEHAVIOR_DECL:
				return behaviorDecl != null;
			case mmaemiliaPackage.ELEM_TYPE__CLASSES:
				return classes != null && !classes.isEmpty();
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
		result.append(" (etName: ");
		result.append(etName);
		result.append(')');
		return result.toString();
	}

} //ElemTypeImpl
