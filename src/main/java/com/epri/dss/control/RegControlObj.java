package com.epri.dss.control;

import java.io.File;

import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.shared.impl.Complex;

/**
 * A RegControl is a control element that is connected to a terminal of another
 * circuit element that must be a transformer.
 *
 * A RegControl is defined by a New command:
 *
 * New RegControl.Name=myname Transformer = name Terminal=[1,2,...] Controlledbus=name etc...
 *
 * Transformer to be controlled must already exist.
 *
 */
public interface RegControlObj extends ControlElem {

	/* Controlled transformer. */
	TransformerObj getTransformer();

	/* Report tapped winding. */
	int getWinding();

	/* CIM accessors */

	double getMinTap();

	double getMaxTap();

	double getTapIncrement();

	int getNumTaps();

	void setPendingTapChange(double Value);

	double getPendingTapChange();

	double getTargetVoltage();

	double getBandwidth();

	double getPTRatio();

	double getCTRating();

	double getLineDropR();

	double getLineDropX();

	double getRevTargetVoltage();

	double getRevBandwidth();

	double getRevR();

	double getRevX();

	boolean useReverseDrop();

	boolean useLineDrop();

	double getTapDelay();

	int getMaxTapChange();

	boolean isInverseTime();

	double getVoltageLimit();

	boolean useLimit();

	// FIXME Private members in OpenDSS

	double getVreg();

	void setVreg(double vreg);

	double getR();

	void setR(double r);

	double getX();

	void setX(double x);

	double getRevVreg();

	void setRevVreg(double revVreg);

	String getRegulatedBus();

	void setRegulatedBus(String regulatedBus);

	boolean isIsReversible();

	void setIsReversible(boolean isReversible);

	boolean isLDCActive();

	void setLDCActive(boolean lDCActive);

	boolean isUsingRegulatedBus();

	void setUsingRegulatedBus(boolean usingRegulatedBus);

	boolean isDebugTrace();

	void setDebugTrace(boolean debugTrace);

	boolean isArmed();

	void setArmed(boolean armed);

	File getTracefile();

	void setTracefile(File tracefile);

	int getTapLimitPerChange();

	void setTapLimitPerChange(int tapLimitPerChange);

	int getTapWinding();

	void setTapWinding(int tapWinding);

	boolean isInversetime();

	void setInversetime(boolean inversetime);

	double getVlimit();

	void setVlimit(double vlimit);

	boolean isVLimitActive();

	void setVLimitActive(boolean vLimitActive);

	int getPTphase();

	void setPTphase(int pTphase);

	int getControlledPhase();

	void setControlledPhase(int controlledPhase);

	Complex[] getVBuffer();

	void setVBuffer(Complex[] vBuffer);

	Complex[] getCBuffer();

	void setCBuffer(Complex[] cBuffer);

	void setBandwidth(double bandwidth);

	void setPTRatio(double pTRatio);

	void setCTRating(double cTRating);

	void setRevBandwidth(double revBandwidth);

	void setRevR(double revR);

	void setRevX(double revX);

	void setTapDelay(double tapDelay);

}
