/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.QueueingNetwork;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Time Units Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * enumerazione introdotta per le unità di tempo derivate dall'outputSE
 * <!-- end-model-doc -->
 * @see metamodel.mmaemilia.QueueingNetwork.QueueingNetworkPackage#getTimeUnitsType()
 * @model
 * @generated
 */
public enum TimeUnitsType implements Enumerator {
	/**
	 * The '<em><b>Day</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DAY_VALUE
	 * @generated
	 * @ordered
	 */
	DAY(0, "day", "day"),

	/**
	 * The '<em><b>Hr</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HR_VALUE
	 * @generated
	 * @ordered
	 */
	HR(2, "hr", "hr"),

	/**
	 * The '<em><b>Min</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MIN_VALUE
	 * @generated
	 * @ordered
	 */
	MIN(4, "min", "min"),

	/**
	 * The '<em><b>Sec</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEC_VALUE
	 * @generated
	 * @ordered
	 */
	SEC(6, "sec", "sec"),

	/**
	 * The '<em><b>Ms</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MS_VALUE
	 * @generated
	 * @ordered
	 */
	MS(8, "ms", "ms"),

	/**
	 * The '<em><b>Ns</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NS_VALUE
	 * @generated
	 * @ordered
	 */
	NS(10, "ns", "ns");

	/**
	 * The '<em><b>Day</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Day</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DAY
	 * @model name="day"
	 * @generated
	 * @ordered
	 */
	public static final int DAY_VALUE = 0;

	/**
	 * The '<em><b>Hr</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Hr</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HR
	 * @model name="hr"
	 * @generated
	 * @ordered
	 */
	public static final int HR_VALUE = 2;

	/**
	 * The '<em><b>Min</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Min</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MIN
	 * @model name="min"
	 * @generated
	 * @ordered
	 */
	public static final int MIN_VALUE = 4;

	/**
	 * The '<em><b>Sec</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Sec</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SEC
	 * @model name="sec"
	 * @generated
	 * @ordered
	 */
	public static final int SEC_VALUE = 6;

	/**
	 * The '<em><b>Ms</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Ms</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MS
	 * @model name="ms"
	 * @generated
	 * @ordered
	 */
	public static final int MS_VALUE = 8;

	/**
	 * The '<em><b>Ns</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Ns</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NS
	 * @model name="ns"
	 * @generated
	 * @ordered
	 */
	public static final int NS_VALUE = 10;

	/**
	 * An array of all the '<em><b>Time Units Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TimeUnitsType[] VALUES_ARRAY =
		new TimeUnitsType[] {
			DAY,
			HR,
			MIN,
			SEC,
			MS,
			NS,
		};

	/**
	 * A public read-only list of all the '<em><b>Time Units Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TimeUnitsType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Time Units Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TimeUnitsType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TimeUnitsType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Time Units Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TimeUnitsType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TimeUnitsType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Time Units Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TimeUnitsType get(int value) {
		switch (value) {
			case DAY_VALUE: return DAY;
			case HR_VALUE: return HR;
			case MIN_VALUE: return MIN;
			case SEC_VALUE: return SEC;
			case MS_VALUE: return MS;
			case NS_VALUE: return NS;
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
	private TimeUnitsType(int value, String name, String literal) {
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
	
} //TimeUnitsType
