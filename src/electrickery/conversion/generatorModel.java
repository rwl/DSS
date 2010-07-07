/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>generator Model</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see electrickery.conversion.ConversionPackage#getgeneratorModel()
 * @model
 * @generated
 */
public enum generatorModel implements Enumerator {
	/**
	 * The '<em><b>Constant kW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONSTANT_KW_VALUE
	 * @generated
	 * @ordered
	 */
	CONSTANT_KW(1, "Constant_kW", "Constant_kW"),

	/**
	 * The '<em><b>Constant Y</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONSTANT_Y_VALUE
	 * @generated
	 * @ordered
	 */
	CONSTANT_Y(2, "Constant_Y", "Constant_Y"),

	/**
	 * The '<em><b>Constant kW and kV</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONSTANT_KW_AND_KV_VALUE
	 * @generated
	 * @ordered
	 */
	CONSTANT_KW_AND_KV(3, "Constant_kW_and_kV", "Constant_kW_and_kV"),

	/**
	 * The '<em><b>Const kW Fixed Q</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONST_KW_FIXED_Q_VALUE
	 * @generated
	 * @ordered
	 */
	CONST_KW_FIXED_Q(4, "Const_kW_Fixed_Q", "Const_kW_Fixed_Q"),

	/**
	 * The '<em><b>Const kW Fixed QConst X</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONST_KW_FIXED_QCONST_X_VALUE
	 * @generated
	 * @ordered
	 */
	CONST_KW_FIXED_QCONST_X(5, "Const_kW_Fixed_Q_Const_X", "Const_kW_Fixed_Q_Const_X"),

	/**
	 * The '<em><b>User model</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USER_MODEL_VALUE
	 * @generated
	 * @ordered
	 */
	USER_MODEL(6, "User_model", "User_model"),

	/**
	 * The '<em><b>Const kW KV Ar Limited I</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONST_KW_KV_AR_LIMITED_I_VALUE
	 * @generated
	 * @ordered
	 */
	CONST_KW_KV_AR_LIMITED_I(7, "Const_kW_KVAr_Limited_I", "Const_kW_KVAr_Limited_I");

	/**
	 * The '<em><b>Constant kW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Constant kW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONSTANT_KW
	 * @model name="Constant_kW"
	 * @generated
	 * @ordered
	 */
	public static final int CONSTANT_KW_VALUE = 1;

	/**
	 * The '<em><b>Constant Y</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Constant Y</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONSTANT_Y
	 * @model name="Constant_Y"
	 * @generated
	 * @ordered
	 */
	public static final int CONSTANT_Y_VALUE = 2;

	/**
	 * The '<em><b>Constant kW and kV</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Constant kW and kV</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONSTANT_KW_AND_KV
	 * @model name="Constant_kW_and_kV"
	 * @generated
	 * @ordered
	 */
	public static final int CONSTANT_KW_AND_KV_VALUE = 3;

	/**
	 * The '<em><b>Const kW Fixed Q</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Const kW Fixed Q</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONST_KW_FIXED_Q
	 * @model name="Const_kW_Fixed_Q"
	 * @generated
	 * @ordered
	 */
	public static final int CONST_KW_FIXED_Q_VALUE = 4;

	/**
	 * The '<em><b>Const kW Fixed QConst X</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Const kW Fixed QConst X</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONST_KW_FIXED_QCONST_X
	 * @model name="Const_kW_Fixed_Q_Const_X"
	 * @generated
	 * @ordered
	 */
	public static final int CONST_KW_FIXED_QCONST_X_VALUE = 5;

	/**
	 * The '<em><b>User model</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>User model</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USER_MODEL
	 * @model name="User_model"
	 * @generated
	 * @ordered
	 */
	public static final int USER_MODEL_VALUE = 6;

	/**
	 * The '<em><b>Const kW KV Ar Limited I</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Const kW KV Ar Limited I</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONST_KW_KV_AR_LIMITED_I
	 * @model name="Const_kW_KVAr_Limited_I"
	 * @generated
	 * @ordered
	 */
	public static final int CONST_KW_KV_AR_LIMITED_I_VALUE = 7;

	/**
	 * An array of all the '<em><b>generator Model</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final generatorModel[] VALUES_ARRAY =
		new generatorModel[] {
			CONSTANT_KW,
			CONSTANT_Y,
			CONSTANT_KW_AND_KV,
			CONST_KW_FIXED_Q,
			CONST_KW_FIXED_QCONST_X,
			USER_MODEL,
			CONST_KW_KV_AR_LIMITED_I,
		};

	/**
	 * A public read-only list of all the '<em><b>generator Model</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<generatorModel> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>generator Model</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static generatorModel get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			generatorModel result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>generator Model</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static generatorModel getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			generatorModel result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>generator Model</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static generatorModel get(int value) {
		switch (value) {
			case CONSTANT_KW_VALUE: return CONSTANT_KW;
			case CONSTANT_Y_VALUE: return CONSTANT_Y;
			case CONSTANT_KW_AND_KV_VALUE: return CONSTANT_KW_AND_KV;
			case CONST_KW_FIXED_Q_VALUE: return CONST_KW_FIXED_Q;
			case CONST_KW_FIXED_QCONST_X_VALUE: return CONST_KW_FIXED_QCONST_X;
			case USER_MODEL_VALUE: return USER_MODEL;
			case CONST_KW_KV_AR_LIMITED_I_VALUE: return CONST_KW_KV_AR_LIMITED_I;
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
	private generatorModel(int value, String name, String literal) {
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
	
} //generatorModel
