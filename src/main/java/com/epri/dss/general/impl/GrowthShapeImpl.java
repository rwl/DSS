package com.epri.dss.general.impl;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.GrowthShape;
import com.epri.dss.general.GrowthShapeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class GrowthShapeImpl extends DSSClassImpl implements GrowthShape {
	
	private GrowthShapeObj ActiveGrowthShapeObj;

	public GrowthShapeImpl() {
		super();
		this.Class_Name = "GrowthShape";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;

		this.ActiveElement = 0;

		defineProperties();

		String[] Commands = new String[0];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(false);
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;
		
		NumProperties = GrowthShape.NumPropsThisClass;
		countProperties();   // Get inherited property count

		allocatePropertyArrays();


		// Define Property names

		PropertyName[0] = "npts";     // Number of points to expect
		PropertyName[1] = "year";     // vextor of year values
		PropertyName[2] = "mult";     // vector of multiplier values corresponding to years
		PropertyName[3] = "csvfile";  // Switch input to a csvfile                 (year, mult)
		PropertyName[4] = "sngfile";  // switch input to a binary file of singles  (year, mult)
		PropertyName[5] = "dblfile";  // switch input to a binary file of doubles (year, mult)

		PropertyHelp[0] = "Number of points to expect in subsequent vector.";
		PropertyHelp[1] = "Array of year values, or a text file spec, corresponding to the multipliers. "+
				"Enter only those years where the growth changes. "+
				"May be any integer sequence -- just so it is consistent. See help on Mult.";
		PropertyHelp[2] = "Array of growth multiplier values, or a text file spec, corresponding to the year values. "+
				"Enter the multiplier by which you would multiply the previous year's load to get the present year's."+
				CRLF+CRLF+"Examples:"+CRLF+CRLF+
				"  Year = [1, 2, 5]   Mult=[1.05, 1.025, 1.02]."+CRLF+
				"  Year= (File=years.txt) Mult= (file=mults.txt)."+ CRLF+CRLF+
				"Text files contain one value per line.";
		PropertyHelp[3] = "Switch input of growth curve data to a csv file containing (year, mult) points, one per line.";
		PropertyHelp[4] = "Switch input of growth curve data to a binary file of singles "+
				"containing (year, mult) points, packed one after another.";
		PropertyHelp[5] = "Switch input of growth curve data to a binary file of doubles "+
				"containing (year, mult) points, packed one after another.";

		ActiveProperty = GrowthShape.NumPropsThisClass;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	public int newObject(String ObjName) {
		// Create a new object of this class and add to list.
		DSSGlobals Globals = DSSGlobals.getInstance();
		Globals.setActiveDSSObject(new GrowthShapeObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		double[] YrBuffer;

		int Result = 0;
		// continue parsing with contents of parser
		setActiveGrowthShapeObj(ElementList.getActive());
		Globals.setActiveDSSObject((DSSObjectImpl) getActiveGrowthShapeObj());

		GrowthShapeObj pShape = getActiveGrowthShapeObj();

		int ParamPointer = 0;
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}
	
			if ((ParamPointer > 0) && (ParamPointer <= NumProperties))  // TODO Check zero based indexing
				pShape.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case 0:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ getName() + "\"", 600);
			case 1:
				Npts = Parser.getInstance().makeInteger();
			case 2:
				Year = Utilities.resizeArray(Year, Npts);
				YrBuffer = new double[Npts];
				Utilities.interpretDblArray(Param, Npts, YrBuffer);  // Parser.parseAsVector(Npts, Yrbuffer);
					
				for (int i = 0; i < Npts; i++) 
					Year[i] = Math.round(YrBuffer[i]);
				BaseYear = Year[0];
				YrBuffer = null;
			case 3: 
				Multiplier = Utilities.resizeArray(Multiplier, Npts);
				Utilities.interpretDblArray(Param, Npts, Multiplier);   //Parser.parseAsVector(Npts, Multiplier);
			case 4:
				doCSVFile(Param);
			case 5:
				doSngFile(Param);
			case 6:
				doDblFile(Param);
			default:
				// Inherited parameters
				classEdit(getActiveGrowthShapeObj(), ParamPointer - GrowthShape.NumPropsThisClass);
			}

			ParamName = Parser.getInstance().getNextParam();
			Param = Parser.getInstance().makeString();
		}

		pShape.reCalcYearMult();
	
		return Result;
	}

	public GrowthShapeObj getActiveGrowthShapeObj() {
		return ActiveGrowthShapeObj;
	}

	public void setActiveGrowthShapeObj(GrowthShapeObj activeGrowthShapeObj) {
		ActiveGrowthShapeObj = activeGrowthShapeObj;
	}

	/**
	 * Returns active GrowthShape string.
	 */
	public String getCode() {
		return null;
	}

	/**
	 * Sets the active GrowthShape.
	 */
	public void setCode(String Value) {

	}

	private void doCSVFile(String FileName) {

	}

	private void doSngFile(String FileName) {

	}

	private void doDblFile(String FileName) {

	}

	protected int makeLike(String ShapeName) {
		return 0;
	}

	public int init(int Handle) {
		return 0;
	}

}
