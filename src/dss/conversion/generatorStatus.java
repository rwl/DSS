/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.conversion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>generator Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.conversion.ConversionPackage#getgeneratorStatus()
 * @model
 * @generated
 */
public enum generatorStatus implements Enumerator {
	/**
	 * The '<em><b>Variable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VARIABLE_VALUE
	 * @generated
	 * @ordered
	 */
	VARIABLE(0, "Variable", "Variable"),

	/**
	 * The '<em><b>Fixed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIXED_VALUE
	 * @generated
	 * @ordered
	 */
	FIXED(1, "Fixed", "Fixed");

	/**
	 * The '<em><b>Variable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Variable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VARIABLE
	 * @model name="Variable"
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_VALUE = 0;

	/**
	 * The '<em><b>Fixed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Fixed</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FIXED
	 * @model name="Fixed"
	 * @generated
	 * @ordered
	 */
	public static final int FIXED_VALUE = 1;

	/**
	 * An array of all the '<em><b>generator Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final generatorStatus[] VALUES_ARRAY =
		new generatorStatus[] {
			VARIABLE,
			FIXED,
		};

	/**
	 * A public read-only list of all the '<em><b>generator Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<generatorStatus> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>generator Status</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static generatorStatus get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			generatorStatus result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>generator Status</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static generatorStatus getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			generatorStatus result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>generator Status</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static generatorStatus get(int value) {
		switch (value) {
			case VARIABLE_VALUE: return VARIABLE;
			case FIXED_VALUE: return FIXED;
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
	private generatorStatus(int value, String name, String literal) {
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
	
} //generatorStatus
