/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.QueueingNetwork;

import metamodel.mmaemilia.ArchiElemInstance;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Server Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.ServerNode#getClasses <em>Classes</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.ServerNode#getInstance <em>Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getServerNode()
 * @model
 * @generated
 */
public interface ServerNode extends EObject {
	/**
	 * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' containment reference list.
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getServerNode_Classes()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<WorkloadClass> getClasses();

	/**
	 * Returns the value of the '<em><b>Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance</em>' reference.
	 * @see #setInstance(ArchiElemInstance)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getServerNode_Instance()
	 * @model required="true"
	 * @generated
	 */
	ArchiElemInstance getInstance();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.ServerNode#getInstance <em>Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instance</em>' reference.
	 * @see #getInstance()
	 * @generated
	 */
	void setInstance(ArchiElemInstance value);

} // ServerNode
