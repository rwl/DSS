package com.epri.dss.control;

import com.epri.dss.delivery.TransformerObj;

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

}
