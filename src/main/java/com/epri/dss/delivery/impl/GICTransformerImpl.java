package com.epri.dss.delivery.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.delivery.GICTransformer;
import com.epri.dss.delivery.GICTransformerObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class GICTransformerImpl extends PDClassImpl implements GICTransformer {

	public static GICTransformerObj activeGICTransformerObj;

	public GICTransformerImpl() {
		super();
		className = "GICTransformer";
		classType = DSSClassDefs.GIC_TRANSFORMER + DSSClassDefs.PD_ELEMENT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);  // allow property list abbreviations
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;

		numProperties = NumPropsThisClass;
	    countProperties();   // get inherited property count
	    allocatePropertyArrays();

	    // define property names
	    propertyName[0] = "BusH";
	    propertyName[1] = "BusNH";
	    propertyName[2] = "BusX";
	    propertyName[3] = "BusNX";
	    propertyName[4] = "phases";
	    propertyName[5] = "Type";
	    propertyName[6] = "R1";
	    propertyName[7] = "R2";

	    // define property help values
	    propertyHelp[0] = "Name of High-side(H) bus. Examples:"+CRLF+
	                       "BusH=busname"+CRLF+
	                       "BusH=busname.1.2.3";
	    propertyHelp[1] = "Name of Neutral bus for H, or first, winding. Defaults to all phases connected "+
	                       "to H-side bus, node 0, if not specified and transformer type is either GSU or YY. " +
	                       "(Shunt Wye Connection to ground reference)" +
	                       "For Auto, this is automatically set to the X bus.";
	    propertyHelp[2] = "Name of Low-side(X) bus, if type=Auto or YY. ";
	    propertyHelp[3] = "Name of Neutral bus for X, or Second, winding. Defaults to all phases connected "+
	                       "to X-side bus, node 0, if not specified. (Shunt Wye Connection to ground reference)";
	    propertyHelp[4] = "Number of Phases. Default is 3.";
	    propertyHelp[5] = "Type of transformer: {GSU* | Auto | YY}. Default is GSU.";
	    propertyHelp[6] = "Resistance, each phase, ohms for H winding, (Series winding, if Auto). Default is 0.0001. ";
	    propertyHelp[7] = "Resistance, each phase, ohms for X winding, (Common winding, if Auto). Default is 0.0001. ";


	    activeProperty = NumPropsThisClass - 1;
	    super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSSGlobals globals = DSSGlobals.getInstance();

		globals.getActiveCircuit().setActiveCktElement(new GICTransformerObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	private void GICTransSetBusH(final String s) {
		String s2;
		int dotpos;

		// set bus2 = busH1.0.0.0

		GICTransformerObj agt = activeGICTransformerObj;

		agt.setBus(1, s);

		// default bus2 to zero node of bus1. (wye grounded connection)

		// strip node designations from s
		dotpos = s.indexOf('.');
		if (dotpos >= 0) {
			s2 = s.substring(0, dotpos);  // copy up to dot
		} else {
			s2 = s.substring(0);
		}

		s2 = s2 + ".0.0.0";  // set default for up to 3 phases

		agt.setBus(2, s2);
		agt.setShunt(true);
	}

    private void GICTransSetBusX(final String s) {
    	String s2;
    	int dotpos;

		// special handling for bus X
		// make sure we have enough terminals defined
		// set bus2 = bus1.0.0.0

		GICTransformerObj agt = activeGICTransformerObj;

		if (agt.getNTerms() != 4) {  // have to have 4 terminals to set this property

			agt.setNTerms(4);
			agt.setNConds(agt.getNPhases());  // force reallocation of terminals and conductors
		}

		agt.setBus(3, s);

		// default bus4 to zero node of bus3. (wye grounded connection)

		// strip node designations from s
		dotpos = s.indexOf('.');
		if (dotpos >= 0) {
			s2 = s.substring(0, dotpos);  // copy up to dot
		} else {
			s2 = s.substring(0);
		}

		s2 = s2 + ".0.0.0";  // set default for up to 3 phases

		agt.setBus(4, s2);
		agt.setShunt(true);
    }

	@Override
	public int edit() {
		int paramPointer;
		String paramName;
		String param;

		DSSGlobals globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int result = 0;

		// continue parsing with contents of parser
		activeGICTransformerObj = (GICTransformerObj) elementList.getActive();
		globals.getActiveCircuit().setActiveCktElement(activeGICTransformerObj);  // use property to set this value

		GICTransformerObj agt = activeGICTransformerObj;

		paramPointer = 0;
		paramName = parser.getNextParam();
		param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if ((paramPointer >= 0) && (paramPointer < numProperties))
				agt.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				globals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ getName() + "\"", 350);
				break;
			case 0:
				GICTransSetBusH(param);
				break;
			case 1:
				agt.setBus(2, param);
				break;
			case 2:
				GICTransSetBusX(param);
				break;
			case 3:
				agt.setBus(4, param);
				break;
			case 4:
				// see below
				break;
			case 5:
				switch (param.toUpperCase().charAt(0)) {
				case 'G':
					agt.setSpecType(SPEC_GSU);
					break;
				case 'A':
					agt.setSpecType(SPEC_AUTO);
					break;
				case 'Y':
					agt.setSpecType(SPEC_YY);
					break;
				}
				break;
			case 6:
				agt.setG1(parser.makeDouble());
				if (agt.getG1() != 0.0) {
					agt.setG1(1.0 / agt.getG1());
				} else {
					agt.setG1(10000.0);  // default to a low resistance
				}
				break;
			case 7:
				agt.setG2(parser.makeDouble());
				if (agt.getG2() != 0.0) {
					agt.setG2(1.0 / agt.getG2());
				} else {
					agt.setG2(10000.0);  // default to a low resistance
				}
				break;
			default:
				// inherited
				classEdit(activeGICTransformerObj, paramPointer - NumPropsThisClass);
				break;
			}

			// some specials ...
			switch (paramPointer) {
			case 0:
				agt.setPropertyValue(1, agt.getBus(2));  // bus2 gets modified if bus1 is set
				break;
			case 2:
				agt.setPropertyValue(3, agt.getBus(4));  // bus4 gets modified if bus3(X) is set
				if (agt.getSpecType() == SPEC_AUTO) {
					// automatically make up series-to-common connection
					agt.setBus(2, agt.getBus(3));
					agt.setPropertyValue(1, agt.getBus(2));
				}
				break;
			case 4:
				if (agt.getNPhases() != parser.makeInteger()) {
					agt.setNPhases(parser.makeInteger());
					agt.setNConds(agt.getNPhases());  // force reallocation of terminal info if different size
					globals.getActiveCircuit().setBusNameRedefined(true);  // set global flag to signal circuit to rebuild bus defs
				}
				break;
			case 5:
				switch (agt.getSpecType()) {
				case SPEC_AUTO:
					if (agt.getNTerms() == 2) {
						agt.setNTerms(4);
						agt.setNConds(agt.getNPhases());
					}
					agt.setBus(2, agt.getBus(3));
					break;
				}
				break;
			}

			// YPrim invalidation on anything that changes impedance values or no. of terminals
			if ((paramPointer >= 2) && (paramPointer <= 7))
				agt.setYPrimInvalid(true);

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		agt.recalcElementData();

		return result;
	}

	@Override
	protected int makeLike(String GICTransName) {
		GICTransformerObj otherGICTrans;
		int i;

		int result = 0;
		/* See if we can find this Fault name in the present collection */
		otherGICTrans = (GICTransformerObj) find(GICTransName);
		if (otherGICTrans != null) {
			GICTransformerObj agt = activeGICTransformerObj;

			if (agt.getNPhases() != otherGICTrans.getNPhases()) {
				agt.setNPhases(otherGICTrans.getNPhases());
				agt.setNTerms(otherGICTrans.getNTerms());
				agt.setNConds(agt.getNPhases());  // force reallocation of terminals and conductors

				agt.setYorder(agt.getNConds() * agt.getNTerms());
				agt.setYPrimInvalid(true);
			}

			agt.setBaseFrequency(otherGICTrans.getBaseFrequency());
			agt.setG1(otherGICTrans.getG1());
			agt.setG2(otherGICTrans.getG2());
			agt.setSpecType(otherGICTrans.getSpecType());

			classMakeLike(otherGICTrans);

			for (i = 0; i < agt.getParentClass().getNumProperties(); i++)
				agt.setPropertyValue(i, otherGICTrans.getPropertyValue(i));
			result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in GICTransformer makeLike: \"" + GICTransName + "\" not found.", 351);
		}
		return result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement GICTransformer.init", -1);
		return 0;
	}

}
