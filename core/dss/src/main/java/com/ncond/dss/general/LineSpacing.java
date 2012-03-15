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
		String Str;

		DSS.auxParser.setCmdString(s);
		LineSpacingObj als = activeLineSpacingObj;

		for (int i = 0; i < als.getNWires(); i++) {
			DSS.auxParser.getNextParam();  // ignore any parameter name not expecting any
			Str = DSS.auxParser.makeString();
			if (Str.length() > 0)
				switch (which) {
				case X:
					als.getX()[i] = DSS.auxParser.makeDouble();
					break;
				case H:
					als.getY()[i] = DSS.auxParser.makeDouble();
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
		DSS.activeDSSObject = activeLineSpacingObj;

		LineSpacingObj als = activeLineSpacingObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				als.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getClassName() +"."+ als.getName() + "\"", 10101);
				break;
			case 0:
				als.setNWires(parser.makeInteger());  // use property value to force reallocations
				break;
			case 1:
				als.setNPhases(parser.makeInteger());
				break;
			case 2:
				interpretArray(param, SpcParmChoice.X);
				break;
			case 3:
				interpretArray(param, SpcParmChoice.H);
				break;
			case 4:
				als.setUnits(LineUnits.interpretUnitsCode(param));
				break;
			default:
				// inherited parameters
				classEdit(activeLineSpacingObj, paramPointer - LineSpacing.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 0:
				als.setDataChanged(true);
				break;
			case 1:
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
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		return result;
	}

	@Override
	protected int makeLike(String lineName) {
		int i;
		int result = 0;
		LineSpacingObj als;

		/* See if we can find this line code in the present collection */
		LineSpacingObj otherLineSpacing = (LineSpacingObj) find(lineName);

		if (otherLineSpacing != null) {
			als = activeLineSpacingObj;

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
			DSS.doSimpleMsg("Error in LineSpacing makeLike: \"" + lineName + "\" not found.", 102);
		}

		return result;
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

		DSS.doSimpleMsg("LineSpacing: \"" + value + "\" not found.", 103);
	}

}
