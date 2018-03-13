/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices;

import metamodel.mmaemilia.ArchitecturalInteraction;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Archi Int Measure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mapmeasurestoindices.ArchiIntMeasure#getIndex <em>Index</em>}</li>
 *   <li>{@link mapmeasurestoindices.ArchiIntMeasure#getArchiInteraction <em>Archi Interaction</em>}</li>
 * </ul>
 * </p>
 *
 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getArchiIntMeasure()
 * @model
 * @generated
 */
public interface ArchiIntMeasure extends EObject {
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
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getArchiIntMeasure_Index()
	 * @model required="true"
	 * @generated
	 */
	IndexType getIndex();

	/**
	 * Sets the value of the '{@link mapmeasurestoindices.ArchiIntMeasure#getIndex <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' attribute.
	 * @see mapmeasurestoindices.IndexType
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(IndexType value);

	/**
	 * Returns the value of the '<em><b>Archi Interaction</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Archi Interaction</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Archi Interaction</em>' reference.
	 * @see #setArchiInteraction(ArchitecturalInteraction)
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getArchiIntMeasure_ArchiInteraction()
	 * @model required="true"
	 * @generated
	 */
	ArchitecturalInteraction getArchiInteraction();

	/**
	 * Sets the value of the '{@link mapmeasurestoindices.ArchiIntMeasure#getArchiInteraction <em>Archi Interaction</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Archi Interaction</em>' reference.
	 * @see #getArchiInteraction()
	 * @generated
	 */
	void setArchiInteraction(ArchitecturalInteraction value);

} // ArchiIntMeasure
