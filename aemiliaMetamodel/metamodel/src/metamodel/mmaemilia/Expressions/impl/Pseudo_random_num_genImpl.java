/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions.impl;

import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Expressions.ExpressionsPackage;
import metamodel.mmaemilia.Expressions.PseudoNumName;
import metamodel.mmaemilia.Expressions.Pseudo_random_num_gen;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pseudo random num gen</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.Pseudo_random_num_genImpl#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.Pseudo_random_num_genImpl#getFirstNumGenOp <em>First Num Gen Op</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.Pseudo_random_num_genImpl#getSecondNumGenOp <em>Second Num Gen Op</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.impl.Pseudo_random_num_genImpl#getThirdNumGenOp <em>Third Num Gen Op</em>}</li>
 * </ul>
 *
 * @generated
 */
public class Pseudo_random_num_genImpl extends ExpressionImpl implements Pseudo_random_num_gen {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final PseudoNumName NAME_EDEFAULT = PseudoNumName.DUNIFORM;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected PseudoNumName name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFirstNumGenOp() <em>First Num Gen Op</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstNumGenOp()
	 * @generated
	 * @ordered
	 */
	protected Expression firstNumGenOp;

	/**
	 * The cached value of the '{@link #getSecondNumGenOp() <em>Second Num Gen Op</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecondNumGenOp()
	 * @generated
	 * @ordered
	 */
	protected Expression secondNumGenOp;

	/**
	 * The cached value of the '{@link #getThirdNumGenOp() <em>Third Num Gen Op</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThirdNumGenOp()
	 * @generated
	 * @ordered
	 */
	protected Expression thirdNumGenOp;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Pseudo_random_num_genImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackage.Literals.PSEUDO_RANDOM_NUM_GEN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PseudoNumName getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(PseudoNumName newName) {
		PseudoNumName oldName = name;
		name = newName == null ? NAME_EDEFAULT : newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getFirstNumGenOp() {
		return firstNumGenOp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFirstNumGenOp(Expression newFirstNumGenOp, NotificationChain msgs) {
		Expression oldFirstNumGenOp = firstNumGenOp;
		firstNumGenOp = newFirstNumGenOp;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP, oldFirstNumGenOp, newFirstNumGenOp);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstNumGenOp(Expression newFirstNumGenOp) {
		if (newFirstNumGenOp != firstNumGenOp) {
			NotificationChain msgs = null;
			if (firstNumGenOp != null)
				msgs = ((InternalEObject)firstNumGenOp).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP, null, msgs);
			if (newFirstNumGenOp != null)
				msgs = ((InternalEObject)newFirstNumGenOp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP, null, msgs);
			msgs = basicSetFirstNumGenOp(newFirstNumGenOp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP, newFirstNumGenOp, newFirstNumGenOp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getSecondNumGenOp() {
		return secondNumGenOp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecondNumGenOp(Expression newSecondNumGenOp, NotificationChain msgs) {
		Expression oldSecondNumGenOp = secondNumGenOp;
		secondNumGenOp = newSecondNumGenOp;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP, oldSecondNumGenOp, newSecondNumGenOp);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecondNumGenOp(Expression newSecondNumGenOp) {
		if (newSecondNumGenOp != secondNumGenOp) {
			NotificationChain msgs = null;
			if (secondNumGenOp != null)
				msgs = ((InternalEObject)secondNumGenOp).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP, null, msgs);
			if (newSecondNumGenOp != null)
				msgs = ((InternalEObject)newSecondNumGenOp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP, null, msgs);
			msgs = basicSetSecondNumGenOp(newSecondNumGenOp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP, newSecondNumGenOp, newSecondNumGenOp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getThirdNumGenOp() {
		return thirdNumGenOp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThirdNumGenOp(Expression newThirdNumGenOp, NotificationChain msgs) {
		Expression oldThirdNumGenOp = thirdNumGenOp;
		thirdNumGenOp = newThirdNumGenOp;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP, oldThirdNumGenOp, newThirdNumGenOp);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThirdNumGenOp(Expression newThirdNumGenOp) {
		if (newThirdNumGenOp != thirdNumGenOp) {
			NotificationChain msgs = null;
			if (thirdNumGenOp != null)
				msgs = ((InternalEObject)thirdNumGenOp).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP, null, msgs);
			if (newThirdNumGenOp != null)
				msgs = ((InternalEObject)newThirdNumGenOp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP, null, msgs);
			msgs = basicSetThirdNumGenOp(newThirdNumGenOp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP, newThirdNumGenOp, newThirdNumGenOp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP:
				return basicSetFirstNumGenOp(null, msgs);
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP:
				return basicSetSecondNumGenOp(null, msgs);
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP:
				return basicSetThirdNumGenOp(null, msgs);
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
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__NAME:
				return getName();
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP:
				return getFirstNumGenOp();
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP:
				return getSecondNumGenOp();
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP:
				return getThirdNumGenOp();
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
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__NAME:
				setName((PseudoNumName)newValue);
				return;
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP:
				setFirstNumGenOp((Expression)newValue);
				return;
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP:
				setSecondNumGenOp((Expression)newValue);
				return;
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP:
				setThirdNumGenOp((Expression)newValue);
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
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP:
				setFirstNumGenOp((Expression)null);
				return;
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP:
				setSecondNumGenOp((Expression)null);
				return;
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP:
				setThirdNumGenOp((Expression)null);
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
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__NAME:
				return name != NAME_EDEFAULT;
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__FIRST_NUM_GEN_OP:
				return firstNumGenOp != null;
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__SECOND_NUM_GEN_OP:
				return secondNumGenOp != null;
			case ExpressionsPackage.PSEUDO_RANDOM_NUM_GEN__THIRD_NUM_GEN_OP:
				return thirdNumGenOp != null;
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

} //Pseudo_random_num_genImpl
