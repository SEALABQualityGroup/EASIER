/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices;

import metamodel.mmaemilia.ArchiElemInstance;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Aei Measure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mapmeasurestoindices.AeiMeasure#getIndex <em>Index</em>}</li>
 *   <li>{@link mapmeasurestoindices.AeiMeasure#getAei <em>Aei</em>}</li>
 * </ul>
 * </p>
 *
 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getAeiMeasure()
 * @model
 * @generated
 */
public interface AeiMeasure extends EObject {
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
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getAeiMeasure_Index()
	 * @model required="true"
	 * @generated
	 */
	IndexType getIndex();

	/**
	 * Sets the value of the '{@link mapmeasurestoindices.AeiMeasure#getIndex <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' attribute.
	 * @see mapmeasurestoindices.IndexType
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(IndexType value);

	/**
	 * Returns the value of the '<em><b>Aei</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aei</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aei</em>' reference.
	 * @see #setAei(ArchiElemInstance)
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getAeiMeasure_Aei()
	 * @model required="true"
	 * @generated
	 */
	ArchiElemInstance getAei();

	/**
	 * Sets the value of the '{@link mapmeasurestoindices.AeiMeasure#getAei <em>Aei</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aei</em>' reference.
	 * @see #getAei()
	 * @generated
	 */
	void setAei(ArchiElemInstance value);

} // AeiMeasure
