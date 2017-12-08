/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior;

import metamodel.mmaemilia.Expressions.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rate Inf</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.RateInf#getInf_priority <em>Inf priority</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.RateInf#getInf_weight <em>Inf weight</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getRateInf()
 * @model
 * @generated
 */
public interface RateInf extends ActionRate {
	/**
	 * Returns the value of the '<em><b>Inf priority</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inf priority</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inf priority</em>' containment reference.
	 * @see #setInf_priority(Expression)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getRateInf_Inf_priority()
	 * @model containment="true"
	 * @generated
	 */
	Expression getInf_priority();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.RateInf#getInf_priority <em>Inf priority</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inf priority</em>' containment reference.
	 * @see #getInf_priority()
	 * @generated
	 */
	void setInf_priority(Expression value);

	/**
	 * Returns the value of the '<em><b>Inf weight</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inf weight</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inf weight</em>' containment reference.
	 * @see #setInf_weight(Expression)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getRateInf_Inf_weight()
	 * @model containment="true"
	 * @generated
	 */
	Expression getInf_weight();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.RateInf#getInf_weight <em>Inf weight</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inf weight</em>' containment reference.
	 * @see #getInf_weight()
	 * @generated
	 */
	void setInf_weight(Expression value);

} // RateInf
