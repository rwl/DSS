/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.control;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capacitor Control</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A CapacitorControl is a control element that is connected to a terminal of
 * another circuit element and controls a capacitor.  The control is usually
 * placed in the terminal of a line or transformer, although a voltage control
 * device could be placed in the terminal of the capacitor it controls.
 * 
 * Capacitor to be controlled must already exist.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.control.CapacitorControl#getElement <em>Element</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getCapacitor <em>Capacitor</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getType <em>Type</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getPTRatio <em>PT Ratio</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getCTRatio <em>CT Ratio</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getOnSetting <em>On Setting</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getOffSetting <em>Off Setting</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getDelay <em>Delay</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#isVoltOverride <em>Volt Override</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getVMax <em>VMax</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getVMin <em>VMin</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getDelayOff <em>Delay Off</em>}</li>
 *   <li>{@link dss.control.CapacitorControl#getDeadTime <em>Dead Time</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.control.ControlPackage#getCapacitorControl()
 * @model
 * @generated
 */
public interface CapacitorControl extends ControlElement {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Full object name of the circuit element, typically a line or transformer, to which the capacitor control's PT and/or CT are connected. There is no default; must be specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element</em>' attribute.
	 * @see #setElement(String)
	 * @see dss.control.ControlPackage#getCapacitorControl_Element()
	 * @model
	 * @generated
	 */
	String getElement();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getElement <em>Element</em>}' attribute.
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
	 * Number of the terminal of the circuit element to which the CapControl is connected. 1 or 2, typically.  Default is 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Terminal</em>' attribute.
	 * @see #setTerminal(int)
	 * @see dss.control.ControlPackage#getCapacitorControl_Terminal()
	 * @model default="1"
	 * @generated
	 */
	int getTerminal();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getTerminal <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminal</em>' attribute.
	 * @see #getTerminal()
	 * @generated
	 */
	void setTerminal(int value);

	/**
	 * Returns the value of the '<em><b>Capacitor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of Capacitor element which the CapControl controls. No Default; Must be specified.  Do not specify the full object name; "Capacitor" is assumed for the object class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Capacitor</em>' attribute.
	 * @see #setCapacitor(String)
	 * @see dss.control.ControlPackage#getCapacitorControl_Capacitor()
	 * @model
	 * @generated
	 */
	String getCapacitor();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getCapacitor <em>Capacitor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacitor</em>' attribute.
	 * @see #getCapacitor()
	 * @generated
	 */
	void setCapacitor(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link dss.control.controlType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Control type.  Specify the ONsetting and OFFsetting appropriately with the type of control. (See help for ONsetting)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see dss.control.controlType
	 * @see #setType(controlType)
	 * @see dss.control.ControlPackage#getCapacitorControl_Type()
	 * @model
	 * @generated
	 */
	controlType getType();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see dss.control.controlType
	 * @see #getType()
	 * @generated
	 */
	void setType(controlType value);

	/**
	 * Returns the value of the '<em><b>PT Ratio</b></em>' attribute.
	 * The default value is <code>"60.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ratio of the PT that converts the monitored voltage to the control voltage. Default is 60.  If the capacitor is Wye, the 1st phase line-to-neutral voltage is monitored.  Else, the line-to-line voltage (1st - 2nd phase) is monitored.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>PT Ratio</em>' attribute.
	 * @see #setPTRatio(double)
	 * @see dss.control.ControlPackage#getCapacitorControl_PTRatio()
	 * @model default="60.0"
	 * @generated
	 */
	double getPTRatio();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getPTRatio <em>PT Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PT Ratio</em>' attribute.
	 * @see #getPTRatio()
	 * @generated
	 */
	void setPTRatio(double value);

	/**
	 * Returns the value of the '<em><b>CT Ratio</b></em>' attribute.
	 * The default value is <code>"60.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ratio of the CT from line amps to control ampere setting for current and kvar control types.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>CT Ratio</em>' attribute.
	 * @see #setCTRatio(double)
	 * @see dss.control.ControlPackage#getCapacitorControl_CTRatio()
	 * @model default="60.0"
	 * @generated
	 */
	double getCTRatio();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getCTRatio <em>CT Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CT Ratio</em>' attribute.
	 * @see #getCTRatio()
	 * @generated
	 */
	void setCTRatio(double value);

	/**
	 * Returns the value of the '<em><b>On Setting</b></em>' attribute.
	 * The default value is <code>"300.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Value at which the control arms to switch the capacitor ON (or ratchet up a step).  Type of Control:
	 *     Current: Line Amps / CTratio
	 *     Voltage: Line-Neutral (or Line-Line for delta) Volts / PTratio
	 *     kvar:    Total kvar, all phases (3-phase for pos seq model). This is
	 *     directional.
	 *     Time:    Hrs from Midnight as a floating point number (decimal).
	 *     7:30am would be entered as 7.5.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>On Setting</em>' attribute.
	 * @see #setOnSetting(double)
	 * @see dss.control.ControlPackage#getCapacitorControl_OnSetting()
	 * @model default="300.0"
	 * @generated
	 */
	double getOnSetting();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getOnSetting <em>On Setting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Setting</em>' attribute.
	 * @see #getOnSetting()
	 * @generated
	 */
	void setOnSetting(double value);

	/**
	 * Returns the value of the '<em><b>Off Setting</b></em>' attribute.
	 * The default value is <code>"200.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Value at which the control arms to switch the capacitor OFF. (See help for ONsetting)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Off Setting</em>' attribute.
	 * @see #setOffSetting(double)
	 * @see dss.control.ControlPackage#getCapacitorControl_OffSetting()
	 * @model default="200.0"
	 * @generated
	 */
	double getOffSetting();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getOffSetting <em>Off Setting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Off Setting</em>' attribute.
	 * @see #getOffSetting()
	 * @generated
	 */
	void setOffSetting(double value);

	/**
	 * Returns the value of the '<em><b>Delay</b></em>' attribute.
	 * The default value is <code>"15.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time delay, in seconds, from when the control is armed before it sends out the switching command to turn ON.  The control may reset before the action actually occurs. This is used to determine which capacity control will act first. Default is 15.  You may specify any floating point number to achieve a model of whatever condition is necessary.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Delay</em>' attribute.
	 * @see #setDelay(double)
	 * @see dss.control.ControlPackage#getCapacitorControl_Delay()
	 * @model default="15.0"
	 * @generated
	 */
	double getDelay();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getDelay <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay</em>' attribute.
	 * @see #getDelay()
	 * @generated
	 */
	void setDelay(double value);

	/**
	 * Returns the value of the '<em><b>Volt Override</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Switch to indicate whether VOLTAGE OVERRIDE is to be considered. Vmax and Vmin must be set to reasonable values if this property is Yes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Volt Override</em>' attribute.
	 * @see #setVoltOverride(boolean)
	 * @see dss.control.ControlPackage#getCapacitorControl_VoltOverride()
	 * @model default="false"
	 * @generated
	 */
	boolean isVoltOverride();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#isVoltOverride <em>Volt Override</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Volt Override</em>' attribute.
	 * @see #isVoltOverride()
	 * @generated
	 */
	void setVoltOverride(boolean value);

	/**
	 * Returns the value of the '<em><b>VMax</b></em>' attribute.
	 * The default value is <code>"126.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum voltage, in volts.  If the voltage across the capacitor divided by the PTRATIO is greater than this voltage, the capacitor will switch OFF regardless of other control settings. Default is 126 (goes with a PT ratio of 60 for 12.47 kV system).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VMax</em>' attribute.
	 * @see #setVMax(double)
	 * @see dss.control.ControlPackage#getCapacitorControl_VMax()
	 * @model default="126.0"
	 * @generated
	 */
	double getVMax();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getVMax <em>VMax</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VMax</em>' attribute.
	 * @see #getVMax()
	 * @generated
	 */
	void setVMax(double value);

	/**
	 * Returns the value of the '<em><b>VMin</b></em>' attribute.
	 * The default value is <code>"115.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum voltage, in volts.  If the voltage across the capacitor divided by the PTRATIO is less than this voltage, the capacitor will switch ON regardless of other control settings. Default is 115 (goes with a PT ratio of 60 for 12.47 kV system).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VMin</em>' attribute.
	 * @see #setVMin(double)
	 * @see dss.control.ControlPackage#getCapacitorControl_VMin()
	 * @model default="115.0"
	 * @generated
	 */
	double getVMin();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getVMin <em>VMin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VMin</em>' attribute.
	 * @see #getVMin()
	 * @generated
	 */
	void setVMin(double value);

	/**
	 * Returns the value of the '<em><b>Delay Off</b></em>' attribute.
	 * The default value is <code>"15.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time delay, in seconds, for control to turn OFF when present state is ON.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Delay Off</em>' attribute.
	 * @see #setDelayOff(double)
	 * @see dss.control.ControlPackage#getCapacitorControl_DelayOff()
	 * @model default="15.0"
	 * @generated
	 */
	double getDelayOff();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getDelayOff <em>Delay Off</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay Off</em>' attribute.
	 * @see #getDelayOff()
	 * @generated
	 */
	void setDelayOff(double value);

	/**
	 * Returns the value of the '<em><b>Dead Time</b></em>' attribute.
	 * The default value is <code>"300.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dead time after capacitor is turned OFF before it can be turned back ON.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dead Time</em>' attribute.
	 * @see #setDeadTime(double)
	 * @see dss.control.ControlPackage#getCapacitorControl_DeadTime()
	 * @model default="300.0"
	 * @generated
	 */
	double getDeadTime();

	/**
	 * Sets the value of the '{@link dss.control.CapacitorControl#getDeadTime <em>Dead Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dead Time</em>' attribute.
	 * @see #getDeadTime()
	 * @generated
	 */
	void setDeadTime(double value);

} // CapacitorControl
