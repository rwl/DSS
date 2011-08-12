package com.epri.dss.delivery.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.delivery.GICTransformer;
import com.epri.dss.delivery.GICTransformerObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class GICTransformerImpl extends PDClassImpl implements GICTransformer {

	private static GICTransformerObj ActiveGICTransformerObj;

	public GICTransformerImpl() {
		super();
		this.className = "GICTransformer";
		this.DSSClassType = DSSClassDefs.GIC_TRANSFORMER + DSSClassDefs.PD_ELEMENT;

		this.activeElement = -1;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);  // allow property list abbreviations
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
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new GICTransformerObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void GICTransSetBusH(final String S) {
		String S2;
		int dotpos;

		// set bus2 = busH1.0.0.0

		GICTransformerObj agt = getActiveGICTransformerObj();

		agt.setBus(1, S);

		// default bus2 to zero node of bus1. (wye grounded connection)

		// strip node designations from s
		dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos);  // copy up to dot
		} else {
			S2 = S.substring(0);
		}

		S2 = S2 + ".0.0.0";  // set default for up to 3 phases

		agt.setBus(2, S2);
		agt.setShunt(true);
	}

    private void GICTransSetBusX(final String S) {
    	String S2;
    	int dotpos;

		// special handling for bus X
		// make sure we have enough terminals defined
		// set bus2 = bus1.0.0.0

		GICTransformerObj agt = getActiveGICTransformerObj();

		if (agt.getNTerms() != 4) {  // have to have 4 terminals to set this property

			agt.setNTerms(4);
			agt.setNConds(agt.getNPhases());  // force reallocation of terminals and conductors
		}

		agt.setBus(3, S);

		// default bus4 to zero node of bus3. (wye grounded connection)

		// strip node designations from s
		dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos);  // copy up to dot
		} else {
			S2 = S.substring(0);
		}

		S2 = S2 + ".0.0.0";  // set default for up to 3 phases

		agt.setBus(4, S2);
		agt.setShunt(true);
    }

	@Override
	public int edit() {
		int ParamPointer;
		String ParamName;
		String Param;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int Result = 0;

		// continue parsing with contents of parser
		setActiveGICTransformerObj((GICTransformerObj) elementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveGICTransformerObj());  // use property to set this value

		GICTransformerObj agt = getActiveGICTransformerObj();

		ParamPointer = 0;
		ParamName = parser.getNextParam();
		Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < numProperties))
				agt.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +"."+ getName() + "\"", 350);
				break;
			case 0:
				GICTransSetBusH(Param);
				break;
			case 1:
				agt.setBus(2, Param);
				break;
			case 2:
				GICTransSetBusX(Param);
				break;
			case 3:
				agt.setBus(4, Param);
				break;
			case 4:
				// see below
				break;
			case 5:
				switch (Param.toUpperCase().charAt(0)) {
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
				classEdit(getActiveGICTransformerObj(), ParamPointer - NumPropsThisClass);
				break;
			}

			// some specials ...
			switch (ParamPointer) {
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
					Globals.getActiveCircuit().setBusNameRedefined(true);  // set global flag to signal circuit to rebuild bus defs
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
			if ((ParamPointer >= 2) && (ParamPointer <= 7))
				agt.setYPrimInvalid(true);

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		agt.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String GICTransName) {
		GICTransformerObj OtherGICTrans;
		int i;

		int Result = 0;
		/* See if we can find this Fault name in the present collection */
		OtherGICTrans = (GICTransformerObj) find(GICTransName);
		if (OtherGICTrans != null) {
			GICTransformerObj agt = getActiveGICTransformerObj();

			if (agt.getNPhases() != OtherGICTrans.getNPhases()) {
				agt.setNPhases(OtherGICTrans.getNPhases());
				agt.setNTerms(OtherGICTrans.getNTerms());
				agt.setNConds(agt.getNPhases());  // force reallocation of terminals and conductors

				agt.setYorder(agt.getNConds() * agt.getNTerms());
				agt.setYPrimInvalid(true);
			}

			agt.setBaseFrequency(OtherGICTrans.getBaseFrequency());
			agt.setG1(OtherGICTrans.getG1());
			agt.setG2(OtherGICTrans.getG2());
			agt.setSpecType(OtherGICTrans.getSpecType());

			classMakeLike(OtherGICTrans);

			for (i = 0; i < agt.getParentClass().getNumProperties(); i++)
				agt.setPropertyValue(i, OtherGICTrans.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in GICTransformer makeLike: \"" + GICTransName + "\" not found.", 351);
		}
		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement GICTransformer.init", -1);
		return 0;
	}

	public static GICTransformerObj getActiveGICTransformerObj() {
		return ActiveGICTransformerObj;
	}

	public static void setActiveGICTransformerObj(GICTransformerObj activeGICTransformerObj) {
		ActiveGICTransformerObj = activeGICTransformerObj;
	}

}
