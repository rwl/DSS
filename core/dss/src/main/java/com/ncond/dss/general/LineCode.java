package com.ncond.dss.general;


import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.LineUnits;

public class LineCode extends DSSClass {

	public static final int NumPropsThisClass = 22;

	public static LineCodeObj activeLineCodeObj;

	private boolean matrixChanged;

	public LineCode() {
		super();

		className = "LineCode";
		classType= DSSClassDefs.DSS_OBJECT;
		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		numProperties = LineCode.NumPropsThisClass;
		countProperties();  // get inherited property count

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
		DSS.activeDSSObject = new LineCodeObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	/**
	 * Decodes the units string and sets the units variable.
	 */
	private void setUnits(String s) {
		activeLineCodeObj.setUnits(LineUnits.interpretUnitsCode(s));
	}

	/**
	 * Set symmetrical component impedances and a flag to indicate they were changed.
	 */
	private void setZ1Z0(int i, double value) {
		activeLineCodeObj.setSymComponentsModel(true);

		switch (i) {
		case 1:
			activeLineCodeObj.setR1(value);
			break;
		case 2:
			activeLineCodeObj.setX1(value);
			break;
		case 3:
			activeLineCodeObj.setR0(value);
			break;
		case 4:
			activeLineCodeObj.setX0(value);
			break;
		case 5:
			activeLineCodeObj.setC1(value);
			break;
		case 6:
			activeLineCodeObj.setC0(value);
			break;
		}
	}

	/**
	 * Set impedances as matrices.
	 */
	private void doMatrix(int i) {
		int orderFound, j;
		int[] nOrder = new int[1];
		double[] matBuffer;
		Complex[] Zvalues;
		double factor;

		int np2 = activeLineCodeObj.getNPhases() * activeLineCodeObj.getNPhases();

		matrixChanged = true;
		matBuffer = new double[np2];
		orderFound = Parser.getInstance().parseAsSymMatrix(activeLineCodeObj.getNPhases(), matBuffer);

		if (orderFound > 0) {  // parse was successful
			switch (i) {
			case 1:  // r
				Zvalues = activeLineCodeObj.getZ().asArray(nOrder);
				if (nOrder[0] == activeLineCodeObj.getNPhases())
					for (j = 0; j < np2; j++)
						Zvalues[j] = new Complex(matBuffer[j], Zvalues[j].getImaginary());
				break;
			case 2:  // x
				Zvalues = activeLineCodeObj.getZ().asArray(nOrder);
				if (nOrder[0] == activeLineCodeObj.getNPhases())
					for (j = 0; j < np2; j++)
						Zvalues[j] = new Complex(Zvalues[j].getReal(), matBuffer[j]);
				break;
			case 3:  // Yc matrix
				factor = DSS.TWO_PI * activeLineCodeObj.getBaseFrequency() * 1.0e-9;
				Zvalues = activeLineCodeObj.getYc().asArray(nOrder);
				if (nOrder[0] == activeLineCodeObj.getNPhases())
					for (j = 0; j < np2; j++)
						Zvalues[j] = new Complex(Zvalues[j].getReal(), factor * matBuffer[j]);
				break;
			}
		}
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeLineCodeObj = (LineCodeObj) elementList.getActive();
		DSS.activeDSSObject = activeLineCodeObj;

		matrixChanged = false;
		activeLineCodeObj.setReduceByKron(false);  // allow all matrices to be computed it raw form

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
				activeLineCodeObj.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Object \"" +
						getClassName() +"."+ getClassName() + "\"", 101);
				break;
			case 0:
				activeLineCodeObj.setNPhases(parser.makeInteger());  // use property value to force reallocations
				break;
			case 1:
				setZ1Z0(1, parser.makeDouble());  // R1
				break;
			case 2:
				setZ1Z0(2, parser.makeDouble());  // X0
				break;
			case 3:
				setZ1Z0(3, parser.makeDouble());  // R1
				break;
			case 4:
				setZ1Z0(4, parser.makeDouble());  // X0
				break;
			case 5:
				setZ1Z0(5, parser.makeDouble() * 1.0e-9);  // C1   // convert from nano to farads
				break;
			case 6:
				setZ1Z0(6, parser.makeDouble() * 1.0e-9);  // C0
				break;
			case 7:
				setUnits(param);
				break;
			case 8:  // Rmatrix
				doMatrix(1);
				break;
			case 9:  // Xmatrix
				doMatrix(2);
				break;
			case 10:  // Cmatrix
				doMatrix(3);
				break;
			case 11:
				activeLineCodeObj.setBaseFrequency(parser.makeDouble());
				break;
			case 12:
				activeLineCodeObj.setNormAmps(parser.makeDouble());
				break;
			case 13:
				activeLineCodeObj.setEmergAmps(parser.makeDouble());
				break;
			case 14:
				activeLineCodeObj.setFaultRate(parser.makeDouble());
				break;
			case 15:
				activeLineCodeObj.setPctPerm(parser.makeDouble());
				break;
			case 16:
				activeLineCodeObj.setHrsToRepair(parser.makeDouble());
				break;
			case 17:
				activeLineCodeObj.setReduceByKron(Util.interpretYesNo(param));
				break;
			case 18:
				activeLineCodeObj.setRg(parser.makeDouble());
				break;
			case 19:
				activeLineCodeObj.setXg(parser.makeDouble());
				break;
			case 20:
				activeLineCodeObj.setRho(parser.makeDouble());
				break;
			case 21:
				activeLineCodeObj.setNeutralConductor(parser.makeInteger());
				break;
			default:
				classEdit(activeLineCodeObj, paramPointer - LineCode.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 8:
				activeLineCodeObj.setSymComponentsModel(false);
				break;
			case 9:
				activeLineCodeObj.setSymComponentsModel(false);
				break;
			case 10:
				activeLineCodeObj.setSymComponentsModel(false);
				break;
			case 17:
				if (activeLineCodeObj.isReduceByKron() && !activeLineCodeObj.isSymComponentsModel())
					activeLineCodeObj.doKronReduction();
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (activeLineCodeObj.isSymComponentsModel())
			activeLineCodeObj.calcMatricesFromZ1Z0();
		if (matrixChanged) {
			activeLineCodeObj.getZinv().copyFrom(activeLineCodeObj.getZ());
			activeLineCodeObj.getZinv().invert();
		}

		return 0;
	}

	@Override
	protected int makeLike(String lineName) {
		int success = 0;

		/* See if we can find this line code in the present collection */
		LineCodeObj other = (LineCodeObj) find(lineName);

		if (other != null) {
			if (activeLineCodeObj.getNPhases() != other.getNPhases()) {
				activeLineCodeObj.setNPhases(other.getNPhases());

				activeLineCodeObj.setZ(new CMatrix(activeLineCodeObj.getNPhases()));
				activeLineCodeObj.setZinv(new CMatrix(activeLineCodeObj.getNPhases()));
				activeLineCodeObj.setYc(new CMatrix(activeLineCodeObj.getNPhases()));
			}

			activeLineCodeObj.getZ().copyFrom(other.getZ());
			activeLineCodeObj.getZinv().copyFrom(other.getZinv());
			activeLineCodeObj.getYc().copyFrom(other.getYc());
			activeLineCodeObj.setBaseFrequency(other.getBaseFrequency());
			activeLineCodeObj.setR1(other.getR1());
			activeLineCodeObj.setX1(other.getX1());
			activeLineCodeObj.setR0(other.getR0());
			activeLineCodeObj.setX0(other.getX0());
			activeLineCodeObj.setC1(other.getC1());
			activeLineCodeObj.setC0(other.getC0());
			activeLineCodeObj.setRg(other.getRg());
			activeLineCodeObj.setXg(other.getXg());
			activeLineCodeObj.setRho(other.getRho());
			activeLineCodeObj.setNeutralConductor(other.getNeutralConductor());
			activeLineCodeObj.setNormAmps(other.getNormAmps());
			activeLineCodeObj.setEmergAmps(other.getEmergAmps());
			activeLineCodeObj.setFaultRate(other.getFaultRate());
			activeLineCodeObj.setPctPerm(other.getPctPerm());
			activeLineCodeObj.setHrsToRepair(other.getHrsToRepair());

			for (int i = 0; i < activeLineCodeObj.getParentClass().getNumProperties(); i++)
				activeLineCodeObj.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in line makeLike: \"" + lineName + "\" not found", 102);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement LineCode.init()", -1);
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
		LineCodeObj code;

		activeLineCodeObj = null;

		for (int i = 0; i < elementList.size(); i++) {
			code = (LineCodeObj) elementList.get(i);
			if (code.getName().equalsIgnoreCase(value)) {
				activeLineCodeObj = code;
				return;
			}
		}

		DSS.doSimpleMsg("LineCode: \"" + value + "\" not found", 103);
	}

}
