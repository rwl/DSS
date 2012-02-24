package com.ncond.dss.control;

import java.io.File;

import com.ncond.dss.delivery.TransformerObj;

import org.apache.commons.math.complex.Complex;

/**
 * A control element that is connected to a terminal of another
 * circuit element that must be a transformer.
 *
 *   new regControl.name=myName transformer=name terminal=[1,2,...] controlledBus=name etc...
 *
 * Transformer to be controlled must already exist.
 *
 */
public interface RegControlObj extends ControlElem {

	/** Controlled transformer. */
	TransformerObj getTransformer();

	/** Report tapped winding. */
	int getWinding();

	/* CIM accessors */

	double getMinTap();

	double getMaxTap();

	double getTapIncrement();

	int getNumTaps();

	void setPendingTapChange(double value);

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

	double getVReg();

	void setVReg(double vreg);

	double getR();

	void setR(double r);

	double getX();

	void setX(double x);

	double getRevVReg();

	void setRevVReg(double revVreg);

	String getRegulatedBus();

	void setRegulatedBus(String regulatedBus);

	boolean isReversible();

	void setReversible(boolean isReversible);

	boolean isLDCActive();

	void setLDCActive(boolean LDCActive);

	boolean isUsingRegulatedBus();

	void setUsingRegulatedBus(boolean usingRegulatedBus);

	boolean isDebugTrace();

	void setDebugTrace(boolean debugTrace);

	boolean isArmed();

	void setArmed(boolean armed);

	File getTraceFile();

	void setTraceFile(File tracefile);

	int getTapLimitPerChange();

	void setTapLimitPerChange(int tapLimitPerChange);

	int getTapWinding();

	void setTapWinding(int tapWinding);

	void setInverseTime(boolean inversetime);

	double getVLimit();

	void setVLimit(double vlimit);

	boolean isVLimitActive();

	void setVLimitActive(boolean vLimitActive);

	int getPTPhase();

	void setPTPhase(int ptphase);

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

	void setKWRevPowerThreshold(double kWRevPowerThreshold);

	double getKWRevPowerThreshold();

	void setRevDelay(double revDelay);

	void setReverseNeutral(boolean reverseNeutral);

	void setRevPowerThreshold(double revPowerThreshold);

	double getRevPowerThreshold();

	double getRevDelay();

	boolean isReverseNeutral();

}
