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
	private double perUnit;
	private double angle;
	private double equivFrequency;

	private double[] R1, X1, R0, X0;

	private boolean needToDoRecalc;

	// base frequency series Z matrix
	protected CMatrix Z;
	protected CMatrix ZInv;

	public EquivalentObjImpl(DSSClassImpl parClass, String sourceName) {
		super(parClass);
		setName(sourceName.toLowerCase());
		DSSObjType = parClass.getDSSClassType(); //SOURCE + NON_PCPD_ELEM;  // don't want this in PC element list

		setNPhases(3);
		this.nConds = 3;
		setNTerms(1);
		this.Z    = null;
		this.ZInv = null;
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
		this.perUnit = 1.0;
		this.equivFrequency = baseFrequency;
		this.angle = 0.0;

		this.Spectrum = "defaultvsource";

		initPropertyValues(0);

		this.YOrder = this.nTerms * this.nConds;
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
		DSSGlobals globals = DSSGlobals.getInstance();

		if (Z != null) Z = null;
		if (ZInv != null) ZInv = null;

		// for a source, nPhases = nCond, for now
		Z    = new CMatrixImpl(nPhases * nTerms);
		ZInv = new CMatrixImpl(nPhases * nTerms);

		// build Z matrix for all phases
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
							Z.setElement(jj + iOffset, ii + jOffset, Zm);  // set other off-diagonal in this submatrix
						}
					}
				}

			}

		//  voltage source properties
		switch (nPhases) {
		case 1:
			VMag = kVBase * perUnit * 1000.0;
			break;
		default:
			VMag = kVBase * perUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases)* Math.PI / 180.0);
			break;
		}

		setSpectrumObj((com.epri.dss.general.SpectrumObj) globals.getSpectrumClass().find(getSpectrum()));
		if (getSpectrumObj() == null)
			globals.doSimpleMsg("Spectrum object \"" + getSpectrum() + "\" for device equivalent."+getName()+" not found.", 802);

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), YOrder) );

		needToDoRecalc = false;
	}

	@Override
	public void calcYPrim() {
		Complex value;
		int i, j;
		double freqMultiplier;

		DSSGlobals globals = DSSGlobals.getInstance();

		// build only YPrim series
		if (isYprimInvalid()) {
			if (YPrimSeries != null) YPrimSeries = null;
			YPrimSeries = new CMatrixImpl(YOrder);
			if (YPrim != null) YPrim = null;
			YPrim = new CMatrixImpl(YOrder);
		} else {
			YPrimSeries.clear();
			YPrim.clear();
		}

		if (needToDoRecalc)
			recalcElementData();

		YPrimFreq = globals.getActiveCircuit().getSolution().getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;

		/* Put in Series RL matrix adjusted for frequency */
		for (i = 0; i < YOrder; i++) {
			for (j = 0; j < YOrder; j++) {
				value = Z.getElement(i, j);
				/* Modify from base freq */
				value = new Complex(value.getReal(), value.getImaginary() * freqMultiplier);
				ZInv.setElement(i, j, value);
			}
		}

		ZInv.invert();  /* Invert in place */

		if (ZInv.getInvertError() > 0) {
			/* If error, put in large series conductance */
			globals.doErrorMsg("EquivalentObj.calcYPrim", "Matrix inversion error for equivalent \"" + getName() + "\"",
					"Invalid impedance specified. Replaced with small resistance.", 803);
			ZInv.clear();
			for (i = 0; i < nPhases; i++)
				ZInv.setElement(i, i, new Complex(1.0 / DSSGlobals.EPSILON, 0.0));
		}


		YPrimSeries.copyFrom(ZInv);

		YPrim.copyFrom(YPrimSeries);

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */
		super.calcYPrim();

		setYPrimInvalid(false);
	}

	private void getVterminalForSource() {
		int i;
		Complex VHarm;
		double equivHarm;

		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			/* This formulation will theoretically handle voltage sources of any number
			 * of phases assuming they are equally displaced in time.
			 */

			switch (nPhases) {
			case 1:
				VMag = kVBase * perUnit * 1000.0;
				break;
			default:
				VMag = kVBase * perUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases) * Math.PI / 180.0);
				break;
			}

			SolutionObj sol = globals.getActiveCircuit().getSolution();

			if (sol.isHarmonicModel()) {
				equivHarm = sol.getFrequency() / equivFrequency ;
				VHarm = getSpectrumObj().getMult(equivHarm).multiply(VMag);  // base voltage for this harmonic
				VHarm = Utilities.rotatePhasorDeg(VHarm, equivHarm, angle);  // rotate for phase 1 shift
				for (i = 0; i < nPhases; i++) {
					VTerminal[i] = VHarm;
					if (i < nPhases)
						VHarm = Utilities.rotatePhasorDeg(VHarm, equivHarm, -360.0 / nPhases);
				}
			} else {
				for (i = 0; i < nPhases; i++)
					VTerminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + angle - (i - 1) * 360.0 / nPhases));
			}

		} catch (Exception e) {
			globals.doSimpleMsg("Error computing voltages for Equivalent."+getName()+". Check specification. Aborting.", 804);
			if (globals.isInRedirect())
				globals.setRedirectAbort(true);
		}
	}

	@Override
	public int injCurrents() {
		getInjCurrents(getInjCurrent());
		return super.injCurrents();  // add into system array
	}

	@Override
	public void getCurrents(Complex[] curr) {
		int i;
		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			SolutionObj sol = globals.getActiveCircuit().getSolution();
			for (i = 0; i < YOrder; i++)
				VTerminal[i] = sol.getNodeV()[nodeRef[i]];

			YPrim.MVMult(curr, VTerminal);

			getInjCurrents(complexBuffer);  // get present value of inj currents
			// add together with Yprim currents
			for (i = 0; i < YOrder; i++)
				curr[i] = curr[i].subtract(complexBuffer[i]);
		} catch (Exception e) {
			globals.doErrorMsg(("GetCurrents for element: " + getName() + "."), e.getMessage(),
			"Inadequate storage allotted for circuit element.", 805);
		}
	}

	@Override
	public void getInjCurrents(Complex[] curr) {

		getVterminalForSource();
		YPrim.MVMult(curr, VTerminal);  /* I = Y V */

		setITerminalUpdated(false);
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		int i, j;
		Complex c;

		super.dumpProperties(f, complete);

		DSSClass pc = getParentClass();
		for (i = 0; i < pc.getNumProperties(); i++)
			f.println("~ " + pc.getPropertyName()[i] + "=" + getPropertyValue(i));

		if (complete) {
			f.println();
			f.println("baseFrequency=" + baseFrequency);
			f.println("vMag=" + VMag);
			f.println("zMatrix=");
			for (i = 0; i < nPhases; i++) {
				for (j = 0; j < i; j++) {
					c = Z.getElement(i, j);
					f.print(c.getReal() + " + j" + c.getImaginary());
				}
				f.println();
			}
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

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
	public String getPropertyValue(int index) {
		switch (index) {
		case 0:
			return null;
		default:
			return super.getPropertyValue(index);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		String s;

		s = "Phases=1 ";
		s = s + String.format("basekV=%-.5g ", kVBase / DSSGlobals.SQRT3);
		s = s + String.format("r1=%-.5g ", R1);
		s = s + String.format("x1=%-.5g ", X1);

		Parser.getInstance().setCmdString(s);
		edit();

		super.makePosSequence();
	}

	// Private method in OpenDSS
	public int doTerminalsDef(int n) {
		int result = nTerms;

		if (n != nTerms)
			if (n > 0)
				reallocRX();

		return result;
	}

	/**
	 * Parse input string as an array.
	 */
	// Private method in OpenDSS
	public void parseDblMatrix(double[] mat) {
		Parser.getInstance().parseAsSymMatrix(nTerms, mat);
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
		return ZInv;
	}

	public void setZInv(CMatrix zinv) {
		ZInv = zinv;
	}

	// FIXME Private members in OpenDSS

	public double getKVBase() {
		return kVBase;
	}

	public void setKVBase(double base) {
		this.kVBase = base;
	}

	public double getVMag() {
		return VMag;
	}

	public void setVMag(double mag) {
		VMag = mag;
	}

	public double getPerUnit() {
		return perUnit;
	}

	public void setPerUnit(double pu) {
		perUnit = pu;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double ang) {
		angle = ang;
	}

	public double getEquivFrequency() {
		return equivFrequency;
	}

	public void setEquivFrequency(double frequency) {
		equivFrequency = frequency;
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
		return needToDoRecalc;
	}

	public void setNeedToDoRecalc(boolean value) {
		needToDoRecalc = value;
	}

}
