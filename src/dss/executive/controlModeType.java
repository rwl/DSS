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
 * A representation of the literals of the enumeration '<em><b>control Mode Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.executive.ExecutivePackage#getcontrolModeType()
 * @model
 * @generated
 */
public enum controlModeType implements Enumerator {
	/**
	 * The '<em><b>Static</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATIC_VALUE
	 * @generated
	 * @ordered
	 */
	STATIC(0, "Static", "Static"),

	/**
	 * The '<em><b>Event</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EVENT_VALUE
	 * @generated
	 * @ordered
	 */
	EVENT(1, "Event", "Event"),

	/**
	 * The '<em><b>Time</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIME_VALUE
	 * @generated
	 * @ordered
	 */
	TIME(2, "Time", "Time");

	/**
	 * The '<em><b>Static</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time does not advance.  Control actions are executed in order of shortest time to act until all actions are cleared from the control queue.  Use this mode for power flow solutions which may require several regulator tap changes per solution.
	 * <!-- end-model-doc -->
	 * @see #STATIC
	 * @model name="Static"
	 * @generated
	 * @ordered
	 */
	public static final int STATIC_VALUE = 0;

	/**
	 * The '<em><b>Event</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Solution is event driven.  Only the control actions nearest in time are executed and the time is advanced automatically to the time of the event.
	 * <!-- end-model-doc -->
	 * @see #EVENT
	 * @model name="Event"
	 * @generated
	 * @ordered
	 */
	public static final int EVENT_VALUE = 1;

	/**
	 * The '<em><b>Time</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Solution is time driven.  Control actions are executed when the time for the pending action is reached or surpassed.  Controls may reset and may choose not to act when it comes their time.  Use TIME mode when modeling a control externally to the DSS and a solution mode such as DAILY or DUTYCYCLE that advances time, or set the time (hour and sec) explicitly from the external program.
	 * <!-- end-model-doc -->
	 * @see #TIME
	 * @model name="Time"
	 * @generated
	 * @ordered
	 */
	public static final int TIME_VALUE = 2;

	/**
	 * An array of all the '<em><b>control Mode Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final controlModeType[] VALUES_ARRAY =
		new controlModeType[] {
			STATIC,
			EVENT,
			TIME,
		};

	/**
	 * A public read-only list of all the '<em><b>control Mode Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<controlModeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>control Mode Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static controlModeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			controlModeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>control Mode Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static controlModeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			controlModeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>control Mode Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static controlModeType get(int value) {
		switch (value) {
			case STATIC_VALUE: return STATIC;
			case EVENT_VALUE: return EVENT;
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
	private controlModeType(int value, String name, String literal) {
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
	
} //controlModeType
