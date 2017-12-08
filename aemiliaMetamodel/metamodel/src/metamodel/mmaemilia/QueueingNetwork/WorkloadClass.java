/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.QueueingNetwork;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workload Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getWorkloadID <em>Workload ID</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getThroughput <em>Throughput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getResidenceTime <em>Residence Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getUtilization <em>Utilization</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getQueueLength <em>Queue Length</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getServiceTime <em>Service Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getEReference1 <em>EReference1</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getTimeUnits <em>Time Units</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkloadClass()
 * @model
 * @generated
 */
public interface WorkloadClass extends EObject {
	/**
	 * Returns the value of the '<em><b>Workload ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workload ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workload ID</em>' attribute.
	 * @see #setWorkloadID(String)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkloadClass_WorkloadID()
	 * @model
	 * @generated
	 */
	String getWorkloadID();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getWorkloadID <em>Workload ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workload ID</em>' attribute.
	 * @see #getWorkloadID()
	 * @generated
	 */
	void setWorkloadID(String value);

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
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkloadClass_Throughput()
	 * @model
	 * @generated
	 */
	float getThroughput();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getThroughput <em>Throughput</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Throughput</em>' attribute.
	 * @see #getThroughput()
	 * @generated
	 */
	void setThroughput(float value);

	/**
	 * Returns the value of the '<em><b>Residence Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Residence Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Residence Time</em>' attribute.
	 * @see #setResidenceTime(float)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkloadClass_ResidenceTime()
	 * @model
	 * @generated
	 */
	float getResidenceTime();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getResidenceTime <em>Residence Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Residence Time</em>' attribute.
	 * @see #getResidenceTime()
	 * @generated
	 */
	void setResidenceTime(float value);

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
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkloadClass_Utilization()
	 * @model
	 * @generated
	 */
	float getUtilization();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getUtilization <em>Utilization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Utilization</em>' attribute.
	 * @see #getUtilization()
	 * @generated
	 */
	void setUtilization(float value);

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
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkloadClass_QueueLength()
	 * @model
	 * @generated
	 */
	float getQueueLength();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getQueueLength <em>Queue Length</em>}' attribute.
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
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkloadClass_ServiceTime()
	 * @model
	 * @generated
	 */
	float getServiceTime();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getServiceTime <em>Service Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Time</em>' attribute.
	 * @see #getServiceTime()
	 * @generated
	 */
	void setServiceTime(float value);

	/**
	 * Returns the value of the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference0</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference0</em>' reference.
	 * @see #setEReference0(WorkloadClass)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkloadClass_EReference0()
	 * @model
	 * @generated
	 */
	WorkloadClass getEReference0();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getEReference0 <em>EReference0</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EReference0</em>' reference.
	 * @see #getEReference0()
	 * @generated
	 */
	void setEReference0(WorkloadClass value);

	/**
	 * Returns the value of the '<em><b>EReference1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference1</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference1</em>' reference.
	 * @see #setEReference1(WorkloadClass)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkloadClass_EReference1()
	 * @model
	 * @generated
	 */
	WorkloadClass getEReference1();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getEReference1 <em>EReference1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EReference1</em>' reference.
	 * @see #getEReference1()
	 * @generated
	 */
	void setEReference1(WorkloadClass value);

	/**
	 * Returns the value of the '<em><b>Time Units</b></em>' attribute.
	 * The literals are from the enumeration {@link metamodel.mmaemilia.QueueingNetwork.TimeUnitsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Units</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Units</em>' attribute.
	 * @see metamodel.mmaemilia.QueueingNetwork.TimeUnitsType
	 * @see #setTimeUnits(TimeUnitsType)
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkloadClass_TimeUnits()
	 * @model
	 * @generated
	 */
	TimeUnitsType getTimeUnits();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getTimeUnits <em>Time Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Units</em>' attribute.
	 * @see metamodel.mmaemilia.QueueingNetwork.TimeUnitsType
	 * @see #getTimeUnits()
	 * @generated
	 */
	void setTimeUnits(TimeUnitsType value);

} // WorkloadClass
