package com.ncond.dss.conversion;

import java.io.OutputStream;
import java.io.PrintWriter;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.common.types.SolutionLoadModel;
import com.ncond.dss.common.types.Randomization;
import com.ncond.dss.general.GrowthShapeObj;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.general.SpectrumObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.ComplexUtil;
import com.ncond.dss.shared.MathUtil;

@Getter @Setter
public class LoadObj extends PCElement {

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
	protected Connection connection;
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
	protected LoadSpecType loadSpecType;
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
	protected String CVRShape;
	protected LoadShapeObj CVRShapeObj;

	protected LoadModel loadModel;

	public LoadObj(DSSClass parClass, String sourceName) {
		super(parClass);

		setName(sourceName.toLowerCase());
		objType = parClass.getClassType();

		setNumPhases(3);
		nConds = 4;  // defaults to wye so it has a 4th conductor
		YOrder = 0;  // to trigger an initial allocation
		setNumTerms(1);  // forces allocations

		kWBase = 10.0;
		kVArBase = 5.0;
		PFNominal = 0.88;
		kVABase = kWBase / PFNominal;
		loadSpecType = LoadSpecType.KW_PF;
		RNeut = -1.0;  // signify neutral is open
		XNeut = 0.0;

		yearlyShape = "";
		yearlyShapeObj = null;  // if yearlyShapeObj = null then the load alway stays nominal * global multipliers
		dailyShape = "";
		dailyShapeObj = null;  // if dailyShapeObj = null then the load alway stays nominal * global multipliers
		dutyShape = "";
		dutyShapeObj = null;  // if dutyShapeObj = null then the load alway stays nominal * global multipliers
		growthShape = "";
		growthShapeObj = null;  // if growthShapeObj = null then the load alway stays nominal * global multipliers
		CVRShape = "";
		CVRShapeObj = null;
		connection = Connection.WYE;
		loadModel = LoadModel.PQ;  // changed from 2 (easiest to solve)
		loadClass = 1;
		numCustomers = 1;
		lastYear = 0;
		CVRwattFactor = 1.0;
		CVRvarFactor = 2.0;

		lastGrowthFactor = 1.0;
		kVAAllocationFactor = 0.5;
		allocationFactor = kVAAllocationFactor;
		hasBeenAllocated = false;
		PFChanged = false;
		shapeIsActual = false;

		loadSolutionCount = -1;  // for keeping track of the present solution in injcurrent calcs
		openLoadSolutionCount = -1;
		YPrimOpenCond = null;

		connectedkVA = 0.0;  // loadSpecType=3
		kWh = 0.0;    // loadSpecType=4
		CFactor = 4.0;
		kWhDays = 30.0;
		VMinNormal = 0.0;    // indicates for program to use circuit quantities
		VMinEmerg = 0.0;
		kVLoadBase = 12.47;
		VBase = 7200.0;
		VMinPU = 0.95;
		VMaxPU = 1.05;
		VBase95 = VMinPU * VBase;
		VBase105 = VMaxPU * VBase;
		YOrder = nTerms * nConds;
		randomMult = 1.0 ;
		fixed = false;
		exemptFromLDCurve = false;

		puMean = 0.5;
		puStdDev = 0.1;
		UE_Factor = 0.0;
		EEN_Factor = 0.0;
		spectrum = "defaultload";  // override base class definition
		harmMag = null;
		harmAng = null;
		ZIPV = null;
		setZIPVSize(0);

		initPropertyValues(0);

		recalcElementData();
	}

	protected void setZIPVSize(int n) {
		nZIPV = n;
		ZIPV = Util.resizeArray(ZIPV, nZIPV);
	}

	public void randomize(Randomization opt) {
		switch (opt) {
		case NONE:
			randomMult = 1.0;
			break;
		case GAUSSIAN:
			if (yearlyShapeObj != null) {
				randomMult = MathUtil.gauss(yearlyShapeObj.getMean(), yearlyShapeObj.getStdDev());
			} else {
				randomMult = MathUtil.gauss(puMean, puStdDev);
			}
			break;
		case UNIFORM:
			randomMult = Math.random();  // number between 0 and 1.0
			break;
		case LOGNORMAL:
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
			shapeFactor = dailyShapeObj.getMult(hr);
			shapeIsActual = dailyShapeObj.isUseActual();
		} else {
			shapeFactor = CDOUBLEONE;  // default to no daily variation
		}
	}

	private void calcDutyMult(double hr) {
		if (dutyShapeObj != null) {
			shapeFactor = dutyShapeObj.getMult(hr);
			shapeIsActual = dutyShapeObj.isUseActual();
		} else {
			calcDailyMult(hr);  // default to daily mult if no duty curve specified
		}
	}

	private void calcYearlyMult(double hr) {
		/* Yearly curve is assumed to be hourly only */
		if (yearlyShapeObj != null) {
			shapeFactor = yearlyShapeObj.getMult(hr);
			shapeIsActual = yearlyShapeObj.isUseActual();
		} else {
			shapeFactor = CDOUBLEONE;  // defaults to no variation
		}
	}

	private void calcCVRMult(double hr) {
		Complex CVRFactor;

		/* CVR curve is assumed to be used in a yearly simulation */
		if (CVRShapeObj != null) {
			CVRFactor = CVRShapeObj.getMult(hr);  /* Complex */
			CVRwattFactor = CVRFactor.getReal();
			CVRvarFactor = CVRFactor.getImaginary();
		} else {
			/* CVRWattFactor, etc. remain unchanged */
		}
	}

	private double growthFactor(int year) {
		if (year == 0) {
			lastGrowthFactor = 1.0;  // default all to 1 in year 0 ; use base values
		} else {
			if (growthShapeObj == null) {
				lastGrowthFactor = DSS.activeCircuit.getDefaultGrowthFactor();
			} else if (year != lastYear) {  // search growth curve
				lastGrowthFactor = growthShapeObj.getMult(year);
			}
		}
		return lastGrowthFactor;  // for now
	}

	public void setKW_KVAr(double PkW, double QkVAr) {
		kWBase = PkW;
		kVArBase = QkVAr;
		loadSpecType = LoadSpecType.KW_KVAR;
	}

	public void setNominalLoad() {
		double factor;

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		shapeFactor = CDOUBLEONE;
		shapeIsActual = false;

		if (fixed) {
			factor = growthFactor(sol.getYear());  // for fixed loads, consider only growth factor
		} else {
			switch (sol.getMode()) {
			case SNAPSHOT:
			case HARMONICMODE:
				if (exemptFromLDCurve) {
					factor = growthFactor(sol.getYear());
				} else {
					factor = ckt.getLoadMultiplier() * growthFactor(sol.getYear());
				}
				break;
			case DAILYMODE:
				factor = growthFactor(sol.getYear());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				calcDailyMult(sol.getDblHour());
				break;
			case YEARLYMODE:
				factor = ckt.getLoadMultiplier() * growthFactor(sol.getYear());
				calcYearlyMult(sol.getDblHour());
				if (loadModel == LoadModel.CVR)
					calcCVRMult(sol.getDblHour());
				break;
			case DUTYCYCLE:
				factor = growthFactor(sol.getYear());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				calcDutyMult(sol.getDblHour());
				break;
			case GENERALTIME:
			case DYNAMICMODE:
				factor = growthFactor(sol.getYear());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				// this mode allows use of one class of load shape
				switch (ckt.getActiveLoadShapeClass()) {
				case DAILY:
					calcDailyMult(sol.getDblHour());
					break;
				case YEARLY:
					calcYearlyMult(sol.getDblHour());
					break;
				case DUTY:
					calcDutyMult(sol.getDblHour());
					break;
				default:
					shapeFactor = Complex.ONE;  // TODO: default to 1 + j1 if not known
					break;
				}
				break;
			case MONTECARLO1:
				randomize(sol.getRandomType());
				factor = randomMult * growthFactor(sol.getYear());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				break;
			case MONTECARLO2:
			case MONTECARLO3:
			case LOADDURATION1:
			case LOADDURATION2:
				factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				if (!exemptFromLDCurve)
					factor = factor * ckt.getLoadMultiplier();
				break;
			case PEAKDAY:
				factor = growthFactor(sol.getYear());
				calcDailyMult(sol.getDblHour());
				break;
			case AUTOADDFLAG:
				factor = growthFactor(sol.getYear());  // loadMult = 1.0 by default
				break;
			default:
				factor = growthFactor(sol.getYear());  // defaults to Base kW * growth
				break;
			}
		}

		if (shapeIsActual) {
			WNominal = 1000.0 * shapeFactor.getReal() / nPhases;
			varNominal = 1000.0 * shapeFactor.getImaginary() / nPhases;
		} else {
			WNominal = 1000.0 * kWBase * factor * shapeFactor.getReal() / nPhases ;
			varNominal = 1000.0 * kVArBase * factor * shapeFactor.getImaginary() / nPhases;
		}

		Yeq = ComplexUtil.divide(new Complex(WNominal, -varNominal), Math.pow(VBase, 2) );
		if (VMinPU != 0.0) {
			Yeq95 = ComplexUtil.divide(Yeq, Math.pow(VMinPU, 2));  // at 95% voltage
		} else {
			Yeq95 = Complex.ZERO;
		}

		if (VMaxPU != 0.0) {
			Yeq105 = ComplexUtil.divide(Yeq, Math.pow(VMaxPU, 2));  // at 105% voltage
		} else {
			Yeq105 = Yeq;
		}
	}

	@Override
	public void recalcElementData() {
		VBase95 = VMinPU * VBase;
		VBase105 = VMaxPU * VBase;

		/* Set kW and kVAr from root values of kVA and PF */

		switch (loadSpecType) {
		case KW_PF:
			kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			if (PFNominal < 0.0)
				kVArBase = -kVArBase;
			kVABase = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kVArBase, 2));
			break;
		case KW_KVAR:  /* need to set PFNominal */
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
		case KVA_PF:
			kWBase   = kVABase * Math.abs(PFNominal);
			kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			if (PFNominal < 0.0)
				kVArBase = -kVArBase;
			break;
		case XFKVA_ALLOCATIONFACTOR_PF:
		case KWH_KWHDAYS24_CFACTOR_PF:
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
		if (yearlyShape.equalsIgnoreCase("none")) yearlyShape = "";
		if (dailyShape.equalsIgnoreCase("none")) dailyShape = "";
		if (dutyShape.equalsIgnoreCase("none")) dutyShape = "";

		if (yearlyShapeObj == null) {
			if (yearlyShape.length() > 0)
				DSS.doSimpleMsg("Warning: Yearly load shape: \""+ yearlyShape +"\" not found.", 583);
		}
		if (dailyShapeObj == null) {
			if (dailyShape.length() > 0)
				DSS.doSimpleMsg("Warning: Daily load shape: \""+ dailyShape +"\" not found.", 584);
		}
		if (dutyShapeObj == null) {
			if (dutyShape.length() > 0)
				DSS.doSimpleMsg("Warning: Duty load shape: \""+ dutyShape +"\" not found.", 585);
		}
		if (growthShapeObj == null) {
			if (growthShape.length() > 0)
				DSS.doSimpleMsg("Warning: Yearly growth shape: \""+ growthShape +"\" not found.", 586);
		}
		if (CVRShapeObj == null) {
			if (CVRShape.length() > 0)
				DSS.doSimpleMsg("Warning: CVR shape shape: \""+ CVRShape +"\" not found.", 586);
		}

		setSpectrumObj((SpectrumObj) DSS.spectrumClass.find(getSpectrum()));
		if (getSpectrumObj() == null)
			DSS.doSimpleMsg("Error: Spectrum \"" + getSpectrum() + "\" not found.", 587);

		if (RNeut < 0.0) {  // flag for open neutral
			Yneut = new Complex(0.0, 0.0);
		} else if ((RNeut == 0.0) && (XNeut == 0.0)) {  // solidly grounded
			Yneut = new Complex(1.0e6, 0.0);  // 1 microohm resistor
		} else {
			Yneut = ComplexUtil.invert(new Complex(RNeut, XNeut));
		}

		varBase = 1000.0 * kVArBase / nPhases;
		YQFixed = -varBase / Math.pow(VBase, 2);

		setInjCurrent(Util.resizeArray(getInjCurrent(), YOrder));

		setPFChanged(false);
	}

	private void calcYPrimMatrix(CMatrix YMatrix) {
		Complex Y, Yij;
		int i, j;
		double freqMultiplier;

		YPrimFreq = DSS.activeCircuit.getSolution().getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;
		Y = Yeq;
		Y = new Complex(Y.getReal(), Y.getImaginary() / freqMultiplier);  /* Correct reactive part for frequency */
		Yij = Y.negate();

		switch (connection) {
		case WYE:
			for (i = 0; i < nPhases; i++) {
				YMatrix.set(i, i, Y);
				YMatrix.add(nConds-1, nConds - 1, Y);
				YMatrix.setSym(i, nConds-1, Yij);
			}
			YMatrix.add(nConds-1, nConds-1, Yneut);  // neutral

			/* If neutral is floating, make sure there is some small
			 * connection to ground  by increasing the last diagonal slightly.
			 */
			if (RNeut < 0.0) {
				YMatrix.set(nConds-1, nConds - 1,
					YMatrix.get(nConds-1, nConds-1).multiply(1.000001));
			}
			break;
		case DELTA:
			for (i = 0; i < nPhases; i++) {
				j = i + 1;
				if (j >= nConds) j = 0;  // wrap around for closed connections
				YMatrix.add(i, i, Y);
				YMatrix.add(j, j, Y);
				YMatrix.addSym(i, j, Yij);  // get both off-diagonal elements
			}
			break;
		}
	}

	/**
	 * If doing an analysis that requires the load to be modeled as an impedance
	 * then put all in.
	 */
	@Override
	public void calcYPrim() {
		// build only YPrim shunt for a load then copy to YPrim
		// build a dummy Yprim series so that calcV does not fail
		if (isYprimInvalid()) {
			YPrimSeries = new CMatrix(YOrder);
			YPrimShunt = new CMatrix(YOrder);
			YPrim = new CMatrix(YOrder);
		} else {
			YPrimShunt.clear();
			YPrimSeries.clear();
			YPrim.clear();
		}

		if (DSS.activeCircuit.getSolution().getLoadModel() == SolutionLoadModel.POWERFLOW) {
			setNominalLoad();  // same as admittance model
			calcYPrimMatrix(YPrimShunt);
		} else {
			// admittance model wanted
			setNominalLoad();
			calcYPrimMatrix(YPrimShunt);
		}

		// set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
		for (int i = 0; i < YOrder; i++)
			YPrimSeries.set(i, i, YPrimShunt.get(i, i).multiply(1.0e-10));

		YPrim.copyFrom(YPrimShunt);

		// account for open conductors
		super.calcYPrim();
	}

	/**
	 * Put the current into the proper location according to connection.
	 */
	private void putCurrInTerminalArray(Complex[] termArray, Complex curr, int i) {
		switch (connection) {
		case WYE:
			termArray[i] = termArray[i].add(curr.negate());
			termArray[nConds-1] = termArray[nConds-1].add(curr);  // neutral
			break;
		case DELTA:
			termArray[i] = termArray[i].add(curr.negate());
			int j = i + 1;
			if (j >= nConds) j = 0;  // rotate the phases
			termArray[j] = termArray[j].add(curr);
			break;
		}
	}

	public void updateVoltageBases() {
		LoadObj elem = Load.activeLoadObj;

		switch (connection) {
		case DELTA:
			elem.setVBase(kVLoadBase * 1000.0);
			break;
		default:  /* wye*/
			switch (nPhases) {
			case 2:
				elem.setVBase(kVLoadBase * DSS.InvSQRT3x1000);
				break;
			case 3:
				elem.setVBase(kVLoadBase * DSS.InvSQRT3x1000);
				break;
			default:
				elem.setVBase(kVLoadBase * 1000.0);  /* 1-phase or unknown */
				break;
			}
			break;
		}
	}

	private void doConstantPQLoad() {
		int i;
		Complex curr;
		Complex V;
		double Vmag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V = VTerminal[i];
			Vmag = V.abs();

			if (Vmag <= VBase95) {
				curr = Yeq95.multiply(V);  // below 95% use an impedance model
			} else if (Vmag > VBase105) {
				curr = Yeq105.multiply(V);  // above 105% use an impedance model
			} else {
				curr = new Complex(
					WNominal,
					varNominal
				).divide(V).conjugate();  // above 95%, constant PQ
			}

			putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
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
			putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Constant P, quadratic Q
	 */
	private void doMotorTypeLoad() {
		Complex curr;
		Complex V;
		double Vmag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			V = VTerminal[i];
			Vmag = V.abs();
			if (Vmag <= VBase95) {
				curr = Yeq95.multiply(V);  // below 95% use an impedance model
			} else if (Vmag > VBase105) {
				curr = Yeq105.multiply(V);  // above 105% use an impedance model
			} else {
				curr = new Complex(
					WNominal,
					0.0
				).divide(V).conjugate();  // above 95%, constant P

				curr = curr.add(new Complex(
					0.0,
					Yeq.getImaginary()
				).multiply(V));  // add in Q component of current
			}
			putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
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
			V = VTerminal[i];
			curr = new Complex(
				WNominal,
				varNominal
			).divide( ComplexUtil.divide(V, V.abs()).multiply(VBase) ).conjugate();

			putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
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
			V = VTerminal[i];
			VMag = V.abs();

			if (VMag <= VBase95) {
				curr = Yeq95.multiply(V);
			} else if (VMag > VBase105) {
				curr = Yeq105.multiply(V);
			} else {
				currZ = new Complex(
					Yeq.getReal() * ZIPV[0],
					Yeq.getImaginary() * ZIPV[3]
				).multiply(VTerminal[i]);

				currI = new Complex(
					WNominal * ZIPV[1],
					varNominal * ZIPV[4]
				).divide(ComplexUtil.divide(V, V.abs()).multiply(VBase)).conjugate();

				currP = new Complex(
					WNominal * ZIPV[2],
					varNominal * ZIPV[5]
				).divide(V).conjugate();

				curr  = currZ.add(currI.add(currP));
			}

			// low-voltage drop-out
			if (ZIPV[6] > 0.0) {
				vx = 500.0 * (VMag / VBase - ZIPV[6]);
				evx = Math.exp(2 * vx);
				yv = 0.5 * (1 + (evx - 1) / (evx + 1));
				curr = curr.multiply(yv);
			}

			putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
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
		double Vmag;
		double Vratio;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		try {
			for (int i = 0; i < nPhases; i++) {
				V = VTerminal[i];
				Vmag = V.abs();
				Vratio = Vmag / VBase;  // VBase is l-n for wye and l-l for delta
				// linear factor adjustment does not converge for some reason while power adjust does easily
				// WattFactor = (1.0 + FCVRwattFactor*(Vmag/VBase - 1.0));
				if (CVRwattFactor != 1.0) {
					wattFactor = Math.pow(Vratio, CVRwattFactor);
				} else {
					wattFactor = Vratio;  // old value (in error): 1.0;
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
					varFactor = Math.pow(Vratio, 3);
					/*writeDLLDebugFile(String.format("%s, V=%.6g +j %.6g", getName(), V.getReal(), V.getImaginary()));*/
					CVar = new Complex(
						0.0,
						varNominal * varFactor
					).divide(V).conjugate();
				} else {
					/* Other VAr factor code here if not squared or cubed */
					varFactor = Math.pow(Vratio, CVRvarFactor);
					CVar = new Complex(
						0.0,
						varNominal * varFactor
					).divide(V).conjugate();
				}
				curr = curr.add(CVar);  // add in Q component of current
				/*writeDLLDebugFile(String.format("%s, %d, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g ",
				 *getName(), i, Vmag, VRatio, WNominal, WattFactor, varNominal, VarFactor, Curr.abs(), V.multiply(Curr.conjugate()).getReal()));*/
				putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
				setITerminalUpdated(true);
				putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
			}
		} catch (Exception e) {
			DSS.doSimpleMsg(String.format("Error in Load.%s: %s ", getName(), e.getMessage()), 5871);
		}
	}

	/**
	 * Constant P, Fixed Q. Q is always kvarBase.
	 */
	private void doFixedQ() {
		Complex curr, V;
		double Vmag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();

		for (int i = 0; i < nPhases; i++) {
			V = VTerminal[i];
			Vmag = V.abs();
			if (Vmag <= VBase95) {
				curr = new Complex(
					Yeq95.getReal(),
					YQFixed
				).multiply(V);  // below 95% use an impedance model
			} else if (Vmag > VBase105) {
				curr = new Complex(
					Yeq105.getReal(),
					YQFixed
				).multiply(V);  // above 105% use an impedance model
			} else {
				curr = new Complex(
					WNominal,
					varBase
				).divide(V).conjugate();
			}
			putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
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
				curr = new Complex(
					Yeq95.getReal(),
					YQFixed
				).multiply(V);  // below 95% use an impedance model
			} else if (VMag >  VBase105) {
				curr = new Complex(
					Yeq105.getReal(),
					YQFixed
				).multiply(V);
			} else {
				curr = new Complex(
					WNominal,
					0.0
				).divide(V).conjugate();  // P component of current
				curr = curr.add(new Complex(
					0.0,
					YQFixed
				).multiply(V));  // add in Q component of current
			}

			putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute injection current only when in harmonics mode.
	 * Assumes spectrum is an ideal current source based on the fundamental current and spectrum.
	 */
	private void doHarmonicMode() {
		Complex curr, mult;
		double loadHarmonic;

		SolutionObj sol = DSS.activeCircuit.getSolution();

		/* Don't calc VTerminal here because it could be undefined */
		zeroInjCurrent();
		zeroITerminal();

		loadHarmonic = sol.getFrequency() / loadFundamental;  // loadFundamental = frequency of solution when harmonic mode entered
		mult = getSpectrumObj().getMult(loadHarmonic);
		for (int i = 0; i < nPhases; i++) {
			curr = mult.multiply(harmMag[i]);  // get base harmonic magnitude
			curr = Util.rotatePhasorDeg(curr, loadHarmonic, harmAng[i]);  // time shift by fundamental
			putCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
			putCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
		}
	}

	private boolean allTerminalsClosed() {
		for (int i = 0; i < nTerms; i++) {
			for (int j = 0; j < nConds; j++) {
				if (!terminals[i].getConductor(j).isClosed())
					return false;
			}
		}
		return true;
	}

	private void calcVTerminalPhase() {
		int j;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		/* Establish phase voltages and stick in VTemp */
		switch (connection) {
		case WYE:
			for (int i = 0; i < nPhases; i++)
				VTerminal[i] = sol.vDiff(nodeRef[i], nodeRef[nConds - 1]);
			break;
		case DELTA:
			for (int i = 0; i < nPhases; i++) {
				j = i + 1;
				if (j >= nConds) j = 0;
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
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		setITerminalUpdated(false);

		if (sol.isDynamicModel()) {
			//doDynamicMode();  // TODO: Implement dynamic mode
		} else if (sol.isHarmonicModel() && (sol.getFrequency() != ckt.getFundamental())) {
			doHarmonicMode();
		} else {
			// compute total load currents and add into injCurrent array
			switch (loadModel) {
			case PQ:
				doConstantPQLoad();  // normal load-flow type load
				break;
			case Z:
				doConstantZLoad();
				break;
			case MOTOR:
				doMotorTypeLoad();  // constant P, quadratic Q;
				break;
			case CVR:
				doCVRModel();  // mixed motor/resistive load with CVR factors
				break;
			case I:
				doConstantILoad();
				break;
			case FIXEDQ:
				doFixedQ();  // fixed Q
				break;
			case FIXEDQZ:
				doFixedQZ();  // fixed, constant Z Q
				break;
			case ZIPV:
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
		Circuit ckt = DSS.activeCircuit;

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
					YPrimOpenCond = new CMatrix(YOrder);
				} else {
					YPrimOpenCond.clear();
				}

				if (YPrimOpenCond.order() != YOrder) {
					YPrimOpenCond = null;
					YPrimOpenCond = new CMatrix(YOrder);
				}

				calcYPrimMatrix(YPrimOpenCond);

				/* Now account for the open conductors */
				/* For any conductor that is open, zero out row and column */
				int k = 0;
				for (int i = 0; i < nTerms; i++) {
					for (int j = 0; j < nConds; j++) {
						if (!terminals[i].getConductor(j).isClosed()) {
							YPrimOpenCond.zeroRow(j + k);
							YPrimOpenCond.zeroCol(j + k);
							YPrimOpenCond.set(j + k, j + k,
								new Complex(1.0e-12, 0.0));  // in case node gets isolated
						}
					}
					k = k + nConds;
				}
				openLoadSolutionCount = ckt.getSolution().getSolutionCount();
			}
			computeVTerminal();
			YPrimOpenCond.vMult(complexBuffer, VTerminal);

			for (int i = 0; i < YOrder; i++)
				complexBuffer[i] = complexBuffer[i].negate();
		}
	}

	/**
	 * Always return total terminal currents in the curr array
	 */
	@Override
	protected void getTerminalCurrents(Complex[] curr) {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (ITerminalSolutionCount != sol.getSolutionCount())  // recalc the contribution
			calcLoadModelContribution();  // adds totals in ITerminal as a side effect

		super.getTerminalCurrents(curr);
	}

	/**
	 * Get the injection currents and add them directly into the currents array.
	 */
	@Override
	public int injCurrents() {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		int curr = 0;
		if (isEnabled()) {
			if (sol.loadsNeedUpdating())
				setNominalLoad();  // set the nominal kW, etc. for the type of solution being done
			calcInjCurrentArray();
			curr = super.injCurrents();  // add into global currents array
		}

		return curr;
	}

	/**
	 * Gets the injection currents for the last solution performed.
	 * Do not call setNominalLoad, as that may change the load values.
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {
		try {
			if (isEnabled()) {
				calcInjCurrentArray();
				// copy into buffer array
				for (int i = 0; i < YOrder; i++) {
					curr[i] = getInjCurrent(i);
				}
			} else {
				for (int i = 0; i < curr.length; i++) {
					curr[i] = Complex.ZERO;
				}
			}
		} catch (Exception e) {
			DSS.doErrorMsg("Load object: \"" + getName() + "\" in getInjCurrents function.",
					e.getMessage(), "Current buffer may not big enough.", 588);
		}
	}

	/**
	 * Line overload takes precedence.
	 * Assumes that low voltage is due to overloaded line.
	 * If voltage is below emergency minimum, it is counted as unserved.
	 */
	public boolean getUnserved() {
		double Vpu, Vmag;
		double normMinCriteria, emergMinCriteria;

		Circuit ckt = DSS.activeCircuit;

		if (UE_Factor > 0.0) return true;

		// else check voltages
		if (loadSolutionCount != ckt.getSolution().getSolutionCount())
			calcVTerminalPhase();

		// get the lowest of the phase voltages
		Vpu = VBase;
		for (int i = 0; i < nPhases; i++) {
			Vmag = VTerminal[i].abs();
			if (Vmag < Vpu) Vpu = Vmag;
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
			// let UE_Factor start small and grow linearly at same slope as EEN_Factor
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
		double Vpu, Vmag;
		double normMinCriteria, emergMinCriteria;

		Circuit ckt = DSS.activeCircuit;

		if (EEN_Factor > 0.0) return true;

		// check line overload

		if (loadSolutionCount != ckt.getSolution().getSolutionCount())
			calcVTerminalPhase();

		// get the lowest of the phase voltages
		Vpu = VBase;
		for (int i = 0; i < nPhases; i++) {
			Vmag = VTerminal[i].abs();
			if (Vmag < Vpu) Vpu = Vmag;
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
			// let EEN factor grow linearly at same slope
			// if EEN_Factor > 1.0 then EEN_Factor = 1.0;
			return true;
		}

		return false;
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		int j;

		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			switch (i) {
			case 3:
				pw.println("~ " + getParentClass().getPropertyName(i) + "=" + kWBase);
				break;
			case 4:
				pw.println("~ " + getParentClass().getPropertyName(i) + "=" + PFNominal);
				break;
			case 11:
				pw.println("~ " + getParentClass().getPropertyName(i) + "=" + kVArBase);
				break;
			case 21:
				pw.println("~ " + getParentClass().getPropertyName(i) + "=" + kVAAllocationFactor);
				break;
			case 22:
				pw.println("~ " + getParentClass().getPropertyName(i) + "=" + kVABase);
				break;
			case 32:
				pw.print("~ " + getParentClass().getPropertyName(i) + "=");
				for (j = 0; j < nZIPV; j++) pw.print(ZIPV[j] + " ");
				pw.println("\"");
				break;
			default:
				pw.println("~ " + getParentClass().getPropertyName(i) + "=" + getPropertyValue(i));
				break;
			}
		}
		pw.close();
	}

	/**
	 * Allocate load from connected kVA or kWh billing.
	 */
	public void setKVAAllocationFactor(double value) {
		setKVAAllocationFactor(value);
		allocationFactor = value;
		loadSpecType = LoadSpecType.XFKVA_ALLOCATIONFACTOR_PF;
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
		case XFKVA_ALLOCATIONFACTOR_PF:
			setKVAAllocationFactor(value);
			break;
		case KWH_KWHDAYS24_CFACTOR_PF:
			setCFactor(value);
			break;
		}
		computeAllocatedLoad();  // update kWBase
		hasBeenAllocated = true;
	}

	public void setCFactor(double value) {
		CFactor = value;
		allocationFactor = value;
		loadSpecType = LoadSpecType.KWH_KWHDAYS24_CFACTOR_PF;
		computeAllocatedLoad();
		hasBeenAllocated = true;
	}

	public void setConnectedKVA(double value) {
		connectedkVA = value;
		loadSpecType = LoadSpecType.XFKVA_ALLOCATIONFACTOR_PF;
		allocationFactor = kVAAllocationFactor;
		computeAllocatedLoad();
	}

	public void setKWh(double value) {
		kWh = value;
		loadSpecType = LoadSpecType.KWH_KWHDAYS24_CFACTOR_PF;
		allocationFactor = CFactor;
		computeAllocatedLoad();
	}

	public void setKWhDays(double value) {
		kWhDays = value;
		loadSpecType = LoadSpecType.KWH_KWHDAYS24_CFACTOR_PF;
		computeAllocatedLoad();
	}

	/**
	 * Fixed loads defined by kW, kVAr or kW, PF are ignored.
	 */
	private void computeAllocatedLoad() {
		switch (loadSpecType) {
		case XFKVA_ALLOCATIONFACTOR_PF:
			if (connectedkVA > 0.0) {
				kWBase = connectedkVA * kVAAllocationFactor * Math.abs(PFNominal);
				kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
				if (PFNominal < 0.0) kVArBase = -kVArBase;
			}
			break;
		case KWH_KWHDAYS24_CFACTOR_PF:
			avgKW = kWh / (kWhDays * 24);
			kWBase = avgKW * CFactor;
			kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			if (PFNominal < 0.0) kVArBase = -kVArBase;
			break;
		}
	}

	/**
	 * Get the present terminal currents and store for harmonics base reference.
	 */
	@Override
	public void initHarmonics() {
		Complex[] currents;

		/* Make sure there's enough memory */
		harmMag = Util.resizeArray(harmMag, nPhases);
		harmAng = Util.resizeArray(harmAng, nPhases);
		currents = new Complex[YOrder];  // to hold currents

		loadFundamental = DSS.activeCircuit.getSolution().getFrequency();

		getCurrents(currents);

		/* Store the currents at fundamental frequency.
		 * The spectrum is applied to these.
		 */
		for (int i = 0; i < nPhases; i++) {
			harmMag[i] = currents[i].abs();
			harmAng[i] = ComplexUtil.degArg(currents[i]);
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "3");  // "phases";
		setPropertyValue(1, getBus(0));  // "bus1";
		setPropertyValue(2, "12.47");
		setPropertyValue(3, "10");
		setPropertyValue(4, ".88");
		setPropertyValue(5, "1");
		setPropertyValue(6, "");
		setPropertyValue(7, "");
		setPropertyValue(8, "");
		setPropertyValue(9, "");
		setPropertyValue(10, "wye");
		setPropertyValue(11, "5");
		setPropertyValue(12, "-1"); // "rneut"; // if entered -, assume open or user defined
		setPropertyValue(13, "0");  // "xneut";
		setPropertyValue(14, "variable"); //"status";  // fixed or variable
		setPropertyValue(15, "1");  // class
		setPropertyValue(16, "0.95");
		setPropertyValue(17, "1.05");
		setPropertyValue(18, "0.0");
		setPropertyValue(19, "0.0");
		setPropertyValue(20, "0.0");
		setPropertyValue(21, "0.5");  // allocation factor
		setPropertyValue(22, "11.3636");
		setPropertyValue(23, "50");
		setPropertyValue(24, "10");
		setPropertyValue(25, "1");    // CVR watt factor
		setPropertyValue(26, "2");    // CVR var factor
		setPropertyValue(27, "0");    // kwh bulling
		setPropertyValue(28, "30");   // kwhdays
		setPropertyValue(29, "4");    // Cfactor
		setPropertyValue(30, "");     // CVRCurve
		setPropertyValue(31, "1");    // NumCust
		setPropertyValue(32, "");     // ZIPV coefficient array

		super.initPropertyValues(Load.NumPropsThisClass - 1);
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {
		double V;

		String s = "phases=1 conn=wye";

		// make sure voltage is line-neutral
		if (nPhases > 1 || connection != Connection.WYE) {
			V = kVLoadBase / DSS.SQRT3;
		} else {
			V = kVLoadBase;
		}

		s = s + String.format(" kV=%-.5g", V);

		// divide the load by no. phases
		if (nPhases > 1) {
			s = s + String.format(" kW=%-.5g  kvar=%-.5g", kWBase / nPhases, kVArBase / nPhases);
			if (connectedkVA > 0.0) {
				s = s + String.format(" xfkVA=%-.5g  ", connectedkVA / nPhases);
			}
		}

		Parser.getInstance().setCmdString(s);
		edit();

		super.makePosSequence();
	}

	@Override
	public String getPropertyValue(int index) {
		switch (index) {
		case 1:
			return getBus(0);
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

	public boolean shapeIsActual() {
		return shapeIsActual;
	}

}
