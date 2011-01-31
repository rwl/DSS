package com.epri.dss.control;

import com.epri.dss.control.impl.CapControlObjImpl.CapControlType;
import com.epri.dss.control.impl.ControlElemImpl.ControlAction;
import com.epri.dss.delivery.CapacitorObj;

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

}
