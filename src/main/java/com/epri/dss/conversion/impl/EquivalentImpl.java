package com.epri.dss.conversion.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.conversion.Equivalent;
import com.epri.dss.conversion.EquivalentObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.CommandListImpl;

public class EquivalentImpl extends PCClassImpl implements Equivalent {

	private static EquivalentObj ActiveEquivalentObj;

	public EquivalentImpl() {
		super();
		this.className = "Equivalent";
		this.DSSClassType = DSSClassDefs.SOURCE + DSSClassDefs.NON_PCPD_ELEM;  // don"t want this in PC element list

		this.activeElement = -1;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

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
		propertyHelp[1] = "Array of Bus Names to which equivalent source is connected."+DSSGlobals.CRLF+"buses=(b1 b2 b3)";
		propertyHelp[2] = "Base Source kV, usually L-L unless you are making a positive-sequence model"+
					"in which case, it will be L-N.";
		propertyHelp[3] = "Per unit of the base voltage that the source is actually operating at."+ DSSGlobals.CRLF +
						"\"pu=1.05\"";
		propertyHelp[4] = "Phase angle in degrees of first phase: e.g.,Angle=10.3";
		propertyHelp[5] = "Source frequency.  Defaults to  60 Hz.";
		propertyHelp[6] = "Number of phases.  Defaults to 3.";
		propertyHelp[7] = "Positive-sequence resistance matrix, lower triangle.";
		propertyHelp[8] = "Positive-sequence reactance matrix, lower triangle.";
		propertyHelp[9] = "Zero-sequence resistance matrix, lower triangle.";
		propertyHelp[10] = "Zero-sequence reactance matrix, lower triangle.";

		activeProperty = Equivalent.NumPropsThisClass;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		// override help string
		propertyHelp[Equivalent.NumPropsThisClass + 1] = "Name of harmonic spectrum for this source.  Default is \"defaultvsource\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new EquivalentObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveEquivalentObj((EquivalentObj) elementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveEquivalentObj());

		int Result = 0;

		EquivalentObj ae = getActiveEquivalentObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param     = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < numProperties))
				ae.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"Equivalent."+ae.getName()+"\"", 800);
				break;
			case 0:
				ae.setNTerms(ae.doTerminalsDef(parser.makeInteger()));
				break;
			case 1:
				interpretAllBuses(Param);
				break;
			case 2:
				ae.setkVBase(parser.makeDouble());  // basekv
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
				ae.setNPhases(parser.makeInteger());  // num phases
				ae.setNConds(ae.getNPhases());  // force reallocation of terminal info
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
				classEdit(getActiveEquivalentObj(), ParamPointer - Equivalent.NumPropsThisClass);
				break;
			}

			if ((ParamPointer == 0) || ((ParamPointer >= 7) && (ParamPointer <= 10))) {
				ae.setNeedToDoRecalc(true);
			}

			ParamName = parser.getNextParam();
			Param     = parser.makeString();
		}

		// recalcElementData();
		ae.setYPrimInvalid(true);

		return Result;
	}

	@Override
	protected int makeLike(String OtherSource) {
		int i, Result = 0;

		/* See if we can find this line name in the present collection */
		EquivalentObj OtherEquivalent = (EquivalentObj) find(OtherSource);
		if (OtherEquivalent != null) {
			EquivalentObj ae = getActiveEquivalentObj();

			if ((ae.getNPhases() != OtherEquivalent.getNPhases()) ||
					(ae.getNTerms() != OtherEquivalent.getNTerms())) {

				ae.setNTerms( ae.doTerminalsDef(OtherEquivalent.getNTerms()) );
				ae.setNPhases(OtherEquivalent.getNPhases());
				ae.setNConds(ae.getNPhases());  // forces reallocation of terminal stuff

				ae.setYorder(ae.getNConds() * ae.getNTerms());
				ae.setYPrimInvalid(true);

				for (i = 0; i < ae.getNTerms(); i++)
					ae.getR1()[i] = OtherEquivalent.getR1()[i];
				for (i = 0; i < ae.getNTerms(); i++)
					ae.getR0()[i] = OtherEquivalent.getR0()[i];

				for (i = 0; i < ae.getNTerms(); i++)
					ae.getX1()[i] = OtherEquivalent.getX1()[i];
				for (i = 0; i < ae.getNTerms(); i++)
					ae.getX0()[i] = OtherEquivalent.getX0()[i];

				if (ae.getZ() != null)
					ae.setZ(null);
				if (ae.getZinv() != null)
					ae.setZinv(null);

				ae.setZ(new CMatrixImpl(ae.getNPhases()));
				ae.setZinv(new CMatrixImpl(ae.getNPhases()));
			}

			ae.getZ().copyFrom(OtherEquivalent.getZ());
			// ae.getZinv().copyFrom(OtherLine.getZinv());
			ae.setVMag(OtherEquivalent.getVMag());
			ae.setkVBase(OtherEquivalent.getkVBase());
			ae.setPerUnit(OtherEquivalent.getPerUnit());
			ae.setAngle(OtherEquivalent.getAngle());
			ae.setEquivFrequency(OtherEquivalent.getEquivFrequency());

			classMakeLike(OtherEquivalent);

			for (i = 0; i < ae.getParentClass().getNumProperties(); i++)
				ae.setPropertyValue(i, OtherEquivalent.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Equivalent makeLike: \"" + OtherSource + "\" not found.", 801);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Equivalent.init", -1);
		return 0;
	}

	/**
	 * Routine expecting all winding connections expressed in one array of strings.
	 */
	public void interpretAllBuses(String S) {
		String BusNam;
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getAuxParser().setCmdString(S);  // load up parser

		/* Loop for no more than the expected number of windings; ignore omitted values */
		EquivalentObj ae = getActiveEquivalentObj();
		for (int i = 0; i < ae.getNTerms(); i++) {
			Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
			BusNam = Globals.getAuxParser().makeString();
			if (BusNam.length() > 0)
				ae.setBus(i, BusNam);  // TODO Check zero based indexing
		}
	}

	public static EquivalentObj getActiveEquivalentObj() {
		return ActiveEquivalentObj;
	}

	public static void setActiveEquivalentObj(EquivalentObj activeEquivalentObj) {
		ActiveEquivalentObj = activeEquivalentObj;
	}

}
