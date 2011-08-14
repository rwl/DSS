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
	/** For all types of allocation */
	private double allocationFactor;
	/** For connected kVA specification */
	private double kVAAllocationFactor;
	private double connectedkVA;
	private double kWh;
	private double kWhDays;
	/** For kWh billed spec */
	private double CFactor;
	private double avgKW;
	/** References for harmonics mode */
	private double[] harmAng;
	private double[] harmMag;
	private double lastGrowthFactor;
	/** Added for speedup so we don't have to search for growth factor a lot */
	private int lastYear;
	private double loadFundamental;
	private int loadSolutionCount;
	private int openLoadSolutionCount;
	private double randomMult;
	private Complex shapeFactor;
	/** Base vars per phase */
	private double varBase;
	private double varNominal;
	/** Base volts suitable for computing currents */
	private double VBase;
	private double VBase105;
	private double VBase95;
	/** Nominal Watts per phase */
	private double WNominal;
	/** At nominal */
	private Complex Yeq;
	private Complex Yeq105;
	private Complex Yeq95;
	private Complex Yneut;
	/** To handle cases where one conductor of load is open */
	private CMatrix YPrimOpenCond;
	/** Fixed value of y for type 7 load */
	private double YQFixed;
	private double[] ZIPV;
	private int nZIPV;

	// formerly private, now read-only properties
	protected double puMean;
	protected double puStdDev;
	protected double CVRwattFactor;
	protected double CVRvarFactor;
	protected double VMaxPU;
	protected double VMinEmerg;  // overrides system settings if != 0.0
	protected double VMinNormal;
	protected double VMinPU;
	protected boolean exemptFromLDCurve;
	protected boolean fixed;  // if fixed, always at base value
	protected boolean shapeIsActual;

	/** 0 = line-neutral; 1 = Delta */
	protected int connection;
	/** Daily (24 HR) load shape */
	protected String dailyShape;
	/** Daily load shape for this load */
	protected LoadShapeObj dailyShapeObj;
	/** Duty cycle load shape for changes typically less than one hour */
	protected String dutyShape;
	/** Shape for this load */
	protected LoadShapeObj dutyShapeObj;
	/** Is overloaded factor is the amount of overload */
	protected double EEN_Factor;
	/** (year, Multiplier from previous year) */
	protected String growthShape;
	/** Shape for this growth curve */
	protected GrowthShapeObj growthShapeObj;
	protected Boolean hasBeenAllocated;
	protected double kWBase;
	protected double kVABase;
	protected double kVArBase;
	protected double kVLoadBase;
	protected int loadClass;
	protected int numCustomers;
	/** 0 = kW, PF; 1 = kw, kvar; 2 = kva, PF */
	protected int loadSpecType;
	protected double PFNominal;
	protected double RNeut;
	/** These are set to > 0 if a line in the critical path */
	protected double UE_Factor;
	/** Neutral impedance */
	protected double XNeut;
	/** ='fixed' means no variation exempt from variation */
	protected String yearlyShape;
	/** Shape for this load */
	protected LoadShapeObj yearlyShapeObj;
	protected String CVRshape;
	protected LoadShapeObj CVRShapeObj;

	/**
	 * Variation with voltage.
	 *
	 *   1 = Constant kVA (P,Q always in same ratio)
	 *   2 = Constant impedance
	 *   3 = Constant P, Quadratic Q (Mostly motor)
	 *   4 = Linear P, Quadratic Q  (Mixed motor/resistive Use this for CVR studies
	 *   5 = Constant |I|
	 *   6 = Constant P (Variable); Q is fixed value (not variable)
	 *   7 = Constant P (Variable); Q is fixed Z (not variable)
	 *   8 = ZIPV (3 real power coefficients, 3 reactive, Vcutoff)
	 */
	protected int loadModel;

	public LoadObjImpl(DSSClassImpl parClass, String sourceName) {
		super(parClass);
		setName(sourceName.toLowerCase());
		objType = parClass.getDSSClassType();

		setNPhases(3);
		this.nConds = 4;  // defaults to wye so it has a 4th conductor
		this.YOrder        = 0;  // to trigger an initial allocation
		setNTerms(1);  // forces allocations
		this.kWBase        = 10.0;
		this.kVArBase      = 5.0;
		this.PFNominal     = 0.88;
		this.kVABase       = kWBase / PFNominal;
		this.loadSpecType  = 0;
		this.RNeut         = -1.0;  // signify neutral is open
		this.XNeut         = 0.0;

		this.yearlyShape    = "";
		this.yearlyShapeObj = null;  // if yearlyShapeObj = null then the load alway stays nominal * global multipliers
		this.dailyShape     = "";
		this.dailyShapeObj  = null;  // if dailyShapeObj = null then the load alway stays nominal * global multipliers
		this.dutyShape      = "";
		this.dutyShapeObj   = null;  // if dutyShapeObj = null then the load alway stays nominal * global multipliers
		this.growthShape    = "";
		this.growthShapeObj = null;  // if growthShapeObj = null then the load alway stays nominal * global multipliers
		this.CVRshape       = "";
		this.CVRShapeObj    = null;
		this.connection     = 0;  // wye (star)
		this.loadModel      = 1;  // changed from 2 RCD (easiest to solve)
		this.loadClass      = 1;
		this.numCustomers   = 1;
		this.lastYear       = 0;
		this.CVRwattFactor  = 1.0;
		this.CVRvarFactor   = 2.0;

		this.lastGrowthFactor = 1.0;
		this.kVAAllocationFactor = 0.5;
		this.allocationFactor = this.kVAAllocationFactor;
		this.hasBeenAllocated = false;
		this.PFChanged        = false;
		this.shapeIsActual    = false;

		this.loadSolutionCount     = -1;  // for keeping track of the present solution in injcurrent calcs
		this.openLoadSolutionCount = -1;
		this.YPrimOpenCond         = null;

		this.connectedkVA = 0.0;  // loadSpecType=3
		this.kWh        = 0.0;    // loadSpecType=4
		this.CFactor    = 4.0;
		this.kWhDays    = 30.0;
		this.VMinNormal = 0.0;    // indicates for program to use circuit quantities
		this.VMinEmerg  = 0.0;
		this.kVLoadBase = 12.47;
		this.VBase      = 7200.0;
		this.VMinPU     = 0.95;
		this.VMaxPU     = 1.05;
		this.VBase95    = this.VMinPU * this.VBase;
		this.VBase105   = this.VMaxPU * this.VBase;
		this.YOrder     = this.nTerms * this.nConds;
		this.randomMult = 1.0 ;
		this.fixed      = false;
		this.exemptFromLDCurve = false;

		this.puMean     = 0.5;
		this.puStdDev   = 0.1;
		this.UE_Factor  = 0.0;
		this.EEN_Factor = 0.0;
		this.spectrum   = "defaultload";  // override base class definition
		this.harmMag    = null;
		this.harmAng    = null;
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
	 * 1 = Gaussian around mean and std dev
	 * 2 = uniform
	 */
	public void randomize(int opt) {
		switch (opt) {
		case 0:
			randomMult = 1.0;
			break;
		case DSSGlobals.GAUSSIAN:
			if (yearlyShapeObj != null) {
				randomMult = MathUtil.gauss(yearlyShapeObj.getMean(), yearlyShapeObj.getStdDev());
			} else {
				randomMult = MathUtil.gauss(puMean, puStdDev);
			}
			break;
		case DSSGlobals.UNIFORM:
			randomMult = Math.random();  // number between 0 and 1.0
			break;
		case DSSGlobals.LOGNORMAL:
			if (yearlyShapeObj != null) {
				randomMult = MathUtil.quasiLognormal(yearlyShapeObj.getMean());
			} else {
				randomMult = MathUtil.quasiLognormal(puMean);
			}
			break;
		}
	}

	private void calcDailyMult(double hr) {
		if (dailyShapeObj != null) {
			shapeFactor   = dailyShapeObj.getMult(hr);
			shapeIsActual = dailyShapeObj.isUseActual();
		} else {
			shapeFactor = new Complex(1.0, 1.0);  // default to no daily variation
		}
	}

	private void calcDutyMult(double hr) {
		if (dutyShapeObj != null) {
			shapeFactor   = dutyShapeObj.getMult(hr);
			shapeIsActual = dutyShapeObj.isUseActual();
		} else {
			calcDailyMult(hr);  // default to daily mult if no duty curve specified
		}
	}

	private void calcYearlyMult(double hr) {
		/* Yearly curve is assumed to be hourly only */
		if (yearlyShapeObj != null) {
			shapeFactor   = yearlyShapeObj.getMult(hr);
			shapeIsActual = yearlyShapeObj.isUseActual();
		} else {
			shapeFactor = new Complex(1.0, 1.0);  // defaults to no variation
		}
	}

	private void calcCVRMult(double hr) {
		Complex CVRFactor;

		/* CVR curve is assumed to be used in a yearly simulation */
		if (CVRShapeObj != null) {
			CVRFactor     = CVRShapeObj.getMult(hr);  /* Complex */
			CVRwattFactor = CVRFactor.getReal();
			CVRvarFactor  = CVRFactor.getImaginary();
		} else {
			/* CVRWattFactor, etc. remain unchanged */
		}
	}

	private double growthFactor(int year) {
		if (year == 0) {
			lastGrowthFactor = 1.0;  // default all to 1 in year 0 ; use base values
		} else {
			if (growthShapeObj == null) {
				lastGrowthFactor = DSSGlobals.getInstance().getActiveCircuit().getDefaultGrowthFactor();
			} else if (year != lastYear) {  // search growth curve
				lastGrowthFactor = growthShapeObj.getMult(year);
			}
		}
		return lastGrowthFactor;  // for now
	}

	public void setKW_KVAr(double PkW, double QkVAr) {
		kWBase = PkW;
		kVArBase = QkVAr;
		loadSpecType = 1;
	}

	public void setNominalLoad() {
		double factor;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		shapeFactor = CDOUBLEONE;
		shapeIsActual = false;

		if (fixed) {
			factor = growthFactor(sol.getYear());  // for fixed loads, consider only growth factor
		} else {
			switch (sol.getMode()) {
			case Dynamics.SNAPSHOT:
				if (exemptFromLDCurve) {
					factor = growthFactor(sol.getYear());
				} else {
					factor = ckt.getLoadMultiplier() * growthFactor(sol.getYear());
				}
				break;
			case Dynamics.HARMONICMODE:
				if (exemptFromLDCurve) {
					factor = growthFactor(sol.getYear());
				} else {
					factor = ckt.getLoadMultiplier() * growthFactor(sol.getYear());
				}
				break;
			case Dynamics.DAILYMODE:
				factor = growthFactor(sol.getYear());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				calcDailyMult(sol.getDblHour());
				break;
			case Dynamics.YEARLYMODE:
				factor = ckt.getLoadMultiplier() * growthFactor(sol.getYear());
				calcYearlyMult(sol.getDblHour());
				if (loadModel == 4)
					calcCVRMult(sol.getDblHour());
				break;
			case Dynamics.DUTYCYCLE:
				factor = growthFactor(sol.getYear());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				calcDutyMult(sol.getDblHour());
				break;
			case Dynamics.GENERALTIME:
				factor = growthFactor(sol.getYear());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				// this mode allows use of one class of load shape
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
					shapeFactor = Complex.ONE;  // default to 1 + j1 if not known
					break;
				}
				break;
			case Dynamics.DYNAMICMODE:
				factor = growthFactor(sol.getYear());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				// this mode allows use of one class of load shape
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
					shapeFactor = Complex.ONE;  // default to 1 + j1 if not known
					break;
				}
				break;
			case Dynamics.MONTECARLO1:
				randomize(sol.getRandomType());
				factor = randomMult * growthFactor(sol.getYear());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				break;
			case Dynamics.MONTECARLO2:
				factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				break;
			case Dynamics.MONTECARLO3:
				factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				break;
			case Dynamics.LOADDURATION1:
				factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				break;
			case Dynamics.LOADDURATION2:
				factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				break;
			case Dynamics.PEAKDAY:
				factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				break;
			case Dynamics.AUTOADDFLAG:
				factor = growthFactor(sol.getYear());  // loadMult = 1.0 by default
				break;
			default:
				factor = growthFactor(sol.getYear());  // defaults to Base kW * growth
				break;
			}
		}

		if (shapeIsActual) {
			WNominal   = 1000.0 * shapeFactor.getReal() / nPhases;
			varNominal = 1000.0 * shapeFactor.getImaginary() / nPhases;
		} else {
			WNominal   = 1000.0 * kWBase   * factor * shapeFactor.getReal() / nPhases ;
			varNominal = 1000.0 * kVArBase * factor * shapeFactor.getImaginary() / nPhases;
		}

		Yeq = new Complex(WNominal, -varNominal).divide( Math.pow(VBase, 2) );
		if (VMinPU != 0.0) {
			Yeq95 = Yeq.divide( Math.pow(VMinPU, 2) );  // at 95% voltage
		} else {
			Yeq95 = Complex.ZERO;
		}

		if (VMaxPU != 0.0) {
			Yeq105 = Yeq.divide( Math.pow(VMaxPU, 2) );  // at 105% voltage
		} else {
			Yeq105 = Yeq;
		}
	}

	public void recalcElementData() {
		DSSGlobals global = DSSGlobals.getInstance();

		VBase95  = VMinPU * VBase;
		VBase105 = VMaxPU * VBase;

		/* Set kW and kVAr from root values of kVA and PF */

		switch (loadSpecType) {
		case 0:  /* kW, PF */
			kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			if (PFNominal < 0.0)
				kVArBase = -kVArBase;
			kVABase = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kVArBase, 2));
			break;
		case 1:  /* kW, kvar -- need to set PFNominal */
			kVABase = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kVArBase, 2));
			if (kVABase > 0.0) {
				PFNominal = kWBase / kVABase;
				/* If kW and kvar are different signs, PF is negative */
				if (kVArBase != 0.0)
					PFNominal = PFNominal * Math.signum(kWBase * kVArBase);
			} else {
				// leave it as it is
			}
			break;
		case 2:  /* kVA, PF */
			kWBase   = kVABase * Math.abs(PFNominal);
			kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			if (PFNominal < 0.0)
				kVArBase = -kVArBase;
			break;
		case 3:
			if (PFChanged) {  // recompute kVAr base
				kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
				if (PFNominal < 0.0)
					kVArBase = -kVArBase;
				kVABase = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kVArBase, 2));
			}
			break;
		case 4:
			if (PFChanged) {  // recompute kVAr base
				kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
				if (PFNominal < 0.0)
					kVArBase = -kVArBase;
				kVABase = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kVArBase, 2));
			}
			break;
		}

		setNominalLoad();

		/* Now check for errors. If any of these came out nil and the string was not nil, give warning. */
		if (yearlyShape.equalsIgnoreCase("none"))
			yearlyShape = "";
		if (dailyShape.equalsIgnoreCase("none"))
			dailyShape = "";
		if (dutyShape.equalsIgnoreCase("none"))
			dutyShape = "";

		if (yearlyShapeObj == null)
			if (yearlyShape.length() > 0)
				global.doSimpleMsg("Warning: Yearly load shape: \""+ yearlyShape +"\" not found.", 583);
		if (dailyShapeObj == null)
			if (dailyShape.length() > 0)
				global.doSimpleMsg("Warning: Daily load shape: \""+ dailyShape +"\" not found.", 584);
		if (dutyShapeObj == null)
			if (dutyShape.length() > 0)
				global.doSimpleMsg("Warning: Duty load shape: \""+ dutyShape +"\" not found.", 585);
		if (growthShapeObj == null)
			if (growthShape.length() > 0)
				global.doSimpleMsg("Warning: Yearly growth shape: \""+ growthShape +"\" not found.", 586);
		if (CVRShapeObj == null)
			if (CVRshape.length() > 0)
				global.doSimpleMsg("Warning: CVR shape shape: \""+ CVRshape +"\" not found.", 586);

		setSpectrumObj( (com.epri.dss.general.SpectrumObj) global.getSpectrumClass().find(getSpectrum()) );
		if (getSpectrumObj() == null)
			global.doSimpleMsg("Error: Spectrum \""+getSpectrum()+"\" not found.", 587);

		if (RNeut < 0.0) {  // flag for open neutral
			Yneut = new Complex(0.0, 0.0);
		} else if ((RNeut == 0.0) && (XNeut == 0.0)) {  // solidly grounded
			Yneut = new Complex(1.0e6, 0.0);  // 1 microohm resistor
		} else {
			Yneut = new Complex(RNeut, XNeut).invert();
		}

		varBase = 1000.0 * kVArBase / nPhases;
		YQFixed = -varBase / Math.pow(VBase, 2);

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), YOrder) );

		setPFChanged(false);
	}

	private void calcYPrimMatrix(CMatrix YMatrix) {
		Complex Y, Yij;
		int i, j;
		double freqMultiplier;

		YPrimFreq = DSSGlobals.getInstance().getActiveCircuit().getSolution().getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;
		Y = Yeq;
		Y = new Complex(Y.getReal(), Y.getImaginary() / freqMultiplier);  /* Correct reactive part for frequency */
		Yij = Y.negate();

		switch (connection) {
		case 0:  // wye
			for (i = 0; i < nPhases; i++) {
				YMatrix.setElement(i, i, Y);
				YMatrix.addElement(nConds, nConds, Y);
				YMatrix.setElemSym(i, nConds, Yij);
			}
			YMatrix.addElement(nConds, nConds, Yneut);  // neutral

			/* If neutral is floating, make sure there is some small
			 * connection to ground  by increasing the last diagonal slightly.
			 */
			if (RNeut < 0.0)
				YMatrix.setElement(nConds, nConds, YMatrix.getElement(nConds, nConds).multiply(1.000001));
			break;
		case 1:  // delta or L-L
			for (i = 0; i < nPhases; i++) {
				j = i + 1;
				if (j > nConds)
					j = 0;  // wrap around for closed connections
				YMatrix.addElement(i, i, Y);
				YMatrix.addElement(j, j, Y);
				YMatrix.addElemSym(i, j, Yij);  // get both off-diagonal elements
			}
			break;
		}
	}

	/**
	 * If doing an analysis that requires the load to be modeled as an impedance
	 * then put all in.
	 */
	public void calcYPrim() {

		// build only YPrim shunt for a load then copy to YPrim
		// build a dummy Yprim series so that calcV does not fail
		if (isYprimInvalid()) {
			if (YPrimShunt != null) YPrimShunt = null;
			if (YPrimSeries != null) YPrimSeries = null;
			if (YPrim != null) YPrim = null;

			YPrimSeries = new CMatrixImpl(YOrder);
			YPrimShunt  = new CMatrixImpl(YOrder);
			YPrim        = new CMatrixImpl(YOrder);
		} else {
			YPrimShunt.clear();
			YPrimSeries.clear();
			YPrim.clear();
		}

		if (DSSGlobals.getInstance().getActiveCircuit().getSolution().getLoadModel() == DSSGlobals.POWERFLOW) {

			setNominalLoad();  // same as admittance model
			calcYPrimMatrix(YPrimShunt);

		} else {
			// admittance model wanted

			setNominalLoad();
			calcYPrimMatrix(YPrimShunt);
		}

		// set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
		for (int i = 0; i < YOrder; i++)
			YPrimSeries.setElement(i, i, YPrimShunt.getElement(i, i).multiply(1.0e-10));

		YPrim.copyFrom(YPrimShunt);

		// account for open conductors
		super.calcYPrim();
	}

	/**
	 * Put the current into the proper location according to connection.
	 */
	private void stickCurrInTerminalArray(Complex[] termArray, Complex curr, int i) {
		switch (connection) {
		case 0:  // wye
			termArray[i] = termArray[i].add(curr.negate());
			termArray[nConds] = termArray[nConds].add(curr);  // neutral
			break;
		case 1:  // delta
			termArray[i] = termArray[i].add(curr.negate());
			int j = i + 1;
			if (j > nConds)
				j = 0;  // rotate the phases
			termArray[j] = termArray[j].add(curr);
			break;
		}
	}

	public void updateVoltageBases() {

		LoadObj al = LoadImpl.getActiveLoadObj();

		switch (connection) {
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
		Complex curr;
		Complex V;
		double VMag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			VMag = V.abs();

			if (VMag <= VBase95) {
				curr = Yeq95.multiply(V);  // below 95% use an impedance model
			} else if (VMag > VBase105) {
				curr = Yeq105.multiply(V);  // above 105% use an impedance model
			} else {
				curr = new Complex(WNominal, varNominal).divide(V).conjugate();  // above 95%, constant PQ
			}

			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	private void doConstantZLoad() {
		Complex curr;

		// assume Yeq is kept up to date
		calcYPrimContribution(getInjCurrent());  // init injCurrent Array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			curr = Yeq.multiply(VTerminal[i]);
			stickCurrInTerminalArray(getITerminal(),  curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Constant P, quadratic Q
	 */
	private void doMotorTypeLoad() {
		Complex curr;
		Complex V;
		double VMag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			VMag = V.abs();
			if (VMag <= VBase95) {
				curr = Yeq95.multiply(V);  // below 95% use an impedance model
			} else if (VMag > VBase105) {
				curr = Yeq105.multiply(V);  // above 105% use an impedance model
			} else {
				curr = new Complex(WNominal, 0.0).divide(V).conjugate();  // above 95%, constant P
				curr = curr.add(new Complex(0.0, Yeq.getImaginary()).multiply(V));  // add in Q component of current
			}
			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Constant current load.
	 */
	private void doConstantILoad() {
		Complex V;
		Complex curr;

		// computes the current assuming the voltage mag is VBase
		// just uses the phase angle off the voltage

		/*
		 * Injection = [s/v]* = [ (P+jQ)/(Vbase * V/|V|)]*
		 */

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			V    = VTerminal[i];

			curr = new Complex(WNominal, varNominal).divide( V.divide( V.abs() ).multiply(VBase) ).conjugate();

			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	private void doZIPVModel() {
		int i;
		Complex curr, currZ, currI, currP, V;
		double VMag, vx, evx, yv;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			VMag = V.abs();

			if (VMag <= VBase95) {
				curr = Yeq95.multiply(V);
			} else if (VMag > VBase105) {
				curr = Yeq105.multiply(V);
			} else {
				currZ = new Complex(Yeq.getReal() * ZIPV[0], Yeq.getImaginary() * ZIPV[3]).multiply( VTerminal[i] );
				currI = new Complex(WNominal * ZIPV[1], varNominal * ZIPV[4]).divide( V.divide(V.abs()).multiply(VBase) ).conjugate();
				currP = new Complex(WNominal * ZIPV[2], varNominal * ZIPV[5]).divide(V).conjugate();
				curr  = currZ.add(currI.add(currP));
			}

			// low-voltage drop-out
			if (ZIPV[6] > 0.0) {
				vx = 500.0 * (VMag / VBase - ZIPV[6]);
				evx = Math.exp(2 * vx);
				yv = 0.5 * (1 + (evx - 1) / (evx + 1));
				curr = curr.multiply(yv);
			}

			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Linear P, quadratic Q.
	 */
	private void doCVRModel() {
		Complex V;
		Complex curr;
		Complex CVar;  // var current
		double wattFactor;
		double varFactor;
		double VMag;
		double VRatio;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		try {
			for (int i = 0; i < nPhases; i++) {
				V    = VTerminal[i];
				VMag = V.abs();
				VRatio = VMag / VBase;  // VBase is l-n for wye and l-l for delta
				// linear factor adjustment does not converge for some reason while power adjust does easily
				// WattFactor = (1.0 + FCVRwattFactor*(Vmag/VBase - 1.0));
				if (CVRwattFactor != 1.0) {
					wattFactor = Math.pow(VRatio, CVRwattFactor);
				} else {
					wattFactor = VRatio;  // old value (in error): 1.0;
				}
				if (wattFactor > 0.0) {
					curr = new Complex(WNominal * wattFactor, 0.0).divide(V).conjugate();
				} else {
					curr = Complex.ZERO;  // P component of current
				}

				/* Compute Q component of current */
				if (CVRvarFactor == 2.0) {  /* Check for easy, quick ones first */
					CVar = new Complex(0.0, Yeq.getImaginary()).multiply(V);  // 2 is same as constant impedance
				} else if (CVRvarFactor == 3.0) {
					varFactor = Math.pow(VRatio, 3);
					/*writeDLLDebugFile(String.format("%s, V=%.6g +j %.6g", getName(), V.getReal(), V.getImaginary()));*/
					CVar      = new Complex(0.0, varNominal * varFactor).divide(V).conjugate();
				} else {
					/* Other VAr factor code here if not squared or cubed */
					varFactor = Math.pow(VRatio, CVRvarFactor);
					CVar      = new Complex(0.0, varNominal * varFactor).divide(V).conjugate();
				}
				curr = curr.add(CVar);  // add in Q component of current
				/*writeDLLDebugFile(String.format("%s, %d, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g ", getName(), i, Vmag, VRatio, WNominal, WattFactor, varNominal, VarFactor, Curr.abs(), V.multiply(Curr.conjugate()).getReal()));*/
				stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
				setITerminalUpdated(true);
				stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
			}
		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg(String.format("Error in Load.%s: %s ", getName(), e.getMessage()), 5871);
		}
	}

	/**
	 * Constant P, Fixed Q. Q is always kvarBase.
	 */
	private void doFixedQ() {
		Complex curr, V;
		double VMag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			VMag = V.abs();
			if (VMag <= VBase95) {
				curr = new Complex(Yeq95.getReal(), YQFixed).multiply(V);  // below 95% use an impedance model
			} else if (VMag > VBase105) {
				curr = new Complex(Yeq105.getReal(), YQFixed).multiply(V);  // above 105% use an impedance model
			} else {
				curr = new Complex(WNominal, varBase).divide(V).conjugate();
			}
			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Constant P, Fixed Q. Q is always a fixed Z derived from kvarBase.
	 */
	private void doFixedQZ() {
		Complex curr, V;
		double VMag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			V = VTerminal[i];
			VMag = V.abs();
			if (VMag <= VBase95) {
				curr = new Complex(Yeq95.getReal(), YQFixed).multiply(V);  // below 95% use an impedance model
			} else if (VMag >  VBase105) {
				curr = new Complex(Yeq105.getReal(), YQFixed).multiply(V);
			} else {
				curr = new Complex(WNominal, 0.0).divide(V).conjugate();  // P component of current
				curr = curr.add(new Complex(0.0, YQFixed).multiply(V));   // add in Q component of current
			}

			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute injection current only when in harmonics mode.
	 * Assumes spectrum is an ideal current source based on the fundamental current and spectrum.
	 */
	private void doHarmonicMode() {
		Complex curr, mult;
		double loadHarmonic;

		/* Don't calc VTerminal here because it could be undefined */
		zeroInjCurrent();
		zeroITerminal();

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		loadHarmonic = sol.getFrequency() / loadFundamental;  // loadFundamental = frequency of solution when harmonic mode entered
		mult = getSpectrumObj().getMult(loadHarmonic);
		for (int i = 0; i < nPhases; i++) {
			curr = mult.multiply(harmMag[i]);  // get base harmonic magnitude
			curr = Utilities.rotatePhasorDeg(curr, loadHarmonic, harmAng[i]);  // time shift by fundamental
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
		}
	}

	private boolean allTerminalsClosed() {
		for (int i = 0; i < nTerms; i++)
			for (int j = 0; j < nConds; j++)
				if (!terminals[i].getConductors()[j].isClosed())
					return false;
		return true;
	}

	private void calcVTerminalPhase() {
		int j;
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		/* Establish phase voltages and stick in VTemp */
		switch (connection) {
		case 0:
			for (int i = 0; i < nPhases; i++)
				VTerminal[i] = sol.vDiff(nodeRef[i], nodeRef[nConds]);
			break;
		case 1:
			for (int i = 0; i < nPhases; i++) {
				j = i + 1;  // TODO Check zero based indexing
				if (j > nConds)
					j = 0;
				VTerminal[i] = sol.vDiff(nodeRef[i], nodeRef[j]);
			}
			break;
		}

		loadSolutionCount = sol.getSolutionCount();
	}

	/**
	 * Calculates total load current and adds it properly into the injCurrent array.
	 */
	private void calcLoadModelContribution() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		setITerminalUpdated(false);

		if (sol.isDynamicModel()) {
			//doDynamicMode();  // TODO Implement dynamic mode
		} else if (sol.isHarmonicModel() && (sol.getFrequency() != ckt.getFundamental())) {
			doHarmonicMode();
		} else {
			// compute total load currents and add into injCurrent array
			switch (loadModel) {
			case 1:
				doConstantPQLoad();  // normal load-flow type load
				break;
			case 2:
				doConstantZLoad();
				break;
			case 3:
				doMotorTypeLoad();  // constant P, quadratic Q;
				break;
			case 4:
				doCVRModel();       // mixed motor/resistive load with CVR factors
				break;
			case 5:
				doConstantILoad();
				break;
			case 6:
				doFixedQ();         // fixed Q
				break;
			case 7:
				doFixedQZ();        // fixed, constant Z Q
				break;
			case 8:
				doZIPVModel();
				break;
			default:
				doConstantZLoad();  // for now, until we implement the other models
				break;
			}
		}
	}

	/**
	 * Fill injCurrent array with the current values to use for injections.
	 */
	private void calcInjCurrentArray() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		// if a terminal is open, then standard load models don't apply, so check it out first
		if (allTerminalsClosed()) {

			// now get injection currents
			calcLoadModelContribution();

		} else {

			// FIXME: THIS MAY NOT WORK !!! WATCH FOR BAD RESULTS

			// some terminals not closed use admittance model for injection
			if (openLoadSolutionCount != ckt.getSolution().getSolutionCount()) {

				// rebuild the YPrimOpenCond if a new solution because values may have changed

				// only reallocate when necessary
				if (YPrimOpenCond == null) {
					YPrimOpenCond = new CMatrixImpl(YOrder);
				} else {
					YPrimOpenCond.clear();
				}
				if (YPrimOpenCond.getNOrder() != YOrder) {
					YPrimOpenCond = null;
					YPrimOpenCond = new CMatrixImpl(YOrder);
				}
				calcYPrimMatrix(YPrimOpenCond);

				/* Now account for the open conductors */
				/* For any conductor that is open, zero out row and column */

				int k = 0;
				for (int i = 0; i < nTerms; i++) {
					for (int j = 0; j < nConds; j++) {
						if (!terminals[i].getConductors()[j].isClosed()) {
							YPrimOpenCond.zeroRow(j + k);
							YPrimOpenCond.zeroCol(j + k);
							YPrimOpenCond.setElement(j + k, j + k, new Complex(1.0e-12, 0.0));  // in case node gets isolated
						}
					}
					k = k + nConds;
				}

				openLoadSolutionCount = ckt.getSolution().getSolutionCount();

			}

			computeVTerminal();
			YPrimOpenCond.MVMult(complexBuffer, VTerminal);
			for (int i = 0; i < YOrder; i++)
				complexBuffer[i] = complexBuffer[i].negate();
		}
	}

	/**
	 * Always return total terminal currents in the curr array
	 */
	protected void getTerminalCurrents(Complex[] Curr) {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		if (ITerminalSolutionCount != sol.getSolutionCount())  // recalc the contribution
			calcLoadModelContribution();  // adds totals in ITerminal as a side effect

		super.getTerminalCurrents(Curr);
	}

	/**
	 * Get the injection currents and add them directly into the currents array.
	 */
	public int injCurrents() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		int result = 0;
		if (isEnabled()) {
			if (sol.loadsNeedUpdating())
				setNominalLoad();  // set the nominal kW, etc. for the type of solution being done
			calcInjCurrentArray();
			result = super.injCurrents();  // add into global currents array
		}

		return result;
	}

	/**
	 * Gets the injection currents for the last solution performed.
	 * Do not call setNominalLoad, as that may change the load values.
	 */
	public void getInjCurrents(Complex[] curr) {
		try {
			if (isEnabled()) {
				calcInjCurrentArray();
				// copy into buffer array
				for (int i = 0; i < YOrder; i++)
					curr[i] = getInjCurrent()[i];
			} else {
				for (int i = 0; i < curr.length; i++)
					curr[i] = Complex.ZERO;
			}
		} catch (Exception e) {
			DSSGlobals.getInstance().doErrorMsg("Load object: \"" + getName() + "\" in getInjCurrents function.",
					e.getMessage(), "Current buffer may not big enough.", 588);
		}
	}

	/**
	 * Line overload takes precedence.
	 * Assumes that low voltage is due to overloaded line.
	 * If voltage is below emergency minimum, it is counted as unserved.
	 */
	public boolean getUnserved() {
		double Vpu, VMag;
		double normMinCriteria, emergMinCriteria;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (UE_Factor > 0.0)
			return true;

		// else check voltages
		if (loadSolutionCount != ckt.getSolution().getSolutionCount())
			calcVTerminalPhase();

		// get the lowest of the phase voltages
		Vpu = VBase;
		for (int i = 0; i < nPhases; i++) {
			VMag = VTerminal[i].abs();
			if (VMag < Vpu)
				Vpu = VMag;
		}
		Vpu = Vpu / VBase;

		if (VMinNormal != 0.0) {
			normMinCriteria = VMinNormal;
		} else {
			normMinCriteria = ckt.getNormalMinVolts();
		}

		if (VMinEmerg != 0.0) {
			emergMinCriteria = VMinEmerg;
		} else {
			emergMinCriteria = ckt.getEmergMinVolts();
		}

		if (Vpu < emergMinCriteria) {
			//UE_Factor = 1.0;
			// 9-19-00 RCD  let UE_Factor start small and grow linearly at same slope
			// as EEN_Factor
			UE_Factor = (emergMinCriteria - Vpu) / (normMinCriteria - emergMinCriteria);
			return true;
		}

		return false;
	}

	/**
	 * Line overload takes precedence.
	 * Assumes that low voltage is due to overloaded line.
	 * If voltage is below normal minimum, it is counted as unserved in proportion
	 * to the difference between the normal and emergency voltage limits.
	 */
	public boolean getExceedsNormal() {
		double Vpu, VMag;
		double normMinCriteria, emergMinCriteria;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (EEN_Factor > 0.0)
			return true;

		// check line overload

		if (loadSolutionCount != ckt.getSolution().getSolutionCount())
			calcVTerminalPhase();

		// get the lowest of the phase voltages
		Vpu = VBase;
		for (int i = 0; i < nPhases; i++) {
			VMag = VTerminal[i].abs();
			if (VMag < Vpu)
				Vpu = VMag;
		}
		Vpu = Vpu / VBase;

		if (VMinNormal != 0.0) {
			normMinCriteria = VMinNormal;
		} else {
			normMinCriteria = ckt.getNormalMinVolts();
		}

		if (VMinEmerg != 0.0) {
			emergMinCriteria = VMinEmerg;
		} else {
			emergMinCriteria = ckt.getEmergMinVolts();
		}

		if (Vpu < normMinCriteria) {
			EEN_Factor = (normMinCriteria - Vpu) / (normMinCriteria - emergMinCriteria);
			// 9-19-00 RCD  Let EEN factor grow linearly at same slope
			// IF EEN_Factor > 1.0 THEN EEN_Factor = 1.0;
			return true;
		}

		return false;
	}

	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			switch (i) {
			case 3:
				f.println("~ " + getParentClass().getPropertyName()[i] + "=" + kWBase);
				break;
			case 4:
				f.println("~ " + getParentClass().getPropertyName()[i] + "=" + PFNominal);
				break;
			case 11:
				f.println("~ " + getParentClass().getPropertyName()[i] + "=" + kVArBase);
				break;
			case 21:
				f.println("~ " + getParentClass().getPropertyName()[i] + "=" + kVAAllocationFactor);
				break;
			case 22:
				f.println("~ " + getParentClass().getPropertyName()[i] + "=" + kVABase);
				break;
			case 32:
				f.print("~ " + getParentClass().getPropertyName()[i] + "=");
				for (int j = 0; j < nZIPV; j++)
					f.print(ZIPV[j] + " ");
				f.println("\"");
				break;
			default:
				f.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));
				break;
			}
		}
	}

	/**
	 * Allocate load from connected kVA or kWh billing.
	 */
	public void setKVAAllocationFactor(double value) {
		setKVAAllocationFactor(value);
		allocationFactor = value;
		loadSpecType = 3;
		computeAllocatedLoad();
		hasBeenAllocated = true;
	}

	/**
	 * AllocationFactor adjusts either connected kVA allocation factor or kWh CFactor.
	 *
	 * This procedure is used by the EnergyMeter allocateLoad function to adjust load allocation factors.
	 */
	public void setAllocationFactor(double value) {
		allocationFactor = value;
		switch (loadSpecType) {
		case 3:
			setKVAAllocationFactor(value);
			break;
		case 4:
			setCFactor(value);
			break;
		}
		computeAllocatedLoad();  // update kWBase
		hasBeenAllocated = true;
	}

	public void setCFactor(double value) {
		CFactor = value;
		allocationFactor = value;
		loadSpecType = 4;
		computeAllocatedLoad();
		hasBeenAllocated = true;
	}

	public void setConnectedKVA(double value) {
		connectedkVA = value;
		loadSpecType = 3;
		allocationFactor = kVAAllocationFactor;
		computeAllocatedLoad();
	}

	public void setKWh(double value) {
		kWh = value;
		loadSpecType = 4;
		allocationFactor = CFactor;
		computeAllocatedLoad();
	}

	public void setKWhDays(double value) {
		kWhDays = value;
		loadSpecType = 4;
		computeAllocatedLoad();
	}

	/**
	 * Fixed loads defined by kW, kVAr or kW, PF are ignored.
	 */
	private void computeAllocatedLoad() {
		switch (loadSpecType) {
		case 3:
			if (connectedkVA > 0.0) {
				kWBase = connectedkVA * kVAAllocationFactor * Math.abs(PFNominal);
				kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
				if (PFNominal < 0.0)
					kVArBase = -kVArBase;
			}
			break;
		case 4:
			avgKW = kWh / (kWhDays * 24);
			kWBase = avgKW * CFactor;
			kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			if (PFNominal < 0.0)
				kVArBase = -kVArBase;
			break;
		}
	}

	/**
	 * Get the present terminal currents and store for harmonics base reference.
	 */
	public void initHarmonics() {
		Complex[] currents;

		/* Make sure there's enough memory */
		harmMag = (double[]) Utilities.resizeArray(harmMag, nPhases);
		harmAng = (double[]) Utilities.resizeArray(harmAng, nPhases);
		currents = new Complex[YOrder];  // to hold currents

		loadFundamental = DSSGlobals.getInstance().getActiveCircuit().getSolution().getFrequency();

		getCurrents(currents);
		/* Store the currents at fundamental frequency.
		 * The spectrum is applied to these.
		 */
		for (int i = 0; i < nPhases; i++) {
			harmMag[i] = currents[i].abs();
			harmAng[i] = currents[i].degArg();
		}

		currents = null;
	}

	public void initPropertyValues(int arrayOffset) {

		propertyValue[0]  = "3";        // "phases";
		propertyValue[1]  = getBus(1);  // "bus1";
		propertyValue[2]  = "12.47";
		propertyValue[3]  = "10";
		propertyValue[4]  = ".88";
		propertyValue[5]  = "1";
		propertyValue[6]  = "";
		propertyValue[7]  = "";
		propertyValue[8]  = "";
		propertyValue[9]  = "";
		propertyValue[10] = "wye";
		propertyValue[11] = "5";
		propertyValue[12] = "-1"; // "rneut"; // if entered -, assume open or user defined
		propertyValue[13] = "0";  // "xneut";
		propertyValue[14] = "variable"; //"status";  // fixed or variable
		propertyValue[15] = "1";  // class
		propertyValue[16] = "0.95";
		propertyValue[17] = "1.05";
		propertyValue[18] = "0.0";
		propertyValue[19] = "0.0";
		propertyValue[20] = "0.0";
		propertyValue[21] = "0.5";  // allocation factor
		propertyValue[22] = "11.3636";
		propertyValue[23] = "50";
		propertyValue[24] = "10";
		propertyValue[25] = "1";    // CVR watt factor
		propertyValue[26] = "2";    // CVR var factor
		propertyValue[27] = "0";    // kwh bulling
		propertyValue[28] = "30";   // kwhdays
		propertyValue[29] = "4";    // Cfactor
		propertyValue[30] = "";     // CVRCurve
		propertyValue[31] = "1";    // NumCust
		propertyValue[32] = "";     // ZIPV coefficient array

		super.initPropertyValues(Load.NumPropsThisClass);
	}

	/**
	 * Make a positive sequence model.
	 */
	public void makePosSequence() {
		double V;

		String s = "phases=1 conn=wye";

		// make sure voltage is line-neutral
		if ((nPhases > 1) || (connection != 0)) {
			V = kVLoadBase / DSSGlobals.SQRT3;
		} else {
			V = kVLoadBase;
		}

		s = s + String.format(" kV=%-.5g", V);

		// divide the load by no. phases
		if (nPhases > 1) {
			s = s + String.format(" kW=%-.5g  kvar=%-.5g", kWBase / nPhases, kVArBase / nPhases);
			if (connectedkVA > 0.0)
				s = s + String.format(" xfkVA=%-.5g  ", connectedkVA / nPhases);
		}

		Parser.getInstance().setCmdString(s);
		edit();

		super.makePosSequence();
	}

	public String getPropertyValue(int index) {
		switch (index) {
		case 1:
			return getBus(1);
		case 2:
			return String.format("%-g", kVLoadBase);
		case 3:
			return String.format("%-g", kWBase);
		case 4:
			return String.format("%-.3g", PFNominal);
		case 6:
			return yearlyShape;
		case 7:
			return dailyShape;
		case 8:
			return dutyShape;
		case 11:
			return String.format("%-.3g", kVArBase);
		case 21:
			return String.format("%-.3g", kVAAllocationFactor);
		case 22:
			return String.format("%-g", kVABase);
		case 29:
			return String.format("%-.3g", CFactor);
		case 32:
			String result = "";
			for (int i = 0; i < nZIPV; i++)
				result = result + String.format(" %-g", ZIPV[i]);
			return result;
		default:
			return super.getPropertyValue(index);
		}
	}

	public double getKVAAllocationFactor() {
		return kVAAllocationFactor;
	}

	public double getConnectedKVA() {
		return connectedkVA;
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
		return allocationFactor;
	}

	public int getConnection() {
		return connection;
	}

	public void setConnection(int conn) {
		connection = conn;
	}

	public String getDailyShape() {
		return dailyShape;
	}

	public void setDailyShape(String shape) {
		dailyShape = shape;
	}

	public LoadShapeObj getDailyShapeObj() {
		return dailyShapeObj;
	}

	public void setDailyShapeObj(LoadShapeObj shapeObj) {
		dailyShapeObj = shapeObj;
	}

	public String getDutyShape() {
		return dutyShape;
	}

	public void setDutyShape(String shape) {
		dutyShape = shape;
	}

	public LoadShapeObj getDutyShapeObj() {
		return dutyShapeObj;
	}

	public void setDutyShapeObj(LoadShapeObj shapeObj) {
		dutyShapeObj = shapeObj;
	}

	public double getEEN_Factor() {
		return EEN_Factor;
	}

	public void setEEN_Factor(double factor) {
		EEN_Factor = factor;
	}

	public String getGrowthShape() {
		return growthShape;
	}

	public void setGrowthShape(String shape) {
		growthShape = shape;
	}

	public GrowthShapeObj getGrowthShapeObj() {
		return growthShapeObj;
	}

	public void setGrowthShapeObj(GrowthShapeObj shapeObj) {
		growthShapeObj = shapeObj;
	}

	public Boolean getHasBeenAllocated() {
		return hasBeenAllocated;
	}

	public void setHasBeenAllocated(Boolean allocated) {
		hasBeenAllocated = allocated;
	}

	public double getKWBase() {
		return kWBase;
	}

	public void setKWBase(double base) {
		this.kWBase = base;
	}

	public double getKVABase() {
		return kVABase;
	}

	public void setKVABase(double base) {
		this.kVABase = base;
	}

	public double getKVArBase() {
		return kVArBase;
	}

	public void setKVArBase(double base) {
		this.kVArBase = base;
	}

	public double getKVLoadBase() {
		return kVLoadBase;
	}

	public void setKVLoadBase(double base) {
		this.kVLoadBase = base;
	}

	public int getLoadClass() {
		return loadClass;
	}

	public void setLoadClass(int cls) {
		loadClass = cls;
	}

	public int getNumCustomers() {
		return numCustomers;
	}

	public void setNumCustomers(int num) {
		numCustomers = num;
	}

	public int getLoadSpecType() {
		return loadSpecType;
	}

	public void setLoadSpecType(int specType) {
		loadSpecType = specType;
	}

	public double getPFNominal() {
		return PFNominal;
	}

	public void setPFNominal(double value) {
		PFNominal = value;
	}

	public double getRNeut() {
		return RNeut;
	}

	public void setRNeut(double rneut) {
		RNeut = rneut;
	}

	public double getUE_Factor() {
		return UE_Factor;
	}

	public void setUE_Factor(double factor) {
		UE_Factor = factor;
	}

	public double getXNeut() {
		return XNeut;
	}

	public void setXNeut(double xneut) {
		XNeut = xneut;
	}

	public String getYearlyShape() {
		return yearlyShape;
	}

	public void setYearlyShape(String shape) {
		yearlyShape = shape;
	}

	public LoadShapeObj getYearlyShapeObj() {
		return yearlyShapeObj;
	}

	public void setYearlyShapeObj(LoadShapeObj shapeObj) {
		yearlyShapeObj = shapeObj;
	}

	public String getCVRShape() {
		return CVRshape;
	}

	public void setCVRShape(String shape) {
		CVRshape = shape;
	}

	public LoadShapeObj getCVRShapeObj() {
		return CVRShapeObj;
	}

	public void setCVRShapeObj(LoadShapeObj shapeObj) {
		CVRShapeObj = shapeObj;
	}

	public int getLoadModel() {
		return loadModel;
	}

	public void setLoadModel(int model) {
		loadModel = model;
	}

	public double getPUMean() {
		return puMean;
	}

	public double getPUStdDev() {
		return puStdDev;
	}

	public double getCVRWattFactor() {
		return CVRwattFactor;
	}

	public double getCVRVArFactor() {
		return CVRvarFactor;
	}

	public double getVMaxPU() {
		return VMaxPU;
	}

	public double getVMinEmerg() {
		return VMinEmerg;
	}

	public double getVMinNormal() {
		return VMinNormal;
	}

	public double getVMinPU() {
		return VMinPU;
	}

	public boolean isExemptFromLDCurve() {
		return exemptFromLDCurve;
	}

	public boolean isFixed() {
		return fixed;
	}

	// FIXME Private members in OpenDSS.

	public boolean isPFChanged() {
		return PFChanged;
	}

	public void setPFChanged(boolean changed) {
		PFChanged = changed;
	}

	public double getAvgKW() {
		return avgKW;
	}

	public void setAvgKW(double avg) {
		avgKW = avg;
	}

	public double[] getHarmAng() {
		return harmAng;
	}

	public void setHarmAng(double[] ang) {
		harmAng = ang;
	}

	public double[] getHarmMag() {
		return harmMag;
	}

	public void setHarmMag(double[] mag) {
		harmMag = mag;
	}

	public double getLastGrowthFactor() {
		return lastGrowthFactor;
	}

	public void setLastGrowthFactor(double factor) {
		lastGrowthFactor = factor;
	}

	public int getLastYear() {
		return lastYear;
	}

	public void setLastYear(int year) {
		lastYear = year;
	}

	public double getLoadFundamental() {
		return loadFundamental;
	}

	public void setLoadFundamental(double fundamental) {
		loadFundamental = fundamental;
	}

	public int getLoadSolutionCount() {
		return loadSolutionCount;
	}

	public void setLoadSolutionCount(int count) {
		loadSolutionCount = count;
	}

	public int getOpenLoadSolutionCount() {
		return openLoadSolutionCount;
	}

	public void setOpenLoadSolutionCount(int count) {
		openLoadSolutionCount = count;
	}

	public double getRandomMult() {
		return randomMult;
	}

	public void setRandomMult(double mult) {
		randomMult = mult;
	}

	public Complex getShapeFactor() {
		return shapeFactor;
	}

	public void setShapeFactor(Complex factor) {
		shapeFactor = factor;
	}

	public double getVArBase() {
		return varBase;
	}

	public void setVArBase(double base) {
		varBase = base;
	}

	public double getVArNominal() {
		return varNominal;
	}

	public void setVArNominal(double value) {
		varNominal = value;
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

	public void setWNominal(double value) {
		WNominal = value;
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

	public void setYneut(Complex value) {
		Yneut = value;
	}

	public CMatrix getYPrimOpenCond() {
		return YPrimOpenCond;
	}

	public void setYPrimOpenCond(CMatrix value) {
		YPrimOpenCond = value;
	}

	public double getYQFixed() {
		return YQFixed;
	}

	public void setYQFixed(double value) {
		YQFixed = value;
	}

	public void setPUMean(double value) {
		this.puMean = value;
	}

	public void setPUStdDev(double value) {
		this.puStdDev = value;
	}

	public void setCVRWattFactor(double factor) {
		CVRwattFactor = factor;
	}

	public void setCVRVArFactor(double factor) {
		CVRvarFactor = factor;
	}

	public void setVMaxPU(double vmaxpu) {
		VMaxPU = vmaxpu;
	}

	public void setVMinEmerg(double vminEmerg) {
		VMinEmerg = vminEmerg;
	}

	public void setVMinNormal(double vminNormal) {
		VMinNormal = vminNormal;
	}

	public void setVMinPU(double vminpu) {
		VMinPU = vminpu;
	}

	public void setExemptFromLDCurve(boolean exempt) {
		exemptFromLDCurve = exempt;
	}

	public void setFixed(boolean value) {
		fixed = value;
	}

	public boolean shapeIsActual() {
		return shapeIsActual;
	}

	public void setShapeIsActual(boolean isActual) {
		shapeIsActual = isActual;
	}

	public double[] getZIPV() {
		return ZIPV;
	}

	public void setZIPV(double[] zipv) {
		ZIPV = zipv;
	}

	public void setNZIPV(int nzipv) {
		nZIPV = nzipv;
	}

	public int getNZIPV() {
		return nZIPV;
	}

}
