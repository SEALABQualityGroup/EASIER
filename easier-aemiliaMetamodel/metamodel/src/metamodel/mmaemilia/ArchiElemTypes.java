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
 * A representation of the model object '<em><b>Archi Elem Types</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.ArchiElemTypes#getEtDeclaration <em>Et Declaration</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.mmaemiliaPackage#getArchiElemTypes()
 * @model
 * @generated
 */
public interface ArchiElemTypes extends EObject {
	/**
	 * Returns the value of the '<em><b>Et Declaration</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.ElemType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Et Declaration</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Et Declaration</em>' containment reference list.
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getArchiElemTypes_EtDeclaration()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<ElemType> getEtDeclaration();

} // ArchiElemTypes
