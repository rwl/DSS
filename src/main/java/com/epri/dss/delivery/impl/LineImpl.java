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

	public static LineObj activeLineObj;

	public static LineGeometry lineGeometryClass;
	public static LineCode lineCodeClass;

	public LineImpl() {
		super();
		className = "Line";
		classType = classType + DSSClassDefs.LINE_ELEMENT;  // in both PD element list and line section lists

		activeElement = -1;
		lineCodeClass = null;
		lineGeometryClass = null;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = Line.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "bus1";
		propertyName[1] = "bus2";
		propertyName[2] = "linecode";
		propertyName[3] = "length";
		propertyName[4] = "phases";
		propertyName[5] = "r1";
		propertyName[6] = "x1";
		propertyName[7] = "r0";
		propertyName[8] = "x0";
		propertyName[9] = "c1";
		propertyName[10] = "c0";
		propertyName[11] = "rmatrix";
		propertyName[12] = "xmatrix";
		propertyName[13] = "cmatrix";
		propertyName[14] = "Switch";
		propertyName[15] = "Rg";
		propertyName[16] = "Xg";
		propertyName[17] = "rho";
		propertyName[18] = "geometry";
		propertyName[19] = "units";
		propertyName[20] = "spacing";
		propertyName[21] = "wires";
		propertyName[22] = "EarthModel";
		propertyName[23] = "cncables";
		propertyName[24] = "tscables";

		// define property help values
		propertyHelp[0] = "Name of bus to which first terminal is connected."+ DSSGlobals.CRLF+
					"Example:"+DSSGlobals.CRLF+
					"bus1=busname   (assumes all terminals connected in normal phase order)"+DSSGlobals.CRLF+
					"bus1=busname.3.1.2.0 (specify terminal to node connections explicitly)";
		propertyHelp[1] = "Name of bus to which 2nd terminal is connected.";
		propertyHelp[2] = "Name of linecode object describing line impedances."+DSSGlobals.CRLF+
					"If you use a line code, you do not need to specify the impedances here. "+
					"The line code must have been PREVIOUSLY defined. " +
					"The values specified last will prevail over those specified earlier (left-to-right " +
					"sequence of properties).  You can subsequently change the number of phases if symmetrical component quantities are specified." +
					"If no line code or impedance data are specified, the line object "+
					"defaults to 336 MCM ACSR on 4 ft spacing.";
		propertyHelp[3] = "Length of line. Default is 1.0. If units do not match the impedance data, specify \"units\" property. ";
		propertyHelp[4] = "Number of phases, this line.";
		propertyHelp[5] = "Positive-sequence Resistance, ohms per unit length. Setting any of R1, R0, X1, X0, C1, C0 forces " +
						"the program to use the symmetrical component line definition. See also Rmatrix.";
		propertyHelp[6] = "Positive-sequence Reactance, ohms per unit length. Setting any of R1, R0, X1, X0, C1, C0 forces " +
						"the program to use the symmetrical component line definition.  See also Xmatrix";
		propertyHelp[7] = "Zero-sequence Resistance, ohms per unit length.";
		propertyHelp[8] = "Zero-sequence Reactance, ohms per unit length.";
		propertyHelp[9] = "Positive-sequence capacitance, nf per unit length.  Setting any of R1, R0, X1, X0, C1, C0 forces " +
						"the program to use the symmetrical component line definition. See also Cmatrix.";
		propertyHelp[10] = "Zero-sequence capacitance, nf per unit length.";
		propertyHelp[11] = "Resistance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. "+
						"May be used to specify the impedance of any line configuration. Using any of Rmatrix, Xmatrix, Cmatrix " +
						"forces program to use the matrix values for line impedance definition. For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		propertyHelp[12] = "Reactance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. "+
						"May be used to specify the impedance of any line configuration. Using any of Rmatrix, Xmatrix, Cmatrix " +
						"forces program to use the matrix values for line impedance definition.  For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		propertyHelp[13] = "Nodal Capacitance matrix, lower triangle, nf per unit length.Order of the matrix is the number of phases. "+
						"May be used to specify the shunt capacitance of any line configuration. Using any of Rmatrix, Xmatrix, Cmatrix " +
						"forces program to use the matrix values for line impedance definition.  For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		propertyHelp[14] = "{y/n | T/F}  Default= no/false.  Designates this line as a switch for graphics and algorithmic purposes. " +DSSGlobals.CRLF+
							"SIDE EFFECT: Sets r1 = 1.0; x1 = 1.0; r0 = 1.0; x0 = 1.0; c1 = 1.1 ; c0 = 1.0;  length = 0.001; You must reset if you want something different.";
		propertyHelp[15] = "Carson earth return resistance per unit length used to compute impedance values at base frequency. " +
							"Default is 0.01805 = 60 Hz value in ohms per kft (matches default line impedances). " +
							"This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. " +
							"If not, set both Rg and Xg = 0.";
		propertyHelp[16] = "Carson earth return reactance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments. " +
							"Default is 0.155081 = 60 Hz value in ohms per kft (matches default line impedances). " +
							"This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. " +
							"If not, set both Rg and Xg = 0.";
		propertyHelp[17] = "Default=100 meter ohms.  Earth resitivity used to compute earth correction factor. Overrides Line geometry definition if specified.";
		propertyHelp[18] = "Geometry code for LineGeometry Object. Supercedes any previous definition of line impedance. " +
							"Line constants are computed for each frequency change or rho change. CAUTION: may alter number of phases. "+
							"You cannot subsequently change the number of phases unless you change how the line impedance is defined.";
		propertyHelp[19] = "Length Units = {none | mi|kft|km|m|Ft|in|cm } Default is None - assumes length units match impedance units.";
		propertyHelp[20] = "Reference to a LineSpacing for use in a line constants calculation." + DSSGlobals.CRLF +
							"Must be used in conjunction with the Wires property." + DSSGlobals.CRLF +
							"Specify this before the wires property.";
		propertyHelp[21] = "Array of WireData names for use in a line constants calculation." + DSSGlobals.CRLF +
				"Must be used in conjunction with the Spacing property." + DSSGlobals.CRLF +
				"Specify the Spacing first, and \"ncond\" wires." + DSSGlobals.CRLF +
				"May also be used to specify bare neutrals with cables, using \"ncond-nphase\" wires.";
		propertyHelp[22] = "One of {Carson | FullCarson | Deri}. Default is the global value established with the Set EarthModel command. " +
							"See the Options Help on EarthModel option. This is used to override the global value for this line. This " +
							"option applies only when the \"geometry\" property is used.";
		propertyHelp[23] = "Array of CNData names for use in a cable constants calculation." + DSSGlobals.CRLF +
				"Must be used in conjunction with the Spacing property." + DSSGlobals.CRLF +
				"Specify the Spacing first, using \"nphases\" cncables." + DSSGlobals.CRLF +
				"You may later specify \"nconds-nphases\" wires for separate neutrals";
		propertyHelp[24] = "Array of TSData names for use in a cable constants calculation." + DSSGlobals.CRLF +
				"Must be used in conjunction with the Spacing property." + DSSGlobals.CRLF +
				"Specify the Spacing first, using \"nphases\" tscables." + DSSGlobals.CRLF +
				"You may later specify \"nconds-nphases\" wires for separate neutrals";


		activeProperty = Line.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSSGlobals globals = DSSGlobals.getInstance();

		globals.getActiveCircuit().setActiveCktElement(new LineObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	private void doRmatrix() {
		int nOrder = 0;
		Complex[] ZValues;

		LineObj al = activeLineObj;

		double[] MatBuffer = new double[al.getNPhases() * al.getNPhases()];
		int OrderFound = Parser.getInstance().parseAsSymMatrix(al.getNPhases(), MatBuffer);

		if (OrderFound > 0) {  // parse was successful
			/* R */
			ZValues = al.getZ().asArray(nOrder);
			if (nOrder == al.getNPhases())
				for (int j = 0; j < al.getNPhases() * al.getNPhases(); j++)
					ZValues[j] = new Complex(MatBuffer[j], ZValues[j].getImaginary());
		}

		MatBuffer = null;
	}

	private void doXmatrix() {
		int nOrder = 0;
		Complex[] ZValues;

		LineObj al = activeLineObj;

		double[] MatBuffer = new double[al.getNPhases() * al.getNPhases()];
		int OrderFound = Parser.getInstance().parseAsSymMatrix(al.getNPhases(), MatBuffer);

		if (OrderFound > 0) {  // parse was successful
			/* X */
			ZValues = al.getZ().asArray(nOrder);
			if (nOrder == al.getNPhases())
				for (int j = 0; j < al.getNPhases() * al.getNPhases(); j++)
					ZValues[j] = new Complex(ZValues[j].getReal(), MatBuffer[j]);
		}

		MatBuffer = null;
	}

	private void doCmatrix() {
		int nOrder = 0;
		Complex[] YValues;
		double factor;

		LineObj al = activeLineObj;

		double[] matBuffer = new double[al.getNPhases() * al.getNPhases()];
		int orderFound = Parser.getInstance().parseAsSymMatrix(al.getNPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful
			/* X */
			factor = DSSGlobals.TWO_PI * al.getBaseFrequency() * 1.0e-9;
			YValues = al.getYc().asArray(nOrder);
			if (nOrder == al.getNPhases())
				for (int j = 0; j < al.getNPhases() * al.getNPhases(); j++)
					YValues[j] = new Complex(YValues[j].getReal(), factor * matBuffer[j]);
		}

		matBuffer = null;
	}

	/**
	 * A line defaults to 3-phases and some typical symmetrical component data.
	 *
	 * Line impedances are specified in per unit length and are multiplied by the length
	 * when the primitive Y matrix is computed.
	 *
	 * You may specify the impedances of the line either by symmetrical components or
	 * by R, X, and nodal C matrices  (also per unit length).
	 *
	 * All C's is entered in nano farads.
	 *
	 * The ultimate values are in the matrices. If you specify matrices, then the symmetrical
	 * component values are ignored. However, if you change any of the symmetrical component values
	 * the matrices will be recomputed. It is assumed you want to use symmetrical component values.
	 * Don't mix data entry by matrix and by symmetrical components.
	 *
	 * Note that if you change the number of phases, the matrices are reallocated and reinitialized
	 * with whatever is currently in the symmetrical component data.
	 */
	@Override
	public int edit() {
		DSSGlobals globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int newLengthUnits;

		int result = 0;
		// continue parsing with contents of parser
		activeLineObj = (LineObj) elementList.getActive();
		globals.getActiveCircuit().setActiveCktElement(activeLineObj);  // use property to set this value

		LineObj al = activeLineObj;

		int paramPointer = 0;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if ((paramPointer >= 0) && (paramPointer < getNumProperties()))
				al.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				globals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"Line." + al.getName() + "\"", 181);
				break;
			case 0:
				al.setBus(1, param);  // TODO Check zero based indexing
				break;
			case 1:
				al.setBus(2, param);
				break;
			case 2:
				al.fetchLineCode(param);  // define line by conductor code
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
				al.setC1(parser.makeDouble() * 1.0e-9);  // convert from nano to farads
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
				al.setSwitch(Utilities.interpretYesNo(param));
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
				al.fetchGeometryCode(param);
				break;
			case 19:  // update units conversion factor that might have been changed previously
				newLengthUnits = LineUnits.getUnitsCode(param);
				if (al.isLineCodeSpecified()) {
					al.setUnitsConvert(LineUnits.convertLineUnits(al.getLineCodeUnits(), newLengthUnits));
				} else {
					al.setUnitsConvert(al.getUnitsConvert() * LineUnits.convertLineUnits(al.getLengthUnits(), newLengthUnits));
				}
				al.setLengthUnits(newLengthUnits);
				break;
			case 20:
				al.fetchLineSpacing(param);
				break;
			case 21:
				al.fetchWireList(param);
				break;
			case 22:
				al.setEarthModel(Utilities.interpretEarthModel(param));
				break;
			case 24:
				al.fetchCNCableList(param);
				break;
			case 25:
				al.fetchTSCableList(param);
				break;
			default:
				// inherited property edits
				classEdit(activeLineObj, paramPointer - Line.NumPropsThisClass);
				break;
			}

			// side effects ...
			switch (paramPointer) {
			case 4:  /* Change the number of phases ... only valid if symComponentsModel=true */
				if (al.getNPhases() != parser.makeInteger())
					if (!al.isGeometrySpecified() && al.isSymComponentsModel()) {  // ignore change of nPhases if geometry used
						al.setNPhases(parser.makeInteger());
						al.setNConds(al.getNPhases());  // force reallocation of terminal info
						al.setYorder(al.getNTerms() * al.getNConds());
						/*al.setYprimInvalid(true);*/  // now set below
						al.recalcElementData();  // reallocate Z, etc.
					} else {
						globals.doSimpleMsg("Illegal change of number of phases for Line."+al.getName(), 181);
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
				if (al.isSwitch()) {
					al.setSymComponentsChanged(true);
					al.setYPrimInvalid(true);
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
				al.setYPrimInvalid(true);
				break;
			case 21:
				if ((al.getLineSpacingObj() != null) && (al.getWireData() != null)) {
					al.setSpacingSpecified(true);
					al.setSymComponentsModel(false);
					al.setSymComponentsChanged(false);
					al.killGeometrySpecified();
				}
				al.setYPrimInvalid(true);
				break;
			}

			// YPrim invalidation on anything that changes impedance values
			if ((paramPointer >= 2) && (paramPointer <= 13)) {
				al.setYPrimInvalid(true);
			} else if (paramPointer == 17) {
				if (al.isGeometrySpecified() && (al.getLineGeometryObj() != null))
					al.getLineGeometryObj().setRhoEarth(al.getRho());
			}

			switch (paramPointer) {
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

			paramName = parser.getNextParam();
			param     = parser.makeString();
		}

		//if (al.isSymComponentsChanged()) al.recalcElementData();
		return result;
	}

	@Override
	protected int makeLike(String lineName) {

		int result = 0;
		/* See if we can find this line name in the present collection */
		LineObj otherLine = (LineObj) find(lineName);
		if (otherLine != null) {
			LineObj al = activeLineObj;

			if (al.getNPhases() != otherLine.getNPhases()) {
				al.setNPhases(otherLine.getNPhases());
				al.setNConds(al.getNPhases());  // force reallocation of terminals and conductors

				al.setYorder(al.getNConds() * al.getNTerms());
				al.setYPrimInvalid(true);

				if (al.getZ() != null)
					al.setZ(null);
				if (al.getZInv() != null)
					al.setZInv(null);
				if (al.getYc() != null)
					al.setYc(null);

				// for a line, nPhases = nCond, for now
				al.setZ(new CMatrixImpl(al.getNPhases()));
				al.setZInv(new CMatrixImpl(al.getNPhases()));
				al.setYc(new CMatrixImpl(al.getNPhases()));
			}

			al.getZ().copyFrom(otherLine.getZ());
			// al.getZinv().copyFrom(OtherLine.getZinv());
			al.getYc().copyFrom(otherLine.getYc());
			al.setR1(otherLine.getR1());
			al.setX1(otherLine.getX1());
			al.setR0(otherLine.getR0());
			al.setX0(otherLine.getX0());
			al.setC1(otherLine.getC1());
			al.setC0(otherLine.getC0());
			al.setLen(otherLine.getLen());
			al.setSymComponentsModel(otherLine.isSymComponentsModel());
			al.setCapSpecified(otherLine.isCapSpecified());

			classMakeLike(otherLine);  // take care of inherited class properties

			for (int i = 0; i < al.getParentClass().getNumProperties(); i++)
				al.setPropertyValue(i, otherLine.getPropertyValue(i));
			result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Line makeLike: \"" + lineName + "\" not found.", 182);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Line.init()", -1);
		return 0;
	}

}
