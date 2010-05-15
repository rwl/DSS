/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.control;

import dss.common.tripAction;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Recloser</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Recloser is a control element that is connected to a terminal of a
 * circuit element and controls the switches in the same or another terminal.
 * 
 * The control is usually placed in the terminal of a line or transformer, but
 * it could be any element
 * 
 * CktElement to be controlled must already exist.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.control.Recloser#getMonitoredObj <em>Monitored Obj</em>}</li>
 *   <li>{@link dss.control.Recloser#getMonitoredTerm <em>Monitored Term</em>}</li>
 *   <li>{@link dss.control.Recloser#getSwitchedObj <em>Switched Obj</em>}</li>
 *   <li>{@link dss.control.Recloser#getSwitchedTerm <em>Switched Term</em>}</li>
 *   <li>{@link dss.control.Recloser#getNFast <em>NFast</em>}</li>
 *   <li>{@link dss.control.Recloser#getPhaseFast <em>Phase Fast</em>}</li>
 *   <li>{@link dss.control.Recloser#getPhaseDelayed <em>Phase Delayed</em>}</li>
 *   <li>{@link dss.control.Recloser#getGroundFast <em>Ground Fast</em>}</li>
 *   <li>{@link dss.control.Recloser#getGroundDelayed <em>Ground Delayed</em>}</li>
 *   <li>{@link dss.control.Recloser#getPhaseTrip <em>Phase Trip</em>}</li>
 *   <li>{@link dss.control.Recloser#getGroundTrip <em>Ground Trip</em>}</li>
 *   <li>{@link dss.control.Recloser#getPhaseInst <em>Phase Inst</em>}</li>
 *   <li>{@link dss.control.Recloser#getGroundInst <em>Ground Inst</em>}</li>
 *   <li>{@link dss.control.Recloser#getReset <em>Reset</em>}</li>
 *   <li>{@link dss.control.Recloser#getShots <em>Shots</em>}</li>
 *   <li>{@link dss.control.Recloser#getRecloseIntervals <em>Reclose Intervals</em>}</li>
 *   <li>{@link dss.control.Recloser#getDelay <em>Delay</em>}</li>
 *   <li>{@link dss.control.Recloser#getAction <em>Action</em>}</li>
 *   <li>{@link dss.control.Recloser#getTDPhFast <em>TD Ph Fast</em>}</li>
 *   <li>{@link dss.control.Recloser#getTDGrFast <em>TD Gr Fast</em>}</li>
 *   <li>{@link dss.control.Recloser#getTDPhDelayed <em>TD Ph Delayed</em>}</li>
 *   <li>{@link dss.control.Recloser#getTDGrDelayed <em>TD Gr Delayed</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.control.ControlPackage#getRecloser()
 * @model
 * @generated
 */
public interface Recloser extends ControlElement {
	/**
	 * Returns the value of the '<em><b>Monitored Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Full object name of the circuit element, typically a line, transformer, load, or generator, to which the Recloser's PT and/or CT are connected.  This is the "monitored" element. There is no default; must be specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Monitored Obj</em>' attribute.
	 * @see #setMonitoredObj(String)
	 * @see dss.control.ControlPackage#getRecloser_MonitoredObj()
	 * @model
	 * @generated
	 */
	String getMonitoredObj();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getMonitoredObj <em>Monitored Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Monitored Obj</em>' attribute.
	 * @see #getMonitoredObj()
	 * @generated
	 */
	void setMonitoredObj(String value);

	/**
	 * Returns the value of the '<em><b>Monitored Term</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of the terminal of the circuit element to which the Recloser is connected. 1 or 2, typically.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Monitored Term</em>' attribute.
	 * @see #setMonitoredTerm(int)
	 * @see dss.control.ControlPackage#getRecloser_MonitoredTerm()
	 * @model default="1"
	 * @generated
	 */
	int getMonitoredTerm();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getMonitoredTerm <em>Monitored Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Monitored Term</em>' attribute.
	 * @see #getMonitoredTerm()
	 * @generated
	 */
	void setMonitoredTerm(int value);

	/**
	 * Returns the value of the '<em><b>Switched Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of circuit element switch that the Recloser controls. Specify the full object name.  Defaults to the same as the Monitored element. This is the "controlled" element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Switched Obj</em>' attribute.
	 * @see #setSwitchedObj(String)
	 * @see dss.control.ControlPackage#getRecloser_SwitchedObj()
	 * @model
	 * @generated
	 */
	String getSwitchedObj();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getSwitchedObj <em>Switched Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switched Obj</em>' attribute.
	 * @see #getSwitchedObj()
	 * @generated
	 */
	void setSwitchedObj(String value);

	/**
	 * Returns the value of the '<em><b>Switched Term</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of the terminal of the controlled element in which the switch is controlled by the Recloser. 1 or 2, typically.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Switched Term</em>' attribute.
	 * @see #setSwitchedTerm(int)
	 * @see dss.control.ControlPackage#getRecloser_SwitchedTerm()
	 * @model default="1"
	 * @generated
	 */
	int getSwitchedTerm();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getSwitchedTerm <em>Switched Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switched Term</em>' attribute.
	 * @see #getSwitchedTerm()
	 * @generated
	 */
	void setSwitchedTerm(int value);

	/**
	 * Returns the value of the '<em><b>NFast</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of Fast (fuse saving) operations. (See "Shots")
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NFast</em>' attribute.
	 * @see #setNFast(int)
	 * @see dss.control.ControlPackage#getRecloser_NFast()
	 * @model default="1"
	 * @generated
	 */
	int getNFast();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getNFast <em>NFast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NFast</em>' attribute.
	 * @see #getNFast()
	 * @generated
	 */
	void setNFast(int value);

	/**
	 * Returns the value of the '<em><b>Phase Fast</b></em>' attribute.
	 * The default value is <code>"A"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of the TCC Curve object that determines the Phase Fast trip. Must have been previously defined as a TCC_Curve object. Default is "A".  Multiplying the current values in the curve by the "phasetrip" value gives the actual current.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phase Fast</em>' attribute.
	 * @see #setPhaseFast(String)
	 * @see dss.control.ControlPackage#getRecloser_PhaseFast()
	 * @model default="A"
	 * @generated
	 */
	String getPhaseFast();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getPhaseFast <em>Phase Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phase Fast</em>' attribute.
	 * @see #getPhaseFast()
	 * @generated
	 */
	void setPhaseFast(String value);

	/**
	 * Returns the value of the '<em><b>Phase Delayed</b></em>' attribute.
	 * The default value is <code>"D"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of the TCC Curve object that determines the Phase Delayed trip. Must have been previously defined as a TCC_Curve object. Default is "D".  Multiplying the current values in the curve by the "phasetrip" value gives the actual current.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phase Delayed</em>' attribute.
	 * @see #setPhaseDelayed(String)
	 * @see dss.control.ControlPackage#getRecloser_PhaseDelayed()
	 * @model default="D"
	 * @generated
	 */
	String getPhaseDelayed();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getPhaseDelayed <em>Phase Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phase Delayed</em>' attribute.
	 * @see #getPhaseDelayed()
	 * @generated
	 */
	void setPhaseDelayed(String value);

	/**
	 * Returns the value of the '<em><b>Ground Fast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of the TCC Curve object that determines the Ground Fast trip.  Must have been previously defined as a TCC_Curve object. Multiplying the current values in the curve by the "groundtrip" value gives the actual current.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ground Fast</em>' attribute.
	 * @see #setGroundFast(String)
	 * @see dss.control.ControlPackage#getRecloser_GroundFast()
	 * @model
	 * @generated
	 */
	String getGroundFast();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getGroundFast <em>Ground Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ground Fast</em>' attribute.
	 * @see #getGroundFast()
	 * @generated
	 */
	void setGroundFast(String value);

	/**
	 * Returns the value of the '<em><b>Ground Delayed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ground Delayed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ground Delayed</em>' attribute.
	 * @see #setGroundDelayed(String)
	 * @see dss.control.ControlPackage#getRecloser_GroundDelayed()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel docmentation='Name of the TCC Curve object that determines the Ground Delayed trip.  Must have been previously defined as a TCC_Curve object. Multiplying the current values in the curve by the \"groundtrip\" value gives the actual current.'"
	 * @generated
	 */
	String getGroundDelayed();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getGroundDelayed <em>Ground Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ground Delayed</em>' attribute.
	 * @see #getGroundDelayed()
	 * @generated
	 */
	void setGroundDelayed(String value);

	/**
	 * Returns the value of the '<em><b>Phase Trip</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Phase Trip</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Phase Trip</em>' attribute.
	 * @see #setPhaseTrip(double)
	 * @see dss.control.ControlPackage#getRecloser_PhaseTrip()
	 * @model default="1.0"
	 * @generated
	 */
	double getPhaseTrip();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getPhaseTrip <em>Phase Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phase Trip</em>' attribute.
	 * @see #getPhaseTrip()
	 * @generated
	 */
	void setPhaseTrip(double value);

	/**
	 * Returns the value of the '<em><b>Ground Trip</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Multiplier or actual ground amps (3I0) for the ground TCC curve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ground Trip</em>' attribute.
	 * @see #setGroundTrip(double)
	 * @see dss.control.ControlPackage#getRecloser_GroundTrip()
	 * @model default="1.0"
	 * @generated
	 */
	double getGroundTrip();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getGroundTrip <em>Ground Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ground Trip</em>' attribute.
	 * @see #getGroundTrip()
	 * @generated
	 */
	void setGroundTrip(double value);

	/**
	 * Returns the value of the '<em><b>Phase Inst</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Multiplier or actual phase amps for the phase TCC curve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phase Inst</em>' attribute.
	 * @see #setPhaseInst(double)
	 * @see dss.control.ControlPackage#getRecloser_PhaseInst()
	 * @model default="1.0"
	 * @generated
	 */
	double getPhaseInst();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getPhaseInst <em>Phase Inst</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phase Inst</em>' attribute.
	 * @see #getPhaseInst()
	 * @generated
	 */
	void setPhaseInst(double value);

	/**
	 * Returns the value of the '<em><b>Ground Inst</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Actual amps for instantaneous ground trip which is assumed to happen in 0.01 sec + Delay Time. Default is 0.0, which signifies no inst trip.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ground Inst</em>' attribute.
	 * @see #setGroundInst(double)
	 * @see dss.control.ControlPackage#getRecloser_GroundInst()
	 * @model
	 * @generated
	 */
	double getGroundInst();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getGroundInst <em>Ground Inst</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ground Inst</em>' attribute.
	 * @see #getGroundInst()
	 * @generated
	 */
	void setGroundInst(double value);

	/**
	 * Returns the value of the '<em><b>Reset</b></em>' attribute.
	 * The default value is <code>"15.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reset time in sec for Recloser.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reset</em>' attribute.
	 * @see #setReset(double)
	 * @see dss.control.ControlPackage#getRecloser_Reset()
	 * @model default="15.0"
	 * @generated
	 */
	double getReset();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getReset <em>Reset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reset</em>' attribute.
	 * @see #getReset()
	 * @generated
	 */
	void setReset(double value);

	/**
	 * Returns the value of the '<em><b>Shots</b></em>' attribute.
	 * The default value is <code>"4"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total Number of fast and delayed shots to lockout. This is one more than the number of reclose intervals.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Shots</em>' attribute.
	 * @see #setShots(int)
	 * @see dss.control.ControlPackage#getRecloser_Shots()
	 * @model default="4"
	 * @generated
	 */
	int getShots();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getShots <em>Shots</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shots</em>' attribute.
	 * @see #getShots()
	 * @generated
	 */
	void setShots(int value);

	/**
	 * Returns the value of the '<em><b>Reclose Intervals</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of reclose intervals.  Default for Recloser is (0.5, 2.0, 2.0) seconds. A locked out Recloser must be closed manually (action=close).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reclose Intervals</em>' attribute list.
	 * @see dss.control.ControlPackage#getRecloser_RecloseIntervals()
	 * @model
	 * @generated
	 */
	EList<Double> getRecloseIntervals();

	/**
	 * Returns the value of the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fixed delay time (sec) added to Recloser trip time. Used to represent breaker time or any other delay.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Delay</em>' attribute.
	 * @see #setDelay(double)
	 * @see dss.control.ControlPackage#getRecloser_Delay()
	 * @model
	 * @generated
	 */
	double getDelay();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getDelay <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay</em>' attribute.
	 * @see #getDelay()
	 * @generated
	 */
	void setDelay(double value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * The literals are from the enumeration {@link dss.common.tripAction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Action that overrides the Recloser control.  Simulates manual control on recloser "Trip" or "Open" causes the controlled element to open and lock out. "Close" causes the controlled element to close and the Recloser to reset to its first operation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see dss.common.tripAction
	 * @see #setAction(tripAction)
	 * @see dss.control.ControlPackage#getRecloser_Action()
	 * @model
	 * @generated
	 */
	tripAction getAction();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see dss.common.tripAction
	 * @see #getAction()
	 * @generated
	 */
	void setAction(tripAction value);

	/**
	 * Returns the value of the '<em><b>TD Ph Fast</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time dial for Phase Fast trip curve. Multiplier on time axis of specified curve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>TD Ph Fast</em>' attribute.
	 * @see #setTDPhFast(double)
	 * @see dss.control.ControlPackage#getRecloser_TDPhFast()
	 * @model default="1.0"
	 * @generated
	 */
	double getTDPhFast();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getTDPhFast <em>TD Ph Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TD Ph Fast</em>' attribute.
	 * @see #getTDPhFast()
	 * @generated
	 */
	void setTDPhFast(double value);

	/**
	 * Returns the value of the '<em><b>TD Gr Fast</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time dial for Ground Fast trip curve. Multiplier on time axis of specified curve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>TD Gr Fast</em>' attribute.
	 * @see #setTDGrFast(double)
	 * @see dss.control.ControlPackage#getRecloser_TDGrFast()
	 * @model default="1.0"
	 * @generated
	 */
	double getTDGrFast();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getTDGrFast <em>TD Gr Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TD Gr Fast</em>' attribute.
	 * @see #getTDGrFast()
	 * @generated
	 */
	void setTDGrFast(double value);

	/**
	 * Returns the value of the '<em><b>TD Ph Delayed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time dial for Phase Delayed trip curve. Multiplier on time axis of specified curve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>TD Ph Delayed</em>' attribute.
	 * @see #setTDPhDelayed(double)
	 * @see dss.control.ControlPackage#getRecloser_TDPhDelayed()
	 * @model
	 * @generated
	 */
	double getTDPhDelayed();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getTDPhDelayed <em>TD Ph Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TD Ph Delayed</em>' attribute.
	 * @see #getTDPhDelayed()
	 * @generated
	 */
	void setTDPhDelayed(double value);

	/**
	 * Returns the value of the '<em><b>TD Gr Delayed</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time dial for Ground Delayed trip curve. Multiplier on time axis of specified curve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>TD Gr Delayed</em>' attribute.
	 * @see #setTDGrDelayed(double)
	 * @see dss.control.ControlPackage#getRecloser_TDGrDelayed()
	 * @model default="1.0"
	 * @generated
	 */
	double getTDGrDelayed();

	/**
	 * Sets the value of the '{@link dss.control.Recloser#getTDGrDelayed <em>TD Gr Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TD Gr Delayed</em>' attribute.
	 * @see #getTDGrDelayed()
	 * @generated
	 */
	void setTDGrDelayed(double value);

} // Recloser
