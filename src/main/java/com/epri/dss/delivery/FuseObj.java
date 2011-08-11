package com.epri.dss.delivery;

import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.control.ControlElem;
import com.epri.dss.control.impl.ControlAction;
import com.epri.dss.general.TCC_CurveObj;
import com.epri.dss.shared.impl.Complex;

/**
 * A control element that is connected to a terminal of a
 * circuit element and controls the switches in the same or another terminal.
 *
 * The control is usually placed in the terminal of a line or transformer,
 * but it could be any element.
 *
 * CktElement to be controlled must already exist.
 *
 */
public interface FuseObj extends ControlElem {

	// FIXME: Private in OpenDSS
	void interpretFuseAction(String Action);

	TCC_CurveObj getFuseCurve();

	void setFuseCurve(TCC_CurveObj fuseCurve);

	double getRatedCurrent();

	void setRatedCurrent(double ratedCurrent);

	double getDelayTime();

	void setDelayTime(double delayTime);

	String getMonitoredElementName();

	void setMonitoredElementName(String monitoredElementName);

	int getMonitoredElementTerminal();

	void setMonitoredElementTerminal(int monitoredElementTerminal);

	DSSCktElement getMonitoredElement();

	void setMonitoredElement(DSSCktElement monitoredElement);

	int[] gethAction();

	void sethAction(int[] hAction);

	ControlAction[] getPresentState();

	void setPresentState(ControlAction[] presentState);

	boolean[] getReadyToBlow();

	void setReadyToBlow(boolean[] readyToBlow);

	int getCondOffset();

	void setCondOffset(int condOffset);

	Complex[] getcBuffer();

	void setcBuffer(Complex[] cBuffer);
}
