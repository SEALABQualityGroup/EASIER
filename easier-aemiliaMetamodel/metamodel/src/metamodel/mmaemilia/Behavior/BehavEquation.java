/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior;

import metamodel.mmaemilia.Headers.BehavHeader;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behav Equation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.BehavEquation#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.BehavEquation#getBHeader <em>BHeader</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.BehavEquation#getPt <em>Pt</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getBehavEquation()
 * @model
 * @generated
 */
public interface BehavEquation extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getBehavEquation_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.BehavEquation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>BHeader</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>BHeader</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>BHeader</em>' containment reference.
	 * @see #setBHeader(BehavHeader)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getBehavEquation_BHeader()
	 * @model containment="true" required="true"
	 * @generated
	 */
	BehavHeader getBHeader();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.BehavEquation#getBHeader <em>BHeader</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>BHeader</em>' containment reference.
	 * @see #getBHeader()
	 * @generated
	 */
	void setBHeader(BehavHeader value);

	/**
	 * Returns the value of the '<em><b>Pt</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pt</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pt</em>' containment reference.
	 * @see #setPt(ProcessTerm)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getBehavEquation_Pt()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ProcessTerm getPt();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.BehavEquation#getPt <em>Pt</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pt</em>' containment reference.
	 * @see #getPt()
	 * @generated
	 */
	void setPt(ProcessTerm value);

} // BehavEquation
