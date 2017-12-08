/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rew Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link mapmeasurestoindices.RewMapping#getMappings <em>Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getRewMapping()
 * @model
 * @generated
 */
public interface RewMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link mapmeasurestoindices.MeasureMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mappings</em>' containment reference list.
	 * @see mapmeasurestoindices.MapmeasurestoindicesPackage#getRewMapping_Mappings()
	 * @model containment="true"
	 * @generated
	 */
	EList<MeasureMapping> getMappings();

} // RewMapping
