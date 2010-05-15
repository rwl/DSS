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
 * A representation of the literals of the enumeration '<em><b>yBuild Option</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.common.CommonPackage#getyBuildOption()
 * @model
 * @generated
 */
public enum yBuildOption implements Enumerator {
	/**
	 * The '<em><b>Series Only</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SERIES_ONLY_VALUE
	 * @generated
	 * @ordered
	 */
	SERIES_ONLY(1, "SeriesOnly", "SeriesOnly"),

	/**
	 * The '<em><b>Whole Matrix</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WHOLE_MATRIX_VALUE
	 * @generated
	 * @ordered
	 */
	WHOLE_MATRIX(2, "WholeMatrix", "WholeMatrix");

	/**
	 * The '<em><b>Series Only</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Series Only</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SERIES_ONLY
	 * @model name="SeriesOnly"
	 * @generated
	 * @ordered
	 */
	public static final int SERIES_ONLY_VALUE = 1;

	/**
	 * The '<em><b>Whole Matrix</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Whole Matrix</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WHOLE_MATRIX
	 * @model name="WholeMatrix"
	 * @generated
	 * @ordered
	 */
	public static final int WHOLE_MATRIX_VALUE = 2;

	/**
	 * An array of all the '<em><b>yBuild Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final yBuildOption[] VALUES_ARRAY =
		new yBuildOption[] {
			SERIES_ONLY,
			WHOLE_MATRIX,
		};

	/**
	 * A public read-only list of all the '<em><b>yBuild Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<yBuildOption> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>yBuild Option</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static yBuildOption get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			yBuildOption result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>yBuild Option</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static yBuildOption getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			yBuildOption result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>yBuild Option</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static yBuildOption get(int value) {
		switch (value) {
			case SERIES_ONLY_VALUE: return SERIES_ONLY;
			case WHOLE_MATRIX_VALUE: return WHOLE_MATRIX;
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
	private yBuildOption(int value, String name, String literal) {
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
	
} //yBuildOption
