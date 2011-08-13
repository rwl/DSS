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
		this.className = "Reactor";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.REACTOR_ELEMENT;

		this.activeElement = -1;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = Reactor.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "bus1";
		propertyName[1] = "bus2";
		propertyName[2] = "phases";
		propertyName[3] = "kvar";
		propertyName[4] = "kv";
		propertyName[5] = "conn";
		propertyName[6] = "Rmatrix";
		propertyName[7] = "Xmatrix";
		propertyName[8] = "Parallel";
		propertyName[9] = "R";
		propertyName[10] = "X";
		propertyName[11] = "Rp";

		// define property help values
		propertyHelp[0] = "Name of first bus. Examples:"+DSSGlobals.CRLF+
							"bus1=busname"+DSSGlobals.CRLF+
							"bus1=busname.1.2.3";
		propertyHelp[1] = "Name of 2nd bus. Defaults to all phases connected "+
							"to first bus, node 0. (Shunt Wye Connection)"+DSSGlobals.CRLF+
							"Not necessary to specify for delta (LL) connection";
		propertyHelp[2] = "Number of phases.";
		propertyHelp[3] = "Total kvar, all phases.  Evenly divided among phases. Only determines X. Specify R separately";
		propertyHelp[4] = "For 2, 3-phase, kV phase-phase. Otherwise specify actual coil rating.";
		propertyHelp[5] = "={wye | delta |LN |LL}  Default is wye, which is equivalent to LN. If Delta, then only one terminal.";
		propertyHelp[6] = "Resistance matrix, lower triangle, ohms at base frequency. Order of the matrix is the number of phases. "+
							"Mutually exclusive to specifying parameters by kvar or X.";
		propertyHelp[7] = "Reactance matrix, lower triangle, ohms at base frequency. Order of the matrix is the number of phases. " +
							"Mutually exclusive to specifying parameters by kvar or X.";
		propertyHelp[8] = "{Yes | No}  Default=No. Indicates whether Rmatrix and Xmatrix are to be considered in parallel. " +
							"Default is series. For other models, specify R and Rp.";
		propertyHelp[9] = "Resistance (in series with reactance), each phase, ohms.";
		propertyHelp[10] = "Reactance, each phase, ohms at base frequency.";
		propertyHelp[11] = "Resistance in parallel with R and X (the entire branch). Assumed infinite if not specified.";

		activeProperty = Reactor.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
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
	 * Accepts (case insensitive):
	 * 		delta or LL
	 * 		Y, wye, or LN
	 */
	private void interpretConnection(String S) {
		ReactorObj ar = getActiveReactorObj();

		String TestS = S.toLowerCase();
		switch (TestS.charAt(0)) {
		case 'y':
			ar.setConnection(0);  /* Wye */
			break;
		case 'w':
			ar.setConnection(0);  /* Wye */
			break;
		case 'd':
			ar.setConnection(1);  /* Delta or Line-Line */
			break;
		case 'l':
			switch (TestS.charAt(1)) {
			case 'n':
				ar.setConnection(0);
				break;
			case 'l':
				ar.setConnection(1);
				break;
			}
			break;
		}

		switch (ar.getConnection()) {
		case 1:
			ar.setNTerms(1);  // force reallocation of terminals
			break;
		case 0:
			if (ar.getNTerms() != 2)
				ar.setNTerms(2);
			break;
		}
	}

	private void reactorSetBus1(String S) {
		String S2;
		int i, dotpos;

		// special handling for bus 1
		// set bus2 = bus1.0.0.0

		ReactorObj ar = getActiveReactorObj();

		ar.setBus(1, S);

		// default bus2 to zero node of bus1. (wye grounded connection)

		// strip node designations from s
		dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos);  // copy up to dot
		} else {
			S2 = S.substring(0, S.length());
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
		// continue parsing with contents of parser
		setActiveReactorObj((ReactorObj) elementList.getActive());
		DSSGlobals.getInstance().getActiveCircuit().setActiveCktElement(getActiveReactorObj());

		ReactorObj ar = getActiveReactorObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();

		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < numProperties))
				ar.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +"."+ ar.getName() + "\"", 230);
				break;
			case 0:
				reactorSetBus1(Param);
				break;
			case 1:
				ar.setBus(2, Param);  // TODO Check zero based indexing
				break;
			case 2:
				/*nPhases = parser.makeInteger();*/  // see below
				break;
			case 3:
				ar.setKVArRating(parser.makeDouble());
				break;
			case 4:
				ar.setKVRating(parser.makeDouble());
				break;
			case 5:
				interpretConnection(Param);
				break;
			case 6:
				doMatrix(ar.getRMatrix());
				break;
			case 7:
				doMatrix(ar.getXMatrix());
				break;
			case 8:
				ar.setParallel(Utilities.interpretYesNo(Param));
				break;
			case 9:
				ar.setR(parser.makeDouble());
				break;
			case 10:
				ar.setX(parser.makeDouble());
				break;
			case 11:
				ar.setRp(parser.makeDouble());
				break;
			default:
				// inherited property edits
				classEdit(ActiveReactorObj, ParamPointer - Reactor.NumPropsThisClass);
				break;
			}

			// some specials ...
			switch (ParamPointer) {
			case 0:
				ar.setPropertyValue(1, ar.getBus(2));  // this gets modified   TODO Check zero based indexing
				ar.getPrpSequence()[1] = 0;            // reset this for save function
				break;
			case 1:
				if (!Utilities.stripExtension(ar.getBus(1)).equalsIgnoreCase( Utilities.stripExtension(ar.getBus(2)) ))
					ar.setShunt(false);
				break;
			case 2:
				if (ar.getNPhases() != parser.makeInteger()) {
					ar.setNPhases(parser.makeInteger());
					ar.setNConds(ar.getNPhases());  // force reallocation of terminal info
					ar.setYorder(ar.getNTerms() * ar.getNConds());
				}
				break;
			case 3:
				ar.setSpecType(1);  // x specified by kVAr, kV
				break;
			case 6:
				ar.setSpecType(3);
				break;
			case 7:
				ar.setSpecType(3);
				break;
			case 10:
				ar.setSpecType(2);  // x specified directly
				break;
			case 11:
				ar.setRpSpecified(true);
				break;
			}

			// YPrim invalidation on anything that changes impedance values
			if ((ParamPointer >= 2) && (ParamPointer <= 11))
				ar.setYPrimInvalid(true);

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		ar.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String ReactorName) {

		int i, Result = 0;

		/* See if we can find this reactor name in the present collection */
		ReactorObj OtherReactor = (ReactorObj) find(ReactorName);
		if (OtherReactor != null) {
			ReactorObj ar = getActiveReactorObj();

			if (ar.getNPhases() != OtherReactor.getNPhases()) {
				ar.setNPhases(OtherReactor.getNPhases());
				ar.setNConds(ar.getNPhases());  // force reallocation of terminals and conductors

				ar.setYorder(ar.getNConds() * ar.getNTerms());
				ar.setYPrimInvalid(true);
			}

			ar.setR(OtherReactor.getR());
			ar.setX(OtherReactor.getX());
			ar.setRp(OtherReactor.getRp());
			ar.setRpSpecified(OtherReactor.isRpSpecified());
			ar.setParallel(OtherReactor.isParallel());

			ar.setKVArRating(OtherReactor.getKVArRating());
			ar.setKVRating(OtherReactor.getKVRating());
			ar.setConnection(OtherReactor.getConnection());
			ar.setSpecType(OtherReactor.getSpecType());

			if (OtherReactor.getRMatrix() == null) {
				ar.setRMatrix(new double[0]);
			} else {
				ar.setRMatrix( (double[]) Utilities.resizeArray(ar.getRMatrix(), ar.getNPhases() * ar.getNPhases()) );
				for (i = 0; i < ar.getNPhases() * ar.getNPhases(); i++) {
					ar.getRMatrix()[i] = OtherReactor.getRMatrix()[i];
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

			classMakeLike(OtherReactor);  // take care of inherited class properties

			for (i = 0; i < ar.getParentClass().getNumProperties(); i++) {
				ar.setPropertyValue(i, OtherReactor.getPropertyValue(i));
			}
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Reactor makeLike: \"" + ReactorName + "\" not found.", 231);
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
