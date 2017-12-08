/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType.impl;

import java.lang.Integer;

import metamodel.mmaemilia.DataType.DataTypePackage;
import metamodel.mmaemilia.DataType.RangeInt;

import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Headers.Const;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Range Int</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.DataType.impl.RangeIntImpl#getMaxVal <em>Max Val</em>}</li>
 *   <li>{@link metamodel.mmaemilia.DataType.impl.RangeIntImpl#getMinVal <em>Min Val</em>}</li>
 *   <li>{@link metamodel.mmaemilia.DataType.impl.RangeIntImpl#getMinVar <em>Min Var</em>}</li>
 *   <li>{@link metamodel.mmaemilia.DataType.impl.RangeIntImpl#getMaxVar <em>Max Var</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RangeIntImpl extends IntegerImpl implements RangeInt {
	/**
	 * The cached value of the '{@link #getMaxVal() <em>Max Val</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxVal()
	 * @generated
	 * @ordered
	 */
	protected Expression maxVal;

	/**
	 * The cached value of the '{@link #getMinVal() <em>Min Val</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinVal()
	 * @generated
	 * @ordered
	 */
	protected Expression minVal;

	/**
	 * The cached value of the '{@link #getMinVar() <em>Min Var</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinVar()
	 * @generated
	 * @ordered
	 */
	protected Const minVar;

	/**
	 * The cached value of the '{@link #getMaxVar() <em>Max Var</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxVar()
	 * @generated
	 * @ordered
	 */
	protected Const maxVar;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RangeIntImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DataTypePackage.Literals.RANGE_INT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getMinVal() {
		return minVal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMinVal(Expression newMinVal, NotificationChain msgs) {
		Expression oldMinVal = minVal;
		minVal = newMinVal;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DataTypePackage.RANGE_INT__MIN_VAL, oldMinVal, newMinVal);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinVal(Expression newMinVal) {
		if (newMinVal != minVal) {
			NotificationChain msgs = null;
			if (minVal != null)
				msgs = ((InternalEObject)minVal).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DataTypePackage.RANGE_INT__MIN_VAL, null, msgs);
			if (newMinVal != null)
				msgs = ((InternalEObject)newMinVal).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DataTypePackage.RANGE_INT__MIN_VAL, null, msgs);
			msgs = basicSetMinVal(newMinVal, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataTypePackage.RANGE_INT__MIN_VAL, newMinVal, newMinVal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getMaxVal() {
		return maxVal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMaxVal(Expression newMaxVal, NotificationChain msgs) {
		Expression oldMaxVal = maxVal;
		maxVal = newMaxVal;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DataTypePackage.RANGE_INT__MAX_VAL, oldMaxVal, newMaxVal);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxVal(Expression newMaxVal) {
		if (newMaxVal != maxVal) {
			NotificationChain msgs = null;
			if (maxVal != null)
				msgs = ((InternalEObject)maxVal).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DataTypePackage.RANGE_INT__MAX_VAL, null, msgs);
			if (newMaxVal != null)
				msgs = ((InternalEObject)newMaxVal).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DataTypePackage.RANGE_INT__MAX_VAL, null, msgs);
			msgs = basicSetMaxVal(newMaxVal, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataTypePackage.RANGE_INT__MAX_VAL, newMaxVal, newMaxVal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Const getMinVar() {
		if (minVar != null && minVar.eIsProxy()) {
			InternalEObject oldMinVar = (InternalEObject)minVar;
			minVar = (Const)eResolveProxy(oldMinVar);
			if (minVar != oldMinVar) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DataTypePackage.RANGE_INT__MIN_VAR, oldMinVar, minVar));
			}
		}
		return minVar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Const basicGetMinVar() {
		return minVar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinVar(Const newMinVar) {
		Const oldMinVar = minVar;
		minVar = newMinVar;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataTypePackage.RANGE_INT__MIN_VAR, oldMinVar, minVar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Const getMaxVar() {
		if (maxVar != null && maxVar.eIsProxy()) {
			InternalEObject oldMaxVar = (InternalEObject)maxVar;
			maxVar = (Const)eResolveProxy(oldMaxVar);
			if (maxVar != oldMaxVar) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DataTypePackage.RANGE_INT__MAX_VAR, oldMaxVar, maxVar));
			}
		}
		return maxVar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Const basicGetMaxVar() {
		return maxVar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxVar(Const newMaxVar) {
		Const oldMaxVar = maxVar;
		maxVar = newMaxVar;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataTypePackage.RANGE_INT__MAX_VAR, oldMaxVar, maxVar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DataTypePackage.RANGE_INT__MAX_VAL:
				return basicSetMaxVal(null, msgs);
			case DataTypePackage.RANGE_INT__MIN_VAL:
				return basicSetMinVal(null, msgs);
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
			case DataTypePackage.RANGE_INT__MAX_VAL:
				return getMaxVal();
			case DataTypePackage.RANGE_INT__MIN_VAL:
				return getMinVal();
			case DataTypePackage.RANGE_INT__MIN_VAR:
				if (resolve) return getMinVar();
				return basicGetMinVar();
			case DataTypePackage.RANGE_INT__MAX_VAR:
				if (resolve) return getMaxVar();
				return basicGetMaxVar();
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
			case DataTypePackage.RANGE_INT__MAX_VAL:
				setMaxVal((Expression)newValue);
				return;
			case DataTypePackage.RANGE_INT__MIN_VAL:
				setMinVal((Expression)newValue);
				return;
			case DataTypePackage.RANGE_INT__MIN_VAR:
				setMinVar((Const)newValue);
				return;
			case DataTypePackage.RANGE_INT__MAX_VAR:
				setMaxVar((Const)newValue);
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
			case DataTypePackage.RANGE_INT__MAX_VAL:
				setMaxVal((Expression)null);
				return;
			case DataTypePackage.RANGE_INT__MIN_VAL:
				setMinVal((Expression)null);
				return;
			case DataTypePackage.RANGE_INT__MIN_VAR:
				setMinVar((Const)null);
				return;
			case DataTypePackage.RANGE_INT__MAX_VAR:
				setMaxVar((Const)null);
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
			case DataTypePackage.RANGE_INT__MAX_VAL:
				return maxVal != null;
			case DataTypePackage.RANGE_INT__MIN_VAL:
				return minVal != null;
			case DataTypePackage.RANGE_INT__MIN_VAR:
				return minVar != null;
			case DataTypePackage.RANGE_INT__MAX_VAR:
				return maxVar != null;
		}
		return super.eIsSet(featureID);
	}

} //RangeIntImpl
