package com.ncond.dss.conversion;

import java.io.OutputStream;
import java.io.PrintWriter;


import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.ComplexUtil;

public class VSourceObj extends PCElement {

	private double MVAsc3;
	private double MVAsc1;
	private double Isc3;
	private double Isc1;
	private int ZSpecType;
	private double R1, X1;
	private double R0, X0;
	private double X1R1;
	private double X0R0;

	private int scanType;
	private int sequenceType;

	protected CMatrix Z;  // base frequency series Z matrix
	protected CMatrix ZInv;
	protected double VMag;

	protected double kVBase;
	protected double perUnit;
	protected double angle;
	protected double srcFrequency;

	public VSourceObj(DSSClass parClass, String sourceName) {
		super(parClass);

		setName(sourceName.toLowerCase());
		objType = parClass.getDSSClassType(); //SOURCE + NON_PCPD_ELEM;  // don't want this in PC element list

		setNumPhases(3);
		nConds = 3;
		setNumTerms(2);  // now a 2-terminal device
		Z        = null;
		ZInv     = null;
		//Basefrequency = 60.0;  // set in base class
		MVAsc3   = 2000.0;
		MVAsc1   = 2100.0;
		ZSpecType = 1;  // default to MVAsc
		R1       = 1.65;
		X1       = 6.6;
		R0       = 1.9;
		X0       = 5.7;
		Isc3     = 10000.0;
		Isc1     = 10540.0;
		kVBase   = 115.0;
		X1R1     = 4.0;
		X0R0     = 3.0;
		perUnit  = 1.0;
		srcFrequency = baseFrequency;
		angle    = 0.0;
		scanType = 1;
		sequenceType = 1;

		spectrum = "defaultvsource";

		initPropertyValues(0);

		YOrder = nTerms * nConds;
		recalcElementData();
	}

	public void recalcElementData() {

		Complex Zs, Zm;
		int i, j;

		double factor;
		double Rs, Xs, Rm, Xm;

		// for a source, nPhases = nCond, for now
		Z    = new CMatrix(nPhases);
		ZInv = new CMatrix(nPhases);

		if (nPhases == 1) {
			factor = 1.0;
		} else {
			factor = DSS.SQRT3;
		}

		Rs = 0.0;
		Rm = 0.0;
		Xs = 0.1;
		Xm = 0.0;

		/* Calculate the short circuit impedance and make all other spec types agree */
		switch (ZSpecType) {
		case 1:  // MVAsc
			X1   = Math.pow(kVBase, 2) / MVAsc3 / Math.sqrt(1.0 + 1.0 / Math.pow(X1R1, 2));
			Xs   = Math.pow(kVBase, 2) / MVAsc1 / Math.sqrt(1.0 + 1.0 / Math.pow(X0R0, 2));  // approx
			R1   = X1 / X1R1;
			Xm   = Xs - X1;
			X0   = (Xs + 2.0 * Xm);
			R0   = X0 / X0R0;
			Isc3 = MVAsc3 * 1000.0 / (DSS.SQRT3 * kVBase);
			Isc1 = MVAsc1 * 1000.0 /(factor * kVBase);

			if (nPhases == 1) {
				Rs = Xs / X0R0;
			} else {
				Rs = (2.0 * R1 + R0) / 3.0;
			}

			Rm = (R0 - R1) / 3.0;
			break;

		case 2:  // Isc
			MVAsc3 = DSS.SQRT3 * kVBase * Isc3 / 1000.0;
			MVAsc1 = factor * kVBase * Isc1 / 1000.0;
			X1 = Math.pow(kVBase, 2) / MVAsc3 / Math.sqrt(1.0 + 1.0 / Math.pow(X1R1, 2));
			Xs = Math.pow(kVBase, 2) / MVAsc1 / Math.sqrt(1.0 + 1.0 / Math.pow(X0R0, 2));  // approx
			R1 = X1 / X1R1;
			Xm = Xs - X1;
			X0 = (Xs + 2.0 * Xm);
			R0 = X0 / X0R0;

			if (nPhases == 1) {
				Rs = Xs / X0R0;
			} else {
				Rs = (2.0 * R1 + R0) / 3.0;
			}

			Rm = (R0 - R1) / 3.0;
			break;

		case 3:  // Z1, Z0; specified
			Isc3 = kVBase * 1000.0 / DSS.SQRT3 / new Complex(R1, X1).abs();

			if (nPhases == 1) {  // force Z0 to be Z1 so Zs is same as Z1
				R0 = R1;
				X0 = X1;
			}
			Rs = (2.0 * R1 + R0) / 3.0;
			Xs = (2.0 * X1 + X0) / 3.0;

			Isc1   = kVBase * 1000.0 / factor / new Complex(Rs, Xs).abs();
			MVAsc3 = DSS.SQRT3 * kVBase * Isc3 / 1000.0;
			MVAsc1 = factor * kVBase * Isc1 / 1000.0;
			Xm     = Xs - X1;

			Rs     = (2.0 * R1 + R0) / 3.0;
			Rm     = (R0 - R1) / 3.0;
			break;
		}

		/* Update property value array */
		/* Don't change a specified value; only computed ones */

		Zs = new Complex(Rs, Xs);
		Zm = new Complex(Rm, Xm);

		for (i = 0; i < nPhases; i++) {
			Z.set(i, i, Zs);
			for (j = 0; j < i - 1; j++)
				Z.setSym(i, j, Zm);
		}

		switch (nPhases) {
		case 1:
			VMag = kVBase * perUnit * 1000.0;
			break;
		default:
			VMag = kVBase * perUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases) * Math.PI / 180.0);
			break;
		}

		setSpectrumObj( (com.ncond.dss.general.SpectrumObj) DSS.spectrumClass.find(getSpectrum()) );
		if (getSpectrumObj() == null)
			DSS.doSimpleMsg("Spectrum object \"" + getSpectrum() + "\" for device VSource."+getName()+" not found.", 324);

		setInjCurrent( Util.resizeArray(getInjCurrent(), YOrder) );
	}

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
				value = new Complex(value.getReal(), value.getImaginary() * freqMultiplier);  /* Modify from base freq */
				ZInv.set(i, j, value);
			}
		}

		ZInv.invert();  /* Invert in place */

		if (ZInv.getErrorCode() > 0) {
			/* If error, put in large series conductance */
			DSS.doErrorMsg("VsourceObj.calcYPrim", "Matrix inversion error for VSource \"" + getName() + "\"",
					"Invalid impedance specified. Replaced with small resistance.", 325);
			ZInv.clear();
			for (i = 0; i < nPhases; i++)
				ZInv.set(i, i, new Complex(1.0 / DSS.EPSILON, 0.0));
		}

		// YPrim_Series.copyFrom(Zinv);

		for (i = 0; i < nPhases; i++) {
			for (j = 0; j < nPhases; j++) {
				value = ZInv.get(i, j);
				YPrimSeries.set(i, j, value);
				YPrimSeries.set(i + nPhases, j + nPhases, value);
				YPrimSeries.setSym(i + nPhases, j, value.negate());
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

		try {
			/* This formulation will theoretically handle voltage sources of any
			 * number of phases assuming they are equally displaced in time.
			 */

			switch (nPhases) {
			case 1:
				VMag = kVBase * perUnit * 1000.0;
				break;
			default:
				VMag = kVBase * perUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases) * Math.PI / 180.0);
				break;
			}

			SolutionObj sol = DSS.activeCircuit.getSolution();

			if (sol.isHarmonicModel()) {

				srcHarmonic = sol.getFrequency() / srcFrequency;
				VHarm = getSpectrumObj().getMult(srcHarmonic).multiply(VMag);  // base voltage for this harmonic
				VHarm = Util.rotatePhasorDeg(VHarm, srcHarmonic, angle);  // rotate for phase 1 shift
				for (i = 0; i < nPhases; i++) {
					VTerminal[i] = VHarm;
					VTerminal[i + nPhases] = Complex.ZERO;
					if (i < nPhases)
						switch (scanType) {
						case 1:
							VHarm = Util.rotatePhasorDeg(VHarm, 1.0, -360.0 / nPhases); // maintain pos seq
							break;
						case 0:
							// do nothing for zero sequence; all the same.
							break;
						default:
							VHarm = Util.rotatePhasorDeg(VHarm, srcHarmonic, -360.0 / nPhases);  // normal rotation
							break;
						}
				}
			} else {

				if (Math.abs(sol.getFrequency() - srcFrequency) > DSS.EPSILON2)
					VMag = 0.0;  // solution frequency and source frequency don't match
				/* NOTE: RE-uses VTerminal space */
				for (i = 0; i < nPhases; i++) {
					switch (sequenceType) {
					case -1:
						VTerminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + angle + (i-1)* 360.0/nPhases));  // neg seq
						break;
					case 0:
						VTerminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + angle));  // all the same for zero sequence
						break;
					default:
						VTerminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + angle - (i-1) * 360.0 / nPhases));
						break;
					}
					VTerminal[i + nPhases] = Complex.ZERO;  // see comments in getInjCurrents
				}
			}
		} catch (Exception e) {
			DSS.doSimpleMsg("Error computing voltages for VSource."+getName()+". Check specification. Aborting.", 326);
			if (DSS.inRedirect)
				DSS.redirectAbort = true;
		}
	}

	public int injCurrents() {
		getInjCurrents(getInjCurrent());

		/* This is source injection */

		return super.injCurrents();  // add into system array
	}

	public void getCurrents(Complex[] curr) {

		try {
			SolutionObj sol = DSS.activeCircuit.getSolution();

			for (int i = 0; i < YOrder; i++)
				VTerminal[i] = sol.getNodeV(nodeRef[i]);

			YPrim.vMult(curr, VTerminal);  // current from elements in system Y

			getInjCurrents(complexBuffer);  // get present value of inj currents
			// add together with Yprim currents
			for (int i = 0; i < YOrder; i++)
				curr[i] = curr[i].subtract(complexBuffer[i]);

		} catch (Exception e) {
			DSS.doErrorMsg("getCurrents for element: " + getName() + ".", e.getMessage(),
					"Inadequate storage allotted for circuit element.", 327);
		}
	}

	/**
	 * Source injection currents given by this formula:
	 * 		_     _           _         _
	 * 		|Iinj1|           |Vsource  |
	 * 		|     | = [Yprim] |         |
	 * 		|Iinj2|           | 0       |
	 * 		_     _           _         _
	 */
	public void getInjCurrents(Complex[] curr) {

		getVTerminalForSource();  // gets voltage vector above
		YPrim.vMult(curr, VTerminal);

		setITerminalUpdated(false);
	}

	public void dumpProperties(OutputStream out, boolean complete) {
		Complex c;

		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			pw.println("~ " + getParentClass().getPropertyName(i) + "=" + getPropertyValue(i));

		if (complete) {
			pw.println();
			pw.println("baseFrequency=" + baseFrequency);
			pw.println("vMag=" + VMag);
			pw.println("zMatrix=");
			for (int i = 0; i < nPhases; i++) {
				for (int j = 0; j < i; j++) {
					c = Z.get(i, j);
					pw.printf("%.8g +j %.8g ", c.getReal(), c.getImaginary());
				}
				pw.println();
			}
		}
		pw.close();
	}

	public void initPropertyValues(int arrayOffset) {

		/* PropertyValue allocated in DSSObject constructor */
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
	public void makePosSequence() {
		String s = "phases=1 ";
		s = s + String.format("basekV=%-.5g ", kVBase / DSS.SQRT3);
		s = s + String.format("r1=%-.5g ", R1);
		s = s + String.format("x1=%-.5g ", X1);

		Parser.getInstance().setCmdString(s);
		edit();

		super.makePosSequence();
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

	public void setZinv(CMatrix zinv) {
		ZInv = zinv;
	}

	public double getVMag() {
		return VMag;
	}

	public void setVMag(double mag) {
		VMag = mag;
	}

	public double getKVBase() {
		return kVBase;
	}

	public void setKVBase(double base) {
		this.kVBase = base;
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

	public double getSrcFrequency() {
		return srcFrequency;
	}

	public void setSrcFrequency(double frequency) {
		srcFrequency = frequency;
	}

	// FIXME Private members in OpenDSS

	public double getMVAsc3() {
		return MVAsc3;
	}

	public void setMVAsc3(double mvasc3) {
		MVAsc3 = mvasc3;
	}

	public double getMVAsc1() {
		return MVAsc1;
	}

	public void setMVAsc1(double mvasc1) {
		MVAsc1 = mvasc1;
	}

	public double getIsc3() {
		return Isc3;
	}

	public void setIsc3(double isc3) {
		Isc3 = isc3;
	}

	public double getIsc1() {
		return Isc1;
	}

	public void setIsc1(double isc1) {
		Isc1 = isc1;
	}

	public int getZSpecType() {
		return ZSpecType;
	}

	public void setZSpecType(int specType) {
		ZSpecType = specType;
	}

	public double getR1() {
		return R1;
	}

	public void setR1(double r1) {
		R1 = r1;
	}

	public double getX1() {
		return X1;
	}

	public void setX1(double x1) {
		X1 = x1;
	}

	public double getR0() {
		return R0;
	}

	public void setR0(double r0) {
		R0 = r0;
	}

	public double getX0() {
		return X0;
	}

	public void setX0(double x0) {
		X0 = x0;
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

	public int getScanType() {
		return scanType;
	}

	public void setScanType(int type) {
		scanType = type;
	}

	public int getSequenceType() {
		return sequenceType;
	}

	public void setSequenceType(int type) {
		sequenceType = type;
	}

}
