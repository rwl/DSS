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

		int result = 0;
		// continue parsing with contents of Parser
		activeSpectrumObj = (SpectrumObj) elementList.getActive();
		DSS.activeDSSObject = activeSpectrumObj;

		SpectrumObj aso = activeSpectrumObj;

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
				aso.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \""+paramName+"\" for Object \""+getClassName()+"\"", 650);
				break;
			case 0:
				aso.setNumHarm(parser.makeInteger());
				aso.setAngleArray( Util.resizeArray(aso.getAngleArray(), aso.getNumHarm()));  // make a dummy angle array
				for (int i = 0; i < aso.getNumHarm(); i++)
					aso.getAngleArray()[i] = 0.0;
				break;
			case 1:
				aso.setHarmArray( Util.resizeArray(aso.getHarmArray(), aso.getNumHarm()) );
				Util.interpretDblArray(param, aso.getNumHarm(), aso.getHarmArray());
				break;
			case 2:
				aso.setPuMagArray( Util.resizeArray(aso.getPuMagArray(), aso.getNumHarm()) );
				Util.interpretDblArray(param, aso.getNumHarm(), aso.getPuMagArray());
				for (int i = 0; i < aso.getNumHarm(); i++)
					aso.getPuMagArray()[i] = aso.getPuMagArray()[i] * 0.01;  // convert to per unit
				break;
			case 3:
				aso.setAngleArray( Util.resizeArray(aso.getAngleArray(), aso.getNumHarm()) );
				Util.interpretDblArray(param, aso.getNumHarm(), aso.getAngleArray());
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

		if (aso.getHarmArray() != null && aso.getPuMagArray() != null && aso.getAngleArray() != null)
			aso.setMultArray();

		return result;
	}

	@Override
	protected int makeLike(String name) {
		SpectrumObj otherSpectrum;

		int result = 0;
		/* See if we can find this line code in the present collection */
		otherSpectrum = (SpectrumObj) find(name);
		if (otherSpectrum != null) {
			SpectrumObj aso = activeSpectrumObj;

			aso.setNumHarm(otherSpectrum.getNumHarm());

			aso.setHarmArray( Util.resizeArray(aso.getHarmArray(), aso.getNumHarm()) );
			aso.setPuMagArray( Util.resizeArray(aso.getPuMagArray(), aso.getNumHarm()) );
			aso.setAngleArray( Util.resizeArray(aso.getAngleArray(), aso.getNumHarm()) );

			for (int i = 0; i < aso.getNumHarm(); i++) {
				aso.getHarmArray()[i] = otherSpectrum.getHarmArray()[i];
				aso.getPuMagArray()[i] = otherSpectrum.getPuMagArray()[i];
				aso.getAngleArray()[i] = otherSpectrum.getAngleArray()[i];
			}

			for (int i = 0; i < aso.getParentClass().getNumProperties(); i++) {
				aso.setPropertyValue(i, otherSpectrum.getPropertyValue(i));
				result = 1;
			}
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
		FileInputStream fis;
		DataInputStream dis;
		BufferedReader br;
		String s;

		Parser parser;

		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			br = new BufferedReader(new InputStreamReader(dis));

			SpectrumObj aso = activeSpectrumObj;

			aso.setHarmArray( Util.resizeArray(aso.getHarmArray(), aso.getNumHarm()) );
			aso.setPuMagArray( Util.resizeArray(aso.getPuMagArray(), aso.getNumHarm()) );
			aso.setAngleArray( Util.resizeArray(aso.getAngleArray(), aso.getNumHarm()) );

			int i = 0;
			while (((s = br.readLine()) != null) && i < aso.getNumHarm()) {
				// use aux parser, which allows for formats
				parser = DSS.auxParser;
				parser.setCmdString(s);
				parser.getNextParam();
				aso.getHarmArray()[i] = parser.makeDouble();
				parser.getNextParam();
				aso.getPuMagArray()[i] = parser.makeDouble() * 0.01;
				parser.getNextParam();
				aso.getAngleArray()[i] = parser.makeDouble();
				i += 1;
			}
			fis.close();
			dis.close();
			br.close();
			if (i != aso.getNumHarm() - 1)
				aso.setNumHarm(i);   // reset number of points


			fis.close();
			dis.close();
			br.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error processing CSV file: \"" + fileName + ". " + e.getMessage(), 654);
			return;
		}
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Spectrum.init", -1);
		return 0;
	}

}
