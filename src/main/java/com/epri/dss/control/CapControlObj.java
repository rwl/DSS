package com.epri.dss.control;

import com.epri.dss.common.CktElement;
import com.epri.dss.control.impl.ControlAction;
import com.epri.dss.control.impl.CapControlObjImpl.CapControlType;
import com.epri.dss.delivery.CapacitorObj;
import com.epri.dss.shared.impl.Complex;

/**
 * A CapControl is a control element that is connected to a terminal of another
 * circuit element and controls a capacitor.  The control is usually placed in
 * the terminal of a line or transformer, although a voltage control device
 * could be placed in the terminal of the capacitor it controls
 *
 * A CapControl is defined by a New command:
 *
 * New CapControl.Name=myname Element=devclass.name terminal=[ 1|2|...] Capacitor = name
 *
 * Capacitor to be controlled must already exist.
 *
 */
public interface CapControlObj extends ControlElem {

	CapacitorObj getCapacitor();

	void setPendingChange(ControlAction Value);

	ControlAction getPendingChange();

	CapControlType getControlType();

	void setControlType(CapControlType controlType);

	double getON_Value();

	double getOFF_Value();

	double getPFON_Value();

	double getPFOFF_Value();

	double getCTRatio();

	double getPTRatio();

	double getONDelay();

	double getOFFDelay();

	double getDeadTime();

	boolean isVOverride();

	double getVmax();

	double getVmin();

	// FIXME Private properties in OpenDSS

	int getCTPhase();

	void setCTPhase(int cTPhase);

	int getPTPhase();

	void setPTPhase(int pTPhase);

	double getLastOpenTime();

	void setLastOpenTime(double lastOpenTime);

	String getCapacitorName();

	void setCapacitorName(String capacitorName);

	CktElement getMonitoredElement();

	void setMonitoredElement(CktElement monitoredElement);

	CapacitorObj getControlledCapacitor();

	void setControlledCapacitor(CapacitorObj controlledCapacitor);

	boolean isShouldSwitch();

	void setShouldSwitch(boolean shouldSwitch);

	boolean isArmed();

	void setArmed(boolean armed);

	ControlAction getPresentState();

	void setPresentState(ControlAction presentState);

	ControlAction getInitialState();

	void setInitialState(ControlAction initialState);

	int getControlActionHandle();

	void setControlActionHandle(int controlActionHandle);

	int getCondOffset();

	void setCondOffset(int condOffset);

	Complex[] getcBuffer();

	void setcBuffer(Complex[] cBuffer);

	void setON_Value(double oN_Value);

	void setOFF_Value(double oFF_Value);

	void setPFON_Value(double pFON_Value);

	void setPFOFF_Value(double pFOFF_Value);

	void setCTRatio(double cTRatio);

	void setPTRatio(double pTRatio);

	void setONDelay(double oNDelay);

	void setOFFDelay(double oFFDelay);

	void setDeadTime(double deadTime);

	void setVOverride(boolean vOverride);

	void setVmax(double vmax);

	void setVmin(double vmin);

}
