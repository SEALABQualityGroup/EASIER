/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Special Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.DataType.DataTypePackage#getSpecialType()
 * @model
 * @generated
 */
public enum SpecialType implements Enumerator {
	/**
	 * The '<em><b>PRIO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRIO_VALUE
	 * @generated
	 * @ordered
	 */
	PRIO(0, "PRIO", "prio"),

	/**
	 * The '<em><b>RATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RATE_VALUE
	 * @generated
	 * @ordered
	 */
	RATE(1, "RATE", "rate"),

	/**
	 * The '<em><b>WEIGHT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	WEIGHT(2, "WEIGHT", "weight");

	/**
	 * The '<em><b>PRIO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PRIO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PRIO
	 * @model literal="prio"
	 * @generated
	 * @ordered
	 */
	public static final int PRIO_VALUE = 0;

	/**
	 * The '<em><b>RATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RATE
	 * @model literal="rate"
	 * @generated
	 * @ordered
	 */
	public static final int RATE_VALUE = 1;

	/**
	 * The '<em><b>WEIGHT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WEIGHT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WEIGHT
	 * @model literal="weight"
	 * @generated
	 * @ordered
	 */
	public static final int WEIGHT_VALUE = 2;

	/**
	 * An array of all the '<em><b>Special Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SpecialType[] VALUES_ARRAY =
		new SpecialType[] {
			PRIO,
			RATE,
			WEIGHT,
		};

	/**
	 * A public read-only list of all the '<em><b>Special Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SpecialType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Special Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SpecialType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SpecialType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Special Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SpecialType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SpecialType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Special Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SpecialType get(int value) {
		switch (value) {
			case PRIO_VALUE: return PRIO;
			case RATE_VALUE: return RATE;
			case WEIGHT_VALUE: return WEIGHT;
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
	private SpecialType(int value, String name, String literal) {
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
	
} //SpecialType
