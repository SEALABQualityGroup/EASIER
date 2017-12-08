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
 * A representation of the model object '<em><b>Rate Pas</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.RatePas#getPriority <em>Priority</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.RatePas#getWeight <em>Weight</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getRatePas()
 * @model
 * @generated
 */
public interface RatePas extends ActionRate {
	/**
	 * Returns the value of the '<em><b>Priority</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' containment reference.
	 * @see #setPriority(Expression)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getRatePas_Priority()
	 * @model containment="true"
	 * @generated
	 */
	Expression getPriority();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.RatePas#getPriority <em>Priority</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' containment reference.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(Expression value);

	/**
	 * Returns the value of the '<em><b>Weight</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight</em>' containment reference.
	 * @see #setWeight(Expression)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getRatePas_Weight()
	 * @model containment="true"
	 * @generated
	 */
	Expression getWeight();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.RatePas#getWeight <em>Weight</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight</em>' containment reference.
	 * @see #getWeight()
	 * @generated
	 */
	void setWeight(Expression value);

} // RatePas
