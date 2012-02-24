package com.ncond.dss.control;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.control.impl.ControlAction;
import com.ncond.dss.general.TCC_CurveObj;

import org.apache.commons.math.complex.Complex;

/**
 * A control element that is connected to a terminal of a
 * circuit element and controls the switches in the same or another terminal.
 *
 * The control is usually placed in the terminal of a line or transformer,
 * but it could be any element.
 *
 *   new relay.name=myName element=devClass.name terminal=[ 1|2|...] switch=devClass.name terminal=[ 1|2|...]
 *   type = [current | voltage]
 *   phase = TCCCurve
 *   ground = TCCCurve
 *   overVolt = TCCcurve
 *   underVolt = TCCCurve
 *   phaseTrip =  Multipliers times curve
 *   groundTrip =
 *   phaseInst  =
 *   groundInst =
 *   recloseIntervals = (array of times, sec);
 *   resetTime =
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
	void interpretRelayType(String s);

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

	double getDelayTime();

	void setDelayTime(double delayTime);

	double getBreakerTime();

	void setBreakerTime(double breakerTime);

	double getTDPhase();

	void setTDPhase(double TDPhase);

	double getTDGround();

	void setTDGround(double TDGround);

	String getRelayTarget();

	void setRelayTarget(String relayTarget);

	TCC_CurveObj getOVCurve();

	void setOVCurve(TCC_CurveObj ovcurve);

	TCC_CurveObj getUVCurve();

	void setUVCurve(TCC_CurveObj uvcurve);

	double getVBase();

	void setVBase(double vbase);

	double getKVBase();

	void setKVBase(double kVBase);

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

	double getNextTripTime();

	void setNextTripTime(double nextTripTime);

	int getLastEventHandle();

	void setLastEventHandle(int lastEventHandle);

	int getCondOffset();

	void setCondOffset(int condOffset);

	Complex[] getCBuffer();

	void setCBuffer(Complex[] cBuffer);

}
