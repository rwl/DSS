package com.epri.dss.meter;

import com.epri.dss.shared.CktTree;

/**
 * This class of device accumulates the energy of the voltage and current in
 * the terminal of the device to which it is connected.
 * 
 * It is an intelligent energy meter capable of measuring losses of all
 * devices within its "zone".
 * 
 * The Zone is determined automatically after a circuit change.  The Zone
 * starts on the opposite side of the branch on which the meter is located
 * and continues in the same direction through the network until:
 *     a) an open point is encountered
 *     b) an open terminal or switch is encountered
 *     c) another energy meter is encountered
 *     d) a branch that is already included in a zone is encountered
 *     
 * It keeps track of kwh, kvarh, UE,  EEN, Losses, etc., having registers FOR
 * each of these quantities.
 * 
 * In EEN/UE calculations, line overload takes precedence.
 * 
 * If the Max Zone kW limits are specified, then these replace the line
 * overload UE/EEN numbers. These limits were added so that the user can
 * override line limits in cases such as networks where it is difficult to
 * judge the UE from the individual line limits.
 * 
 * Only the maximum |kVA| overload is accumulated, not all.  Loads downline
 * from an overload are marked WITH a factor representing the degree of
 * overload.  This is used to compute EEN/UE FOR loads.
 * 
 * FOR low voltages, the full kW FOR loads below the emergency min voltage
 * are counted. The EEN is proportioned based on how low the voltage is.
 * 
 * Emergency min voltage must be less than normal min voltage.
 *
 */
public interface EnergyMeterObj extends MeterElement {
	
	public static final int NumEMVbase = 7;
	// Total number of energy meter registers
	public static final int NumEMRegisters = 32 + 5 * NumEMVbase;   

	String[] getRegisterNames();

	void setRegisterNames(String[] registerNames);

	CktTree getBranchList();

	void setBranchList(CktTree branchList);

	double[] getRegisters();

	void setRegisters(double[] registers);

	double[] getDerivatives();

	void setDerivatives(double[] derivatives);

	double[] getTotalsMask();

	void setTotalsMask(double[] totalsMask);
	
	void resetRegisters();
	
	void saveRegisters();
	
	void makeMeterZoneLists();
	
	void zoneDump();
	
	void interpolateCoordinates();
	
	void enableFeeder();

	void allocateLoad();
	
	/* Reduce Zone by eliminating buses and merging lines */
	void reduceZone();
	
	void saveZone(String dirname);

}
