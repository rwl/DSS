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
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

import static com.ncond.dss.common.Util.interpretDblArray;
import static com.ncond.dss.common.Util.interpretYesNo;
import static com.ncond.dss.common.Util.resizeArray;


public class LoadShape extends DSSClass {

	public static final int NumPropsThisClass = 18;

	public static LoadShapeObj activeLoadShapeObj;

	public LoadShape() {
		super();
		className = "LoadShape";
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

		numProperties = LoadShape.NumPropsThisClass;
		countProperties();   // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0] = "npts";     // number of points to expect
		propertyName[1] = "interval"; // default = 1.0;
		propertyName[2] = "mult";     // vector of power multiplier values
		propertyName[3] = "hour";     // vextor of hour values
		propertyName[4] = "mean";     // set the mean (otherwise computed)
		propertyName[5] = "stddev";   // set the std dev (otherwise computed)
		propertyName[6] = "csvfile";  // switch input to a csvfile
		propertyName[7] = "sngfile";  // switch input to a binary file of singles
		propertyName[8] = "dblfile";  // switch input to a binary file of singles
		propertyName[9] = "action";   // actions  Normalize
		propertyName[10] = "qmult";   // Q multiplier
		propertyName[11] = "UseActual"; // flag to signify to use actual value
		propertyName[12] = "Pmax";    // maxP value
		propertyName[13] = "Qmax";    // maxQ
		propertyName[14] = "sinterval"; // interval in seconds
		propertyName[15] = "minterval"; // interval in minutes
		propertyName[16] = "Pbase";  // for normalization, use peak if 0
		propertyName[17] = "Qbase";  // for normalization, use peak if 0

		// define property help values
		propertyHelp[0] = "Max number of points to expect in load shape vectors. This gets reset to the number of multiplier values found (in files only) if less than specified.";     // Number of points to expect
		propertyHelp[1] = "Time interval for fixed interval data (hrs). Default = 1. "+
				"If set = 0 then time data (in hours) is expected using either the Hour property or input files. " +CRLF+CRLF+
				"See also \"sinterval\" and \"minterval\"."; // default = 1.0;
		propertyHelp[2] = "Array of multiplier values for active power (P).  You can also use the syntax: "+CRLF+
				"mult = (file=filename)     !for text file one value per line"+CRLF+
				"mult = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"mult = (sngfile=filename)  !for packed file of singles "+CRLF+CRLF+
				"mult = (file=MyCSVFile.CSV, col=3, header=yes)  !for multicolumn CSV files "+CRLF+CRLF+
				"Note: this property will reset Npts if the  number of values in the files are fewer.";     // vextor of hour values
		propertyHelp[3] = "Array of hour values. Only necessary to define for variable interval data."+
				" If the data are fixed interval, do not use this property. " +
				"You can also use the syntax: "+CRLF+
				"hour = (file=filename)     !for text file one value per line"+CRLF+
				"hour = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"hour = (sngfile=filename)  !for packed file of singles ";     // vextor of hour values
		propertyHelp[4] = "Mean of the active power multipliers.  This is computed on demand the first time a "+
				"value is needed.  However, you may set it to another value independently. "+
				"Used for Monte Carlo load simulations.";     // set the mean (otherwise computed)
		propertyHelp[5] = "Standard deviation of active power multipliers.  This is computed on demand the first time a "+
				"value is needed.  However, you may set it to another value independently."+
				"Is overwritten if you subsequently read in a curve" + CRLF + CRLF +
				"Used for Monte Carlo load simulations.";   // set the std dev (otherwise computed)
		propertyHelp[6] = "Switch input of active power load curve data to a csv file "+
				"containing (hour, mult) points, or simply (mult) values for fixed time interval data, one per line. " +
				"NOTE: This action may reset the number of points to a lower value.";   // Switch input to a csvfile
		propertyHelp[7] = "Switch input of active power load curve data to a binary file of singles "+
				"containing (hour, mult) points, or simply (mult) values for fixed time interval data, packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		propertyHelp[8] = "Switch input of active power load curve data to a binary file of doubles "+
				"containing (hour, mult) points, or simply (mult) values for fixed time interval data, packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";   // switch input to a binary file of singles
		propertyHelp[9] = "{NORMALIZE | DblSave | SngSave} After defining load curve data, setting action=normalize "+
				"will modify the multipliers so that the peak is 1.0. " +
				"The mean and std deviation are recomputed." +  CRLF + CRLF +
				"Setting action=DblSave or SngSave will cause the present mult and qmult values to be written to " +
				"either a packed file of double or single. The filename is the loadshape name. The mult array will have a "+
				"\"_P\" appended on the file name and the qmult array, if it exists, will have \"_Q\" appended."; // Action
		propertyHelp[10] = "Array of multiplier values for reactive power (Q).  You can also use the syntax: "+CRLF+
				"qmult = (file=filename)     !for text file one value per line"+CRLF+
				"qmult = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"qmult = (sngfile=filename)  !for packed file of singles ";     // vector of qmultiplier values
		propertyHelp[11] = "{Yes | No* | True | False*} If true, signals to Load, Generator, or other objects to " +
				"use the return value as the actual kW, kvar value rather than a multiplier. Nominally for AMI data.";
		propertyHelp[12] = "kW value at the time of max power. Is automatically set upon reading in a loadshape. "+
				"Use this property to override the value automatically computed or to retrieve the value computed.";
		propertyHelp[13] = "kvar value at the time of max kW power. Is automatically set upon reading in a loadshape. "+
				"Use this property to override the value automatically computed or to retrieve the value computed.";
		propertyHelp[14] = "Specify fixed interval in SECONDS. Alternate way to specify Interval property.";
		propertyHelp[15] = "Specify fixed interval in MINUTES. Alternate way to specify Interval property.";
		propertyHelp[16] = "Base P value for normalization. Default is zero, meaning the peak will be used.";
		propertyHelp[17] = "Base Q value for normalization. Default is zero, meaning the peak will be used.";

		activeProperty = LoadShape.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeDSSObject = new LoadShapeObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeLoadShapeObj = (LoadShapeObj) elementList.getActive();
		DSS.activeDSSObject = activeLoadShapeObj;

		LoadShapeObj elem = activeLoadShapeObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.stringValue();

		while (param.length() > 0) {
			if (paramName.length() == 0){
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
				elem.setNumPoints(parser.integerValue());
				break;
			case 1:
				elem.setInterval(parser.doubleValue());
				break;
			case 2:
				elem.setPMultipliers(resizeArray(elem.getPMultipliers(), elem.getNumPoints()));
				// allow possible resetting (to a lower value) of num points when specifying multipliers not hours
				elem.setNumPoints(interpretDblArray(param, elem.getNumPoints(), elem.getPMultipliers()));
				break;
			case 3:
				elem.setHours(resizeArray(elem.getHours(), elem.getNumPoints()));
				interpretDblArray(param, elem.getNumPoints(), elem.getHours());
				elem.setInterval(0.0);
				break;
			case 4:
				elem.setMean(parser.doubleValue());
				break;
			case 5:
				elem.setStdDev(parser.doubleValue());
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
				switch (param.toLowerCase().charAt(0)) {
				case 'n':
					elem.normalize();
					break;
				case 'd':
					elem.saveToDblFile();
					break;
				case 's':
					elem.saveToSngFile();
					break;
				}
				break;
			case 10:
				elem.setQMultipliers(resizeArray(elem.getQMultipliers(), elem.getNumPoints()));
				interpretDblArray(param, elem.getNumPoints(), elem.getQMultipliers());
				break;
			case 11:
				elem.setUseActual(interpretYesNo(param));
				break;
			case 12:
				elem.setMaxP(parser.doubleValue());
				break;
			case 13:
				elem.setMaxQ(parser.doubleValue());
				break;
			case 14:
				elem.setInterval(parser.doubleValue() / 3600.0);  // convert seconds to hr
				break;
			case 15:
				elem.setInterval(parser.doubleValue() / 60.0);    // convert minutes to hr
				break;
			case 16:
				elem.setBaseP(parser.doubleValue());
				break;
			case 17:
				elem.setBaseQ(parser.doubleValue());
				break;
			default:
				// inherited parameters
				classEdit(activeLoadShapeObj, paramPointer - LoadShape.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 2:
			case 6:
			case 7:
			case 8:
			case 10:
				elem.setStdDevCalculated(false);   // now calculated on demand
				elem.setArrayPropertyIndex(paramPointer);
				elem.setNumPoints(elem.getNumPoints());  // keep properties in order for save command
				break;
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		if (elem.getPMultipliers() != null) elem.setMaxPandQ();

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

	@Override
	protected int makeLike(String shapeName) {
		int success = 0;

		/* See if we can find this line code in the present collection */
		LoadShapeObj other = (LoadShapeObj) find(shapeName);

		if (other != null) {
			LoadShapeObj elem = activeLoadShapeObj;

			elem.setNumPoints(other.getNumPoints());
			elem.setInterval(other.getInterval());
			elem.setPMultipliers(resizeArray(elem.getPMultipliers(), elem.getNumPoints()));
			for (int i = 0; i < elem.getNumPoints(); i++)
				elem.setPMultiplier(i, other.getPMultiplier(i));

			if (other.getQMultipliers() != null)
				elem.setQMultipliers(resizeArray(elem.getQMultipliers(), elem.getNumPoints()));
			elem.setQMultipliers( resizeArray(elem.getQMultipliers(), elem.getNumPoints()) );
			for (int i = 0; i < elem.getNumPoints(); i++)
				elem.setQMultiplier(i, other.getQMultiplier(i));

			if (elem.getInterval() > 0.0) {
				elem.setHours(new double[0]);
			} else {
				elem.setHours( resizeArray(elem.getHours(), elem.getNumPoints()) );
				for (int i = 0; i < elem.getNumPoints(); i++)
					elem.setHour(i, other.getHour(i));
			}
			elem.setMaxPandQ();
			elem.setUseActual(other.isUseActual());
			elem.setBaseP(other.getBaseP());
			elem.setBaseQ(other.getBaseQ());

			/*als.setMaxP(OtherLoadShape.getMaxP());
			als.setMaxQ(OtherLoadShape.getMaxQ());
			als.setMean(OtherLoadShape.getMean());
			als.setStdDev(OtherLoadShape.getStdDev());*/

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in LoadShape makeLike: \"" + shapeName + "\" not found.", 611);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement LoadShape.init()", -1);
		return 0;
	}

	/**
	 * Returns active LoadShape string.
	 */
	public String getCode() {
		return ((LoadShapeObj) elementList.getActive()).getName();
	}

	/**
	 * Sets the active LoadShape.
	 */
	public void setCode(String value) {
		activeLoadShapeObj = null;

		LoadShapeObj shape;
		for (int i = 0; i < elementList.size(); i++) {
			shape = (LoadShapeObj) elementList.get(i);
			if (shape.getName().equalsIgnoreCase(value)) {
				activeLoadShapeObj = shape;
				return;
			}
		}

		DSS.doSimpleMsg("LoadShape: \"" + value + "\" not found", 612);
	}

	private void doCSVFile(String fileName) {
		FileReader fr;
		BufferedReader br;
		String s;
		Parser parser;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			LoadShapeObj elem = activeLoadShapeObj;

			elem.setPMultipliers(resizeArray(elem.getPMultipliers(), elem.getNumPoints()));

			if (elem.getInterval() == 0.0)
				elem.setHours(resizeArray(elem.getHours(), elem.getNumPoints()));

			int i = 0;
			while (((s = br.readLine()) != null) && i < elem.getNumPoints()) {
				/* aux parser allows commas or white space */
				parser = DSS.auxParser;
				parser.setCommand(s);
				if (elem.getInterval() == 0.0) {
					parser.getNextParam();
					elem.getHours()[i] = parser.doubleValue();
				}
				parser.getNextParam();
				elem.getPMultipliers()[i] = parser.doubleValue();
				i += 1;
			}
			br.close();
			fr.close();
			if (i != elem.getNumPoints())
				elem.setNumPoints(i);

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing CSV file: \"" + fileName + ". " + e.getMessage(), 604);
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

		LoadShapeObj elem = activeLoadShapeObj;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			elem.setPMultipliers(resizeArray(elem.getPMultipliers(), elem.getNumPoints()));

			if (elem.getInterval() == 0.0)
				elem.setHours(resizeArray(elem.getHours(), elem.getNumPoints()));

			i = 0;
			s = "";
			while (s != null && i < elem.getNumPoints()) {
				if (elem.getInterval() == 0.0) {
					s = br.readLine();
					elem.setHour(i, Double.parseDouble(s));
				}
				s = br.readLine();
				elem.setPMultiplier(i, Double.parseDouble(s));
				i += 1;
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing growth shape file \"" + fileName + ": " + e.getMessage(), 604);
			return;
		}
	}

	public void TOPExport(String objName) {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

}
