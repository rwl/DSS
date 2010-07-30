/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common;

import electrickery.executive.Executive;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Globals</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.common.Globals#isSolutionAbort <em>Solution Abort</em>}</li>
 *   <li>{@link electrickery.common.Globals#getExecutives <em>Executives</em>}</li>
 *   <li>{@link electrickery.common.Globals#isSolutionWasAttempted <em>Solution Was Attempted</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.common.CommonPackage#getGlobals()
 * @model
 * @generated
 */
public interface Globals extends EObject {
	/**
	 * Returns the value of the '<em><b>Solution Abort</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Solution Abort</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solution Abort</em>' attribute.
	 * @see #setSolutionAbort(boolean)
	 * @see electrickery.common.CommonPackage#getGlobals_SolutionAbort()
	 * @model default="false"
	 * @generated
	 */
	boolean isSolutionAbort();

	/**
	 * Sets the value of the '{@link electrickery.common.Globals#isSolutionAbort <em>Solution Abort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Solution Abort</em>' attribute.
	 * @see #isSolutionAbort()
	 * @generated
	 */
	void setSolutionAbort(boolean value);

	/**
	 * Returns the value of the '<em><b>Executives</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link electrickery.executive.Executive#getGlobals <em>Globals</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executives</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executives</em>' reference.
	 * @see #setExecutives(Executive)
	 * @see electrickery.common.CommonPackage#getGlobals_Executives()
	 * @see electrickery.executive.Executive#getGlobals
	 * @model opposite="globals"
	 * @generated
	 */
	Executive getExecutives();

	/**
	 * Sets the value of the '{@link electrickery.common.Globals#getExecutives <em>Executives</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executives</em>' reference.
	 * @see #getExecutives()
	 * @generated
	 */
	void setExecutives(Executive value);

	/**
	 * Returns the value of the '<em><b>Solution Was Attempted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Solution Was Attempted</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solution Was Attempted</em>' attribute.
	 * @see #setSolutionWasAttempted(boolean)
	 * @see electrickery.common.CommonPackage#getGlobals_SolutionWasAttempted()
	 * @model
	 * @generated
	 */
	boolean isSolutionWasAttempted();

	/**
	 * Sets the value of the '{@link electrickery.common.Globals#isSolutionWasAttempted <em>Solution Was Attempted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Solution Was Attempted</em>' attribute.
	 * @see #isSolutionWasAttempted()
	 * @generated
	 */
	void setSolutionWasAttempted(boolean value);

} // Globals
