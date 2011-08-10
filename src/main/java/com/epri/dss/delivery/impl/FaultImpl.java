package com.epri.dss.delivery.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Fault;
import com.epri.dss.delivery.FaultObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class FaultImpl extends PDClassImpl implements Fault {

	private static FaultObj ActiveFaultObj;

	public FaultImpl() {
		super();
		this.Class_Name = "Fault";
		this.DSSClassType = DSSClassDefs.FAULTOBJECT + DSSClassDefs.NON_PCPD_ELEM;  // Only in Fault object class

		this.ActiveElement = -1;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		NumProperties = Fault.NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();


		// Define Property names

		PropertyName[0] = "bus1";
		PropertyName[1] = "bus2";
		PropertyName[2] = "phases";
		PropertyName[3] = "r";
		PropertyName[4] = "%stddev";
		PropertyName[5] = "Gmatrix";
		PropertyName[6] = "ONtime";
		PropertyName[7] = "temporary";
		PropertyName[8] = "MinAmps";

		// define Property help values
		PropertyHelp[0] = "Name of first bus. Examples:"+DSSGlobals.CRLF+
						"bus1=busname"+DSSGlobals.CRLF+
						"bus1=busname.1.2.3";
		PropertyHelp[1] = "Name of 2nd bus. Defaults to all phases connected "+
						"to first bus, node 0, if not specified. (Shunt Wye Connection to ground reference)";
		PropertyHelp[2] = "Number of Phases. Default is 1.";
		PropertyHelp[3] = "Resistance, each phase, ohms. Default is 0.0001. Assumed to be Mean value if gaussian random mode."+
						"Max value if uniform mode.  A Fault is actually a series resistance "+
						"that defaults to a wye connection to ground on the second terminal.  You "+
						"may reconnect the 2nd terminal to achieve whatever connection.  Use "+
						"the Gmatrix property to specify an arbitrary conductance matrix.";
		PropertyHelp[4] = "Percent standard deviation in resistance to assume for Monte Carlo fault (MF) solution mode for GAUSSIAN distribution. Default is 0 (no variation from mean).";
		PropertyHelp[5] = "Use this to specify a nodal conductance (G) matrix to represent some arbitrary resistance network. "+
						"Specify in lower triangle form as usual for DSS matrices.";
		PropertyHelp[6] = "Time (sec) at which the fault is established for time varying simulations. Default is 0.0 " +
							"(on at the beginning of the simulation)";
		PropertyHelp[7] = "{Yes | No} Default is No.  Designate whether the fault is temporary.  For Time-varying simulations, " +
							"the fault will be removed if the current through the fault drops below the MINAMPS criteria.";
		PropertyHelp[8] = "Minimum amps that can sustain a temporary fault. Default is 5.";


		ActiveProperty = Fault.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new FaultObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void doGmatrix() {

		FaultObj af = getActiveFaultObj();

		double[] MatBuffer = new double[af.getNPhases() * af.getNPhases()];
		int OrderFound = Parser.getInstance().parseAsSymMatrix(af.getNPhases(), MatBuffer);

		if (OrderFound > 0) {  // Parse was successful  TODO Check zero based indexing
			/* X */
			af.setGmatrix( (double[]) Utilities.resizeArray(af.getGmatrix(), af.getNPhases() * af.getNPhases()) );
			for (int j = 0; j < af.getNPhases() * af.getNPhases(); j++)
				af.getGmatrix()[j] = MatBuffer[j];
		}

		MatBuffer = null;
	}

	private void fltSetBus1(String S) {
		String S2;

		// Special handling for Bus 1
		// Set Bus2 = Bus1.0.0.0

		FaultObj af = getActiveFaultObj();

		af.setBus(1, S);  // TODO Check zero based indexing

		// Default Bus2 to zero node of Bus1. (Wye Grounded connection)

		// Strip node designations from S
		int dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos - 1);  // copy up to Dot   TODO Check zero based indexing
		} else {
			S2 = S.substring(S.length());
		}

		S2 = S2 + ".0.0.0";  // Set Default for up to 3 phases

		af.setBus(2, S2);  // TODO Check zero based indexing
		af.setShunt(true);
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int Result = 0;
		// continue parsing with contents of Parser
		setActiveFaultObj((FaultObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveFaultObj());  // use property to set this value

		FaultObj af = getActiveFaultObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer <= NumProperties))
				af.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ af.getName() + "\"", 350);
				break;
			case 0:
				fltSetBus1(Param);
				break;
			case 1:
				af.setBus(2, Param);  // TODO Check zero based indexing
				break;
			case 2:
				//NumPhases = parser.makeInteger();  // see below
				break;
			case 3:
				af.setG(parser.makeDouble());
				if (af.getG() != 0.0) {
					af.setG(1.0 / af.getG());
				} else {
					af.setG(10000.0);  // Default to a low resistance
				}
				break;
			case 4:
				af.setStddev(parser.makeDouble() * 0.01);
				break;
			case 5:
				doGmatrix();
				break;
			case 6:
				af.setOn_Time(parser.makeDouble());
				break;
			case 7:
				af.setIsTemporary(Utilities.interpretYesNo(Param));
				break;
			case 8:
				af.setMinAmps(parser.makeDouble());
				break;
			default:
				// Inherited
				classEdit(getActiveFaultObj(), ParamPointer - Fault.NumPropsThisClass);
				break;
			}

			// Some specials ...
			switch (ParamPointer) {
			case 0:
				af.setPropertyValue(1, af.getBus(2));  // Bus2 gets modified if bus1 is   TODO Check zero based indexing
				break;
			case 1:
				if (!Utilities.stripExtension(af.getBus(1)).equalsIgnoreCase( Utilities.stripExtension(af.getBus(2)) ))
					af.setShunt(false);
				break;
			case 2:
				if (af.getNPhases() != parser.makeInteger()) {
					af.setNPhases(parser.makeInteger());
					af.setNConds(af.getNPhases());  // Force Reallocation of terminal info
					Globals.getActiveCircuit().setBusNameRedefined(true);  // Set Global Flag to signal circuit to rebuild busdefs
				}
				break;
			case 3:
				af.setSpecType(1);
				break;
			case 5:
				af.setSpecType(2);
				break;
			case 6:
				if (af.getOn_Time() > 0.0)
					af.setIs_ON(false);  // Assume fault will be on later
				break;
			}

			// YPrim invalidation on anything that changes impedance values
			switch (ParamPointer) {
			case 3:
				af.setYprimInvalid(true);
				break;
			case 4:
				af.setYprimInvalid(true);
				break;
			case 6:
				af.setYprimInvalid(true);
				break;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		af.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String FaultName) {

		int Result = 0;
		/* See if we can find this Fault name in the present collection */
		FaultObj OtherFault = (FaultObj) find(FaultName);
		if (OtherFault != null) {
			FaultObj af = getActiveFaultObj();

			if (af.getNPhases() != OtherFault.getNPhases()) {
				af.setNPhases(OtherFault.getNPhases());
				af.setNConds(af.getNPhases()); // force reallocation of terminals and conductors

				af.setYorder(af.getNConds() * af.getNTerms());
				af.setYprimInvalid(true);
			}

			af.setBaseFrequency(OtherFault.getBaseFrequency());
			af.setG(OtherFault.getG());
			af.setSpecType(OtherFault.getSpecType());

			af.setMinAmps(OtherFault.getMinAmps());
			af.setIsTemporary(OtherFault.isIsTemporary());
			af.setCleared(OtherFault.isCleared());
			af.setIs_ON(OtherFault.isIs_ON());
			af.setOn_Time(OtherFault.getOn_Time());


			if (OtherFault.getGmatrix() == null) {
				af.setGmatrix(null);
			} else {
				af.setGmatrix( (double[]) Utilities.resizeArray(af.getGmatrix(), af.getNPhases() * af.getNPhases()) );
				for (int i = 0; i < af.getNPhases() * af.getNPhases(); i++)
					af.getGmatrix()[i] = OtherFault.getGmatrix()[i];
			}

			classMakeLike(OtherFault);

			for (int i = 0; i < af.getParentClass().getNumProperties(); i++)
				af.setPropertyValue(i, OtherFault.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Fault.makeLike(): \"" + FaultName + "\" Not Found.", 351);
		}
		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Fault.init()", -1);
		return 0;
	}

	public static FaultObj getActiveFaultObj() {
		return ActiveFaultObj;
	}

	public static void setActiveFaultObj(FaultObj activeFaultObj) {
		ActiveFaultObj = activeFaultObj;
	}

}
