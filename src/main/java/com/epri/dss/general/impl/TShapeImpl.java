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
import com.epri.dss.general.TShape;
import com.epri.dss.general.TShapeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class TShapeImpl extends DSSClassImpl implements TShape {

	private static TShapeObj ActiveTShapeObj;

	public TShapeImpl() {
		super();
		this.Class_Name   = "TShape";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;

		setActiveElement(-1);

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		final String CRLF = DSSGlobals.CRLF;

		NumProperties = TShape.NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();


		// Define property names
		PropertyName[0]  = "npts";      // Number of points to expect
		PropertyName[1]  = "interval";  // default = 1.0;
		PropertyName[2]  = "temp";      // vector of temperature values
		PropertyName[3]  = "hour";      // vector of hour values
		PropertyName[4]  = "mean";      // set the mean temp (otherwise computed)
		PropertyName[5]  = "stddev";    // set the std dev of the temp (otherwise computed)
		PropertyName[6]  = "csvfile";   // Switch input to a csvfile
		PropertyName[7]  = "sngfile";   // switch input to a binary file of singles
		PropertyName[8]  = "dblfile";   // switch input to a binary file of singles
		PropertyName[9]  = "sinterval"; // Interval in seconds
		PropertyName[10] = "minterval"; // Interval in minutes
		PropertyName[11] = "action";    // Interval in minutes

		// Define property help values
		PropertyHelp[0] = "Max number of points to expect in temperature shape vectors. This gets reset to the number of temperature values " +
				"found if less than specified.";  // Number of points to expect
		PropertyHelp[1] = "Time interval for fixed interval data, hrs. Default = 1. "+
				"If set = 0 then time data (in hours) is expected using either the Hour property or input files. " +CRLF+CRLF+
				"See also \"sinterval\" and \"minterval\".";  // default = 1.0;
		PropertyHelp[2] = "Array of temperature values.  Units should be compatible with the object using the data. " +
				"You can also use the syntax: "+CRLF+
				"Temp = (file=filename)     !for text file one value per line"+CRLF+
				"Temp = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"Temp = (sngfile=filename)  !for packed file of singles "+CRLF+CRLF+
				"Note: this property will reset Npts if the  number of values in the files are fewer.";  // vextor of hour values
		PropertyHelp[3] = "Array of hour values. Only necessary to define this property for variable interval data."+
				" If the data are fixed interval, do not use this property. " +
				"You can also use the syntax: "+CRLF+
				"hour = (file=filename)     !for text file one value per line"+CRLF+
				"hour = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"hour = (sngfile=filename)  !for packed file of singles ";  // vextor of hour values
		PropertyHelp[4] = "Mean of the temperature curve values.  This is computed on demand the first time a "+
				"value is needed.  However, you may set it to another value independently. "+
				"Used for Monte Carlo load simulations.";  // set the mean (otherwise computed)
		PropertyHelp[5] = "Standard deviation of the temperature.  This is computed on demand the first time a "+
				"value is needed.  However, you may set it to another value independently."+
				"Is overwritten if you subsequently read in a curve" + CRLF + CRLF +
				"Used for Monte Carlo load simulations.";  // set the std dev (otherwise computed)
		PropertyHelp[6] = "Switch input of temperature curve data to a csv file "+
				"containing (hour, Temp) points, or simply (Temp) values for fixed time interval data, one per line. " +
				"NOTE: This action may reset the number of points to a lower value.";  // Switch input to a csvfile
		PropertyHelp[7] = "Switch input of temperature curve data to a binary file of singles "+
				"containing (hour, Temp) points, or simply (Temp) values for fixed time interval data, packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		PropertyHelp[8] = "Switch input of temperature curve data to a binary file of doubles "+
				"containing (hour, Temp) points, or simply (Temp) values for fixed time interval data, packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		PropertyHelp[9] ="Specify fixed interval in SECONDS. Alternate way to specify Interval property.";
		PropertyHelp[10] ="Specify fixed interval in MINUTES. Alternate way to specify Interval property.";
		PropertyHelp[11] ="{DblSave | SngSave} After defining temperature curve data... " +
				"Setting action=DblSave or SngSave will cause the present \"Temp\" values to be written to " +
				"either a packed file of double or single. The filename is the TShape name. ";  // Action

		ActiveProperty = TShape.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Globals.setActiveDSSObject(new TShapeObjImpl(this, ObjName));
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
		setActiveTShapeObj((TShapeObj) ElementList.getActive());
		Globals.setActiveDSSObject(getActiveTShapeObj());

		TShapeObj ats = getActiveTShapeObj();

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
				ats.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ ats.getName() + "\"", 610);
				break;
			case 0:
				ats.setNumPoints(parser.makeInteger());
				break;
			case 1:
				ats.setInterval(parser.makeDouble());
				break;
			case 2:
				ats.setTValues( (double[]) Utilities.resizeArray(ats.getTValues(), ats.getNumPoints()) );
				// Allow possible resetting (to a lower value) of num points when specifying temperatures not hours
				ats.setNumPoints( Utilities.interpretDblArray(Param, ats.getNumPoints(), ats.getTValues()) );   //parser.parseAsVector(Npts, Temps);
				break;
			case 3:
				ats.setHours( (double[]) Utilities.resizeArray(ats.getHours(), ats.getNumPoints()) );
				Utilities.interpretDblArray(Param, ats.getNumPoints(), ats.getHours());   //parser.parseAsVector(Npts, Hours);
				break;
			case 4:
				ats.setMean(parser.makeDouble());
				break;
			case 5:
				ats.setStdDev(parser.makeDouble());
				break;
			case 6:
				doCSVFile(Param);
				break;
			case 7:
				doSngFile(Param);
				break;
			case 8:
				doDblFile(Param);
				break;
			case 9:
				ats.setInterval(parser.makeDouble() / 3600.0);  // Convert seconds to hr
				break;
			case 10:
				ats.setInterval(parser.makeDouble() / 60.0);  // Convert minutes to hr
				break;
			case 11:
				switch (Param.toLowerCase().charAt(0)) {
				case 'd':
					ats.saveToDblFile();
					break;
				case 's':
					ats.saveToSngFile();
					break;
				}
				break;
			default:
				// Inherited parameters
				classEdit(getActiveTShapeObj(), ParamPointer - TShape.NumPropsThisClass);
				break;
			}

			switch (ParamPointer) {
			case 2:
				ats.setStdDevCalculated(false);  // now calculated on demand
				ats.setArrayPropertyIndex(ParamPointer);
				ats.setNumPoints(ats.getNumPoints());  // Keep properties in order for save command  FIXME
				break;
			case 6:
				ats.setStdDevCalculated(false);  // now calculated on demand
				ats.setArrayPropertyIndex(ParamPointer);
				ats.setNumPoints(ats.getNumPoints());  // Keep properties in order for save command
				break;
			case 7:
				ats.setStdDevCalculated(false);  // now calculated on demand
				ats.setArrayPropertyIndex(ParamPointer);
				ats.setNumPoints(ats.getNumPoints());  // Keep properties in order for save command
				break;
			case 8:
				ats.setStdDevCalculated(false);  // now calculated on demand
				ats.setArrayPropertyIndex(ParamPointer);
				ats.setNumPoints(ats.getNumPoints());  // Keep properties in order for save command
				break;
			}

			ParamName = parser.getNextParam();
			Param     = parser.makeString();
		}
		return Result;
	}

	@Override
	public Object find(String ObjName) {
		if ((ObjName.length() == 0) || ObjName.equalsIgnoreCase("none")) {
			return null;
		} else {
			return super.find(ObjName);
		}
	}

	@Override
	protected int makeLike(String ShapeName) {
		TShapeObj OtherTShape;
		int i, Result = 0;

		/* See if we can find this line code in the present collection */
		OtherTShape = (TShapeObj) find(ShapeName);
		if (OtherTShape != null) {
			TShapeObj aps = getActiveTShapeObj();

			aps.setNumPoints(OtherTShape.getNumPoints());
			aps.setInterval(OtherTShape.getInterval());
			aps.setTValues( (double[]) Utilities.resizeArray(aps.getTValues(), aps.getNumPoints()) );
			for (i = 0; i < aps.getNumPoints(); i++)
				aps.getTValues()[i] = OtherTShape.getTValues()[i];
			if (aps.getInterval() > 0.0) {
				aps.setHours(new double[0]);
			} else {
				aps.setHours( (double[]) Utilities.resizeArray(aps.getHours(), aps.getNumPoints()) );
			}
			for (i = 0; i < aps.getNumPoints(); i++)
				aps.getHours()[i] = OtherTShape.getHours()[i];

			for (i = 0; i < aps.getParentClass().getNumProperties(); i++)
				aps.setPropertyValue(i, OtherTShape.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in TShape makeLike: \"" + ShapeName + "\" Not Found.", 611);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement TShape.init", -1);
		return 0;
	}

	public String getCode() {
		return ((TShapeObj) ElementList.getActive()).getName();
	}

	public void setCode(String Value) {
		setActiveTShapeObj(null);
		TShapeObj pTShapeObj = (TShapeObj) ElementList.getFirst();
		while (pTShapeObj != null) {
			if (pTShapeObj.getName().equalsIgnoreCase(Value)) {
				setActiveTShapeObj(pTShapeObj);
				return;
			}

			pTShapeObj = (TShapeObj) ElementList.getNext();
		}

		DSSGlobals.getInstance().doSimpleMsg("TShape: \"" + Value + "\" not found.", 612);
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

			TShapeObj ats = getActiveTShapeObj();

			ats.setTValues( (double[]) Utilities.resizeArray(ats.getTValues(), ats.getNumPoints()) );

			if (ats.getInterval() == 0.0)
				ats.setHours( (double[]) Utilities.resizeArray(ats.getHours(), ats.getNumPoints()) );
			int i = 0;
			while (((s = reader.readLine()) != null) && i < ats.getNumPoints()) {  // TODO: Check zero based indexing
				i += 1;
				/* AuxParser allows commas or white space */
				parser = Globals.getAuxParser();
				parser.setCmdString(s);
				if (ats.getInterval() == 0.0) {
					parser.getNextParam();
					ats.getHours()[i] = parser.makeDouble();
				}
				parser.getNextParam();
				ats.getTValues()[i] = parser.makeDouble();
			}
			fileStream.close();
			dataStream.close();
			reader.close();
			if (i != ats.getNumPoints())  // TODO: Check zero based indexing
				ats.setNumPoints(i);

			fileStream.close();
			dataStream.close();
			reader.close();
		} catch (IOException e) {
			Globals.doSimpleMsg("Error Processing CSV File: \"" + FileName + ". " + e.getMessage(), 604);
			return;
		}
	}

	private void doSngFile(String FileName) {
		throw new UnsupportedOperationException();  // FIXME
	}

	private void doDblFile(String FileName) {
		throw new UnsupportedOperationException();
	}

//	public String getCode() {
//		return null;
//	}
//
//	public void setCode(String Value) {
//
//	}
//
//	private void doCSVFile(String FileName) {
//
//	}
//
//	private void doSngFile(String FileName) {
//
//	}
//
//	private void doDblFile(String FileName) {
//
//	}
//
//	protected void defineProperties() {
//
//	}
//
//	@Override
//	protected int makeLike(String CNName) {
//		return 0;
//	}
//
//	/**
//	 * Uses global parser.
//	 */
//	@Override
//	public int edit() {
//		return 0;
//	}
//
//	@Override
//	public int init(int Handle) {
//		return 0;
//	}
//
//	@Override
//	public int newObject(String ObjName) {
//		return 0;
//	}
//
//	/**
//	 * Find an obj of this class by name.
//	 */
//	@Override
//	public Object find(String ObjName) {
//		return null;
//	}

	/**
	 * Can export this to top for plotting
	 */
	public void TOPExport(String ObjName) {
		throw new UnsupportedOperationException();  // FIXME Implement
	}

	public static void setActiveTShapeObj(TShapeObj activeTShapeObj) {
		ActiveTShapeObj = activeTShapeObj;
	}

	public static TShapeObj getActiveTShapeObj() {
		return ActiveTShapeObj;
	}

}
