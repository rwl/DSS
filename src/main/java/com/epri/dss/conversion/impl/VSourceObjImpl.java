package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.ComplexUtil;

import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.VSource;
import com.epri.dss.conversion.VSourceObj;
import com.epri.dss.shared.CMatrix;

public class VSourceObjImpl extends PCElementImpl implements VSourceObj {

	private double MVAsc3;
	private double MVAsc1;
	private double Isc3;
	private double Isc1;
	private int ZSpecType;
	private double R1, X1;
	private double R0, X0;
	private double X1R1;
	private double X0R0;

	private int ScanType;
	private int SequenceType;

	protected CMatrix Z;  // Base Frequency Series Z matrix
	protected CMatrix Zinv;
	protected double VMag;

	protected double kVBase;
	protected double PerUnit;
	protected double Angle;
	protected double SrcFrequency;

	public VSourceObjImpl(DSSClassImpl ParClass, String SourceName) {
		super(ParClass);

		setName(SourceName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType(); //SOURCE + NON_PCPD_ELEM;  // Don't want this in PC Element List

		setNPhases(3);
		setNConds(3);
		setNTerms(2);  // now a 2-terminal device
		this.Z        = null;
		this.Zinv     = null;
		//this.Basefrequency = 60.0; // set in base class
		this.MVAsc3   = 2000.0;
		this.MVAsc1   = 2100.0;
		this.ZSpecType = 1; // default to MVAsc
		this.R1       = 1.65;
		this.X1       = 6.6;
		this.R0       = 1.9;
		this.X0       = 5.7;
		this.Isc3     = 10000.0;
		this.Isc1     = 10540.0;
		this.kVBase   = 115.0;
		this.X1R1     = 4.0;
		this.X0R0     = 3.0;
		this.PerUnit  = 1.0;
		this.SrcFrequency = this.BaseFrequency;
		this.Angle    = 0.0;
		this.ScanType = 1;
		this.SequenceType = 1;

		setSpectrum("defaultvsource");

		initPropertyValues(0);

		this.Yorder = this.nTerms * this.nConds;
		recalcElementData();
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Complex Zs, Zm;
		int i, j;

		double Factor;
		double Rs, Xs, Rm, Xm;

		if (Z != null) Z = null;
		if (Zinv != null) Zinv = null;

		// For a source, nPhases = nCond, for now
		Z    = new CMatrixImpl(nPhases);
		Zinv = new CMatrixImpl(nPhases);

		if (nPhases == 1) {
			Factor = 1.0;
		} else {
			Factor = DSSGlobals.SQRT3;
		}

		Rs = 0.0;
		Rm = 0.0;
		Xs = 0.1;
		Xm = 0.0;

		/* Calculate the short circuit impedance and make all other spec types agree */
		switch (ZSpecType) {
		case 1:  // MVAsc
			X1   = Math.pow(kVBase, 2) / MVAsc3 / Math.sqrt(1.0 + 1.0 / Math.pow(X1R1, 2));
			Xs   = Math.pow(kVBase, 2) / MVAsc1 / Math.sqrt(1.0 + 1.0 / Math.pow(X0R0, 2)); // Approx
			R1   = X1 / X1R1;
			Xm   = Xs - X1;
			X0   = (Xs + 2.0 * Xm);
			R0   = X0 / X0R0;
			Isc3 = MVAsc3 * 1000.0 / (DSSGlobals.SQRT3 * kVBase);
			Isc1 = MVAsc1 * 1000.0 /(Factor * kVBase);

			if (nPhases == 1) {
				Rs = Xs / X0R0;
			} else {
				Rs = (2.0 * R1 + R0) / 3.0;
			}

			Rm = (R0 - R1) / 3.0;
			break;

		case 2:  // Isc
			MVAsc3 = DSSGlobals.SQRT3 * kVBase * Isc3 / 1000.0;
			MVAsc1 = Factor * kVBase * Isc1 / 1000.0;
			X1   = Math.pow(kVBase, 2) / MVAsc3 / Math.sqrt(1.0 + 1.0 / Math.pow(X1R1, 2));
			Xs   = Math.pow(kVBase, 2) / MVAsc1 / Math.sqrt(1.0 + 1.0 / Math.pow(X0R0, 2)); //Approx
			R1   = X1 / X1R1;
			Xm   = Xs - X1;
			X0   = (Xs + 2.0 * Xm);
			R0   = X0 / X0R0;

			if (nPhases == 1) {
				Rs = Xs / X0R0;
			} else {
				Rs = (2.0 * R1 + R0) / 3.0;
			}

			Rm = (R0 - R1) / 3.0;
			break;

		case 3:  // Z1, Z0    Specified
			Isc3 = kVBase * 1000.0 / DSSGlobals.SQRT3 / new Complex(R1, X1).abs();

			if (nPhases == 1) {  // Force Z0 to be Z1 so Zs is same as Z1
				R0 = R1;
				X0 = X1;
			}
			Rs = (2.0 * R1 + R0) / 3.0;
			Xs = (2.0 * X1 + X0) / 3.0;

			Isc1   = kVBase * 1000.0 / Factor / new Complex(Rs, Xs).abs();
			MVAsc3 = DSSGlobals.SQRT3 * kVBase * Isc3 / 1000.0;
			MVAsc1 = Factor * kVBase * Isc1 / 1000.0;
			Xm     = Xs - X1;

			Rs     = (2.0 * R1 + R0) / 3.0;
			Rm     = (R0 - R1) / 3.0;
			break;
		}

		/* Update property Value array */
		/* Don't change a specified value; only computed ones */

		Zs = new Complex(Rs, Xs);
		Zm = new Complex(Rm, Xm);

		for (i = 0; i < nPhases; i++) {
			Z.setElement(i, i, Zs);
			for (j = 0; j < i - 1; j++)
				Z.setElemSym(i, j, Zm);
		}

		switch (nPhases) {
		case 1:
			VMag = kVBase * PerUnit * 1000.0;
			break;
		default:
			VMag = kVBase * PerUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases) * Math.PI / 180.0);
			break;
		}

		setSpectrumObj((com.epri.dss.general.SpectrumObj) Globals.getSpectrumClass().find(getSpectrum()));
		if (getSpectrumObj() == null)
			Globals.doSimpleMsg("Spectrum Object \"" + getSpectrum() + "\" for Device Vsource."+getName()+" Not Found.", 324);

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), Yorder) );
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

		YprimFreq = Globals.getActiveCircuit().getSolution().getFrequency();
		FreqMultiplier = YprimFreq / BaseFrequency;

		/* Put in Series RL Adjusted for frequency */
		for (i = 0; i < nPhases; i++) {
			for (j = 0; j < nPhases; j++) {
				Value    = Z.getElement(i, j);
				Value = new Complex(Value.getReal(), Value.getImaginary() * FreqMultiplier);  /* Modify from base freq */
				Zinv.setElement(i, j, Value);
			}
		}

		Zinv.invert();  /* Invert in place */

		if (Zinv.getInvertError() > 0) {
			/* If error, put in Large series conductance */
			Globals.doErrorMsg("VsourceObj.calcYPrim", "Matrix Inversion Error for Vsource \"" + getName() + "\"",
					"Invalid impedance specified. Replaced with small resistance.", 325);
			Zinv.clear();
			for (i = 0; i < nPhases; i++)
				Zinv.setElement(i, i, new Complex(1.0 / DSSGlobals.EPSILON, 0.0));
		}

		// YPrim_Series.copyFrom(Zinv);

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

	private void getVTerminalForSource() {
		int i;
		Complex Vharm;
		double SrcHarmonic;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			/* This formulation will theoretically handle voltage sources of any number of phases assuming they are
			 * equally displaced in time.
			 */

			switch (nPhases) {
			case 1:
				VMag = kVBase * PerUnit * 1000.0;
				break;
			default:
				VMag = kVBase * PerUnit * 1000.0 / 2.0 / Math.sin((180.0 / nPhases) * Math.PI / 180.0);
				break;
			}

			SolutionObj sol = Globals.getActiveCircuit().getSolution();

			if (sol.isIsHarmonicModel()) {

				SrcHarmonic = sol.getFrequency() / SrcFrequency;
				Vharm = getSpectrumObj().getMult(SrcHarmonic).multiply(VMag);  // Base voltage for this harmonic
				Vharm = Utilities.rotatePhasorDeg(Vharm, SrcHarmonic, Angle);  // Rotate for phase 1 shift
				for (i = 0; i < nPhases; i++) {
					Vterminal[i] = Vharm;
					Vterminal[i + nPhases] = Complex.ZERO;
					if (i < nPhases)
						switch (ScanType) {
						case 1:
							Vharm = Utilities.rotatePhasorDeg(Vharm, 1.0, -360.0 / nPhases); // maintain pos seq
							break;
						case 0:
							// Do nothing for Zero Sequence; All the same.
							break;
						default:
							Vharm = Utilities.rotatePhasorDeg(Vharm, SrcHarmonic, -360.0 / nPhases); // normal rotation
							break;
						}
				}
			} else {

				if (Math.abs(sol.getFrequency() - SrcFrequency) > DSSGlobals.EPSILON2)
					VMag = 0.0;  // Solution Frequency and Source Frequency don't match!
				/* NOTE: RE-uses VTerminal space */
				for (i = 0; i < nPhases; i++) {
					switch (SequenceType) {
					case -1:
						Vterminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + Angle + (i-1)* 360.0/nPhases));   // neg seq
						break;
					case 0:
						Vterminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + Angle));   // all the same for zero sequence
						break;
					default:
						Vterminal[i] = ComplexUtil.polarDeg2Complex(VMag, (360.0 + Angle - (i-1) * 360.0 / nPhases));
						break;
					}
					Vterminal[i + nPhases] = Complex.ZERO;    // See comments in GetInjCurrents
				}
			}
		} catch (Exception e) {
			Globals.doSimpleMsg("Error computing Voltages for Vsource."+getName()+". Check specification. Aborting.", 326);
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
		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			SolutionObj sol = Globals.getActiveCircuit().getSolution();

			for (int i = 0; i < Yorder; i++)
				Vterminal[i] = sol.getNodeV()[NodeRef[i]];

			YPrim.MVMult(Curr, Vterminal);  // Current from Elements in System Y

			getInjCurrents(ComplexBuffer);  // Get present value of inj currents
			// Add together  with Yprim currents
			for (int i = 0; i < Yorder; i++)
				Curr[i] = Curr[i].subtract(ComplexBuffer[i]);

		} catch (Exception e) {
			Globals.doErrorMsg(("GetCurrents for element: " + getName() + "."), e.getMessage(),
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
	@Override
	public void getInjCurrents(Complex[] Curr) {

		getVTerminalForSource();  // gets voltage vector above
		YPrim.MVMult(Curr, Vterminal);

		setITerminalUpdated(false);
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		Complex c;

		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++)
			F.println("~ " + getParentClass().getPropertyName()[i] + "=" + PropertyValue[i]);

		if (Complete) {
			F.println();
			F.println("BaseFrequency=" + BaseFrequency);
			F.println("VMag=" + VMag);
			F.println("Z Matrix=");
			for (int i = 0; i < nPhases; i++) {
				for (int j = 0; j < i; j++) {
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
		PropertyValue[1]  = getBus(1);
		PropertyValue[2]  = "115";
		PropertyValue[3]  = "1";
		PropertyValue[4]  = "0";
		PropertyValue[5]  = String.format("%d", Math.round(DSSGlobals.getInstance().getDefaultBaseFreq()));
		PropertyValue[6]  = "3";
		PropertyValue[7]  = "2000";
		PropertyValue[8]  = "2100";
		PropertyValue[9]  = "4";
		PropertyValue[10] = "3";
		PropertyValue[11] = "10000";
		PropertyValue[12] = "10500";
		PropertyValue[13] = "1.65";
		PropertyValue[14] = "6.6";
		PropertyValue[15] = "1.9";
		PropertyValue[16] = "5.7";
		PropertyValue[17] = "Pos";
		PropertyValue[18] = "Pos";
		PropertyValue[19] = getBus(2);

		super.initPropertyValues(VSource.NumPropsThisClass);
	}

	@Override
	public String getPropertyValue(int Index) {
		switch (Index) {
		case 0:
			return getBus(1);
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
		String S = "Phases=1 ";
		S = S + String.format("BasekV=%-.5g ", kVBase / DSSGlobals.SQRT3);
		S = S + String.format("R1=%-.5g ", R1);
		S = S + String.format("X1=%-.5g ", X1);

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

	public double getVMag() {
		return VMag;
	}

	public void setVMag(double vMag) {
		VMag = vMag;
	}

	public double getkVBase() {
		return kVBase;
	}

	public void setkVBase(double kVBase) {
		this.kVBase = kVBase;
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

	public double getSrcFrequency() {
		return SrcFrequency;
	}

	public void setSrcFrequency(double srcFrequency) {
		SrcFrequency = srcFrequency;
	}

	// FIXME Private members in OpenDSS

	public double getMVAsc3() {
		return MVAsc3;
	}

	public void setMVAsc3(double mVAsc3) {
		MVAsc3 = mVAsc3;
	}

	public double getMVAsc1() {
		return MVAsc1;
	}

	public void setMVAsc1(double mVAsc1) {
		MVAsc1 = mVAsc1;
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

	public void setZSpecType(int zSpecType) {
		ZSpecType = zSpecType;
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
