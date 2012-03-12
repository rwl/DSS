package com.ncond.dss.executive;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.ExportResults;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.CIMProfileChoice;
import com.ncond.dss.common.types.ProfilePlot;
import com.ncond.dss.meter.MonitorObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class ExportOptions {

	private static final int NumExportOptions = 34;

	private String[] exportOption;
	private String[] exportHelp;

	private static CommandList exportCommands;

	// private constructor prevents instantiation from other classes
	private ExportOptions() {
		defineOptions();

		exportCommands = new CommandList(exportOption);
		exportCommands.setAbbrevAllowed(true);
	}

	/**
	 * ExportOptionsHolder is loaded on the first execution of
	 * ExportOptions.getInstance() or the first access to
	 * ExportOptionsHolder.INSTANCE, not before.
	 */
	private static class ExportOptionsHolder {
		public static final ExportOptions INSTANCE = new ExportOptions();
	}

	public static ExportOptions getInstance() {
		return ExportOptionsHolder.INSTANCE;
	}

	private void defineOptions() {

		exportOption = new String[NumExportOptions];

		exportOption[ 0] = "Voltages";
		exportOption[ 1] = "SeqVoltages";
		exportOption[ 2] = "Currents";
		exportOption[ 3] = "SeqCurrents";
		exportOption[ 4] = "Estimation";
		exportOption[ 5] = "Capacity";
		exportOption[ 6] = "Overloads";
		exportOption[ 7] = "Unserved";
		exportOption[ 8] = "Powers";
		exportOption[ 9] = "SeqPowers";
		exportOption[10] = "Faultstudy";
		exportOption[11] = "Generators";
		exportOption[12] = "Loads";
		exportOption[13] = "Meters";
		exportOption[14] = "Monitors";
		exportOption[15] = "Yprims";
		exportOption[16] = "Y";
		exportOption[17] = "seqz";
		exportOption[18] = "P_byphase";
		exportOption[19] = "CDPSMCombined";
		exportOption[20] = "CDPSMFunc";
		exportOption[21] = "CDPSMAsset";
		exportOption[22] = "Buscoords";
		exportOption[23] = "Losses";
		exportOption[24] = "Guids";
		exportOption[25] = "Counts";
		exportOption[26] = "Summary";
		exportOption[27] = "CDPSMElec";
		exportOption[28] = "CDPSMGeo";
		exportOption[29] = "CDPSMTopo";
		exportOption[30] = "CDPSMStateVar";
		exportOption[31] = "Profile";
		exportOption[32] = "EventLog";
		exportOption[33] = "AllocationFactors";

		exportHelp[ 0] = "(Default file = EXP_VOLTAGES.CSV) Voltages to ground by bus/node.";
		exportHelp[ 1] = "(Default file = EXP_SEQVOLTAGES.CSV) Sequence voltages.";
		exportHelp[ 2] = "(Default file = EXP_CURRENTS.CSV) Currents in each conductor of each element.";
		exportHelp[ 3] = "(Default file = EXP_SEQCURRENTS.CSV) Sequence currents in each terminal of 3-phase elements.";
		exportHelp[ 4] = "(Default file = EXP_ESTIMATION.CSV) Results of last estimation.";
		exportHelp[ 5] = "(Default file = EXP_CAPACITY.CSV) Capacity report.";
		exportHelp[ 6] = "(Default file = EXP_OVERLOADS.CSV) Overloaded elements report.";
		exportHelp[ 7] = "(Default file = EXP_UNSERVED.CSV) Report on elements that are served in violation of ratings.";
		exportHelp[ 8] = "(Default file = EXP_POWERS.CSV) Powers into each terminal of each element.";
		exportHelp[ 9] = "(Default file = EXP_SEQPOWERS.CSV) Sequence powers into each terminal of 3-phase elements.";
		exportHelp[10] = "(Default file = EXP_FAULTS.CSV) results of a fault study.";
		exportHelp[11] = "(Default file = EXP_GENMETERS.CSV) Present values of generator meters. Adding the switch \"/multiple\" or \"/m\" will " +
							" cause a separate file to be written for each generator.";
		exportHelp[12] = "(Default file = EXP_LOADS.CSV) Report on loads from most recent solution.";
		exportHelp[13] = "(Default file = EXP_METERS.CSV) Energy meter exports. Adding the switch \"/multiple\" or \"/m\" will " +
							" cause a separate file to be written for each meter.";
		exportHelp[14] = "(file name is assigned by Monitor export) Monitor values.";
		exportHelp[15] = "(Default file = EXP_YPRIMS.CSV) All primitive Y matrices.";
		exportHelp[16] = "(Default file = EXP_Y.CSV) System Y matrix.";
		exportHelp[17] = "(Default file = EXP_SEQZ.CSV) Equivalent sequence Z1, Z0 to each bus.";
		exportHelp[18] = "(Default file = EXP_P_BYPHASE.CSV) Power by phase.";
		exportHelp[19] = "(Default file = CDPSM.XML) (IEC 61968-13, CDPSM Unbalanced load flow profile)";
		exportHelp[20] = "(Default file = CDPSM_Connect.XML) (IEC 61968-13, CDPSM Unbalanced connectivity profile)";
		exportHelp[21] = "(Default file = CDPSM_Balanced.XML) (IEC 61968-13, CDPSM Balanced profile)";
		exportHelp[22] = "[Default file = EXP_BUSCOORDS.CSV] Bus coordinates in csv form.";
		exportHelp[23] = "[Default file = EXP_LOSSES.CSV] Losses for each element.";
		exportHelp[24] = "[Default file = EXP_GUIDS.CSV] Guids for each element.";
		exportHelp[25] = "[Default file = EXP_Counts.CSV] (instance counts for each class)";
		exportHelp[26] = "[Default file = EXP_Summary.CSV] Solution summary.";
		exportHelp[27] = "(Default file = CDPSM_ElectricalProperties.XML) (IEC 61968-13, CDPSM Electrical Properties profile)";
		exportHelp[28] = "(Default file = CDPSM_Geographical.XML) (IEC 61968-13, CDPSM Geographical profile)";
		exportHelp[29] = "(Default file = CDPSM_Topology.XML) (IEC 61968-13, CDPSM Topology profile)";
		exportHelp[30] = "(Default file = CDPSM_StateVariables.XML) (IEC 61968-13, CDPSM State Variables profile)";
		exportHelp[31] = "[Default file = EXP_Profile.CSV] Coordinates, color of each line section in Profile plot. Same options as Plot Profile Phases property." + DSS.CRLF + DSS.CRLF +
			"Example:  Export Profile Phases=All [optional file name]";
		exportHelp[32] = "(Default file = EXP_EVTLOG.CSV) All entries in the present event log.";
		exportHelp[33] = "Exports load allocation factors. File name is assigned.";
	}

	public int doExportCmd() {
		String parm2 = "", fileName;
		MonitorObj pMon;
		int phasesToPlot;

		Parser parser = Parser.getInstance();

		int result = 0;

		Parser.getInstance().getNextParam();
		String parm1 = Parser.getInstance().makeString().toLowerCase();
		int paramPointer = exportCommands.getCommand(parm1);

		/* Check commands requiring a solution and abort if no solution or circuit */
		if ((paramPointer >= 0 && paramPointer < 24) || (paramPointer >= 27 && paramPointer < 32)) {
			if (DSS.activeCircuit == null) {
				DSS.doSimpleMsg("No circuit created.", 24711);
				return result;
			}
			if ((DSS.activeCircuit.getSolution() == null) || (DSS.activeCircuit.getSolution().getNodeV() == null)) {
				DSS.doSimpleMsg("The circuit must be solved before you can do this.", 24712);
				return result;
			}
		}

		int MVAOpt = 0;
		boolean UEOnlyOpt = false;
		phasesToPlot = ProfilePlot.THREEPH.phs();  // init this to get rid of compiler warning

		switch (paramPointer) {
		case 8:  // trap export powers command and look for MVA/kVA option
			parser.getNextParam();
			parm2 = Parser.getInstance().makeString().toLowerCase();
			MVAOpt = 0;
			if (parm2.length() > 0)
				if (parm2.charAt(0) == 'm')
					MVAOpt = 1;
			break;
		case 18:
			parser.getNextParam();
			parm2 = Parser.getInstance().makeString().toLowerCase();
			MVAOpt = 0;
			if (parm2.length() > 0)
				if (parm2.charAt(0) == 'm')
					MVAOpt = 1;
			break;
		case 7:  // trap UE only flag
			parser.getNextParam();
			parm2 = parser.makeString().toLowerCase();
			UEOnlyOpt = false;
			if (parm2.length() > 0)
				if (parm2.charAt(0) == 'u')
					UEOnlyOpt = true;
			break;
		case 14:  // get monitor name for export monitors command
			parser.getNextParam();
			parm2 = parser.makeString();
			break;
		case 31:  /* Get phases to plot */
			parser.getNextParam();
			parm2 = parser.makeString();
			phasesToPlot = ProfilePlot.THREEPH.phs();  // the default
			if (Util.compareTextShortest(parm2, "default") == 0) {
				phasesToPlot = ProfilePlot.THREEPH.phs();
			} else if (Util.compareTextShortest(parm2, "all") == 0) {
				phasesToPlot = ProfilePlot.ALL.phs();
			} else if (Util.compareTextShortest(parm2, "primary") == 0) {
				phasesToPlot = ProfilePlot.ALLPRI.phs();
			} else if (Util.compareTextShortest(parm2, "ll3ph") == 0) {
				phasesToPlot = ProfilePlot.LL.phs();
			} else if (Util.compareTextShortest(parm2, "llall") == 0) {
				phasesToPlot = ProfilePlot.LLALL.phs();
			} else if (Util.compareTextShortest(parm2, "llprimary") == 0) {
				phasesToPlot = ProfilePlot.LLPRI.phs();
			} else if (parm2.length() == 1) {
				phasesToPlot = parser.makeInteger();
			}
			break;
		}

		/* Pick up last parameter on line, alternate file name, if any */
		parser.getNextParam();
		fileName = parser.makeString().toLowerCase();  // should be full path name to work universally

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
		case 0: ExportResults.exportVoltages(fileName); break;
		case 1: ExportResults.exportSeqVoltages(fileName); break;
		case 2: ExportResults.exportCurrents(fileName); break;
		case 3: ExportResults.exportSeqCurrents(fileName); break;
		case 4: ExportResults.exportEstimation(fileName); break;  // estimation error
		case 5: ExportResults.exportCapacity(fileName); break;
		case 6: ExportResults.exportOverloads(fileName); break;
		case 7: ExportResults.exportUnserved(fileName, UEOnlyOpt); break;
		case 8: ExportResults.exportPowers(fileName, MVAOpt); break;
		case 9: ExportResults.exportSeqPowers(fileName, MVAOpt); break;
		case 10: ExportResults.exportFaultStudy(fileName); break;
		case 11: ExportResults.exportGenMeters(fileName); break;
		case 12: ExportResults.exportLoads(fileName); break;
		case 13: ExportResults.exportMeters(fileName); break;
		case 14:
			if (parm2.length() > 0) {
				pMon = (MonitorObj) DSS.monitorClass.find(parm2);
				if (pMon != null) {
					pMon.translateToCSV(false);
					fileName = DSS.globalResult;
				} else {
					DSS.doSimpleMsg("Monitor \""+parm2+"\" not found."+ DSS.CRLF + parser.getCmdString(), 250);
				}
			} else {
				DSS.doSimpleMsg("Monitor name not specified."+ DSS.CRLF + parser.getCmdString(), 251);
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
		case 33: Util.dumpAllocationFactors(fileName); break;
		default: ExportResults.exportVoltages(fileName); break;
		}

		DSS.inShowResults = false;

		if (DSS.autoShowExport)
			Util.fireOffEditor(fileName);

		return result;
	}

}
