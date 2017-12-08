/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.impl;

import java.util.Collection;

import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Expressions.ExpressionsPackage;
import metamodel.mmaemilia.Expressions.ListExpr;
import metamodel.mmaemilia.Expressions.ListOpName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.ListExprImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.ListExprImpl#getList_operands <em>List operands</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ListExprImpl extends ExpressionImpl implements ListExpr {
	/**
	 * The default value of the '{@link #getOperation() <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected static final ListOpName OPERATION_EDEFAULT = ListOpName.LIST_CONS;

	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected ListOpName operation = OPERATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getList_operands() <em>List operands</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getList_operands()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> list_operands;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ListExprImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackage.Literals.LIST_EXPR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListOpName getOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(ListOpName newOperation) {
		ListOpName oldOperation = operation;
		operation = newOperation == null ? OPERATION_EDEFAULT : newOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.LIST_EXPR__OPERATION, oldOperation, operation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getList_operands() {
		if (list_operands == null) {
			list_operands = new EObjectContainmentEList<Expression>(Expression.class, this, ExpressionsPackage.LIST_EXPR__LIST_OPERANDS);
		}
		return list_operands;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExpressionsPackage.LIST_EXPR__LIST_OPERANDS:
				return ((InternalEList<?>)getList_operands()).basicRemove(otherEnd, msgs);
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
			case ExpressionsPackage.LIST_EXPR__OPERATION:
				return getOperation();
			case ExpressionsPackage.LIST_EXPR__LIST_OPERANDS:
				return getList_operands();
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
			case ExpressionsPackage.LIST_EXPR__OPERATION:
				setOperation((ListOpName)newValue);
				return;
			case ExpressionsPackage.LIST_EXPR__LIST_OPERANDS:
				getList_operands().clear();
				getList_operands().addAll((Collection<? extends Expression>)newValue);
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
			case ExpressionsPackage.LIST_EXPR__OPERATION:
				setOperation(OPERATION_EDEFAULT);
				return;
			case ExpressionsPackage.LIST_EXPR__LIST_OPERANDS:
				getList_operands().clear();
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
			case ExpressionsPackage.LIST_EXPR__OPERATION:
				return operation != OPERATION_EDEFAULT;
			case ExpressionsPackage.LIST_EXPR__LIST_OPERANDS:
				return list_operands != null && !list_operands.isEmpty();
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
		result.append(" (operation: ");
		result.append(operation);
		result.append(')');
		return result.toString();
	}

} //ListExprImpl
