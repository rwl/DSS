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

	private double MinAmps;
	private boolean IsTemporary, Cleared, Is_ON;
	private double On_Time;
	private double RandomMult;

	/* single G per phase (line rating) if Gmatrix not specified */
	protected double G;
	/* If not null then overrides G */
	protected double[] Gmatrix;

	/* per unit stddev */
	protected double Stddev;
	protected int SpecType;

	public FaultObjImpl(DSSClass ParClass, String FaultName) {
		super(ParClass);

		this.DSSObjType = ParClass.getDSSClassType(); //FAULTOBJECT + NON_PCPD_ELEM;  // Only in Fault object class
		setName(FaultName.toLowerCase());

		// default to SLG fault
		setNPhases(1);  // directly set conds and phases
		setNConds(1);
		setNTerms(2);   // force allocation of terminals and conductors

		setBus(2, (getBus(1) + ".0"));  // Default to grounded   TODO Check zero based indexing
		setShunt(true);

		this.Gmatrix       = null;
		this.G             = 10000.0;
		this.SpecType      = 1;  // G  2=Gmatrix

		this.MinAmps       = 5.0;
		this.IsTemporary   = false;
		this.Cleared       = false;
		this.Is_ON         = true;
		this.On_Time       = 0.0;  // Always enabled at the start of a solution.


		this.RandomMult = 1;

		setNormAmps(0.0);
		setEmergAmps(0.0);
		setFaultRate(0.0);
		setPctPerm(100.0);
		setHrsToRepair(0.0);

		initPropertyValues(0);

		this.Yorder = this.nTerms * this.nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		// Nothing to do
	}

//	private double cube(double x) {
//		return x * x * x;
//	}

	/**
	 * Called from solveMontefault procedure.
	 */
	public void randomize() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		switch (sol.getRandomType()) {
		case DSSGlobals.GAUSSIAN:
			RandomMult = MathUtil.gauss(1.0, Stddev);
			break;
		case DSSGlobals.UNIFORM:
			RandomMult = Math.random();
			break;
		case DSSGlobals.LOGNORMAL:
			RandomMult = MathUtil.quasiLognormal(1.0);
			break;
		default:
			RandomMult = 1.0;
			break;
		}

		// Give the multiplier some skew to approximate more uniform/Gaussian current distributions
		// RandomMult = cube(RandomMult);   removed 12/7/04

		setYprimInvalid(true);    // force rebuilding of matrix
	}

	@Override
	public void calcYPrim() {
		Complex Value, Value2;
		int i, j, ioffset;

		CMatrix YPrimTemp;

		if (isYprimInvalid()) {  // Reallocate YPrim if something has invalidated old allocation
			if (YPrim_Series != null)
				YPrim_Series = null;
			YPrim_Series = new CMatrixImpl(Yorder);
			if (YPrim_Shunt != null)
				YPrim_Shunt = null;
			YPrim_Shunt = new CMatrixImpl(Yorder);
			if (YPrim != null)
				YPrim = null;
			YPrim = new CMatrixImpl(Yorder);
		} else {
			YPrim_Series.clear(); // zero out YPrim
			YPrim_Shunt.clear(); // zero out YPrim
			YPrim.clear();
		}


		if (isShunt()) {
			YPrimTemp = YPrim_Shunt;
		} else {
			YPrimTemp = YPrim_Series;
		}

		// make sure randommult is 1.0 if not solution mode MonteFault

		if (DSSGlobals.getInstance().getActiveCircuit().getSolution().getMode() != Dynamics.MONTEFAULT)
			RandomMult = 1.0;

		if (RandomMult == 0.0)
			RandomMult = 0.000001;

		/* Now, put in Yprim matrix */

		/* If the fault is not ON, the set zero conductance */

		switch (SpecType) {
		case 1:

			if (Is_ON) {
				Value = new Complex(G / RandomMult, 0.0);
			} else {
				Value = Complex.ZERO;
			}
			Value2 = Value.negate();
			for (i = 0; i < nPhases; i++) {
				YPrimTemp.setElement(i, i, Value);  // Elements are only on the diagonals
				YPrimTemp.setElement(i + nPhases, i + nPhases, Value);
				YPrimTemp.setElemSym(i, i + nPhases, Value2);
			}
			break;
		case 2:  // G matrix specified
			for (i = 0; i < nPhases; i++) {
				ioffset = (i - 1) * nPhases;
				for (j = 0; j < nPhases; j++) {
					if (Is_ON) {
						Value = new Complex(Gmatrix[(ioffset + j)] / RandomMult, 0.0);
					} else {
						Value = Complex.ZERO;
					}
					YPrimTemp.setElement(i, j, Value);
					YPrimTemp.setElement(i + nPhases, j + nPhases, Value);
					Value = Value.negate();
					YPrimTemp.setElemSym(i, j + nPhases, Value);
				}
			}
			break;
		}

		YPrim.copyFrom(YPrimTemp);

		super.calcYPrim();
		setYprimInvalid(false);
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		int i, j;

		super.dumpProperties(F, Complete);

		DSSClass pc = getParentClass();

		F.println("~ " + pc.getPropertyName()[0] + "=" + getFirstBus());
		F.println("~ " + pc.getPropertyName()[1] + "=" + getNextBus());

		F.println("~ " + pc.getPropertyName()[2] + "=" + nPhases);
		F.println("~ " + pc.getPropertyName()[3] + "=" + (1.0 / G));
		F.println("~ " + pc.getPropertyName()[4] + "=" + (Stddev * 100.0));
		if (Gmatrix != null) {
			F.print("~ " + pc.getPropertyName()[5] + "= (");
			for (i = 0; i < nPhases; i++) {
				for (j = 0; j < i; j++)
					F.print(Gmatrix[(i - 1) * nPhases + j] + " ");
				if (i != nPhases)
					F.print("|");
			}
			F.println(")");
		}
		F.println("~ " + pc.getPropertyName()[6] + "=" + On_Time);
		if (IsTemporary) {
			F.println("~ " + pc.getPropertyName()[7] + "= Yes");
		} else {
			F.println("~ " + pc.getPropertyName()[7] + "= No");
		}
		F.println("~ " + pc.getPropertyName()[8] + "=" + MinAmps);


		for (i = Fault.NumPropsThisClass; i < pc.getNumProperties(); i++)
			F.println("~ " + pc.getPropertyName()[i] + "=" + getPropertyValue(i));

		if (Complete)
			F.println("// SpecType=" + SpecType);
	}

	public void checkStatus(int ControlMode) {

		switch (ControlMode) {
		case DSSGlobals.CTRLSTATIC:  /* Leave it however it is defined by other processes */
			break;
		case DSSGlobals.EVENTDRIVEN:
			if (!Is_ON) {
				/* Turn it on unless it has been previously cleared */
				if ((Utilities.presentTimeInSec() > On_Time) && !Cleared) {
					Is_ON = true;
					setYprimInvalid(true);
					Utilities.appendToEventLog("Fault." + getName(), "**APPLIED**");
				}
			} else {
				if (IsTemporary)
					if (!faultStillGoing()) {
						Is_ON = false;
						Cleared = true;
						setYprimInvalid(true);
						Utilities.appendToEventLog("Fault." + getName(), "**CLEARED**");
					}
			}
			break;
		case DSSGlobals.TIMEDRIVEN:  // Identical to event driven case.
			if (!Is_ON) {
				/* Turn it on unless it has been previously cleared */
				if ((Utilities.presentTimeInSec() > On_Time) && !Cleared) {
					Is_ON = true;
					setYprimInvalid(true);
					Utilities.appendToEventLog("Fault." + getName(), "**APPLIED**");
				}
			} else {
				if (IsTemporary)
					if (!faultStillGoing()) {
						Is_ON = false;
						Cleared = true;
						setYprimInvalid(true);
						Utilities.appendToEventLog("Fault." + getName(), "**CLEARED**");
					}
			}
			break;
		}
	}

	private boolean faultStillGoing() {
		computeIterminal();
		for (int i = 0; i < nPhases; i++)
			if (Iterminal[i].abs() > MinAmps)
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

		// Override Inherited Properties
		PropertyValue[Fault.NumPropsThisClass + 1] = "0";  // NormAmps   TODO Check zero based indexing
		PropertyValue[Fault.NumPropsThisClass + 2] = "0";  // EmergAmps
		PropertyValue[Fault.NumPropsThisClass + 3] = "0";  // Fault rate
		PropertyValue[Fault.NumPropsThisClass + 4] = "0";  // Pct Perm
		PropertyValue[Fault.NumPropsThisClass + 5] = "0";  // Hrs to repair
	}

	@Override
	public String getPropertyValue(int Index) {
		String Result;

		switch (Index) {
		case 5:
			Result = "(";
			if (Gmatrix != null) {
				for (int i = 0; i < nPhases; i++) {
					for (int j = 0; j < i; j++)
						Result = Result + String.format("%-g", Gmatrix[(i - 1) * nPhases + j]) + " ";
				if (i < nPhases)
					Result = Result + "|";
				}
			}

			Result = Result + ")";
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}

		return Result;
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
		return MinAmps;
	}

	public void setMinAmps(double minAmps) {
		MinAmps = minAmps;
	}

	public boolean isIsTemporary() {
		return IsTemporary;
	}

	public void setIsTemporary(boolean isTemporary) {
		IsTemporary = isTemporary;
	}

	public boolean isCleared() {
		return Cleared;
	}

	public void setCleared(boolean cleared) {
		Cleared = cleared;
	}

	public boolean isIs_ON() {
		return Is_ON;
	}

	public void setIs_ON(boolean is_ON) {
		Is_ON = is_ON;
	}

	public double getOn_Time() {
		return On_Time;
	}

	public void setOn_Time(double on_Time) {
		On_Time = on_Time;
	}

	public double getRandomMult() {
		return RandomMult;
	}

	public void setRandomMult(double randomMult) {
		RandomMult = randomMult;
	}

	public double getG() {
		return G;
	}

	public void setG(double g) {
		G = g;
	}

	public double[] getGmatrix() {
		return Gmatrix;
	}

	public void setGmatrix(double[] gmatrix) {
		Gmatrix = gmatrix;
	}

	public double getStddev() {
		return Stddev;
	}

	public void setStddev(double stddev) {
		Stddev = stddev;
	}

	public int getSpecType() {
		return SpecType;
	}

	public void setSpecType(int specType) {
		SpecType = specType;
	}

}
