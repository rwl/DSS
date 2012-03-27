/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.LineUnits;

public class LineSpacing extends DSSClass {

	public static final int NumPropsThisClass = 5;

	public static LineSpacingObj activeLineSpacingObj;

	private enum SpcParmChoice {X, H};

	public LineSpacing() {
		super();

		className = "LineSpacing";
		classType = DSSClassDefs.DSS_OBJECT;
		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
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
		DSS.activeDSSObject = new LineSpacingObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	private void interpretArray(String s, SpcParmChoice which) {
		String ss;

		DSS.auxParser.setCommand(s);
		LineSpacingObj elem = activeLineSpacingObj;

		for (int i = 0; i < elem.getNWires(); i++) {
			DSS.auxParser.getNextParam();  // ignore any parameter name not expecting any
			ss = DSS.auxParser.stringValue();
			if (ss.length() > 0) {
				switch (which) {
				case X:
					elem.setXCoord(i, DSS.auxParser.doubleValue());
					break;
				case H:
					elem.setYCoord(i, DSS.auxParser.doubleValue());
					break;
				}
			}
		}
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeLineSpacingObj = (LineSpacingObj) elementList.getActive();
		DSS.activeDSSObject = activeLineSpacingObj;

		LineSpacingObj elem = activeLineSpacingObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.stringValue();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() + "." + elem.getName() + "\"", 10101);
				break;
			case 0:
				elem.setNWires(parser.integerValue());  // forces reallocations
				break;
			case 1:
				elem.setNPhases(parser.integerValue());
				break;
			case 2:
				interpretArray(param, SpcParmChoice.X);
				break;
			case 3:
				interpretArray(param, SpcParmChoice.H);
				break;
			case 4:
				elem.setUnits(LineUnits.interpretUnitsCode(param));
				break;
			default:
				// inherited parameters
				classEdit(activeLineSpacingObj, paramPointer - LineSpacing.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				elem.setDataChanged(true);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		return 0;
	}

	@Override
	protected int makeLike(String lineName) {
		int i;
		int success = 0;
		LineSpacingObj elem, other;

		/* See if we can find this line code in the present collection */
		other = (LineSpacingObj) find(lineName);

		if (other != null) {
			elem = activeLineSpacingObj;

			elem.setNWires(other.getNWires());  // allocates
			elem.setNPhases(other.getNPhases());

			for (i = 0; i < elem.getNConds(); i++)
				elem.setXCoord(i, other.getXCoord(i));

			for (i = 0; i < elem.getNConds(); i++)
				elem.setYCoord(i, other.getYCoord(i));

			elem.setUnits(other.getUnits());
			elem.setDataChanged(true);

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
			success = 1;
		} else {
			DSS.doSimpleMsg("Error in LineSpacing makeLike: \"" +
					lineName + "\" not found.", 102);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement LineSpacing.init()", -1);
		return 0;
	}

	/**
	 * Returns active line code string.
	 */
	public String getCode() {
		return ((LineSpacingObj) elementList.getActive()).getName();
	}

	/**
	 * Sets the active LineSpacing.
	 */
	public void setCode(String value) {
		LineSpacingObj spacing;

		activeLineSpacingObj = null;

		for (int i = 0; i < elementList.size(); i++) {
			spacing = (LineSpacingObj) elementList.get(i);

			if (spacing.getName().equalsIgnoreCase(value)) {
				activeLineSpacingObj = spacing;
				return;
			}
		}

		DSS.doSimpleMsg("LineSpacing: \"" + value + "\" not found", 103);
	}

}
