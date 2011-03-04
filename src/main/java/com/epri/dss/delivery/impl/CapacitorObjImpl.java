package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.delivery.CapacitorObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;

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
	private double[] Cmatrix;  // If not nil then overrides C

	private boolean doHarmonicRecalc;

	private int SpecType;
	
	/* 0 or 1 for wye (default) or delta, respectively */
	protected int Connection;

	public CapacitorObjImpl(DSSClass ParClass, String CapacitorName) {
		super(ParClass);

		setName(CapacitorName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();
		
		this.nPhases = 3;  // Directly set conds and phases
		this.nConds = 3;
		this.nTerms = 2;  // Force allocation of terminals and conductors

		setBus(1, (getBus(0) + ".0.0.0"));  // Default to grounded wye

		setShunt(true);  // defaults to shunt capacitor

		this.Cmatrix = null;

		/* Initialize these pointers to Nil so reallocmem will work reliably. */
		this.C = null;
		this.XL = null;
		this.kvarrating = null;
		this.R = null;
		this.Harm = null;
		this.States = null;

		this.NumSteps = 1;  // Initial Allocation for the Arrays, too
		this.LastStepInService = this.NumSteps;

		Utilities.initDblArray(this.NumSteps, this.R, 0.0);
		Utilities.initDblArray(this.NumSteps, this.XL, 0.0);
		Utilities.initDblArray(this.NumSteps, this.Harm, 0.0);
		Utilities.initDblArray(this.NumSteps, this.kvarrating, 1200.0);
		
		this.States[0] = 1;

		this.kvrating = 12.47;
		Utilities.initDblArray(this.NumSteps, this.C,
				1.0 / (DSSGlobals.TwoPi * BaseFrequency * Math.pow(kvrating, 2) * 1000.0 / this.kvarrating[0]));

		this.Connection = 0;   // 0 or 1 for wye (default) or delta, respectively
		this.SpecType = 1; // 1=kvar, 2=Cuf, 3=Cmatrix

		setNormAmps(this.kvarrating[0] * DSSGlobals.SQRT3 / this.kvrating * 1.35);   // 135%
		setEmergAmps(getNormAmps() * 1.8 / 1.35);   //180%
		setFaultRate(0.0005);
		setPctPerm(100.0);
		setHrsToRepair(3.0);
		this.Yorder = this.nTerms * this.nConds;

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
		w = DSSGlobals.TwoPi * BaseFrequency;
		switch (SpecType) {
		case 1:// kvar
			switch (Connection) {
			case 1:  // Line-to-Line
				PhasekV = kvrating;
			default:  // line-to-neutral
				switch (nPhases) {
				case 2:
					PhasekV = kvrating / DSSGlobals.SQRT3;  // Assume three phase system
				case 3:
					PhasekV = kvrating / DSSGlobals.SQRT3;
				default:
					PhasekV = kvrating;
				}
			}
			for (i = 0; i < NumSteps; i++) 
				C[i] = 1.0 / (w * Math.pow(PhasekV, 2) * 1000.0 / (kvarrating[0] / nPhases));
			for (i = 0; i < NumSteps; i++) 
				totalkvar = totalkvar + kvarrating[i];
		case 2:  // Cuf
			switch (Connection) {
			case 1:  // Line-to-Line
				PhasekV = kvrating;
			default:  // line-to-neutral
				switch (nPhases) {
				case 2:
					PhasekV = kvrating / DSSGlobals.SQRT3;  // Assume three phase system
				case 3:
					PhasekV = kvrating / DSSGlobals.SQRT3;
				default:
					PhasekV = kvrating;
				}
			}
			for (i = 0; i < NumSteps; i++) 
				totalkvar = totalkvar + w * C[i] * Math.pow(PhasekV, 2) / 1000.0;
		case 3:  // Cmatrix
			// Nothing to do
		}

		if (doHarmonicRecalc)  // If harmonic specified, compute filter reactance
			for (i = 0; i < NumSteps; i++) {
				if (Harm[i] != 0.0) {
					XL[i] = (1.0 / (w * C[i])) / Math.pow(Harm[i], 2);
				} else {
					XL[i] = 0.0;  // Assume 0 harmonic means no filter
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

		// Normally build only Yprim Shunt, but if there are 2 terminals and
		// Bus1 != Bus 2

		if (isYprimInvalid()) {
			// Reallocate YPrim if something has invalidated old allocation
			if (YPrim_Shunt != null)
				YPrim_Shunt = null;
			YPrim_Shunt = new CMatrixImpl(Yorder);
			if (YPrim_Series != null)
				YPrim_Series = null;
			YPrim_Series = new CMatrixImpl(Yorder);
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

		YPrimWork = new CMatrixImpl(Yorder);

		for (i = 0; i < NumSteps; i++) 
			if (States[i] == 1) {
				makeYprimWork(YPrimWork, i);
				YPrimTemp.addFrom(YPrimWork);
			}

		YPrimWork = null;

		// Set YPrim_Series based on diagonals of YPrim_shunt  so that CalcVoltages doesn't fail
		if (isShunt())
			for (i = 0; i < Yorder; i++) 
				YPrim_Series.setElement(i, i, YPrim_Shunt.getElement(i, i).multiply(1.0e-10));

		YPrim.copyFrom(YPrimTemp);

		/* Don't Free YPrimTemp - It's just a pointer to an existing complex matrix */

		super.calcYPrim();

		setYprimInvalid(false);
	}
	
	public int getStates(int Idx) {
		return 0;
	}
	
	public void setStates(int Idx, int Value) {
		
	}
	
	private void processHarmonicSpec(String Param) {
		
	}
	
	private void processStatesSpec(String Param) {
		
	}
	
	private void makeYprimWork(CMatrix YprimWork, int iStep) {
		
	}
	
	/* 1=kvar, 2=Cuf, 3=Cmatrix */
	public void setNumSteps(int Value) {
		
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
	
	@Override
	public void makePosSequence() {
		
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}
	
	@Override
	public String getPropertyValue(int Index) {
		return null;
	}
	
	public boolean addStep() {
		return false;
	}
	
	public boolean subtractStep() {
		return false;
	}
	
	public int availableSteps() {
		return 0;
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
