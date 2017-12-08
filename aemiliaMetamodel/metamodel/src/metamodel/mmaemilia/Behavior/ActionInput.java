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
 * A representation of the model object '<em><b>Action Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.ActionInput#getVar <em>Var</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getActionInput()
 * @model
 * @generated
 */
public interface ActionInput extends ActionType {
	/**
	 * Returns the value of the '<em><b>Var</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Var</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Var</em>' attribute list.
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getActionInput_Var()
	 * @model required="true"
	 * @generated
	 */
	EList<String> getVar();

} // ActionInput
