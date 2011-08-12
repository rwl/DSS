package com.epri.dss.delivery.impl;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.control.impl.ControlClassImpl;
import com.epri.dss.delivery.Fuse;
import com.epri.dss.delivery.FuseObj;
import com.epri.dss.general.TCC_CurveObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class FuseImpl extends ControlClassImpl implements Fuse {

	private static FuseObj ActiveFuseObj;

	private static DSSClass TCC_CurveClass;

	public FuseImpl() {
		super();

		this.className = "Fuse";
		this.DSSClassType = getDSSClassType() + DSSClassDefs.FUSE_CONTROL;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);

		setTCC_CurveClass( DSSClassDefs.getDSSClass("TCC_Curve") );
	}

	/**
	 * General module function
	 */
	public static TCC_CurveObj getTccCurve(String CurveName) {

		TCC_CurveObj Result = (TCC_CurveObj) TCC_CurveClass.find(CurveName);

		if (Result == null)
			DSSGlobals.getInstance().doSimpleMsg("TCC Curve object: \"" + CurveName + "\" not found.", 401);

		return Result;
	}

	protected void defineProperties() {

		numProperties = Fuse.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();


		// define property names
		propertyName[0]  = "MonitoredObj";
		propertyName[1]  = "MonitoredTerm";
		propertyName[2]  = "SwitchedObj";
		propertyName[3]  = "SwitchedTerm";
		propertyName[4]  = "FuseCurve";
		propertyName[5] = "RatedCurrent";
		propertyName[6] = "Delay";
		propertyName[7] = "Action";

		propertyHelp[0] = "Full object name of the circuit element, typically a line, transformer, load, or generator, "+
							"to which the Fuse is connected." +
							" This is the \"monitored\" element. " +
							"There is no default; must be specified.";
		propertyHelp[1] = "Number of the terminal of the circuit element to which the Fuse is connected. "+
							"1 or 2, typically.  Default is 1.";
		propertyHelp[2] = "Name of circuit element switch that the Fuse controls. "+
							"Specify the full object name." +
							"Defaults to the same as the Monitored element. "+
							"This is the \"controlled\" element.";
		propertyHelp[3] = "Number of the terminal of the controlled element in which the switch is controlled by the Fuse. "+
							"1 or 2, typically.  Default is 1.  Assumes all phases of the element have a fuse of this type.";
		propertyHelp[4] = "Name of the TCC Curve object that determines the fuse blowing.  Must have been previously defined as a TCC_Curve object."+
							" Default is \"Tlink\". "+
							"Multiplying the current values in the curve by the \"RatedCurrent\" value gives the actual current.";
		propertyHelp[5] = "Multiplier or actual phase amps for the phase TCC curve.  Defaults to 1.0.";
		propertyHelp[6] = "Fixed delay time (sec) added to Fuse blowing time determined from the TCC curve. Default is 0.0. Used to represent fuse clearing time or any other delay." ;
		propertyHelp[7] = "{Trip/Open | Close}  Action that overrides the Fuse control. Simulates manual control on Fuse " +
							"\"Trip\" or \"Open\" causes the controlled element to open and lock out. " +
							"\"Close\" causes the controlled element to close and the Fuse to reset.";

		activeProperty  = Fuse.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new FuseObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveFuseObj((FuseObj) elementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveFuseObj());

		int Result = 0;

		FuseObj af = getActiveFuseObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < numProperties))  // TODO Check zero based indexing
				af.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() + "." + getName() + "\"", 402);
				break;
			case 0:
				af.setMonitoredElementName(Param.toLowerCase());
				break;
			case 1:
				af.setMonitoredElementTerminal(parser.makeInteger());
				break;
			case 2:
				af.setElementName(Param.toLowerCase());
				break;
			case 3:
				af.setElementTerminal(parser.makeInteger());
				break;
			case 4:
				af.setFuseCurve(getTccCurve(Param));
				break;
			case 5:
				af.setRatedCurrent(parser.makeDouble());
				break;
			case 6:
				af.setDelayTime(parser.makeDouble());
				break;
			case 7:
				af.interpretFuseAction(Param);
				break;

			default:
				// inherited parameters
				classEdit(getActiveFuseObj(), ParamPointer - Fuse.NumPropsThisClass);
				break;
			}

			switch (ParamPointer) {
			/* Default the controlled element to the monitored element */
			case 0:
				af.setElementName(af.getMonitoredElementName());
				break;
			case 1:
				af.setElementTerminal(af.getMonitoredElementTerminal());
				break;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		af.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String FuseName) {

		int Result = 0;
		/* See if we can find this Fuse name in the present collection */
		FuseObj OtherFuse = (FuseObj) find(FuseName);
		if (OtherFuse != null) {
			FuseObj af = getActiveFuseObj();

			af.setNPhases(OtherFuse.getNPhases());
			af.setNConds(OtherFuse.getNConds()); // force reallocation of terminal stuff

			af.setElementName(OtherFuse.getElementName());
			af.setElementTerminal(OtherFuse.getElementTerminal());
			af.setControlledElement(OtherFuse.getControlledElement());  // target circuit element

			af.setMonitoredElement(OtherFuse.getMonitoredElement());  // target circuit element
			af.setMonitoredElementName(OtherFuse.getMonitoredElementName());  // target circuit element
			af.setMonitoredElementTerminal(OtherFuse.getMonitoredElementTerminal());  // target circuit element

			af.setFuseCurve(OtherFuse.getFuseCurve());
			af.setRatedCurrent(OtherFuse.getRatedCurrent());

			// can't copy action handles
			af.setPresentState(OtherFuse.getPresentState());
			af.setCondOffset(OtherFuse.getCondOffset());

			for (int i = 0; i < af.getParentClass().getNumProperties(); i++)
				af.setPropertyValue(i, OtherFuse.getPropertyValue(i));

		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Fuse makeLike: \"" + FuseName + "\" not found.", 403);
		}

		return Result;
	}

	public static FuseObj getActiveFuseObj() {
		return ActiveFuseObj;
	}

	public static void setActiveFuseObj(FuseObj activeFuseObj) {
		ActiveFuseObj = activeFuseObj;
	}

	public static DSSClass getTCC_CurveClass() {
		return TCC_CurveClass;
	}

	public static void setTCC_CurveClass(DSSClass tCC_CurveClass) {
		TCC_CurveClass = tCC_CurveClass;
	}

}
