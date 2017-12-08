/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices; 

import metamodel.mmaemilia.Behavior.Action;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action Measure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mapmeasurestoindices.ActionMeasure#getIndex <em>Index</em>}</li>
 *   <li>{@link mapmeasurestoindices.ActionMeasure#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getActionMeasure()
 * @model
 * @generated
 */
public interface ActionMeasure extends EObject {
	/**
	 * Returns the value of the '<em><b>Index</b></em>' attribute.
	 * The literals are from the enumeration {@link mapmeasurestoindices.IndexType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' attribute.
	 * @see mapmeasurestoindices.IndexType
	 * @see #setIndex(IndexType)
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getActionMeasure_Index()
	 * @model required="true"
	 * @generated
	 */
	IndexType getIndex();

	/**
	 * Sets the value of the '{@link mapmeasurestoindices.ActionMeasure#getIndex <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' attribute.
	 * @see mapmeasurestoindices.IndexType
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(IndexType value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' reference.
	 * @see #setAction(Action)
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getActionMeasure_Action()
	 * @model required="true"
	 * @generated
	 */
	Action getAction();

	/**
	 * Sets the value of the '{@link mapmeasurestoindices.ActionMeasure#getAction <em>Action</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(Action value);

} // ActionMeasure
