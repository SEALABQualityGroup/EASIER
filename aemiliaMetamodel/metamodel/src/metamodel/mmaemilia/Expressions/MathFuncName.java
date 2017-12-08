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
 * A representation of the literals of the enumeration '<em><b>Math Func Name</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getMathFuncName()
 * @model
 * @generated
 */
public enum MathFuncName implements Enumerator {
	/**
	 * The '<em><b>MOD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MOD_VALUE
	 * @generated
	 * @ordered
	 */
	MOD(0, "MOD", "mod"),

	/**
	 * The '<em><b>ABS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ABS_VALUE
	 * @generated
	 * @ordered
	 */
	ABS(1, "ABS", "abs"),

	/**
	 * The '<em><b>CEIL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CEIL_VALUE
	 * @generated
	 * @ordered
	 */
	CEIL(2, "CEIL", "ceil"),

	/**
	 * The '<em><b>FLOOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FLOOR_VALUE
	 * @generated
	 * @ordered
	 */
	FLOOR(3, "FLOOR", "floor"),

	/**
	 * The '<em><b>MIN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MIN_VALUE
	 * @generated
	 * @ordered
	 */
	MIN(4, "MIN", "min"),

	/**
	 * The '<em><b>MAX</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MAX_VALUE
	 * @generated
	 * @ordered
	 */
	MAX(5, "MAX", "max"),

	/**
	 * The '<em><b>POWER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POWER_VALUE
	 * @generated
	 * @ordered
	 */
	POWER(6, "POWER", "power"),

	/**
	 * The '<em><b>EPOWER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EPOWER_VALUE
	 * @generated
	 * @ordered
	 */
	EPOWER(7, "EPOWER", "epower"),

	/**
	 * The '<em><b>LOGE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGE_VALUE
	 * @generated
	 * @ordered
	 */
	LOGE(8, "LOGE", "loge"),

	/**
	 * The '<em><b>LOG10</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOG10_VALUE
	 * @generated
	 * @ordered
	 */
	LOG10(9, "LOG10", "log10"),

	/**
	 * The '<em><b>SQRT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SQRT_VALUE
	 * @generated
	 * @ordered
	 */
	SQRT(10, "SQRT", "sqrt"),

	/**
	 * The '<em><b>SIN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIN_VALUE
	 * @generated
	 * @ordered
	 */
	SIN(11, "SIN", "sin"),

	/**
	 * The '<em><b>COS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COS_VALUE
	 * @generated
	 * @ordered
	 */
	COS(12, "COS", "cos");

	/**
	 * The '<em><b>MOD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MOD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MOD
	 * @model literal="mod"
	 * @generated
	 * @ordered
	 */
	public static final int MOD_VALUE = 0;

	/**
	 * The '<em><b>ABS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ABS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ABS
	 * @model literal="abs"
	 * @generated
	 * @ordered
	 */
	public static final int ABS_VALUE = 1;

	/**
	 * The '<em><b>CEIL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CEIL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CEIL
	 * @model literal="ceil"
	 * @generated
	 * @ordered
	 */
	public static final int CEIL_VALUE = 2;

	/**
	 * The '<em><b>FLOOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FLOOR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FLOOR
	 * @model literal="floor"
	 * @generated
	 * @ordered
	 */
	public static final int FLOOR_VALUE = 3;

	/**
	 * The '<em><b>MIN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MIN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MIN
	 * @model literal="min"
	 * @generated
	 * @ordered
	 */
	public static final int MIN_VALUE = 4;

	/**
	 * The '<em><b>MAX</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MAX</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MAX
	 * @model literal="max"
	 * @generated
	 * @ordered
	 */
	public static final int MAX_VALUE = 5;

	/**
	 * The '<em><b>POWER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>POWER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POWER
	 * @model literal="power"
	 * @generated
	 * @ordered
	 */
	public static final int POWER_VALUE = 6;

	/**
	 * The '<em><b>EPOWER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EPOWER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EPOWER
	 * @model literal="epower"
	 * @generated
	 * @ordered
	 */
	public static final int EPOWER_VALUE = 7;

	/**
	 * The '<em><b>LOGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOGE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOGE
	 * @model literal="loge"
	 * @generated
	 * @ordered
	 */
	public static final int LOGE_VALUE = 8;

	/**
	 * The '<em><b>LOG10</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOG10</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOG10
	 * @model literal="log10"
	 * @generated
	 * @ordered
	 */
	public static final int LOG10_VALUE = 9;

	/**
	 * The '<em><b>SQRT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SQRT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SQRT
	 * @model literal="sqrt"
	 * @generated
	 * @ordered
	 */
	public static final int SQRT_VALUE = 10;

	/**
	 * The '<em><b>SIN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SIN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SIN
	 * @model literal="sin"
	 * @generated
	 * @ordered
	 */
	public static final int SIN_VALUE = 11;

	/**
	 * The '<em><b>COS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COS
	 * @model literal="cos"
	 * @generated
	 * @ordered
	 */
	public static final int COS_VALUE = 12;

	/**
	 * An array of all the '<em><b>Math Func Name</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MathFuncName[] VALUES_ARRAY =
		new MathFuncName[] {
			MOD,
			ABS,
			CEIL,
			FLOOR,
			MIN,
			MAX,
			POWER,
			EPOWER,
			LOGE,
			LOG10,
			SQRT,
			SIN,
			COS,
		};

	/**
	 * A public read-only list of all the '<em><b>Math Func Name</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<MathFuncName> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Math Func Name</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static MathFuncName get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MathFuncName result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Math Func Name</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static MathFuncName getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MathFuncName result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Math Func Name</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static MathFuncName get(int value) {
		switch (value) {
			case MOD_VALUE: return MOD;
			case ABS_VALUE: return ABS;
			case CEIL_VALUE: return CEIL;
			case FLOOR_VALUE: return FLOOR;
			case MIN_VALUE: return MIN;
			case MAX_VALUE: return MAX;
			case POWER_VALUE: return POWER;
			case EPOWER_VALUE: return EPOWER;
			case LOGE_VALUE: return LOGE;
			case LOG10_VALUE: return LOG10;
			case SQRT_VALUE: return SQRT;
			case SIN_VALUE: return SIN;
			case COS_VALUE: return COS;
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
	private MathFuncName(int value, String name, String literal) {
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
	
} //MathFuncName
