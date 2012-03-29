/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.delivery;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.general.LineCode;
import com.ncond.dss.general.LineGeometry;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.LineUnits;

import static com.ncond.dss.common.Util.interpretYesNo;
import static com.ncond.dss.common.Util.interpretEarthModel;

import static java.lang.Math.sqrt;
import static java.lang.Math.log;


public class Line extends PDClass {

	public static final int NumPropsThisClass = 23;

	// 5 kvar of capacitive reactance at 345 kV to avoid open line problem;
	public static final Complex CAP_EPSILON = new Complex(0.0, 4.2e-8);

	public static LineObj activeLineObj;

	public static LineGeometry lineGeometryClass;
	public static LineCode lineCodeClass;

	public Line() {
		super();
		className = "Line";
		classType = classType + DSSClassDefs.LINE_ELEMENT;  // in both PD element list and line section lists

		activeElement = -1;
		lineCodeClass = null;
		lineGeometryClass = null;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
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
		propertyName[22] = "earthModel";
		propertyName[23] = "cncables";
		propertyName[24] = "tscables";

		// define property help values
		propertyHelp[0] = "Name of bus to which first terminal is connected."+ DSS.CRLF+
				"Example:"+DSS.CRLF+
				"bus1=busname   (assumes all terminals connected in normal phase order)"+DSS.CRLF+
				"bus1=busname.3.1.2.0 (specify terminal to node connections explicitly)";
		propertyHelp[1] = "Name of bus to which 2nd terminal is connected.";
		propertyHelp[2] = "Name of linecode object describing line impedances."+DSS.CRLF+
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
		propertyHelp[14] = "{y/n | T/F}  Default= no/false.  Designates this line as a switch for graphics and algorithmic purposes. " +DSS.CRLF+
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
		propertyHelp[20] = "Reference to a LineSpacing for use in a line constants calculation." + DSS.CRLF +
				"Must be used in conjunction with the Wires property." + DSS.CRLF +
				"Specify this before the wires property.";
		propertyHelp[21] = "Array of WireData names for use in a line constants calculation." + DSS.CRLF +
				"Must be used in conjunction with the Spacing property." + DSS.CRLF +
				"Specify the Spacing first, and \"ncond\" wires." + DSS.CRLF +
				"May also be used to specify bare neutrals with cables, using \"ncond-nphase\" wires.";
		propertyHelp[22] = "One of {Carson | FullCarson | Deri}. Default is the global value established with the Set EarthModel command. " +
				"See the Options Help on EarthModel option. This is used to override the global value for this line. This " +
				"option applies only when the \"geometry\" property is used.";
		propertyHelp[23] = "Array of CNData names for use in a cable constants calculation." + DSS.CRLF +
				"Must be used in conjunction with the Spacing property." + DSS.CRLF +
				"Specify the Spacing first, using \"nphases\" cncables." + DSS.CRLF +
				"You may later specify \"nconds-nphases\" wires for separate neutrals";
		propertyHelp[24] = "Array of TSData names for use in a cable constants calculation." + DSS.CRLF +
				"Must be used in conjunction with the Spacing property." + DSS.CRLF +
				"Specify the Spacing first, using \"nphases\" tscables." + DSS.CRLF +
				"You may later specify \"nconds-nphases\" wires for separate neutrals";

		activeProperty = Line.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new LineObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void doRmatrix() {
		int[] nOrder = new int[1];
		Complex[] Zvalues;

		LineObj elem = activeLineObj;

		double[] matBuffer = new double[elem.getNumPhases() * elem.getNumPhases()];
		int orderFound = Parser.getInstance().parseAsSymMatrix(elem.getNumPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful
			Zvalues = elem.getZ().asArray(nOrder);
			if (nOrder[0] == elem.getNumPhases()) {
				for (int j = 0; j < elem.getNumPhases() * elem.getNumPhases(); j++)
					Zvalues[j] = new Complex(matBuffer[j], Zvalues[j].getImaginary());
			}
		}
	}

	private void doXmatrix() {
		int[] nOrder = new int[1];
		Complex[] Zvalues;

		LineObj elem = activeLineObj;

		double[] matBuffer = new double[elem.getNumPhases() * elem.getNumPhases()];
		int orderFound = Parser.getInstance().parseAsSymMatrix(elem.getNumPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful
			Zvalues = elem.getZ().asArray(nOrder);
			if (nOrder[0] == elem.getNumPhases()) {
				for (int j = 0; j < elem.getNumPhases() * elem.getNumPhases(); j++)
					Zvalues[j] = new Complex(Zvalues[j].getReal(), matBuffer[j]);
			}
		}
	}

	private void doCmatrix() {
		int[] nOrder = new int[1];
		Complex[] Yvalues;
		double factor;

		LineObj elem = activeLineObj;

		double[] matBuffer = new double[elem.getNumPhases() * elem.getNumPhases()];
		int orderFound = Parser.getInstance().parseAsSymMatrix(elem.getNumPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful
			factor = DSS.TWO_PI * elem.getBaseFrequency() * 1.0e-9;
			Yvalues = elem.getYc().asArray(nOrder);
			if (nOrder[0] == elem.getNumPhases()) {
				for (int j = 0; j < elem.getNumPhases() * elem.getNumPhases(); j++)
					Yvalues[j] = new Complex(Yvalues[j].getReal(), factor * matBuffer[j]);
			}
		}
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
		Parser parser = Parser.getInstance();

		LineUnits newLengthUnits;

		// continue parsing with contents of parser
		activeLineObj = (LineObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeLineObj);  // use property to set this value

		LineObj elem = activeLineObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.stringValue();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < getNumProperties())
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"Line." +
						elem.getName() + "\"", 181);
				break;
			case 0:
				elem.setBus(0, param);
				break;
			case 1:
				elem.setBus(1, param);
				break;
			case 2:
				elem.fetchLineCode(param);  // define line by conductor code
				break;
			case 3:
				elem.setLen(parser.doubleValue());
				break;
			case 4:
				/* nPhases (see below) */
				break;
			case 5:
				elem.setR1(parser.doubleValue());
				break;
			case 6:
				elem.setX1(parser.doubleValue());
				break;
			case 7:
				elem.setR0(parser.doubleValue());
				break;
			case 8:
				elem.setX0(parser.doubleValue());
				break;
			case 9:
				elem.setC1(parser.doubleValue() * 1.0e-9);  // convert from nano to farads
				break;
			case 10:
				elem.setC0(parser.doubleValue() * 1.0e-9);
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
				elem.setSwitch(interpretYesNo(param));
				break;
			case 15:
				elem.setRg(parser.doubleValue());
				break;
			case 16:
				elem.setXg(parser.doubleValue());
				break;
			case 17:
				elem.setRho(parser.doubleValue());
				elem.setRhoSpecified(true);
				break;
			case 18:
				elem.fetchGeometryCode(param);
				break;
			case 19:  // update units conversion factor that might have been changed previously
				newLengthUnits = LineUnits.interpretUnitsCode(param);
				if (elem.isLineCodeSpecified()) {
					elem.setUnitsConvert(LineUnits.convertLineUnits(elem.getLineCodeUnits(), newLengthUnits));
				} else {
					elem.setUnitsConvert(elem.getUnitsConvert() * LineUnits.convertLineUnits(elem.getLengthUnits(), newLengthUnits));
				}
				elem.setLengthUnits(newLengthUnits);
				break;
			case 20:
				elem.fetchLineSpacing(param);
				break;
			case 21:
				elem.fetchWireList(param);
				break;
			case 22:
				elem.setEarthModel(interpretEarthModel(param));
				break;
			case 23:
				elem.fetchCNCableList(param);
				break;
			case 24:
				elem.fetchTSCableList(param);
				break;
			default:
				// inherited property edits
				classEdit(activeLineObj, paramPointer - Line.NumPropsThisClass);
				break;
			}

			// side effects ...
			switch (paramPointer) {
			case 2:
				elem.setSpacingSpecified(false);
				if (elem.isGeometrySpecified() == true)
					elem.killGeometrySpecified();
				elem.setGeometrySpecified(false);
				break;
			case 4:  /* Change the number of phases ... only valid if symComponentsModel=true */
				if (elem.getNumPhases() != parser.integerValue())
					if (!elem.isGeometrySpecified() && elem.isSymComponentsModel()) {  // ignore change of nPhases if geometry used
						elem.setNumPhases(parser.integerValue());
						elem.setNumConds(elem.getNumPhases());  // force reallocation of terminal info
						elem.setYOrder(elem.getNumTerms() * elem.getNumConds());
						/*al.setYprimInvalid(true);*/  // now set below
						elem.recalcElementData();  // reallocate Z, etc.
					} else {
						DSS.doSimpleMsg("Illegal change of number of phases for Line."+elem.getName(), 181);
					}
				break;
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				elem.setLineCodeSpecified(false);
				elem.killGeometrySpecified();
				elem.killSpacingSpecified();
				elem.resetLengthUnits();
				elem.setSymComponentsChanged(true);
				elem.setSymComponentsModel(true);
				break;
			case 11:
			case 12:
			case 13:
				elem.setLineCodeSpecified(false);
				elem.setSymComponentsModel(false);
				elem.resetLengthUnits();
				elem.killGeometrySpecified();
				elem.killSpacingSpecified();
				break;
			case 14:
				if (elem.isSwitch()) {
					elem.setSymComponentsChanged(true);
					elem.setYPrimInvalid(true);
					elem.setGeometrySpecified(false);
					elem.setSpacingSpecified(false);
					elem.setR1(1.0);
					elem.setX1(1.0);
					elem.setR0(1.0);
					elem.setX0(1.0);
					elem.setC1(1.1 * 1.0e-9);
					elem.setC0(1.0 * 1.0e-9);
					elem.setLen(0.001);
					elem.resetLengthUnits();
				}
				break;
			case 16:
			case 17:
				elem.setKXg(elem.getXg() / log(658.5 * sqrt(elem.getRho() / elem.getBaseFrequency())));
				break;
			case 18:
				elem.setGeometrySpecified(true);
				elem.setSymComponentsModel(false);
				elem.setSymComponentsChanged(false);
				break;
			case 20:
			case 21:
			case 23:
			case 24:
				if (elem.getLineSpacingObj() != null && elem.getWireData() != null) {
					elem.setSpacingSpecified(true);
					elem.setSymComponentsModel(false);
					elem.setSymComponentsChanged(false);
					elem.killGeometrySpecified();
				}
				elem.setYPrimInvalid(true);
				break;
			}

			// YPrim invalidation on anything that changes impedance values
			if (paramPointer >= 2 && paramPointer <= 13) {
				elem.setYPrimInvalid(true);
			} else if (paramPointer == 17) {
				if (elem.isGeometrySpecified() && (elem.getLineGeometryObj() != null))
					elem.getLineGeometryObj().setRhoEarth(elem.getRho());
			}

			switch (paramPointer) {
			case 9:
			case 10:
			case 13:
				elem.setCapSpecified(true);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		//if (elem.isSymComponentsChanged()) elem.recalcElementData();
		return 0;
	}

	@Override
	protected int makeLike(String lineName) {
		int success = 0;

		/* See if we can find this line name in the present collection */
		LineObj other = (LineObj) find(lineName);

		if (other != null) {
			LineObj elem = activeLineObj;

			if (elem.getNumPhases() != other.getNumPhases()) {
				elem.setNumPhases(other.getNumPhases());
				elem.setNumConds(elem.getNumPhases());  // force reallocation of terminals and conductors

				elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
				elem.setYPrimInvalid(true);

				// for a line, nPhases = nCond, for now
				elem.setZ(new CMatrix(elem.getNumPhases()));
				elem.setZinv(new CMatrix(elem.getNumPhases()));
				elem.setYc(new CMatrix(elem.getNumPhases()));
			}

			elem.getZ().copyFrom(other.getZ());
			// al.getZinv().copyFrom(OtherLine.getZinv());
			elem.getYc().copyFrom(other.getYc());
			elem.setR1(other.getR1());
			elem.setX1(other.getX1());
			elem.setR0(other.getR0());
			elem.setX0(other.getX0());
			elem.setC1(other.getC1());
			elem.setC0(other.getC0());
			elem.setLen(other.getLen());
			elem.setSymComponentsModel(other.isSymComponentsModel());
			elem.setCapSpecified(other.isCapSpecified());

			classMakeLike(other);  // take care of inherited class properties

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in Line makeLike: \"" + lineName + "\" not found.", 182);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Line.init()", -1);
		return 0;
	}

}
