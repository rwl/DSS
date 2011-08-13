package com.epri.dss.conversion.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
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
import com.epri.dss.shared.CMatrix;
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

	private Complex CurrentLimit;
	private boolean DebugTrace;
	/** Max allowable var change on Model=3 per iteration */
	private double DeltaQMax;
	private int DispatchMode;
	private double DispatchValue;
	private double dQdV;
	private double dQdVSaved;
	private boolean ForcedON;
	private boolean FirstSampleAfterReset;
	private boolean Fixed;   // if fixed, always at base value
	private int GeneratorSolutionCount;
	/** Thevenin equivalent voltage mag and angle reference for harmonic model. */
	private double GenFundamental;
	/** Indicates whether generator is currently on. */
	private boolean GenON;
	private boolean GenSwitchOpen;
	private boolean kVANotSet;
	private double LastGrowthFactor;
	/** Added for speedup so we don't have to search for growth factor a lot. */
	private int LastYear;
	private int OpenGeneratorSolutionCount;
	/** Deceleration factor for computing vars for PV generators. */
	private double PVFactor;
	private double RandomMult;
	private int Reg_Hours;
	private int Reg_kvarh;
	private int Reg_kWh;
	private int Reg_MaxkVA;
	private int Reg_MaxkW;
	private int Reg_Price;
	private Complex ShapeFactor;
	/** Thevinen equivalent voltage mag and angle reference for harmonic model. */
	private double ThetaHarm;
	private File TraceFile;
	/** User-written models. */
	private GenUserModel UserModel, ShaftModel;
	private double V_Avg;
	private double V_Remembered;
	private double var_Remembered;
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
	private Complex Vthev;
	/** Thevinen equivalent voltage mag and angle reference for harmonic model. */
	private double VThevHarm;
	/** Thevinen equivalent voltage for dynamic model. */
	private double VThevMag;
	/**
	 * To handle cases where one conductor of load is open;
	 * We revert to admittance for inj currents.
	 */
	private CMatrix YPrimOpenCond;
	/** Fixed value of y for type 7 load. */
	private double YQFixed;
	private boolean ShapeIsActual;

	/** 0 = line-neutral; 1 = Delta */
	protected int Connection;
	/** Daily (24 HR) generator shape. */
	protected String DailyDispShape;
	/** Daily Generator Shape for this load. */
	protected LoadShapeObj DailyDispShapeObj;
	/** Duty cycle load shape for changes typically less than one hour. */
	protected String DutyShape;
	/** Shape for this generator. */
	protected LoadShapeObj DutyShapeObj;
	protected int GenClass;
	/** Variation with voltage. */
	protected int GenModel;
	/** State variables. */
	protected GeneratorVars GenVars;
	protected double kvarBase;
	protected double kvarMax;
	protected double kvarMin;
	protected double kWBase;
	protected double PFNominal;
	/** Per unit target voltage for generator with voltage control. */
	protected double Vpu;
	/** Target voltage for generator with voltage control. */
	protected double VTarget;
	/** ='fixed' means no variation  on all the time. */
	protected String YearlyShape;
	/** Shape for this generator. */
	protected LoadShapeObj YearlyShapeObj;

	protected double[] Registers = new double[NumGenRegisters];
	protected double[] Derivatives = new double[NumGenRegisters];

	public GeneratorObjImpl(DSSClassImpl ParClass, String GeneratorName) {
		super(ParClass);
		setName(GeneratorName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType(); // + GEN_ELEMENT;  // in both PC element and gen element list

		setNPhases(3);
		this.nConds = 4;   // defaults to wye
		this.YOrder  = 0;  // to trigger an initial allocation
		setNTerms(1);      // forces allocations
		this.kWBase  = 1000.0;
		this.kvarBase = 60.0;

		this.kvarMax   = this.kvarBase * 2.0;
		this.kvarMin   = -this.kvarMax;
		this.PFNominal = 0.88;
		//this.Rneut     = 0.0;
		//this.Xneut     = 0.0;
		this.YearlyShape    = "";
		this.YearlyShapeObj = null;     // if YearlyShapeObj = null then the load alway stays nominal * global multipliers
		this.DailyDispShape = "";
		this.DailyDispShapeObj = null;  // if DaillyShapeObj = null then the load alway stays nominal * global multipliers
		this.DutyShape         = "";
		this.DutyShapeObj      = null;  // if DutyShapeObj = null then the load alway stays nominal * global multipliers
		this.Connection        = 0;     // Wye (star)
		this.GenModel          = 1;  /* Typical fixed kW negative load */
		this.GenClass          = 1;
		this.LastYear          = 0;
		this.LastGrowthFactor  = 1.0;

		this.dQdVSaved = 0.0;  // initialize this here; allows generators to be turned off and on


		this.GeneratorSolutionCount     = -1;  // for keep track of the present solution in injcurrent calcs
		this.OpenGeneratorSolutionCount = -1;
		this.YPrimOpenCond              = null;

		this.GenVars.kVGeneratorBase  = 12.47;
		this.Vpu              = 1.0;
		this.VTarget          = 1000.0 * this.Vpu * this.GenVars.kVGeneratorBase / DSSGlobals.SQRT3;  /* Line-to-Neutral target */
		this.VBase            = 7200.0;
		this.VMinPU           = 0.90;
		this.VMaxPU           = 1.10;
		this.VBase95          = this.VMinPU * this.VBase;
		this.VBase105         = this.VMaxPU * this.VBase;
		this.YOrder           = this.nTerms * this.nConds;
		this.RandomMult       = 1.0 ;
		this.Fixed            = false;

		/* Machine rating stuff */
		this.GenVars.kVArating  = this.kWBase * 1.2;
		this.kVANotSet = true;  // flag for default value for kVA

		//this.GenVars.Vd = 7200.0;

		this.GenVars.puXd       = 1.0;
		this.GenVars.puXdp      = 0.28;
		this.GenVars.puXdpp     = 0.20;
		this.GenVars.Xd         = this.GenVars.puXd * Math.pow(this.GenVars.kVGeneratorBase, 2) * 1000.0 / this.GenVars.kVArating;
		this.GenVars.Xdp        = this.GenVars.puXdp * Math.pow(this.GenVars.kVGeneratorBase, 2) * 1000.0 / this.GenVars.kVArating;
		this.GenVars.Xdpp       = this.GenVars.puXdpp * Math.pow(this.GenVars.kVGeneratorBase, 2) * 1000.0 / this.GenVars.kVArating;
		this.GenVars.Hmass      = 1.0;  // W-sec/VA rating
		this.GenVars.Theta      = 0.0;
		this.GenVars.w0         = DSSGlobals.TWO_PI * getBaseFrequency();
		this.GenVars.Speed      = 0.0;
		this.GenVars.dSpeed     = 0.0;
		this.GenVars.D          = 1.0;

		this.UserModel  = new GenUserModelImpl(this.GenVars) ;
		this.ShaftModel = new GenUserModelImpl(this.GenVars);

		this.DispatchValue = 0.0;  // follow curves

		this.Reg_kWh    = 1;
		this.Reg_kvarh  = 2;
		this.Reg_MaxkW  = 3;
		this.Reg_MaxkVA = 4;
		this.Reg_Hours  = 5;
		this.Reg_Price  = 6;

		this.PVFactor      = 0.1;
		this.DebugTrace    = false;
		this.ForcedON     = false;
		this.GenSwitchOpen = false;
		this.ShapeIsActual = false;

		this.Spectrum = "defaultgen";  // override base class

		initPropertyValues(0);

		recalcElementData();
	}

	/**
	 * 0 = reset to 1.0; 1 = Gaussian around mean and std Dev; 2 = uniform
	 */
	public void randomize(int Opt) {
		switch (Opt) {
		case 0:
			RandomMult = 1.0;
			break;
		case DSSGlobals.GAUSSIAN:
			RandomMult = MathUtil.gauss(YearlyShapeObj.getMean(), YearlyShapeObj.getStdDev());
			break;
		case DSSGlobals.UNIFORM:
			RandomMult = Math.random();  // number between 0 and 1.0
			break;
		case DSSGlobals.LOGNORMAL:
			RandomMult = MathUtil.quasiLognormal(YearlyShapeObj.getMean());
			break;
		}
	}

	private void calcDailyMult(double Hr) {
		if (DailyDispShapeObj != null) {
			ShapeFactor = DailyDispShapeObj.getMult(Hr);
			ShapeIsActual = DailyDispShapeObj.isUseActual();
		} else {
			ShapeFactor = CDOUBLEONE;  // default to no daily variation
		}
	}

	private void calcDutyMult(double Hr) {
		if (DutyShapeObj != null) {
			ShapeFactor = DutyShapeObj.getMult(Hr);
			ShapeIsActual = DutyShapeObj.isUseActual();
		} else {
			calcDailyMult(Hr);  // default to daily mult if no duty curve specified
		}
	}

	private void calcYearlyMult(double Hr) {
		/* Yearly curve is assumed to be hourly only */
		if (YearlyShapeObj != null) {
			ShapeFactor = YearlyShapeObj.getMult(Hr);
			ShapeIsActual = YearlyShapeObj.isUseActual();
		} else {
			ShapeFactor = CDOUBLEONE;  // defaults to no variation
		}
	}

	public void setNominalGeneration() {
		double Factor;
		boolean GenOn_Saved;

		GenOn_Saved = GenON;
		ShapeFactor = CDOUBLEONE;
		// check to make sure the generation is on
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		if (!sol.isDynamicModel() || !sol.isHarmonicModel()) {  // leave generator in whatever state it was prior to entering dynamic mode
			GenON = true;   // init to on then check if it should be off
			if (!ForcedON)
				switch (DispatchMode) {
				case Generator.LOADMODE:
					if ((DispatchValue > 0.0) && (ckt.getGeneratorDispatchReference() < DispatchValue))
						GenON = false;
					break;
				case Generator.PRICEMODE:
					if ((DispatchValue > 0.0) && (ckt.getPriceSignal() < DispatchValue))
						GenON = false;
					break;
				}
		}


		if (!GenON) {
			// if generator is off enter as tiny resistive load (.0001 pu) so we don't get divide by zero in matrix
			GenVars.Pnominalperphase = -0.1 * kWBase / nPhases;
			// Pnominalperphase   = 0.0;
			GenVars.Qnominalperphase = 0.0;
		} else {
			// generator is on, compute it's nominal watts and vars

			if (isFixed()) {
				Factor = 1.0;  // for fixed generators, set constant
			} else {
				switch (sol.getMode()) {
				case Dynamics.SNAPSHOT:
					Factor = ckt.getGenMultiplier() * 1.0;
					break;
				case Dynamics.DAILYMODE:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());  // daily dispatch curve
					break;
				case Dynamics.YEARLYMODE:
					Factor = ckt.getGenMultiplier();
					calcYearlyMult(sol.getDblHour());
					break;
				case Dynamics.DUTYCYCLE:
					Factor = ckt.getGenMultiplier();
					calcDutyMult(sol.getDblHour());
					break;
				case Dynamics.GENERALTIME:  // general sequential time simulation
					Factor = ckt.getGenMultiplier();
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
						ShapeFactor = new Complex(1.0, 1.0);  // default to 1 + j1 if not known
						break;
					}
					break;
				case Dynamics.MONTECARLO1:
					Factor = ckt.getGenMultiplier() * 1.0;
					break;
				case Dynamics.MONTEFAULT:
					Factor = ckt.getGenMultiplier() * 1.0;
					break;
				case Dynamics.FAULTSTUDY:
					Factor = ckt.getGenMultiplier() * 1.0;
					break;
				case Dynamics.DYNAMICMODE:
					Factor = ckt.getGenMultiplier() * 1.0;
					break;
				case Dynamics.MONTECARLO2:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
					break;
				case Dynamics.MONTECARLO3:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
					break;
				case Dynamics.LOADDURATION1:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
					break;
				case Dynamics.LOADDURATION2:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
					break;
				case Dynamics.PEAKDAY:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
					break;
				case Dynamics.AUTOADDFLAG:
					Factor = 1.0;
					break;
				default:
					Factor = 1.0;
					break;
				}
			}

			if (!sol.isDynamicModel() || sol.isHarmonicModel()) {
				if (ShapeIsActual) {
					GenVars.Pnominalperphase = 1000.0 * ShapeFactor.getReal() / nPhases;
				} else {
					GenVars.Pnominalperphase = 1000.0 * kWBase * Factor * ShapeFactor.getReal() / nPhases;
				}


				if (GenModel == 3) {
					/* Just make sure present value is reasonable. */
					if (GenVars.Qnominalperphase > varMax) {
						GenVars.Qnominalperphase = varMax;
					} else if (GenVars.Qnominalperphase < varMin) {
						GenVars.Qnominalperphase = varMin;
					}
				} else {
					/* for other generator models */
					if (ShapeIsActual) {
						GenVars.Qnominalperphase = 1000.0 * ShapeFactor.getImaginary() / nPhases;
					} else {
						GenVars.Qnominalperphase = 1000.0 * kvarBase * Factor * ShapeFactor.getImaginary() / nPhases;
					}
				}
			}
		}  /* else genON */

		if (!sol.isDynamicModel() || sol.isHarmonicModel()) {

			switch (GenModel) {
			case 6:
				Yeq = new Complex(0.0, -GenVars.Xd).invert();  // gets negated in calcYPrim
				break;
			default:
				Yeq = new Complex(GenVars.Pnominalperphase, -GenVars.Qnominalperphase).divide(Math.pow(VBase, 2));  // VBase must be L-N for 3-phase
				if (VMinPU != 0.0) {
					Yeq95 = Yeq.divide(Math.pow(VMinPU, 2));  // at 95% voltage
				} else {
					Yeq95 = Yeq;  // always a constant Z model
				}

				if (VMaxPU != 0.0) {
					Yeq105 = Yeq.divide(Math.pow(VMaxPU, 2));  // at 105% voltage
				} else {
					Yeq105 = Yeq;
				}
				break;
			}

			/* When we leave here, all the Yeq's are in L-N values */
			if (GenModel == 7)
				CurrentLimit = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(VBase95);
		}

		// if generator state changes, force re-calc of Y matrix
		if (GenON != GenOn_Saved)
			setYPrimInvalid(true);
	}

	@Override
	public void recalcElementData() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		VBase95  = VMinPU * VBase;
		VBase105 = VMaxPU * VBase;

		varBase = 1000.0 * kvarBase / nPhases;
		varMin  = 1000.0 * kvarMin  / nPhases;
		varMax  = 1000.0 * kvarMax  / nPhases;

		/* Populate data structures used for interchange with user-written models. */
		GenVars.Xd    = GenVars.puXd   * 1000.0 * Math.pow(GenVars.kVGeneratorBase, 2) / GenVars.kVArating;
		GenVars.Xdp   = GenVars.puXdp  * 1000.0 * Math.pow(GenVars.kVGeneratorBase, 2) / GenVars.kVArating;
		GenVars.Xdpp  = GenVars.puXdpp * 1000.0 * Math.pow(GenVars.kVGeneratorBase, 2) / GenVars.kVArating;
		GenVars.Conn = Connection;
		GenVars.NumPhases = nPhases;
		GenVars.NumConductors = nConds;

		setNominalGeneration();

		/* Now check for errors. If any of these came out nil and the string was not nil, give warning. */
		if (YearlyShape.equalsIgnoreCase("none"))
			YearlyShape = "";
		if (DailyDispShape.equalsIgnoreCase("none"))
			DailyDispShape = "";
		if (DutyShape.equalsIgnoreCase("none"))
			DutyShape = "";

		if (YearlyShapeObj == null)
			if (YearlyShape.length() > 0)
				Globals.doSimpleMsg("Warning: Yearly load shape: \""+ YearlyShape +"\" not found.", 563);
		if (DailyDispShapeObj == null)
			if (DailyDispShape.length() > 0)
				Globals.doSimpleMsg("Warning: Daily load shape: \""+ DailyDispShape +"\" not found.", 564);
		if (DutyShapeObj == null)
			if (DutyShape.length() > 0)
				Globals.doSimpleMsg("Warning: Duty load shape: \""+ DutyShape +"\" not found.", 565);

		setSpectrumObj( (com.epri.dss.general.SpectrumObj) Globals.getSpectrumClass().find(getSpectrum()) );
		if (getSpectrumObj() == null)
			Globals.doSimpleMsg("Error: Spectrum \""+getSpectrum()+"\" not found.", 566);
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
		VTarget = Vpu * 1000.0 * GenVars.kVGeneratorBase / DSSGlobals.SQRT3;

		// initialize to zero - defaults to PQ generator
		// solution object will reset after circuit modifications
		dQdV      = dQdVSaved;         // for Model = 3
		DeltaQMax = (varMax - varMin) * 0.10;  // limit to 10% of range

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), YOrder) );

		/* Update any user-written models */
		if (UserModel.exists()) UserModel.updateModel();
		if (ShaftModel.exists()) ShaftModel.updateModel();
	}

	private void calcYPrimMatrix(CMatrix Ymatrix) {
		Complex Y , Yij;
		int i, j;
		double FreqMultiplier;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		YPrimFreq = ckt.getSolution().getFrequency();
		FreqMultiplier = YPrimFreq / baseFrequency;

		SolutionObj sol = ckt.getSolution();

		if (sol.isDynamicModel() || sol.isHarmonicModel()) {
			if (GenON) {
				Y  = Yeq;  // L-N value computed in initialization routines
			} else {
				Y = new Complex(DSSGlobals.EPSILON, 0.0);
			}

			if (Connection == 1)
				Y = Y.divide(3.0);  // convert to delta impedance
			Y = new Complex(Y.getReal(), Y.getImaginary() / FreqMultiplier);
			Yij = Y.negate();
			for (i = 0; i < nPhases; i++) {
				switch (Connection) {
				case 0:
					Ymatrix.setElement(i, i, Y);
					Ymatrix.addElement(nConds, nConds, Y);
					Ymatrix.setElemSym(i, nConds, Yij);
					break;
				case 1:  /* Delta connection */
					Ymatrix.setElement(i, i, Y);
					Ymatrix.addElement(i, i, Y);  // put it in again
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						Ymatrix.setElemSym(i, j, Yij);
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
			Y = new Complex(Y.getReal(), Y.getImaginary() / FreqMultiplier);

			switch (Connection) {
			case 0:  // WYE
				Yij = Y.negate();
				for (i = 0; i < nPhases; i++) {
					Ymatrix.setElement(i, i, Y);
					Ymatrix.addElement(nConds, nConds, Y);
					Ymatrix.setElemSym(i, nConds, Yij);
				}
				break;
			case 1:  // delta or L-L
				Y    = Y.divide(3.0);  // convert to delta impedance
				Yij  = Y.negate();
				for (i = 0; i < nPhases; i++) {
					j = i + 1;
					if (j > nConds) j = 0;  // wrap around for closed connections
					Ymatrix.addElement(i, i, Y);
					Ymatrix.addElement(j, j, Y);
					Ymatrix.addElemSym(i, j, Yij);
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
			YPrimShunt = new CMatrixImpl(YOrder);
			if (YPrimSeries != null) YPrimSeries = null;
			YPrimSeries = new CMatrixImpl(YOrder);
			if (YPrim != null) YPrim = null;
			YPrim = new CMatrixImpl(YOrder);
		} else {
			YPrimShunt.clear();
			YPrimSeries.clear();
			YPrim.clear();
		}

		if (DSSGlobals.getInstance().getActiveCircuit().getSolution().getLoadModel() == DSSGlobals.POWERFLOW) {

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
			YPrimSeries.setElement(i, i, YPrimShunt.getElement(i, i).multiply(1.0e-10));

		YPrim.copyFrom(YPrimShunt);

		// account for open conductors
		super.calcYPrim();
	}

	/**
	 * Add the current into the proper location according to connection.
	 *
	 * Reverse of similar routine in load (complex negates are switched).
	 */
	private void stickCurrInTerminalArray(Complex[] TermArray, Complex Curr, int i) {
		switch (Connection) {
		case 0:  // wye
			TermArray[i] = TermArray[i].add(Curr);
			TermArray[nConds] = TermArray[nConds].add(Curr.negate());  // neutral
			break;
		case 1:  // delta
			TermArray[i] = TermArray[i].add(Curr);
			int j = i + 1;
			if (j > nConds) j = 0;
			TermArray[j] = TermArray[j].add(Curr.negate());
			break;
		}
	}

	private void writeTraceRecord(String s) {
		int i;
		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			if (!Globals.isInShowResults()) {
				FileWriter TraceStream = new FileWriter(TraceFile, true);
				BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);
				TraceBuffer.write(String.format("%-.g, %d, %-.g, ",
						Globals.getActiveCircuit().getSolution().getDynaVars().t,
						Globals.getActiveCircuit().getSolution().getIteration(),
						Globals.getActiveCircuit().getLoadMultiplier()) +
						Utilities.getSolutionModeID() + ", " +
						Utilities.getLoadModel() + ", " +
						GenModel + ", " +
						dQdV + ", " +
						(V_Avg * 0.001732 / GenVars.kVGeneratorBase) + ", " +
						(VTarget - V_Avg) + ", " +
						(GenVars.Qnominalperphase * 3.0 / 1.0e6) + ", " +
						(GenVars.Pnominalperphase * 3.0 / 1.0e6) + ", " +
						s + ", ");
				for (i = 0; i < nPhases; i++)
					TraceBuffer.write(getInjCurrent()[i].abs() + ", ");
				for (i = 0; i < nPhases; i++)
					TraceBuffer.write(getITerminal()[i].abs() + ", ");
				for (i = 0; i < nPhases; i++)
					TraceBuffer.write(getVTerminal()[i].abs() + ", ");
				TraceBuffer.write(VThevMag + ", " + GenVars.Theta * 180.0 / Math.PI);
				TraceBuffer.newLine();
				TraceBuffer.close();
				TraceStream.close();
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
		Complex V, Curr = null;
		double Vmag;
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
			Vmag = V.abs();

			switch (Connection) {
			case 0:  /* Wye */
				if (Vmag <= VBase95) {
					Curr = Yeq95.multiply(V);  // below 95% use an impedance model
				} else if (Vmag > VBase105) {
					Curr = Yeq105.multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(V).conjugate();  // between 95% -105%, constant PQ
				}
				break;
			case 1:  /* Delta */
				Vmag = Vmag / DSSGlobals.SQRT3;  // L-N magnitude
				if (Vmag <= VBase95) {
					Curr = Yeq95.divide(3.0).multiply(V);  // below 95% use an impedance model
				} else if (Vmag > VBase105) {
					Curr = Yeq105.divide(3.0).multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(V).conjugate();  // between 95% -105%, constant PQ
				}
				break;
			}

			stickCurrInTerminalArray(getITerminal(), Curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // put into terminal array taking into account connection
		}
	}

	private void doConstantZGen() {
		int i;
		Complex Curr, Yeq2;

		// assume Yeq is kept up to date
		calcYPrimContribution(getInjCurrent());  // init InjCurrent array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();
		if (Connection == 0) {
			Yeq2 = Yeq;
		} else {
			Yeq2 = Yeq.divide(3.0);
		}

		for (i = 0; i < nPhases; i++) {
			Curr = Yeq2.multiply(VTerminal[i]);   // Yeq is always line to neutral
			stickCurrInTerminalArray(getITerminal(), Curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current for Constant P, |V|.
	 */
	private void doPVTypeGen() {
		int i;
		double DQ;
		Complex Curr;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase(); // get actual voltage across each phase of the generator
		zeroITerminal();

		// guess at a new var output value
		V_Avg = 0.0;
		for (i = 0; i < nPhases; i++)
			V_Avg = V_Avg + VTerminal[i].abs();

		if (Connection == 1) {
			V_Avg = V_Avg / (DSSGlobals.SQRT3 * nPhases);
		} else {
			V_Avg = V_Avg / nPhases;
		}

		// 12-9-99 added empirical 0.7 factor to improve iteration
		// 12-17-99 changed to 0.1 because first guess was consistently too high
		DQ =  PVFactor * dQdV * (VTarget - V_Avg);   // vTarget is L-N
		if (Math.abs(DQ) > DeltaQMax)
			if (DQ < 0.0) {
				DQ = -DeltaQMax;
			} else {
				DQ = DeltaQMax;
			}
		GenVars.Qnominalperphase = GenVars.Qnominalperphase + DQ;

		/* Test limits */
		if (GenVars.Qnominalperphase > varMax) {
			GenVars.Qnominalperphase = varMax;
		} else if (GenVars.Qnominalperphase < varMin) {
			GenVars.Qnominalperphase = varMin;
		}

		// compute injection currents using W and var values
		// do not use constant Z models outside normal range
		// presumably the var source will take care of the voltage problems
		for (i = 0; i < nPhases; i++) {
			Curr = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(VTerminal[i]).conjugate();
			stickCurrInTerminalArray(getITerminal(), Curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current for Fixed Q.
	 * Constant P, Fixed Q - Q is always kvarBase.
	 */
	private void doFixedQGen() {
		int i;
		Complex Curr = null, V;
		double Vmag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			Vmag = V.abs();

			switch (Connection) {
			case 0:
				if (Vmag <= VBase95) {
					Curr = new Complex(Yeq95.getReal(), YQFixed).multiply(V);  // below 95% use an impedance model
				} else if (Vmag > VBase105) {
					Curr = new Complex(Yeq105.getReal(), YQFixed).multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, varBase).divide(V).conjugate();
				}
				break;
			case 1:
				Vmag = Vmag / DSSGlobals.SQRT3;  // convert to L-N for test
				if (Vmag <= VBase95) {
					Curr = new Complex(Yeq95.getReal() / 3.0, YQFixed / 3.0).multiply(V);  // below 95% use an impedance model
				} else if(Vmag > VBase105) {
					Curr = new Complex(Yeq105.getReal() / 3.0, YQFixed / 3.0).multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, varBase).divide(V).conjugate();
				}
				break;
			}
			stickCurrInTerminalArray(getITerminal(), Curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current for constant P, fixed Q.
	 * Q is always a fixed Z derived from kvarBase
	 */
	private void doFixedQZGen() {
		int i;
		Complex Curr = null, V;
		double Vmag;

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			Vmag = V.abs();

			switch (Connection) {
			case 0:
				if (Vmag <= VBase95) {
					Curr = new Complex(Yeq95.getReal(), YQFixed).multiply(V);  // below 95% use an impedance model
				} else if (Vmag > VBase105) {
					Curr = new Complex(Yeq105.getReal(), YQFixed).multiply(V);
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, 0.0).divide(V).conjugate(); // P component of current
					Curr = Curr.add(new Complex(0.0, YQFixed).multiply(V));  // add in Q component of current
				}
				break;
			case 1:
				Vmag = Vmag / DSSGlobals.SQRT3;  // convert to L-N for test
				if (Vmag <= VBase95) {
					Curr = new Complex(Yeq95.getReal() / 3.0, YQFixed / 3.0).multiply(V);  // below 95% use an impedance model
				} else  if (Vmag > VBase105) {
					Curr = new Complex(Yeq105.getReal() / 3.0, YQFixed / 3.0).multiply(V);
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, 0.0).divide(V).conjugate();  // P component of current
					Curr = Curr.add(new Complex(0.0, YQFixed / 3.0).multiply(V));  // add in Q component of current
				}
				break;
			}

			stickCurrInTerminalArray(getITerminal(), Curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // put into terminal array taking into account connection
		}
	}

	/**
	 * Compute total terminal current from User-written model.
	 */
	private void doUserModel() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		calcYPrimContribution(getInjCurrent());  // init injCurrent array

		if (UserModel.exists()) {  // check automatically selects the usermodel if true
			//appendToEventLog("Wnominal=", String.format("%-.5g", Pnominalperphase));
			UserModel.calc(VTerminal, ITerminal);
			setITerminalUpdated(true);
//			SolutionObj sol = Globals.getActiveCircuit().getSolution();
			// negate currents from user model for power flow generator model
			for (int i = 0; i < nConds; i++)
				getInjCurrent()[i] = getInjCurrent()[i].add( getITerminal()[i].negate() );
		} else {
			Globals.doSimpleMsg("Generator." + getName() + " model designated to use user-written model, but user-written model is not defined.", 567);
		}
	}

	/**
	 * Compute total terminal current for constant PQ, but limit to max current below VminPU.
	 */
	private void doCurrentLimitedPQ() {
		int i;
		Complex Curr = null, V;
		double Vmag, VmagLN;

		// treat this just like the load model

		calcYPrimContribution(getInjCurrent());  // init injCurrent array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = VTerminal[i];
			Vmag = V.abs();

			switch (Connection) {
			case 0:
				if ((Vmag <= VBase95) || (Vmag > VBase105)) {  // limit the current magnitude when voltage drops outside normal range
					Curr = CurrentLimit.divide( V.divide(Vmag) ).conjugate();  // current limit expression
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(V).conjugate();  // above vMinPU, constant PQ
				}
				break;
			case 1:
				VmagLN = Vmag / DSSGlobals.SQRT3;
				if ((VmagLN <= VBase95) || (VmagLN > VBase105)) {  // limit the current magnitude when voltage drops outside normal range
					Curr = CurrentLimit.divide( V.divide(Vmag) ).conjugate();  // Current limit expression
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(V).conjugate();  // above vMinPU, constant PQ
				}
				break;
			}

			stickCurrInTerminalArray(getITerminal(), Curr.negate(), i);  // put into terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // put into terminal array taking into account connection
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
		if ((GenModel == 6) && UserModel.exists()) {  // auto selects model
			/* We have total currents in Itemp */
			UserModel.calc(VTerminal, ITerminal);  // returns terminal currents in iTerminal
		} else {
			/* No user model, use default Thevinen equivalent */
			switch (nPhases) {
			case 1:
				calcVThevDyn();  // update for latest phase angle

				getITerminal()[0] = getVTerminal()[0].subtract(Vthev).subtract(getVTerminal()[1]).divide(new Complex(0.0, GenVars.Xdp));
				getITerminal()[1] = getITerminal()[0].negate();

				break;
			case 3:
				MathUtil.phase2SymComp(VTerminal, V012);

				// positive sequence contribution to iTerminal
				calcVThevDyn();  // update for latest phase angle

				// positive sequence contribution to iTerminal
				I012[0] = V012[0].subtract(Vthev).divide(new Complex(0.0, GenVars.Xdp));
				I012[1] = V012[1].divide(new Complex(0.0, GenVars.Xdpp));
				if (Connection == 1) {
					I012[0] = Complex.ZERO;  // TODO Check zero based indexing
				} else {
					I012[0] = V012[0].divide(new Complex(0.0, GenVars.Xdpp));
				}
				MathUtil.symComp2Phase(getITerminal(), I012);  // convert back to phase components

				// Neutral current
				if (Connection == 0)
					getITerminal()[nConds] = I012[0].multiply(3.0).negate();
				break;
			default:
				DSSGlobals.getInstance().doSimpleMsg(String.format("Dynamics mode is implemented only for 1- or 3-phase generators. Generator."+getName()+" has %d phases.", nPhases), 5671);
				DSSGlobals.getInstance().setSolutionAbort(true);
				break;
			}
		}

		setITerminalUpdated(true);

		/* Add it into inj current array */
		for (i = 0; i < nConds; i++)
			getInjCurrent()[i] = getInjCurrent()[i].add( getITerminal()[i].negate() );

		/* Take Care of any shaft model calcs */
		if ((GenModel == 6) && ShaftModel.exists()) {  // auto selects model
			// compute mech power to shaft
			ShaftModel.calc(getVTerminal(), getITerminal());  // returns pshaft at least
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
		Complex E;
		double GenHarmonic;

		computeVTerminal();

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
		GenHarmonic = sol.getFrequency() / GenFundamental;
		E = getSpectrumObj().getMult(GenHarmonic).multiply(VThevHarm);  // get base harmonic magnitude
		E = Utilities.rotatePhasorRad(E, GenHarmonic, ThetaHarm);  // time shift by fundamental frequency phase shift
		for (i = 0; i < nPhases; i++) {
			cBuffer[i] = E;
			if (i < nPhases)
				E = Utilities.rotatePhasorDeg(E, GenHarmonic, -120.0);  // assume 3-phase generator
		}

		/* Handle wye connection */
		if (Connection == 0)
			cBuffer[nConds] = getVTerminal()[nConds];  // assume no neutral injection voltage

		/* Inj currents = Yprim (E) */
		YPrim.MVMult(getInjCurrent(), cBuffer);
	}

	private void calcVTerminalPhase() {
		int i, j;
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		/* Establish phase voltages and stick in Vterminal */
		switch (Connection) {
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

		GeneratorSolutionCount = sol.getSolutionCount();
	}

	/**
	 * Put terminal voltages in an array.
	 */
	private void calcVterminal() {

		computeVTerminal();

		GeneratorSolutionCount = DSSGlobals.getInstance().getActiveCircuit().getSolution().getSolutionCount();
	}

	/**
	 * Calculates generator current and adds it properly into the injcurrent array
	 * routines may also compute ITerminal (ITerminalUpdated flag).
	 */
	private void calcGenModelContribution() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		setITerminalUpdated(false);
		if (sol.isDynamicModel()) {
			doDynamicMode();
		} else if (sol.isHarmonicModel() && (sol.getFrequency() != ckt.getFundamental())) {
			doHarmonicMode();
		} else {
			// compute currents and put into injTemp array
			switch (GenModel) {
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
		if (GenSwitchOpen) {
			zeroInjCurrent();
		} else {
			calcGenModelContribution();
		}

		/*
		We're not going to mess with this logic here -- too complicated: Use an open line in series
		to look at open phase conditions.

		} else {
			SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

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
	protected void getTerminalCurrents(Complex[] Curr) {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		if (ITerminalSolutionCount != sol.getSolutionCount()) {  // recalc the contribution
			if (!GenSwitchOpen)
				calcGenModelContribution();  // adds totals in iTerminal as a side effect
		}

		super.getTerminalCurrents(Curr);

		if (DebugTrace) writeTraceRecord("TotalCurrent");
	}

	@Override
	public int injCurrents() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		if (sol.loadsNeedUpdating())
			setNominalGeneration();  // set the nominal kW, etc for the type of solution being done

		calcInjCurrentArray();  // difference between currents in YPrim and total terminal current

		if (DebugTrace) writeTraceRecord("Injection");

		// add into system injection current array

		return super.injCurrents();
	}

	/**
	 * Gives the currents for the last solution performed.
	 *
	 * Do not call setNominalLoad, as that may change the load values.
	 */
	@Override
	public void getInjCurrents(Complex[] Curr) {
		calcInjCurrentArray();  // difference between currents in YPrim and total current

		try {
			// copy into buffer array
			for (int i = 0; i < YOrder; i++)
				Curr[i] = getInjCurrent()[i];
		} catch (Exception e) {
			DSSGlobals.getInstance().doErrorMsg("Generator Object: \"" + getName() + "\" in getInjCurrents method.",
					e.getMessage(),
					"Current buffer not big enough.", 568);
		}
	}

	public void resetRegisters() {
		for (int i = 0; i < NumGenRegisters; i++)
			Registers[i] = 0.0;
		for (int i = 0; i < NumGenRegisters; i++)
			Derivatives[i] = 0.0;
		FirstSampleAfterReset = true;  // initialize for trapezoidal integration
	}

	private void integrate(int Reg, double Deriv, double Interval) {
		if (DSSGlobals.getInstance().getActiveCircuit().isTrapezoidalIntegration()) {
			/* Trapezoidal Rule Integration */
			if (!FirstSampleAfterReset)
				Registers[Reg] = Registers[Reg] + 0.5 * Interval * (Deriv + Derivatives[Reg]);
		} else {
			/* Plain Euler integration */
			Registers[Reg] = Registers[Reg] + Interval * Deriv;
		}

		Derivatives[Reg] = Deriv;
	}

	/**
	 * Update energy from metered zone.
	 */
	public void takeSample() {
		Complex S;
		double Smag;
		double HourValue;

		// compute energy in generator branch
		if (isEnabled()) {

			if (GenON) {
				S = new Complex(getPresentKW(), getPresentKVAr());
				Smag = S.abs();
				HourValue = 1.0;
			} else {
				S = Complex.ZERO;
				Smag = 0.0;
				HourValue = 0.0;
			}

			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

			if (GenON || ckt.isTrapezoidalIntegration()) {
				/* Make sure we always integrate for Trapezoidal case.
				 * Don't need to for gen off and normal integration.
				 */
				if (ckt.isPositiveSequence()) {
					S    = S.multiply(3.0);
					Smag = 3.0 * Smag;
				}
				integrate            (Reg_kWh,   S.getReal(), ckt.getSolution().getIntervalHrs());   // accumulate the power
				integrate            (Reg_kvarh, S.getImaginary(), ckt.getSolution().getIntervalHrs());
				setDragHandRegister  (Reg_MaxkW, Math.abs(S.getReal()));
				setDragHandRegister  (Reg_MaxkVA, Smag);
				integrate            (Reg_Hours, HourValue, ckt.getSolution().getIntervalHrs());  // accumulate hours in operation
				integrate            (Reg_Price, S.getReal() * ckt.getPriceSignal(), ckt.getSolution().getIntervalHrs());  // accumulate hours in operation
				FirstSampleAfterReset = false;
			}
		}
	}

	public double getPresentKW() {
		return GenVars.Pnominalperphase * 0.001 * nPhases;
	}

	public double getPresentKV() {
		return GenVars.kVGeneratorBase;
	}

	public double getPresentKVAr() {
		return GenVars.Qnominalperphase * 0.001 * nPhases;
	}

	/**
	 * Procedures for setting the dQdV used by the solution object.
	 */
	public void initDQDVCalc() {
		dQdV = 0.0;
		GenVars.Qnominalperphase = 0.5 * (varMax + varMin);  // avg of the limits
	}

	/**
	 * Bump up vars by 10% of range for next calc.
	 */
	public void bumpUpQ() {
		GenVars.Qnominalperphase = GenVars.Qnominalperphase + 0.1 * (varMax - varMin);
	}

	public void rememberQV() {
		var_Remembered = GenVars.Qnominalperphase;
		calcVterminal();
		V_Avg = 0.0;
		for (int i = 0; i < nPhases; i++)
			V_Avg = V_Avg + VTerminal[i].abs();
		V_Avg = V_Avg / nPhases;
		V_Remembered = V_Avg;
	}

	public void calcDQDV() {
		double Vdiff;
		int i;

		calcVterminal();
		V_Avg = 0.0;
		for (i = 0; i < nPhases; i++)
			V_Avg = V_Avg + VTerminal[i].abs();
		V_Avg = V_Avg / nPhases;

		Vdiff = V_Avg - V_Remembered;
		if (Vdiff != 0.0) {
			dQdV = (GenVars.Qnominalperphase - var_Remembered) / Vdiff;
		} else {
			dQdV = 0.0;  // something strange has occured
		}

		// this will force a de facto P, Q model
		dQdVSaved = dQdV;  // save for next time; allows generator to be enabled/disabled during simulation
	}

	public void resetStartPoint() {
		GenVars.Qnominalperphase = 1000.0 * kvarBase / nPhases;
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
				F.println("~ " + getParentClass().getPropertyName()[i] + "=(" + PropertyValue[idx] + ")");
				break;
			case 35:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=(" + PropertyValue[idx] + ")");
				break;
			default:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + PropertyValue[idx]);
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
		Complex E, Va = null;

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		setYPrimInvalid(true);  // force rebuild of YPrims
		GenFundamental = sol.getFrequency();  // whatever the frequency is when we enter here.


		Yeq = new Complex(0.0, GenVars.Xdpp).invert();  // used for current calcs; always L-N

		/* Compute reference Thevinen voltage from phase 1 current */

		if (GenON) {

			computeITerminal();  // get present value of current

			switch (Connection) {
			case 0:  /* Wye - neutral is explicit */
				Va = sol.getNodeV()[nodeRef[0]].subtract( sol.getNodeV()[nodeRef[nConds]] );
				break;
			case 1:  /* Delta -- assume neutral is at zero */
				Va = sol.getNodeV()[nodeRef[0]];
				break;
			}

			E = Va.subtract(ITerminal[0].multiply( new Complex(0.0, GenVars.Xdpp) ));
			VThevHarm = E.abs();   // establish base mag and angle
			ThetaHarm = E.getArgument();
		} else {
			VThevHarm = 0.0;
			ThetaHarm = 0.0;
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0]      = "3";        // phases;
		PropertyValue[1]      = getBus(1);  // bus1  TODO Check zero based indexing
		PropertyValue[2]      = "12.47";
		PropertyValue[3]      = "100";
		PropertyValue[4]      = ".80";
		PropertyValue[5]      = "1";
		PropertyValue[6]      = "";
		PropertyValue[7]      = "";
		PropertyValue[8]      = "";
		PropertyValue[9]      = "Default";
		PropertyValue[10]     = "0.0";
		PropertyValue[11]     = "wye";
		PropertyValue[12]     = "60";
		PropertyValue[13]     = "0";  // "rneut"; // if entered -, assume open
		PropertyValue[14]     = "0";  // "xneut";
		PropertyValue[15]     = "variable"; //"status"  fixed or variable
		PropertyValue[16]     = "1"; //"class"
		PropertyValue[17]     = "1.0";
		PropertyValue[18]     = Utilities.strReal(kvarMax, 3);
		PropertyValue[19]     = Utilities.strReal(kvarMin, 3);
		PropertyValue[20]     = "0.1";
		PropertyValue[21]     = "no";
		PropertyValue[22]     = "0.90";
		PropertyValue[23]     = "1.10";
		PropertyValue[24]     = "No";
		PropertyValue[25]     = String.format("%-g", GenVars.kVArating);
		PropertyValue[26]     = String.format("%-g", GenVars.kVArating * 0.001);
		PropertyValue[27]     = String.format("%-g", GenVars.puXd);
		PropertyValue[28]     = String.format("%-g", GenVars.puXdp);
		PropertyValue[29]     = String.format("%-g", GenVars.puXdpp);
		PropertyValue[30]     = String.format("%-g", GenVars.Hmass);
		PropertyValue[31]     = String.format("%-g", GenVars.Dpu);
		PropertyValue[32]     = "";
		PropertyValue[33]     = "";
		PropertyValue[34]     = "";
		PropertyValue[35]     = "";

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

		Yeq = new Complex(0.0, GenVars.Xdp).invert();

		/* Compute nominal positive sequence voltage behind transient reactance */

		if (GenON) {
			DSSGlobals Globals = DSSGlobals.getInstance();
			SolutionObj sol = Globals.getActiveCircuit().getSolution();

			computeITerminal();

			switch (nPhases) {
			case 1:
				Edp      = sol.getNodeV()[nodeRef[0]].subtract( sol.getNodeV()[nodeRef[1]] ).subtract( getITerminal()[0].multiply(new Complex(0.0, GenVars.Xdp)) );
				VThevMag = Edp.abs();

				break;
			case 3:
				// calculate Edp based on pos seq only
				MathUtil.phase2SymComp(getITerminal(), I012);
				// voltage behind Xdp  (transient reactance), volts

				for (i = 0; i < nPhases; i++)
					Vabc[i] = sol.getNodeV()[nodeRef[i]];  // wye voltage
				MathUtil.phase2SymComp(Vabc, V012);
				Edp      = V012[0].subtract( I012[0].multiply(new Complex(0.0, GenVars.Xdp)) );  // pos sequence
				VThevMag = Edp.abs();
				break;
			default:
				Globals.doSimpleMsg(String.format("Dynamics mode is implemented only for 1- or 3-phase generators. Generator."+getName()+" has %d phases.", nPhases), 5672);
				Globals.setSolutionAbort(true);
				break;
			}


			// shaft variables
			// theta is angle on Vthev[1] relative to system reference
			//Theta  = Vthev[0].getArgument();  // assume source at 0
			GenVars.Theta  = Edp.getArgument() ;
			GenVars.dTheta = 0.0;
			GenVars.w0     = DSSGlobals.TWO_PI * sol.getFrequency();
			// recalc mMass and D in case the frequency has changed
			GenVars.Mmass = 2.0 * GenVars.Hmass * GenVars.kVArating * 1000.0 / GenVars.w0;   // M = W-sec
			GenVars.D = GenVars.Dpu * GenVars.kVArating * 1000.0 / GenVars.w0;

			GenVars.Pshaft = -getPower(0).getReal();  // initialize pShaft to present power output

			GenVars.Speed  = 0.0;  // relative to synch speed
			GenVars.dSpeed = 0.0;

			// init user-written models
			//int nCond; Complex[] V, I; double X, Pshaft, Theta, Speed, dt, time;
			if (GenModel == 6) {
				if (UserModel.exists())
					UserModel.init(VTerminal, ITerminal);
				if (ShaftModel.exists())
					ShaftModel.init(VTerminal, ITerminal);
			}

		} else {
			Vthev  = Complex.ZERO;
			GenVars.Theta  = 0.0;
			GenVars.dTheta = 0.0;
			GenVars.w0     = 0;
			GenVars.Speed  = 0.0;
			GenVars.dSpeed = 0.0;
		}
	}

	@Override
	public void integrateStates() {
		Complex TracePower;

		// compute derivatives and then integrate

		computeITerminal();

		// check for user-written exciter model
		//function(Complex[] V, Complex[] I, double Pshaft, double Theta, double Speed, double dt, double time)
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		if (sol.getDynaVars().IterationFlag == 0){  // first iteration of new time step
			GenVars.ThetaHistory = GenVars.Theta + 0.5 * sol.getDynaVars().h * GenVars.dTheta;
			GenVars.SpeedHistory = GenVars.Speed + 0.5 * sol.getDynaVars().h * GenVars.dSpeed;
		}

		// compute shaft dynamics
		TracePower = MathUtil.terminalPowerIn(VTerminal, ITerminal, nPhases);
		GenVars.dSpeed = (GenVars.Pshaft + TracePower.getReal() - GenVars.D * GenVars.Speed) / GenVars.Mmass;
		//GenVars.dSpeed = (GenVars.Torque + terminalPowerIn(Vtemp, Itemp, nPhases).getReal() / GenVars.Speed) / (GenVars.Mmass);
		GenVars.dTheta  = GenVars.Speed;

		// trapezoidal method
		GenVars.Speed = GenVars.SpeedHistory + 0.5 * sol.getDynaVars().h * GenVars.dSpeed;
		GenVars.Theta = GenVars.ThetaHistory + 0.5 * sol.getDynaVars().h * GenVars.dTheta;

		// write dynamics trace record
		if (DebugTrace) {
			try {
				FileWriter TraceStream = new FileWriter(TraceFile, true);
				BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);
				TraceBuffer.write(String.format("t=%-.5g ", sol.getDynaVars().t));
				TraceBuffer.write(String.format(" Flag=%d ", sol.getDynaVars().IterationFlag));
				TraceBuffer.write(String.format(" Speed=%-.5g ", GenVars.Speed));
				TraceBuffer.write(String.format(" dSpeed=%-.5g ", GenVars.dSpeed));
				TraceBuffer.write(String.format(" Pshaft=%-.5g ", GenVars.Pshaft));
				TraceBuffer.write(String.format(" P=%-.5g Q= %-.5g", TracePower.getReal(), TracePower.getImaginary()));
				TraceBuffer.write(String.format(" M=%-.5g ", GenVars.Mmass));
				TraceBuffer.newLine();
				TraceBuffer.close();
				TraceStream.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}

		if (GenModel == 6) {
			if (UserModel.exists()) UserModel.integrate();
			if (ShaftModel.exists()) ShaftModel.integrate();
		}
	}

	/**
	 * Return variables one at a time.
	 */
	@Override
	public double getVariable(int i) {
		int N, k;

		N = 0;
		double Result = -9999.99;  // error return value

		if (i < 0) return Result;

		switch (i) {
		case 0:
			Result = (GenVars.w0 + GenVars.Speed) / DSSGlobals.TWO_PI;  // frequency, Hz
			break;
		case 1:
			Result = GenVars.Theta * DSSGlobals.RADIANS_TO_DEGREES;  // report in deg
			break;
		case 2:
			Result = Vthev.abs() / VBase;  // report in pu
			break;
		case 3:
			Result = GenVars.Pshaft;
			break;
		case 4:
			Result = GenVars.dSpeed * DSSGlobals.RADIANS_TO_DEGREES;  // report in deg
			break;
		case 5:
			Result = GenVars.dTheta;
			break;
		default:
			if (UserModel.exists()) {
				N = UserModel.numVars();
				k = (i - NumGenVariables);
				if (k <= N)
					return UserModel.getVariable(k);
			}

			/* If we get here, must be in the shaft model if anywhere */
			if (ShaftModel.exists()) {
				k = i - (NumGenVariables + N);
				if (k > 0)
					return ShaftModel.getVariable(k);
			}
			break;
		}

		return Result;
	}

	@Override
	public void setVariable(int i, double Value) {
		int N, k;

		N = 0;
		if (i < 0) return;
		switch (i) {
		case 0:
			GenVars.Speed = (Value - GenVars.w0) * DSSGlobals.TWO_PI;
			break;
		case 1:
			GenVars.Theta = Value / DSSGlobals.RADIANS_TO_DEGREES;  // deg to rad
			break;
		case 2:
			// meaningless to set Vd = Value * vbase; // pu to volts
			break;
		case 3:
			GenVars.Pshaft = Value;
			break;
		case 4:
			GenVars.dSpeed = Value / DSSGlobals.RADIANS_TO_DEGREES;
			break;
		case 5:
			GenVars.dTheta = Value ;
			break;
		default:
			if (UserModel.exists()) {
				N = UserModel.numVars();
				k = (i - NumGenVariables) ;
				if (k <= N) {
					UserModel.setVariable(k, Value);
					return;
				}
			}

			// if we get here, must be in the shaft model
			if (ShaftModel.exists()) {
				k = (i - (NumGenVariables + N)) ;
				if (k > 0)
					ShaftModel.setVariable(k, Value);
			}
			break;
		}
	}

	@Override
	public void getAllVariables(double[] States) {
		int i, N;
		N = 0;

		for (i = 0; i < NumGenVariables; i++)
			States[i] = getVariable(i);

		if (UserModel.exists()) {
			N = UserModel.numVars();
			UserModel.getAllVars(States[NumGenVariables + 1]);
		}

		if (ShaftModel.exists())
			ShaftModel.getAllVars(States[NumGenVariables + 1 + N]);
	}

	@Override
	public int numVariables() {
		int Result = NumGenVariables;
		if (UserModel.exists())
			Result = Result + UserModel.numVars();
		if (ShaftModel.exists())
			Result = Result + ShaftModel.numVars();
		return Result;
	}

	@Override
	public String variableName(int i) {
		final int BuffSize = 255;

		int n, i2;
		//char[] Buff = new char[BuffSize];
		int pName;
		String Result = "";

		n = 0;
		if (i < 0) return Result;
		switch (i) {
		case 0:
			Result = "Frequency";
			break;
		case 1:
			Result = "Theta (Deg)";
			break;
		case 2:
			Result = "Vd";
			break;
		case 3:
			Result = "PShaft";
			break;
		case 4:
			Result = "dSpeed (Deg/sec)";
			break;
		case 5:
			Result = "dTheta (Deg)";
			break;
		default:
			if (UserModel.exists()) {
				pName = 0;
				n = UserModel.numVars();
				i2 = i - NumGenVariables;
				if (i2 <= n) {
					UserModel.getVarName(i2, pName, BuffSize);
					return String.valueOf(pName);
				}
			}

			if (ShaftModel.exists()) {
				pName = 0;
				i2 = i - NumGenVariables - n;
				if (i2 >= 0)
					UserModel.getVarName(i2, pName, BuffSize);
				Result = String.valueOf(pName);
			}
			break;
		}

		return Result;
	}

	@Override
	public String getPropertyValue(int Index) {
		String Result = "";

		switch (Index) {
		case 2:
			Result = String.format("%.6g", GenVars.kVGeneratorBase);
			break;
		case 3:
			Result = String.format("%.6g", kWBase);
			break;
		case 4:
			Result = String.format("%.6g", PFNominal);
			break;
		case 6:
			Result = YearlyShape;
			break;
		case 7:
			Result = DailyDispShape;
			break;
		case 8:
			Result = DutyShape;
			break;
		case 12:
			Result = String.format("%.6g", kvarBase);
			break;
		case 18:
			Result = String.format("%.6g", kvarMax);
			break;
		case 19:
			Result = String.format("%.6g", kvarMin);
			break;
		case 25:
			Result = String.format("%.6g", GenVars.kVArating);
			break;
		case 26:
			Result = String.format("%.6g", GenVars.kVArating * 0.001);
			break;
		case 33:
			Result = "(" + super.getPropertyValue(Index) + ")";
			break;
		case 35:
			Result = "(" + super.getPropertyValue(Index) + ")";
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}

		return Result;
	}

	/**
	 * Make a positive sequence model
	 */
	@Override
	public void makePosSequence() {
		String S;
		double V;

		S = "Phases=1 conn=wye";

		// make sure voltage is line-neutral
		if ((nPhases > 1) || (Connection != 0)) {
			V =  GenVars.kVGeneratorBase / DSSGlobals.SQRT3;
		} else {
			V =  GenVars.kVGeneratorBase;
		}

		S = S + String.format(" kV=%-.5g", V);

		// divide the load by no. phases
		if (nPhases > 1) {
			S = S + String.format(" kW=%-.5g  PF=%-.5g", kWBase / nPhases, PFNominal);
			if ((PrpSequence[18] != 0) || (PrpSequence[19] != 0))
				S = S + String.format(" maxkvar=%-.5g  minkvar=%-.5g", kvarMax / nPhases, kvarMin / nPhases);
			if (PrpSequence[25] > 0)
				S = S + String.format(" kva=%-.5g  ", GenVars.kVArating / nPhases);
			if (PrpSequence[26] > 0)
				S = S + String.format(" MVA=%-.5g  ", GenVars.kVArating / 1000.0 / nPhases);
		}

		Parser.getInstance().setCmdString(S);
		edit();

		super.makePosSequence();
	}

	@Override
	public void setConductorClosed(int Index, boolean Value) {
		super.setConductorClosed(Index, Value);

		// just turn generator on or off

		if (Value) {
			GenSwitchOpen = false;
		} else {
			GenSwitchOpen = true;
		}
	}

	public void setPowerFactor(double Value) {
		PFNominal = Value;
		syncUpPowerQuantities();
	}

	public void setPresentKV(double Value) {
		GenVars.kVGeneratorBase = Value;

		switch (nPhases) {
		case 2:
			VBase = GenVars.kVGeneratorBase * DSSGlobals.InvSQRT3x1000;
			break;
		case 3:
			VBase = GenVars.kVGeneratorBase * DSSGlobals.InvSQRT3x1000;
			break;
		default:
			VBase = GenVars.kVGeneratorBase * 1000.0 ;
			break;
		}
	}

	public void setPresentKVAr(double Value) {
		double kVA_Gen;

		kvarBase = Value;
		GenVars.Qnominalperphase = 1000.0 * kvarBase  / nPhases; // init to something reasonable
		kVA_Gen = Math.sqrt(Math.pow(kWBase, 2) + Math.pow(kvarBase, 2));
		if (kVA_Gen != 0.0) {
			setPowerFactor(kWBase / kVA_Gen);
		} else {
			setPowerFactor(1.0);
		}

		if ((kWBase * kvarBase) < 0.0)
			setPowerFactor(-PFNominal);

		kvarMax  = 2.0 * kvarBase;
		kvarMin  = -kvarMax;
	}

	public void setPresentKW(double Value) {
		kWBase = Value;
		syncUpPowerQuantities();
	}

	// FIXME Private method in OpenDSS
	public void syncUpPowerQuantities() {
		// keep kvar nominal up to date with kW and PF
		if (PFNominal != 0.0) {
			kvarBase = kWBase * Math.sqrt(1.0 / Math.pow(PFNominal, 2) - 1.0);
			GenVars.Qnominalperphase = 1000.0 * kvarBase / nPhases;
			kvarMax = 2.0 * kvarBase;
			kvarMin = -kvarMax;
			if (PFNominal < 0.0)
				kvarBase = -kvarBase;

			if (kVANotSet)
				GenVars.kVArating = kWBase * 1.2;
		}
	}

	private void setDragHandRegister(int Reg, double Value) {
		if (Value > Registers[Reg])
			Registers[Reg] = Value;
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
		if (GenSwitchOpen)
			VThevMag = 0.0;
		Vthev = ComplexUtil.pclx(VThevMag, GenVars.Theta);
	}

	public boolean isForcedOn() {
		return ForcedON;
	}

	public void setForcedOn(boolean forcedON) {
		ForcedON = forcedON;
	}

	public double getPowerFactor() {
		return PFNominal;
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}

	public String getDailyDispShape() {
		return DailyDispShape;
	}

	public void setDailyDispShape(String dailyDispShape) {
		DailyDispShape = dailyDispShape;
	}

	public LoadShapeObj getDailyDispShapeObj() {
		return DailyDispShapeObj;
	}

	public void setDailyDispShapeObj(LoadShapeObj dailyDispShapeObj) {
		DailyDispShapeObj = dailyDispShapeObj;
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

	public int getGenClass() {
		return GenClass;
	}

	public void setGenClass(int genClass) {
		GenClass = genClass;
	}

	public int getGenModel() {
		return GenModel;
	}

	public void setGenModel(int genModel) {
		GenModel = genModel;
	}

	public GeneratorVars getGenVars() {
		return GenVars;
	}

	public void setGenVars(GeneratorVars genVars) {
		GenVars = genVars;
	}

	public double getKVArBase() {
		return kvarBase;
	}

	public void setKVArBase(double kvarBase) {
		this.kvarBase = kvarBase;
	}

	public double getKVArMax() {
		return kvarMax;
	}

	public void setKVArMax(double kvarMax) {
		this.kvarMax = kvarMax;
	}

	public double getKVArMin() {
		return kvarMin;
	}

	public void setKVArMin(double kvarMin) {
		this.kvarMin = kvarMin;
	}

	public double getKWBase() {
		return kWBase;
	}

	public void setKWBase(double kWBase) {
		this.kWBase = kWBase;
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

	public void setVTarget(double vTarget) {
		VTarget = vTarget;
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

	public double[] getRegisters() {
		return Registers;
	}

	public void setRegisters(double[] registers) {
		Registers = registers;
	}

	public double[] getDerivatives() {
		return Derivatives;
	}

	public void setDerivatives(double[] derivatives) {
		Derivatives = derivatives;
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
		return CurrentLimit;
	}

	public void setCurrentLimit(Complex currentLimit) {
		CurrentLimit = currentLimit;
	}

	public boolean isDebugTrace() {
		return DebugTrace;
	}

	public void setDebugTrace(boolean debugTrace) {
		DebugTrace = debugTrace;
	}

	public double getDeltaQMax() {
		return DeltaQMax;
	}

	public void setDeltaQMax(double deltaQMax) {
		DeltaQMax = deltaQMax;
	}

	public int getDispatchMode() {
		return DispatchMode;
	}

	public void setDispatchMode(int dispatchMode) {
		DispatchMode = dispatchMode;
	}

	public double getDispatchValue() {
		return DispatchValue;
	}

	public void setDispatchValue(double dispatchValue) {
		DispatchValue = dispatchValue;
	}

	public double getDQDV() {
		return dQdV;
	}

	public void setDQDV(double dQdV) {
		this.dQdV = dQdV;
	}

	public double getDQDVSaved() {
		return dQdVSaved;
	}

	public void setDQDVSaved(double dQdVSaved) {
		this.dQdVSaved = dQdVSaved;
	}

	public boolean isFirstSampleAfterReset() {
		return FirstSampleAfterReset;
	}

	public void setFirstSampleAfterReset(boolean firstSampleAfterReset) {
		FirstSampleAfterReset = firstSampleAfterReset;
	}

	public boolean isFixed() {
		return Fixed;
	}

	public void setFixed(boolean fixed) {
		Fixed = fixed;
	}

	public int getGeneratorSolutionCount() {
		return GeneratorSolutionCount;
	}

	public void setGeneratorSolutionCount(int generatorSolutionCount) {
		GeneratorSolutionCount = generatorSolutionCount;
	}

	public double getGenFundamental() {
		return GenFundamental;
	}

	public void setGenFundamental(double genFundamental) {
		GenFundamental = genFundamental;
	}

	public boolean isGenOn() {
		return GenON;
	}

	public void setGenOn(boolean genON) {
		GenON = genON;
	}

	public boolean isGenSwitchOpen() {
		return GenSwitchOpen;
	}

	public void setGenSwitchOpen(boolean genSwitchOpen) {
		GenSwitchOpen = genSwitchOpen;
	}

	public boolean iskVANotSet() {
		return kVANotSet;
	}

	public void setkVANotSet(boolean kVANotSet) {
		this.kVANotSet = kVANotSet;
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

	public int getOpenGeneratorSolutionCount() {
		return OpenGeneratorSolutionCount;
	}

	public void setOpenGeneratorSolutionCount(int openGeneratorSolutionCount) {
		OpenGeneratorSolutionCount = openGeneratorSolutionCount;
	}

	public double getPVFactor() {
		return PVFactor;
	}

	public void setPVFactor(double pVFactor) {
		PVFactor = pVFactor;
	}

	public double getRandomMult() {
		return RandomMult;
	}

	public void setRandomMult(double randomMult) {
		RandomMult = randomMult;
	}

	public int getRegHours() {
		return Reg_Hours;
	}

	public void setRegHours(int reg_Hours) {
		Reg_Hours = reg_Hours;
	}

	public int getRegKVArh() {
		return Reg_kvarh;
	}

	public void setRegKVArh(int reg_kvarh) {
		Reg_kvarh = reg_kvarh;
	}

	public int getRegKWh() {
		return Reg_kWh;
	}

	public void setRegKWh(int reg_kWh) {
		Reg_kWh = reg_kWh;
	}

	public int getRegMaxKVA() {
		return Reg_MaxkVA;
	}

	public void setReg_MaxkVA(int reg_MaxkVA) {
		Reg_MaxkVA = reg_MaxkVA;
	}

	public int getReg_MaxkW() {
		return Reg_MaxkW;
	}

	public void setRegMaxKW(int reg_MaxkW) {
		Reg_MaxkW = reg_MaxkW;
	}

	public int getRegPrice() {
		return Reg_Price;
	}

	public void setRegPrice(int reg_Price) {
		Reg_Price = reg_Price;
	}

	public Complex getShapeFactor() {
		return ShapeFactor;
	}

	public void setShapeFactor(Complex shapeFactor) {
		ShapeFactor = shapeFactor;
	}

	public double getThetaHarm() {
		return ThetaHarm;
	}

	public void setThetaHarm(double thetaHarm) {
		ThetaHarm = thetaHarm;
	}

	public File getTraceFile() {
		return TraceFile;
	}

	public void setTraceFile(File traceFile) {
		TraceFile = traceFile;
	}

	public double getV_Avg() {
		return V_Avg;
	}

	public void setV_Avg(double v_Avg) {
		V_Avg = v_Avg;
	}

	public double getVRemembered() {
		return V_Remembered;
	}

	public void setVRemembered(double v_Remembered) {
		V_Remembered = v_Remembered;
	}

	public double getVArRemembered() {
		return var_Remembered;
	}

	public void setVArRemembered(double var_Remembered) {
		this.var_Remembered = var_Remembered;
	}

	public double getVArBase() {
		return varBase;
	}

	public void setVArBase(double varBase) {
		this.varBase = varBase;
	}

	public double getVArMax() {
		return varMax;
	}

	public void setVArMax(double varMax) {
		this.varMax = varMax;
	}

	public double getVArMin() {
		return varMin;
	}

	public void setVArMin(double varMin) {
		this.varMin = varMin;
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

	public double getVMaxPU() {
		return VMaxPU;
	}

	public void setVMaxPU(double vMaxPU) {
		VMaxPU = vMaxPU;
	}

	public double getVMinPU() {
		return VMinPU;
	}

	public void setVMinPU(double vMinPU) {
		VMinPU = vMinPU;
	}

	public Complex getVThev() {
		return Vthev;
	}

	public void setVThev(Complex vthev) {
		Vthev = vthev;
	}

	public double getVThevHarm() {
		return VThevHarm;
	}

	public void setVThevHarm(double vThevHarm) {
		VThevHarm = vThevHarm;
	}

	public double getVThevMag() {
		return VThevMag;
	}

	public void setVThevMag(double vThevMag) {
		VThevMag = vThevMag;
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

	public boolean isShapeIsActual() {
		return ShapeIsActual;
	}

	public void setShapeIsActual(boolean shapeIsActual) {
		ShapeIsActual = shapeIsActual;
	}

	public GenUserModel getUserModel() {
		return UserModel;
	}

	public void setUserModel(GenUserModel userModel) {
		UserModel = userModel;
	}

	public GenUserModel getShaftModel() {
		return ShaftModel;
	}

	public void setShaftModel(GenUserModel shaftModel) {
		ShaftModel = shaftModel;
	}

}
