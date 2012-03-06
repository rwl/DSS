package com.ncond.dss.general;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

	public int newObject(String objName) {
		// create a new object of this class and add to list.
		DSS.activeDSSObject = new GrowthShapeObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	public int edit() {
		double[] YrBuffer;
		int result = 0;

		// continue parsing with contents of parser
		activeGrowthShapeObj = (GrowthShapeObj) elementList.getActive();
		DSS.activeDSSObject = activeGrowthShapeObj;

		GrowthShapeObj pShape = activeGrowthShapeObj;

		int paramPointer = -1;
		String paramName = Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				pShape.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Object \"" + getName() +"."+ getName() + "\"", 600);
				break;
			case 0:
				pShape.setNPts(Parser.getInstance().makeInteger());
				break;
			case 1:
				pShape.setYear( Util.resizeArray(pShape.getYear(), pShape.getNPts()) );
				YrBuffer = new double[pShape.getNPts()];
				Util.interpretDblArray(param, pShape.getNPts(), YrBuffer);  // Parser.parseAsVector(pShape.getNpts(), Yrbuffer);

				for (int i = 0; i < pShape.getNPts(); i++)
					pShape.getYear()[i] = (int) Math.round(YrBuffer[i]);
				pShape.setBaseYear(pShape.getYear()[0]);
				YrBuffer = null;
				break;
			case 2:
				pShape.setMultiplier( Util.resizeArray(pShape.getMultiplier(), pShape.getNPts()) );
				Util.interpretDblArray(param, pShape.getNPts(), pShape.getMultiplier());   //Parser.parseAsVector(pShape.getNpts(), pShape.getMultiplier());
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

			paramName = Parser.getInstance().getNextParam();
			param = Parser.getInstance().makeString();
		}

		pShape.reCalcYearMult();

		return result;
	}

	protected int makeLike(String shapeName) {
		GrowthShapeObj pShape, otherGrowthShape;

		/* See if we can find this line code in the present collection */
		otherGrowthShape = (GrowthShapeObj) find(shapeName);

		if (otherGrowthShape != null) {
			pShape = activeGrowthShapeObj;
			pShape.setNPts(otherGrowthShape.getNPts());
			pShape.setMultiplier( Util.resizeArray(pShape.getMultiplier(), pShape.getNPts()) );
			for (int i = 0; i < pShape.getNPts(); i++)
				pShape.getMultiplier()[i] = otherGrowthShape.getMultiplier()[i];
			pShape.setYear( Util.resizeArray(pShape.getYear(), pShape.getNPts()) );
			for (int i = 0; i < pShape.getNPts(); i++)
				pShape.getYear()[i] = otherGrowthShape.getYear()[i];
			for (int i = 0; i < pShape.getParentClass().getNumProperties(); i++)
				pShape.setPropertyValue(i, otherGrowthShape.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in GrowthShape makeLike: \"" + shapeName + "\" not found.", 601);
		}
		return 0;
	}

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
		GrowthShapeObj pShape;
		activeGrowthShapeObj = null;

		for (int i = 0; i < elementList.size(); i++) {
			pShape = (GrowthShapeObj) elementList.get(i);
			if (pShape.getName().equalsIgnoreCase(value)) {
				activeGrowthShapeObj = pShape;
				return;
			}
		}

		DSS.doSimpleMsg("GrowthShape: \"" + value + "\" not found.", 602);
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

			GrowthShapeObj pShape = activeGrowthShapeObj;

			int i = 0;
			while (((s = br.readLine()) != null) && i < pShape.getNPts()) {
				// use aux parser to allow flexible formats
				parser = DSS.auxParser;
				parser.setCmdString(s);
				parser.getNextParam();
				pShape.getYear()[i] = parser.makeInteger();
				parser.getNextParam();
				pShape.getMultiplier()[i] = parser.makeDouble();
				i += 1;
			}

			fis.close();
			dis.close();
			br.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing CSV file: \"" + fileName + ". " + e.getMessage(), 604);
			return;
		}
	}

	private void doSngFile(String fileName) {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

	private void doDblFile(String fileName) {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

}
