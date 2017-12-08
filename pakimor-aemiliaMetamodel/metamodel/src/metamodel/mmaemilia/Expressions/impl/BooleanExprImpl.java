/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.impl;

import metamodel.mmaemilia.Expressions.BoolOpName;
import metamodel.mmaemilia.Expressions.BooleanExpr;
import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Expressions.ExpressionsPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.BooleanExprImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.BooleanExprImpl#getLeftExprBool <em>Left Expr Bool</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.BooleanExprImpl#getRightExprBool <em>Right Expr Bool</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BooleanExprImpl extends ExpressionImpl implements BooleanExpr {
	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final BoolOpName OPERATOR_EDEFAULT = BoolOpName.NOT;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected BoolOpName operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLeftExprBool() <em>Left Expr Bool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftExprBool()
	 * @generated
	 * @ordered
	 */
	protected Expression leftExprBool;

	/**
	 * The cached value of the '{@link #getRightExprBool() <em>Right Expr Bool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightExprBool()
	 * @generated
	 * @ordered
	 */
	protected Expression rightExprBool;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanExprImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackage.Literals.BOOLEAN_EXPR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BoolOpName getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(BoolOpName newOperator) {
		BoolOpName oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.BOOLEAN_EXPR__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftExprBool() {
		return leftExprBool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftExprBool(Expression newLeftExprBool, NotificationChain msgs) {
		Expression oldLeftExprBool = leftExprBool;
		leftExprBool = newLeftExprBool;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.BOOLEAN_EXPR__LEFT_EXPR_BOOL, oldLeftExprBool, newLeftExprBool);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftExprBool(Expression newLeftExprBool) {
		if (newLeftExprBool != leftExprBool) {
			NotificationChain msgs = null;
			if (leftExprBool != null)
				msgs = ((InternalEObject)leftExprBool).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.BOOLEAN_EXPR__LEFT_EXPR_BOOL, null, msgs);
			if (newLeftExprBool != null)
				msgs = ((InternalEObject)newLeftExprBool).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.BOOLEAN_EXPR__LEFT_EXPR_BOOL, null, msgs);
			msgs = basicSetLeftExprBool(newLeftExprBool, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.BOOLEAN_EXPR__LEFT_EXPR_BOOL, newLeftExprBool, newLeftExprBool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightExprBool() {
		return rightExprBool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightExprBool(Expression newRightExprBool, NotificationChain msgs) {
		Expression oldRightExprBool = rightExprBool;
		rightExprBool = newRightExprBool;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.BOOLEAN_EXPR__RIGHT_EXPR_BOOL, oldRightExprBool, newRightExprBool);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightExprBool(Expression newRightExprBool) {
		if (newRightExprBool != rightExprBool) {
			NotificationChain msgs = null;
			if (rightExprBool != null)
				msgs = ((InternalEObject)rightExprBool).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.BOOLEAN_EXPR__RIGHT_EXPR_BOOL, null, msgs);
			if (newRightExprBool != null)
				msgs = ((InternalEObject)newRightExprBool).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.BOOLEAN_EXPR__RIGHT_EXPR_BOOL, null, msgs);
			msgs = basicSetRightExprBool(newRightExprBool, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.BOOLEAN_EXPR__RIGHT_EXPR_BOOL, newRightExprBool, newRightExprBool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExpressionsPackage.BOOLEAN_EXPR__LEFT_EXPR_BOOL:
				return basicSetLeftExprBool(null, msgs);
			case ExpressionsPackage.BOOLEAN_EXPR__RIGHT_EXPR_BOOL:
				return basicSetRightExprBool(null, msgs);
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
			case ExpressionsPackage.BOOLEAN_EXPR__OPERATOR:
				return getOperator();
			case ExpressionsPackage.BOOLEAN_EXPR__LEFT_EXPR_BOOL:
				return getLeftExprBool();
			case ExpressionsPackage.BOOLEAN_EXPR__RIGHT_EXPR_BOOL:
				return getRightExprBool();
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
			case ExpressionsPackage.BOOLEAN_EXPR__OPERATOR:
				setOperator((BoolOpName)newValue);
				return;
			case ExpressionsPackage.BOOLEAN_EXPR__LEFT_EXPR_BOOL:
				setLeftExprBool((Expression)newValue);
				return;
			case ExpressionsPackage.BOOLEAN_EXPR__RIGHT_EXPR_BOOL:
				setRightExprBool((Expression)newValue);
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
			case ExpressionsPackage.BOOLEAN_EXPR__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case ExpressionsPackage.BOOLEAN_EXPR__LEFT_EXPR_BOOL:
				setLeftExprBool((Expression)null);
				return;
			case ExpressionsPackage.BOOLEAN_EXPR__RIGHT_EXPR_BOOL:
				setRightExprBool((Expression)null);
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
			case ExpressionsPackage.BOOLEAN_EXPR__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case ExpressionsPackage.BOOLEAN_EXPR__LEFT_EXPR_BOOL:
				return leftExprBool != null;
			case ExpressionsPackage.BOOLEAN_EXPR__RIGHT_EXPR_BOOL:
				return rightExprBool != null;
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

} //BooleanExprImpl
