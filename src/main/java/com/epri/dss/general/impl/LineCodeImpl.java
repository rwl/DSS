package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.LineCode;
import com.epri.dss.general.LineCodeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.CommandListImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.LineUnits;

public class LineCodeImpl extends DSSClassImpl implements LineCode {

	private static LineCodeObj ActiveLineCodeObj;

	private boolean SymComponentsChanged;

	private boolean MatrixChanged;

	public LineCodeImpl() {
		super();
		this.Class_Name = "LineCode";
		this.DSSClassType= DSSClassDefs.DSS_OBJECT;
		this.ActiveElement = -1;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		NumProperties = LineCode.NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();

		PropertyName[0] = "nphases";
		PropertyName[1] = "r1";
		PropertyName[2] = "x1";
		PropertyName[3] = "r0";
		PropertyName[4] = "x0";
		PropertyName[5] = "c1";
		PropertyName[6] = "c0";
		PropertyName[7] = "units";
		PropertyName[8] = "rmatrix";
		PropertyName[9] = "xmatrix";
		PropertyName[10] = "cmatrix";
		PropertyName[11] = "baseFreq";
		PropertyName[12] = "normamps";
		PropertyName[13] = "emergamps";
		PropertyName[14] = "faultrate";
		PropertyName[15] = "pctperm";
		PropertyName[16] = "repair";
		PropertyName[17] = "Kron";
		PropertyName[18] = "Rg";
		PropertyName[19] = "Xg";
		PropertyName[20] = "rho";
		PropertyName[21] = "neutral";

		PropertyHelp[0] = "Number of phases in the line this line code data represents.  Setting this property reinitializes the line code.  Impedance matrix is reset for default symmetrical component.";
		PropertyHelp[1] = "Positive-sequence Resistance, ohms per unit length.  See also Rmatrix.";
		PropertyHelp[2] = "Positive-sequence Reactance, ohms per unit length.  See also Xmatrix";
		PropertyHelp[3] = "Zero-sequence Resistance, ohms per unit length.";
		PropertyHelp[4] = "Zero-sequence Reactance, ohms per unit length.";
		PropertyHelp[5] = "Positive-sequence capacitance, nf per unit length. See also Cmatrix.";
		PropertyHelp[6] = "Zero-sequence capacitance, nf per unit length.";
		PropertyHelp[7] = "One of (ohms per ...) {none|mi|km|kft|m|me|ft|in|cm}.  Default is none; assumes units agree with length units" +
					"given in Line object";
		PropertyHelp[8] = "Resistance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. "+
						"May be used to specify the impedance of any line configuration.  For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		PropertyHelp[9] = "Reactance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. "+
						"May be used to specify the impedance of any line configuration.  For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		PropertyHelp[10] = "Nodal Capacitance matrix, lower triangle, nf per unit length.Order of the matrix is the number of phases. "+
						"May be used to specify the shunt capacitance of any line configuration.  For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		PropertyHelp[11] = "Frequency at which impedances are specified.";
		PropertyHelp[12] = "Normal ampere limit on line.  This is the so-called Planning Limit. It may also be "+
						"the value above which load will have to be dropped in a contingency.  Usually about "+
						"75% - 80% of the emergency (one-hour) rating.";
		PropertyHelp[13] = "Emergency ampere limit on line (usually one-hour rating).";
		PropertyHelp[14] = "Number of faults per unit length per year.";
		PropertyHelp[15] = "Percentage of the faults that become permanent.";
		PropertyHelp[16] = "Hours to repair.";
		PropertyHelp[17] = "Kron = Y/N. Default=N.  Perform Kron reduction on the impedance matrix after it is formed, reducing order by 1. " +
							"Eliminates the conductor designated by the \"Neutral=\" property. " +
							"Do this after the R, X, and C matrices are defined. Ignored for symmetrical components. " +
							"May be issued more than once to eliminate more than one conductor by resetting the Neutral property after the previous " +
							"invoking of this property. Generally, you do not want to do a Kron reduction on the matrix if you intend to solve at a " +
							"frequency other than the base frequency and exploit the Rg and Xg values.";
		PropertyHelp[18] = "Carson earth return resistance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments. " +
							"Default is 0.01805 = 60 Hz value in ohms per kft (matches default line impedances). " +
							"This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. " +
							"If not, set both Rg and Xg = 0.";
		PropertyHelp[19] = "Carson earth return reactance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments. " +
							"Default value is 0.155081 = 60 Hz value in ohms per kft (matches default line impedances). " +
							"This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. " +
							"If not, set both Rg and Xg = 0.";
		PropertyHelp[20] = "Default=100 meter ohms.  Earth resitivity used to compute earth correction factor.";
		PropertyHelp[21] = "Designates which conductor is the \"neutral\" conductor that will be eliminated by Kron reduction. " +
							"Default is the last conductor (nphases value). After Kron reduction is set to 0. Subsequent issuing of Kron=Yes " +
							"will not do anything until this property is set to a legal value. Applies only to LineCodes defined by R, X, and C matrix.";

		ActiveProperty = LineCode.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	/**
	 * Create a new object of this class and add to list.
	 */
	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Globals.setActiveDSSObject(new LineCodeObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	/**
	 * Decodes the units string and sets the units variable.
	 */
	private void setUnits(String S) {
		getActiveLineCodeObj().setUnits(LineUnits.getUnitsCode(S));
	}

	/**
	 * Set symmetrical component impedances and a flag to indicate they were changed.
	 */
	private void setZ1Z0(int i, double Value) {
		SymComponentsChanged = true;

		getActiveLineCodeObj().setSymComponentsModel(true);

		switch (i) {
		case 1:  // TODO Check zero based indexing
			getActiveLineCodeObj().setR1(Value);
		case 2:
			getActiveLineCodeObj().setX1(Value);
		case 3:
			getActiveLineCodeObj().setR0(Value);
		case 4:
			getActiveLineCodeObj().setX0(Value);
		case 5:
			getActiveLineCodeObj().setC1(Value);
		case 6:
			getActiveLineCodeObj().setC0(Value);
		}
	}

	/**
	 * Set impedances as matrices.
	 */
	private void doMatrix(int i) {
		int OrderFound, Norder = 0, j;
		double[] MatBuffer;
		Complex[] ZValues;
		double Factor;

		int np2 = getActiveLineCodeObj().getNPhases() * getActiveLineCodeObj().getNPhases();

		MatrixChanged = true;
		MatBuffer = new double[np2];
		OrderFound = Parser.getInstance().parseAsSymMatrix(getActiveLineCodeObj().getNPhases(), MatBuffer);

		if (OrderFound > 0) {  // Parse was successful
			switch (i) {  // TODO Check zero based indexing
			case 1:  // R
				ZValues = getActiveLineCodeObj().getZ().asArray(Norder);  // TODO Check nOrder is set
				if (Norder == getActiveLineCodeObj().getNPhases())
					for (j = 0; j < np2; j++)
						ZValues[j] = new Complex(MatBuffer[j], ZValues[j].getImaginary());
			case 2:  // X
				ZValues = getActiveLineCodeObj().getZ().asArray(Norder);
				if (Norder == getActiveLineCodeObj().getNPhases())
					for (j = 0; j < np2; j++)
						ZValues[j] = new Complex(ZValues[j].getReal(), MatBuffer[j]);
			case 3:  // YC Matrix
				Factor = DSSGlobals.TwoPi * getActiveLineCodeObj().getBaseFrequency() * 1.0e-9;
				ZValues = getActiveLineCodeObj().getYC().asArray(Norder);
				if (Norder == getActiveLineCodeObj().getNPhases())
					for (j = 0; j < np2; j++)
						ZValues[j] = new Complex(ZValues[j].getReal(), Factor * MatBuffer[j]);
			}
		}

		MatBuffer = null;
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		int Result = 0;

		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveLineCodeObj((LineCodeObj) ElementList.getActive());
		DSSGlobals.getInstance().setActiveDSSObject(getActiveLineCodeObj());
		SymComponentsChanged = false;
		MatrixChanged = false;
		getActiveLineCodeObj().setReduceByKron(false);  // Allow all matrices to be computed it raw form

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);

				if ((ParamPointer > 0) && (ParamPointer <= NumProperties))
					getActiveLineCodeObj().setPropertyValue(ParamPointer, Param);

				switch (ParamPointer) {
				case 0:
					DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ getName() + "\"", 101);
				case 1:
					getActiveLineCodeObj().setNPhases(parser.makeInteger());  // Use property value to force reallocations
				case 2:
					setZ1Z0(1, parser.makeDouble());  // R1
				case 3:
					setZ1Z0(2, parser.makeDouble());  // X0
				case 4:
					setZ1Z0(3, parser.makeDouble());  // R1
				case 5:
					setZ1Z0(4, parser.makeDouble());  // X0
				case 6:
					setZ1Z0(5, parser.makeDouble() * 1.0e-9);  // C1   // Convert from nano to farads
				case 7:
					setZ1Z0(6, parser.makeDouble() * 1.0e-9);  // C0
				case 8:
					setUnits(Param);
				case 9:  // Rmatrix
					doMatrix(1);
				case 10:  // Xmatrix
					doMatrix(2);
				case 11:  // Cmatrix
					doMatrix(3);
				case 12:
					getActiveLineCodeObj().setBaseFrequency(parser.makeDouble());
				case 13:
					getActiveLineCodeObj().setNormAmps(parser.makeDouble());
				case 14:
					getActiveLineCodeObj().setEmergAmps(parser.makeDouble());
				case 15:
					getActiveLineCodeObj().setFaultRate(parser.makeDouble());
				case 16:
					getActiveLineCodeObj().setPctPerm(parser.makeDouble());
				case 17:
					getActiveLineCodeObj().setHrsToRepair(parser.makeDouble());
				case 18:
					getActiveLineCodeObj().setReduceByKron(Utilities.interpretYesNo(Param));
				case 19:
					getActiveLineCodeObj().setRg(parser.makeDouble());
				case 20:
					getActiveLineCodeObj().setXg(parser.makeDouble());
				case 21:
					getActiveLineCodeObj().setRho(parser.makeDouble());
				case 22:
					getActiveLineCodeObj().setNeutralConductor(parser.makeInteger());
				default:
					classEdit(getActiveLineCodeObj(), ParamPointer - LineCode.NumPropsThisClass);
				}

				switch (ParamPointer) {
				case 9:
					getActiveLineCodeObj().setSymComponentsModel(false);
				case 10:
					getActiveLineCodeObj().setSymComponentsModel(false);
				case 11:
					getActiveLineCodeObj().setSymComponentsModel(false);
				case 18:
					if (getActiveLineCodeObj().isReduceByKron() && !getActiveLineCodeObj().isSymComponentsModel())
						getActiveLineCodeObj().doKronReduction();
				}


				ParamName = parser.getNextParam();
				Param = parser.makeString();
			}

			if (getActiveLineCodeObj().isSymComponentsModel())
				getActiveLineCodeObj().calcMatricesFromZ1Z0();
			if (MatrixChanged) {
				getActiveLineCodeObj().getZinv().copyFrom(getActiveLineCodeObj().getZ());
				getActiveLineCodeObj().getZinv().invert();
			}
		}

		return 0;
	}

	@Override
	protected int makeLike(String LineName) {

		int Result = 0;
		/* See if we can find this line code in the present collection */
		LineCodeObj OtherLineCode = (LineCodeObj) find(LineName);
		if (OtherLineCode != null) {

			if (getActiveLineCodeObj().getNPhases() != OtherLineCode.getNPhases()) {
				getActiveLineCodeObj().setNPhases(OtherLineCode.getNPhases());

				if (getActiveLineCodeObj().getZ() != null)
					getActiveLineCodeObj().setZ(null);
				if (getActiveLineCodeObj().getZinv() != null)
					getActiveLineCodeObj().setZinv(null);
				if (getActiveLineCodeObj().getYC() != null)
					getActiveLineCodeObj().setYC(null);

				getActiveLineCodeObj().setZ(new CMatrixImpl(getActiveLineCodeObj().getNPhases()));
				getActiveLineCodeObj().setZinv(new CMatrixImpl(getActiveLineCodeObj().getNPhases()));
				getActiveLineCodeObj().setYC(new CMatrixImpl(getActiveLineCodeObj().getNPhases()));
			}

			getActiveLineCodeObj().getZ().copyFrom(OtherLineCode.getZ());
			getActiveLineCodeObj().getZinv().copyFrom(OtherLineCode.getZinv());
			getActiveLineCodeObj().getYC().copyFrom(OtherLineCode.getYC());
			getActiveLineCodeObj().setBaseFrequency(OtherLineCode.getBaseFrequency());
			getActiveLineCodeObj().setR1(OtherLineCode.getR1());
			getActiveLineCodeObj().setX1(OtherLineCode.getX1());
			getActiveLineCodeObj().setR0(OtherLineCode.getR0());
			getActiveLineCodeObj().setX0(OtherLineCode.getX0());
			getActiveLineCodeObj().setC1(OtherLineCode.getC1());
			getActiveLineCodeObj().setC0(OtherLineCode.getC0());
			getActiveLineCodeObj().setRg(OtherLineCode.getRg());
			getActiveLineCodeObj().setXg(OtherLineCode.getXg());
			getActiveLineCodeObj().setRho(OtherLineCode.getRho());
			getActiveLineCodeObj().setNeutralConductor(OtherLineCode.getNeutralConductor());
			getActiveLineCodeObj().setNormAmps(OtherLineCode.getNormAmps());
			getActiveLineCodeObj().setEmergAmps(OtherLineCode.getEmergAmps());
			getActiveLineCodeObj().setFaultRate(OtherLineCode.getFaultRate());
			getActiveLineCodeObj().setPctPerm(OtherLineCode.getPctPerm());
			getActiveLineCodeObj().setHrsToRepair(OtherLineCode.getHrsToRepair());

			for (int i = 0; i < getActiveLineCodeObj().getParentClass().getNumProperties(); i++)
				getActiveLineCodeObj().setPropertyValue(i, OtherLineCode.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Line MakeLike: \"" + LineName + "\" Not Found.", 102);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement LineCode.init()", -1);
		return 0;
	}

	/**
	 * Returns active line code string.
	 */
	public String getCode() {
		LineCodeObj active = (LineCodeObj) ElementList.getActive();
		return active.getName();
	}

	/**
	 * Sets the active linecode.
	 */
	public void setCode(String Value) {
		LineCodeObj pCode;

		setActiveLineCodeObj(null);
		for (int i = 0; i < ElementList.size(); i++) {
			pCode = (LineCodeObj) ElementList.get(i);
			if (pCode.getName().equals(Value)) {
				setActiveLineCodeObj(pCode);
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("Linecode: \"" + Value + "\" not Found.", 103);
	}

	public static LineCodeObj getActiveLineCodeObj() {
		return ActiveLineCodeObj;
	}

	public static void setActiveLineCodeObj(LineCodeObj activeLineCodeObj) {
		ActiveLineCodeObj = activeLineCodeObj;
	}


}
