/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pseudo random num gen</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getFirstNumGenOp <em>First Num Gen Op</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getSecondNumGenOp <em>Second Num Gen Op</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getThirdNumGenOp <em>Third Num Gen Op</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getPseudo_random_num_gen()
 * @model
 * @generated
 */
public interface Pseudo_random_num_gen extends Expression {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The literals are from the enumeration {@link metamodel.mmaemilia.Expressions.PseudoNumName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.PseudoNumName
	 * @see #setName(PseudoNumName)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getPseudo_random_num_gen_Name()
	 * @model required="true"
	 * @generated
	 */
	PseudoNumName getName();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see metamodel.mmaemilia.Expressions.PseudoNumName
	 * @see #getName()
	 * @generated
	 */
	void setName(PseudoNumName value);

	/**
	 * Returns the value of the '<em><b>First Num Gen Op</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Num Gen Op</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Num Gen Op</em>' containment reference.
	 * @see #setFirstNumGenOp(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getPseudo_random_num_gen_FirstNumGenOp()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getFirstNumGenOp();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getFirstNumGenOp <em>First Num Gen Op</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Num Gen Op</em>' containment reference.
	 * @see #getFirstNumGenOp()
	 * @generated
	 */
	void setFirstNumGenOp(Expression value);

	/**
	 * Returns the value of the '<em><b>Second Num Gen Op</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Second Num Gen Op</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Second Num Gen Op</em>' containment reference.
	 * @see #setSecondNumGenOp(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getPseudo_random_num_gen_SecondNumGenOp()
	 * @model containment="true"
	 * @generated
	 */
	Expression getSecondNumGenOp();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getSecondNumGenOp <em>Second Num Gen Op</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Second Num Gen Op</em>' containment reference.
	 * @see #getSecondNumGenOp()
	 * @generated
	 */
	void setSecondNumGenOp(Expression value);

	/**
	 * Returns the value of the '<em><b>Third Num Gen Op</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Third Num Gen Op</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Third Num Gen Op</em>' containment reference.
	 * @see #setThirdNumGenOp(Expression)
	 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getPseudo_random_num_gen_ThirdNumGenOp()
	 * @model containment="true"
	 * @generated
	 */
	Expression getThirdNumGenOp();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Expressions.Pseudo_random_num_gen#getThirdNumGenOp <em>Third Num Gen Op</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Third Num Gen Op</em>' containment reference.
	 * @see #getThirdNumGenOp()
	 * @generated
	 */
	void setThirdNumGenOp(Expression value);

} // Pseudo_random_num_gen
