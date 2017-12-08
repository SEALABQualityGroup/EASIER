/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Architectural Interaction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.ArchitecturalInteraction#getIs_A <em>Is A</em>}</li>
 *   <li>{@link metamodel.mmaemilia.ArchitecturalInteraction#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.ArchitecturalInteraction#getFromInstance <em>From Instance</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.mmaemiliaPackage#getArchitecturalInteraction()
 * @model
 * @generated
 */
public interface ArchitecturalInteraction extends Interaction {
	/**
	 * Returns the value of the '<em><b>Is A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is A</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is A</em>' reference.
	 * @see #setIs_A(LocalInteraction)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getArchitecturalInteraction_Is_A()
	 * @model required="true"
	 * @generated
	 */
	LocalInteraction getIs_A();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.ArchitecturalInteraction#getIs_A <em>Is A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is A</em>' reference.
	 * @see #getIs_A()
	 * @generated
	 */
	void setIs_A(LocalInteraction value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getArchitecturalInteraction_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.ArchitecturalInteraction#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>From Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Instance</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Instance</em>' reference.
	 * @see #setFromInstance(ArchiElemInstance)
	 * @see metamodel.mmaemilia.mmaemiliaPackage#getArchitecturalInteraction_FromInstance()
	 * @model required="true"
	 * @generated
	 */
	ArchiElemInstance getFromInstance();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.ArchitecturalInteraction#getFromInstance <em>From Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Instance</em>' reference.
	 * @see #getFromInstance()
	 * @generated
	 */
	void setFromInstance(ArchiElemInstance value);

} // ArchitecturalInteraction
