/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType.impl;

import metamodel.mmaemilia.DataType.DataTypePackage;
import metamodel.mmaemilia.DataType.Normal;

import metamodel.mmaemilia.Headers.Const;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Normal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.DataType.impl.NormalImpl#getVar <em>Var</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NormalImpl extends DataTypeImpl implements Normal {
	/**
	 * The cached value of the '{@link #getVar() <em>Var</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVar()
	 * @generated
	 * @ordered
	 */
	protected Const var;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NormalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DataTypePackage.Literals.NORMAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Const getVar() {
		if (var != null && var.eIsProxy()) {
			InternalEObject oldVar = (InternalEObject)var;
			var = (Const)eResolveProxy(oldVar);
			if (var != oldVar) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DataTypePackage.NORMAL__VAR, oldVar, var));
			}
		}
		return var;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Const basicGetVar() {
		return var;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVar(Const newVar) {
		Const oldVar = var;
		var = newVar;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataTypePackage.NORMAL__VAR, oldVar, var));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DataTypePackage.NORMAL__VAR:
				if (resolve) return getVar();
				return basicGetVar();
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
			case DataTypePackage.NORMAL__VAR:
				setVar((Const)newValue);
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
			case DataTypePackage.NORMAL__VAR:
				setVar((Const)null);
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
			case DataTypePackage.NORMAL__VAR:
				return var != null;
		}
		return super.eIsSet(featureID);
	}

} //NormalImpl
