package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.TCC_Curve;
import com.epri.dss.general.TCC_CurveObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class TCC_CurveImpl extends DSSClassImpl implements TCC_Curve {

	public static TCC_CurveObj activeTCC_CurveObj;

	public TCC_CurveImpl() {
		super();
		className = "TCC_Curve";
		classType = DSSClassDefs.DSS_OBJECT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		final String CRLF = DSSGlobals.CRLF;

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
		DSSGlobals globals = DSSGlobals.getInstance();

		globals.setActiveDSSObject(new TCC_CurveObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	private void calcLogPoints(double[] X, double[] logX, int n) {
		for (int i = 0; i < n; i++)
			if (X[i] > 0.0) {
				logX[i] = Math.log(X[i]);
			} else {
				logX[i] = Math.log(0.001);
			}
	}

	@Override
	public int edit() {
		int result = 0;
		// continue parsing with contents of parser
		activeTCC_CurveObj = (TCC_CurveObj) elementList.getActive();
		DSSGlobals.getInstance().setActiveDSSObject(activeTCC_CurveObj);

		Parser parser = Parser.getInstance();

		TCC_CurveObj atc = activeTCC_CurveObj;

		int paramPointer = 0;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}  // FIXME Check indenting of other edit methods at this point.

			if ((paramPointer > 0) && (paramPointer <= numProperties))
				atc.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case 0:
				DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ atc.getName() + "\"", 420);
				break;
			case 1:
				atc.setNPts(parser.makeInteger());
				break;
			case 2:
				Utilities.interpretDblArray(param, atc.getNPts(), atc.getCValues());   // Parser.ParseAsVector(Npts, Multipliers);
				break;
			case 3:
				Utilities.interpretDblArray(param, atc.getNPts(), atc.getTValues());   // Parser.ParseAsVector(Npts, Hours);
				break;
			default:
				// inherited parameters
				classEdit(activeTCC_CurveObj, paramPointer - TCC_Curve.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 0:  // reallocate arrays to correspond to npts
				atc.setCValues( (double[]) Utilities.resizeArray(atc.getCValues(), atc.getNPts()) );
				atc.setLogC( (double[]) Utilities.resizeArray(atc.getLogC(), atc.getNPts()) );
				atc.setTValues( (double[]) Utilities.resizeArray(atc.getTValues(), atc.getNPts()) );
				atc.setLogT( (double[]) Utilities.resizeArray(atc.getLogT(), atc.getNPts()) );
				break;
			case 1:
				calcLogPoints(atc.getCValues(), atc.getLogC(), atc.getNPts());
				break;
			case 2:
				calcLogPoints(atc.getTValues(), atc.getLogT(), atc.getNPts());
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		return result;
	}

	@Override
	protected int makeLike(String name) {
		int i, result = 0;
		/* See if we can find this line code in the present collection */
		TCC_CurveObj otherTCC_Curve = (TCC_CurveObj) find(name);
		if (otherTCC_Curve != null) {
			TCC_CurveObj atc = activeTCC_CurveObj;
			atc.setNPts(otherTCC_Curve.getNPts());
			atc.setCValues( (double[]) Utilities.resizeArray(atc.getCValues(), atc.getNPts()) );
			atc.setLogC( (double[]) Utilities.resizeArray(atc.getLogC(), atc.getNPts()) );
			atc.setTValues( (double[]) Utilities.resizeArray(atc.getTValues(), atc.getNPts()) );
			atc.setLogT( (double[]) Utilities.resizeArray(atc.getLogT(), atc.getNPts()) );
			for (i = 0; i < atc.getNPts(); i++)
				atc.getCValues()[i] = otherTCC_Curve.getCValues()[i];
			for (i = 0; i < atc.getNPts(); i++)
				atc.getTValues()[i] = otherTCC_Curve.getTValues()[i];
			for (i = 0; i < atc.getNPts(); i++)
				atc.getLogC()[i] = otherTCC_Curve.getLogC()[i];
			for (i = 0; i < atc.getNPts(); i++)
				atc.getLogT()[i] = otherTCC_Curve.getLogT()[i];

			for (i = 0; i < atc.getParentClass().getNumProperties(); i++)
				atc.setPropertyValue(i, otherTCC_Curve.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in TCC_Curve.makeLike(): \"" + name + "\" not found.", 421);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement TCC_Curve.init()", -1);
		return 0;
	}

	public String getCode() {
		TCC_CurveObj curve = (TCC_CurveObj) elementList.getActive();
		return curve.getName();
	}

	public void setCode(String value) {
		activeTCC_CurveObj = null;
		TCC_CurveObj curve;
		for (int i = 0; i < elementList.size(); i++) {
			curve = (TCC_CurveObj) elementList.get(i);
			if (curve.getName().equalsIgnoreCase(value)) {
				activeTCC_CurveObj = curve;
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("TCC_Curve: \"" + value + "\" not found.", 422);
	}

}
