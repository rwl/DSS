/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.shared.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.general.SpectrumObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.MathUtil;

public class VSourceObj extends PCElement {

	private double MVAsc3;
	private double MVAsc1;
	private double Isc3;
	private double Isc1;
	private ZSpecType ZspecType;
	private double R1, X1;
	private double R0, X0;
	private double X1R1;
	private double X0R0;

	private SequenceType scanType;
	private SequenceType sequenceType;

	protected CMatrix Z;  // base frequency series Z matrix
	protected CMatrix Zinv;
	protected double Vmag;

	protected double kVBase;
	protected double perUnit;
	protected double angle;
	protected double srcFrequency;

	public VSourceObj(DSSClass parClass, String sourceName) {
		super(parClass);

		setName(sourceName.toLowerCase());
		objType = parClass.getClassType(); //SOURCE + NON_PCPD_ELEM;  // don't want this in PC element list

		setNumPhases(3);
		nConds = 3;
		setNumTerms(2);  // now a 2-terminal device

		Z = null;
		Zinv = null;

		MVAsc3 = 2000.0;
		MVAsc1 = 2100.0;
		ZspecType = ZSpecType.MVASC;
		R1 = 1.65;
		X1 = 6.6;
		R0 = 1.9;
		X0 = 5.7;
		Isc3 = 10000.0;
		Isc1 = 10540.0;
		kVBase = 115.0;
		X1R1 = 4.0;
		X0R0 = 3.0;
		perUnit = 1.0;
		srcFrequency = baseFrequency;
		angle = 0.0;
		scanType = SequenceType.POS;
		sequenceType = SequenceType.POS;

		spectrum = "defaultvsource";

		initPropertyValues(0);

		YOrder = nTerms * nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		Complex Zs, Zm;
		int i, j;

		double factor;
		double Rs, Xs, Rm, Xm;

		// for a source, nPhases = nCond, for now
		Z  = new CMatrix(nPhases);
		Zinv = new CMatrix(nPhases);

		factor = (nPhases == 1) ? 1.0 : DSS.SQRT3;

		Rs = 0.0;
		Rm = 0.0;
		Xs = 0.1;
		Xm = 0.0;

		/* Calculate the short circuit impedance and make all other spec types agree */
		switch (ZspecType) {
		case MVASC:
			X1 = MathUtil.sqr(kVBase) / MVAsc3 / Math.sqrt(1.0 + 1.0 / MathUtil.sqr(X1R1));
			Xs = MathUtil.sqr(kVBase) / MVAsc1 / Math.sqrt(1.0 + 1.0 / MathUtil.sqr(X0R0));  // approx
			R1 = X1 / X1R1;
			Xm = Xs - X1;
			X0 = (Xs + 2.0 * Xm);
			R0 = X0 / X0R0;
			Isc3 = MVAsc3 * 1000.0 / (DSS.SQRT3 * kVBase);
			Isc1 = MVAsc1 * 1000.0 / (factor * kVBase);

			Rs = (nPhases == 1) ? Xs / X0R0 : (2.0 * R1 + R0) / 3.0;

			Rm = (R0 - R1) / 3.0;
			break;

		case ISC:
			MVAsc3 = DSS.SQRT3 * kVBase * Isc3 / 1000.0;
			MVAsc1 = factor * kVBase * Isc1 / 1000.0;
			X1 = MathUtil.sqr(kVBase) / MVAsc3 / Math.sqrt(1.0 + 1.0 / MathUtil.sqr(X1R1));
			Xs = MathUtil.sqr(kVBase) / MVAsc1 / Math.sqrt(1.0 + 1.0 / MathUtil.sqr(X0R0));  // approx
			R1 = X1 / X1R1;
			Xm = Xs - X1;
			X0 = (Xs + 2.0 * Xm);
			R0 = X0 / X0R0;

			Rs = (nPhases == 1) ? Xs / X0R0 : (2.0 * R1 + R0) / 3.0;

			Rm = (R0 - R1) / 3.0;
			break;

		case Z1Z0:
			Isc3 = kVBase * 1000.0 / DSS.SQRT3 / new Complex(R1, X1).abs();

			if (nPhases == 1) {  // force Z0 to be Z1 so Zs is same as Z1
				R0 = R1;
				X0 = X1;
			}
			Rs = (2.0 * R1 + R0) / 3.0;
			Xs = (2.0 * X1 + X0) / 3.0;

			Isc1 = kVBase * 1000.0 / factor / new Complex(Rs, Xs).abs();
			MVAsc3 = DSS.SQRT3 * kVBase * Isc3 / 1000.0;
			MVAsc1 = factor * kVBase * Isc1 / 1000.0;
			Xm = Xs - X1;

			Rs = (2.0 * R1 + R0) / 3.0;
			Rm = (R0 - R1) / 3.0;
			break;
		}

		/* Update property value array */
		/* Don't change a specified value; only computed ones */

		Zs = new Complex(Rs, Xs);
		Zm = new Complex(Rm, Xm);

		for (i = 0; i < nPhases; i++) {
			Z.set(i, i, Zs);
			for (j = 0; j < i; j++) {
				Z.setSym(i, j, Zm);
			}
		}

		switch (nPhases) {
		case 1:
			Vmag = kVBase * perUnit * 1000.0;
			break;
		default:
			Vmag = kVBase * perUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases) * Math.PI / 180.0);
			break;
		}

		setSpectrumObj((SpectrumObj) DSS.spectrumClass.find(getSpectrum()));
		if (getSpectrumObj() == null)
			DSS.doSimpleMsg("Spectrum object \"" + getSpectrum() + "\" for device VSource." +
					getName() + " not found.", 324);

		setInjCurrent( Util.resizeArray(getInjCurrent(), YOrder) );
	}

	@Override
	public void calcYPrim() {
		Complex value;
		int i, j;
		double freqMultiplier;

		// build only YPrim_Series
		if (isYprimInvalid()) {
			YPrimSeries = new CMatrix(YOrder);
			YPrim = new CMatrix(YOrder);
		} else {
			YPrimSeries.clear();
			YPrim.clear();
		}

		YPrimFreq = DSS.activeCircuit.getSolution().getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;

		/* Put in series RL adjusted for frequency */
		for (i = 0; i < nPhases; i++) {
			for (j = 0; j < nPhases; j++) {
				value = Z.get(i, j);
				value = new Complex(
					value.real(),
					value.imag() * freqMultiplier
				);  /* Modify from base freq */
				Zinv.set(i, j, value);
			}
		}

		Zinv.invert();  /* Invert in place */

		if (Zinv.getErrorCode() > 0) {
			/* If error, put in large series conductance */
			DSS.doErrorMsg("VsourceObj.calcYPrim", "Matrix inversion error for VSource \"" + getName() + "\"",
					"Invalid impedance specified. Replaced with small resistance.", 325);
			Zinv.clear();
			for (i = 0; i < nPhases; i++) {
				Zinv.set(i, i, new Complex(1.0 / DSS.EPSILON, 0.0));
			}
		}

		// YPrim_Series.copyFrom(Zinv);

		for (i = 0; i < nPhases; i++) {
			for (j = 0; j < nPhases; j++) {
				value = Zinv.get(i, j);
				YPrimSeries.set(i, j, value);
				YPrimSeries.set(i + nPhases, j + nPhases, value);
				YPrimSeries.setSym(i + nPhases, j, value.neg());
			}
		}

		YPrim.copyFrom(YPrimSeries);

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */
		super.calcYPrim();

		setYPrimInvalid(false);
	}

	private void getVTerminalForSource() {
		int i;
		Complex VHarm;
		double srcHarmonic;

		SolutionObj sol = DSS.activeCircuit.getSolution();

		try {
			/* This formulation will theoretically handle voltage sources of any
			 * number of phases assuming they are equally displaced in time.
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
				srcHarmonic = sol.getFrequency() / srcFrequency;
				VHarm = getSpectrumObj().getMult(srcHarmonic).mult(Vmag);  // base voltage for this harmonic
				VHarm = Util.rotatePhasorDeg(VHarm, srcHarmonic, angle);  // rotate for phase 1 shift
				for (i = 0; i < nPhases; i++) {
					VTerminal[i] = VHarm;
					VTerminal[i + nPhases] = Complex.ZERO;
					if (i < nPhases)
						switch (scanType) {
						case POS:
							VHarm = Util.rotatePhasorDeg(VHarm, 1.0, -360.0 / nPhases); // maintain pos seq
							break;
						case ZERO:
							// do nothing for zero sequence; all the same.
							break;
						default:
							VHarm = Util.rotatePhasorDeg(VHarm, srcHarmonic, -360.0 / nPhases);  // normal rotation
							break;
						}
				}
			} else {

				if (Math.abs(sol.getFrequency() - srcFrequency) > DSS.EPSILON2)
					Vmag = 0.0;  // solution frequency and source frequency don't match

				/* NOTE: RE-uses VTerminal space */
				for (i = 0; i < nPhases; i++) {
					switch (sequenceType) {
					case NONE:
						VTerminal[i] = Complex.fromPolarDeg(Vmag, (360.0 + angle + i * 360.0/nPhases));  // neg seq
						break;
					case ZERO:
						VTerminal[i] = Complex.fromPolarDeg(Vmag, (360.0 + angle));  // all the same for zero sequence
						break;
					default:
						VTerminal[i] = Complex.fromPolarDeg(Vmag, (360.0 + angle - i * 360.0 / nPhases));
						break;
					}
					VTerminal[i + nPhases] = Complex.ZERO;  // see comments in getInjCurrents
				}
			}
		} catch (Exception e) {
			DSS.doSimpleMsg("Error computing voltages for VSource." + getName() + ". Check specification. Aborting.", 326);
			if (DSS.inRedirect) DSS.redirectAbort = true;
		}
	}

	@Override
	public int injCurrents() {
		getInjCurrents(injCurrent);

		/* This is source injection */

		return super.injCurrents();  // add into system array
	}

	@Override
	public void getCurrents(Complex[] curr) {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		try {
			for (int i = 0; i < YOrder; i++)
				VTerminal[i] = sol.getNodeV(nodeRef[i]);

			YPrim.vMult(curr, VTerminal);  // current from elements in system Y

			getInjCurrents(complexBuffer);  // get present value of inj currents

			// add together with Yprim currents
			for (int i = 0; i < YOrder; i++)
				curr[i] = curr[i].sub(complexBuffer[i]);

		} catch (Exception e) {
			DSS.doErrorMsg("getCurrents for element: " + getName() + ".", e.getMessage(),
					"Inadequate storage allotted for circuit element.", 327);
		}
	}

	/**
	 * Source injection currents given by this formula:
	 * 		_     _           _         _
	 * 		|Iinj1|           | Vsource |
	 * 		|     | = [Yprim] |         |
	 * 		|Iinj2|           | 0       |
	 * 		_     _           _         _
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {

		getVTerminalForSource();  // gets voltage vector above
		YPrim.vMult(curr, VTerminal);

		setITerminalUpdated(false);
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		Complex c;

		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			pw.println("~ " + getParentClass().getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete) {
			pw.println();
			pw.println("baseFrequency=" + baseFrequency);
			pw.println("vMag=" + Vmag);
			pw.println("zMatrix=");
			for (int i = 0; i < nPhases; i++) {
				for (int j = 0; j <= i; j++) {
					c = Z.get(i, j);
					pw.printf("%.8g +j %.8g ", c.real(), c.imag());
				}
				pw.println();
			}
		}
		pw.close();
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, getBus(0));
		setPropertyValue(1, "115");
		setPropertyValue(2, "1");
		setPropertyValue(3, "0");
		setPropertyValue(4, String.format("%d", Math.round(DSS.defaultBaseFreq)));
		setPropertyValue(5, "3");
		setPropertyValue(6, "2000");
		setPropertyValue(7, "2100");
		setPropertyValue(8, "4");
		setPropertyValue(9, "3");
		setPropertyValue(10, "10000");
		setPropertyValue(11, "10500");
		setPropertyValue(12, "1.65");
		setPropertyValue(13, "6.6");
		setPropertyValue(14, "1.9");
		setPropertyValue(15, "5.7");
		setPropertyValue(16, "Pos");
		setPropertyValue(17, "Pos");
		setPropertyValue(18, getBus(1));

		super.initPropertyValues(VSource.NumPropsThisClass - 1);
	}

	@Override
	public String getPropertyValue(int index) {
		switch (index) {
		case 0:
			return getBus(0);
		case 6:
			return String.format("%-.5g", MVAsc3);
		case 7:
			return String.format("%-.5g", MVAsc1);
		case 10:
			return String.format("%-.5g", Isc3);
		case 11:
			return String.format("%-.5g", Isc1);
		case 12:
			return String.format("%-.5g", R1);
		case 13:
			return String.format("%-.5g", X1);
		case 14:
			return String.format("%-.5g", R0);
		case 15:
			return String.format("%-.5g", X0);
		case 18:
			return getBus(1);
		default:
			return super.getPropertyValue(index);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		String s = "phases=1 ";
		s = s + String.format("basekV=%-.5g ", kVBase / DSS.SQRT3);
		s = s + String.format("r1=%-.5g ", R1);
		s = s + String.format("x1=%-.5g ", X1);

		Parser.getInstance().setCommand(s);
		edit();

		super.makePosSequence();
	}

	public double getX1R1() {
		return X1R1;
	}

	public void setX1R1(double x1r1) {
		X1R1 = x1r1;
	}

	public double getX0R0() {
		return X0R0;
	}

	public void setX0R0(double x0r0) {
		X0R0 = x0r0;
	}

	public SequenceType getScanType() {
		return scanType;
	}

	public void setScanType(SequenceType scanType) {
		this.scanType = scanType;
	}

	public SequenceType getSequenceType() {
		return sequenceType;
	}

	public void setSequenceType(SequenceType sequenceType) {
		this.sequenceType = sequenceType;
	}

	public CMatrix getZ() {
		return Z;
	}

	public void setZ(CMatrix z) {
		Z = z;
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

	public double getSrcFrequency() {
		return srcFrequency;
	}

	public void setSrcFrequency(double srcFrequency) {
		this.srcFrequency = srcFrequency;
	}

	public double getMVAsc3() {
		return MVAsc3;
	}

	public double getMVAsc1() {
		return MVAsc1;
	}

	public double getVmag() {
		return Vmag;
	}

	public double getKVBase() {
		return kVBase;
	}

	public void setIsc3(double isc3) {
		Isc3 = isc3;
	}

	public void setIsc1(double isc1) {
		Isc1 = isc1;
	}

	public void setZspecType(ZSpecType zspecType) {
		ZspecType = zspecType;
	}

	public void setR1(double r1) {
		R1 = r1;
	}

	public void setX1(double x1) {
		X1 = x1;
	}

	public void setR0(double r0) {
		R0 = r0;
	}

	public void setX0(double x0) {
		X0 = x0;
	}

	public void setZinv(CMatrix zinv) {
		Zinv = zinv;
	}

	public void setMVAsc3(double mVAsc3) {
		MVAsc3 = mVAsc3;
	}

	public void setMVAsc1(double mVAsc1) {
		MVAsc1 = mVAsc1;
	}

	public void setVmag(double vmag) {
		Vmag = vmag;
	}

	public void setKVBase(double kVBase) {
		this.kVBase = kVBase;
	}

}
