/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.control;

import dss.common.Bus;
import dss.common.CircuitElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base for control classes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.control.ControlElement#getElementName <em>Element Name</em>}</li>
 *   <li>{@link dss.control.ControlElement#getElementTerminal <em>Element Terminal</em>}</li>
 *   <li>{@link dss.control.ControlElement#getControlledBusName <em>Controlled Bus Name</em>}</li>
 *   <li>{@link dss.control.ControlElement#getControlledBus <em>Controlled Bus</em>}</li>
 *   <li>{@link dss.control.ControlElement#getMonitoredVariable <em>Monitored Variable</em>}</li>
 *   <li>{@link dss.control.ControlElement#getMonitoredVarIndex <em>Monitored Var Index</em>}</li>
 *   <li>{@link dss.control.ControlElement#getTimeDelay <em>Time Delay</em>}</li>
 *   <li>{@link dss.control.ControlElement#getDblTraceParam <em>Dbl Trace Param</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.control.ControlPackage#getControlElement()
 * @model abstract="true"
 * @generated
 */
public interface ControlElement extends CircuitElement {
	/**
	 * Returns the value of the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Name</em>' attribute.
	 * @see #setElementName(String)
	 * @see dss.control.ControlPackage#getControlElement_ElementName()
	 * @model
	 * @generated
	 */
	String getElementName();

	/**
	 * Sets the value of the '{@link dss.control.ControlElement#getElementName <em>Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Name</em>' attribute.
	 * @see #getElementName()
	 * @generated
	 */
	void setElementName(String value);

	/**
	 * Returns the value of the '<em><b>Element Terminal</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Terminal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Terminal</em>' attribute.
	 * @see #setElementTerminal(int)
	 * @see dss.control.ControlPackage#getControlElement_ElementTerminal()
	 * @model default="1"
	 * @generated
	 */
	int getElementTerminal();

	/**
	 * Sets the value of the '{@link dss.control.ControlElement#getElementTerminal <em>Element Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Terminal</em>' attribute.
	 * @see #getElementTerminal()
	 * @generated
	 */
	void setElementTerminal(int value);

	/**
	 * Returns the value of the '<em><b>Controlled Bus Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Controlled Bus Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Controlled Bus Name</em>' attribute.
	 * @see #setControlledBusName(String)
	 * @see dss.control.ControlPackage#getControlElement_ControlledBusName()
	 * @model
	 * @generated
	 */
	String getControlledBusName();

	/**
	 * Sets the value of the '{@link dss.control.ControlElement#getControlledBusName <em>Controlled Bus Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Controlled Bus Name</em>' attribute.
	 * @see #getControlledBusName()
	 * @generated
	 */
	void setControlledBusName(String value);

	/**
	 * Returns the value of the '<em><b>Controlled Bus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Controlled Bus</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Controlled Bus</em>' reference.
	 * @see #setControlledBus(Bus)
	 * @see dss.control.ControlPackage#getControlElement_ControlledBus()
	 * @model
	 * @generated
	 */
	Bus getControlledBus();

	/**
	 * Sets the value of the '{@link dss.control.ControlElement#getControlledBus <em>Controlled Bus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Controlled Bus</em>' reference.
	 * @see #getControlledBus()
	 * @generated
	 */
	void setControlledBus(Bus value);

	/**
	 * Returns the value of the '<em><b>Monitored Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Monitored Variable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Monitored Variable</em>' attribute.
	 * @see #setMonitoredVariable(String)
	 * @see dss.control.ControlPackage#getControlElement_MonitoredVariable()
	 * @model
	 * @generated
	 */
	String getMonitoredVariable();

	/**
	 * Sets the value of the '{@link dss.control.ControlElement#getMonitoredVariable <em>Monitored Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Monitored Variable</em>' attribute.
	 * @see #getMonitoredVariable()
	 * @generated
	 */
	void setMonitoredVariable(String value);

	/**
	 * Returns the value of the '<em><b>Monitored Var Index</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Monitored Var Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Monitored Var Index</em>' attribute.
	 * @see #setMonitoredVarIndex(int)
	 * @see dss.control.ControlPackage#getControlElement_MonitoredVarIndex()
	 * @model default="1"
	 * @generated
	 */
	int getMonitoredVarIndex();

	/**
	 * Sets the value of the '{@link dss.control.ControlElement#getMonitoredVarIndex <em>Monitored Var Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Monitored Var Index</em>' attribute.
	 * @see #getMonitoredVarIndex()
	 * @generated
	 */
	void setMonitoredVarIndex(int value);

	/**
	 * Returns the value of the '<em><b>Time Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Delay</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Delay</em>' attribute.
	 * @see #setTimeDelay(double)
	 * @see dss.control.ControlPackage#getControlElement_TimeDelay()
	 * @model
	 * @generated
	 */
	double getTimeDelay();

	/**
	 * Sets the value of the '{@link dss.control.ControlElement#getTimeDelay <em>Time Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Delay</em>' attribute.
	 * @see #getTimeDelay()
	 * @generated
	 */
	void setTimeDelay(double value);

	/**
	 * Returns the value of the '<em><b>Dbl Trace Param</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dbl Trace Param</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dbl Trace Param</em>' attribute.
	 * @see #setDblTraceParam(double)
	 * @see dss.control.ControlPackage#getControlElement_DblTraceParam()
	 * @model
	 * @generated
	 */
	double getDblTraceParam();

	/**
	 * Sets the value of the '{@link dss.control.ControlElement#getDblTraceParam <em>Dbl Trace Param</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dbl Trace Param</em>' attribute.
	 * @see #getDblTraceParam()
	 * @generated
	 */
	void setDblTraceParam(double value);

} // ControlElement
