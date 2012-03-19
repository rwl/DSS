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
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Spectrum extends DSSClass {

	public static final int NumPropsThisClass = 5;

	public static SpectrumObj activeSpectrumObj;

	public Spectrum() {
		super();
		className = "Spectrum";
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

		numProperties = Spectrum.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		propertyName[0] = "NumHarm";
		propertyName[1] = "harmonic";
		propertyName[2] = "%mag";
		propertyName[3] = "angle";
		propertyName[4] = "CSVFile";

		propertyHelp[0] = "Number of frequencies in this spectrum. (See CSVFile)";
		propertyHelp[1] = "Array of harmonic values. You can also use the syntax" + CRLF +
			"harmonic = (file=filename)     !for text file one value per line"+CRLF+
			"harmonic = (dblfile=filename)  !for packed file of doubles"+CRLF+
			"harmonic = (sngfile=filename)  !for packed file of singles ";
		propertyHelp[2] = "Array of magnitude values, assumed to be in PERCENT. You can also use the syntax" + CRLF +
			"%mag = (file=filename)     !for text file one value per line"+CRLF+
			"%mag = (dblfile=filename)  !for packed file of doubles"+CRLF+
			"%mag = (sngfile=filename)  !for packed file of singles ";
		propertyHelp[3] = "Array of phase angle values, degrees.You can also use the syntax" + CRLF +
			"angle = (file=filename)     !for text file one value per line"+CRLF+
			"angle = (dblfile=filename)  !for packed file of doubles"+CRLF+
			"angle = (sngfile=filename)  !for packed file of singles ";
		propertyHelp[4] = "File of spectrum points with (harmonic, magnitude-percent, angle-degrees) values, one set of 3 per line, in CSV format. "+
			"If fewer than NUMHARM frequencies found in the file, NUMHARM is set to the smaller value.";

		activeProperty = Spectrum.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeDSSObject = new SpectrumObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of Parser
		activeSpectrumObj = (SpectrumObj) elementList.getActive();
		DSS.activeDSSObject = activeSpectrumObj;

		SpectrumObj elem = activeSpectrumObj;

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
						getClassName() + "\"", 650);
				break;
			case 0:
				elem.setNumHarm(parser.makeInteger());
				elem.setAngleArray(Util.resizeArray(elem.getAngleArray(), elem.getNumHarm()));  // make a dummy angle array
				for (int i = 0; i < elem.getNumHarm(); i++)
					elem.getAngleArray()[i] = 0.0;
				break;
			case 1:
				elem.setHarmArray(Util.resizeArray(elem.getHarmArray(), elem.getNumHarm()));
				Util.interpretDblArray(param, elem.getNumHarm(), elem.getHarmArray());
				break;
			case 2:
				elem.setPuMagArray(Util.resizeArray(elem.getPuMagArray(), elem.getNumHarm()));
				Util.interpretDblArray(param, elem.getNumHarm(), elem.getPuMagArray());
				for (int i = 0; i < elem.getNumHarm(); i++)
					elem.getPuMagArray()[i] = elem.getPuMagArray()[i] * 0.01;  // convert to per unit
				break;
			case 3:
				elem.setAngleArray(Util.resizeArray(elem.getAngleArray(), elem.getNumHarm()));
				Util.interpretDblArray(param, elem.getNumHarm(), elem.getAngleArray());
				break;
			case 4:
				doCSVFile(param);
				break;
			default:
				// inherited parameters
				classEdit(activeSpectrumObj, paramPointer - Spectrum.NumPropsThisClass);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (elem.getHarmArray() != null && elem.getPuMagArray() != null && elem.getAngleArray() != null)
			elem.setMultArray();

		return 0;
	}

	@Override
	protected int makeLike(String name) {
		SpectrumObj other, elem;
		int result = 0;

		/* See if we can find this line code in the present collection */
		other = (SpectrumObj) find(name);

		if (other != null) {
			elem = activeSpectrumObj;

			elem.setNumHarm(other.getNumHarm());

			elem.setHarmArray(Util.resizeArray(elem.getHarmArray(), elem.getNumHarm()));
			elem.setPuMagArray(Util.resizeArray(elem.getPuMagArray(), elem.getNumHarm()));
			elem.setAngleArray(Util.resizeArray(elem.getAngleArray(), elem.getNumHarm()));

			for (int i = 0; i < elem.getNumHarm(); i++) {
				elem.getHarmArray()[i] = other.getHarmArray()[i];
				elem.getPuMagArray()[i] = other.getPuMagArray()[i];
				elem.getAngleArray()[i] = other.getAngleArray()[i];
			}

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++) {
				elem.setPropertyValue(i, other.getPropertyValue(i));
			}
			result = 1;
		} else {
			DSS.doSimpleMsg("Error in Spectrum.makeLike(): \"" + name + "\" not found.", 651);
		}

		return result;
	}

	/**
	 * Returns active spectrum code string.
	 */
	public String getCode() {
		SpectrumObj spectrum = (SpectrumObj) elementList.getActive();
		return spectrum.getName();
	}

	/**
	 * Sets the active spectrum.
	 */
	public void setCode(String value) {
		SpectrumObj spectrum;
		activeSpectrumObj = null;
		for (int i = 0; i < elementList.size(); i++) {
			spectrum = (SpectrumObj) elementList.get(i);
			if (spectrum.getName().equalsIgnoreCase(value)) {
				activeSpectrumObj = spectrum;
				return;
			}
		};

		DSS.doSimpleMsg("Spectrum: \"" + value + "\" not found.", 652);
	}

	private void doCSVFile(String fileName) {
		FileReader fr;
		BufferedReader br;
		String s;
		Parser parser;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			SpectrumObj elem = activeSpectrumObj;

			elem.setHarmArray( Util.resizeArray(elem.getHarmArray(), elem.getNumHarm()) );
			elem.setPuMagArray( Util.resizeArray(elem.getPuMagArray(), elem.getNumHarm()) );
			elem.setAngleArray( Util.resizeArray(elem.getAngleArray(), elem.getNumHarm()) );

			int i = 0;
			while (((s = br.readLine()) != null) && i < elem.getNumHarm()) {
				// use aux parser, which allows for formats
				parser = DSS.auxParser;
				parser.setCmdString(s);
				parser.getNextParam();
				elem.getHarmArray()[i] = parser.makeDouble();
				parser.getNextParam();
				elem.getPuMagArray()[i] = parser.makeDouble() * 0.01;
				parser.getNextParam();
				elem.getAngleArray()[i] = parser.makeDouble();
				i += 1;
			}

			if (i != elem.getNumHarm() - 1)
				elem.setNumHarm(i);   // reset number of points

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing CSV file \"" + fileName + "\": " + e.getMessage(), 654);
			return;
		}
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Spectrum.init", -1);
		return 0;
	}

}
