package com.epri.dss.executive.impl;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.ExportResults;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.executive.ExportOptions;
import com.epri.dss.meter.MonitorObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CommandList;

public class ExportOptionsImpl implements ExportOptions {

	private static final int NumExportOptions = 26;

	private String[] ExportOption;
	private String[] ExportHelp;

	private static CommandList ExportCommands;

	public ExportOptionsImpl() {

		this.ExportOption = new String[NumExportOptions];

		this.ExportOption[ 0] = "Voltages";
		this.ExportOption[ 1] = "SeqVoltages";
		this.ExportOption[ 2] = "Currents";
		this.ExportOption[ 3] = "SeqCurrents";
		this.ExportOption[ 4] = "Estimation";
		this.ExportOption[ 5] = "Capacity";
		this.ExportOption[ 6] = "Overloads";
		this.ExportOption[ 7] = "Unserved";
		this.ExportOption[ 8] = "Powers";
		this.ExportOption[ 9] = "SeqPowers";
		this.ExportOption[10] = "Faultstudy";
		this.ExportOption[11] = "Generators";
		this.ExportOption[12] = "Loads";
		this.ExportOption[13] = "Meters";
		this.ExportOption[14] = "Monitors";
		this.ExportOption[15] = "Yprims";
		this.ExportOption[16] = "Y";
		this.ExportOption[17] = "seqz";
		this.ExportOption[18] = "P_byphase";
		this.ExportOption[19] = "CDPSM";
		this.ExportOption[20] = "CDPSMConnect";
		this.ExportOption[21] = "CDPSMBalanced";
		this.ExportOption[22] = "Buscoords";
		this.ExportOption[23] = "Losses";
		this.ExportOption[24] = "Guids";
		this.ExportOption[25] = "Counts";

		this.ExportHelp[ 0] = "(Default file = EXP_VOLTAGES.CSV) Voltages to ground by bus/node.";
		this.ExportHelp[ 1] = "(Default file = EXP_SEQVOLTAGES.CSV) Sequence voltages.";
		this.ExportHelp[ 2] = "(Default file = EXP_CURRENTS.CSV) Currents in each conductor of each element.";
		this.ExportHelp[ 3] = "(Default file = EXP_SEQCURRENTS.CSV) Sequence currents in each terminal of 3-phase elements.";
		this.ExportHelp[ 4] = "(Default file = EXP_ESTIMATION.CSV) Results of last estimation.";
		this.ExportHelp[ 5] = "(Default file = EXP_CAPACITY.CSV) Capacity report.";
		this.ExportHelp[ 6] = "(Default file = EXP_OVERLOADS.CSV) Overloaded elements report.";
		this.ExportHelp[ 7] = "(Default file = EXP_UNSERVED.CSV) Report on elements that are served in violation of ratings.";
		this.ExportHelp[ 8] = "(Default file = EXP_POWERS.CSV) Powers into each terminal of each element.";
		this.ExportHelp[ 9] = "(Default file = EXP_SEQPOWERS.CSV) Sequence powers into each terminal of 3-phase elements.";
		this.ExportHelp[10] = "(Default file = EXP_FAULTS.CSV) results of a fault study.";
		this.ExportHelp[11] = "(Default file = EXP_GENMETERS.CSV) Present values of generator meters. Adding the switch \"/multiple\" or \"/m\" will " +
							" cause a separate file to be written for each generator.";
		this.ExportHelp[12] = "(Default file = EXP_LOADS.CSV) Report on loads from most recent solution.";
		this.ExportHelp[13] = "(Default file = EXP_METERS.CSV) Energy meter exports. Adding the switch \"/multiple\" or \"/m\" will " +
							" cause a separate file to be written for each meter.";
		this.ExportHelp[14] = "(file name is assigned by Monitor export) Monitor values.";
		this.ExportHelp[15] = "(Default file = EXP_YPRIMS.CSV) All primitive Y matrices.";
		this.ExportHelp[16] = "(Default file = EXP_Y.CSV) System Y matrix.";
		this.ExportHelp[17] = "(Default file = EXP_SEQZ.CSV) Equivalent sequence Z1, Z0 to each bus.";
		this.ExportHelp[18] = "(Default file = EXP_P_BYPHASE.CSV) Power by phase.";
		this.ExportHelp[19] = "(Default file = CDPSM.XML) (IEC 61968-13, CDPSM Unbalanced load flow profile)";
		this.ExportHelp[20] = "(Default file = CDPSM_Connect.XML) (IEC 61968-13, CDPSM Unbalanced connectivity profile)";
		this.ExportHelp[21] = "(Default file = CDPSM_Balanced.XML) (IEC 61968-13, CDPSM Balanced profile)";
		this.ExportHelp[22] = "[Default file = EXP_BUSCOORDS.CSV] Bus coordinates in csv form.";
		this.ExportHelp[23] = "[Default file = EXP_LOSSES.CSV] Losses for each element.";
		this.ExportHelp[24] = "[Default file = EXP_GUIDS.CSV] Guids for each element.";
		this.ExportHelp[25] = "[Default file = EXP_Counts.CSV] (instance counts for each class)";

	}

	public static int doExportCmd() {
		String Parm2, FileName;
		MonitorObj pMon;
		
		Parser parser = Parser.getInstance();
		DSSGlobals Globals = DSSGlobals.getInstance();

		String ParamName = Parser.getInstance().getNextParam();
		String Parm1 = Parser.getInstance().makeString().toLowerCase();
		int ParamPointer = ExportCommands.getCommand(Parm1);

		int MVAOpt = 0;
		boolean UEonlyOpt = false;

		switch (ParamPointer) {
		case 9:  // Trap export powers command and look for MVA/kVA option
			ParamName = parser.getNextParam();
			Parm2 = Parser.getInstance().makeString().toLowerCase();
			MVAOpt = 0;
			if (Parm2.length() > 0)
				if (Parm2.charAt(0) == 'm')
					MVAOpt = 1;
		case 19:
			ParamName = parser.getNextParam();
			Parm2 = Parser.getInstance().makeString().toLowerCase();
			MVAOpt = 0;
			if (Parm2.length() > 0)
				if (Parm2.charAt(0) == 'm')
					MVAOpt = 1;
		case 8:  // Trap UE only flag
			ParamName = parser.getNextParam();
			Parm2 = parser.makeString().toLowerCase();
			UEonlyOpt = false;
			if (Parm2.length() > 0) 
				if (Parm2.charAt(0) == 'u')
					UEonlyOpt = true;
		case 15:  // Get monitor name for export monitors command
			ParamName = parser.getNextParam();
			Parm2 = parser.makeString();
		}

		/* Pick up last parameter on line, alternate file name, if any */
		ParamName = parser.getNextParam();
		FileName = parser.makeString().toLowerCase();  // should be full path name to work universally

		Globals.setInShowResults(true);

		/* Assign default file name if alternate not specified */
		if (FileName.length() == 0) {
			switch (ParamPointer) {
			case 1: FileName = "EXP_VOLTAGES.CSV";
			case 2: FileName = "EXP_SEQVOLTAGES.CSV";
			case 3: FileName = "EXP_CURRENTS.CSV";
			case 4: FileName = "EXP_SEQCURRENTS.CSV";
			case 5: FileName = "EXP_ESTIMATION.CSV";   // Estimation error
			case 6: FileName = "EXP_CAPACITY.CSV";
			case 7: FileName = "EXP_OVERLOADS.CSV";
			case 8: FileName = "EXP_UNSERVED.CSV";
			case 9: FileName = "EXP_POWERS.CSV";
			case 10: FileName = "EXP_SEQPOWERS.CSV";
			case 11: FileName = "EXP_FAULTS.CSV";
			case 12: FileName = "EXP_GENMETERS.CSV";
			case 13: FileName = "EXP_LOADS.CSV";
			case 14: FileName = "EXP_METERS.CSV";
			//case 15: FileName is assigned
			case 16: FileName = "EXP_YPRIM.CSV";
			case 17: FileName = "EXP_Y.CSV";
			case 18: FileName = "EXP_SEQZ.CSV";
			case 19: FileName = "EXP_P_BYPHASE.CSV";
			case 20: FileName = "CDPSM_Unbalanced.XML";
			case 21: FileName = "CDPSM_Connect.XML";
			case 22: FileName = "CDPSM_Balanced.XML";
			case 23: FileName = "EXP_BUSCOORDS.CSV";
			case 24: FileName = "EXP_LOSSES.CSV";
			case 25: FileName = "EXP_GUIDS.CSV";
			case 26: FileName = "EXP_Counts.CSV";
			case 27: FileName = "EXP_Summary.CSV";
			default:
				FileName = "EXP_VOLTAGES.CSV";
			}
			FileName = Globals.getDSSDataDirectory() + Globals.getCircuitName_() + FileName;  // Explicitly define directory
		}

		switch (ParamPointer) {
		case 1: ExportResults.exportVoltages(FileName);
		case 2: ExportResults.exportSeqVoltages(FileName);
		case 3: ExportResults.exportCurrents(FileName);
		case 4: ExportResults.exportSeqCurrents(FileName);
		case 5: ExportResults.exportEstimation(FileName);   // Estimation error
		case 6: ExportResults.exportCapacity(FileName);
		case 7: ExportResults.exportOverloads(FileName);
		case 8: ExportResults.exportUnserved(FileName, UEonlyOpt);
		case 9: ExportResults.exportPowers(FileName, MVAOpt);
		case 10: ExportResults.exportSeqPowers(FileName, MVAOpt);
		case 11: ExportResults.exportFaultStudy(FileName);
		case 12: ExportResults.exportGenMeters(FileName);
		case 13: ExportResults.exportLoads(FileName);
		case 14: ExportResults.exportMeters(FileName);
		case 15:
			if (Parm2.length() > 0) {
				pMon = (MonitorObj) Globals.getMonitorClass().find(Parm2);
				if (pMon != null) {
					pMon.translateToCSV(false);
				} else {
					Globals.doSimpleMsg("Monitor \""+Parm2+"\" not found."+ DSSGlobals.CRLF + parser.getCmdString(), 250);
				}
			} else {
				Globals.doSimpleMsg("Monitor Name Not Specified."+ DSSGlobals.CRLF + parser.getCmdString(), 251);
			}
		case 16: ExportResults.exportYprim(FileName);
		case 17: ExportResults.exportY(FileName);
		case 18: ExportResults.exportSeqZ(FileName);
		case 19: ExportResults.exportPbyphase(FileName, MVAOpt);
		case 20: ExportResults.exportCDPSM_UnBal(FileName);        // defaults to a load-flow model
		case 21: ExportResults.exportCDPSM_UnBal(FileName, false); // not a load-flow model
		case 22: ExportResults.exportCDPSM_Bal(FileName);
		case 23: ExportResults.exportBusCoords(FileName);
		case 24: ExportResults.exportLosses(FileName);
		case 25: ExportResults.exportUUIDs(FileName);
		case 26: ExportResults.exportCounts(FileName);
		case 27: ExportResults.exportSummary(FileName);
		default:
			ExportResults.exportVoltages(FileName);    
		}

		int Result = 0;
		Globals.setInShowResults(false);

		if (Globals.isAutoShowExport())
			Utilities.fireOffEditor(FileName);

		return Result;
	}

}
