/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Regulator Control</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A RegulatorControl is a control element that is connected to a terminal
 * of another circuit element that must be a transformer.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.control.RegulatorControl#getTransformer <em>Transformer</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getWinding <em>Winding</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getVReg <em>VReg</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getBand <em>Band</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getPTRatio <em>PT Ratio</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getCTPrim <em>CT Prim</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getR <em>R</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getX <em>X</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getBus <em>Bus</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getDelay <em>Delay</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#isReversible <em>Reversible</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getRevVReg <em>Rev VReg</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getRevBand <em>Rev Band</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getRevR <em>Rev R</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getRevX <em>Rev X</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getTapDelay <em>Tap Delay</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#isDebugTrace <em>Debug Trace</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getMaxTapChange <em>Max Tap Change</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#isInverseTime <em>Inverse Time</em>}</li>
 *   <li>{@link electrickery.control.RegulatorControl#getTapWinding <em>Tap Winding</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.control.ControlPackage#getRegulatorControl()
 * @model
 * @generated
 */
public interface RegulatorControl extends ControlElement {
	/**
	 * Returns the value of the '<em><b>Transformer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of Transformer element to which the RegControl is connected. Do not specify the full object name; "Transformer" is assumed for the object class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transformer</em>' attribute.
	 * @see #setTransformer(String)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_Transformer()
	 * @model
	 * @generated
	 */
	String getTransformer();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getTransformer <em>Transformer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transformer</em>' attribute.
	 * @see #getTransformer()
	 * @generated
	 */
	void setTransformer(String value);

	/**
	 * Returns the value of the '<em><b>Winding</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of the winding of the transformer element that the RegControl is monitoring. 1 or 2, typically.  Side Effect: Sets TAPWINDING property to the same winding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Winding</em>' attribute.
	 * @see #setWinding(int)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_Winding()
	 * @model default="1"
	 * @generated
	 */
	int getWinding();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getWinding <em>Winding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Winding</em>' attribute.
	 * @see #getWinding()
	 * @generated
	 */
	void setWinding(int value);

	/**
	 * Returns the value of the '<em><b>VReg</b></em>' attribute.
	 * The default value is <code>"120.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Voltage regulator setting, in VOLTS, for the winding being controlled.  Multiplying this value times the ptratio should yield the voltage across the WINDING of the controlled transformer.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VReg</em>' attribute.
	 * @see #setVReg(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_VReg()
	 * @model default="120.0"
	 * @generated
	 */
	double getVReg();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getVReg <em>VReg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VReg</em>' attribute.
	 * @see #getVReg()
	 * @generated
	 */
	void setVReg(double value);

	/**
	 * Returns the value of the '<em><b>Band</b></em>' attribute.
	 * The default value is <code>"3.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bandwidth in VOLTS for the controlled bus (see help for ptratio property)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Band</em>' attribute.
	 * @see #setBand(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_Band()
	 * @model default="3.0"
	 * @generated
	 */
	double getBand();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getBand <em>Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Band</em>' attribute.
	 * @see #getBand()
	 * @generated
	 */
	void setBand(double value);

	/**
	 * Returns the value of the '<em><b>PT Ratio</b></em>' attribute.
	 * The default value is <code>"60.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ratio of the PT that converts the controlled winding voltage to the regulator voltage. If the winding is Wye, the line-to-neutral voltage is used.  Else, the line-to-line voltage is used.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>PT Ratio</em>' attribute.
	 * @see #setPTRatio(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_PTRatio()
	 * @model default="60.0"
	 * @generated
	 */
	double getPTRatio();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getPTRatio <em>PT Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PT Ratio</em>' attribute.
	 * @see #getPTRatio()
	 * @generated
	 */
	void setPTRatio(double value);

	/**
	 * Returns the value of the '<em><b>CT Prim</b></em>' attribute.
	 * The default value is <code>"300.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rating, in Amperes, of the primary CT rating for converting the line amps to control amps.The typical default secondary ampere rating is 5 Amps.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>CT Prim</em>' attribute.
	 * @see #setCTPrim(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_CTPrim()
	 * @model default="300.0"
	 * @generated
	 */
	double getCTPrim();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getCTPrim <em>CT Prim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CT Prim</em>' attribute.
	 * @see #getCTPrim()
	 * @generated
	 */
	void setCTPrim(double value);

	/**
	 * Returns the value of the '<em><b>R</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * R setting on the line drop compensator in the regulator, expressed in VOLTS.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>R</em>' attribute.
	 * @see #setR(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_R()
	 * @model
	 * @generated
	 */
	double getR();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getR <em>R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>R</em>' attribute.
	 * @see #getR()
	 * @generated
	 */
	void setR(double value);

	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * X setting on the line drop compensator in the regulator, expressed in VOLTS.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_X()
	 * @model
	 * @generated
	 */
	double getX();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(double value);

	/**
	 * Returns the value of the '<em><b>Bus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of a bus in the system to use as the controlled bus instead of the bus to which the winding is connected or the R and X line drop compensator settings.  Do not specify this value if you wish to use the line drop compensator settings.  Default is null string. Assumes the base voltage for this bus is the same as the transformer winding base specified above. Note: This bus WILL BE CREATED by the regulator control upon SOLVE if not defined by some other device.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus</em>' attribute.
	 * @see #setBus(String)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_Bus()
	 * @model
	 * @generated
	 */
	String getBus();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getBus <em>Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus</em>' attribute.
	 * @see #getBus()
	 * @generated
	 */
	void setBus(String value);

	/**
	 * Returns the value of the '<em><b>Delay</b></em>' attribute.
	 * The default value is <code>"15.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time delay, in seconds, from when the voltage goes out of band to when the tap changing begins. This is used to determine which regulator control will act first. You may specify any floating point number to achieve a model of whatever condition is necessary.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Delay</em>' attribute.
	 * @see #setDelay(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_Delay()
	 * @model default="15.0"
	 * @generated
	 */
	double getDelay();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getDelay <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay</em>' attribute.
	 * @see #getDelay()
	 * @generated
	 */
	void setDelay(double value);

	/**
	 * Returns the value of the '<em><b>Reversible</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates whether or not the regulator can be switched to regulate in the reverse direction. Default is No.Typically applies only to line regulators and not to LTC on a substation transformer.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reversible</em>' attribute.
	 * @see #setReversible(boolean)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_Reversible()
	 * @model default="false"
	 * @generated
	 */
	boolean isReversible();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#isReversible <em>Reversible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reversible</em>' attribute.
	 * @see #isReversible()
	 * @generated
	 */
	void setReversible(boolean value);

	/**
	 * Returns the value of the '<em><b>Rev VReg</b></em>' attribute.
	 * The default value is <code>"120.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Voltage setting in volts for operation in the reverse direction.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rev VReg</em>' attribute.
	 * @see #setRevVReg(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_RevVReg()
	 * @model default="120.0"
	 * @generated
	 */
	double getRevVReg();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getRevVReg <em>Rev VReg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rev VReg</em>' attribute.
	 * @see #getRevVReg()
	 * @generated
	 */
	void setRevVReg(double value);

	/**
	 * Returns the value of the '<em><b>Rev Band</b></em>' attribute.
	 * The default value is <code>"3.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bandwidth for operating in the reverse direction.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rev Band</em>' attribute.
	 * @see #setRevBand(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_RevBand()
	 * @model default="3.0"
	 * @generated
	 */
	double getRevBand();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getRevBand <em>Rev Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rev Band</em>' attribute.
	 * @see #getRevBand()
	 * @generated
	 */
	void setRevBand(double value);

	/**
	 * Returns the value of the '<em><b>Rev R</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * R line drop compensator setting for reverse direction.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rev R</em>' attribute.
	 * @see #setRevR(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_RevR()
	 * @model
	 * @generated
	 */
	double getRevR();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getRevR <em>Rev R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rev R</em>' attribute.
	 * @see #getRevR()
	 * @generated
	 */
	void setRevR(double value);

	/**
	 * Returns the value of the '<em><b>Rev X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * X line drop compensator setting for reverse direction.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rev X</em>' attribute.
	 * @see #setRevX(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_RevX()
	 * @model
	 * @generated
	 */
	double getRevX();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getRevX <em>Rev X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rev X</em>' attribute.
	 * @see #getRevX()
	 * @generated
	 */
	void setRevX(double value);

	/**
	 * Returns the value of the '<em><b>Tap Delay</b></em>' attribute.
	 * The default value is <code>"2.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Delay in sec between tap changes. This is how long it takes between changes after the first change.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tap Delay</em>' attribute.
	 * @see #setTapDelay(double)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_TapDelay()
	 * @model default="2.0"
	 * @generated
	 */
	double getTapDelay();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getTapDelay <em>Tap Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tap Delay</em>' attribute.
	 * @see #getTapDelay()
	 * @generated
	 */
	void setTapDelay(double value);

	/**
	 * Returns the value of the '<em><b>Debug Trace</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Turn this on to capture the progress of the regulator model for each control iteration.  Creates a separate file for each RegControl named "REG_name.CSV".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Debug Trace</em>' attribute.
	 * @see #setDebugTrace(boolean)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_DebugTrace()
	 * @model default="false"
	 * @generated
	 */
	boolean isDebugTrace();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#isDebugTrace <em>Debug Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Debug Trace</em>' attribute.
	 * @see #isDebugTrace()
	 * @generated
	 */
	void setDebugTrace(boolean value);

	/**
	 * Returns the value of the '<em><b>Max Tap Change</b></em>' attribute.
	 * The default value is <code>"16"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum allowable tap change per control iteration in STATIC control mode. Set this to 1 to better approximate actual control action. Set this to 0 to fix the tap in the current position.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Tap Change</em>' attribute.
	 * @see #setMaxTapChange(int)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_MaxTapChange()
	 * @model default="16"
	 * @generated
	 */
	int getMaxTapChange();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getMaxTapChange <em>Max Tap Change</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Tap Change</em>' attribute.
	 * @see #getMaxTapChange()
	 * @generated
	 */
	void setMaxTapChange(int value);

	/**
	 * Returns the value of the '<em><b>Inverse Time</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The time delay is adjusted inversely proportional to the amount the voltage is outside the band down to 10%.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inverse Time</em>' attribute.
	 * @see #setInverseTime(boolean)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_InverseTime()
	 * @model default="false"
	 * @generated
	 */
	boolean isInverseTime();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#isInverseTime <em>Inverse Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inverse Time</em>' attribute.
	 * @see #isInverseTime()
	 * @generated
	 */
	void setInverseTime(boolean value);

	/**
	 * Returns the value of the '<em><b>Tap Winding</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Winding containing the actual taps, if different than the WINDING property. Defaults to the same winding as specified by the WINDING property.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tap Winding</em>' attribute.
	 * @see #setTapWinding(int)
	 * @see electrickery.control.ControlPackage#getRegulatorControl_TapWinding()
	 * @model default="1"
	 * @generated
	 */
	int getTapWinding();

	/**
	 * Sets the value of the '{@link electrickery.control.RegulatorControl#getTapWinding <em>Tap Winding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tap Winding</em>' attribute.
	 * @see #getTapWinding()
	 * @generated
	 */
	void setTapWinding(int value);

} // RegulatorControl
