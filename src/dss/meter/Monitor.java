/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.meter;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Monitor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A monitor is a circuit element that is connected to a terminal of
 * another circuit element.  It records the voltages and currents at that
 * terminal as a function of time and can report those values upon demand.
 * 
 * A Monitor is defined by a New commands:
 * 
 * New Type=Monitor Name=myname Element=elemname Terminal=[1,2,...]
 * Buffer=clear|save
 * 
 * Upon creation, the monitor buffer is established.  There is a file
 * associated with the buffer.  It is named "Mon_elemnameN.mon"  where N is
 * the terminal no. The file is truncated to zero at creation or buffer
 * clearing.
 * 
 * The Monitor keeps results in the in-memory buffer until it is filled.  Then
 * it appends the buffer to the associated file and resets the in-memory
 * buffer.
 * 
 * For buffer=save, the present in-memory buffer is appended to the disk file
 * so that it is saved for later reference.
 * 
 * The Monitor is a passive device that takes a sample whenever its
 * "TakeSample" method is invoked.  The SampleAll method of the Monitor ckt
 * element class will force all monitors elements to take a sample.  If the
 * present time (for the most recent solution is greater than the last time
 * entered in to the monitor buffer, the sample is appended to the buffer.
 * Otherwise, it replaces the last entry.
 * 
 * Monitor Files are simple binary files of doubles.  The first record
 * contains the number of conductors per terminal (NCond). (always use 'round'
 * function when converting this to an integer). Then subsequent records
 * consist of time and voltage and current samples for each terminal (all
 * complex doubles) in the order shown below:
 * 
 * The time values will not necessarily be in a uniform time step;  they will
 * be at times samples or solutions were taken.  This could vary from several
 * hours down to a few milliseconds.
 * 
 * The monitor ID can be determined from the file name.  Thus, these values
 * can be post-processed at any later time, provided that the monitors are not
 * reset.
 * 
 * Modes are:
 *     0: Standard mode - V and I,each phase, Mag and Angle
 *     1: Power each phase, complex (kw and kvars)
 *     2: Transformer Tap
 *     3: State Variables
 *     +16: Sequence components: V012, I012
 *     +32: Magnitude Only
 *     +64: Pos Seq only or Average of phases
 * 
 * 
 * A monitor is a benign circuit element that is associated with a terminal of
 * another circuit element.  It takes a sample when instructed, recording the
 * time and the complex values of voltage and current, or power, at all
 * phases.
 * 
 * The data are saved in a file (separate one for each monitor) at the
 * conclusion of a multistep solution or each solution in a Monte Carlo
 * calculation.  In essence, it works like a real power monitor.  The data in
 * the file may be converted to csv form and, for example, brought into
 * (EPRI provides VBA routines to read the monitor files directly and import
 * either complex voltages and currents or their magnitudes.)  The binary form
 * of the monitor file is
 *     Signature (4-byte Integer) signifies that this is a
 *     DSS monitor file = 43756
 *     Version (4-byte integer)    version number of the file
 *     Sample Size (4-byte integer)    No. of quantities saved per sample
 *     Mode (4-byte integer)         Monitor mode
 * 
 * Records follow
 * <--- All voltages first ---------------->|<--- All currents ----->|
 * <hour 1> <sec 1> <V1.re>  <V1.im>  <V2.re>  <V2.im>  .... <I1.re>  <I1.im>
 * <hour 2> <sec 1> <V1.re>  <V1.im>  <V2.re>  <V2.im>  .... <I1.re>  <I1.im>
 * <hour 3> <sec 1> <V1.re>  <V1.im>  <V2.re>  <V2.im>  .... <I1.re>  <I1.im>
 * 
 * If powers are saved then the record has only the power for each phase.
 * 
 * All values are Singles (32-bit). Hours and Seconds values are not included
 * in Sample Size. Recorded values are not necessarily saved as illustrated,
 * depending on Mode (see below).  However, the file is always packed singles
 * with each record beginning with the hour and seconds past the hour.
 * 
 * For Monte Carlo runs, the hour is set to the number of the solution and
 * seconds is set to zero.
 * 
 * Monitors may be connected to both power delivery elements and power
 * conversion elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.meter.Monitor#getElement <em>Element</em>}</li>
 *   <li>{@link dss.meter.Monitor#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link dss.meter.Monitor#getMode <em>Mode</em>}</li>
 *   <li>{@link dss.meter.Monitor#getAction <em>Action</em>}</li>
 *   <li>{@link dss.meter.Monitor#isResidual <em>Residual</em>}</li>
 *   <li>{@link dss.meter.Monitor#isVIPolar <em>VI Polar</em>}</li>
 *   <li>{@link dss.meter.Monitor#isPPolar <em>PPolar</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.meter.MeterPackage#getMonitor()
 * @model
 * @generated
 */
public interface Monitor extends MeterElement {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name (Full Object name) of element to which the monitor is connected.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element</em>' attribute.
	 * @see #setElement(String)
	 * @see dss.meter.MeterPackage#getMonitor_Element()
	 * @model
	 * @generated
	 */
	String getElement();

	/**
	 * Sets the value of the '{@link dss.meter.Monitor#getElement <em>Element</em>}' attribute.
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
	 * Number of the terminal of the circuit element to which the monitor is connected.  1 or 2, typically. For monitoring states, attach monitor to terminal 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Terminal</em>' attribute.
	 * @see #setTerminal(int)
	 * @see dss.meter.MeterPackage#getMonitor_Terminal()
	 * @model default="1"
	 * @generated
	 */
	int getTerminal();

	/**
	 * Sets the value of the '{@link dss.meter.Monitor#getTerminal <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminal</em>' attribute.
	 * @see #getTerminal()
	 * @generated
	 */
	void setTerminal(int value);

	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link dss.meter.monitorValues}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bitmask integer designating the values the monitor is to capture:
	 *     0 = Voltages and currents
	 *     1 = Powers
	 *     2 = Tap Position (Transformers only)
	 *     3 = State Variables (PCElements only)
	 * Normally, these would be actual phasor quantities from solution.
	 * Combine with adders below to achieve other results for terminal
	 * quantities:
	 *     +16 = Sequence quantities
	 *     +32 = Magnitude only
	 *     +64 = Positive sequence only or avg of all phases
	 * 
	 * Mix adder to obtain desired results. For example:
	 * Mode=112 will save positive sequence voltage and current magnitudes only
	 * Mode=48 will save all sequence voltages and currents, but magnitude only.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see dss.meter.monitorValues
	 * @see #setMode(monitorValues)
	 * @see dss.meter.MeterPackage#getMonitor_Mode()
	 * @model
	 * @generated
	 */
	monitorValues getMode();

	/**
	 * Sets the value of the '{@link dss.meter.Monitor#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see dss.meter.monitorValues
	 * @see #getMode()
	 * @generated
	 */
	void setMode(monitorValues value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * The literals are from the enumeration {@link dss.meter.monitorAction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * (C)lears or (S)aves current buffer.  (T)ake action takes a sample.  Note that monitors are automatically reset (cleared) when the Set Mode= command is issued.  Otherwise, the user must explicitly reset all monitors (reset monitors command) or individual monitors with the Clear action.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see dss.meter.monitorAction
	 * @see #setAction(monitorAction)
	 * @see dss.meter.MeterPackage#getMonitor_Action()
	 * @model
	 * @generated
	 */
	monitorAction getAction();

	/**
	 * Sets the value of the '{@link dss.meter.Monitor#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see dss.meter.monitorAction
	 * @see #getAction()
	 * @generated
	 */
	void setAction(monitorAction value);

	/**
	 * Returns the value of the '<em><b>Residual</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Include Residual channel (sum of all phases) for voltage and current.  Does not apply to sequence quantity modes or power modes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Residual</em>' attribute.
	 * @see #setResidual(boolean)
	 * @see dss.meter.MeterPackage#getMonitor_Residual()
	 * @model default="false"
	 * @generated
	 */
	boolean isResidual();

	/**
	 * Sets the value of the '{@link dss.meter.Monitor#isResidual <em>Residual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Residual</em>' attribute.
	 * @see #isResidual()
	 * @generated
	 */
	void setResidual(boolean value);

	/**
	 * Returns the value of the '<em><b>VI Polar</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Report voltage and current in polar form (Mag/Angle). Otherwise, it will be real and imaginary.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VI Polar</em>' attribute.
	 * @see #setVIPolar(boolean)
	 * @see dss.meter.MeterPackage#getMonitor_VIPolar()
	 * @model default="true"
	 * @generated
	 */
	boolean isVIPolar();

	/**
	 * Sets the value of the '{@link dss.meter.Monitor#isVIPolar <em>VI Polar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VI Polar</em>' attribute.
	 * @see #isVIPolar()
	 * @generated
	 */
	void setVIPolar(boolean value);

	/**
	 * Returns the value of the '<em><b>PPolar</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Report power in Apparent power, S, in polar form (Mag/Angle). Otherwise, is P and Q.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>PPolar</em>' attribute.
	 * @see #setPPolar(boolean)
	 * @see dss.meter.MeterPackage#getMonitor_PPolar()
	 * @model default="false"
	 * @generated
	 */
	boolean isPPolar();

	/**
	 * Sets the value of the '{@link dss.meter.Monitor#isPPolar <em>PPolar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PPolar</em>' attribute.
	 * @see #isPPolar()
	 * @generated
	 */
	void setPPolar(boolean value);

} // Monitor
