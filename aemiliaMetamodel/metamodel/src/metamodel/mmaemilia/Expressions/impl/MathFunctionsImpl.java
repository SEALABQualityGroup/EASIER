/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.impl;

import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Expressions.ExpressionsPackage;
import metamodel.mmaemilia.Expressions.MathFuncName;
import metamodel.mmaemilia.Expressions.MathFunctions;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Math Functions</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.MathFunctionsImpl#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.MathFunctionsImpl#getLeftExprMath <em>Left Expr Math</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.MathFunctionsImpl#getRightExprMath <em>Right Expr Math</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MathFunctionsImpl extends ExpressionImpl implements MathFunctions {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final MathFuncName NAME_EDEFAULT = MathFuncName.MOD;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected MathFuncName name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLeftExprMath() <em>Left Expr Math</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftExprMath()
	 * @generated
	 * @ordered
	 */
	protected Expression leftExprMath;

	/**
	 * The cached value of the '{@link #getRightExprMath() <em>Right Expr Math</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightExprMath()
	 * @generated
	 * @ordered
	 */
	protected Expression rightExprMath;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MathFunctionsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackage.Literals.MATH_FUNCTIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MathFuncName getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(MathFuncName newName) {
		MathFuncName oldName = name;
		name = newName == null ? NAME_EDEFAULT : newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.MATH_FUNCTIONS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftExprMath() {
		return leftExprMath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftExprMath(Expression newLeftExprMath, NotificationChain msgs) {
		Expression oldLeftExprMath = leftExprMath;
		leftExprMath = newLeftExprMath;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.MATH_FUNCTIONS__LEFT_EXPR_MATH, oldLeftExprMath, newLeftExprMath);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftExprMath(Expression newLeftExprMath) {
		if (newLeftExprMath != leftExprMath) {
			NotificationChain msgs = null;
			if (leftExprMath != null)
				msgs = ((InternalEObject)leftExprMath).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.MATH_FUNCTIONS__LEFT_EXPR_MATH, null, msgs);
			if (newLeftExprMath != null)
				msgs = ((InternalEObject)newLeftExprMath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.MATH_FUNCTIONS__LEFT_EXPR_MATH, null, msgs);
			msgs = basicSetLeftExprMath(newLeftExprMath, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.MATH_FUNCTIONS__LEFT_EXPR_MATH, newLeftExprMath, newLeftExprMath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightExprMath() {
		return rightExprMath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightExprMath(Expression newRightExprMath, NotificationChain msgs) {
		Expression oldRightExprMath = rightExprMath;
		rightExprMath = newRightExprMath;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.MATH_FUNCTIONS__RIGHT_EXPR_MATH, oldRightExprMath, newRightExprMath);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightExprMath(Expression newRightExprMath) {
		if (newRightExprMath != rightExprMath) {
			NotificationChain msgs = null;
			if (rightExprMath != null)
				msgs = ((InternalEObject)rightExprMath).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.MATH_FUNCTIONS__RIGHT_EXPR_MATH, null, msgs);
			if (newRightExprMath != null)
				msgs = ((InternalEObject)newRightExprMath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.MATH_FUNCTIONS__RIGHT_EXPR_MATH, null, msgs);
			msgs = basicSetRightExprMath(newRightExprMath, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.MATH_FUNCTIONS__RIGHT_EXPR_MATH, newRightExprMath, newRightExprMath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExpressionsPackage.MATH_FUNCTIONS__LEFT_EXPR_MATH:
				return basicSetLeftExprMath(null, msgs);
			case ExpressionsPackage.MATH_FUNCTIONS__RIGHT_EXPR_MATH:
				return basicSetRightExprMath(null, msgs);
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
			case ExpressionsPackage.MATH_FUNCTIONS__NAME:
				return getName();
			case ExpressionsPackage.MATH_FUNCTIONS__LEFT_EXPR_MATH:
				return getLeftExprMath();
			case ExpressionsPackage.MATH_FUNCTIONS__RIGHT_EXPR_MATH:
				return getRightExprMath();
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
			case ExpressionsPackage.MATH_FUNCTIONS__NAME:
				setName((MathFuncName)newValue);
				return;
			case ExpressionsPackage.MATH_FUNCTIONS__LEFT_EXPR_MATH:
				setLeftExprMath((Expression)newValue);
				return;
			case ExpressionsPackage.MATH_FUNCTIONS__RIGHT_EXPR_MATH:
				setRightExprMath((Expression)newValue);
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
			case ExpressionsPackage.MATH_FUNCTIONS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ExpressionsPackage.MATH_FUNCTIONS__LEFT_EXPR_MATH:
				setLeftExprMath((Expression)null);
				return;
			case ExpressionsPackage.MATH_FUNCTIONS__RIGHT_EXPR_MATH:
				setRightExprMath((Expression)null);
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
			case ExpressionsPackage.MATH_FUNCTIONS__NAME:
				return name != NAME_EDEFAULT;
			case ExpressionsPackage.MATH_FUNCTIONS__LEFT_EXPR_MATH:
				return leftExprMath != null;
			case ExpressionsPackage.MATH_FUNCTIONS__RIGHT_EXPR_MATH:
				return rightExprMath != null;
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

} //MathFunctionsImpl
