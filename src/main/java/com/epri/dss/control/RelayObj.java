package com.epri.dss.control;

import com.epri.dss.common.CktElement;
import com.epri.dss.control.impl.ControlAction;
import com.epri.dss.general.TCC_CurveObj;
import com.epri.dss.shared.impl.Complex;

/**
 * A Relay is a control element that is connected to a terminal of a
 * circuit element and controls the switches in the same or another terminal.
 *
 * The control is usually placed in the terminal of a line or transformer,
 * but it could be any element.
 *
 * A Relay is defined by a New command:
 *
 * New Relay.Name=myname Element=devclass.name terminal=[ 1|2|...] Switch = devclass.name   terminal=[ 1|2|...]
 * Type = [current | voltage]
 * Phase = TCCCurve
 * Ground = TCCCurve
 * OverVolt = TCCcurve
 * UnderVolt = TCCCurve
 * PhaseTrip =  Multipliers times curve
 * GroundTrip =
 * PhaseInst  =
 * GroundInst =
 * RecloseIntervals= (array of times, sec);
 * ResetTime =
 *
 * CktElement to be controlled must already exist.
 *
 * Voltage relay is a definite time relay that operates after the voltage
 * stays out of bounds for a fixed time interval.  It will then reclose a
 * set time after the voltage comes back in the normal range.
 *
 */
public interface RelayObj extends ControlElem {

	// FIXME Private method in OpenDSS
	void interpretRelayType(String S);

	// FIXME Private method in OpenDSS
	void interpretRelayAction(String Action);


	// FIXME Private members in OpenDSS

	int getControlType();

	void setControlType(int controlType);

	TCC_CurveObj getPhaseCurve();

	void setPhaseCurve(TCC_CurveObj phaseCurve);

	TCC_CurveObj getGroundCurve();

	void setGroundCurve(TCC_CurveObj groundCurve);

	double getPhaseTrip();

	void setPhaseTrip(double phaseTrip);

	double getGroundTrip();

	void setGroundTrip(double groundTrip);

	double getPhaseInst();

	void setPhaseInst(double phaseInst);

	double getGroundInst();

	void setGroundInst(double groundInst);

	double[] getRecloseIntervals();

	void setRecloseIntervals(double[] recloseIntervals);

	int getNumReclose();

	void setNumReclose(int numReclose);

	double getResetTime();

	void setResetTime(double resetTime);

	double getDelay_Time();

	void setDelay_Time(double delay_Time);

	double getBreaker_time();

	void setBreaker_time(double breaker_time);

	double getTDPhase();

	void setTDPhase(double tDPhase);

	double getTDGround();

	void setTDGround(double tDGround);

	String getRelayTarget();

	void setRelayTarget(String relayTarget);

	TCC_CurveObj getOVcurve();

	void setOVcurve(TCC_CurveObj oVcurve);

	TCC_CurveObj getUVCurve();

	void setUVCurve(TCC_CurveObj uVCurve);

	double getVbase();

	void setVbase(double vbase);

	double getkVBase();

	void setkVBase(double kVBase);

	double getPickupAmps46();

	void setPickupAmps46(double pickupAmps46);

	double getPctPickup46();

	void setPctPickup46(double pctPickup46);

	double getBaseAmps46();

	void setBaseAmps46(double baseAmps46);

	double getIsqt46();

	void setIsqt46(double isqt46);

	double getPickupVolts47();

	void setPickupVolts47(double pickupVolts47);

	double getPctPickup47();

	void setPctPickup47(double pctPickup47);

	double getOverTrip();

	void setOverTrip(double overTrip);

	double getUnderTrip();

	void setUnderTrip(double underTrip);

	String getMonitoredElementName();

	void setMonitoredElementName(String monitoredElementName);

	int getMonitoredElementTerminal();

	void setMonitoredElementTerminal(int monitoredElementTerminal);

	CktElement getMonitoredElement();

	void setMonitoredElement(CktElement monitoredElement);

	ControlAction getPresentState();

	void setPresentState(ControlAction presentState);

	int getOperationCount();

	void setOperationCount(int operationCount);

	boolean isLockedOut();

	void setLockedOut(boolean lockedOut);

	boolean isArmedForClose();

	void setArmedForClose(boolean armedForClose);

	boolean isArmedForOpen();

	void setArmedForOpen(boolean armedForOpen);

	boolean isPhaseTarget();

	void setPhaseTarget(boolean phaseTarget);

	boolean isGroundTarget();

	void setGroundTarget(boolean groundTarget);

	double getNextTriptime();

	void setNextTriptime(double nextTriptime);

	int getLastEventHandle();

	void setLastEventHandle(int lastEventHandle);

	int getCondOffset();

	void setCondOffset(int condOffset);

	Complex[] getcBuffer();

	void setcBuffer(Complex[] cBuffer);

}
