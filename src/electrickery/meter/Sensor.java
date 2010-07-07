/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.meter;

import electrickery.common.connectionType;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sensor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A sensor.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.meter.Sensor#getElement <em>Element</em>}</li>
 *   <li>{@link electrickery.meter.Sensor#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link electrickery.meter.Sensor#getModes <em>Modes</em>}</li>
 *   <li>{@link electrickery.meter.Sensor#getV <em>V</em>}</li>
 *   <li>{@link electrickery.meter.Sensor#getI <em>I</em>}</li>
 *   <li>{@link electrickery.meter.Sensor#getP <em>P</em>}</li>
 *   <li>{@link electrickery.meter.Sensor#getQ <em>Q</em>}</li>
 *   <li>{@link electrickery.meter.Sensor#getPhases <em>Phases</em>}</li>
 *   <li>{@link electrickery.meter.Sensor#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.meter.Sensor#getPctError <em>Pct Error</em>}</li>
 *   <li>{@link electrickery.meter.Sensor#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.meter.MeterPackage#getSensor()
 * @model
 * @generated
 */
public interface Sensor extends MeterElement {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name (Full Object name) of element to which the Sensor is connected.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element</em>' attribute.
	 * @see #setElement(String)
	 * @see electrickery.meter.MeterPackage#getSensor_Element()
	 * @model
	 * @generated
	 */
	String getElement();

	/**
	 * Sets the value of the '{@link electrickery.meter.Sensor#getElement <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' attribute.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(String value);

	/**
	 * Returns the value of the '<em><b>Terminal</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of the terminal of the circuit element to which the Sensor is connected. 1 or 2, typically. For Sensoring states, attach Sensor to terminal 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Terminal</em>' attribute.
	 * @see #setTerminal(int)
	 * @see electrickery.meter.MeterPackage#getSensor_Terminal()
	 * @model default="1"
	 * @generated
	 */
	int getTerminal();

	/**
	 * Sets the value of the '{@link electrickery.meter.Sensor#getTerminal <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminal</em>' attribute.
	 * @see #getTerminal()
	 * @generated
	 */
	void setTerminal(int value);

	/**
	 * Returns the value of the '<em><b>Modes</b></em>' attribute list.
	 * The list contents are of type {@link electrickery.meter.sensorMode}.
	 * The literals are from the enumeration {@link electrickery.meter.sensorMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of any of { Voltage | Current | kW | kvar } in any order.  Quantities being sensed. Scalar magnitudes only.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modes</em>' attribute list.
	 * @see electrickery.meter.sensorMode
	 * @see electrickery.meter.MeterPackage#getSensor_Modes()
	 * @model
	 * @generated
	 */
	EList<sensorMode> getModes();

	/**
	 * Returns the value of the '<em><b>V</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of Voltages (kV) measured by the voltage sensor.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>V</em>' attribute list.
	 * @see electrickery.meter.MeterPackage#getSensor_V()
	 * @model default="7.2"
	 * @generated
	 */
	EList<Double> getV();

	/**
	 * Returns the value of the '<em><b>I</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of Currents (amps) measured by the current sensor.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>I</em>' attribute list.
	 * @see electrickery.meter.MeterPackage#getSensor_I()
	 * @model
	 * @generated
	 */
	EList<Double> getI();

	/**
	 * Returns the value of the '<em><b>P</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of Active power (kW) measurements at the sensor.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>P</em>' attribute list.
	 * @see electrickery.meter.MeterPackage#getSensor_P()
	 * @model
	 * @generated
	 */
	EList<Double> getP();

	/**
	 * Returns the value of the '<em><b>Q</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of Reactive power (kvar) measurements at the sensor.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Q</em>' attribute list.
	 * @see electrickery.meter.MeterPackage#getSensor_Q()
	 * @model
	 * @generated
	 */
	EList<Double> getQ();

	/**
	 * Returns the value of the '<em><b>Phases</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of phases being monitored by this sensor. [1, 2, 3] or [2 3 1] or [1], etc.  Corresponds to the order that the measurement arrays will be supplied. Defaults to same number of quantities as phases in the monitored element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phases</em>' attribute list.
	 * @see electrickery.meter.MeterPackage#getSensor_Phases()
	 * @model upper="3"
	 * @generated
	 */
	EList<Integer> getPhases();

	/**
	 * Returns the value of the '<em><b>Conn</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.common.connectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Applies to voltage measurement. If wye or LN, voltage is assumed measured line-neutral; otherwise, line-line.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #setConn(connectionType)
	 * @see electrickery.meter.MeterPackage#getSensor_Conn()
	 * @model
	 * @generated
	 */
	connectionType getConn();

	/**
	 * Sets the value of the '{@link electrickery.meter.Sensor#getConn <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #getConn()
	 * @generated
	 */
	void setConn(connectionType value);

	/**
	 * Returns the value of the '<em><b>Pct Error</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Assumed percent error in the measurement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Error</em>' attribute.
	 * @see #setPctError(double)
	 * @see electrickery.meter.MeterPackage#getSensor_PctError()
	 * @model default="1.0"
	 * @generated
	 */
	double getPctError();

	/**
	 * Sets the value of the '{@link electrickery.meter.Sensor#getPctError <em>Pct Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Error</em>' attribute.
	 * @see #getPctError()
	 * @generated
	 */
	void setPctError(double value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.meter.sensorAction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Action options: SQERROR: Show square error of the present value of the monitored terminal quantity vs the sensor value. Actual values - convert to per unit in calling program.  Value reported in result window/result variable.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see electrickery.meter.sensorAction
	 * @see #setAction(sensorAction)
	 * @see electrickery.meter.MeterPackage#getSensor_Action()
	 * @model
	 * @generated
	 */
	sensorAction getAction();

	/**
	 * Sets the value of the '{@link electrickery.meter.Sensor#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see electrickery.meter.sensorAction
	 * @see #getAction()
	 * @generated
	 */
	void setAction(sensorAction value);

} // Sensor
