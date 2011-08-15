package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.general.LineSpacing;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;
import com.epri.dss.shared.impl.LineUnits;

public class LineSpacingImpl extends DSSClassImpl implements LineSpacing {

	public static LineSpacingObj activeLineSpacingObj;

	private enum SpcParmChoice {X, H};

	public LineSpacingImpl() {
		super();

		className = "LineSpacing";
		classType = DSSClassDefs.DSS_OBJECT;
		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();


		propertyName[0]  = "nconds";
		propertyName[1]  = "nphases";
		propertyName[2]  = "x";
		propertyName[3]  = "h";
		propertyName[4]  = "units";


		propertyHelp[0] = "Number of wires in this geometry. Default is 3. Triggers memory allocations. Define first!";
		propertyHelp[1] = "Number of retained phase conductors. If less than the number of wires, list the retained phase coordinates first.";
		propertyHelp[2] = "Array of wire X coordinates.";
		propertyHelp[3] = "Array of wire Heights.";
		propertyHelp[4] = "Units for x and h: {mi|kft|km|m|Ft|in|cm } Initial default is \"ft\", but defaults to last unit defined";

		activeProperty = LineSpacing.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSSGlobals globals = DSSGlobals.getInstance();

		globals.setActiveDSSObject(new LineSpacingObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	private void interpretArray(String s, SpcParmChoice which) {
		DSSGlobals globals = DSSGlobals.getInstance();
		String Str;

		globals.getAuxParser().setCmdString(s);
		LineSpacingObj als = activeLineSpacingObj;

		for (int i = 0; i < als.getNWires(); i++) {
			globals.getAuxParser().getNextParam();  // ignore any parameter name not expecting any
			Str = globals.getAuxParser().makeString();
			if (Str.length() > 0)
				switch (which) {
				case X:
					als.getX()[i] = globals.getAuxParser().makeDouble();
					break;
				case H:
					als.getY()[i] = globals.getAuxParser().makeDouble();
					break;
				}
		}
	}

	@Override
	public int edit() {
		int result = 0;
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeLineSpacingObj = (LineSpacingObj) elementList.getActive();
		DSSGlobals.getInstance().setActiveDSSObject(activeLineSpacingObj);

		LineSpacingObj als = activeLineSpacingObj;

		int paramPointer = 0;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);

				if ((paramPointer > 0) && (paramPointer <= numProperties))
					als.setPropertyValue(paramPointer, param);

				switch (paramPointer) {
				case 0:  // TODO Check zero based indexing
					DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ als.getName() + "\"", 10101);
					break;
				case 1:
					als.setNWires(parser.makeInteger());  // use property value to force reallocations
					break;
				case 2:
					als.setNPhases(parser.makeInteger());
					break;
				case 3:
					interpretArray(param, SpcParmChoice.X);
					break;
				case 4:
					interpretArray(param, SpcParmChoice.H);
					break;
				case 5:
					als.setUnits(LineUnits.getUnitsCode(param));
					break;
				default:
					// inherited parameters
					classEdit(activeLineSpacingObj, paramPointer - LineSpacing.NumPropsThisClass);
					break;
				}

				switch (paramPointer) {
				case 1:  // TODO Check zero based indexing
					als.setDataChanged(true);
					break;
				case 2:
					als.setDataChanged(true);
					break;
				case 3:
					als.setDataChanged(true);
					break;
				case 4:
					als.setDataChanged(true);
					break;
				case 5:
					als.setDataChanged(true);
					break;
				}

				paramName = parser.getNextParam();
				param = parser.makeString();
			}
		}

		return result;
	}

	@Override
	protected int makeLike(String lineName) {
		int i;
		int result = 0;

		/* See if we can find this line code in the present collection */
		LineSpacingObj otherLineSpacing = (LineSpacingObj) find(lineName);
		if (otherLineSpacing != null) {
			LineSpacingObj als = activeLineSpacingObj;

			als.setNWires(otherLineSpacing.getNWires());  // allocates
			als.setNPhases(otherLineSpacing.getNPhases());
			for (i = 0; i < als.getNConds(); i++)
				als.getX()[i] = otherLineSpacing.getX()[i];
			for (i = 0; i < als.getNConds(); i++)
				als.getY()[i] = otherLineSpacing.getY()[i];
			als.setUnits(otherLineSpacing.getUnits());
			als.setDataChanged(true);
			for (i = 0; i < als.getParentClass().getNumProperties(); i++)
				als.setPropertyValue(i, otherLineSpacing.getPropertyValue(i));
			result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in LineSpacing makeLike: \"" + lineName + "\" not found.", 102);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement LineSpacing.init()", -1);
		return 0;
	}

	/**
	 * Returns active line code string.
	 */
	public String getCode() {
		LineSpacingObj active = (LineSpacingObj) elementList.getActive();
		return active.getName();
	}

	/**
	 * Sets the active LineSpacing.
	 */
	public void setCode(String value) {
		LineSpacingObj pSpacing;

		activeLineSpacingObj = null;
		for (int i = 0; i < elementList.size(); i++) {
			pSpacing = (LineSpacingObj) elementList.get(i);

			if (pSpacing.getName().equalsIgnoreCase(value)) {
				activeLineSpacingObj = pSpacing;
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("LineSpacing: \"" + value + "\" not found.", 103);
	}

}
