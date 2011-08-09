package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.Load;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.GrowthShapeObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.Dynamics;

public class LoadObjImpl extends PCElementImpl implements LoadObj {

	private static final Complex CDOUBLEONE = new Complex(1.0, 1.0);

	private boolean PFChanged;
	/* For all types of allocation */
	private double AllocationFactor;
	/* for connected kVA specification */
	private double kVAAllocationFactor;
	private double ConnectedkVA;
	private double kWh;
	private double kWhDays;
	/* For kWh billed spec */
	private double CFactor;
	private double AvgkW;
	/* References for Harmonics mode */
	private double[] HarmAng;
	private double[] HarmMag;
	private double LastGrowthFactor;
	/* added FOR speedup so we don't have to search FOR growth factor a lot */
	private int LastYear;
	private double LoadFundamental;
	private int LoadSolutionCount;
	private int OpenLoadSolutionCount;
	private double RandomMult;
	private Complex ShapeFactor;
	/* Base vars per phase */
	private double varBase;
	private double varNominal;
	/* Base volts suitable for computing currents */
	private double VBase;
	private double VBase105;
	private double VBase95;
	/* Nominal Watts per phase */
	private double WNominal;
	/* at nominal */
	private Complex Yeq;
	private Complex Yeq105;
	private Complex Yeq95;
	private Complex Yneut;
	/* To handle cases where one conductor of load is open */
	private CMatrix YPrimOpenCond;
	/* Fixed value of y for type 7 load */
	private double YQFixed;
	private double[] ZIPV;
	private int nZIPV;

	// formerly private, now read-only properties for COM access
	protected double puMean;
	protected double puStdDev;
	protected double CVRwattFactor;
	protected double CVRvarFactor;
	protected double Vmaxpu;
	protected double VminEmerg;  // overrides system settings IF <> 0.0
	protected double VminNormal;
	protected double Vminpu;
	protected boolean ExemptFromLDCurve;
	protected boolean Fixed;   // If Fixed, always at base value
	protected boolean ShapeIsActual;

	/* 0 = line-neutral; 1 = Delta */
	protected int Connection;
	/* Daily (24 HR) load shape */
	protected String DailyShape;
	/* Daily load Shape FOR this load */
	protected LoadShapeObj DailyShapeObj;
	/* Duty cycle load shape FOR changes typically less than one hour */
	protected String DutyShape;
	/* Shape for this load */
	protected LoadShapeObj DutyShapeObj;
	/* is overloaded  Factor is the amount of overload */
	protected double EEN_Factor;
	/* (year, Multiplier from previous year) */
	protected String GrowthShape;
	/* Shape for this Growth  Curve */
	protected GrowthShapeObj GrowthShapeObj;
	protected Boolean HasBeenAllocated;
	protected double kWBase;
	protected double kVABase;
	protected double kvarBase;
	protected double kVLoadBase;
	protected int LoadClass;
	protected int NumCustomers;
	/* 0 = kW, PF;  1 = kw, kvar;  2 = kva, PF */
	protected int LoadSpecType;
	protected double PFNominal;
	protected double Rneut;
	/* These are set to > 0 IF a line in the critical path */
	protected double UE_Factor;
	/* Neutral impedance */
	protected double Xneut;
	/* ='fixed' means no variation  exempt from variation */
	protected String YearlyShape;
	/* Shape for this load */
	protected LoadShapeObj YearlyShapeObj;
	protected String CVRshape;
	protected LoadShapeObj CVRShapeObj;

	/*
	 * Variation with voltage
	 *
	 * 1 = Constant kVA (P,Q always in same ratio)
	 * 2 = Constant impedance
	 * 3 = Constant P, Quadratic Q (Mostly motor)
	 * 4 = Linear P, Quadratic Q  (Mixed motor/resistive Use this for CVR studies
	 * 5 = Constant |I|
	 * 6 = Constant P (Variable); Q is fixed value (not variable)
	 * 7 = Constant P (Variable); Q is fixed Z (not variable)
	 * 8 = ZIPV (3 real power coefficients, 3 reactive, Vcutoff)
	 */
	protected int LoadModel;

	public LoadObjImpl(DSSClassImpl ParClass, String SourceName) {
		super(ParClass);
		setName(SourceName.toLowerCase());
		DSSObjType = ParClass.getDSSClassType();

		setNPhases(3);
		setNConds(4);  // defaults to wye  so it has a 4th conductor
		this.Yorder        = 0;  // To trigger an initial allocation
		setNTerms(1);  // forces allocations
		this.kWBase        = 10.0;
		this.kvarBase      = 5.0;
		this.PFNominal     = 0.88;
		this.kVABase       = kWBase / PFNominal;
		this.LoadSpecType  = 0;
		this.Rneut         = -1.0;  // signify neutral is open
		this.Xneut         = 0.0;

		this.YearlyShape    = "";
		this.YearlyShapeObj = null;  // IF YearlyShapeobj = null THEN the load alway stays nominal * global multipliers
		this.DailyShape     = "";
		this.DailyShapeObj  = null;  // IF DaillyShapeobj = null THEN the load alway stays nominal * global multipliers
		this.DutyShape      = "";
		this.DutyShapeObj   = null;  // IF DutyShapeobj = null THEN the load alway stays nominal * global multipliers
		this.GrowthShape    = "";
		this.GrowthShapeObj = null;  // IF grwothshapeobj = null THEN the load alway stays nominal * global multipliers
		this.CVRshape       = "";
		this.CVRShapeObj    = null;
		this.Connection     = 0;    // Wye (star)
		this.LoadModel      = 1;  // changed from 2 RCD {easiest to solve}
		this.LoadClass      = 1;
		this.NumCustomers   = 1;
		this.LastYear       = 0;
		this.CVRwattFactor  = 1.0;
		this.CVRvarFactor   = 2.0;

		this.LastGrowthFactor    = 1.0;
		this.kVAAllocationFactor = 0.5;
		this.AllocationFactor = this.kVAAllocationFactor;
		this.HasBeenAllocated = false;
		this.PFChanged        = false;
		this.ShapeIsActual    = false;

		this.LoadSolutionCount     = -1;  // for keeping track of the present solution in Injcurrent calcs
		this.OpenLoadSolutionCount = -1;
		this.YPrimOpenCond         = null;

		this.ConnectedkVA  = 0.0;  // Loadspectype=3
		this.kWh           = 0.0;  // Loadspectype=4
		this.CFactor       = 4.0;
		this.kWhDays       = 30.0;
		this.VminNormal    = 0.0;    // indicates for program to use Circuit quantities
		this.VminEmerg     = 0.0;
		this.kVLoadBase    = 12.47;
		this.VBase         = 7200.0;
		this.Vminpu        = 0.95;
		this.Vmaxpu        = 1.05;
		this.VBase95       = this.Vminpu * this.VBase;
		this.VBase105      = this.Vmaxpu * this.VBase;
		this.Yorder        = this.nTerms * this.nConds;
		this.RandomMult    = 1.0 ;
		this.Fixed         = false;
		this.ExemptFromLDCurve = false;

		this.puMean     = 0.5;
		this.puStdDev   = 0.1;
		this.UE_Factor  = 0.0;
		this.EEN_Factor = 0.0;
		setSpectrum("defaultload");  // override base class definition
		this.HarmMag    = null;
		this.HarmAng    = null;
		this.ZIPV       = null;
		this.setZIPVSize(0);

		initPropertyValues(0);

		recalcElementData();
	}

	// FIXME Private method in OpenDSS
	public void setZIPVSize(int n) {
		nZIPV = n;
		ZIPV = (double[]) Utilities.resizeArray(ZIPV, nZIPV);
	}

	/**
	 * 0 = reset to 1.0
	 * 1 = Gaussian around mean and std Dev
	 * 2 = uniform
	 */
	public void randomize(int Opt) {
		switch (Opt) {
		case 0:
			RandomMult = 1.0;
			break;
		case DSSGlobals.GAUSSIAN:
			if (YearlyShapeObj != null) {
				RandomMult = MathUtil.gauss(YearlyShapeObj.getMean(), YearlyShapeObj.getStdDev());
			} else {
				RandomMult = MathUtil.gauss(puMean, puStdDev);
			}
			break;
		case DSSGlobals.UNIFORM:
			RandomMult = Math.random();  // number between 0 and 1.0
			break;
		case DSSGlobals.LOGNORMAL:
			if (YearlyShapeObj != null) {
				RandomMult = MathUtil.quasiLognormal(YearlyShapeObj.getMean());
			} else {
				RandomMult = MathUtil.quasiLognormal(puMean);
			}
			break;
		}
	}

	private void calcDailyMult(double Hr) {
		if (DailyShapeObj != null) {
			ShapeFactor   = DailyShapeObj.getMult(Hr);
			ShapeIsActual = DailyShapeObj.isUseActual();
		} else {
			ShapeFactor = new Complex(1.0, 1.0);  // Default to no daily variation
		}
	}

	private void calcDutyMult(double Hr) {
		if (DutyShapeObj != null) {
			ShapeFactor   = DutyShapeObj.getMult(Hr);
			ShapeIsActual = DutyShapeObj.isUseActual();
		} else {
			calcDailyMult(Hr);  // Default to daily mult if no duty curve specified
		}
	}

	private void calcYearlyMult(double Hr) {
		/* Yearly curve is assumed to be hourly only */
		if (YearlyShapeObj != null) {
			ShapeFactor   = YearlyShapeObj.getMult(Hr);
			ShapeIsActual = YearlyShapeObj.isUseActual();
		} else {
			ShapeFactor = new Complex(1.0, 1.0);  // Defaults to no variation
		}
	}

	private void calcCVRMult(double Hr) {
		Complex CVRFactor;

		/* CVR curve is assumed to be used in a yearly simulation */
		if (CVRShapeObj != null) {
			CVRFactor     = CVRShapeObj.getMult(Hr);  /* Complex */
			CVRwattFactor = CVRFactor.getReal();
			CVRvarFactor  = CVRFactor.getImaginary();
		} else {
			/* CVRWattFactor, etc. remain unchanged */
		}
	}

	private double growthFactor(int Year) {
		if (Year == 0) {
			LastGrowthFactor = 1.0;  // default all to 1 in year 0 ; use base values
		} else {
			if (GrowthShapeObj == null) {
				LastGrowthFactor = DSSGlobals.getInstance().getActiveCircuit().getDefaultGrowthFactor();
			} else if (Year != LastYear) {  // Search growthcurve
				LastGrowthFactor = GrowthShapeObj.getMult(Year);
			}
		}
		return LastGrowthFactor;  // for now
	}

	public void setkWkvar(double PkW, double Qkvar) {
		kWBase = PkW;
		kvarBase = Qkvar;
		LoadSpecType = 1;
	}

	public void setNominalLoad() {
		double Factor;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		ShapeFactor = CDOUBLEONE;
		ShapeIsActual = false;

		if (Fixed) {
			Factor = growthFactor(sol.getYear());   // For fixed loads, consider only growth factor
		} else {
			switch (sol.getMode()) {
			case Dynamics.SNAPSHOT:
				if (ExemptFromLDCurve) {
					Factor = growthFactor(sol.getYear());
				} else {
					Factor = ckt.getLoadMultiplier() * growthFactor(sol.getYear());
				}
				break;
			case Dynamics.HARMONICMODE:
				if (ExemptFromLDCurve) {
					Factor = growthFactor(sol.getYear());
				} else {
					Factor = ckt.getLoadMultiplier() * growthFactor(sol.getYear());
				}
				break;
			case Dynamics.DAILYMODE:
				Factor = growthFactor(sol.getYear());
				if (!ExemptFromLDCurve)
					Factor = Factor * ckt.getLoadMultiplier();
				calcDailyMult(sol.getDblHour());
				break;
			case Dynamics.YEARLYMODE:
				Factor = ckt.getLoadMultiplier() * growthFactor(sol.getYear());
				calcYearlyMult(sol.getDblHour());
				if (LoadModel == 4)
					calcCVRMult(sol.getDblHour());
				break;
			case Dynamics.DUTYCYCLE:
				Factor = growthFactor(sol.getYear());
				if (!ExemptFromLDCurve)
					Factor = Factor * ckt.getLoadMultiplier();
				calcDutyMult(sol.getDblHour());
				break;
			case Dynamics.GENERALTIME:
				Factor = growthFactor(sol.getYear());
				if (!ExemptFromLDCurve)
					Factor = Factor * ckt.getLoadMultiplier();
				// This mode allows use of one class of load shape
				switch (ckt.getActiveLoadShapeClass()) {
				case DSSGlobals.USEDAILY:
					calcDailyMult(sol.getDblHour());
					break;
				case DSSGlobals.USEYEARLY:
					calcYearlyMult(sol.getDblHour());
					break;
				case DSSGlobals.USEDUTY:
					calcDutyMult(sol.getDblHour());
					break;
				default:
					ShapeFactor = Complex.ONE;  // default to 1 + j1 if not known
					break;
				}
				break;
			case Dynamics.DYNAMICMODE:
				Factor = growthFactor(sol.getYear());
				if (!ExemptFromLDCurve)
					Factor = Factor * ckt.getLoadMultiplier();
				// This mode allows use of one class of load shape
				switch (ckt.getActiveLoadShapeClass()) {
				case DSSGlobals.USEDAILY:
					calcDailyMult(sol.getDblHour());
					break;
				case DSSGlobals.USEYEARLY:
					calcYearlyMult(sol.getDblHour());
					break;
				case DSSGlobals.USEDUTY:
					calcDutyMult(sol.getDblHour());
					break;
				default:
					ShapeFactor = Complex.ONE;  // default to 1 + j1 if not known
					break;
				}
				break;
			case Dynamics.MONTECARLO1:
				randomize(sol.getRandomType());
				Factor = RandomMult * growthFactor(sol.getYear());
				if (!ExemptFromLDCurve)
					Factor = Factor * ckt.getLoadMultiplier();
				break;
			case Dynamics.MONTECARLO2:
				Factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				if (!ExemptFromLDCurve)
					Factor = Factor * ckt.getLoadMultiplier();
				break;
			case Dynamics.MONTECARLO3:
				Factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				if (!ExemptFromLDCurve)
					Factor = Factor * ckt.getLoadMultiplier();
				break;
			case Dynamics.LOADDURATION1:
				Factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				if (!ExemptFromLDCurve)
					Factor = Factor * ckt.getLoadMultiplier();
				break;
			case Dynamics.LOADDURATION2:
				Factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				if (!ExemptFromLDCurve)
					Factor = Factor * ckt.getLoadMultiplier();
				break;
			case Dynamics.PEAKDAY:
				Factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				break;
			case Dynamics.AUTOADDFLAG:
				Factor = growthFactor(sol.getYear());  // Loadmult = 1.0 by default
				break;
			default:
				Factor = growthFactor(sol.getYear());  // defaults to Base kW * growth
				break;
			}
		}

		if (ShapeIsActual) {
			WNominal   = 1000.0 * ShapeFactor.getReal() / nPhases;
			varNominal = 1000.0 * ShapeFactor.getImaginary() / nPhases;
		} else {
			WNominal   = 1000.0 * kWBase   * Factor * ShapeFactor.getReal() / nPhases ;
			varNominal = 1000.0 * kvarBase * Factor * ShapeFactor.getImaginary() / nPhases;
		}

		Yeq = new Complex(WNominal, -varNominal).divide( Math.pow(VBase, 2) );
		if (Vminpu != 0.0) {
			Yeq95 = Yeq.divide( Math.pow(Vminpu, 2) );  // at 95% voltage
		} else {
			Yeq95 = Complex.ZERO;
		}

		if (Vmaxpu != 0.0) {
			Yeq105 = Yeq.divide( Math.pow(Vmaxpu, 2) );  // at 105% voltage
		} else {
			Yeq105 = Yeq;
		}
	}

	public void recalcElementData() {
		DSSGlobals Global = DSSGlobals.getInstance();

		VBase95  = Vminpu * VBase;
		VBase105 = Vmaxpu * VBase;

		/* Set kW and kvar from root values of kVA and PF */

		switch (LoadSpecType) {
		case 0:  /* kW, PF */
			kvarBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			if (PFNominal < 0.0)
				kvarBase = -kvarBase;
			kVABase = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kvarBase, 2));
			break;
		case 1:  /* kW, kvar -- need to set PFNominal */
			kVABase = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kvarBase, 2));
			if (kVABase > 0.0) {
				PFNominal = kWBase / kVABase;
				/* If kW and kvar are different signs, PF is negative */
				if (kvarBase != 0.0)
					PFNominal = PFNominal * Math.signum(kWBase * kvarBase);
			} else {
				// leave it as it is
			}
			break;
		case 2:  /* kVA, PF */
			kWBase   = kVABase * Math.abs(PFNominal);
			kvarBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			if (PFNominal < 0.0)
				kvarBase = -kvarBase;
			break;
		case 3:
			if (PFChanged) {  // Recompute kvarBase
				kvarBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
				if (PFNominal < 0.0)
					kvarBase = -kvarBase;
				kVABase = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kvarBase, 2));
			}
			break;
		case 4:
			if (PFChanged) {  // Recompute kvarBase
				kvarBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
				if (PFNominal < 0.0)
					kvarBase = -kvarBase;
				kVABase = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kvarBase, 2));
			}
			break;
		}

		setNominalLoad();

		/* Now check for errors. If any of these came out nil and the string was not nil, give warning. */
		if (YearlyShape.equals("none")) YearlyShape = "";
		if (DailyShape.equals("none")) DailyShape = "";
		if (DutyShape.equals("none")) DutyShape = "";

		if (YearlyShapeObj == null)
			if (YearlyShape.length() > 0)
				Global.doSimpleMsg("WARNING! Yearly load shape: \""+ YearlyShape +"\" Not Found.", 583);
		if (DailyShapeObj == null)
			if (DailyShape.length() > 0)
				Global.doSimpleMsg("WARNING! Daily load shape: \""+ DailyShape +"\" Not Found.", 584);
		if (DutyShapeObj == null)
			if (DutyShape.length() > 0)
				Global.doSimpleMsg("WARNING! Duty load shape: \""+ DutyShape +"\" Not Found.", 585);
		if (GrowthShapeObj == null)
			if (GrowthShape.length() > 0)
				Global.doSimpleMsg("WARNING! Yearly Growth shape: \""+ GrowthShape +"\" Not Found.", 586);
		if (CVRShapeObj == null)
			if (CVRshape.length() > 0)
				Global.doSimpleMsg("WARNING! CVR Shape shape: \""+ CVRshape +"\" Not Found.", 586);

		setSpectrumObj( (com.epri.dss.general.SpectrumObj) Global.getSpectrumClass().find(getSpectrum()) );
		if (getSpectrumObj() == null)
			Global.doSimpleMsg("ERROR! Spectrum \""+getSpectrum()+"\" Not Found.", 587);

		if (Rneut < 0.0) {  // flag FOR open neutral
			Yneut = new Complex(0.0, 0.0);
		} else if ((Rneut == 0.0) && (Xneut == 0.0)) {  // Solidly Grounded
			Yneut = new Complex(1.0e6, 0.0);  // 1 microohm resistor
		} else {
			Yneut = new Complex(Rneut, Xneut).invert();
		}

		varBase = 1000.0 * kvarBase / nPhases;
		YQFixed = -varBase / Math.pow(VBase, 2);

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), Yorder) );

		setPFChanged(false);
	}

	private void calcYPrimMatrix(CMatrix Ymatrix) {
		Complex Y, Yij;
		int i, j;
		double FreqMultiplier;

		YprimFreq = DSSGlobals.getInstance().getActiveCircuit().getSolution().getFrequency();
		FreqMultiplier = YprimFreq / BaseFrequency;
		Y = Yeq;
		Y = new Complex(Y.getReal(), Y.getImaginary() / FreqMultiplier);  /* Correct reactive part for frequency */
		Yij = Y.negate();

		switch (Connection) {
		case 0:  // WYE
			for (i = 0; i < nPhases; i++) {
				Ymatrix.setElement(i, i, Y);
				Ymatrix.addElement(nConds, nConds, Y);
				Ymatrix.setElemSym(i, nConds, Yij);
			}
			Ymatrix.addElement(nConds, nConds, Yneut);  // Neutral

			/* If neutral is floating, make sure there is some small
			 * connection to ground  by increasing the last diagonal slightly.
			 */
			if (Rneut < 0.0)
				Ymatrix.setElement(nConds, nConds, Ymatrix.getElement(nConds, nConds).multiply(1.000001));
			break;
		case 1:  // Delta  or L-L
			for (i = 0; i < nPhases; i++) {
				j = i + 1;
				if (j > nConds)
					j = 0;  // wrap around for closed connections
				Ymatrix.addElement(i, i, Y);
				Ymatrix.addElement(j, j, Y);
				Ymatrix.addElemSym(i, j, Yij);   // get both off-diagonal elements
			}
			break;
		}
	}

	/**
	 * If doing an analysis that requires the load to be modeled as an impedance
	 * then put all in.
	 */
	public void calcYPrim() {

		// Build only YPrim shunt for a load then copy to YPrim
		// Build a dummy Yprim series so that CalcV does not fail
		if (isYprimInvalid()) {
			if (YPrim_Shunt != null) YPrim_Shunt = null;
			if (YPrim_Series != null) YPrim_Series = null;
			if (YPrim != null) YPrim = null;

			YPrim_Series = new CMatrixImpl(Yorder);
			YPrim_Shunt  = new CMatrixImpl(Yorder);
			YPrim        = new CMatrixImpl(Yorder);
		} else {
			YPrim_Shunt.clear();
			YPrim_Series.clear();
			YPrim.clear();
		}

		if (DSSGlobals.getInstance().getActiveCircuit().getSolution().getLoadModel() == DSSGlobals.POWERFLOW) {

			setNominalLoad();  // same as admittance model
			calcYPrimMatrix(YPrim_Shunt);

		} else {
			// ADMITTANCE model wanted

			setNominalLoad();
			calcYPrimMatrix(YPrim_Shunt);

		}

		// Set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
		for (int i = 0; i < Yorder; i++)
			YPrim_Series.setElement(i, i, YPrim_Shunt.getElement(i, i).multiply(1.0e-10));

		YPrim.copyFrom(YPrim_Shunt);

		// Account for Open Conductors
		super.calcYPrim();
	}

	/**
	 * Put the current into the proper location according to connection.
	 */
	private void stickCurrInTerminalArray(Complex[] TermArray, Complex Curr, int i) {
		switch (Connection) {
		case 0:  // Wye
			TermArray[i] = TermArray[i].add(Curr.negate());
			TermArray[nConds] = TermArray[nConds].add(Curr);  // Neutral
			break;
		case 1:  // Delta
			TermArray[i] = TermArray[i].add(Curr.negate());
			int j = i + 1;
			if (j > nConds)
				j = 0;  // rotate the phases
			TermArray[j] = TermArray[j].add(Curr);
			break;
		}
	}

	public void updateVoltageBases() {

		LoadObj al = LoadImpl.getActiveLoadObj();

		switch (Connection) {
		case 1:
			al.setVBase(kVLoadBase * 1000.0);
			break;
		default:  /* wye*/
			switch (nPhases) {
			case 2:
				al.setVBase(kVLoadBase * DSSGlobals.InvSQRT3x1000);
				break;
			case 3:
				al.setVBase(kVLoadBase * DSSGlobals.InvSQRT3x1000);
				break;
			default:
				al.setVBase(kVLoadBase * 1000.0);  /* 1-phase or unknown */
				break;
			}
			break;
		}
	}

	private void doConstantPQLoad() {
		int i;
		Complex Curr;
		Complex V;
		double Vmag;

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = Vterminal[i];
			Vmag = V.abs();

			if (Vmag <= VBase95) {
				Curr = Yeq95.multiply(V);  // Below 95% use an impedance model
			} else if (Vmag > VBase105) {
				Curr = Yeq105.multiply(V);  // above 105% use an impedance model
			} else {
				Curr = new Complex(WNominal, varNominal).divide(V).conjugate();  // Above 95%, constant PQ
			}

			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}

	private void doConstantZLoad() {
		Complex Curr;

		// Assume Yeq is kept up to date
		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase();  // Get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			Curr = Yeq.multiply(Vterminal[i]);
			stickCurrInTerminalArray(getIterminal(),  Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}

	/**
	 * Constant P, quadratic Q
	 */
	private void doMotorTypeLoad() {
		Complex Curr;
		Complex V;
		double VMag;

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			V    = Vterminal[i];
			VMag = V.abs();
			if (VMag <= VBase95) {
				Curr = Yeq95.multiply(V);  // Below 95% use an impedance model
			} else if (VMag > VBase105) {
				Curr = Yeq105.multiply(V);  // above 105% use an impedance model
			} else {
				Curr = new Complex(WNominal, 0.0).divide(V).conjugate();  // Above 95%, constant P
				Curr = Curr.add(new Complex(0.0, Yeq.getImaginary()).multiply(V));  // Add in Q component of current
			}
			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}

	/**
	 * Constant current load.
	 */
	private void doConstantILoad() {
		Complex V;
		Complex Curr;

		// Computes the current assuming the voltage mag is Vbase
		// Just uses the phase angle off the voltage

		/*
		 * Injection = [s/v]* = [ (P+jQ)/(Vbase * V/|V|)]*
		 */

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase();  // Get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			V    = Vterminal[i];

			Curr = new Complex(WNominal, varNominal).divide( V.divide( V.abs() ).multiply(VBase) ).conjugate();

			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}

	private void doZIPVModel() {
		int i;
		Complex Curr, CurrZ, CurrI, CurrP, V;
		double VMag, vx, evx, yv;

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = Vterminal[i];
			VMag = V.abs();

			if (VMag <= VBase95) {
				Curr = Yeq95.multiply(V);
			} else if (VMag > VBase105) {
				Curr = Yeq105.multiply(V);
			} else {
				CurrZ = new Complex(Yeq.getReal() * ZIPV[0], Yeq.getImaginary() * ZIPV[3]).multiply( Vterminal[i] );
				CurrI = new Complex(WNominal * ZIPV[1], varNominal * ZIPV[4]).divide( V.divide(V.abs()).multiply(VBase) ).conjugate();
				CurrP = new Complex(WNominal * ZIPV[2], varNominal * ZIPV[5]).divide(V).conjugate();
				Curr  = CurrZ.add(CurrI.add(CurrP));
			}

			// low-voltage drop-out
			if (ZIPV[6] > 0.0) {
				vx = 500.0 * (VMag / VBase - ZIPV[6]);
				evx = Math.exp(2 * vx);
				yv = 0.5 * (1 + (evx - 1) / (evx + 1));
				Curr = Curr.multiply(yv);
			}

			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}

	/**
	 * Linear P, quadratic Q.
	 */
	private void doCVRModel() {
		Complex V;
		Complex Curr;
		Complex Cvar;  // var current
		double WattFactor;
		double VarFactor;
		double Vmag;
		double VRatio;

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase();  // Get actual voltage across each phase of the load
		zeroITerminal();

		try {
			for (int i = 0; i < nPhases; i++) {
				V    = Vterminal[i];
				Vmag = V.abs();
				VRatio = Vmag / VBase;  // vbase is l-n for wye and l-l for delta
				// Linear factor adjustment does not converge for some reason while power adjust does easily
				// WattFactor = (1.0 + FCVRwattFactor*(Vmag/VBase - 1.0));
				if (CVRwattFactor != 1.0) {
					WattFactor = Math.pow(VRatio, CVRwattFactor);
				} else {
					WattFactor = VRatio;  // old value (in error): 1.0;
				}
				if (WattFactor > 0.0) {
					Curr = new Complex(WNominal * WattFactor, 0.0).divide(V).conjugate();
				} else {
					Curr = Complex.ZERO;  // P component of current
				}

				/* Compute Q component of current */
				if (CVRvarFactor == 2.0) {  /* Check for easy, quick ones first */
					Cvar = new Complex(0.0, Yeq.getImaginary()).multiply(V); // 2 is same as Constant impedance
				} else if (CVRvarFactor == 3.0) {
					VarFactor = Math.pow(VRatio, 3);
					/*writeDLLDebugFile(String.format("%s, V=%.6g +j %.6g", getName(), V.getReal(), V.getImaginary()));*/
					Cvar      = new Complex(0.0, varNominal * VarFactor).divide(V).conjugate();
				} else {
					/* Other Var factor code here if not squared or cubed */
					VarFactor = Math.pow(VRatio, CVRvarFactor);
					Cvar      = new Complex(0.0, varNominal * VarFactor).divide(V).conjugate();
				}
				Curr = Curr.add(Cvar);  // add in Q component of current
				/*writeDLLDebugFile(String.format("%s, %d, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g ", getName(), i, Vmag, VRatio, WNominal, WattFactor, varNominal, VarFactor, Curr.abs(), V.multiply(Curr.conjugate()).getReal()));*/
				stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
				setITerminalUpdated(true);
				stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
			}
		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg(String.format("Error in Load.%s: %s ", getName(), e.getMessage()), 5871);
		}
	}

	/**
	 * Constant P, Fixed Q.  Q is always kvarBase.
	 */
	private void doFixedQ() {
		Complex Curr, V;
		double Vmag;

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase();  // Get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			V    = Vterminal[i];
			Vmag = V.abs();
			if (Vmag <= VBase95) {
				Curr = new Complex(Yeq95.getReal(), YQFixed).multiply(V);  // Below 95% use an impedance model
			} else if (Vmag > VBase105) {
				Curr = new Complex(Yeq105.getReal(), YQFixed).multiply(V);  // above 105% use an impedance model
			} else {
				Curr = new Complex(WNominal, varBase).divide(V).conjugate();
			}
			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}

	/**
	 * Constant P, Fixed Q.  Q is always a fixed Z derived from kvarBase.
	 */
	private void doFixedQZ() {
		Complex Curr, V;
		double Vmag;

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase();  // Get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			V = Vterminal[i];
			Vmag = V.abs();
			if (Vmag <= VBase95) {
				Curr = new Complex(Yeq95.getReal(), YQFixed).multiply(V);  // Below 95% use an impedance model
			} else if (Vmag >  VBase105) {
				Curr = new Complex(Yeq105.getReal(), YQFixed).multiply(V);
			} else {
				Curr = new Complex(WNominal, 0.0).divide(V).conjugate(); // P component of current
				Curr = Curr.add(new Complex(0.0, YQFixed).multiply(V));  // add in Q component of current
			}

			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}

	/**
	 * Compute Injection Current Only when in harmonics mode.
	 * Assumes spectrum is an ideal current source based on the fundamental current and spectrum.
	 */
	private void doHarmonicMode() {
		Complex Curr, Mult;
		double LoadHarmonic;

		/* Don't calc Vterminal here because it could be undefined! */
		zeroInjCurrent();
		zeroITerminal();

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		LoadHarmonic = sol.getFrequency() / LoadFundamental;  // LoadFundamental = frequency of solution when Harmonic mode entered
		Mult = getSpectrumObj().getMult(LoadHarmonic);
		for (int i = 0; i < nPhases; i++) {
			Curr = Mult.multiply(HarmMag[i]); // Get base harmonic magnitude
			Curr = Utilities.rotatePhasorDeg(Curr, LoadHarmonic, HarmAng[i]);   // Time shift by fundamental
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
		}
	}

	private boolean allTerminalsClosed() {
		for (int i = 0; i < nTerms; i++)
			for (int j = 0; j < nConds; j++)
				if (!Terminals[i].getConductors()[j].isClosed())
					return false;
		return true;
	}

	private void calcVTerminalPhase() {
		int j;
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		/* Establish phase voltages and stick in Vtemp */
		switch (Connection) {
		case 0:
			for (int i = 0; i < nPhases; i++)
				Vterminal[i] = sol.vDiff(NodeRef[i], NodeRef[nConds]);
			break;
		case 1:
			for (int i = 0; i < nPhases; i++) {
				j = i + 1;  // TODO Check zero based indexing
				if (j > nConds)
					j = 0;
				Vterminal[i] = sol.vDiff(NodeRef[i], NodeRef[j]);
			}
			break;
		}

		LoadSolutionCount = sol.getSolutionCount();
	}

	/**
	 * Calculates total load current and adds it properly into the InjCurrent array.
	 */
	private void calcLoadModelContribution() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		setITerminalUpdated(false);

		if (sol.isIsDynamicModel()) {
			//doDynamicMode();  // TODO Implement dynamic mode
		} else if (sol.isIsHarmonicModel() && (sol.getFrequency() != ckt.getFundamental())) {
			doHarmonicMode();
		} else {
			// Compute total Load currents and add into InjCurrent array;
			switch (LoadModel) {
			case 1:
				doConstantPQLoad();  // normal load-flow type load
				break;
			case 2:
				doConstantZLoad();
				break;
			case 3:
				doMotorTypeLoad();  // Constant P, Quadratic Q;
				break;
			case 4:
				doCVRModel();       // mixed motor/resistive load   with CVR factors
				break;
			case 5:
				doConstantILoad();
				break;
			case 6:
				doFixedQ();         // Fixed Q
				break;
			case 7:
				doFixedQZ();        // Fixed, constant Z Q
				break;
			case 8:
				doZIPVModel();
				break;
			default:
				doConstantZLoad();     // FOR now, until we implement the other models.
				break;
			}
		}
	}

	/**
	 * Fill InjCurrent array with the current values to use for injections.
	 */
	private void calcInjCurrentArray() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		// If a terminal is open, then standard load models don't apply, so check it out first.
		if (allTerminalsClosed()) {

			// Now get injection currents
			calcLoadModelContribution();

		} else {

			// FIXME: THIS MAY NOT WORK !!! WATCH FOR BAD RESULTS

			// Some terminals not closed use admittance model for injection.
			if (OpenLoadSolutionCount != ckt.getSolution().getSolutionCount()) {

				// Rebuild the YprimOpenCond if a new solution because values may have changed.

				// Only reallocate when necessary
				if (YPrimOpenCond == null) {
					YPrimOpenCond = new CMatrixImpl(Yorder);
				} else {
					YPrimOpenCond.clear();
				}
				if (YPrimOpenCond.getNOrder() != Yorder) {
					YPrimOpenCond = null;
					YPrimOpenCond = new CMatrixImpl(Yorder);
				}
				calcYPrimMatrix(YPrimOpenCond);

				/* Now account for the open conductors */
				/* For any conductor that is open, zero out row and column */

				int k = 0;
				for (int i = 0; i < nTerms; i++) {
					for (int j = 0; j < nConds; j++) {
						if (!Terminals[i].getConductors()[j].isClosed()) {
							YPrimOpenCond.zeroRow(j + k);
							YPrimOpenCond.zeroCol(j + k);
							YPrimOpenCond.setElement(j + k, j + k, new Complex(1.0e-12, 0.0));  // In case node gets isolated
						}
					}
					k = k + nConds;
				}

				OpenLoadSolutionCount = ckt.getSolution().getSolutionCount();

			}

			computeVterminal();
			YPrimOpenCond.MVMult(ComplexBuffer, Vterminal);
			for (int i = 0; i < Yorder; i++)
				ComplexBuffer[i] = ComplexBuffer[i].negate();
		}
	}

	/**
	 * Always return total terminal currents in the Curr array
	 */
	protected void getTerminalCurrents(Complex[] Curr) {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		if (IterminalSolutionCount != sol.getSolutionCount())  // recalc the contribution
			calcLoadModelContribution();  // Adds totals in Iterminal as a side effect

		super.getTerminalCurrents(Curr);
	}

	/**
	 * Get the injection currents and add them directly into the currents array.
	 */
	public int injCurrents() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		int Result = 0;
		if (isEnabled()) {
			if (sol.isLoadsNeedUpdating())
				setNominalLoad();  // Set the nominal kW, etc. for the type of solution being done
			calcInjCurrentArray();
			Result = super.injCurrents();  // Add into Global Currents Array
		}

		return Result;
	}

	/**
	 * Gets the injection  currents for the last solution performed.
	 * Do not call setNominalLoad, as that may change the load values.
	 */
	public void getInjCurrents(Complex[] Curr) {
		try {
			if (isEnabled()) {
				calcInjCurrentArray();
				// Copy into buffer array
				for (int i = 0; i < Yorder; i++)
					Curr[i] = getInjCurrent()[i];
			} else {
				for (int i = 0; i < Curr.length; i++)
					Curr[i] = Complex.ZERO;
			}
		} catch (Exception e) {
			DSSGlobals.getInstance().doErrorMsg("Load Object: \"" + getName() + "\" in getInjCurrents function.",
					e.getMessage(), "Current buffer may not big enough.", 588);
		}
	}

	/**
	 * Line overload takes precedence.
	 * Assumes that low voltage is due to overloaded line.
	 * If voltage is below Emergency minimum, it is counted as unserved.
	 */
	public boolean getUnserved() {
		double Vpu, Vmag;
		double NormMinCriteria, EmergMinCriteria;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (UE_Factor > 0.0)
			return true;

		// else check voltages
		if (LoadSolutionCount != ckt.getSolution().getSolutionCount())
			calcVTerminalPhase();

		// Get the lowest of the phase voltages
		Vpu = VBase;
		for (int i = 0; i < nPhases; i++) {
			Vmag = Vterminal[i].abs();
			if (Vmag < Vpu)
				Vpu = Vmag;
		}
		Vpu = Vpu / VBase;

		if (VminNormal != 0.0) {
			NormMinCriteria = VminNormal;
		} else {
			NormMinCriteria = ckt.getNormalMinVolts();
		}

		if (VminEmerg != 0.0) {
			EmergMinCriteria = VminEmerg;
		} else {
			EmergMinCriteria = ckt.getEmergMinVolts();
		}

		if (Vpu < EmergMinCriteria) {
			//UE_Factor = 1.0;
			// 9-19-00 RCD  let UE_Factor start small and grow linearly at same slope
			// as EEN_Factor
			UE_Factor = (EmergMinCriteria - Vpu) / (NormMinCriteria - EmergMinCriteria);
			return true;
		}

		return false;
	}

	/**
	 * Line overload takes precedence.
	 * Assumes that low voltage is due to overloaded line.
	 * If voltage is below Normal minumum, it is counted as unserved in proportion
	 * to the difference between the normal and emergency voltage limits.
	 */
	public boolean getExceedsNormal() {
		double Vpu, Vmag;
		double NormMinCriteria, EmergMinCriteria;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (EEN_Factor > 0.0)
			return true;

		// Check line overload

		if (LoadSolutionCount != ckt.getSolution().getSolutionCount())
			calcVTerminalPhase();

		// Get the lowest of the phase voltages
		Vpu = VBase;
		for (int i = 0; i < nPhases; i++) {
			Vmag = Vterminal[i].abs();
			if (Vmag < Vpu)
				Vpu = Vmag;
		}
		Vpu = Vpu / VBase;

		if (VminNormal != 0.0) {
			NormMinCriteria = VminNormal;
		} else {
			NormMinCriteria = ckt.getNormalMinVolts();
		}

		if (VminEmerg != 0.0) {
			EmergMinCriteria = VminEmerg;
		} else {
			EmergMinCriteria = ckt.getEmergMinVolts();
		}

		if (Vpu < NormMinCriteria) {
			EEN_Factor = (NormMinCriteria - Vpu) / (NormMinCriteria - EmergMinCriteria);
			// 9-19-00 RCD  Let EEN factor grow linearly at same slope
			// IF EEN_Factor > 1.0 THEN EEN_Factor = 1.0;
			return true;
		}

		return false;
	}

	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			switch (i) {
			case 3:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + kWBase);
				break;
			case 4:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + PFNominal);
				break;
			case 11:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + kvarBase);
				break;
			case 21:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + kVAAllocationFactor);
				break;
			case 22:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + kVABase);
				break;
			case 32:
				F.print("~ " + getParentClass().getPropertyName()[i] + "=");
				for (int j = 0; j < nZIPV; j++)
					F.print(ZIPV[j] + " ");
				F.println("\"");
				break;
			default:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));
				break;
			}
		}
	}

	/**
	 * Allocate load from connected kva or kWh billing.
	 */
	public void setkVAAllocationFactor(double Value) {
		kVAAllocationFactor = Value;
		AllocationFactor = Value;
		LoadSpecType = 3;
		computeAllocatedLoad();
		HasBeenAllocated = true;
	}

	/**
	 * AllocationFactor adjusts either connected kVA allocation factor or kWh CFactor.
	 *
	 * This procedure is used by the EnergyMeter allocateLoad function to adjust load allocation factors.
	 */
	public void setAllocationFactor(double Value) {
		AllocationFactor = Value;
		switch (LoadSpecType) {
		case 3:
			kVAAllocationFactor = Value;
			break;
		case 4:
			CFactor             = Value;
			break;
		}
		computeAllocatedLoad();  // update kWbase
		HasBeenAllocated = true;
	}

	public void setCFactor(double Value) {
		CFactor = Value;
		AllocationFactor = Value;
		LoadSpecType = 4;
		computeAllocatedLoad();
		HasBeenAllocated = true;
	}

	public void setConnectedkVA(double Value) {
		ConnectedkVA = Value;
		LoadSpecType = 3;
		AllocationFactor = kVAAllocationFactor;
		computeAllocatedLoad();
	}

	public void setKWh(double Value) {
		kWh = Value;
		LoadSpecType = 4;
		AllocationFactor = CFactor;
		computeAllocatedLoad();
	}

	public void setKWhDays(double Value) {
		kWhDays = Value;
		LoadSpecType = 4;
		computeAllocatedLoad();
	}

	/**
	 * Fixed loads defined by kW, kvar or kW, pf are ignored.
	 */
	private void computeAllocatedLoad() {
		switch (LoadSpecType) {
		case 3:
			if (ConnectedkVA > 0.0) {
				kWBase = ConnectedkVA * kVAAllocationFactor * Math.abs(PFNominal);
				kvarBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
				if (PFNominal < 0.0)
					kvarBase = -kvarBase;
			}
			break;
		case 4:
			AvgkW = kWh / (kWhDays * 24);
			kWBase = AvgkW * CFactor;
			kvarBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			if (PFNominal < 0.0)
				kvarBase = -kvarBase;
			break;
		}
	}

	/**
	 * Get the present terminal currents and store for harmonics base reference.
	 */
	public void initHarmonics() {
		Complex[] Currents;

		/* Make sure there's enough memory */
		HarmMag = (double[]) Utilities.resizeArray(HarmMag, nPhases);
		HarmAng = (double[]) Utilities.resizeArray(HarmAng, nPhases);
		Currents = new Complex[Yorder];  // to hold currents

		LoadFundamental = DSSGlobals.getInstance().getActiveCircuit().getSolution().getFrequency();

		getCurrents(Currents);
		/* Store the currents at fundamental frequency.
		 * The spectrum is applied to these.
		 */
		for (int i = 0; i < nPhases; i++) {
			HarmMag[i] = Currents[i].abs();
			HarmAng[i] = Currents[i].degArg();
		}

		Currents = null;  // get rid of temp space
	}

	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0]  = "3";        // "phases";
		PropertyValue[1]  = getBus(1);  // "bus1";
		PropertyValue[2]  = "12.47";
		PropertyValue[3]  = "10";
		PropertyValue[4]  = ".88";
		PropertyValue[5]  = "1";
		PropertyValue[6]  = "";
		PropertyValue[7]  = "";
		PropertyValue[8]  = "";
		PropertyValue[9]  = "";
		PropertyValue[10] = "wye";
		PropertyValue[11] = "5";
		PropertyValue[12] = "-1"; // "rneut"; // IF entered -, assume open or user defined
		PropertyValue[13] = "0";  //"xneut";
		PropertyValue[14] = "variable"; //"status";  // fixed or variable
		PropertyValue[15] = "1"; //class
		PropertyValue[16] = "0.95";
		PropertyValue[17] = "1.05";
		PropertyValue[18] = "0.0";
		PropertyValue[19] = "0.0";
		PropertyValue[20] = "0.0";
		PropertyValue[21] = "0.5";  // Allocation Factor
		PropertyValue[22] = "11.3636";
		PropertyValue[23] = "50";
		PropertyValue[24] = "10";
		PropertyValue[25] = "1";  // CVR watt factor
		PropertyValue[26] = "2";  // CVR var factor
		PropertyValue[27] = "0";  // kwh bulling
		PropertyValue[28] = "30";  // kwhdays
		PropertyValue[29] = "4";  // Cfactor
		PropertyValue[30] = "";  // CVRCurve
		PropertyValue[31] = "1";  // NumCust
		PropertyValue[32] = "";  // ZIPV coefficient array

		super.initPropertyValues(Load.NumPropsThisClass);
	}

	/**
	 * Make a positive sequence model.
	 */
	public void makePosSequence() {
		double V;

		String S = "Phases=1 conn=wye";

		// Make sure voltage is line-neutral
		if ((nPhases > 1) || (Connection != 0)) {
			V = kVLoadBase / DSSGlobals.SQRT3;
		} else {
			V = kVLoadBase;
		}

		S = S + String.format(" kV=%-.5g", V);

		// Divide the load by no. phases
		if (nPhases > 1) {
			S = S + String.format(" kW=%-.5g  kvar=%-.5g", kWBase / nPhases, kvarBase / nPhases);
			if (ConnectedkVA > 0.0)
				S = S + String.format(" xfkVA=%-.5g  ", ConnectedkVA / nPhases);
		}

		Parser.getInstance().setCmdString(S);
		edit();

		super.makePosSequence();
	}

	public String getPropertyValue(int Index) {
		switch (Index) {
		case 1:
			return getBus(1);
		case 2:
			return String.format("%-g", kVLoadBase);
		case 3:
			return String.format("%-g", kWBase);
		case 4:
			return String.format("%-.3g", PFNominal);
		case 6:
			return YearlyShape;
		case 7:
			return DailyShape;
		case 8:
			return DutyShape;
		case 11:
			return String.format("%-.3g", kvarBase);
		case 21:
			return String.format("%-.3g", kVAAllocationFactor);
		case 22:
			return String.format("%-g", kVABase);
		case 29:
			return String.format("%-.3g", CFactor);
		case 32:
			String Result = "";
			for (int i = 0; i < nZIPV; i++)
				Result = Result + String.format(" %-g", ZIPV[i]);
			return Result;
		default:
			return super.getPropertyValue(Index);
		}
	}

	public double getkVAAllocationFactor() {
		return kVAAllocationFactor;
	}

	public double getConnectedkVA() {
		return ConnectedkVA;
	}

	public double getCFactor() {
		return CFactor;
	}

	public double getKWh() {
		return kWh;
	}

	public double getKWhDays() {
		return kWhDays;
	}

	public double getAllocationFactor() {
		return AllocationFactor;
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}

	public String getDailyShape() {
		return DailyShape;
	}

	public void setDailyShape(String dailyShape) {
		DailyShape = dailyShape;
	}

	public LoadShapeObj getDailyShapeObj() {
		return DailyShapeObj;
	}

	public void setDailyShapeObj(LoadShapeObj dailyShapeObj) {
		DailyShapeObj = dailyShapeObj;
	}

	public String getDutyShape() {
		return DutyShape;
	}

	public void setDutyShape(String dutyShape) {
		DutyShape = dutyShape;
	}

	public LoadShapeObj getDutyShapeObj() {
		return DutyShapeObj;
	}

	public void setDutyShapeObj(LoadShapeObj dutyShapeObj) {
		DutyShapeObj = dutyShapeObj;
	}

	public double getEEN_Factor() {
		return EEN_Factor;
	}

	public void setEEN_Factor(double eEN_Factor) {
		EEN_Factor = eEN_Factor;
	}

	public String getGrowthShape() {
		return GrowthShape;
	}

	public void setGrowthShape(String growthShape) {
		GrowthShape = growthShape;
	}

	public GrowthShapeObj getGrowthShapeObj() {
		return GrowthShapeObj;
	}

	public void setGrowthShapeObj(GrowthShapeObj growthShapeObj) {
		GrowthShapeObj = growthShapeObj;
	}

	public Boolean getHasBeenAllocated() {
		return HasBeenAllocated;
	}

	public void setHasBeenAllocated(Boolean hasBeenAllocated) {
		HasBeenAllocated = hasBeenAllocated;
	}

	public double getkWBase() {
		return kWBase;
	}

	public void setkWBase(double kWBase) {
		this.kWBase = kWBase;
	}

	public double getkVABase() {
		return kVABase;
	}

	public void setkVABase(double kVABase) {
		this.kVABase = kVABase;
	}

	public double getKvarBase() {
		return kvarBase;
	}

	public void setKvarBase(double kvarBase) {
		this.kvarBase = kvarBase;
	}

	public double getkVLoadBase() {
		return kVLoadBase;
	}

	public void setkVLoadBase(double kVLoadBase) {
		this.kVLoadBase = kVLoadBase;
	}

	public int getLoadClass() {
		return LoadClass;
	}

	public void setLoadClass(int loadClass) {
		LoadClass = loadClass;
	}

	public int getNumCustomers() {
		return NumCustomers;
	}

	public void setNumCustomers(int numCustomers) {
		NumCustomers = numCustomers;
	}

	public int getLoadSpecType() {
		return LoadSpecType;
	}

	public void setLoadSpecType(int loadSpecType) {
		LoadSpecType = loadSpecType;
	}

	public double getPFNominal() {
		return PFNominal;
	}

	public void setPFNominal(double pFNominal) {
		PFNominal = pFNominal;
	}

	public double getRneut() {
		return Rneut;
	}

	public void setRneut(double rneut) {
		Rneut = rneut;
	}

	public double getUE_Factor() {
		return UE_Factor;
	}

	public void setUE_Factor(double uE_Factor) {
		UE_Factor = uE_Factor;
	}

	public double getXneut() {
		return Xneut;
	}

	public void setXneut(double xneut) {
		Xneut = xneut;
	}

	public String getYearlyShape() {
		return YearlyShape;
	}

	public void setYearlyShape(String yearlyShape) {
		YearlyShape = yearlyShape;
	}

	public LoadShapeObj getYearlyShapeObj() {
		return YearlyShapeObj;
	}

	public void setYearlyShapeObj(LoadShapeObj yearlyShapeObj) {
		YearlyShapeObj = yearlyShapeObj;
	}

	public String getCVRshape() {
		return CVRshape;
	}

	public void setCVRshape(String cVRshape) {
		CVRshape = cVRshape;
	}

	public LoadShapeObj getCVRShapeObj() {
		return CVRShapeObj;
	}

	public void setCVRShapeObj(LoadShapeObj cVRShapeObj) {
		CVRShapeObj = cVRShapeObj;
	}

	public int getLoadModel() {
		return LoadModel;
	}

	public void setLoadModel(int loadModel) {
		LoadModel = loadModel;
	}

	public double getPuMean() {
		return puMean;
	}

	public double getPuStdDev() {
		return puStdDev;
	}

	public double getCVRwattFactor() {
		return CVRwattFactor;
	}

	public double getCVRvarFactor() {
		return CVRvarFactor;
	}

	public double getVmaxpu() {
		return Vmaxpu;
	}

	public double getVminEmerg() {
		return VminEmerg;
	}

	public double getVminNormal() {
		return VminNormal;
	}

	public double getVminpu() {
		return Vminpu;
	}

	public boolean isExemptFromLDCurve() {
		return ExemptFromLDCurve;
	}

	public boolean isFixed() {
		return Fixed;
	}

	// FIXME Private members in OpenDSS.

	public boolean isPFChanged() {
		return PFChanged;
	}

	public void setPFChanged(boolean pFChanged) {
		PFChanged = pFChanged;
	}

	public double getkWh() {
		return kWh;
	}

	public void setkWh(double kWh) {
		this.kWh = kWh;
	}

	public double getkWhDays() {
		return kWhDays;
	}

	public void setkWhDays(double kWhDays) {
		this.kWhDays = kWhDays;
	}

	public double getAvgkW() {
		return AvgkW;
	}

	public void setAvgkW(double avgkW) {
		AvgkW = avgkW;
	}

	public double[] getHarmAng() {
		return HarmAng;
	}

	public void setHarmAng(double[] harmAng) {
		HarmAng = harmAng;
	}

	public double[] getHarmMag() {
		return HarmMag;
	}

	public void setHarmMag(double[] harmMag) {
		HarmMag = harmMag;
	}

	public double getLastGrowthFactor() {
		return LastGrowthFactor;
	}

	public void setLastGrowthFactor(double lastGrowthFactor) {
		LastGrowthFactor = lastGrowthFactor;
	}

	public int getLastYear() {
		return LastYear;
	}

	public void setLastYear(int lastYear) {
		LastYear = lastYear;
	}

	public double getLoadFundamental() {
		return LoadFundamental;
	}

	public void setLoadFundamental(double loadFundamental) {
		LoadFundamental = loadFundamental;
	}

	public int getLoadSolutionCount() {
		return LoadSolutionCount;
	}

	public void setLoadSolutionCount(int loadSolutionCount) {
		LoadSolutionCount = loadSolutionCount;
	}

	public int getOpenLoadSolutionCount() {
		return OpenLoadSolutionCount;
	}

	public void setOpenLoadSolutionCount(int openLoadSolutionCount) {
		OpenLoadSolutionCount = openLoadSolutionCount;
	}

	public double getRandomMult() {
		return RandomMult;
	}

	public void setRandomMult(double randomMult) {
		RandomMult = randomMult;
	}

	public Complex getShapeFactor() {
		return ShapeFactor;
	}

	public void setShapeFactor(Complex shapeFactor) {
		ShapeFactor = shapeFactor;
	}

	public double getVarBase() {
		return varBase;
	}

	public void setVarBase(double varBase) {
		this.varBase = varBase;
	}

	public double getVarNominal() {
		return varNominal;
	}

	public void setVarNominal(double varNominal) {
		this.varNominal = varNominal;
	}

	public double getVBase() {
		return VBase;
	}

	public void setVBase(double vBase) {
		VBase = vBase;
	}

	public double getVBase105() {
		return VBase105;
	}

	public void setVBase105(double vBase105) {
		VBase105 = vBase105;
	}

	public double getVBase95() {
		return VBase95;
	}

	public void setVBase95(double vBase95) {
		VBase95 = vBase95;
	}

	public double getWNominal() {
		return WNominal;
	}

	public void setWNominal(double wNominal) {
		WNominal = wNominal;
	}

	public Complex getYeq() {
		return Yeq;
	}

	public void setYeq(Complex yeq) {
		Yeq = yeq;
	}

	public Complex getYeq105() {
		return Yeq105;
	}

	public void setYeq105(Complex yeq105) {
		Yeq105 = yeq105;
	}

	public Complex getYeq95() {
		return Yeq95;
	}

	public void setYeq95(Complex yeq95) {
		Yeq95 = yeq95;
	}

	public Complex getYneut() {
		return Yneut;
	}

	public void setYneut(Complex yneut) {
		Yneut = yneut;
	}

	public CMatrix getYPrimOpenCond() {
		return YPrimOpenCond;
	}

	public void setYPrimOpenCond(CMatrix yPrimOpenCond) {
		YPrimOpenCond = yPrimOpenCond;
	}

	public double getYQFixed() {
		return YQFixed;
	}

	public void setYQFixed(double yQFixed) {
		YQFixed = yQFixed;
	}

	public void setPuMean(double puMean) {
		this.puMean = puMean;
	}

	public void setPuStdDev(double puStdDev) {
		this.puStdDev = puStdDev;
	}

	public void setCVRwattFactor(double cVRwattFactor) {
		CVRwattFactor = cVRwattFactor;
	}

	public void setCVRvarFactor(double cVRvarFactor) {
		CVRvarFactor = cVRvarFactor;
	}

	public void setVmaxpu(double vmaxpu) {
		Vmaxpu = vmaxpu;
	}

	public void setVminEmerg(double vminEmerg) {
		VminEmerg = vminEmerg;
	}

	public void setVminNormal(double vminNormal) {
		VminNormal = vminNormal;
	}

	public void setVminpu(double vminpu) {
		Vminpu = vminpu;
	}

	public void setExemptFromLDCurve(boolean exemptFromLDCurve) {
		ExemptFromLDCurve = exemptFromLDCurve;
	}

	public void setFixed(boolean fixed) {
		Fixed = fixed;
	}

	public boolean isShapeIsActual() {
		return ShapeIsActual;
	}

	public void setShapeIsActual(boolean shapeIsActual) {
		ShapeIsActual = shapeIsActual;
	}

	public double[] getZIPV() {
		return ZIPV;
	}

	public void setZIPV(double[] zIPV) {
		ZIPV = zIPV;
	}

	public void setnZIPV(int nZIPV) {
		this.nZIPV = nZIPV;
	}

	public int getnZIPV() {
		return nZIPV;
	}

}
