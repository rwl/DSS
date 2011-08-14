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

	private static LineCodeObj activeLineCodeObj;

	private boolean symComponentsChanged;

	private boolean matrixChanged;

	public LineCodeImpl() {
		super();
		this.className = "LineCode";
		this.classType= DSSClassDefs.DSS_OBJECT;
		this.activeElement = -1;

		defineProperties();

		String[] commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		numProperties = LineCode.NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();

		propertyName[0] = "nphases";
		propertyName[1] = "r1";
		propertyName[2] = "x1";
		propertyName[3] = "r0";
		propertyName[4] = "x0";
		propertyName[5] = "c1";
		propertyName[6] = "c0";
		propertyName[7] = "units";
		propertyName[8] = "rmatrix";
		propertyName[9] = "xmatrix";
		propertyName[10] = "cmatrix";
		propertyName[11] = "baseFreq";
		propertyName[12] = "normamps";
		propertyName[13] = "emergamps";
		propertyName[14] = "faultrate";
		propertyName[15] = "pctperm";
		propertyName[16] = "repair";
		propertyName[17] = "Kron";
		propertyName[18] = "Rg";
		propertyName[19] = "Xg";
		propertyName[20] = "rho";
		propertyName[21] = "neutral";

		propertyHelp[0] = "Number of phases in the line this line code data represents.  Setting this property reinitializes the line code.  Impedance matrix is reset for default symmetrical component.";
		propertyHelp[1] = "Positive-sequence Resistance, ohms per unit length.  See also Rmatrix.";
		propertyHelp[2] = "Positive-sequence Reactance, ohms per unit length.  See also Xmatrix";
		propertyHelp[3] = "Zero-sequence Resistance, ohms per unit length.";
		propertyHelp[4] = "Zero-sequence Reactance, ohms per unit length.";
		propertyHelp[5] = "Positive-sequence capacitance, nf per unit length. See also Cmatrix.";
		propertyHelp[6] = "Zero-sequence capacitance, nf per unit length.";
		propertyHelp[7] = "One of (ohms per ...) {none|mi|km|kft|m|me|ft|in|cm}.  Default is none; assumes units agree with length units" +
					"given in Line object";
		propertyHelp[8] = "Resistance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. "+
						"May be used to specify the impedance of any line configuration.  For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		propertyHelp[9] = "Reactance matrix, lower triangle, ohms per unit length. Order of the matrix is the number of phases. "+
						"May be used to specify the impedance of any line configuration.  For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		propertyHelp[10] = "Nodal Capacitance matrix, lower triangle, nf per unit length.Order of the matrix is the number of phases. "+
						"May be used to specify the shunt capacitance of any line configuration.  For balanced line models, you may "+
						"use the standard symmetrical component data definition instead.";
		propertyHelp[11] = "Frequency at which impedances are specified.";
		propertyHelp[12] = "Normal ampere limit on line.  This is the so-called Planning Limit. It may also be "+
						"the value above which load will have to be dropped in a contingency.  Usually about "+
						"75% - 80% of the emergency (one-hour) rating.";
		propertyHelp[13] = "Emergency ampere limit on line (usually one-hour rating).";
		propertyHelp[14] = "Number of faults per unit length per year.";
		propertyHelp[15] = "Percentage of the faults that become permanent.";
		propertyHelp[16] = "Hours to repair.";
		propertyHelp[17] = "Kron = Y/N. Default=N.  Perform Kron reduction on the impedance matrix after it is formed, reducing order by 1. " +
							"Eliminates the conductor designated by the \"Neutral=\" property. " +
							"Do this after the R, X, and C matrices are defined. Ignored for symmetrical components. " +
							"May be issued more than once to eliminate more than one conductor by resetting the Neutral property after the previous " +
							"invoking of this property. Generally, you do not want to do a Kron reduction on the matrix if you intend to solve at a " +
							"frequency other than the base frequency and exploit the Rg and Xg values.";
		propertyHelp[18] = "Carson earth return resistance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments. " +
							"Default is 0.01805 = 60 Hz value in ohms per kft (matches default line impedances). " +
							"This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. " +
							"If not, set both Rg and Xg = 0.";
		propertyHelp[19] = "Carson earth return reactance per unit length used to compute impedance values at base frequency.  For making better frequency adjustments. " +
							"Default value is 0.155081 = 60 Hz value in ohms per kft (matches default line impedances). " +
							"This value is required for harmonic solutions if you wish to adjust the earth return impedances for frequency. " +
							"If not, set both Rg and Xg = 0.";
		propertyHelp[20] = "Default=100 meter ohms.  Earth resitivity used to compute earth correction factor.";
		propertyHelp[21] = "Designates which conductor is the \"neutral\" conductor that will be eliminated by Kron reduction. " +
							"Default is the last conductor (nphases value). After Kron reduction is set to 0. Subsequent issuing of Kron=Yes " +
							"will not do anything until this property is set to a legal value. Applies only to LineCodes defined by R, X, and C matrix.";

		activeProperty = LineCode.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	/**
	 * Create a new object of this class and add to list.
	 */
	@Override
	public int newObject(String objName) {
		DSSGlobals globals = DSSGlobals.getInstance();
		globals.setActiveDSSObject(new LineCodeObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	/**
	 * Decodes the units string and sets the units variable.
	 */
	private void setUnits(String s) {
		getActiveLineCodeObj().setUnits(LineUnits.getUnitsCode(s));
	}

	/**
	 * Set symmetrical component impedances and a flag to indicate they were changed.
	 */
	private void setZ1Z0(int i, double value) {
		symComponentsChanged = true;

		getActiveLineCodeObj().setSymComponentsModel(true);

		switch (i) {
		case 1:  // TODO Check zero based indexing
			getActiveLineCodeObj().setR1(value);
			break;
		case 2:
			getActiveLineCodeObj().setX1(value);
			break;
		case 3:
			getActiveLineCodeObj().setR0(value);
			break;
		case 4:
			getActiveLineCodeObj().setX0(value);
			break;
		case 5:
			getActiveLineCodeObj().setC1(value);
			break;
		case 6:
			getActiveLineCodeObj().setC0(value);
			break;
		}
	}

	/**
	 * Set impedances as matrices.
	 */
	private void doMatrix(int i) {
		int orderFound, nOrder = 0, j;
		double[] matBuffer;
		Complex[] ZValues;
		double factor;

		int np2 = getActiveLineCodeObj().getNPhases() * getActiveLineCodeObj().getNPhases();

		matrixChanged = true;
		matBuffer = new double[np2];
		orderFound = Parser.getInstance().parseAsSymMatrix(getActiveLineCodeObj().getNPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful
			switch (i) {  // TODO Check zero based indexing
			case 1:  // r
				ZValues = getActiveLineCodeObj().getZ().asArray(nOrder);  // TODO Check nOrder is set
				if (nOrder == getActiveLineCodeObj().getNPhases())
					for (j = 0; j < np2; j++)
						ZValues[j] = new Complex(matBuffer[j], ZValues[j].getImaginary());
				break;
			case 2:  // x
				ZValues = getActiveLineCodeObj().getZ().asArray(nOrder);
				if (nOrder == getActiveLineCodeObj().getNPhases())
					for (j = 0; j < np2; j++)
						ZValues[j] = new Complex(ZValues[j].getReal(), matBuffer[j]);
				break;
			case 3:  // Yc matrix
				factor = DSSGlobals.TWO_PI * getActiveLineCodeObj().getBaseFrequency() * 1.0e-9;
				ZValues = getActiveLineCodeObj().getYC().asArray(nOrder);
				if (nOrder == getActiveLineCodeObj().getNPhases())
					for (j = 0; j < np2; j++)
						ZValues[j] = new Complex(ZValues[j].getReal(), factor * matBuffer[j]);
				break;
			}
		}

		matBuffer = null;
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		int result = 0;

		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveLineCodeObj((LineCodeObj) elementList.getActive());
		DSSGlobals.getInstance().setActiveDSSObject(getActiveLineCodeObj());
		symComponentsChanged = false;
		matrixChanged = false;
		getActiveLineCodeObj().setReduceByKron(false);  // allow all matrices to be computed it raw form

		int paramPointer = 0;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);

				if ((paramPointer > 0) && (paramPointer <= numProperties))
					getActiveLineCodeObj().setPropertyValue(paramPointer, param);

				switch (paramPointer) {
				case 0:
					DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + paramName + "\" for Object \"" + getName() +"."+ getName() + "\"", 101);
					break;
				case 1:
					getActiveLineCodeObj().setNPhases(parser.makeInteger());  // use property value to force reallocations
					break;
				case 2:
					setZ1Z0(1, parser.makeDouble());  // R1
					break;
				case 3:
					setZ1Z0(2, parser.makeDouble());  // X0
					break;
				case 4:
					setZ1Z0(3, parser.makeDouble());  // R1
					break;
				case 5:
					setZ1Z0(4, parser.makeDouble());  // X0
					break;
				case 6:
					setZ1Z0(5, parser.makeDouble() * 1.0e-9);  // C1   // convert from nano to farads
					break;
				case 7:
					setZ1Z0(6, parser.makeDouble() * 1.0e-9);  // C0
					break;
				case 8:
					setUnits(param);
					break;
				case 9:  // Rmatrix
					doMatrix(1);
					break;
				case 10:  // Xmatrix
					doMatrix(2);
					break;
				case 11:  // Cmatrix
					doMatrix(3);
					break;
				case 12:
					getActiveLineCodeObj().setBaseFrequency(parser.makeDouble());
					break;
				case 13:
					getActiveLineCodeObj().setNormAmps(parser.makeDouble());
					break;
				case 14:
					getActiveLineCodeObj().setEmergAmps(parser.makeDouble());
					break;
				case 15:
					getActiveLineCodeObj().setFaultRate(parser.makeDouble());
					break;
				case 16:
					getActiveLineCodeObj().setPctPerm(parser.makeDouble());
					break;
				case 17:
					getActiveLineCodeObj().setHrsToRepair(parser.makeDouble());
					break;
				case 18:
					getActiveLineCodeObj().setReduceByKron(Utilities.interpretYesNo(param));
					break;
				case 19:
					getActiveLineCodeObj().setRg(parser.makeDouble());
					break;
				case 20:
					getActiveLineCodeObj().setXg(parser.makeDouble());
					break;
				case 21:
					getActiveLineCodeObj().setRho(parser.makeDouble());
					break;
				case 22:
					getActiveLineCodeObj().setNeutralConductor(parser.makeInteger());
					break;
				default:
					classEdit(getActiveLineCodeObj(), paramPointer - LineCode.NumPropsThisClass);
					break;
				}

				switch (paramPointer) {
				case 9:
					getActiveLineCodeObj().setSymComponentsModel(false);
					break;
				case 10:
					getActiveLineCodeObj().setSymComponentsModel(false);
					break;
				case 11:
					getActiveLineCodeObj().setSymComponentsModel(false);
					break;
				case 18:
					if (getActiveLineCodeObj().isReduceByKron() && !getActiveLineCodeObj().isSymComponentsModel())
						getActiveLineCodeObj().doKronReduction();
					break;
				}


				paramName = parser.getNextParam();
				param = parser.makeString();
			}

			if (getActiveLineCodeObj().isSymComponentsModel())
				getActiveLineCodeObj().calcMatricesFromZ1Z0();
			if (matrixChanged) {
				getActiveLineCodeObj().getZinv().copyFrom(getActiveLineCodeObj().getZ());
				getActiveLineCodeObj().getZinv().invert();
			}
		}

		return result;
	}

	@Override
	protected int makeLike(String lineName) {

		int result = 0;
		/* See if we can find this line code in the present collection */
		LineCodeObj otherLineCode = (LineCodeObj) find(lineName);
		if (otherLineCode != null) {

			if (getActiveLineCodeObj().getNPhases() != otherLineCode.getNPhases()) {
				getActiveLineCodeObj().setNPhases(otherLineCode.getNPhases());

				if (getActiveLineCodeObj().getZ() != null)
					getActiveLineCodeObj().setZ(null);
				if (getActiveLineCodeObj().getZinv() != null)
					getActiveLineCodeObj().setZinv(null);
				if (getActiveLineCodeObj().getYC() != null)
					getActiveLineCodeObj().setYc(null);

				getActiveLineCodeObj().setZ(new CMatrixImpl(getActiveLineCodeObj().getNPhases()));
				getActiveLineCodeObj().setZinv(new CMatrixImpl(getActiveLineCodeObj().getNPhases()));
				getActiveLineCodeObj().setYc(new CMatrixImpl(getActiveLineCodeObj().getNPhases()));
			}

			getActiveLineCodeObj().getZ().copyFrom(otherLineCode.getZ());
			getActiveLineCodeObj().getZinv().copyFrom(otherLineCode.getZinv());
			getActiveLineCodeObj().getYC().copyFrom(otherLineCode.getYC());
			getActiveLineCodeObj().setBaseFrequency(otherLineCode.getBaseFrequency());
			getActiveLineCodeObj().setR1(otherLineCode.getR1());
			getActiveLineCodeObj().setX1(otherLineCode.getX1());
			getActiveLineCodeObj().setR0(otherLineCode.getR0());
			getActiveLineCodeObj().setX0(otherLineCode.getX0());
			getActiveLineCodeObj().setC1(otherLineCode.getC1());
			getActiveLineCodeObj().setC0(otherLineCode.getC0());
			getActiveLineCodeObj().setRg(otherLineCode.getRg());
			getActiveLineCodeObj().setXg(otherLineCode.getXg());
			getActiveLineCodeObj().setRho(otherLineCode.getRho());
			getActiveLineCodeObj().setNeutralConductor(otherLineCode.getNeutralConductor());
			getActiveLineCodeObj().setNormAmps(otherLineCode.getNormAmps());
			getActiveLineCodeObj().setEmergAmps(otherLineCode.getEmergAmps());
			getActiveLineCodeObj().setFaultRate(otherLineCode.getFaultRate());
			getActiveLineCodeObj().setPctPerm(otherLineCode.getPctPerm());
			getActiveLineCodeObj().setHrsToRepair(otherLineCode.getHrsToRepair());

			for (int i = 0; i < getActiveLineCodeObj().getParentClass().getNumProperties(); i++)
				getActiveLineCodeObj().setPropertyValue(i, otherLineCode.getPropertyValue(i));
			result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in line makeLike: \"" + lineName + "\" not found.", 102);
		}

		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement LineCode.init()", -1);
		return 0;
	}

	/**
	 * Returns active line code string.
	 */
	public String getCode() {
		LineCodeObj active = (LineCodeObj) elementList.getActive();
		return active.getName();
	}

	/**
	 * Sets the active line code.
	 */
	public void setCode(String value) {
		LineCodeObj pCode;

		setActiveLineCodeObj(null);
		for (int i = 0; i < elementList.size(); i++) {
			pCode = (LineCodeObj) elementList.get(i);
			if (pCode.getName().equalsIgnoreCase(value)) {
				setActiveLineCodeObj(pCode);
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("LineCode: \"" + value + "\" not found.", 103);
	}

	public static LineCodeObj getActiveLineCodeObj() {
		return activeLineCodeObj;
	}

	public static void setActiveLineCodeObj(LineCodeObj lineCodeObj) {
		activeLineCodeObj = lineCodeObj;
	}


}
