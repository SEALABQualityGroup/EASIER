/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interaction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Interaction#getThroughput <em>Throughput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Interaction#getUtilization <em>Utilization</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Interaction#getResponseTime <em>Response Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Interaction#getThroughtputDistr <em>Throughtput Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Interaction#getRespTimeDistr <em>Resp Time Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Interaction#getUtilDistr <em>Util Distr</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.mmaemiliaPackage#getInteraction()
 * @model
 * @generated
 */
public interface Interaction extends EObject {
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
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getInteraction_Throughput()
	 * @model
	 * @generated
	 */
	float getThroughput();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Interaction#getThroughput <em>Throughput</em>}' attribute.
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
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getInteraction_Utilization()
	 * @model
	 * @generated
	 */
	float getUtilization();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Interaction#getUtilization <em>Utilization</em>}' attribute.
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
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getInteraction_ResponseTime()
	 * @model
	 * @generated
	 */
	float getResponseTime();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Interaction#getResponseTime <em>Response Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Response Time</em>' attribute.
	 * @see #getResponseTime()
	 * @generated
	 */
	void setResponseTime(float value);

	/**
	 * Returns the value of the '<em><b>Throughtput Distr</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Throughtput Distr</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Throughtput Distr</em>' attribute list.
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getInteraction_ThroughtputDistr()
	 * @model
	 * @generated
	 */
	EList<String> getThroughtputDistr();

	/**
	 * Returns the value of the '<em><b>Resp Time Distr</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resp Time Distr</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resp Time Distr</em>' attribute list.
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getInteraction_RespTimeDistr()
	 * @model
	 * @generated
	 */
	EList<String> getRespTimeDistr();

	/**
	 * Returns the value of the '<em><b>Util Distr</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Util Distr</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Util Distr</em>' attribute list.
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getInteraction_UtilDistr()
	 * @model
	 * @generated
	 */
	EList<String> getUtilDistr();

} // Interaction
