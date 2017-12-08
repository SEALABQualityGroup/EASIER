/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType;

import metamodel.mmaemilia.Headers.Var;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Record</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.DataType.Record#getField_decl_seq <em>Field decl seq</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.DataType.DataTypePackage#getRecord()
 * @model
 * @generated
 */
public interface Record extends Normal {
	/**
	 * Returns the value of the '<em><b>Field decl seq</b></em>' containment reference list.
	 * The list contents are of type {@link metamodel.mmaemilia.Headers.Var}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field decl seq</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field decl seq</em>' containment reference list.
	 * @see metamodel.mmaemilia.DataType.DataTypePackage#getRecord_Field_decl_seq()
	 * @model containment="true"
	 * @generated
	 */
	EList<Var> getField_decl_seq();

} // Record
