/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior;

import metamodel.mmaemilia.Expressions.Expression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behav Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.BehavProcess#getEqCall <em>Eq Call</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.BehavProcess#getExprs <em>Exprs</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getBehavProcess()
 * @model
 * @generated
 */
public interface BehavProcess extends ProcessTerm {
	/**
	 * Returns the value of the '<em><b>Eq Call</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Eq Call</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eq Call</em>' reference.
	 * @see #setEqCall(BehavEquation)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getBehavProcess_EqCall()
	 * @model required="true"
	 * @generated
	 */
	BehavEquation getEqCall();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.BehavProcess#getEqCall <em>Eq Call</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eq Call</em>' reference.
	 * @see #getEqCall()
	 * @generated
	 */
	void setEqCall(BehavEquation value);

	/**
	 * Returns the value of the '<em><b>Exprs</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Expressions.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exprs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exprs</em>' containment reference list.
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getBehavProcess_Exprs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getExprs();

} // BehavProcess
