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
import com.epri.dss.general.Spectrum;
import com.epri.dss.general.SpectrumObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class SpectrumImpl extends DSSClassImpl implements Spectrum {

	public static SpectrumObj activeSpectrumObj;

	public SpectrumImpl() {
		super();
		this.className = "Spectrum";
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
		DSSGlobals globals = DSSGlobals.getInstance();

		globals.setActiveDSSObject(new SpectrumObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of Parser
		activeSpectrumObj = (SpectrumObj) elementList.getActive();
		DSSGlobals.getInstance().setActiveDSSObject(activeSpectrumObj);

		SpectrumObj aso = activeSpectrumObj;

		int paramPointer = 0;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);

				if ((paramPointer > 0) && (paramPointer <= numProperties))  // TODO Check zero based indexing
					aso.setPropertyValue(paramPointer, param);

				switch (paramPointer) {
				case 0:
					DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \""+paramName+"\" for Object \""+getName()+"\"", 650);
					break;
				case 1:
					aso.setNumHarm(parser.makeInteger());
					aso.setAngleArray( (double[]) Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()));  // make a dummy angle array
					for (int i = 0; i < aso.getNumHarm(); i++)
						aso.getAngleArray()[i] = 0.0;
					break;
				case 2:
					aso.setHarmArray((double[]) Utilities.resizeArray(aso.getHarmArray(), aso.getNumHarm()));
					Utilities.interpretDblArray(param, aso.getNumHarm(), aso.getHarmArray());
					break;
				case 3:
					aso.setPUMagArray((double[]) Utilities.resizeArray(aso.getPUMagArray(), aso.getNumHarm()));
					Utilities.interpretDblArray(param, aso.getNumHarm(), aso.getPUMagArray());
					for (int i = 0; i < aso.getNumHarm(); i++)
						aso.getPUMagArray()[i] = aso.getPUMagArray()[i] * 0.01;  // convert to per unit
					break;
				case 4:
					aso.setAngleArray((double[]) Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()));
					Utilities.interpretDblArray(param, aso.getNumHarm(), aso.getAngleArray());
					break;
				case 5:
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
		}

		if ((aso.getHarmArray() != null) && (aso.getPUMagArray() != null) && (aso.getAngleArray() != null))
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

			aso.setHarmArray((double[]) Utilities.resizeArray(aso.getHarmArray(), aso.getNumHarm()));
			aso.setPUMagArray((double[]) Utilities.resizeArray(aso.getPUMagArray(), aso.getNumHarm()));
			aso.setAngleArray((double[]) Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()));

			for (int i = 0; i < aso.getNumHarm(); i++) {
				aso.getHarmArray()[i] = otherSpectrum.getHarmArray()[i];
				aso.getPUMagArray()[i] = otherSpectrum.getPUMagArray()[i];
				aso.getAngleArray()[i] = otherSpectrum.getAngleArray()[i];
			}

			for (int i = 0; i < aso.getParentClass().getNumProperties(); i++) {
				aso.setPropertyValue(i, otherSpectrum.getPropertyValue(i));
				result = 1;
			}
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Spectrum.makeLike(): \"" + name + "\" not found.", 651);
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

		DSSGlobals.getInstance().doSimpleMsg("Spectrum: \"" + value + "\" not found.", 652);
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


			SpectrumObj aso = activeSpectrumObj;

			aso.setHarmArray((double[]) Utilities.resizeArray(aso.getHarmArray(), aso.getNumHarm()));
			aso.setPUMagArray((double[]) Utilities.resizeArray(aso.getPUMagArray(), aso.getNumHarm()));
			aso.setAngleArray((double[]) Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()));

			int i = 0;
			while (((s = br.readLine()) != null) && i < aso.getNumHarm()) {  // TODO: Check zero based indexing
				i += 1;
				// use aux parser, which allows for formats
				parser = globals.getAuxParser();
				parser.setCmdString(s);
				parser.getNextParam();
				aso.getHarmArray()[i] = parser.makeDouble();
				parser.getNextParam();
				aso.getPUMagArray()[i] = parser.makeDouble() * 0.01;
				parser.getNextParam();
				aso.getAngleArray()[i] = parser.makeDouble();
			}
			fis.close();
			dis.close();
			br.close();
			if (i != aso.getNumHarm())
				aso.setNumHarm(i);   // reset number of points


			fis.close();
			dis.close();
			br.close();
		} catch (IOException e) {
			globals.doSimpleMsg("Error processing CSV file: \"" + fileName + ". " + e.getMessage(), 654);
			return;
		}
	}

}
