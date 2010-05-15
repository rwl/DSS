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
 * A representation of the literals of the enumeration '<em><b>circuit Model Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.executive.ExecutivePackage#getcircuitModelType()
 * @model
 * @generated
 */
public enum circuitModelType implements Enumerator {
	/**
	 * The '<em><b>Multiphase</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MULTIPHASE_VALUE
	 * @generated
	 * @ordered
	 */
	MULTIPHASE(0, "Multiphase", "Multiphase"),

	/**
	 * The '<em><b>Positive</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POSITIVE_VALUE
	 * @generated
	 * @ordered
	 */
	POSITIVE(1, "Positive", "Positive");

	/**
	 * The '<em><b>Multiphase</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Multiphase</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MULTIPHASE
	 * @model name="Multiphase"
	 * @generated
	 * @ordered
	 */
	public static final int MULTIPHASE_VALUE = 0;

	/**
	 * The '<em><b>Positive</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Positive</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POSITIVE
	 * @model name="Positive"
	 * @generated
	 * @ordered
	 */
	public static final int POSITIVE_VALUE = 1;

	/**
	 * An array of all the '<em><b>circuit Model Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final circuitModelType[] VALUES_ARRAY =
		new circuitModelType[] {
			MULTIPHASE,
			POSITIVE,
		};

	/**
	 * A public read-only list of all the '<em><b>circuit Model Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<circuitModelType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>circuit Model Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static circuitModelType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			circuitModelType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>circuit Model Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static circuitModelType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			circuitModelType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>circuit Model Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static circuitModelType get(int value) {
		switch (value) {
			case MULTIPHASE_VALUE: return MULTIPHASE;
			case POSITIVE_VALUE: return POSITIVE;
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
	private circuitModelType(int value, String name, String literal) {
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
	
} //circuitModelType
