/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.control;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>control Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.control.ControlPackage#getcontrolType()
 * @model
 * @generated
 */
public enum controlType implements Enumerator {
	/**
	 * The '<em><b>Current</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENT_VALUE
	 * @generated
	 * @ordered
	 */
	CURRENT(0, "Current", "Current"),

	/**
	 * The '<em><b>Voltage</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VOLTAGE_VALUE
	 * @generated
	 * @ordered
	 */
	VOLTAGE(1, "Voltage", "Voltage"),

	/**
	 * The '<em><b>KV Ar</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KV_AR_VALUE
	 * @generated
	 * @ordered
	 */
	KV_AR(2, "kVAr", "kVAr"),

	/**
	 * The '<em><b>Time</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIME_VALUE
	 * @generated
	 * @ordered
	 */
	TIME(3, "Time", "Time");

	/**
	 * The '<em><b>Current</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Current</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENT
	 * @model name="Current"
	 * @generated
	 * @ordered
	 */
	public static final int CURRENT_VALUE = 0;

	/**
	 * The '<em><b>Voltage</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Voltage</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VOLTAGE
	 * @model name="Voltage"
	 * @generated
	 * @ordered
	 */
	public static final int VOLTAGE_VALUE = 1;

	/**
	 * The '<em><b>KV Ar</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>KV Ar</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KV_AR
	 * @model name="kVAr"
	 * @generated
	 * @ordered
	 */
	public static final int KV_AR_VALUE = 2;

	/**
	 * The '<em><b>Time</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Time</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIME
	 * @model name="Time"
	 * @generated
	 * @ordered
	 */
	public static final int TIME_VALUE = 3;

	/**
	 * An array of all the '<em><b>control Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final controlType[] VALUES_ARRAY =
		new controlType[] {
			CURRENT,
			VOLTAGE,
			KV_AR,
			TIME,
		};

	/**
	 * A public read-only list of all the '<em><b>control Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<controlType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>control Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static controlType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			controlType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>control Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static controlType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			controlType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>control Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static controlType get(int value) {
		switch (value) {
			case CURRENT_VALUE: return CURRENT;
			case VOLTAGE_VALUE: return VOLTAGE;
			case KV_AR_VALUE: return KV_AR;
			case TIME_VALUE: return TIME;
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
	private controlType(int value, String name, String literal) {
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
	
} //controlType
