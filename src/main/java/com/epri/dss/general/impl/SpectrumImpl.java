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

	private static SpectrumObj ActiveSpectrumObj;

	public SpectrumImpl() {
		super();
		this.Class_Name = "Spectrum";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;
		this.ActiveElement = -1;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;

		NumProperties = Spectrum.NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();

		PropertyName[0] = "NumHarm";
		PropertyName[1] = "harmonic";
		PropertyName[2] = "%mag";
		PropertyName[3] = "angle";
		PropertyName[4] = "CSVFile";

		PropertyHelp[0] = "Number of frequencies in this spectrum. (See CSVFile)";
		PropertyHelp[1] = "Array of harmonic values. You can also use the syntax" + CRLF +
			"harmonic = (file=filename)     !for text file one value per line"+CRLF+
			"harmonic = (dblfile=filename)  !for packed file of doubles"+CRLF+
			"harmonic = (sngfile=filename)  !for packed file of singles ";
		PropertyHelp[2] = "Array of magnitude values, assumed to be in PERCENT. You can also use the syntax" + CRLF +
			"%mag = (file=filename)     !for text file one value per line"+CRLF+
			"%mag = (dblfile=filename)  !for packed file of doubles"+CRLF+
			"%mag = (sngfile=filename)  !for packed file of singles ";
		PropertyHelp[3] = "Array of phase angle values, degrees.You can also use the syntax" + CRLF +
			"angle = (file=filename)     !for text file one value per line"+CRLF+
			"angle = (dblfile=filename)  !for packed file of doubles"+CRLF+
			"angle = (sngfile=filename)  !for packed file of singles ";
		PropertyHelp[4] = "File of spectrum points with (harmonic, magnitude-percent, angle-degrees) values, one set of 3 per line, in CSV format. "+
			"If fewer than NUMHARM frequencies found in the file, NUMHARM is set to the smaller value.";

		ActiveProperty = Spectrum.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.setActiveDSSObject(new SpectrumObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		int Result = 0;
		// continue parsing with contents of Parser
		setActiveSpectrumObj((SpectrumObj) ElementList.getActive());
		DSSGlobals.getInstance().setActiveDSSObject(getActiveSpectrumObj());

		SpectrumObj aso = getActiveSpectrumObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);

				if ((ParamPointer > 0) && (ParamPointer <= NumProperties))  // TODO Check zero based indexing
					aso.setPropertyValue(ParamPointer, Param);

				switch (ParamPointer) {
				case 0:
					DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \""+ParamName+"\" for Object \""+getName()+"\"", 650);
				case 1:
					aso.setNumHarm(parser.makeInteger());
					aso.setAngleArray( (double[]) Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()));  // Make a dummy angle array
					for (int i = 0; i < aso.getNumHarm(); i++)
						aso.getAngleArray()[i] = 0.0;
				case 2:
					aso.setHarmArray((double[]) Utilities.resizeArray(aso.getHarmArray(), aso.getNumHarm()));
					Utilities.interpretDblArray(Param, aso.getNumHarm(), aso.getHarmArray());
				case 3:
					aso.setPuMagArray((double[]) Utilities.resizeArray(aso.getPuMagArray(), aso.getNumHarm()));
					Utilities.interpretDblArray(Param, aso.getNumHarm(), aso.getPuMagArray());
					for (int i = 0; i < aso.getNumHarm(); i++)
						aso.getPuMagArray()[i] = aso.getPuMagArray()[i] * 0.01;  // convert to per unit
				case 4:
					aso.setAngleArray((double[]) Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()));
					Utilities.interpretDblArray(Param, aso.getNumHarm(), aso.getAngleArray());
				case 5:
					doCSVFile(Param);
				default:
					// Inherited parameters
					classEdit(getActiveSpectrumObj(), ParamPointer - Spectrum.NumPropsThisClass);
				}

				ParamName = parser.getNextParam();
				Param = parser.makeString();
			}
		}

		if ((aso.getHarmArray() != null) && (aso.getPuMagArray() != null) && (aso.getAngleArray() != null))
			aso.setMultArray();

		return Result;
	}

	@Override
	protected int makeLike(String Name) {
		SpectrumObj OtherSpectrum;

		int Result = 0;
		/* See if we can find this line code in the present collection */
		OtherSpectrum = (SpectrumObj) find(Name);
		if (OtherSpectrum != null) {
			SpectrumObj aso = getActiveSpectrumObj();

			aso.setNumHarm(OtherSpectrum.getNumHarm());

			aso.setHarmArray((double[]) Utilities.resizeArray(aso.getHarmArray(), aso.getNumHarm()));
			aso.setPuMagArray((double[]) Utilities.resizeArray(aso.getPuMagArray(), aso.getNumHarm()));
			aso.setAngleArray((double[]) Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()));

			for (int i = 0; i < aso.getNumHarm(); i++) {
				aso.getHarmArray()[i] = OtherSpectrum.getHarmArray()[i];
				aso.getPuMagArray()[i] = OtherSpectrum.getPuMagArray()[i];
				aso.getAngleArray()[i] = OtherSpectrum.getAngleArray()[i];
			}

			for (int i = 0; i < aso.getParentClass().getNumProperties(); i++) {
				aso.setPropertyValue(i, OtherSpectrum.getPropertyValue(i));
				Result = 1;
			}
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Spectrum.makeLike(): \"" + Name + "\" Not Found.", 651);
		}

		return Result;
	}

	/**
	 * Returns active spectrum code string.
	 */
	public String getCode() {
		SpectrumObj pSpectrum = (SpectrumObj) ElementList.getActive();
		return pSpectrum.getName();
	}

	/**
	 * Sets the active spectrum.
	 */
	public void setCode(String Value) {
		setActiveSpectrumObj(null);
		for (int i = 0; i < ElementList.size(); i++) {
			SpectrumObj pSpectrum = (SpectrumObj) ElementList.get(i);
			if (pSpectrum.getName().equals(Value)) {
				setActiveSpectrumObj(pSpectrum);
				return;
			}
		};

		DSSGlobals.getInstance().doSimpleMsg("Spectrum: \"" + Value + "\" not found.", 652);
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


			SpectrumObj aso = getActiveSpectrumObj();

			aso.setHarmArray((double[]) Utilities.resizeArray(aso.getHarmArray(), aso.getNumHarm()));
			aso.setPuMagArray((double[]) Utilities.resizeArray(aso.getPuMagArray(), aso.getNumHarm()));
			aso.setAngleArray((double[]) Utilities.resizeArray(aso.getAngleArray(), aso.getNumHarm()));

			int i = 0;
			while (((s = reader.readLine()) != null) && i < aso.getNumHarm()) {  // TODO: Check zero based indexing
				i += 1;
				// Use Auxparser, which allows for formats
				parser = Globals.getAuxParser();
				parser.setCmdString(s);
				parser.getNextParam();
				aso.getHarmArray()[i] = parser.makeDouble();
				parser.getNextParam();
				aso.getPuMagArray()[i] = parser.makeDouble() * 0.01;
				parser.getNextParam();
				aso.getAngleArray()[i] = parser.makeDouble();
			}
			fileStream.close();
			dataStream.close();
			reader.close();
			if (i != aso.getNumHarm())
				aso.setNumHarm(i);   // reset number of points


			fileStream.close();
			dataStream.close();
			reader.close();
		} catch (IOException e) {
			Globals.doSimpleMsg("Error Processing CSV File: \"" + FileName + ". " + e.getMessage(), 654);
			return;
		}
	}

	public static SpectrumObj getActiveSpectrumObj() {
		return ActiveSpectrumObj;
	}

	public static void setActiveSpectrumObj(SpectrumObj activeSpectrumObj) {
		ActiveSpectrumObj = activeSpectrumObj;
	}

}
