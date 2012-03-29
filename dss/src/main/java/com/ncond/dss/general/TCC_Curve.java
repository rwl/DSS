/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

import static com.ncond.dss.common.Util.interpretDblArray;
import static com.ncond.dss.common.Util.resizeArray;


public class TCC_Curve extends DSSClass {

	public static final int NumPropsThisClass = 3;

	public static TCC_CurveObj activeTCC_CurveObj;

	public TCC_Curve() {
		super();
		className = "TCC_Curve";
		classType = DSSClassDefs.DSS_OBJECT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		final String CRLF = DSS.CRLF;

		numProperties = TCC_Curve.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0] = "npts";     // number of points to expect
		propertyName[1] = "C_array";  // vector of multiplier values
		propertyName[2] = "T_array";  // vextor of time values , Sec

		// define property help values
		propertyHelp[0] = "Number of points to expect in time-current arrays.";  // number of points to expect
		propertyHelp[1] = "Array of current (or voltage) values corresponding to time values (see help on T_Array).";  // vector of multiplier values
		propertyHelp[2] = "Array of time values in sec. Typical array syntax: " +CRLF+
				"t_array = (1, 2, 3, 4, ...)" +CRLF+CRLF+
				"Can also substitute a file designation: " +CRLF+
				"t_array =  (file=filename)"+CRLF+CRLF+
				"The specified file has one value per line.";

		activeProperty = TCC_Curve.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeDSSObject = new TCC_CurveObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	private void calcLogPoints(double[] X, double[] logX, int n) {
		for (int i = 0; i < n; i++)
			logX[i] = X[i] > 0.0 ? Math.log(X[i]) : Math.log(0.001);
	}

	@Override
	public int edit() {
		// continue parsing with contents of parser
		activeTCC_CurveObj = (TCC_CurveObj) elementList.getActive();
		DSS.activeDSSObject = activeTCC_CurveObj;

		Parser parser = Parser.getInstance();

		TCC_CurveObj elem = activeTCC_CurveObj;

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
						getClassName() + "." + elem.getName() + "\"", 420);
				break;
			case 0:
				elem.setNpts(parser.integerValue());
				break;
			case 1:
				interpretDblArray(param, elem.getNpts(), elem.getCValues());
				break;
			case 2:
				interpretDblArray(param, elem.getNpts(), elem.getTValues());
				break;
			default:
				// inherited parameters
				classEdit(activeTCC_CurveObj, paramPointer - TCC_Curve.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 0:  // reallocate arrays to correspond to npts
				elem.setCValues(resizeArray(elem.getCValues(), elem.getNpts()));
				elem.setLogC(resizeArray(elem.getLogC(), elem.getNpts()));
				elem.setTValues(resizeArray(elem.getTValues(), elem.getNpts()));
				elem.setLogT(resizeArray(elem.getLogT(), elem.getNpts()));
				break;
			case 1:
			case 2:
				calcLogPoints(elem.getTValues(), elem.getLogT(), elem.getNpts());
				break;
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		return 0;
	}

	@Override
	protected int makeLike(String name) {
		int i, success = 0;

		/* See if we can find this line code in the present collection */
		TCC_CurveObj other = (TCC_CurveObj) find(name);

		if (other != null) {
			TCC_CurveObj elem = activeTCC_CurveObj;
			elem.setNpts(other.getNpts());
			elem.setCValues( resizeArray(elem.getCValues(), elem.getNpts()) );
			elem.setLogC( resizeArray(elem.getLogC(), elem.getNpts()) );
			elem.setTValues( resizeArray(elem.getTValues(), elem.getNpts()) );
			elem.setLogT( resizeArray(elem.getLogT(), elem.getNpts()) );
			for (i = 0; i < elem.getNpts(); i++)
				elem.getCValues()[i] = other.getCValues()[i];
			for (i = 0; i < elem.getNpts(); i++)
				elem.getTValues()[i] = other.getTValues()[i];
			for (i = 0; i < elem.getNpts(); i++)
				elem.getLogC()[i] = other.getLogC()[i];
			for (i = 0; i < elem.getNpts(); i++)
				elem.getLogT()[i] = other.getLogT()[i];

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in TCC_Curve.makeLike(): \"" +
					name + "\" not found", 421);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement TCC_Curve.init()", -1);
		return 0;
	}

	public String getCode() {
		return ((TCC_CurveObj) elementList.getActive()).getName();
	}

	public void setCode(String value) {
		TCC_CurveObj curve;

		activeTCC_CurveObj = null;

		for (int i = 0; i < elementList.size(); i++) {
			curve = (TCC_CurveObj) elementList.get(i);
			if (curve.getName().equalsIgnoreCase(value)) {
				activeTCC_CurveObj = curve;
				return;
			}
		}

		DSS.doSimpleMsg("TCC_Curve: \"" + value + "\" not found.", 422);
	}

}
