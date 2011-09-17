package com.epri.dss.general.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.XYCurve;
import com.epri.dss.general.XYCurveObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class XYCurveImpl extends DSSClassImpl implements XYCurve {

	public static XYCurveObj activeXYCurveObj;

	private double[] tempPointsBuffer;

	public XYCurveImpl() {
		super();
		className   = "XYcurve";
		classType = DSSClassDefs.DSS_OBJECT;

		activeElement = -1;
		tempPointsBuffer = null;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		final String CRLF = DSSGlobals.CRLF;

		numProperties = XYCurve.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0]  = "npts";     // number of points to expect
		propertyName[1]  = "Points";
		propertyName[2]  = "Yarray";   // vector of Y values
		propertyName[3]  = "Xarray";   // vector of X values corresponding to Y values
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
		DSSGlobals.activeDSSObject = new XYCurveObjImpl(this, objName);
		return addObjectToList(DSSGlobals.activeDSSObject);
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of parser
		activeXYCurveObj = (XYCurveObj) elementList.getActive();
		DSSGlobals.activeDSSObject = activeXYCurveObj;

		XYCurveObj xyc = activeXYCurveObj;

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
				xyc.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSSGlobals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ xyc.getName() + "\"", 610);
				break;
			case 0:
				xyc.setNumPoints(parser.makeInteger());
				break;
			case 1:
				tempPointsBuffer = Utilities.resizeArray(tempPointsBuffer, xyc.getNumPoints() * 2);
				// allow possible resetting (to a lower value) of num points when specifying temperatures not hours
				xyc.setNumPoints( Utilities.interpretDblArray(param, (xyc.getNumPoints() * 2), tempPointsBuffer) / 2);  // parser.parseAsVector(Npts, Temperatures);
				xyc.setYValues( Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()) );
				xyc.setXValues( Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()) );
				for (int i = 0; i < xyc.getNumPoints(); i++) {
					xyc.getXValues()[i] = tempPointsBuffer[2 * i - 1];
					xyc.getYValues()[i] = tempPointsBuffer[2 * i];
				}
				xyc.setX(xyc.getXValues()[0]);
				xyc.setY(xyc.getYValues()[0]);
				tempPointsBuffer = null;  // throw away temp array
				break;
			case 2:
				xyc.setYValues( Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()) );
				xyc.setNumPoints( Utilities.interpretDblArray(param, xyc.getNumPoints(), xyc.getYValues()) );
				xyc.setY(xyc.getYValues()[0]);
				break;
			case 3:
				xyc.setXValues( Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()) );
				xyc.setNumPoints( Utilities.interpretDblArray(param, xyc.getNumPoints(), xyc.getXValues()) );
				xyc.setX( xyc.getXValues()[0] );
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
				xyc.setX(parser.makeDouble());
				break;
			case 8:
				xyc.setY(parser.makeDouble());
				break;
			default:
				// inherited parameters
				classEdit(activeXYCurveObj, paramPointer - XYCurve.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 4:
				xyc.setX(xyc.getXValues()[0]);
				xyc.setY(xyc.getYValues()[0]);
				break;
			case 5:
				xyc.setX(xyc.getXValues()[0]);
				xyc.setY(xyc.getYValues()[0]);
				break;
			case 6:
				xyc.setX(xyc.getXValues()[0]);
				xyc.setY(xyc.getYValues()[0]);
				break;
			}

			switch (paramPointer) {
			case 1:
				xyc.setArrayPropertyIndex(paramPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
				break;
			case 2:
				xyc.setArrayPropertyIndex(paramPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
				break;
			case 3:
				xyc.setArrayPropertyIndex(paramPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
				break;
			case 4:
				xyc.setArrayPropertyIndex(paramPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
				break;
			case 5:
				xyc.setArrayPropertyIndex(paramPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
				break;
			case 6:
				xyc.setArrayPropertyIndex(paramPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
				break;
			}

			paramName = parser.getNextParam();
			param     = parser.makeString();
		}
		return result;
	}

	public String getCode() {
		return ((XYCurveObj) elementList.getActive()).getName();
	}

	public void setCode(String value) {
		activeXYCurveObj = null;
		XYCurveObj pXYCurveObj = (XYCurveObj) elementList.getFirst();
		while (pXYCurveObj != null) {

			if (pXYCurveObj.getName().equalsIgnoreCase(value)) {
				activeXYCurveObj = pXYCurveObj;
				return;
			}

			pXYCurveObj = (XYCurveObj) elementList.getNext();
		}

		DSSGlobals.doSimpleMsg("XYCurve: \"" + value + "\" not found.", 612);
	}

	private void doCSVFile(String fileName) {
		FileInputStream fis;
		DataInputStream dis;
		BufferedReader br;

		String s;
		Parser parser;

		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			br = new BufferedReader(new InputStreamReader(dis));

			XYCurveObj xyc = activeXYCurveObj;

			xyc.setXValues( Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()) );
			xyc.setYValues( Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()) );

			int i = 0;
			while (((s = br.readLine()) != null) && i < xyc.getNumPoints()) {
				/* Aux parser allows commas or white space */
				parser = DSSGlobals.auxParser;
				parser.setCmdString(s);
				parser.getNextParam();
				xyc.getXValues()[i] = parser.makeDouble();
				parser.getNextParam();
				xyc.getYValues()[i] = parser.makeDouble();
				i += 1;
			}
			fis.close();
			dis.close();
			br.close();
			if (i != xyc.getNumPoints() - 1)
				xyc.setNumPoints(i);

			fis.close();
			dis.close();
			br.close();
		} catch (IOException e) {
			DSSGlobals.doSimpleMsg("Error processing XYCurve CSV file: \"" + fileName + ". " + e.getMessage(), 604);
			return;
		}
	}

	private void doSngFile(String fileName) {
		throw new UnsupportedOperationException();
	}

	private void doDblFile(String fileName) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected int makeLike(String curveName) {
		int i, result = 0;

		/* See if we can find this curve in the present collection */
		XYCurveObj otherXYCurve = (XYCurveObj) find(curveName);
		if (otherXYCurve != null) {
			XYCurveObj xyc = activeXYCurveObj;

			xyc.setNumPoints(otherXYCurve.getNumPoints());
			xyc.setXValues( Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()) );
			xyc.setYValues( Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()) );
			for (i = 0; i < xyc.getNumPoints(); i++)
				xyc.getXValues()[i] = otherXYCurve.getXValues()[i];
			for (i = 0; i < xyc.getNumPoints(); i++)
				xyc.getYValues()[i] = otherXYCurve.getYValues()[i];

			for (i = 0; i < xyc.getParentClass().getNumProperties(); i++)
				xyc.setPropertyValue(i, otherXYCurve.getPropertyValue(i));
		} else {
			DSSGlobals.doSimpleMsg("Error in XYCurve makeLike: \"" + curveName + "\" not found.", 611);
		}
		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.doSimpleMsg("Need to implement XYcurve.init", -1);
		return 0;
	}

	/**
	 * Find an obj of this class by name.
	 */
	@Override
	public Object find(String objName) {
		if (objName.length() == 0 || objName.equalsIgnoreCase("none")) {
			return null;
		} else {
			return super.find(objName);
		}
	}

}
