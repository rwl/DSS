package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.Equivalent;
import com.epri.dss.conversion.EquivalentObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.ComplexUtil;

public class EquivalentObjImpl extends PCElementImpl implements EquivalentObj {

	private double kVBase;

	private double VMag;
	private double PerUnit;
	private double Angle;
	private double EquivFrequency;

	private double[] R1, X1, R0, X0;

	private boolean NeedToDoRecalc;

	// Base Frequency Series Z matrix
	protected CMatrix Z;
	protected CMatrix Zinv;

	public EquivalentObjImpl(DSSClassImpl ParClass, String SourceName) {
		super(ParClass);
		setName(SourceName.toLowerCase());
		DSSObjType = ParClass.getDSSClassType(); //SOURCE + NON_PCPD_ELEM;  // Don't want this in PC Element List

		this.nPhases = 3;
		this.nConds = 3;
		this.nTerms = 1;
		this.Z    = null;
		this.Zinv = null;
		/*this.Basefrequency = 60.0;*/  // set in base class

		this.R1 = null;
		this.X1 = null;
		this.R0 = null;
		this.X0 = null;

		reallocRX();

		this.R1[0]   = 1.65;
		this.X1[0]   = 6.6;
		this.R0[0]   = 1.9;
		this.X0[0]   = 5.7;

		this.kVBase = 115.0;
		this.PerUnit = 1.0;
		this.EquivFrequency = BaseFrequency;
		this.Angle = 0.0;

		setSpectrum("defaultvsource");

		initPropertyValues(0);

		this.Yorder = this.nTerms * this.nConds;
		recalcElementData();
	}

	private int idx(int a, int b) {
		return (b - 1) * nTerms + a;
	}

	@Override
	public void recalcElementData() {
		Complex Zs, Zm;
//		int i, j, ii, jj;
		int iOffset, jOffset, indx;
		DSSGlobals Globals = DSSGlobals.getInstance();

		if (Z != null) Z = null;
		if (Zinv != null) Zinv = null;

		// For a source, nPhases = nCond, for now
		Z    = new CMatrixImpl(nPhases * nTerms);
		Zinv = new CMatrixImpl(nPhases * nTerms);

		// Build Z matrix for all phases
		for (int i = 0; i < nTerms; i++)
			for (int j = 0; j < nTerms; j++) {
				indx = idx(i, j);
				Zs = new Complex(2.0 * R1[indx] + R0[indx], 2.0 * X1[indx] + X0[indx] ).divide(3.0);
				Zm = new Complex(R0[indx] - R1[indx], X0[indx] - X1[indx]).divide(3.0);

				iOffset = (i - 1) * nPhases;
				jOffset = (j - 1) * nPhases;

				for (int ii = 0; ii < nPhases; ii++) {
					for (int jj = 0; jj < ii; jj++) {
						if (ii == jj) {
							Z.setElement(ii + iOffset, jj + jOffset, Zs);
						} else {
							Z.setElement(ii + iOffset, jj + jOffset, Zm);
							Z.setElement(jj + iOffset, ii + jOffset, Zm);  // set other offdiagonal in this submatrix
						}
					}
				}

			}

		// Voltage source properties
		switch (nPhases) {
		case 1:
			VMag = kVBase * PerUnit * 1000.0;
		default:
			VMag = kVBase * PerUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases)* Math.PI / 180.0);
		}

		setSpectrumObj((com.epri.dss.general.SpectrumObj) Globals.getSpectrumClass().find(getSpectrum()));
		if (getSpectrumObj() == null)
			Globals.doSimpleMsg("Spectrum Object \"" + getSpectrum() + "\" for Device Equivalent."+getName()+" Not Found.", 802);

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), Yorder) );

		NeedToDoRecalc = false;
	}

	@Override
	public void calcYPrim() {
		Complex Value;
		int i, j;
		double FreqMultiplier;

		DSSGlobals Globals = DSSGlobals.getInstance();

		// Build only YPrim Series
		if (isYprimInvalid()) {
			if (YPrim_Series != null) YPrim_Series = null;
			YPrim_Series = new CMatrixImpl(Yorder);
			if (YPrim != null) YPrim = null;
			YPrim = new CMatrixImpl(Yorder);
		} else {
			YPrim_Series.clear();
			YPrim.clear();
		}

		if (NeedToDoRecalc)
			recalcElementData();

		YprimFreq = Globals.getActiveCircuit().getSolution().getFrequency();
		FreqMultiplier = YprimFreq / BaseFrequency;

		/* Put in Series RL matrix Adjusted for frequency */
		for (i = 0; i < Yorder; i++) {
			for (j = 0; j < Yorder; j++) {
				Value = Z.getElement(i, j);
				/* Modify from base freq */
				Value = new Complex(Value.getReal(), Value.getImaginary() * FreqMultiplier);
				Zinv.setElement(i, j, Value);
			}
		}

		Zinv.invert();  /* Invert in place */

		if (Zinv.getInvertError() > 0) {
			/* If error, put in Large series conductance */
			Globals.doErrorMsg("EquivalentObj.calcYPrim", "Matrix Inversion Error for Equivalent \"" + getName() + "\"",
					"Invalid impedance specified. Replaced with small resistance.", 803);
			Zinv.clear();
			for (i = 0; i < nPhases; i++)
				Zinv.setElement(i, i, new Complex(1.0 / DSSGlobals.EPSILON, 0.0));
		}


		YPrim_Series.copyFrom(Zinv);

		YPrim.copyFrom(YPrim_Series);

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */
		super.calcYPrim();

		setYprimInvalid(false);
	}

	private void getVterminalForSource() {
		int i;
		Complex Vharm;
		double EquivHarm;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			/* This formulation will theoretically handle voltage sources of any number
			 * of phases assuming they are equally displaced in time.
			 */

			switch (nPhases) {
			case 1:
				VMag = kVBase * PerUnit * 1000.0;
			default:
				VMag = kVBase * PerUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases) * Math.PI / 180.0);
			}

			SolutionObj sol = Globals.getActiveCircuit().getSolution();

			if (sol.isIsHarmonicModel()) {
				EquivHarm = sol.getFrequency() / EquivFrequency ;
				Vharm = getSpectrumObj().getMult(EquivHarm).multiply(VMag);  // Base voltage for this harmonic
				Utilities.rotatePhasorDeg(Vharm, EquivHarm, Angle);  // Rotate for phase 1 shift
				for (i = 0; i < nPhases; i++) {
					Vterminal[i] = Vharm;
					if (i < nPhases)
						Utilities.rotatePhasorDeg(Vharm, EquivHarm, -360.0 / nPhases);
				}
			} else {
				for (i = 0; i < nPhases; i++)
					Vterminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + Angle - (i - 1) * 360.0 / nPhases));
			}

		} catch (Exception e) {
			Globals.doSimpleMsg("Error computing Voltages for Equivalent."+getName()+". Check specification. Aborting.", 804);
			if (Globals.isIn_Redirect())
				Globals.setRedirect_Abort(true);
		}
	}

	@Override
	public int injCurrents() {
		getInjCurrents(getInjCurrent());
		return super.injCurrents();  // Add into system array
	}

	@Override
	public void getCurrents(Complex[] Curr) {
		int i;
		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			SolutionObj sol = Globals.getActiveCircuit().getSolution();
			for (i = 0; i < Yorder; i++)
				Vterminal[i] = sol.getNodeV()[NodeRef[i]];

			YPrim.MVMult(Curr, Vterminal);

			getInjCurrents(ComplexBuffer);  // Get present value of inj currents
			// Add together with Yprim currents
			for (i = 0; i < Yorder; i++)
				Curr[i] = Curr[i].subtract(ComplexBuffer[i]);
		} catch (Exception e) {
			Globals.doErrorMsg(("GetCurrents for Element: " + getName() + "."), e.getMessage(),
			"Inadequate storage allotted for circuit element.", 805);
		}
	}

	@Override
	public void getInjCurrents(Complex[] Curr) {

		getVterminalForSource();
		YPrim.MVMult(Curr, Vterminal);  /* I = Y V */

		setITerminalUpdated(false);
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		int i, j;
		Complex c;

		super.dumpProperties(F, Complete);

		DSSClass pc = getParentClass();
		for (i = 0; i < pc.getNumProperties(); i++)
			F.println("~ " + pc.getPropertyName()[i] + "=" + getPropertyValue(i));

		if (Complete) {
			F.println();
			F.println("BaseFrequency=" + BaseFrequency);
			F.println("VMag=" + VMag);
			F.println("Z Matrix=");
			for (i = 0; i < nPhases; i++) {
				for (j = 0; j < i; j++) {
					c = Z.getElement(i, j);
					F.print(c.getReal() + " + j" + c.getImaginary());
				}
				F.println();
			}
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0]  = "1";
		PropertyValue[1]  = getBus(1);  // TODO Check zero based indexing
		PropertyValue[2]  = "115";
		PropertyValue[3]  = "1";
		PropertyValue[4]  = "0";
		PropertyValue[5]  = "60";
		PropertyValue[6]  = "3";
		PropertyValue[7]  = "1.65";
		PropertyValue[8]  = "6.6";
		PropertyValue[9]  = "1.9";
		PropertyValue[10] = "5.7";

		super.initPropertyValues(Equivalent.NumPropsThisClass);
	}

	@Override
	public String getPropertyValue(int Index) {
		switch (Index) {
		case 0:
			return null;
		default:
			return super.getPropertyValue(Index);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		String S;

		S = "Phases=1 ";
		S = S + String.format("BasekV=%-.5g ", kVBase / DSSGlobals.SQRT3);
		S = S + String.format("R1=%-.5g ", R1);
		S = S + String.format("X1=%-.5g ", X1);

		Parser.getInstance().setCmdString(S);
		edit();

		super.makePosSequence();
	}

	// Private method in OpenDSS
	public int doTerminalsDef(int N) {
		int Result = nTerms;

		if (N != nTerms)
			if (N > 0)
				reallocRX();

		return Result;
	}

	/**
	 * Parse input string as an array.
	 */
	// Private method in OpenDSS
	public void parseDblMatrix(double[] Mat) {
		Parser.getInstance().parseAsSymMatrix(nTerms, Mat);
	}

	private void reallocRX() {
		R1 = (double[]) Utilities.resizeArray(R1, (int) Math.pow(nTerms, 2));
		X1 = (double[]) Utilities.resizeArray(X1, (int) Math.pow(nTerms, 2));
		R0 = (double[]) Utilities.resizeArray(R0, (int) Math.pow(nTerms, 2));
		X0 = (double[]) Utilities.resizeArray(X0, (int) Math.pow(nTerms, 2));
	}

	public CMatrix getZ() {
		return Z;
	}

	public void setZ(CMatrix z) {
		Z = z;
	}

	public CMatrix getZinv() {
		return Zinv;
	}

	public void setZinv(CMatrix zinv) {
		Zinv = zinv;
	}

	// FIXME Private members in OpenDSS

	public double getkVBase() {
		return kVBase;
	}

	public void setkVBase(double kVBase) {
		this.kVBase = kVBase;
	}

	public double getVMag() {
		return VMag;
	}

	public void setVMag(double vMag) {
		VMag = vMag;
	}

	public double getPerUnit() {
		return PerUnit;
	}

	public void setPerUnit(double perUnit) {
		PerUnit = perUnit;
	}

	public double getAngle() {
		return Angle;
	}

	public void setAngle(double angle) {
		Angle = angle;
	}

	public double getEquivFrequency() {
		return EquivFrequency;
	}

	public void setEquivFrequency(double equivFrequency) {
		EquivFrequency = equivFrequency;
	}

	public double[] getR1() {
		return R1;
	}

	public void setR1(double[] r1) {
		R1 = r1;
	}

	public double[] getX1() {
		return X1;
	}

	public void setX1(double[] x1) {
		X1 = x1;
	}

	public double[] getR0() {
		return R0;
	}

	public void setR0(double[] r0) {
		R0 = r0;
	}

	public double[] getX0() {
		return X0;
	}

	public void setX0(double[] x0) {
		X0 = x0;
	}

	public boolean isNeedToDoRecalc() {
		return NeedToDoRecalc;
	}

	public void setNeedToDoRecalc(boolean needToDoRecalc) {
		NeedToDoRecalc = needToDoRecalc;
	}

}
