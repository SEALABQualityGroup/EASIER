/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.QueueingNetwork;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage
 * @generated
 */
public interface QueueingNetworkFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QueueingNetworkFactory eINSTANCE = metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Workload</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Workload</em>'.
	 * @generated
	 */
	Workload createWorkload();

	/**
	 * Returns a new object of class '<em>Workload Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Workload Class</em>'.
	 * @generated
	 */
	WorkloadClass createWorkloadClass();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	QueueingNetworkPackage getQueueingNetworkPackage();

} //QueueingNetworkFactory
