/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Choice Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.ChoiceProcess#getProcesses <em>Processes</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getChoiceProcess()
 * @model
 * @generated
 */
public interface ChoiceProcess extends ProcessTerm {
	/**
	 * Returns the value of the '<em><b>Processes</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Behavior.ProcessTerm}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Processes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Processes</em>' containment reference list.
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getChoiceProcess_Processes()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProcessTerm> getProcesses();

} // ChoiceProcess
