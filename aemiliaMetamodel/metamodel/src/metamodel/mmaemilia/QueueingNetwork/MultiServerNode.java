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
 * A representation of the model object '<em><b>Multi Server Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getMultiServerID <em>Multi Server ID</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getThroughput <em>Throughput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getUtilization <em>Utilization</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getResponseTime <em>Response Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getQueueLength <em>Queue Length</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getServiceTime <em>Service Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getInstances <em>Instances</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getClasses <em>Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getMultiServerNode()
 * @model
 * @generated
 */
public interface MultiServerNode extends EObject {
	/**
	 * Returns the value of the '<em><b>Multi Server ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multi Server ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multi Server ID</em>' attribute.
	 * @see #setMultiServerID(String)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getMultiServerNode_MultiServerID()
	 * @model
	 * @generated
	 */
	String getMultiServerID();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getMultiServerID <em>Multi Server ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multi Server ID</em>' attribute.
	 * @see #getMultiServerID()
	 * @generated
	 */
	void setMultiServerID(String value);

	/**
	 * Returns the value of the '<em><b>Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Throughput</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Throughput</em>' attribute.
	 * @see #setThroughput(float)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getMultiServerNode_Throughput()
	 * @model
	 * @generated
	 */
	float getThroughput();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getThroughput <em>Throughput</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Throughput</em>' attribute.
	 * @see #getThroughput()
	 * @generated
	 */
	void setThroughput(float value);

	/**
	 * Returns the value of the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Utilization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Utilization</em>' attribute.
	 * @see #setUtilization(float)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getMultiServerNode_Utilization()
	 * @model
	 * @generated
	 */
	float getUtilization();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getUtilization <em>Utilization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Utilization</em>' attribute.
	 * @see #getUtilization()
	 * @generated
	 */
	void setUtilization(float value);

	/**
	 * Returns the value of the '<em><b>Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Response Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Response Time</em>' attribute.
	 * @see #setResponseTime(float)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getMultiServerNode_ResponseTime()
	 * @model
	 * @generated
	 */
	float getResponseTime();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getResponseTime <em>Response Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Response Time</em>' attribute.
	 * @see #getResponseTime()
	 * @generated
	 */
	void setResponseTime(float value);

	/**
	 * Returns the value of the '<em><b>Queue Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Queue Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Queue Length</em>' attribute.
	 * @see #setQueueLength(float)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getMultiServerNode_QueueLength()
	 * @model
	 * @generated
	 */
	float getQueueLength();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getQueueLength <em>Queue Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Queue Length</em>' attribute.
	 * @see #getQueueLength()
	 * @generated
	 */
	void setQueueLength(float value);

	/**
	 * Returns the value of the '<em><b>Service Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Time</em>' attribute.
	 * @see #setServiceTime(float)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getMultiServerNode_ServiceTime()
	 * @model
	 * @generated
	 */
	float getServiceTime();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.MultiServerNode#getServiceTime <em>Service Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Time</em>' attribute.
	 * @see #getServiceTime()
	 * @generated
	 */
	void setServiceTime(float value);

	/**
	 * Returns the value of the '<em><b>Instances</b></em>' reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.ArchiElemInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instances</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instances</em>' reference list.
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getMultiServerNode_Instances()
	 * @model required="true"
	 * @generated
	 */
	EList<ArchiElemInstance> getInstances();

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
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getMultiServerNode_Classes()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<WorkloadClass> getClasses();

} // MultiServerNode
