/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

/**
 * A general DSS object used by all circuits as a reference for obtaining yearly
 * growth curves.
 *
 * The values are set by the normal "new" and "edit" procedures as for any DSS object.
 *
 * The values are retrieved by setting the code property in the GrowthShape class.
 * This sets the active GrowthShapeObj object to be the one referenced by the code property;
 *
 * Then the values of that code can be retrieved via the public variables.  Or you
 * can pick up the activeGrowthShapeObj object and save the direct reference to the object.
 *
 * Growth shapes are entered as multipliers for the previous year's load.  If the
 * load grows by 2.5% in a year, the multiplier is entered as 1.025.  You do not need
 * to enter subsequent years if the multiplier remains the same.  You need only enter
 * the years in which the growth rate is assumed to have changed.
 *
 * The user may place the data in CSV or binary files as well as passing through the
 * command interface. The rules are the same as for LoadShapes except that the year
 * is always entered.  CSV files are text separated by commas, one interval to a line.
 * There are two binary formats permitted: 1) a file of Singles; 2) a file of Doubles.
 *
 * (Year, multiplier) pairs are expected in all formats.  Through the COM interface,
 * supply separate arrays of Year and Mult.
 *
 * Edit growthshape.allisonsub npts=5
 * ~   year="1999 2000 2001 2005 2010"
 * ~   mult="1.10 1.07 1.05 1.025 1.01"
 *
 * This example describes a growth curve that start off relatively fast (10%) and after
 * 10 years tapers off to 1%.
 *
 */
public class GrowthShape extends DSSClass {

	public static final int NumPropsThisClass = 6;

	public GrowthShapeObj activeGrowthShapeObj;

	public GrowthShape() {
		super();
		className = "GrowthShape";
		classType = DSSClassDefs.DSS_OBJECT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(false);
	}

	@Override
	protected void defineProperties() {
		final String CRLF = DSS.CRLF;

		numProperties = GrowthShape.NumPropsThisClass;
		countProperties();   // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0] = "npts";     // number of points to expect
		propertyName[1] = "year";     // vextor of year values
		propertyName[2] = "mult";     // vector of multiplier values corresponding to years
		propertyName[3] = "csvfile";  // switch input to a csvfile                (year, mult)
		propertyName[4] = "sngfile";  // switch input to a binary file of singles (year, mult)
		propertyName[5] = "dblfile";  // switch input to a binary file of doubles (year, mult)

		propertyHelp[0] = "Number of points to expect in subsequent vector.";
		propertyHelp[1] = "Array of year values, or a text file spec, corresponding to the multipliers. "+
			"Enter only those years where the growth changes. "+
			"May be any integer sequence -- just so it is consistent. See help on Mult.";
		propertyHelp[2] = "Array of growth multiplier values, or a text file spec, corresponding to the year values. "+
			"Enter the multiplier by which you would multiply the previous year's load to get the present year's."+
			CRLF+CRLF+"Examples:"+CRLF+CRLF+
			"  Year = [1, 2, 5]   Mult=[1.05, 1.025, 1.02]."+CRLF+
			"  Year= (File=years.txt) Mult= (file=mults.txt)."+ CRLF+CRLF+
			"Text files contain one value per line.";
		propertyHelp[3] = "Switch input of growth curve data to a csv file containing (year, mult) points, one per line.";
		propertyHelp[4] = "Switch input of growth curve data to a binary file of singles "+
			"containing (year, mult) points, packed one after another.";
		propertyHelp[5] = "Switch input of growth curve data to a binary file of doubles "+
			"containing (year, mult) points, packed one after another.";

		activeProperty = GrowthShape.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		// create a new object of this class and add to list.
		DSS.activeDSSObject = new GrowthShapeObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		double[] YrBuffer;
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeGrowthShapeObj = (GrowthShapeObj) elementList.getActive();
		DSS.activeDSSObject = activeGrowthShapeObj;

		GrowthShapeObj elem = activeGrowthShapeObj;

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
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Object \"" + getClassName() +"."+ getClassName() + "\"", 600);
				break;
			case 0:
				elem.setNpts(parser.integerValue());
				break;
			case 1:
				elem.setYear( Util.resizeArray(elem.getYear(), elem.getNpts()) );
				YrBuffer = new double[elem.getNpts()];
				Util.interpretDblArray(param, elem.getNpts(), YrBuffer);

				for (int i = 0; i < elem.getNpts(); i++)
					elem.getYear()[i] = (int) Math.round(YrBuffer[i]);

				elem.setBaseYear(elem.getYear()[0]);
				YrBuffer = null;
				break;
			case 2:
				elem.setMultiplier(Util.resizeArray(elem.getMultiplier(), elem.getNpts()));
				Util.interpretDblArray(param, elem.getNpts(), elem.getMultiplier());
				break;
			case 3:
				doCSVFile(param);
				break;
			case 4:
				doSngFile(param);
				break;
			case 5:
				doDblFile(param);
				break;
			default:
				// inherited parameters
				classEdit(activeGrowthShapeObj, paramPointer - GrowthShape.NumPropsThisClass);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		elem.reCalcYearMult();

		return 0;
	}

	@Override
	protected int makeLike(String shapeName) {
		GrowthShapeObj elem, other;

		/* See if we can find this line code in the present collection */
		other = (GrowthShapeObj) find(shapeName);

		if (other != null) {
			elem = activeGrowthShapeObj;
			elem.setNpts(other.getNpts());
			elem.setMultiplier(Util.resizeArray(elem.getMultiplier(), elem.getNpts()));
			for (int i = 0; i < elem.getNpts(); i++)
				elem.getMultiplier()[i] = other.getMultiplier()[i];
			elem.setYear( Util.resizeArray(elem.getYear(), elem.getNpts()) );
			for (int i = 0; i < elem.getNpts(); i++)
				elem.getYear()[i] = other.getYear()[i];
			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in GrowthShape makeLike: \"" + shapeName + "\" not found.", 601);
		}

		return 0;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement GrowthShape.init()", -1);
		return 0;
	}

	/**
	 * Returns active GrowthShape string.
	 */
	public String getCode() {
		return ((GrowthShapeObj) elementList.getActive()).getName();
	}

	/**
	 * Sets the active GrowthShape.
	 */
	public void setCode(String value) {
		GrowthShapeObj elem;
		activeGrowthShapeObj = null;

		for (int i = 0; i < elementList.size(); i++) {
			elem = (GrowthShapeObj) elementList.get(i);
			if (elem.getName().equalsIgnoreCase(value)) {
				activeGrowthShapeObj = elem;
				return;
			}
		}

		DSS.doSimpleMsg("GrowthShape: \"" + value + "\" not found.", 602);
	}

	private void doCSVFile(String fileName) {
		FileReader fr;
		BufferedReader br;
		String s;
		Parser parser;

		GrowthShapeObj elem = activeGrowthShapeObj;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			int i = 0;
			while (((s = br.readLine()) != null) && i < elem.getNpts()) {
				// use aux parser to allow flexible formats
				parser = DSS.auxParser;
				parser.setCommand(s);
				parser.getNextParam();
				elem.getYear()[i] = parser.integerValue();
				parser.getNextParam();
				elem.getMultiplier()[i] = parser.doubleValue();
				i += 1;
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing CSV file \"" + fileName + ": " + e.getMessage(), 604);
			return;
		}
	}

	private void doSngFile(String fileName) {
		doDblFile(fileName);
	}

	private void doDblFile(String fileName) {
		FileReader fr;
		BufferedReader br;
		double y, m;
		int i;
		String s;
		String[] parts;

		GrowthShapeObj elem = activeGrowthShapeObj;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			i = 0;
			while (((s = br.readLine()) != null) && i < elem.getNpts()) {
				parts = s.split("\\s+");
				if (parts.length == 2) {
					DSS.doSimpleMsg("Invalid growth shape file (line " + (i+1) + "): " + s, -1);
					break;
				}
				y = Double.parseDouble(parts[0]);
				m = Double.parseDouble(parts[1]);
				elem.getYear()[i] = (int) Math.round(y);
				elem.getMultiplier()[i] = m;
				i += 1;
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing growth shape file \"" + fileName + ": " + e.getMessage(), 604);
			return;
		}
	}

}
