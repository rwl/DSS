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
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.ComplexUtil;

public class GICLineObjImpl extends PCElementImpl implements GICLineObj {

	private double Angle;
	private double Volts;
	private double Vmag;  // present voltage magnitude
	private double SrcFrequency;
	private double R, X, C;

    private int ScanType;
    private int SequenceType;

    protected CMatrix Z;  // base frequency series Z matrix
    protected CMatrix Zinv;

	public GICLineObjImpl(DSSClass ParClass, String LineName) {
		super(ParClass);
		setName(LineName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType(); //SOURCE + NON_PCPD_ELEM;  // don't want this in PC element list

		setNPhases(3);
		this.nConds = 3;
		setNTerms(2);  // now a 2-terminal device
		this.Z        = null;
		this.Zinv     = null;
		/*this.Basefrequency = 60.0;*/ // set in base class

		this.R        = 1.0;
		this.X        = 0.0;
		this.C        = 0.0;

		this.SrcFrequency = 0.1;  // typical GIC study frequency
		this.Angle        = 0.0;
		this.ScanType     = 0;
		this.SequenceType = 0;  // default to zero sequence (same voltage induced in all phases)

		this.Spectrum = "";  // no default

		initPropertyValues(0);

		this.YOrder = this.nTerms * this.nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		Complex Zs, Zm;
		int i, j;

		DSSGlobals Globals = DSSGlobals.getInstance();

		if (Z != null) Z = null;
		if (Zinv != null) Zinv = null;

		// for a source, nPhases = nCond, for now
		Z    = new CMatrixImpl(nPhases);
		Zinv = new CMatrixImpl(nPhases);

		/* Update property value array */
		/* Don't change a specified value; only computed ones */

		Zs = new Complex(R, X);
		Zm = Complex.ZERO;

		for (i = 0; i < nPhases; i++) {
			Z.setElement(i, i, Zs);
			for (j = 0; j < i-1; j++)
				Z.setElemSym(i, j, Zm);
		}

		Vmag = Volts;

		setSpectrumObj((com.epri.dss.general.SpectrumObj) Globals.getSpectrumClass().find(getSpectrum()));
		if ((getSpectrumObj() == null) && (getSpectrum().length() > 0))
			Globals.doSimpleMsg("Spectrum object \"" + getSpectrum() + "\" for device GICLine."+getName()+" not found.", 324);

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), YOrder) );
	}

	@Override
	public void calcYPrim() {
		Complex Value;
		int i, j;
		double FreqMultiplier;
		double Xc;

		DSSGlobals Globals = DSSGlobals.getInstance();

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

		YPrimFreq      = Globals.getActiveCircuit().getSolution().getFrequency();
		FreqMultiplier  = YPrimFreq / baseFrequency;

		/* Put in series RL adjusted for frequency */
		for (i = 0; i < nPhases; i++) {
			for (j = 0; j < nPhases; j++) {
				Value    = Z.getElement(i, j);
				// modify from base freq
				Value = new Complex(Value.getReal(), Value.getImaginary() * FreqMultiplier);
				Zinv.setElement(i, j, Value);
			}
		}

		if (C > 0.0) {  // add 1/wC into diagonals of Zinv

			Xc = -1.0 / (DSSGlobals.TWO_PI * YPrimFreq * C * 1.0e-6);
			for (i = 0; i < nPhases; i++)
				Zinv.addElement(i, i, new Complex(0.0, Xc)) ;
		}

		Zinv.invert();  /* Invert in place */

		if (Zinv.getInvertError() > 0) {
			/* If error, put in large series conductance */
			Globals.doErrorMsg("GICLineObj.calcYPrim", "Matrix inversion error for GICLine \"" + getName() + "\"",
	                   "Invalid impedance specified. Replaced with small resistance.", 325);
	        Zinv.clear();
	        for (i = 0; i < nPhases; i++)
				Zinv.setElement(i, i, new Complex(1.0 / DSSGlobals.EPSILON, 0.0));
		}

		// YPrim_Series.CopyFrom(Zinv);

		for (i = 0; i < nPhases; i++) {
			for (j = 0; j < nPhases; j++) {
				Value = Zinv.getElement(i, j);
				YPrimSeries.setElement(i, j, Value);
				YPrimSeries.setElement(i + nPhases, j + nPhases, Value);
				YPrimSeries.setElemSym(i + nPhases, j, Value.negate());
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
		Complex Vharm;
		double SrcHarmonic;

		DSSGlobals Globals = DSSGlobals.getInstance();
		SolutionObj sol = Globals.getActiveCircuit().getSolution();

		try {
			/* This formulation will theoretically handle voltage sources of
			 * any number of phases assuming they are equally displaced in
			 * time. */
			Vmag = Volts;

			if (sol.isHarmonicModel() && (getSpectrumObj() != null)) {

				SrcHarmonic = sol.getFrequency() / SrcFrequency;
				// base voltage for this harmonic
				Vharm = getSpectrumObj().getMult(SrcHarmonic).multiply(Vmag);
				// rotate for phase 1 shift
				Vharm = Utilities.rotatePhasorDeg(Vharm, SrcHarmonic, Angle);
				for (i = 0; i < nPhases; i++) {
					VTerminal[i] =  Vharm;
	                VTerminal[i+nPhases] = Complex.ZERO;
	                if (i < nPhases) {
	                	switch (ScanType) {
						case 1:
							// maintain pos seq
							Vharm = Utilities.rotatePhasorDeg(Vharm, 1.0, -360.0 / nPhases);
							break;
						case 0:
							// do nothing for zero Sequence; all the same
							break;
	                    default:
	                    	// normal rotation
	                    	Vharm = Utilities.rotatePhasorDeg(Vharm, SrcHarmonic, -360.0 / nPhases);
	    					break;
	                	}
	                }
				}
			} else {
				// non-harmonic modes or no spectrum
				if (Math.abs(sol.getFrequency() - SrcFrequency) > DSSGlobals.EPSILON2)
					Vmag = 0.0;  // solution frequency and source frequency don't match
				/* Note: Re-uses VTerminal space */
				for (i = 0; i < nPhases; i++) {
					switch (SequenceType) {
					case -1:
						VTerminal[i] = ComplexUtil.polarDeg2Complex(Vmag, (360.0 + Angle + (i-1)* 360.0 / nPhases) );  // neg seq
						break;
					case 0:
						VTerminal[i] = ComplexUtil.polarDeg2Complex(Vmag, (360.0 + Angle) );  // all the same for zero sequence
						break;
					default:
						VTerminal[i] = ComplexUtil.polarDeg2Complex(Vmag, (360.0 + Angle - (i-1)* 360.0 / nPhases) );
						break;
					}
	                VTerminal[i+nPhases] = Complex.ZERO;  // see comments in getInjCurrents
				}
			}
		} catch (Exception e) {
			Globals.doSimpleMsg("Error computing Voltages for GICLine."+getName()+". Check specification. Aborting.", 326);
			if (Globals.isInRedirect())
				Globals.setRedirectAbort(true);
		}
	}

	@Override
	public int injCurrents() {
		getInjCurrents(getInjCurrent());

		/* This is source injection */
		return super.injCurrents();  // add into system array
	}

	@Override
	public void getCurrents(Complex[] Curr) {
		int i;

		DSSGlobals Globals = DSSGlobals.getInstance();
		SolutionObj sol = Globals.getActiveCircuit().getSolution();

		try {
			for (i = 0; i < YOrder; i++)
				VTerminal[i] = sol.getNodeV()[nodeRef[i]];

			YPrim.MVMult(Curr, VTerminal);  // current from elements in system Y

			getInjCurrents(complexBuffer);  // get present value of inj currents
			// add together with Yprim currents
			for (i = 0; i < YOrder; i++)
				Curr[i] = Curr[i].subtract( complexBuffer[i] );

		} catch (Exception e) {
			Globals.doErrorMsg("getCurrents for Element: " + getName() + ".",
			e.getMessage(),
	        "Inadequate storage allotted for circuit element.", 327);
		}
	}

	@Override
	public void getInjCurrents(Complex[] Curr) {
		/* Source injection currents given by this formula:
		 *    _     _           _         _
		 *    |Iinj1|           |GICLine  |
		 *    |     | = [Yprim] |         |
		 *    |Iinj2|           | 0       |
		 *    _     _           _         _
		 */

		getVterminalForSource();  // gets voltage vector above
		YPrim.MVMult(Curr, VTerminal);

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
			F.println("baseFrequency=" + baseFrequency);
			F.println("vMag=" + Vmag);
			F.println("zMatrix=");
			for (i = 0; i < nPhases; i++) {
				for (j = 0; j < i; j++) {
					c = Z.getElement(i, j);
					F.printf("%.8g +j %.8g ", c.getReal(), c.getImaginary());
				}
				F.println();
			}
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
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
	public String getPropertyValue(int Index) {
		switch (Index) {
		case 1:
			return getBus(1);
		case 2:
			return getBus(2);
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

		S = "phases=1 ";
		S = S + String.format("voltage=%-.8g angle=%=.5g", Volts, Angle);
		S = S + String.format("r=%-.8g ", R);
		S = S + String.format("x=%-.8g ", X);

		Parser.getInstance().setCmdString(S);
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
		return Zinv;
	}

	public void setZInv(CMatrix zinv) {
		Zinv = zinv;
	}

	// FIXME: Private members in OpenDSS

	public double getAngle() {
		return Angle;
	}

	public void setAngle(double angle) {
		Angle = angle;
	}

	public double getVolts() {
		return Volts;
	}

	public void setVolts(double volts) {
		Volts = volts;
	}

	public double getVMag() {
		return Vmag;
	}

	public void setVMag(double vmag) {
		Vmag = vmag;
	}

	public double getSrcFrequency() {
		return SrcFrequency;
	}

	public void setSrcFrequency(double srcFrequency) {
		SrcFrequency = srcFrequency;
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
		return ScanType;
	}

	public void setScanType(int scanType) {
		ScanType = scanType;
	}

	public int getSequenceType() {
		return SequenceType;
	}

	public void setSequenceType(int sequenceType) {
		SequenceType = sequenceType;
	}

}
