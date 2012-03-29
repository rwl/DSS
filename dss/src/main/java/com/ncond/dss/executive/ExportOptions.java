/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.executive;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.ExportResults;
import com.ncond.dss.common.types.CIMProfileChoice;
import com.ncond.dss.common.types.ProfilePlot;
import com.ncond.dss.meter.MonitorObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

import static com.ncond.dss.common.Util.compareTextShortest;
import static com.ncond.dss.common.Util.fireOffEditor;
import static com.ncond.dss.common.Util.dumpAllocationFactors;


public class ExportOptions {

	private static final int NumExportOptions = 34;

	public static final String[] exportOption = defineOptions();
	public static final String[] exportHelp = defineHelp();

	public static CommandList exportCommands = new CommandList(exportOption, true);

	private ExportOptions() {}

	private static String[] defineOptions() {

		String[] options = new String[NumExportOptions];

		options[ 0] = "Voltages";
		options[ 1] = "SeqVoltages";
		options[ 2] = "Currents";
		options[ 3] = "SeqCurrents";
		options[ 4] = "Estimation";
		options[ 5] = "Capacity";
		options[ 6] = "Overloads";
		options[ 7] = "Unserved";
		options[ 8] = "Powers";
		options[ 9] = "SeqPowers";
		options[10] = "Faultstudy";
		options[11] = "Generators";
		options[12] = "Loads";
		options[13] = "Meters";
		options[14] = "Monitors";
		options[15] = "Yprims";
		options[16] = "Y";
		options[17] = "seqz";
		options[18] = "P_byphase";
		options[19] = "CDPSMCombined";
		options[20] = "CDPSMFunc";
		options[21] = "CDPSMAsset";
		options[22] = "Buscoords";
		options[23] = "Losses";
		options[24] = "Guids";
		options[25] = "Counts";
		options[26] = "Summary";
		options[27] = "CDPSMElec";
		options[28] = "CDPSMGeo";
		options[29] = "CDPSMTopo";
		options[30] = "CDPSMStateVar";
		options[31] = "Profile";
		options[32] = "EventLog";
		options[33] = "AllocationFactors";

		return options;
	}

	private static String[] defineHelp() {

		String[] help = new String[NumExportOptions];

		help[ 0] = "(Default file = EXP_VOLTAGES.CSV) Voltages to ground by bus/node.";
		help[ 1] = "(Default file = EXP_SEQVOLTAGES.CSV) Sequence voltages.";
		help[ 2] = "(Default file = EXP_CURRENTS.CSV) Currents in each conductor of each element.";
		help[ 3] = "(Default file = EXP_SEQCURRENTS.CSV) Sequence currents in each terminal of 3-phase elements.";
		help[ 4] = "(Default file = EXP_ESTIMATION.CSV) Results of last estimation.";
		help[ 5] = "(Default file = EXP_CAPACITY.CSV) Capacity report.";
		help[ 6] = "(Default file = EXP_OVERLOADS.CSV) Overloaded elements report.";
		help[ 7] = "(Default file = EXP_UNSERVED.CSV) Report on elements that are served in violation of ratings.";
		help[ 8] = "(Default file = EXP_POWERS.CSV) Powers into each terminal of each element.";
		help[ 9] = "(Default file = EXP_SEQPOWERS.CSV) Sequence powers into each terminal of 3-phase elements.";
		help[10] = "(Default file = EXP_FAULTS.CSV) results of a fault study.";
		help[11] = "(Default file = EXP_GENMETERS.CSV) Present values of generator meters. Adding the switch \"/multiple\" or \"/m\" will " +
			" cause a separate file to be written for each generator.";
		help[12] = "(Default file = EXP_LOADS.CSV) Report on loads from most recent solution.";
		help[13] = "(Default file = EXP_METERS.CSV) Energy meter exports. Adding the switch \"/multiple\" or \"/m\" will " +
			" cause a separate file to be written for each meter.";
		help[14] = "(file name is assigned by Monitor export) Monitor values.";
		help[15] = "(Default file = EXP_YPRIMS.CSV) All primitive Y matrices.";
		help[16] = "(Default file = EXP_Y.CSV) System Y matrix.";
		help[17] = "(Default file = EXP_SEQZ.CSV) Equivalent sequence Z1, Z0 to each bus.";
		help[18] = "(Default file = EXP_P_BYPHASE.CSV) Power by phase.";
		help[19] = "(Default file = CDPSM.XML) (IEC 61968-13, CDPSM Unbalanced load flow profile)";
		help[20] = "(Default file = CDPSM_Connect.XML) (IEC 61968-13, CDPSM Unbalanced connectivity profile)";
		help[21] = "(Default file = CDPSM_Balanced.XML) (IEC 61968-13, CDPSM Balanced profile)";
		help[22] = "[Default file = EXP_BUSCOORDS.CSV] Bus coordinates in csv form.";
		help[23] = "[Default file = EXP_LOSSES.CSV] Losses for each element.";
		help[24] = "[Default file = EXP_GUIDS.CSV] Guids for each element.";
		help[25] = "[Default file = EXP_Counts.CSV] (instance counts for each class)";
		help[26] = "[Default file = EXP_Summary.CSV] Solution summary.";
		help[27] = "(Default file = CDPSM_ElectricalProperties.XML) (IEC 61968-13, CDPSM Electrical Properties profile)";
		help[28] = "(Default file = CDPSM_Geographical.XML) (IEC 61968-13, CDPSM Geographical profile)";
		help[29] = "(Default file = CDPSM_Topology.XML) (IEC 61968-13, CDPSM Topology profile)";
		help[30] = "(Default file = CDPSM_StateVariables.XML) (IEC 61968-13, CDPSM State Variables profile)";
		help[31] = "[Default file = EXP_Profile.CSV] Coordinates, color of each line section in Profile plot. Same options as Plot Profile Phases property." + DSS.CRLF + DSS.CRLF +
			"Example:  Export Profile Phases=All [optional file name]";
		help[32] = "(Default file = EXP_EVTLOG.CSV) All entries in the present event log.";
		help[33] = "Exports load allocation factors. File name is assigned.";

		return help;
	}

	public static int doExportCmd() {
		String parm1, parm2 = "", fileName;
		MonitorObj mon;
		int phasesToPlot, paramPointer;

		Parser parser = Parser.getInstance();
		Circuit ckt = DSS.activeCircuit;

		Parser.getInstance().getNextParam();
		parm1 = Parser.getInstance().stringValue().toLowerCase();
		paramPointer = exportCommands.getCommand(parm1);

		/* Check commands requiring a solution and abort if no solution or circuit */
		if ((paramPointer >= 0 && paramPointer < 24) || (paramPointer >= 27 && paramPointer < 32)) {
			if (ckt == null) {
				DSS.doSimpleMsg("No circuit created.", 24711);
				return 0;
			}
			if ((ckt.getSolution() == null) || (ckt.getSolution().getNodeV() == null)) {
				DSS.doSimpleMsg("The circuit must be solved before you can do this.", 24712);
				return 0;
			}
		}

		int MVAOpt = 0;
		boolean UEOnlyOpt = false;
		phasesToPlot = ProfilePlot.THREEPH.phs();  // init this to get rid of compiler warning

		switch (paramPointer) {
		case 8:  // trap export powers command and look for MVA/kVA option
			parser.getNextParam();
			parm2 = Parser.getInstance().stringValue().toLowerCase();
			MVAOpt = 0;
			if (parm2.length() > 0)
				if (parm2.charAt(0) == 'm')
					MVAOpt = 1;
			break;
		case 18:
			parser.getNextParam();
			parm2 = Parser.getInstance().stringValue().toLowerCase();
			MVAOpt = 0;
			if (parm2.length() > 0)
				if (parm2.charAt(0) == 'm')
					MVAOpt = 1;
			break;
		case 7:  // trap UE only flag
			parser.getNextParam();
			parm2 = parser.stringValue().toLowerCase();
			UEOnlyOpt = false;
			if (parm2.length() > 0)
				if (parm2.charAt(0) == 'u')
					UEOnlyOpt = true;
			break;
		case 14:  // get monitor name for export monitors command
			parser.getNextParam();
			parm2 = parser.stringValue();
			break;
		case 31:  // get phases to plot
			parser.getNextParam();
			parm2 = parser.stringValue();
			phasesToPlot = ProfilePlot.THREEPH.phs();  // the default
			if (compareTextShortest(parm2, "default") == 0) {
				phasesToPlot = ProfilePlot.THREEPH.phs();
			} else if (compareTextShortest(parm2, "all") == 0) {
				phasesToPlot = ProfilePlot.ALL.phs();
			} else if (compareTextShortest(parm2, "primary") == 0) {
				phasesToPlot = ProfilePlot.ALLPRI.phs();
			} else if (compareTextShortest(parm2, "ll3ph") == 0) {
				phasesToPlot = ProfilePlot.LL.phs();
			} else if (compareTextShortest(parm2, "llall") == 0) {
				phasesToPlot = ProfilePlot.LLALL.phs();
			} else if (compareTextShortest(parm2, "llprimary") == 0) {
				phasesToPlot = ProfilePlot.LLPRI.phs();
			} else if (parm2.length() == 1) {
				phasesToPlot = parser.integerValue();
			}
			break;
		}

		/* Pick up last parameter on line, alternate file name, if any */
		parser.getNextParam();
		fileName = parser.stringValue().toLowerCase();  // should be full path name to work universally

		DSS.inShowResults = true;

		/* Assign default file name if alternate not specified */
		if (fileName.length() == 0) {
			switch (paramPointer) {
			case  0: fileName = "EXP_VOLTAGES.csv"; break;
			case  1: fileName = "EXP_SEQVOLTAGES.csv"; break;
			case  2: fileName = "EXP_CURRENTS.csv"; break;
			case  3: fileName = "EXP_SEQCURRENTS.csv"; break;
			case  4: fileName = "EXP_ESTIMATION.csv"; break;  // estimation error
			case  5: fileName = "EXP_CAPACITY.csv"; break;
			case  6: fileName = "EXP_OVERLOADS.csv"; break;
			case  7: fileName = "EXP_UNSERVED.csv"; break;
			case  8: fileName = "EXP_POWERS.csv"; break;
			case  9: fileName = "EXP_SEQPOWERS.csv"; break;
			case 10: fileName = "EXP_FAULTS.csv"; break;
			case 11: fileName = "EXP_GENMETERS.csv"; break;
			case 12: fileName = "EXP_LOADS.csv"; break;
			case 13: fileName = "EXP_METERS.csv"; break;
			//case 14: FileName is assigned
			case 15: fileName = "EXP_YPRIM.csv"; break;
			case 16: fileName = "EXP_Y.csv"; break;
			case 17: fileName = "EXP_SEQZ.csv"; break;
			case 18: fileName = "EXP_P_BYPHASE.csv"; break;
			case 19: fileName = "CDPSM_Combined.xml"; break;
			case 20: fileName = "CDPSM_Functional.xml"; break;
			case 21: fileName = "CDPSM_Asset.xml"; break;
			case 22: fileName = "EXP_BUSCOORDS.csv"; break;
			case 23: fileName = "EXP_LOSSES.csv"; break;
			case 24: fileName = "EXP_GUIDS.csv"; break;
			case 25: fileName = "EXP_Counts.csv"; break;
			case 26: fileName = "EXP_Summary.csv"; break;
			case 27: fileName = "CDPSM_ElectricalProperties.xml"; break;
			case 28: fileName = "CDPSM_Geographical.xml"; break;
			case 29: fileName = "CDPSM_Topology.xml"; break;
			case 30: fileName = "CDPSM_StateVariables.xml"; break;
			case 31: fileName = "EXP_Profile.csv"; break;
			case 32: fileName = "EXP_EVTLOG.csv"; break;
			case 33: fileName = "AllocationFactors.txt";
			default: fileName = "EXP_VOLTAGES.csv"; break;
			}
			fileName = DSS.dataDirectory + DSS.circuitName_ + fileName;  // explicitly define directory
		}

		switch (paramPointer) {
		case  0: ExportResults.exportVoltages(fileName); break;
		case  1: ExportResults.exportSeqVoltages(fileName); break;
		case  2: ExportResults.exportCurrents(fileName); break;
		case  3: ExportResults.exportSeqCurrents(fileName); break;
		case  4: ExportResults.exportEstimation(fileName); break;  // estimation error
		case  5: ExportResults.exportCapacity(fileName); break;
		case  6: ExportResults.exportOverloads(fileName); break;
		case  7: ExportResults.exportUnserved(fileName, UEOnlyOpt); break;
		case  8: ExportResults.exportPowers(fileName, MVAOpt); break;
		case  9: ExportResults.exportSeqPowers(fileName, MVAOpt); break;
		case 10: ExportResults.exportFaultStudy(fileName); break;
		case 11: ExportResults.exportGenMeters(fileName); break;
		case 12: ExportResults.exportLoads(fileName); break;
		case 13: ExportResults.exportMeters(fileName); break;
		case 14:
			if (parm2.length() > 0) {
				mon = (MonitorObj) DSS.monitorClass.find(parm2);
				if (mon != null) {
					mon.translateToCSV(false);
					fileName = DSS.globalResult;
				} else {
					DSS.doSimpleMsg("Monitor \"" + parm2 + "\" not found." +
							DSS.CRLF + parser.getCommand(), 250);
				}
			} else {
				DSS.doSimpleMsg("Monitor name not specified." + DSS.CRLF +
						parser.getCommand(), 251);
			}
			break;
		case 15: ExportResults.exportYprim(fileName); break;
		case 16: ExportResults.exportY(fileName); break;
		case 17: ExportResults.exportSeqZ(fileName); break;
		case 18: ExportResults.exportPowersByPhase(fileName, MVAOpt); break;
		case 19: ExportResults.exportCDPSM(fileName, CIMProfileChoice.COMBINED); break;  // defaults to a load-flow model
		case 20: ExportResults.exportCDPSM(fileName, CIMProfileChoice.FUNCTIONAL); break;
		case 21: ExportResults.exportCDPSM(fileName, CIMProfileChoice.ASSET); break;
		case 22: ExportResults.exportBusCoords(fileName); break;
		case 23: ExportResults.exportLosses(fileName); break;
		case 24: ExportResults.exportUUIDs(fileName); break;
		case 25: ExportResults.exportCounts(fileName); break;
		case 26: ExportResults.exportSummary(fileName); break;
		case 27: ExportResults.exportCDPSM(fileName, CIMProfileChoice.ELECTRICAL_PROPERTIES); break;
		case 28: ExportResults.exportCDPSM(fileName, CIMProfileChoice.GEOGRAPHICAL); break;
		case 29: ExportResults.exportCDPSM(fileName, CIMProfileChoice.TOPOLOGY); break;
		case 30: ExportResults.exportCDPSM(fileName, CIMProfileChoice.STATE_VARIABLES); break;
		case 31: ExportResults.exportProfile(fileName, phasesToPlot); break;
		case 32: ExportResults.exportEventLog(fileName); break;
		case 33: dumpAllocationFactors(fileName); break;
		default: ExportResults.exportVoltages(fileName); break;
		}

		DSS.inShowResults = false;

		if (DSS.autoShowExport) fireOffEditor(fileName);

		return 0;
	}

}
