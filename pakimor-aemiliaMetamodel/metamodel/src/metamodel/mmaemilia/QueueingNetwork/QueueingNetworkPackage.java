/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.QueueingNetwork;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkFactory
 * @model kind="package"
 * @generated
 */
public interface QueueingNetworkPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "QueueingNetwork";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://queueingNetwork.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "QueueingNetwork";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QueueingNetworkPackage eINSTANCE = metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl.init();

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadImpl <em>Workload</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.QueueingNetwork.impl.WorkloadImpl
	 * @see metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl#getWorkload()
	 * @generated
	 */
	int WORKLOAD = 0;

	/**
	 * The feature id for the '<em><b>Workload ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD__WORKLOAD_ID = 0;

	/**
	 * The feature id for the '<em><b>Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD__THROUGHPUT = 1;

	/**
	 * The feature id for the '<em><b>Residence Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD__RESIDENCE_TIME = 2;

	/**
	 * The feature id for the '<em><b>Time Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD__TIME_UNITS = 3;

	/**
	 * The number of structural features of the '<em>Workload</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl <em>Workload Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl
	 * @see metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl#getWorkloadClass()
	 * @generated
	 */
	int WORKLOAD_CLASS = 1;

	/**
	 * The feature id for the '<em><b>Workload ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_CLASS__WORKLOAD_ID = 0;

	/**
	 * The feature id for the '<em><b>Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_CLASS__THROUGHPUT = 1;

	/**
	 * The feature id for the '<em><b>Residence Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_CLASS__RESIDENCE_TIME = 2;

	/**
	 * The feature id for the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_CLASS__UTILIZATION = 3;

	/**
	 * The feature id for the '<em><b>Queue Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_CLASS__QUEUE_LENGTH = 4;

	/**
	 * The feature id for the '<em><b>Service Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_CLASS__SERVICE_TIME = 5;

	/**
	 * The feature id for the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_CLASS__EREFERENCE0 = 6;

	/**
	 * The feature id for the '<em><b>EReference1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_CLASS__EREFERENCE1 = 7;

	/**
	 * The feature id for the '<em><b>Time Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_CLASS__TIME_UNITS = 8;

	/**
	 * The number of structural features of the '<em>Workload Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_CLASS_FEATURE_COUNT = 9;


	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.QueueingNetwork.TimeUnitsType <em>Time Units Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.QueueingNetwork.TimeUnitsType
	 * @see metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl#getTimeUnitsType()
	 * @generated
	 */
	int TIME_UNITS_TYPE = 2;


	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.QueueingNetwork.Workload <em>Workload</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workload</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.Workload
	 * @generated
	 */
	EClass getWorkload();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.Workload#getWorkloadID <em>Workload ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Workload ID</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.Workload#getWorkloadID()
	 * @see #getWorkload()
	 * @generated
	 */
	EAttribute getWorkload_WorkloadID();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.Workload#getThroughput <em>Throughput</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Throughput</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.Workload#getThroughput()
	 * @see #getWorkload()
	 * @generated
	 */
	EAttribute getWorkload_Throughput();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.Workload#getResidenceTime <em>Residence Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Residence Time</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.Workload#getResidenceTime()
	 * @see #getWorkload()
	 * @generated
	 */
	EAttribute getWorkload_ResidenceTime();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.Workload#getTimeUnits <em>Time Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Units</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.Workload#getTimeUnits()
	 * @see #getWorkload()
	 * @generated
	 */
	EAttribute getWorkload_TimeUnits();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass <em>Workload Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workload Class</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.WorkloadClass
	 * @generated
	 */
	EClass getWorkloadClass();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getWorkloadID <em>Workload ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Workload ID</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getWorkloadID()
	 * @see #getWorkloadClass()
	 * @generated
	 */
	EAttribute getWorkloadClass_WorkloadID();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getThroughput <em>Throughput</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Throughput</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getThroughput()
	 * @see #getWorkloadClass()
	 * @generated
	 */
	EAttribute getWorkloadClass_Throughput();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getResidenceTime <em>Residence Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Residence Time</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getResidenceTime()
	 * @see #getWorkloadClass()
	 * @generated
	 */
	EAttribute getWorkloadClass_ResidenceTime();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getUtilization <em>Utilization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Utilization</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getUtilization()
	 * @see #getWorkloadClass()
	 * @generated
	 */
	EAttribute getWorkloadClass_Utilization();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getQueueLength <em>Queue Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Queue Length</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getQueueLength()
	 * @see #getWorkloadClass()
	 * @generated
	 */
	EAttribute getWorkloadClass_QueueLength();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getServiceTime <em>Service Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Time</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getServiceTime()
	 * @see #getWorkloadClass()
	 * @generated
	 */
	EAttribute getWorkloadClass_ServiceTime();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getEReference0 <em>EReference0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference0</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getEReference0()
	 * @see #getWorkloadClass()
	 * @generated
	 */
	EReference getWorkloadClass_EReference0();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getEReference1 <em>EReference1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference1</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getEReference1()
	 * @see #getWorkloadClass()
	 * @generated
	 */
	EReference getWorkloadClass_EReference1();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getTimeUnits <em>Time Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Units</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.WorkloadClass#getTimeUnits()
	 * @see #getWorkloadClass()
	 * @generated
	 */
	EAttribute getWorkloadClass_TimeUnits();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.QueueingNetwork.TimeUnitsType <em>Time Units Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Time Units Type</em>'.
	 * @see metamodel.mmaemilia.QueueingNetwork.TimeUnitsType
	 * @generated
	 */
	EEnum getTimeUnitsType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	QueueingNetworkFactory getQueueingNetworkFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadImpl <em>Workload</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.QueueingNetwork.impl.WorkloadImpl
		 * @see metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl#getWorkload()
		 * @generated
		 */
		EClass WORKLOAD = eINSTANCE.getWorkload();

		/**
		 * The meta object literal for the '<em><b>Workload ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD__WORKLOAD_ID = eINSTANCE.getWorkload_WorkloadID();

		/**
		 * The meta object literal for the '<em><b>Throughput</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD__THROUGHPUT = eINSTANCE.getWorkload_Throughput();

		/**
		 * The meta object literal for the '<em><b>Residence Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD__RESIDENCE_TIME = eINSTANCE.getWorkload_ResidenceTime();

		/**
		 * The meta object literal for the '<em><b>Time Units</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD__TIME_UNITS = eINSTANCE.getWorkload_TimeUnits();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl <em>Workload Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.QueueingNetwork.impl.WorkloadClassImpl
		 * @see metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl#getWorkloadClass()
		 * @generated
		 */
		EClass WORKLOAD_CLASS = eINSTANCE.getWorkloadClass();

		/**
		 * The meta object literal for the '<em><b>Workload ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD_CLASS__WORKLOAD_ID = eINSTANCE.getWorkloadClass_WorkloadID();

		/**
		 * The meta object literal for the '<em><b>Throughput</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD_CLASS__THROUGHPUT = eINSTANCE.getWorkloadClass_Throughput();

		/**
		 * The meta object literal for the '<em><b>Residence Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD_CLASS__RESIDENCE_TIME = eINSTANCE.getWorkloadClass_ResidenceTime();

		/**
		 * The meta object literal for the '<em><b>Utilization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD_CLASS__UTILIZATION = eINSTANCE.getWorkloadClass_Utilization();

		/**
		 * The meta object literal for the '<em><b>Queue Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD_CLASS__QUEUE_LENGTH = eINSTANCE.getWorkloadClass_QueueLength();

		/**
		 * The meta object literal for the '<em><b>Service Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD_CLASS__SERVICE_TIME = eINSTANCE.getWorkloadClass_ServiceTime();

		/**
		 * The meta object literal for the '<em><b>EReference0</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKLOAD_CLASS__EREFERENCE0 = eINSTANCE.getWorkloadClass_EReference0();

		/**
		 * The meta object literal for the '<em><b>EReference1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKLOAD_CLASS__EREFERENCE1 = eINSTANCE.getWorkloadClass_EReference1();

		/**
		 * The meta object literal for the '<em><b>Time Units</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKLOAD_CLASS__TIME_UNITS = eINSTANCE.getWorkloadClass_TimeUnits();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.QueueingNetwork.TimeUnitsType <em>Time Units Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.QueueingNetwork.TimeUnitsType
		 * @see metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl#getTimeUnitsType()
		 * @generated
		 */
		EEnum TIME_UNITS_TYPE = eINSTANCE.getTimeUnitsType();

	}

} //QueueingNetworkPackage
