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

	private static XYCurveObj ActiveXYCurveObj;

	private double[] TempPointsBuffer;

	public XYCurveImpl() {
		super();
		this.Class_Name   = "XYcurve";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;

		this.ActiveElement = -1;
		this.TempPointsBuffer = null;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		final String CRLF = DSSGlobals.CRLF;

		NumProperties = XYCurve.NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();

		// Define property names
		PropertyName[0]  = "npts";     // Number of points to expect
		PropertyName[1]  = "Points";
		PropertyName[2]  = "Yarray";     // vector of Y values
		PropertyName[3]  = "Xarray";     // vector of X values corresponding to Y values
		PropertyName[4]  = "csvfile";  // Switch input to a csvfile
		PropertyName[5]  = "sngfile";  // switch input to a binary file of singles
		PropertyName[6]  = "dblfile";    // switch input to a binary file of singles
		PropertyName[7]  = "x";
		PropertyName[8]  = "y";

		// Define property help values
		PropertyHelp[0] = "Max number of points to expect in curve. This could get reset to the actual number of points defined " +
				"if less than specified.";     // Number of points to expect
		PropertyHelp[1] = "One way to enter the points in a curve. Enter x and y values as one array "+
				"in the order [x1, y1, x2, y2, ...]. For example:"+CRLF+CRLF+
				"Points=[1,100 2,200 3, 300] "+CRLF+CRLF+
				"Values separated by commas or white space. Zero fills arrays if insufficient number of values.";
		PropertyHelp[2] = "Alternate way to enter Y values. Enter an array of Y values corresponding to the X values.  "+
				"You can also use the syntax: "+CRLF+
				"Yarray = (file=filename)     !for text file one value per line"+CRLF+
				"Yarray = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"Yarray = (sngfile=filename)  !for packed file of singles "+CRLF+CRLF+
				"Note: this property will reset Npts to a smaller value if the  number of values in the files are fewer.";     // vextor of hour values
		PropertyHelp[3] = "Alternate way to enter X values. Enter an array of X values corresponding to the Y values.  "+
				"You can also use the syntax: "+CRLF+
				"Xarray = (file=filename)     !for text file one value per line"+CRLF+
				"Xarray = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"Xarray = (sngfile=filename)  !for packed file of singles "+CRLF+CRLF+
				"Note: this property will reset Npts to a smaller value if the  number of values in the files are fewer.";     // vextor of hour values
		PropertyHelp[4] = "Switch input of  X-Y curve data to a CSV file "+
				"containing X, Y points one per line. " +
				"NOTE: This action may reset the number of points to a lower value.";   // Switch input to a csvfile
		PropertyHelp[5] = "Switch input of  X-Y curve data to a binary file of SINGLES "+
				"containing X, Y points packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		PropertyHelp[6] = "Switch input of  X-Y  curve data to a binary file of DOUBLES "+
				"containing X, Y points packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";   // switch input to a binary file of singles
		PropertyHelp[7] = "Enter an value and then retrieve the interpolated Y value from the Y property.";
		PropertyHelp[8] = "Enter an value and then retrieve the interpolated X value from the X property.";

		ActiveProperty = XYCurve.NumPropsThisClass;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Globals.setActiveDSSObject(new XYCurveObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int Result = 0;
		// continue parsing with contents of parser
		setActiveXYCurveObj((XYCurveObj) ElementList.getActive());
		Globals.setActiveDSSObject(getActiveXYCurveObj());

		XYCurveObj xyc = getActiveXYCurveObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();

		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < NumProperties))
				xyc.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ xyc.getName() + "\"", 610);
			case 0:
				xyc.setNumPoints(parser.makeInteger());
			case 1:
				TempPointsBuffer = (double[]) Utilities.resizeArray(TempPointsBuffer, xyc.getNumPoints() * 2);
				// Allow possible resetting (to a lower value) of num points when specifying temperatures not hours
				xyc.setNumPoints( Utilities.interpretDblArray(Param, (xyc.getNumPoints() * 2), TempPointsBuffer) / 2);  // parser.parseAsVector(Npts, Temperatures);
				xyc.setYValues( (double[]) Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()) );
				xyc.setXValues( (double[]) Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()) );
				for (int i = 0; i < xyc.getNumPoints(); i++) {
					xyc.getXValues()[i] = TempPointsBuffer[2 * i - 1];
					xyc.getYValues()[i] = TempPointsBuffer[2 * i];
				}
				xyc.setX(xyc.getXValues()[0]);
				xyc.setY(xyc.getYValues()[0]);
				TempPointsBuffer = null;  // Throw away temp array
			case 2:
				xyc.setYValues( (double[]) Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()) );
				xyc.setNumPoints( Utilities.interpretDblArray(Param, xyc.getNumPoints(), xyc.getYValues()) );
				xyc.setY(xyc.getYValues()[0]);
			case 3:
				xyc.setXValues( (double[]) Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()) );
				xyc.setNumPoints( Utilities.interpretDblArray(Param, xyc.getNumPoints(), xyc.getXValues()) );
				xyc.setX( xyc.getXValues()[0] );
			case 4:
				doCSVFile(Param);   // file of x,y points, one to a line
			case 5:
				doSngFile(Param);
			case 6:
				doDblFile(Param);
			case 7:
				xyc.setX(parser.makeDouble());
			case 8:
				xyc.setY(parser.makeDouble());
			default:
				// Inherited parameters
				classEdit(getActiveXYCurveObj(), ParamPointer - XYCurve.NumPropsThisClass);
			}

			switch (ParamPointer) {
			case 4:
				xyc.setX(xyc.getXValues()[0]);
				xyc.setY(xyc.getYValues()[0]);
			case 5:
				xyc.setX(xyc.getXValues()[0]);
				xyc.setY(xyc.getYValues()[0]);
			case 6:
				xyc.setX(xyc.getXValues()[0]);
				xyc.setY(xyc.getYValues()[0]);
			}

			switch (ParamPointer) {
			case 1:
				xyc.setArrayPropertyIndex(ParamPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
			case 2:
				xyc.setArrayPropertyIndex(ParamPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
			case 3:
				xyc.setArrayPropertyIndex(ParamPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
			case 4:
				xyc.setArrayPropertyIndex(ParamPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
			case 5:
				xyc.setArrayPropertyIndex(ParamPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
			case 6:
				xyc.setArrayPropertyIndex(ParamPointer);
				xyc.setNumPoints(xyc.getNumPoints());  // FIXME Keep properties in order for save command
				xyc.setLastValueAccessed(0);
			}

			ParamName = parser.getNextParam();
			Param     = parser.makeString();
		}
		return Result;
	}

	public String getCode() {
		return ((XYCurveObj) ElementList.getActive()).getName();
	}

	public void setCode(String Value) {
		setActiveXYCurveObj(null);
		XYCurveObj pXYCurveObj = (XYCurveObj) ElementList.getFirst();
		while (pXYCurveObj != null) {

			if (pXYCurveObj.getName().equals(Value)) {
				setActiveXYCurveObj(pXYCurveObj);
				return;
			}

			pXYCurveObj = (XYCurveObj) ElementList.getNext();
		}

		DSSGlobals.getInstance().doSimpleMsg("XYCurve: \"" + Value + "\" not Found.", 612);
	}

	private void doCSVFile(String FileName) {
		FileInputStream fileStream;
		DataInputStream dataStream;
		BufferedReader reader;

		String s;
		Parser parser;
		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			fileStream = new FileInputStream(FileName);
			dataStream = new DataInputStream(fileStream);
			reader = new BufferedReader(new InputStreamReader(dataStream));

			XYCurveObj xyc = getActiveXYCurveObj();

			xyc.setXValues( (double[]) Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()) );
			xyc.setYValues( (double[]) Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()) );

			int i = -1;  // TODO Check zero based indexing
			while (((s = reader.readLine()) != null) && i < xyc.getNumPoints()) {  // TODO: Check zero based indexing
				i += 1;
				/* AuxParser allows commas or white space */
				parser = Globals.getAuxParser();
				parser.setCmdString(s);
				parser.getNextParam();
				xyc.getXValues()[i] = parser.makeDouble();
				parser.getNextParam();
				xyc.getYValues()[i] = parser.makeDouble();
			}
			fileStream.close();
			dataStream.close();
			reader.close();
			if (i != xyc.getNumPoints())  // TODO: Check zero based indexing
				xyc.setNumPoints(i);

			fileStream.close();
			dataStream.close();
			reader.close();
		} catch (IOException e) {
			Globals.doSimpleMsg("Error processing XYCurve CSV File: \"" + FileName + ". " + e.getMessage(), 604);
			return;
		}
	}

	private void doSngFile(String FileName) {
		throw new UnsupportedOperationException();
	}

	private void doDblFile(String FileName) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected int makeLike(String CurveName) {
		int i, Result = 0;

		/* See if we can find this curve in the present collection */
		XYCurveObj OtherXYCurve = (XYCurveObj) find(CurveName);
		if (OtherXYCurve != null) {
			XYCurveObj xyc = getActiveXYCurveObj();

			xyc.setNumPoints(OtherXYCurve.getNumPoints());
			xyc.setXValues( (double[]) Utilities.resizeArray(xyc.getXValues(), xyc.getNumPoints()) );
			xyc.setYValues( (double[]) Utilities.resizeArray(xyc.getYValues(), xyc.getNumPoints()) );
			for (i = 0; i < xyc.getNumPoints(); i++)
				xyc.getXValues()[i] = OtherXYCurve.getXValues()[i];
			for (i = 0; i < xyc.getNumPoints(); i++)
				xyc.getYValues()[i] = OtherXYCurve.getYValues()[i];

			for (i = 0; i < xyc.getParentClass().getNumProperties(); i++)
				xyc.setPropertyValue(i, OtherXYCurve.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in XYCurve makeLike: \"" + CurveName + "\" Not Found.", 611);
		}
		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement XYcurve.init", -1);
		return 0;
	}

	/**
	 * Find an obj of this class by name.
	 */
	@Override
	public Object find(String ObjName) {
		if ((ObjName.length() == 0) || ObjName.equals("none")) {
			return null;
		} else {
			return super.find(ObjName);
		}
	}

	public static void setActiveXYCurveObj(XYCurveObj activeXYCurveObj) {
		ActiveXYCurveObj = activeXYCurveObj;
	}

	public static XYCurveObj getActiveXYCurveObj() {
		return ActiveXYCurveObj;
	}

}
