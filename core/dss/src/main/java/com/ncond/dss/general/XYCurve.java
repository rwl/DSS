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

public class XYCurve extends DSSClass {

	public static final int NumPropsThisClass = 9;

	public static XYCurveObj activeXYCurveObj;

	private double[] tempPointsBuffer;

	public XYCurve() {
		super();
		className = "XYcurve";
		classType = DSSClassDefs.DSS_OBJECT;

		activeElement = -1;
		tempPointsBuffer = null;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		final String CRLF = DSS.CRLF;

		numProperties = XYCurve.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0]  = "npts";  // number of points to expect
		propertyName[1]  = "Points";
		propertyName[2]  = "Yarray";  // vector of Y values
		propertyName[3]  = "Xarray";  // vector of X values corresponding to Y values
		propertyName[4]  = "csvfile";  // switch input to a csvfile
		propertyName[5]  = "sngfile";  // switch input to a binary file of singles
		propertyName[6]  = "dblfile";  // switch input to a binary file of singles
		propertyName[7]  = "x";
		propertyName[8]  = "y";

		// define property help values
		propertyHelp[0] = "Max number of points to expect in curve. This could get reset to the actual number of points defined " +
				"if less than specified.";     // number of points to expect
		propertyHelp[1] = "One way to enter the points in a curve. Enter x and y values as one array "+
				"in the order [x1, y1, x2, y2, ...]. For example:"+CRLF+CRLF+
				"Points=[1,100 2,200 3, 300] "+CRLF+CRLF+
				"Values separated by commas or white space. Zero fills arrays if insufficient number of values.";
		propertyHelp[2] = "Alternate way to enter Y values. Enter an array of Y values corresponding to the X values.  "+
				"You can also use the syntax: "+CRLF+
				"Yarray = (file=filename)     !for text file one value per line"+CRLF+
				"Yarray = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"Yarray = (sngfile=filename)  !for packed file of singles "+CRLF+CRLF+
				"Note: this property will reset Npts to a smaller value if the  number of values in the files are fewer.";  // vextor of hour values
		propertyHelp[3] = "Alternate way to enter X values. Enter an array of X values corresponding to the Y values.  "+
				"You can also use the syntax: "+CRLF+
				"Xarray = (file=filename)     !for text file one value per line"+CRLF+
				"Xarray = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"Xarray = (sngfile=filename)  !for packed file of singles "+CRLF+CRLF+
				"Note: this property will reset Npts to a smaller value if the  number of values in the files are fewer.";  // vextor of hour values
		propertyHelp[4] = "Switch input of  X-Y curve data to a CSV file "+
				"containing X, Y points one per line. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a csvfile
		propertyHelp[5] = "Switch input of  X-Y curve data to a binary file of SINGLES "+
				"containing X, Y points packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		propertyHelp[6] = "Switch input of  X-Y  curve data to a binary file of DOUBLES "+
				"containing X, Y points packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		propertyHelp[7] = "Enter an value and then retrieve the interpolated Y value from the Y property.";
		propertyHelp[8] = "Enter an value and then retrieve the interpolated X value from the X property.";

		activeProperty = XYCurve.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeDSSObject = new XYCurveObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeXYCurveObj = (XYCurveObj) elementList.getActive();
		DSS.activeDSSObject = activeXYCurveObj;

		XYCurveObj elem = activeXYCurveObj;

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
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() +"."+ elem.getName() + "\"", 610);
				break;
			case 0:
				elem.setNumPoints(parser.makeInteger());
				break;
			case 1:
				tempPointsBuffer = Util.resizeArray(tempPointsBuffer, elem.getNumPoints() * 2);
				// allow possible resetting (to a lower value) of num points when specifying temperatures not hours
				elem.setNumPoints(Util.interpretDblArray(param, (elem.getNumPoints() * 2), tempPointsBuffer) / 2);
				elem.setYValues(Util.resizeArray(elem.getYValues(), elem.getNumPoints()));
				elem.setXValues(Util.resizeArray(elem.getXValues(), elem.getNumPoints()));
				for (int i = 0; i < elem.getNumPoints(); i++) {
					elem.getXValues()[i] = tempPointsBuffer[2 * i];
					elem.getYValues()[i] = tempPointsBuffer[2 * i + 1];
				}
				elem.setX(elem.getXValues()[0]);
				elem.setY(elem.getYValues()[0]);
				tempPointsBuffer = null;  // throw away temp array
				break;
			case 2:
				elem.setYValues(Util.resizeArray(elem.getYValues(), elem.getNumPoints()));
				elem.setNumPoints(Util.interpretDblArray(param, elem.getNumPoints(), elem.getYValues()));
				elem.setY(elem.getYValues()[0]);
				break;
			case 3:
				elem.setXValues(Util.resizeArray(elem.getXValues(), elem.getNumPoints()));
				elem.setNumPoints(Util.interpretDblArray(param, elem.getNumPoints(), elem.getXValues()));
				elem.setX(elem.getXValues()[0]);
				break;
			case 4:
				doCSVFile(param);  // file of x,y points, one to a line
				break;
			case 5:
				doSngFile(param);
				break;
			case 6:
				doDblFile(param);
				break;
			case 7:
				elem.setX(parser.makeDouble());
				break;
			case 8:
				elem.setY(parser.makeDouble());
				break;
			default:
				// inherited parameters
				classEdit(activeXYCurveObj, paramPointer - XYCurve.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 4:
			case 5:
			case 6:
				elem.setX(elem.getXValues()[0]);
				elem.setY(elem.getYValues()[0]);
				break;
			}

			switch (paramPointer) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				elem.setArrayPropertyIndex(paramPointer);
				elem.setNumPoints(elem.getNumPoints());  // FIXME: keep properties in order for save command
				elem.setLastValueAccessed(0);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}
		return 0;
	}

	public String getCode() {
		return ((XYCurveObj) elementList.getActive()).getName();
	}

	public void setCode(String value) {
		activeXYCurveObj = null;
		XYCurveObj elem = (XYCurveObj) elementList.getFirst();
		while (elem != null) {

			if (elem.getName().equalsIgnoreCase(value)) {
				activeXYCurveObj = elem;
				return;
			}

			elem = (XYCurveObj) elementList.getNext();
		}

		DSS.doSimpleMsg("XYCurve: \"" + value + "\" not found.", 612);
	}

	private void doCSVFile(String fileName) {
		FileReader fr;
		BufferedReader br;
		String s;
		Parser parser;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			XYCurveObj elem = activeXYCurveObj;

			elem.setXValues(Util.resizeArray(elem.getXValues(), elem.getNumPoints()));
			elem.setYValues(Util.resizeArray(elem.getYValues(), elem.getNumPoints()));

			int i = 0;
			while (((s = br.readLine()) != null) && i < elem.getNumPoints()) {
				/* Aux parser allows for commas or white space */
				parser = DSS.auxParser;
				parser.setCmdString(s);
				parser.getNextParam();
				elem.getXValues()[i] = parser.makeDouble();
				parser.getNextParam();
				elem.getYValues()[i] = parser.makeDouble();
				i += 1;
			}

			if (i != elem.getNumPoints() - 1)
				elem.setNumPoints(i);

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing XYCurve CSV file: \"" + fileName + ". " + e.getMessage(), 604);
			return;
		}
	}

	private void doSngFile(String fileName) {
		doDblFile(fileName);
	}

	private void doDblFile(String fileName) {
		FileReader fr;
		BufferedReader br;
		int i;
		String s;

		XYCurveObj elem = activeXYCurveObj;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			elem.setXValues(Util.resizeArray(elem.getXValues(), elem.getNumPoints()));
			elem.setYValues(Util.resizeArray(elem.getYValues(), elem.getNumPoints()));

			i = 0;
			s = "";
			while (s != null && i < elem.getNumPoints()) {
				s = br.readLine();
				elem.getXValues()[i] = Double.parseDouble(s);
				s = br.readLine();
				elem.getYValues()[i] = Double.parseDouble(s);
				i += 1;
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing XY values file \"" + fileName + ": " + e.getMessage(), 604);
			return;
		}
	}

	@Override
	protected int makeLike(String curveName) {
		int i, success = 0;

		/* See if we can find this curve in the present collection */
		XYCurveObj other = (XYCurveObj) find(curveName);

		if (other != null) {
			XYCurveObj elem = activeXYCurveObj;

			elem.setNumPoints(other.getNumPoints());
			elem.setXValues(Util.resizeArray(elem.getXValues(), elem.getNumPoints()));
			elem.setYValues(Util.resizeArray(elem.getYValues(), elem.getNumPoints()));

			for (i = 0; i < elem.getNumPoints(); i++)
				elem.getXValues()[i] = other.getXValues()[i];

			for (i = 0; i < elem.getNumPoints(); i++)
				elem.getYValues()[i] = other.getYValues()[i];

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in XYCurve makeLike: \"" + curveName + "\" not found", 611);
		}
		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement XYcurve.init", -1);
		return 0;
	}

	/**
	 * Find an obj of this class by name.
	 */
	@Override
	public DSSObject find(String objName) {
		if (objName.length() == 0 || objName.equalsIgnoreCase("none")) {
			return null;
		} else {
			return super.find(objName);
		}
	}

}
