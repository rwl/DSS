/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.meter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>sensor Action</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.meter.MeterPackage#getsensorAction()
 * @model
 * @generated
 */
public enum sensorAction implements Enumerator {
	/**
	 * The '<em><b>Square Error</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SQUARE_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	SQUARE_ERROR(0, "SquareError", "SquareError"),

	/**
	 * The '<em><b>Actual Values</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ACTUAL_VALUES_VALUE
	 * @generated
	 * @ordered
	 */
	ACTUAL_VALUES(1, "ActualValues", "ActualValues");

	/**
	 * The '<em><b>Square Error</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Square Error</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SQUARE_ERROR
	 * @model name="SquareError"
	 * @generated
	 * @ordered
	 */
	public static final int SQUARE_ERROR_VALUE = 0;

	/**
	 * The '<em><b>Actual Values</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Actual Values</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ACTUAL_VALUES
	 * @model name="ActualValues"
	 * @generated
	 * @ordered
	 */
	public static final int ACTUAL_VALUES_VALUE = 1;

	/**
	 * An array of all the '<em><b>sensor Action</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final sensorAction[] VALUES_ARRAY =
		new sensorAction[] {
			SQUARE_ERROR,
			ACTUAL_VALUES,
		};

	/**
	 * A public read-only list of all the '<em><b>sensor Action</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<sensorAction> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>sensor Action</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static sensorAction get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			sensorAction result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>sensor Action</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static sensorAction getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			sensorAction result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>sensor Action</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static sensorAction get(int value) {
		switch (value) {
			case SQUARE_ERROR_VALUE: return SQUARE_ERROR;
			case ACTUAL_VALUES_VALUE: return ACTUAL_VALUES;
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
	private sensorAction(int value, String name, String literal) {
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
	
} //sensorAction
