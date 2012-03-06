package com.ncond.dss.delivery.impl;

import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.delivery.Capacitor;
import com.ncond.dss.delivery.CapacitorObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.impl.CommandListImpl;

public class CapacitorImpl extends PDClassImpl implements Capacitor {

	public static CapacitorObj activeCapacitorObj;

	public CapacitorImpl() {
		super();
		className = "Capacitor";
		classType = classType + DSSClassDefs.CAP_ELEMENT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		final String CRLF = DSS.CRLF;

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
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new CapacitorObjImpl(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void doCmatrix() {
		int orderFound, j;
		double[] matBuffer;

		CapacitorObj aco = activeCapacitorObj;

		matBuffer = new double[aco.getNumPhases() * aco.getNumPhases()];
		orderFound = Parser.getInstance().parseAsSymMatrix(aco.getNumPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful
			/* C */
			aco.setCMatrix( Util.resizeArray(aco.getCMatrix(), aco.getNumPhases() * aco.getNumPhases()) );
			for (j = 0; j < aco.getNumPhases() * aco.getNumPhases(); j++)
				aco.getCMatrix()[j] = 1.0e-6 * matBuffer[j];
		}

		matBuffer = null;
	}

	/**
	 * Accepts (case insensitive):
	 *   delta or LL
	 *   Y, wye, or LN
	 */
	private void interpretConnection(String s) {
		CapacitorObj aco = activeCapacitorObj;

		String testS = s.toLowerCase();
		switch (testS.charAt(0)) {
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
			switch (testS.charAt(1)) {
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
			aco.setNumTerms(1);  // force reallocation of terminals
			break;
		case 0:
			if (aco.getNumTerms() != 2)
				aco.setNumTerms(2);
			break;
		}
	}

	private void capSetBus1(String s) {
		String s2;
		int i, dotpos;

		// special handling for bus 1
		// set bus2 = bus1.0.0.0

		CapacitorObj aco = activeCapacitorObj;

		aco.setBus(0, s);

		// default bus2 to zero node of bus1. (grounded-Y connection)

		// strip node designations from s
		dotpos = s.indexOf('.');
		if (dotpos >= 0) {
			s2 = s.substring(0, dotpos - 1);
		} else {
			s2 = s.substring(0, s.length());  // copy up to dot
		}
		for (i = 0; i < aco.getNumPhases(); i++)
			s2 = s2 + ".0";   // append series of ".0"'s

		aco.setBus(1, s2);    // default setting for bus2
		aco.setShunt(true);
	}

	@Override
	public int edit() {
		int result = 0;
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeCapacitorObj = (CapacitorObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeCapacitorObj);  // use property to set this value

		CapacitorObj aco = activeCapacitorObj;

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
				aco.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \""+paramName+"\" for object \"Capacitor."+aco.getName()+"\"", 450);
				break;
			case 0:
				capSetBus1(param);
				break;
			case 1:
				aco.setBus(1, param);
				break;
			case 2:
				//aco.setNumPhases(parser.makeInteger());  // see below
				break;
			case 3:
				Util.interpretDblArray(param, aco.getNumSteps(), aco.getKVArRating());
				break;
			case 4:
				aco.setKVARating(parser.makeDouble());
				break;
			case 5:
				interpretConnection(param);
				break;
			case 6:
				doCmatrix();
				break;
			case 7:
				Util.interpretDblArray(param, aco.getNumSteps(), aco.getC());
				break;
			case 8:
				Util.interpretDblArray(param, aco.getNumSteps(), aco.getR());
				break;
			case 9:
				Util.interpretDblArray(param, aco.getNumSteps(), aco.getXL());
				break;
			case 10:
				aco.processHarmonicSpec(param);
				break;
			case 11:
				aco.setNumSteps(parser.makeInteger());
				break;
			case 12:
				aco.processStatesSpec(param);
				break;
			default:
				// inherited property edits
				classEdit(activeCapacitorObj, paramPointer - Capacitor.NumPropsThisClass);
				break;
			}

			// some specials ...
			switch (paramPointer) {
			case 0:
				aco.setPropertyValue(1, aco.getBus(0));  // this gets modified
				aco.getPrpSequence()[1] = 0;  // reset this for save function
				break;
			case 1:
				if (!Util.stripExtension(aco.getBus(0)).equalsIgnoreCase( Util.stripExtension(aco.getBus(1)) ))
					aco.setShunt(false);
				break;
			case 2:
				if (aco.getNumPhases() != parser.makeInteger()) {
					aco.setNumPhases(parser.makeInteger());
					aco.setNumConds(aco.getNumPhases());  // force reallocation of terminal info
					aco.setYOrder(aco.getNumTerms() * aco.getNumConds());
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
			switch (paramPointer) {
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

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		aco.recalcElementData();

		return result;
	}

	@Override
	protected int makeLike(String capacitorName) {
		int result = 0;
		/* See if we can find this capacitor name in the present collection */
		CapacitorObj otherCapacitor = (CapacitorObj) find(capacitorName);
		if (otherCapacitor != null) {
			CapacitorObj aco = activeCapacitorObj;

			if (aco.getNumPhases() != otherCapacitor.getNumPhases()) {
				aco.setNumPhases(otherCapacitor.getNumPhases());
				aco.setNumConds(aco.getNumPhases());  // force reallocation of terminals and conductors

				aco.setYOrder(aco.getNumConds() * aco.getNumTerms());
				aco.setYPrimInvalid(true);
			}

			aco.setNumSteps(otherCapacitor.getNumSteps());

			for (int i = 0; i < aco.getNumSteps(); i++) {
				aco.getC()[i] = otherCapacitor.getC()[i];
				aco.getKVArRating()[i] = otherCapacitor.getKVArRating()[i];
				aco.getR()[i]  = otherCapacitor.getR()[i];
				aco.getXL()[i] = otherCapacitor.getXL()[i];
				aco.getXL()[i] = otherCapacitor.getXL()[i];
				aco.getHarm()[i] = otherCapacitor.getHarm()[i];
				aco.getStates()[i] = otherCapacitor.getStates()[i];
			}

			aco.setKVARating(otherCapacitor.getKVRating());
			aco.setConnection(otherCapacitor.getConnection());
			aco.setSpecType(otherCapacitor.getSpecType());

			if (otherCapacitor.getCMatrix() == null) {
				aco.setCMatrix(new double[0]);
			} else {
				aco.setCMatrix((double[]) Util.resizeArray(aco.getCMatrix(), aco.getNumPhases() * aco.getNumPhases()));
				for (int i = 0; i < aco.getNumPhases() * aco.getNumPhases(); i++)
					aco.getCMatrix()[i] = otherCapacitor.getCMatrix()[i];
			}

			classMakeLike(otherCapacitor);  // take care of inherited class properties

			for (int i = 0; i < aco.getParentClass().getNumProperties(); i++) {
				aco.setPropertyValue(i, otherCapacitor.getPropertyValue(i));
				result = 1;
			}
		} else {
			DSS.doSimpleMsg("Error in Capacitor.makeLike(): \"" + capacitorName + "\" not found.", 451);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Capacitor.init()", 452);
		return 0;
	}

}
