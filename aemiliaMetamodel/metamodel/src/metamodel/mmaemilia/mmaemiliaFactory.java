/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.mmaemiliaPackage
 * @generated
 */
public interface mmaemiliaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	mmaemiliaFactory eINSTANCE = metamodel.mmaemilia.impl.mmaemiliaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>AEmilia Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AEmilia Specification</em>'.
	 * @generated
	 */
	AEmiliaSpecification createAEmiliaSpecification();

	/**
	 * Returns a new object of class '<em>Archi Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Archi Type</em>'.
	 * @generated
	 */
	ArchiType createArchiType();

	/**
	 * Returns a new object of class '<em>Archi Elem Types</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Archi Elem Types</em>'.
	 * @generated
	 */
	ArchiElemTypes createArchiElemTypes();

	/**
	 * Returns a new object of class '<em>Archi Topology</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Archi Topology</em>'.
	 * @generated
	 */
	ArchiTopology createArchiTopology();

	/**
	 * Returns a new object of class '<em>Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interaction</em>'.
	 * @generated
	 */
	Interaction createInteraction();

	/**
	 * Returns a new object of class '<em>Local Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Local Interaction</em>'.
	 * @generated
	 */
	LocalInteraction createLocalInteraction();

	/**
	 * Returns a new object of class '<em>Architectural Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Architectural Interaction</em>'.
	 * @generated
	 */
	ArchitecturalInteraction createArchitecturalInteraction();

	/**
	 * Returns a new object of class '<em>Input Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Input Interaction</em>'.
	 * @generated
	 */
	InputInteraction createInputInteraction();

	/**
	 * Returns a new object of class '<em>Output Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Output Interaction</em>'.
	 * @generated
	 */
	OutputInteraction createOutputInteraction();

	/**
	 * Returns a new object of class '<em>Archi Elem Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Archi Elem Instance</em>'.
	 * @generated
	 */
	ArchiElemInstance createArchiElemInstance();

	/**
	 * Returns a new object of class '<em>Attachment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attachment</em>'.
	 * @generated
	 */
	Attachment createAttachment();

	/**
	 * Returns a new object of class '<em>Elem Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Elem Type</em>'.
	 * @generated
	 */
	ElemType createElemType();

	/**
	 * Returns a new object of class '<em>To</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>To</em>'.
	 * @generated
	 */
	To createTo();

	/**
	 * Returns a new object of class '<em>From</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>From</em>'.
	 * @generated
	 */
	From createFrom();

	/**
	 * Returns a new object of class '<em>Elem</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Elem</em>'.
	 * @generated
	 */
	Elem createElem();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	mmaemiliaPackage getmmaemiliaPackage();

} //mmaemiliaFactory
