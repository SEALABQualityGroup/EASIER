/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Special</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.DataType.Special#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.DataType.DataTypePackage#getSpecial()
 * @model
 * @generated
 */
public interface Special extends DataType {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link metamodel.mmaemilia.DataType.SpecialType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see metamodel.mmaemilia.DataType.SpecialType
	 * @see #setType(SpecialType)
	 * @see metamodel.mmaemilia.DataType.DataTypePackage#getSpecial_Type()
	 * @model required="true"
	 * @generated
	 */
	SpecialType getType();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.DataType.Special#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see metamodel.mmaemilia.DataType.SpecialType
	 * @see #getType()
	 * @generated
	 */
	void setType(SpecialType value);

} // Special
