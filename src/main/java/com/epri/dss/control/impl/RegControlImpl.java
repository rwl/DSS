package com.epri.dss.control.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.RegControl;
import com.epri.dss.control.RegControlObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class RegControlImpl extends ControlClassImpl implements RegControl {

	private static RegControlObj ActiveRegControlObj;

	public RegControlImpl() {
		super();

		this.className   = "RegControl";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.REG_CONTROL;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = RegControl.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names

		propertyName[0] = "transformer";
		propertyName[1] = "winding";
		propertyName[2] = "vreg";
		propertyName[3] = "band";
		propertyName[4] = "ptratio";
		propertyName[5] = "CTprim";
		propertyName[6] = "R";
		propertyName[7] = "X";
		propertyName[8] = "bus";
		propertyName[9] = "delay";
		propertyName[10] = "reversible";
		propertyName[11] = "revvreg";
		propertyName[12] = "revband";
		propertyName[13] = "revR";
		propertyName[14] = "revX";
		propertyName[15] = "tapdelay";
		propertyName[16] = "debugtrace";
		propertyName[17] = "maxtapchange";
		propertyName[18] = "inversetime";
		propertyName[19] = "tapwinding";
		propertyName[20] = "vlimit";
		propertyName[21] = "PTphase";
		propertyName[22] = "revThreshold";
		propertyName[23] = "revDelay";
		propertyName[24] = "revNeutral";

		propertyHelp[0] = "Name of Transformer element to which the RegControl is connected. "+
				"Do not specify the full object name; \"Transformer\" is assumed for "  +
				"the object class.  Example:"+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Transformer=Xfmr1";
		propertyHelp[1] = "Number of the winding of the transformer element that the RegControl is monitoring. "+
				"1 or 2, typically.  Side Effect: Sets TAPWINDING property to the same winding.";
		propertyHelp[2] = "Voltage regulator setting, in VOLTS, for the winding being controlled.  Multiplying this "+
				"value times the ptratio should yield the voltage across the WINDING of the controlled transformer." +
				" Default is 120.0";
		propertyHelp[3] = "Bandwidth in VOLTS for the controlled bus (see help for ptratio property).  Default is 3.0";
		propertyHelp[4] = "Ratio of the PT that converts the controlled winding voltage to the regulator voltage. "+
				"Default is 60.  If the winding is Wye, the line-to-neutral voltage is used.  Else, the line-to-line " +
				"voltage is used.";
		propertyHelp[5] = "Rating, in Amperes, of the primary CT rating for converting the line amps to control amps."+
				"The typical default secondary ampere rating is 0.2 Amps (check with manufacturer specs).";
		propertyHelp[6] = "R setting on the line drop compensator in the regulator, expressed in VOLTS.";
		propertyHelp[7] = "X setting on the line drop compensator in the regulator, expressed in VOLTS.";
		propertyHelp[8] = "Name of a bus (busname.nodename) in the system to use as the controlled bus instead of the bus to which the "+
				"transformer winding is connected or the R and X line drop compensator settings.  Do not specify this "+
				"value if you wish to use the line drop compensator settings.  Default is null string. Assumes the base voltage for this "+
				"bus is the same as the transformer winding base specified above. " +
				"Note: This bus (1-phase) WILL BE CREATED by the regulator control upon SOLVE if not defined by some other device. " +
				"You can specify the node of the bus you wish to sample (defaults to 1). " +
				"If specified, the RegControl is redefined as a 1-phase device since only one voltage is used." ;
		propertyHelp[9] = "Time delay, in seconds, from when the voltage goes out of band to when the tap changing begins. " +
				"This is used to determine which regulator control will act first. Default is 15.  You may specify any "+
				"floating point number to achieve a model of whatever condition is necessary.";
		propertyHelp[10] = "{Yes | No*} Indicates whether or not the regulator can be switched to regulate in the reverse direction. Default is No." +
				"Typically applies only to line regulators and not to LTC on a substation transformer.";
		propertyHelp[11] = "Voltage setting in volts for operation in the reverse direction.";
		propertyHelp[12] = "Bandwidth for operating in the reverse direction.";
		propertyHelp[13] = "R line drop compensator setting for reverse direction.";
		propertyHelp[14] = "X line drop compensator setting for reverse direction.";
		propertyHelp[15] = "Delay in sec between tap changes. Default is 2. This is how long it takes between changes " +
				"after the first change.";
		propertyHelp[16] = "{Yes | No*}  Default is no.  Turn this on to capture the progress of the regulator model " +
				"for each control iteration.  Creates a separate file for each RegControl named \"REG_name.CSV\"." ;
		propertyHelp[17] = "Maximum allowable tap change per control iteration in STATIC control mode.  Default is 16. " + DSSGlobals.CRLF+ DSSGlobals.CRLF +
				"Set this to 1 to better approximate actual control action. " + DSSGlobals.CRLF + DSSGlobals.CRLF +
				"Set this to 0 to fix the tap in the current position.";
		propertyHelp[18] = "{Yes | No*} Default is no.  The time delay is adjusted inversely proportional to the amount the voltage is outside the band down to 10%.";
		propertyHelp[19] = "Winding containing the actual taps, if different than the WINDING property. Defaults to the same winding as specified by the WINDING property.";
		propertyHelp[20] = "Voltage Limit for bus to which regulated winding is connected (e.g. first customer). Default is 0.0. " +
				"Set to a value greater then zero to activate this function.";
		propertyHelp[21] = "For multi-phase transformers, the number of the phase being monitored or one of { MAX | MIN} for all phases. Default=1. " +
				"Must be less than or equal to the number of phases. Ignored for regulated bus.";
		propertyHelp[22] = "kW reverse power threshold for reversing the direction of the regulator. Default is 100.0 kw.";
		propertyHelp[23] = "Time Delay in seconds (s) for executing the reversing action once the threshold for reversing has been exceeded. Default is 60 s.";
		propertyHelp[24] = "{Yes | No*} Default is no. Set this to Yes if you want the regulator to go to neutral in the reverse direction.";


		activeProperty = RegControl.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new GenDispatcherObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveRegControlObj((RegControlObj) elementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveRegControlObj());

		int Result = 0;

		RegControlObj arc = getActiveRegControlObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < numProperties))
				arc.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +"."+ arc.getName() + "\"", 120);
				break;
			case 0:
				arc.setElementName("Transformer." + Param.toLowerCase());
				break;
			case 1:
				arc.setElementTerminal(parser.makeInteger());
				break;
			case 2:
				arc.setVreg(parser.makeDouble());
				break;
			case 3:
				arc.setBandwidth(parser.makeDouble());
				break;
			case 4:
				arc.setPTRatio(parser.makeDouble());
				break;
			case 5:
				arc.setCTRating(parser.makeDouble());
				break;
			case 6:
				arc.setR(parser.makeDouble());
				break;
			case 7:
				arc.setX(parser.makeDouble());
				break;
			case 8:
				arc.setRegulatedBus(Param);
				break;
			case 9:
				arc.setTimeDelay(parser.makeDouble());
				break;
			case 10:
				arc.setIsReversible(Utilities.interpretYesNo(Param));
				break;
			case 11:
				arc.setRevVreg(parser.makeDouble());
				break;
			case 12:
				arc.setRevBandwidth(parser.makeDouble());
				break;
			case 13:
				arc.setRevR(parser.makeDouble());
				break;
			case 14:
				arc.setRevX(parser.makeDouble());
				break;
			case 15:
				arc.setTapDelay(parser.makeDouble());
				break;
			case 16:
				arc.setDebugTrace(Utilities.interpretYesNo(Param));
				break;
			case 17:
				arc.setTapLimitPerChange(Math.max(0, parser.makeInteger()));
				break;
			case 18:
				arc.setInversetime(Utilities.interpretYesNo(Param));
				break;
			case 19:
				arc.setTapWinding(parser.makeInteger());
				break;
			case 20:
				arc.setVlimit(parser.makeDouble());
				if (arc.getVlimit() > 0.0) {
					arc.setVLimitActive(true);
				} else {
					arc.setVLimitActive(false);
				}
				break;
			case 21:
				if (Utilities.compareTextShortest(Param, "max") == 0) {
					arc.setPTphase(MAXPHASE);
				} else if (Utilities.compareTextShortest(Param, "min") == 0) {
					arc.setPTphase(MINPHASE);
				} else {
					arc.setPTphase(Math.max(1, parser.makeInteger()));
				}
				break;
			case 22:
				arc.setkWRevPowerThreshold(parser.makeDouble());
				break;
			case 23:
				arc.setRevDelay(parser.makeDouble());
				break;
			case 24:
				arc.setReverseNeutral(Utilities.interpretYesNo(Param));
				break;
			default:
				// inherited parameters
				classEdit(ActiveRegControlObj, ParamPointer - RegControl.NumPropsThisClass);
				break;
			}

			switch (ParamPointer) {
			case 1:
				arc.setTapWinding(arc.getElementTerminal());  // resets if property re-assigned
				arc.setPropertyValue(19, Param);
				break;
			case 16:
				if (arc.isDebugTrace()) {
					try {
						File TraceFile = new File(Globals.getDSSDataDirectory() + "REG_"+arc.getName()+".csv");
						FileWriter TraceStream = new FileWriter(TraceFile, false);
						BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);

						TraceBuffer.write("Hour, Sec, ControlIteration, Iterations, LoadMultiplier, Present Tap, Pending Change, Actual Change, Increment, Min Tap, Max Tap");
						TraceBuffer.newLine();
						TraceBuffer.close();
						TraceStream.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				break;
			case 23:
				arc.setRevPowerThreshold(arc.getkWRevPowerThreshold() * 1000.0);
				break;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		arc.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String RegControlName) {
		int Result = 0;

		/* See if we can find this RegControl name in the present collection */
		RegControlObj OtherRegControl = (RegControlObj) find(RegControlName);
		if (OtherRegControl != null) {

			RegControlObj arc = getActiveRegControlObj();

			arc.setNPhases(OtherRegControl.getNPhases());
			arc.setNConds(OtherRegControl.getNConds());  // force reallocation of terminal stuff

			arc.setElementName(OtherRegControl.getElementName());
			arc.setControlledElement(OtherRegControl.getControlledElement());  // pointer to target circuit element
			arc.setElementTerminal(OtherRegControl.getElementTerminal());
			arc.setVreg(OtherRegControl.getVreg());
			arc.setBandwidth(OtherRegControl.getBandwidth());
			arc.setPTRatio(OtherRegControl.getPTRatio());
			arc.setCTRating(OtherRegControl.getCTRating());
			arc.setR(OtherRegControl.getR());
			arc.setX(OtherRegControl.getX());
			arc.setRegulatedBus(OtherRegControl.getRegulatedBus());
			arc.setTimeDelay(OtherRegControl.getTimeDelay());
			arc.setIsReversible(OtherRegControl.isIsReversible());
			arc.setRevVreg(OtherRegControl.getRevVreg());
			arc.setRevBandwidth(OtherRegControl.getRevBandwidth());
			arc.setRevR(OtherRegControl.getRevR());
			arc.setRevX(OtherRegControl.getRevX());
			arc.setTapDelay(OtherRegControl.getTapDelay());
			arc.setTapWinding(OtherRegControl.getTapWinding());
			arc.setInversetime(OtherRegControl.isInversetime());
			arc.setTapLimitPerChange(OtherRegControl.getTapLimitPerChange());
	        arc.setTapLimitPerChange(OtherRegControl.getTapLimitPerChange());
			arc.setkWRevPowerThreshold(OtherRegControl.getkWRevPowerThreshold());
			arc.setRevPowerThreshold(OtherRegControl.getRevPowerThreshold());
			arc.setRevDelay(OtherRegControl.getRevDelay());
			arc.setReverseNeutral(OtherRegControl.isReverseNeutral());
			//arc.setDebugTrace(OtherRegControl.isDebugTrace();  always default to no

			arc.setPTphase(OtherRegControl.getPTphase());

			for (int i = 0; i < arc.getParentClass().getNumProperties(); i++)
				arc.setPropertyValue(i, OtherRegControl.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in RegControl makeLike: \"" + RegControlName + "\" not found.",121);
		}

		return Result;
	}

	public static void setActiveRegControlObj(RegControlObj activeRegControlObj) {
		ActiveRegControlObj = activeRegControlObj;
	}

	public static RegControlObj getActiveRegControlObj() {
		return ActiveRegControlObj;
	}

}
