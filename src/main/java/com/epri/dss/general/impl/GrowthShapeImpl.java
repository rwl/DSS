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

	private GrowthShapeObj ActiveGrowthShapeObj;

	public GrowthShapeImpl() {
		super();
		this.className = "GrowthShape";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;

		this.activeElement = -1;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(false);
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;

		numProperties = GrowthShape.NumPropsThisClass;
		countProperties();   // Get inherited property count

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

	public int newObject(String ObjName) {
		// create a new object of this class and add to list.
		DSSGlobals Globals = DSSGlobals.getInstance();
		Globals.setActiveDSSObject(new GrowthShapeObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		double[] YrBuffer;

		int Result = 0;
		// continue parsing with contents of parser
		setActiveGrowthShapeObj((GrowthShapeObj) elementList.getActive());
		Globals.setActiveDSSObject(getActiveGrowthShapeObj());

		GrowthShapeObj pShape = getActiveGrowthShapeObj();

		int ParamPointer = 0;
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer > 0) && (ParamPointer <= numProperties))  // TODO Check zero based indexing
				pShape.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case 0:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ getName() + "\"", 600);
				break;
			case 1:
				pShape.setNpts(Parser.getInstance().makeInteger());
				break;
			case 2:
				pShape.setYear( (int[]) Utilities.resizeArray(pShape.getYear(), pShape.getNpts()) );
				YrBuffer = new double[pShape.getNpts()];
				Utilities.interpretDblArray(Param, pShape.getNpts(), YrBuffer);  // Parser.parseAsVector(pShape.getNpts(), Yrbuffer);

				for (int i = 0; i < pShape.getNpts(); i++)
					pShape.getYear()[i] = (int) Math.round(YrBuffer[i]);
				pShape.setBaseYear(pShape.getYear()[0]);
				YrBuffer = null;
				break;
			case 3:
				pShape.setMultiplier( (double[]) Utilities.resizeArray(pShape.getMultiplier(), pShape.getNpts()) );
				Utilities.interpretDblArray(Param, pShape.getNpts(), pShape.getMultiplier());   //Parser.parseAsVector(pShape.getNpts(), pShape.getMultiplier());
				break;
			case 4:
				doCSVFile(Param);
				break;
			case 5:
				doSngFile(Param);
				break;
			case 6:
				doDblFile(Param);
				break;
			default:
				// inherited parameters
				classEdit(getActiveGrowthShapeObj(), ParamPointer - GrowthShape.NumPropsThisClass);
				break;
			}

			ParamName = Parser.getInstance().getNextParam();
			Param = Parser.getInstance().makeString();
		}

		pShape.reCalcYearMult();

		return Result;
	}

	protected int makeLike(String ShapeName) {
		/* See if we can find this line code in the present collection */
		GrowthShapeObj OtherGrowthShape = (GrowthShapeObj) find(ShapeName);
		if (OtherGrowthShape != null) {
			GrowthShapeObj pShape = getActiveGrowthShapeObj();
			pShape.setNpts(OtherGrowthShape.getNpts());
			pShape.setMultiplier( (double[]) Utilities.resizeArray(pShape.getMultiplier(), pShape.getNpts()) );
			for (int i = 0; i < pShape.getNpts(); i++)
				pShape.getMultiplier()[i] = OtherGrowthShape.getMultiplier()[i];
			pShape.setYear( (int[]) Utilities.resizeArray(pShape.getYear(), pShape.getNpts()) );
			for (int i = 0; i < pShape.getNpts(); i++)
				pShape.getYear()[i] = OtherGrowthShape.getYear()[i];
			for (int i = 0; i < pShape.getParentClass().getNumProperties(); i++)
				pShape.setPropertyValue(i, OtherGrowthShape.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in GrowthShape makeLike: \"" + ShapeName + "\" not found.", 601);
		}
		return 0;
	}

	public int init(int Handle) {
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
	public void setCode(String Value) {
		setActiveGrowthShapeObj(null);

		for (int i = 0; i < elementList.size(); i++) {
			GrowthShapeObj pShape = (GrowthShapeObj) elementList.get(i);
			if (pShape.getName().equalsIgnoreCase(Value)) {
				setActiveGrowthShapeObj(pShape);
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("GrowthShape: \"" + Value + "\" not found.", 602);
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

			GrowthShapeObj pShape = getActiveGrowthShapeObj();

			int i = 0;
			while (((s = reader.readLine()) != null) && i < pShape.getNpts()) {  // TODO: Check zero based indexing
				i += 1;
				// use aux parser to allow flexible formats
				parser = Globals.getAuxParser();
				parser.setCmdString(s);
				parser.getNextParam();
				pShape.getYear()[i] = parser.makeInteger();
				parser.getNextParam();
				pShape.getMultiplier()[i] = parser.makeDouble();
			}

			fileStream.close();
			dataStream.close();
			reader.close();
		} catch (IOException e) {
			Globals.doSimpleMsg("Error processing CSV file: \"" + FileName + ". " + e.getMessage(), 604);
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

	public GrowthShapeObj getActiveGrowthShapeObj() {
		return ActiveGrowthShapeObj;
	}

	public void setActiveGrowthShapeObj(GrowthShapeObj activeGrowthShapeObj) {
		ActiveGrowthShapeObj = activeGrowthShapeObj;
	}

}
