package com.epri.dss.meter.impl;

import java.io.File;
import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.FeederObj;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.shared.CktTree;
import com.epri.dss.shared.impl.CktTreeImpl.CktTreeNode;

public class EnergyMeterObjImpl extends MeterElementImpl implements EnergyMeterObj {
	
	private boolean FirstSampleAfterReset;
	private boolean ExcessFlag;
	private boolean ZoneIsRadial;
	private boolean VoltageUEOnly;
	private boolean LocalOnly;
	private boolean HasFeeder;

	private boolean Losses;
	private boolean LineLosses;
	private boolean XfmrLosses;
	private boolean SeqLosses;
	private boolean ThreePhaseLosses;
	private boolean VBaseLosses;
	private boolean PhaseVoltageReport;

	private FeederObj FeederObj;   // not used at present
	private String[] DefinedZoneList;
	private int DefinedZoneListSize;

	/* Limits on the entire load in the zone for networks where UE cannot be
	 * determined by the individual branches.
	 */
	private double MaxZonekVA_Norm;
	private double MaxZonekVA_Emerg;

	/* Voltage bases in the Meter Zone */
	private double[] VBaseTotalLosses;    // allocated array
	private double[] VBaseLineLosses;
	private double[] VBaseLoadLosses;
	private double[] VBaseNoLoadLosses;
	private double[] VBaseLoad;
	private double[] VBaseList;
	private int VBaseCount;
	private int MaxVBaseCount;

	/* Arrays for phase voltage report */
	private double[] VphaseMax;
	private double[] VPhaseMin;
	private double[] VPhaseAccum;
	private int[] VPhaseAccumCount;
	private File VPhase_File;
	private boolean VPhaseReportFileIsOpen;

	/* Demand Interval File variables */
	private File DI_File;
	private boolean This_Meter_DIFileIsOpen;
	
	
	protected String[] RegisterNames = new String[NumEMRegisters];
	
	protected CktTree BranchList;  // Pointers to all circuit elements in meter's zone

	protected double[] Registers   = new double[NumEMRegisters];
	protected double[] Derivatives = new double[NumEMRegisters];
	protected double[] TotalsMask  = new double[NumEMRegisters];

	public EnergyMeterObjImpl(DSSClassImpl ParClass, String EnergyMeterName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	private void integrate(int Reg, double Deriv, double Interval) {
		
	}
	
	private void setDragHandRegister(int Reg, double Value) {
		
	}
	
	private double accumulateLoad(LoadObj Load, double TotalZonekW,
			double TotalZonekvar, double TotalLoad_EEN, double TotalLoad_UE) {
		return 0.0;
	}
	
	private void accumulateGen(GeneratorObj Gen, double TotalZonekW, double TotalZonekvar) {
		
	}
	
	private void calcBusCoordinates(CktTreeNode StartBranch, int FirstCoordRef,
			int SecondCoordRef, int LineCount) {
		
	}
	
	private int addToVoltBaseList(int BusRef) {
		return 0;
	}
	
	private String makeDIFileName() {
		return null;
	}
	
	private String makeVPhaseReportFileName() {
		return null;
	}
	
	private void assignVoltBaseRegisterNames() {
		
	}

	private void makeFeederObj() {
		
	}
	
	private void removeFeederObj() {
		
	}
	
	private void totalUpDownstreamCustomers() {
		
	}
	
	protected void openDemandIntervalFile() {
		
	}
	
	protected void writeDemandIntervalData() {
		
	}
	
	protected void closeDemandIntervalFile() {
		
	}
	
	protected void appendDemandIntervalFile() {
		
	}

	public String[] getRegisterNames() {
		return RegisterNames;
	}

	public void setRegisterNames(String[] registerNames) {
		RegisterNames = registerNames;
	}

	public CktTree getBranchList() {
		return BranchList;
	}

	public void setBranchList(CktTree branchList) {
		BranchList = branchList;
	}

	public double[] getRegisters() {
		return Registers;
	}

	public void setRegisters(double[] registers) {
		Registers = registers;
	}

	public double[] getDerivatives() {
		return Derivatives;
	}

	public void setDerivatives(double[] derivatives) {
		Derivatives = derivatives;
	}

	public double[] getTotalsMask() {
		return TotalsMask;
	}

	public void setTotalsMask(double[] totalsMask) {
		TotalsMask = totalsMask;
	}
	
	/* Make a positive Sequence Model */
	@Override
	public void makePosSequence() {
		
	}

	@Override
	public void recalcElementData() {
		
	}
	
	@Override
	public void calcYPrim() {
		
	}
	
	@Override
	public void getInjCurrents(Complex[] Curr) {
		
	}
	
	@Override
	public void getCurrents(Complex[] Curr) {
		
	}
	
	public void resetRegisters() {
		
	}
	
	@Override
	public void takeSample() {
		
	}
	
	public void saveRegisters() {
		
	}
	
	public void makeMeterZoneLists() {
		
	}
	
	public void zoneDump() {
		
	}
	
	public void interpolateCoordinates() {
		
	}
	
	public void enableFeeder() {
		
	}

	public void allocateLoad() {
		
	}
	
	/* Reduce Zone by eliminating buses and merging lines */
	public void reduceZone() {
		
	}
	
	public void saveZone(String dirname) {
		
	}

	@Override
	public String getPropertyValue(int Index) {
		return null;
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override 
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}

}
