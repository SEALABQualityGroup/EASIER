/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.impl;

import metamodel.mmaemilia.Expressions.ArithExpr;
import metamodel.mmaemilia.Expressions.ArithOpName;
import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Expressions.ExpressionsPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arith Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.ArithExprImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.ArithExprImpl#getLeftExprArith <em>Left Expr Arith</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.ArithExprImpl#getRightExprArith <em>Right Expr Arith</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArithExprImpl extends ExpressionImpl implements ArithExpr {
	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final ArithOpName OPERATOR_EDEFAULT = ArithOpName.PLUS;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected ArithOpName operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLeftExprArith() <em>Left Expr Arith</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftExprArith()
	 * @generated
	 * @ordered
	 */
	protected Expression leftExprArith;

	/**
	 * The cached value of the '{@link #getRightExprArith() <em>Right Expr Arith</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightExprArith()
	 * @generated
	 * @ordered
	 */
	protected Expression rightExprArith;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArithExprImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackage.Literals.ARITH_EXPR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithOpName getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(ArithOpName newOperator) {
		ArithOpName oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.ARITH_EXPR__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftExprArith() {
		return leftExprArith;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftExprArith(Expression newLeftExprArith, NotificationChain msgs) {
		Expression oldLeftExprArith = leftExprArith;
		leftExprArith = newLeftExprArith;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.ARITH_EXPR__LEFT_EXPR_ARITH, oldLeftExprArith, newLeftExprArith);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftExprArith(Expression newLeftExprArith) {
		if (newLeftExprArith != leftExprArith) {
			NotificationChain msgs = null;
			if (leftExprArith != null)
				msgs = ((InternalEObject)leftExprArith).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.ARITH_EXPR__LEFT_EXPR_ARITH, null, msgs);
			if (newLeftExprArith != null)
				msgs = ((InternalEObject)newLeftExprArith).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.ARITH_EXPR__LEFT_EXPR_ARITH, null, msgs);
			msgs = basicSetLeftExprArith(newLeftExprArith, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.ARITH_EXPR__LEFT_EXPR_ARITH, newLeftExprArith, newLeftExprArith));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightExprArith() {
		return rightExprArith;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightExprArith(Expression newRightExprArith, NotificationChain msgs) {
		Expression oldRightExprArith = rightExprArith;
		rightExprArith = newRightExprArith;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.ARITH_EXPR__RIGHT_EXPR_ARITH, oldRightExprArith, newRightExprArith);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightExprArith(Expression newRightExprArith) {
		if (newRightExprArith != rightExprArith) {
			NotificationChain msgs = null;
			if (rightExprArith != null)
				msgs = ((InternalEObject)rightExprArith).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.ARITH_EXPR__RIGHT_EXPR_ARITH, null, msgs);
			if (newRightExprArith != null)
				msgs = ((InternalEObject)newRightExprArith).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.ARITH_EXPR__RIGHT_EXPR_ARITH, null, msgs);
			msgs = basicSetRightExprArith(newRightExprArith, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.ARITH_EXPR__RIGHT_EXPR_ARITH, newRightExprArith, newRightExprArith));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExpressionsPackage.ARITH_EXPR__LEFT_EXPR_ARITH:
				return basicSetLeftExprArith(null, msgs);
			case ExpressionsPackage.ARITH_EXPR__RIGHT_EXPR_ARITH:
				return basicSetRightExprArith(null, msgs);
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
			case ExpressionsPackage.ARITH_EXPR__OPERATOR:
				return getOperator();
			case ExpressionsPackage.ARITH_EXPR__LEFT_EXPR_ARITH:
				return getLeftExprArith();
			case ExpressionsPackage.ARITH_EXPR__RIGHT_EXPR_ARITH:
				return getRightExprArith();
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
			case ExpressionsPackage.ARITH_EXPR__OPERATOR:
				setOperator((ArithOpName)newValue);
				return;
			case ExpressionsPackage.ARITH_EXPR__LEFT_EXPR_ARITH:
				setLeftExprArith((Expression)newValue);
				return;
			case ExpressionsPackage.ARITH_EXPR__RIGHT_EXPR_ARITH:
				setRightExprArith((Expression)newValue);
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
			case ExpressionsPackage.ARITH_EXPR__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case ExpressionsPackage.ARITH_EXPR__LEFT_EXPR_ARITH:
				setLeftExprArith((Expression)null);
				return;
			case ExpressionsPackage.ARITH_EXPR__RIGHT_EXPR_ARITH:
				setRightExprArith((Expression)null);
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
			case ExpressionsPackage.ARITH_EXPR__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case ExpressionsPackage.ARITH_EXPR__LEFT_EXPR_ARITH:
				return leftExprArith != null;
			case ExpressionsPackage.ARITH_EXPR__RIGHT_EXPR_ARITH:
				return rightExprArith != null;
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
		result.append(" (operator: ");
		result.append(operator);
		result.append(')');
		return result.toString();
	}

} //ArithExprImpl
