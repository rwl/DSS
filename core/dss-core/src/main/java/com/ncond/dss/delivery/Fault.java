package com.ncond.dss.delivery;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Fault extends PDClass {

	public static final int NumPropsThisClass = 9;

	public static FaultObj activeFaultObj;

	public Fault() {
		super();
		className = "Fault";
		classType = DSSClassDefs.FAULTOBJECT + DSSClassDefs.NON_PCPD_ELEM;  // only in fault object class

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {

		numProperties = Fault.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "bus1";
		propertyName[1] = "bus2";
		propertyName[2] = "phases";
		propertyName[3] = "r";
		propertyName[4] = "%stddev";
		propertyName[5] = "Gmatrix";
		propertyName[6] = "ONtime";
		propertyName[7] = "temporary";
		propertyName[8] = "MinAmps";

		// define property help values
		propertyHelp[0] = "Name of first bus. Examples:"+DSS.CRLF+
						"bus1=busname"+DSS.CRLF+
						"bus1=busname.1.2.3";
		propertyHelp[1] = "Name of 2nd bus. Defaults to all phases connected "+
						"to first bus, node 0, if not specified. (Shunt Wye Connection to ground reference)";
		propertyHelp[2] = "Number of Phases. Default is 1.";
		propertyHelp[3] = "Resistance, each phase, ohms. Default is 0.0001. Assumed to be Mean value if gaussian random mode."+
						"Max value if uniform mode.  A Fault is actually a series resistance "+
						"that defaults to a wye connection to ground on the second terminal.  You "+
						"may reconnect the 2nd terminal to achieve whatever connection.  Use "+
						"the Gmatrix property to specify an arbitrary conductance matrix.";
		propertyHelp[4] = "Percent standard deviation in resistance to assume for Monte Carlo fault (MF) solution mode for GAUSSIAN distribution. Default is 0 (no variation from mean).";
		propertyHelp[5] = "Use this to specify a nodal conductance (G) matrix to represent some arbitrary resistance network. "+
						"Specify in lower triangle form as usual for DSS matrices.";
		propertyHelp[6] = "Time (sec) at which the fault is established for time varying simulations. Default is 0.0 " +
							"(on at the beginning of the simulation)";
		propertyHelp[7] = "{Yes | No} Default is No.  Designate whether the fault is temporary.  For Time-varying simulations, " +
							"the fault will be removed if the current through the fault drops below the MINAMPS criteria.";
		propertyHelp[8] = "Minimum amps that can sustain a temporary fault. Default is 5.";


		activeProperty = Fault.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new FaultObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void doGmatrix() {
		FaultObj af = activeFaultObj;

		double[] matBuffer = new double[af.getNumPhases() * af.getNumPhases()];
		int orderFound = Parser.getInstance().parseAsSymMatrix(af.getNumPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful
			/* X */
			af.setGMatrix( Util.resizeArray(af.getGMatrix(), af.getNumPhases() * af.getNumPhases()) );
			for (int j = 0; j < af.getNumPhases() * af.getNumPhases(); j++)
				af.getGMatrix()[j] = matBuffer[j];
		}

		matBuffer = null;
	}

	private void fltSetBus1(String s) {
		String s2;

		// special handling for bus 1
		// set bus2 = bus1.0.0.0

		FaultObj af = activeFaultObj;

		af.setBus(0, s);

		// default bus2 to zero node of bus1. (wye grounded connection)

		// strip node designations from s
		int dotpos = s.indexOf('.');
		if (dotpos >= 0) {
			s2 = s.substring(0, dotpos);  // copy up to dot
		} else {
			s2 = s.substring(0, s.length());
		}

		s2 = s2 + ".0.0.0";  // set default for up to 3 phases

		af.setBus(1, s2);
		af.setShunt(true);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of parser
		activeFaultObj = (FaultObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeFaultObj);  // use property to set this value

		FaultObj af = activeFaultObj;

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
				af.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ af.getName() + "\"", 350);
				break;
			case 0:
				fltSetBus1(param);
				break;
			case 1:
				af.setBus(1, param);
				break;
			case 2:
				//NumPhases = parser.makeInteger();  // see below
				break;
			case 3:
				af.setG(parser.makeDouble());
				if (af.getG() != 0.0) {
					af.setG(1.0 / af.getG());
				} else {
					af.setG(10000.0);  // default to a low resistance
				}
				break;
			case 4:
				af.setStdDev(parser.makeDouble() * 0.01);
				break;
			case 5:
				doGmatrix();
				break;
			case 6:
				af.setOnTime(parser.makeDouble());
				break;
			case 7:
				af.setTemporary(Util.interpretYesNo(param));
				break;
			case 8:
				af.setMinAmps(parser.makeDouble());
				break;
			default:
				// inherited
				classEdit(activeFaultObj, paramPointer - Fault.NumPropsThisClass);
				break;
			}

			// some specials ...
			switch (paramPointer) {
			case 0:
				af.setPropertyValue(1, af.getBus(1));  // bus2 gets modified if bus1 is
				break;
			case 1:
				if (!Util.stripExtension(af.getBus(0)).equalsIgnoreCase( Util.stripExtension(af.getBus(1)) ))
					af.setShunt(false);
				break;
			case 2:
				if (af.getNumPhases() != parser.makeInteger()) {
					af.setNumPhases(parser.makeInteger());
					af.setNumConds(af.getNumPhases());  // force reallocation of terminal info
					DSS.activeCircuit.setBusNameRedefined(true);  // set global flag to signal circuit to rebuild bus defs
				}
				break;
			case 3:
				af.setSpecType(1);
				break;
			case 5:
				af.setSpecType(2);
				break;
			case 6:
				if (af.getOnTime() > 0.0)
					af.setOn(false);  // assume fault will be on later
				break;
			}

			// YPrim invalidation on anything that changes impedance values
			switch (paramPointer) {
			case 3:
				af.setYPrimInvalid(true);
				break;
			case 4:
				af.setYPrimInvalid(true);
				break;
			case 6:
				af.setYPrimInvalid(true);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		af.recalcElementData();

		return result;
	}

	@Override
	protected int makeLike(String faultName) {

		int result = 0;
		/* See if we can find this fault name in the present collection */
		FaultObj otherFault = (FaultObj) find(faultName);
		if (otherFault != null) {
			FaultObj af = activeFaultObj;

			if (af.getNumPhases() != otherFault.getNumPhases()) {
				af.setNumPhases(otherFault.getNumPhases());
				af.setNumConds(af.getNumPhases());  // force reallocation of terminals and conductors

				af.setYOrder(af.getNumConds() * af.getNumTerms());
				af.setYPrimInvalid(true);
			}

			af.setBaseFrequency(otherFault.getBaseFrequency());
			af.setG(otherFault.getG());
			af.setSpecType(otherFault.getSpecType());

			af.setMinAmps(otherFault.getMinAmps());
			af.setTemporary(otherFault.isTemporary());
			af.setCleared(otherFault.isCleared());
			af.setOn(otherFault.isOn());
			af.setOnTime(otherFault.getOnTime());

			if (otherFault.getGMatrix() == null) {
				af.setGMatrix(null);
			} else {
				af.setGMatrix( (double[]) Util.resizeArray(af.getGMatrix(), af.getNumPhases() * af.getNumPhases()) );
				for (int i = 0; i < af.getNumPhases() * af.getNumPhases(); i++)
					af.getGMatrix()[i] = otherFault.getGMatrix()[i];
			}

			classMakeLike(otherFault);

			for (int i = 0; i < af.getParentClass().getNumProperties(); i++)
				af.setPropertyValue(i, otherFault.getPropertyValue(i));
			result = 1;
		} else {
			DSS.doSimpleMsg("Error in Fault.makeLike(): \"" + faultName + "\" not found.", 351);
		}
		return result;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Fault.init()", -1);
		return 0;
	}

}
