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
 * A representation of the literals of the enumeration '<em><b>export Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.executive.ExecutivePackage#getexportType()
 * @model
 * @generated
 */
public enum exportType implements Enumerator {
	/**
	 * The '<em><b>Voltages</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VOLTAGES_VALUE
	 * @generated
	 * @ordered
	 */
	VOLTAGES(0, "Voltages", "Voltages"),

	/**
	 * The '<em><b>Seq Voltages</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEQ_VOLTAGES_VALUE
	 * @generated
	 * @ordered
	 */
	SEQ_VOLTAGES(1, "SeqVoltages", "SeqVoltages"),

	/**
	 * The '<em><b>Currents</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CURRENTS_VALUE
	 * @generated
	 * @ordered
	 */
	CURRENTS(2, "Currents", "Currents"),

	/**
	 * The '<em><b>Overloads</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OVERLOADS_VALUE
	 * @generated
	 * @ordered
	 */
	OVERLOADS(3, "Overloads", "Overloads"),

	/**
	 * The '<em><b>Unserved</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNSERVED_VALUE
	 * @generated
	 * @ordered
	 */
	UNSERVED(4, "Unserved", "Unserved"),

	/**
	 * The '<em><b>Seq Currents</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEQ_CURRENTS_VALUE
	 * @generated
	 * @ordered
	 */
	SEQ_CURRENTS(5, "SeqCurrents", "SeqCurrents"),

	/**
	 * The '<em><b>Powers</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POWERS_VALUE
	 * @generated
	 * @ordered
	 */
	POWERS(6, "Powers", "Powers"),

	/**
	 * The '<em><b>Fault Study</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FAULT_STUDY_VALUE
	 * @generated
	 * @ordered
	 */
	FAULT_STUDY(7, "FaultStudy", "FaultStudy"),

	/**
	 * The '<em><b>Generators</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATORS_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATORS(8, "Generators", "Generators"),

	/**
	 * The '<em><b>Loads</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOADS_VALUE
	 * @generated
	 * @ordered
	 */
	LOADS(9, "Loads", "Loads"),

	/**
	 * The '<em><b>Meters</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #METERS_VALUE
	 * @generated
	 * @ordered
	 */
	METERS(10, "Meters", "Meters"),

	/**
	 * The '<em><b>Monitors</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MONITORS_VALUE
	 * @generated
	 * @ordered
	 */
	MONITORS(11, "Monitors", "Monitors"),

	/**
	 * The '<em><b>Yprims</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #YPRIMS_VALUE
	 * @generated
	 * @ordered
	 */
	YPRIMS(12, "Yprims", "Yprims"),

	/**
	 * The '<em><b>Y</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #Y_VALUE
	 * @generated
	 * @ordered
	 */
	Y(13, "Y", "Y");

	/**
	 * The '<em><b>Voltages</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Voltages</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VOLTAGES
	 * @model name="Voltages"
	 * @generated
	 * @ordered
	 */
	public static final int VOLTAGES_VALUE = 0;

	/**
	 * The '<em><b>Seq Voltages</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Seq Voltages</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SEQ_VOLTAGES
	 * @model name="SeqVoltages"
	 * @generated
	 * @ordered
	 */
	public static final int SEQ_VOLTAGES_VALUE = 1;

	/**
	 * The '<em><b>Currents</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Currents</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CURRENTS
	 * @model name="Currents"
	 * @generated
	 * @ordered
	 */
	public static final int CURRENTS_VALUE = 2;

	/**
	 * The '<em><b>Overloads</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Overloads</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OVERLOADS
	 * @model name="Overloads"
	 * @generated
	 * @ordered
	 */
	public static final int OVERLOADS_VALUE = 3;

	/**
	 * The '<em><b>Unserved</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unserved</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSERVED
	 * @model name="Unserved"
	 * @generated
	 * @ordered
	 */
	public static final int UNSERVED_VALUE = 4;

	/**
	 * The '<em><b>Seq Currents</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Seq Currents</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SEQ_CURRENTS
	 * @model name="SeqCurrents"
	 * @generated
	 * @ordered
	 */
	public static final int SEQ_CURRENTS_VALUE = 5;

	/**
	 * The '<em><b>Powers</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Powers</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POWERS
	 * @model name="Powers"
	 * @generated
	 * @ordered
	 */
	public static final int POWERS_VALUE = 6;

	/**
	 * The '<em><b>Fault Study</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Fault Study</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FAULT_STUDY
	 * @model name="FaultStudy"
	 * @generated
	 * @ordered
	 */
	public static final int FAULT_STUDY_VALUE = 7;

	/**
	 * The '<em><b>Generators</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Generators</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATORS
	 * @model name="Generators"
	 * @generated
	 * @ordered
	 */
	public static final int GENERATORS_VALUE = 8;

	/**
	 * The '<em><b>Loads</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Loads</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOADS
	 * @model name="Loads"
	 * @generated
	 * @ordered
	 */
	public static final int LOADS_VALUE = 9;

	/**
	 * The '<em><b>Meters</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Meters</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #METERS
	 * @model name="Meters"
	 * @generated
	 * @ordered
	 */
	public static final int METERS_VALUE = 10;

	/**
	 * The '<em><b>Monitors</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Monitors</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MONITORS
	 * @model name="Monitors"
	 * @generated
	 * @ordered
	 */
	public static final int MONITORS_VALUE = 11;

	/**
	 * The '<em><b>Yprims</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Yprims</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #YPRIMS
	 * @model name="Yprims"
	 * @generated
	 * @ordered
	 */
	public static final int YPRIMS_VALUE = 12;

	/**
	 * The '<em><b>Y</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Y</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #Y
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int Y_VALUE = 13;

	/**
	 * An array of all the '<em><b>export Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final exportType[] VALUES_ARRAY =
		new exportType[] {
			VOLTAGES,
			SEQ_VOLTAGES,
			CURRENTS,
			OVERLOADS,
			UNSERVED,
			SEQ_CURRENTS,
			POWERS,
			FAULT_STUDY,
			GENERATORS,
			LOADS,
			METERS,
			MONITORS,
			YPRIMS,
			Y,
		};

	/**
	 * A public read-only list of all the '<em><b>export Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<exportType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>export Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static exportType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			exportType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>export Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static exportType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			exportType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>export Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static exportType get(int value) {
		switch (value) {
			case VOLTAGES_VALUE: return VOLTAGES;
			case SEQ_VOLTAGES_VALUE: return SEQ_VOLTAGES;
			case CURRENTS_VALUE: return CURRENTS;
			case OVERLOADS_VALUE: return OVERLOADS;
			case UNSERVED_VALUE: return UNSERVED;
			case SEQ_CURRENTS_VALUE: return SEQ_CURRENTS;
			case POWERS_VALUE: return POWERS;
			case FAULT_STUDY_VALUE: return FAULT_STUDY;
			case GENERATORS_VALUE: return GENERATORS;
			case LOADS_VALUE: return LOADS;
			case METERS_VALUE: return METERS;
			case MONITORS_VALUE: return MONITORS;
			case YPRIMS_VALUE: return YPRIMS;
			case Y_VALUE: return Y;
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
	private exportType(int value, String name, String literal) {
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
	
} //exportType
