/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.control;

import dss.common.tripAction;

import dss.general.TimeCurrentCurve;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relay</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Relay is a control element that is connected to a terminal of a
 * circuit element and controls the switches in the same or another terminal.
 * 
 * The control is usually placed in the terminal of a line or transformer, but
 * it could be any element.
 * 
 * Voltage relay is a definite time relay that operates after the voltage
 * stays out of bounds for a fixed time interval.  It will then reclose a set
 * time after the voltage comes back in the normal range.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.control.Relay#getMonitoredObj <em>Monitored Obj</em>}</li>
 *   <li>{@link dss.control.Relay#getMonitoredTerm <em>Monitored Term</em>}</li>
 *   <li>{@link dss.control.Relay#getSwitchedObj <em>Switched Obj</em>}</li>
 *   <li>{@link dss.control.Relay#getSwitchedTerm <em>Switched Term</em>}</li>
 *   <li>{@link dss.control.Relay#getType <em>Type</em>}</li>
 *   <li>{@link dss.control.Relay#getPhaseCurve <em>Phase Curve</em>}</li>
 *   <li>{@link dss.control.Relay#getGroundCurve <em>Ground Curve</em>}</li>
 *   <li>{@link dss.control.Relay#getPhaseTrip <em>Phase Trip</em>}</li>
 *   <li>{@link dss.control.Relay#getGroundTrip <em>Ground Trip</em>}</li>
 *   <li>{@link dss.control.Relay#getTDPhase <em>TD Phase</em>}</li>
 *   <li>{@link dss.control.Relay#getTDGround <em>TD Ground</em>}</li>
 *   <li>{@link dss.control.Relay#getPhaseInst <em>Phase Inst</em>}</li>
 *   <li>{@link dss.control.Relay#getGroundInst <em>Ground Inst</em>}</li>
 *   <li>{@link dss.control.Relay#getReset <em>Reset</em>}</li>
 *   <li>{@link dss.control.Relay#getShots <em>Shots</em>}</li>
 *   <li>{@link dss.control.Relay#getRecloseIntervals <em>Reclose Intervals</em>}</li>
 *   <li>{@link dss.control.Relay#getDelay <em>Delay</em>}</li>
 *   <li>{@link dss.control.Relay#getOvervoltCurve <em>Overvolt Curve</em>}</li>
 *   <li>{@link dss.control.Relay#getUndervoltCurve <em>Undervolt Curve</em>}</li>
 *   <li>{@link dss.control.Relay#getKVBase <em>KV Base</em>}</li>
 *   <li>{@link dss.control.Relay#getPctPickup47 <em>Pct Pickup47</em>}</li>
 *   <li>{@link dss.control.Relay#getPctAmps46 <em>Pct Amps46</em>}</li>
 *   <li>{@link dss.control.Relay#getPctPickup46 <em>Pct Pickup46</em>}</li>
 *   <li>{@link dss.control.Relay#getISQT46 <em>ISQT46</em>}</li>
 *   <li>{@link dss.control.Relay#getVariable <em>Variable</em>}</li>
 *   <li>{@link dss.control.Relay#getOvertrip <em>Overtrip</em>}</li>
 *   <li>{@link dss.control.Relay#getUndertrip <em>Undertrip</em>}</li>
 *   <li>{@link dss.control.Relay#getBreakerTime <em>Breaker Time</em>}</li>
 *   <li>{@link dss.control.Relay#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.control.ControlPackage#getRelay()
 * @model
 * @generated
 */
public interface Relay extends ControlElement {
	/**
	 * Returns the value of the '<em><b>Monitored Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Full object name of the circuit element, typically a line, transformer, load, or generator, to which the relay's PT and/or CT are connected. This is the "monitored" element. There is no default; must be specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Monitored Obj</em>' attribute.
	 * @see #setMonitoredObj(String)
	 * @see dss.control.ControlPackage#getRelay_MonitoredObj()
	 * @model
	 * @generated
	 */
	String getMonitoredObj();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getMonitoredObj <em>Monitored Obj</em>}' attribute.
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
	 * Number of the terminal of the circuit element to which the Relay is connected. 1 or 2, typically.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Monitored Term</em>' attribute.
	 * @see #setMonitoredTerm(int)
	 * @see dss.control.ControlPackage#getRelay_MonitoredTerm()
	 * @model default="1"
	 * @generated
	 */
	int getMonitoredTerm();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getMonitoredTerm <em>Monitored Term</em>}' attribute.
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
	 * Name of circuit element switch that the Relay controls. Specify the full object name. Defaults to the same as the Monitored element. This is the "controlled" element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Switched Obj</em>' attribute.
	 * @see #setSwitchedObj(String)
	 * @see dss.control.ControlPackage#getRelay_SwitchedObj()
	 * @model
	 * @generated
	 */
	String getSwitchedObj();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getSwitchedObj <em>Switched Obj</em>}' attribute.
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
	 * Number of the terminal of the controlled element in which the switch is controlled by the Relay. 1 or 2, typically.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Switched Term</em>' attribute.
	 * @see #setSwitchedTerm(int)
	 * @see dss.control.ControlPackage#getRelay_SwitchedTerm()
	 * @model default="1"
	 * @generated
	 */
	int getSwitchedTerm();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getSwitchedTerm <em>Switched Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switched Term</em>' attribute.
	 * @see #getSwitchedTerm()
	 * @generated
	 */
	void setSwitchedTerm(int value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link dss.control.relayType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * One of a legal relay type:
	 *     Current Voltage Reversepower 46 (neg seq current)
	 *     47 (neg seq voltage)
	 *     Generic (generic over/under relay) Default is overcurrent relay
	 *     (Current) Specify the curve and pickup settings appropriate for each
	 *     type. Generic relays monitor PC Element Control variables and trip on
	 *     out of over/under range in definite time.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see dss.control.relayType
	 * @see #setType(relayType)
	 * @see dss.control.ControlPackage#getRelay_Type()
	 * @model
	 * @generated
	 */
	relayType getType();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see dss.control.relayType
	 * @see #getType()
	 * @generated
	 */
	void setType(relayType value);

	/**
	 * Returns the value of the '<em><b>Phase Curve</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of the TCC Curve object that determines the phase trip.  Must have been previously defined as a TCC_Curve object. Default is none (ignored).  For overcurrent relay, multiplying the current values in the curve by the "phasetrip" value gives the actual current.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phase Curve</em>' attribute.
	 * @see #setPhaseCurve(String)
	 * @see dss.control.ControlPackage#getRelay_PhaseCurve()
	 * @model
	 * @generated
	 */
	String getPhaseCurve();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getPhaseCurve <em>Phase Curve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phase Curve</em>' attribute.
	 * @see #getPhaseCurve()
	 * @generated
	 */
	void setPhaseCurve(String value);

	/**
	 * Returns the value of the '<em><b>Ground Curve</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of the TCC Curve object that determines the ground trip. Must have been previously defined as a TCC_Curve object. For overcurrent relay, multiplying the current values in the curve by the "groundtrip" value gives the actual current.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ground Curve</em>' attribute.
	 * @see #setGroundCurve(String)
	 * @see dss.control.ControlPackage#getRelay_GroundCurve()
	 * @model
	 * @generated
	 */
	String getGroundCurve();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getGroundCurve <em>Ground Curve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ground Curve</em>' attribute.
	 * @see #getGroundCurve()
	 * @generated
	 */
	void setGroundCurve(String value);

	/**
	 * Returns the value of the '<em><b>Phase Trip</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Multiplier or actual phase amps for the phase TCC curve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phase Trip</em>' attribute.
	 * @see #setPhaseTrip(double)
	 * @see dss.control.ControlPackage#getRelay_PhaseTrip()
	 * @model default="1.0"
	 * @generated
	 */
	double getPhaseTrip();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getPhaseTrip <em>Phase Trip</em>}' attribute.
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
	 * @see dss.control.ControlPackage#getRelay_GroundTrip()
	 * @model default="1.0"
	 * @generated
	 */
	double getGroundTrip();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getGroundTrip <em>Ground Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ground Trip</em>' attribute.
	 * @see #getGroundTrip()
	 * @generated
	 */
	void setGroundTrip(double value);

	/**
	 * Returns the value of the '<em><b>TD Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time dial for Phase trip curve. Multiplier on time axis of specified curve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>TD Phase</em>' attribute.
	 * @see #setTDPhase(double)
	 * @see dss.control.ControlPackage#getRelay_TDPhase()
	 * @model
	 * @generated
	 */
	double getTDPhase();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getTDPhase <em>TD Phase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TD Phase</em>' attribute.
	 * @see #getTDPhase()
	 * @generated
	 */
	void setTDPhase(double value);

	/**
	 * Returns the value of the '<em><b>TD Ground</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time dial for Ground trip curve. Multiplier on time axis of specified curve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>TD Ground</em>' attribute.
	 * @see #setTDGround(double)
	 * @see dss.control.ControlPackage#getRelay_TDGround()
	 * @model
	 * @generated
	 */
	double getTDGround();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getTDGround <em>TD Ground</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TD Ground</em>' attribute.
	 * @see #getTDGround()
	 * @generated
	 */
	void setTDGround(double value);

	/**
	 * Returns the value of the '<em><b>Phase Inst</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Actual amps (Current relay) or kW (reverse power relay) for instantaneous phase trip which is assumed to happen in 0.01 sec + Delay Time. Default is 0.0, which signifies no inst trip. Use this value for specifying the Reverse Power threshold (kW) for reverse power relays.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phase Inst</em>' attribute.
	 * @see #setPhaseInst(double)
	 * @see dss.control.ControlPackage#getRelay_PhaseInst()
	 * @model
	 * @generated
	 */
	double getPhaseInst();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getPhaseInst <em>Phase Inst</em>}' attribute.
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
	 * Actual  amps for instantaneous ground trip which is assumed to happen in 0.01 sec + Delay Time.Default is 0.0, which signifies no inst trip.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ground Inst</em>' attribute.
	 * @see #setGroundInst(double)
	 * @see dss.control.ControlPackage#getRelay_GroundInst()
	 * @model
	 * @generated
	 */
	double getGroundInst();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getGroundInst <em>Ground Inst</em>}' attribute.
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
	 * Reset time in sec for relay.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reset</em>' attribute.
	 * @see #setReset(double)
	 * @see dss.control.ControlPackage#getRelay_Reset()
	 * @model default="15.0"
	 * @generated
	 */
	double getReset();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getReset <em>Reset</em>}' attribute.
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
	 * Number of shots to lockout. This is one more than the number of reclose intervals.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Shots</em>' attribute.
	 * @see #setShots(int)
	 * @see dss.control.ControlPackage#getRelay_Shots()
	 * @model default="4"
	 * @generated
	 */
	int getShots();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getShots <em>Shots</em>}' attribute.
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
	 * Array of reclose intervals. If none, specify "NONE". Default for overcurrent relay is (0.5, 2.0, 2.0) seconds. Default for a voltage relay is (5.0). In a voltage relay, this is  seconds after restoration of voltage that the reclose occurs. Reverse power relay is one shot to lockout, so this is ignored.  A locked out relay must be closed manually (set action=close).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reclose Intervals</em>' attribute list.
	 * @see dss.control.ControlPackage#getRelay_RecloseIntervals()
	 * @model
	 * @generated
	 */
	EList<Double> getRecloseIntervals();

	/**
	 * Returns the value of the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Trip time delay (sec) for definite time relays. Default is 0.0 for current and voltage relays.  If >0 then this value is used instead of curves.  Used exclusively by RevPower, 46 and 47 relays at this release.  Defaults to 0.1 s for these relays.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Delay</em>' attribute.
	 * @see #setDelay(double)
	 * @see dss.control.ControlPackage#getRelay_Delay()
	 * @model
	 * @generated
	 */
	double getDelay();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getDelay <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay</em>' attribute.
	 * @see #getDelay()
	 * @generated
	 */
	void setDelay(double value);

	/**
	 * Returns the value of the '<em><b>Overvolt Curve</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * TCC Curve object to use for overvoltage relay.  Curve is assumed to be defined with per unit voltage values. Voltage base should be defined for the relay.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overvolt Curve</em>' reference.
	 * @see #setOvervoltCurve(TimeCurrentCurve)
	 * @see dss.control.ControlPackage#getRelay_OvervoltCurve()
	 * @model
	 * @generated
	 */
	TimeCurrentCurve getOvervoltCurve();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getOvervoltCurve <em>Overvolt Curve</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overvolt Curve</em>' reference.
	 * @see #getOvervoltCurve()
	 * @generated
	 */
	void setOvervoltCurve(TimeCurrentCurve value);

	/**
	 * Returns the value of the '<em><b>Undervolt Curve</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * TCC Curve object to use for undervoltage relay.  Curve is assumed to be defined with per unit voltage values. Voltage base should be defined for the relay.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Undervolt Curve</em>' reference.
	 * @see #setUndervoltCurve(TimeCurrentCurve)
	 * @see dss.control.ControlPackage#getRelay_UndervoltCurve()
	 * @model
	 * @generated
	 */
	TimeCurrentCurve getUndervoltCurve();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getUndervoltCurve <em>Undervolt Curve</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Undervolt Curve</em>' reference.
	 * @see #getUndervoltCurve()
	 * @generated
	 */
	void setUndervoltCurve(TimeCurrentCurve value);

	/**
	 * Returns the value of the '<em><b>KV Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Voltage base (kV) for the relay. Specify line-line for 3 phase devices); line-neutral for 1-phase devices.  Relay assumes the number of phases of the monitored element.  Default is 0.0, which results in assuming the voltage values in the "TCC" curve are specified in actual line-to-neutral volts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV Base</em>' attribute.
	 * @see #setKVBase(double)
	 * @see dss.control.ControlPackage#getRelay_KVBase()
	 * @model
	 * @generated
	 */
	double getKVBase();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getKVBase <em>KV Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV Base</em>' attribute.
	 * @see #getKVBase()
	 * @generated
	 */
	void setKVBase(double value);

	/**
	 * Returns the value of the '<em><b>Pct Pickup47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent voltage pickup for 47 relay (Neg seq voltage). Specify also base voltage (kvbase) and delay time value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Pickup47</em>' attribute.
	 * @see #setPctPickup47(double)
	 * @see dss.control.ControlPackage#getRelay_PctPickup47()
	 * @model
	 * @generated
	 */
	double getPctPickup47();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getPctPickup47 <em>Pct Pickup47</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Pickup47</em>' attribute.
	 * @see #getPctPickup47()
	 * @generated
	 */
	void setPctPickup47(double value);

	/**
	 * Returns the value of the '<em><b>Pct Amps46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base current, Amps, for 46 relay (neg seq current). Used for establishing pickup and per unit I-squared-t.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Amps46</em>' attribute.
	 * @see #setPctAmps46(double)
	 * @see dss.control.ControlPackage#getRelay_PctAmps46()
	 * @model
	 * @generated
	 */
	double getPctAmps46();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getPctAmps46 <em>Pct Amps46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Amps46</em>' attribute.
	 * @see #getPctAmps46()
	 * @generated
	 */
	void setPctAmps46(double value);

	/**
	 * Returns the value of the '<em><b>Pct Pickup46</b></em>' attribute.
	 * The default value is <code>"20.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent pickup current for 46 relay (neg seq current). When current exceeds this value * BaseAmps, I-squared-t calc starts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Pickup46</em>' attribute.
	 * @see #setPctPickup46(double)
	 * @see dss.control.ControlPackage#getRelay_PctPickup46()
	 * @model default="20.0"
	 * @generated
	 */
	double getPctPickup46();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getPctPickup46 <em>Pct Pickup46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Pickup46</em>' attribute.
	 * @see #getPctPickup46()
	 * @generated
	 */
	void setPctPickup46(double value);

	/**
	 * Returns the value of the '<em><b>ISQT46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Negative Sequence I-squared-t trip value for 46 relay (neg seq current).  Default is 1 (trips in 1 sec for 1 per unit neg seq current).  Should be 1 to 99.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>ISQT46</em>' attribute.
	 * @see #setISQT46(double)
	 * @see dss.control.ControlPackage#getRelay_ISQT46()
	 * @model
	 * @generated
	 */
	double getISQT46();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getISQT46 <em>ISQT46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ISQT46</em>' attribute.
	 * @see #getISQT46()
	 * @generated
	 */
	void setISQT46(double value);

	/**
	 * Returns the value of the '<em><b>Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of variable in PC Elements being monitored.  Only applies to Generic relay.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Variable</em>' attribute.
	 * @see #setVariable(String)
	 * @see dss.control.ControlPackage#getRelay_Variable()
	 * @model
	 * @generated
	 */
	String getVariable();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getVariable <em>Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable</em>' attribute.
	 * @see #getVariable()
	 * @generated
	 */
	void setVariable(String value);

	/**
	 * Returns the value of the '<em><b>Overtrip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Trip setting (high value) for Generic relay variable. Relay trips in definite time if value of variable exceeds this value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtrip</em>' attribute.
	 * @see #setOvertrip(double)
	 * @see dss.control.ControlPackage#getRelay_Overtrip()
	 * @model
	 * @generated
	 */
	double getOvertrip();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getOvertrip <em>Overtrip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overtrip</em>' attribute.
	 * @see #getOvertrip()
	 * @generated
	 */
	void setOvertrip(double value);

	/**
	 * Returns the value of the '<em><b>Undertrip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Trip setting (low value) for Generic relay variable.  Relay trips in definite time if value of variable is less than this value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Undertrip</em>' attribute.
	 * @see #setUndertrip(double)
	 * @see dss.control.ControlPackage#getRelay_Undertrip()
	 * @model
	 * @generated
	 */
	double getUndertrip();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getUndertrip <em>Undertrip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Undertrip</em>' attribute.
	 * @see #getUndertrip()
	 * @generated
	 */
	void setUndertrip(double value);

	/**
	 * Returns the value of the '<em><b>Breaker Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fixed delay time (sec) added to relay time. Designed to represent breaker time or some other delay after a trip decision is made.Use Delay_Time property for setting a fixed trip time delay.Added to trip time of current and voltage relays. Could use in combination with inst trip value to obtain a definite time overcurrent relay.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Breaker Time</em>' attribute.
	 * @see #setBreakerTime(double)
	 * @see dss.control.ControlPackage#getRelay_BreakerTime()
	 * @model
	 * @generated
	 */
	double getBreakerTime();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getBreakerTime <em>Breaker Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Breaker Time</em>' attribute.
	 * @see #getBreakerTime()
	 * @generated
	 */
	void setBreakerTime(double value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * The literals are from the enumeration {@link dss.common.tripAction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Action that overrides the relay control. Simulates manual control on breaker. "Trip" or "Open" causes the controlled element to open and lock out. "Close" causes the controlled element to close and the relay to reset to its first operation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see dss.common.tripAction
	 * @see #setAction(tripAction)
	 * @see dss.control.ControlPackage#getRelay_Action()
	 * @model
	 * @generated
	 */
	tripAction getAction();

	/**
	 * Sets the value of the '{@link dss.control.Relay#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see dss.common.tripAction
	 * @see #getAction()
	 * @generated
	 */
	void setAction(tripAction value);

} // Relay
