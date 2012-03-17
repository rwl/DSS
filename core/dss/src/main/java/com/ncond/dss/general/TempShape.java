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

public class TempShape extends DSSClass {

	public static final int NumPropsThisClass = 12;

	public static TempShapeObj activeTempShapeObj;

	public TempShape() {
		super();
		className = "TShape";
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
		final String CRLF = DSS.CRLF;

		numProperties = TempShape.NumPropsThisClass;
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

		activeProperty = TempShape.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeDSSObject = new TempShapeObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeTempShapeObj = (TempShapeObj) elementList.getActive();
		DSS.activeDSSObject = activeTempShapeObj;

		TempShapeObj elem = activeTempShapeObj;

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
						getClassName() + "." + elem.getName() + "\"", 610);
				break;
			case 0:
				elem.setNumPoints(parser.makeInteger());
				break;
			case 1:
				elem.setInterval(parser.makeDouble());
				break;
			case 2:
				elem.setTempValues(Util.resizeArray(elem.getTempValues(), elem.getNumPoints()));
				// allow possible resetting (to a lower value) of num points when specifying temperatures not hours
				elem.setNumPoints(Util.interpretDblArray(param, elem.getNumPoints(), elem.getTempValues()));
				break;
			case 3:
				elem.setHours(Util.resizeArray(elem.getHours(), elem.getNumPoints()));
				Util.interpretDblArray(param, elem.getNumPoints(), elem.getHours());
				break;
			case 4:
				elem.setMean(parser.makeDouble());
				break;
			case 5:
				elem.setStdDev(parser.makeDouble());
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
				elem.setInterval(parser.makeDouble() / 3600.0);  // convert seconds to hr
				break;
			case 10:
				elem.setInterval(parser.makeDouble() / 60.0);  // convert minutes to hr
				break;
			case 11:
				switch (param.toLowerCase().charAt(0)) {
				case 'd': elem.saveToDblFile(); break;
				case 's': elem.saveToSngFile(); break;
				}
				break;
			default:
				// inherited parameters
				classEdit(activeTempShapeObj, paramPointer - TempShape.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 2:
			case 6:
			case 7:
			case 8:
				elem.setStdDevCalculated(false);  // now calculated on demand
				elem.setArrayPropertyIndex(paramPointer);
				elem.setNumPoints(elem.getNumPoints());  // keep properties in order for save command
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}
		return 0;
	}

	@Override
	public DSSObject find(String objName) {
		if (objName.length() == 0 || objName.equalsIgnoreCase("none")) {
			return null;
		} else {
			return super.find(objName);
		}
	}

	@Override
	protected int makeLike(String shapeName) {
		TempShapeObj other, elem;
		int i, success = 0;

		/* See if we can find this line code in the present collection */
		other = (TempShapeObj) find(shapeName);

		if (other != null) {
			elem = activeTempShapeObj;

			elem.setNumPoints(other.getNumPoints());
			elem.setInterval(other.getInterval());
			elem.setTempValues(Util.resizeArray(elem.getTempValues(), elem.getNumPoints()));

			for (i = 0; i < elem.getNumPoints(); i++)
				elem.getTempValues()[i] = other.getTempValues()[i];

			if (elem.getInterval() > 0.0) {
				elem.setHours(new double[0]);
			} else {
				elem.setHours( Util.resizeArray(elem.getHours(), elem.getNumPoints()) );
			}

			for (i = 0; i < elem.getNumPoints(); i++)
				elem.getHours()[i] = other.getHours()[i];

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in TShape makeLike: \"" + shapeName + "\" not found.", 611);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement TShape.init", -1);
		return 0;
	}

	public String getCode() {
		return ((TempShapeObj) elementList.getActive()).getName();
	}

	public void setCode(String value) {
		activeTempShapeObj = null;
		TempShapeObj elem = (TempShapeObj) elementList.getFirst();
		while (elem != null) {
			if (elem.getName().equalsIgnoreCase(value)) {
				activeTempShapeObj = elem;
				return;
			}

			elem = (TempShapeObj) elementList.getNext();
		}

		DSS.doSimpleMsg("TShape: \"" + value + "\" not found", 612);
	}

	private void doCSVFile(String fileName) {
		FileReader fr;
		BufferedReader br;

		String s;
		Parser parser;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			TempShapeObj elem = activeTempShapeObj;

			elem.setTempValues(Util.resizeArray(elem.getTempValues(), elem.getNumPoints()));

			if (elem.getInterval() == 0.0)
				elem.setHours(Util.resizeArray(elem.getHours(), elem.getNumPoints()));

			int i = 0;
			while (((s = br.readLine()) != null) && i < elem.getNumPoints()) {
				/* Aux parser allows commas or white space */
				parser = DSS.auxParser;
				parser.setCmdString(s);
				if (elem.getInterval() == 0.0) {
					parser.getNextParam();
					elem.getHours()[i] = parser.makeDouble();
				}
				parser.getNextParam();
				elem.getTempValues()[i] = parser.makeDouble();
				i += 1;
			}

			if (i != elem.getNumPoints() - 1)
				elem.setNumPoints(i);

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing CSV file \"" + fileName + "\": " + e.getMessage(), 604);
			return;
		}
	}

	private void doSngFile(String fileName) {
		throw new UnsupportedOperationException();  // FIXME
	}

	private void doDblFile(String fileName) {
		FileReader fr;
		BufferedReader br;
		int i;
		String s;

		TempShapeObj elem = activeTempShapeObj;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			elem.setTempValues(Util.resizeArray(elem.getTempValues(), elem.getNumPoints()));
			if (elem.getInterval() == 0.0)
				elem.setHours(Util.resizeArray(elem.getHours(), elem.getNumPoints()));

			i = 0;
			s = "";
			while (s != null && i < elem.getNumPoints()) {
				if (elem.getInterval() == 0.0) {
					s = br.readLine();
					elem.getHours()[i] = Double.parseDouble(s);
				}
				s = br.readLine();
				elem.getTempValues()[i] = Double.parseDouble(s);
				i += 1;
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing price shape file \"" + fileName + "\": " + e.getMessage(), 604);
			return;
		}
	}

	/**
	 * Can export this to top for plotting
	 */
	public void TOPExport(String objName) {
		throw new UnsupportedOperationException();  // FIXME Implement
	}

}
