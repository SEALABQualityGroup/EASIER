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
 * A representation of the literals of the enumeration '<em><b>Pseudo Num Name</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Expressions.ExpressionsPackage#getPseudoNumName()
 * @model
 * @generated
 */
public enum PseudoNumName implements Enumerator {
	/**
	 * The '<em><b>DUNIFORM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DUNIFORM_VALUE
	 * @generated
	 * @ordered
	 */
	DUNIFORM(0, "D_UNIFORM", "d_uniform"),

	/**
	 * The '<em><b>GAMMA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GAMMA_VALUE
	 * @generated
	 * @ordered
	 */
	GAMMA(1, "GAMMA", "gamma"),

	/**
	 * The '<em><b>CUNIFORM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CUNIFORM_VALUE
	 * @generated
	 * @ordered
	 */
	CUNIFORM(2, "C_UNIFORM", "c_uniform"),

	/**
	 * The '<em><b>ERLANG</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERLANG_VALUE
	 * @generated
	 * @ordered
	 */
	ERLANG(3, "ERLANG", "erlang"),

	/**
	 * The '<em><b>EXPONENTIAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXPONENTIAL_VALUE
	 * @generated
	 * @ordered
	 */
	EXPONENTIAL(4, "EXPONENTIAL", "exponential"),

	/**
	 * The '<em><b>WEIBULL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEIBULL_VALUE
	 * @generated
	 * @ordered
	 */
	WEIBULL(5, "WEIBULL", "weibull"),

	/**
	 * The '<em><b>BETA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BETA_VALUE
	 * @generated
	 * @ordered
	 */
	BETA(6, "BETA", "beta"),

	/**
	 * The '<em><b>NORMAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NORMAL_VALUE
	 * @generated
	 * @ordered
	 */
	NORMAL(7, "NORMAL", "normal"),

	/**
	 * The '<em><b>PARETO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARETO_VALUE
	 * @generated
	 * @ordered
	 */
	PARETO(8, "PARETO", "pareto"),

	/**
	 * The '<em><b>BPARETO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BPARETO_VALUE
	 * @generated
	 * @ordered
	 */
	BPARETO(9, "B_PARETO", "b_pareto"),

	/**
	 * The '<em><b>BERNOULLI</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BERNOULLI_VALUE
	 * @generated
	 * @ordered
	 */
	BERNOULLI(10, "BERNOULLI", "bernoulli"),

	/**
	 * The '<em><b>BINOMIAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BINOMIAL_VALUE
	 * @generated
	 * @ordered
	 */
	BINOMIAL(11, "BINOMIAL", "binomial"),

	/**
	 * The '<em><b>POISSON</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POISSON_VALUE
	 * @generated
	 * @ordered
	 */
	POISSON(12, "POISSON", "poisson"),

	/**
	 * The '<em><b>NEG BINOMIAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEG_BINOMIAL_VALUE
	 * @generated
	 * @ordered
	 */
	NEG_BINOMIAL(13, "NEG_BINOMIAL", "neg_binomial"),

	/**
	 * The '<em><b>GEOMETRIC</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GEOMETRIC_VALUE
	 * @generated
	 * @ordered
	 */
	GEOMETRIC(14, "GEOMETRIC", "geometric"),

	/**
	 * The '<em><b>PASCAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PASCAL_VALUE
	 * @generated
	 * @ordered
	 */
	PASCAL(15, "PASCAL", "pascal");

	/**
	 * The '<em><b>DUNIFORM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DUNIFORM</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DUNIFORM
	 * @model name="D_UNIFORM" literal="d_uniform"
	 * @generated
	 * @ordered
	 */
	public static final int DUNIFORM_VALUE = 0;

	/**
	 * The '<em><b>GAMMA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GAMMA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GAMMA
	 * @model literal="gamma"
	 * @generated
	 * @ordered
	 */
	public static final int GAMMA_VALUE = 1;

	/**
	 * The '<em><b>CUNIFORM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CUNIFORM</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CUNIFORM
	 * @model name="C_UNIFORM" literal="c_uniform"
	 * @generated
	 * @ordered
	 */
	public static final int CUNIFORM_VALUE = 2;

	/**
	 * The '<em><b>ERLANG</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ERLANG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERLANG
	 * @model literal="erlang"
	 * @generated
	 * @ordered
	 */
	public static final int ERLANG_VALUE = 3;

	/**
	 * The '<em><b>EXPONENTIAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EXPONENTIAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXPONENTIAL
	 * @model literal="exponential"
	 * @generated
	 * @ordered
	 */
	public static final int EXPONENTIAL_VALUE = 4;

	/**
	 * The '<em><b>WEIBULL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WEIBULL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WEIBULL
	 * @model literal="weibull"
	 * @generated
	 * @ordered
	 */
	public static final int WEIBULL_VALUE = 5;

	/**
	 * The '<em><b>BETA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BETA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BETA
	 * @model literal="beta"
	 * @generated
	 * @ordered
	 */
	public static final int BETA_VALUE = 6;

	/**
	 * The '<em><b>NORMAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NORMAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NORMAL
	 * @model literal="normal"
	 * @generated
	 * @ordered
	 */
	public static final int NORMAL_VALUE = 7;

	/**
	 * The '<em><b>PARETO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PARETO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARETO
	 * @model literal="pareto"
	 * @generated
	 * @ordered
	 */
	public static final int PARETO_VALUE = 8;

	/**
	 * The '<em><b>BPARETO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BPARETO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BPARETO
	 * @model name="B_PARETO" literal="b_pareto"
	 * @generated
	 * @ordered
	 */
	public static final int BPARETO_VALUE = 9;

	/**
	 * The '<em><b>BERNOULLI</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BERNOULLI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BERNOULLI
	 * @model literal="bernoulli"
	 * @generated
	 * @ordered
	 */
	public static final int BERNOULLI_VALUE = 10;

	/**
	 * The '<em><b>BINOMIAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BINOMIAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BINOMIAL
	 * @model literal="binomial"
	 * @generated
	 * @ordered
	 */
	public static final int BINOMIAL_VALUE = 11;

	/**
	 * The '<em><b>POISSON</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>POISSON</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POISSON
	 * @model literal="poisson"
	 * @generated
	 * @ordered
	 */
	public static final int POISSON_VALUE = 12;

	/**
	 * The '<em><b>NEG BINOMIAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NEG BINOMIAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEG_BINOMIAL
	 * @model literal="neg_binomial"
	 * @generated
	 * @ordered
	 */
	public static final int NEG_BINOMIAL_VALUE = 13;

	/**
	 * The '<em><b>GEOMETRIC</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GEOMETRIC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GEOMETRIC
	 * @model literal="geometric"
	 * @generated
	 * @ordered
	 */
	public static final int GEOMETRIC_VALUE = 14;

	/**
	 * The '<em><b>PASCAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PASCAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PASCAL
	 * @model literal="pascal"
	 * @generated
	 * @ordered
	 */
	public static final int PASCAL_VALUE = 15;

	/**
	 * An array of all the '<em><b>Pseudo Num Name</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PseudoNumName[] VALUES_ARRAY =
		new PseudoNumName[] {
			DUNIFORM,
			GAMMA,
			CUNIFORM,
			ERLANG,
			EXPONENTIAL,
			WEIBULL,
			BETA,
			NORMAL,
			PARETO,
			BPARETO,
			BERNOULLI,
			BINOMIAL,
			POISSON,
			NEG_BINOMIAL,
			GEOMETRIC,
			PASCAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Pseudo Num Name</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PseudoNumName> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Pseudo Num Name</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PseudoNumName get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PseudoNumName result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Pseudo Num Name</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PseudoNumName getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PseudoNumName result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Pseudo Num Name</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PseudoNumName get(int value) {
		switch (value) {
			case DUNIFORM_VALUE: return DUNIFORM;
			case GAMMA_VALUE: return GAMMA;
			case CUNIFORM_VALUE: return CUNIFORM;
			case ERLANG_VALUE: return ERLANG;
			case EXPONENTIAL_VALUE: return EXPONENTIAL;
			case WEIBULL_VALUE: return WEIBULL;
			case BETA_VALUE: return BETA;
			case NORMAL_VALUE: return NORMAL;
			case PARETO_VALUE: return PARETO;
			case BPARETO_VALUE: return BPARETO;
			case BERNOULLI_VALUE: return BERNOULLI;
			case BINOMIAL_VALUE: return BINOMIAL;
			case POISSON_VALUE: return POISSON;
			case NEG_BINOMIAL_VALUE: return NEG_BINOMIAL;
			case GEOMETRIC_VALUE: return GEOMETRIC;
			case PASCAL_VALUE: return PASCAL;
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
	private PseudoNumName(int value, String name, String literal) {
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
	
} //PseudoNumName
