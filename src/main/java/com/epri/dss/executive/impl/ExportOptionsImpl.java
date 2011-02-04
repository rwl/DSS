package com.epri.dss.executive.impl;

import com.epri.dss.executive.ExportOptions;
import com.epri.dss.shared.CommandList;

public class ExportOptionsImpl implements ExportOptions {

	private static final int NumExportOptions = 26;

	private String[] ExportOption;
	private String[] ExportHelp;

	private CommandList ExportCommands;

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
		return 0;
	}

}
