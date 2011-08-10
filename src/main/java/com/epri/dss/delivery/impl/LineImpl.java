package com.epri.dss.delivery.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Line;
import com.epri.dss.delivery.LineObj;
import com.epri.dss.general.LineCode;
import com.epri.dss.general.LineGeometry;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.CommandListImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.LineUnits;

public class LineImpl extends PDClassImpl implements Line {

	private static LineObj ActiveLineObj;
	private static LineGeometry LineGeometryClass;

	private static LineCode LineCodeClass;

	public LineImpl() {
		super();
		this.Class_Name = "Line";
		this.DSSClassType = DSSClassType + DSSClassDefs.LINE_ELEMENT;  // in both PDElement list and LineSection lists

		setActiveElement(-1);
		LineCodeClass = null;
		LineGeometryClass = null;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		NumProperties = Line.NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();


		// Define Property names
		PropertyName[0] = "bus1";
		PropertyName[1] = "bus2";
		PropertyName[2] = "linecode";
		PropertyName[3] = "length";
		PropertyName[4] = "phases";
		PropertyName[5] = "r1";
		PropertyName[6] = "x1";
		PropertyName[7] = "r0";
		PropertyName[8] = "x0";
		PropertyName[9] = "c1";
		PropertyName[10] = "c0";
		PropertyName[11] = "rmatrix";
		PropertyName[12] = "xmatrix";
		PropertyName[13] = "cmatrix";
		PropertyName[14] = "Switch";
		PropertyName[15] = "Rg";
		PropertyName[16] = "Xg";
		PropertyName[17] = "rho";
		PropertyName[18] = "geometry";
		PropertyName[19] = "units";
		PropertyName[20] = "spacing";
		PropertyName[21] = "wires";
		PropertyName[22] = "EarthModel";
		PropertyName[23] = "cncables";
		PropertyName[24] = "tscables";

		// define Property help values

		PropertyHelp[0] = "Name of bus to which first terminal is connected."+ DSSGlobals.CRLF+
					"Example:"+DSSGlobals.CRLF+
					"bus1=busname   (assumes all terminals connected in normal phase order)"+DSSGlobals.CRLF+
					"bus1=busname.3.1.2.0 (specify terminal to node connections explicitly)";
		PropertyHelp[1] = "Name of bus to which 2nd terminal is connected.";
		PropertyHelp[2] = "Name of linecode object describing line impedances."+DSSGlobals.CRLF+
					"If you use a line code, you do not need to specify the impedances here. "+
					"The line code must have been PREVIOUSLY defined. " +
					"The values specified last will prevail over those specified earlier (left-to-right " +
					"sequence of properties).  You can subsequently change the number of phases if symmetrical component quantities are specified." +
					"If no line code or impedance data are specified, the line object "+
					"defaults to 336 MCM ACSR on 4 ft spacing.";
		PropertyHelp[3] = "Length of line. Default is 1.0. If units do not match the impedance data, specify \"units\" property. ";
		PropertyHelp[4] = "Number of phases, this line.";
		PropertyHelp[5] = "Positive-sequence Resistance, ohms per unit length. Setting any of R1, R0, X1, X0, C1, C0 forces " +
						"the program to use the symmetrical component line definition. See also Rmatrix.";
		PropertyHelp[6] = "Positive-sequence Reactance, ohms per unit length. Setting any of R1, R0, X1, X0, C1, C0 forces " +
						"the program to use the symmetrical component line definition.  See also Xmatrix";
		PropertyHelp[7] = "Zero-sequence Resistance, ohms per unit length.";
		PropertyHelp[8] = "Zero-sequence Reactance, ohms per unit length.";
		PropertyHelp[9] = "Positive-sequence capacitance, nf per unit length.  Setting any of R1, R0, X1, X0, C1, C0 forces " +
						"the program to use the symmetrical component line definition. See also Cmatrix.";
		PropertyHelp[10] = "Zero-sequence capacitance, nf per unit length.";
		PropertyHelp[11] = "Resistance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. "+
						"May be used to specify the impedance of any line configuration. Using any of Rmatrix, Xmatrix, Cmatrix " +
						"forces program to use the matrix values for line impedance definition. For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		PropertyHelp[12] = "Reactance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. "+
						"May be used to specify the impedance of any line configuration. Using any of Rmatrix, Xmatrix, Cmatrix " +
						"forces program to use the matrix values for line impedance definition.  For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		PropertyHelp[13] = "Nodal Capacitance matrix, lower triangle, nf per unit length.Order of the matrix is the number of phases. "+
						"May be used to specify the shunt capacitance of any line configuration. Using any of Rmatrix, Xmatrix, Cmatrix " +
						"forces program to use the matrix values for line impedance definition.  For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		PropertyHelp[14] = "{y/n | T/F}  Default= no/false.  Designates this line as a switch for graphics and algorithmic purposes. " +DSSGlobals.CRLF+
							"SIDE EFFECT: Sets r1 = 1.0; x1 = 1.0; r0 = 1.0; x0 = 1.0; c1 = 1.1 ; c0 = 1.0;  length = 0.001; You must reset if you want something different.";
		PropertyHelp[15] = "Carson earth return resistance per unit length used to compute impedance values at base frequency. " +
							"Default is 0.01805 = 60 Hz value in ohms per kft (matches default line impedances). " +
							"This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. " +
							"If not, set both Rg and Xg = 0.";
		PropertyHelp[16] = "Carson earth return reactance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments. " +
							"Default is 0.155081 = 60 Hz value in ohms per kft (matches default line impedances). " +
							"This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. " +
							"If not, set both Rg and Xg = 0.";
		PropertyHelp[17] = "Default=100 meter ohms.  Earth resitivity used to compute earth correction factor. Overrides Line geometry definition if specified.";
		PropertyHelp[18] = "Geometry code for LineGeometry Object. Supercedes any previous definition of line impedance. " +
							"Line constants are computed for each frequency change or rho change. CAUTION: may alter number of phases. "+
							"You cannot subsequently change the number of phases unless you change how the line impedance is defined.";
		PropertyHelp[19] = "Length Units = {none | mi|kft|km|m|Ft|in|cm } Default is None - assumes length units match impedance units.";
		PropertyHelp[20] = "Reference to a LineSpacing for use in a line constants calculation." + DSSGlobals.CRLF +
							"Must be used in conjunction with the Wires property." + DSSGlobals.CRLF +
							"Specify this before the wires property.";
		PropertyHelp[21] = "Array of WireData names for use in a line constants calculation." + DSSGlobals.CRLF +
				"Must be used in conjunction with the Spacing property." + DSSGlobals.CRLF +
				"Specify the Spacing first, and \"ncond\" wires." + DSSGlobals.CRLF +
				"May also be used to specify bare neutrals with cables, using \"ncond-nphase\" wires.";
		PropertyHelp[22] = "One of {Carson | FullCarson | Deri}. Default is the global value established with the Set EarthModel command. " +
							"See the Options Help on EarthModel option. This is used to override the global value for this line. This " +
							"option applies only when the \"geometry\" property is used.";
		PropertyHelp[23] = "Array of CNData names for use in a cable constants calculation." + DSSGlobals.CRLF +
				"Must be used in conjunction with the Spacing property." + DSSGlobals.CRLF +
				"Specify the Spacing first, using \"nphases\" cncables." + DSSGlobals.CRLF +
				"You may later specify \"nconds-nphases\" wires for separate neutrals";
		PropertyHelp[24] = "Array of TSData names for use in a cable constants calculation." + DSSGlobals.CRLF +
				"Must be used in conjunction with the Spacing property." + DSSGlobals.CRLF +
				"Specify the Spacing first, using \"nphases\" tscables." + DSSGlobals.CRLF +
				"You may later specify \"nconds-nphases\" wires for separate neutrals";


		ActiveProperty = Line.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new LineObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void doRmatrix() {
		int Norder = 0;
		Complex[] ZValues;

		LineObj al = getActiveLineObj();

		double[] MatBuffer = new double[al.getNPhases() * al.getNPhases()];
		int OrderFound = Parser.getInstance().parseAsSymMatrix(al.getNPhases(), MatBuffer);

		if (OrderFound > 0) {  // Parse was successful
			/* R */
			ZValues = al.getZ().asArray(Norder);
			if (Norder == al.getNPhases())
				for (int j = 0; j < al.getNPhases() * al.getNPhases(); j++)
					ZValues[j] = new Complex(MatBuffer[j], ZValues[j].getImaginary());
		}

		MatBuffer = null;
	}

	private void doXmatrix() {
		int Norder = 0;
		Complex[] ZValues;

		LineObj al = getActiveLineObj();

		double[] MatBuffer = new double[al.getNPhases() * al.getNPhases()];
		int OrderFound = Parser.getInstance().parseAsSymMatrix(al.getNPhases(), MatBuffer);

		if (OrderFound > 0) {  // Parse was successful
			/* X */
			ZValues = al.getZ().asArray(Norder);
			if (Norder == al.getNPhases())
				for (int j = 0; j < al.getNPhases() * al.getNPhases(); j++)
					ZValues[j] = new Complex(ZValues[j].getReal(), MatBuffer[j]);
		}

		MatBuffer = null;
	}

	private void doCmatrix() {
		int Norder = 0;
		Complex[] YValues;
		double Factor;

		LineObj al = getActiveLineObj();

		double[] MatBuffer = new double[al.getNPhases() * al.getNPhases()];
		int OrderFound = Parser.getInstance().parseAsSymMatrix(al.getNPhases(), MatBuffer);

		if (OrderFound > 0) {  // Parse was successful
			/* X */
			Factor = DSSGlobals.TwoPi * al.getBaseFrequency() * 1.0e-9;
			YValues = al.getYc().asArray(Norder);
			if (Norder == al.getNPhases())
				for (int j = 0; j < al.getNPhases() * al.getNPhases(); j++)
					YValues[j] = new Complex(YValues[j].getReal(), Factor * MatBuffer[j]);
		}

		MatBuffer = null;
	}

	/**
	 * A Line Defaults to 3-phases and some typical symmetrical component data.
	 *
	 * Line impedances are specified in per unit length and are multiplied by the length
	 * when the primitive Y matrix is computed.
	 *
	 * You may specify the impedances of the line either by symmetrical components or
	 * by R, X, and nodal C matrices  (also per unit length).
	 *
	 * All C's is entered in nano farads.
	 *
	 * The ultimate values are in the matrices.  If you specify matrices, then the symmetrical
	 * component values are ignored.  However, if you change any of the symmetrical component values
	 * the matrices will be recomputed.  It is assumed you want to use symmetrical component values.
	 * Don't mix data entry by matrix and by symmetrical components.
	 *
	 * Note that if you change the number of phases, the matrices are reallocated and reinitialized
	 * with whatever is currently in the symmetrical component data.
	 */
	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int NewLengthUnits;

		int Result = 0;
		// continue parsing with contents of Parser
		setActiveLineObj((LineObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveLineObj());  // use property to set this value

		LineObj al = getActiveLineObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < getNumProperties()))
				al.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"Line." + al.getName() + "\"", 181);
				break;
			case 0:
				al.setBus(1, Param);  // TODO Check zero based indexing
				break;
			case 1:
				al.setBus(2, Param);
				break;
			case 2:
				al.fetchLineCode(Param);  // Define line by conductor code
				break;
			case 3:
				al.setLen(parser.makeDouble());
				break;
			case 4:
				/* Nphases: See below */
				break;
			case 5:
				al.setR1(parser.makeDouble());
				break;
			case 6:
				al.setX1(parser.makeDouble());
				break;
			case 7:
				al.setR0(parser.makeDouble());
				break;
			case 8:
				al.setX0(parser.makeDouble());
				break;
			case 9:
				al.setC1(parser.makeDouble() * 1.0e-9);    // Convert from nano to farads
				break;
			case 10:
				al.setC0(parser.makeDouble() * 1.0e-9);
				break;
			case 11:
				doRmatrix();
				break;
			case 12:
				doXmatrix();
				break;
			case 13:
				doCmatrix();
				break;
			case 14:
				al.setIsSwitch(Utilities.interpretYesNo(Param));
				break;
			case 15:
				al.setRg(parser.makeDouble());
				break;
			case 16:
				al.setXg(parser.makeDouble());
				break;
			case 17:
				al.setRho(parser.makeDouble());
				al.setRhoSpecified(true);
				break;
			case 18:
				al.fetchGeometryCode(Param);
				break;
			case 19:  // Update units conversion factor that might have been changed previously
				NewLengthUnits = LineUnits.getUnitsCode(Param);
				if (al.isLineCodeSpecified()) {
					al.setUnitsConvert(LineUnits.convertLineUnits(al.getLineCodeUnits(), NewLengthUnits));
				} else {
					al.setUnitsConvert(al.getUnitsConvert() * LineUnits.convertLineUnits(al.getLengthUnits(), NewLengthUnits));
				}
				al.setLengthUnits(NewLengthUnits);
				break;
			case 20:
				al.fetchLineSpacing(Param);
				break;
			case 21:
				al.fetchWireList(Param);
				break;
			case 22:
				al.setEarthModel(Utilities.interpretEarthModel(Param));
				break;
			case 24:
				al.fetchCNCableList(Param);
				break;
			case 25:
				al.fetchTSCableList(Param);
				break;
			default:
				// Inherited Property Edits
				classEdit(getActiveLineObj(), ParamPointer - Line.NumPropsThisClass);
				break;
			}

			// Side Effects ...
			switch (ParamPointer) {
			case 4:  /* Change the number of phases ... only valid if SymComponentsModel=TRUE */
				if (al.getNPhases() != parser.makeInteger())
					if (!al.isGeometrySpecified() && al.isSymComponentsModel()) {  // ignore change of nphases if geometry used
						al.setNPhases(parser.makeInteger());
						al.setNConds(al.getNPhases());  // Force Reallocation of terminal info
						al.setYorder(al.getNTerms() * al.getNConds());
						/*al.setYprimInvalid(true);*/  // now set below
						al.recalcElementData();  // Reallocate Z, etc.
					} else {
						Globals.doSimpleMsg("Illegal change of number of phases for Line."+al.getName(), 181);
					}
				break;
			case 5:
				al.setLineCodeSpecified(false);
				al.killGeometrySpecified();
				al.killSpacingSpecified();
				al.resetLengthUnits();
				al.setSymComponentsChanged(true);
				al.setSymComponentsModel(true);
				break;
			case 6:
				al.setLineCodeSpecified(false);
				al.killGeometrySpecified();
				al.killSpacingSpecified();
				al.resetLengthUnits();
				al.setSymComponentsChanged(true);
				al.setSymComponentsModel(true);
				break;
			case 7:
				al.setLineCodeSpecified(false);
				al.killGeometrySpecified();
				al.killSpacingSpecified();
				al.resetLengthUnits();
				al.setSymComponentsChanged(true);
				al.setSymComponentsModel(true);
				break;
			case 8:
				al.setLineCodeSpecified(false);
				al.killGeometrySpecified();
				al.killSpacingSpecified();
				al.resetLengthUnits();
				al.setSymComponentsChanged(true);
				al.setSymComponentsModel(true);
				break;
			case 9:
				al.setLineCodeSpecified(false);
				al.killGeometrySpecified();
				al.killSpacingSpecified();
				al.resetLengthUnits();
				al.setSymComponentsChanged(true);
				al.setSymComponentsModel(true);
				break;
			case 10:
				al.setLineCodeSpecified(false);
				al.killGeometrySpecified();
				al.killSpacingSpecified();
				al.resetLengthUnits();
				al.setSymComponentsChanged(true);
				al.setSymComponentsModel(true);
				break;
			case 11:
				al.setLineCodeSpecified(false);
				al.setSymComponentsModel(false);
				al.resetLengthUnits();
				al.killGeometrySpecified();
				al.killSpacingSpecified();
				break;
			case 12:
				al.setLineCodeSpecified(false);
				al.setSymComponentsModel(false);
				al.resetLengthUnits();
				al.killGeometrySpecified();
				al.killSpacingSpecified();
				break;
			case 13:
				al.setLineCodeSpecified(false);
				al.setSymComponentsModel(false);
				al.resetLengthUnits();
				al.killGeometrySpecified();
				al.killSpacingSpecified();
				break;
			case 14:
				if (al.isIsSwitch()) {
					al.setSymComponentsChanged(true);
					al.setYprimInvalid(true);
					al.setGeometrySpecified(false);
					al.setSpacingSpecified(false);
					al.setR1(1.0);
					al.setX1(1.0);
					al.setR0(1.0);
					al.setX0(1.0);
					al.setC1(1.1 * 1.0e-9);
					al.setC0(1.0 * 1.0e-9);
					al.setLen(0.001);
					al.resetLengthUnits();
				}
				break;
			case 16:
				al.setKXg(al.getXg() / Math.log(658.5 * Math.sqrt(al.getRho() / al.getBaseFrequency())));
				break;
			case 17:
				al.setKXg(al.getXg() / Math.log(658.5 * Math.sqrt(al.getRho() / al.getBaseFrequency())));
				break;
			case 18:
				al.setGeometrySpecified(true);
				al.setSymComponentsModel(false);
				al.setSymComponentsChanged(false);
				break;
			case 20:
				if ((al.getLineSpacingObj() != null) && (al.getWireData() != null)) {
					al.setSpacingSpecified(true);
					al.setSymComponentsModel(false);
					al.setSymComponentsChanged(false);
					al.killGeometrySpecified();
				}
				al.setYprimInvalid(true);
				break;
			case 21:
				if ((al.getLineSpacingObj() != null) && (al.getWireData() != null)) {
					al.setSpacingSpecified(true);
					al.setSymComponentsModel(false);
					al.setSymComponentsChanged(false);
					al.killGeometrySpecified();
				}
				al.setYprimInvalid(true);
				break;
			}

			// YPrim invalidation on anything that changes impedance values
			if ((ParamPointer >= 2) && (ParamPointer <= 13)) {
				al.setYprimInvalid(true);
			} else if (ParamPointer == 17) {
				if (al.isGeometrySpecified() && (al.getLineGeometryObj() != null))
					al.getLineGeometryObj().setRhoEarth(al.getRho());
			}

			switch (ParamPointer) {
			case 9:
				al.setCapSpecified(true);
				break;
			case 10:
				al.setCapSpecified(true);
				break;
			case 13:
				al.setCapSpecified(true);
				break;
			}

			ParamName = parser.getNextParam();
			Param     = parser.makeString();
		}

		//if (al.isSymComponentsChanged()) al.recalcElementData();
		return Result;
	}

	@Override
	protected int makeLike(String LineName) {

		int Result = 0;
		/* See if we can find this line name in the present collection */
		LineObj OtherLine = (LineObj) find(LineName);
		if (OtherLine != null) {
			LineObj al = getActiveLineObj();

			if (al.getNPhases() != OtherLine.getNPhases()) {
				al.setNPhases(OtherLine.getNPhases());
				al.setNConds(al.getNPhases());  // force reallocation of terminals and conductors

				al.setYorder(al.getNConds() * al.getNTerms());
				al.setYprimInvalid(true);

				if (al.getZ() != null)
					al.setZ(null);
				if (al.getZinv() != null)
					al.setZinv(null);
				if (al.getYc() != null)
					al.setYc(null);

				// For a line, nphases = ncond, for now
				al.setZ(new CMatrixImpl(al.getNPhases()));
				al.setZinv(new CMatrixImpl(al.getNPhases()));
				al.setYc(new CMatrixImpl(al.getNPhases()));
			}

			al.getZ().copyFrom(OtherLine.getZ());
			// al.getZinv().copyFrom(OtherLine.getZinv());
			al.getYc().copyFrom(OtherLine.getYc());
			al.setR1(OtherLine.getR1());
			al.setX1(OtherLine.getX1());
			al.setR0(OtherLine.getR0());
			al.setX0(OtherLine.getX0());
			al.setC1(OtherLine.getC1());
			al.setC0(OtherLine.getC0());
			al.setLen(OtherLine.getLen());
			al.setSymComponentsModel(OtherLine.isSymComponentsModel());
			al.setCapSpecified(OtherLine.isCapSpecified());

			classMakeLike(OtherLine);  // Take care of inherited class properties

			for (int i = 0; i < al.getParentClass().getNumProperties(); i++)
				al.setPropertyValue(i, OtherLine.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Line MakeLike: \"" + LineName + "\" Not Found.", 182);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Line.init()", -1);
		return 0;
	}

	public static LineObj getActiveLineObj() {
		return ActiveLineObj;
	}

	public static void setActiveLineObj(LineObj activeLineObj) {
		ActiveLineObj = activeLineObj;
	}

	public static LineGeometry getLineGeometryClass() {
		return LineGeometryClass;
	}

	public static void setLineGeometryClass(LineGeometry lineGeometryClass) {
		LineGeometryClass = lineGeometryClass;
	}

	public static LineCode getLineCodeClass() {
		return LineCodeClass;
	}

	public static void setLineCodeClass(LineCode lineCodeClass) {
		LineCodeClass = lineCodeClass;
	}

}
