package com.epri.dss.delivery.impl;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.meter.MeterElement;

public class PDElementImpl extends DSSCktElement implements PDElement {

	protected double normAmps,
		emergAmps,
		faultRate,
		pctPerm,
		hrsToRepair;
	private int fromTerminal,
		toTerminal;  // set by meter zone for radial feeder
	protected boolean isShunt;

	private int numCustomers;
	private int totalCustomers;

	private PDElement parentPDElement;

	private MeterElement meterObj,   // upline energymeter
		sensorObj;  // upline sensor for this element for allocation and estimation

	private double overloadUE,
		overloadEEN;  // indicate amount of branch overload

	public PDElementImpl(DSSClass parClass) {
		super(parClass);

		isShunt = false;
		fromTerminal = 1;
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
		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			if (isEnabled()) {
				SolutionObj sol = globals.getActiveCircuit().getSolution();
				for (i = 0; i < YOrder; i++)
					VTerminal[i] = sol.getNodeV()[nodeRef[i]];

				YPrim.MVMult(curr, VTerminal);
			} else {
				for (i = 0; i < YOrder; i++)
					curr[i] = Complex.ZERO;
			}
		} catch (Exception e) {
			globals.doErrorMsg(("Trying to Get Currents for Element: " + getName() + "."), e.getMessage(),
					"Has circuit been solved?", 660);
		}
	}

	public Complex getExcessKVANorm (int idxTerm) {
		Complex result;

		if ((normAmps == 0.0) || !isEnabled()) {
			overloadEEN = 0.0;
			return Complex.ZERO;
		}

		Complex kVA = getPower(idxTerm).multiply(0.001);  // also forces computation of current into iTemp
		double Factor = maxTerminalOneIMag() / normAmps - 1.0;
		if (Factor > 0.0) {
			overloadEEN = Factor;
			Factor = 1.0 - 1.0 / (Factor + 1.0);  // to get factor
			result = kVA.multiply(Factor) ;
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
		Complex result;

		if ((getEmergAmps() == 0.0) || !isEnabled()) {
			overloadUE = 0.0;
			return Complex.ZERO;
		}

		Complex kVA = getPower(idxTerm).multiply(0.001);  // also forces computation of current into iTemp

		double Factor = maxTerminalOneIMag() / getEmergAmps() - 1.0;
		if (Factor > 0.0) {
			overloadUE = Factor;
			Factor = 1.0 - 1.0 / (Factor + 1.0);  // to get excess
			result = kVA.multiply(Factor);
		} else {
			overloadUE = 0.0;
			result = Complex.ZERO;
		}

		return result;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		propertyValue[arrayOffset + 1] = "400";  // normAmps   TODO Check zero based indexing
		propertyValue[arrayOffset + 2] = "600";  // emerAamps
		propertyValue[arrayOffset + 3] = "0.1";  // faultRate
		propertyValue[arrayOffset + 4] = "20";   // pctPerm
		propertyValue[arrayOffset + 5] = "3";    // hrsToRepair

		super.initPropertyValues(arrayOffset + 5);
	}

	public double getNormAmps() {
		return normAmps;
	}

	public void setNormAmps(double amps) {
		normAmps = amps;
	}

	public double getEmergAmps() {
		return emergAmps;
	}

	public void setEmergAmps(double amps) {
		emergAmps = amps;
	}

	public double getFaultRate() {
		return faultRate;
	}

	public void setFaultRate(double rate) {
		faultRate = rate;
	}

	public double getPctPerm() {
		return pctPerm;
	}

	public void setPctPerm(double pct) {
		pctPerm = pct;
	}

	public double getHrsToRepair() {
		return hrsToRepair;
	}

	public void setHrsToRepair(double hrs) {
		hrsToRepair = hrs;
	}

	public int getFromTerminal() {
		return fromTerminal;
	}

	public void setFromTerminal(int terminal) {
		fromTerminal = terminal;
	}

	public int getToTerminal() {
		return toTerminal;
	}

	public void setToTerminal(int terminal) {
		toTerminal = terminal;
	}

	public boolean isShunt() {
		return isShunt;
	}

	public void setShunt(boolean value) {
		isShunt = value;
	}

	public int getNumCustomers() {
		return numCustomers;
	}

	public void setNumCustomers(int num) {
		numCustomers = num;
	}

	public int getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(int total) {
		totalCustomers = total;
	}

	public PDElement getParentPDElement() {
		return parentPDElement;
	}

	public void setParentPDElement(PDElement parent) {
		parentPDElement = parent;
	}

	public MeterElement getMeterObj() {
		return meterObj;
	}

	public void setMeterObj(MeterElement meter) {
		meterObj = meter;
	}

	public MeterElement getSensorObj() {
		return sensorObj;
	}

	public void setSensorObj(MeterElement sensor) {
		sensorObj = sensor;
	}

	public double getOverloadUE() {
		return overloadUE;
	}

	public void setOverload_UE(double overload) {
		overloadUE = overload;
	}

	public double getOverloadEEN() {
		return overloadEEN;
	}

	public void setOverloadEEN(double overload) {
		overloadEEN = overload;
	}

}
