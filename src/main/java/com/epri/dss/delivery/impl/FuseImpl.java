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

	public static FuseObj activeFuseObj;

	private static DSSClass TCC_CurveClass;

	public FuseImpl() {
		super();

		className = "Fuse";
		classType = getDSSClassType() + DSSClassDefs.FUSE_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);

		TCC_CurveClass = DSSClassDefs.getDSSClass("TCC_Curve");
	}

	/**
	 * General module function
	 */
	public static TCC_CurveObj getTccCurve(String curveName) {

		TCC_CurveObj result = (TCC_CurveObj) TCC_CurveClass.find(curveName);

		if (result == null)
			DSSGlobals.getInstance().doSimpleMsg("TCC Curve object: \"" + curveName + "\" not found.", 401);

		return result;
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
	public int newObject(String objName) {
		DSSGlobals globals = DSSGlobals.getInstance();

		globals.getActiveCircuit().setActiveCktElement(new FuseObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeFuseObj = (FuseObj) elementList.getActive();
		globals.getActiveCircuit().setActiveCktElement(activeFuseObj);

		int result = 0;

		FuseObj af = activeFuseObj;

		int paramPointer = 0;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if ((paramPointer >= 0) && (paramPointer < numProperties))  // TODO Check zero based indexing
				af.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				globals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() + "." + getName() + "\"", 402);
				break;
			case 0:
				af.setMonitoredElementName(param.toLowerCase());
				break;
			case 1:
				af.setMonitoredElementTerminal(parser.makeInteger());
				break;
			case 2:
				af.setElementName(param.toLowerCase());
				break;
			case 3:
				af.setElementTerminal(parser.makeInteger());
				break;
			case 4:
				af.setFuseCurve(getTccCurve(param));
				break;
			case 5:
				af.setRatedCurrent(parser.makeDouble());
				break;
			case 6:
				af.setDelayTime(parser.makeDouble());
				break;
			case 7:
				af.interpretFuseAction(param);
				break;

			default:
				// inherited parameters
				classEdit(activeFuseObj, paramPointer - Fuse.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			/* Default the controlled element to the monitored element */
			case 0:
				af.setElementName(af.getMonitoredElementName());
				break;
			case 1:
				af.setElementTerminal(af.getMonitoredElementTerminal());
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		af.recalcElementData();

		return result;
	}

	@Override
	protected int makeLike(String fuseName) {

		int result = 0;
		/* See if we can find this Fuse name in the present collection */
		FuseObj otherFuse = (FuseObj) find(fuseName);
		if (otherFuse != null) {
			FuseObj af = activeFuseObj;

			af.setNPhases(otherFuse.getNPhases());
			af.setNConds(otherFuse.getNConds()); // force reallocation of terminal stuff

			af.setElementName(otherFuse.getElementName());
			af.setElementTerminal(otherFuse.getElementTerminal());
			af.setControlledElement(otherFuse.getControlledElement());  // target circuit element

			af.setMonitoredElement(otherFuse.getMonitoredElement());  // target circuit element
			af.setMonitoredElementName(otherFuse.getMonitoredElementName());  // target circuit element
			af.setMonitoredElementTerminal(otherFuse.getMonitoredElementTerminal());  // target circuit element

			af.setFuseCurve(otherFuse.getFuseCurve());
			af.setRatedCurrent(otherFuse.getRatedCurrent());

			// can't copy action handles
			af.setPresentState(otherFuse.getPresentState());
			af.setCondOffset(otherFuse.getCondOffset());

			for (int i = 0; i < af.getParentClass().getNumProperties(); i++)
				af.setPropertyValue(i, otherFuse.getPropertyValue(i));

		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Fuse makeLike: \"" + fuseName + "\" not found.", 403);
		}

		return result;
	}

}
