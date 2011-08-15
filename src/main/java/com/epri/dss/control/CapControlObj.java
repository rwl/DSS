package com.epri.dss.control;

import com.epri.dss.common.CktElement;
import com.epri.dss.control.impl.ControlAction;
import com.epri.dss.control.impl.CapControlObjImpl.CapControlType;
import com.epri.dss.delivery.CapacitorObj;
import org.apache.commons.math.complex.Complex;

/**
 * A control element that is connected to a terminal of another
 * circuit element and controls a capacitor.  The control is usually placed in
 * the terminal of a line or transformer, although a voltage control device
 * could be placed in the terminal of the capacitor it controls.
 *
 *   new capControl.name=myName element=devClass.name terminal=[ 1|2|...] capacitor=name
 *
 * Capacitor to be controlled must already exist.
 *
 */
public interface CapControlObj extends ControlElem {

	CapacitorObj getCapacitor();

	void setPendingChange(ControlAction value);

	ControlAction getPendingChange();

	CapControlType getControlType();

	void setControlType(CapControlType controlType);

	double getOnValue();

	double getOffValue();

	double getPFOnValue();

	double getPFOffValue();

	double getCTRatio();

	double getPTRatio();

	double getOnDelay();

	double getOffDelay();

	double getDeadTime();

	boolean isVOverride();

	double getVMax();

	double getVMin();

	// FIXME Private properties in OpenDSS

	int getCTPhase();

	void setCTPhase(int CTPhase);

	int getPTPhase();

	void setPTPhase(int PTPhase);

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

	Complex[] getCBuffer();

	void setCBuffer(Complex[] cBuffer);

	void setOnValue(double onValue);

	void setOffValue(double offValue);

	void setPFOnValue(double PFOnValue);

	void setPFOffValue(double PFOffValue);

	void setCTRatio(double CTRatio);

	void setPTRatio(double PTRatio);

	void setOnDelay(double ONDelay);

	void setOffDelay(double OffDelay);

	void setDeadTime(double deadTime);

	void setVOverride(boolean VOverride);

	void setVMax(double vmax);

	void setVMin(double vmin);

}
