/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.conversion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>load Model</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.conversion.ConversionPackage#getloadModel()
 * @model
 * @generated
 */
public enum loadModel implements Enumerator {
	/**
	 * The '<em><b>PQ</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PQ_VALUE
	 * @generated
	 * @ordered
	 */
	PQ(1, "PQ", "PQ"),

	/**
	 * The '<em><b>Const Y</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONST_Y_VALUE
	 * @generated
	 * @ordered
	 */
	CONST_Y(2, "Const_Y", "Const_Y"),

	/**
	 * The '<em><b>Motor</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MOTOR_VALUE
	 * @generated
	 * @ordered
	 */
	MOTOR(3, "Motor", "Motor"),

	/**
	 * The '<em><b>Linear PQuad Q</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LINEAR_PQUAD_Q_VALUE
	 * @generated
	 * @ordered
	 */
	LINEAR_PQUAD_Q(4, "Linear_P_Quad_Q", "Linear_P_Quad_Q"),

	/**
	 * The '<em><b>Const I</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONST_I_VALUE
	 * @generated
	 * @ordered
	 */
	CONST_I(5, "Const_I", "Const_I"),

	/**
	 * The '<em><b>Const PFixed Q</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONST_PFIXED_Q_VALUE
	 * @generated
	 * @ordered
	 */
	CONST_PFIXED_Q(6, "Const_P_Fixed_Q", "Const_P_Fixed_Q"),

	/**
	 * The '<em><b>Const PFixed Z</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONST_PFIXED_Z_VALUE
	 * @generated
	 * @ordered
	 */
	CONST_PFIXED_Z(7, "Const_P_Fixed_Z", "Const_P_Fixed_Z");

	/**
	 * The '<em><b>PQ</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PQ</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PQ
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PQ_VALUE = 1;

	/**
	 * The '<em><b>Const Y</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Const Y</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONST_Y
	 * @model name="Const_Y"
	 * @generated
	 * @ordered
	 */
	public static final int CONST_Y_VALUE = 2;

	/**
	 * The '<em><b>Motor</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Motor</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MOTOR
	 * @model name="Motor"
	 * @generated
	 * @ordered
	 */
	public static final int MOTOR_VALUE = 3;

	/**
	 * The '<em><b>Linear PQuad Q</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Linear PQuad Q</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LINEAR_PQUAD_Q
	 * @model name="Linear_P_Quad_Q"
	 * @generated
	 * @ordered
	 */
	public static final int LINEAR_PQUAD_Q_VALUE = 4;

	/**
	 * The '<em><b>Const I</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Const I</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONST_I
	 * @model name="Const_I"
	 * @generated
	 * @ordered
	 */
	public static final int CONST_I_VALUE = 5;

	/**
	 * The '<em><b>Const PFixed Q</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Const PFixed Q</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONST_PFIXED_Q
	 * @model name="Const_P_Fixed_Q"
	 * @generated
	 * @ordered
	 */
	public static final int CONST_PFIXED_Q_VALUE = 6;

	/**
	 * The '<em><b>Const PFixed Z</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Const PFixed Z</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONST_PFIXED_Z
	 * @model name="Const_P_Fixed_Z"
	 * @generated
	 * @ordered
	 */
	public static final int CONST_PFIXED_Z_VALUE = 7;

	/**
	 * An array of all the '<em><b>load Model</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final loadModel[] VALUES_ARRAY =
		new loadModel[] {
			PQ,
			CONST_Y,
			MOTOR,
			LINEAR_PQUAD_Q,
			CONST_I,
			CONST_PFIXED_Q,
			CONST_PFIXED_Z,
		};

	/**
	 * A public read-only list of all the '<em><b>load Model</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<loadModel> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>load Model</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static loadModel get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			loadModel result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>load Model</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static loadModel getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			loadModel result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>load Model</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static loadModel get(int value) {
		switch (value) {
			case PQ_VALUE: return PQ;
			case CONST_Y_VALUE: return CONST_Y;
			case MOTOR_VALUE: return MOTOR;
			case LINEAR_PQUAD_Q_VALUE: return LINEAR_PQUAD_Q;
			case CONST_I_VALUE: return CONST_I;
			case CONST_PFIXED_Q_VALUE: return CONST_PFIXED_Q;
			case CONST_PFIXED_Z_VALUE: return CONST_PFIXED_Z;
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
	private loadModel(int value, String name, String literal) {
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
	
} //loadModel
