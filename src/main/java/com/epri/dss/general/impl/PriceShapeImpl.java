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
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.PriceShape;
import com.epri.dss.general.PriceShapeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class PriceShapeImpl extends DSSClassImpl implements PriceShape {

	private static PriceShapeObj ActivePriceShapeObj;

	public PriceShapeImpl() {
		super();
		this.Class_Name   = "PriceShape";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;

		this.ActiveElement = -1;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		final String CRLF = DSSGlobals.CRLF;

		NumProperties = PriceShape.NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();


		// Define property names
		PropertyName[0]  = "npts";     // Number of points to expect
		PropertyName[1]  = "interval"; // default = 1.0;
		PropertyName[2]  = "price";     // vector of price values
		PropertyName[3]  = "hour";     // vector of hour values
		PropertyName[4]  = "mean";     // set the mean Price (otherwise computed)
		PropertyName[5]  = "stddev";   // set the std dev of the Price (otherwise computed)
		PropertyName[6]  = "csvfile";  // Switch input to a csvfile
		PropertyName[7]  = "sngfile";  // switch input to a binary file of singles
		PropertyName[8]  = "dblfile";    // switch input to a binary file of singles
		PropertyName[9] = "sinterval"; // Interval in seconds
		PropertyName[10] = "minterval"; // Interval in minutes
		PropertyName[11] = "action";    //

		// Define property help values
		PropertyHelp[0] = "Max number of points to expect in price shape vectors. This gets reset to the number of Price values " +
				"found if less than specified.";  // Number of points to expect
		PropertyHelp[1] = "Time interval for fixed interval data, hrs. Default = 1. "+
				"If set = 0 then time data (in hours) is expected using either the Hour property or input files. " +CRLF+CRLF+
				"See also \"sinterval\" and \"minterval\".";  // default = 1.0;
		PropertyHelp[2] = "Array of Price values.  Units should be compatible with the object using the data. " +
				"You can also use the syntax: "+CRLF+
				"Price = (file=filename)     !for text file one value per line"+CRLF+
				"Price = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"Price = (sngfile=filename)  !for packed file of singles "+CRLF+CRLF+
				"Note: this property will reset Npts if the  number of values in the files are fewer.";  // vextor of hour values
		PropertyHelp[3] = "Array of hour values. Only necessary to define this property for variable interval data."+
				" If the data are fixed interval, do not use this property. " +
				"You can also use the syntax: "+CRLF+
				"hour = (file=filename)     !for text file one value per line"+CRLF+
				"hour = (dblfile=filename)  !for packed file of doubles"+CRLF+
				"hour = (sngfile=filename)  !for packed file of singles ";  // vextor of hour values
		PropertyHelp[4] = "Mean of the Price curve values.  This is computed on demand the first time a "+
				"value is needed.  However, you may set it to another value independently. "+
				"Used for Monte Carlo load simulations.";  // set the mean (otherwise computed)
		PropertyHelp[5] = "Standard deviation of the Prices.  This is computed on demand the first time a "+
				"value is needed.  However, you may set it to another value independently."+
				"Is overwritten if you subsequently read in a curve" + CRLF + CRLF +
				"Used for Monte Carlo load simulations.";  // set the std dev (otherwise computed)
		PropertyHelp[6] = "Switch input of  Price curve data to a csv file "+
				"containing (hour, Price) points, or simply (Price) values for fixed time interval data, one per line. " +
				"NOTE: This action may reset the number of points to a lower value.";  // Switch input to a csvfile
		PropertyHelp[7] = "Switch input of  Price curve data to a binary file of singles "+
				"containing (hour, Price) points, or simply (Price) values for fixed time interval data, packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		PropertyHelp[8] = "Switch input of  Price curve data to a binary file of doubles "+
				"containing (hour, Price) points, or simply (Price) values for fixed time interval data, packed one after another. " +
				"NOTE: This action may reset the number of points to a lower value.";  // switch input to a binary file of singles
		PropertyHelp[9] ="Specify fixed interval in SECONDS. Alternate way to specify Interval property.";
		PropertyHelp[10] ="Specify fixed interval in MINUTES. Alternate way to specify Interval property.";
		PropertyHelp[11] ="{DblSave | SngSave} After defining Price curve data... " +
				"Setting action=DblSave or SngSave will cause the present \"Price\" values to be written to " +
				"either a packed file of double or single. The filename is the PriceShape name. ";  // Action

		ActiveProperty = PriceShape.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Globals.setActiveDSSObject(new PriceShapeObjImpl(this, ObjName));
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
		setActivePriceShapeObj((PriceShapeObj) ElementList.getActive());
		Globals.setActiveDSSObject(getActivePriceShapeObj());

		PriceShapeObj aps = getActivePriceShapeObj();

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
				aps.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ aps.getName() + "\"", 610);
				break;
			case 0:
				aps.setNumPoints(parser.makeInteger());
				break;
			case 1:
				aps.setInterval(parser.makeDouble());
				break;
			case 2:
				aps.setPriceValues( (double[]) Utilities.resizeArray(aps.getPriceValues(), aps.getNumPoints()) );
				// Allow possible Resetting (to a lower value) of num points when specifying Prices not Hours
				aps.setNumPoints( Utilities.interpretDblArray(Param, aps.getNumPoints(), aps.getPriceValues()) );   //parser.parseAsVector(Npts, Prices);
				break;
			case 3:
				aps.setHours( (double[]) Utilities.resizeArray(aps.getHours(), aps.getNumPoints()) );
				Utilities.interpretDblArray(Param, aps.getNumPoints(), aps.getHours());   //parser.parseAsVector(Npts, Hours);
				break;
			case 4:
				aps.setMean(parser.makeDouble());
				break;
			case 5:
				aps.setStdDev(parser.makeDouble());
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
				aps.setInterval(parser.makeDouble() / 3600.0);  // Convert seconds to hr
				break;
			case 10:
				aps.setInterval(parser.makeDouble() / 60.0);  // Convert minutes to hr
				break;
			case 11:
				switch (Param.toLowerCase().charAt(0)) {
				case 'd':
					aps.saveToDblFile();
					break;
				case 's':
					aps.saveToSngFile();
					break;
				}
				break;
			default:
				// Inherited parameters
				classEdit(getActivePriceShapeObj(), ParamPointer - PriceShape.NumPropsThisClass);
				break;
			}

			switch (ParamPointer) {
			case 2:
				aps.setStdDevCalculated(false);  // now calculated on demand
				aps.setArrayPropertyIndex(ParamPointer);
				aps.setNumPoints(aps.getNumPoints());  // Keep properties in order for save command  FIXME
				break;
			case 6:
				aps.setStdDevCalculated(false);  // now calculated on demand
				aps.setArrayPropertyIndex(ParamPointer);
				aps.setNumPoints(aps.getNumPoints());  // Keep properties in order for save command
				break;
			case 7:
				aps.setStdDevCalculated(false);  // now calculated on demand
				aps.setArrayPropertyIndex(ParamPointer);
				aps.setNumPoints(aps.getNumPoints());  // Keep properties in order for save command
				break;
			case 8:
				aps.setStdDevCalculated(false);  // now calculated on demand
				aps.setArrayPropertyIndex(ParamPointer);
				aps.setNumPoints(aps.getNumPoints());  // Keep properties in order for save command
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
		PriceShapeObj OtherPriceShape;
		int i, Result = 0;

		/* See if we can find this line code in the present collection */
		OtherPriceShape = (PriceShapeObj) find(ShapeName);
		if (OtherPriceShape != null) {
			PriceShapeObj aps = getActivePriceShapeObj();

			aps.setNumPoints(OtherPriceShape.getNumPoints());
			aps.setInterval(OtherPriceShape.getInterval());
			aps.setPriceValues( (double[]) Utilities.resizeArray(aps.getPriceValues(), aps.getNumPoints()) );
			for (i = 0; i < aps.getNumPoints(); i++)
				aps.getPriceValues()[i] = OtherPriceShape.getPriceValues()[i];
			if (aps.getInterval() > 0.0) {
				aps.setHours(new double[0]);
			} else {
				aps.setHours( (double[]) Utilities.resizeArray(aps.getHours(), aps.getNumPoints()) );
			}
			for (i = 0; i < aps.getNumPoints(); i++)
				aps.getHours()[i] = OtherPriceShape.getHours()[i];

			for (i = 0; i < aps.getParentClass().getNumProperties(); i++)
				aps.setPropertyValue(i, OtherPriceShape.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in PriceShape MakeLike: \"" + ShapeName + "\" Not Found.", 611);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement PriceShape.init", -1);
		return 0;
	}

	public String getCode() {
		return ((PriceShapeObj) ElementList.getActive()).getName();
	}

	public void setCode(String Value) {
		setActivePriceShapeObj(null);
		PriceShapeObj pPriceShapeObj = (PriceShapeObj) ElementList.getFirst();
		while (pPriceShapeObj != null) {
			if (pPriceShapeObj.getName().equalsIgnoreCase(Value)) {
				setActivePriceShapeObj(pPriceShapeObj);
				return;
			}

			pPriceShapeObj = (PriceShapeObj) ElementList.getNext();
		}

		DSSGlobals.getInstance().doSimpleMsg("PriceShape: \"" + Value + "\" not Found.", 612);
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

			PriceShapeObj aps = getActivePriceShapeObj();

			aps.setPriceValues( (double[]) Utilities.resizeArray(aps.getPriceValues(), aps.getNumPoints()) );

			if (aps.getInterval() == 0.0)
				aps.setHours( (double[]) Utilities.resizeArray(aps.getHours(), aps.getNumPoints()) );
			int i = 0;
			while (((s = reader.readLine()) != null) && i < aps.getNumPoints()) {  // TODO: Check zero based indexing
				i += 1;
				/* AuxParser allows commas or white space */
				parser = Globals.getAuxParser();
				parser.setCmdString(s);
				if (aps.getInterval() == 0.0) {
					parser.getNextParam();
					aps.getHours()[i] = parser.makeDouble();
				}
				parser.getNextParam();
				aps.getPriceValues()[i] = parser.makeDouble();
			}
			fileStream.close();
			dataStream.close();
			reader.close();
			if (i != aps.getNumPoints())  // TODO: Check zero based indexing
				aps.setNumPoints(i);

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

	public static void setActivePriceShapeObj(PriceShapeObj activePriceShapeObj) {
		ActivePriceShapeObj = activePriceShapeObj;
	}

	public static PriceShapeObj getActivePriceShapeObj() {
		return ActivePriceShapeObj;
	}

}
