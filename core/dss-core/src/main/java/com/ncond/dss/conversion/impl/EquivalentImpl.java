package com.ncond.dss.conversion.impl;

import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.conversion.Equivalent;
import com.ncond.dss.conversion.EquivalentObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.impl.CMatrixImpl;
import com.ncond.dss.shared.impl.CommandListImpl;

public class EquivalentImpl extends PCClassImpl implements Equivalent {

	public static EquivalentObj activeEquivalentObj;

	public EquivalentImpl() {
		super();
		className = "Equivalent";
		classType = DSSClassDefs.SOURCE + DSSClassDefs.NON_PCPD_ELEM;  // don"t want this in PC element list

		activeElement = -1;

		defineProperties();

		String[] Commands = new String[numProperties];
		System.arraycopy(propertyName, 0, Commands, 0, numProperties);
		commandList = new CommandListImpl(Commands);
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
		propertyHelp[Equivalent.NumPropsThisClass] = "Name of harmonic spectrum for this source.  Default is \"defaultvsource\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new EquivalentObjImpl(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeEquivalentObj = (EquivalentObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeEquivalentObj);

		int result = 0;

		EquivalentObj ae = activeEquivalentObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param     = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				ae.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"Equivalent."+ae.getName()+"\"", 800);
				break;
			case 0:
				ae.setNumTerms(ae.doTerminalsDef(parser.makeInteger()));
				break;
			case 1:
				interpretAllBuses(param);
				break;
			case 2:
				ae.setKvBase(parser.makeDouble());  // basekv
				break;
			case 3:
				ae.setPerUnit(parser.makeDouble());  // pu
				break;
			case 4:
				ae.setAngle(parser.makeDouble());  // ang
				break;
			case 5:
				ae.setEquivFrequency(parser.makeDouble());  // freq
				break;
			case 6:
				ae.setNumPhases(parser.makeInteger());  // num phases
				ae.setNumConds(ae.getNumPhases());  // force reallocation of terminal info
				break;
			case 7:
				ae.parseDblMatrix(ae.getR1());
				break;
			case 8:
				ae.parseDblMatrix(ae.getX1());
				break;
			case 9:
				ae.parseDblMatrix(ae.getR0());
				break;
			case 10:
				ae.parseDblMatrix(ae.getX0());
				break;
			default:
				classEdit(activeEquivalentObj, paramPointer - Equivalent.NumPropsThisClass);
				break;
			}

			if ((paramPointer == 0) || ((paramPointer >= 7) && (paramPointer <= 10))) {
				ae.setNeedToDoRecalc(true);
			}

			paramName = parser.getNextParam();
			param     = parser.makeString();
		}

		// recalcElementData();
		ae.setYPrimInvalid(true);

		return result;
	}

	@Override
	protected int makeLike(String OtherSource) {
		int i, result = 0;

		/* See if we can find this line name in the present collection */
		EquivalentObj otherEquivalent = (EquivalentObj) find(OtherSource);
		if (otherEquivalent != null) {
			EquivalentObj ae = activeEquivalentObj;

			if ((ae.getNumPhases() != otherEquivalent.getNumPhases()) ||
					(ae.getNumTerms() != otherEquivalent.getNumTerms())) {

				ae.setNumTerms( ae.doTerminalsDef(otherEquivalent.getNumTerms()) );
				ae.setNumPhases(otherEquivalent.getNumPhases());
				ae.setNumConds(ae.getNumPhases());  // forces reallocation of terminal stuff

				ae.setYOrder(ae.getNumConds() * ae.getNumTerms());
				ae.setYPrimInvalid(true);

				for (i = 0; i < ae.getNumTerms(); i++)
					ae.getR1()[i] = otherEquivalent.getR1()[i];
				for (i = 0; i < ae.getNumTerms(); i++)
					ae.getR0()[i] = otherEquivalent.getR0()[i];

				for (i = 0; i < ae.getNumTerms(); i++)
					ae.getX1()[i] = otherEquivalent.getX1()[i];
				for (i = 0; i < ae.getNumTerms(); i++)
					ae.getX0()[i] = otherEquivalent.getX0()[i];

				if (ae.getZ() != null)
					ae.setZ(null);
				if (ae.getZinv() != null)
					ae.setZInv(null);

				ae.setZ(new CMatrixImpl(ae.getNumPhases()));
				ae.setZInv(new CMatrixImpl(ae.getNumPhases()));
			}

			ae.getZ().copyFrom(otherEquivalent.getZ());
			// ae.getZinv().copyFrom(OtherLine.getZinv());
			ae.setVMag(otherEquivalent.getVMag());
			ae.setKvBase(otherEquivalent.getKvBase());
			ae.setPerUnit(otherEquivalent.getPerUnit());
			ae.setAngle(otherEquivalent.getAngle());
			ae.setEquivFrequency(otherEquivalent.getEquivFrequency());

			classMakeLike(otherEquivalent);

			for (i = 0; i < ae.getParentClass().getNumProperties(); i++)
				ae.setPropertyValue(i, otherEquivalent.getPropertyValue(i));
			result = 1;
		} else {
			DSS.doSimpleMsg("Error in Equivalent makeLike: \"" + OtherSource + "\" not found.", 801);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Equivalent.init", -1);
		return 0;
	}

	/**
	 * Routine expecting all winding connections expressed in one array of strings.
	 */
	@Override
	public void interpretAllBuses(String s) {
		String busName;

		DSS.auxParser.setCmdString(s);  // load up parser

		/* Loop for no more than the expected number of windings; ignore omitted values */
		EquivalentObj ae = activeEquivalentObj;
		for (int i = 0; i < ae.getNumTerms(); i++) {
			DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
			busName = DSS.auxParser.makeString();
			if (busName.length() > 0)
				ae.setBus(i, busName);
		}
	}

}
