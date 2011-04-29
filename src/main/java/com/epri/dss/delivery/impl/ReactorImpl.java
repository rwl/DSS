package com.epri.dss.delivery.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Reactor;
import com.epri.dss.delivery.ReactorObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class ReactorImpl extends PDClassImpl implements Reactor {

	private static ReactorObj ActiveReactorObj;

	public ReactorImpl() {
		super();
		this.Class_Name = "Reactor";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.REACTOR_ELEMENT;

		this.ActiveElement = 0;

		defineProperties();

		String[] Commands = new String[0];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		NumProperties = Reactor.NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();

		// Define Property names
		PropertyName[0] = "bus1";
		PropertyName[1] = "bus2";
		PropertyName[2] = "phases";
		PropertyName[3] = "kvar";
		PropertyName[4] = "kv";
		PropertyName[5] = "conn";
		PropertyName[6] = "Rmatrix";
		PropertyName[7] = "Xmatrix";
		PropertyName[8] = "Parallel";
		PropertyName[9] = "R";
		PropertyName[10] = "X";
		PropertyName[11] = "Rp";

		// define Property help values

		PropertyHelp[0] = "Name of first bus. Examples:"+DSSGlobals.CRLF+
							"bus1=busname"+DSSGlobals.CRLF+
							"bus1=busname.1.2.3";
		PropertyHelp[1] = "Name of 2nd bus. Defaults to all phases connected "+
							"to first bus, node 0. (Shunt Wye Connection)"+DSSGlobals.CRLF+
							"Not necessary to specify for delta (LL) connection";
		PropertyHelp[2] = "Number of phases.";
		PropertyHelp[3] = "Total kvar, all phases.  Evenly divided among phases. Only determines X. Specify R separately";
		PropertyHelp[4] = "For 2, 3-phase, kV phase-phase. Otherwise specify actual coil rating.";
		PropertyHelp[5] = "={wye | delta |LN |LL}  Default is wye, which is equivalent to LN. If Delta, then only one terminal.";
		PropertyHelp[6] = "Resistance matrix, lower triangle, ohms at base frequency. Order of the matrix is the number of phases. "+
							"Mutually exclusive to specifying parameters by kvar or X.";
		PropertyHelp[7] = "Reactance matrix, lower triangle, ohms at base frequency. Order of the matrix is the number of phases. " +
							"Mutually exclusive to specifying parameters by kvar or X.";
		PropertyHelp[8] = "{Yes | No}  Default=No. Indicates whether Rmatrix and Xmatrix are to be considered in parallel. " +
							"Default is series. For other models, specify R and Rp.";
		PropertyHelp[9] = "Resistance (in series with reactance), each phase, ohms.";
		PropertyHelp[10] = "Reactance, each phase, ohms at base frequency.";
		PropertyHelp[11] = "Resistance in parallel with R and X (the entire branch). Assumed infinite if not specified.";

		ActiveProperty = Reactor.NumPropsThisClass;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new ReactorObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void doMatrix(double[] Matrix) {
		ReactorObj ar = getActiveReactorObj();

		double[] MatBuffer = new double[ar.getNPhases() * ar.getNPhases()];
		int OrderFound = Parser.getInstance().parseAsSymMatrix(ar.getNPhases(), MatBuffer);

		if (OrderFound > 0) {  // Parse was successful Else don't change Matrix
			/* X */
			Matrix = (double[]) Utilities.resizeArray(Matrix, ar.getNPhases() * ar.getNPhases());
			for (int j = 0; j < ar.getNPhases() * ar.getNPhases(); j++)
				Matrix[j] = MatBuffer[j];

			MatBuffer = null;
		}
	}

	/**
	 * Accepts:
	 * 		delta or LL           (Case insensitive)
	 * 		Y, wye, or LN
	 */
	private void interpretConnection(String S) {
		ReactorObj ar = getActiveReactorObj();

		String TestS = S.toLowerCase();
		switch (TestS.charAt(0)) {
		case 'y':
			ar.setConnection(0);  /* Wye */
		case 'w':
			ar.setConnection(0);  /* Wye */
		case 'd':
			ar.setConnection(1);  /* Delta or line-Line */
		case 'l':
			switch (TestS.charAt(1)) {
			case 'n':
				ar.setConnection(0);
			case 'l':
				ar.setConnection(1);
			}
		}

		switch (ar.getConnection()) {
		case 1:
			ar.setNTerms(1);  // Force reallocation of terminals
		case 0:
			if (ar.getNTerms() != 2)
				ar.setNTerms(2);
		}
	}

	private void reactorSetBus1(String S) {
		String S2;
		int i, dotpos;

		// Special handling for Bus 1
		// Set Bus2 = Bus1.0.0.0

		ReactorObj ar = getActiveReactorObj();

		ar.setBus(1, S);

		// Default Bus2 to zero node of Bus1. (Wye Grounded connection)

		// Strip node designations from S
		dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos);  // TODO Check zero based indexing
		} else {
			S2 = S.substring(0, S.length());  // copy up to Dot
		}

		for (i = 0; i < ar.getNPhases(); i++)
			S2 = S2 + ".0";

		ar.setBus(2, S2);
		ar.setShunt(true);
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int Result = 0;
		// continue parsing with contents of Parser
		setActiveReactorObj((ReactorObj) ElementList.getActive());
		DSSGlobals.getInstance().getActiveCircuit().setActiveCktElement(getActiveReactorObj());

		ReactorObj ar = getActiveReactorObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();

		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < NumProperties))
				ar.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ ar.getName() + "\"", 230);
			case 0:
				reactorSetBus1(Param);
			case 1:
				ar.setBus(2, Param);  // TODO Check zero based indexing
			case 2:
				/*Numphases = parser.makeInteger();*/  // see below
			case 3:
				ar.setKvarrating(parser.makeDouble());
			case 4:
				ar.setKvrating(parser.makeDouble());
			case 5:
				interpretConnection(Param);
			case 6:
				doMatrix(ar.getRmatrix());
			case 7:
				doMatrix(ar.getXMatrix());
			case 8:
				ar.setIsParallel(Utilities.interpretYesNo(Param));
			case 9:
				ar.setR(parser.makeDouble());
			case 10:
				ar.setX(parser.makeDouble());
			case 11:
				ar.setRp(parser.makeDouble());
			default:
				// Inherited Property Edits
				classEdit(ActiveReactorObj, ParamPointer - Reactor.NumPropsThisClass);
			}

			// Some specials ...
			switch (ParamPointer) {
			case 0:
				ar.setPropertyValue(1, ar.getBus(2));  // this gets modified   TODO Check zero based indexing
				ar.getPrpSequence()[1] = 0;            // Reset this for save function
			case 1:
				if (Utilities.stripExtension(ar.getBus(1)).equals( Utilities.stripExtension(ar.getBus(2)) ))
					ar.setShunt(false);
			case 2:
				if (ar.getNPhases() != parser.makeInteger()) {
					ar.setNPhases(parser.makeInteger());
					ar.setNConds(ar.getNPhases());  // Force Reallocation of terminal info
					ar.setYorder(ar.getNTerms() * ar.getNConds());
				}
			case 3:
				ar.setSpecType(1);   // X specified by kvar, kV
			case 6:
				ar.setSpecType(3);
			case 7:
				ar.setSpecType(3);
			case 10:
				ar.setSpecType(2);   // X specified directly
			case 11:
				ar.setRpSpecified(true);
			}

			// YPrim invalidation on anything that changes impedance values
			if ((ParamPointer >= 2) && (ParamPointer <= 11)) {
				ar.setYprimInvalid(true);
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		ar.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String ReactorName) {

		int i, Result = 0;

		/* See if we can find this Reactor name in the present collection */
		ReactorObj OtherReactor = (ReactorObj) find(ReactorName);
		if (OtherReactor != null) {
			ReactorObj ar = getActiveReactorObj();

			if (ar.getNPhases() != OtherReactor.getNPhases()) {
				ar.setNPhases(OtherReactor.getNPhases());
				ar.setNConds(ar.getNPhases()); // force reallocation of terminals and conductors

				ar.setYorder(ar.getNConds() * ar.getNTerms());
				ar.setYprimInvalid(true);
			}

			ar.setR(OtherReactor.getR());
			ar.setX(OtherReactor.getX());
			ar.setRp(OtherReactor.getRp());
			ar.setRpSpecified(OtherReactor.isRpSpecified());
			ar.setIsParallel(OtherReactor.isIsParallel());

			ar.setKvarrating(OtherReactor.getKvarrating());
			ar.setKvrating(OtherReactor.getKvrating());
			ar.setConnection(OtherReactor.getConnection());
			ar.setSpecType(OtherReactor.getSpecType());

			if (OtherReactor.getRmatrix() == null) {
				ar.setRmatrix(new double[0]);
			} else {
				ar.setRmatrix( (double[]) Utilities.resizeArray(ar.getRmatrix(), ar.getNPhases() * ar.getNPhases()) );
				for (i = 0; i < ar.getNPhases() * ar.getNPhases(); i++) {
					ar.getRmatrix()[i] = OtherReactor.getRmatrix()[i];
				}
			}

			if (OtherReactor.getXMatrix() == null) {
				ar.setXMatrix(new double[0]);
			} else {
				ar.setXMatrix( (double[]) Utilities.resizeArray(ar.getXMatrix(), ar.getNPhases() * ar.getNPhases()) );
				for (i = 0; i < ar.getNPhases() * ar.getNPhases(); i++) {
					ar.getXMatrix()[i] = OtherReactor.getXMatrix()[i];
				}
			}

			classMakeLike(OtherReactor);  // Take care of inherited class properties

			for (i = 0; i < ar.getParentClass().getNumProperties(); i++) {
				ar.setPropertyValue(i, OtherReactor.getPropertyValue(i));
			}
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Reactor MakeLike: \"" + ReactorName + "\" Not Found.", 231);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Reactor.init()", -1);
		return 0;
	}

	public static ReactorObj getActiveReactorObj() {
		return ActiveReactorObj;
	}

	public static void setActiveReactorObj(ReactorObj activeReactorObj) {
		ActiveReactorObj = activeReactorObj;
	}

}
