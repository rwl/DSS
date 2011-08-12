package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.Capacitor;
import com.epri.dss.delivery.CapacitorObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;

public class CapacitorObjImpl extends PDElementImpl implements CapacitorObj {

	private double[] C,
		XL,
		kvarrating,
		R,
		Harm;  // single C per phase (line rating) if Cmatrix not specified
	private int[] States;

	private double totalkvar,
		kvrating;
	private int NumSteps,
		LastStepInService;
	private double[] Cmatrix;  // if not nil then overrides C

	private boolean doHarmonicRecalc;

	private int SpecType;

	/* 0 or 1 for wye (default) or delta, respectively */
	protected int Connection;

	public CapacitorObjImpl(DSSClass ParClass, String CapacitorName) {
		super(ParClass);

		setName(CapacitorName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		setNPhases(3);  // directly set conds and phases
		this.nConds = 3;
		setNTerms(2);  // force allocation of terminals and conductors

		setBus(1, (getBus(0) + ".0.0.0"));  // default to grounded wye

		this.IsShunt = true;  // defaults to shunt capacitor

		this.Cmatrix = null;

		/* Initialize these pointers to nil so reallocmem will work reliably. */
		this.C = null;
		this.XL = null;
		this.kvarrating = null;
		this.R = null;
		this.Harm = null;
		this.States = null;

		setNumSteps(1);  // initial allocation for the arrays, too
		this.LastStepInService = this.NumSteps;

		Utilities.initDblArray(this.NumSteps, this.R, 0.0);
		Utilities.initDblArray(this.NumSteps, this.XL, 0.0);
		Utilities.initDblArray(this.NumSteps, this.Harm, 0.0);
		Utilities.initDblArray(this.NumSteps, this.kvarrating, 1200.0);

		this.States[0] = 1;

		this.kvrating = 12.47;
		Utilities.initDblArray(this.NumSteps, this.C,
				1.0 / (DSSGlobals.TwoPi * baseFrequency * Math.pow(kvrating, 2) * 1000.0 / this.kvarrating[0]));

		this.Connection = 0;   // 0 or 1 for wye (default) or delta, respectively
		this.SpecType = 1; // 1=kvar, 2=Cuf, 3=Cmatrix

		this.NormAmps = this.kvarrating[0] * DSSGlobals.SQRT3 / this.kvrating * 1.35;  // 135%
		this.EmergAmps = getNormAmps() * 1.8 / 1.35;  // 180%
		this.FaultRate = 0.0005;
		this.PctPerm = 100.0;
		this.HrsToRepair = 3.0;
		this.YOrder = this.nTerms * this.nConds;

		this.doHarmonicRecalc = false;

		recalcElementData();

		initPropertyValues(0);
	}

	@Override
	public void recalcElementData() {
		double KvarPerPhase, PhasekV, w;
		int i;

		totalkvar = 0.0;
		PhasekV = 1.0;
		w = DSSGlobals.TwoPi * baseFrequency;
		switch (SpecType) {
		case 1:// kvar
			switch (Connection) {
			case 1:  // line-to-line
				PhasekV = kvrating;
				break;
			default:  // line-to-neutral
				switch (nPhases) {
				case 2:
					PhasekV = kvrating / DSSGlobals.SQRT3;  // assume three phase system
					break;
				case 3:
					PhasekV = kvrating / DSSGlobals.SQRT3;
					break;
				default:
					PhasekV = kvrating;
					break;
				}
				break;
			}
			for (i = 0; i < NumSteps; i++)
				C[i] = 1.0 / (w * Math.pow(PhasekV, 2) * 1000.0 / (kvarrating[0] / nPhases));
			for (i = 0; i < NumSteps; i++)
				totalkvar = totalkvar + kvarrating[i];
			break;
		case 2:  // Cuf
			switch (Connection) {
			case 1:  // line-to-line
				PhasekV = kvrating;
				break;
			default:  // line-to-neutral
				switch (nPhases) {
				case 2:
					PhasekV = kvrating / DSSGlobals.SQRT3;  // assume three phase system
					break;
				case 3:
					PhasekV = kvrating / DSSGlobals.SQRT3;
					break;
				default:
					PhasekV = kvrating;
					break;
				}
				break;
			}
			for (i = 0; i < NumSteps; i++)
				totalkvar = totalkvar + w * C[i] * Math.pow(PhasekV, 2) / 1000.0;
			break;
		case 3:  // Cmatrix
			// Nothing to do
			break;
		}

		if (doHarmonicRecalc)  // if harmonic specified, compute filter reactance
			for (i = 0; i < NumSteps; i++) {
				if (Harm[i] != 0.0) {
					XL[i] = (1.0 / (w * C[i])) / Math.pow(Harm[i], 2);
				} else {
					XL[i] = 0.0;  // assume 0 harmonic means no filter
				}
				if (R[i] == 0.0)
					R[i] = XL[i] / 1000.0;
			}

		KvarPerPhase = totalkvar / nPhases;
		setNormAmps(KvarPerPhase / PhasekV * 1.35);
		setEmergAmps(getNormAmps() * 1.8 / 1.35);
	}

	@Override
	public void calcYPrim() {
		int i;
		CMatrix YPrimTemp, YPrimWork;

		// normally build only Yprim_Shunt, but if there are 2 terminals and bus1 != bus2

		if (isYprimInvalid()) {
			// reallocate YPrim if something has invalidated old allocation
			if (YPrimShunt != null)
				YPrimShunt = null;
			YPrimShunt = new CMatrixImpl(YOrder);
			if (YPrimSeries != null)
				YPrimSeries = null;
			YPrimSeries = new CMatrixImpl(YOrder);
			if (YPrim != null)
				YPrim = null;
			YPrim = new CMatrixImpl(YOrder);
		} else {
			YPrimSeries.clear(); // zero out YPrim
			YPrimShunt.clear();  // zero out YPrim
			YPrim.clear();
		}

		if (isShunt()) {
			YPrimTemp = YPrimShunt;
		} else {
			YPrimTemp = YPrimSeries;
		}

		YPrimWork = new CMatrixImpl(YOrder);

		for (i = 0; i < NumSteps; i++)
			if (States[i] == 1) {
				makeYprimWork(YPrimWork, i);
				YPrimTemp.addFrom(YPrimWork);
			}

		YPrimWork = null;

		// set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
		if (isShunt())
			for (i = 0; i < YOrder; i++)
				YPrimSeries.setElement(i, i, YPrimShunt.getElement(i, i).multiply(1.0e-10));

		YPrim.copyFrom(YPrimTemp);

		super.calcYPrim();

		setYPrimInvalid(false);
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		F.println("~ " + ParentClass.getPropertyName()[0] + "=" + getFirstBus());
		F.println("~ " + ParentClass.getPropertyName()[1] + "=" + getNextBus());

		F.println("~ " + ParentClass.getPropertyName()[2] + "=" + getNPhases());
		F.println("~ " + ParentClass.getPropertyName()[3] + "=" + getPropertyValue(3));

		F.println("~ " + ParentClass.getPropertyName()[4] + "=" + getKvrating());
		switch (getConnection()) {
		case 0:
			F.println("~ " + ParentClass.getPropertyName()[5] + "=wye");
			break;
		case 1:
			F.println("~ " + ParentClass.getPropertyName()[6] + "=delta");
			break;
		}
		if (getCmatrix() != null) {
			F.print(ParentClass.getPropertyName()[6] + "= (");
			for (int i = 0; i < getNPhases(); i++) {
				for (int j = 0; j < i; j++) {
					// TODO: Check zero based indexing
					F.print((getCmatrix()[(i - 1) * getNPhases() + j] * 1.0e6) + " ");
				}
				if (i != getNPhases())
					F.print("|");
			}
			F.println(")");
		}

		F.println("~ " + ParentClass.getPropertyName()[7] + "=" + getPropertyValue(7));
		F.println("~ " + ParentClass.getPropertyName()[8] + "=" + getPropertyValue(8));
		F.println("~ " + ParentClass.getPropertyName()[9] + "=" + getPropertyValue(9));
		F.println("~ " + ParentClass.getPropertyName()[10] + "=" + getPropertyValue(10));
		F.println("~ " + ParentClass.getPropertyName()[11] + "=" + getNumSteps());
		F.println("~ " + ParentClass.getPropertyName()[12] + "=" + getPropertyValue(12));

		for (int i = Capacitor.NumPropsThisClass + 1; i < ParentClass.getNumProperties(); i++) {  // TODO: Check zero based indexing
			F.println("~ " + ParentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
		}

		if (Complete) {
			F.println("SpecType=" + getSpecType());
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		PropertyValue[0] = getBus(1);  // TODO: Check zero based indexing
		PropertyValue[1] = getBus(2);  // TODO: Check zero based indexing
		PropertyValue[2] = "3";
		PropertyValue[3] = "1200";
		PropertyValue[4] = "12.47";
		PropertyValue[5] = "wye";
		PropertyValue[6] = "";
		PropertyValue[7] = "";
		PropertyValue[8] = "0";
		PropertyValue[9] = "0";
		PropertyValue[10] = "0";
		PropertyValue[11] = "1";
		PropertyValue[12] = "1";  // states

		super.initPropertyValues(Capacitor.NumPropsThisClass);

		// override inherited properties
		PropertyValue[Capacitor.NumPropsThisClass + 1] = Utilities.strReal(getNormAmps(), 0);  // TODO: Check zero based indexing
		PropertyValue[Capacitor.NumPropsThisClass + 2] = Utilities.strReal(getEmergAmps(), 0);
		PropertyValue[Capacitor.NumPropsThisClass + 3] = Utilities.strReal(getFaultRate(), 0);
		PropertyValue[Capacitor.NumPropsThisClass + 4] = Utilities.strReal(getPctPerm(), 0);
		PropertyValue[Capacitor.NumPropsThisClass + 5] = Utilities.strReal(getHrsToRepair(), 0);
		clearPropSeqArray();
	}

	@Override
	public void makePosSequence() {
		String S = null;
		double kvarPerPhase, PhasekV, Cs, Cm;
		int i, j;

		if (getNPhases() > 1) {
			switch (getSpecType()) {
			case 1:  // kvar

				if ((getNPhases() > 1) || (getConnection() != 0)) {
					PhasekV = getKvrating() / DSSGlobals.SQRT3;
				} else {
					PhasekV = getKvrating();
				}

				S = "Phases=1 " + String.format(" kV=%-.5g kvar=(", PhasekV);

				for (i = 0; i < getNumSteps(); i++) {
					kvarPerPhase = getKvarrating()[i] / getNPhases();
					S = S + String.format(" %-.5g", kvarPerPhase);
				}

				S = S + ")";

				break;
				/* Leave R as specified */
			case 2:
				S = "Phases=1 ";
				break;
			case 3:  // C matrix
				S = "Phases=1 ";
				// r1
				Cs = 0.0;  // avg self
				for (i = 0; i < getNPhases(); i++)
					Cs = Cs + getCmatrix()[(i - 1) * getNPhases() + i];  // TODO: Check zero based indexing
				Cs = Cs / getNPhases();

				Cm = 0.0;  // avg mutual
				for (i = 1; i < getNPhases(); i++)
					for (j = i; j < getNPhases(); j++)
						Cm = Cm + getCmatrix()[(i - 1) * getNPhases() + j];
				Cm = Cm / (getNPhases() * (getNPhases() - 1.0) / 2.0);

				S = S + String.format(" Cuf=%-.5g", (Cs - Cm));
				break;
			}

			Parser.getInstance().setCmdString(S);
			edit();
		}

		super.makePosSequence();
	}

	public int getStates(int Idx) {
		return getStates()[Idx];
	}

	public void setStates(int Idx, int Value) {
		if (getStates()[Idx] != Value) {
			getStates()[Idx] = Value;
			setYPrimInvalid(true);
		}
	}

	/**
	 * Special case for changing from 1 to more. Automatically make a new bank.
	 *
	 * 1=kvar, 2=Cuf, 3=Cmatrix
	 */
	public void setNumSteps(int Value) {
		double StepSize, Rstep, XLstep;
		int i;

		/* Reallocate all arrays associated with steps */

		if ((getNumSteps() != Value) && (Value > 0)) {
			Rstep = 0.0;
			XLstep = 0.0;
			if (getNumSteps() == 1) {
				/* Save total values to be divided up */
				setTotalkvar(getKvarrating()[0]);
				Rstep = getR()[0] * Value;
				XLstep = getXL()[0] * Value;
			}

			// reallocate arrays (must be initialized to nil for first call)
			setC( (double[]) Utilities.resizeArray(getC(), Value) );
			setXL( (double[]) Utilities.resizeArray(getXL(), Value) );
			setKvarrating( (double[]) Utilities.resizeArray(getKvarrating(), Value) );
			setR( (double[]) Utilities.resizeArray(getR(), Value) );
			setHarm( (double[]) Utilities.resizeArray(getHarm(), Value) );
			setStates( (int[]) Utilities.resizeArray(getStates(), Value) );

			// special case for numSteps=1

			if (getNumSteps() == 1) {
				switch (getSpecType()) {
				case 1:  // kvar   /* We'll make a multi-step bank of same net size as at present */
					StepSize = getTotalkvar() / Value;
					for (i = 0; i < Value; i++)
						getKvarrating()[i] = StepSize;
					break;

				case 2:  // Cuf   /* We'll make a multi-step bank with all the same as first */
					for (i = 1; i < Value; i++)
						getC()[i] = getC()[0];  // make same as first step
					break;

				case 3:  // Cmatrix   /* We'll make a multi-step bank with all the same as first */
					// nothing to do since all will be the same
					break;
				}

				switch (getSpecType()) {
				case 1:
					for (i = 0; i < Value; i++)
						getR()[i] = Rstep;
					for (i = 0; i < Value; i++)
						getXL()[i] = XLstep;
					break;

				case 2:  // make R and XL same as first step
					for (i = 1; i < Value; i++)
						getR()[i] = getR()[0];
					for (i = 1; i < Value; i++)
						getXL()[i] = getXL()[0];
					break;
				case 3:  // make R and XL same as first step
					for (i = 1; i < Value; i++)
						getR()[i] = getR()[0];
					for (i = 1; i < Value; i++)
						getXL()[i] = getXL()[0];
					break;
				}

				for (i = 0; i < Value; i++)
					getStates()[i] = 1;  // turn them all ON
				setLastStepInService(Value);
				for (i = 1; i < Value; i++)
					getHarm()[i] = getHarm()[0];  // tune them all the same as first
			}
		}

		setNumSteps(Value);
	}

	// FIXME Private member in OpenDSS
	public void processHarmonicSpec(String Param) {
		Utilities.interpretDblArray(Param, getNumSteps(), getHarm());

		setDoHarmonicRecalc(true);
	}

	// FIXME Private member in OpenDSS
	public void processStatesSpec(String Param) {
		Utilities.interpretIntArray(Param, getNumSteps(), getStates());

		LastStepInService = 0;

		for (int i = getNumSteps(); i < 0; i--)  // TODO Check zero based indexing
			if (getStates()[i] == 1) {
				LastStepInService = i;
				break;
			}
	}

	/**
	 * Call this routine only if step is energized.
	 */
	private void makeYprimWork(CMatrix YprimWork, int iStep) {
		Complex Value, Value2, ZL = null;
		int i,j, ioffset;
		double w, FreqMultiple;
		boolean HasZL;

		setYPrimFreq(DSSGlobals.getInstance().getActiveCircuit().getSolution().getFrequency());
		FreqMultiple = getYPrimFreq() / getBaseFrequency();
		w = DSSGlobals.TwoPi * getYPrimFreq();

		if ((getR()[iStep] + Math.abs(getXL()[iStep])) > 0.0) {
			HasZL = true;
		} else {
			HasZL = false;
		}

		if (HasZL)
			ZL = new Complex(getR()[iStep], getXL()[iStep] * FreqMultiple);

		/* Now, put C into in Yprim matrix */

		switch (getSpecType()) {
		case 1:
			Value = new Complex(0.0, getC()[iStep] * w);
			switch (getConnection()) {
			case 1:  // line-line
				Value2 = Value.multiply(2.0);
				Value = Value.negate();
				for (i = 0; i < getNPhases(); i++) {
					YprimWork.setElement(i, i, Value2);
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						YprimWork.setElemSym(i, j, Value);
					}
					// remainder of the matrix is all zero
				}
				break;
			default:  // wye
				if (HasZL)
					Value = ZL.add(Value.invert()).invert(); // add in ZL
				Value2 = Value.negate();
				for (i = 0; i < getNPhases(); i++) {
					YprimWork.setElement(i, i, Value);  // elements are only on the diagonals
					YprimWork.setElement(i + getNPhases(), i + getNPhases(), Value);
					YprimWork.setElemSym(i, i + getNPhases(), Value2);
				}
				break;
			}
			break;
		case 2:  // identical to case 1

			Value = new Complex(0.0, getC()[iStep] * w);
			switch (getConnection()) {
			case 1:  // line-line
				Value2 = Value.multiply(2.0);
				Value = Value.negate();
				for (i = 0; i < getNPhases(); i++) {
					YprimWork.setElement(i, i, Value2);
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						YprimWork.setElemSym(i, j, Value);
					}
					// remainder of the matrix is all zero
				}
				break;
			default:  // wye
				if (HasZL)
					Value = ZL.add(Value.invert()).invert();  // add in ZL
				Value2 = Value.negate();
				for (i = 0; i < getNPhases(); i++) {
					YprimWork.setElement(i, i, Value);  // elements are only on the diagonals
					YprimWork.setElement(i + getNPhases(), i + getNPhases(), Value);
					YprimWork.setElemSym(i, i + getNPhases(), Value2);
				}
				break;
			}
			break;
		case 3:  // C matrix specified
			for (i = 0; i < getNPhases(); i++) {
				ioffset = (i - 1) * getNPhases();  // TODO Check zero based indexing
				for (j = 0; j < getNPhases(); j++) {
					Value = new Complex(0.0, getCmatrix()[(ioffset + j)] * w);
					YprimWork.setElement(i, j, Value);
					YprimWork.setElement(i + getNPhases(), j + getNPhases(), Value);
					Value = Value.negate();
					YprimWork.setElemSym(i, j + getNPhases(), Value);
				}
			}
			break;
		}

		/* Add line reactance for filter reactor, if any */
		if (HasZL) {
			switch (getSpecType()) {
			case 1:

				switch (getConnection()) {
				case 1:  // line-line
					/* Add a little bit to each phase so it will invert */
					for (i = 0; i < getNPhases(); i++)
						YprimWork.setElement(i, i, YprimWork.getElement(i, i).multiply(1.000001));
					YprimWork.invert();
					for (i = 0; i < getNPhases(); i++) {
						Value = ZL.add(YprimWork.getElement(i, i));
						YprimWork.setElement(i, i, Value);
					}
					YprimWork.invert();
					break;
				default:  /* Wye - just put ZL in series */
					/* Do nothing; already in - see above */
					break;
				}
				break;
			case 2:  // identical to case 1

				switch (getConnection()) {
				case 1:  // line-line
					/* Add a little bit to each phase so it will invert */
					for (i = 0; i < getNPhases(); i++)
						YprimWork.setElement(i, i, YprimWork.getElement(i, i).multiply(1.000001));
					YprimWork.invert();
					for (i = 0; i < getNPhases(); i++) {
						Value = ZL.add(YprimWork.getElement(i, i));
						YprimWork.setElement(i, i, Value);
					}
					YprimWork.invert();
					break;
				default:  /* Wye - just put ZL in series */
					/* Do nothing; already in - see above */
					break;
				}
				break;
			case 3:
				YprimWork.invert();
				for (i = 0; i < getNPhases(); i++) {
					Value = ZL.add(YprimWork.getElement(i, i));
					YprimWork.setElement(i, i, Value);
				}
				YprimWork.invert();
				break;
			}
		}
	}

	@Override
	public String getPropertyValue(int Index) {
		double[] Temp;

		String Result = "";
		switch (Index) {  // special cases
		case 0:
			Result = getBus(1);  // TODO: Check zero based indexing
			break;
		case 1:
			Result = getBus(2);  // TODO: Check zero based indexing
			break;
		case 3:
			Result = Utilities.getDSSArray_Real(getNumSteps(), getKvarrating());
			break;
		case 7:
			Temp = new double[getNumSteps()];
			for (int i = 0; i < getNumSteps(); i++) {
				Temp[i] = getC()[i] * 1.0e6;  // to microfarads
			}
			Result = Utilities.getDSSArray_Real(getNumSteps(), Temp);
			Temp = null;  // throw away temp storage
			break;
		case 8:
			Result = Utilities.getDSSArray_Real(getNumSteps(), getR());
			break;
		case 10:
			Result = Utilities.getDSSArray_Real(getNumSteps(), getXL());
			break;
		case 11:
			Result = Utilities.getDSSArray_Real(getNumSteps(), getHarm());
			break;
		case 12:
			Result = Utilities.getDSSArray_Integer(getNumSteps(), getStates());
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}
		return Result;
	}

	public boolean addStep() {
		// start with last step in service and see if we can add more; if not return false.

		if (LastStepInService == getNumCustomers()) {
			return false;
		} else {
			LastStepInService += 1;
			getStates()[LastStepInService] = 1;  // TODO Check zero based indexing
			return true;
		}
	}

	public boolean subtractStep() {
		if (LastStepInService == 0) {  // TODO Check zero based indexing
			return false;
		} else {
			getStates()[LastStepInService] = 0;  // TODO Check zero based indexing
			LastStepInService -= 1;
			if (LastStepInService == 0) {
				return false;
			} else {
				return true;  // signify bank open
			}
		}
	}

	public int availableSteps() {
		return getNumSteps() - LastStepInService;
	}

	public int getNumSteps() {
		return NumSteps;
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}

	public double getTotalkvar() {
		return totalkvar;
	}

	public double getKvrating() {
		return kvrating;
	}

	// FIXME Private members in OpenDSS

	public double[] getC() {
		return C;
	}

	public void setC(double[] c) {
		C = c;
	}

	public double[] getXL() {
		return XL;
	}

	public void setXL(double[] xL) {
		XL = xL;
	}

	public double[] getKvarrating() {
		return kvarrating;
	}

	public void setKvarrating(double[] kvarrating) {
		this.kvarrating = kvarrating;
	}

	public double[] getR() {
		return R;
	}

	public void setR(double[] r) {
		R = r;
	}

	public double[] getHarm() {
		return Harm;
	}

	public void setHarm(double[] harm) {
		Harm = harm;
	}

	public int[] getStates() {
		return States;
	}

	public void setStates(int[] states) {
		States = states;
	}

	public int getLastStepInService() {
		return LastStepInService;
	}

	public void setLastStepInService(int lastStepInService) {
		LastStepInService = lastStepInService;
	}

	public double[] getCmatrix() {
		return Cmatrix;
	}

	public void setCmatrix(double[] cmatrix) {
		Cmatrix = cmatrix;
	}

	public boolean isDoHarmonicRecalc() {
		return doHarmonicRecalc;
	}

	public void setDoHarmonicRecalc(boolean doHarmonicRecalc) {
		this.doHarmonicRecalc = doHarmonicRecalc;
	}

	public int getSpecType() {
		return SpecType;
	}

	public void setSpecType(int specType) {
		SpecType = specType;
	}

	public void setTotalkvar(double totalkvar) {
		this.totalkvar = totalkvar;
	}

	public void setKvrating(double kvrating) {
		this.kvrating = kvrating;
	}

}
