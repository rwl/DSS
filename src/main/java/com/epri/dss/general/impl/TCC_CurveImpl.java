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
		this.Class_Name = "TCC_Curve";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;

		this.ActiveElement = 0;

		defineProperties();

		String[] Commands = new String[0];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;

		NumProperties = TCC_Curve.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// Define Property names
		PropertyName[0] = "npts";     // Number of points to expect
		PropertyName[1] = "C_array";     // vector of multiplier values
		PropertyName[2] = "T_array";     // vextor of time values , Sec

		// define property help values

		PropertyHelp[0] = "Number of points to expect in time-current arrays.";     // Number of points to expect
		PropertyHelp[1] = "Array of current (or voltage) values corresponding to time values (see help on T_Array).";  // vector of multiplier values
		PropertyHelp[2] = "Array of time values in sec. Typical array syntax: " +CRLF+
					"t_array = (1, 2, 3, 4, ...)" +CRLF+CRLF+
					"Can also substitute a file designation: " +CRLF+
					"t_array =  (file=filename)"+CRLF+CRLF+
					"The specified file has one value per line.";

		ActiveProperty = TCC_Curve.NumPropsThisClass;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
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
		// continue parsing with contents of Parser
		setActiveTCC_CurveObj((TCC_CurveObj) ElementList.getActive());
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
				ParamPointer = CommandList.getCommand(ParamName);
			}  // FIXME Check indenting of other edit methods at this point.

			if ((ParamPointer > 0) && (ParamPointer <= NumProperties))
				atc.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case 0:
				DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ atc.getName() + "\"", 420);
			case 1:
				atc.setNpts(parser.makeInteger());
			case 2:
				Utilities.interpretDblArray(Param, atc.getNpts(), atc.getC_values());   // Parser.ParseAsVector(Npts, Multipliers);
			case 3:
				Utilities.interpretDblArray(Param, atc.getNpts(), atc.getT_values());   // Parser.ParseAsVector(Npts, Hours);
			default:
				// Inherited parameters
				classEdit(getActiveTCC_CurveObj(), ParamPointer - TCC_Curve.NumPropsThisClass);
			}

			switch (ParamPointer) {
			case 0:  // Reallocate arrays to correspond to Npts
				atc.setC_values( (double[]) Utilities.resizeArray(atc.getC_values(), atc.getNpts()) );
				atc.setLogC( (double[]) Utilities.resizeArray(atc.getLogC(), atc.getNpts()) );
				atc.setT_values( (double[]) Utilities.resizeArray(atc.getT_values(), atc.getNpts()) );
				atc.setLogT( (double[]) Utilities.resizeArray(atc.getLogT(), atc.getNpts()) );
			case 1:
				calcLogPoints(atc.getC_values(), atc.getLogC(), atc.getNpts());
			case 2:
				calcLogPoints(atc.getT_values(), atc.getLogT(), atc.getNpts());
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
			atc.setNpts(OtherTCC_Curve.getNpts());
			atc.setC_values( (double[]) Utilities.resizeArray(atc.getC_values(), atc.getNpts()) );
			atc.setLogC( (double[]) Utilities.resizeArray(atc.getLogC(), atc.getNpts()) );
			atc.setT_values( (double[]) Utilities.resizeArray(atc.getT_values(), atc.getNpts()) );
			atc.setLogT( (double[]) Utilities.resizeArray(atc.getLogT(), atc.getNpts()) );
			for (i = 0; i < atc.getNpts(); i++)
				atc.getC_values()[i] = OtherTCC_Curve.getC_values()[i];
			for (i = 0; i < atc.getNpts(); i++)
				atc.getT_values()[i] = OtherTCC_Curve.getT_values()[i];
			for (i = 0; i < atc.getNpts(); i++)
				atc.getLogC()[i] = OtherTCC_Curve.getLogC()[i];
			for (i = 0; i < atc.getNpts(); i++)
				atc.getLogT()[i] = OtherTCC_Curve.getLogT()[i];

			for (i = 0; i < atc.getParentClass().getNumProperties(); i++)
				atc.setPropertyValue(i, OtherTCC_Curve.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in TCC_Curve.makeLike(): \"" + Name + "\" Not Found.", 421);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement TCC_Curve.init()", -1);
		return 0;
	}

	public String getCode() {
		TCC_CurveObj pCurve = (TCC_CurveObj) ElementList.getActive();
		return pCurve.getName();
	}

	public void setCode(String Value) {
		setActiveTCC_CurveObj(null);
		TCC_CurveObj pCurve;
		for (int i = 0; i < ElementList.size(); i++) {
			pCurve = (TCC_CurveObj) ElementList.get(i);
			if (pCurve.getName().equals(Value)) {
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
