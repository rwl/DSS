/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.general.SpectrumObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.ComplexUtil;

public class EquivalentObj extends PCElement {

	private double kVBase;

	private double Vmag;
	private double perUnit;
	private double angle;
	private double equivFrequency;

	private double[] R1, X1, R0, X0;

	private boolean needToDoRecalc;

	// base frequency series Z matrix
	protected CMatrix Z;
	protected CMatrix Zinv;

	public EquivalentObj(DSSClass parClass, String sourceName) {
		super(parClass);

		setName(sourceName.toLowerCase());
		objType = parClass.getClassType(); //SOURCE + NON_PCPD_ELEM;  // don't want this in PC element list

		setNumPhases(3);
		nConds = 3;
		setNumTerms(1);

		Z    = null;
		Zinv = null;

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

	@Override
	public void recalcElementData() {
		Complex Zs, Zm;
		int i, j, ii, jj;
		int ioffset, joffset, indx;

		// for a source, nPhases = nCond, for now
		Z = new CMatrix(nPhases * nTerms);
		Zinv = new CMatrix(nPhases * nTerms);

		// build Z matrix for all phases
		for (i = 0; i < nTerms; i++) {
			for (j = 0; j < nTerms; j++) {
				indx = idx(i, j);
				Zs = ComplexUtil.divide(new Complex(
					2.0 * R1[indx] + R0[indx],
					2.0 * X1[indx] + X0[indx]
				), 3.0);
				Zm = ComplexUtil.divide(new Complex(
					R0[indx] - R1[indx],
					X0[indx] - X1[indx]
				), 3.0);

				ioffset = i * nPhases;
				joffset = j * nPhases;

				for (ii = 0; ii < nPhases; ii++) {
					for (jj = 0; jj < ii; jj++) {
						if (ii == jj) {
							Z.set(ii + ioffset, jj + joffset, Zs);
						} else {
							Z.set(ii + ioffset, jj + joffset, Zm);
							// set other off-diagonal in this submatrix
							Z.set(jj + ioffset, ii + joffset, Zm);
						}
					}
				}
			}
		}

		// voltage source properties
		switch (nPhases) {
		case 1:
			Vmag = kVBase * perUnit * 1000.0;
			break;
		default:
			Vmag = kVBase * perUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases) * Math.PI / 180.0);
			break;
		}

		setSpectrumObj((SpectrumObj) DSS.spectrumClass.find(spectrum));
		if (getSpectrumObj() == null)
			DSS.doSimpleMsg("Spectrum object \"" + getSpectrum() +
				"\" for device equivalent." + getName() + " not found.", 802);

		setInjCurrent(Util.resizeArray(getInjCurrent(), YOrder));

		needToDoRecalc = false;
	}

	private int idx(int a, int b) {
		return b * nTerms + a;
	}

	@Override
	public void calcYPrim() {
		Complex c;
		int i, j;
		double freqMultiplier;

		// build only YPrim series
		if (isYprimInvalid()) {
			YPrimSeries = new CMatrix(YOrder);
			YPrim = new CMatrix(YOrder);
		} else {
			YPrimSeries.clear();
			YPrim.clear();
		}

		if (needToDoRecalc) recalcElementData();

		YPrimFreq = DSS.activeCircuit.getSolution().getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;

		/* Put in series RL matrix adjusted for frequency */
		for (i = 0; i < YOrder; i++) {
			for (j = 0; j < YOrder; j++) {
				c = Z.get(i, j);
				/* Modify from base freq */
				c = new Complex(c.getReal(), c.getImaginary() * freqMultiplier);
				Zinv.set(i, j, c);
			}
		}

		Zinv.invert();  /* Invert in place */

		if (Zinv.getErrorCode() > 0) {
			/* If error, put in large series conductance */
			DSS.doErrorMsg("EquivalentObj.calcYPrim", "Matrix inversion error for equivalent \"" + getName() + "\"",
					"Invalid impedance specified. Replaced with small resistance.", 803);
			Zinv.clear();
			for (i = 0; i < nPhases; i++)
				Zinv.set(i, i, new Complex(1.0 / DSS.EPSILON, 0.0));
		}

		YPrimSeries.copyFrom(Zinv);

		YPrim.copyFrom(YPrimSeries);

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */
		super.calcYPrim();

		setYPrimInvalid(false);
	}

	private void getVterminalForSource() {
		int i;
		Complex Vharm;
		double equivHarm;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		try {
			/* This formulation will theoretically handle voltage sources of any number
			 * of phases assuming they are equally displaced in time.
			 */
			switch (nPhases) {
			case 1:
				Vmag = kVBase * perUnit * 1000.0;
				break;
			default:
				Vmag = kVBase * perUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases) * Math.PI / 180.0);
				break;
			}

			if (sol.isHarmonicModel()) {
				equivHarm = sol.getFrequency() / equivFrequency;
				Vharm = getSpectrumObj().getMult(equivHarm).multiply(Vmag);  // base voltage for this harmonic
				Vharm = Util.rotatePhasorDeg(Vharm, equivHarm, angle);  // rotate for phase 1 shift
				for (i = 0; i < nPhases; i++) {
					VTerminal[i] = Vharm;
					if (i < nPhases - 1) {
						Vharm = Util.rotatePhasorDeg(Vharm, equivHarm, -360.0 / nPhases);
					}
				}
			} else {
				for (i = 0; i < nPhases; i++)
					VTerminal[i] = ComplexUtil.polarDeg2Complex(Vmag, (360.0 + angle - i * 360.0 / nPhases));
			}

		} catch (Exception e) {
			DSS.doSimpleMsg("Error computing voltages for Equivalent." + getName() +
					". Check specification. Aborting.", 804);
			if (DSS.inRedirect) DSS.redirectAbort = true;
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
		SolutionObj sol = DSS.activeCircuit.getSolution();

		try {
			for (i = 0; i < YOrder; i++)
				VTerminal[i] = sol.getNodeV(nodeRef[i]);

			YPrim.vMult(curr, VTerminal);

			getInjCurrents(complexBuffer);  // get present value of inj currents

			// add together with Yprim currents
			for (i = 0; i < YOrder; i++)
				curr[i] = curr[i].subtract(complexBuffer[i]);

		} catch (Exception e) {
			DSS.doErrorMsg(("getCurrents for element: " + getName()), e.getMessage(),
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
	public void dumpProperties(OutputStream out, boolean complete) {
		int i, j;
		Complex c;

		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		DSSClass pc = getParentClass();
		for (i = 0; i < pc.getNumProperties(); i++)
			pw.println("~ " + pc.getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete) {
			pw.println();
			pw.println("baseFrequency=" + baseFrequency);
			pw.println("vMag=" + Vmag);
			pw.println("zMatrix=");
			for (i = 0; i < nPhases; i++) {
				for (j = 0; j < i; j++) {
					c = Z.get(i, j);
					pw.print(c.getReal() + " + j" + c.getImaginary());
				}
				pw.println();
			}
		}
		pw.close();
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

		s = "phases=1 ";
		s = s + String.format("basekV=%-.5g ", kVBase / DSS.SQRT3);
		s = s + String.format("r1=%-.5g ", R1);
		s = s + String.format("x1=%-.5g ", X1);

		Parser.getInstance().setCommand(s);
		edit();

		super.makePosSequence();
	}

	protected int doTerminalsDef(int n) {
		if (n != nTerms)
			if (n > 0)
				reallocRX();

		return nTerms;
	}

	/**
	 * Parse input string as an array.
	 */
	protected void parseDblMatrix(double[] mat) {
		Parser.getInstance().parseAsSymMatrix(nTerms, mat);
	}

	private void reallocRX() {
		R1 = Util.resizeArray(R1, (int) Math.pow(nTerms, 2));
		X1 = Util.resizeArray(X1, (int) Math.pow(nTerms, 2));
		R0 = Util.resizeArray(R0, (int) Math.pow(nTerms, 2));
		X0 = Util.resizeArray(X0, (int) Math.pow(nTerms, 2));
	}

	public double getKVBase() {
		return kVBase;
	}

	public void setKVBase(double kVBase) {
		this.kVBase = kVBase;
	}

	public double getPerUnit() {
		return perUnit;
	}

	public void setPerUnit(double perUnit) {
		this.perUnit = perUnit;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getEquivFrequency() {
		return equivFrequency;
	}

	public void setEquivFrequency(double equivFrequency) {
		this.equivFrequency = equivFrequency;
	}

	public CMatrix getZ() {
		return Z;
	}

	public void setZ(CMatrix z) {
		Z = z;
	}

	public double getVmag() {
		return Vmag;
	}

	public double[] getR1() {
		return R1;
	}

	public double[] getX1() {
		return X1;
	}

	public double[] getR0() {
		return R0;
	}

	public double[] getX0() {
		return X0;
	}

	public void setNeedToDoRecalc(boolean needToDoRecalc) {
		this.needToDoRecalc = needToDoRecalc;
	}

	public void setZinv(CMatrix zinv) {
		Zinv = zinv;
	}

	public void setVmag(double vmag) {
		Vmag = vmag;
	}

}
