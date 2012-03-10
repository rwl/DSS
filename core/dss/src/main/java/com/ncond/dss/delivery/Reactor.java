package com.ncond.dss.delivery;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Reactor extends PDClass {

	public static final int NumPropsThisClass = 12;

	public static ReactorObj activeReactorObj;

	public Reactor() {
		super();
		className = "Reactor";
		classType = classType + DSSClassDefs.REACTOR_ELEMENT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
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
		propertyHelp[0] = "Name of first bus. Examples:"+DSS.CRLF+
							"bus1=busname"+DSS.CRLF+
							"bus1=busname.1.2.3";
		propertyHelp[1] = "Name of 2nd bus. Defaults to all phases connected "+
							"to first bus, node 0. (Shunt Wye Connection)"+DSS.CRLF+
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
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new ReactorObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void doMatrix(double[] matrix) {
		ReactorObj ar = activeReactorObj;

		double[] matBuffer = new double[ar.getNumPhases() * ar.getNumPhases()];
		int orderFound = Parser.getInstance().parseAsSymMatrix(ar.getNumPhases(), matBuffer);

		if (orderFound > 0) {  // Parse was successful Else don't change Matrix
			/* X */
			matrix = Util.resizeArray(matrix, ar.getNumPhases() * ar.getNumPhases());
			for (int j = 0; j < ar.getNumPhases() * ar.getNumPhases(); j++)
				matrix[j] = matBuffer[j];

			matBuffer = null;
		}
	}

	/**
	 * Accepts (case insensitive):
	 * 		delta or LL
	 * 		Y, wye, or LN
	 */
	private void interpretConnection(String s) {
		ReactorObj ar = activeReactorObj;

		String testS = s.toLowerCase();
		switch (testS.charAt(0)) {
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
			switch (testS.charAt(1)) {
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
			ar.setNumTerms(1);  // force reallocation of terminals
			break;
		case 0:
			if (ar.getNumTerms() != 2)
				ar.setNumTerms(2);
			break;
		}
	}

	private void reactorSetBus1(String s) {
		String s2;
		int i, dotpos;

		// special handling for bus 1
		// set bus2 = bus1.0.0.0

		ReactorObj ar = activeReactorObj;

		ar.setBus(0, s);

		// default bus2 to zero node of bus1. (wye grounded connection)

		// strip node designations from s
		dotpos = s.indexOf('.');
		if (dotpos >= 0) {
			s2 = s.substring(0, dotpos);  // copy up to dot
		} else {
			s2 = s.substring(0, s.length());
		}

		for (i = 0; i < ar.getNumPhases(); i++)
			s2 = s2 + ".0";

		ar.setBus(1, s2);
		ar.setShunt(true);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of parser
		activeReactorObj = (ReactorObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeReactorObj);

		ReactorObj ar = activeReactorObj;

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
				ar.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getClassName() +"."+ ar.getName() + "\"", 230);
				break;
			case 0:
				reactorSetBus1(param);
				break;
			case 1:
				ar.setBus(1, param);
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
				interpretConnection(param);
				break;
			case 6:
				doMatrix(ar.getRMatrix());
				break;
			case 7:
				doMatrix(ar.getXMatrix());
				break;
			case 8:
				ar.setParallel(Util.interpretYesNo(param));
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
				classEdit(activeReactorObj, paramPointer - Reactor.NumPropsThisClass);
				break;
			}

			// some specials ...
			switch (paramPointer) {
			case 0:
				ar.setPropertyValue(1, ar.getBus(1));  // this gets modified
				ar.getPrpSequence()[1] = 0;            // reset this for save function
				break;
			case 1:
				if (!Util.stripExtension(ar.getBus(0)).equalsIgnoreCase( Util.stripExtension(ar.getBus(1)) ))
					ar.setShunt(false);
				break;
			case 2:
				if (ar.getNumPhases() != parser.makeInteger()) {
					ar.setNumPhases(parser.makeInteger());
					ar.setNumConds(ar.getNumPhases());  // force reallocation of terminal info
					ar.setYOrder(ar.getNumTerms() * ar.getNumConds());
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
			if (paramPointer >= 2 && paramPointer <= 11)
				ar.setYPrimInvalid(true);

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		ar.recalcElementData();

		return result;
	}

	@Override
	protected int makeLike(String reactorName) {

		int i, result = 0;

		/* See if we can find this reactor name in the present collection */
		ReactorObj otherReactor = (ReactorObj) find(reactorName);
		if (otherReactor != null) {
			ReactorObj ar = activeReactorObj;

			if (ar.getNumPhases() != otherReactor.getNumPhases()) {
				ar.setNumPhases(otherReactor.getNumPhases());
				ar.setNumConds(ar.getNumPhases());  // force reallocation of terminals and conductors

				ar.setYOrder(ar.getNumConds() * ar.getNumTerms());
				ar.setYPrimInvalid(true);
			}

			ar.setR(otherReactor.getR());
			ar.setX(otherReactor.getX());
			ar.setRp(otherReactor.getRp());
			ar.setRpSpecified(otherReactor.isRpSpecified());
			ar.setParallel(otherReactor.isParallel());

			ar.setKVArRating(otherReactor.getKVArRating());
			ar.setKVRating(otherReactor.getKVRating());
			ar.setConnection(otherReactor.getConnection());
			ar.setSpecType(otherReactor.getSpecType());

			if (otherReactor.getRMatrix() == null) {
				ar.setRMatrix(new double[0]);
			} else {
				ar.setRMatrix( (double[]) Util.resizeArray(ar.getRMatrix(), ar.getNumPhases() * ar.getNumPhases()) );
				for (i = 0; i < ar.getNumPhases() * ar.getNumPhases(); i++)
					ar.getRMatrix()[i] = otherReactor.getRMatrix()[i];
			}

			if (otherReactor.getXMatrix() == null) {
				ar.setXMatrix(new double[0]);
			} else {
				ar.setXMatrix( (double[]) Util.resizeArray(ar.getXMatrix(), ar.getNumPhases() * ar.getNumPhases()) );
				for (i = 0; i < ar.getNumPhases() * ar.getNumPhases(); i++)
					ar.getXMatrix()[i] = otherReactor.getXMatrix()[i];
			}

			classMakeLike(otherReactor);  // take care of inherited class properties

			for (i = 0; i < ar.getParentClass().getNumProperties(); i++) {
				ar.setPropertyValue(i, otherReactor.getPropertyValue(i));
			}
			result = 1;
		} else {
			DSS.doSimpleMsg("Error in Reactor makeLike: \"" + reactorName + "\" not found.", 231);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Reactor.init()", -1);
		return 0;
	}

}
