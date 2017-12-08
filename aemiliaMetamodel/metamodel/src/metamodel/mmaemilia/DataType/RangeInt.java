/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType;

import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Headers.Const;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Range Int</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.DataType.RangeInt#getMaxVal <em>Max Val</em>}</li>
 *   <li>{@link metamodel.mmaemilia.DataType.RangeInt#getMinVal <em>Min Val</em>}</li>
 *   <li>{@link metamodel.mmaemilia.DataType.RangeInt#getMinVar <em>Min Var</em>}</li>
 *   <li>{@link metamodel.mmaemilia.DataType.RangeInt#getMaxVar <em>Max Var</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.DataType.DataTypePackage#getRangeInt()
 * @model
 * @generated
 */
public interface RangeInt extends metamodel.mmaemilia.DataType.Integer {
	/**
	 * Returns the value of the '<em><b>Min Val</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Val</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Val</em>' containment reference.
	 * @see #setMinVal(Expression)
	 * @see metamodel.mmaemilia.DataType.DataTypePackage#getRangeInt_MinVal()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getMinVal();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.DataType.RangeInt#getMinVal <em>Min Val</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Val</em>' containment reference.
	 * @see #getMinVal()
	 * @generated
	 */
	void setMinVal(Expression value);

	/**
	 * Returns the value of the '<em><b>Max Val</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Val</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Val</em>' containment reference.
	 * @see #setMaxVal(Expression)
	 * @see metamodel.mmaemilia.DataType.DataTypePackage#getRangeInt_MaxVal()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getMaxVal();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.DataType.RangeInt#getMaxVal <em>Max Val</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Val</em>' containment reference.
	 * @see #getMaxVal()
	 * @generated
	 */
	void setMaxVal(Expression value);

	/**
	 * Returns the value of the '<em><b>Min Var</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Var</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Var</em>' reference.
	 * @see #setMinVar(Const)
	 * @see metamodel.mmaemilia.DataType.DataTypePackage#getRangeInt_MinVar()
	 * @model
	 * @generated
	 */
	Const getMinVar();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.DataType.RangeInt#getMinVar <em>Min Var</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Var</em>' reference.
	 * @see #getMinVar()
	 * @generated
	 */
	void setMinVar(Const value);

	/**
	 * Returns the value of the '<em><b>Max Var</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Var</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Var</em>' reference.
	 * @see #setMaxVar(Const)
	 * @see metamodel.mmaemilia.DataType.DataTypePackage#getRangeInt_MaxVar()
	 * @model
	 * @generated
	 */
	Const getMaxVar();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.DataType.RangeInt#getMaxVar <em>Max Var</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Var</em>' reference.
	 * @see #getMaxVar()
	 * @generated
	 */
	void setMaxVar(Const value);

} // RangeInt
