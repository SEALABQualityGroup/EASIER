/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType;

import metamodel.mmaemilia.Expressions.Expression;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.DataType.Array#getArrayElemType <em>Array Elem Type</em>}</li>
 *   <li>{@link metamodel.mmaemilia.DataType.Array#getLength <em>Length</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.DataType.DataTypePackage#getArray()
 * @model
 * @generated
 */
public interface Array extends Normal {
	/**
	 * Returns the value of the '<em><b>Array Elem Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Elem Type</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Elem Type</em>' containment reference.
	 * @see #setArrayElemType(Normal)
	 * @see metamodel.mmaemilia.DataType.DataTypePackage#getArray_ArrayElemType()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Normal getArrayElemType();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.DataType.Array#getArrayElemType <em>Array Elem Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Elem Type</em>' containment reference.
	 * @see #getArrayElemType()
	 * @generated
	 */
	void setArrayElemType(Normal value);

	/**
	 * Returns the value of the '<em><b>Length</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' containment reference.
	 * @see #setLength(Expression)
	 * @see metamodel.mmaemilia.DataType.DataTypePackage#getArray_Length()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getLength();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.DataType.Array#getLength <em>Length</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' containment reference.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(Expression value);

} // Array
