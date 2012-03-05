package com.ncond.dss.conversion.impl;

import java.io.PrintStream;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.impl.DSSClassImpl;
import com.ncond.dss.common.impl.DSSGlobals;
import com.ncond.dss.common.impl.Utilities;
import com.ncond.dss.conversion.Equivalent;
import com.ncond.dss.conversion.EquivalentObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.impl.CMatrixImpl;
import com.ncond.dss.shared.impl.ComplexUtil;

import org.apache.commons.math.complex.Complex;

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
		objType = parClass.getDSSClassType(); //SOURCE + NON_PCPD_ELEM;  // don't want this in PC element list

		setNPhases(3);
		nConds = 3;
		setNTerms(1);
		Z    = null;
		ZInv = null;
		/*Basefrequency = 60.0;*/  // set in base class

		R1 = null;
		X1 = null;
		R0 = null;
		X0 = null;

		reallocRX();

		R1[0] = 1.65;
		X1[0] = 6.6;
		R0[0] = 1.9;
		X0[0] = 5.7;

		kVBase = 115.0;
		perUnit = 1.0;
		equivFrequency = baseFrequency;
		angle = 0.0;

		spectrum = "defaultvsource";

		initPropertyValues(0);

		YOrder = nTerms * nConds;
		recalcElementData();
	}

	private int idx(int a, int b) {
		return b * nTerms + a;
	}

	@Override
	public void recalcElementData() {
		Complex Zs, Zm;
//		int i, j, ii, jj;
		int iOffset, jOffset, indx;

		// for a source, nPhases = nCond, for now
		Z    = new CMatrixImpl(nPhases * nTerms);
		ZInv = new CMatrixImpl(nPhases * nTerms);

		// build Z matrix for all phases
		for (int i = 0; i < nTerms; i++)
			for (int j = 0; j < nTerms; j++) {
				indx = idx(i, j);
				Zs = ComplexUtil.divide(new Complex(2.0 * R1[indx] + R0[indx], 2.0 * X1[indx] + X0[indx] ), 3.0);
				Zm = ComplexUtil.divide(new Complex(R0[indx] - R1[indx], X0[indx] - X1[indx]), 3.0);

				iOffset = i * nPhases;
				jOffset = j * nPhases;

				for (int ii = 0; ii < nPhases; ii++) {
					for (int jj = 0; jj < ii; jj++) {
						if (ii == jj) {
							Z.set(ii + iOffset, jj + jOffset, Zs);
						} else {
							Z.set(ii + iOffset, jj + jOffset, Zm);
							Z.set(jj + iOffset, ii + jOffset, Zm);  // set other off-diagonal in this submatrix
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

		setSpectrumObj((com.ncond.dss.general.SpectrumObj) DSSGlobals.spectrumClass.find(getSpectrum()));
		if (getSpectrumObj() == null)
			DSSGlobals.doSimpleMsg("Spectrum object \"" + getSpectrum() + "\" for device equivalent."+getName()+" not found.", 802);

		setInjCurrent( Utilities.resizeArray(getInjCurrent(), YOrder) );

		needToDoRecalc = false;
	}

	@Override
	public void calcYPrim() {
		Complex value;
		int i, j;
		double freqMultiplier;

		// build only YPrim series
		if (isYprimInvalid()) {
			YPrimSeries = new CMatrixImpl(YOrder);
			YPrim = new CMatrixImpl(YOrder);
		} else {
			YPrimSeries.clear();
			YPrim.clear();
		}

		if (needToDoRecalc)
			recalcElementData();

		YPrimFreq = DSSGlobals.activeCircuit.getSolution().getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;

		/* Put in Series RL matrix adjusted for frequency */
		for (i = 0; i < YOrder; i++) {
			for (j = 0; j < YOrder; j++) {
				value = Z.get(i, j);
				/* Modify from base freq */
				value = new Complex(value.getReal(), value.getImaginary() * freqMultiplier);
				ZInv.set(i, j, value);
			}
		}

		ZInv.invert();  /* Invert in place */

		if (ZInv.getErrorCode() > 0) {
			/* If error, put in large series conductance */
			DSSGlobals.doErrorMsg("EquivalentObj.calcYPrim", "Matrix inversion error for equivalent \"" + getName() + "\"",
					"Invalid impedance specified. Replaced with small resistance.", 803);
			ZInv.clear();
			for (i = 0; i < nPhases; i++)
				ZInv.set(i, i, new Complex(1.0 / DSSGlobals.EPSILON, 0.0));
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
		SolutionObj sol;

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

			sol = DSSGlobals.activeCircuit.getSolution();

			if (sol.isHarmonicModel()) {
				equivHarm = sol.getFrequency() / equivFrequency;
				VHarm = getSpectrumObj().getMult(equivHarm).multiply(VMag);  // base voltage for this harmonic
				VHarm = Utilities.rotatePhasorDeg(VHarm, equivHarm, angle);  // rotate for phase 1 shift
				for (i = 0; i < nPhases; i++) {
					VTerminal[i] = VHarm;
					if (i < nPhases)
						VHarm = Utilities.rotatePhasorDeg(VHarm, equivHarm, -360.0 / nPhases);
				}
			} else {
				for (i = 0; i < nPhases; i++)
					VTerminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + angle - i * 360.0 / nPhases));
			}

		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error computing voltages for Equivalent."+getName()+". Check specification. Aborting.", 804);
			if (DSSGlobals.inRedirect)
				DSSGlobals.redirectAbort = true;
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
		SolutionObj sol;

		try {
			sol = DSSGlobals.activeCircuit.getSolution();
			for (i = 0; i < YOrder; i++)
				VTerminal[i] = sol.getNodeV(nodeRef[i]);

			YPrim.vMult(curr, VTerminal);

			getInjCurrents(complexBuffer);  // get present value of inj currents
			// add together with Yprim currents
			for (i = 0; i < YOrder; i++)
				curr[i] = curr[i].subtract(complexBuffer[i]);

		} catch (Exception e) {
			DSSGlobals.doErrorMsg(("GetCurrents for element: " + getName() + "."), e.getMessage(),
					"Inadequate storage allotted for circuit element.", 805);
		}
	}

	@Override
	public void getInjCurrents(Complex[] curr) {

		getVterminalForSource();
		YPrim.vMult(curr, VTerminal);  /* I = Y V */

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
					c = Z.get(i, j);
					f.print(c.getReal() + " + j" + c.getImaginary());
				}
				f.println();
			}
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		setPropertyValue(0, "1");
		setPropertyValue(1, getBus(0));
		setPropertyValue(2, "115");
		setPropertyValue(3, "1");
		setPropertyValue(4, "0");
		setPropertyValue(5, "60");
		setPropertyValue(6, "3");
		setPropertyValue(7, "1.65");
		setPropertyValue(8, "6.6");
		setPropertyValue(9, "1.9");
		setPropertyValue(10, "5.7");

		super.initPropertyValues(Equivalent.NumPropsThisClass - 1);
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
		R1 = Utilities.resizeArray(R1, (int) Math.pow(nTerms, 2));
		X1 = Utilities.resizeArray(X1, (int) Math.pow(nTerms, 2));
		R0 = Utilities.resizeArray(R0, (int) Math.pow(nTerms, 2));
		X0 = Utilities.resizeArray(X0, (int) Math.pow(nTerms, 2));
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

	public double getKvBase() {
		return kVBase;
	}

	public void setKvBase(double base) {
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
