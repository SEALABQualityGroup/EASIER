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
 * A representation of the model object '<em><b>Workload</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.Workload#getWorkloadID <em>Workload ID</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.Workload#getThroughput <em>Throughput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.Workload#getResidenceTime <em>Residence Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.QueueingNetwork.Workload#getTimeUnits <em>Time Units</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkload()
 * @model
 * @generated
 */
public interface Workload extends EObject {
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
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkload_WorkloadID()
	 * @model
	 * @generated
	 */
	String getWorkloadID();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.Workload#getWorkloadID <em>Workload ID</em>}' attribute.
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
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkload_Throughput()
	 * @model
	 * @generated
	 */
	float getThroughput();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.Workload#getThroughput <em>Throughput</em>}' attribute.
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
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkload_ResidenceTime()
	 * @model
	 * @generated
	 */
	float getResidenceTime();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.Workload#getResidenceTime <em>Residence Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Residence Time</em>' attribute.
	 * @see #getResidenceTime()
	 * @generated
	 */
	void setResidenceTime(float value);

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
	 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getWorkload_TimeUnits()
	 * @model
	 * @generated
	 */
	TimeUnitsType getTimeUnits();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.QueueingNetwork.Workload#getTimeUnits <em>Time Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Units</em>' attribute.
	 * @see metamodel.mmaemilia.QueueingNetwork.TimeUnitsType
	 * @see #getTimeUnits()
	 * @generated
	 */
	void setTimeUnits(TimeUnitsType value);

} // Workload
