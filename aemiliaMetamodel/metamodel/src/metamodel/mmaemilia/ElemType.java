/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia;

import metamodel.mmaemilia.Behavior.Behavior;

import metamodel.mmaemilia.Headers.ET_Header;

import metamodel.mmaemilia.QueueingNetwork.WorkloadClass;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Elem Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.ElemType#getIiDecl <em>Ii Decl</em>}</li>
 *   <li>{@link metamodel.mmaemilia.ElemType#getOiDecl <em>Oi Decl</em>}</li>
 *   <li>{@link metamodel.mmaemilia.ElemType#getEtName <em>Et Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.ElemType#getElemHeader <em>Elem Header</em>}</li>
 *   <li>{@link metamodel.mmaemilia.ElemType#getBehaviorDecl <em>Behavior Decl</em>}</li>
 *   <li>{@link metamodel.mmaemilia.ElemType#getClasses <em>Classes</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.mmaemiliaPackage#getElemType()
 * @model
 * @generated
 */
public interface ElemType extends EObject {
	/**
	 * Returns the value of the '<em><b>Ii Decl</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.InputInteraction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ii Decl</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ii Decl</em>' containment reference list.
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getElemType_IiDecl()
	 * @model containment="true"
	 * @generated
	 */
	EList<InputInteraction> getIiDecl();

	/**
	 * Returns the value of the '<em><b>Oi Decl</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.OutputInteraction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Oi Decl</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Oi Decl</em>' containment reference list.
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getElemType_OiDecl()
	 * @model containment="true"
	 * @generated
	 */
	EList<OutputInteraction> getOiDecl();

	/**
	 * Returns the value of the '<em><b>Et Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Et Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Et Name</em>' attribute.
	 * @see #setEtName(String)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getElemType_EtName()
	 * @model required="true"
	 * @generated
	 */
	String getEtName();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.ElemType#getEtName <em>Et Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Et Name</em>' attribute.
	 * @see #getEtName()
	 * @generated
	 */
	void setEtName(String value);

	/**
	 * Returns the value of the '<em><b>Elem Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elem Header</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elem Header</em>' containment reference.
	 * @see #setElemHeader(ET_Header)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getElemType_ElemHeader()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ET_Header getElemHeader();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.ElemType#getElemHeader <em>Elem Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elem Header</em>' containment reference.
	 * @see #getElemHeader()
	 * @generated
	 */
	void setElemHeader(ET_Header value);

	/**
	 * Returns the value of the '<em><b>Behavior Decl</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behavior Decl</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior Decl</em>' containment reference.
	 * @see #setBehaviorDecl(Behavior)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getElemType_BehaviorDecl()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Behavior getBehaviorDecl();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.ElemType#getBehaviorDecl <em>Behavior Decl</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior Decl</em>' containment reference.
	 * @see #getBehaviorDecl()
	 * @generated
	 */
	void setBehaviorDecl(Behavior value);

	/**
	 * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.QueueingNetwork.WorkloadClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' containment reference list.
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getElemType_Classes()
	 * @model containment="true"
	 * @generated
	 */
	EList<WorkloadClass> getClasses();

} // ElemType
