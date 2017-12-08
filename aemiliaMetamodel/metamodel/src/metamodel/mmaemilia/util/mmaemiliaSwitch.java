/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.util;

import java.util.List;

import metamodel.mmaemilia.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.mmaemiliaPackage
 * @generated
 */
public class mmaemiliaSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static mmaemiliaPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public mmaemiliaSwitch() {
		if (modelPackage == null) {
			modelPackage = mmaemiliaPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case mmaemiliaPackage.AEMILIA_SPECIFICATION: {
				AEmiliaSpecification aEmiliaSpecification = (AEmiliaSpecification)theEObject;
				T result = caseAEmiliaSpecification(aEmiliaSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.ARCHI_TYPE: {
				ArchiType archiType = (ArchiType)theEObject;
				T result = caseArchiType(archiType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.ARCHI_ELEM_TYPES: {
				ArchiElemTypes archiElemTypes = (ArchiElemTypes)theEObject;
				T result = caseArchiElemTypes(archiElemTypes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.ARCHI_TOPOLOGY: {
				ArchiTopology archiTopology = (ArchiTopology)theEObject;
				T result = caseArchiTopology(archiTopology);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.INTERACTION: {
				Interaction interaction = (Interaction)theEObject;
				T result = caseInteraction(interaction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.LOCAL_INTERACTION: {
				LocalInteraction localInteraction = (LocalInteraction)theEObject;
				T result = caseLocalInteraction(localInteraction);
				if (result == null) result = caseInteraction(localInteraction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.ARCHITECTURAL_INTERACTION: {
				ArchitecturalInteraction architecturalInteraction = (ArchitecturalInteraction)theEObject;
				T result = caseArchitecturalInteraction(architecturalInteraction);
				if (result == null) result = caseInteraction(architecturalInteraction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.INPUT_INTERACTION: {
				InputInteraction inputInteraction = (InputInteraction)theEObject;
				T result = caseInputInteraction(inputInteraction);
				if (result == null) result = caseLocalInteraction(inputInteraction);
				if (result == null) result = caseInteraction(inputInteraction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.OUTPUT_INTERACTION: {
				OutputInteraction outputInteraction = (OutputInteraction)theEObject;
				T result = caseOutputInteraction(outputInteraction);
				if (result == null) result = caseLocalInteraction(outputInteraction);
				if (result == null) result = caseInteraction(outputInteraction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.ARCHI_ELEM_INSTANCE: {
				ArchiElemInstance archiElemInstance = (ArchiElemInstance)theEObject;
				T result = caseArchiElemInstance(archiElemInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.ATTACHMENT: {
				Attachment attachment = (Attachment)theEObject;
				T result = caseAttachment(attachment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.ELEM_TYPE: {
				ElemType elemType = (ElemType)theEObject;
				T result = caseElemType(elemType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.TO: {
				To to = (To)theEObject;
				T result = caseTo(to);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.FROM: {
				From from = (From)theEObject;
				T result = caseFrom(from);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case mmaemiliaPackage.ELEM: {
				Elem elem = (Elem)theEObject;
				T result = caseElem(elem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AEmilia Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AEmilia Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAEmiliaSpecification(AEmiliaSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Archi Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Archi Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArchiType(ArchiType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Archi Elem Types</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Archi Elem Types</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArchiElemTypes(ArchiElemTypes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Archi Topology</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Archi Topology</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArchiTopology(ArchiTopology object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInteraction(Interaction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Local Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Local Interaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocalInteraction(LocalInteraction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Architectural Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Architectural Interaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArchitecturalInteraction(ArchitecturalInteraction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Interaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputInteraction(InputInteraction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Output Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Output Interaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutputInteraction(OutputInteraction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Archi Elem Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Archi Elem Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArchiElemInstance(ArchiElemInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attachment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attachment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttachment(Attachment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Elem Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Elem Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElemType(ElemType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>To</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>To</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTo(To object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>From</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>From</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFrom(From object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Elem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Elem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElem(Elem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //mmaemiliaSwitch
