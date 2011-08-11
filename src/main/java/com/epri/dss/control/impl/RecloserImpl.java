package com.epri.dss.control.impl;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.Recloser;
import com.epri.dss.control.RecloserObj;
import com.epri.dss.general.TCC_CurveObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class RecloserImpl extends ControlClassImpl implements Recloser {

	private static RecloserObj ActiveRecloserObj;

	private static DSSClass TCC_CurveClass = DSSClassDefs.getDSSClass("TCC_Curve");

	public RecloserImpl() {
		super();

		this.Class_Name = "Recloser";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.RECLOSER_CONTROL;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	public static TCC_CurveObj getTCC_Curve(String CurveName) {

		TCC_CurveObj Result = (TCC_CurveObj) TCC_CurveClass.find(CurveName);

		if (Result == null)
			DSSGlobals.getInstance().doSimpleMsg("TCC Curve object: \""+CurveName+"\" not found.", 388);

		return Result;
	}

	protected void defineProperties() {

		NumProperties = NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();


		// define property names

		PropertyName[0]  = "MonitoredObj";
		PropertyName[1]  = "MonitoredTerm";
		PropertyName[2]  = "SwitchedObj";
		PropertyName[3]  = "SwitchedTerm";
		PropertyName[4]  = "NumFast";
		PropertyName[5]  = "PhaseFast";
		PropertyName[6]  = "PhaseDelayed";
		PropertyName[7]  = "GroundFast";
		PropertyName[8]  = "GroundDelayed";
		PropertyName[9]  = "PhaseTrip";
		PropertyName[10] = "GroundTrip";
		PropertyName[11] = "PhaseInst";
		PropertyName[12] = "GroundInst";
		PropertyName[13] = "Reset";
		PropertyName[14] = "Shots";
		PropertyName[15] = "RecloseIntervals";
		PropertyName[16] = "Delay";
		PropertyName[17] = "Action";
		PropertyName[18] = "TDPhFast";
		PropertyName[19] = "TDGrFast";
		PropertyName[20] = "TDPhDelayed";
		PropertyName[21] = "TDGrDelayed";

		PropertyHelp[0] = "Full object name of the circuit element, typically a line, transformer, load, or generator, "+
				"to which the Recloser's PT and/or CT are connected." +
				" This is the \"monitored\" element. " +
				"There is no default; must be specified.";
		PropertyHelp[1] = "Number of the terminal of the circuit element to which the Recloser is connected. "+
				"1 or 2, typically.  Default is 1.";
		PropertyHelp[2] = "Name of circuit element switch that the Recloser controls. "+
				"Specify the full object name." +
				"Defaults to the same as the Monitored element. "+
				"This is the \"controlled\" element.";
		PropertyHelp[3] = "Number of the terminal of the controlled element in which the switch is controlled by the Recloser. "+
				"1 or 2, typically.  Default is 1.";
		PropertyHelp[4] = "Number of Fast (fuse saving) operations.  Default is 1. (See \"Shots\")";
		PropertyHelp[5] = "Name of the TCC Curve object that determines the Phase Fast trip.  Must have been previously defined as a TCC_Curve object."+
				" Default is \"A\". "+
				"Multiplying the current values in the curve by the \"phasetrip\" value gives the actual current.";
		PropertyHelp[6] = "Name of the TCC Curve object that determines the Phase Delayed trip.  Must have been previously defined as a TCC_Curve object."+
				" Default is \"D\"."+
				"Multiplying the current values in the curve by the \"phasetrip\" value gives the actual current.";
		PropertyHelp[7] = "Name of the TCC Curve object that determines the Ground Fast trip.  Must have been previously defined as a TCC_Curve object."+
				" Default is none (ignored). "+
				"Multiplying the current values in the curve by the \"groundtrip\" value gives the actual current.";
		PropertyHelp[8] = "Name of the TCC Curve object that determines the Ground Delayed trip.  Must have been previously defined as a TCC_Curve object."+
				" Default is none (ignored)."+
				"Multiplying the current values in the curve by the \"groundtrip\" value gives the actual current.";
		PropertyHelp[9] = "Multiplier or actual phase amps for the phase TCC curve.  Defaults to 1.0.";
		PropertyHelp[10] = "Multiplier or actual ground amps (3I0) for the ground TCC curve.  Defaults to 1.0.";
		PropertyHelp[11] = "Actual amps for instantaneous phase trip which is assumed to happen in 0.01 sec + Delay Time. Default is 0.0, which signifies no inst trip. ";
		PropertyHelp[12] = "Actual amps for instantaneous ground trip which is assumed to happen in 0.01 sec + Delay Time.Default is 0.0, which signifies no inst trip.";
		PropertyHelp[13] = "Reset time in sec for Recloser.  Default is 15. ";
		PropertyHelp[14] = "Total Number of fast and delayed shots to lockout.  Default is 4. This is one more than the number of reclose intervals.";
		PropertyHelp[15] = "Array of reclose intervals.  Default for Recloser is (0.5, 2.0, 2.0) seconds. " +
				"A locked out Recloser must be closed manually (action=close).";
		PropertyHelp[16] = "Fixed delay time (sec) added to Recloser trip time. Default is 0.0. Used to represent breaker time or any other delay." ;
		PropertyHelp[17] = "{Trip/Open | Close}  Action that overrides the Recloser control. Simulates manual control on recloser " +
				"\"Trip\" or \"Open\" causes the controlled element to open and lock out. " +
				"\"Close\" causes the controlled element to close and the Recloser to reset to its first operation.";
		PropertyHelp[18] = "Time dial for Phase Fast trip curve. Multiplier on time axis of specified curve. Default=1.0.";
		PropertyHelp[19] = "Time dial for Ground Fast trip curve. Multiplier on time axis of specified curve. Default=1.0.";
		PropertyHelp[20] = "Time dial for Phase Delayed trip curve. Multiplier on time axis of specified curve. Default=1.0.";
		PropertyHelp[21] = "Time dial for Ground Delayed trip curve. Multiplier on time axis of specified curve. Default=1.0.";

		ActiveProperty = Recloser.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new RecloserObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveRecloserObj((RecloserObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveRecloserObj());

		int Result = 0;

		RecloserObj ar = getActiveRecloserObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer <= NumProperties))
				ar.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +"."+ ar.getName() +"\"", 390);
				break;
			case 0:
				ar.setMonitoredElementName(Param.toLowerCase());
				break;
			case 1:
				ar.setMonitoredElementTerminal(parser.makeInteger());
				break;
			case 2:
				ar.setElementName(Param.toLowerCase());
				break;
			case 3:
				ar.setElementTerminal(parser.makeInteger());
				break;
			case 4:
				ar.setNumFast(parser.makeInteger());
				break;
			case 5:
				ar.setPhaseFast(getTCC_Curve(Param));
				break;
			case 6:
				ar.setPhaseDelayed(getTCC_Curve(Param));
				break;
			case 7:
				ar.setGroundFast(getTCC_Curve(Param));
				break;
			case 8:
				ar.setGroundDelayed(getTCC_Curve(Param));
				break;
			case 9:
				ar.setPhaseTrip(parser.makeDouble());
				break;
			case 10:
				ar.setGroundTrip(parser.makeDouble());
				break;
			case 11:
				ar.setPhaseInst(parser.makeDouble());
				break;
			case 12:
				ar.setGroundInst(parser.makeDouble());
				break;
			case 13:
				ar.setResetTime(parser.makeDouble());
				break;
			case 14:
				ar.setNumReclose(parser.makeInteger() - 1);  // one less than number of shots
				break;
			case 15:
				ar.setNumReclose(parser.parseAsVector(4, ar.getRecloseIntervals()));  // max of 4 allowed
				break;
			case 16:
				ar.setDelayTime(parser.makeDouble());
				break;
			case 17:
				ar.interpretRecloserAction(Param);
				break;
			case 18:
				ar.setTDPhFast(parser.makeDouble());
				break;
			case 19:
				ar.setTDGrFast(parser.makeDouble());
				break;
			case 20:
				ar.setTDPhDelayed(parser.makeDouble());
				break;
			case 21:
				ar.setTDGrDelayed(parser.makeDouble());
				break;
			default:
				// inherited parameters
				classEdit(ActiveRecloserObj, ParamPointer - Recloser.NumPropsThisClass);  // TODO Check name-static member conflict
				break;
			}

			switch (ParamPointer) {
			/* Default the controlled element to the monitored element */
			case 1:
				ar.setElementName(ar.getMonitoredElementName());
				break;
			case 2:
				ar.setElementTerminal(ar.getMonitoredElementTerminal());
				break;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		ar.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String RecloserName) {
		int i, Result = 0;

		/* See if we can find this Recloser name in the present collection */
		RecloserObj OtherRecloser = (RecloserObj) find(RecloserName);
		if (OtherRecloser != null) {
			RecloserObj ar = getActiveRecloserObj();

			ar.setNPhases(OtherRecloser.getNPhases());
			ar.setNConds(OtherRecloser.getNConds());  // force reallocation of terminal stuff

			ar.setElementName(OtherRecloser.getElementName());
			ar.setElementTerminal(OtherRecloser.getElementTerminal());
			ar.setControlledElement(OtherRecloser.getControlledElement());  // pointer to target circuit element

			ar.setMonitoredElement(OtherRecloser.getMonitoredElement());  // pointer to target circuit element
			ar.setMonitoredElementName(OtherRecloser.getMonitoredElementName());  // pointer to target circuit element
			ar.setMonitoredElementTerminal(OtherRecloser.getMonitoredElementTerminal());  // pointer to target circuit element

			ar.setPhaseDelayed(OtherRecloser.getPhaseDelayed());
			ar.setGroundDelayed(OtherRecloser.getGroundDelayed());
			ar.setPhaseFast(OtherRecloser.getPhaseFast());
			ar.setGroundFast(OtherRecloser.getGroundFast());
			ar.setPhaseTrip(OtherRecloser.getPhaseTrip());
			ar.setGroundTrip(OtherRecloser.getGroundTrip());
			ar.setPhaseInst(OtherRecloser.getPhaseInst());
			ar.setGroundInst(OtherRecloser.getGroundInst());
			ar.setResetTime(OtherRecloser.getResetTime());
			ar.setNumReclose(OtherRecloser.getNumReclose());
			ar.setNumFast(OtherRecloser.getNumFast());

			ar.setRecloseIntervals( (double[]) Utilities.resizeArray(ar.getRecloseIntervals(), 4) );  // always make a max of 4
			for (i = 0; i < ar.getNumReclose(); i++)
				ar.getRecloseIntervals()[i] = OtherRecloser.getRecloseIntervals()[i];

			ar.setLockedOut(OtherRecloser.isLockedOut());

			ar.setPresentState(OtherRecloser.getPresentState());
			ar.setCondOffset(OtherRecloser.getCondOffset());

			for (i = 0; i < ar.getParentClass().getNumProperties(); i++)
				ar.setPropertyValue(i, OtherRecloser.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Recloser makeLike: \"" + RecloserName + "\" not found.", 391);
		}

		return Result;
	}

	public static RecloserObj getActiveRecloserObj() {
		return ActiveRecloserObj;
	}

	public static void setActiveRecloserObj(RecloserObj activeRecloserObj) {
		ActiveRecloserObj = activeRecloserObj;
	}

}
