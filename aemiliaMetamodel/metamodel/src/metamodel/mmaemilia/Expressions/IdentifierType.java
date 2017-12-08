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
 * A representation of the literals of the enumeration '<em><b>Identifier Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getIdentifierType()
 * @model
 * @generated
 */
public enum IdentifierType implements Enumerator {
	/**
	 * The '<em><b>TYPED IDENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TYPED_IDENT_VALUE
	 * @generated
	 * @ordered
	 */
	TYPED_IDENT(0, "TYPED_IDENT", "TYPED_IDENT"),

	/**
	 * The '<em><b>NUMERIC CONST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NUMERIC_CONST_VALUE
	 * @generated
	 * @ordered
	 */
	NUMERIC_CONST(1, "NUMERIC_CONST", "NUMERIC_CONST"),

	/**
	 * The '<em><b>TRUTH VAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRUTH_VAL_VALUE
	 * @generated
	 * @ordered
	 */
	TRUTH_VAL(2, "TRUTH_VAL", "TRUTH_VAL");

	/**
	 * The '<em><b>TYPED IDENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TYPED IDENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TYPED_IDENT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TYPED_IDENT_VALUE = 0;

	/**
	 * The '<em><b>NUMERIC CONST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NUMERIC CONST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NUMERIC_CONST
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NUMERIC_CONST_VALUE = 1;

	/**
	 * The '<em><b>TRUTH VAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRUTH VAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRUTH_VAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TRUTH_VAL_VALUE = 2;

	/**
	 * An array of all the '<em><b>Identifier Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final IdentifierType[] VALUES_ARRAY =
		new IdentifierType[] {
			TYPED_IDENT,
			NUMERIC_CONST,
			TRUTH_VAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Identifier Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<IdentifierType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Identifier Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static IdentifierType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IdentifierType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Identifier Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static IdentifierType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			IdentifierType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Identifier Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static IdentifierType get(int value) {
		switch (value) {
			case TYPED_IDENT_VALUE: return TYPED_IDENT;
			case NUMERIC_CONST_VALUE: return NUMERIC_CONST;
			case TRUTH_VAL_VALUE: return TRUTH_VAL;
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
	private IdentifierType(int value, String name, String literal) {
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
	
} //IdentifierType
