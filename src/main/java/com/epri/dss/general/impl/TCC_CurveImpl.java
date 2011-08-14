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

	private static TCC_CurveObj ActiveTCC_CurveObj;

	public TCC_CurveImpl() {
		super();
		this.className = "TCC_Curve";
		this.classType = DSSClassDefs.DSS_OBJECT;

		this.activeElement = -1;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;

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
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.setActiveDSSObject(new TCC_CurveObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void calcLogPoints(double[] X, double[] LogX, int N) {
		for (int i = 0; i < N; i++)
			if (X[i] > 0.0) {
				LogX[i] = Math.log(X[i]);
			} else {
				LogX[i] = Math.log(0.001);
			}
	}

	@Override
	public int edit() {
		int Result = 0;
		// continue parsing with contents of parser
		setActiveTCC_CurveObj((TCC_CurveObj) elementList.getActive());
		DSSGlobals.getInstance().setActiveDSSObject(getActiveTCC_CurveObj());

		Parser parser = Parser.getInstance();

		TCC_CurveObj atc = getActiveTCC_CurveObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}  // FIXME Check indenting of other edit methods at this point.

			if ((ParamPointer > 0) && (ParamPointer <= numProperties))
				atc.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case 0:
				DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +"."+ atc.getName() + "\"", 420);
				break;
			case 1:
				atc.setNPts(parser.makeInteger());
				break;
			case 2:
				Utilities.interpretDblArray(Param, atc.getNPts(), atc.getCValues());   // Parser.ParseAsVector(Npts, Multipliers);
				break;
			case 3:
				Utilities.interpretDblArray(Param, atc.getNPts(), atc.getTValues());   // Parser.ParseAsVector(Npts, Hours);
				break;
			default:
				// inherited parameters
				classEdit(getActiveTCC_CurveObj(), ParamPointer - TCC_Curve.NumPropsThisClass);
				break;
			}

			switch (ParamPointer) {
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

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		return Result;
	}

	@Override
	protected int makeLike(String Name) {
		int i, Result = 0;
		/* See if we can find this line code in the present collection */
		TCC_CurveObj OtherTCC_Curve = (TCC_CurveObj) find(Name);
		if (OtherTCC_Curve != null) {
			TCC_CurveObj atc = getActiveTCC_CurveObj();
			atc.setNPts(OtherTCC_Curve.getNPts());
			atc.setCValues( (double[]) Utilities.resizeArray(atc.getCValues(), atc.getNPts()) );
			atc.setLogC( (double[]) Utilities.resizeArray(atc.getLogC(), atc.getNPts()) );
			atc.setTValues( (double[]) Utilities.resizeArray(atc.getTValues(), atc.getNPts()) );
			atc.setLogT( (double[]) Utilities.resizeArray(atc.getLogT(), atc.getNPts()) );
			for (i = 0; i < atc.getNPts(); i++)
				atc.getCValues()[i] = OtherTCC_Curve.getCValues()[i];
			for (i = 0; i < atc.getNPts(); i++)
				atc.getTValues()[i] = OtherTCC_Curve.getTValues()[i];
			for (i = 0; i < atc.getNPts(); i++)
				atc.getLogC()[i] = OtherTCC_Curve.getLogC()[i];
			for (i = 0; i < atc.getNPts(); i++)
				atc.getLogT()[i] = OtherTCC_Curve.getLogT()[i];

			for (i = 0; i < atc.getParentClass().getNumProperties(); i++)
				atc.setPropertyValue(i, OtherTCC_Curve.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in TCC_Curve.makeLike(): \"" + Name + "\" not found.", 421);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement TCC_Curve.init()", -1);
		return 0;
	}

	public String getCode() {
		TCC_CurveObj pCurve = (TCC_CurveObj) elementList.getActive();
		return pCurve.getName();
	}

	public void setCode(String Value) {
		setActiveTCC_CurveObj(null);
		TCC_CurveObj pCurve;
		for (int i = 0; i < elementList.size(); i++) {
			pCurve = (TCC_CurveObj) elementList.get(i);
			if (pCurve.getName().equalsIgnoreCase(Value)) {
				setActiveTCC_CurveObj(pCurve);
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("TCC_Curve: \"" + Value + "\" not found.", 422);
	}

	public static TCC_CurveObj getActiveTCC_CurveObj() {
		return ActiveTCC_CurveObj;
	}

	public static void setActiveTCC_CurveObj(TCC_CurveObj activeTCC_CurveObj) {
		ActiveTCC_CurveObj = activeTCC_CurveObj;
	}

}
