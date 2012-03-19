/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.CommandList;

public class Equivalent extends PCClass {

	public static final int NumPropsThisClass = 16;

	public static EquivalentObj activeEquivalentObj;

	public Equivalent() {
		super();
		className = "Equivalent";
		classType = DSSClassDefs.SOURCE + DSSClassDefs.NON_PCPD_ELEM;  // don"t want this in PC element list

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		numProperties = Equivalent.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0] = "terminals";
		propertyName[1] = "buses";
		propertyName[2] = "basekv";
		propertyName[3] = "pu";
		propertyName[4] = "angle";
		propertyName[5] = "frequency";
		propertyName[6] = "phases";
		propertyName[7] = "R1";
		propertyName[8] = "X1";
		propertyName[9] = "R0";
		propertyName[10] = "X0";

		// define property help values
		propertyHelp[0] = "Number of terminals.  Default =1. Set this BEFORE defining matrices.";
		propertyHelp[1] = "Array of Bus Names to which equivalent source is connected."+DSS.CRLF+"buses=(b1 b2 b3)";
		propertyHelp[2] = "Base Source kV, usually L-L unless you are making a positive-sequence model"+
				"in which case, it will be L-N.";
		propertyHelp[3] = "Per unit of the base voltage that the source is actually operating at."+ DSS.CRLF +
				"\"pu=1.05\"";
		propertyHelp[4] = "Phase angle in degrees of first phase: e.g.,Angle=10.3";
		propertyHelp[5] = "Source frequency.  Defaults to  60 Hz.";
		propertyHelp[6] = "Number of phases.  Defaults to 3.";
		propertyHelp[7] = "Positive-sequence resistance matrix, lower triangle.";
		propertyHelp[8] = "Positive-sequence reactance matrix, lower triangle.";
		propertyHelp[9] = "Zero-sequence resistance matrix, lower triangle.";
		propertyHelp[10] = "Zero-sequence reactance matrix, lower triangle.";

		activeProperty = Equivalent.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		// override help string
		propertyHelp[Equivalent.NumPropsThisClass] = "Name of harmonic spectrum for this source. Default is \"defaultvsource\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new EquivalentObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeEquivalentObj = (EquivalentObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeEquivalentObj);

		EquivalentObj elem = activeEquivalentObj;

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
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"Equivalent." +
						elem.getName() + "\"", 800);
				break;
			case 0:
				elem.setNumTerms(elem.doTerminalsDef(parser.makeInteger()));
				break;
			case 1:
				interpretAllBuses(param);
				break;
			case 2:
				elem.setKVBase(parser.makeDouble());  // basekv
				break;
			case 3:
				elem.setPerUnit(parser.makeDouble());  // pu
				break;
			case 4:
				elem.setAngle(parser.makeDouble());  // ang
				break;
			case 5:
				elem.setEquivFrequency(parser.makeDouble());  // freq
				break;
			case 6:
				elem.setNumPhases(parser.makeInteger());  // num phases
				elem.setNumConds(elem.getNumPhases());  // force reallocation of terminal info
				break;
			case 7:
				elem.parseDblMatrix(elem.getR1());
				break;
			case 8:
				elem.parseDblMatrix(elem.getX1());
				break;
			case 9:
				elem.parseDblMatrix(elem.getR0());
				break;
			case 10:
				elem.parseDblMatrix(elem.getX0());
				break;
			default:
				classEdit(activeEquivalentObj, paramPointer - Equivalent.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 0:
			case 7:
			case 8:
			case 9:
			case 10:
				elem.setNeedToDoRecalc(true);
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		// recalcElementData();
		elem.setYPrimInvalid(true);

		return 0;
	}

	@Override
	protected int makeLike(String name) {
		int i, ret = 0;

		/* See if we can find this line name in the present collection */
		EquivalentObj other = (EquivalentObj) find(name);

		if (other != null) {
			EquivalentObj elem = activeEquivalentObj;

			if ((elem.getNumPhases() != other.getNumPhases()) ||
					(elem.getNumTerms() != other.getNumTerms())) {

				elem.setNumTerms(elem.doTerminalsDef(other.getNumTerms()));
				elem.setNumPhases(other.getNumPhases());
				elem.setNumConds(elem.getNumPhases());  // forces reallocation of terminal stuff

				elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
				elem.setYPrimInvalid(true);

				for (i = 0; i < elem.getNumTerms(); i++)
					elem.getR1()[i] = other.getR1()[i];

				for (i = 0; i < elem.getNumTerms(); i++)
					elem.getR0()[i] = other.getR0()[i];

				for (i = 0; i < elem.getNumTerms(); i++)
					elem.getX1()[i] = other.getX1()[i];

				for (i = 0; i < elem.getNumTerms(); i++)
					elem.getX0()[i] = other.getX0()[i];

				elem.setZ(new CMatrix(elem.getNumPhases()));
				elem.setZinv(new CMatrix(elem.getNumPhases()));
			}

			elem.getZ().copyFrom(other.getZ());
			// elem.getZinv().copyFrom(other.getZinv());
			elem.setVmag(other.getVmag());
			elem.setKVBase(other.getKVBase());
			elem.setPerUnit(other.getPerUnit());
			elem.setAngle(other.getAngle());
			elem.setEquivFrequency(other.getEquivFrequency());

			classMakeLike(other);

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
			ret = 1;
		} else {
			DSS.doSimpleMsg("Error in Equivalent makeLike: \"" + name + "\" not found.", 801);
		}

		return ret;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Equivalent.init", -1);
		return 0;
	}

	/**
	 * Routine expecting all winding connections expressed in one array of strings.
	 */
	public void interpretAllBuses(String s) {
		String busName;
		Parser parser = DSS.auxParser;

		parser.setCmdString(s);  // load up parser

		/* Loop for no more than the expected number of windings; ignore omitted values */
		EquivalentObj elem = activeEquivalentObj;
		for (int i = 0; i < elem.getNumTerms(); i++) {
			parser.getNextParam();  // ignore any parameter name  not expecting any
			busName = parser.makeString();
			if (busName.length() > 0)
				elem.setBus(i, busName);
		}
	}

}
