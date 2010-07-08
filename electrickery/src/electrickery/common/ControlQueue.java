/**
 * Copyright (C) 2010 Richard Lincoln
 *
 * $Id$
 */
package electrickery.common;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Control Queue</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.common.ControlQueue#isEmpty <em>Empty</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.common.CommonPackage#getControlQueue()
 * @model
 * @generated
 */
public interface ControlQueue extends EObject {
	/**
	 * Returns the value of the '<em><b>Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Empty</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Empty</em>' attribute.
	 * @see #isSetEmpty()
	 * @see #unsetEmpty()
	 * @see #setEmpty(boolean)
	 * @see electrickery.common.CommonPackage#getControlQueue_Empty()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isEmpty();

	/**
	 * Sets the value of the '{@link electrickery.common.ControlQueue#isEmpty <em>Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empty</em>' attribute.
	 * @see #isSetEmpty()
	 * @see #unsetEmpty()
	 * @see #isEmpty()
	 * @generated
	 */
	void setEmpty(boolean value);

	/**
	 * Unsets the value of the '{@link electrickery.common.ControlQueue#isEmpty <em>Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEmpty()
	 * @see #isEmpty()
	 * @see #setEmpty(boolean)
	 * @generated
	 */
	void unsetEmpty();

	/**
	 * Returns whether the value of the '{@link electrickery.common.ControlQueue#isEmpty <em>Empty</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Empty</em>' attribute is set.
	 * @see #unsetEmpty()
	 * @see #isEmpty()
	 * @see #setEmpty(boolean)
	 * @generated
	 */
	boolean isSetEmpty();

} // ControlQueue
