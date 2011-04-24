package com.epri.dss.meter;

import java.io.File;

import com.epri.dss.common.FeederObj;
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

	static final int NumEMVbase = 7;
	// Total number of energy meter registers
	static final int NumEMRegisters = 32 + 5 * NumEMVbase;

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


	// FIXME Private members in OpenDSS

	boolean isFirstSampleAfterReset();

	void setFirstSampleAfterReset(boolean firstSampleAfterReset);

	boolean isExcessFlag();

	void setExcessFlag(boolean excessFlag);

	boolean isZoneIsRadial();

	void setZoneIsRadial(boolean zoneIsRadial);

	boolean isVoltageUEOnly();

	void setVoltageUEOnly(boolean voltageUEOnly);

	boolean isLocalOnly();

	void setLocalOnly(boolean localOnly);

	boolean isHasFeeder();

	void setHasFeeder(boolean hasFeeder);

	boolean isLosses();

	void setLosses(boolean losses);

	boolean isLineLosses();

	void setLineLosses(boolean lineLosses);

	boolean isXfmrLosses();

	void setXfmrLosses(boolean xfmrLosses);

	boolean isSeqLosses();

	void setSeqLosses(boolean seqLosses);

	boolean isThreePhaseLosses();

	void setThreePhaseLosses(boolean threePhaseLosses);

	boolean isVBaseLosses();

	void setVBaseLosses(boolean vBaseLosses);

	boolean isPhaseVoltageReport();

	void setPhaseVoltageReport(boolean phaseVoltageReport);

	FeederObj getFeederObj();

	void setFeederObj(FeederObj feederObj);

	String[] getDefinedZoneList();

	void setDefinedZoneList(String[] definedZoneList);

	int getDefinedZoneListSize();

	void setDefinedZoneListSize(int definedZoneListSize);

	double getMaxZonekVA_Norm();

	void setMaxZonekVA_Norm(double maxZonekVA_Norm);

	double getMaxZonekVA_Emerg();

	void setMaxZonekVA_Emerg(double maxZonekVA_Emerg);

	double[] getVBaseTotalLosses();

	void setVBaseTotalLosses(double[] vBaseTotalLosses);

	double[] getVBaseLineLosses();

	void setVBaseLineLosses(double[] vBaseLineLosses);

	double[] getVBaseLoadLosses();

	void setVBaseLoadLosses(double[] vBaseLoadLosses);

	double[] getVBaseNoLoadLosses();

	void setVBaseNoLoadLosses(double[] vBaseNoLoadLosses);

	double[] getVBaseLoad();

	void setVBaseLoad(double[] vBaseLoad);

	double[] getVBaseList();

	void setVBaseList(double[] vBaseList);

	int getVBaseCount();

	void setVBaseCount(int vBaseCount);

	int getMaxVBaseCount();

	void setMaxVBaseCount(int maxVBaseCount);

	double[] getVphaseMax();

	void setVphaseMax(double[] vphaseMax);

	double[] getVPhaseMin();

	void setVPhaseMin(double[] vPhaseMin);

	double[] getVPhaseAccum();

	void setVPhaseAccum(double[] vPhaseAccum);

	int[] getVPhaseAccumCount();

	void setVPhaseAccumCount(int[] vPhaseAccumCount);

	File getVPhase_File();

	void setVPhase_File(File vPhase_File);

	boolean isVPhaseReportFileIsOpen();

	void setVPhaseReportFileIsOpen(boolean vPhaseReportFileIsOpen);

	File getDI_File();

	void setDI_File(File dI_File);

	boolean isThis_Meter_DIFileIsOpen();

	void setThis_Meter_DIFileIsOpen(boolean this_Meter_DIFileIsOpen);

}
