/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.delivery;

import com.ncond.dss.shared.Complex;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.meter.MeterElement;

@SuppressWarnings("unused")
abstract public class PDElement extends CktElement {

	protected double normAmps, emergAmps, faultRate, pctPerm, hrsToRepair;
	private int fromTerminal, toTerminal;  // set by meter zone for radial feeder
	protected boolean isShunt;

	private int numCustomers;
	private int totalCustomers;

	private PDElement parentPDElement;

	private MeterElement meterObj;   // upline energymeter
	private MeterElement sensorObj;  // upline sensor for this element for allocation and estimation

	private double overloadUE, overloadEEN;  // indicate amount of branch overload

	public PDElement(DSSClass parClass) {
		super(parClass);

		isShunt = false;
		fromTerminal = 0;
		numCustomers = 0;
		totalCustomers = 0;
		sensorObj = null;
		meterObj = null;
		parentPDElement = null;
		objType = DSSClassDefs.PD_ELEMENT;
	}

	@Override
	public void getCurrents(Complex[] curr) {
		int i;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		try {
			if (isEnabled()) {
				for (i = 0; i < YOrder; i++)
					VTerminal[i] = sol.getNodeV(nodeRef[i]);

				YPrim.vMult(curr, VTerminal);
			} else {
				for (i = 0; i < YOrder; i++) curr[i] = Complex.ZERO;
			}
		} catch (Exception e) {
			DSS.doErrorMsg(("Trying to get currents for element: " + getName() + "."), e.getMessage(),
					"Has circuit been solved?", 660);
		}
	}

	public Complex getExcessKVANorm (int idxTerm) {
		double factor;
		Complex result, kVA;

		if (normAmps == 0.0 || !isEnabled()) {
			overloadEEN = 0.0;
			return Complex.ZERO;
		}

		kVA = getPower(idxTerm).mult(0.001);  // also forces computation of current into iTemp
		factor = maxTerminalOneIMag() / normAmps - 1.0;
		if (factor > 0.0) {
			overloadEEN = factor;
			factor = 1.0 - 1.0 / (factor + 1.0);  // to get factor
			result = kVA.mult(factor) ;
		} else {
			overloadEEN = 0.0;
			result = Complex.ZERO;
		}
		/* **********DEBUG CODE: Use DLL Debug file  *** */
		/* ****    WriteDLLDebugFile(String.format("%s.%s: Terminal=%u Factor=%.7g kW=%.7g kvar=%.7g Normamps=%.7g Overload_EEN=%.7g Result=%.7g +j %.7g ",
			ParentClass.getName(), getName(), ActiveTerminalIdx, Factor, kVA.re, kVA.im, NormAmps, Overload_EEN, Result.re, Result.im));
		*/

		return result;
	}

	public Complex getExcessKVAEmerg(int idxTerm) {
		double factor;
		Complex result, kVA;

		if (getEmergAmps() == 0.0 || !isEnabled()) {
			overloadUE = 0.0;
			return Complex.ZERO;
		}

		kVA = getPower(idxTerm).mult(0.001);  // also forces computation of current into iTemp

		factor = maxTerminalOneIMag() / getEmergAmps() - 1.0;
		if (factor > 0.0) {
			overloadUE = factor;
			factor = 1.0 - 1.0 / (factor + 1.0);  // to get excess
			result = kVA.mult(factor);
		} else {
			overloadUE = 0.0;
			result = Complex.ZERO;
		}

		return result;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(arrayOffset + 1, "400");  // normAmps
		setPropertyValue(arrayOffset + 2, "600");  // emerAamps
		setPropertyValue(arrayOffset + 3, "0.1");  // faultRate
		setPropertyValue(arrayOffset + 4, "20");   // pctPerm
		setPropertyValue(arrayOffset + 5, "3");    // hrsToRepair

		super.initPropertyValues(arrayOffset + 5);
	}

	public double getEmergAmps() {
		return emergAmps;
	}

	public double getFaultRate() {
		return faultRate;
	}

	public double getHrsToRepair() {
		return hrsToRepair;
	}

	public MeterElement getMeterObj() {
		return meterObj;
	}

	public double getNormAmps() {
		return normAmps;
	}

	public double getPctPerm() {
		return pctPerm;
	}

	public int getNumCustomers() {
		return numCustomers;
	}

	public PDElement getParentPDElement() {
		return parentPDElement;
	}

	public double getOverloadUE() {
		return overloadUE;
	}

	public double getOverloadEEN() {
		return overloadEEN;
	}

	public boolean isShunt() {
		return isShunt;
	}

	public int getTotalCustomers() {
		return totalCustomers;
	}

	public MeterElement getSensorObj() {
		return sensorObj;
	}

	public void setNormAmps(double normAmps) {
		this.normAmps = normAmps;
	}

	public void setEmergAmps(double emergAmps) {
		this.emergAmps = emergAmps;
	}

	public void setFaultRate(double faultRate) {
		this.faultRate = faultRate;
	}

	public void setHrsToRepair(double hrsToRepair) {
		this.hrsToRepair = hrsToRepair;
	}

	public void setFromTerminal(int fromTerminal) {
		this.fromTerminal = fromTerminal;
	}

	public void setMeterObj(MeterElement meterObj) {
		this.meterObj = meterObj;
	}

	public void setPctPerm(double pctPerm) {
		this.pctPerm = pctPerm;
	}

	public void setShunt(boolean isShunt) {
		this.isShunt = isShunt;
	}

	public void setNumCustomers(int numCustomers) {
		this.numCustomers = numCustomers;
	}

	public void setTotalCustomers(int totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public void setParentPDElement(PDElement parentPDElement) {
		this.parentPDElement = parentPDElement;
	}

	public void setSensorObj(MeterElement sensorObj) {
		this.sensorObj = sensorObj;
	}

	public void setOverloadUE(double overloadUE) {
		this.overloadUE = overloadUE;
	}

	public void setOverloadEEN(double overloadEEN) {
		this.overloadEEN = overloadEEN;
	}

}
