/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.meter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.conversion.Generator;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CktTree;
import com.ncond.dss.shared.CommandList;

@Getter @Setter
public class EnergyMeter extends MeterClass {

	public static final int NumPropsThisClass = 17;

	public static final int NUM_EM_VBASE = 7;
	public static final int NUM_EM_REGISTERS = 32 + 5 * NUM_EM_VBASE;   // total number of energy meter registers

	public static final int REG_KWH                    = 0;
	public static final int REG_KVARH                  = 1;
	public static final int REG_MAX_KW                 = 2;
	public static final int REG_MAX_KVA                = 3;
	public static final int REG_ZONE_KWH               = 4;
	public static final int REG_ZONE_KVARH             = 5;
	public static final int REG_ZONE_MAX_KW            = 6;
	public static final int REG_ZONE_MAX_KVA           = 7;
	public static final int REG_OVERLOAD_KWH_NORM      = 8;  // max overload
	public static final int REG_OVERLOAD_KWH_EMERG     = 9;
	public static final int REG_LOAD_EEN               = 10;
	public static final int REG_LOAD_UE                = 11;  // energy served below normal voltage
	public static final int REG_ZONE_LOSSES_KWH        = 12;
	public static final int REG_ZONE_LOSSES_KVARH      = 13;
	public static final int REG_LOSSES_MAX_KW          = 14;
	public static final int REG_LOSSES_MAX_KVAR        = 15;
	public static final int REG_LOAD_LOSSES_KWH        = 16;
	public static final int REG_LOAD_LOSSES_KVARH      = 17;
	public static final int REG_NO_LOAD_LOSSES_KWH     = 18;
	public static final int REG_NO_LOAD_LOSSES_KVARH   = 19;
	public static final int REG_MAX_LOAD_LOSSES        = 20;
	public static final int REG_MAX_NO_LOAD_LOSSES     = 21;
	public static final int REG_LINE_LOSSES_KWH        = 22;
	public static final int REG_TRANSFORMER_LOSSES_KWH = 23;
	public static final int REG_LINE_MODE_LINE_LOSS    = 24;  // for 3-phase feeder lines
	public static final int REG_ZERO_MODE_LINE_LOSS    = 25;
	public static final int REG_3_PHASE_LINE_LOSS      = 26;
	public static final int REG_1_PHASE_LINE_LOSS      = 27;
	public static final int REG_GEN_KWH                = 28;
	public static final int REG_GEN_KVARH              = 29;
	public static final int REG_GEN_MAX_KW             = 30;
	public static final int REG_GEN_MAX_KVA            = 31;
	public static final int REG_VBASE_START            = 32;  // anchor for the voltage base loss registers

	// adjacency lists for PC and PD elements at each bus, built for faster searches
	public static List<PCElement>[] busAdjPC;  // also includes shunt PD elements
	public static List<PDElement>[] busAdjPD;

	public static EnergyMeterObj activeEnergyMeterObj;

	private Generator generatorClass;
	private boolean saveDemandInterval;
	private boolean DI_Verbose;
	private FileWriter overloadFile;
	private FileWriter voltageFile;

	protected double[] DI_RegisterTotals;
	protected String DI_Dir;
	protected FileWriter DI_Totals;
	protected FileWriter meterTotals;
	protected SystemMeter systemMeter;
	protected boolean doOverloadReport;
	protected boolean doVoltageExceptionReport;
	protected boolean overloadFileIsOpen;
	protected boolean voltageFileIsOpen;

	public EnergyMeter() {
		super();

		className = "EnergyMeter";
		classType = classType + DSSClassDefs.ENERGY_METER;

		activeElement = -1;

		/* Initialise demand interval options to off */
		saveDemandInterval = false;
		DI_Verbose = false;
		doOverloadReport = false;  // saveDemandInterval must be true for this to have an effect
		overloadFileIsOpen = false;
		voltageFileIsOpen = false;

		DI_Dir = "";

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);

		generatorClass = (Generator) DSS.DSSClassList.get(DSS.classNames.find("generator"));

		systemMeter = new SystemMeter();
	}

	@Override
	protected void defineProperties() {
		numProperties = EnergyMeter.NumPropsThisClass;
		countProperties();   // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0] = "element";
		propertyName[1] = "terminal";
		propertyName[2] = "action";
		propertyName[3] = "option";
		propertyName[4] = "kVAnormal";
		propertyName[5] = "kVAemerg";
		propertyName[6] = "peakcurrent";
		propertyName[7] = "Zonelist";
		propertyName[8] = "LocalOnly";
		propertyName[9] = "Mask";
		propertyName[10] = "Losses";
		propertyName[11] = "LineLosses";
		propertyName[12] = "XfmrLosses";
		propertyName[13] = "SeqLosses";
		propertyName[14] = "3phaseLosses";
		propertyName[15] = "VbaseLosses";  // segregate losses by voltage base
		propertyName[16] = "PhaseVoltageReport";  // compute avg phase voltages in zone

		propertyHelp[0] = "Name (Full Object name) of element to which the monitor is connected.";
		propertyHelp[1] = "Number of the terminal of the circuit element to which the monitor is connected. "+
				"1 or 2, typically.";
		propertyHelp[2] = "{Clear (reset) | Save | Take | Zonedump | Allocate | Reduce} " + DSS.CRLF + DSS.CRLF +
				"(A)llocate = Allocate loads on the meter zone to match PeakCurrent." + DSS.CRLF +
				"(C)lear = reset all registers to zero" + DSS.CRLF +
				"(R)educe = reduces zone by merging lines (see Set Keeplist & ReduceOption)" + DSS.CRLF +
				"(S)ave = saves the current register values to a file." + DSS.CRLF +
				"   File name is \"MTR_metername.csv\"." +DSS.CRLF +
				"(T)ake = Takes a sample at present solution" + DSS.CRLF +
				"(Z)onedump = Dump names of elements in meter zone to a file" + DSS.CRLF +
				"   File name is \"Zone_metername.csv\".";
		propertyHelp[3] = "Enter a string ARRAY of any combination of the following. Options processed left-to-right:" + DSS.CRLF + DSS.CRLF +
				"(E)xcess : (default) UE/EEN is estimate of energy over capacity " + DSS.CRLF +
				"(T)otal : UE/EEN is total energy after capacity exceeded"+ DSS.CRLF +
				"(R)adial : (default) Treats zone as a radial circuit"+ DSS.CRLF +
				"(M)esh : Treats zone as meshed network (not radial)." +DSS.CRLF+
				"(C)ombined : (default) Load UE/EEN computed from combination of overload and undervoltage."+ DSS.CRLF +
				"(V)oltage : Load UE/EEN computed based on voltage only."+DSS.CRLF+DSS.CRLF+
				"Example: option=(E, R)";
		propertyHelp[4] = "Upper limit on kVA load in the zone, Normal configuration. Default is 0.0 (ignored). " +
				"Overrides limits on individual lines for overload EEN. " +
				"With \"LocalOnly=Yes\" option, uses only load in metered branch.";
		propertyHelp[5] = "Upper limit on kVA load in the zone, Emergency configuration. Default is 0.0 (ignored). " +
				"Overrides limits on individual lines for overload UE. " +
				"With \"LocalOnly=Yes\" option, uses only load in metered branch.";
		propertyHelp[6] = "ARRAY of current magnitudes representing the peak currents measured at this location " +
				"for the load allocation function.  Default is (400, 400, 400). Enter one current for each phase";
		propertyHelp[7] = "ARRAY of full element names for this meter's zone.  Default is for meter to find it's own zone. " +
				"If specified, DSS uses this list instead.  Can access the names in a single-column text file.  Examples: " + DSS.CRLF + DSS.CRLF+
				"zonelist=[line.L1, transformer.T1, Line.L3] " + DSS.CRLF +
				"zonelist=(file=branchlist.txt)";
		propertyHelp[8] = "{Yes | No}  Default is NO.  If Yes, meter considers only the monitored element " +
				"for EEN and UE calcs.  Uses whole zone for losses.";
		propertyHelp[9] = "Mask for adding registers whenever all meters are totalized.  Array of floating point numbers " +
				"representing the multiplier to be used for summing each register from this meter. " +
				"Default = (1, 1, 1, 1, ... ).  You only have to enter as many as are changed (positional). " +
				"Useful when two meters monitor same energy, etc.";
		propertyHelp[10]= "{Yes | No}  Default is YES. Compute Zone losses. If NO, then no losses at all are computed.";
		propertyHelp[11]= "{Yes | No}  Default is YES. Compute Line losses. If NO, then none of the losses are computed.";
		propertyHelp[12]= "{Yes | No}  Default is YES. Compute Transformer losses. If NO, transformers are ignored in loss calculations.";
		propertyHelp[13]= "{Yes | No}  Default is YES. Compute Sequence losses in lines and segregate by line mode losses and zero mode losses.";
		propertyHelp[14]= "{Yes | No}  Default is YES. Compute Line losses and segregate by 3-phase and other (1- and 2-phase) line losses. ";
		propertyHelp[15]= "{Yes | No}  Default is YES. Compute losses and segregate by voltage base. If NO, then voltage-based tabulation is not reported.";
		propertyHelp[16]= "{Yes | No}  Default is NO.  Report min, max, and average phase voltages for the zone and tabulate by voltage base. " +
				"Demand Intervals must be turned on (Set Demand=true) and voltage bases must be defined for this property to take effect. "+
				"Result is in a separate report file.";

		activeProperty = EnergyMeter.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new EnergyMeterObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeEnergyMeterObj = (EnergyMeterObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeEnergyMeterObj);

		boolean doRecalc = false;

		EnergyMeterObj elem = activeEnergyMeterObj;

		elem.setMeteredElementChanged(false);

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() +"."+ elem.getName() + "\"", 520);
				break;
			case 0:
				elem.setElementName(param.toLowerCase());
				break;
			case 1:
				elem.setMeteredTerminalIdx(parser.makeInteger() - 1);
				break;
			case 2:  /* Actions */
				switch (param.toLowerCase().charAt(0)) {
				case 'a':
					elem.allocateLoad();
					break;
				case 'c':
					elem.resetRegisters();
					break;
				case 'r':
					elem.reduceZone();
					break;
				case 's':
					elem.saveRegisters();
					break;
				case 't':
					elem.takeSample();
					break;
				case 'z':
					elem.zoneDump();
					break;
				}
				break;
			case 3:
				processOptions(param);
				break;
			case 4:
				elem.setMaxZoneKVANorm(parser.makeDouble());
				break;
			case 5:
				elem.setMaxZoneKVAEmerg(parser.makeDouble());
				break;
			case 6:
				parser.parseAsVector(elem.getNumPhases(), elem.getSensorCurrent());  // inits to zero
				break;
			case 7:
				Util.interpretAndAllocStrArray(param, elem.getDefinedZoneListSize(), elem.getDefinedZoneList());
				break;
			case 8:
				elem.setLocalOnly(Util.interpretYesNo(param));
				break;
			case 9:
				interpretRegisterMaskArray(elem.getTotalsMask());
				break;
			case 10:
				elem.setLosses(Util.interpretYesNo(param));
				break;
			case 11:
				elem.setLineLosses(Util.interpretYesNo(param));
				break;
			case 12:
				elem.setXfmrLosses(Util.interpretYesNo(param));
				break;
			case 13:
				elem.setSeqLosses(Util.interpretYesNo(param));
				break;
			case 14:
				elem.setThreePhaseLosses(Util.interpretYesNo(param));
				break;
			case 15:
				elem.setVBaseLosses(Util.interpretYesNo(param));
				break;
			case 16:
				elem.setPhaseVoltageReport(Util.interpretYesNo(param));
				break;
			/* case 10: aem.setHasFeeder(Utilities.interpretYesNo(Param)); break;*/
			default:
				classEdit(activeEnergyMeterObj, paramPointer - EnergyMeter.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 0:
				elem.setMeteredElementChanged(true);
				doRecalc = true;
				break;
			case 1:
				elem.setMeteredElementChanged(true);
				doRecalc = true;
				break;
			case 10:
				if (elem.hasFeeder()) {
					doRecalc = true;
				} else {
					elem.removeFeederObj();
				}
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (doRecalc)
			elem.recalcElementData();  // when some basic data have changed

		return 0;
	}

	@Override
	protected int makeLike(String energyMeterName) {
		EnergyMeterObj elem;
		int success = 0;

		/* See if we can find this EnergyMeter name in the present collection */
		EnergyMeterObj other = (EnergyMeterObj) find(energyMeterName);

		if (other != null) {
			elem = activeEnergyMeterObj;

			elem.setNumPhases(other.getNumPhases());
			elem.setNumConds(other.getNumConds());  // force reallocation of terminal stuff

			elem.setElementName(other.getElementName());
			elem.setMeteredElement(other.getMeteredElement());  // pointer to target circuit element
			elem.setMeteredTerminalIdx(other.getMeteredTerminalIdx());
			elem.setExcessFlag(other.isExcessFlag());

			elem.setMaxZoneKVANorm(other.getMaxZoneKVANorm());
			elem.setMaxZoneKVAEmerg(other.getMaxZoneKVAEmerg());

			elem.setDefinedZoneListSize(other.getDefinedZoneListSize());
			elem.setDefinedZoneList(new String[elem.getDefinedZoneListSize()]);

			// copy strings over (actually incr ref count on string)
			for (int i = 0; i < elem.getDefinedZoneListSize(); i++)
				elem.getDefinedZoneList()[i] = other.getDefinedZoneList()[i];

			elem.setLocalOnly(other.isLocalOnly());
			elem.setVoltageUEOnly(other.isVoltageUEOnly());

			/* Boolean flags */
			elem.setLosses(other.isLosses());
			elem.setLineLosses(other.isLineLosses());
			elem.setXfmrLosses(other.isXfmrLosses());
			elem.setSeqLosses(other.isSeqLosses());
			elem.setThreePhaseLosses(other.isThreePhaseLosses());
			elem.setVBaseLosses(other.isVBaseLosses());
			elem.setPhaseVoltageReport(other.isPhaseVoltageReport());

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in EnergyMeter makeLike: \"" + energyMeterName + "\" not found.", 521);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement EnergyMeter.init", -1);
		return 0;
	}

	public void resetMeterZonesAll() {
		int i;
		Circuit ckt = DSS.activeCircuit;

		if (ckt.getEnergyMeters().size() == 0) return;

		// initialize the checked flag for all circuit elements.
		for (CktElement elem : ckt.getCktElements()) {
			elem.setChecked(false);
			elem.setIsolated(true);
			for (i = 0; i < elem.getNumTerms(); i++)
				elem.getTerminal(i).setChecked(false);
		}

		/* Clear some things that will be set by the meter zone */
		for (PDElement elem : ckt.getPDElements()) {
			elem.setMeterObj(null);
			elem.setSensorObj(null);
			elem.setParentPDElement(null);
		}

		for (PCElement elem : ckt.getPCElements()) {
			elem.setMeterObj(null);
			elem.setSensorObj(null);
		}

		// set up the bus adjacency lists for faster searches to build meter zone lists
		CktTree.buildActiveBusAdjacencyLists(busAdjPD, busAdjPC);

		/* Set hasMeter flag for all cktElements */
		setHasMeterFlag();
		DSS.sensorClass.setHasSensorFlag();  // set all sensor branch flags, too.

		// initialise the checked flag for all buses
		for (i = 0; i < ckt.getNumBuses(); i++)
			ckt.getBus(i).setBusChecked(false);

		for (EnergyMeterObj meter : ckt.getEnergyMeters())
			if (meter.isEnabled())
				meter.makeMeterZoneLists();

		CktTree.freeBusAdjacencyLists(busAdjPD, busAdjPC);
		busAdjPD = null;
		busAdjPC = null;
	}

	/**
	 * Reset all meters in active circuit to zero.
	 */
	@Override
	public void resetAll() {
		Circuit ckt = DSS.activeCircuit;

		if (DSS.DIFilesAreOpen) closeAllDIFiles();

		if (saveDemandInterval) {
			/* Make directories to save data */
			File dir = new File(ckt.getCaseName());
			if (!dir.exists()) {
				try {
					dir.mkdir();
				} catch (Exception e) {
					DSS.doSimpleMsg("Error making directory \"" + ckt.getCaseName() +
							"\": " + e.getMessage(), 522);
				}
			}
			DI_Dir = ckt.getCaseName() + "/DI_yr_" + String.valueOf(ckt.getSolution().getYear()).trim();
			dir = new File(DI_Dir);
			if (!dir.exists()) {
				try {
					dir.mkdir();
				} catch (Exception e) {
					DSS.doSimpleMsg("Error making demand interval directory: \"" +
							DI_Dir + "\". " + e.getMessage(), 523);
				}
			}

			createDI_Totals();
			try {
				DI_Totals.close();
			} catch (IOException e) {
				DSS.doSimpleMsg("Error closing DI totals file: " + e.getMessage(), -1);
			}
		}

		for (EnergyMeterObj meter : ckt.getEnergyMeters())
			meter.resetRegisters();

		systemMeter.reset();

		// reset generator objects, too
		generatorClass.resetRegistersAll();
		DSS.storageClass.resetRegistersAll();
	}

	/**
	 * Force all meters in active circuit to sample.
	 */
	@Override
	public void sampleAll() {
		Circuit ckt = DSS.activeCircuit;

		for (EnergyMeterObj meter : ckt.getEnergyMeters())
			if (meter.isEnabled())
				meter.takeSample();

		systemMeter.takeSample();

		if (saveDemandInterval) {  /* Write totals demand interval file */
			PrintWriter pw = new PrintWriter(DI_Totals);

			pw.printf("%-.6g ", ckt.getSolution().getDblHour());
			for (int i = 0; i < NUM_EM_REGISTERS; i++)
				pw.printf(", %-.6g", DI_RegisterTotals[i]);
			pw.println();
			pw.close();

			clearDI_Totals();
			if (overloadFileIsOpen) writeOverloadReport();
			if (voltageFileIsOpen) writeVoltageReport();
		}

		// sample generator and storage objects, too
		generatorClass.sampleAll();
		DSS.storageClass.sampleAll();  // samples energymeter part of storage elements (not update)
	}

	/**
	 * Force all EnergyMeters in the circuit to take a sample.
	 */
	@Override
	public void saveAll() {
		Circuit ckt = DSS.activeCircuit;

		for (EnergyMeterObj meter : ckt.getEnergyMeters())
			if (meter.isEnabled())
				meter.saveRegisters();

		systemMeter.save();
	}

	/**
	 * Set the hasMeter flag for all cktElement;
	 */
	protected void setHasMeterFlag() {
		Circuit ckt = DSS.activeCircuit;

		/* Initialize all to false */
		for (PDElement elem : ckt.getPDElements()) {
			elem.setHasEnergyMeter(false);
		}

		for (EnergyMeterObj meter : ckt.getEnergyMeters()) {
			if (meter.getMeteredElement() != null)
				meter.getMeteredElement().setHasEnergyMeter(true);
		}
	}

	private void processOptions(String opts) {
		String s2 = " ";

		DSS.auxParser.setCmdBuffer(opts);  // load up aux parser

		EnergyMeterObj elem = activeEnergyMeterObj;

		/* Loop until no more options found */
		while (s2.length() > 0) {
			DSS.auxParser.getNextParam();  // ignore any parameter name not expecting any
			s2 = DSS.auxParser.makeString().toLowerCase();
			if (s2.length() > 0) {
				switch (s2.charAt(0)) {
				case 'e':
					elem.setExcessFlag(true);
					break;
				case 't':
					elem.setExcessFlag(false);
					break;
				case 'r':
					elem.setZoneIsRadial(true);
					break;
				case 'm':
					elem.setZoneIsRadial(false);
					break;
				case 'c':
					elem.setVoltageUEOnly(false);
					break;
				case 'v':
					elem.setVoltageUEOnly(true);
					break;
				}
			}
		}
	}

	public void closeAllDIFiles() {
		if (saveDemandInterval) {
			/* While closing DI files, write all meter registers to one file */
			try {
				createMeterTotals();
			} catch (Exception e) {
				DSS.doSimpleMsg("Error on rewrite of totals file: " + e.getMessage(), 536);
			}

			/* Close all the DI file for each meter */
			for (EnergyMeterObj meter : DSS.activeCircuit.getEnergyMeters())
				if (meter.isEnabled())
					meter.closeDemandIntervalFile();

			writeTotalsFile();  // sum all EnergyMeter registers to "totals.csv"
			systemMeter.closeDemandIntervalFile();
			systemMeter.save();
			try {
				meterTotals.close();
				DI_Totals.close();
				DSS.DIFilesAreOpen = false;
				if (overloadFileIsOpen) {
					overloadFile.close();
					overloadFileIsOpen = false;
				}
				if (voltageFileIsOpen) {
					voltageFile.close();
					voltageFileIsOpen = false;
				}
			} catch (IOException e) {
				DSS.doSimpleMsg("Error closing file: " + e.getMessage(), 537);
			}
		}
	}

	public void appendAllDIFiles() {
		if (saveDemandInterval) {
			clearDI_Totals();  // clears accumulator arrays

			for (EnergyMeterObj meter : DSS.activeCircuit.getEnergyMeters())
				if (meter.isEnabled())
					meter.appendDemandIntervalFile();

			systemMeter.appendDemandIntervalFile();

			/* Open DI_Totals */
			try {
				File DI_TotalsFile = new File(DI_Dir + DSS.SEPARATOR + "DI_Totals.csv");
				/* File must exist */
				if (DI_TotalsFile.exists()) {
					DI_Totals = new FileWriter(DI_TotalsFile, true);
				} else {
					createDI_Totals();
				}
			} catch (Exception e) {
				DSS.doSimpleMsg("Error opening demand interval file \"" + getClassName() + ".csv"
						+ " for appending." + DSS.CRLF + e.getMessage(), 538);
			}

			DSS.DIFilesAreOpen = true;
		}
	}

	public void setSaveDemandInterval(boolean value) {
		saveDemandInterval = value;
		resetAll();
	}

	/**
	 * Scans the active circuit for overloaded PD elements and writes each to a file.
	 * This is called only if in Demand Interval (DI) mode and the file is open.
	 */
	private void writeOverloadReport() {
		double Cmax;
		Circuit ckt = DSS.activeCircuit;

		PrintWriter pw = new PrintWriter(overloadFile);

		/* Check PDElements only */
		for (PDElement elem : ckt.getPDElements()) {
			if (elem.isEnabled() && !elem.isShunt()) {  // ignore shunts

				if (elem.getNormAmps() > 0.0 || elem.getEmergAmps() > 0.0) {
					elem.computeITerminal();
					Cmax = elem.maxTerminalOneIMag();  // for now, check only terminal 1 for overloads
					if (Cmax > elem.getNormAmps() || Cmax > elem.getEmergAmps()) {
						pw.printf("%-.6g,", ckt.getSolution().getDblHour());
						pw.printf(" %s, %-.4g, %-.4g,", Util.fullName(elem), elem.getNormAmps(), elem.getEmergAmps());
						if (elem.getNormAmps() > 0.0) {
							pw.printf(" %-.7g,", Cmax / elem.getNormAmps() * 100.0);
						} else {
							pw.print(" 0.0,");
						}
						if (elem.getEmergAmps() > 0.0) {
							pw.printf(" %-.7g,", Cmax / elem.getEmergAmps() * 100.0);
						} else {
							pw.print(" 0.0,");
						}
						/* Find bus of first terminal */
						pw.printf(" %-.3g ", ckt.getBus(ckt.getMapNodeToBus(elem.getNodeRef(0)).busRef - 1).getKVBase());

						pw.println();
					}
				}
			}
		}

		pw.close();
	}

	private void clearDI_Totals() {
		for (int i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			DI_RegisterTotals[i] = 0.0;
	}

	private void createDI_Totals() {
		EnergyMeterObj meter;

		try {
			DI_Totals = new FileWriter(DI_Dir + DSS.SEPARATOR + "DI_Totals.csv");
			PrintWriter pw = new PrintWriter(DI_Totals);

			pw.write("Time");
			for (int i = 0; i < DSS.activeCircuit.getEnergyMeters().size(); i++) {
				meter = DSS.activeCircuit.getEnergyMeters().get(i);
				pw.print(", \"" + meter.getRegisterName(i) + "\"");
			}
			pw.println();
			pw.close();
		} catch (Exception e) {
			DSS.doSimpleMsg("Error creating: \"" + DI_Dir + DSS.SEPARATOR +
					"DI_Totals.csv\": " + e.getMessage(), 539);
		}
	}

	private void createMeterTotals() {
		int i;
		EnergyMeterObj mtr;
		Circuit ckt = DSS.activeCircuit;

		try {
			File f = new File(DI_Dir + DSS.SEPARATOR + "EnergyMeterTotals.csv");
			meterTotals = new FileWriter(f);
			PrintWriter pw = new PrintWriter(meterTotals);

			pw.write("Name");
			mtr = ckt.getEnergyMeters().get(0);
			if (mtr != null) {
				for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
					pw.print(", \"" + mtr.getRegisterName(i) + "\"");
			}
			pw.println();

			pw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error writing meter totals: " + e.getMessage(), -1);
		}
	}

	public void setDIVerbose(boolean value) {
		DI_Verbose = value;
		resetAll();
	}

	private void writeTotalsFile() {
		EnergyMeterObj mtr;
		double[] regSum = new double[EnergyMeter.NUM_EM_REGISTERS];
		int i, j;
		File f;

		/* Sum up all registers of all meters and write to totals.csv */
		for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++) regSum[i] = 0.0;

		for (EnergyMeterObj meter : DSS.activeCircuit.getEnergyMeters()) {
			if (meter.isEnabled()) {
				for (j = 0; j < EnergyMeter.NUM_EM_REGISTERS; j++)
					regSum[j] = regSum[j] + meter.getRegister(j) * meter.getTotalsMask(j);
			}
		}

		try {  // write the file
			f = new File(DI_Dir + DSS.SEPARATOR + "totals.csv");

			FileWriter fw = new FileWriter(f, false);
			PrintWriter pw = new PrintWriter(fw);

			pw.write("Year");
			mtr = DSS.activeCircuit.getEnergyMeters().get(0);
			if (mtr != null) {
				for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
					pw.print(", \"" + mtr.getRegisterName(i) + "\"");
			}
			pw.println();

			pw.write(DSS.activeCircuit.getSolution().getYear());
			for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
				pw.printf(", %g ", regSum[i]);
			pw.println();

			pw.close();
			fw.close();
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening demand interval file Totals.csv." +
					DSS.CRLF + e.getMessage(), 543);
		}
	}

	private void writeVoltageReport() {
		int i, j;
		double VmagPU;
		int underCount;
		int overCount;
		double overVMax;
		double underVMin;
		Bus bus;

		/* For any bus with a defined voltage base, test for > Vmax or < Vmin */

		overCount  = 0;
		underCount = 0;

		Circuit ckt = DSS.activeCircuit;

		overVMax   = ckt.getNormalMinVolts();
		underVMin  = ckt.getNormalMaxVolts();
		for (i = 0; i < ckt.getNumBuses(); i++) {
			bus = ckt.getBus(i);
			if (bus.getKVBase() > 0.0) {
				for (j = 0; j < bus.getNumNodesThisBus(); j++) {
					VmagPU = ckt.getSolution().getNodeV(bus.getRef(j)).abs() / bus.getKVBase() * 0.001;
					if (VmagPU > 0.1) {  // ignore neutral buses
						underVMin = Math.min(underVMin, VmagPU);
						overVMax = Math.max(overVMax, VmagPU);
						if (VmagPU < ckt.getNormalMinVolts()) {
							underCount += 1;
							break;  /* next i */
						} else if (VmagPU > ckt.getNormalMaxVolts()) {
							overCount += 1;
							break;
						}
					}
				}
			}
		}  /* for i */

		PrintWriter pw = new PrintWriter(voltageFile);
		pw.printf("%-.6g,", ckt.getSolution().getDblHour());
		pw.printf(" %d, %-.6g, %d, %-.6g", underCount, underVMin, overCount, overVMax);
		pw.println();
		pw.close();
	}

	private void interpretRegisterMaskArray(double[] mask) {
		int n = Parser.getInstance().parseAsVector(NUM_EM_REGISTERS, mask);
		for (int i = n; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			mask[i] = 1.0;  // set the rest to 1
	}

	/**
	 * Similar to "append", by creates the files.
	 */
	public void openAllDIFiles() {
		if (saveDemandInterval) {

			clearDI_Totals();  // clears accumulator arrays

			for (EnergyMeterObj meter : DSS.activeCircuit.getEnergyMeters())
				if (meter.isEnabled())
					meter.openDemandIntervalFile();

			systemMeter.openDemandIntervalFile();

			/* Optional exception reporting */
			if (doOverloadReport) openOverloadReportFile();
			if (doVoltageExceptionReport) openVoltageReportFile();

			/* Open DI_Totals */
			try {
				createDI_Totals();
			} catch (Exception e) {
				DSS.doSimpleMsg("Error opening demand interval file \"" + getClassName() + ".csv" +
						" for appending." + DSS.CRLF + e.getMessage(), 538);
			}

			DSS.DIFilesAreOpen = true;
		}
	}

	private void openOverloadReportFile() {
		try {
			if (overloadFileIsOpen) overloadFile.close();

			overloadFile = new FileWriter(DSS.energyMeterClass.getDI_Dir() + "/DI_Overloads.csv");
			PrintWriter pw = new PrintWriter(overloadFile);

			overloadFileIsOpen = true;
			pw.print("\"Hour\", \"Element\", \"Normal Amps\", \"Emerg Amps\", \"% Normal\", \"% Emerg\", \"kVBase\"");
			pw.println();
			pw.close();
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening demand interval file \"" + DSS.energyMeterClass.getDI_Dir() +
				DSS.SEPARATOR + "DI_Overloads.csv\" for writing." + DSS.CRLF + e.getMessage(), 541);
		}
	}

	private void openVoltageReportFile() {
		try {
			if (voltageFileIsOpen) voltageFile.close();

			voltageFile = new FileWriter(DSS.energyMeterClass.getDI_Dir() + DSS.SEPARATOR + "DI_VoltExceptions.csv");

			voltageFileIsOpen = true;
			PrintWriter pw = new PrintWriter(voltageFile);
			pw.println("\"Hour\", \"Undervoltages\", \"Min Voltage\", \"Overvoltage\", \"Max Voltage\"");
			pw.close();
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening demand interval file \"" + DSS.energyMeterClass.getDI_Dir() + DSS.SEPARATOR +
					"DI_VoltExceptions.csv\" for writing." + DSS.CRLF + e.getMessage(), 541);
		}
	}

}
