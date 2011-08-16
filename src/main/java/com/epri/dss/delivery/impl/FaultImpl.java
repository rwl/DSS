package com.epri.dss.delivery.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Fault;
import com.epri.dss.delivery.FaultObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class FaultImpl extends PDClassImpl implements Fault {

	public static FaultObj activeFaultObj;

	public FaultImpl() {
		super();
		className = "Fault";
		classType = DSSClassDefs.FAULTOBJECT + DSSClassDefs.NON_PCPD_ELEM;  // only in fault object class

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

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
		propertyHelp[0] = "Name of first bus. Examples:"+DSSGlobals.CRLF+
						"bus1=busname"+DSSGlobals.CRLF+
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

		DSSGlobals.activeCircuit.setActiveCktElement(new FaultObjImpl(this, objName));
		return addObjectToList(DSSGlobals.activeDSSObject);
	}

	private void doGmatrix() {
		FaultObj af = activeFaultObj;

		double[] matBuffer = new double[af.getNPhases() * af.getNPhases()];
		int orderFound = Parser.getInstance().parseAsSymMatrix(af.getNPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful  TODO Check zero based indexing
			/* X */
			af.setGMatrix( Utilities.resizeArray(af.getGMatrix(), af.getNPhases() * af.getNPhases()) );
			for (int j = 0; j < af.getNPhases() * af.getNPhases(); j++)
				af.getGMatrix()[j] = matBuffer[j];
		}

		matBuffer = null;
	}

	private void fltSetBus1(String s) {
		String s2;

		// special handling for bus 1
		// set bus2 = bus1.0.0.0

		FaultObj af = activeFaultObj;

		af.setBus(1, s);  // TODO Check zero based indexing

		// default bus2 to zero node of bus1. (wye grounded connection)

		// strip node designations from s
		int dotpos = s.indexOf('.');
		if (dotpos >= 0) {
			s2 = s.substring(0, dotpos);  // copy up to dot
		} else {
			s2 = s.substring(s.length());
		}

		s2 = s2 + ".0.0.0";  // set default for up to 3 phases

		af.setBus(2, s2);  // TODO Check zero based indexing
		af.setShunt(true);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of parser
		activeFaultObj = (FaultObj) elementList.getActive();
		DSSGlobals.activeCircuit.setActiveCktElement(activeFaultObj);  // use property to set this value

		FaultObj af = activeFaultObj;

		int paramPointer = 0;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if ((paramPointer >= 0) && (paramPointer <= numProperties))
				af.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSSGlobals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ af.getName() + "\"", 350);
				break;
			case 0:
				fltSetBus1(param);
				break;
			case 1:
				af.setBus(2, param);  // TODO Check zero based indexing
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
				af.setTemporary(Utilities.interpretYesNo(param));
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
				af.setPropertyValue(1, af.getBus(1));  // bus2 gets modified if bus1 is   TODO Check zero based indexing
				break;
			case 1:
				if (!Utilities.stripExtension(af.getBus(0)).equalsIgnoreCase( Utilities.stripExtension(af.getBus(1)) ))
					af.setShunt(false);
				break;
			case 2:
				if (af.getNPhases() != parser.makeInteger()) {
					af.setNPhases(parser.makeInteger());
					af.setNConds(af.getNPhases());  // force reallocation of terminal info
					DSSGlobals.activeCircuit.setBusNameRedefined(true);  // set global flag to signal circuit to rebuild bus defs
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

			if (af.getNPhases() != otherFault.getNPhases()) {
				af.setNPhases(otherFault.getNPhases());
				af.setNConds(af.getNPhases());  // force reallocation of terminals and conductors

				af.setYorder(af.getNConds() * af.getNTerms());
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
				af.setGMatrix( (double[]) Utilities.resizeArray(af.getGMatrix(), af.getNPhases() * af.getNPhases()) );
				for (int i = 0; i < af.getNPhases() * af.getNPhases(); i++)
					af.getGMatrix()[i] = otherFault.getGMatrix()[i];
			}

			classMakeLike(otherFault);

			for (int i = 0; i < af.getParentClass().getNumProperties(); i++)
				af.setPropertyValue(i, otherFault.getPropertyValue(i));
			result = 1;
		} else {
			DSSGlobals.doSimpleMsg("Error in Fault.makeLike(): \"" + faultName + "\" not found.", 351);
		}
		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.doSimpleMsg("Need to implement Fault.init()", -1);
		return 0;
	}

}
