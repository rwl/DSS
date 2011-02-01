package com.epri.dss.executive.impl;

import com.epri.dss.common.DSSGlobals;
import com.epri.dss.executive.PlotOptions;
import com.epri.dss.shared.CommandList;

public class PlotOptionsImpl implements PlotOptions {

	private String CRLF = DSSGlobals.CRLF;

	private static final int NumPlotOptions = 17;

	private String[] PlotOption;
	private String[] PlotHelp;

	private CommandList PlotCommands;

	public PlotOptionsImpl() {

		this.PlotOption = new String[NumPlotOptions];

		this.PlotOption[ 0] = "type";
		this.PlotOption[ 1] = "quantity";
		this.PlotOption[ 2] = "max";
		this.PlotOption[ 3] = "dots";
		this.PlotOption[ 4] = "labels";
		this.PlotOption[ 5] = "object";
		this.PlotOption[ 6] = "showloops";
		this.PlotOption[ 7] = "r3";
		this.PlotOption[ 8] = "r2";
		this.PlotOption[ 9] = "c1";
		this.PlotOption[10] = "c2";
		this.PlotOption[11] = "c3";
		this.PlotOption[12] = "channels";
		this.PlotOption[13] = "bases";
		this.PlotOption[14] = "subs";
		this.PlotOption[15] = "thickness";
		this.PlotOption[16] = "buslist";


		this.PlotHelp = new String[NumPlotOptions];

		this.PlotHelp[ 0] = "One of {Circuit | Monitor | Daisy | Zones | AutoAdd | General (bus data) | Loadshape } " +
		                    "A \"Daisy\" plot is a special circuit plot that places a marker at each Generator location " +
		                    "or at buses in the BusList property, if defined. " +
		                    "A Zones plot shows the meter zones (see help on Object). " +
		                    "Autoadd shows the autoadded generators. General plot shows quantities associated with buses " +
		                    "using gradient colors between C1 and C2. Values are read from a file (see Object). " +
		                    "Loadshape plots the specified loadshape. Examples:"+CRLF+CRLF+
		                    "Plot type=circuit quantity=power" +CRLF+
		                    "Plot Circuit Losses" +CRLF+
		                    "Plot Circuit quantity=3 object=mybranchdata.csv" +CRLF+
		                    "Plot daisy power max=5000 dots=N Buslist=[file=MyBusList.txt]" +CRLF+
		                    "Plot General quantity=1 object=mybusdata.csv" +CRLF+
		                    "Plot Loadshape object=myloadshape" ;
		this.PlotHelp[ 1] = "One of {Voltage | Current | Power | Losses | Capacity | (Value Index for General, AutoAdd, or Circuit[w/ file]) }";
		this.PlotHelp[ 2] = "Enter 0 or the value corresponding to max scale or line thickness in the circuit plots. "+
		                    "Power and Losses in kW.";
		this.PlotHelp[ 3] = "Yes or No*. Places a marker on the circuit plot at the bus location. See Set Markercode under options.";
		this.PlotHelp[ 4] = "Yes or No*. If yes, bus labels (abbreviated) are printed on the circuit plot.";
		this.PlotHelp[ 5] = "Object to be plotted. One of [Meter Name (zones plot) | Monitor Name | LoadShape Name | File Name for General bus data | File Name Circuit branch data]";
		this.PlotHelp[ 6] = "{Yes | No*} Shows loops on Circuit plot. Requires an EnergyMeter to be defined.";
		this.PlotHelp[ 7] = "pu value for tri-color plot max range [default=.85 of max scale]. Corresponds to color C3.";
		this.PlotHelp[ 8] = "pu value for tri-color plot mid range [default=.50 of max scale]. Corresponds to color C2.";
		this.PlotHelp[ 9] = "RGB color number for color C1. This is the default color for circuit plots. Default is blue. See options in the Plot menu.";
		this.PlotHelp[10] = "RGB color number for color C2. Used for gradients and tricolor plots such as circuit voltage.";
		this.PlotHelp[11] = "RGB color number for color C3. Used for gradients and tricolor plots such a circuit voltage.";
		this.PlotHelp[12] = "Array of channel numbers for monitor plot. Example" +CRLF+CRLF+
		                    "Plot Type=Monitor Object=MyMonitor Channels=[1, 3, 5]"+CRLF+CRLF+
		                    "Do \"Show Monitor MyMonitor\" to see channel definitions.";
		this.PlotHelp[13] = "Array of base values for each channel for monitor plot. Useful for creating per unit plots. Default is 1.0 for each channel.  Set Base= property after defining channels."+CRLF+CRLF+
		                    "Plot Type=Monitor Object=MyMonitor Channels=[1, 3, 5] Bases=[2400 2400 2400]"+CRLF+CRLF+
		                    "Do \"Show Monitor MyMonitor\" to see channel range and definitions.";;
		this.PlotHelp[14] = "{Yes | No*} Displays a marker at each transformer declared to be a substation. " +
		                    "At least one bus coordinate must be defined for the transformer. "+
		                    "See MarkTransformer and TransMarkerCode options.";
		this.PlotHelp[15] = "Max thickness allowed for lines in circuit plots (default=7).";
		this.PlotHelp[16] = "{Array of Bus Names | File=filename } This is for the Daisy plot. "+CRLF+CRLF+
		                    "Plot daisy power max=5000 dots=N Buslist=[file=MyBusList.txt]" +CRLF+CRLF+
		                    "A \"daisy\" marker is plotted for " +
		                    "each bus in the list. Bus name may be repeated, which results in multiple markers distributed around the bus location. " +
		                    "This gives the appearance of a daisy if there are several symbols at a bus. Not needed for plotting active generators.";

	}

	public int doPlotCmd() {
		return 0;
	}

}
