/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavior</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.Behavior#getEquations <em>Equations</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getBehavior()
 * @model
 * @generated
 */
public interface Behavior extends EObject {
	/**
	 * Returns the value of the '<em><b>Equations</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Behavior.BehavEquation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Equations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Equations</em>' containment reference list.
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getBehavior_Equations()
	 * @model containment="true"
	 * @generated
	 */
	EList<BehavEquation> getEquations();

} // Behavior
