/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.util;

import metamodel.mmaemilia.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.mmaemiliaPackage
 * @generated
 */
public class mmaemiliaAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static mmaemiliaPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public mmaemiliaAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = mmaemiliaPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected mmaemiliaSwitch<Adapter> modelSwitch =
		new mmaemiliaSwitch<Adapter>() {
			@Override
			public Adapter caseAEmiliaSpecification(AEmiliaSpecification object) {
				return createAEmiliaSpecificationAdapter();
			}
			@Override
			public Adapter caseArchiType(ArchiType object) {
				return createArchiTypeAdapter();
			}
			@Override
			public Adapter caseArchiElemTypes(ArchiElemTypes object) {
				return createArchiElemTypesAdapter();
			}
			@Override
			public Adapter caseArchiTopology(ArchiTopology object) {
				return createArchiTopologyAdapter();
			}
			@Override
			public Adapter caseInteraction(Interaction object) {
				return createInteractionAdapter();
			}
			@Override
			public Adapter caseLocalInteraction(LocalInteraction object) {
				return createLocalInteractionAdapter();
			}
			@Override
			public Adapter caseArchitecturalInteraction(ArchitecturalInteraction object) {
				return createArchitecturalInteractionAdapter();
			}
			@Override
			public Adapter caseInputInteraction(InputInteraction object) {
				return createInputInteractionAdapter();
			}
			@Override
			public Adapter caseOutputInteraction(OutputInteraction object) {
				return createOutputInteractionAdapter();
			}
			@Override
			public Adapter caseArchiElemInstance(ArchiElemInstance object) {
				return createArchiElemInstanceAdapter();
			}
			@Override
			public Adapter caseAttachment(Attachment object) {
				return createAttachmentAdapter();
			}
			@Override
			public Adapter caseElemType(ElemType object) {
				return createElemTypeAdapter();
			}
			@Override
			public Adapter caseTo(To object) {
				return createToAdapter();
			}
			@Override
			public Adapter caseFrom(From object) {
				return createFromAdapter();
			}
			@Override
			public Adapter caseElem(Elem object) {
				return createElemAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.AEmiliaSpecification <em>AEmilia Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.AEmiliaSpecification
	 * @generated
	 */
	public Adapter createAEmiliaSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.ArchiType <em>Archi Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.ArchiType
	 * @generated
	 */
	public Adapter createArchiTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.ArchiElemTypes <em>Archi Elem Types</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.ArchiElemTypes
	 * @generated
	 */
	public Adapter createArchiElemTypesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.ArchiTopology <em>Archi Topology</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.ArchiTopology
	 * @generated
	 */
	public Adapter createArchiTopologyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Interaction <em>Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Interaction
	 * @generated
	 */
	public Adapter createInteractionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.LocalInteraction <em>Local Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.LocalInteraction
	 * @generated
	 */
	public Adapter createLocalInteractionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.ArchitecturalInteraction <em>Architectural Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.ArchitecturalInteraction
	 * @generated
	 */
	public Adapter createArchitecturalInteractionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.InputInteraction <em>Input Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.InputInteraction
	 * @generated
	 */
	public Adapter createInputInteractionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.OutputInteraction <em>Output Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.OutputInteraction
	 * @generated
	 */
	public Adapter createOutputInteractionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.ArchiElemInstance <em>Archi Elem Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.ArchiElemInstance
	 * @generated
	 */
	public Adapter createArchiElemInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Attachment <em>Attachment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Attachment
	 * @generated
	 */
	public Adapter createAttachmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.ElemType <em>Elem Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.ElemType
	 * @generated
	 */
	public Adapter createElemTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.To <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.To
	 * @generated
	 */
	public Adapter createToAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.From <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.From
	 * @generated
	 */
	public Adapter createFromAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link metamodel.mmaemilia.Elem <em>Elem</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see metamodel.mmaemilia.Elem
	 * @generated
	 */
	public Adapter createElemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //mmaemiliaAdapterFactory
