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

	private static TShapeObj activeTShapeObj;

	public TShapeImpl() {
		super();
		this.className = "TShape";
		this.classType = DSSClassDefs.DSS_OBJECT;

		this.activeElement = -1;

		defineProperties();

		String[] commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		final String CRLF = DSSGlobals.CRLF;

		numProperties = TShape.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0]  = "npts";      // number of points to expect
		propertyName[1]  = "interval";  // default = 1.0;
		propertyName[2]  = "temp";      // vector of temperature values
		propertyName[3]  = "hour";      // vector of hour values
		propertyName[4]  = "mean";      // set the mean temp (otherwise computed)
		propertyName[5]  = "stddev";    // set the std dev of the temp (otherwise computed)
		propertyName[6]  = "csvfile";   // switch input to a csvfile
		propertyName[7]  = "sngfile";   // switch input to a binary file of singles
		propertyName[8]  = "dblfile";   // switch input to a binary file of singles
		propertyName[9]  = "sinterval"; // interval in seconds
		propertyName[10] = "minterval"; // interval in minutes
		propertyName[11] = "action";    // interval in minutes

		// define property help values
		propertyHelp[0] = "Max number of points to expect in temperature shape vectors. This gets reset to the number of temperature values " +
				"found if less than specified.";  // Number of points to expect
		propertyHelp[1] = "Time interval for fixed interval data, hrs. Default = 1. "+
				"If set = 0 then time data (in hours) is expected using either the Hour property or input files. " +CRLF+CRLF+
				"See also \"sinterval\" and \"minterval\".";  // default = 1.0;
		propertyHelp[2] = "Array of temperature values.  Units should be compatible with the object using the data. " +
				"You can also use the syntax: "+CRLF+
				"Temp = (file=filename)     !for text file one value per line"+CRLF+
				"Temp = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"Temp = (sngfile=filename)  !for packed file of singles "+CRLF+CRLF+
				"Note: this property will reset Npts if the  number of values in the files are fewer.";  // vextor of hour values
		propertyHelp[3] = "Array of hour values. Only necessary to define this property for variable interval data."+
				" If the data are fixed interval, do not use this property. " +
				"You can also use the syntax: "+CRLF+
				"hour = (file=filename)     !for text file one value per line"+CRLF+
				"hour = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"hour = (sngfile=filename)  !for packed file of singles ";  // vextor of hour values
		propertyHelp[4] = "Mean of the temperature curve values.  This is computed on demand the first time a "+
				"value is needed.  However, you may set it to another value independently. "+
				"Used for Monte Carlo load simulations.";  // set the mean (otherwise computed)
		propertyHelp[5] = "Standard deviation of the temperature.  This is computed on demand the first time a "+
				"value is needed.  However, you may set it to another value independently."+
				"Is overwritten if you subsequently read in a curve" + CRLF + CRLF +
				"Used for Monte Carlo load simulations.";  // set the std dev (otherwise computed)
		propertyHelp[6] = "Switch input of temperature curve data to a csv file "+
				"containing (hour, Temp) points, or simply (Temp) values for fixed time interval data, one per line. " +
				"NOTE: This action may reset the number of points to a lower value.";  // Switch input to a csvfile
		propertyHelp[7] = "Switch input of temperature curve data to a binary file of singles "+
				"containing (hour, Temp) points, or simply (Temp) values for fixed time interval data, packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		propertyHelp[8] = "Switch input of temperature curve data to a binary file of doubles "+
				"containing (hour, Temp) points, or simply (Temp) values for fixed time interval data, packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		propertyHelp[9] ="Specify fixed interval in SECONDS. Alternate way to specify Interval property.";
		propertyHelp[10] ="Specify fixed interval in MINUTES. Alternate way to specify Interval property.";
		propertyHelp[11] ="{DblSave | SngSave} After defining temperature curve data... " +
				"Setting action=DblSave or SngSave will cause the present \"Temp\" values to be written to " +
				"either a packed file of double or single. The filename is the TShape name. ";  // Action

		activeProperty = TShape.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSSGlobals globals = DSSGlobals.getInstance();
		globals.setActiveDSSObject(new TShapeObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		DSSGlobals globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of parser
		setActiveTShapeObj((TShapeObj) elementList.getActive());
		globals.setActiveDSSObject(getActiveTShapeObj());

		TShapeObj ats = getActiveTShapeObj();

		int paramPointer = 0;
		String paramName = parser.getNextParam();

		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if ((paramPointer >= 0) && (paramPointer < numProperties))
				ats.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				globals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ ats.getName() + "\"", 610);
				break;
			case 0:
				ats.setNumPoints(parser.makeInteger());
				break;
			case 1:
				ats.setInterval(parser.makeDouble());
				break;
			case 2:
				ats.setTValues( (double[]) Utilities.resizeArray(ats.getTValues(), ats.getNumPoints()) );
				// allow possible resetting (to a lower value) of num points when specifying temperatures not hours
				ats.setNumPoints( Utilities.interpretDblArray(param, ats.getNumPoints(), ats.getTValues()) );   //parser.parseAsVector(Npts, Temps);
				break;
			case 3:
				ats.setHours( (double[]) Utilities.resizeArray(ats.getHours(), ats.getNumPoints()) );
				Utilities.interpretDblArray(param, ats.getNumPoints(), ats.getHours());   //parser.parseAsVector(Npts, Hours);
				break;
			case 4:
				ats.setMean(parser.makeDouble());
				break;
			case 5:
				ats.setStdDev(parser.makeDouble());
				break;
			case 6:
				doCSVFile(param);
				break;
			case 7:
				doSngFile(param);
				break;
			case 8:
				doDblFile(param);
				break;
			case 9:
				ats.setInterval(parser.makeDouble() / 3600.0);  // convert seconds to hr
				break;
			case 10:
				ats.setInterval(parser.makeDouble() / 60.0);  // convert minutes to hr
				break;
			case 11:
				switch (param.toLowerCase().charAt(0)) {
				case 'd':
					ats.saveToDblFile();
					break;
				case 's':
					ats.saveToSngFile();
					break;
				}
				break;
			default:
				// inherited parameters
				classEdit(getActiveTShapeObj(), paramPointer - TShape.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 2:
				ats.setStdDevCalculated(false);  // now calculated on demand
				ats.setArrayPropertyIndex(paramPointer);
				ats.setNumPoints(ats.getNumPoints());  //keep properties in order for save command  FIXME
				break;
			case 6:
				ats.setStdDevCalculated(false);  // now calculated on demand
				ats.setArrayPropertyIndex(paramPointer);
				ats.setNumPoints(ats.getNumPoints());  // keep properties in order for save command
				break;
			case 7:
				ats.setStdDevCalculated(false);  // now calculated on demand
				ats.setArrayPropertyIndex(paramPointer);
				ats.setNumPoints(ats.getNumPoints());  // keep properties in order for save command
				break;
			case 8:
				ats.setStdDevCalculated(false);  // now calculated on demand
				ats.setArrayPropertyIndex(paramPointer);
				ats.setNumPoints(ats.getNumPoints());  // keep properties in order for save command
				break;
			}

			paramName = parser.getNextParam();
			param     = parser.makeString();
		}
		return result;
	}

	@Override
	public Object find(String objName) {
		if ((objName.length() == 0) || objName.equalsIgnoreCase("none")) {
			return null;
		} else {
			return super.find(objName);
		}
	}

	@Override
	protected int makeLike(String shapeName) {
		TShapeObj otherTShape;
		int i, result = 0;

		/* See if we can find this line code in the present collection */
		otherTShape = (TShapeObj) find(shapeName);
		if (otherTShape != null) {
			TShapeObj aps = getActiveTShapeObj();

			aps.setNumPoints(otherTShape.getNumPoints());
			aps.setInterval(otherTShape.getInterval());
			aps.setTValues( (double[]) Utilities.resizeArray(aps.getTValues(), aps.getNumPoints()) );
			for (i = 0; i < aps.getNumPoints(); i++)
				aps.getTValues()[i] = otherTShape.getTValues()[i];
			if (aps.getInterval() > 0.0) {
				aps.setHours(new double[0]);
			} else {
				aps.setHours( (double[]) Utilities.resizeArray(aps.getHours(), aps.getNumPoints()) );
			}
			for (i = 0; i < aps.getNumPoints(); i++)
				aps.getHours()[i] = otherTShape.getHours()[i];

			for (i = 0; i < aps.getParentClass().getNumProperties(); i++)
				aps.setPropertyValue(i, otherTShape.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in TShape makeLike: \"" + shapeName + "\" not found.", 611);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement TShape.init", -1);
		return 0;
	}

	public String getCode() {
		return ((TShapeObj) elementList.getActive()).getName();
	}

	public void setCode(String value) {
		setActiveTShapeObj(null);
		TShapeObj pTShapeObj = (TShapeObj) elementList.getFirst();
		while (pTShapeObj != null) {
			if (pTShapeObj.getName().equalsIgnoreCase(value)) {
				setActiveTShapeObj(pTShapeObj);
				return;
			}

			pTShapeObj = (TShapeObj) elementList.getNext();
		}

		DSSGlobals.getInstance().doSimpleMsg("TShape: \"" + value + "\" not found.", 612);
	}

	private void doCSVFile(String fileName) {
		FileInputStream fis;
		DataInputStream dis;
		BufferedReader br;

		String s;
		Parser parser;
		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			br = new BufferedReader(new InputStreamReader(dis));

			TShapeObj ats = getActiveTShapeObj();

			ats.setTValues( (double[]) Utilities.resizeArray(ats.getTValues(), ats.getNumPoints()) );

			if (ats.getInterval() == 0.0)
				ats.setHours( (double[]) Utilities.resizeArray(ats.getHours(), ats.getNumPoints()) );
			int i = 0;
			while (((s = br.readLine()) != null) && i < ats.getNumPoints()) {  // TODO: Check zero based indexing
				i += 1;
				/* Aux parser allows commas or white space */
				parser = globals.getAuxParser();
				parser.setCmdString(s);
				if (ats.getInterval() == 0.0) {
					parser.getNextParam();
					ats.getHours()[i] = parser.makeDouble();
				}
				parser.getNextParam();
				ats.getTValues()[i] = parser.makeDouble();
			}
			fis.close();
			dis.close();
			br.close();
			if (i != ats.getNumPoints())  // TODO: Check zero based indexing
				ats.setNumPoints(i);

			fis.close();
			dis.close();
			br.close();
		} catch (IOException e) {
			globals.doSimpleMsg("Error processing CSV file: \"" + fileName + ". " + e.getMessage(), 604);
			return;
		}
	}

	private void doSngFile(String fileName) {
		throw new UnsupportedOperationException();  // FIXME
	}

	private void doDblFile(String fileName) {
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
	public void TOPExport(String objName) {
		throw new UnsupportedOperationException();  // FIXME Implement
	}

	public static void setActiveTShapeObj(TShapeObj TShapeObj) {
		activeTShapeObj = TShapeObj;
	}

	public static TShapeObj getActiveTShapeObj() {
		return activeTShapeObj;
	}

}
