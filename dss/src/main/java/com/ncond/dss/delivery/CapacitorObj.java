/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.delivery;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.shared.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;

/**
 * Basic capacitor.
 *
 * Implemented as a two-terminal constant impedance (power delivery element)
 *
 * bus2 connection defaults to 0 node of bus1 (if bus2 has the default bus
 * connection at the time bus1 is defined.  Therefore, if only bus1 is
 * specified, a shunt capacitor results. If delta connected, bus2 is set to
 * node zero of bus1 and nothing is returned in the lower half of YPrim - all
 * zeroes.
 *
 * If an ungrounded wye is desired, explicitly set bus2= and set all nodes the
 * same,
 *   e.g. bus1.4.4.4   (uses 4th node of Bus1 as neutral point)
 *     or busNew.1.1.1  (makes a new bus for the neutral point)
 * You must specify the nodes or you will get a series capacitor!
 *
 * A series capacitor is specified simply by setting bus2 and declaring the
 * connection to be Wye.  If the connection is specified as delta, nothing will
 * be connected to bus2.
 * In fact the number of terminals is set to 1.
 *
 * Capacitance may be specified as:
 *
 *   1.  kvar and kv ratings at base frequency.  impedance.  Specify kvar as
 *       total for all phases (all cans assumed equal). For 1-phase,
 *       kV = capacitor can kV rating. For 2 or 3-phase, kV is line-line three
 *       phase. For more than 3 phases, specify kV as actual can voltage.
 *   2.  Capacitance in uF to be used in each phase.  If specified in this
 *       manner, the given value is always used whether wye or delta.
 *   3.  A nodal C matrix (like a nodal admittance matrix).
 *       If conn=wye then 2-terminal through device
 *       If conn=delta then 1-terminal.
 *       Microfarads.
 *
 */
public class CapacitorObj extends PDElement {

	// single C per phase (line rating) if Cmatrix not specified
	private double[] C, XL, kVArRating, R, harm;

	private boolean[] states;

	private double totalKVAr, kVRating;
	private int numSteps, lastStepInService;
	private double[] Cmatrix;  // if not nil then overrides C

	private boolean doHarmonicRecalc;

	private CapacitorSpecType specType;

	protected Connection connection;

	public CapacitorObj(DSSClass parClass, String capacitorName) {
		super(parClass);

		setName(capacitorName.toLowerCase());
		objType = parClass.getClassType();

		setNumPhases(3);  // directly set conds and phases
		nConds = 3;
		setNumTerms(2);  // force allocation of terminals and conductors

		setBus(1, (getBus(0) + ".0.0.0"));  // default to grounded wye

		isShunt = true;  // defaults to shunt capacitor

		Cmatrix = null;

		/* Initialize these pointers to nil so reallocmem will work reliably. */
		C = null;
		XL = null;
		kVArRating = null;
		R = null;
		harm = null;
		states = null;

		setNumSteps(1);  // initial allocation for the arrays, too
		lastStepInService = numSteps - 1;

		Util.initDblArray(numSteps, R, 0.0);
		Util.initDblArray(numSteps, XL, 0.0);
		Util.initDblArray(numSteps, harm, 0.0);
		Util.initDblArray(numSteps, kVArRating, 1200.0);

		states[0] = true;

		kVRating = 12.47;
		Util.initDblArray(numSteps, C,
			1.0 / (DSS.TWO_PI * baseFrequency * Math.pow(kVRating, 2) * 1000.0 / kVArRating[0]));

		connection = Connection.WYE;
		specType = CapacitorSpecType.KVAR;

		normAmps = kVArRating[0] * DSS.SQRT3 / kVRating * 1.35;  // 135%
		emergAmps = getNormAmps() * 1.8 / 1.35;  // 180%
		faultRate = 0.0005;
		pctPerm = 100.0;
		hrsToRepair = 3.0;
		YOrder = nTerms * nConds;

		doHarmonicRecalc = false;

		recalcElementData();

		initPropertyValues(0);
	}

	@Override
	public void recalcElementData() {
		double kVArPerPhase, phaseKV, w;
		int i;

		totalKVAr = 0.0;
		phaseKV = 1.0;
		w = DSS.TWO_PI * baseFrequency;

		switch (specType) {
		case KVAR:
			switch (connection) {
			case DELTA:
				phaseKV = kVRating;
				break;
			default:
				switch (nPhases) {
				case 2:
				case 3:
					phaseKV = kVRating / DSS.SQRT3;  // assume three phase system
					break;
				default:
					phaseKV = kVRating;
					break;
				}
				break;
			}

			for (i = 0; i < numSteps; i++)
				C[i] = 1.0 / (w * Math.pow(phaseKV, 2) * 1000.0 / (kVArRating[0] / nPhases));

			for (i = 0; i < numSteps; i++)
				totalKVAr = totalKVAr + kVArRating[i];
			break;
		case CUF:
			switch (connection) {
			case DELTA:  // line-to-line
				phaseKV = kVRating;
				break;
			default:  // line-to-neutral
				switch (nPhases) {
				case 2:
				case 3:
					phaseKV = kVRating / DSS.SQRT3;  // assume three phase system
					break;
				default:
					phaseKV = kVRating;
					break;
				}
				break;
			}
			for (i = 0; i < numSteps; i++)
				totalKVAr = totalKVAr + w * C[i] * Math.pow(phaseKV, 2) / 1000.0;
			break;
		case CMATRIX:
			// nothing to do
			break;
		}

		if (doHarmonicRecalc)  // if harmonic specified, compute filter reactance
			for (i = 0; i < numSteps; i++) {
				if (harm[i] != 0.0) {
					XL[i] = (1.0 / (w * C[i])) / Math.pow(harm[i], 2);
				} else {
					XL[i] = 0.0;  // assume 0 harmonic means no filter
				}
				if (R[i] == 0.0) {
					R[i] = XL[i] / 1000.0;
				}
			}

		kVArPerPhase = totalKVAr / nPhases;
		setNormAmps(kVArPerPhase / phaseKV * 1.35);
		setEmergAmps(normAmps * 1.8 / 1.35);
	}

	@Override
	public void calcYPrim() {
		int i;
		CMatrix YPrimTemp, YPrimWork;

		// normally build only Yprim_Shunt, but if there are 2 terminals and bus1 != bus2

		if (isYprimInvalid()) {
			// reallocate YPrim if something has invalidated old allocation
			YPrimShunt = new CMatrix(YOrder);
			YPrimSeries = new CMatrix(YOrder);
			YPrim = new CMatrix(YOrder);
		} else {
			YPrimSeries.clear(); // zero out YPrim
			YPrimShunt.clear();  // zero out YPrim
			YPrim.clear();
		}

		YPrimTemp = isShunt() ? YPrimShunt : YPrimSeries;

		YPrimWork = new CMatrix(YOrder);

		for (i = 0; i < numSteps; i++) {
			if (states[i]) {
				makeYprimWork(YPrimWork, i);
				YPrimTemp.addFrom(YPrimWork);
			}
		}

		// set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
		if (isShunt()) {
			for (i = 0; i < YOrder; i++)
				YPrimSeries.set(i, i, YPrimShunt.get(i, i).mult(1.0e-10));
		}

		YPrim.copyFrom(YPrimTemp);

		super.calcYPrim();

		setYPrimInvalid(false);
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		pw.println("~ " + parentClass.getPropertyName(0) + "=" + getFirstBus());
		pw.println("~ " + parentClass.getPropertyName(1) + "=" + getNextBus());

		pw.println("~ " + parentClass.getPropertyName(2) + "=" + nPhases);
		pw.println("~ " + parentClass.getPropertyName(3) + "=" + getPropertyValue(3));

		pw.println("~ " + parentClass.getPropertyName(4) + "=" + kVRating);
		switch (getConnection()) {
		case WYE:
			pw.println("~ " + parentClass.getPropertyName(5) + "=wye");
			break;
		case DELTA:
			pw.println("~ " + parentClass.getPropertyName(6) + "=delta");
			break;
		}
		if (getCmatrix() != null) {
			pw.print(parentClass.getPropertyName(6) + "= (");
			for (int i = 0; i < nPhases; i++) {
				for (int j = 0; j < i; j++)
					pw.print((Cmatrix[i * nPhases + j] * 1.0e6) + " ");
				if (i != nPhases - 1) pw.print("|");
			}
			pw.println(")");
		}

		pw.println("~ " + parentClass.getPropertyName(7) + "=" + getPropertyValue(7));
		pw.println("~ " + parentClass.getPropertyName(8) + "=" + getPropertyValue(8));
		pw.println("~ " + parentClass.getPropertyName(9) + "=" + getPropertyValue(9));
		pw.println("~ " + parentClass.getPropertyName(10) + "=" + getPropertyValue(10));
		pw.println("~ " + parentClass.getPropertyName(11) + "=" + numSteps);
		pw.println("~ " + parentClass.getPropertyName(12) + "=" + getPropertyValue(12));

		for (int i = Capacitor.NumPropsThisClass; i < parentClass.getNumProperties(); i++) {
			pw.println("~ " + parentClass.getPropertyName(i) + "=" + getPropertyValue(i));
		}

		if (complete) pw.println("specType=" + specType);

		pw.close();
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, getBus(0));
		setPropertyValue(1, getBus(1));
		setPropertyValue(2, "3");
		setPropertyValue(3, "1200");
		setPropertyValue(4, "12.47");
		setPropertyValue(5, "wye");
		setPropertyValue(6, "");
		setPropertyValue(7, "");
		setPropertyValue(8, "0");
		setPropertyValue(9, "0");
		setPropertyValue(10, "0");
		setPropertyValue(11, "1");
		setPropertyValue(12, "1");  // states

		super.initPropertyValues(Capacitor.NumPropsThisClass - 1);

		// override inherited properties
		setPropertyValue(Capacitor.NumPropsThisClass + 0, Util.strReal(getNormAmps(), 0));
		setPropertyValue(Capacitor.NumPropsThisClass + 1, Util.strReal(getEmergAmps(), 0));
		setPropertyValue(Capacitor.NumPropsThisClass + 2, Util.strReal(getFaultRate(), 0));
		setPropertyValue(Capacitor.NumPropsThisClass + 3, Util.strReal(getPctPerm(), 0));
		setPropertyValue(Capacitor.NumPropsThisClass + 4, Util.strReal(getHrsToRepair(), 0));
		clearPropSeqArray();
	}

	@Override
	public void makePosSequence() {
		String s = null;
		double kVArPerPhase, phaseKV, Cs, Cm;
		int i, j;

		if (nPhases > 1) {
			switch (getSpecType()) {
			case KVAR:
				if (nPhases > 1 || connection != Connection.WYE) {
					phaseKV = getKVRating() / DSS.SQRT3;
				} else {
					phaseKV = getKVRating();
				}

				s = "phases=1 " + String.format(" kV=%-.5g kvar=(", phaseKV);

				for (i = 0; i < getNumSteps(); i++) {
					kVArPerPhase = kVArRating[i] / nPhases;
					s = s + String.format(" %-.5g", kVArPerPhase);
				}

				s = s + ")";

				break;
				/* Leave R as specified */
			case CUF:
				s = "phases=1 ";
				break;
			case CMATRIX:
				s = "phases=1 ";
				// r1
				Cs = 0.0;  // avg self
				for (i = 0; i < nPhases; i++)
					Cs = Cs + Cmatrix[i * nPhases + i];
				Cs = Cs / nPhases;

				Cm = 0.0;  // avg mutual
				for (i = 1; i < nPhases; i++)
					for (j = i; j < nPhases; j++)
						Cm = Cm + Cmatrix[i * nPhases + j];
				Cm = Cm / (nPhases * (nPhases - 1.0) / 2.0);

				s = s + String.format(" Cuf=%-.5g", Cs - Cm);
				break;
			}

			Parser.getInstance().setCommand(s);
			edit();
		}

		super.makePosSequence();
	}

	/**
	 * Special case for changing from 1 to more. Automatically make a new bank.
	 */
	public void setNumSteps(int value) {
		int i;
		double stepSize, Rstep, XLstep;

		/* Reallocate all arrays associated with steps */

		if (getNumSteps() != value && value > 0) {
			Rstep = 0.0;
			XLstep = 0.0;

			if (numSteps == 1) {
				/* Save total values to be divided up */
				totalKVAr = kVArRating[0];
				Rstep = R[0] * value;
				XLstep = XL[0] * value;
			}

			// reallocate arrays (must be initialized to nil for first call)
			C = Util.resizeArray(C, value);
			XL = Util.resizeArray(XL, value);
			kVArRating = Util.resizeArray(kVArRating, value);
			R = Util.resizeArray(R, value);
			harm = Util.resizeArray(harm, value);
			states = Util.resizeArray(states, value);

			// special case for numSteps=1

			if (numSteps == 1) {
				switch (specType) {
				case KVAR:  /* We'll make a multi-step bank of same net size as at present */
					stepSize = totalKVAr / value;
					for (i = 0; i < value; i++)
						kVArRating[i] = stepSize;
					break;

				case CUF:  /* We'll make a multi-step bank with all the same as first */
					for (i = 1; i < value; i++)
						C[i] = C[0];  // make same as first step
					break;

				case CMATRIX:  /* We'll make a multi-step bank with all the same as first */
					// nothing to do since all will be the same
					break;
				}

				switch (specType) {
				case KVAR:
					for (i = 0; i < value; i++) R[i] = Rstep;
					for (i = 0; i < value; i++) XL[i] = XLstep;
					break;

				case CUF:
				case CMATRIX:  // make R and XL same as first step
					for (i = 1; i < value; i++) R[i] = R[0];
					for (i = 1; i < value; i++) XL[i] = XL[0];
					break;
				}

				for (i = 0; i < value; i++)
					states[i] = true;  // turn them all on
				setLastStepInService(value);
				for (i = 1; i < value; i++)
					harm[i] = harm[0];  // tune them all the same as first
			}
		}

		setNumSteps(value);
	}

	protected void processHarmonicSpec(String param) {
		Util.interpretDblArray(param, numSteps, getHarm());
		setDoHarmonicRecalc(true);
	}

	protected void processStatesSpec(String param) {
		Util.interpretIntArray(param, numSteps, states);

		lastStepInService = -1;

		for (int i = getNumSteps() - 1; i < 0; i--) {
			if (states[i]) {
				lastStepInService = i;
				break;
			}
		}
	}

	/**
	 * Call this routine only if step is energized.
	 */
	private void makeYprimWork(CMatrix YPrimWork, int iStep) {
		Complex value, value2, Zl = null;
		int i, j, ioffset;
		double w, freqMultiple;
		boolean hasZl;

		YPrimFreq = DSS.activeCircuit.getSolution().getFrequency();
		freqMultiple = YPrimFreq / getBaseFrequency();
		w = DSS.TWO_PI * YPrimFreq;

		hasZl = (R[iStep] + Math.abs(XL[iStep])) > 0.0;

		if (hasZl) Zl = new Complex(R[iStep], XL[iStep] * freqMultiple);

		/* Now, put C into in Yprim matrix */

		switch (getSpecType()) {
		case KVAR:
			value = new Complex(0.0, C[iStep] * w);
			switch (connection) {
			case DELTA:  // line-line
				value2 = value.mult(2.0);
				value = value.neg();
				for (i = 0; i < nPhases; i++) {
					YPrimWork.set(i, i, value2);
					for (j = 0; j < i; j++)
						YPrimWork.setSym(i, j, value);
					// remainder of the matrix is all zero
				}
				break;
			default:  // wye
				if (hasZl) {  // add in ZL
					value = Zl.add(value.inv()).inv();
				}
				value2 = value.neg();
				for (i = 0; i < nPhases; i++) {
					YPrimWork.set(i, i, value);  // elements are only on the diagonals
					YPrimWork.set(i + nPhases, i + nPhases, value);
					YPrimWork.setSym(i, i + nPhases, value2);
				}
				break;
			}
			break;
		case CUF:  // identical to case 1

			value = new Complex(0.0, C[iStep] * w);
			switch (connection) {
			case DELTA:  // line-line
				value2 = value.mult(2.0);
				value = value.neg();
				for (i = 0; i < nPhases; i++) {
					YPrimWork.set(i, i, value2);
					for (j = 0; j < i; j++)
						YPrimWork.setSym(i, j, value);
					// remainder of the matrix is all zero
				}
				break;
			default:  // wye
				if (hasZl) {  // add in ZL
					value = Zl.add(value.inv()).inv();
				}
				value2 = value.neg();
				for (i = 0; i < nPhases; i++) {
					YPrimWork.set(i, i, value);  // elements are only on the diagonals
					YPrimWork.set(i + nPhases, i + nPhases, value);
					YPrimWork.setSym(i, i + nPhases, value2);
				}
				break;
			}
			break;
		case CMATRIX:
			for (i = 0; i < nPhases; i++) {
				ioffset = i * nPhases;
				for (j = 0; j < nPhases; j++) {
					value = new Complex(0.0, Cmatrix[ioffset + j] * w);
					YPrimWork.set(i, j, value);
					YPrimWork.set(i + nPhases, j + nPhases, value);
					value = value.neg();
					YPrimWork.setSym(i, j + nPhases, value);
				}
			}
			break;
		}

		/* Add line reactance for filter reactor, if any */
		if (hasZl) {
			switch (specType) {
			case KVAR:
			case CUF:
				switch (connection) {
				case DELTA:  // line-line
					/* Add a little bit to each phase so it will invert */
					for (i = 0; i < nPhases; i++)
						YPrimWork.set(i, i, YPrimWork.get(i, i).mult(1.000001));
					YPrimWork.invert();
					for (i = 0; i < nPhases; i++) {
						value = Zl.add(YPrimWork.get(i, i));
						YPrimWork.set(i, i, value);
					}
					YPrimWork.invert();
					break;
				default:  /* Wye - just put ZL in series */
					/* Do nothing; already in - see above */
					break;
				}
				break;
			case CMATRIX:
				YPrimWork.invert();
				for (i = 0; i < nPhases; i++) {
					value = Zl.add(YPrimWork.get(i, i));
					YPrimWork.set(i, i, value);
				}
				YPrimWork.invert();
				break;
			}
		}
	}

	@Override
	public String getPropertyValue(int index) {
		double[] temp;
		String val = "";

		switch (index) {  // special cases
		case 0:
			val = getBus(0);
			break;
		case 1:
			val = getBus(1);
			break;
		case 3:
			val = Util.getDSSArray(numSteps, kVArRating);
			break;
		case 7:
			temp = new double[numSteps];
			for (int i = 0; i < numSteps; i++)
				temp[i] = C[i] * 1.0e6;  // to microfarads
			val = Util.getDSSArray(getNumSteps(), temp);
			break;
		case 8:
			val = Util.getDSSArray(getNumSteps(), R);
			break;
		case 10:
			val = Util.getDSSArray(getNumSteps(), XL);
			break;
		case 11:
			val = Util.getDSSArray(getNumSteps(), harm);
			break;
		case 12:
			val = Util.getDSSArray(getNumSteps(), states);
			break;
		default:
			val = super.getPropertyValue(index);
			break;
		}
		return val;
	}

	public boolean addStep() {
		// start with last step in service and see if we can add more; if not return false.
		if (lastStepInService == getNumCustomers() - 1) {
			return false;
		} else {
			lastStepInService += 1;
			states[lastStepInService] = true;
			return true;
		}
	}

	public boolean subtractStep() {
		if (lastStepInService == -1) {
			return false;
		} else {
			states[lastStepInService] = false;
			lastStepInService--;
			if (lastStepInService == -1) {
				return false;
			} else {
				return true;  // signify bank open
			}
		}
	}

	public boolean getState(int idx) {
		return states[idx];
	}

	public void setState(int idx, boolean value) {
		if (states[idx] != value) {
			states[idx] = value;
			setYPrimInvalid(true);
		}
	}

	public int availableSteps() {
		return getNumSteps() - lastStepInService;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	public double[] getC() {
		return C;
	}

	public double[] getKVArRating() {
		return kVArRating;
	}

	public double[] getHarm() {
		return harm;
	}

	public double getKVRating() {
		return kVRating;
	}

	public int getNumSteps() {
		return numSteps;
	}

	public double[] getCmatrix() {
		return Cmatrix;
	}

	public Connection getConnection() {
		return connection;
	}

	public double[] getXL() {
		return XL;
	}

	public double[] getR() {
		return R;
	}

	public boolean[] getStates() {
		return states;
	}

	public double getTotalKVAr() {
		return totalKVAr;
	}

	public CapacitorSpecType getSpecType() {
		return specType;
	}

	public void setKVRating(double kVRating) {
		this.kVRating = kVRating;
	}

	public void setLastStepInService(int lastStepInService) {
		this.lastStepInService = lastStepInService;
	}

	public void setCmatrix(double[] cmatrix) {
		Cmatrix = cmatrix;
	}

	public void setDoHarmonicRecalc(boolean doHarmonicRecalc) {
		this.doHarmonicRecalc = doHarmonicRecalc;
	}

	public void setSpecType(CapacitorSpecType specType) {
		this.specType = specType;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
