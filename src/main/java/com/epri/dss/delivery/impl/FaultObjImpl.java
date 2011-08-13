package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Fault;
import com.epri.dss.delivery.FaultObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.Dynamics;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

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

		this.DSSObjType = parClass.getDSSClassType(); //FAULTOBJECT + NON_PCPD_ELEM;  // only in fault object class
		setName(faultName.toLowerCase());

		// default to SLG fault
		setNPhases(1);  // directly set conds and phases
		this.nConds = 1;
		setNTerms(2);   // force allocation of terminals and conductors

		setBus(2, (getBus(1) + ".0"));  // default to grounded   TODO Check zero based indexing
		setShunt(true);

		this.GMatrix       = null;
		this.G             = 10000.0;
		this.specType      = 1;  // G 2=Gmatrix

		this.minAmps       = 5.0;
		this.isTemporary   = false;
		this.cleared       = false;
		this.isOn         = true;
		this.onTime       = 0.0;  // always enabled at the start of a solution


		this.randomMult = 1;

		this.NormAmps  = 0.0;
		this.EmergAmps = 0.0;
		this.FaultRate = 0.0;
		this.PctPerm   = 100.0;
		this.HrsToRepair = 0.0;

		initPropertyValues(0);

		this.YOrder = this.nTerms * this.nConds;
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
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		switch (sol.getRandomType()) {
		case DSSGlobals.GAUSSIAN:
			randomMult = MathUtil.gauss(1.0, stdDev);
			break;
		case DSSGlobals.UNIFORM:
			randomMult = Math.random();
			break;
		case DSSGlobals.LOGNORMAL:
			randomMult = MathUtil.quasiLognormal(1.0);
			break;
		default:
			randomMult = 1.0;
			break;
		}

		// give the multiplier some skew to approximate more uniform/Gaussian current distributions
		// RandomMult = cube(RandomMult);   removed 12/7/04

		setYPrimInvalid(true);  // force rebuilding of matrix
	}

	@Override
	public void calcYPrim() {
		Complex value, value2;
		int i, j, ioffset;

		CMatrix YPrimTemp;

		if (isYprimInvalid()) {  // reallocate YPrim if something has invalidated old allocation
			if (YPrimSeries != null)
				YPrimSeries = null;
			YPrimSeries = new CMatrixImpl(YOrder);
			if (YPrimShunt != null)
				YPrimShunt = null;
			YPrimShunt = new CMatrixImpl(YOrder);
			if (YPrim != null)
				YPrim = null;
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

		if (DSSGlobals.getInstance().getActiveCircuit().getSolution().getMode() != Dynamics.MONTEFAULT)
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
				YPrimTemp.setElement(i, i, value);  // elements are only on the diagonals
				YPrimTemp.setElement(i + nPhases, i + nPhases, value);
				YPrimTemp.setElemSym(i, i + nPhases, value2);
			}
			break;
		case 2:  // G matrix specified
			for (i = 0; i < nPhases; i++) {
				ioffset = (i - 1) * nPhases;
				for (j = 0; j < nPhases; j++) {
					if (isOn) {
						value = new Complex(GMatrix[(ioffset + j)] / randomMult, 0.0);
					} else {
						value = Complex.ZERO;
					}
					YPrimTemp.setElement(i, j, value);
					YPrimTemp.setElement(i + nPhases, j + nPhases, value);
					value = value.negate();
					YPrimTemp.setElemSym(i, j + nPhases, value);
				}
			}
			break;
		}

		YPrim.copyFrom(YPrimTemp);

		super.calcYPrim();
		setYPrimInvalid(false);
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		int i, j;

		super.dumpProperties(f, complete);

		DSSClass pc = getParentClass();

		f.println("~ " + pc.getPropertyName()[0] + "=" + getFirstBus());
		f.println("~ " + pc.getPropertyName()[1] + "=" + getNextBus());

		f.println("~ " + pc.getPropertyName()[2] + "=" + nPhases);
		f.println("~ " + pc.getPropertyName()[3] + "=" + (1.0 / G));
		f.println("~ " + pc.getPropertyName()[4] + "=" + (stdDev * 100.0));
		if (GMatrix != null) {
			f.print("~ " + pc.getPropertyName()[5] + "= (");
			for (i = 0; i < nPhases; i++) {
				for (j = 0; j < i; j++)
					f.print(GMatrix[(i - 1) * nPhases + j] + " ");
				if (i != nPhases)
					f.print("|");
			}
			f.println(")");
		}
		f.println("~ " + pc.getPropertyName()[6] + "=" + onTime);
		if (isTemporary) {
			f.println("~ " + pc.getPropertyName()[7] + "= Yes");
		} else {
			f.println("~ " + pc.getPropertyName()[7] + "= No");
		}
		f.println("~ " + pc.getPropertyName()[8] + "=" + minAmps);


		for (i = Fault.NumPropsThisClass; i < pc.getNumProperties(); i++)
			f.println("~ " + pc.getPropertyName()[i] + "=" + getPropertyValue(i));

		if (complete)
			f.println("// SpecType=" + specType);
	}

	public void checkStatus(int controlMode) {

		switch (controlMode) {
		case DSSGlobals.CTRLSTATIC:  /* Leave it however it is defined by other processes */
			break;
		case DSSGlobals.EVENTDRIVEN:
			if (!isOn) {
				/* Turn it on unless it has been previously cleared */
				if ((Utilities.presentTimeInSec() > onTime) && !cleared) {
					isOn = true;
					setYPrimInvalid(true);
					Utilities.appendToEventLog("Fault." + getName(), "**APPLIED**");
				}
			} else {
				if (isTemporary)
					if (!faultStillGoing()) {
						isOn = false;
						cleared = true;
						setYPrimInvalid(true);
						Utilities.appendToEventLog("Fault." + getName(), "**CLEARED**");
					}
			}
			break;
		case DSSGlobals.TIMEDRIVEN:  // identical to event driven case.
			if (!isOn) {
				/* Turn it on unless it has been previously cleared */
				if ((Utilities.presentTimeInSec() > onTime) && !cleared) {
					isOn = true;
					setYPrimInvalid(true);
					Utilities.appendToEventLog("Fault." + getName(), "**APPLIED**");
				}
			} else {
				if (isTemporary)
					if (!faultStillGoing()) {
						isOn = false;
						cleared = true;
						setYPrimInvalid(true);
						Utilities.appendToEventLog("Fault." + getName(), "**CLEARED**");
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

		PropertyValue[0] = getBus(1);  // TODO Check zero based indexing
		PropertyValue[1] = getBus(2);
		PropertyValue[2] = "1";
		PropertyValue[3] = "0.0001";
		PropertyValue[4] = "0";
		PropertyValue[5] = "";
		PropertyValue[6] = "0.0";
		PropertyValue[7] = "no";
		PropertyValue[8] = "5.0";

		super.initPropertyValues(Fault.NumPropsThisClass);

		// override inherited properties
		PropertyValue[Fault.NumPropsThisClass + 1] = "0";  // normAmps   TODO Check zero based indexing
		PropertyValue[Fault.NumPropsThisClass + 2] = "0";  // emergAmps
		PropertyValue[Fault.NumPropsThisClass + 3] = "0";  // faultRate
		PropertyValue[Fault.NumPropsThisClass + 4] = "0";  // pctPerm
		PropertyValue[Fault.NumPropsThisClass + 5] = "0";  // hrsToRepair
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
						result = result + String.format("%-g", GMatrix[(i - 1) * nPhases + j]) + " ";
				if (i < nPhases)
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
