/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.impl;

import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Expressions.ExpressionsPackage;
import metamodel.mmaemilia.Expressions.RelatOpName;
import metamodel.mmaemilia.Expressions.RelationalExpr;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relational Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.RelationalExprImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.RelationalExprImpl#getLeftExprRel <em>Left Expr Rel</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.RelationalExprImpl#getRightExprRel <em>Right Expr Rel</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationalExprImpl extends ExpressionImpl implements RelationalExpr {
	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final RelatOpName OPERATOR_EDEFAULT = RelatOpName.GREATER;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected RelatOpName operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLeftExprRel() <em>Left Expr Rel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftExprRel()
	 * @generated
	 * @ordered
	 */
	protected Expression leftExprRel;

	/**
	 * The cached value of the '{@link #getRightExprRel() <em>Right Expr Rel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightExprRel()
	 * @generated
	 * @ordered
	 */
	protected Expression rightExprRel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationalExprImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackage.Literals.RELATIONAL_EXPR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelatOpName getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(RelatOpName newOperator) {
		RelatOpName oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.RELATIONAL_EXPR__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftExprRel() {
		return leftExprRel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftExprRel(Expression newLeftExprRel, NotificationChain msgs) {
		Expression oldLeftExprRel = leftExprRel;
		leftExprRel = newLeftExprRel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.RELATIONAL_EXPR__LEFT_EXPR_REL, oldLeftExprRel, newLeftExprRel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftExprRel(Expression newLeftExprRel) {
		if (newLeftExprRel != leftExprRel) {
			NotificationChain msgs = null;
			if (leftExprRel != null)
				msgs = ((InternalEObject)leftExprRel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.RELATIONAL_EXPR__LEFT_EXPR_REL, null, msgs);
			if (newLeftExprRel != null)
				msgs = ((InternalEObject)newLeftExprRel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.RELATIONAL_EXPR__LEFT_EXPR_REL, null, msgs);
			msgs = basicSetLeftExprRel(newLeftExprRel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.RELATIONAL_EXPR__LEFT_EXPR_REL, newLeftExprRel, newLeftExprRel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightExprRel() {
		return rightExprRel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightExprRel(Expression newRightExprRel, NotificationChain msgs) {
		Expression oldRightExprRel = rightExprRel;
		rightExprRel = newRightExprRel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.RELATIONAL_EXPR__RIGHT_EXPR_REL, oldRightExprRel, newRightExprRel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightExprRel(Expression newRightExprRel) {
		if (newRightExprRel != rightExprRel) {
			NotificationChain msgs = null;
			if (rightExprRel != null)
				msgs = ((InternalEObject)rightExprRel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.RELATIONAL_EXPR__RIGHT_EXPR_REL, null, msgs);
			if (newRightExprRel != null)
				msgs = ((InternalEObject)newRightExprRel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.RELATIONAL_EXPR__RIGHT_EXPR_REL, null, msgs);
			msgs = basicSetRightExprRel(newRightExprRel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.RELATIONAL_EXPR__RIGHT_EXPR_REL, newRightExprRel, newRightExprRel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExpressionsPackage.RELATIONAL_EXPR__LEFT_EXPR_REL:
				return basicSetLeftExprRel(null, msgs);
			case ExpressionsPackage.RELATIONAL_EXPR__RIGHT_EXPR_REL:
				return basicSetRightExprRel(null, msgs);
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
			case ExpressionsPackage.RELATIONAL_EXPR__OPERATOR:
				return getOperator();
			case ExpressionsPackage.RELATIONAL_EXPR__LEFT_EXPR_REL:
				return getLeftExprRel();
			case ExpressionsPackage.RELATIONAL_EXPR__RIGHT_EXPR_REL:
				return getRightExprRel();
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
			case ExpressionsPackage.RELATIONAL_EXPR__OPERATOR:
				setOperator((RelatOpName)newValue);
				return;
			case ExpressionsPackage.RELATIONAL_EXPR__LEFT_EXPR_REL:
				setLeftExprRel((Expression)newValue);
				return;
			case ExpressionsPackage.RELATIONAL_EXPR__RIGHT_EXPR_REL:
				setRightExprRel((Expression)newValue);
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
			case ExpressionsPackage.RELATIONAL_EXPR__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case ExpressionsPackage.RELATIONAL_EXPR__LEFT_EXPR_REL:
				setLeftExprRel((Expression)null);
				return;
			case ExpressionsPackage.RELATIONAL_EXPR__RIGHT_EXPR_REL:
				setRightExprRel((Expression)null);
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
			case ExpressionsPackage.RELATIONAL_EXPR__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case ExpressionsPackage.RELATIONAL_EXPR__LEFT_EXPR_REL:
				return leftExprRel != null;
			case ExpressionsPackage.RELATIONAL_EXPR__RIGHT_EXPR_REL:
				return rightExprRel != null;
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

} //RelationalExprImpl
