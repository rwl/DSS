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
 * A representation of the literals of the enumeration '<em><b>reset Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.executive.ExecutivePackage#getresetType()
 * @model
 * @generated
 */
public enum resetType implements Enumerator {
	/**
	 * The '<em><b>MO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MO_VALUE
	 * @generated
	 * @ordered
	 */
	MO(0, "MO", "MO"),

	/**
	 * The '<em><b>ME</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ME_VALUE
	 * @generated
	 * @ordered
	 */
	ME(1, "ME", "ME"),

	/**
	 * The '<em><b>Faults</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FAULTS_VALUE
	 * @generated
	 * @ordered
	 */
	FAULTS(3, "Faults", "Faults"),

	/**
	 * The '<em><b>Controls</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTROLS_VALUE
	 * @generated
	 * @ordered
	 */
	CONTROLS(4, "Controls", "Controls"),

	/**
	 * The '<em><b>Event Log</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EVENT_LOG_VALUE
	 * @generated
	 * @ordered
	 */
	EVENT_LOG(5, "EventLog", "EventLog"),

	/**
	 * The '<em><b>Keep List</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KEEP_LIST_VALUE
	 * @generated
	 * @ordered
	 */
	KEEP_LIST(6, "KeepList", "KeepList"),

	/**
	 * The '<em><b>All</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL_VALUE
	 * @generated
	 * @ordered
	 */
	ALL(7, "All", "All");

	/**
	 * The '<em><b>MO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Monitors
	 * <!-- end-model-doc -->
	 * @see #MO
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MO_VALUE = 0;

	/**
	 * The '<em><b>ME</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Meters
	 * <!-- end-model-doc -->
	 * @see #ME
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ME_VALUE = 1;

	/**
	 * The '<em><b>Faults</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Faults</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FAULTS
	 * @model name="Faults"
	 * @generated
	 * @ordered
	 */
	public static final int FAULTS_VALUE = 3;

	/**
	 * The '<em><b>Controls</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Controls</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTROLS
	 * @model name="Controls"
	 * @generated
	 * @ordered
	 */
	public static final int CONTROLS_VALUE = 4;

	/**
	 * The '<em><b>Event Log</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Event Log</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EVENT_LOG
	 * @model name="EventLog"
	 * @generated
	 * @ordered
	 */
	public static final int EVENT_LOG_VALUE = 5;

	/**
	 * The '<em><b>Keep List</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Keep List</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KEEP_LIST
	 * @model name="KeepList"
	 * @generated
	 * @ordered
	 */
	public static final int KEEP_LIST_VALUE = 6;

	/**
	 * The '<em><b>All</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>All</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALL
	 * @model name="All"
	 * @generated
	 * @ordered
	 */
	public static final int ALL_VALUE = 7;

	/**
	 * An array of all the '<em><b>reset Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final resetType[] VALUES_ARRAY =
		new resetType[] {
			MO,
			ME,
			FAULTS,
			CONTROLS,
			EVENT_LOG,
			KEEP_LIST,
			ALL,
		};

	/**
	 * A public read-only list of all the '<em><b>reset Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<resetType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>reset Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static resetType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			resetType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>reset Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static resetType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			resetType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>reset Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static resetType get(int value) {
		switch (value) {
			case MO_VALUE: return MO;
			case ME_VALUE: return ME;
			case FAULTS_VALUE: return FAULTS;
			case CONTROLS_VALUE: return CONTROLS;
			case EVENT_LOG_VALUE: return EVENT_LOG;
			case KEEP_LIST_VALUE: return KEEP_LIST;
			case ALL_VALUE: return ALL;
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
	private resetType(int value, String name, String literal) {
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
	
} //resetType
