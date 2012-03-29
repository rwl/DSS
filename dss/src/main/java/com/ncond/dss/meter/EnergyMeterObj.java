/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.meter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.FeederObj;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.conversion.GeneratorObj;
import com.ncond.dss.conversion.LoadObj;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.LineObj;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CktTree;
import com.ncond.dss.shared.CktTreeNode;
import com.ncond.dss.shared.LineUnits;

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
public class EnergyMeterObj extends MeterElement {

	public static final int NumEMVbase = 7;

	/** Total number of energy meter registers */
	public static final int NumEMRegisters = 32 + 5 * NumEMVbase;

	private static double deltaHrs;

	private boolean firstSampleAfterReset;
	private boolean excessFlag;
	private boolean zoneIsRadial;
	private boolean voltageUEOnly;
	private boolean localOnly;
	private boolean hasFeeder;

	private boolean losses;
	private boolean lineLosses;
	private boolean xfmrLosses;
	private boolean seqLosses;
	private boolean threePhaseLosses;
	private boolean VBaseLosses;
	private boolean phaseVoltageReport;

	private FeederObj feederObj;   // not used at present
	private String[] definedZoneList;
	private int definedZoneListSize;

	/* Limits on the entire load in the zone for networks where UE cannot be
	 * determined by the individual branches. */
	private double maxZoneKVANorm;
	private double maxZoneKVAEmerg;

	/* Voltage bases in the meter zone */
	private double[] VBaseTotalLosses;  // allocated array
	private double[] VBaseLineLosses;
	private double[] VBaseLoadLosses;
	private double[] VBaseNoLoadLosses;
	private double[] VBaseLoad;
	private double[] VBaseList;
	private int VBaseCount;
	private int maxVBaseCount;

	/* Arrays for phase voltage report */
	private double[] VPhaseMax;
	private double[] VPhaseMin;
	private double[] VPhaseAccum;
	private int[] VPhaseAccumCount;
	private FileWriter VPhaseFile;
	private boolean VPhaseReportFileIsOpen;

	/* Demand interval file variables */
	private FileWriter DI_File;
	private boolean thisMeterDI_FileIsOpen;

	protected String[] registerNames = new String[NumEMRegisters];

	protected CktTree branchList;  // all circuit elements in meter"s zone

	protected double[] registers   = new double[NumEMRegisters];
	protected double[] derivatives = new double[NumEMRegisters];
	protected double[] totalsMask  = new double[NumEMRegisters];

	public EnergyMeterObj(DSSClass parClass, String energyMeterName) {
		super(parClass);

		int i;
		Circuit ckt = DSS.activeCircuit;

		setName(energyMeterName.toLowerCase());
		objType = parClass.getClassType();  // ENERGY_METER;

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(1);  // this forces allocation of terminals and conductors in base class

		excessFlag = true;  // default to excess energy for UE
		elementName = "Vsource." + ckt.getCktElements().get(0).getName();  // default to first circuit element (source)
		meteredElement = null;
		branchList = null;  // initialize to null, set later when inited

		thisMeterDI_FileIsOpen = false;
		VPhaseReportFileIsOpen  = false;

		initPropertyValues(0);

		// max zone kW limits ignored unless the user provides a rating
		maxZoneKVANorm = 0.0;
		maxZoneKVAEmerg = 0.0;

		zoneIsRadial = true;
		hasFeeder = false;
		feederObj = null;  // initialise to not assigned
		definedZoneList = null;
		definedZoneListSize = 0;
		losses = true;  /* Loss reporting switches */
		lineLosses = true;
		xfmrLosses = true;
		seqLosses = true;
		threePhaseLosses = true;
		VBaseLosses = true;
		phaseVoltageReport = false;
		VBaseList = null;
		VBaseTotalLosses = null;
		VBaseLineLosses = null;
		VBaseLoadLosses = null;
		VBaseNoLoadLosses = null;
		VBaseLoad = null;
		VBaseCount = 0;

		maxVBaseCount = (EnergyMeter.NUM_EM_REGISTERS - EnergyMeter.REG_VBASE_START) / 5;
		VBaseList         = new double[maxVBaseCount];
		VBaseTotalLosses  = new double[maxVBaseCount];
		VBaseLineLosses   = new double[maxVBaseCount];
		VBaseLoadLosses   = new double[maxVBaseCount];
		VBaseNoLoadLosses = new double[maxVBaseCount];
		VBaseLoad         = new double[maxVBaseCount];

		// arrays for phase voltage report
		VPhaseMax   = new double[maxVBaseCount * 3];
		VPhaseMin   = new double[maxVBaseCount * 3];
		VPhaseAccum = new double[maxVBaseCount * 3];
		VPhaseAccumCount = new int[maxVBaseCount * 3];

		localOnly = false;
		voltageUEOnly = false;

		// set register names  that correspond to the register quantities
		registerNames[0]  = "kWh";
		registerNames[1]  = "kvarh";
		registerNames[2]  = "Max kW";
		registerNames[3]  = "Max kVA";
		registerNames[4]  = "Zone kWh";
		registerNames[5]  = "Zone kvarh";
		registerNames[6]  = "Zone Max kW";
		registerNames[7]  = "Zone Max kVA";
		registerNames[8]  = "Overload kWh Normal";
		registerNames[9]  = "Overload kWh Emerg";
		registerNames[10] = "Load EEN";
		registerNames[11] = "Load UE";
		registerNames[12] = "Zone Losses kWh";
		registerNames[13] = "Zone Losses kvarh";
		registerNames[14] = "Zone Max kW Losses";
		registerNames[15] = "Zone Max kvar Losses";
		registerNames[16] = "Load Losses kWh";
		registerNames[17] = "Load Losses kvarh";
		registerNames[18] = "No Load Losses kWh";
		registerNames[19] = "No Load Losses kvarh";
		registerNames[20] = "Max kW Load Losses";
		registerNames[21] = "Max kW No Load Losses";
		registerNames[22] = "Line Losses";
		registerNames[23] = "Transformer Losses";

		registerNames[24] = "Line Mode Line Losses";
		registerNames[25] = "Zero Mode Line Losses";

		registerNames[26] = "3-phase Line Losses";
		registerNames[27] = "1- and 2-phase Line Losses";

		registerNames[28] = "Gen kWh";
		registerNames[29] = "Gen kvarh";
		registerNames[30] = "Gen Max kW";
		registerNames[31] = "Gen Max kVA";

		/* Registers for capturing losses by base voltage, names assigned later */
		for (i = EnergyMeter.REG_VBASE_START + 1; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			registerNames[i] = "";

		resetRegisters();
		for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			totalsMask[i] = 1.0;

		allocateSensorArrays();

		for (i = 0; i < nPhases; i++)
			sensorCurrent[i] = 400.0;

		//recalcElementData();
	}

	@Override
	public void recalcElementData() {
		int idx = Util.getCktElementIndex(elementName);

		if (idx >= 0) {  // monitored element must already exist
			meteredElement = (CktElement) DSS.activeCircuit.getCktElements().get(idx);

			/* MeteredElement must be a PDElement */
			if (!(meteredElement instanceof PDElement)) {
				meteredElement = null;  // element not found
				DSS.doErrorMsg("EnergyMeter: \"" + getName() + "\"",
					"Circuit element \""+ elementName + "\" is not a power delivery element.",
					"Element must be a PD element.", 525);
				return;
			}

			if (meteredTerminalIdx >= meteredElement.getNumTerms()) {
				DSS.doErrorMsg("EnergyMeter: \"" + getName() + "\"",
					"Terminal no. \"" + meteredTerminalIdx + "\" does not exist.",
					"Respecify terminal no.", 524);
			} else {
				if (meteredElementChanged) {
					// sets name of i-th terminal's connected bus in monitor's bus list
					// this value will be used to set the nodeRef array (see takeSample)
					setBus(0, meteredElement.getBus(meteredTerminalIdx));
					setNumPhases(meteredElement.getNumPhases());
					nConds = meteredElement.getNumConds();
					allocateSensorArrays();

					// if we come through here, throw branch list away
					branchList = null;
				}

				if (hasFeeder) makeFeederObj();  // OK to call multiple times
			}
		} else {
			meteredElement = null;  // element not found
			DSS.doErrorMsg("EnergyMeter: \"" + getName() + "\"",
				"Circuit element \""+ elementName + "\" not found.",
				"Element must be defined previously.", 525);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {

		if (meteredElement != null) {
			setBus(0, meteredElement.getBus(meteredTerminalIdx));
			setNumPhases(meteredElement.getNumPhases());
			setNumConds(meteredElement.getNumConds());
			allocateSensorArrays();
			branchList = null;
		}

		if (hasFeeder) makeFeederObj();

		super.makePosSequence();
	}

	private String makeVPhaseReportFileName() {
		return DSS.energyMeterClass.getDI_Dir() + DSS.SEPARATOR + getName() + "_PhaseVoltageReport.csv";
	}

	public void resetRegisters() {
		int i;
		for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			registers[i] = 0.0;
		for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			derivatives[i] = 0.0;

		/* Initialise drag hand registers to some big negative number */
		registers[EnergyMeter.REG_MAX_KW]             = -1.0e50;
		registers[EnergyMeter.REG_MAX_KVA]            = -1.0e50;
		registers[EnergyMeter.REG_ZONE_MAX_KW]        = -1.0e50;
		registers[EnergyMeter.REG_ZONE_MAX_KVA]       = -1.0e50;
		registers[EnergyMeter.REG_MAX_LOAD_LOSSES]    = -1.0e50;
		registers[EnergyMeter.REG_MAX_NO_LOAD_LOSSES] = -1.0e50;
		registers[EnergyMeter.REG_LOSSES_MAX_KW]      = -1.0e50;
		registers[EnergyMeter.REG_LOSSES_MAX_KVAR]    = -1.0e50;

		registers[EnergyMeter.REG_GEN_MAX_KW]         = -1.0e50;
		registers[EnergyMeter.REG_GEN_MAX_KVA]        = -1.0e50;

		firstSampleAfterReset = true;  // initialise for trapezoidal integration
		// Removed .. open in solution loop see solve yearly
		// if (EnergyMeterClass.saveDemandInterval) openDemandIntervalFile();
	}

	@Override
	public void calcYPrim() {
		// YPrim is all zeros; just leave as nil so it is ignored
	}

	public void saveRegisters() {
		PrintWriter pw;

		String csvName = "MTR_" + getName() + ".csv";

		try {
			pw = new PrintWriter(DSS.dataDirectory + csvName);

			DSS.globalResult = csvName;

			pw.print("Year, " + DSS.activeCircuit.getSolution().getYear() + ",");
			pw.println();

			for (int i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++) {
				pw.write("\"" + registerNames[i] + "\"," + registers[i]);
				pw.println();
			}

			pw.close();
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening Meter File \"" + DSS.CRLF + csvName + "\": " + e.getMessage(), 526);
			return;
		}
	}

	private void integrate(int reg, double deriv, double interval) {
		Circuit ckt = DSS.activeCircuit;

		if (ckt.isTrapezoidalIntegration()) {
			/* Trapezoidal rule integration */
			if (!firstSampleAfterReset)
				registers[reg] += 0.5 * interval * (deriv + derivatives[reg]);
		} else {
			/* Plain Euler integration */
			registers[reg] += interval * deriv;
		}

		/* Set the derivatives so that the proper value shows up in demand interval files
		 * and prepare for next time step in Trapezoidal integration. */
		derivatives[reg] = deriv;
	}

	/**
	 * Update registers from metered zone.
	 * Assumes one time period has taken place since last sample.
	 */
	@Override
	public void takeSample() {
		int i, j;

		/* pass by reference */
		Complex[] pS_TotalLosses = new Complex[1];
		Complex[] pS_LoadLosses = new Complex[1];
		Complex[] pS_NoLoadLosses = new Complex[1];
		Complex[] pS_PosSeqLosses = new Complex[1];
		Complex[] pS_ZeroSeqLosses = new Complex[1];
		Complex[] pS_NegSeqLosses = new Complex[1];

		Complex S_Local,
			S_TotalLosses,
			S_LoadLosses,
			S_NoLoadLosses,
			totalLoadLosses,
			totalNoLoadLosses,
			totalLineLosses,
			totalTransformerLosses,
			totalLineModeLosses,  // lines only for now
			totalZeroModeLosses,
			total3PhaseLosses,
			total1PhaseLosses,
			totalLosses;

		PDElement pdElem, parent;
		PCElement pcElem;
		LoadObj load;
		GeneratorObj gen;

		double maxExcessKWNorm, maxExcessKWEmerg;
		double EEN = 0, UE = 0;
		double zoneKW;
		double[] totalZoneKW, totalZoneKVAr;
		double[] totalLoad_EEN, totalLoad_UE;
		double[] totalGenKW, totalGenKVAr;
		double loadKVA, genKVA, S_LocalKVA, loadKW;
		Complex S_PosSeqLosses;
		Complex S_ZeroSeqLosses;
		Complex S_NegSeqLosses;

		double puV;

		// compute energy in branch to which meter is connected

		//meteredElement.setActiveTerminalIdx(meteredTerminalIdx);  // needed for excess kVA calcs
		S_Local = meteredElement.getPower(meteredTerminalIdx).multiply(0.001);
		S_LocalKVA = S_Local.abs();
		deltaHrs = DSS.activeCircuit.getSolution().getIntervalHrs();
		integrate(EnergyMeter.REG_KWH, S_Local.getReal(), deltaHrs);  // accumulate the power
		integrate(EnergyMeter.REG_KVARH, S_Local.getImaginary(), deltaHrs);
		setDragHandRegister(EnergyMeter.REG_MAX_KW, S_Local.getReal());
		setDragHandRegister(EnergyMeter.REG_MAX_KVA, S_LocalKVA);

		// compute maximum overload energy in all branches in zone
		// and mark all load downline from an overloaded branch as unserved
		// if localonly, check only metered element

		totalLosses = Complex.ZERO;  // initialize loss accumulators
		totalLoadLosses = Complex.ZERO;
		totalNoLoadLosses = Complex.ZERO;
		totalLineLosses = Complex.ZERO;
		totalLineModeLosses = Complex.ZERO;
		totalZeroModeLosses = Complex.ZERO;
		total3PhaseLosses = Complex.ZERO;
		total1PhaseLosses = Complex.ZERO;
		totalTransformerLosses = Complex.ZERO;

		// init all voltage base loss accumulators
		for (i = 0; i < maxVBaseCount; i++) {
			VBaseTotalLosses[i] = 0.0;
			VBaseLineLosses[i] = 0.0;
			VBaseLoadLosses[i] = 0.0;
			VBaseNoLoadLosses[i] = 0.0;
			VBaseLoad[i] = 0.0;
		}

		// phase voltage arrays
		if (phaseVoltageReport) {
			for (i = 0; i < maxVBaseCount; i++) {
				if (VBaseList[i] > 0.0) {
					for (j = 0; j < 3; j++) {
						VPhaseMax[jiIndex(j, i)] = 0.0;
						VPhaseMin[jiIndex(j, i)] = 9999.0;
						VPhaseAccum[jiIndex(j, i)] = 0.0;
						VPhaseAccumCount[jiIndex(j, i)] = 0;  // keep track of counts for average
					}
				}
			}
		}

		pdElem = (PDElement) branchList.getFirst();
		maxExcessKWNorm = 0.0;
		maxExcessKWEmerg = 0.0;

		if (localOnly) {
			/* ------------------------------------------------------------------ */
			/* ------------------------ Local Zone Only ------------------------- */
			/* ------------------------------------------------------------------ */
			pdElem = (PDElement) meteredElement;
			maxExcessKWNorm  = Math.abs(pdElem.getExcessKVANorm(meteredTerminalIdx).getReal());
			maxExcessKWEmerg = Math.abs(pdElem.getExcessKVAEmerg(meteredTerminalIdx).getReal());
		} else {
			/* -------------------------------------------------------------- */
			/* --------Cyle Through Entire Zone Setting EEN/UE -------------- */
			/* -------------------------------------------------------------- */
			while (pdElem != null) {  // loop thru all ckt elements on zone
				pdElem.setActiveTerminalIdx(branchList.getPresentBranch().getFromTerminalIdx());

				// invoking this property sets the Overload_UE flag in the PD element
				EEN = Math.abs(pdElem.getExcessKVANorm(pdElem.getActiveTerminalIdx()).getReal());
				UE = Math.abs(pdElem.getExcessKVAEmerg(pdElem.getActiveTerminalIdx()).getReal());

				/* For radial circuits just keep the maximum overload; for mesh, add them up */
				if (zoneIsRadial) {
					if (UE > maxExcessKWEmerg) maxExcessKWEmerg = UE;
					if (EEN > maxExcessKWNorm) maxExcessKWNorm = EEN;
				} else {
					maxExcessKWEmerg = maxExcessKWEmerg + UE;
					maxExcessKWNorm = maxExcessKWNorm + EEN;
				}

				// even if this branch is not overloaded, if the parent element is overloaded
				// mark load on this branch as unserved also
				// use the larger of the two factors
				parent = (PDElement) branchList.getParent();
				if (parent != null) {
					pdElem.setOverloadEEN(Math.max(pdElem.getOverloadEEN(), parent.getOverloadEEN()));
					pdElem.setOverloadUE(Math.max(pdElem.getOverloadUE(), parent.getOverloadUE()));
				}

				// mark loads (not generators) by the degree of overload if the meter's zone is to be considered radial
				// this overrides and supercedes the load's own determination of unserved based on voltage
				// if voltage only is to be used for Load UE/EEN, don't mark (set to 0.0 and load will calc UE based on voltage)
				pcElem = (PCElement) branchList.getFirstObject();
				while (pcElem != null) {
					if ((pcElem.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LOAD_ELEMENT) {
						load = (LoadObj) pcElem;
						if (pdElem.getOverloadEEN() > 0.0 && zoneIsRadial && !voltageUEOnly) {
							load.setEEN_Factor(pdElem.getOverloadEEN());
						} else {
							load.setEEN_Factor(0.0);
						}

						if (pdElem.getOverloadUE() > 0.0 && zoneIsRadial && !voltageUEOnly) {
							load.setUE_Factor(pdElem.getOverloadUE());
						} else {
							load.setUE_Factor(0.0);
						}
					}
					pcElem = (PCElement) branchList.getNextObject();
				}

				pdElem = (PDElement) branchList.goForward();
			}
		}

		// get the losses, and unserved bus energies
		totalZoneKW = new double[1];
		totalZoneKVAr = new double[1];
		totalLoad_EEN = new double[1];
		totalLoad_UE = new double[1];
		totalGenKW = new double[1];
		totalGenKVAr = new double[1];

		/* ------------------------------------------------------------------ */
		/* ----       Cycle Through Zone Accumulating Load and Losses    ---- */
		/* ------------------------------------------------------------------ */
		pdElem = (PDElement) branchList.getFirst();
		while (pdElem != null) {
			pcElem = (PCElement) branchList.getFirstObject();
			while (pcElem != null) {
				switch (pcElem.getObjType() & DSSClassDefs.CLASSMASK) {
				case DSSClassDefs.LOAD_ELEMENT:
					if (!localOnly) {  // don't check for load EEN/UE if Local only
						load = (LoadObj) pcElem;
						loadKW = accumulateLoad(load, totalZoneKW, totalZoneKVAr, totalLoad_EEN, totalLoad_UE);
						if (VBaseLosses) {
							int vbi = branchList.getPresentBranch().getVoltBaseIndex();
							if (vbi >= 0) VBaseLoad[vbi] = VBaseLoad[vbi] + loadKW;
						}
					}
					break;
				case DSSClassDefs.GEN_ELEMENT:
					gen = (GeneratorObj) pcElem;
					accumulateGen(gen, totalGenKW, totalGenKVAr);
					break;
				}
				pcElem = (PCElement) branchList.getNextObject();
			}

			if (losses) {  // compute and report losses

				/* Get losses from the present circuit element */
				pdElem.getLosses(pS_TotalLosses, pS_LoadLosses, pS_NoLoadLosses);  // returns watts, vars
				S_TotalLosses = pS_TotalLosses[0];
				S_LoadLosses = pS_LoadLosses[0];
				S_NoLoadLosses = pS_NoLoadLosses[0];

				/* Convert to kW */
				S_TotalLosses = S_TotalLosses.multiply(0.001);
				S_LoadLosses = S_LoadLosses.multiply(0.001);
				S_NoLoadLosses = S_NoLoadLosses.multiply(0.001);

				/* Update accumulators */
				totalLosses = totalLosses.add(S_TotalLosses);  // accumulate total losses in meter zone
				totalLoadLosses = totalLoadLosses.add(S_LoadLosses);  // accumulate total load losses in meter zone
				totalNoLoadLosses = totalNoLoadLosses.add(S_NoLoadLosses);  // accumulate total no load losses in meter zone

				/* Line and transformer elements */
				if (Util.isLineElement(pdElem) && lineLosses) {
					totalLineLosses = totalLineLosses.add(S_TotalLosses);  // accumulate total losses in meter zone
					if (seqLosses) {
						pdElem.getSeqLosses(pS_PosSeqLosses, pS_NegSeqLosses, pS_ZeroSeqLosses);
						S_PosSeqLosses = pS_PosSeqLosses[0];
						S_NegSeqLosses = pS_NegSeqLosses[0];
						S_ZeroSeqLosses = pS_ZeroSeqLosses[0];

						S_PosSeqLosses = S_PosSeqLosses.add(S_NegSeqLosses);  // add line modes together
						S_PosSeqLosses = S_PosSeqLosses.multiply(0.001);  // convert to kW
						S_ZeroSeqLosses = S_ZeroSeqLosses.multiply(0.001);
						totalLineModeLosses = totalLineModeLosses.add(S_PosSeqLosses);
						totalZeroModeLosses = totalZeroModeLosses.add(S_ZeroSeqLosses);
					}
					/* Separate line losses into 3- and "1-phase" losses */
					if (threePhaseLosses) {
						if (pdElem.getNumPhases() == 3) {
							total3PhaseLosses = total3PhaseLosses.add(S_TotalLosses);
						} else {
							total1PhaseLosses = total1PhaseLosses.add(S_TotalLosses);
						}
					}
				} else if (Util.isTransformerElement(pdElem) && xfmrLosses) {
					totalTransformerLosses = totalTransformerLosses.add(S_TotalLosses);  // accumulate total losses in meter zone
				}

				if (VBaseLosses) {
					int vbi = branchList.getPresentBranch().getVoltBaseIndex();
					if (vbi >= 0) {
						VBaseTotalLosses[vbi] = VBaseTotalLosses[vbi] + S_TotalLosses.getReal();
						if (Util.isLineElement(pdElem)) {
							VBaseLineLosses[vbi] = VBaseLineLosses[vbi] + S_TotalLosses.getReal();
						} else if (Util.isTransformerElement(pdElem)) {
							VBaseLoadLosses[vbi] = VBaseLoadLosses[vbi] + S_LoadLosses.getReal();
							VBaseNoLoadLosses[vbi] = VBaseNoLoadLosses[vbi] + S_NoLoadLosses.getReal();
						}
					}
				}

				// compute min, max, and average pu voltages for 1st 3 phases (nodes designated 1, 2, or 3)
				if (phaseVoltageReport) {
					int vbi = branchList.getPresentBranch().getVoltBaseIndex();
					int fbr = branchList.getPresentBranch().getFromBusReference();
					if (vbi >= 0) {
						Circuit ckt = DSS.activeCircuit;
						if (ckt.getBus(fbr).getKVBase() > 0.0) {
							for (i = 0; i < ckt.getBus(fbr).getNumNodesThisBus(); i++) {
								j = ckt.getBus(fbr).getNum(i) - 1;
								if (j >= 0 && j < 3) {
									puV = ckt.getSolution().getNodeV(ckt.getBus(fbr).getRef(i)).abs() / ckt.getBus(fbr).getKVBase();
									VPhaseMax[jiIndex(j, vbi)] = Math.max(VPhaseMax[jiIndex(j, vbi)], puV);
									VPhaseMin[jiIndex(j, vbi)] = Math.min(VPhaseMin[jiIndex(j, vbi)], puV);
									VPhaseAccum[jiIndex(j, vbi)] = VPhaseAccum[jiIndex(j, vbi)] + puV;
									VPhaseAccumCount[jiIndex(j, vbi)] += 1;  // keep track of counts for average
								}
							}
						}
					}
				}
			}  // if (losses)

			pdElem = (PDElement) branchList.goForward();
		}

		/* NOTE: integrate proc automatically sets derivatives array */
		integrate(EnergyMeter.REG_LOAD_EEN, totalLoad_EEN[0], deltaHrs);
		integrate(EnergyMeter.REG_LOAD_UE,  totalLoad_UE[0],  deltaHrs);

		/* Accumulate losses in appropriate registers */
		integrate(EnergyMeter.REG_ZONE_LOSSES_KWH,      totalLosses.getReal(),          deltaHrs);
		integrate(EnergyMeter.REG_ZONE_LOSSES_KVARH,    totalLosses.getImaginary(),     deltaHrs);
		integrate(EnergyMeter.REG_LOAD_LOSSES_KWH,      totalLoadLosses.getReal(),      deltaHrs);
		integrate(EnergyMeter.REG_LOAD_LOSSES_KVARH,    totalLoadLosses.getImaginary(), deltaHrs);
		integrate(EnergyMeter.REG_NO_LOAD_LOSSES_KWH,   totalNoLoadLosses.getReal(),    deltaHrs);
		integrate(EnergyMeter.REG_NO_LOAD_LOSSES_KVARH, totalNoLoadLosses.getImaginary(), deltaHrs);
		integrate(EnergyMeter.REG_LINE_LOSSES_KWH,      totalLineLosses.getReal(),      deltaHrs);
		integrate(EnergyMeter.REG_LINE_MODE_LINE_LOSS,  totalLineModeLosses.getReal(),  deltaHrs);
		integrate(EnergyMeter.REG_ZERO_MODE_LINE_LOSS,  totalZeroModeLosses.getReal(),  deltaHrs);
		integrate(EnergyMeter.REG_3_PHASE_LINE_LOSS,    total3PhaseLosses.getReal(),    deltaHrs);
		integrate(EnergyMeter.REG_1_PHASE_LINE_LOSS,    total1PhaseLosses.getReal(),    deltaHrs);
		integrate(EnergyMeter.REG_TRANSFORMER_LOSSES_KWH,  totalTransformerLosses.getReal(), deltaHrs);

		for (i = 0; i < maxVBaseCount; i++) {
			integrate(EnergyMeter.REG_VBASE_START + i, VBaseTotalLosses[i], deltaHrs);
			integrate(EnergyMeter.REG_VBASE_START + 1 * maxVBaseCount + i, VBaseLineLosses[i],   deltaHrs);
			integrate(EnergyMeter.REG_VBASE_START + 2 * maxVBaseCount + i, VBaseLoadLosses[i],   deltaHrs);
			integrate(EnergyMeter.REG_VBASE_START + 3 * maxVBaseCount + i, VBaseNoLoadLosses[i], deltaHrs);
			integrate(EnergyMeter.REG_VBASE_START + 4 * maxVBaseCount + i, VBaseLoad[i],         deltaHrs);
		}

		/* ------------------------------------------------------------------ */
		/* ---------------   Total Zone Load and Generation ----------------- */
		/* ------------------------------------------------------------------ */

		integrate(EnergyMeter.REG_ZONE_KWH,   totalZoneKW[0],   deltaHrs);
		integrate(EnergyMeter.REG_ZONE_KVARH, totalZoneKVAr[0], deltaHrs);
		integrate(EnergyMeter.REG_GEN_KWH,    totalGenKW[0],    deltaHrs);
		integrate(EnergyMeter.REG_GEN_KVARH,  totalGenKVAr[0],  deltaHrs);
		genKVA  = Math.sqrt(Math.pow(totalGenKVAr[0], 2)  + Math.pow(totalGenKW[0], 2));
		loadKVA = Math.sqrt(Math.pow(totalZoneKVAr[0], 2) + Math.pow(totalZoneKW[0], 2));

		/* ------------------------------------------------------------------ */
		/* ---------------   Set Drag Hand Registers  ----------------------- */
		/* ------------------------------------------------------------------ */

		setDragHandRegister(EnergyMeter.REG_LOSSES_MAX_KW,      Math.abs(totalLosses.getReal()));
		setDragHandRegister(EnergyMeter.REG_LOSSES_MAX_KVAR,    Math.abs(totalLosses.getImaginary()));
		setDragHandRegister(EnergyMeter.REG_MAX_LOAD_LOSSES,    Math.abs(totalLoadLosses.getReal()));
		setDragHandRegister(EnergyMeter.REG_MAX_NO_LOAD_LOSSES, Math.abs(totalNoLoadLosses.getReal()));
		setDragHandRegister(EnergyMeter.REG_ZONE_MAX_KW,        totalZoneKW[0]);
		setDragHandRegister(EnergyMeter.REG_ZONE_MAX_KVA,       loadKVA);
		/* Max total generator registers */
		setDragHandRegister(EnergyMeter.REG_GEN_MAX_KW, totalGenKW[0]);
		setDragHandRegister(EnergyMeter.REG_GEN_MAX_KVA, genKVA);

		/* ------------------------------------------------------------------ */
		/* ---------------------   Overload Energy  ------------------------- */
		/* ------------------------------------------------------------------ */

		/* Overload energy for the entire zone */
		zoneKW = localOnly ? S_Local.getReal() : totalZoneKW[0];

		/* Either the max excess kW of any PD element or the excess over zone limits */

		/* regs 9 and 10 */
		/* Fixed these formulas 2-7-07 per discussions with Daniel Brooks */
		if (maxZoneKVANorm > 0.0) {
			if (S_LocalKVA == 0.0) S_LocalKVA = maxZoneKVANorm;
			integrate(EnergyMeter.REG_OVERLOAD_KWH_NORM,
				Math.max(0.0, (zoneKW * (1.0 - maxZoneKVANorm / S_LocalKVA))), deltaHrs);
		} else {
			integrate(EnergyMeter.REG_OVERLOAD_KWH_NORM, maxExcessKWNorm, deltaHrs);
		}

		if (maxZoneKVAEmerg > 0.0) {
			if (S_LocalKVA == 0.0) S_LocalKVA = maxZoneKVAEmerg;
			integrate(EnergyMeter.REG_OVERLOAD_KWH_EMERG,
				Math.max(0.0, (zoneKW * (1.0 - maxZoneKVAEmerg / S_LocalKVA))), deltaHrs);
		} else {
			integrate(EnergyMeter.REG_OVERLOAD_KWH_EMERG, maxExcessKWEmerg,  deltaHrs);
		}

		firstSampleAfterReset = false;
		if (DSS.energyMeterClass.isSaveDemandInterval()) writeDemandIntervalData();
	}

	private static int jiIndex(int i, int j) {
		return j * 3 + i;
	}

	private void totalUpDownstreamCustomers() {
		int i, accumulator;
		CktTreeNode presentNode = null;
		PDElement elem;

		if (branchList == null) {
			DSS.doSimpleMsg("Meter zone lists need to be built. Solve or makeBusList first.", 529);
			return;
		}

		/* Init totals and checked flag */
		elem = (PDElement) branchList.getFirst();
		while (elem != null) {
			elem.setChecked(false);
			elem.setTotalCustomers(0);
			elem = (PDElement) branchList.goForward();
		}

		/* This algorithm could be made more efficient with a sequence list */

		for (i = 0; i < branchList.getZoneEndsList().getNumEnds(); i++) {
			/*busref = */branchList.getZoneEndsList().get(i, presentNode);
			if (presentNode != null) {
				elem = (PDElement) presentNode.getCktObject();
				if (!elem.isChecked()) {  // don't do a zone end element more than once
					elem.setChecked(true);
					accumulator = elem.getNumCustomers();
					while (true) {  /* Trace back to the source */
						elem.setTotalCustomers( elem.getTotalCustomers() + accumulator );
						presentNode = presentNode.getParent();
						if (presentNode == null) break;
						elem = (PDElement) presentNode.getCktObject();
						if (!elem.isChecked()) {  // avoid double counting
							accumulator = accumulator + elem.getNumCustomers();
							elem.setChecked(true);
						}
					}
				}
			}
		}
	}

	/**
	 * This gets fired off whenever the buslists are rebuilt.
	 * Must be updated whenever there is a change in the circuit.
	 */
	public void makeMeterZoneLists() {
		Circuit ckt = DSS.activeCircuit;

		int testBusRef, zoneListCounter;
		int j, iTerm, iPC, iPD;
		CktElement activeBranch;
		PDElement testElement;
		PCElement pc;
		LoadObj load;
		boolean isFeederEnd;
		List<PCElement> adjLstPC;
		List<PDElement> adjLstPD;

		zoneListCounter = 0;
		VBaseCount = 0;  /* Build the voltage base list over in case a base added or deleted */
		for (j = 0; j < maxVBaseCount; j++)
			VBaseList[j] = 0.0;

		branchList = new CktTree();  /* Instantiates ZoneEndsList, too */

		// get started
		if (meteredElement != null) {
			branchList.setNew(meteredElement);
		} else {
			DSS.doSimpleMsg("Metered Element for EnergyMeter " + getName() + " not defined.", 527);
			return;
		}

		/*
		 * Initialize sensorObj property of the first branch to this MeterElement object.
		 * Before starting, all sensorObj definitions are cleared in PC elements and PD elements. The
		 * sensorObj property is passed down to the Load objects for loadAllocation and state estimation.
		 */

		if (meteredElement instanceof PDElement) {
			((PDElement) meteredElement).setSensorObj(this);
		} else if (meteredElement instanceof PCElement) {
			((PCElement) meteredElement).setSensorObj(this);
		}

		if (meteredElement instanceof PDElement) {
			((PDElement) meteredElement).setMeterObj(this);
		} else if (meteredElement instanceof PCElement) {
			((PCElement) meteredElement).setMeterObj(this);
		}

		meteredElement.getTerminal(meteredTerminalIdx).setChecked(true);

		CktTreeNode pb = branchList.getPresentBranch();
		// this bus is the head of the feeder; do not mark as radial bus
		pb.setFromBusReference(meteredElement.getTerminal(meteredTerminalIdx).getBusRef());
		DSS.activeCircuit.getBus(pb.getFromBusReference() - 1).setDistFromMeter(0.0);
		pb.setVoltBaseIndex(addToVoltBaseList(pb.getFromBusReference()));
		pb.setFromTerminalIdx(meteredTerminalIdx);
		if (meteredElement instanceof PDElement)
			((PDElement) meteredElement).setFromTerminal(meteredTerminalIdx);

		// check off this element so we don't use it again
		meteredElement.setChecked(true);
		meteredElement.setIsolated(false);

		// now start looking for other branches
		// finds any branch connected to the testBranch and adds it to the list
		// goes until end of circuit, another energy meter, an open terminal, or disabled device
		activeBranch = meteredElement;
		while (activeBranch != null) {
			pb = branchList.getPresentBranch();
			pb.setLoopedHere(false);
			pb.setParallel(false);
			pb.setDangling(true);  // unless we find something connected to it
			pb.setVoltBaseIndex( addToVoltBaseList(pb.getFromBusReference()) );

			((PDElement) activeBranch).setNumCustomers(0);  // init counter

			for (iTerm = 0; iTerm < activeBranch.getNumTerms(); iTerm++) {
				if (!activeBranch.getTerminal(iTerm).isChecked()) {
					// now find all loads and generators connected to the bus on this end of branch
					// attach them as generic objects to cktTree node
					testBusRef = activeBranch.getTerminal(iTerm).getBusRef();
					branchList.getPresentBranch().setToBusReference(testBusRef);  // add this as a "to" bus reference
					if (Util.isLineElement(activeBranch)) {  // convert to consistent units (km)
						ckt.getBus(testBusRef - 1).setDistFromMeter(ckt.getBus(branchList.getPresentBranch().getFromBusReference() - 1).getDistFromMeter()
								+ ((LineObj) activeBranch).getLen() * LineUnits.convertLineUnits(((LineObj) activeBranch).getLengthUnits(), LineUnits.KM));
					} else {
						ckt.getBus(testBusRef).setDistFromMeter(ckt.getBus(branchList.getPresentBranch().getFromBusReference()).getDistFromMeter());
					}

					adjLstPC = EnergyMeter.busAdjPC[testBusRef - 1];
					for (iPC = 0; iPC < adjLstPC.size(); iPC++) {
						pc = (PCElement) adjLstPC.get(iPC);
						if (pc.isChecked()) continue;  // skip ones we already checked
						branchList.getPresentBranch().setDangling(false);  // something is connected here
						// is this a load or a generator or a capacitor or reactor?
						if (((pc.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LOAD_ELEMENT)
								|| ((pc.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.GEN_ELEMENT)
								|| ((pc.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.CAP_ELEMENT)
								|| ((pc.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.REACTOR_ELEMENT)) {

							branchList.setNewObject(pc);
							pc.setChecked(true);  // so we don't pick this element up again
							pc.setIsolated(false);
							pc.setActiveTerminalIdx(0);
							/* Totalize number of customers if load type */
							if (pc instanceof LoadObj) {
								load = (LoadObj) pc;
								((PDElement) activeBranch).setNumCustomers(((PDElement) activeBranch).getNumCustomers() + load.getNumCustomers());
							}
							/* If object does not have a sensor attached, it acquires the sensor of its parent branch */
							if (!pc.hasSensorObj()) pc.setSensorObj(((PDElement) activeBranch).getSensorObj());
							pc.setMeterObj(this);
						}
					}

					// now find all branches connected to this bus that we havent found already
					// do not include in this zone if branch has open terminals or has another meter

					if (definedZoneListSize == 0) {  // search tree for connected branches (default)
						isFeederEnd = true;
						adjLstPD = EnergyMeter.busAdjPD[testBusRef - 1];
						for (iPD = 0; iPD < adjLstPD.size(); iPD++) {
							testElement = (PDElement) adjLstPD.get(iPD);  // only enabled objects are in this list
							// see resetMeterZonesAll()
							if (!(testElement == activeBranch)) {  // skip self
								if (!testElement.hasEnergyMeter()) {  // stop at other meters so zones don't interfere
									for (j = 0; j < testElement.getNumTerms(); j++) {  // check each terminal
										if (testBusRef == testElement.getTerminal(j).getBusRef()) {
											branchList.getPresentBranch().setDangling(false);  // we found something it was connected to
											/* Check for loops and parallel branches and mark them */
											if (testElement.isChecked()) {  /* This branch is on some meter's list already */
												branchList.getPresentBranch().setLoopedHere(true);  /* It's a loop */
												branchList.getPresentBranch().setLoopLineObj(testElement);
												if (Util.isLineElement(activeBranch) && Util.isLineElement(testElement))
													if (Util.checkParallel(activeBranch, testElement))
														branchList.getPresentBranch().setParallel(true);  /* It's paralleled with another line */
											} else {  // push testElement onto stack and set properties
												isFeederEnd = false;  // for interpolation
												branchList.addNewChild(testElement, testBusRef, j);  // add new child to the branch list
												testElement.getTerminal(j).setChecked(true);
												testElement.setFromTerminal(j);
												testElement.setChecked(true);
												testElement.setIsolated(false);
												/* Branch inherits sensor of upline branch if it doesn't have its own */
												if (!hasSensorObj) testElement.setSensorObj(((PDElement) activeBranch).getSensorObj());
												testElement.setMeterObj(this);  // set meterobj to this meter
												// record the parent so we can easily back up for reconductoring, etc.
												testElement.setParentPDElement((PDElement) activeBranch);
												break;
											}
										}  /* if testBusRef */
									}  /* for terminals */
								}
							}
						}  /* for iPD */
						if (isFeederEnd) branchList.getZoneEndsList().add(branchList.getPresentBranch(), testBusRef);
						/* This is an end of the feeder and testBusNum is the end bus */
					} else {  // zone is manually specified; just add next element in list as a child
						zoneListCounter += 1;
						while (zoneListCounter < definedZoneListSize) {
							if (ckt.setElementActive(definedZoneList[zoneListCounter]) == 0) {
								zoneListCounter += 1;  // not found, let's search for another
							} else {
								testElement = (PDElement) ckt.getActiveCktElement();
								if (!testElement.isEnabled()) {
									zoneListCounter += 1;  // lets ignore disabled devices
								} else {
									if ((testElement.getObjType() & DSSClassDefs.BASECLASSMASK) != DSSClassDefs.PD_ELEMENT) {
										zoneListCounter += 1;  // lets ignore non-PD elements
									} else {
										branchList.addNewChild(testElement, 0, -1);  // FIXME: add it as a child to the previous element
									}
									break;  // can't do reductions if manually spec'd
								}
							}
						}  // while
					}
				}
			}  /* for iTerm */
			activeBranch = (CktElement) branchList.goForward();  // sets present node
		}

		totalUpDownstreamCustomers();

		if (hasFeeder) feederObj.initializeFeeder(branchList);  // synchronise the feeder definition

		assignVoltBaseRegisterNames();
	}

	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++) curr[i] = Complex.ZERO;
	}

	public void zoneDump() {
		File f;
		PrintWriter pw;
		PDElement pdElem;
		CktElement elem;

		Circuit ckt = DSS.activeCircuit;

		String csvName = "Zone_" + getName() + ".csv";

		try {
			f = new File(DSS.dataDirectory, csvName);
			pw = new PrintWriter(f);

			DSS.globalResult = csvName;

			pw.print("Level, Branch, Bus1, Bus2, Distance");
			pw.println();

			if (branchList != null) {
				pdElem = (PDElement) branchList.getFirst();
				while (pdElem != null) {
					pw.printf("%d, %s.%s, %s, %s, %10.4f",
							branchList.getLevel(), pdElem.getParentClass().getClassName(), pdElem.getName(),
							pdElem.getFirstBus(), pdElem.getNextBus(),
							/*BusList.get(BranchList.getPresentBranch().getToBusReference()),*/
							ckt.getBus(branchList.getPresentBranch().getToBusReference()).getDistFromMeter());
					pw.println();
					elem = (CktElement) branchList.getFirstObject();
					while (elem != null) {
						pw.printf("-1, %s.%s, %s", elem.getParentClass().getClassName(), elem.getName(),
							elem.getFirstBus()/*ckt.getBusList().get(BranchList.getPresentBranch().getToBusReference())*/);
						pw.println();
						elem = (CktElement) branchList.getNextObject();
					}
					pdElem = (PDElement) branchList.goForward();
				}
			}

			pw.close();
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening file \"" + csvName + "\": " + e.getMessage(), 528);
		}
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		int i;
		PDElement pdElem;
		CktElement elem;

		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (i = 0; i < getParentClass().getNumProperties(); i++) {
			switch (i) {
			case 3:  // option
				pw.print("~ " + getParentClass().getPropertyName(i) + "=(");
				if (excessFlag) {
					pw.print("E,");
				} else {
					pw.print("T,");
				}
				if (zoneIsRadial) {
					pw.print(" R,");
				} else {
					pw.print(" M,");
				}
				if (voltageUEOnly) {
					pw.print(" V");
				} else {
					pw.print(" C");
				}
				pw.println(")");
				break;
			case 6:
				pw.println("~ " + getParentClass().getPropertyName(i) + "=(" + getPropertyValue(i) + ")");
				break;
			default:
				pw.println("~ " + getParentClass().getPropertyName(i) + "=" + getPropertyValue(i));
				break;
			}
		}

		if (complete) {
			pw.println("Registers");
			for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
				pw.println("\"" + registerNames[i] + "\" = " + registers[i]);
			pw.println();

			pw.println("Branch List:");
			if (branchList != null) {
				pdElem = (PDElement) branchList.getFirst();
				while (pdElem != null) {
					pw.println("Circuit element = " + pdElem.getName());
					elem = (CktElement) branchList.getFirstObject();
					while (elem != null) {
						pw.println("   Shunt element = " + elem.getParentClass().getClassName() + "." + elem.getName());
						elem = (CktElement) branchList.getNextObject();
					}
					pdElem = (PDElement) branchList.goForward();
				}
			}
		}
		pw.close();
	}

	/**
	 * Add to VoltBase list if not already there and return index.
	 */
	private int addToVoltBaseList(int busRef) {
		Bus bus = DSS.activeCircuit.getBus(busRef - 1);

		for (int i = 0; i < VBaseCount; i++) {
			if (Math.abs(1.0 - bus.getKVBase() / VBaseList[i]) < 0.01) {  // < 1% difference
				return i;
			}
		}

		if (bus.getKVBase() > 0.0 && VBaseCount < maxVBaseCount) {
			VBaseCount += 1;
			VBaseList[VBaseCount] = bus.getKVBase();
			return VBaseCount;
		} else {
			return 0;
		}
	}

	public void allocateLoad() {
		int connectedPhase;
		PDElement elem;
		LoadObj loadElem;

		/* Now go through the meter's zone and adjust the loads.
		 *
		 * While the AllocationFactor property is adjusted for all loads, it will only
		 * have an effect on loads defined with either the XFKVA property or the
		 * kWh property.
		 *
		 * Loads have a sensorObj property that points to its upstream sensor that has the adjustments for
		 * the allocation factors.  This is established in the makeMeterZoneLists proc in this unit.
		 *
		 * Sensors consist of EnergyMeters, which drive the load allocation process and Sensor objects that
		 * are simply voltage and current measuring points.  A Sensor may be attached to a line or transformer
		 * or it may be connected directly to a load.
		 */

		elem = (PDElement) branchList.getFirst();
		while (elem != null) {
			loadElem = (LoadObj) branchList.getFirstObject();
			while (loadElem != null) {
				if ((loadElem.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LOAD_ELEMENT) {  // only for loads not other shunts
					switch (loadElem.getNumPhases()) {
					/* For single phase loads, allocate based on phase factor, else average factor */
					case 1:
						connectedPhase = DSS.activeCircuit.getMapNodeToBus(nodeRef[0]).nodeNum;  // one-based
						if (connectedPhase > 0 && connectedPhase <= 3)  // restrict to phases 1..3
							loadElem.setAllocationFactor(loadElem.getAllocationFactor() * loadElem.getSensorObj().getPhsAllocationFactor()[connectedPhase - 1]);
						break;
					default:
						loadElem.setAllocationFactor(loadElem.getAllocationFactor() * avgAllocFactor);
						break;
					}
				}
				loadElem = (LoadObj) branchList.getNextObject();  /* Next load at this bus */
			}
			elem = (PDElement) branchList.goForward();  /* Go on down the tree */
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		StringBuilder sb;

		setPropertyValue(0, "");          // "element";
		setPropertyValue(1, "1");         // "terminal";
		setPropertyValue(2, "clear");     // "action";
		setPropertyValue(3, "(E, R, C)"); // "option";
		setPropertyValue(4, "0.0");       // "kWnormal";
		setPropertyValue(5, "0.0");       // "kwEmerg";
		setPropertyValue(6, "(400, 400, 400)"); // "peakCurrent";
		setPropertyValue(7, "");  // zoneList
		setPropertyValue(8, "No");
		/* Define mask as 1 for all registers */
		sb = new StringBuilder("[");
		for (int i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			sb.append("1 ");
		sb.append(']');
		setPropertyValue(9, sb.toString());
		setPropertyValue(10, "Yes");
		setPropertyValue(11, "Yes");
		setPropertyValue(12, "Yes");
		setPropertyValue(13, "Yes");
		setPropertyValue(14, "Yes");  // segregate losses by voltage base
		setPropertyValue(15, "Yes");
		setPropertyValue(16, "No");

		super.initPropertyValues(EnergyMeter.NumPropsThisClass);
	}

	private void accumulateGen(GeneratorObj gen, double[] totalZonekW, double[] totalZoneKVAr) {
		//gen.setActiveTerminalIdx(0);
		Complex S = gen.getPower(0).multiply(0.001).negate();

		totalZonekW[0] += S.getReal();
		totalZoneKVAr[0] += S.getImaginary();
	}

	private double accumulateLoad(LoadObj load, double[] totalZoneKW, double[] totalZoneKVAr,
			double[] totalLoad_EEN, double[] totalLoad_UE) {
		Complex SLoad;
		double kWLoad, result;
		double loadEEN, loadUE;

		//pLoad.setActiveTerminalIdx(1);
		SLoad = load.getPower(0).multiply(0.001);  // get power in terminal 1
		kWLoad = SLoad.getReal();
		result = kWLoad;

		/* Accumulate load in zone */
		totalZoneKW[0] += kWLoad;
		totalZoneKVAr[0] += SLoad.getImaginary();

		/* Always integrate even if the value is 0.0
		 * otherwise the integrate function is not correct. */

		/* Invoking the exceedsNormal and unserved properties causes
		 * the factors to be computed */
		if (excessFlag) {  // return excess load as EEN/UE
			loadEEN = load.getExceedsNormal() ? kWLoad * load.getEEN_Factor() : 0.0;
			loadUE = load.getUnserved() ? kWLoad * load.getUE_Factor() : 0.0;
		} else {  // return total load as EEN/UE
			loadEEN = load.getExceedsNormal() ? kWLoad : 0.0;
			loadUE = load.getUnserved() ? kWLoad : 0.0;
		}

		totalLoad_EEN[0] += loadEEN;
		totalLoad_UE[0] += loadUE;

		return result;
	}

	/**
	 * Reduce zone by eliminating buses and merging lines.
	 */
	public void reduceZone() {
		// make sure zone list is built
		if (branchList == null) makeMeterZoneLists();

		switch (DSS.activeCircuit.getReductionStrategy()) {
		case STUBS:          ReduceAlgs.doReduceStubs(branchList); break;
		case TAP_ENDS:       ReduceAlgs.doReduceTapEnds (branchList); break;
		case MERGE_PARALLEL: ReduceAlgs.doMergeParallelLines(branchList); break;
		case DANGLING:       ReduceAlgs.doReduceDangling(branchList); break;
		case BREAK_LOOP:     ReduceAlgs.doBreakLoops(branchList); break;
		case SWITCHES:       ReduceAlgs.doReduceSwitches(branchList); break;
		default:             ReduceAlgs.doReduceDefault(branchList); break;
		}

		// resynchronize with feeders
		if (hasFeeder) feederObj.initializeFeeder(branchList);
	}

	/**
	 * Start at the ends of the zone and work toward the start
	 * interpolating between known coordinates.
	 */
	public void interpolateCoordinates() {
		int i, busRef, firstCoordRef, secondCoordRef, lineCount;
		CktTreeNode startNode, presentNode = null;
		CktElement elem;

		Circuit ckt = DSS.activeCircuit;

		if (branchList == null) {
			DSS.doSimpleMsg("Meter zone lists need to be built. Solve or makeBusList first.", 529);
			return;
		}

		for (i = 0; i < branchList.getZoneEndsList().getNumEnds(); i++) {
			busRef = branchList.getZoneEndsList().get(i, presentNode);

			firstCoordRef = secondCoordRef = busRef;

			/* Find a bus with a coordinate */
			if (!ckt.getBus(busRef).isCoordDefined()) {
				while (!ckt.getBus( presentNode.getFromBusReference() ).isCoordDefined()) {
					presentNode = presentNode.getParent();
					if (presentNode == null) break;
				}
				if (presentNode != null)
					firstCoordRef = presentNode.getFromBusReference();
			}

			while (presentNode != null) {
				/* Back up until we find another coord defined */
				lineCount = 0;  /* number of line segments in this segment */
				startNode = presentNode;
				elem = (CktElement) presentNode.getCktObject();
				if (firstCoordRef != presentNode.getFromBusReference()) {
					/* Handle special case for end branch */
					if (ckt.getBus( presentNode.getFromBusReference() ).isCoordDefined()) {
						firstCoordRef = presentNode.getFromBusReference();
					} else {
						lineCount += 1;
					}
				}

				while (!ckt.getBus(secondCoordRef - 1).isCoordDefined() && !elem.isChecked()) {
					elem.setChecked(true);
					presentNode = presentNode.getParent();
					if (presentNode == null) break;
					elem = (CktElement) presentNode.getCktObject();
					secondCoordRef = presentNode.getFromBusReference();
					lineCount += 1;
				}

				if (presentNode != null && lineCount > 1) {
					if (ckt.getBus(secondCoordRef - 1).isCoordDefined()) {
						calcBusCoordinates(startNode, firstCoordRef, secondCoordRef, lineCount);
					} else {
						break;  /* While - went as far as we could go this way */
					}
				}

				firstCoordRef = secondCoordRef;
			}
		}
	}

	private void calcBusCoordinates(CktTreeNode startBranch, int firstCoordRef, int secondCoordRef, int lineCount) {
		double X, Y, Xinc, Yinc;

		if (lineCount == 1) return;

		Circuit ckt = DSS.activeCircuit;

		Xinc = (ckt.getBus(firstCoordRef - 1).getX() - ckt.getBus(secondCoordRef - 1).getX()) / lineCount;
		Yinc = (ckt.getBus(firstCoordRef - 1).getY() - ckt.getBus(secondCoordRef - 1).getY()) / lineCount;

		X = ckt.getBus(firstCoordRef - 1).getX();
		Y = ckt.getBus(firstCoordRef - 1).getY();

		/*if (((X < 10.0) && (y < 10.0)) || ((ckt.getBus(secondCoordRef).getX() < 10.0) && (ckt.getBus(secondCoordRef).getY() < 10.0)))
			X = Y;*/  // stopping point

		/* Either start with the "to" end of startNode or the "from" end; */
		if (firstCoordRef != startBranch.getFromBusReference()) {
			// start with "to" end
			X = X - Xinc;
			Y = Y - Yinc;
			ckt.getBus(startBranch.getFromBusReference()).setX(X);
			ckt.getBus(startBranch.getFromBusReference()).setY(Y);
			ckt.getBus(startBranch.getFromBusReference()).setCoordDefined(true);
			lineCount -= 1;
		}

		while (lineCount > 1) {
			X = X - Xinc;
			Y = Y - Yinc;
			startBranch = startBranch.getParent();  // back up the tree
			ckt.getBus(startBranch.getFromBusReference()).setX(X);
			ckt.getBus(startBranch.getFromBusReference()).setY(Y);
			ckt.getBus(startBranch.getFromBusReference()).setCoordDefined(true);
			lineCount -= 1;
		}
	}

	@Override
	public String getPropertyValue(int index) {
		String val;

		switch (index) {
		case 3:
		case 6:
			val = "(";
			break;
		default:
			val = "";
			break;
		}

		switch (index) {
		case 3:  // option
			val += excessFlag ? "E," : "T,";
			val += zoneIsRadial ? " R," : " M,";
			val += voltageUEOnly ? " V" : " C";
			break;
		default:
			val += super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 3:
		case 6:
			val += ")";
			break;
		}

		return val;
	}

	public void saveZone(String dirname) {
		CktElement elem, shuntElem;
		LoadObj loadElem;
		File fbranches, fshunts, floads, fgens, fcaps;
		PrintWriter pwbranches, pwshunts, pwloads, pwgens, pwcaps;
		int nbranches, nshunts, nloads, ngens, ncaps;

		Circuit ckt = DSS.activeCircuit;

		/* We are in the directory indicated by dirname */

		/* Run down the zone and write each element into a file */

		if (branchList != null) {
			/* Open some files: */

			try {
				fbranches = new File("Branches.dss");
				pwbranches = new PrintWriter(fbranches);  // both lines and transformers
				nbranches = 0;
			} catch (Exception e) {
				DSS.doSimpleMsg("Error creating Branches.dss for EnergyMeter: " + getName() + ". " + e.getMessage(), 530);
				return;
			}

			try {
				fshunts = new File("Shunts.dss");
				pwshunts = new PrintWriter(fshunts);
				nshunts = 0;
			} catch (Exception e) {
				DSS.doSimpleMsg("Error creating Shunts.dss for EnergyMeter: " + getName() + ". " + e.getMessage(), 531);
				return;
			}

			try {
				floads = new File("Loads.dss");
				pwloads = new PrintWriter(floads);
				nloads = 0;
			} catch (Exception e) {
				DSS.doSimpleMsg("Error creating Loads.dss for EnergyMeter: " + getName() + ". " + e.getMessage(), 532);
				return;
			}

			try {
				fgens = new File("Generators.dss");
				pwgens = new PrintWriter(fgens);
				ngens = 0;
			} catch (Exception e) {
				DSS.doSimpleMsg("Error creating Generators.dss for EnergyMeter: " + getName() + ". " + e.getMessage(), 533);
				return;
			}

			try {
				fcaps = new File("Capacitors.dss");
				pwcaps = new PrintWriter(fcaps);
				ncaps = 0;
			} catch (Exception e) {
				DSS.doSimpleMsg("Error creating Capacitors.dss for EnergyMeter: " + getName() + ". " + e.getMessage(), 534);
				return;
			}

			elem = (CktElement) branchList.getFirst();
			while (elem != null) {
				if (elem.isEnabled()) {
					ckt.setActiveCktElement(elem);
					nbranches += 1;
					Util.writeActiveDSSObject(pwbranches, "new");  // sets hasBeenSaved(true)
					if (ckt.getActiveCktElement().hasControl()) {
						ckt.setActiveCktElement(ckt.getActiveCktElement().getControlElement());
						Util.writeActiveDSSObject(pwbranches, "new");  // regulator control ... also, relays, switch controls
					}

					shuntElem = (CktElement) branchList.getFirstObject();
					while (shuntElem != null) {
						ckt.setActiveCktElement(shuntElem);
						if ((shuntElem.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LOAD_ELEMENT) {
							loadElem = (LoadObj) shuntElem;
							if (loadElem.getHasBeenAllocated()) {
								/* Manually set the allocation factor so it shows up */
								Parser.getInstance().setCommand("allocationfactor=" + String.format("%-.4g", loadElem.getAllocationFactor()));
								loadElem.edit();
							}
							ckt.setActiveCktElement(shuntElem);  // reset in case edit mangles it
							nloads += 1;
							Util.writeActiveDSSObject(pwloads, "new");
						} else if ((shuntElem.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.GEN_ELEMENT) {
							ngens += 1;
							Util.writeActiveDSSObject(pwgens, "new");
							if (ckt.getActiveCktElement().hasControl()) {
								ckt.setActiveCktElement(ckt.getActiveCktElement().getControlElement());
								Util.writeActiveDSSObject(pwgens, "new");
							}
						} else if ((shuntElem.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.CAP_ELEMENT) {
							ncaps += 1;
							Util.writeActiveDSSObject(pwcaps, "new");
							if (ckt.getActiveCktElement().hasControl()) {
								ckt.setActiveCktElement(ckt.getActiveCktElement().getControlElement());
								Util.writeActiveDSSObject(pwcaps, "new");
							}
						} else {
							nshunts += 1;
							Util.writeActiveDSSObject(pwshunts, "new");
						}
						shuntElem = (CktElement) branchList.getNextObject();
					}
				}  /* if enabled */
				elem = (CktElement) branchList.goForward();
			}

			pwbranches.close();
			pwshunts.close();
			pwloads.close();
			pwgens.close();
			pwcaps.close();

			/* If any records were written to the file, record their relative names */
			if (nbranches > 0) {
				DSS.savedFileList.add(dirname + DSS.SEPARATOR + "Branches.dss");
			} else {
				fbranches.delete();
			}
			if (nshunts > 0) {
				DSS.savedFileList.add(dirname + DSS.SEPARATOR + "Shunts.dss");
			} else {
				fshunts.delete();
			}
			if (nloads > 0) {
				DSS.savedFileList.add(dirname + DSS.SEPARATOR + "Loads.dss");
			} else {
				floads.delete();
			}
			if (ngens > 0) {
				DSS.savedFileList.add(dirname + DSS.SEPARATOR + "Generators.dss");
			} else {
				fgens.delete();
			}
			if (ncaps > 0) {
				DSS.savedFileList.add(dirname + DSS.SEPARATOR + "Capacitors.dss");
			} else {
				fcaps.delete();
			}
		}
	}

	private void setDragHandRegister(int reg, double value) {
		if (value > registers[reg]) {
			registers[reg] = value;
			derivatives[reg] = value;  // use this for demand interval data
		}
	}

	protected void closeDemandIntervalFile() {
		try {
			if (thisMeterDI_FileIsOpen) {
				DI_File.close();
				thisMeterDI_FileIsOpen = false;
				if (VPhaseReportFileIsOpen) VPhaseFile.close();
				VPhaseReportFileIsOpen = false;
			}
		} catch (IOException e) {
			DSS.doSimpleMsg("Error closing demand interval file for meter \"" + getName() + "\"", 534);
		}

		/* Write registers to totals file */
		PrintWriter pw = new PrintWriter(DSS.energyMeterClass.getMeterTotals());
		pw.print("\"" + getName() + "\"");
		for (int i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			pw.printf(", %g", registers[i]);
		pw.println();
		pw.close();
	}

	protected void openDemandIntervalFile() {
		int i, j;
		double VBase;
		PrintWriter pw;

		try {
			if (thisMeterDI_FileIsOpen) closeDemandIntervalFile();

			if (DSS.energyMeterClass.isDI_Verbose()) {
				DI_File = new FileWriter(makeDIFileName());
				pw = new PrintWriter(DI_File);

				thisMeterDI_FileIsOpen = true;
				pw.print("\"Hour\"");
				for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
					pw.print(", \"" + registerNames[i] + "\"");
				pw.println();
				pw.close();

				/* Phase voltage report, if requested */
				if (phaseVoltageReport) {
					VPhaseFile = new FileWriter(makeVPhaseReportFileName());
					pw = new PrintWriter(VPhaseFile);

					VPhaseReportFileIsOpen = true;
					pw.write("\"Hour\"");
					for (i = 0; i < maxVBaseCount; i++) {
						VBase = VBaseList[i] * DSS.SQRT3;
						if (VBase > 0.0) {
							for (j = 0; j < 3; j++) {
								pw.printf(", %.3gkV_Phs_%d_Max", VBase, j);
								pw.printf(", %.3gkV_Phs_%d_Min", VBase, j);
								pw.printf(", %.3gkV_Phs_%d_Avg", VBase, j);
							}
						}
					}
					pw.println();
					pw.close();
				}

			}
		} catch (IOException e) {
			DSS.doSimpleMsg("Error opening demand interval file \"" + getName() + ".csv" +
					" for writing." + DSS.CRLF + e.getMessage(), 535);
		}
	}

	protected void writeDemandIntervalData() {
		int i, j;
		PrintWriter pw;

		SolutionObj sol = DSS.activeCircuit.getSolution();

		if ((DSS.energyMeterClass.isDI_Verbose()) && thisMeterDI_FileIsOpen) {
			pw = new PrintWriter(DI_File);
			pw.printf("%-.6g", sol.getDblHour());
			for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
				pw.printf(", %-.6g", derivatives[i]);
			pw.println();
			pw.close();
		}

		/* Add to class demand interval registers */
		for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			DSS.energyMeterClass.getDI_RegisterTotals()[i] += derivatives[i] * totalsMask[i];

		/* Phase voltage report, if requested */
		if (VPhaseReportFileIsOpen) {
			pw = new PrintWriter(VPhaseFile);
			pw.printf("%-.6g", sol.getDblHour());
			for (i = 0; i < maxVBaseCount; i++) {
				if (VBaseList[i] > 0.0) {
					for (j = 0; j < 3; j++) {
						pw.printf(", %-.6g", 0.001 * VPhaseMax[jiIndex(j, i)]);
						pw.printf(", %-.6g", 0.001 * VPhaseMin[jiIndex(j, i)]);
						pw.printf(", %-.6g", 0.001 * myCountAvg(VPhaseAccum[jiIndex(j, i)], VPhaseAccumCount[jiIndex(j, i)]));
					}
				}
			}
			pw.println();
		}
	}

	private double myCountAvg(double value, int count) {
		return (count == 0) ? 0.0 : value / count;
	}

	/**
	 * Only called if "saveDemandInterval".
	 */
	protected void appendDemandIntervalFile() {
		String fileName;

		if (thisMeterDI_FileIsOpen) return;

		try {
			if (DSS.energyMeterClass.isDI_Verbose()) {
				fileName = makeDIFileName();  // creates directory if it doesn't exist
				/* File must exist */
				if (new File(fileName).exists()) {
					DI_File = new FileWriter(fileName, true);
				} else {
					DI_File = new FileWriter(fileName, false);
				}

				thisMeterDI_FileIsOpen = true;
			}
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening demand interval file \"" + getName() + ".csv" +
					" for appending." + DSS.CRLF + e.getMessage(), 537);
		}
	}

	private void assignVoltBaseRegisterNames() {
		int i, ireg;
		double Vbase;

		ireg = 0;
		for (i = 0; i < maxVBaseCount; i++) {
			if (VBaseList[i] > 0.0) {
				Vbase = VBaseList[i] * DSS.SQRT3;
				registerNames[i + EnergyMeter.REG_VBASE_START] = String.format("%.3g kV Losses", Vbase);
				registerNames[i + 1 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("%.3g kV Line Loss", Vbase);
				registerNames[i + 2 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("%.3g kV Load Loss", Vbase);
				registerNames[i + 3 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("%.3g kV No Load Loss", Vbase);
				registerNames[i + 4 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("%.3g kV Load Energy", Vbase);
			} else {
				registerNames[i + EnergyMeter.REG_VBASE_START] = String.format("Aux%d", ireg);
				ireg += 1;
				registerNames[i + 1 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("Aux%d", ireg);
				ireg += 1;
				registerNames[i + 2 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("Aux%d", ireg);
				ireg += 1;
				registerNames[i + 3 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("Aux%d", ireg);
				ireg += 1;
				registerNames[i + 4 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("Aux%d", ireg);
				ireg += 1;
			}
		}
		for (i = EnergyMeter.REG_VBASE_START + 5; i < EnergyMeter.NUM_EM_REGISTERS; i++) {
			registerNames[i] = String.format("Aux%d", ireg);
			ireg += 1;
		}
	}

	private void makeFeederObj() {

		if (meteredElement != null) {
			//DSS.getFeederClass().newObject(getName());  // newObject creates only if not existent else inits and desynchs
			//setFeederObj( (FeederObj) DSSGlobals.activeCircuit.getActiveCktElement() );
			//getFeederObj().setBus(0, MeteredElement.getBus(meteredTerminal));
			//getFeederObj().setNPhases(MeteredElement.getNPhases());
			//getFeederObj().setNConds(MeteredElement.getNConds());
			//getFeederObj().setEnabled(DSSGlobals.activeCircuit.isRadialSolution());
		} else {
			DSS.doSimpleMsg("Error: Attempted to make feeder object without instantiating meteredElement in EnergyMeter." + getName(), 544);
		}
	}

	protected void removeFeederObj() {
		if (feederObj != null) {
			feederObj.setEnabled(false);
			feederObj.setCktElementFeederFlags(false);
		}
	}

	/**
	 * HasFeeder has to be true before FeederObj will be re-enabled.
	 */
	public void enableFeeder() {
		if (hasFeeder) {
			if (feederObj == null) {
				makeFeederObj();
			} else {
				feederObj.setEnabled(true);
			}
			feederObj.setCktElementFeederFlags(true);
		}
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	private String makeDIFileName() {
		return DSS.energyMeterClass.getDI_Dir() + DSS.SEPARATOR + getName() + ".csv";
	}

	public double getRegister(int idx) {
		return registers[idx];
	}

	public String getRegisterName(int idx) {
		return registerNames[idx];
	}

	public double getTotalsMask(int idx) {
		return totalsMask[idx];
	}

	public double[] getTotalsMask() {
		return totalsMask;
	}

	public boolean hasFeeder() {
		return hasFeeder;
	}

	public boolean isExcessFlag() {
		return excessFlag;
	}

	public boolean isLocalOnly() {
		return localOnly;
	}

	public boolean isLosses() {
		return losses;
	}

	public boolean isLineLosses() {
		return lineLosses;
	}

	public boolean isPhaseVoltageReport() {
		return phaseVoltageReport;
	}

	public String[] getDefinedZoneList() {
		return definedZoneList;
	}

	public int getDefinedZoneListSize() {
		return definedZoneListSize;
	}

	public double getMaxZoneKVANorm() {
		return maxZoneKVANorm;
	}

	public double getMaxZoneKVAEmerg() {
		return maxZoneKVAEmerg;
	}

	public String[] getRegisterNames() {
		return registerNames;
	}

	public CktTree getBranchList() {
		return branchList;
	}

	public boolean isVoltageUEOnly() {
		return voltageUEOnly;
	}

	public void setVoltageUEOnly(boolean voltageUEOnly) {
		this.voltageUEOnly = voltageUEOnly;
	}

	public boolean isXfmrLosses() {
		return xfmrLosses;
	}

	public void setXfmrLosses(boolean xfmrLosses) {
		this.xfmrLosses = xfmrLosses;
	}

	public boolean isSeqLosses() {
		return seqLosses;
	}

	public void setSeqLosses(boolean seqLosses) {
		this.seqLosses = seqLosses;
	}

	public boolean isThreePhaseLosses() {
		return threePhaseLosses;
	}

	public void setThreePhaseLosses(boolean threePhaseLosses) {
		this.threePhaseLosses = threePhaseLosses;
	}

	public boolean isVBaseLosses() {
		return VBaseLosses;
	}

	public void setVBaseLosses(boolean vBaseLosses) {
		VBaseLosses = vBaseLosses;
	}

	public void setExcessFlag(boolean excessFlag) {
		this.excessFlag = excessFlag;
	}

	public void setZoneIsRadial(boolean zoneIsRadial) {
		this.zoneIsRadial = zoneIsRadial;
	}

	public void setLocalOnly(boolean localOnly) {
		this.localOnly = localOnly;
	}

	public void setLosses(boolean losses) {
		this.losses = losses;
	}

	public void setLineLosses(boolean lineLosses) {
		this.lineLosses = lineLosses;
	}

	public void setPhaseVoltageReport(boolean phaseVoltageReport) {
		this.phaseVoltageReport = phaseVoltageReport;
	}

	public void setDefinedZoneList(String[] definedZoneList) {
		this.definedZoneList = definedZoneList;
	}

	public void setDefinedZoneListSize(int definedZoneListSize) {
		this.definedZoneListSize = definedZoneListSize;
	}

	public void setMaxZoneKVANorm(double maxZoneKVANorm) {
		this.maxZoneKVANorm = maxZoneKVANorm;
	}

	public void setMaxZoneKVAEmerg(double maxZoneKVAEmerg) {
		this.maxZoneKVAEmerg = maxZoneKVAEmerg;
	}

}
