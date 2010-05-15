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
 * A representation of the literals of the enumeration '<em><b>length Unit</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.common.CommonPackage#getlengthUnit()
 * @model
 * @generated
 */
public enum lengthUnit implements Enumerator {
	/**
	 * The '<em><b>None</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_VALUE
	 * @generated
	 * @ordered
	 */
	NONE(0, "none", "none"),

	/**
	 * The '<em><b>Mi</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MI_VALUE
	 * @generated
	 * @ordered
	 */
	MI(1, "mi", "mi"),

	/**
	 * The '<em><b>Km</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KM_VALUE
	 * @generated
	 * @ordered
	 */
	KM(2, "km", "km"),

	/**
	 * The '<em><b>Kft</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KFT_VALUE
	 * @generated
	 * @ordered
	 */
	KFT(3, "kft", "kft"),

	/**
	 * The '<em><b>M</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #M_VALUE
	 * @generated
	 * @ordered
	 */
	M(4, "m", "m"),

	/**
	 * The '<em><b>Me</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ME_VALUE
	 * @generated
	 * @ordered
	 */
	ME(5, "me", "me"),

	/**
	 * The '<em><b>Ft</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FT_VALUE
	 * @generated
	 * @ordered
	 */
	FT(6, "ft", "ft"),

	/**
	 * The '<em><b>In</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IN_VALUE
	 * @generated
	 * @ordered
	 */
	IN(7, "in", "in"),

	/**
	 * The '<em><b>Cm</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CM_VALUE
	 * @generated
	 * @ordered
	 */
	CM(8, "cm", "cm");

	/**
	 * The '<em><b>None</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @model name="none"
	 * @generated
	 * @ordered
	 */
	public static final int NONE_VALUE = 0;

	/**
	 * The '<em><b>Mi</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Mi</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MI
	 * @model name="mi"
	 * @generated
	 * @ordered
	 */
	public static final int MI_VALUE = 1;

	/**
	 * The '<em><b>Km</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Km</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KM
	 * @model name="km"
	 * @generated
	 * @ordered
	 */
	public static final int KM_VALUE = 2;

	/**
	 * The '<em><b>Kft</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Kft</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KFT
	 * @model name="kft"
	 * @generated
	 * @ordered
	 */
	public static final int KFT_VALUE = 3;

	/**
	 * The '<em><b>M</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>M</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #M
	 * @model name="m"
	 * @generated
	 * @ordered
	 */
	public static final int M_VALUE = 4;

	/**
	 * The '<em><b>Me</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Me</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ME
	 * @model name="me"
	 * @generated
	 * @ordered
	 */
	public static final int ME_VALUE = 5;

	/**
	 * The '<em><b>Ft</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Ft</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FT
	 * @model name="ft"
	 * @generated
	 * @ordered
	 */
	public static final int FT_VALUE = 6;

	/**
	 * The '<em><b>In</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>In</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IN
	 * @model name="in"
	 * @generated
	 * @ordered
	 */
	public static final int IN_VALUE = 7;

	/**
	 * The '<em><b>Cm</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cm</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CM
	 * @model name="cm"
	 * @generated
	 * @ordered
	 */
	public static final int CM_VALUE = 8;

	/**
	 * An array of all the '<em><b>length Unit</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final lengthUnit[] VALUES_ARRAY =
		new lengthUnit[] {
			NONE,
			MI,
			KM,
			KFT,
			M,
			ME,
			FT,
			IN,
			CM,
		};

	/**
	 * A public read-only list of all the '<em><b>length Unit</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<lengthUnit> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>length Unit</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static lengthUnit get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			lengthUnit result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>length Unit</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static lengthUnit getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			lengthUnit result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>length Unit</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static lengthUnit get(int value) {
		switch (value) {
			case NONE_VALUE: return NONE;
			case MI_VALUE: return MI;
			case KM_VALUE: return KM;
			case KFT_VALUE: return KFT;
			case M_VALUE: return M;
			case ME_VALUE: return ME;
			case FT_VALUE: return FT;
			case IN_VALUE: return IN;
			case CM_VALUE: return CM;
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
	private lengthUnit(int value, String name, String literal) {
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
	
} //lengthUnit
