/**
 * Copyright (C) 2010 Richard Lincoln
 *
 * $Id$
 */
package electrickery.conversion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>spec Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see electrickery.conversion.ConversionPackage#getspecType()
 * @model
 * @generated
 */
public enum specType implements Enumerator {
	/**
	 * The '<em><b>MVA Sc</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MVA_SC_VALUE
	 * @generated
	 * @ordered
	 */
	MVA_SC(1, "MVASc", "MVASc"), /**
	 * The '<em><b>ISc</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISC_VALUE
	 * @generated
	 * @ordered
	 */
	ISC(2, "ISc", "ISc"),

	/**
	 * The '<em><b>Z1Z0</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #Z1Z0_VALUE
	 * @generated
	 * @ordered
	 */
	Z1Z0(3, "Z1Z0", "Z1Z0");

	/**
	 * The '<em><b>MVA Sc</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MVA Sc</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MVA_SC
	 * @model name="MVASc"
	 * @generated
	 * @ordered
	 */
	public static final int MVA_SC_VALUE = 1;

	/**
	 * The '<em><b>ISc</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Isc</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ISC
	 * @model name="ISc"
	 * @generated
	 * @ordered
	 */
	public static final int ISC_VALUE = 2;

	/**
	 * The '<em><b>Z1Z0</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Z1Z0</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #Z1Z0
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int Z1Z0_VALUE = 3;

	/**
	 * An array of all the '<em><b>spec Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final specType[] VALUES_ARRAY =
		new specType[] {
			MVA_SC,
			ISC,
			Z1Z0,
		};

	/**
	 * A public read-only list of all the '<em><b>spec Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<specType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>spec Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static specType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			specType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>spec Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static specType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			specType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>spec Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static specType get(int value) {
		switch (value) {
			case MVA_SC_VALUE: return MVA_SC;
			case ISC_VALUE: return ISC;
			case Z1Z0_VALUE: return Z1Z0;
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
	private specType(int value, String name, String literal) {
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
	
} //specType
