/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>connection Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.common.CommonPackage#getconnectionType()
 * @model
 * @generated
 */
public enum connectionType implements Enumerator {
	/**
	 * The '<em><b>Wye</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WYE_VALUE
	 * @generated
	 * @ordered
	 */
	WYE(0, "Wye", "Wye"),

	/**
	 * The '<em><b>LN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LN_VALUE
	 * @generated
	 * @ordered
	 */
	LN(1, "LN", "LN"),

	/**
	 * The '<em><b>Delta</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELTA_VALUE
	 * @generated
	 * @ordered
	 */
	DELTA(2, "Delta", "Delta"),

	/**
	 * The '<em><b>LL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LL_VALUE
	 * @generated
	 * @ordered
	 */
	LL(3, "LL", "LL");

	/**
	 * The '<em><b>Wye</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Wye</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WYE
	 * @model name="Wye"
	 * @generated
	 * @ordered
	 */
	public static final int WYE_VALUE = 0;

	/**
	 * The '<em><b>LN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LN_VALUE = 1;

	/**
	 * The '<em><b>Delta</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Delta</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELTA
	 * @model name="Delta"
	 * @generated
	 * @ordered
	 */
	public static final int DELTA_VALUE = 2;

	/**
	 * The '<em><b>LL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LL_VALUE = 3;

	/**
	 * An array of all the '<em><b>connection Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final connectionType[] VALUES_ARRAY =
		new connectionType[] {
			WYE,
			LN,
			DELTA,
			LL,
		};

	/**
	 * A public read-only list of all the '<em><b>connection Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<connectionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>connection Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static connectionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			connectionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>connection Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static connectionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			connectionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>connection Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static connectionType get(int value) {
		switch (value) {
			case WYE_VALUE: return WYE;
			case LN_VALUE: return LN;
			case DELTA_VALUE: return DELTA;
			case LL_VALUE: return LL;
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
	private connectionType(int value, String name, String literal) {
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
	
} //connectionType
