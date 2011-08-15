package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.GICLine;
import com.epri.dss.conversion.GICLineObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.ComplexUtil;

import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.complex.ComplexUtils;

public class GICLineObjImpl extends PCElementImpl implements GICLineObj {

	private double angle;
	private double volts;
	private double VMag;  // present voltage magnitude
	private double srcFrequency;
	private double R, X, C;

    private int scanType;
    private int sequenceType;

    protected CMatrix Z;  // base frequency series Z matrix
    protected CMatrix ZInv;

	public GICLineObjImpl(DSSClass parClass, String lineName) {
		super(parClass);
		setName(lineName.toLowerCase());
		objType = parClass.getDSSClassType(); //SOURCE + NON_PCPD_ELEM;  // don't want this in PC element list

		setNPhases(3);
		nConds = 3;
		setNTerms(2);  // now a 2-terminal device
		Z        = null;
		ZInv     = null;
		/*Basefrequency = 60.0;*/ // set in base class

		R        = 1.0;
		X        = 0.0;
		C        = 0.0;

		srcFrequency = 0.1;  // typical GIC study frequency
		angle        = 0.0;
		scanType     = 0;
		sequenceType = 0;  // default to zero sequence (same voltage induced in all phases)

		spectrum = "";  // no default

		initPropertyValues(0);

		YOrder = nTerms * nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		Complex Zs, Zm;
		int i, j;


		if (Z != null) Z = null;
		if (ZInv != null) ZInv = null;

		// for a source, nPhases = nCond, for now
		Z    = new CMatrixImpl(nPhases);
		ZInv = new CMatrixImpl(nPhases);

		/* Update property value array */
		/* Don't change a specified value; only computed ones */

		Zs = new Complex(R, X);
		Zm = Complex.ZERO;

		for (i = 0; i < nPhases; i++) {
			Z.setElement(i, i, Zs);
			for (j = 0; j < i-1; j++)
				Z.setElemSym(i, j, Zm);
		}

		VMag = volts;

		setSpectrumObj((com.epri.dss.general.SpectrumObj) DSSGlobals.spectrumClass.find(getSpectrum()));
		if ((getSpectrumObj() == null) && (getSpectrum().length() > 0))
			DSSGlobals.doSimpleMsg("Spectrum object \"" + getSpectrum() + "\" for device GICLine."+getName()+" not found.", 324);

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), YOrder) );
	}

	@Override
	public void calcYPrim() {
		Complex value;
		int i, j;
		double freqMultiplier;
		double Xc;


		// build only YPrim_Series
		if (isYprimInvalid()) {
			if (YPrimSeries != null) YPrimSeries = null;
			YPrimSeries = new CMatrixImpl(YOrder);
			if (YPrim != null) YPrim = null;
			YPrim = new CMatrixImpl(YOrder);
		} else {
			YPrimSeries.clear();
			YPrim.clear();
		}

		YPrimFreq      = DSSGlobals.activeCircuit.getSolution().getFrequency();
		freqMultiplier  = YPrimFreq / baseFrequency;

		/* Put in series RL adjusted for frequency */
		for (i = 0; i < nPhases; i++) {
			for (j = 0; j < nPhases; j++) {
				value    = Z.getElement(i, j);
				// modify from base freq
				value = new Complex(value.getReal(), value.getImaginary() * freqMultiplier);
				ZInv.setElement(i, j, value);
			}
		}

		if (C > 0.0) {  // add 1/wC into diagonals of Zinv

			Xc = -1.0 / (DSSGlobals.TWO_PI * YPrimFreq * C * 1.0e-6);
			for (i = 0; i < nPhases; i++)
				ZInv.addElement(i, i, new Complex(0.0, Xc)) ;
		}

		ZInv.invert();  /* Invert in place */

		if (ZInv.getInvertError() > 0) {
			/* If error, put in large series conductance */
			DSSGlobals.doErrorMsg("GICLineObj.calcYPrim", "Matrix inversion error for GICLine \"" + getName() + "\"",
	                   "Invalid impedance specified. Replaced with small resistance.", 325);
	        ZInv.clear();
	        for (i = 0; i < nPhases; i++)
				ZInv.setElement(i, i, new Complex(1.0 / DSSGlobals.EPSILON, 0.0));
		}

		// YPrim_Series.CopyFrom(Zinv);

		for (i = 0; i < nPhases; i++) {
			for (j = 0; j < nPhases; j++) {
				value = ZInv.getElement(i, j);
				YPrimSeries.setElement(i, j, value);
				YPrimSeries.setElement(i + nPhases, j + nPhases, value);
				YPrimSeries.setElemSym(i + nPhases, j, value.negate());
			}
		}

		YPrim.copyFrom(YPrimSeries);

		/* Now account for open conductors */
		/* For any conductor that is open, zero out row and column */
		super.calcYPrim();

		setYPrimInvalid(false);
	}

	private void getVterminalForSource() {
		int i;
		Complex VHarm;
		double srcHarmonic;

		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

		try {
			/* This formulation will theoretically handle voltage sources of
			 * any number of phases assuming they are equally displaced in
			 * time. */
			VMag = volts;

			if (sol.isHarmonicModel() && (getSpectrumObj() != null)) {

				srcHarmonic = sol.getFrequency() / srcFrequency;
				// base voltage for this harmonic
				VHarm = getSpectrumObj().getMult(srcHarmonic).multiply(VMag);
				// rotate for phase 1 shift
				VHarm = Utilities.rotatePhasorDeg(VHarm, srcHarmonic, angle);
				for (i = 0; i < nPhases; i++) {
					VTerminal[i] =  VHarm;
	                VTerminal[i+nPhases] = Complex.ZERO;
	                if (i < nPhases) {
	                	switch (scanType) {
						case 1:
							// maintain pos seq
							VHarm = Utilities.rotatePhasorDeg(VHarm, 1.0, -360.0 / nPhases);
							break;
						case 0:
							// do nothing for zero Sequence; all the same
							break;
	                    default:
	                    	// normal rotation
	                    	VHarm = Utilities.rotatePhasorDeg(VHarm, srcHarmonic, -360.0 / nPhases);
	    					break;
	                	}
	                }
				}
			} else {
				// non-harmonic modes or no spectrum
				if (Math.abs(sol.getFrequency() - srcFrequency) > DSSGlobals.EPSILON2)
					VMag = 0.0;  // solution frequency and source frequency don't match
				/* Note: Re-uses VTerminal space */
				for (i = 0; i < nPhases; i++) {
					switch (sequenceType) {
					case -1:
						VTerminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + angle + (i-1)* 360.0 / nPhases) );  // neg seq
						break;
					case 0:
						VTerminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + angle) );  // all the same for zero sequence
						break;
					default:
						VTerminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + angle - (i-1)* 360.0 / nPhases) );
						break;
					}
	                VTerminal[i+nPhases] = Complex.ZERO;  // see comments in getInjCurrents
				}
			}
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error computing Voltages for GICLine."+getName()+". Check specification. Aborting.", 326);
			if (DSSGlobals.inRedirect)
				DSSGlobals.redirectAbort = true;
		}
	}

	@Override
	public int injCurrents() {
		getInjCurrents(getInjCurrent());

		/* This is source injection */
		return super.injCurrents();  // add into system array
	}

	@Override
	public void getCurrents(Complex[] curr) {
		int i;

		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

		try {
			for (i = 0; i < YOrder; i++)
				VTerminal[i] = sol.getNodeV()[nodeRef[i]];

			YPrim.MVMult(curr, VTerminal);  // current from elements in system Y

			getInjCurrents(complexBuffer);  // get present value of inj currents
			// add together with Yprim currents
			for (i = 0; i < YOrder; i++)
				curr[i] = curr[i].subtract( complexBuffer[i] );

		} catch (Exception e) {
			DSSGlobals.doErrorMsg("getCurrents for Element: " + getName() + ".",
			e.getMessage(),
	        "Inadequate storage allotted for circuit element.", 327);
		}
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		/* Source injection currents given by this formula:
		 *    _     _           _         _
		 *    |Iinj1|           |GICLine  |
		 *    |     | = [Yprim] |         |
		 *    |Iinj2|           | 0       |
		 *    _     _           _         _
		 */

		getVterminalForSource();  // gets voltage vector above
		YPrim.MVMult(curr, VTerminal);

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
					f.printf("%.8g +j %.8g ", c.getReal(), c.getImaginary());
				}
				f.println();
			}
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		/* PropertyValue allocated in DSSObject constructor */
		setPropertyValue(0, getBus(1));
		setPropertyValue(1, getBus(2));
		setPropertyValue(2, "0.0");
		setPropertyValue(3, "0");
		setPropertyValue(4, "0.1");
		setPropertyValue(5, "3");
		setPropertyValue(6, "1.0");
		setPropertyValue(7, "0");
		setPropertyValue(8, "0");

		setPropertyValue(9, "zero");
		setPropertyValue(10, "zero");

		super.initPropertyValues(GICLine.NumPropsThisClass);
	}

	@Override
	public String getPropertyValue(int index) {
		switch (index) {
		case 1:
			return getBus(1);
		case 2:
			return getBus(2);
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
		s = s + String.format("voltage=%-.8g angle=%=.5g", volts, angle);
		s = s + String.format("r=%-.8g ", R);
		s = s + String.format("x=%-.8g ", X);

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

	public CMatrix getZInv() {
		return ZInv;
	}

	public void setZInv(CMatrix zinv) {
		ZInv = zinv;
	}

	// FIXME: Private members in OpenDSS

	public double getAngle() {
		return angle;
	}

	public void setAngle(double value) {
		angle = value;
	}

	public double getVolts() {
		return volts;
	}

	public void setVolts(double value) {
		volts = value;
	}

	public double getVMag() {
		return VMag;
	}

	public void setVMag(double vmag) {
		VMag = vmag;
	}

	public double getSrcFrequency() {
		return srcFrequency;
	}

	public void setSrcFrequency(double frequency) {
		srcFrequency = frequency;
	}

	public double getR() {
		return R;
	}

	public void setR(double r) {
		R = r;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getC() {
		return C;
	}

	public void setC(double c) {
		C = c;
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
