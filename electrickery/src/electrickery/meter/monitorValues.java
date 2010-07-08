/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.meter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>monitor Values</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see electrickery.meter.MeterPackage#getmonitorValues()
 * @model
 * @generated
 */
public enum monitorValues implements Enumerator {
	/**
	 * The '<em><b>VI</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VI_VALUE
	 * @generated
	 * @ordered
	 */
	VI(0, "V_I", "V_I"),

	/**
	 * The '<em><b>PQ</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PQ_VALUE
	 * @generated
	 * @ordered
	 */
	PQ(1, "P_Q", "P_Q"),

	/**
	 * The '<em><b>Tap Position</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TAP_POSITION_VALUE
	 * @generated
	 * @ordered
	 */
	TAP_POSITION(2, "Tap_Position", "Tap_Position"),

	/**
	 * The '<em><b>State Variables</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATE_VARIABLES_VALUE
	 * @generated
	 * @ordered
	 */
	STATE_VARIABLES(3, "State_Variables", "State_Variables");

	/**
	 * The '<em><b>VI</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>VI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VI
	 * @model name="V_I"
	 * @generated
	 * @ordered
	 */
	public static final int VI_VALUE = 0;

	/**
	 * The '<em><b>PQ</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PQ</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PQ
	 * @model name="P_Q"
	 * @generated
	 * @ordered
	 */
	public static final int PQ_VALUE = 1;

	/**
	 * The '<em><b>Tap Position</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Tap Position</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TAP_POSITION
	 * @model name="Tap_Position"
	 * @generated
	 * @ordered
	 */
	public static final int TAP_POSITION_VALUE = 2;

	/**
	 * The '<em><b>State Variables</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>State Variables</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATE_VARIABLES
	 * @model name="State_Variables"
	 * @generated
	 * @ordered
	 */
	public static final int STATE_VARIABLES_VALUE = 3;

	/**
	 * An array of all the '<em><b>monitor Values</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final monitorValues[] VALUES_ARRAY =
		new monitorValues[] {
			VI,
			PQ,
			TAP_POSITION,
			STATE_VARIABLES,
		};

	/**
	 * A public read-only list of all the '<em><b>monitor Values</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<monitorValues> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>monitor Values</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static monitorValues get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			monitorValues result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>monitor Values</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static monitorValues getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			monitorValues result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>monitor Values</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static monitorValues get(int value) {
		switch (value) {
			case VI_VALUE: return VI;
			case PQ_VALUE: return PQ;
			case TAP_POSITION_VALUE: return TAP_POSITION;
			case STATE_VARIABLES_VALUE: return STATE_VARIABLES;
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
	private monitorValues(int value, String name, String literal) {
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
	
} //monitorValues
