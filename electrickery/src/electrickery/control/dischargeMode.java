/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.control;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>discharge Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see electrickery.control.ControlPackage#getdischargeMode()
 * @model
 * @generated
 */
public enum dischargeMode implements Enumerator {
	/**
	 * The '<em><b>Peak Shave</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PEAK_SHAVE_VALUE
	 * @generated
	 * @ordered
	 */
	PEAK_SHAVE(0, "peakShave", "peakShave"),

	/**
	 * The '<em><b>Follow</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FOLLOW_VALUE
	 * @generated
	 * @ordered
	 */
	FOLLOW(1, "follow", "follow"),

	/**
	 * The '<em><b>Support</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUPPORT_VALUE
	 * @generated
	 * @ordered
	 */
	SUPPORT(2, "support", "support"),

	/**
	 * The '<em><b>Load Shape</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOAD_SHAPE_VALUE
	 * @generated
	 * @ordered
	 */
	LOAD_SHAPE(3, "loadShape", "loadShape"),

	/**
	 * The '<em><b>Time</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIME_VALUE
	 * @generated
	 * @ordered
	 */
	TIME(4, "time", "time");

	/**
	 * The '<em><b>Peak Shave</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Peak Shave</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PEAK_SHAVE
	 * @model name="peakShave"
	 * @generated
	 * @ordered
	 */
	public static final int PEAK_SHAVE_VALUE = 0;

	/**
	 * The '<em><b>Follow</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Follow</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FOLLOW
	 * @model name="follow"
	 * @generated
	 * @ordered
	 */
	public static final int FOLLOW_VALUE = 1;

	/**
	 * The '<em><b>Support</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Support</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUPPORT
	 * @model name="support"
	 * @generated
	 * @ordered
	 */
	public static final int SUPPORT_VALUE = 2;

	/**
	 * The '<em><b>Load Shape</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Load Shape</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOAD_SHAPE
	 * @model name="loadShape"
	 * @generated
	 * @ordered
	 */
	public static final int LOAD_SHAPE_VALUE = 3;

	/**
	 * The '<em><b>Time</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Time</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIME
	 * @model name="time"
	 * @generated
	 * @ordered
	 */
	public static final int TIME_VALUE = 4;

	/**
	 * An array of all the '<em><b>discharge Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final dischargeMode[] VALUES_ARRAY =
		new dischargeMode[] {
			PEAK_SHAVE,
			FOLLOW,
			SUPPORT,
			LOAD_SHAPE,
			TIME,
		};

	/**
	 * A public read-only list of all the '<em><b>discharge Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<dischargeMode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>discharge Mode</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static dischargeMode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			dischargeMode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>discharge Mode</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static dischargeMode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			dischargeMode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>discharge Mode</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static dischargeMode get(int value) {
		switch (value) {
			case PEAK_SHAVE_VALUE: return PEAK_SHAVE;
			case FOLLOW_VALUE: return FOLLOW;
			case SUPPORT_VALUE: return SUPPORT;
			case LOAD_SHAPE_VALUE: return LOAD_SHAPE;
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
	private dischargeMode(int value, String name, String literal) {
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
	
} //dischargeMode
