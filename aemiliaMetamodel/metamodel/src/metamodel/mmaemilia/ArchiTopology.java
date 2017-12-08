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
 * A representation of the model object '<em><b>Archi Topology</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.ArchiTopology#getAeiDecl <em>Aei Decl</em>}</li>
 *   <li>{@link metamodel.mmaemilia.ArchiTopology#getAttDecl <em>Att Decl</em>}</li>
 *   <li>{@link metamodel.mmaemilia.ArchiTopology#getAiDecl <em>Ai Decl</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.mmaemiliaPackage#getArchiTopology()
 * @model
 * @generated
 */
public interface ArchiTopology extends EObject {
	/**
	 * Returns the value of the '<em><b>Aei Decl</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.ArchiElemInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aei Decl</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aei Decl</em>' containment reference list.
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getArchiTopology_AeiDecl()
	 * @model containment="true"
	 * @generated
	 */
	EList<ArchiElemInstance> getAeiDecl();

	/**
	 * Returns the value of the '<em><b>Att Decl</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Attachment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Att Decl</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Att Decl</em>' containment reference list.
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getArchiTopology_AttDecl()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attachment> getAttDecl();

	/**
	 * Returns the value of the '<em><b>Ai Decl</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.ArchitecturalInteraction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ai Decl</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ai Decl</em>' containment reference list.
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getArchiTopology_AiDecl()
	 * @model containment="true"
	 * @generated
	 */
	EList<ArchitecturalInteraction> getAiDecl();

} // ArchiTopology
