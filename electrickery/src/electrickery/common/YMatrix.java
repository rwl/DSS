/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YMatrix</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.common.YMatrix#getCircuit <em>Circuit</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.common.CommonPackage#getYMatrix()
 * @model
 * @generated
 */
public interface YMatrix extends EObject {
	/**
	 * Returns the value of the '<em><b>Circuit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Circuit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Circuit</em>' reference.
	 * @see #setCircuit(Circuit)
	 * @see electrickery.common.CommonPackage#getYMatrix_Circuit()
	 * @model
	 * @generated
	 */
	Circuit getCircuit();

	/**
	 * Sets the value of the '{@link electrickery.common.YMatrix#getCircuit <em>Circuit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Circuit</em>' reference.
	 * @see #getCircuit()
	 * @generated
	 */
	void setCircuit(Circuit value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Builds designated Y matrix for system and allocates solution arrays.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void buildYMatrix(yBuildOption buildOption, boolean allocateVI);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void reCalcAllYPrims();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void reCalcInvalidYPrims();

} // YMatrix
