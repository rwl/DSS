/**
 * Copyright (C) 2010 Richard Lincoln
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
 * A representation of the literals of the enumeration '<em><b>algorithm Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see dss.common.CommonPackage#getalgorithmType()
 * @model
 * @generated
 */
public enum algorithmType implements Enumerator {
	/**
	 * The '<em><b>Normal Solve</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NORMAL_SOLVE_VALUE
	 * @generated
	 * @ordered
	 */
	NORMAL_SOLVE(0, "NormalSolve", "NormalSolve"),

	/**
	 * The '<em><b>Newton Solve</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEWTON_SOLVE_VALUE
	 * @generated
	 * @ordered
	 */
	NEWTON_SOLVE(1, "NewtonSolve", "NewtonSolve");

	/**
	 * The '<em><b>Normal Solve</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Normal Solve</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NORMAL_SOLVE
	 * @model name="NormalSolve"
	 * @generated
	 * @ordered
	 */
	public static final int NORMAL_SOLVE_VALUE = 0;

	/**
	 * The '<em><b>Newton Solve</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Newton Solve</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEWTON_SOLVE
	 * @model name="NewtonSolve"
	 * @generated
	 * @ordered
	 */
	public static final int NEWTON_SOLVE_VALUE = 1;

	/**
	 * An array of all the '<em><b>algorithm Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final algorithmType[] VALUES_ARRAY =
		new algorithmType[] {
			NORMAL_SOLVE,
			NEWTON_SOLVE,
		};

	/**
	 * A public read-only list of all the '<em><b>algorithm Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<algorithmType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>algorithm Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static algorithmType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			algorithmType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>algorithm Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static algorithmType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			algorithmType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>algorithm Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static algorithmType get(int value) {
		switch (value) {
			case NORMAL_SOLVE_VALUE: return NORMAL_SOLVE;
			case NEWTON_SOLVE_VALUE: return NEWTON_SOLVE;
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
	private algorithmType(int value, String name, String literal) {
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
	
} //algorithmType
