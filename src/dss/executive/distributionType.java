/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.executive;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>distribution Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.executive.ExecutivePackage#getdistributionType()
 * @model
 * @generated
 */
public enum distributionType implements Enumerator {
	/**
	 * The '<em><b>Proportional</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROPORTIONAL_VALUE
	 * @generated
	 * @ordered
	 */
	PROPORTIONAL(0, "Proportional", "Proportional"),

	/**
	 * The '<em><b>Uniform</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIFORM_VALUE
	 * @generated
	 * @ordered
	 */
	UNIFORM(1, "Uniform", "Uniform"),

	/**
	 * The '<em><b>Random</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RANDOM_VALUE
	 * @generated
	 * @ordered
	 */
	RANDOM(2, "Random", "Random"),

	/**
	 * The '<em><b>Skip</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SKIP_VALUE
	 * @generated
	 * @ordered
	 */
	SKIP(3, "Skip", "Skip");

	/**
	 * The '<em><b>Proportional</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Proportional</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROPORTIONAL
	 * @model name="Proportional"
	 * @generated
	 * @ordered
	 */
	public static final int PROPORTIONAL_VALUE = 0;

	/**
	 * The '<em><b>Uniform</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Uniform</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNIFORM
	 * @model name="Uniform"
	 * @generated
	 * @ordered
	 */
	public static final int UNIFORM_VALUE = 1;

	/**
	 * The '<em><b>Random</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Random</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RANDOM
	 * @model name="Random"
	 * @generated
	 * @ordered
	 */
	public static final int RANDOM_VALUE = 2;

	/**
	 * The '<em><b>Skip</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Skip</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SKIP
	 * @model name="Skip"
	 * @generated
	 * @ordered
	 */
	public static final int SKIP_VALUE = 3;

	/**
	 * An array of all the '<em><b>distribution Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final distributionType[] VALUES_ARRAY =
		new distributionType[] {
			PROPORTIONAL,
			UNIFORM,
			RANDOM,
			SKIP,
		};

	/**
	 * A public read-only list of all the '<em><b>distribution Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<distributionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>distribution Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static distributionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			distributionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>distribution Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static distributionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			distributionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>distribution Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static distributionType get(int value) {
		switch (value) {
			case PROPORTIONAL_VALUE: return PROPORTIONAL;
			case UNIFORM_VALUE: return UNIFORM;
			case RANDOM_VALUE: return RANDOM;
			case SKIP_VALUE: return SKIP;
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
	private distributionType(int value, String name, String literal) {
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
	
} //distributionType
