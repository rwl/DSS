package com.epri.dss.delivery.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Capacitor;
import com.epri.dss.delivery.CapacitorObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class CapacitorImpl extends PDClassImpl implements Capacitor {

	private static CapacitorObj ActiveCapacitorObj;

	public CapacitorImpl() {
		super();
		this.className = "Capacitor";
		this.DSSClassType = DSSClassType + DSSClassDefs.CAP_ELEMENT;

		this.activeElement = -1;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;

		numProperties = Capacitor.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();


		// define property names
		propertyName[0] = "bus1";
		propertyName[1] = "bus2";
		propertyName[2] = "phases";
		propertyName[3] = "kvar";
		propertyName[4] = "kv";
		propertyName[5] = "conn";
		propertyName[6] = "cmatrix";
		propertyName[7] = "cuf";
		propertyName[8] = "R";
		propertyName[9] = "XL";
		propertyName[10] = "Harm";
		propertyName[11] = "Numsteps";
		propertyName[12] = "states";

		// define property help values
		propertyHelp[0] = "Name of first bus. Examples:" + CRLF +
							"bus1=busname" + CRLF + "bus1=busname.1.2.3";
		propertyHelp[1] = "Name of 2nd bus. Defaults to all phases connected "+
							"to first bus, node 0. (Shunt Wye Connection)" + CRLF +
							"Not necessary to specify for delta (LL) connection";
		propertyHelp[2] = "Number of phases.";
		propertyHelp[3] = "Total kvar, if one step, or ARRAY of kvar ratings for each step.  Evenly divided among phases. See rules for NUMSTEPS.";
		propertyHelp[4] = "For 2, 3-phase, kV phase-phase. Otherwise specify actual can rating.";
		propertyHelp[5] = "={wye | delta |LN |LL}  Default is wye, which is equivalent to LN";
		propertyHelp[6] = "Nodal cap. matrix, lower triangle, microfarads, of the following form:"+CRLF+CRLF+
							"cmatrix=\"c11 | -c21 c22 | -c31 -c32 c33\""+CRLF+CRLF+
							"All steps are assumed the same if this property is used.";
		propertyHelp[7] = "ARRAY of Capacitance, each phase, for each step, microfarads."+CRLF+
							"See Rules for NumSteps.";
		propertyHelp[8] = "ARRAY of series resistance in each phase (line), ohms. Default is 0.0";
		propertyHelp[9] = "ARRAY of series inductive reactance(s) in each phase (line) for filter, ohms at base frequency. Use this OR \"h\" property to define filter. Default is 0.0.";
		propertyHelp[10] = "ARRAY of harmonics to which each step is tuned. Zero is interpreted as meaning zero reactance (no filter). Default is zero.";
		propertyHelp[11] = "Number of steps in this capacitor bank. Default = 1. Forces reallocation of the capacitance, reactor, and states array.  Rules: "+
							"If this property was previously =1, the value in the kvar property is divided equally among the steps. The kvar property " +
							"does not need to be reset if that is accurate.  If the Cuf or Cmatrix property was used previously, all steps are set to the value of the first step. " +
							"The states property is set to all steps on. All filter steps are set to the same harmonic. " +
							"If this property was previously >1, the arrays are reallocated, but no values are altered. You must SUBSEQUENTLY assign all array properties.";
		propertyHelp[12] = "ARRAY of integers {1|0} states representing the state of each step (on|off). Defaults to 1 when reallocated (on). "+
							"Capcontrol will modify this array as it turns steps on or off.";

		activeProperty = Capacitor.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new CapacitorObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void doCmatrix() {
		int OrderFound, j;
		double[] MatBuffer;

		CapacitorObj aco = getActiveCapacitorObj();

		MatBuffer = new double[aco.getNPhases() * aco.getNPhases()];
		OrderFound = Parser.getInstance().parseAsSymMatrix(aco.getNPhases(), MatBuffer);

		if (OrderFound > 0) {  // parse was successful
			/* C */
			aco.setCmatrix((double[]) Utilities.resizeArray(aco.getCmatrix(), aco.getNPhases() * aco.getNPhases()));
			for (j = 0; j < aco.getNPhases() * aco.getNPhases(); j++)
				aco.getCmatrix()[j] = 1.0e-6 * MatBuffer[j];
		}

		MatBuffer = null;
	}

	/**
	 * Accepts (case insensitive):
	 *   delta or LL
	 *   Y, wye, or LN
	 */
	private void interpretConnection(String S) {
		CapacitorObj aco = getActiveCapacitorObj();

		String TestS = S.toLowerCase();
		switch (TestS.charAt(0)) {
		case 'y':
			aco.setConnection(0);  /* Wye */
			break;
		case 'w':
			aco.setConnection(0);  /* Wye */
			break;
		case 'd':
			aco.setConnection(1);  /* Delta or Line-Line */
			break;
		case 'l':
			switch (TestS.charAt(1)) {
			case 'n':
				aco.setConnection(0);
				break;
			case 'l':
				aco.setConnection(1);
				break;
			}
			break;
		}

		switch (aco.getConnection()) {
		case 1:
			aco.setNTerms(1);  // force reallocation of terminals
			break;
		case 0:
			if (aco.getNTerms() != 2)
				aco.setNTerms(2);
			break;
		}
	}

	private void capSetBus1(String S) {
		String S2;
		int i, dotpos;

		// special handling for bus 1
		// set bus2 = bus1.0.0.0

		CapacitorObj aco = getActiveCapacitorObj();

		aco.setBus(0, S);

		// default bus2 to zero node of bus1. (grounded-Y connection)

		// strip node designations from s
		dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos - 1);
		} else {
			S2 = S.substring(0, S.length());  // copy up to dot
		}
		for (i = 0; i < aco.getNPhases(); i++)
			S2 = S2 + ".0";   // append series of ".0"'s

		aco.setBus(1, S2);    // default setting for bus2
		aco.setShunt(true);
	}

	@Override
	public int edit() {
		int Result = 0;
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveCapacitorObj((CapacitorObj) elementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveCapacitorObj());  // use property to set this value

		CapacitorObj aco = getActiveCapacitorObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer > 0) && (ParamPointer <= numProperties))  // TODO Check zero based indexing
				aco.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case 0:
				Globals.doSimpleMsg("Unknown parameter \""+ParamName+"\" for object \"Capacitor."+aco.getName()+"\"", 450);
				break;
			case 1:
				capSetBus1(Param);
				break;
			case 2:
				aco.setBus(1, Param);
				break;
			case 3:
				//aco.setNumPhases(parser.makeInteger());  // see below
				break;
			case 4:
				Utilities.interpretDblArray(Param, aco.getNumSteps(), aco.getKvarrating());
				break;
			case 5:
				aco.setKvrating(parser.makeDouble());
				break;
			case 6:
				interpretConnection(Param);
				break;
			case 7:
				doCmatrix();
				break;
			case 8:
				Utilities.interpretDblArray(Param, aco.getNumSteps(), aco.getC());
				break;
			case 9:
				Utilities.interpretDblArray(Param, aco.getNumSteps(), aco.getR());
				break;
			case 10:
				Utilities.interpretDblArray(Param, aco.getNumSteps(), aco.getXL());
				break;
			case 11:
				aco.processHarmonicSpec(Param);
				break;
			case 12:
				aco.setNumSteps(parser.makeInteger());
				break;
			case 13:
				aco.processStatesSpec(Param);
				break;
			default:
				// inherited property edits
				classEdit(getActiveCapacitorObj(), ParamPointer - Capacitor.NumPropsThisClass);
				break;
			}

			// some specials ...
			switch (ParamPointer) {
			case 0:
				aco.setPropertyValue(1, aco.getBus(1));  // this gets modified
				aco.getPrpSequence()[1] = 0;  // reset this for save function
				break;
			case 1:
				if (!Utilities.stripExtension(aco.getBus(1)).equalsIgnoreCase( Utilities.stripExtension(aco.getBus(2)) ))
					aco.setShunt(false);
				break;
			case 2:
				if (aco.getNPhases() != parser.makeInteger()) {
					aco.setNPhases(parser.makeInteger());
					aco.setNConds(aco.getNPhases());  // force reallocation of terminal info
					aco.setYorder(aco.getNTerms() * aco.getNConds());
				}
				break;
			case 3:
				aco.setSpecType(1);
				break;
			case 6:
				aco.setSpecType(3);
				break;
			case 7:
				aco.setSpecType(2);
				for (int i = 0; i < aco.getNumSteps(); i++)
					aco.getC()[i] = aco.getC()[i] * 1.0e-6;
				break;
			case 9:
				for (int i = 0; i < aco.getNumSteps(); i++)
					if (aco.getXL()[i] != 0.0)
						if (aco.getR()[i] == 0.0)
							aco.getR()[i] = Math.abs(aco.getXL()[i]) / 1000.0;  // put in something so it doesn't fail
				aco.setDoHarmonicRecalc(false);  // XL is specified
				break;
			}

			// YPrim invalidation on anything that changes impedance values
			switch (ParamPointer) {
			case 3:
				aco.setYPrimInvalid(true);
				break;
			case 4:
				aco.setYPrimInvalid(true);
				break;
			case 5:
				aco.setYPrimInvalid(true);
				break;
			case 6:
				aco.setYPrimInvalid(true);
				break;
			case 7:
				aco.setYPrimInvalid(true);
				break;
			case 8:
				aco.setYPrimInvalid(true);
				break;
			case 12:
				aco.setYPrimInvalid(true);
				break;
			case 13:
				aco.setYPrimInvalid(true);
				break;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		aco.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String CapacitorName) {
		int Result = 0;
		/* See if we can find this capacitor name in the present collection */
		CapacitorObj OtherCapacitor = (CapacitorObj) find(CapacitorName);
		if (OtherCapacitor != null) {
			CapacitorObj aco = getActiveCapacitorObj();

			if (aco.getNPhases() != OtherCapacitor.getNPhases()) {
				aco.setNPhases(OtherCapacitor.getNPhases());
				aco.setNConds(aco.getNPhases());  // force reallocation of terminals and conductors

				aco.setYorder(aco.getNConds() * aco.getNTerms());
				aco.setYPrimInvalid(true);
			}

			aco.setNumSteps(OtherCapacitor.getNumSteps());

			for (int i = 0; i < aco.getNumSteps(); i++) {
				aco.getC()[i] = OtherCapacitor.getC()[i];
				aco.getKvarrating()[i] = OtherCapacitor.getKvarrating()[i];
				aco.getR()[i]  = OtherCapacitor.getR()[i];
				aco.getXL()[i] = OtherCapacitor.getXL()[i];
				aco.getXL()[i] = OtherCapacitor.getXL()[i];
				aco.getHarm()[i] = OtherCapacitor.getHarm()[i];
				aco.getStates()[i] = OtherCapacitor.getStates()[i];
			}

			aco.setKvrating(OtherCapacitor.getKvrating());
			aco.setConnection(OtherCapacitor.getConnection());
			aco.setSpecType(OtherCapacitor.getSpecType());

			if (OtherCapacitor.getCmatrix() == null) {
				aco.setCmatrix(new double[0]);
			} else {
				aco.setCmatrix((double[]) Utilities.resizeArray(aco.getCmatrix(), aco.getNPhases() * aco.getNPhases()));
				for (int i = 0; i < aco.getNPhases() * aco.getNPhases(); i++)
					aco.getCmatrix()[i] = OtherCapacitor.getCmatrix()[i];
			}

			classMakeLike(OtherCapacitor);  // take care of inherited class properties

			for (int i = 0; i < aco.getParentClass().getNumProperties(); i++) {
				aco.setPropertyValue(i, OtherCapacitor.getPropertyValue(i));
				Result = 1;
			}
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Capacitor.makeLike(): \"" + CapacitorName + "\" not found.", 451);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Capacitor.init()", 452);
		return 0;
	}

	public static CapacitorObj getActiveCapacitorObj() {
		return ActiveCapacitorObj;
	}

	public static void setActiveCapacitorObj(CapacitorObj activeCapacitorObj) {
		ActiveCapacitorObj = activeCapacitorObj;
	}

}
