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
import com.epri.dss.general.LoadShape;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

/* Superstructure for all LoadShape objects */
public class LoadShapeImpl extends DSSClassImpl implements LoadShape {

	private static LoadShapeObj ActiveLoadShapeObj;

	public LoadShapeImpl() {
		super();
		Class_Name = "LoadShape";
		DSSClassType = DSSClassDefs.DSS_OBJECT;

		setActiveElement(-1);

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;

		NumProperties = LoadShape.NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();


		// define property names
		PropertyName[0] = "npts";     // Number of points to expect
		PropertyName[1] = "interval"; // default = 1.0;
		PropertyName[2] = "mult";     // vector of power multiplier values
		PropertyName[3] = "hour";     // vextor of hour values
		PropertyName[4] = "mean";     // set the mean (otherwise computed)
		PropertyName[5] = "stddev";   // set the std dev (otherwise computed)
		PropertyName[6] = "csvfile";  // Switch input to a csvfile
		PropertyName[7] = "sngfile";  // switch input to a binary file of singles
		PropertyName[8] = "dblfile";  // switch input to a binary file of singles
		PropertyName[9] = "action";   // actions  Normalize
		PropertyName[10] = "qmult";   // Q multiplier
		PropertyName[11] = "UseActual"; // Flag to signify to use actual value
		PropertyName[12] = "Pmax";    // MaxP value
		PropertyName[13] = "Qmax";    // MaxQ
		PropertyName[14] = "sinterval"; // Interval in seconds
		PropertyName[15] = "minterval"; // Interval in minutes

		// define property help values

		PropertyHelp[0] = "Max number of points to expect in load shape vectors. This gets reset to the number of multiplier values found (in files only) if less than specified.";     // Number of points to expect
		PropertyHelp[1] = "Time interval for fixed interval data (hrs). Default = 1. "+
				"If set = 0 then time data (in hours) is expected using either the Hour property or input files. " +CRLF+CRLF+
				"See also \"sinterval\" and \"minterval\"."; // default = 1.0;
		PropertyHelp[2] = "Array of multiplier values for active power (P).  You can also use the syntax: "+CRLF+
				"mult = (file=filename)     !for text file one value per line"+CRLF+
				"mult = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"mult = (sngfile=filename)  !for packed file of singles "+CRLF+CRLF+
				"Note: this property will reset Npts if the  number of values in the files are fewer.";     // vextor of hour values
		PropertyHelp[3] = "Array of hour values. Only necessary to define for variable interval data."+
				" If the data are fixed interval, do not use this property. " +
				"You can also use the syntax: "+CRLF+
					"hour = (file=filename)     !for text file one value per line"+CRLF+
					"hour = (dblfile=filename)  !for packed file of doubles"+CRLF+
					"hour = (sngfile=filename)  !for packed file of singles ";     // vextor of hour values
		PropertyHelp[4] = "Mean of the active power multipliers.  This is computed on demand the first time a "+
				"value is needed.  However, you may set it to another value independently. "+
				"Used for Monte Carlo load simulations.";     // set the mean (otherwise computed)
		PropertyHelp[5] = "Standard deviation of active power multipliers.  This is computed on demand the first time a "+
				"value is needed.  However, you may set it to another value independently."+
				"Is overwritten if you subsequently read in a curve" + CRLF + CRLF +
				"Used for Monte Carlo load simulations.";   // set the std dev (otherwise computed)
		PropertyHelp[6] = "Switch input of active power load curve data to a csv file "+
				"containing (hour, mult) points, or simply (mult) values for fixed time interval data, one per line. " +
				"NOTE: This action may reset the number of points to a lower value.";   // Switch input to a csvfile
		PropertyHelp[7] = "Switch input of active power load curve data to a binary file of singles "+
				"containing (hour, mult) points, or simply (mult) values for fixed time interval data, packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		PropertyHelp[8] = "Switch input of active power load curve data to a binary file of doubles "+
				"containing (hour, mult) points, or simply (mult) values for fixed time interval data, packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";   // switch input to a binary file of singles
		PropertyHelp[9] = "{NORMALIZE | DblSave | SngSave} After defining load curve data, setting action=normalize "+
				"will modify the multipliers so that the peak is 1.0. " +
				"The mean and std deviation are recomputed." +  CRLF + CRLF +
				"Setting action=DblSave or SngSave will cause the present mult and qmult values to be written to " +
				"either a packed file of double or single. The filename is the loadshape name. The mult array will have a "+
				"\"_P\" appended on the file name and the qmult array, if it exists, will have \"_Q\" appended."; // Action
		PropertyHelp[10] = "Array of multiplier values for reactive power (Q).  You can also use the syntax: "+CRLF+
				"qmult = (file=filename)     !for text file one value per line"+CRLF+
				"qmult = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"qmult = (sngfile=filename)  !for packed file of singles ";     // vector of qmultiplier values
		PropertyHelp[11] = "{Yes | No* | True | False*} If true, signals to Load, Generator, or other objects to " +
				"use the return value as the actual kW, kvar value rather than a multiplier. Nominally for AMI data.";
		PropertyHelp[12] = "kW value at the time of max power. Is automatically set upon reading in a loadshape. "+
				"Use this property to override the value automatically computed or to retrieve the value computed.";
		PropertyHelp[13] = "kvar value at the time of max kW power. Is automatically set upon reading in a loadshape. "+
				"Use this property to override the value automatically computed or to retrieve the value computed.";
		PropertyHelp[14] = "Specify fixed interval in SECONDS. Alternate way to specify Interval property.";
		PropertyHelp[15] = "Specify fixed interval in MINUTES. Alternate way to specify Interval property.";



		ActiveProperty = LoadShape.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.setActiveDSSObject(new LoadShapeObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int Result = 0;
		// continue parsing with contents of Parser
		setActiveLoadShapeObj((LoadShapeObj) ElementList.getActive());
		Globals.setActiveDSSObject(getActiveLoadShapeObj());

		LoadShapeObj als = getActiveLoadShapeObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0){
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);

				if ((ParamPointer > 0) && (ParamPointer <= NumProperties))
					als.setPropertyValue(ParamPointer, Param);

				switch (ParamPointer) {  // TODO Check zero based indexing
				case -1:
					Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ als.getName() + "\"", 610);
					break;
				case 0:
					als.setNumPoints(parser.makeInteger());
					break;
				case 1:
					als.setInterval(parser.makeDouble());
					break;
				case 2:
					als.setPMultipliers( (double[]) Utilities.resizeArray(als.getPMultipliers(), als.getNumPoints()) );
					// Allow possible Resetting (to a lower value) of num points when specifying multipliers not Hours
					als.setNumPoints( Utilities.interpretDblArray(Param, als.getNumPoints(), als.getPMultipliers()) );   // parser.parseAsVector(Npts, Multipliers);
					break;
				case 3:
					als.setHours( (double[]) Utilities.resizeArray(als.getHours(), als.getNumPoints()) );
					Utilities.interpretDblArray(Param, als.getNumPoints(), als.getHours());   // parser.parseAsVector(Npts, Hours);
					break;
				case 4:
					als.setMean(parser.makeDouble());
					break;
				case 5:
					als.setStdDev(parser.makeDouble());
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
					switch (Param.toLowerCase().charAt(0)) {
					case 'n':
						als.normalize();
						break;
					case 'd':
						als.saveToDblFile();
						break;
					case 's':
						als.saveToSngFile();
						break;
					}
					break;
				case 10:
					als.setQMultipliers( (double[]) Utilities.resizeArray(als.getQMultipliers(), als.getNumPoints()) );
					Utilities.interpretDblArray(Param, als.getNumPoints(), als.getQMultipliers());  // parser.parseAsVector(Npts, Multipliers);
					break;
				case 11:
					als.setUseActual(Utilities.interpretYesNo(Param));
					break;
				case 12:
					als.setMaxP(parser.makeDouble());
					break;
				case 13:
					als.setMaxQ(parser.makeDouble());
					break;
				case 14:
					als.setInterval(parser.makeDouble() / 3600.0);  // Convert seconds to hr
					break;
				case 15:
					als.setInterval(parser.makeDouble() / 60.0);  // Convert minutes to hr
					break;
				default:
					// Inherited parameters
					classEdit(ActiveLoadShapeObj, ParamPointer - LoadShape.NumPropsThisClass);
					break;
				}

				switch (ParamPointer) {
				case 2:  // TODO Check zero based indexing
					als.setStdDevCalculated(false);   // now calculated on demand
					als.setArrayPropertyIndex(ParamPointer);
					als.setNumPoints(als.getNumPoints());  // Keep Properties in order for save command
					break;
				case 6:
					als.setStdDevCalculated(false);
					als.setArrayPropertyIndex(ParamPointer);
					als.setNumPoints(als.getNumPoints());
					break;
				case 7:
					als.setStdDevCalculated(false);
					als.setArrayPropertyIndex(ParamPointer);
					als.setNumPoints(als.getNumPoints());
					break;
				case 8:
					als.setStdDevCalculated(false);
					als.setArrayPropertyIndex(ParamPointer);
					als.setNumPoints(als.getNumPoints());
					break;
				case 10:
					als.setStdDevCalculated(false);
					als.setArrayPropertyIndex(ParamPointer);
					als.setNumPoints(als.getNumPoints());
					break;
				}

				ParamName = parser.getNextParam();
				Param = parser.makeString();
			}
		}

		if (als.getPMultipliers() != null)
			als.setMaxPandQ();

		return Result;
	}

	/**
	 * Find an obj of this class by name.
	 */
	public Object find(String ObjName) {
		if ((ObjName.length() == 0) || (ObjName.equals("none"))) {
			return null;
		} else {
			return super.find(ObjName);
		}
	}

	protected int makeLike(String ShapeName) {
		int Result = 0;
		/* See if we can find this line code in the present collection */
		LoadShapeObj OtherLoadShape = (LoadShapeObj) find(ShapeName);
		if (OtherLoadShape != null) {
			LoadShapeObj als = getActiveLoadShapeObj();

			als.setNumPoints(OtherLoadShape.getNumPoints());
			als.setInterval(OtherLoadShape.getInterval());
			als.setPMultipliers( (double[]) Utilities.resizeArray(als.getPMultipliers(), als.getNumPoints()) );
			for (int i = 0; i < als.getNumPoints(); i++)
				als.getPMultipliers()[i] = OtherLoadShape.getPMultipliers()[i];
			if (OtherLoadShape.getQMultipliers() != null)
				als.setQMultipliers( (double[]) Utilities.resizeArray(als.getQMultipliers(), als.getNumPoints()) );
			als.setQMultipliers( (double[]) Utilities.resizeArray(als.getQMultipliers(), als.getNumPoints()) );
			for (int i = 0; i < als.getNumPoints(); i++)
				als.getQMultipliers()[i] = OtherLoadShape.getQMultipliers()[i];
			if (als.getInterval() > 0.0) {
				als.setHours(new double[0]);
			} else {
				als.setHours( (double[]) Utilities.resizeArray(als.getHours(), als.getNumPoints()) );
				for (int i = 0; i < als.getNumPoints(); i++)
					als.getHours()[i] = OtherLoadShape.getHours()[i];
			}
			als.setMaxPandQ();
			als.setUseActual(OtherLoadShape.isUseActual());


			/*als.setMaxP(OtherLoadShape.getMaxP());
			als.setMaxQ(OtherLoadShape.getMaxQ());
			als.setMean(OtherLoadShape.getMean());
			als.setStdDev(OtherLoadShape.getStdDev());*/

			for (int i = 0; i < als.getParentClass().getNumProperties(); i++)
				als.setPropertyValue(i, OtherLoadShape.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in LoadShape MakeLike: \"" + ShapeName + "\" Not Found.", 611);
		}

		return Result;
	}

	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement LoadShape.init()", -1);
		return 0;
	}

	/**
	 * Returns active LoadShape string.
	 */
	public String getCode() {
		LoadShapeObj pShape = (LoadShapeObj) ElementList.getActive();
		return pShape.getName();
	}

	/**
	 * Sets the active LoadShape.
	 */
	public void setCode(String Value) {
		setActiveLoadShapeObj(null);

		LoadShapeObj pShape;
		for (int i = 0; i < ElementList.size(); i++) {
			pShape = (LoadShapeObj) ElementList.get(i);
			if (pShape.getName().equals(Value)) {
				setActiveLoadShapeObj(pShape);
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("LoadShape: \"" + Value + "\" not found.", 612);
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

			LoadShapeObj als = getActiveLoadShapeObj();

			als.setPMultipliers( (double[]) Utilities.resizeArray(als.getPMultipliers(), als.getNumPoints()) );

			if (als.getInterval() == 0.0)
				als.setHours( (double[]) Utilities.resizeArray(als.getHours(), als.getNumPoints()) );
			int i = 0;
			while (((s = reader.readLine()) != null) && i < als.getNumPoints()) {  // TODO: Check zero based indexing
				i += 1;
				/* AuxParser allows commas or white space */
				parser = Globals.getAuxParser();
				parser.setCmdString(s);
				if (als.getInterval() == 0.0) {
					parser.getNextParam();
					als.getHours()[i] = parser.makeDouble();
				}
				parser.getNextParam();
				als.getPMultipliers()[i] = parser.makeDouble();
			}
			fileStream.close();
			dataStream.close();
			reader.close();
			if (i != als.getNumPoints())
				als.setNumPoints(i);

			fileStream.close();
			dataStream.close();
			reader.close();
		} catch (IOException e) {
			Globals.doSimpleMsg("Error Processing CSV File: \"" + FileName + ". " + e.getMessage(), 604);
			return;
		}
	}

	private void doSngFile(String FileName) {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

	private void doDblFile(String FileName) {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

	public void TOPExport(String ObjName) {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

	public static LoadShapeObj getActiveLoadShapeObj() {
		return ActiveLoadShapeObj;
	}

	public static void setActiveLoadShapeObj(LoadShapeObj activeLoadShapeObj) {
		ActiveLoadShapeObj = activeLoadShapeObj;
	}

}
