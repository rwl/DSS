package com.epri.dss.delivery.impl;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassImpl;
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
		// TODO Auto-generated constructor stub
	}
	
	public Complex getExcessKVANorm (int idxTerm) {
		return null;
	}
	
	public Complex getExcessKVAEmerg(int idxTerm) {
		return null;
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

	public boolean isIsShunt() {
		return IsShunt;
	}

	public void setIsShunt(boolean isShunt) {
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
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override
	public void getCurrents(Complex[] Curr) {
		
	}

}
