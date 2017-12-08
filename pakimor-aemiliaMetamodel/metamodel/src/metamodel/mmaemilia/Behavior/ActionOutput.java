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
 * A representation of the model object '<em><b>Action Output</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.ActionOutput#getExprOutput <em>Expr Output</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getActionOutput()
 * @model
 * @generated
 */
public interface ActionOutput extends ActionType {
	/**
	 * Returns the value of the '<em><b>Expr Output</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Expressions.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expr Output</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expr Output</em>' containment reference list.
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getActionOutput_ExprOutput()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getExprOutput();

} // ActionOutput
