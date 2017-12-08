/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia;

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
 * @see metamodel.mmaemilia.mmaemiliaFactory
 * @model kind="package"
 * @generated
 */
public interface mmaemiliaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "mmaemilia";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://mmaemilia.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mmaemilia";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	mmaemiliaPackage eINSTANCE = metamodel.mmaemilia.impl.mmaemiliaPackageImpl.init();

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.AEmiliaSpecificationImpl <em>AEmilia Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.AEmiliaSpecificationImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getAEmiliaSpecification()
	 * @generated
	 */
	int AEMILIA_SPECIFICATION = 0;

	/**
	 * The feature id for the '<em><b>Archi Type Decl</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AEMILIA_SPECIFICATION__ARCHI_TYPE_DECL = 0;

	/**
	 * The number of structural features of the '<em>AEmilia Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AEMILIA_SPECIFICATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.ArchiTypeImpl <em>Archi Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.ArchiTypeImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getArchiType()
	 * @generated
	 */
	int ARCHI_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Aet Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE__AET_DECLARATION = 0;

	/**
	 * The feature id for the '<em><b>At Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE__AT_DECLARATION = 1;

	/**
	 * The feature id for the '<em><b>At Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE__AT_NAME = 2;

	/**
	 * The feature id for the '<em><b>Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE__HEADER = 3;

	/**
	 * The feature id for the '<em><b>Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE__THROUGHPUT = 4;

	/**
	 * The feature id for the '<em><b>Residence Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE__RESIDENCE_TIME = 5;

	/**
	 * The feature id for the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE__UTILIZATION = 6;

	/**
	 * The feature id for the '<em><b>Queue Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE__QUEUE_LENGTH = 7;

	/**
	 * The feature id for the '<em><b>Service Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE__SERVICE_TIME = 8;

	/**
	 * The feature id for the '<em><b>Workloads</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE__WORKLOADS = 9;

	/**
	 * The number of structural features of the '<em>Archi Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TYPE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.ArchiElemTypesImpl <em>Archi Elem Types</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.ArchiElemTypesImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getArchiElemTypes()
	 * @generated
	 */
	int ARCHI_ELEM_TYPES = 2;

	/**
	 * The feature id for the '<em><b>Et Declaration</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_TYPES__ET_DECLARATION = 0;

	/**
	 * The number of structural features of the '<em>Archi Elem Types</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_TYPES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.ArchiTopologyImpl <em>Archi Topology</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.ArchiTopologyImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getArchiTopology()
	 * @generated
	 */
	int ARCHI_TOPOLOGY = 3;

	/**
	 * The feature id for the '<em><b>Aei Decl</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TOPOLOGY__AEI_DECL = 0;

	/**
	 * The feature id for the '<em><b>Att Decl</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TOPOLOGY__ATT_DECL = 1;

	/**
	 * The feature id for the '<em><b>Ai Decl</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TOPOLOGY__AI_DECL = 2;

	/**
	 * The number of structural features of the '<em>Archi Topology</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_TOPOLOGY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.InteractionImpl <em>Interaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.InteractionImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getInteraction()
	 * @generated
	 */
	int INTERACTION = 4;

	/**
	 * The feature id for the '<em><b>Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__THROUGHPUT = 0;

	/**
	 * The feature id for the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__UTILIZATION = 1;

	/**
	 * The feature id for the '<em><b>Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__RESPONSE_TIME = 2;

	/**
	 * The feature id for the '<em><b>Throughtput Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__THROUGHTPUT_DISTR = 3;

	/**
	 * The feature id for the '<em><b>Resp Time Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__RESP_TIME_DISTR = 4;

	/**
	 * The feature id for the '<em><b>Util Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__UTIL_DISTR = 5;

	/**
	 * The number of structural features of the '<em>Interaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.LocalInteractionImpl <em>Local Interaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.LocalInteractionImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getLocalInteraction()
	 * @generated
	 */
	int LOCAL_INTERACTION = 5;

	/**
	 * The feature id for the '<em><b>Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_INTERACTION__THROUGHPUT = INTERACTION__THROUGHPUT;

	/**
	 * The feature id for the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_INTERACTION__UTILIZATION = INTERACTION__UTILIZATION;

	/**
	 * The feature id for the '<em><b>Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_INTERACTION__RESPONSE_TIME = INTERACTION__RESPONSE_TIME;

	/**
	 * The feature id for the '<em><b>Throughtput Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_INTERACTION__THROUGHTPUT_DISTR = INTERACTION__THROUGHTPUT_DISTR;

	/**
	 * The feature id for the '<em><b>Resp Time Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_INTERACTION__RESP_TIME_DISTR = INTERACTION__RESP_TIME_DISTR;

	/**
	 * The feature id for the '<em><b>Util Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_INTERACTION__UTIL_DISTR = INTERACTION__UTIL_DISTR;

	/**
	 * The feature id for the '<em><b>Int Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_INTERACTION__INT_NAME = INTERACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_INTERACTION__TYPE = INTERACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Local Interaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_INTERACTION_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.ArchitecturalInteractionImpl <em>Architectural Interaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.ArchitecturalInteractionImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getArchitecturalInteraction()
	 * @generated
	 */
	int ARCHITECTURAL_INTERACTION = 6;

	/**
	 * The feature id for the '<em><b>Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURAL_INTERACTION__THROUGHPUT = INTERACTION__THROUGHPUT;

	/**
	 * The feature id for the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURAL_INTERACTION__UTILIZATION = INTERACTION__UTILIZATION;

	/**
	 * The feature id for the '<em><b>Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURAL_INTERACTION__RESPONSE_TIME = INTERACTION__RESPONSE_TIME;

	/**
	 * The feature id for the '<em><b>Throughtput Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURAL_INTERACTION__THROUGHTPUT_DISTR = INTERACTION__THROUGHTPUT_DISTR;

	/**
	 * The feature id for the '<em><b>Resp Time Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURAL_INTERACTION__RESP_TIME_DISTR = INTERACTION__RESP_TIME_DISTR;

	/**
	 * The feature id for the '<em><b>Util Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURAL_INTERACTION__UTIL_DISTR = INTERACTION__UTIL_DISTR;

	/**
	 * The feature id for the '<em><b>Is A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURAL_INTERACTION__IS_A = INTERACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURAL_INTERACTION__NAME = INTERACTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>From Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURAL_INTERACTION__FROM_INSTANCE = INTERACTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Architectural Interaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURAL_INTERACTION_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.InputInteractionImpl <em>Input Interaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.InputInteractionImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getInputInteraction()
	 * @generated
	 */
	int INPUT_INTERACTION = 7;

	/**
	 * The feature id for the '<em><b>Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_INTERACTION__THROUGHPUT = LOCAL_INTERACTION__THROUGHPUT;

	/**
	 * The feature id for the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_INTERACTION__UTILIZATION = LOCAL_INTERACTION__UTILIZATION;

	/**
	 * The feature id for the '<em><b>Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_INTERACTION__RESPONSE_TIME = LOCAL_INTERACTION__RESPONSE_TIME;

	/**
	 * The feature id for the '<em><b>Throughtput Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_INTERACTION__THROUGHTPUT_DISTR = LOCAL_INTERACTION__THROUGHTPUT_DISTR;

	/**
	 * The feature id for the '<em><b>Resp Time Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_INTERACTION__RESP_TIME_DISTR = LOCAL_INTERACTION__RESP_TIME_DISTR;

	/**
	 * The feature id for the '<em><b>Util Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_INTERACTION__UTIL_DISTR = LOCAL_INTERACTION__UTIL_DISTR;

	/**
	 * The feature id for the '<em><b>Int Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_INTERACTION__INT_NAME = LOCAL_INTERACTION__INT_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_INTERACTION__TYPE = LOCAL_INTERACTION__TYPE;

	/**
	 * The number of structural features of the '<em>Input Interaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_INTERACTION_FEATURE_COUNT = LOCAL_INTERACTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.OutputInteractionImpl <em>Output Interaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.OutputInteractionImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getOutputInteraction()
	 * @generated
	 */
	int OUTPUT_INTERACTION = 8;

	/**
	 * The feature id for the '<em><b>Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_INTERACTION__THROUGHPUT = LOCAL_INTERACTION__THROUGHPUT;

	/**
	 * The feature id for the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_INTERACTION__UTILIZATION = LOCAL_INTERACTION__UTILIZATION;

	/**
	 * The feature id for the '<em><b>Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_INTERACTION__RESPONSE_TIME = LOCAL_INTERACTION__RESPONSE_TIME;

	/**
	 * The feature id for the '<em><b>Throughtput Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_INTERACTION__THROUGHTPUT_DISTR = LOCAL_INTERACTION__THROUGHTPUT_DISTR;

	/**
	 * The feature id for the '<em><b>Resp Time Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_INTERACTION__RESP_TIME_DISTR = LOCAL_INTERACTION__RESP_TIME_DISTR;

	/**
	 * The feature id for the '<em><b>Util Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_INTERACTION__UTIL_DISTR = LOCAL_INTERACTION__UTIL_DISTR;

	/**
	 * The feature id for the '<em><b>Int Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_INTERACTION__INT_NAME = LOCAL_INTERACTION__INT_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_INTERACTION__TYPE = LOCAL_INTERACTION__TYPE;

	/**
	 * The number of structural features of the '<em>Output Interaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_INTERACTION_FEATURE_COUNT = LOCAL_INTERACTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl <em>Archi Elem Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.ArchiElemInstanceImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getArchiElemInstance()
	 * @generated
	 */
	int ARCHI_ELEM_INSTANCE = 9;

	/**
	 * The feature id for the '<em><b>Instance Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__INSTANCE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Actual Param</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__ACTUAL_PARAM = 1;

	/**
	 * The feature id for the '<em><b>Type Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__TYPE_OF = 2;

	/**
	 * The feature id for the '<em><b>Throughput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__THROUGHPUT = 3;

	/**
	 * The feature id for the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__UTILIZATION = 4;

	/**
	 * The feature id for the '<em><b>Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__RESPONSE_TIME = 5;

	/**
	 * The feature id for the '<em><b>Instance Util Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__INSTANCE_UTIL_DISTR = 6;

	/**
	 * The feature id for the '<em><b>Instance Th Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__INSTANCE_TH_DISTR = 7;

	/**
	 * The feature id for the '<em><b>Instance Res Time Distr</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__INSTANCE_RES_TIME_DISTR = 8;

	/**
	 * The feature id for the '<em><b>Queue Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__QUEUE_LENGTH = 9;

	/**
	 * The feature id for the '<em><b>Service Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__SERVICE_TIME = 10;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__CLASSES = 11;

	/**
	 * The feature id for the '<em><b>Elem</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__ELEM = 12;

	/**
	 * The feature id for the '<em><b>Selector</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE__SELECTOR = 13;

	/**
	 * The number of structural features of the '<em>Archi Elem Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHI_ELEM_INSTANCE_FEATURE_COUNT = 14;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.AttachmentImpl <em>Attachment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.AttachmentImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getAttachment()
	 * @generated
	 */
	int ATTACHMENT = 10;

	/**
	 * The feature id for the '<em><b>End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__END = 0;

	/**
	 * The feature id for the '<em><b>Start</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__START = 1;

	/**
	 * The number of structural features of the '<em>Attachment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.ElemTypeImpl <em>Elem Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.ElemTypeImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getElemType()
	 * @generated
	 */
	int ELEM_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Ii Decl</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEM_TYPE__II_DECL = 0;

	/**
	 * The feature id for the '<em><b>Oi Decl</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEM_TYPE__OI_DECL = 1;

	/**
	 * The feature id for the '<em><b>Et Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEM_TYPE__ET_NAME = 2;

	/**
	 * The feature id for the '<em><b>Elem Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEM_TYPE__ELEM_HEADER = 3;

	/**
	 * The feature id for the '<em><b>Behavior Decl</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEM_TYPE__BEHAVIOR_DECL = 4;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEM_TYPE__CLASSES = 5;

	/**
	 * The number of structural features of the '<em>Elem Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEM_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.ToImpl <em>To</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.ToImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getTo()
	 * @generated
	 */
	int TO = 12;

	/**
	 * The feature id for the '<em><b>To Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO__TO_INSTANCE = 0;

	/**
	 * The feature id for the '<em><b>Is Input</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO__IS_INPUT = 1;

	/**
	 * The number of structural features of the '<em>To</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.FromImpl <em>From</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.FromImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getFrom()
	 * @generated
	 */
	int FROM = 13;

	/**
	 * The feature id for the '<em><b>From Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM__FROM_INSTANCE = 0;

	/**
	 * The feature id for the '<em><b>Is Output</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM__IS_OUTPUT = 1;

	/**
	 * The number of structural features of the '<em>From</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.impl.ElemImpl <em>Elem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.impl.ElemImpl
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getElem()
	 * @generated
	 */
	int ELEM = 14;

	/**
	 * The feature id for the '<em><b>Behavior Decl</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEM__BEHAVIOR_DECL = 0;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEM__INSTANCE = 1;

	/**
	 * The number of structural features of the '<em>Elem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEM_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link metamodel.mmaemilia.InteractionType <em>Interaction Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metamodel.mmaemilia.InteractionType
	 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getInteractionType()
	 * @generated
	 */
	int INTERACTION_TYPE = 15;


	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.AEmiliaSpecification <em>AEmilia Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AEmilia Specification</em>'.
	 * @see metamodel.mmaemilia.AEmiliaSpecification
	 * @generated
	 */
	EClass getAEmiliaSpecification();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.AEmiliaSpecification#getArchiTypeDecl <em>Archi Type Decl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Archi Type Decl</em>'.
	 * @see metamodel.mmaemilia.AEmiliaSpecification#getArchiTypeDecl()
	 * @see #getAEmiliaSpecification()
	 * @generated
	 */
	EReference getAEmiliaSpecification_ArchiTypeDecl();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.ArchiType <em>Archi Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Archi Type</em>'.
	 * @see metamodel.mmaemilia.ArchiType
	 * @generated
	 */
	EClass getArchiType();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.ArchiType#getAetDeclaration <em>Aet Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Aet Declaration</em>'.
	 * @see metamodel.mmaemilia.ArchiType#getAetDeclaration()
	 * @see #getArchiType()
	 * @generated
	 */
	EReference getArchiType_AetDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.ArchiType#getAtDeclaration <em>At Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>At Declaration</em>'.
	 * @see metamodel.mmaemilia.ArchiType#getAtDeclaration()
	 * @see #getArchiType()
	 * @generated
	 */
	EReference getArchiType_AtDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiType#getAtName <em>At Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>At Name</em>'.
	 * @see metamodel.mmaemilia.ArchiType#getAtName()
	 * @see #getArchiType()
	 * @generated
	 */
	EAttribute getArchiType_AtName();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.ArchiType#getHeader <em>Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Header</em>'.
	 * @see metamodel.mmaemilia.ArchiType#getHeader()
	 * @see #getArchiType()
	 * @generated
	 */
	EReference getArchiType_Header();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiType#getThroughput <em>Throughput</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Throughput</em>'.
	 * @see metamodel.mmaemilia.ArchiType#getThroughput()
	 * @see #getArchiType()
	 * @generated
	 */
	EAttribute getArchiType_Throughput();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiType#getResidenceTime <em>Residence Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Residence Time</em>'.
	 * @see metamodel.mmaemilia.ArchiType#getResidenceTime()
	 * @see #getArchiType()
	 * @generated
	 */
	EAttribute getArchiType_ResidenceTime();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiType#getUtilization <em>Utilization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Utilization</em>'.
	 * @see metamodel.mmaemilia.ArchiType#getUtilization()
	 * @see #getArchiType()
	 * @generated
	 */
	EAttribute getArchiType_Utilization();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiType#getQueueLength <em>Queue Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Queue Length</em>'.
	 * @see metamodel.mmaemilia.ArchiType#getQueueLength()
	 * @see #getArchiType()
	 * @generated
	 */
	EAttribute getArchiType_QueueLength();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiType#getServiceTime <em>Service Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Time</em>'.
	 * @see metamodel.mmaemilia.ArchiType#getServiceTime()
	 * @see #getArchiType()
	 * @generated
	 */
	EAttribute getArchiType_ServiceTime();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.ArchiType#getWorkloads <em>Workloads</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Workloads</em>'.
	 * @see metamodel.mmaemilia.ArchiType#getWorkloads()
	 * @see #getArchiType()
	 * @generated
	 */
	EReference getArchiType_Workloads();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.ArchiElemTypes <em>Archi Elem Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Archi Elem Types</em>'.
	 * @see metamodel.mmaemilia.ArchiElemTypes
	 * @generated
	 */
	EClass getArchiElemTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.ArchiElemTypes#getEtDeclaration <em>Et Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Et Declaration</em>'.
	 * @see metamodel.mmaemilia.ArchiElemTypes#getEtDeclaration()
	 * @see #getArchiElemTypes()
	 * @generated
	 */
	EReference getArchiElemTypes_EtDeclaration();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.ArchiTopology <em>Archi Topology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Archi Topology</em>'.
	 * @see metamodel.mmaemilia.ArchiTopology
	 * @generated
	 */
	EClass getArchiTopology();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.ArchiTopology#getAeiDecl <em>Aei Decl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Aei Decl</em>'.
	 * @see metamodel.mmaemilia.ArchiTopology#getAeiDecl()
	 * @see #getArchiTopology()
	 * @generated
	 */
	EReference getArchiTopology_AeiDecl();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.ArchiTopology#getAttDecl <em>Att Decl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Att Decl</em>'.
	 * @see metamodel.mmaemilia.ArchiTopology#getAttDecl()
	 * @see #getArchiTopology()
	 * @generated
	 */
	EReference getArchiTopology_AttDecl();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.ArchiTopology#getAiDecl <em>Ai Decl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ai Decl</em>'.
	 * @see metamodel.mmaemilia.ArchiTopology#getAiDecl()
	 * @see #getArchiTopology()
	 * @generated
	 */
	EReference getArchiTopology_AiDecl();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Interaction <em>Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interaction</em>'.
	 * @see metamodel.mmaemilia.Interaction
	 * @generated
	 */
	EClass getInteraction();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Interaction#getThroughput <em>Throughput</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Throughput</em>'.
	 * @see metamodel.mmaemilia.Interaction#getThroughput()
	 * @see #getInteraction()
	 * @generated
	 */
	EAttribute getInteraction_Throughput();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Interaction#getUtilization <em>Utilization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Utilization</em>'.
	 * @see metamodel.mmaemilia.Interaction#getUtilization()
	 * @see #getInteraction()
	 * @generated
	 */
	EAttribute getInteraction_Utilization();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.Interaction#getResponseTime <em>Response Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Response Time</em>'.
	 * @see metamodel.mmaemilia.Interaction#getResponseTime()
	 * @see #getInteraction()
	 * @generated
	 */
	EAttribute getInteraction_ResponseTime();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.Interaction#getThroughtputDistr <em>Throughtput Distr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Throughtput Distr</em>'.
	 * @see metamodel.mmaemilia.Interaction#getThroughtputDistr()
	 * @see #getInteraction()
	 * @generated
	 */
	EAttribute getInteraction_ThroughtputDistr();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.Interaction#getRespTimeDistr <em>Resp Time Distr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Resp Time Distr</em>'.
	 * @see metamodel.mmaemilia.Interaction#getRespTimeDistr()
	 * @see #getInteraction()
	 * @generated
	 */
	EAttribute getInteraction_RespTimeDistr();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.Interaction#getUtilDistr <em>Util Distr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Util Distr</em>'.
	 * @see metamodel.mmaemilia.Interaction#getUtilDistr()
	 * @see #getInteraction()
	 * @generated
	 */
	EAttribute getInteraction_UtilDistr();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.LocalInteraction <em>Local Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Local Interaction</em>'.
	 * @see metamodel.mmaemilia.LocalInteraction
	 * @generated
	 */
	EClass getLocalInteraction();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.LocalInteraction#getIntName <em>Int Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Int Name</em>'.
	 * @see metamodel.mmaemilia.LocalInteraction#getIntName()
	 * @see #getLocalInteraction()
	 * @generated
	 */
	EAttribute getLocalInteraction_IntName();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.LocalInteraction#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see metamodel.mmaemilia.LocalInteraction#getType()
	 * @see #getLocalInteraction()
	 * @generated
	 */
	EAttribute getLocalInteraction_Type();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.ArchitecturalInteraction <em>Architectural Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Architectural Interaction</em>'.
	 * @see metamodel.mmaemilia.ArchitecturalInteraction
	 * @generated
	 */
	EClass getArchitecturalInteraction();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.ArchitecturalInteraction#getIs_A <em>Is A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Is A</em>'.
	 * @see metamodel.mmaemilia.ArchitecturalInteraction#getIs_A()
	 * @see #getArchitecturalInteraction()
	 * @generated
	 */
	EReference getArchitecturalInteraction_Is_A();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchitecturalInteraction#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see metamodel.mmaemilia.ArchitecturalInteraction#getName()
	 * @see #getArchitecturalInteraction()
	 * @generated
	 */
	EAttribute getArchitecturalInteraction_Name();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.ArchitecturalInteraction#getFromInstance <em>From Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From Instance</em>'.
	 * @see metamodel.mmaemilia.ArchitecturalInteraction#getFromInstance()
	 * @see #getArchitecturalInteraction()
	 * @generated
	 */
	EReference getArchitecturalInteraction_FromInstance();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.InputInteraction <em>Input Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Interaction</em>'.
	 * @see metamodel.mmaemilia.InputInteraction
	 * @generated
	 */
	EClass getInputInteraction();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.OutputInteraction <em>Output Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Interaction</em>'.
	 * @see metamodel.mmaemilia.OutputInteraction
	 * @generated
	 */
	EClass getOutputInteraction();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.ArchiElemInstance <em>Archi Elem Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Archi Elem Instance</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance
	 * @generated
	 */
	EClass getArchiElemInstance();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiElemInstance#getInstanceName <em>Instance Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instance Name</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getInstanceName()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EAttribute getArchiElemInstance_InstanceName();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.ArchiElemInstance#getActualParam <em>Actual Param</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Actual Param</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getActualParam()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EAttribute getArchiElemInstance_ActualParam();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.ArchiElemInstance#getTypeOf <em>Type Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type Of</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getTypeOf()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EReference getArchiElemInstance_TypeOf();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiElemInstance#getThroughput <em>Throughput</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Throughput</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getThroughput()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EAttribute getArchiElemInstance_Throughput();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiElemInstance#getUtilization <em>Utilization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Utilization</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getUtilization()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EAttribute getArchiElemInstance_Utilization();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiElemInstance#getResponseTime <em>Response Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Response Time</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getResponseTime()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EAttribute getArchiElemInstance_ResponseTime();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.ArchiElemInstance#getInstanceUtilDistr <em>Instance Util Distr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Instance Util Distr</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getInstanceUtilDistr()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EAttribute getArchiElemInstance_InstanceUtilDistr();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.ArchiElemInstance#getInstanceThDistr <em>Instance Th Distr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Instance Th Distr</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getInstanceThDistr()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EAttribute getArchiElemInstance_InstanceThDistr();

	/**
	 * Returns the meta object for the attribute list '{@link metamodel.mmaemilia.ArchiElemInstance#getInstanceResTimeDistr <em>Instance Res Time Distr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Instance Res Time Distr</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getInstanceResTimeDistr()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EAttribute getArchiElemInstance_InstanceResTimeDistr();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiElemInstance#getQueueLength <em>Queue Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Queue Length</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getQueueLength()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EAttribute getArchiElemInstance_QueueLength();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ArchiElemInstance#getServiceTime <em>Service Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Time</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getServiceTime()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EAttribute getArchiElemInstance_ServiceTime();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.ArchiElemInstance#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getClasses()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EReference getArchiElemInstance_Classes();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.ArchiElemInstance#getElem <em>Elem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Elem</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getElem()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EReference getArchiElemInstance_Elem();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.ArchiElemInstance#getSelector <em>Selector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Selector</em>'.
	 * @see metamodel.mmaemilia.ArchiElemInstance#getSelector()
	 * @see #getArchiElemInstance()
	 * @generated
	 */
	EReference getArchiElemInstance_Selector();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Attachment <em>Attachment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attachment</em>'.
	 * @see metamodel.mmaemilia.Attachment
	 * @generated
	 */
	EClass getAttachment();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Attachment#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>End</em>'.
	 * @see metamodel.mmaemilia.Attachment#getEnd()
	 * @see #getAttachment()
	 * @generated
	 */
	EReference getAttachment_End();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Attachment#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start</em>'.
	 * @see metamodel.mmaemilia.Attachment#getStart()
	 * @see #getAttachment()
	 * @generated
	 */
	EReference getAttachment_Start();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.ElemType <em>Elem Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Elem Type</em>'.
	 * @see metamodel.mmaemilia.ElemType
	 * @generated
	 */
	EClass getElemType();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.ElemType#getIiDecl <em>Ii Decl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ii Decl</em>'.
	 * @see metamodel.mmaemilia.ElemType#getIiDecl()
	 * @see #getElemType()
	 * @generated
	 */
	EReference getElemType_IiDecl();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.ElemType#getOiDecl <em>Oi Decl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Oi Decl</em>'.
	 * @see metamodel.mmaemilia.ElemType#getOiDecl()
	 * @see #getElemType()
	 * @generated
	 */
	EReference getElemType_OiDecl();

	/**
	 * Returns the meta object for the attribute '{@link metamodel.mmaemilia.ElemType#getEtName <em>Et Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Et Name</em>'.
	 * @see metamodel.mmaemilia.ElemType#getEtName()
	 * @see #getElemType()
	 * @generated
	 */
	EAttribute getElemType_EtName();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.ElemType#getElemHeader <em>Elem Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Elem Header</em>'.
	 * @see metamodel.mmaemilia.ElemType#getElemHeader()
	 * @see #getElemType()
	 * @generated
	 */
	EReference getElemType_ElemHeader();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.ElemType#getBehaviorDecl <em>Behavior Decl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behavior Decl</em>'.
	 * @see metamodel.mmaemilia.ElemType#getBehaviorDecl()
	 * @see #getElemType()
	 * @generated
	 */
	EReference getElemType_BehaviorDecl();

	/**
	 * Returns the meta object for the containment reference list '{@link metamodel.mmaemilia.ElemType#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see metamodel.mmaemilia.ElemType#getClasses()
	 * @see #getElemType()
	 * @generated
	 */
	EReference getElemType_Classes();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.To <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>To</em>'.
	 * @see metamodel.mmaemilia.To
	 * @generated
	 */
	EClass getTo();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.To#getToInstance <em>To Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To Instance</em>'.
	 * @see metamodel.mmaemilia.To#getToInstance()
	 * @see #getTo()
	 * @generated
	 */
	EReference getTo_ToInstance();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.To#getIsInput <em>Is Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Is Input</em>'.
	 * @see metamodel.mmaemilia.To#getIsInput()
	 * @see #getTo()
	 * @generated
	 */
	EReference getTo_IsInput();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.From <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>From</em>'.
	 * @see metamodel.mmaemilia.From
	 * @generated
	 */
	EClass getFrom();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.From#getFromInstance <em>From Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From Instance</em>'.
	 * @see metamodel.mmaemilia.From#getFromInstance()
	 * @see #getFrom()
	 * @generated
	 */
	EReference getFrom_FromInstance();

	/**
	 * Returns the meta object for the reference '{@link metamodel.mmaemilia.From#getIsOutput <em>Is Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Is Output</em>'.
	 * @see metamodel.mmaemilia.From#getIsOutput()
	 * @see #getFrom()
	 * @generated
	 */
	EReference getFrom_IsOutput();

	/**
	 * Returns the meta object for class '{@link metamodel.mmaemilia.Elem <em>Elem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Elem</em>'.
	 * @see metamodel.mmaemilia.Elem
	 * @generated
	 */
	EClass getElem();

	/**
	 * Returns the meta object for the containment reference '{@link metamodel.mmaemilia.Elem#getBehaviorDecl <em>Behavior Decl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behavior Decl</em>'.
	 * @see metamodel.mmaemilia.Elem#getBehaviorDecl()
	 * @see #getElem()
	 * @generated
	 */
	EReference getElem_BehaviorDecl();

	/**
	 * Returns the meta object for the container reference '{@link metamodel.mmaemilia.Elem#getInstance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Instance</em>'.
	 * @see metamodel.mmaemilia.Elem#getInstance()
	 * @see #getElem()
	 * @generated
	 */
	EReference getElem_Instance();

	/**
	 * Returns the meta object for enum '{@link metamodel.mmaemilia.InteractionType <em>Interaction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Interaction Type</em>'.
	 * @see metamodel.mmaemilia.InteractionType
	 * @generated
	 */
	EEnum getInteractionType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	mmaemiliaFactory getmmaemiliaFactory();

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
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.AEmiliaSpecificationImpl <em>AEmilia Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.AEmiliaSpecificationImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getAEmiliaSpecification()
		 * @generated
		 */
		EClass AEMILIA_SPECIFICATION = eINSTANCE.getAEmiliaSpecification();

		/**
		 * The meta object literal for the '<em><b>Archi Type Decl</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AEMILIA_SPECIFICATION__ARCHI_TYPE_DECL = eINSTANCE.getAEmiliaSpecification_ArchiTypeDecl();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.ArchiTypeImpl <em>Archi Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.ArchiTypeImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getArchiType()
		 * @generated
		 */
		EClass ARCHI_TYPE = eINSTANCE.getArchiType();

		/**
		 * The meta object literal for the '<em><b>Aet Declaration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_TYPE__AET_DECLARATION = eINSTANCE.getArchiType_AetDeclaration();

		/**
		 * The meta object literal for the '<em><b>At Declaration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_TYPE__AT_DECLARATION = eINSTANCE.getArchiType_AtDeclaration();

		/**
		 * The meta object literal for the '<em><b>At Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_TYPE__AT_NAME = eINSTANCE.getArchiType_AtName();

		/**
		 * The meta object literal for the '<em><b>Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_TYPE__HEADER = eINSTANCE.getArchiType_Header();

		/**
		 * The meta object literal for the '<em><b>Throughput</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_TYPE__THROUGHPUT = eINSTANCE.getArchiType_Throughput();

		/**
		 * The meta object literal for the '<em><b>Residence Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_TYPE__RESIDENCE_TIME = eINSTANCE.getArchiType_ResidenceTime();

		/**
		 * The meta object literal for the '<em><b>Utilization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_TYPE__UTILIZATION = eINSTANCE.getArchiType_Utilization();

		/**
		 * The meta object literal for the '<em><b>Queue Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_TYPE__QUEUE_LENGTH = eINSTANCE.getArchiType_QueueLength();

		/**
		 * The meta object literal for the '<em><b>Service Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_TYPE__SERVICE_TIME = eINSTANCE.getArchiType_ServiceTime();

		/**
		 * The meta object literal for the '<em><b>Workloads</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_TYPE__WORKLOADS = eINSTANCE.getArchiType_Workloads();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.ArchiElemTypesImpl <em>Archi Elem Types</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.ArchiElemTypesImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getArchiElemTypes()
		 * @generated
		 */
		EClass ARCHI_ELEM_TYPES = eINSTANCE.getArchiElemTypes();

		/**
		 * The meta object literal for the '<em><b>Et Declaration</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_ELEM_TYPES__ET_DECLARATION = eINSTANCE.getArchiElemTypes_EtDeclaration();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.ArchiTopologyImpl <em>Archi Topology</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.ArchiTopologyImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getArchiTopology()
		 * @generated
		 */
		EClass ARCHI_TOPOLOGY = eINSTANCE.getArchiTopology();

		/**
		 * The meta object literal for the '<em><b>Aei Decl</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_TOPOLOGY__AEI_DECL = eINSTANCE.getArchiTopology_AeiDecl();

		/**
		 * The meta object literal for the '<em><b>Att Decl</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_TOPOLOGY__ATT_DECL = eINSTANCE.getArchiTopology_AttDecl();

		/**
		 * The meta object literal for the '<em><b>Ai Decl</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_TOPOLOGY__AI_DECL = eINSTANCE.getArchiTopology_AiDecl();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.InteractionImpl <em>Interaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.InteractionImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getInteraction()
		 * @generated
		 */
		EClass INTERACTION = eINSTANCE.getInteraction();

		/**
		 * The meta object literal for the '<em><b>Throughput</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION__THROUGHPUT = eINSTANCE.getInteraction_Throughput();

		/**
		 * The meta object literal for the '<em><b>Utilization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION__UTILIZATION = eINSTANCE.getInteraction_Utilization();

		/**
		 * The meta object literal for the '<em><b>Response Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION__RESPONSE_TIME = eINSTANCE.getInteraction_ResponseTime();

		/**
		 * The meta object literal for the '<em><b>Throughtput Distr</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION__THROUGHTPUT_DISTR = eINSTANCE.getInteraction_ThroughtputDistr();

		/**
		 * The meta object literal for the '<em><b>Resp Time Distr</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION__RESP_TIME_DISTR = eINSTANCE.getInteraction_RespTimeDistr();

		/**
		 * The meta object literal for the '<em><b>Util Distr</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION__UTIL_DISTR = eINSTANCE.getInteraction_UtilDistr();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.LocalInteractionImpl <em>Local Interaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.LocalInteractionImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getLocalInteraction()
		 * @generated
		 */
		EClass LOCAL_INTERACTION = eINSTANCE.getLocalInteraction();

		/**
		 * The meta object literal for the '<em><b>Int Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_INTERACTION__INT_NAME = eINSTANCE.getLocalInteraction_IntName();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_INTERACTION__TYPE = eINSTANCE.getLocalInteraction_Type();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.ArchitecturalInteractionImpl <em>Architectural Interaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.ArchitecturalInteractionImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getArchitecturalInteraction()
		 * @generated
		 */
		EClass ARCHITECTURAL_INTERACTION = eINSTANCE.getArchitecturalInteraction();

		/**
		 * The meta object literal for the '<em><b>Is A</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURAL_INTERACTION__IS_A = eINSTANCE.getArchitecturalInteraction_Is_A();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHITECTURAL_INTERACTION__NAME = eINSTANCE.getArchitecturalInteraction_Name();

		/**
		 * The meta object literal for the '<em><b>From Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURAL_INTERACTION__FROM_INSTANCE = eINSTANCE.getArchitecturalInteraction_FromInstance();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.InputInteractionImpl <em>Input Interaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.InputInteractionImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getInputInteraction()
		 * @generated
		 */
		EClass INPUT_INTERACTION = eINSTANCE.getInputInteraction();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.OutputInteractionImpl <em>Output Interaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.OutputInteractionImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getOutputInteraction()
		 * @generated
		 */
		EClass OUTPUT_INTERACTION = eINSTANCE.getOutputInteraction();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.ArchiElemInstanceImpl <em>Archi Elem Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.ArchiElemInstanceImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getArchiElemInstance()
		 * @generated
		 */
		EClass ARCHI_ELEM_INSTANCE = eINSTANCE.getArchiElemInstance();

		/**
		 * The meta object literal for the '<em><b>Instance Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_ELEM_INSTANCE__INSTANCE_NAME = eINSTANCE.getArchiElemInstance_InstanceName();

		/**
		 * The meta object literal for the '<em><b>Actual Param</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_ELEM_INSTANCE__ACTUAL_PARAM = eINSTANCE.getArchiElemInstance_ActualParam();

		/**
		 * The meta object literal for the '<em><b>Type Of</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_ELEM_INSTANCE__TYPE_OF = eINSTANCE.getArchiElemInstance_TypeOf();

		/**
		 * The meta object literal for the '<em><b>Throughput</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_ELEM_INSTANCE__THROUGHPUT = eINSTANCE.getArchiElemInstance_Throughput();

		/**
		 * The meta object literal for the '<em><b>Utilization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_ELEM_INSTANCE__UTILIZATION = eINSTANCE.getArchiElemInstance_Utilization();

		/**
		 * The meta object literal for the '<em><b>Response Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_ELEM_INSTANCE__RESPONSE_TIME = eINSTANCE.getArchiElemInstance_ResponseTime();

		/**
		 * The meta object literal for the '<em><b>Instance Util Distr</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_ELEM_INSTANCE__INSTANCE_UTIL_DISTR = eINSTANCE.getArchiElemInstance_InstanceUtilDistr();

		/**
		 * The meta object literal for the '<em><b>Instance Th Distr</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_ELEM_INSTANCE__INSTANCE_TH_DISTR = eINSTANCE.getArchiElemInstance_InstanceThDistr();

		/**
		 * The meta object literal for the '<em><b>Instance Res Time Distr</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_ELEM_INSTANCE__INSTANCE_RES_TIME_DISTR = eINSTANCE.getArchiElemInstance_InstanceResTimeDistr();

		/**
		 * The meta object literal for the '<em><b>Queue Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_ELEM_INSTANCE__QUEUE_LENGTH = eINSTANCE.getArchiElemInstance_QueueLength();

		/**
		 * The meta object literal for the '<em><b>Service Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHI_ELEM_INSTANCE__SERVICE_TIME = eINSTANCE.getArchiElemInstance_ServiceTime();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_ELEM_INSTANCE__CLASSES = eINSTANCE.getArchiElemInstance_Classes();

		/**
		 * The meta object literal for the '<em><b>Elem</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_ELEM_INSTANCE__ELEM = eINSTANCE.getArchiElemInstance_Elem();

		/**
		 * The meta object literal for the '<em><b>Selector</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHI_ELEM_INSTANCE__SELECTOR = eINSTANCE.getArchiElemInstance_Selector();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.AttachmentImpl <em>Attachment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.AttachmentImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getAttachment()
		 * @generated
		 */
		EClass ATTACHMENT = eINSTANCE.getAttachment();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTACHMENT__END = eINSTANCE.getAttachment_End();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTACHMENT__START = eINSTANCE.getAttachment_Start();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.ElemTypeImpl <em>Elem Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.ElemTypeImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getElemType()
		 * @generated
		 */
		EClass ELEM_TYPE = eINSTANCE.getElemType();

		/**
		 * The meta object literal for the '<em><b>Ii Decl</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEM_TYPE__II_DECL = eINSTANCE.getElemType_IiDecl();

		/**
		 * The meta object literal for the '<em><b>Oi Decl</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEM_TYPE__OI_DECL = eINSTANCE.getElemType_OiDecl();

		/**
		 * The meta object literal for the '<em><b>Et Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEM_TYPE__ET_NAME = eINSTANCE.getElemType_EtName();

		/**
		 * The meta object literal for the '<em><b>Elem Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEM_TYPE__ELEM_HEADER = eINSTANCE.getElemType_ElemHeader();

		/**
		 * The meta object literal for the '<em><b>Behavior Decl</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEM_TYPE__BEHAVIOR_DECL = eINSTANCE.getElemType_BehaviorDecl();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEM_TYPE__CLASSES = eINSTANCE.getElemType_Classes();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.ToImpl <em>To</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.ToImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getTo()
		 * @generated
		 */
		EClass TO = eINSTANCE.getTo();

		/**
		 * The meta object literal for the '<em><b>To Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TO__TO_INSTANCE = eINSTANCE.getTo_ToInstance();

		/**
		 * The meta object literal for the '<em><b>Is Input</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TO__IS_INPUT = eINSTANCE.getTo_IsInput();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.FromImpl <em>From</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.FromImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getFrom()
		 * @generated
		 */
		EClass FROM = eINSTANCE.getFrom();

		/**
		 * The meta object literal for the '<em><b>From Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FROM__FROM_INSTANCE = eINSTANCE.getFrom_FromInstance();

		/**
		 * The meta object literal for the '<em><b>Is Output</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FROM__IS_OUTPUT = eINSTANCE.getFrom_IsOutput();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.impl.ElemImpl <em>Elem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.impl.ElemImpl
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getElem()
		 * @generated
		 */
		EClass ELEM = eINSTANCE.getElem();

		/**
		 * The meta object literal for the '<em><b>Behavior Decl</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEM__BEHAVIOR_DECL = eINSTANCE.getElem_BehaviorDecl();

		/**
		 * The meta object literal for the '<em><b>Instance</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEM__INSTANCE = eINSTANCE.getElem_Instance();

		/**
		 * The meta object literal for the '{@link metamodel.mmaemilia.InteractionType <em>Interaction Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metamodel.mmaemilia.InteractionType
		 * @see metamodel.mmaemilia.impl.mmaemiliaPackageImpl#getInteractionType()
		 * @generated
		 */
		EEnum INTERACTION_TYPE = eINSTANCE.getInteractionType();

	}

} //mmaemiliaPackage
