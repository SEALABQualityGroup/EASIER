/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia;

import metamodel.mmaemilia.Behavior.Behavior;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Elem</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Elem#getBehaviorDecl <em>Behavior Decl</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Elem#getInstance <em>Instance</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.mmaemiliaPackage#getElem()
 * @model
 * @generated
 */
public interface Elem extends EObject {
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
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getElem_BehaviorDecl()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Behavior getBehaviorDecl();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Elem#getBehaviorDecl <em>Behavior Decl</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior Decl</em>' containment reference.
	 * @see #getBehaviorDecl()
	 * @generated
	 */
	void setBehaviorDecl(Behavior value);

	/**
	 * Returns the value of the '<em><b>Instance</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link metamodel.mmaemilia.ArchiElemInstance#getElem <em>Elem</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance</em>' container reference.
	 * @see #setInstance(ArchiElemInstance)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getElem_Instance()
	 * @see metamodel.mmaemilia.ArchiElemInstance#getElem
	 * @model opposite="elem" required="true" transient="false"
	 * @generated
	 */
	ArchiElemInstance getInstance();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Elem#getInstance <em>Instance</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instance</em>' container reference.
	 * @see #getInstance()
	 * @generated
	 */
	void setInstance(ArchiElemInstance value);

} // Elem
