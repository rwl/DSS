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
import com.epri.dss.general.GrowthShape;
import com.epri.dss.general.GrowthShapeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class GrowthShapeImpl extends DSSClassImpl implements GrowthShape {

	public GrowthShapeObj activeGrowthShapeObj;

	public GrowthShapeImpl() {
		super();
		this.className = "GrowthShape";
		this.classType = DSSClassDefs.DSS_OBJECT;

		this.activeElement = -1;

		defineProperties();

		String[] commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(commands);
		this.commandList.setAbbrevAllowed(false);
	}

	protected void defineProperties() {
		final String CRLF = DSSGlobals.CRLF;

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
		DSSGlobals globals = DSSGlobals.getInstance();
		globals.setActiveDSSObject(new GrowthShapeObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	public int edit() {
		DSSGlobals globals = DSSGlobals.getInstance();

		double[] YrBuffer;

		int result = 0;
		// continue parsing with contents of parser
		activeGrowthShapeObj = (GrowthShapeObj) elementList.getActive();
		globals.setActiveDSSObject(activeGrowthShapeObj);

		GrowthShapeObj pShape = activeGrowthShapeObj;

		int paramPointer = 0;
		String paramName = Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if ((paramPointer > 0) && (paramPointer <= numProperties))  // TODO Check zero based indexing
				pShape.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case 0:
				globals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Object \"" + getName() +"."+ getName() + "\"", 600);
				break;
			case 1:
				pShape.setNPts(Parser.getInstance().makeInteger());
				break;
			case 2:
				pShape.setYear( (int[]) Utilities.resizeArray(pShape.getYear(), pShape.getNPts()) );
				YrBuffer = new double[pShape.getNPts()];
				Utilities.interpretDblArray(param, pShape.getNPts(), YrBuffer);  // Parser.parseAsVector(pShape.getNpts(), Yrbuffer);

				for (int i = 0; i < pShape.getNPts(); i++)
					pShape.getYear()[i] = (int) Math.round(YrBuffer[i]);
				pShape.setBaseYear(pShape.getYear()[0]);
				YrBuffer = null;
				break;
			case 3:
				pShape.setMultiplier( (double[]) Utilities.resizeArray(pShape.getMultiplier(), pShape.getNPts()) );
				Utilities.interpretDblArray(param, pShape.getNPts(), pShape.getMultiplier());   //Parser.parseAsVector(pShape.getNpts(), pShape.getMultiplier());
				break;
			case 4:
				doCSVFile(param);
				break;
			case 5:
				doSngFile(param);
				break;
			case 6:
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
		/* See if we can find this line code in the present collection */
		GrowthShapeObj otherGrowthShape = (GrowthShapeObj) find(shapeName);
		if (otherGrowthShape != null) {
			GrowthShapeObj pShape = activeGrowthShapeObj;
			pShape.setNPts(otherGrowthShape.getNPts());
			pShape.setMultiplier( (double[]) Utilities.resizeArray(pShape.getMultiplier(), pShape.getNPts()) );
			for (int i = 0; i < pShape.getNPts(); i++)
				pShape.getMultiplier()[i] = otherGrowthShape.getMultiplier()[i];
			pShape.setYear( (int[]) Utilities.resizeArray(pShape.getYear(), pShape.getNPts()) );
			for (int i = 0; i < pShape.getNPts(); i++)
				pShape.getYear()[i] = otherGrowthShape.getYear()[i];
			for (int i = 0; i < pShape.getParentClass().getNumProperties(); i++)
				pShape.setPropertyValue(i, otherGrowthShape.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in GrowthShape makeLike: \"" + shapeName + "\" not found.", 601);
		}
		return 0;
	}

	public int init(int handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement GrowthShape.init()", -1);
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

		DSSGlobals.getInstance().doSimpleMsg("GrowthShape: \"" + value + "\" not found.", 602);
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

			GrowthShapeObj pShape = activeGrowthShapeObj;

			int i = 0;
			while (((s = br.readLine()) != null) && i < pShape.getNPts()) {  // TODO: Check zero based indexing
				i += 1;
				// use aux parser to allow flexible formats
				parser = globals.getAuxParser();
				parser.setCmdString(s);
				parser.getNextParam();
				pShape.getYear()[i] = parser.makeInteger();
				parser.getNextParam();
				pShape.getMultiplier()[i] = parser.makeDouble();
			}

			fis.close();
			dis.close();
			br.close();
		} catch (IOException e) {
			globals.doSimpleMsg("Error processing CSV file: \"" + fileName + ". " + e.getMessage(), 604);
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
