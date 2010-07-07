/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.executive;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>solution Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see electrickery.executive.ExecutivePackage#getsolutionMode()
 * @model
 * @generated
 */
public enum solutionMode implements Enumerator {
	/**
	 * The '<em><b>Snapshot</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SNAPSHOT_VALUE
	 * @generated
	 * @ordered
	 */
	SNAPSHOT(0, "Snapshot", "Snapshot"),

	/**
	 * The '<em><b>Daily</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DAILY_VALUE
	 * @generated
	 * @ordered
	 */
	DAILY(1, "Daily", "Daily"),

	/**
	 * The '<em><b>Direct</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIRECT_VALUE
	 * @generated
	 * @ordered
	 */
	DIRECT(2, "Direct", "Direct"),

	/**
	 * The '<em><b>Dutycycle</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DUTYCYCLE_VALUE
	 * @generated
	 * @ordered
	 */
	DUTYCYCLE(3, "Dutycycle", "Dutycycle"),

	/**
	 * The '<em><b>Dynamic</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DYNAMIC_VALUE
	 * @generated
	 * @ordered
	 */
	DYNAMIC(4, "Dynamic", "Dynamic"),

	/**
	 * The '<em><b>Harmonic</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HARMONIC_VALUE
	 * @generated
	 * @ordered
	 */
	HARMONIC(5, "Harmonic", "Harmonic"),

	/**
	 * The '<em><b>Monte Carlo1</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MONTE_CARLO1_VALUE
	 * @generated
	 * @ordered
	 */
	MONTE_CARLO1(6, "MonteCarlo1", "MonteCarlo1"), /**
	 * The '<em><b>Monte Carlo2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MONTE_CARLO2_VALUE
	 * @generated
	 * @ordered
	 */
	MONTE_CARLO2(7, "MonteCarlo2", "MonteCarlo2"), /**
	 * The '<em><b>Monte Carlo3</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MONTE_CARLO3_VALUE
	 * @generated
	 * @ordered
	 */
	MONTE_CARLO3(8, "MonteCarlo3", "MonteCarlo3"), /**
	 * The '<em><b>Fault Study</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FAULT_STUDY_VALUE
	 * @generated
	 * @ordered
	 */
	FAULT_STUDY(9, "FaultStudy", "FaultStudy"),

	/**
	 * The '<em><b>Yearly</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #YEARLY_VALUE
	 * @generated
	 * @ordered
	 */
	YEARLY(10, "Yearly", "Yearly"),

	/**
	 * The '<em><b>Monte Fault</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MONTE_FAULT_VALUE
	 * @generated
	 * @ordered
	 */
	MONTE_FAULT(11, "MonteFault", "MonteFault"), /**
	 * The '<em><b>Peakday</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PEAKDAY_VALUE
	 * @generated
	 * @ordered
	 */
	PEAKDAY(12, "Peakday", "Peakday"),

	/**
	 * The '<em><b>Load Duration1</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOAD_DURATION1_VALUE
	 * @generated
	 * @ordered
	 */
	LOAD_DURATION1(13, "LoadDuration1", "LoadDuration1"), /**
	 * The '<em><b>Load Duration2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOAD_DURATION2_VALUE
	 * @generated
	 * @ordered
	 */
	LOAD_DURATION2(14, "LoadDuration2", "LoadDuration2"), /**
	 * The '<em><b>Auto Add</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTO_ADD_VALUE
	 * @generated
	 * @ordered
	 */
	AUTO_ADD(15, "AutoAdd", "AutoAdd");

	/**
	 * The '<em><b>Snapshot</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Snapshot</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SNAPSHOT
	 * @model name="Snapshot"
	 * @generated
	 * @ordered
	 */
	public static final int SNAPSHOT_VALUE = 0;

	/**
	 * The '<em><b>Daily</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Daily</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DAILY
	 * @model name="Daily"
	 * @generated
	 * @ordered
	 */
	public static final int DAILY_VALUE = 1;

	/**
	 * The '<em><b>Direct</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Direct</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIRECT
	 * @model name="Direct"
	 * @generated
	 * @ordered
	 */
	public static final int DIRECT_VALUE = 2;

	/**
	 * The '<em><b>Dutycycle</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Dutycycle</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DUTYCYCLE
	 * @model name="Dutycycle"
	 * @generated
	 * @ordered
	 */
	public static final int DUTYCYCLE_VALUE = 3;

	/**
	 * The '<em><b>Dynamic</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Dynamic</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DYNAMIC
	 * @model name="Dynamic"
	 * @generated
	 * @ordered
	 */
	public static final int DYNAMIC_VALUE = 4;

	/**
	 * The '<em><b>Harmonic</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Harmonic</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HARMONIC
	 * @model name="Harmonic"
	 * @generated
	 * @ordered
	 */
	public static final int HARMONIC_VALUE = 5;

	/**
	 * The '<em><b>Monte Carlo1</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Monte Carlo1</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MONTE_CARLO1
	 * @model name="MonteCarlo1"
	 * @generated
	 * @ordered
	 */
	public static final int MONTE_CARLO1_VALUE = 6;

	/**
	 * The '<em><b>Monte Carlo2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Monte Carlo2</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MONTE_CARLO2
	 * @model name="MonteCarlo2"
	 * @generated
	 * @ordered
	 */
	public static final int MONTE_CARLO2_VALUE = 7;

	/**
	 * The '<em><b>Monte Carlo3</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Monte Carlo3</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MONTE_CARLO3
	 * @model name="MonteCarlo3"
	 * @generated
	 * @ordered
	 */
	public static final int MONTE_CARLO3_VALUE = 8;

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
	public static final int FAULT_STUDY_VALUE = 9;

	/**
	 * The '<em><b>Yearly</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Follow yearly curve.
	 * <!-- end-model-doc -->
	 * @see #YEARLY
	 * @model name="Yearly"
	 * @generated
	 * @ordered
	 */
	public static final int YEARLY_VALUE = 10;

	/**
	 * The '<em><b>Monte Fault</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Monte Carlo fault study.
	 * <!-- end-model-doc -->
	 * @see #MONTE_FAULT
	 * @model name="MonteFault"
	 * @generated
	 * @ordered
	 */
	public static final int MONTE_FAULT_VALUE = 11;

	/**
	 * The '<em><b>Peakday</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Peakday</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PEAKDAY
	 * @model name="Peakday"
	 * @generated
	 * @ordered
	 */
	public static final int PEAKDAY_VALUE = 12;

	/**
	 * The '<em><b>Load Duration1</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Load Duration1</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOAD_DURATION1
	 * @model name="LoadDuration1"
	 * @generated
	 * @ordered
	 */
	public static final int LOAD_DURATION1_VALUE = 13;

	/**
	 * The '<em><b>Load Duration2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Load Duration2</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOAD_DURATION2
	 * @model name="LoadDuration2"
	 * @generated
	 * @ordered
	 */
	public static final int LOAD_DURATION2_VALUE = 14;

	/**
	 * The '<em><b>Auto Add</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * See AddType.
	 * <!-- end-model-doc -->
	 * @see #AUTO_ADD
	 * @model name="AutoAdd"
	 * @generated
	 * @ordered
	 */
	public static final int AUTO_ADD_VALUE = 15;

	/**
	 * An array of all the '<em><b>solution Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final solutionMode[] VALUES_ARRAY =
		new solutionMode[] {
			SNAPSHOT,
			DAILY,
			DIRECT,
			DUTYCYCLE,
			DYNAMIC,
			HARMONIC,
			MONTE_CARLO1,
			MONTE_CARLO2,
			MONTE_CARLO3,
			FAULT_STUDY,
			YEARLY,
			MONTE_FAULT,
			PEAKDAY,
			LOAD_DURATION1,
			LOAD_DURATION2,
			AUTO_ADD,
		};

	/**
	 * A public read-only list of all the '<em><b>solution Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<solutionMode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>solution Mode</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static solutionMode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			solutionMode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>solution Mode</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static solutionMode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			solutionMode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>solution Mode</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static solutionMode get(int value) {
		switch (value) {
			case SNAPSHOT_VALUE: return SNAPSHOT;
			case DAILY_VALUE: return DAILY;
			case DIRECT_VALUE: return DIRECT;
			case DUTYCYCLE_VALUE: return DUTYCYCLE;
			case DYNAMIC_VALUE: return DYNAMIC;
			case HARMONIC_VALUE: return HARMONIC;
			case MONTE_CARLO1_VALUE: return MONTE_CARLO1;
			case MONTE_CARLO2_VALUE: return MONTE_CARLO2;
			case MONTE_CARLO3_VALUE: return MONTE_CARLO3;
			case FAULT_STUDY_VALUE: return FAULT_STUDY;
			case YEARLY_VALUE: return YEARLY;
			case MONTE_FAULT_VALUE: return MONTE_FAULT;
			case PEAKDAY_VALUE: return PEAKDAY;
			case LOAD_DURATION1_VALUE: return LOAD_DURATION1;
			case LOAD_DURATION2_VALUE: return LOAD_DURATION2;
			case AUTO_ADD_VALUE: return AUTO_ADD;
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
	private solutionMode(int value, String name, String literal) {
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
	
} //solutionMode
