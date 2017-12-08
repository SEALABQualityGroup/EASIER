/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.ActionProcess#getProcess <em>Process</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.ActionProcess#getAct <em>Act</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getActionProcess()
 * @model
 * @generated
 */
public interface ActionProcess extends ProcessTerm {
	/**
	 * Returns the value of the '<em><b>Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process</em>' containment reference.
	 * @see #setProcess(ProcessTerm)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getActionProcess_Process()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ProcessTerm getProcess();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.ActionProcess#getProcess <em>Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process</em>' containment reference.
	 * @see #getProcess()
	 * @generated
	 */
	void setProcess(ProcessTerm value);

	/**
	 * Returns the value of the '<em><b>Act</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Act</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Act</em>' containment reference.
	 * @see #setAct(Action)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getActionProcess_Act()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Action getAct();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.ActionProcess#getAct <em>Act</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Act</em>' containment reference.
	 * @see #getAct()
	 * @generated
	 */
	void setAct(Action value);

} // ActionProcess
