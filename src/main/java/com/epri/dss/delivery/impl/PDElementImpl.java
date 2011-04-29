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

	private double NormAmps,
		EmergAmps,
		FaultRate,
		PctPerm,
		HrsToRepair;
	private int FromTerminal,
		ToTerminal;  // Set by Meter zone for radial feeder
	private boolean IsShunt;

	private int NumCustomers;
	private int TotalCustomers;

	private PDElement ParentPDElement;

	private MeterElement MeterObj,   // Upline energymeter
		SensorObj; // Upline Sensor for this element  for allocation and estimation

	private double Overload_UE,
		OverLoad_EEN;  // Indicate amount of branch overload

	public PDElementImpl(DSSClass ParClass) {
		super(ParClass);

		this.IsShunt = false;
		this.FromTerminal = 1;
		this.NumCustomers = 0;
		this.TotalCustomers = 0;
		this.SensorObj = null;
		this.MeterObj = null;
		this.ParentPDElement = null;
		this.DSSObjType = DSSClassDefs.PD_ELEMENT;
	}

	@Override
	public void getCurrents(Complex[] Curr) {
		int i;
		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			if (isEnabled()) {
				SolutionObj sol = Globals.getActiveCircuit().getSolution();
				for (i = 0; i < Yorder; i++)
					Vterminal[i] = sol.getNodeV()[NodeRef[i]];

				YPrim.MVMult(Curr, Vterminal);
			} else {
				for (i = 0; i < Yorder; i++)
					Curr[i] = Complex.ZERO;
			}
		} catch (Exception e) {
			Globals.doErrorMsg(("Trying to Get Currents for Element: " + getName() + "."), e.getMessage(),
					"Has circuit been solved?", 660);
		}
	}

	public Complex getExcessKVANorm (int idxTerm) {
		Complex Result;

		if ((NormAmps == 0.0) || !isEnabled()) {
			OverLoad_EEN = 0.0;
			return Complex.ZERO;
		}

		Complex kVA = getPower(idxTerm).multiply(0.001);  // Also forces computation of Current into Itemp
		double Factor = maxTerminalOneIMag() / NormAmps - 1.0;
		if (Factor > 0.0) {
			OverLoad_EEN = Factor;
			Factor = 1.0 - 1.0 / (Factor + 1.0);  // To get factor
			Result = kVA.multiply(Factor) ;
		} else {
			OverLoad_EEN = 0.0;
			Result = Complex.ZERO;
		}
		/* **********DEBUG CODE: Use DLL Debug file  *** */
		/* ****    WriteDLLDebugFile(String.format("%s.%s: Terminal=%u Factor=%.7g kW=%.7g kvar=%.7g Normamps=%.7g Overload_EEN=%.7g Result=%.7g +j %.7g ",
			ParentClass.getName(), getName(), ActiveTerminalIdx, Factor, kVA.re, kVA.im, NormAmps, Overload_EEN, Result.re, Result.im));
		*/

		return Result;
	}

	public Complex getExcessKVAEmerg(int idxTerm) {
		Complex Result;

		if ((getEmergAmps() == 0.0) || !isEnabled()) {
			Overload_UE = 0.0;
			return Complex.ZERO;
		}

		Complex kVA = getPower(idxTerm).multiply(0.001);  // Also forces computation of Current into Itemp

		double Factor = maxTerminalOneIMag() / getEmergAmps() - 1.0;
		if (Factor > 0.0) {
			Overload_UE = Factor;
			Factor = 1.0 - 1.0 / (Factor + 1.0);  // To get excess
			Result = kVA.multiply(Factor);
		} else {
			Overload_UE = 0.0;
			Result = Complex.ZERO;
		}

		return Result;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[ArrayOffset + 1] = "400";  // NormAmps   TODO Check zero based indexing
		PropertyValue[ArrayOffset + 2] = "600";  // EmerAamps
		PropertyValue[ArrayOffset + 3] = "0.1";  // Fault Rate
		PropertyValue[ArrayOffset + 4] = "20";   // Pct Perm
		PropertyValue[ArrayOffset + 5] = "3";    // Hrs to repair

		super.initPropertyValues(ArrayOffset + 5);
	}

	public double getNormAmps() {
		return NormAmps;
	}

	public void setNormAmps(double normAmps) {
		NormAmps = normAmps;
	}

	public double getEmergAmps() {
		return EmergAmps;
	}

	public void setEmergAmps(double emergAmps) {
		EmergAmps = emergAmps;
	}

	public double getFaultRate() {
		return FaultRate;
	}

	public void setFaultRate(double faultRate) {
		FaultRate = faultRate;
	}

	public double getPctPerm() {
		return PctPerm;
	}

	public void setPctPerm(double pctPerm) {
		PctPerm = pctPerm;
	}

	public double getHrsToRepair() {
		return HrsToRepair;
	}

	public void setHrsToRepair(double hrsToRepair) {
		HrsToRepair = hrsToRepair;
	}

	public int getFromTerminal() {
		return FromTerminal;
	}

	public void setFromTerminal(int fromTerminal) {
		FromTerminal = fromTerminal;
	}

	public int getToTerminal() {
		return ToTerminal;
	}

	public void setToTerminal(int toTerminal) {
		ToTerminal = toTerminal;
	}

	public boolean isShunt() {
		return IsShunt;
	}

	public void setShunt(boolean isShunt) {
		IsShunt = isShunt;
	}

	public int getNumCustomers() {
		return NumCustomers;
	}

	public void setNumCustomers(int numCustomers) {
		NumCustomers = numCustomers;
	}

	public int getTotalCustomers() {
		return TotalCustomers;
	}

	public void setTotalCustomers(int totalCustomers) {
		TotalCustomers = totalCustomers;
	}

	public PDElement getParentPDElement() {
		return ParentPDElement;
	}

	public void setParentPDElement(PDElement parentPDElement) {
		ParentPDElement = parentPDElement;
	}

	public MeterElement getMeterObj() {
		return MeterObj;
	}

	public void setMeterObj(MeterElement meterObj) {
		MeterObj = meterObj;
	}

	public MeterElement getSensorObj() {
		return SensorObj;
	}

	public void setSensorObj(MeterElement sensorObj) {
		SensorObj = sensorObj;
	}

	public double getOverload_UE() {
		return Overload_UE;
	}

	public void setOverload_UE(double overload_UE) {
		Overload_UE = overload_UE;
	}

	public double getOverLoad_EEN() {
		return OverLoad_EEN;
	}

	public void setOverLoad_EEN(double overLoad_EEN) {
		OverLoad_EEN = overLoad_EEN;
	}

}
