package com.ncond.dss.delivery.impl;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.delivery.Fault;
import com.ncond.dss.delivery.FaultObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.Dynamics;
import com.ncond.dss.shared.impl.CMatrixImpl;
import com.ncond.dss.shared.impl.MathUtil;

public class FaultObjImpl extends PDElementImpl implements FaultObj {

	private double minAmps;
	private boolean isTemporary, cleared, isOn;
	private double onTime;
	private double randomMult;

	/* Single G per phase (line rating) if GMatrix not specified */
	protected double G;
	/* If not null then overrides G */
	protected double[] GMatrix;

	/* Per unit std dev */
	protected double stdDev;
	protected int specType;

	public FaultObjImpl(DSSClass parClass, String faultName) {
		super(parClass);

		objType = parClass.getDSSClassType(); //FAULTOBJECT + NON_PCPD_ELEM;  // only in fault object class
		setName(faultName.toLowerCase());

		// default to SLG fault
		setNumPhases(1);  // directly set conds and phases
		nConds = 1;
		setNumTerms(2);   // force allocation of terminals and conductors

		setBus(1, (getBus(0) + ".0"));  // default to grounded
		setShunt(true);

		GMatrix  = null;
		G        = 10000.0;
		specType = 1;  // G 2=Gmatrix

		minAmps     = 5.0;
		isTemporary = false;
		cleared     = false;
		isOn        = true;
		onTime      = 0.0;  // always enabled at the start of a solution

		randomMult = 1;

		normAmps  = 0.0;
		emergAmps = 0.0;
		faultRate = 0.0;
		pctPerm   = 100.0;
		hrsToRepair = 0.0;

		initPropertyValues(0);

		YOrder = nTerms * nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		// nothing to do
	}

	/**
	 * Called from solveMontefault procedure.
	 */
	public void randomize() {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		switch (sol.getRandomType()) {
		case DSS.GAUSSIAN:
			randomMult = MathUtil.gauss(1.0, stdDev);
			break;
		case DSS.UNIFORM:
			randomMult = Math.random();
			break;
		case DSS.LOGNORMAL:
			randomMult = MathUtil.quasiLognormal(1.0);
			break;
		default:
			randomMult = 1.0;
			break;
		}

		// give the multiplier some skew to approximate more uniform/Gaussian current distributions
		// randomMult = cube(randomMult);   removed 12/7/04

		setYPrimInvalid(true);  // force rebuilding of matrix
	}

	@Override
	public void calcYPrim() {
		Complex value, value2;
		int i, j, ioffset;

		CMatrix YPrimTemp;

		if (isYprimInvalid()) {  // reallocate YPrim if something has invalidated old allocation
			YPrimSeries = new CMatrixImpl(YOrder);
			YPrimShunt = new CMatrixImpl(YOrder);
			YPrim = new CMatrixImpl(YOrder);
		} else {
			YPrimSeries.clear();  // zero out YPrim
			YPrimShunt.clear();   // zero out YPrim
			YPrim.clear();
		}


		if (isShunt()) {
			YPrimTemp = YPrimShunt;
		} else {
			YPrimTemp = YPrimSeries;
		}

		// make sure randomMult is 1.0 if not solution mode MonteFault

		if (DSS.activeCircuit.getSolution().getMode() != Dynamics.MONTEFAULT)
			randomMult = 1.0;

		if (randomMult == 0.0)
			randomMult = 0.000001;

		/* Now, put in Yprim matrix */

		/* If the fault is not on, the set zero conductance */
		switch (specType) {
		case 1:

			if (isOn) {
				value = new Complex(G / randomMult, 0.0);
			} else {
				value = Complex.ZERO;
			}
			value2 = value.negate();
			for (i = 0; i < nPhases; i++) {
				YPrimTemp.set(i, i, value);  // elements are only on the diagonals
				YPrimTemp.set(i + nPhases, i + nPhases, value);
				YPrimTemp.setSym(i, i + nPhases, value2);
			}
			break;
		case 2:  // G matrix specified
			for (i = 0; i < nPhases; i++) {
				ioffset = i * nPhases;
				for (j = 0; j < nPhases; j++) {
					if (isOn) {
						value = new Complex(GMatrix[ioffset + j] / randomMult, 0.0);
					} else {
						value = Complex.ZERO;
					}
					YPrimTemp.set(i, j, value);
					YPrimTemp.set(i + nPhases, j + nPhases, value);
					value = value.negate();
					YPrimTemp.setSym(i, j + nPhases, value);
				}
			}
			break;
		}

		YPrim.copyFrom(YPrimTemp);

		super.calcYPrim();
		setYPrimInvalid(false);
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		int i, j;

		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		DSSClass pc = getParentClass();

		pw.println("~ " + pc.getPropertyName(0) + "=" + getFirstBus());
		pw.println("~ " + pc.getPropertyName(1) + "=" + getNextBus());

		pw.println("~ " + pc.getPropertyName(2) + "=" + nPhases);
		pw.println("~ " + pc.getPropertyName(3) + "=" + (1.0 / G));
		pw.println("~ " + pc.getPropertyName(4) + "=" + (stdDev * 100.0));
		if (GMatrix != null) {
			pw.print("~ " + pc.getPropertyName(5) + "= (");
			for (i = 0; i < nPhases; i++) {
				for (j = 0; j < i; j++)
					pw.print(GMatrix[i * nPhases + j] + " ");
				if (i != nPhases)
					pw.print("|");
			}
			pw.println(")");
		}
		pw.println("~ " + pc.getPropertyName(6) + "=" + onTime);
		if (isTemporary) {
			pw.println("~ " + pc.getPropertyName(7) + "= Yes");
		} else {
			pw.println("~ " + pc.getPropertyName(7) + "= No");
		}
		pw.println("~ " + pc.getPropertyName(8) + "=" + minAmps);


		for (i = Fault.NumPropsThisClass; i < pc.getNumProperties(); i++)
			pw.println("~ " + pc.getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete)
			pw.println("// SpecType=" + specType);

		pw.close();
	}

	public void checkStatus(int controlMode) {

		switch (controlMode) {
		case DSS.CTRLSTATIC:  /* Leave it however it is defined by other processes */
			break;
		case DSS.EVENTDRIVEN:
			if (!isOn) {
				/* Turn it on unless it has been previously cleared */
				if (Util.presentTimeInSec() > onTime && !cleared) {
					isOn = true;
					setYPrimInvalid(true);
					Util.appendToEventLog("Fault." + getName(), "**APPLIED**");
				}
			} else {
				if (isTemporary)
					if (!faultStillGoing()) {
						isOn = false;
						cleared = true;
						setYPrimInvalid(true);
						Util.appendToEventLog("Fault." + getName(), "**CLEARED**");
					}
			}
			break;
		case DSS.TIMEDRIVEN:  // identical to event driven case.
			if (!isOn) {
				/* Turn it on unless it has been previously cleared */
				if (Util.presentTimeInSec() > onTime && !cleared) {
					isOn = true;
					setYPrimInvalid(true);
					Util.appendToEventLog("Fault." + getName(), "**APPLIED**");
				}
			} else {
				if (isTemporary)
					if (!faultStillGoing()) {
						isOn = false;
						cleared = true;
						setYPrimInvalid(true);
						Util.appendToEventLog("Fault." + getName(), "**CLEARED**");
					}
			}
			break;
		}
	}

	private boolean faultStillGoing() {
		computeITerminal();
		for (int i = 0; i < nPhases; i++)
			if (ITerminal[i].abs() > minAmps)
				return true;
		return false;
	}

	public void reset() {
		setCleared(false);
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		setPropertyValue(0, getBus(0));
		setPropertyValue(1, getBus(1));
		setPropertyValue(2, "1");
		setPropertyValue(3, "0.0001");
		setPropertyValue(4, "0");
		setPropertyValue(5, "");
		setPropertyValue(6, "0.0");
		setPropertyValue(7, "no");
		setPropertyValue(8, "5.0");

		super.initPropertyValues(Fault.NumPropsThisClass - 1);

		// override inherited properties
		setPropertyValue(Fault.NumPropsThisClass + 0, "0");  // normAmps
		setPropertyValue(Fault.NumPropsThisClass + 1, "0");  // emergAmps
		setPropertyValue(Fault.NumPropsThisClass + 2, "0");  // faultRate
		setPropertyValue(Fault.NumPropsThisClass + 3, "0");  // pctPerm
		setPropertyValue(Fault.NumPropsThisClass + 4, "0");  // hrsToRepair
	}

	@Override
	public String getPropertyValue(int index) {
		String result;

		switch (index) {
		case 5:
			result = "(";
			if (GMatrix != null) {
				for (int i = 0; i < nPhases; i++) {
					for (int j = 0; j < i; j++)
						result = result + String.format("%-g", GMatrix[i * nPhases + j]) + " ";
					if (i < nPhases - 1)
						result = result + "|";
				}
			}
			result = result + ")";
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}

		return result;
	}

	@Override
	public void makePosSequence() {
		if (nPhases != 1) {
			Parser.getInstance().setCmdString("Phases=1");
			edit();
		}
		super.makePosSequence();
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int injCurrents() {
		throw new UnsupportedOperationException();
	}

	// FIXME Private members in OpenDSS

	public double getMinAmps() {
		return minAmps;
	}

	public void setMinAmps(double min) {
		minAmps = min;
	}

	public boolean isTemporary() {
		return isTemporary;
	}

	public void setTemporary(boolean temp) {
		isTemporary = temp;
	}

	public boolean isCleared() {
		return cleared;
	}

	public void setCleared(boolean value) {
		cleared = value;
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean on) {
		isOn = on;
	}

	public double getOnTime() {
		return onTime;
	}

	public void setOnTime(double value) {
		onTime = value;
	}

	public double getRandomMult() {
		return randomMult;
	}

	public void setRandomMult(double mult) {
		randomMult = mult;
	}

	public double getG() {
		return G;
	}

	public void setG(double g) {
		G = g;
	}

	public double[] getGMatrix() {
		return GMatrix;
	}

	public void setGMatrix(double[] value) {
		GMatrix = value;
	}

	public double getStdDev() {
		return stdDev;
	}

	public void setStdDev(double value) {
		stdDev = value;
	}

	public int getSpecType() {
		return specType;
	}

	public void setSpecType(int type) {
		specType = type;
	}

}
