package com.epri.dss.executive.impl;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.plot.DSSPlot;
import com.epri.dss.plot.impl.DSSPlotImpl;
import com.epri.dss.plot.impl.DSSPlotImpl.PlotQuantity;
import com.epri.dss.plot.impl.DSSPlotImpl.PlotType;
import com.epri.dss.shared.CommandList;
import com.epri.dss.shared.impl.CommandListImpl;

public class PlotOptions {

	private static final String CRLF = DSSGlobals.CRLF;

	private static final int NumPlotOptions = 17;

	private String[] plotOption;
	private String[] plotHelp;

	private CommandList plotCommands;

	// Private constructor prevents instantiation from other classes
	private PlotOptions() {
		defineOptions();

		plotCommands = new CommandListImpl(plotOption);
		plotCommands.setAbbrevAllowed(true);
	}

	/**
	 * PlotOptionsHolder is loaded on the first execution of
	 * PlotOptions.getInstance() or the first access to
	 * PlotOptionsHolder.INSTANCE, not before.
	 */
	private static class PlotOptionsHolder {
		public static final PlotOptions INSTANCE = new PlotOptions();
	}

	public static PlotOptions getInstance() {
		return PlotOptionsHolder.INSTANCE;
	}

	private void defineOptions() {

		this.plotOption = new String[NumPlotOptions];

		this.plotOption[ 0] = "type";
		this.plotOption[ 1] = "quantity";
		this.plotOption[ 2] = "max";
		this.plotOption[ 3] = "dots";
		this.plotOption[ 4] = "labels";
		this.plotOption[ 5] = "object";
		this.plotOption[ 6] = "showloops";
		this.plotOption[ 7] = "r3";
		this.plotOption[ 8] = "r2";
		this.plotOption[ 9] = "c1";
		this.plotOption[10] = "c2";
		this.plotOption[11] = "c3";
		this.plotOption[12] = "channels";
		this.plotOption[13] = "bases";
		this.plotOption[14] = "subs";
		this.plotOption[15] = "thickness";
		this.plotOption[16] = "buslist";
		this.plotOption[17] = "min";
		this.plotOption[18] = "3phLinestyle";
		this.plotOption[19] = "1phLinestyle";
		this.plotOption[20] = "phases";


		this.plotHelp = new String[NumPlotOptions];

		this.plotHelp[ 0] = "One of {Circuit | Monitor | Daisy | Zones | AutoAdd | "+ CRLF +
							"General (bus data) | Loadshape Tshape | Priceshape | Profile} " + CRLF +
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
							"Plot Loadshape object=myloadshape" +CRLF+
							"Plot Tshape object=mytemperatureshape" +CRLF+
							"Plot Priceshape object=mypriceshape" +CRLF+
							"Plot Profile" +CRLF+
							"Plot Profile Phases=Primary";
		this.plotHelp[ 1] = "One of {Voltage | Current | Power | Losses | Capacity | (Value Index for General, AutoAdd, or Circuit[w/ file]) }";
		this.plotHelp[ 2] = "Enter 0 or the value corresponding to max scale or line thickness in the circuit plots. "+
							"Power and Losses in kW.";
		this.plotHelp[ 3] = "Yes or No*. Places a marker on the circuit plot at the bus location. See Set Markercode under options.";
		this.plotHelp[ 4] = "Yes or No*. If yes, bus labels (abbreviated) are printed on the circuit plot.";
		this.plotHelp[ 5] = "Object to be plotted. One of [Meter Name (zones plot) | Monitor Name | LoadShape Name | File Name for General bus data | File Name Circuit branch data]";
		this.plotHelp[ 6] = "{Yes | No*} Shows loops on Circuit plot. Requires an EnergyMeter to be defined.";
		this.plotHelp[ 7] = "pu value for tri-color plot max range [default=.85 of max scale]. Corresponds to color C3.";
		this.plotHelp[ 8] = "pu value for tri-color plot mid range [default=.50 of max scale]. Corresponds to color C2.";
		this.plotHelp[ 9] = "RGB color number for color C1. This is the default color for circuit plots. Default is blue. See options in the Plot menu.";
		this.plotHelp[10] = "RGB color number for color C2. Used for gradients and tricolor plots such as circuit voltage.";
		this.plotHelp[11] = "RGB color number for color C3. Used for gradients and tricolor plots such a circuit voltage.";
		this.plotHelp[12] = "Array of channel numbers for monitor plot. Example" +CRLF+CRLF+
							"Plot Type=Monitor Object=MyMonitor Channels=[1, 3, 5]"+CRLF+CRLF+
							"Do \"Show Monitor MyMonitor\" to see channel definitions.";
		this.plotHelp[13] = "Array of base values for each channel for monitor plot. Useful for creating per unit plots. Default is 1.0 for each channel.  Set Base= property after defining channels."+CRLF+CRLF+
							"Plot Type=Monitor Object=MyMonitor Channels=[1, 3, 5] Bases=[2400 2400 2400]"+CRLF+CRLF+
							"Do \"Show Monitor MyMonitor\" to see channel range and definitions.";;
		this.plotHelp[14] = "{Yes | No*} Displays a marker at each transformer declared to be a substation. " +
							"At least one bus coordinate must be defined for the transformer. "+
							"See MarkTransformer and TransMarkerCode options.";
		this.plotHelp[15] = "Max thickness allowed for lines in circuit plots (default=7).";
		this.plotHelp[16] = "{Array of Bus Names | File=filename } This is for the Daisy plot. "+CRLF+CRLF+
							"Plot daisy power max=5000 dots=N Buslist=[file=MyBusList.txt]" +CRLF+CRLF+
							"A \"daisy\" marker is plotted for " +
							"each bus in the list. Bus name may be repeated, which results in multiple markers distributed around the bus location. " +
							"This gives the appearance of a daisy if there are several symbols at a bus. Not needed for plotting active generators.";
		this.plotHelp[17] = "Enter 0 (the default value) or the value corresponding to min value corresponding to color C1 in General bus data plots.";
		this.plotHelp[18] = "Line style for drawing 3-phase lines. A number in the range of [1..7].Default is 1 (solid). Use 3 for dotted; 2 for dashed.";
		this.plotHelp[19] = "Line style for drawing 1-phase lines. A number in the range of [1..7].Default is 1 (solid). Use 3 for dotted; 2 for dashed.";
		this.plotHelp[20] = "{default* | ALL | PRIMARY | LL3ph | LLALL | LLPRIMARY | (phase number)} For Profile plot. Specify which phases you want plotted." + CRLF+CRLF+
				"default = plot only nodes 1-3 at 3-phase buses (default)" +CRLF+
				"ALL = plot all nodes" +CRLF+
				"PRIMARY = plot all nodes -- primary only (voltage > 1kV)" +CRLF+
				"LL3ph = 3-ph buses only -- L-L voltages)" +CRLF+
				"LLALL = plot all nodes -- L-L voltages)" +CRLF+
				"LLPRIMARY = plot all nodes -- L-L voltages primary only)" +CRLF+
				"(phase number) = plot all nodes on selected phase"+CRLF+CRLF+
				"Note: Only nodes downline from an energy meter are plotted.";

	}

	/** Produce a plot with the DSSGraphX object. */
	public int doPlotCmd() {

		Parser parser = Parser.getInstance();
		DSSGlobals Globals = DSSGlobals.getInstance();

		double[] DblBuffer = new double[50];
		int NumChannels;

		int Result = 0;

		if (Globals.isNoFormsAllowed()) {
			Result =1;
			return Result;
		}

		if (DSSPlotImpl.getDSSPlotObj() == null)
			DSSPlotImpl.setDSSPlotObj(new DSSPlotImpl());

		DSSPlotImpl.getDSSPlotObj().setDefaults();

		/* Get next parameter on command line */
		int ParamPointer = 0;
		String ParamName = parser.getNextParam().toUpperCase();
		String Param = parser.makeString().toUpperCase();
		while (Param.length() > 0) {
			/* Interpret Parameter */
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = plotCommands.getCommand(ParamName);
			}

			DSSPlot plot = DSSPlotImpl.getDSSPlotObj();
			switch (ParamPointer) {
			case 0:
				switch (Param.charAt(0)) {
				case 'A':
					plot.setPlotType(PlotType.AutoAddLogPlot);
					plot.setObjectName(Globals.getCircuitName_() + "AutoAddLog.csv");
					plot.setValueIndex(2);
					break;
				case 'C':
					plot.setPlotType(PlotType.CircuitPlot);
					break;
				case 'G':
					plot.setPlotType(PlotType.GeneralDataPlot);
					break;
				case 'L':
					plot.setPlotType(PlotType.LoadShape);
					break;
				case 'M':
					plot.setPlotType(PlotType.MonitorPlot);
					break;
				case 'P':
					if (Utilities.compareTextShortest("pro", Param) == 0) {
						plot.setPlotType(PlotType.Profile);
					} else {
						plot.setPlotType(PlotType.PriceShape);
					}
					break;
				case 'T':
					plot.setPlotType(PlotType.TShape);
					break;
				case 'D':
					plot.setPlotType(PlotType.DaisyPlot);
					plot.getDaisyBusList().clear();
					break;
				case 'Z':
					plot.setPlotType(PlotType.MeterZones);
					break;
				}
				break;
			case 1:
				switch (Param.charAt(0)) {
				case 'V':
					plot.setQuantity(PlotQuantity.Voltage);
					break;
				case 'C':
					switch (Param.charAt(1)) {
					case 'A':
						plot.setQuantity(PlotQuantity.Capacity);
						break;
					case 'U':
						plot.setQuantity(PlotQuantity.Current);
						break;
					}
					break;
				case 'P':
					plot.setQuantity(PlotQuantity.Power);
					break;
				case 'L':
					plot.setQuantity(PlotQuantity.Losses);
					break;
				default:
					plot.setQuantity(PlotQuantity.None);
					plot.setValueIndex(parser.makeInteger());
					break;
				}
				break;
			case 2:
				plot.setMaxScale(parser.makeDouble());
				if (plot.getMaxScale() > 0.0)
					plot.setMaxScaleIsSpecified(true);  // Indicate the user wants a particular value
				break;
			case 3:
				plot.setDots(Utilities.interpretYesNo(Param));
				break;
			case 4:
				plot.setLabels(Utilities.interpretYesNo(Param));
				break;
			case 5:
				plot.setObjectName(parser.makeString());
				break;
			case 6:
				plot.setShowLoops(Utilities.interpretYesNo(Param));
				if (plot.isShowLoops())
					plot.setPlotType(PlotType.MeterZones);
				break;
			case 7:
				plot.setTriColorMax(parser.makeDouble());
				break;
			case 8:
				plot.setTriColorMid(parser.makeDouble());
				break;
			case 9:
				plot.setColor1(Utilities.interpretColor(Param));
				break;
			case 10:
				plot.setColor2(Utilities.interpretColor(Param));
				break;
			case 11:
				plot.setColor3(Utilities.interpretColor(Param));
				break;
			case 12:  // Channel definitions for Plot Monitor
				NumChannels = parser.parseAsVector(51, DblBuffer);  // allow up to 50 channels
				if (NumChannels > 0) {  // Else take the defaults
					plot.setChannels(new int[NumChannels]);
					for (int i = 0; i < NumChannels - 1; i++)  // TODO Check zero indexing
						plot.getChannels()[i] = (int) DblBuffer[i];
					plot.setBases(new double[NumChannels]);
					for (int i = 0; i < NumChannels - 1; i++)  // TODO Check zero indexing
						plot.getBases()[i] = 1.0;
				}
				break;
			case 13:
				NumChannels = parser.parseAsVector(51, DblBuffer);  // allow up to 50 channels
				if (NumChannels > 0) {
					plot.setBases(new double[NumChannels]);
					for (int i = 0; i < NumChannels - 1; i++)  // TODO Check zero indexing
						plot.getBases()[i] = DblBuffer[i];
				}
				break;
			case 14:
				plot.setShowSubs(Utilities.interpretYesNo(Param));
				break;
			case 15:
				plot.setMaxLineThickness(parser.makeInteger());
				break;
			case 16:
				Utilities.interpretStringListArray(Param, plot.getDaisyBusList());  // read in Bus list
				break;
			/*case 17:
				plot.setMinScale(parser.makeDouble());
				plot.setMinScaleIsSpecified(true);    // Indicate the user wants a particular value
				break;*/
			/*case 18:
				plot.setThreePhLineStyle = parser.makeInteger();
				break;*/
			/*case 19:
				plot.setSinglePhLineStyle = parser.makeInteger();
				break;*/
			/*case 20:  // Parse off phase(s) to plot
				plot.setPhasesToPlot(PROFILE3PH); // the default
				if (Utilities.compareTextShortest(Param, "default") == 0) {
					plot.setPhasesToPlot(PROFILE3PH);
				} else if (Utilities.compareTextShortest(Param, "all") == 0) {
					plot.setPhasesToPlot(PROFILEALL);
				} else if (Utilities.compareTextShortest(Param, "primary") == 0) {
					plot.setPhasesToPlot(PROFILEALLPRI) {
				} else if (Param.length() == 1) {
					plot.setPhasesToPlot(parser.makeInteger());
				}
				break;*/
			}

			ParamName = parser.getNextParam().toUpperCase();
			Param = parser.makeString().toUpperCase();
		}

		if (Globals.getActiveCircuit().isSolved())
			DSSPlotImpl.getDSSPlotObj().setQuantity(PlotQuantity.None);

		DSSPlotImpl.getDSSPlotObj().execute();  // makes a new plot based on these options

		return Result;
	}

}
