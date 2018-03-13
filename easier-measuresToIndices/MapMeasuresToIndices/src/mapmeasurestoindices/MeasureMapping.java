/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices;

import metamodel.mmaemilia.Expressions.Expression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measure Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mapmeasurestoindices.MeasureMapping#getMeasureName <em>Measure Name</em>}</li>
 *   <li>{@link mapmeasurestoindices.MeasureMapping#getSelector <em>Selector</em>}</li>
 *   <li>{@link mapmeasurestoindices.MeasureMapping#getInstances <em>Instances</em>}</li>
 *   <li>{@link mapmeasurestoindices.MeasureMapping#getActions <em>Actions</em>}</li>
 *   <li>{@link mapmeasurestoindices.MeasureMapping#getArchiInteractions <em>Archi Interactions</em>}</li>
 * </ul>
 * </p>
 *
 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getMeasureMapping()
 * @model
 * @generated
 */
public interface MeasureMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Measure Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Measure Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measure Name</em>' attribute.
	 * @see #setMeasureName(String)
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getMeasureMapping_MeasureName()
	 * @model required="true"
	 * @generated
	 */
	String getMeasureName();

	/**
	 * Sets the value of the '{@link mapmeasurestoindices.MeasureMapping#getMeasureName <em>Measure Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Measure Name</em>' attribute.
	 * @see #getMeasureName()
	 * @generated
	 */
	void setMeasureName(String value);

	/**
	 * Returns the value of the '<em><b>Selector</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selector</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selector</em>' containment reference.
	 * @see #setSelector(Expression)
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getMeasureMapping_Selector()
	 * @model containment="true"
	 * @generated
	 */
	Expression getSelector();

	/**
	 * Sets the value of the '{@link mapmeasurestoindices.MeasureMapping#getSelector <em>Selector</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selector</em>' containment reference.
	 * @see #getSelector()
	 * @generated
	 */
	void setSelector(Expression value);

	/**
	 * Returns the value of the '<em><b>Instances</b></em>' containment reference list.
	 * The list contents are of type {@link mapmeasurestoindices.AeiMeasure}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instances</em>' containment reference list.
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getMeasureMapping_Instances()
	 * @model containment="true"
	 * @generated
	 */
	EList<AeiMeasure> getInstances();

	/**
	 * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
	 * The list contents are of type {@link mapmeasurestoindices.ActionMeasure}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actions</em>' containment reference list.
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getMeasureMapping_Actions()
	 * @model containment="true"
	 * @generated
	 */
	EList<ActionMeasure> getActions();

	/**
	 * Returns the value of the '<em><b>Archi Interactions</b></em>' containment reference list.
	 * The list contents are of type {@link mapmeasurestoindices.ArchiIntMeasure}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Archi Interactions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Archi Interactions</em>' containment reference list.
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getMeasureMapping_ArchiInteractions()
	 * @model containment="true"
	 * @generated
	 */
	EList<ArchiIntMeasure> getArchiInteractions();

} // MeasureMapping
