/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.control;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Switch Control</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.control.SwitchControl#getAction <em>Action</em>}</li>
 *   <li>{@link electrickery.control.SwitchControl#isLock <em>Lock</em>}</li>
 *   <li>{@link electrickery.control.SwitchControl#getDelay <em>Delay</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.control.ControlPackage#getSwitchControl()
 * @model
 * @generated
 */
public interface SwitchControl extends ControlElement {
	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.control.switchAction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Simulates manual operation of the controlled switch to open or close, after
	 * a time delay.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see electrickery.control.switchAction
	 * @see #setAction(switchAction)
	 * @see electrickery.control.ControlPackage#getSwitchControl_Action()
	 * @model
	 * @generated
	 */
	switchAction getAction();

	/**
	 * Sets the value of the '{@link electrickery.control.SwitchControl#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see electrickery.control.switchAction
	 * @see #getAction()
	 * @generated
	 */
	void setAction(switchAction value);

	/**
	 * Returns the value of the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Controlled switch is locked in its present open / close state. Switch will not
	 * respond to either manual or automatic control until this Lock is removed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lock</em>' attribute.
	 * @see #setLock(boolean)
	 * @see electrickery.control.ControlPackage#getSwitchControl_Lock()
	 * @model
	 * @generated
	 */
	boolean isLock();

	/**
	 * Sets the value of the '{@link electrickery.control.SwitchControl#isLock <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock</em>' attribute.
	 * @see #isLock()
	 * @generated
	 */
	void setLock(boolean value);

	/**
	 * Returns the value of the '<em><b>Delay</b></em>' attribute.
	 * The default value is <code>"120.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Operating time delay (sec) of the switch.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Delay</em>' attribute.
	 * @see #setDelay(double)
	 * @see electrickery.control.ControlPackage#getSwitchControl_Delay()
	 * @model default="120.0"
	 * @generated
	 */
	double getDelay();

	/**
	 * Sets the value of the '{@link electrickery.control.SwitchControl#getDelay <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay</em>' attribute.
	 * @see #getDelay()
	 * @generated
	 */
	void setDelay(double value);

} // SwitchControl
