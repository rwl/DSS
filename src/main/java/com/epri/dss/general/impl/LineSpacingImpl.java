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

	private static LineSpacingObj ActiveLineSpacingObj;

	private enum SpcParmChoice {X, H};

	public LineSpacingImpl() {
		super();

		this.Class_Name    = "LineSpacing";
		this.DSSClassType  = DSSClassDefs.DSS_OBJECT;
		this.ActiveElement = -1;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		NumProperties = NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();


		PropertyName[0]  = "nconds";
		PropertyName[1]  = "nphases";
		PropertyName[2]  = "x";
		PropertyName[3]  = "h";
		PropertyName[4]  = "units";


		PropertyHelp[0] = "Number of wires in this geometry. Default is 3. Triggers memory allocations. Define first!";
		PropertyHelp[1] = "Number of retained phase conductors. If less than the number of wires, list the retained phase coordinates first.";
		PropertyHelp[2] = "Array of wire X coordinates.";
		PropertyHelp[3] = "Array of wire Heights.";
		PropertyHelp[4] = "Units for x and h: {mi|kft|km|m|Ft|in|cm } Initial default is \"ft\", but defaults to last unit defined";

		ActiveProperty = LineSpacing.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.setActiveDSSObject(new LineSpacingObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void interpretArray(String S, SpcParmChoice which) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		String Str;

		Globals.getAuxParser().setCmdString(S);
		LineSpacingObj als = getActiveLineSpacingObj();

		for (int i = 0; i < als.getNWires(); i++) {
			Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
			Str = Globals.getAuxParser().makeString();
			if (Str.length() > 0)
				switch (which) {
				case X:
					als.getX()[i] = Globals.getAuxParser().makeDouble();
					break;
				case H:
					als.getY()[i] = Globals.getAuxParser().makeDouble();
					break;
				}
		}
	}

	@Override
	public int edit() {
		int Result = 0;
		Parser parser = Parser.getInstance();

		// continue parsing with contents of Parser
		setActiveLineSpacingObj((LineSpacingObj) ElementList.getActive());
		DSSGlobals.getInstance().setActiveDSSObject(getActiveLineSpacingObj());

		LineSpacingObj als = getActiveLineSpacingObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);

				if ((ParamPointer > 0) && (ParamPointer <= NumProperties))
					als.setPropertyValue(ParamPointer, Param);

				switch (ParamPointer) {
				case 0:  // TODO Check zero based indexing
					DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ als.getName() + "\"", 10101);
					break;
				case 1:
					als.setNWires(parser.makeInteger());  // Use property value to force reallocations
					break;
				case 2:
					als.setNPhases(parser.makeInteger());
					break;
				case 3:
					interpretArray(Param, SpcParmChoice.X);
					break;
				case 4:
					interpretArray(Param, SpcParmChoice.H);
					break;
				case 5:
					als.setUnits(LineUnits.getUnitsCode(Param));
					break;
				default:
					// Inherited parameters
					classEdit(getActiveLineSpacingObj(), ParamPointer - LineSpacing.NumPropsThisClass);
					break;
				}

				switch (ParamPointer) {
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

				ParamName = parser.getNextParam();
				Param = parser.makeString();
			}
		}

		return Result;
	}

	@Override
	protected int makeLike(String LineName) {
		int i;

		int Result = 0;
		/* See if we can find this line code in the present collection */
		LineSpacingObj OtherLineSpacing = (LineSpacingObj) find(LineName);
		if (OtherLineSpacing != null) {
			LineSpacingObj als = getActiveLineSpacingObj();

			als.setNWires(OtherLineSpacing.getNWires());   // allocates
			als.setNPhases(OtherLineSpacing.getNPhases());
			for (i = 0; i < als.getNConds(); i++)
				als.getX()[i] = OtherLineSpacing.getX()[i];
			for (i = 0; i < als.getNConds(); i++)
				als.getY()[i] = OtherLineSpacing.getY()[i];
			als.setUnits(OtherLineSpacing.getUnits());
			als.setDataChanged(true);
			for (i = 0; i < als.getParentClass().getNumProperties(); i++)
				als.setPropertyValue(i, OtherLineSpacing.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in LineSpacing MakeLike: \"" + LineName + "\" Not Found.", 102);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement LineSpacing.init()", -1);
		return 0;
	}

	/**
	 * Returns active line code string.
	 */
	public String getCode() {
		LineSpacingObj active = (LineSpacingObj) ElementList.getActive();
		return active.getName();
	}

	/**
	 * Sets the active LineSpacing.
	 */
	public void setCode(String Value) {
		LineSpacingObj pSpacing;

		setActiveLineSpacingObj(null);
		for (int i = 0; i < ElementList.size(); i++) {
			pSpacing = (LineSpacingObj) ElementList.get(i);

			if (pSpacing.getName().equalsIgnoreCase(Value)) {
				setActiveLineSpacingObj(pSpacing);
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("LineSpacing: \"" + Value + "\" not Found.", 103);
	}

	public static LineSpacingObj getActiveLineSpacingObj() {
		return ActiveLineSpacingObj;
	}

	public static void setActiveLineSpacingObj(LineSpacingObj activeLineSpacingObj) {
		ActiveLineSpacingObj = activeLineSpacingObj;
	}

}
