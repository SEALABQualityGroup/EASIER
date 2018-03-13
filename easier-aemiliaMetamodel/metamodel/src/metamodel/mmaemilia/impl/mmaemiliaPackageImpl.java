/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.impl;

import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchiElemTypes;
import metamodel.mmaemilia.ArchiTopology;
import metamodel.mmaemilia.ArchiType;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.Attachment;

import metamodel.mmaemilia.Behavior.BehaviorPackage;

import metamodel.mmaemilia.Behavior.impl.BehaviorPackageImpl;

import metamodel.mmaemilia.DataType.DataTypePackage;

import metamodel.mmaemilia.DataType.impl.DataTypePackageImpl;

import metamodel.mmaemilia.Elem;
import metamodel.mmaemilia.ElemType;

import metamodel.mmaemilia.Expressions.ExpressionsPackage;

import metamodel.mmaemilia.Expressions.impl.ExpressionsPackageImpl;

import metamodel.mmaemilia.From;

import metamodel.mmaemilia.Headers.HeadersPackage;

import metamodel.mmaemilia.Headers.impl.HeadersPackageImpl;

import metamodel.mmaemilia.InputInteraction;
import metamodel.mmaemilia.Interaction;
import metamodel.mmaemilia.InteractionType;
import metamodel.mmaemilia.LocalInteraction;
import metamodel.mmaemilia.OutputInteraction;
import metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage;
import metamodel.mmaemilia.QueueingNetwork.impl.QueueingNetworkPackageImpl;
import metamodel.mmaemilia.To;
import metamodel.mmaemilia.mmaemiliaFactory;
import metamodel.mmaemilia.mmaemiliaPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class mmaemiliaPackageImpl extends EPackageImpl implements mmaemiliaPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aEmiliaSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass archiTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass archiElemTypesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass archiTopologyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interactionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass localInteractionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass architecturalInteractionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputInteractionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outputInteractionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass archiElemInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attachmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elemTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fromEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum interactionTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see metamodel.mmaemilia.mmaemiliaPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private mmaemiliaPackageImpl() {
		super(eNS_URI, mmaemiliaFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link mmaemiliaPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static mmaemiliaPackage init() {
		if (isInited) return (mmaemiliaPackage)EPackage.Registry.INSTANCE.getEPackage(mmaemiliaPackage.eNS_URI);

		// Obtain or create and register package
		mmaemiliaPackageImpl themmaemiliaPackage = (mmaemiliaPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof mmaemiliaPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new mmaemiliaPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		DataTypePackageImpl theDataTypePackage = (DataTypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI) instanceof DataTypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI) : DataTypePackage.eINSTANCE);
		ExpressionsPackageImpl theExpressionsPackage = (ExpressionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) : ExpressionsPackage.eINSTANCE);
		HeadersPackageImpl theHeadersPackage = (HeadersPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI) instanceof HeadersPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI) : HeadersPackage.eINSTANCE);
		BehaviorPackageImpl theBehaviorPackage = (BehaviorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI) instanceof BehaviorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI) : BehaviorPackage.eINSTANCE);
		QueueingNetworkPackageImpl theQueueingNetworkPackage = (QueueingNetworkPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI) instanceof QueueingNetworkPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI) : QueueingNetworkPackage.eINSTANCE);

		// Create package meta-data objects
		themmaemiliaPackage.createPackageContents();
		theDataTypePackage.createPackageContents();
		theExpressionsPackage.createPackageContents();
		theHeadersPackage.createPackageContents();
		theBehaviorPackage.createPackageContents();
		theQueueingNetworkPackage.createPackageContents();

		// Initialize created meta-data
		themmaemiliaPackage.initializePackageContents();
		theDataTypePackage.initializePackageContents();
		theExpressionsPackage.initializePackageContents();
		theHeadersPackage.initializePackageContents();
		theBehaviorPackage.initializePackageContents();
		theQueueingNetworkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		themmaemiliaPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(mmaemiliaPackage.eNS_URI, themmaemiliaPackage);
		return themmaemiliaPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAEmiliaSpecification() {
		return aEmiliaSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAEmiliaSpecification_ArchiTypeDecl() {
		return (EReference)aEmiliaSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchiType() {
		return archiTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiType_AetDeclaration() {
		return (EReference)archiTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiType_AtDeclaration() {
		return (EReference)archiTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiType_AtName() {
		return (EAttribute)archiTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiType_Header() {
		return (EReference)archiTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiType_Throughput() {
		return (EAttribute)archiTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiType_ResidenceTime() {
		return (EAttribute)archiTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiType_Utilization() {
		return (EAttribute)archiTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiType_QueueLength() {
		return (EAttribute)archiTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiType_ServiceTime() {
		return (EAttribute)archiTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiType_Workloads() {
		return (EReference)archiTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchiElemTypes() {
		return archiElemTypesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiElemTypes_EtDeclaration() {
		return (EReference)archiElemTypesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchiTopology() {
		return archiTopologyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiTopology_AeiDecl() {
		return (EReference)archiTopologyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiTopology_AttDecl() {
		return (EReference)archiTopologyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiTopology_AiDecl() {
		return (EReference)archiTopologyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInteraction() {
		return interactionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInteraction_Throughput() {
		return (EAttribute)interactionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInteraction_Utilization() {
		return (EAttribute)interactionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInteraction_ResponseTime() {
		return (EAttribute)interactionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInteraction_ThroughtputDistr() {
		return (EAttribute)interactionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInteraction_RespTimeDistr() {
		return (EAttribute)interactionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInteraction_UtilDistr() {
		return (EAttribute)interactionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocalInteraction() {
		return localInteractionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocalInteraction_IntName() {
		return (EAttribute)localInteractionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocalInteraction_Type() {
		return (EAttribute)localInteractionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchitecturalInteraction() {
		return architecturalInteractionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecturalInteraction_Is_A() {
		return (EReference)architecturalInteractionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchitecturalInteraction_Name() {
		return (EAttribute)architecturalInteractionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecturalInteraction_FromInstance() {
		return (EReference)architecturalInteractionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInputInteraction() {
		return inputInteractionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutputInteraction() {
		return outputInteractionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchiElemInstance() {
		return archiElemInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiElemInstance_InstanceName() {
		return (EAttribute)archiElemInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiElemInstance_ActualParam() {
		return (EAttribute)archiElemInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiElemInstance_TypeOf() {
		return (EReference)archiElemInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiElemInstance_Throughput() {
		return (EAttribute)archiElemInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiElemInstance_Utilization() {
		return (EAttribute)archiElemInstanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiElemInstance_ResponseTime() {
		return (EAttribute)archiElemInstanceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiElemInstance_InstanceUtilDistr() {
		return (EAttribute)archiElemInstanceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiElemInstance_InstanceThDistr() {
		return (EAttribute)archiElemInstanceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiElemInstance_InstanceResTimeDistr() {
		return (EAttribute)archiElemInstanceEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiElemInstance_QueueLength() {
		return (EAttribute)archiElemInstanceEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchiElemInstance_ServiceTime() {
		return (EAttribute)archiElemInstanceEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiElemInstance_Classes() {
		return (EReference)archiElemInstanceEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiElemInstance_Elem() {
		return (EReference)archiElemInstanceEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchiElemInstance_Selector() {
		return (EReference)archiElemInstanceEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttachment() {
		return attachmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttachment_End() {
		return (EReference)attachmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttachment_Start() {
		return (EReference)attachmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElemType() {
		return elemTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElemType_IiDecl() {
		return (EReference)elemTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElemType_OiDecl() {
		return (EReference)elemTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElemType_EtName() {
		return (EAttribute)elemTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElemType_ElemHeader() {
		return (EReference)elemTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElemType_BehaviorDecl() {
		return (EReference)elemTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElemType_Classes() {
		return (EReference)elemTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTo() {
		return toEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTo_ToInstance() {
		return (EReference)toEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTo_IsInput() {
		return (EReference)toEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFrom() {
		return fromEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFrom_FromInstance() {
		return (EReference)fromEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFrom_IsOutput() {
		return (EReference)fromEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElem() {
		return elemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElem_BehaviorDecl() {
		return (EReference)elemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElem_Instance() {
		return (EReference)elemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getInteractionType() {
		return interactionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public mmaemiliaFactory getmmaemiliaFactory() {
		return (mmaemiliaFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		aEmiliaSpecificationEClass = createEClass(AEMILIA_SPECIFICATION);
		createEReference(aEmiliaSpecificationEClass, AEMILIA_SPECIFICATION__ARCHI_TYPE_DECL);

		archiTypeEClass = createEClass(ARCHI_TYPE);
		createEReference(archiTypeEClass, ARCHI_TYPE__AET_DECLARATION);
		createEReference(archiTypeEClass, ARCHI_TYPE__AT_DECLARATION);
		createEAttribute(archiTypeEClass, ARCHI_TYPE__AT_NAME);
		createEReference(archiTypeEClass, ARCHI_TYPE__HEADER);
		createEAttribute(archiTypeEClass, ARCHI_TYPE__THROUGHPUT);
		createEAttribute(archiTypeEClass, ARCHI_TYPE__RESIDENCE_TIME);
		createEAttribute(archiTypeEClass, ARCHI_TYPE__UTILIZATION);
		createEAttribute(archiTypeEClass, ARCHI_TYPE__QUEUE_LENGTH);
		createEAttribute(archiTypeEClass, ARCHI_TYPE__SERVICE_TIME);
		createEReference(archiTypeEClass, ARCHI_TYPE__WORKLOADS);

		archiElemTypesEClass = createEClass(ARCHI_ELEM_TYPES);
		createEReference(archiElemTypesEClass, ARCHI_ELEM_TYPES__ET_DECLARATION);

		archiTopologyEClass = createEClass(ARCHI_TOPOLOGY);
		createEReference(archiTopologyEClass, ARCHI_TOPOLOGY__AEI_DECL);
		createEReference(archiTopologyEClass, ARCHI_TOPOLOGY__ATT_DECL);
		createEReference(archiTopologyEClass, ARCHI_TOPOLOGY__AI_DECL);

		interactionEClass = createEClass(INTERACTION);
		createEAttribute(interactionEClass, INTERACTION__THROUGHPUT);
		createEAttribute(interactionEClass, INTERACTION__UTILIZATION);
		createEAttribute(interactionEClass, INTERACTION__RESPONSE_TIME);
		createEAttribute(interactionEClass, INTERACTION__THROUGHTPUT_DISTR);
		createEAttribute(interactionEClass, INTERACTION__RESP_TIME_DISTR);
		createEAttribute(interactionEClass, INTERACTION__UTIL_DISTR);

		localInteractionEClass = createEClass(LOCAL_INTERACTION);
		createEAttribute(localInteractionEClass, LOCAL_INTERACTION__INT_NAME);
		createEAttribute(localInteractionEClass, LOCAL_INTERACTION__TYPE);

		architecturalInteractionEClass = createEClass(ARCHITECTURAL_INTERACTION);
		createEReference(architecturalInteractionEClass, ARCHITECTURAL_INTERACTION__IS_A);
		createEAttribute(architecturalInteractionEClass, ARCHITECTURAL_INTERACTION__NAME);
		createEReference(architecturalInteractionEClass, ARCHITECTURAL_INTERACTION__FROM_INSTANCE);

		inputInteractionEClass = createEClass(INPUT_INTERACTION);

		outputInteractionEClass = createEClass(OUTPUT_INTERACTION);

		archiElemInstanceEClass = createEClass(ARCHI_ELEM_INSTANCE);
		createEAttribute(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__INSTANCE_NAME);
		createEAttribute(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__ACTUAL_PARAM);
		createEReference(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__TYPE_OF);
		createEAttribute(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__THROUGHPUT);
		createEAttribute(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__UTILIZATION);
		createEAttribute(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__RESPONSE_TIME);
		createEAttribute(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__INSTANCE_UTIL_DISTR);
		createEAttribute(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__INSTANCE_TH_DISTR);
		createEAttribute(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__INSTANCE_RES_TIME_DISTR);
		createEAttribute(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__QUEUE_LENGTH);
		createEAttribute(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__SERVICE_TIME);
		createEReference(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__CLASSES);
		createEReference(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__ELEM);
		createEReference(archiElemInstanceEClass, ARCHI_ELEM_INSTANCE__SELECTOR);

		attachmentEClass = createEClass(ATTACHMENT);
		createEReference(attachmentEClass, ATTACHMENT__END);
		createEReference(attachmentEClass, ATTACHMENT__START);

		elemTypeEClass = createEClass(ELEM_TYPE);
		createEReference(elemTypeEClass, ELEM_TYPE__II_DECL);
		createEReference(elemTypeEClass, ELEM_TYPE__OI_DECL);
		createEAttribute(elemTypeEClass, ELEM_TYPE__ET_NAME);
		createEReference(elemTypeEClass, ELEM_TYPE__ELEM_HEADER);
		createEReference(elemTypeEClass, ELEM_TYPE__BEHAVIOR_DECL);
		createEReference(elemTypeEClass, ELEM_TYPE__CLASSES);

		toEClass = createEClass(TO);
		createEReference(toEClass, TO__TO_INSTANCE);
		createEReference(toEClass, TO__IS_INPUT);

		fromEClass = createEClass(FROM);
		createEReference(fromEClass, FROM__FROM_INSTANCE);
		createEReference(fromEClass, FROM__IS_OUTPUT);

		elemEClass = createEClass(ELEM);
		createEReference(elemEClass, ELEM__BEHAVIOR_DECL);
		createEReference(elemEClass, ELEM__INSTANCE);

		// Create enums
		interactionTypeEEnum = createEEnum(INTERACTION_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		DataTypePackage theDataTypePackage = (DataTypePackage)EPackage.Registry.INSTANCE.getEPackage(DataTypePackage.eNS_URI);
		ExpressionsPackage theExpressionsPackage = (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI);
		HeadersPackage theHeadersPackage = (HeadersPackage)EPackage.Registry.INSTANCE.getEPackage(HeadersPackage.eNS_URI);
		BehaviorPackage theBehaviorPackage = (BehaviorPackage)EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI);
		QueueingNetworkPackage theQueueingNetworkPackage = (QueueingNetworkPackage)EPackage.Registry.INSTANCE.getEPackage(QueueingNetworkPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theDataTypePackage);
		getESubpackages().add(theExpressionsPackage);
		getESubpackages().add(theHeadersPackage);
		getESubpackages().add(theBehaviorPackage);
		getESubpackages().add(theQueueingNetworkPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		localInteractionEClass.getESuperTypes().add(this.getInteraction());
		architecturalInteractionEClass.getESuperTypes().add(this.getInteraction());
		inputInteractionEClass.getESuperTypes().add(this.getLocalInteraction());
		outputInteractionEClass.getESuperTypes().add(this.getLocalInteraction());

		// Initialize classes and features; add operations and parameters
		initEClass(aEmiliaSpecificationEClass, AEmiliaSpecification.class, "AEmiliaSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAEmiliaSpecification_ArchiTypeDecl(), this.getArchiType(), null, "archiTypeDecl", null, 1, 1, AEmiliaSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(archiTypeEClass, ArchiType.class, "ArchiType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArchiType_AetDeclaration(), this.getArchiElemTypes(), null, "aetDeclaration", null, 1, 1, ArchiType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchiType_AtDeclaration(), this.getArchiTopology(), null, "atDeclaration", null, 1, 1, ArchiType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiType_AtName(), ecorePackage.getEString(), "atName", null, 1, 1, ArchiType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchiType_Header(), theHeadersPackage.getAT_Header(), null, "header", null, 1, 1, ArchiType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiType_Throughput(), ecorePackage.getEFloat(), "throughput", null, 0, 1, ArchiType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiType_ResidenceTime(), ecorePackage.getEFloat(), "residenceTime", null, 0, 1, ArchiType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiType_Utilization(), ecorePackage.getEFloat(), "utilization", null, 0, 1, ArchiType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiType_QueueLength(), ecorePackage.getEFloat(), "queueLength", null, 0, 1, ArchiType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiType_ServiceTime(), ecorePackage.getEFloat(), "serviceTime", null, 0, 1, ArchiType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchiType_Workloads(), theQueueingNetworkPackage.getWorkload(), null, "workloads", null, 0, -1, ArchiType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(archiElemTypesEClass, ArchiElemTypes.class, "ArchiElemTypes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArchiElemTypes_EtDeclaration(), this.getElemType(), null, "etDeclaration", null, 1, -1, ArchiElemTypes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(archiTopologyEClass, ArchiTopology.class, "ArchiTopology", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArchiTopology_AeiDecl(), this.getArchiElemInstance(), null, "aeiDecl", null, 0, -1, ArchiTopology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchiTopology_AttDecl(), this.getAttachment(), null, "attDecl", null, 0, -1, ArchiTopology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchiTopology_AiDecl(), this.getArchitecturalInteraction(), null, "aiDecl", null, 0, -1, ArchiTopology.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(interactionEClass, Interaction.class, "Interaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInteraction_Throughput(), ecorePackage.getEFloat(), "throughput", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInteraction_Utilization(), ecorePackage.getEFloat(), "utilization", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInteraction_ResponseTime(), ecorePackage.getEFloat(), "responseTime", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInteraction_ThroughtputDistr(), ecorePackage.getEString(), "throughtputDistr", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInteraction_RespTimeDistr(), ecorePackage.getEString(), "respTimeDistr", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInteraction_UtilDistr(), ecorePackage.getEString(), "utilDistr", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(localInteractionEClass, LocalInteraction.class, "LocalInteraction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLocalInteraction_IntName(), ecorePackage.getEString(), "intName", null, 1, 1, LocalInteraction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocalInteraction_Type(), this.getInteractionType(), "type", null, 1, 1, LocalInteraction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(architecturalInteractionEClass, ArchitecturalInteraction.class, "ArchitecturalInteraction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArchitecturalInteraction_Is_A(), this.getLocalInteraction(), null, "Is_A", null, 1, 1, ArchitecturalInteraction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchitecturalInteraction_Name(), ecorePackage.getEString(), "name", null, 1, 1, ArchitecturalInteraction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecturalInteraction_FromInstance(), this.getArchiElemInstance(), null, "fromInstance", null, 1, 1, ArchitecturalInteraction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputInteractionEClass, InputInteraction.class, "InputInteraction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(outputInteractionEClass, OutputInteraction.class, "OutputInteraction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(archiElemInstanceEClass, ArchiElemInstance.class, "ArchiElemInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArchiElemInstance_InstanceName(), ecorePackage.getEString(), "instanceName", null, 1, 1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiElemInstance_ActualParam(), ecorePackage.getEString(), "actualParam", null, 0, -1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchiElemInstance_TypeOf(), this.getElemType(), null, "TypeOf", null, 1, 1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiElemInstance_Throughput(), ecorePackage.getEFloat(), "throughput", null, 0, 1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiElemInstance_Utilization(), ecorePackage.getEFloat(), "utilization", null, 0, 1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiElemInstance_ResponseTime(), ecorePackage.getEFloat(), "responseTime", null, 0, 1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiElemInstance_InstanceUtilDistr(), ecorePackage.getEString(), "instanceUtilDistr", null, 0, -1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiElemInstance_InstanceThDistr(), ecorePackage.getEString(), "instanceThDistr", null, 0, -1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiElemInstance_InstanceResTimeDistr(), ecorePackage.getEString(), "InstanceResTimeDistr", null, 0, -1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiElemInstance_QueueLength(), ecorePackage.getEFloat(), "queueLength", null, 0, 1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchiElemInstance_ServiceTime(), ecorePackage.getEFloat(), "serviceTime", null, 0, 1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchiElemInstance_Classes(), theQueueingNetworkPackage.getWorkloadClass(), null, "classes", null, 0, -1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchiElemInstance_Elem(), this.getElem(), this.getElem_Instance(), "elem", null, 1, 1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchiElemInstance_Selector(), theExpressionsPackage.getExpression(), null, "selector", null, 0, 1, ArchiElemInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attachmentEClass, Attachment.class, "Attachment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttachment_End(), this.getTo(), null, "end", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttachment_Start(), this.getFrom(), null, "start", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elemTypeEClass, ElemType.class, "ElemType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElemType_IiDecl(), this.getInputInteraction(), null, "iiDecl", null, 0, -1, ElemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElemType_OiDecl(), this.getOutputInteraction(), null, "oiDecl", null, 0, -1, ElemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElemType_EtName(), ecorePackage.getEString(), "etName", null, 1, 1, ElemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElemType_ElemHeader(), theHeadersPackage.getET_Header(), null, "elemHeader", null, 1, 1, ElemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElemType_BehaviorDecl(), theBehaviorPackage.getBehavior(), null, "behaviorDecl", null, 1, 1, ElemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElemType_Classes(), theQueueingNetworkPackage.getWorkloadClass(), null, "classes", null, 0, -1, ElemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(toEClass, To.class, "To", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTo_ToInstance(), this.getArchiElemInstance(), null, "toInstance", null, 1, 1, To.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTo_IsInput(), this.getInputInteraction(), null, "isInput", null, 1, 1, To.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fromEClass, From.class, "From", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFrom_FromInstance(), this.getArchiElemInstance(), null, "fromInstance", null, 1, 1, From.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFrom_IsOutput(), this.getOutputInteraction(), null, "isOutput", null, 1, 1, From.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elemEClass, Elem.class, "Elem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElem_BehaviorDecl(), theBehaviorPackage.getBehavior(), null, "behaviorDecl", null, 1, 1, Elem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElem_Instance(), this.getArchiElemInstance(), this.getArchiElemInstance_Elem(), "instance", null, 1, 1, Elem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(interactionTypeEEnum, InteractionType.class, "InteractionType");
		addEEnumLiteral(interactionTypeEEnum, InteractionType.UNI);
		addEEnumLiteral(interactionTypeEEnum, InteractionType.AND);
		addEEnumLiteral(interactionTypeEEnum, InteractionType.OR);

		// Create resource
		createResource(eNS_URI);
	}

} //mmaemiliaPackageImpl
