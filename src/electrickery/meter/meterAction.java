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
 * A representation of the literals of the enumeration '<em><b>meter Action</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see electrickery.meter.MeterPackage#getmeterAction()
 * @model
 * @generated
 */
public enum meterAction implements Enumerator {
	/**
	 * The '<em><b>Clear</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLEAR_VALUE
	 * @generated
	 * @ordered
	 */
	CLEAR(0, "Clear", "Clear"),

	/**
	 * The '<em><b>Save</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SAVE_VALUE
	 * @generated
	 * @ordered
	 */
	SAVE(1, "Save", "Save"),

	/**
	 * The '<em><b>Take</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TAKE_VALUE
	 * @generated
	 * @ordered
	 */
	TAKE(2, "Take", "Take"),

	/**
	 * The '<em><b>Zonedump</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ZONEDUMP_VALUE
	 * @generated
	 * @ordered
	 */
	ZONEDUMP(3, "Zonedump", "Zonedump"),

	/**
	 * The '<em><b>Allocate</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALLOCATE_VALUE
	 * @generated
	 * @ordered
	 */
	ALLOCATE(4, "Allocate", "Allocate"),

	/**
	 * The '<em><b>Reduce</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REDUCE_VALUE
	 * @generated
	 * @ordered
	 */
	REDUCE(5, "Reduce", "Reduce");

	/**
	 * The '<em><b>Clear</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Clear</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLEAR
	 * @model name="Clear"
	 * @generated
	 * @ordered
	 */
	public static final int CLEAR_VALUE = 0;

	/**
	 * The '<em><b>Save</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Save</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SAVE
	 * @model name="Save"
	 * @generated
	 * @ordered
	 */
	public static final int SAVE_VALUE = 1;

	/**
	 * The '<em><b>Take</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Take</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TAKE
	 * @model name="Take"
	 * @generated
	 * @ordered
	 */
	public static final int TAKE_VALUE = 2;

	/**
	 * The '<em><b>Zonedump</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Zonedump</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ZONEDUMP
	 * @model name="Zonedump"
	 * @generated
	 * @ordered
	 */
	public static final int ZONEDUMP_VALUE = 3;

	/**
	 * The '<em><b>Allocate</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Allocate</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALLOCATE
	 * @model name="Allocate"
	 * @generated
	 * @ordered
	 */
	public static final int ALLOCATE_VALUE = 4;

	/**
	 * The '<em><b>Reduce</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Reduce</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REDUCE
	 * @model name="Reduce"
	 * @generated
	 * @ordered
	 */
	public static final int REDUCE_VALUE = 5;

	/**
	 * An array of all the '<em><b>meter Action</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final meterAction[] VALUES_ARRAY =
		new meterAction[] {
			CLEAR,
			SAVE,
			TAKE,
			ZONEDUMP,
			ALLOCATE,
			REDUCE,
		};

	/**
	 * A public read-only list of all the '<em><b>meter Action</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<meterAction> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>meter Action</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static meterAction get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			meterAction result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>meter Action</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static meterAction getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			meterAction result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>meter Action</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static meterAction get(int value) {
		switch (value) {
			case CLEAR_VALUE: return CLEAR;
			case SAVE_VALUE: return SAVE;
			case TAKE_VALUE: return TAKE;
			case ZONEDUMP_VALUE: return ZONEDUMP;
			case ALLOCATE_VALUE: return ALLOCATE;
			case REDUCE_VALUE: return REDUCE;
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
	private meterAction(int value, String name, String literal) {
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
	
} //meterAction
