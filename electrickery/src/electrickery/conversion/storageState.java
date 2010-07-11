/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.conversion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>storage State</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see electrickery.conversion.ConversionPackage#getstorageState()
 * @model
 * @generated
 */
public enum storageState implements Enumerator {
	/**
	 * The '<em><b>Idling</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IDLING_VALUE
	 * @generated
	 * @ordered
	 */
	IDLING(0, "Idling", "Idling"),

	/**
	 * The '<em><b>Charging</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHARGING_VALUE
	 * @generated
	 * @ordered
	 */
	CHARGING(1, "Charging", "Charging"),

	/**
	 * The '<em><b>Discharging</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISCHARGING_VALUE
	 * @generated
	 * @ordered
	 */
	DISCHARGING(2, "Discharging", "Discharging");

	/**
	 * The '<em><b>Idling</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Idling</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IDLING
	 * @model name="Idling"
	 * @generated
	 * @ordered
	 */
	public static final int IDLING_VALUE = 0;

	/**
	 * The '<em><b>Charging</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Charging</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CHARGING
	 * @model name="Charging"
	 * @generated
	 * @ordered
	 */
	public static final int CHARGING_VALUE = 1;

	/**
	 * The '<em><b>Discharging</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Discharging</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DISCHARGING
	 * @model name="Discharging"
	 * @generated
	 * @ordered
	 */
	public static final int DISCHARGING_VALUE = 2;

	/**
	 * An array of all the '<em><b>storage State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final storageState[] VALUES_ARRAY =
		new storageState[] {
			IDLING,
			CHARGING,
			DISCHARGING,
		};

	/**
	 * A public read-only list of all the '<em><b>storage State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<storageState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>storage State</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static storageState get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			storageState result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>storage State</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static storageState getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			storageState result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>storage State</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static storageState get(int value) {
		switch (value) {
			case IDLING_VALUE: return IDLING;
			case CHARGING_VALUE: return CHARGING;
			case DISCHARGING_VALUE: return DISCHARGING;
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
	private storageState(int value, String name, String literal) {
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
	
} //storageState
