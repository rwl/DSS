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
	private double Vmag;  // Present voltage magnitude
	private double SrcFrequency;
	private double R, X, C;

    private int ScanType;
    private int SequenceType;

    protected CMatrix Z;  // Base Frequency Series Z matrix
    protected CMatrix Zinv;

	public GICLineObjImpl(DSSClass ParClass, String LineName) {
		super(ParClass);
		setName(LineName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType(); //SOURCE + NON_PCPD_ELEM;  // Don't want this in PC Element List

		this.nPhases  = 3;
		this.nConds   = 3;
		this.nTerms   = 2;   // Now a 2-terminal device
		this.Z        = null;
		this.Zinv     = null;
		/*this.Basefrequency = 60.0;*/ // set in base class

		this.R        = 1.0;
		this.X        = 0.0;
		this.C        = 0.0;

		this.SrcFrequency = 0.1;  // Typical GIC study frequency
		this.Angle        = 0.0;
		this.ScanType     = 0;
		this.SequenceType = 0; // default to zero sequence (same voltage induced in all phases)

		setSpectrum("");  // no default

		initPropertyValues(0);

		this.Yorder = this.nTerms * this.nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		Complex Zs, Zm;
		int i, j;

		DSSGlobals Globals = DSSGlobals.getInstance();

		if (Z != null) Z = null;
		if (Zinv != null) Zinv = null;

		// For a source, nPhases = nCond, for now
		Z    = new CMatrixImpl(nPhases);
		Zinv = new CMatrixImpl(nPhases);

		/* Update property Value array */
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
			Globals.doSimpleMsg("Spectrum Object \"" + getSpectrum() + "\" for Device GICLine."+getName()+" Not Found.", 324);

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), Yorder) );
	}

	@Override
	public void calcYPrim() {
		Complex Value;
		int i, j;
		double FreqMultiplier;
		double Xc;

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

		YprimFreq      = Globals.getActiveCircuit().getSolution().getFrequency();
		FreqMultiplier  = YprimFreq / BaseFrequency;

		/* Put in Series RL Adjusted for frequency */
		for (i = 0; i < nPhases; i++) {
			for (j = 0; j < nPhases; j++) {
				Value    = Z.getElement(i, j);
				// Modify from base freq
				Value = new Complex(Value.getReal(), Value.getImaginary() * FreqMultiplier);
				Zinv.setElement(i, j, Value);
			}
		}

		if (C > 0.0) {  // Add 1/wC into diagonals of Zinv

			Xc = -1.0 / (DSSGlobals.TwoPi * YprimFreq * C * 1.0e-6);
			for (i = 0; i < nPhases; i++)
				Zinv.addElement(i, i, new Complex(0.0, Xc)) ;
		}

		Zinv.invert();  /* Invert in place */

		if (Zinv.getInvertError() > 0) {
			/* If error, put in Large series conductance */
			Globals.doErrorMsg("GICLineObj.calcYPrim", "Matrix Inversion Error for GICLine \"" + getName() + "\"",
	                   "Invalid impedance specified. Replaced with small resistance.", 325);
	        Zinv.clear();
	        for (i = 0; i < nPhases; i++)
				Zinv.setElement(i, i, new Complex(1.0 / DSSGlobals.EPSILON, 0.0));
		}

		// YPrim_Series.CopyFrom(Zinv);

		for (i = 0; i < nPhases; i++) {
			for (j = 0; j < nPhases; j++) {
				Value = Zinv.getElement(i, j);
				YPrim_Series.setElement(i, j, Value);
				YPrim_Series.setElement(i + nPhases, j + nPhases, Value);
				YPrim_Series.setElemSym(i + nPhases, j, Value.negate());
			}
		}

		YPrim.copyFrom(YPrim_Series);

		/* Now Account for Open Conductors */
		/* For any conductor that is open, zero out row and column */
		super.calcYPrim();

		setYprimInvalid(false);
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

			if (sol.isIsHarmonicModel() && (getSpectrumObj() != null)) {

				SrcHarmonic = sol.getFrequency() / SrcFrequency;
				// Base voltage for this harmonic
				Vharm = getSpectrumObj().getMult(SrcHarmonic).multiply(Vmag);
				// Rotate for phase 1 shift
				Vharm = Utilities.rotatePhasorDeg(Vharm, SrcHarmonic, Angle);
				for (i = 0; i < nPhases; i++) {
					Vterminal[i] =  Vharm;
	                Vterminal[i+nPhases] = Complex.ZERO;
	                if (i < nPhases) {
	                	switch (ScanType) {
						case 1:
							// maintain pos seq
							Vharm = Utilities.rotatePhasorDeg(Vharm, 1.0, -360.0/nPhases);
						case 0:
							// Do nothing for zero Sequence; All the same
	                    default:
	                    	// normal rotation
	                    	Vharm = Utilities.rotatePhasorDeg(Vharm, SrcHarmonic, -360.0/nPhases);
	                	}
	                }
				}
			} else {
				// non-harmonic modes or no spectrum
				if (Math.abs(sol.getFrequency() - SrcFrequency) > DSSGlobals.EPSILON2)
					Vmag = 0.0;  // Solution Frequency and Source Frequency don't match!
				/* NOTE: RE-uses VTerminal space */
				for (i = 0; i < nPhases; i++) {
					switch (SequenceType) {
					case -1:
						Vterminal[i] = ComplexUtil.polarDeg2Complex(Vmag, (360.0 + Angle + (i-1)* 360.0/nPhases) );  // neg seq
					case 0:
						Vterminal[i] = ComplexUtil.polarDeg2Complex(Vmag, (360.0 + Angle) );   // all the same for zero sequence
					default:
						Vterminal[i] = ComplexUtil.polarDeg2Complex(Vmag, (360.0 + Angle - (i-1)* 360.0/nPhases) );
					}
	                Vterminal[i+nPhases] = Complex.ZERO;    // See comments in GetInjCurrents
				}
			}
		} catch (Exception e) {
			Globals.doSimpleMsg("Error computing Voltages for GICLine."+getName()+". Check specification. Aborting.", 326);
			if (Globals.isIn_Redirect())
				Globals.setRedirect_Abort(true);
		}
	}

	@Override
	public int injCurrents() {
		getInjCurrents(getInjCurrent());

		/* This is source injection */
		return super.injCurrents();  // Add into system array
	}

	@Override
	public void getCurrents(Complex[] Curr) {
		int i;

		DSSGlobals Globals = DSSGlobals.getInstance();
		SolutionObj sol = Globals.getActiveCircuit().getSolution();

		try {
			for (i = 0; i < Yorder; i++)
				Vterminal[i] = sol.getNodeV()[NodeRef[i]];

			YPrim.MVMult(Curr, Vterminal);  // Current from Elements in System Y

			getInjCurrents(ComplexBuffer);  // Get present value of inj currents
			// Add together with Yprim currents
			for (i = 0; i < Yorder; i++)
				Curr[i] = Curr[i].subtract( ComplexBuffer[i] );

		} catch (Exception e) {
			Globals.doErrorMsg("getCurrents for Element: " + getName() + ".",
			e.getMessage(),
	        "Inadequate storage allotted for circuit element.", 327);
		}
	}

	@Override
	public void getInjCurrents(Complex[] Curr) {
		/* source injection currents given by this formula:
		 *    _     _           _         _
		 *    |Iinj1|           |GICLine  |
		 *    |     | = [Yprim] |         |
		 *    |Iinj2|           | 0       |
		 *    _     _           _         _
		 */

		getVterminalForSource();  // gets voltage vector above
		YPrim.MVMult(Curr, Vterminal);

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
			F.println("VMag=" + Vmag);
			F.println("Z Matrix=");
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

		S = "Phases=1 ";
		S = S + String.format("Voltage=%-.8g  Angle=%=.5g", Volts, Angle);
		S = S + String.format("R=%-.8g ", R);
		S = S + String.format("X=%-.8g ", X);

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

	public CMatrix getZinv() {
		return Zinv;
	}

	public void setZinv(CMatrix zinv) {
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

	public double getVmag() {
		return Vmag;
	}

	public void setVmag(double vmag) {
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
