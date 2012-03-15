package com.ncond.dss.delivery;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Capacitor extends PDClass {

	static final int NumPropsThisClass = 13;

	public static CapacitorObj activeCapacitorObj;

	public Capacitor() {
		super();
		className = "Capacitor";
		classType = classType + DSSClassDefs.CAP_ELEMENT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
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
		propertyName[10] = "harm";
		propertyName[11] = "numsteps";
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
		DSS.activeCircuit.setActiveCktElement(new CapacitorObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void doCmatrix() {
		int orderFound, j;
		double[] matBuffer;

		CapacitorObj elem = activeCapacitorObj;

		matBuffer = new double[elem.getNumPhases() * elem.getNumPhases()];
		orderFound = Parser.getInstance().parseAsSymMatrix(elem.getNumPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful
			elem.setCmatrix(Util.resizeArray(elem.getCmatrix(), elem.getNumPhases() * elem.getNumPhases()));
			for (j = 0; j < elem.getNumPhases() * elem.getNumPhases(); j++)
				elem.getCmatrix()[j] = 1.0e-6 * matBuffer[j];
		}

		matBuffer = null;
	}

	/**
	 * Accepts (case insensitive):
	 *   delta or LL
	 *   Y, wye, or LN
	 */
	private void interpretConnection(String s) {
		CapacitorObj elem = activeCapacitorObj;

		String testS = s.toLowerCase();
		switch (testS.charAt(0)) {
		case 'y':
			elem.setConnection(Connection.WYE);
			break;
		case 'w':
			elem.setConnection(Connection.WYE);
			break;
		case 'd':
			elem.setConnection(Connection.DELTA);
			break;
		case 'l':
			switch (testS.charAt(1)) {
			case 'n':
				elem.setConnection(Connection.WYE);
				break;
			case 'l':
				elem.setConnection(Connection.DELTA);
				break;
			}
			break;
		}

		switch (elem.getConnection()) {
		case DELTA:
			elem.setNumTerms(1);  // force reallocation of terminals
			break;
		case WYE:
			if (elem.getNumTerms() != 2)
				elem.setNumTerms(2);
			break;
		}
	}

	private void capSetBus1(String s) {
		String s2;
		int i, dotpos;

		// special handling for bus 1
		// set bus2 = bus1.0.0.0

		CapacitorObj elem = activeCapacitorObj;

		elem.setBus(0, s);

		// default bus2 to zero node of bus1. (grounded-Y connection)

		// strip node designations from s
		dotpos = s.indexOf('.');
		if (dotpos >= 0) {
			s2 = s.substring(0, dotpos - 1);  // copy up to dot
		} else {
			s2 = s;
		}
		for (i = 0; i < elem.getNumPhases(); i++)
			s2 = s2 + ".0";   // append series of ".0"'s

		elem.setBus(1, s2);    // default setting for bus2
		elem.setShunt(true);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeCapacitorObj = (CapacitorObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeCapacitorObj);  // use property to set this value

		CapacitorObj elem = activeCapacitorObj;

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
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"Capacitor." +
						elem.getName() + "\"", 450);
				break;
			case 0:
				capSetBus1(param);
				break;
			case 1:
				elem.setBus(1, param);
				break;
			case 2:
				//elem.setNumPhases(parser.makeInteger());  // see below
				break;
			case 3:
				Util.interpretDblArray(param, elem.getNumSteps(), elem.getKVArRating());
				break;
			case 4:
				elem.setKVRating(parser.makeDouble());
				break;
			case 5:
				interpretConnection(param);
				break;
			case 6:
				doCmatrix();
				break;
			case 7:
				Util.interpretDblArray(param, elem.getNumSteps(), elem.getC());
				break;
			case 8:
				Util.interpretDblArray(param, elem.getNumSteps(), elem.getR());
				break;
			case 9:
				Util.interpretDblArray(param, elem.getNumSteps(), elem.getXL());
				break;
			case 10:
				elem.processHarmonicSpec(param);
				break;
			case 11:
				elem.setNumSteps(parser.makeInteger());
				break;
			case 12:
				elem.processStatesSpec(param);
				break;
			default:
				// inherited property edits
				classEdit(activeCapacitorObj, paramPointer - NumPropsThisClass);
				break;
			}

			// some specials ...
			switch (paramPointer) {
			case 0:
				elem.setPropertyValue(1, elem.getBus(1));  // this gets modified
				elem.getPrpSequence()[1] = 0;  // reset this for save function
				break;
			case 1:
				if (!Util.stripExtension(elem.getBus(0)).equalsIgnoreCase(Util.stripExtension(elem.getBus(1))))
					elem.setShunt(false);
				break;
			case 2:
				if (elem.getNumPhases() != parser.makeInteger()) {
					elem.setNumPhases(parser.makeInteger());
					elem.setNumConds(elem.getNumPhases());  // force reallocation of terminal info
					elem.setYOrder(elem.getNumTerms() * elem.getNumConds());
				}
				break;
			case 3:
				elem.setSpecType(CapacitorSpecType.KVAR);
				break;
			case 6:
				elem.setSpecType(CapacitorSpecType.CMATRIX);
				break;
			case 7:
				elem.setSpecType(CapacitorSpecType.CUF);
				for (int i = 0; i < elem.getNumSteps(); i++)
					elem.getC()[i] = elem.getC()[i] * 1.0e-6;
				break;
			case 9:
				for (int i = 0; i < elem.getNumSteps(); i++) {
					if (elem.getXL()[i] != 0.0)
						if (elem.getR()[i] == 0.0)
							elem.getR()[i] = Math.abs(elem.getXL()[i]) / 1000.0;  // put in something so it doesn't fail
				}
				elem.setDoHarmonicRecalc(false);  // XL is specified
				break;
			}

			// YPrim invalidation on anything that changes impedance values
			switch (paramPointer) {
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 11:
			case 12:
				elem.setYPrimInvalid(true);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		elem.recalcElementData();

		return 0;
	}

	@Override
	protected int makeLike(String capacitorName) {
		int success = 0;

		/* See if we can find this capacitor name in the present collection */
		CapacitorObj other = (CapacitorObj) find(capacitorName);

		if (other != null) {
			CapacitorObj elem = activeCapacitorObj;

			if (elem.getNumPhases() != other.getNumPhases()) {
				elem.setNumPhases(other.getNumPhases());
				elem.setNumConds(elem.getNumPhases());  // force reallocation of terminals and conductors

				elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
				elem.setYPrimInvalid(true);
			}

			elem.setNumSteps(other.getNumSteps());

			for (int i = 0; i < elem.getNumSteps(); i++) {
				elem.getC()[i] = other.getC()[i];
				elem.getKVArRating()[i] = other.getKVArRating()[i];
				elem.getR()[i]  = other.getR()[i];
				elem.getXL()[i] = other.getXL()[i];
				elem.getXL()[i] = other.getXL()[i];
				elem.getHarm()[i] = other.getHarm()[i];
				elem.getStates()[i] = other.getStates()[i];
			}

			elem.setKVRating(other.getKVRating());
			elem.setConnection(other.getConnection());
			elem.setSpecType(other.getSpecType());

			if (other.getCmatrix() == null) {
				elem.setCmatrix(new double[0]);
			} else {
				elem.setCmatrix((double[]) Util.resizeArray(elem.getCmatrix(), elem.getNumPhases() * elem.getNumPhases()));
				for (int i = 0; i < elem.getNumPhases() * elem.getNumPhases(); i++)
					elem.getCmatrix()[i] = other.getCmatrix()[i];
			}

			classMakeLike(other);  // take care of inherited class properties

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++) {
				elem.setPropertyValue(i, other.getPropertyValue(i));
				success = 1;
			}
		} else {
			DSS.doSimpleMsg("Error in Capacitor.makeLike(): \"" + capacitorName + "\" not found.", 451);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Capacitor.init()", 452);
		return 0;
	}

}
