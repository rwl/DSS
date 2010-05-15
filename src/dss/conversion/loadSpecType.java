/**
 * Copyright (C) 2010 Richard Lincoln
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
 * A representation of the literals of the enumeration '<em><b>load Spec Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.conversion.ConversionPackage#getloadSpecType()
 * @model
 * @generated
 */
public enum loadSpecType implements Enumerator {
	/**
	 * The '<em><b>KW PF</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KW_PF_VALUE
	 * @generated
	 * @ordered
	 */
	KW_PF(0, "kW_PF", "kW_PF"),

	/**
	 * The '<em><b>KW kVar</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KW_KVAR_VALUE
	 * @generated
	 * @ordered
	 */
	KW_KVAR(1, "kW_kVar", "kW_kVar"), /**
	 * The '<em><b>KVA PF</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KVA_PF_VALUE
	 * @generated
	 * @ordered
	 */
	KVA_PF(2, "kVA_PF", "kVA_PF");

	/**
	 * The '<em><b>KW PF</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>KW PF</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KW_PF
	 * @model name="kW_PF"
	 * @generated
	 * @ordered
	 */
	public static final int KW_PF_VALUE = 0;

	/**
	 * The '<em><b>KW kVar</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>KW kVar</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KW_KVAR
	 * @model name="kW_kVar"
	 * @generated
	 * @ordered
	 */
	public static final int KW_KVAR_VALUE = 1;

	/**
	 * The '<em><b>KVA PF</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>KVA PF</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KVA_PF
	 * @model name="kVA_PF"
	 * @generated
	 * @ordered
	 */
	public static final int KVA_PF_VALUE = 2;

	/**
	 * An array of all the '<em><b>load Spec Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final loadSpecType[] VALUES_ARRAY =
		new loadSpecType[] {
			KW_PF,
			KW_KVAR,
			KVA_PF,
		};

	/**
	 * A public read-only list of all the '<em><b>load Spec Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<loadSpecType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>load Spec Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static loadSpecType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			loadSpecType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>load Spec Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static loadSpecType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			loadSpecType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>load Spec Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static loadSpecType get(int value) {
		switch (value) {
			case KW_PF_VALUE: return KW_PF;
			case KW_KVAR_VALUE: return KW_KVAR;
			case KVA_PF_VALUE: return KVA_PF;
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
	private loadSpecType(int value, String name, String literal) {
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
	
} //loadSpecType
