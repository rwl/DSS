/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.delivery;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Connection;
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
		ReactorObj elem = activeReactorObj;

		double[] matBuffer = new double[elem.getNumPhases() * elem.getNumPhases()];
		int orderFound = Parser.getInstance().parseAsSymMatrix(elem.getNumPhases(), matBuffer);

		if (orderFound > 0) {  // Parse was successful Else don't change Matrix
			matrix = Util.resizeArray(matrix, elem.getNumPhases() * elem.getNumPhases());
			for (int j = 0; j < elem.getNumPhases() * elem.getNumPhases(); j++)
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
		ReactorObj elem = activeReactorObj;

		String ss = s.toLowerCase();
		switch (ss.charAt(0)) {
		case 'y':
			elem.setConnection(Connection.WYE);
			break;
		case 'w':
			elem.setConnection(Connection.WYE);
			break;
		case 'd':
			elem.setConnection(Connection.DELTA);
			break;
		case 'l':
			switch (ss.charAt(1)) {
			case 'n':
				elem.setConnection(Connection.WYE);
				break;
			case 'l':
				elem.setConnection(Connection.DELTA);
				break;
			}
			break;
		}

		switch (elem.getConnection()) {
		case DELTA:
			elem.setNumTerms(1);  // force reallocation of terminals
			break;
		case WYE:
			if (elem.getNumTerms() != 2)
				elem.setNumTerms(2);
			break;
		}
	}

	private void reactorSetBus1(String s) {
		String s2;
		int i, dotpos;

		// special handling for bus 1
		// set bus2 = bus1.0.0.0

		ReactorObj elem = activeReactorObj;

		elem.setBus(0, s);

		// default bus2 to zero node of bus1. (wye grounded connection)

		// strip node designations from s
		dotpos = s.indexOf('.');
		if (dotpos >= 0) {
			s2 = s.substring(0, dotpos);  // copy up to dot
		} else {
			s2 = s.substring(0, s.length());
		}

		for (i = 0; i < elem.getNumPhases(); i++)
			s2 = s2 + ".0";

		elem.setBus(1, s2);
		elem.setShunt(true);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeReactorObj = (ReactorObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeReactorObj);

		ReactorObj elem = activeReactorObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.stringValue();

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
						getClassName() +"."+ elem.getName() + "\"", 230);
				break;
			case 0:
				reactorSetBus1(param);
				break;
			case 1:
				elem.setBus(1, param);
				break;
			case 2:
				/*elem.setNPhases(parser.makeInteger());*/  // see below
				break;
			case 3:
				elem.setKVArRating(parser.doubleValue());
				break;
			case 4:
				elem.setKVRating(parser.doubleValue());
				break;
			case 5:
				interpretConnection(param);
				break;
			case 6:
				doMatrix(elem.getRmatrix());
				break;
			case 7:
				doMatrix(elem.getXmatrix());
				break;
			case 8:
				elem.setParallel(Util.interpretYesNo(param));
				break;
			case 9:
				elem.setR(parser.doubleValue());
				break;
			case 10:
				elem.setX(parser.doubleValue());
				break;
			case 11:
				elem.setRp(parser.doubleValue());
				break;
			default:
				// inherited property edits
				classEdit(activeReactorObj, paramPointer - Reactor.NumPropsThisClass);
				break;
			}

			// some specials ...
			switch (paramPointer) {
			case 0:
				elem.setPropertyValue(1, elem.getBus(1));  // this gets modified
				elem.getPrpSequence()[1] = 0;            // reset this for save function
				break;
			case 1:
				if (!Util.stripExtension(elem.getBus(0)).equalsIgnoreCase(Util.stripExtension(elem.getBus(1))))
					elem.setShunt(false);
				break;
			case 2:
				if (elem.getNumPhases() != parser.integerValue()) {
					elem.setNumPhases(parser.integerValue());
					elem.setNumConds(elem.getNumPhases());  // force reallocation of terminal info
					elem.setYOrder(elem.getNumTerms() * elem.getNumConds());
				}
				break;
			case 3:
				elem.setSpecType(ReactorSpecType.KVAR);  // x specified by kVAr, kV
				break;
			case 6:
			case 7:
				elem.setSpecType(ReactorSpecType.ZMATRIX);
				break;
			case 10:
				elem.setSpecType(ReactorSpecType.Z);  // x specified directly
				break;
			case 11:
				elem.setRpSpecified(true);
				break;
			}

			// YPrim invalidation on anything that changes impedance values
			if (paramPointer >= 2 && paramPointer <= 11)
				elem.setYPrimInvalid(true);

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		elem.recalcElementData();

		return 0;
	}

	@Override
	protected int makeLike(String reactorName) {
		int i, success = 0;

		/* See if we can find this reactor name in the present collection */
		ReactorObj other = (ReactorObj) find(reactorName);

		if (other != null) {
			ReactorObj elem = activeReactorObj;

			if (elem.getNumPhases() != other.getNumPhases()) {
				elem.setNumPhases(other.getNumPhases());
				elem.setNumConds(elem.getNumPhases());  // force reallocation of terminals and conductors

				elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
				elem.setYPrimInvalid(true);
			}

			elem.setR(other.getR());
			elem.setX(other.getX());
			elem.setRp(other.getRp());
			elem.setRpSpecified(other.isRpSpecified());
			elem.setParallel(other.isParallel());

			elem.setKVArRating(other.getKVArRating());
			elem.setKVRating(other.getKVRating());
			elem.setConnection(other.getConnection());
			elem.setSpecType(other.getSpecType());

			if (other.getRmatrix() == null) {
				elem.setRmatrix(new double[0]);
			} else {
				elem.setRmatrix((double[]) Util.resizeArray(elem.getRmatrix(), elem.getNumPhases() * elem.getNumPhases()));
				for (i = 0; i < elem.getNumPhases() * elem.getNumPhases(); i++)
					elem.getRmatrix()[i] = other.getRmatrix()[i];
			}

			if (other.getXmatrix() == null) {
				elem.setXmatrix(new double[0]);
			} else {
				elem.setXmatrix((double[]) Util.resizeArray(elem.getXmatrix(), elem.getNumPhases() * elem.getNumPhases()));
				for (i = 0; i < elem.getNumPhases() * elem.getNumPhases(); i++)
					elem.getXmatrix()[i] = other.getXmatrix()[i];
			}

			classMakeLike(other);  // take care of inherited class properties

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++) {
				elem.setPropertyValue(i, other.getPropertyValue(i));
			}
			success = 1;
		} else {
			DSS.doSimpleMsg("Error in Reactor makeLike: \"" + reactorName + "\" not found.", 231);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Reactor.init()", -1);
		return 0;
	}

}
