package com.epri.dss.conversion.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.ComplexMatrixImpl;
import org.apache.commons.math.complex.Complex;

import com.epri.dss.shared.impl.ComplexUtil;
import com.epri.dss.shared.impl.GeneratorVars;
import com.epri.dss.shared.impl.MathUtil;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.GenUserModel;
import com.epri.dss.conversion.Generator;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.shared.ComplexMatrix;
import com.epri.dss.shared.Dynamics;

public class GeneratorObjImpl extends PCElementImpl implements GeneratorObj {

	/** Number of energy meter registers. */
	private static final int NumGenRegisters = 6;
	private static final int NumGenVariables = 6;

	private static final Complex CDOUBLEONE = new Complex(1.0, 1.0);

	private Complex[] cBuffer = new Complex[24];  // temp buffer for calcs

	private Complex Yeq;     // at nominal
	private Complex Yeq95;   // at 95%
	private Complex Yeq105;  // at 105%

	private Complex currentLimit;
	private boolean debugTrace;
	/** Max allowable var change on Model=3 per iteration */
	private double deltaQMax;
	private int dispatchMode;
	private double dispatchValue;
	private double dQdV;
	private double dQdVSaved;
	private boolean forcedON;
	private boolean firstSampleAfterReset;
	private boolean fixed;   // if fixed, always at base value
	private int generatorSolutionCount;
	/** Thevenin equivalent voltage mag and angle reference for harmonic model. */
	private double genFundamental;
	/** Indicates whether generator is currently on. */
	private boolean genOn;
	private boolean genSwitchOpen;
	private boolean kVANotSet;
	private double lastGrowthFactor;
	/** Added for speedup so we don't have to search for growth factor a lot. */
	private int lastYear;
	private int openGeneratorSolutionCount;
	/** Deceleration factor for computing vars for PV generators. */
	private double PVFactor;
	private double randomMult;
	private int regHours;
	private int regKVArh;
	private int regKWh;
	private int regMaxKVA;
	private int regMaxKW;
	private int regPrice;
	private Complex shapeFactor;
	/** Thevinen equivalent voltage mag and angle reference for harmonic model. */
	private double thetaHarm;
	private File traceFile;
	/** User-written models. */
	private GenUserModel userModel, shaftModel;
	private double VAvg;
	private double VRemembered;
	private double varRemembered;
	/** Base vars per phase. */
	private double varBase;
	private double varMax;
	private double varMin;
	/** Base volts suitable for computing currents */
	private double VBase;
	private double VBase105;
	private double VBase95;
	private double VMaxPU;
	private double VMinPU;
	/** Thevinen equivalent voltage (complex) for dynamic model. */
	private Complex VThev;
	/** Thevinen equivalent voltage mag and angle reference for harmonic model. */
	private double VThevHarm;
	/** Thevinen equivalent voltage for dynamic model. */
	private double VThevMag;
	/**
	 * To handle cases where one conductor of load is open;
	 * We revert to admittance for inj currents.
	 */
	private ComplexMatrix YPrimOpenCond;
	/** Fixed value of y for type 7 load. */
	private double YQFixed;
	private boolean shapeIsActual;

	/** 0 = line-neutral; 1 = Delta */
	protected int connection;
	/** Daily (24 HR) generator shape. */
	protected String dailyDispShape;
	/** Daily Generator Shape for this load. */
	protected LoadShapeObj dailyDispShapeObj;
	/** Duty cycle load shape for changes typically less than one hour. */
	protected String dutyShape;
	/** Shape for this generator. */
	protected LoadShapeObj dutyShapeObj;
	protected int genClass;
	/** Variation with voltage. */
	protected int genModel;
	/** State variables. */
	protected GeneratorVars genVars;
	protected double kVArBase;
	protected double kVArMax;
	protected double kVArMin;
	protected double kWBase;
	protected double PFNominal;
	/** Per unit target voltage for generator with voltage control. */
	protected double Vpu;
	/** Target voltage for generator with voltage control. */
	protected double VTarget;
	/** ='fixed' means no variation  on all the time. */
	protected String yearlyShape;
	/** Shape for this generator. */
	protected LoadShapeObj yearlyShapeObj;

	protected double[] registers = new double[NumGenRegisters];
	protected double[] derivatives = new double[NumGenRegisters];

	public GeneratorObjImpl(DSSClassImpl parClass, String generatorName) {
		super(parClass);
		setName(generatorName.toLowerCase());
		objType = parClass.getDSSClassType(); // + GEN_ELEMENT;  // in both PC element and gen element list

		setNPhases(3);
		nConds = 4;   // defaults to wye
		YOrder  = 0;  // to trigger an initial allocation
		setNTerms(1);      // forces allocations
		kWBase  = 1000.0;
		kVArBase = 60.0;

		kVArMax   = kVArBase * 2.0;
		kVArMin   = -kVArMax;
		PFNominal = 0.88;
		//Rneut     = 0.0;
		//Xneut     = 0.0;
		yearlyShape    = "";
		yearlyShapeObj = null;     // if YearlyShapeObj = null then the load alway stays nominal * global multipliers
		dailyDispShape = "";
		dailyDispShapeObj = null;  // if DaillyShapeObj = null then the load alway stays nominal * global multipliers
		dutyShape         = "";
		dutyShapeObj      = null;  // if DutyShapeObj = null then the load alway stays nominal * global multipliers
		connection        = 0;     // Wye (star)
		genModel          = 1;  /* Typical fixed kW negative load */
		genClass          = 1;
		lastYear          = 0;
		lastGrowthFactor  = 1.0;

		dQdVSaved = 0.0;  // initialize this here; allows generators to be turned off and on


		generatorSolutionCount     = -1;  // for keep track of the present solution in injcurrent calcs
		openGeneratorSolutionCount = -1;
		YPrimOpenCond              = null;

		genVars.kVGeneratorBase  = 12.47;
		Vpu              = 1.0;
		VTarget          = 1000.0 * Vpu * genVars.kVGeneratorBase / DSSGlobals.SQRT3;  /* Line-to-Neutral target */
		VBase            = 7200.0;
		VMinPU           = 0.90;
		VMaxPU           = 1.10;
		VBase95          = VMinPU * VBase;
		VBase105         = VMaxPU * VBase;
		YOrder           = nTerms * nConds;
		randomMult       = 1.0 ;
		fixed            = false;

		/* Machine rating stuff */
		genVars.kVARating  = kWBase * 1.2;
		kVANotSet = true;  // flag for default value for kVA

		//GenVars.Vd = 7200.0;

		genVars.puXd       = 1.0;
		genVars.puXdp      = 0.28;
		genVars.puXdpp     = 0.20;
		genVars.Xd         = genVars.puXd * Math.pow(genVars.kVGeneratorBase, 2) * 1000.0 / genVars.kVARating;
		genVars.Xdp        = genVars.puXdp * Math.pow(genVars.kVGeneratorBase, 2) * 1000.0 / genVars.kVARating;
		genVars.Xdpp       = genVars.puXdpp * Math.pow(genVars.kVGeneratorBase, 2) * 1000.0 / genVars.kVARating;
		genVars.HMass      = 1.0;  // W-sec/VA rating
		genVars.theta      = 0.0;
		genVars.w0         = DSSGlobals.TWO_PI * getBaseFrequency();
		genVars.speed      = 0.0;
		genVars.dSpeed     = 0.0;
		genVars.D          = 1.0;

		userModel  = new GenUserModelImpl(genVars) ;
		shaftModel = new GenUserModelImpl(genVars);

		dispatchValue = 0.0;  // follow curves

		regKWh    = 1;
		regKVArh  = 2;
		regMaxKW  = 3;
		regMaxKVA = 4;
		regHours  = 5;
		regPrice  = 6;

		PVFactor      = 0.1;
		debugTrace    = false;
		forcedON     = false;
		genSwitchOpen = false;
		shapeIsActual = false;

		spectrum = "defaultgen";  // override base class

		initPropertyValues(0);

		recalcElementData();
	}

	/**
	 * 0 = reset to 1.0; 1 = Gaussian around mean and std Dev; 2 = uniform
	 */
	public void randomize(int opt) {
		switch (opt) {
		case 0:
			randomMult = 1.0;
			break;
		case DSSGlobals.GAUSSIAN:
			randomMult = MathUtil.gauss(yearlyShapeObj.getMean(), yearlyShapeObj.getStdDev());
			break;
		case DSSGlobals.UNIFORM:
			randomMult = Math.random();  // number between 0 and 1.0
			break;
		case DSSGlobals.LOGNORMAL:
			randomMult = MathUtil.quasiLognormal(yearlyShapeObj.getMean());
			break;
		}
	}

	private void calcDailyMult(double hr) {
		if (dailyDispShapeObj != null) {
			shapeFactor = dailyDispShapeObj.getMult(hr);
			shapeIsActual = dailyDispShapeObj.isUseActual();
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

	public void setNominalGeneration() {
		double factor;
		boolean genOnSaved;

		genOnSaved = genOn;
		shapeFactor = CDOUBLEONE;
		// check to make sure the generation is on
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		if (!sol.isDynamicModel() || !sol.isHarmonicModel()) {  // leave generator in whatever state it was prior to entering dynamic mode
			genOn = true;   // init to on then check if it should be off
			if (!forcedON)
				switch (dispatchMode) {
				case Generator.LOADMODE:
					if ((dispatchValue > 0.0) && (ckt.getGeneratorDispatchReference() < dispatchValue))
						genOn = false;
					break;
				case Generator.PRICEMODE:
					if ((dispatchValue > 0.0) && (ckt.getPriceSignal() < dispatchValue))
						genOn = false;
					break;
				}
		}


		if (!genOn) {
			// if generator is off enter as tiny resistive load (.0001 pu) so we don't get divide by zero in matrix
			genVars.PNominalPerPhase = -0.1 * kWBase / nPhases;
			// Pnominalperphase   = 0.0;
			genVars.QNominalPerPhase = 0.0;
		} else {
			// generator is on, compute it's nominal watts and vars

			if (isFixed()) {
				factor = 1.0;  // for fixed generators, set constant
			} else {
				switch (sol.getMode()) {
				case Dynamics.SNAPSHOT:
					factor = ckt.getGenMultiplier() * 1.0;
					break;
				case Dynamics.DAILYMODE:
					factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());  // daily dispatch curve
					break;
				case Dynamics.YEARLYMODE:
					factor = ckt.getGenMultiplier();
					calcYearlyMult(sol.getDblHour());
					break;
				case Dynamics.DUTYCYCLE:
					factor = ckt.getGenMultiplier();
					calcDutyMult(sol.getDblHour());
					break;
				case Dynamics.GENERALTIME:  // general sequential time simulation
					factor = ckt.getGenMultiplier();
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
						shapeFactor = new Complex(1.0, 1.0);  // default to 1 + j1 if not known
						break;
					}
					break;
				case Dynamics.MONTECARLO1:
					factor = ckt.getGenMultiplier() * 1.0;
					break;
				case Dynamics.MONTEFAULT:
					factor = ckt.getGenMultiplier() * 1.0;
					break;
				case Dynamics.FAULTSTUDY:
					factor = ckt.getGenMultiplier() * 1.0;
					break;
				case Dynamics.DYNAMICMODE:
					factor = ckt.getGenMultiplier() * 1.0;
					break;
				case Dynamics.MONTECARLO2:
					factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
					break;
				case Dynamics.MONTECARLO3:
					factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
					break;
				case Dynamics.LOADDURATION1:
					factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
					break;
				case Dynamics.LOADDURATION2:
					factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
					break;
				case Dynamics.PEAKDAY:
					factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
					break;
				case Dynamics.AUTOADDFLAG:
					factor = 1.0;
					break;
				default:
					factor = 1.0;
					break;
				}
			}

			if (!sol.isDynamicModel() || sol.isHarmonicModel()) {
				if (shapeIsActual) {
					genVars.PNominalPerPhase = 1000.0 * shapeFactor.getReal() / nPhases;
				} else {
					genVars.PNominalPerPhase = 1000.0 * kWBase * factor * shapeFactor.getReal() / nPhases;
				}


				if (genModel == 3) {
					/* Just make sure present value is reasonable. */
					if (genVars.QNominalPerPhase > varMax) {
						genVars.QNominalPerPhase = varMax;
					} else if (genVars.QNominalPerPhase < varMin) {
						genVars.QNominalPerPhase = varMin;
					}
				} else {
					/* for other generator models */
					if (shapeIsActual) {
						genVars.QNominalPerPhase = 1000.0 * shapeFactor.getImaginary() / nPhases;
					} else {
						genVars.QNominalPerPhase = 1000.0 * kVArBase * factor * shapeFactor.getImaginary() / nPhases;
					}
				}
			}
		}  /* else genON */

		if (!sol.isDynamicModel() || sol.isHarmonicModel()) {

			switch (genModel) {
			case 6:
				Yeq = ComplexUtil.invert(new Complex(0.0, -genVars.Xd));  // gets negated in calcYPrim
				break;
			default:
				Yeq = ComplexUtil.divide(new Complex(genVars.PNominalPerPhase, -genVars.QNominalPerPhase), Math.pow(VBase, 2));  // VBase must be L-N for 3-phase
				if (VMinPU != 0.0) {
					Yeq95 = ComplexUtil.divide(Yeq, Math.pow(VMinPU, 2));  // at 95% voltage
				} else {
					Yeq95 = Yeq;  // always a constant Z model
				}

				if (VMaxPU != 0.0) {
					Yeq105 = ComplexUtil.divide(Yeq, Math.pow(VMaxPU, 2));  // at 105% voltage
				} else {
					Yeq105 = Yeq;
				}
				break;
			}

			/* When we leave here, all the Yeq's are in L-N values */
			if (genModel == 7)
				currentLimit = ComplexUtil.divide(new Complex(genVars.PNominalPerPhase, genVars.QNominalPerPhase), VBase95);
		}

		// if generator state changes, force re-calc of Y matrix
		if (genOn != genOnSaved)
			setYPrimInvalid(true);
	}

	@Override
	public void recalcElementData() {

		VBase95  = VMinPU * VBase;
		VBase105 = VMaxPU * VBase;

		varBase = 1000.0 * kVArBase / nPhases;
		varMin  = 1000.0 * kVArMin  / nPhases;
		varMax  = 1000.0 * kVArMax  / nPhases;

		/* Populate data structures used for interchange with user-written models. */
		genVars.Xd    = genVars.puXd   * 1000.0 * Math.pow(genVars.kVGeneratorBase, 2) / genVars.kVARating;
		genVars.Xdp   = genVars.puXdp  * 1000.0 * Math.pow(genVars.kVGeneratorBase, 2) / genVars.kVARating;
		genVars.Xdpp  = genVars.puXdpp * 1000.0 * Math.pow(genVars.kVGeneratorBase, 2) / genVars.kVARating;
		genVars.conn = connection;
		genVars.numPhases = nPhases;
		genVars.numConductors = nConds;

		setNominalGeneration();

		/* Now check for errors. If any of these came out nil and the string was not nil, give warning. */
		if (yearlyShape.equalsIgnoreCase("none"))
			yearlyShape = "";
		if (dailyDispShape.equalsIgnoreCase("none"))
			dailyDispShape = "";
		if (dutyShape.equalsIgnoreCase("none"))
			dutyShape = "";

		if (yearlyShapeObj == null)
			if (yearlyShape.length() > 0)
				DSSGlobals.doSimpleMsg("Warning: Yearly load shape: \""+ yearlyShape +"\" not found.", 563);
		if (dailyDispShapeObj == null)
			if (dailyDispShape.length() > 0)
				DSSGlobals.doSimpleMsg("Warning: Daily load shape: \""+ dailyDispShape +"\" not found.", 564);
		if (dutyShapeObj == null)
			if (dutyShape.length() > 0)
				DSSGlobals.doSimpleMsg("Warning: Duty load shape: \""+ dutyShape +"\" not found.", 565);

		setSpectrumObj( (com.epri.dss.general.SpectrumObj) DSSGlobals.spectrumClass.find(getSpectrum()) );
		if (getSpectrumObj() == null)
			DSSGlobals.doSimpleMsg("Error: Spectrum \""+getSpectrum()+"\" not found.", 566);
		/*
		if (Rneut < 0.0) {  // flag for open neutral
			YNeut = new Complex(0.0, 0.0);
		} else if ((Rneut == 0.0) && (Xneut = 0.0)) {  // solidly grounded
			YNeut = new Complex(1.0e6, 0.0);  // 1 microohm resistor
		} else {
			YNeut = new Complex(Rneut, XNeut).invert();
		}
		*/

		YQFixed = -varBase / Math.pow(VBase, 2);   // 10-17-02  Fixed negative sign
		VTarget = Vpu * 1000.0 * genVars.kVGeneratorBase / DSSGlobals.SQRT3;

		// initialize to zero - defaults to PQ generator
		// solution object will reset after circuit modifications
		dQdV      = dQdVSaved;         // for Model = 3
		deltaQMax = (varMax - varMin) * 0.10;  // limit to 10% of range

		setInjCurrent( Utilities.resizeArray(getInjCurrent(), YOrder) );

		/* Update any user-written models */
		if (userModel.exists()) userModel.updateModel();
		if (shaftModel.exists()) shaftModel.updateModel();
	}

	private void calcYPrimMatrix(ComplexMatrix Ymatrix) {
		Complex Y , Yij;
		int i, j;
		double freqMultiplier;

		Circuit ckt = DSSGlobals.activeCircuit;

		YPrimFreq = ckt.getSolution().getFrequency();
		freqMultiplier = YPrimFreq / baseFrequency;

		SolutionObj sol = ckt.getSolution();

		if (sol.isDynamicModel() || sol.isHarmonicModel()) {
			if (genOn) {
				Y  = Yeq;  // L-N value computed in initialization routines
			} else {
				Y = new Complex(DSSGlobals.EPSILON, 0.0);
			}

			if (connection == 1)
				Y = ComplexUtil.divide(Y, 3.0);  // convert to delta impedance
			Y = new Complex(Y.getReal(), Y.getImaginary() / freqMultiplier);
			Yij = Y.negate();
			for (i = 0; i < nPhases; i++) {
				switch (connection) {
				case 0:
					Ymatrix.set(i, i, Y);
					Ymatrix.add(nConds, nConds, Y);
					Ymatrix.setSym(i, nConds, Yij);
					break;
				case 1:  /* Delta connection */
					Ymatrix.set(i, i, Y);
					Ymatrix.add(i, i, Y);  // put it in again
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						Ymatrix.setSym(i, j, Yij);
					}
					break;
				}
			}

			/* **** Removed neutral / neutral may float

			if (Connection == 0) {  // take care of neutral issues
				Ymatrix.addElement(nConds, nConds, YNeut);  // add in user specified neutral Z, if any
				// bump up neutral-ground in case neutral ends up floating
				Ymatrix.setElement(nConds, nConds, Ymatrix.getElement(nConds, nConds).multiply(1.000001));
			}

			 */

		} else {
			// Regular power flow generator model

			/* Yeq is always expected as the equivalent line-neutral admittance */

			Y = Yeq.negate();  // negate for generation; Yeq is L-N quantity

			// ****** Need to modify the base admittance for real harmonics calcs
			Y = new Complex(Y.getReal(), Y.getImaginary() / freqMultiplier);

			switch (connection) {
			case 0:  // WYE
				Yij = Y.negate();
				for (i = 0; i < nPhases; i++) {
					Ymatrix.set(i, i, Y);
					Ymatrix.add(nConds, nConds, Y);
					Ymatrix.setSym(i, nConds, Yij);
				}
				break;
			case 1:  // delta or L-L
				Y    = ComplexUtil.divide(Y, 3.0);  // convert to delta impedance
				Yij  = Y.negate();
				for (i = 0; i < nPhases; i++) {
					j = i + 1;
					if (j > nConds) j = 0;  // wrap around for closed connections
					Ymatrix.add(i, i, Y);
					Ymatrix.add(j, j, Y);
					Ymatrix.addSym(i, j, Yij);
				}
				break;
			}
		}
	}

	@Override
	public void calcYPrim() {

		// build only shunt Yprim
		// build a dummy Yprim series so that calcV does not fail
		if (isYprimInvalid()) {
			if (YPrimShunt != null) YPrimShunt = null;
			YPrimShunt = new ComplexMatrixImpl(YOrder);
			if (YPrimSeries != null) YPrimSeries = null;
			YPrimSeries = new ComplexMatrixImpl(YOrder);
			if (YPrim != null) YPrim = null;
			YPrim = new ComplexMatrixImpl(YOrder);
		} else {
			YPrimShunt.clear();
			YPrimSeries.clear();
			YPrim.clear();
		}

		if (DSSGlobals.activeCircuit.getSolution().getLoadModel() == DSSGlobals.POWERFLOW) {

			// 12-7-99 we'll start with Yeq in system matrix
			setNominalGeneration();
			calcYPrimMatrix(YPrimShunt);

		} else {

			// admittance model wanted
			setNominalGeneration();
			calcYPrimMatrix(YPrimShunt);

		}

		// set YPrim_Series based on diagonals of YPrim_shunt so that calcVoltages doesn't fail
		for (int i = 0; i < YOrder; i++)
			YPrimSeries.set(i, i, YPrimShunt.get(i, i).multiply(1.0e-10));

		YPrim.copyFrom(YPrimShunt);

		// account for open conductors
		super.calcYPrim();
	}

	/**
	 * Add the current into the proper location according to connection.
	 *
	 * Reverse of similar routine in load (complex negates are switched).
	 */
	private void stickCurrInTerminalArray(Complex[] termArray, Complex curr, int i) {
		switch (connection) {
		case 0:  // wye
			termArray[i] = termArray[i].add(curr);
			termArray[nConds] = termArray[nConds].add(curr.negate());  // neutral
			break;
		case 1:  // delta
			termArray[i] = termArray[i].add(curr);
			int j = i + 1;
			if (j > nConds) j = 0;
			termArray[j] = termArray[j].add(curr.negate());
			break;
		}
	}

	private void writeTraceRecord(String s) {
		int i;

		try {
			if (!DSSGlobals.inShowResults) {
				FileWriter fw = new FileWriter(traceFile, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(String.format("%-.g, %d, %-.g, ",
						DSSGlobals.activeCircuit.getSolution().getDynaVars().t,
						DSSGlobals.activeCircuit.getSolution().getIteration(),
						DSSGlobals.activeCircuit.getLoadMultiplier()) +
						Utilities.getSolutionModeID() + ", " +
						Utilities.getLoadModel() + ", " +
						genModel + ", " +
						dQdV + ", " +
						(VAvg * 0.001732 / genVars.kVGeneratorBase) + ", " +
						(VTarget - VAvg) + ", " +
						(genVars.QNominalPerPhase * 3.0 / 1.0e6) + ", " +
						(genVars.PNominalPerPhase * 3.0 / 1.0e6) + ", " +
						s + ", ");
				for (i = 0; i < nPhases; i++)
					bw.write(getInjCurrent()[i].abs() + ", ");
				for (i = 0; i < nPhases; i++)
					bw.write(getITerminal()[i].abs() + ", ");
				for (i = 0; i < nPhases; i++)
					bw.write(getVTerminal()[i].abs() + ", ");
				bw.write(VThevMag + ", " + genVars.theta * 180.0 / Math.PI);
				bw.newLine();
				bw.close();
				fw.close();
			}
		} catch (Exception e) {
			// FIXME handle exception
		}
	}

	/**
	 * Compute total terminal current for Constant PQ.
	 */
	private void doConstantPQGen() {
		int i;
		Complex V, curr = null;
		double VMag;
		//Complex[] V012, I012 = new Complex[2];
		//Complex[] Iabc = new Complex[3];

		// treat this just like the Load moVdel

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		zeroITerminal();

		/* Tried this but couldn't get it to work
		switch (nPhases) {
		case 3:  // Use Symmetrical Components
			phase2SymComp(Vterminal, V012);   // vTerminal is L-N voltages here
			// Phase2SymComp(InjCurrent, @I012);   // vTerminal is L-G voltages here
			V = V012[0];  // positive sequence L-N voltage
			Vmag = V012[0].abs();

//			if (VMag <= VBase95) {
//				Curr = Yeq95.multiply(V).negate();  // below 95% (Vminpu) use an impedance model
//			} else {
//				if (Vmag > VBase105)
//					Curr = Yeq105.multiply(V).negate();  // above 105% (Vmaxpu) use an impedance model
//			}
			if ((Vmag <= VBase95) || (Vmag > VBase105)) {
				Curr = CurrentLimit.divide(V.divide(-Vmag)).conjugate();
			} else {
				Curr = new Complex(-GenVars.Pnominalperphase, -GenVars.Qnominalperphase).divide(V).conjugate();  // current into pos seq model
			}

			I012[0] = Curr;  // pos sequence current into the terminal

			if (Connection == 1) {
				I012[0] = Complex.ZERO;
			} else {
				I012[0] = V012[0].divide(new Complex(0.0, xdpp));
			}
			I012[1] = V012[1].divide(new Complex(0.0, xdpp));

			// negative and zero sequence contributions
			symComp2Phase(Iabc, I012);    // Iabc now desired terminal current
			if (DebugTrace) {
				FileWriter TraceStream = new FileWriter(TraceFile, true);
				BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);
				TraceBuffer.write(String.format("V1=%-.5g, /_%-.5g, ", V.abs(), V.degArg()));
				TraceBuffer.write(String.format("I1=%-.5g, /_%-.5g, ", Curr.abs(), Curr.degArg()));
				TraceBuffer.write("Iabc=");
				for (int i = 0; i < 3; i++)
					TraceBuffer.write(String.format("%-.5g, /_%-.5g, ", Iabc[i].abs(), Iabc[i].degArg()));
				TraceBuffer.newLine();
				TraceBuffer.close();
			}

			for (i = 0; i < 3; i++) {
				ITerminal[i] = Iabc[i];  // put into terminal array directly because we have computed line current above
				Caccum(getInjCurrent()[i], Iabc[i].negate()));  // subtract in
				if (Connection == 0) {
					Caccum(getIterminal()[nConds], Iabc[i].negate());  // neutral
					Caccum(getInjCurrent()[nConds], Iabc[i]);  // neutral
				}
			}
			IterminalUpdated = true;  // so that we con't have to recompute for a report
			break;
		}
		*/


		calcVTerminalPhase();  // get actual voltage across each phase of the load
		for (i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			VMag = V.abs();

			switch (connection) {
			case 0:  /* Wye */
				if (VMag <= VBase95) {
					curr = Yeq95.multiply(V);  // below 95% use an impedance model
				} else if (VMag > VBase105) {
					curr = Yeq105.multiply(V);  // above 105% use an impedance model
				} else {
					curr = new Complex(genVars.PNominalPerPhase, genVars.QNominalPerPhase).divide(V).conjugate();  // between 95% -105%, constant PQ
				}
				break;
			case 1:  /* Delta */
				VMag = VMag / DSSGlobals.SQRT3;  // L-N magnitude
				if (VMag <= VBase95) {
					curr = ComplexUtil.divide(Yeq95, 3.0).multiply(V);  // below 95% use an impedance model
				} else if (VMag > VBase105) {
					curr = ComplexUtil.divide(Yeq105, 3.0).multiply(V);  // above 105% use an impedance model
				} else {
					curr = new Complex(genVars.PNominalPerPhase, genVars.QNominalPerPhase).divide(V).conjugate();  // between 95% -105%, constant PQ
				}
				break;
			}

			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	private void doConstantZGen() {
		int i;
		Complex curr, Yeq2;

		// assume Yeq is kept up to date
		calcYPrimContribution(getInjCurrent());  // init InjCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();
		if (connection == 0) {
			Yeq2 = Yeq;
		} else {
			Yeq2 = ComplexUtil.divide(Yeq, 3.0);
		}

		for (i = 0; i < nPhases; i++) {
			curr = Yeq2.multiply(VTerminal[i]);   // Yeq is always line to neutral
			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current for Constant P, |V|.
	 */
	private void doPVTypeGen() {
		int i;
		double dQ;
		Complex curr;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase(); // get actual voltage across each phase of the generator
		zeroITerminal();

		// guess at a new var output value
		VAvg = 0.0;
		for (i = 0; i < nPhases; i++)
			VAvg = VAvg + VTerminal[i].abs();

		if (connection == 1) {
			VAvg = VAvg / (DSSGlobals.SQRT3 * nPhases);
		} else {
			VAvg = VAvg / nPhases;
		}

		// 12-9-99 added empirical 0.7 factor to improve iteration
		// 12-17-99 changed to 0.1 because first guess was consistently too high
		dQ =  PVFactor * dQdV * (VTarget - VAvg);   // vTarget is L-N
		if (Math.abs(dQ) > deltaQMax)
			if (dQ < 0.0) {
				dQ = -deltaQMax;
			} else {
				dQ = deltaQMax;
			}
		genVars.QNominalPerPhase = genVars.QNominalPerPhase + dQ;

		/* Test limits */
		if (genVars.QNominalPerPhase > varMax) {
			genVars.QNominalPerPhase = varMax;
		} else if (genVars.QNominalPerPhase < varMin) {
			genVars.QNominalPerPhase = varMin;
		}

		// compute injection currents using W and var values
		// do not use constant Z models outside normal range
		// presumably the var source will take care of the voltage problems
		for (i = 0; i < nPhases; i++) {
			curr = new Complex(genVars.PNominalPerPhase, genVars.QNominalPerPhase).divide(VTerminal[i]).conjugate();
			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current for Fixed Q.
	 * Constant P, Fixed Q - Q is always kvarBase.
	 */
	private void doFixedQGen() {
		int i;
		Complex curr = null, V;
		double VMag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			VMag = V.abs();

			switch (connection) {
			case 0:
				if (VMag <= VBase95) {
					curr = new Complex(Yeq95.getReal(), YQFixed).multiply(V);  // below 95% use an impedance model
				} else if (VMag > VBase105) {
					curr = new Complex(Yeq105.getReal(), YQFixed).multiply(V);  // above 105% use an impedance model
				} else {
					curr = new Complex(genVars.PNominalPerPhase, varBase).divide(V).conjugate();
				}
				break;
			case 1:
				VMag = VMag / DSSGlobals.SQRT3;  // convert to L-N for test
				if (VMag <= VBase95) {
					curr = new Complex(Yeq95.getReal() / 3.0, YQFixed / 3.0).multiply(V);  // below 95% use an impedance model
				} else if(VMag > VBase105) {
					curr = new Complex(Yeq105.getReal() / 3.0, YQFixed / 3.0).multiply(V);  // above 105% use an impedance model
				} else {
					curr = new Complex(genVars.PNominalPerPhase, varBase).divide(V).conjugate();
				}
				break;
			}
			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current for constant P, fixed Q.
	 * Q is always a fixed Z derived from kvarBase
	 */
	private void doFixedQZGen() {
		int i;
		Complex curr = null, V;
		double VMag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			VMag = V.abs();

			switch (connection) {
			case 0:
				if (VMag <= VBase95) {
					curr = new Complex(Yeq95.getReal(), YQFixed).multiply(V);  // below 95% use an impedance model
				} else if (VMag > VBase105) {
					curr = new Complex(Yeq105.getReal(), YQFixed).multiply(V);
				} else {
					curr = new Complex(genVars.PNominalPerPhase, 0.0).divide(V).conjugate(); // P component of current
					curr = curr.add(new Complex(0.0, YQFixed).multiply(V));  // add in Q component of current
				}
				break;
			case 1:
				VMag = VMag / DSSGlobals.SQRT3;  // convert to L-N for test
				if (VMag <= VBase95) {
					curr = new Complex(Yeq95.getReal() / 3.0, YQFixed / 3.0).multiply(V);  // below 95% use an impedance model
				} else  if (VMag > VBase105) {
					curr = new Complex(Yeq105.getReal() / 3.0, YQFixed / 3.0).multiply(V);
				} else {
					curr = new Complex(genVars.PNominalPerPhase, 0.0).divide(V).conjugate();  // P component of current
					curr = curr.add(new Complex(0.0, YQFixed / 3.0).multiply(V));  // add in Q component of current
				}
				break;
			}

			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current from User-written model.
	 */
	private void doUserModel() {
		calcYPrimContribution(getInjCurrent());  // init injCurrent array

		if (userModel.exists()) {  // check automatically selects the usermodel if true
			//appendToEventLog("Wnominal=", String.format("%-.5g", Pnominalperphase));
			userModel.calc(VTerminal, ITerminal);
			setITerminalUpdated(true);
//			SolutionObj sol = DSSGlobals.activeCircuit.getSolution();
			// negate currents from user model for power flow generator model
			for (int i = 0; i < nConds; i++)
				getInjCurrent()[i] = getInjCurrent()[i].add( getITerminal()[i].negate() );
		} else {
			DSSGlobals.doSimpleMsg("Generator." + getName() + " model designated to use user-written model, but user-written model is not defined.", 567);
		}
	}

	/**
	 * Compute total terminal current for constant PQ, but limit to max current below VminPU.
	 */
	private void doCurrentLimitedPQ() {
		int i;
		Complex curr = null, V;
		double VMag, VMagLN;

		// treat this just like the load model

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			VMag = V.abs();

			switch (connection) {
			case 0:
				if ((VMag <= VBase95) || (VMag > VBase105)) {  // limit the current magnitude when voltage drops outside normal range
					curr = currentLimit.divide( ComplexUtil.divide(V, VMag) ).conjugate();  // current limit expression
				} else {
					curr = new Complex(genVars.PNominalPerPhase, genVars.QNominalPerPhase).divide(V).conjugate();  // above vMinPU, constant PQ
				}
				break;
			case 1:
				VMagLN = VMag / DSSGlobals.SQRT3;
				if ((VMagLN <= VBase95) || (VMagLN > VBase105)) {  // limit the current magnitude when voltage drops outside normal range
					curr = currentLimit.divide( ComplexUtil.divide(V, VMag) ).conjugate();  // Current limit expression
				} else {
					curr = new Complex(genVars.PNominalPerPhase, genVars.QNominalPerPhase).divide(V).conjugate();  // above vMinPU, constant PQ
				}
				break;
			}

			stickCurrInTerminalArray(getITerminal(), curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total current and add into injTemp.
	 */
	private void doDynamicMode() {
		int i;
		Complex[] V012 = new Complex[2];
		Complex[] I012 = new Complex[2];

		calcYPrimContribution(getInjCurrent());  // init injCurrent array

		/* Inj = -Itotal (in) - Yprim * Vtemp */
		if ((genModel == 6) && userModel.exists()) {  // auto selects model
			/* We have total currents in Itemp */
			userModel.calc(VTerminal, ITerminal);  // returns terminal currents in iTerminal
		} else {
			/* No user model, use default Thevinen equivalent */
			switch (nPhases) {
			case 1:
				calcVThevDyn();  // update for latest phase angle

				getITerminal()[0] = getVTerminal()[0].subtract(VThev).subtract(getVTerminal()[1]).divide(new Complex(0.0, genVars.Xdp));
				getITerminal()[1] = getITerminal()[0].negate();

				break;
			case 3:
				MathUtil.phase2SymComp(VTerminal, V012);

				// positive sequence contribution to iTerminal
				calcVThevDyn();  // update for latest phase angle

				// positive sequence contribution to iTerminal
				I012[0] = V012[0].subtract(VThev).divide(new Complex(0.0, genVars.Xdp));
				I012[1] = V012[1].divide(new Complex(0.0, genVars.Xdpp));
				if (connection == 1) {
					I012[0] = Complex.ZERO;  // TODO Check zero based indexing
				} else {
					I012[0] = V012[0].divide(new Complex(0.0, genVars.Xdpp));
				}
				MathUtil.symComp2Phase(getITerminal(), I012);  // convert back to phase components

				// Neutral current
				if (connection == 0)
					getITerminal()[nConds] = I012[0].multiply(3.0).negate();
				break;
			default:
				DSSGlobals.doSimpleMsg(String.format("Dynamics mode is implemented only for 1- or 3-phase generators. Generator."+getName()+" has %d phases.", nPhases), 5671);
				DSSGlobals.solutionAbort = true;
				break;
			}
		}

		setITerminalUpdated(true);

		/* Add it into inj current array */
		for (i = 0; i < nConds; i++)
			getInjCurrent()[i] = getInjCurrent()[i].add( getITerminal()[i].negate() );

		/* Take Care of any shaft model calcs */
		if ((genModel == 6) && shaftModel.exists()) {  // auto selects model
			// compute mech power to shaft
			shaftModel.calc(getVTerminal(), getITerminal());  // returns pshaft at least
		}
	}

	/**
	 * Compute injection current only when in harmonics mode.
	 *
	 * Assumes spectrum is a voltage source behind subtransient reactance and YPrim has been built.
	 * Vd is the fundamental frequency voltage behind Xd" for phase 1.
	 */
	private void doHarmonicMode() {
		int i;
		Complex e;
		double genHarmonic;

		computeVTerminal();

		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();
		genHarmonic = sol.getFrequency() / genFundamental;
		e = getSpectrumObj().getMult(genHarmonic).multiply(VThevHarm);  // get base harmonic magnitude
		e = Utilities.rotatePhasorRad(e, genHarmonic, thetaHarm);  // time shift by fundamental frequency phase shift
		for (i = 0; i < nPhases; i++) {
			cBuffer[i] = e;
			if (i < nPhases)
				e = Utilities.rotatePhasorDeg(e, genHarmonic, -120.0);  // assume 3-phase generator
		}

		/* Handle wye connection */
		if (connection == 0)
			cBuffer[nConds] = getVTerminal()[nConds];  // assume no neutral injection voltage

		/* Inj currents = Yprim (E) */
		YPrim.vMult(getInjCurrent(), cBuffer);
	}

	private void calcVTerminalPhase() {
		int i, j;
		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

		/* Establish phase voltages and stick in Vterminal */
		switch (connection) {
		case 0:
			for (i = 0; i < nPhases; i++)
				getVTerminal()[i] = sol.vDiff(nodeRef[i], nodeRef[nConds]);

			break;
		case 1:
			for (i = 0; i < nPhases; i++) {
				j = i + 1;
				if (j > nConds) j = 0;
				getVTerminal()[i] = sol.vDiff(nodeRef[i], nodeRef[j]);
			}
			break;
		}

		generatorSolutionCount = sol.getSolutionCount();
	}

	/**
	 * Put terminal voltages in an array.
	 */
	private void calcVterminal() {

		computeVTerminal();

		generatorSolutionCount = DSSGlobals.activeCircuit.getSolution().getSolutionCount();
	}

	/**
	 * Calculates generator current and adds it properly into the injcurrent array
	 * routines may also compute ITerminal (ITerminalUpdated flag).
	 */
	private void calcGenModelContribution() {
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		setITerminalUpdated(false);
		if (sol.isDynamicModel()) {
			doDynamicMode();
		} else if (sol.isHarmonicModel() && (sol.getFrequency() != ckt.getFundamental())) {
			doHarmonicMode();
		} else {
			// compute currents and put into injTemp array
			switch (genModel) {
			case 1:
				doConstantPQGen();
				break;
			case 2:
				doConstantZGen();
				break;
			case 3:
				doPVTypeGen();  // constant P, |V|
				break;
			case 4:
				doFixedQGen();
				break;
			case 5:
				doFixedQZGen();
				break;
			case 6:
				doUserModel();
				break;
			case 7:
				doCurrentLimitedPQ();
				break;
			default:
				doConstantPQGen();  // for now, until we implement the other models.
				break;
			}
		}
		/* When this is done, ITerminal is up to date */
	}

	/**
	 * Difference between currents in YPrim and total current.
	 */
	private void calcInjCurrentArray() {

		// now get injection currents.
		if (genSwitchOpen) {
			zeroInjCurrent();
		} else {
			calcGenModelContribution();
		}

		/*
		We're not going to mess with this logic here -- too complicated: Use an open line in series
		to look at open phase conditions.

		} else {
			SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

			// some terminals not closed  use admittance model for injection
			if (OpenGeneratorSolutionCount != sol.getSolutionCount()) {

				// rebuild the YPrimOpenCond if a new solution because values may have changed

				// only reallocate when necessary
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

				// now account for the open conductors //
				// for any conductor that is open, zero out row and column
				int k = 0;
				for (int i = 0; i < nTerms; i++) {
					for (int j = 0; j < nConds; j++) {
						if (!Terminals[i].getConductors()[j].isClosed()) {
							YPrimOpenCond.zeroRow(j + k);
							YPrimOpenCond.zeroCol(j + k);
							YPrimOpenCond.setElement(j + k, j + k, new Complex(1.0e-12, 0.0));  // in case node gets isolated
						}
					}
					k = k + nConds;
				}

				OpenGeneratorSolutionCount = sol.getSolutionCount();

			}

			for (int i = 0; i < Yorder; i++) {
				Ref = NodeRef[i];
				if (Ref == -1) {
					Vterminal[i] = Complex.ZERO;
				} else {
					Vterminal[i] = V[Ref];
				}
			}
			YPrimOpenCond.MVmult(InjTemp, Vterminal);
			for (int i = 0; i < Yorder; i++)
				InjTemp[i] = InjTemp[i].neagte();
		} */
	}

	/**
	 * Compute total currents.
	 */
	@Override
	protected void getTerminalCurrents(Complex[] curr) {
		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

		if (ITerminalSolutionCount != sol.getSolutionCount()) {  // recalc the contribution
			if (!genSwitchOpen)
				calcGenModelContribution();  // adds totals in iTerminal as a side effect
		}

		super.getTerminalCurrents(curr);

		if (debugTrace) writeTraceRecord("TotalCurrent");
	}

	@Override
	public int injCurrents() {
		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

		if (sol.loadsNeedUpdating())
			setNominalGeneration();  // set the nominal kW, etc for the type of solution being done

		calcInjCurrentArray();  // difference between currents in YPrim and total terminal current

		if (debugTrace) writeTraceRecord("Injection");

		// add into system injection current array

		return super.injCurrents();
	}

	/**
	 * Gives the currents for the last solution performed.
	 *
	 * Do not call setNominalLoad, as that may change the load values.
	 */
	@Override
	public void getInjCurrents(Complex[] curr) {
		calcInjCurrentArray();  // difference between currents in YPrim and total current

		try {
			// copy into buffer array
			for (int i = 0; i < YOrder; i++)
				curr[i] = getInjCurrent()[i];
		} catch (Exception e) {
			DSSGlobals.doErrorMsg("Generator Object: \"" + getName() + "\" in getInjCurrents method.",
					e.getMessage(),
					"Current buffer not big enough.", 568);
		}
	}

	public void resetRegisters() {
		for (int i = 0; i < NumGenRegisters; i++)
			registers[i] = 0.0;
		for (int i = 0; i < NumGenRegisters; i++)
			derivatives[i] = 0.0;
		firstSampleAfterReset = true;  // initialize for trapezoidal integration
	}

	private void integrate(int reg, double deriv, double interval) {
		if (DSSGlobals.activeCircuit.isTrapezoidalIntegration()) {
			/* Trapezoidal Rule Integration */
			if (!firstSampleAfterReset)
				registers[reg] = registers[reg] + 0.5 * interval * (deriv + derivatives[reg]);
		} else {
			/* Plain Euler integration */
			registers[reg] = registers[reg] + interval * deriv;
		}

		derivatives[reg] = deriv;
	}

	/**
	 * Update energy from metered zone.
	 */
	public void takeSample() {
		Complex S;
		double SMag;
		double hourValue;

		// compute energy in generator branch
		if (isEnabled()) {

			if (genOn) {
				S = new Complex(getPresentKW(), getPresentKVAr());
				SMag = S.abs();
				hourValue = 1.0;
			} else {
				S = Complex.ZERO;
				SMag = 0.0;
				hourValue = 0.0;
			}

			Circuit ckt = DSSGlobals.activeCircuit;

			if (genOn || ckt.isTrapezoidalIntegration()) {
				/* Make sure we always integrate for Trapezoidal case.
				 * Don't need to for gen off and normal integration.
				 */
				if (ckt.isPositiveSequence()) {
					S    = S.multiply(3.0);
					SMag = 3.0 * SMag;
				}
				integrate            (regKWh,   S.getReal(), ckt.getSolution().getIntervalHrs());   // accumulate the power
				integrate            (regKVArh, S.getImaginary(), ckt.getSolution().getIntervalHrs());
				setDragHandRegister  (regMaxKW, Math.abs(S.getReal()));
				setDragHandRegister  (regMaxKVA, SMag);
				integrate            (regHours, hourValue, ckt.getSolution().getIntervalHrs());  // accumulate hours in operation
				integrate            (regPrice, S.getReal() * ckt.getPriceSignal(), ckt.getSolution().getIntervalHrs());  // accumulate hours in operation
				firstSampleAfterReset = false;
			}
		}
	}

	public double getPresentKW() {
		return genVars.PNominalPerPhase * 0.001 * nPhases;
	}

	public double getPresentKV() {
		return genVars.kVGeneratorBase;
	}

	public double getPresentKVAr() {
		return genVars.QNominalPerPhase * 0.001 * nPhases;
	}

	/**
	 * Procedures for setting the dQdV used by the solution object.
	 */
	public void initDQDVCalc() {
		dQdV = 0.0;
		genVars.QNominalPerPhase = 0.5 * (varMax + varMin);  // avg of the limits
	}

	/**
	 * Bump up vars by 10% of range for next calc.
	 */
	public void bumpUpQ() {
		genVars.QNominalPerPhase = genVars.QNominalPerPhase + 0.1 * (varMax - varMin);
	}

	public void rememberQV() {
		varRemembered = genVars.QNominalPerPhase;
		calcVterminal();
		VAvg = 0.0;
		for (int i = 0; i < nPhases; i++)
			VAvg = VAvg + VTerminal[i].abs();
		VAvg = VAvg / nPhases;
		VRemembered = VAvg;
	}

	public void calcDQDV() {
		double VDiff;
		int i;

		calcVterminal();
		VAvg = 0.0;
		for (i = 0; i < nPhases; i++)
			VAvg = VAvg + VTerminal[i].abs();
		VAvg = VAvg / nPhases;

		VDiff = VAvg - VRemembered;
		if (VDiff != 0.0) {
			dQdV = (genVars.QNominalPerPhase - varRemembered) / VDiff;
		} else {
			dQdV = 0.0;  // something strange has occured
		}

		// this will force a de facto P, Q model
		dQdVSaved = dQdV;  // save for next time; allows generator to be enabled/disabled during simulation
	}

	public void resetStartPoint() {
		genVars.QNominalPerPhase = 1000.0 * kVArBase / nPhases;
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		int i, idx;

		super.dumpProperties(F, Complete);

		F.println("!dQdV=" + dQdV);

		for (i = 0; i < getParentClass().getNumProperties(); i++) {
			idx = getParentClass().getPropertyIdxMap()[i];
			switch (idx) {
			case 33:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=(" + propertyValue[idx] + ")");
				break;
			case 35:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=(" + propertyValue[idx] + ")");
				break;
			default:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + propertyValue[idx]);
				break;
			}
		}

		F.println();
	}

	/*
	 * Support for harmonics mode.
	 */

	@Override
	public void initHarmonics() {
		Complex e, Va = null;

		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

		setYPrimInvalid(true);  // force rebuild of YPrims
		genFundamental = sol.getFrequency();  // whatever the frequency is when we enter here.


		Yeq = ComplexUtil.invert(new Complex(0.0, genVars.Xdpp));  // used for current calcs; always L-N

		/* Compute reference Thevinen voltage from phase 1 current */

		if (genOn) {

			computeITerminal();  // get present value of current

			switch (connection) {
			case 0:  /* Wye - neutral is explicit */
				Va = sol.getNodeV()[nodeRef[0]].subtract( sol.getNodeV()[nodeRef[nConds]] );
				break;
			case 1:  /* Delta -- assume neutral is at zero */
				Va = sol.getNodeV()[nodeRef[0]];
				break;
			}

			e = Va.subtract(ITerminal[0].multiply( new Complex(0.0, genVars.Xdpp) ));
			VThevHarm = e.abs();   // establish base mag and angle
			thetaHarm = e.getArgument();
		} else {
			VThevHarm = 0.0;
			thetaHarm = 0.0;
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		propertyValue[0]      = "3";        // phases;
		propertyValue[1]      = getBus(0);  // bus1
		propertyValue[2]      = "12.47";
		propertyValue[3]      = "100";
		propertyValue[4]      = ".80";
		propertyValue[5]      = "1";
		propertyValue[6]      = "";
		propertyValue[7]      = "";
		propertyValue[8]      = "";
		propertyValue[9]      = "Default";
		propertyValue[10]     = "0.0";
		propertyValue[11]     = "wye";
		propertyValue[12]     = "60";
		propertyValue[13]     = "0";  // "rneut"; // if entered -, assume open
		propertyValue[14]     = "0";  // "xneut";
		propertyValue[15]     = "variable"; //"status"  fixed or variable
		propertyValue[16]     = "1"; //"class"
		propertyValue[17]     = "1.0";
		propertyValue[18]     = Utilities.strReal(kVArMax, 3);
		propertyValue[19]     = Utilities.strReal(kVArMin, 3);
		propertyValue[20]     = "0.1";
		propertyValue[21]     = "no";
		propertyValue[22]     = "0.90";
		propertyValue[23]     = "1.10";
		propertyValue[24]     = "No";
		propertyValue[25]     = String.format("%-g", genVars.kVARating);
		propertyValue[26]     = String.format("%-g", genVars.kVARating * 0.001);
		propertyValue[27]     = String.format("%-g", genVars.puXd);
		propertyValue[28]     = String.format("%-g", genVars.puXdp);
		propertyValue[29]     = String.format("%-g", genVars.puXdpp);
		propertyValue[30]     = String.format("%-g", genVars.HMass);
		propertyValue[31]     = String.format("%-g", genVars.Dpu);
		propertyValue[32]     = "";
		propertyValue[33]     = "";
		propertyValue[34]     = "";
		propertyValue[35]     = "";

		super.initPropertyValues(Generator.NumPropsThisClass);
	}

	/**
	 * Support for dynamics mode.
	 */

	@Override
	public void initStateVars() {
		//Complex VNeut;
		Complex Edp = null;
		int i;
		Complex[] V012 = new Complex[2];  // TODO Check zero based indexing
		Complex[] I012 = new Complex[2];
		Complex[] Vabc = new Complex[3];

		setYPrimInvalid(true);  // force rebuild of YPrims

		Yeq = ComplexUtil.invert(new Complex(0.0, genVars.Xdp));

		/* Compute nominal positive sequence voltage behind transient reactance */

		if (genOn) {
			SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

			computeITerminal();

			switch (nPhases) {
			case 1:
				Edp      = sol.getNodeV()[nodeRef[0]].subtract( sol.getNodeV()[nodeRef[1]] ).subtract( getITerminal()[0].multiply(new Complex(0.0, genVars.Xdp)) );
				VThevMag = Edp.abs();

				break;
			case 3:
				// calculate Edp based on pos seq only
				MathUtil.phase2SymComp(getITerminal(), I012);
				// voltage behind Xdp  (transient reactance), volts

				for (i = 0; i < nPhases; i++)
					Vabc[i] = sol.getNodeV()[nodeRef[i]];  // wye voltage
				MathUtil.phase2SymComp(Vabc, V012);
				Edp      = V012[0].subtract( I012[0].multiply(new Complex(0.0, genVars.Xdp)) );  // pos sequence
				VThevMag = Edp.abs();
				break;
			default:
				DSSGlobals.doSimpleMsg(String.format("Dynamics mode is implemented only for 1- or 3-phase generators. Generator."+getName()+" has %d phases.", nPhases), 5672);
				DSSGlobals.solutionAbort = true;
				break;
			}


			// shaft variables
			// theta is angle on Vthev[1] relative to system reference
			//Theta  = Vthev[0].getArgument();  // assume source at 0
			genVars.theta  = Edp.getArgument() ;
			genVars.dTheta = 0.0;
			genVars.w0     = DSSGlobals.TWO_PI * sol.getFrequency();
			// recalc mMass and D in case the frequency has changed
			genVars.MMass = 2.0 * genVars.HMass * genVars.kVARating * 1000.0 / genVars.w0;   // M = W-sec
			genVars.D = genVars.Dpu * genVars.kVARating * 1000.0 / genVars.w0;

			genVars.PShaft = -getPower(0).getReal();  // initialize pShaft to present power output

			genVars.speed  = 0.0;  // relative to synch speed
			genVars.dSpeed = 0.0;

			// init user-written models
			//int nCond; Complex[] V, I; double X, Pshaft, Theta, Speed, dt, time;
			if (genModel == 6) {
				if (userModel.exists())
					userModel.init(VTerminal, ITerminal);
				if (shaftModel.exists())
					shaftModel.init(VTerminal, ITerminal);
			}

		} else {
			VThev  = Complex.ZERO;
			genVars.theta  = 0.0;
			genVars.dTheta = 0.0;
			genVars.w0     = 0;
			genVars.speed  = 0.0;
			genVars.dSpeed = 0.0;
		}
	}

	@Override
	public void integrateStates() {
		Complex tracePower;

		// compute derivatives and then integrate

		computeITerminal();

		// check for user-written exciter model
		//function(Complex[] V, Complex[] I, double Pshaft, double Theta, double Speed, double dt, double time)
		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

		if (sol.getDynaVars().iterationFlag == 0){  // first iteration of new time step
			genVars.thetaHistory = genVars.theta + 0.5 * sol.getDynaVars().h * genVars.dTheta;
			genVars.speedHistory = genVars.speed + 0.5 * sol.getDynaVars().h * genVars.dSpeed;
		}

		// compute shaft dynamics
		tracePower = MathUtil.terminalPowerIn(VTerminal, ITerminal, nPhases);
		genVars.dSpeed = (genVars.PShaft + tracePower.getReal() - genVars.D * genVars.speed) / genVars.MMass;
		//GenVars.dSpeed = (GenVars.Torque + terminalPowerIn(Vtemp, Itemp, nPhases).getReal() / GenVars.Speed) / (GenVars.Mmass);
		genVars.dTheta  = genVars.speed;

		// trapezoidal method
		genVars.speed = genVars.speedHistory + 0.5 * sol.getDynaVars().h * genVars.dSpeed;
		genVars.theta = genVars.thetaHistory + 0.5 * sol.getDynaVars().h * genVars.dTheta;

		// write dynamics trace record
		if (debugTrace) {
			try {
				FileWriter TraceStream = new FileWriter(traceFile, true);
				BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);
				TraceBuffer.write(String.format("t=%-.5g ", sol.getDynaVars().t));
				TraceBuffer.write(String.format(" Flag=%d ", sol.getDynaVars().iterationFlag));
				TraceBuffer.write(String.format(" Speed=%-.5g ", genVars.speed));
				TraceBuffer.write(String.format(" dSpeed=%-.5g ", genVars.dSpeed));
				TraceBuffer.write(String.format(" Pshaft=%-.5g ", genVars.PShaft));
				TraceBuffer.write(String.format(" P=%-.5g Q= %-.5g", tracePower.getReal(), tracePower.getImaginary()));
				TraceBuffer.write(String.format(" M=%-.5g ", genVars.MMass));
				TraceBuffer.newLine();
				TraceBuffer.close();
				TraceStream.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}

		if (genModel == 6) {
			if (userModel.exists()) userModel.integrate();
			if (shaftModel.exists()) shaftModel.integrate();
		}
	}

	/**
	 * Return variables one at a time.
	 */
	@Override
	public double getVariable(int i) {
		int n, k;

		n = 0;
		double result = -9999.99;  // error return value

		if (i < 0) return result;

		switch (i) {
		case 0:
			result = (genVars.w0 + genVars.speed) / DSSGlobals.TWO_PI;  // frequency, Hz
			break;
		case 1:
			result = genVars.theta * DSSGlobals.RADIANS_TO_DEGREES;  // report in deg
			break;
		case 2:
			result = VThev.abs() / VBase;  // report in pu
			break;
		case 3:
			result = genVars.PShaft;
			break;
		case 4:
			result = genVars.dSpeed * DSSGlobals.RADIANS_TO_DEGREES;  // report in deg
			break;
		case 5:
			result = genVars.dTheta;
			break;
		default:
			if (userModel.exists()) {
				n = userModel.numVars();
				k = (i - NumGenVariables);
				if (k <= n)
					return userModel.getVariable(k);
			}

			/* If we get here, must be in the shaft model if anywhere */
			if (shaftModel.exists()) {
				k = i - (NumGenVariables + n);
				if (k > 0)
					return shaftModel.getVariable(k);
			}
			break;
		}

		return result;
	}

	@Override
	public void setVariable(int i, double value) {
		int n, k;

		n = 0;
		if (i < 0) return;
		switch (i) {
		case 0:
			genVars.speed = (value - genVars.w0) * DSSGlobals.TWO_PI;
			break;
		case 1:
			genVars.theta = value / DSSGlobals.RADIANS_TO_DEGREES;  // deg to rad
			break;
		case 2:
			// meaningless to set Vd = Value * vbase; // pu to volts
			break;
		case 3:
			genVars.PShaft = value;
			break;
		case 4:
			genVars.dSpeed = value / DSSGlobals.RADIANS_TO_DEGREES;
			break;
		case 5:
			genVars.dTheta = value ;
			break;
		default:
			if (userModel.exists()) {
				n = userModel.numVars();
				k = (i - NumGenVariables) ;
				if (k <= n) {
					userModel.setVariable(k, value);
					return;
				}
			}

			// if we get here, must be in the shaft model
			if (shaftModel.exists()) {
				k = (i - (NumGenVariables + n)) ;
				if (k > 0)
					shaftModel.setVariable(k, value);
			}
			break;
		}
	}

	@Override
	public void getAllVariables(double[] states) {
		int i, n;
		n = 0;

		for (i = 0; i < NumGenVariables; i++)
			states[i] = getVariable(i);

		if (userModel.exists()) {
			n = userModel.numVars();
			userModel.getAllVars(states[NumGenVariables + 1]);
		}

		if (shaftModel.exists())
			shaftModel.getAllVars(states[NumGenVariables + 1 + n]);
	}

	@Override
	public int numVariables() {
		int result = NumGenVariables;
		if (userModel.exists())
			result = result + userModel.numVars();
		if (shaftModel.exists())
			result = result + shaftModel.numVars();
		return result;
	}

	@Override
	public String variableName(int i) {
		final int buffSize = 255;

		int n, i2;
		//char[] Buff = new char[BuffSize];
		int pName;
		String result = "";

		n = 0;
		if (i < 0) return result;
		switch (i) {
		case 0:
			result = "Frequency";
			break;
		case 1:
			result = "Theta (Deg)";
			break;
		case 2:
			result = "Vd";
			break;
		case 3:
			result = "PShaft";
			break;
		case 4:
			result = "dSpeed (Deg/sec)";
			break;
		case 5:
			result = "dTheta (Deg)";
			break;
		default:
			if (userModel.exists()) {
				pName = 0;
				n = userModel.numVars();
				i2 = i - NumGenVariables;
				if (i2 <= n) {
					userModel.getVarName(i2, pName, buffSize);
					return String.valueOf(pName);
				}
			}

			if (shaftModel.exists()) {
				pName = 0;
				i2 = i - NumGenVariables - n;
				if (i2 >= 0)
					userModel.getVarName(i2, pName, buffSize);
				result = String.valueOf(pName);
			}
			break;
		}

		return result;
	}

	@Override
	public String getPropertyValue(int index) {
		String result = "";

		switch (index) {
		case 2:
			result = String.format("%.6g", genVars.kVGeneratorBase);
			break;
		case 3:
			result = String.format("%.6g", kWBase);
			break;
		case 4:
			result = String.format("%.6g", PFNominal);
			break;
		case 6:
			result = yearlyShape;
			break;
		case 7:
			result = dailyDispShape;
			break;
		case 8:
			result = dutyShape;
			break;
		case 12:
			result = String.format("%.6g", kVArBase);
			break;
		case 18:
			result = String.format("%.6g", kVArMax);
			break;
		case 19:
			result = String.format("%.6g", kVArMin);
			break;
		case 25:
			result = String.format("%.6g", genVars.kVARating);
			break;
		case 26:
			result = String.format("%.6g", genVars.kVARating * 0.001);
			break;
		case 33:
			result = "(" + super.getPropertyValue(index) + ")";
			break;
		case 35:
			result = "(" + super.getPropertyValue(index) + ")";
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}

		return result;
	}

	/**
	 * Make a positive sequence model
	 */
	@Override
	public void makePosSequence() {
		String s;
		double V;

		s = "Phases=1 conn=wye";

		// make sure voltage is line-neutral
		if ((nPhases > 1) || (connection != 0)) {
			V =  genVars.kVGeneratorBase / DSSGlobals.SQRT3;
		} else {
			V =  genVars.kVGeneratorBase;
		}

		s = s + String.format(" kV=%-.5g", V);

		// divide the load by no. phases
		if (nPhases > 1) {
			s = s + String.format(" kW=%-.5g  PF=%-.5g", kWBase / nPhases, PFNominal);
			if ((prpSequence[18] != 0) || (prpSequence[19] != 0))
				s = s + String.format(" maxkvar=%-.5g  minkvar=%-.5g", kVArMax / nPhases, kVArMin / nPhases);
			if (prpSequence[25] > 0)
				s = s + String.format(" kva=%-.5g  ", genVars.kVARating / nPhases);
			if (prpSequence[26] > 0)
				s = s + String.format(" MVA=%-.5g  ", genVars.kVARating / 1000.0 / nPhases);
		}

		Parser.getInstance().setCmdString(s);
		edit();

		super.makePosSequence();
	}

	@Override
	public void setConductorClosed(int index, boolean value) {
		super.setConductorClosed(index, value);

		// just turn generator on or off

		if (value) {
			genSwitchOpen = false;
		} else {
			genSwitchOpen = true;
		}
	}

	public void setPowerFactor(double value) {
		PFNominal = value;
		syncUpPowerQuantities();
	}

	public void setPresentKV(double value) {
		genVars.kVGeneratorBase = value;

		switch (nPhases) {
		case 2:
			VBase = genVars.kVGeneratorBase * DSSGlobals.InvSQRT3x1000;
			break;
		case 3:
			VBase = genVars.kVGeneratorBase * DSSGlobals.InvSQRT3x1000;
			break;
		default:
			VBase = genVars.kVGeneratorBase * 1000.0 ;
			break;
		}
	}

	public void setPresentKVAr(double value) {
		double kVA_Gen;

		kVArBase = value;
		genVars.QNominalPerPhase = 1000.0 * kVArBase  / nPhases; // init to something reasonable
		kVA_Gen = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kVArBase, 2));
		if (kVA_Gen != 0.0) {
			setPowerFactor(kWBase / kVA_Gen);
		} else {
			setPowerFactor(1.0);
		}

		if ((kWBase * kVArBase) < 0.0)
			setPowerFactor(-PFNominal);

		kVArMax  = 2.0 * kVArBase;
		kVArMin  = -kVArMax;
	}

	public void setPresentKW(double value) {
		kWBase = value;
		syncUpPowerQuantities();
	}

	// FIXME Private method in OpenDSS
	public void syncUpPowerQuantities() {
		// keep kvar nominal up to date with kW and PF
		if (PFNominal != 0.0) {
			kVArBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			genVars.QNominalPerPhase = 1000.0 * kVArBase / nPhases;
			kVArMax = 2.0 * kVArBase;
			kVArMin = -kVArMax;
			if (PFNominal < 0.0)
				kVArBase = -kVArBase;

			if (kVANotSet)
				genVars.kVARating = kWBase * 1.2;
		}
	}

	private void setDragHandRegister(int reg, double value) {
		if (value > registers[reg])
			registers[reg] = value;
	}

	// FIXME Private method in OpenDSS
	public void setKwKVAr(double PkW, double QkVar) {
		setKWBase(PkW);
		setPresentKVAr(QkVar);
	}

	/**
	 * 3-phase Voltage behind transient reactance.
	 */
	private void calcVThevDyn() {
		if (genSwitchOpen)
			VThevMag = 0.0;
		VThev = ComplexUtil.pclx(VThevMag, genVars.theta);
	}

	public boolean isForcedOn() {
		return forcedON;
	}

	public void setForcedOn(boolean forced) {
		forcedON = forced;
	}

	public double getPowerFactor() {
		return PFNominal;
	}

	public int getConnection() {
		return connection;
	}

	public void setConnection(int conn) {
		connection = conn;
	}

	public String getDailyDispShape() {
		return dailyDispShape;
	}

	public void setDailyDispShape(String shape) {
		dailyDispShape = shape;
	}

	public LoadShapeObj getDailyDispShapeObj() {
		return dailyDispShapeObj;
	}

	public void setDailyDispShapeObj(LoadShapeObj shapeObj) {
		dailyDispShapeObj = shapeObj;
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

	public int getGenClass() {
		return genClass;
	}

	public void setGenClass(int cls) {
		genClass = cls;
	}

	public int getGenModel() {
		return genModel;
	}

	public void setGenModel(int model) {
		genModel = model;
	}

	public GeneratorVars getGenVars() {
		return genVars;
	}

	public void setGenVars(GeneratorVars vars) {
		genVars = vars;
	}

	public double getKVArBase() {
		return kVArBase;
	}

	public void setKVArBase(double base) {
		this.kVArBase = base;
	}

	public double getKVArMax() {
		return kVArMax;
	}

	public void setKVArMax(double max) {
		kVArMax = max;
	}

	public double getKVArMin() {
		return kVArMin;
	}

	public void setKVArMin(double min) {
		kVArMin = min;
	}

	public double getKWBase() {
		return kWBase;
	}

	public void setKWBase(double base) {
		kWBase = base;
	}

	public double getVpu() {
		return Vpu;
	}

	public void setVpu(double vpu) {
		Vpu = vpu;
	}

	public double getVTarget() {
		return VTarget;
	}

	public void setVTarget(double target) {
		VTarget = target;
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

	public double[] getRegisters() {
		return registers;
	}

	public void setRegisters(double[] values) {
		registers = values;
	}

	public double[] getDerivatives() {
		return derivatives;
	}

	public void setDerivatives(double[] values) {
		derivatives = values;
	}

	// FIXME Private members in OpenDSS

	public Complex getYeq() {
		return Yeq;
	}

	public void setYeq(Complex yeq) {
		Yeq = yeq;
	}

	public Complex getYeq95() {
		return Yeq95;
	}

	public void setYeq95(Complex yeq95) {
		Yeq95 = yeq95;
	}

	public Complex getYeq105() {
		return Yeq105;
	}

	public void setYeq105(Complex yeq105) {
		Yeq105 = yeq105;
	}

	public Complex getCurrentLimit() {
		return currentLimit;
	}

	public void setCurrentLimit(Complex limit) {
		currentLimit = limit;
	}

	public boolean isDebugTrace() {
		return debugTrace;
	}

	public void setDebugTrace(boolean debug) {
		debugTrace = debug;
	}

	public double getDeltaQMax() {
		return deltaQMax;
	}

	public void setDeltaQMax(double delta) {
		deltaQMax = delta;
	}

	public int getDispatchMode() {
		return dispatchMode;
	}

	public void setDispatchMode(int mode) {
		dispatchMode = mode;
	}

	public double getDispatchValue() {
		return dispatchValue;
	}

	public void setDispatchValue(double value) {
		dispatchValue = value;
	}

	public double getDQDV() {
		return dQdV;
	}

	public void setDQDV(double value) {
		this.dQdV = value;
	}

	public double getDQDVSaved() {
		return dQdVSaved;
	}

	public void setDQDVSaved(double value) {
		this.dQdVSaved = value;
	}

	public boolean isFirstSampleAfterReset() {
		return firstSampleAfterReset;
	}

	public void setFirstSampleAfterReset(boolean firstSample) {
		firstSampleAfterReset = firstSample;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean value) {
		fixed = value;
	}

	public int getGeneratorSolutionCount() {
		return generatorSolutionCount;
	}

	public void setGeneratorSolutionCount(int count) {
		generatorSolutionCount = count;
	}

	public double getGenFundamental() {
		return genFundamental;
	}

	public void setGenFundamental(double fundamental) {
		genFundamental = fundamental;
	}

	public boolean isGenOn() {
		return genOn;
	}

	public void setGenOn(boolean on) {
		genOn = on;
	}

	public boolean isGenSwitchOpen() {
		return genSwitchOpen;
	}

	public void setGenSwitchOpen(boolean open) {
		genSwitchOpen = open;
	}

	public boolean iskVANotSet() {
		return kVANotSet;
	}

	public void setkVANotSet(boolean value) {
		this.kVANotSet = value;
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

	public int getOpenGeneratorSolutionCount() {
		return openGeneratorSolutionCount;
	}

	public void setOpenGeneratorSolutionCount(int count) {
		openGeneratorSolutionCount = count;
	}

	public double getPVFactor() {
		return PVFactor;
	}

	public void setPVFactor(double factor) {
		PVFactor = factor;
	}

	public double getRandomMult() {
		return randomMult;
	}

	public void setRandomMult(double mult) {
		randomMult = mult;
	}

	public int getRegHours() {
		return regHours;
	}

	public void setRegHours(int hours) {
		regHours = hours;
	}

	public int getRegKVArh() {
		return regKVArh;
	}

	public void setRegKVArh(int kvarh) {
		regKVArh = kvarh;
	}

	public int getRegKWh() {
		return regKWh;
	}

	public void setRegKWh(int value) {
		regKWh = value;
	}

	public int getRegMaxKVA() {
		return regMaxKVA;
	}

	public void setReg_MaxkVA(int maxKVA) {
		regMaxKVA = maxKVA;
	}

	public int getReg_MaxkW() {
		return regMaxKW;
	}

	public void setRegMaxKW(int maxKW) {
		regMaxKW = maxKW;
	}

	public int getRegPrice() {
		return regPrice;
	}

	public void setRegPrice(int price) {
		regPrice = price;
	}

	public Complex getShapeFactor() {
		return shapeFactor;
	}

	public void setShapeFactor(Complex factor) {
		shapeFactor = factor;
	}

	public double getThetaHarm() {
		return thetaHarm;
	}

	public void setThetaHarm(double theta) {
		thetaHarm = theta;
	}

	public File getTraceFile() {
		return traceFile;
	}

	public void setTraceFile(File file) {
		traceFile = file;
	}

	public double getV_Avg() {
		return VAvg;
	}

	public void setV_Avg(double value) {
		VAvg = value;
	}

	public double getVRemembered() {
		return VRemembered;
	}

	public void setVRemembered(double value) {
		VRemembered = value;
	}

	public double getVArRemembered() {
		return varRemembered;
	}

	public void setVArRemembered(double value) {
		this.varRemembered = value;
	}

	public double getVArBase() {
		return varBase;
	}

	public void setVArBase(double base) {
		this.varBase = base;
	}

	public double getVArMax() {
		return varMax;
	}

	public void setVArMax(double max) {
		this.varMax = max;
	}

	public double getVArMin() {
		return varMin;
	}

	public void setVArMin(double min) {
		this.varMin = min;
	}

	public double getVBase() {
		return VBase;
	}

	public void setVBase(double value) {
		VBase = value;
	}

	public double getVBase105() {
		return VBase105;
	}

	public void setVBase105(double value) {
		VBase105 = value;
	}

	public double getVBase95() {
		return VBase95;
	}

	public void setVBase95(double value) {
		VBase95 = value;
	}

	public double getVMaxPU() {
		return VMaxPU;
	}

	public void setVMaxPU(double value) {
		VMaxPU = value;
	}

	public double getVMinPU() {
		return VMinPU;
	}

	public void setVMinPU(double value) {
		VMinPU = value;
	}

	public Complex getVThev() {
		return VThev;
	}

	public void setVThev(Complex value) {
		VThev = value;
	}

	public double getVThevHarm() {
		return VThevHarm;
	}

	public void setVThevHarm(double value) {
		VThevHarm = value;
	}

	public double getVThevMag() {
		return VThevMag;
	}

	public void setVThevMag(double mag) {
		VThevMag = mag;
	}

	public ComplexMatrix getYPrimOpenCond() {
		return YPrimOpenCond;
	}

	public void setYPrimOpenCond(ComplexMatrix value) {
		YPrimOpenCond = value;
	}

	public double getYQFixed() {
		return YQFixed;
	}

	public void setYQFixed(double value) {
		YQFixed = value;
	}

	public boolean isShapeIsActual() {
		return shapeIsActual;
	}

	public void setShapeIsActual(boolean model) {
		shapeIsActual = model;
	}

	public GenUserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(GenUserModel model) {
		userModel = model;
	}

	public GenUserModel getShaftModel() {
		return shaftModel;
	}

	public void setShaftModel(GenUserModel model) {
		shaftModel = model;
	}

}
