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
		numProperties = NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0] = "bus1";
		propertyName[1] = "bus2";
		propertyName[2] = "phases";
		propertyName[3] = "r";
		propertyName[4] = "%stddev";
		propertyName[5] = "Gmatrix";
		propertyName[6] = "ontime";
		propertyName[7] = "temporary";
		propertyName[8] = "minAmps";

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

		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new FaultObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void doGmatrix() {
		FaultObj elem = activeFaultObj;

		double[] matBuffer = new double[elem.getNumPhases() * elem.getNumPhases()];
		int orderFound = Parser.getInstance().parseAsSymMatrix(elem.getNumPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful
			elem.setGMatrix(Util.resizeArray(elem.getGMatrix(), elem.getNumPhases() * elem.getNumPhases()));
			for (int j = 0; j < elem.getNumPhases() * elem.getNumPhases(); j++)
				elem.getGMatrix()[j] = matBuffer[j];
		}
	}

	private void faultSetBus1(String s) {
		String s2;

		// special handling for bus 1
		// set bus2 = bus1.0.0.0

		FaultObj elem = activeFaultObj;

		elem.setBus(0, s);

		// default bus2 to zero node of bus1. (wye grounded connection)

		// strip node designations from s
		int dotpos = s.indexOf('.');
		if (dotpos >= 0) {
			s2 = s.substring(0, dotpos);  // copy up to dot
		} else {
			s2 = s;
		}

		s2 = s2 + ".0.0.0";  // set default for up to 3 phases

		elem.setBus(1, s2);
		elem.setShunt(true);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeFaultObj = (FaultObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeFaultObj);  // use property to set this value

		FaultObj elem = activeFaultObj;

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
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() +"."+ elem.getName() + "\"", 350);
				break;
			case 0:
				faultSetBus1(param);
				break;
			case 1:
				elem.setBus(1, param);
				break;
			case 2:
				//elem.setNumPhases(parser.makeInteger());  // see below
				break;
			case 3:
				elem.setG(parser.makeDouble());
				if (elem.getG() != 0.0) {
					elem.setG(1.0 / elem.getG());
				} else {
					elem.setG(10000.0);  // default to a low resistance
				}
				break;
			case 4:
				elem.setStdDev(parser.makeDouble() * 0.01);
				break;
			case 5:
				doGmatrix();
				break;
			case 6:
				elem.setOnTime(parser.makeDouble());
				break;
			case 7:
				elem.setTemporary(Util.interpretYesNo(param));
				break;
			case 8:
				elem.setMinAmps(parser.makeDouble());
				break;
			default:
				// inherited
				classEdit(activeFaultObj, paramPointer - NumPropsThisClass);
				break;
			}

			// some specials ...
			switch (paramPointer) {
			case 0:
				elem.setPropertyValue(1, elem.getBus(1));  // bus2 gets modified if bus1 is
				break;
			case 1:
				if (!Util.stripExtension(elem.getBus(0)).equalsIgnoreCase(Util.stripExtension(elem.getBus(1))))
					elem.setShunt(false);
				break;
			case 2:
				if (elem.getNumPhases() != parser.makeInteger()) {
					elem.setNumPhases(parser.makeInteger());
					elem.setNumConds(elem.getNumPhases());  // force reallocation of terminal info
					DSS.activeCircuit.setBusNameRedefined(true);  // set global flag to signal circuit to rebuild bus defs
				}
				break;
			case 3:
				elem.setSpecType(1);
				break;
			case 5:
				elem.setSpecType(2);
				break;
			case 6:
				if (elem.getOnTime() > 0.0)
					elem.setOn(false);  // assume fault will be on later
				break;
			}

			// YPrim invalidation on anything that changes impedance values
			switch (paramPointer) {
			case 2:
			case 3:
			case 5:
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
	protected int makeLike(String faultName) {
		int success = 0;

		/* See if we can find this fault name in the present collection */
		FaultObj other = (FaultObj) find(faultName);

		if (other != null) {
			FaultObj elem = activeFaultObj;

			if (elem.getNumPhases() != other.getNumPhases()) {
				elem.setNumPhases(other.getNumPhases());
				elem.setNumConds(elem.getNumPhases());  // force reallocation of terminals and conductors

				elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
				elem.setYPrimInvalid(true);
			}

			elem.setBaseFrequency(other.getBaseFrequency());
			elem.setG(other.getG());
			elem.setSpecType(other.getSpecType());

			elem.setMinAmps(other.getMinAmps());
			elem.setTemporary(other.isTemporary());
			elem.setCleared(other.isCleared());
			elem.setOn(other.isOn());
			elem.setOnTime(other.getOnTime());

			if (other.getGMatrix() == null) {
				elem.setGMatrix(null);
			} else {
				elem.setGMatrix(Util.resizeArray(elem.getGMatrix(), elem.getNumPhases() * elem.getNumPhases()));
				for (int i = 0; i < elem.getNumPhases() * elem.getNumPhases(); i++)
					elem.getGMatrix()[i] = other.getGMatrix()[i];
			}

			classMakeLike(other);

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in Fault.makeLike(): \"" + faultName + "\" not found.", 351);
		}
		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Fault.init()", -1);
		return 0;
	}

}
