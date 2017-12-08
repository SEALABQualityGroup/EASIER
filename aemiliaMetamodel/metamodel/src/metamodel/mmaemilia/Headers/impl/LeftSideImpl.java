/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers.impl;

import java.util.Collection;

import metamodel.mmaemilia.Headers.HeadersPackage;
import metamodel.mmaemilia.Headers.LeftSide;
import metamodel.mmaemilia.Headers.Var;
import metamodel.mmaemilia.Headers.VarInit;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Left Side</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Headers.impl.LeftSideImpl#getInitVar <em>Init Var</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Headers.impl.LeftSideImpl#getVarDef <em>Var Def</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LeftSideImpl extends EObjectImpl implements LeftSide {
	/**
	 * The cached value of the '{@link #getInitVar() <em>Init Var</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitVar()
	 * @generated
	 * @ordered
	 */
	protected EList<VarInit> initVar;

	/**
	 * The cached value of the '{@link #getVarDef() <em>Var Def</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVarDef()
	 * @generated
	 * @ordered
	 */
	protected EList<Var> varDef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LeftSideImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HeadersPackage.Literals.LEFT_SIDE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VarInit> getInitVar() {
		if (initVar == null) {
			initVar = new EObjectContainmentEList<VarInit>(VarInit.class, this, HeadersPackage.LEFT_SIDE__INIT_VAR);
		}
		return initVar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Var> getVarDef() {
		if (varDef == null) {
			varDef = new EObjectContainmentEList<Var>(Var.class, this, HeadersPackage.LEFT_SIDE__VAR_DEF);
		}
		return varDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HeadersPackage.LEFT_SIDE__INIT_VAR:
				return ((InternalEList<?>)getInitVar()).basicRemove(otherEnd, msgs);
			case HeadersPackage.LEFT_SIDE__VAR_DEF:
				return ((InternalEList<?>)getVarDef()).basicRemove(otherEnd, msgs);
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
			case HeadersPackage.LEFT_SIDE__INIT_VAR:
				return getInitVar();
			case HeadersPackage.LEFT_SIDE__VAR_DEF:
				return getVarDef();
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
			case HeadersPackage.LEFT_SIDE__INIT_VAR:
				getInitVar().clear();
				getInitVar().addAll((Collection<? extends VarInit>)newValue);
				return;
			case HeadersPackage.LEFT_SIDE__VAR_DEF:
				getVarDef().clear();
				getVarDef().addAll((Collection<? extends Var>)newValue);
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
			case HeadersPackage.LEFT_SIDE__INIT_VAR:
				getInitVar().clear();
				return;
			case HeadersPackage.LEFT_SIDE__VAR_DEF:
				getVarDef().clear();
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
			case HeadersPackage.LEFT_SIDE__INIT_VAR:
				return initVar != null && !initVar.isEmpty();
			case HeadersPackage.LEFT_SIDE__VAR_DEF:
				return varDef != null && !varDef.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LeftSideImpl
