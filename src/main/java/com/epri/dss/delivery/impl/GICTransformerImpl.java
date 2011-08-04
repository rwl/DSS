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
		this.Class_Name = "GICTransformer";
		this.DSSClassType = DSSClassDefs.GIC_TRANSFORMER + DSSClassDefs.PD_ELEMENT;

		this.ActiveElement = -1;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);  // Allow property list abbreviations
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;

		NumProperties = NumPropsThisClass;
	    countProperties();   // Get inherited property count
	    allocatePropertyArrays();


	    // define property names

	    PropertyName[0] = "BusH";
	    PropertyName[1] = "BusNH";
	    PropertyName[2] = "BusX";
	    PropertyName[3] = "BusNX";
	    PropertyName[4] = "phases";
	    PropertyName[5] = "Type";
	    PropertyName[6] = "R1";
	    PropertyName[7] = "R2";

	    // define property help values
	    PropertyHelp[0] = "Name of High-side(H) bus. Examples:"+CRLF+
	                       "BusH=busname"+CRLF+
	                       "BusH=busname.1.2.3";
	    PropertyHelp[1] = "Name of Neutral bus for H, or first, winding. Defaults to all phases connected "+
	                       "to H-side bus, node 0, if not specified and transformer type is either GSU or YY. " +
	                       "(Shunt Wye Connection to ground reference)" +
	                       "For Auto, this is automatically set to the X bus.";
	    PropertyHelp[2] = "Name of Low-side(X) bus, if type=Auto or YY. ";
	    PropertyHelp[3] = "Name of Neutral bus for X, or Second, winding. Defaults to all phases connected "+
	                       "to X-side bus, node 0, if not specified. (Shunt Wye Connection to ground reference)";
	    PropertyHelp[4] = "Number of Phases. Default is 3.";
	    PropertyHelp[5] = "Type of transformer: {GSU* | Auto | YY}. Default is GSU.";
	    PropertyHelp[6] = "Resistance, each phase, ohms for H winding, (Series winding, if Auto). Default is 0.0001. ";
	    PropertyHelp[7] = "Resistance, each phase, ohms for X winding, (Common winding, if Auto). Default is 0.0001. ";


	    ActiveProperty = NumPropsThisClass - 1;
	    super.defineProperties();  // Add defs of inherited properties to bottom of list
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

		// Set Bus2 = BusH1.0.0.0

		GICTransformerObj agt = getActiveGICTransformerObj();

		agt.setBus(1, S);

		// Default Bus2 to zero node of Bus1. (Wye Grounded connection)

		// Strip node designations from S
		dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos - 1);  // copy up to dot
		} else {
			S2 = S.substring(0);
		}

		S2 = S2 + ".0.0.0";     // Set Default for up to 3 phases

		agt.setBus(2, S2);
		agt.setShunt(true);
	}

    private void GICTransSetBusX(final String S) {
    	String S2;
    	int dotpos;

		// Special handling for Bus X
		// Make sure we have enough terminals defined
		// Set Bus2 = Bus1.0.0.0

		GICTransformerObj agt = getActiveGICTransformerObj();

		if (agt.getNTerms() != 4) {  // have to have 4 terminals to set this property

			agt.setNTerms(4);
			agt.setNConds(agt.getNPhases()); // force reallocation of terminals and conductors
		}

		agt.setBus(3, S);

		// Default Bus4 to zero node of Bus3. (Wye Grounded connection)

		// Strip node designations from S
		dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos - 1);  // copy up to Dot
		} else {
			S2 = S.substring(0);
		}

		S2 = S2 + ".0.0.0";     // Set Default for up to 3 phases

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
		setActiveGICTransformerObj((GICTransformerObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveGICTransformerObj());  // use property to set this value

		GICTransformerObj agt = getActiveGICTransformerObj();

		ParamPointer = 0;
		ParamName = parser.getNextParam();
		Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < NumProperties))
				agt.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ getName() + "\"", 350);
			case 0:
				GICTransSetBusH(Param);
			case 1:
				agt.setBus(2, Param);
			case 2:
				GICTransSetBusX(Param);
			case 3:
				agt.setBus(4, Param);
			case 4:
				// see below
			case 5:
				switch (Param.toUpperCase().charAt(0)) {
				case 'G':
					agt.setSpecType(SPEC_GSU);
				case 'A':
					agt.setSpecType(SPEC_AUTO);
				case 'Y':
					agt.setSpecType(SPEC_YY);
				}
			case 6:
				agt.setG1(parser.makeDouble());
				if (agt.getG1() != 0.0) {
					agt.setG1(1.0 / agt.getG1());
				} else {
					agt.setG1(10000.0);  // Default to a low resistance
				}
			case 7:
				agt.setG2(parser.makeDouble());
				if (agt.getG2() != 0.0) {
					agt.setG2(1.0 / agt.getG2());
				} else {
					agt.setG2(10000.0);  // Default to a low resistance
				}
			default:
				// Inherited
				classEdit(getActiveGICTransformerObj(), ParamPointer - NumPropsThisClass);
			}

			// Some specials ...
			switch (ParamPointer) {
			case 0:
				agt.setPropertyValue(1, agt.getBus(2));  // Bus2 gets modified if bus1 is set
			case 2:
				agt.setPropertyValue(3, agt.getBus(4));  // Bus4 gets modified if bus3(X) is set
				if (agt.getSpecType() == SPEC_AUTO) {
					// automatically make up series-to-common connection
					agt.setBus(2, agt.getBus(3));
					agt.setPropertyValue(1, agt.getBus(2));
				}
			case 4:
				if (agt.getNPhases() != parser.makeInteger()) {
					agt.setNPhases(parser.makeInteger());
					agt.setNConds(agt.getNPhases());  // Force Reallocation of terminal info if different size
					Globals.getActiveCircuit().setBusNameRedefined(true);  // Set Global Flag to signal circuit to rebuild busdefs
				}
			case 5:
				switch (agt.getSpecType()) {
				case SPEC_AUTO:
					if (agt.getNTerms() == 2) {
						agt.setNTerms(4);
						agt.setNConds(agt.getNPhases());
					}
					agt.setBus(2, agt.getBus(3));
				}
			}

			// YPrim invalidation on anything that changes impedance values or no. of terminals
			if ((ParamPointer >= 2) && (ParamPointer <= 7))
				agt.setYprimInvalid(true);

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
				agt.setYprimInvalid(true);
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
			DSSGlobals.getInstance().doSimpleMsg("Error in GICTransformer MakeLike: \"" + GICTransName + "\" Not Found.", 351);
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
