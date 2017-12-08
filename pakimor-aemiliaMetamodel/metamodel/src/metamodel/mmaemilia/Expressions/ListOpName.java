/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Expressions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>List Op Name</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getListOpName()
 * @model
 * @generated
 */
public enum ListOpName implements Enumerator {
	/**
	 * The '<em><b>LIST CONS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIST_CONS_VALUE
	 * @generated
	 * @ordered
	 */
	LIST_CONS(0, "LIST_CONS", "list_cons"),

	/**
	 * The '<em><b>FIRST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIRST_VALUE
	 * @generated
	 * @ordered
	 */
	FIRST(1, "FIRST", "first"),

	/**
	 * The '<em><b>TAIL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TAIL_VALUE
	 * @generated
	 * @ordered
	 */
	TAIL(2, "TAIL", "tail"),

	/**
	 * The '<em><b>CONCAT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONCAT_VALUE
	 * @generated
	 * @ordered
	 */
	CONCAT(3, "CONCAT", "concat"),

	/**
	 * The '<em><b>INSERT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INSERT_VALUE
	 * @generated
	 * @ordered
	 */
	INSERT(4, "INSERT", "insert"),

	/**
	 * The '<em><b>REMOVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMOVE_VALUE
	 * @generated
	 * @ordered
	 */
	REMOVE(5, "REMOVE", "remove"),

	/**
	 * The '<em><b>LENGHT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LENGHT_VALUE
	 * @generated
	 * @ordered
	 */
	LENGHT(6, "LENGHT", "lenght");

	/**
	 * The '<em><b>LIST CONS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LIST CONS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LIST_CONS
	 * @model literal="list_cons"
	 * @generated
	 * @ordered
	 */
	public static final int LIST_CONS_VALUE = 0;

	/**
	 * The '<em><b>FIRST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FIRST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FIRST
	 * @model literal="first"
	 * @generated
	 * @ordered
	 */
	public static final int FIRST_VALUE = 1;

	/**
	 * The '<em><b>TAIL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TAIL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TAIL
	 * @model literal="tail"
	 * @generated
	 * @ordered
	 */
	public static final int TAIL_VALUE = 2;

	/**
	 * The '<em><b>CONCAT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONCAT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONCAT
	 * @model literal="concat"
	 * @generated
	 * @ordered
	 */
	public static final int CONCAT_VALUE = 3;

	/**
	 * The '<em><b>INSERT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INSERT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INSERT
	 * @model literal="insert"
	 * @generated
	 * @ordered
	 */
	public static final int INSERT_VALUE = 4;

	/**
	 * The '<em><b>REMOVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REMOVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMOVE
	 * @model literal="remove"
	 * @generated
	 * @ordered
	 */
	public static final int REMOVE_VALUE = 5;

	/**
	 * The '<em><b>LENGHT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LENGHT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LENGHT
	 * @model literal="lenght"
	 * @generated
	 * @ordered
	 */
	public static final int LENGHT_VALUE = 6;

	/**
	 * An array of all the '<em><b>List Op Name</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ListOpName[] VALUES_ARRAY =
		new ListOpName[] {
			LIST_CONS,
			FIRST,
			TAIL,
			CONCAT,
			INSERT,
			REMOVE,
			LENGHT,
		};

	/**
	 * A public read-only list of all the '<em><b>List Op Name</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ListOpName> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>List Op Name</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ListOpName get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ListOpName result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>List Op Name</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ListOpName getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ListOpName result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>List Op Name</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ListOpName get(int value) {
		switch (value) {
			case LIST_CONS_VALUE: return LIST_CONS;
			case FIRST_VALUE: return FIRST;
			case TAIL_VALUE: return TAIL;
			case CONCAT_VALUE: return CONCAT;
			case INSERT_VALUE: return INSERT;
			case REMOVE_VALUE: return REMOVE;
			case LENGHT_VALUE: return LENGHT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ListOpName(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ListOpName
