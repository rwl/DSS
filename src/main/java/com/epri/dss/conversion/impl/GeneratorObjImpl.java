package com.epri.dss.conversion.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.Generator;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.Dynamics;
import com.epri.dss.shared.impl.DynamicsImpl.GeneratorVars;

public class GeneratorObjImpl extends PCElementImpl implements GeneratorObj {
	
	/* Number of energy meter registers */
	private static final int NumGenRegisters = 6;    
	private static final int NumGenVariables = 6;
	
	private static final Complex CDOUBLEONE = new Complex(1.0, 1.0);
	
	private Complex[] cBuffer = new Complex[24];  // Temp buffer for calcs  24-phase generator?
	
	private Complex Yeq;     // at nominal
	private Complex Yeq95;   // at 95%
	private Complex Yeq105;  // at 105%

	private Complex CurrentLimit;
	private boolean DebugTrace;
	/* Max allowable var change on Model=3 per iteration */
	private double DeltaQMax;  
	private int DispatchMode;
	private double DispatchValue;
	private double dQdV;
	private double dQdVSaved;
	private boolean ForcedON;
	private boolean FirstSampleAfterReset;
	private boolean Fixed;   // if Fixed, always at base value
	private int GeneratorSolutionCount;
	/* Thevenin equivalent voltage mag and angle reference for Harmonic model */
	private double GenFundamental; 
	/* Indicates whether generator is currently on */
	private boolean GenON;           
	private boolean GenSwitchOpen;
	private boolean kVANotSet;
	private double LastGrowthFactor;
	/* Added for speedup so we don't have to search for growth factor a lot */
	private int LastYear;   
	private int OpenGeneratorSolutionCount;
	/* Deceleration Factor for computing vars for PV generators */
	private double PVFactor;  
	private double RandomMult;
	private int Reg_Hours;
	private int Reg_kvarh;
	private int Reg_kWh;
	private int Reg_MaxkVA;
	private int Reg_MaxkW;
	private int Reg_Price;
	private Complex ShapeFactor;
	/* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private double ThetaHarm;  
	private File TraceFile;
	/* User-Written Models */
//	private GenUserModel UserModel, ShaftModel;   
	private double V_Avg;
	private double V_Remembered;
	private double var_Remembered;
	/* Base vars per phase */
	private double varBase; 
	private double varMax;
	private double varMin;
	/* Base volts suitable for computing currents */
	private double VBase;  
	private double VBase105;
	private double VBase95;
	private double VMaxPU;
	private double VMinPU;
	/* Thevinen equivalent voltage (complex) for dynamic model */
	private Complex Vthev;  
	/* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private double VThevHarm;  
	/* Thevinen equivalent voltage for dynamic model */
	private double VThevMag;    
	/*
	 * To handle cases where one conductor of load is open;
	 * We revert to admittance for inj currents
	 */
	private CMatrix YPrimOpenCond;
	/* Fixed value of y for type 7 load */
	private double YQFixed;  
	private boolean ShapeIsActual;
	
	/* 0 = line-neutral; 1 = Delta */
	protected int Connection; 
	/* Daily (24 HR) Generator shape */
	protected String DailyDispShape;  
	/* Daily Generator Shape for this load */
	protected LoadShapeObj DailyDispShapeObj;
	/* Duty cycle load shape for changes typically less than one hour */
	protected String DutyShape;  
	/* Shape for this generator */
	protected LoadShapeObj DutyShapeObj;  
	protected int GenClass;
	/* Variation with voltage */
	protected int GenModel;   
	/* State Variables */
	protected GeneratorVars GenVars; 
	protected double kvarBase;
	protected double kvarMax;
	protected double kvarMin;
	protected double kWBase;
	protected double PFNominal;
	/* Per unit Target voltage for generator with voltage control */
	protected double Vpu;       
	/* Target voltage for generator with voltage control */
	protected double VTarget;  
	/* ='fixed' means no variation  on all the time */
	protected String YearlyShape;  
	/* Shape for this Generator */
	protected LoadShapeObj YearlyShapeObj;  

	protected double[] Registers = new double[NumGenRegisters];
	protected double[] Derivatives = new double[NumGenRegisters];

	public GeneratorObjImpl(DSSClassImpl ParClass, String GeneratorName) {
		super(ParClass);
		setName(GeneratorName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType(); // + GEN_ELEMENT;  // In both PCelement and Genelement list

		this.nPhases = 3;
		this.nConds  = 4;  // defaults to wye
		this.Yorder  = 0;  // To trigger an initial allocation
		this.nTerms  = 1;  // forces allocations
		this.kWBase  = 1000.0;
		this.kvarBase = 60.0;

		this.kvarMax   = this.kvarBase * 2.0;
		this.kvarMin   = -this.kvarMax;
		this.PFNominal = 0.88;
		//this.Rneut     = 0.0;
		//this.Xneut     = 0.0;
		this.YearlyShape    = "";
		this.YearlyShapeObj = null;     // if YearlyShapeobj = null then the load alway stays nominal * global multipliers
		this.DailyDispShape = "";
		this.DailyDispShapeObj = null;  // if DaillyShapeobj = null then the load alway stays nominal * global multipliers
		this.DutyShape         = "";
		this.DutyShapeObj      = null;  // if DutyShapeobj = null then the load alway stays nominal * global multipliers
		this.Connection        = 0;     // Wye (star)
		this.GenModel          = 1;  /* Typical fixed kW negative load */
		this.GenClass          = 1;
		this.LastYear          = 0;
		this.LastGrowthFactor  = 1.0;

		this.dQdVSaved = 0.0;  // Initialize this here.  Allows generators to be turned off and on


		this.GeneratorSolutionCount     = -1;  // For keep track of the present solution in Injcurrent calcs
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
		this.Yorder           = this.nTerms * this.nConds;
		this.RandomMult       = 1.0 ;
		this.Fixed            = false;

		/* Machine rating stuff */
		this.GenVars.kVArating  = this.kWBase * 1.2;
		this.kVANotSet = true;  // Flag for default value for kVA
		
		//this.GenVars.Vd = 7200.0;


		this.GenVars.puXd       = 1.0;
		this.GenVars.puXdp      = 0.28;
		this.GenVars.puXdpp     = 0.20;
		this.GenVars.Xd         = this.GenVars.puXd * Math.pow(this.GenVars.kVGeneratorBase, 2) * 1000.0 / this.GenVars.kVArating;
		this.GenVars.Xdp        = this.GenVars.puXdp * Math.pow(this.GenVars.kVGeneratorBase, 2) * 1000.0 / this.GenVars.kVArating;
		this.GenVars.Xdpp       = this.GenVars.puXdpp * Math.pow(this.GenVars.kVGeneratorBase, 2) * 1000.0 / this.GenVars.kVArating;
		this.GenVars.Hmass      = 1.0;       //  W-sec/VA rating
		this.GenVars.Theta      = 0.0;
		this.GenVars.w0         = DSSGlobals.TwoPi * getBaseFrequency();
		this.GenVars.Speed      = 0.0;
		this.GenVars.dSpeed     = 0.0;
		this.GenVars.D          = 1.0;

		this.UserModel  = new GenUserModel(this.GenVars) ;
		this.ShaftModel = new GenUserModel(this.GenVars);

		this.DispatchValue = 0.0;  // Follow curves

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

		setSpectrum("defaultgen");  // override base class

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
		case DSSGlobals.GAUSSIAN:
			RandomMult = MathUtil.Gauss(YearlyShapeObj.getMean(), YearlyShapeObj.getStdDev());
		case DSSGlobals.UNIFORM:
			RandomMult = Math.random();  // number between 0 and 1.0
		case DSSGlobals.LOGNORMAL:
			RandomMult = MathUtil.QuasiLognormal(YearlyShapeObj.getMean());
		}
	}
	
	private void calcDailyMult(double Hr) {
		if (DailyDispShapeObj != null) {
			ShapeFactor = DailyDispShapeObj.getMult(Hr);
			ShapeIsActual = DailyDispShapeObj.isUseActual();
		} else {
			ShapeFactor = CDOUBLEONE;  // Default to no daily variation
		}
	}
	
	private void calcDutyMult(double Hr) {
		if (DutyShapeObj != null) {
			ShapeFactor = DutyShapeObj.getMult(Hr);
			ShapeIsActual = DutyShapeObj.isUseActual();
		} else {
			calcDailyMult(Hr);  // Default to Daily Mult if no duty curve specified
		}
	}
	
	private void calcYearlyMult(double Hr) {
		/* Yearly curve is assumed to be hourly only */
		if (YearlyShapeObj != null) {
			ShapeFactor = YearlyShapeObj.getMult(Hr);
			ShapeIsActual = YearlyShapeObj.isUseActual();
		} else {
			ShapeFactor = CDOUBLEONE;  // Defaults to no variation
		}
	}
	
	public void setNominalGeneration() {
		double Factor;
		boolean GenOn_Saved;

		GenOn_Saved = GenON;
		ShapeFactor = CDOUBLEONE;
		// Check to make sure the generation is ON
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();
		
		if (!sol.isIsDynamicModel() || !sol.isIsHarmonicModel()) {  // Leave generator in whatever state it was prior to entering Dynamic mode
			GenON = true;   // Init to on then check if it should be off
			if (!ForcedON) 
				switch (DispatchMode) {
				case Generator.LOADMODE:
					if ((DispatchValue > 0.0) && (ckt.getGeneratorDispatchReference() < DispatchValue))
						GenON = false;
				case Generator.PRICEMODE:
					if ((DispatchValue > 0.0) && (ckt.getPriceSignal() < DispatchValue))
						GenON = false;
				}
		}


		if (!GenON) {
			// If Generator is OFF enter as tiny resistive load (.0001 pu) so we don't get divide by zero in matrix
			GenVars.Pnominalperphase = -0.1 * kWBase / nPhases;
			// Pnominalperphase   = 0.0;
			GenVars.Qnominalperphase = 0.0;
		} else {
			// Generator is on, compute it's nominal watts and vars
			
			if (isFixed()) {
				Factor = 1.0;   // for fixed generators, set constant
			} else {
				switch (sol.getMode()) {
				case Dynamics.SNAPSHOT:
					Factor = ckt.getGenMultiplier() * 1.0;
				case Dynamics.DAILYMODE:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());  // Daily dispatch curve
				case Dynamics.YEARLYMODE:
					Factor = ckt.getGenMultiplier();
					calcYearlyMult(sol.getDblHour());
				case Dynamics.DUTYCYCLE:
					Factor = ckt.getGenMultiplier();
					calcDutyMult(sol.getDblHour());
				case Dynamics.GENERALTIME:  // General sequential time simulation
					Factor = ckt.getGenMultiplier();
					// This mode allows use of one class of load shape
					switch (ckt.getActiveLoadShapeClass()) {
					case DSSGlobals.USEDAILY:
						calcDailyMult(sol.getDblHour());
					case DSSGlobals.USEYEARLY:
						calcYearlyMult(sol.getDblHour());
					case DSSGlobals.USEDUTY:
						calcDutyMult(sol.getDblHour());
					default:
						ShapeFactor = new Complex(1.0, 1.0);  // default to 1 + j1 if not known
					}
				case Dynamics.MONTECARLO1:
					Factor = ckt.getGenMultiplier() * 1.0;
				case Dynamics.MONTEFAULT:
					Factor = ckt.getGenMultiplier() * 1.0;
				case Dynamics.FAULTSTUDY:
					Factor = ckt.getGenMultiplier() * 1.0;
				case Dynamics.DYNAMICMODE:
					Factor = ckt.getGenMultiplier() * 1.0;
				case Dynamics.MONTECARLO2:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
				case Dynamics.MONTECARLO3:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
				case Dynamics.LOADDURATION1:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
				case Dynamics.LOADDURATION2:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
				case Dynamics.PEAKDAY:
					Factor = ckt.getGenMultiplier();
					calcDailyMult(sol.getDblHour());
				case Dynamics.AUTOADDFLAG:
					Factor = 1.0;
				default:
					Factor = 1.0;
				}
			}
	
			if (!sol.isIsDynamicModel() || sol.isIsHarmonicModel()) {  
				if (ShapeIsActual) {
					GenVars.Pnominalperphase = 1000.0 * ShapeFactor.getReal() / nPhases;
				} else {
					GenVars.Pnominalperphase = 1000.0 * kWBase * Factor * ShapeFactor.getReal() / nPhases;
				}
	
				
				if (GenModel == 3) {
					/* Just make sure present value is reasonable} */
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
		}  /* else GenON */

		if (!sol.isIsDynamicModel() || sol.isIsHarmonicModel()) {

			switch (GenModel) {
			case 6:
				Yeq = new Complex(0.0, -GenVars.Xd).invert();  // Gets negated in CalcYPrim
			default:
				Yeq = new Complex(GenVars.Pnominalperphase, -GenVars.Qnominalperphase).divide(Math.pow(VBase, 2));  // Vbase must be L-N for 3-phase
				if (VMinPU != 0.0) {
					Yeq95 = Yeq.divide(Math.pow(VMinPU, 2));  // at 95% voltage
				} else {
					Yeq95 = Yeq; // Always a constant Z model
				}

				if (VMaxPU != 0.0) {
					Yeq105 = Yeq.divide(Math.pow(VMaxPU, 2));  // at 105% voltage
				} else {
					Yeq105 = Yeq;
				}
			}

			/* When we leave here, all the Yeq's are in L-N values */
			if (GenModel == 7)
				CurrentLimit = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(VBase95);
		}

		// If generator state changes, force re-calc of Y matrix
		if (GenON != GenOn_Saved)
			setYprimInvalid(true);
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

		/* Now check for errors.  If any of these came out nil and the string was not nil, give warning */
		if (YearlyShape.equals("none")) YearlyShape = "";
		if (DailyDispShape.equals("none")) DailyDispShape = "";
		if (DutyShape.equals("none")) DutyShape = "";

		if (YearlyShapeObj == null)
			if (YearlyShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Yearly load shape: \""+ YearlyShape +"\" Not Found.", 563);
		if (DailyDispShapeObj == null)
			if (DailyDispShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Daily load shape: \""+ DailyDispShape +"\" Not Found.", 564);
		if (DutyShapeObj == null)
			if (DutyShape.length() > 0)
				Globals.doSimpleMsg("WARNING! Duty load shape: \""+ DutyShape +"\" Not Found.", 565);

		setSpectrumObj( (com.epri.dss.general.SpectrumObj) Globals.getSpectrumClass().find(getSpectrum()) );
		if (getSpectrumObj() == null)
			Globals.doSimpleMsg("ERROR! Spectrum \""+getSpectrum()+"\" Not Found.", 566);
		/*
		if (Rneut < 0.0) {  // flag for open neutral
			YNeut = new Complex(0.0, 0.0);
		} else if ((Rneut == 0.0) && (Xneut = 0.0)) {  // Solidly Grounded
			YNeut = new Complex(1.0e6, 0.0);  // 1 microohm resistor
		} else {
			YNeut = new Complex(Rneut, XNeut).invert();
		}
		*/

		YQFixed = -varBase / Math.pow(VBase, 2);   // 10-17-02  Fixed negative sign
		VTarget = Vpu * 1000.0 * GenVars.kVGeneratorBase / DSSGlobals.SQRT3;

		// Initialize to Zero - defaults to PQ generator
		// Solution object will reset after circuit modifications
		dQdV      = dQdVSaved;         // for Model = 3
		DeltaQMax = (varMax - varMin) * 0.10;  // Limit to 10% of range

		setInjCurrent( (Complex[]) Utilities.resizeArray(getInjCurrent(), Yorder) );

		/* Update any user-written models */
		if (UserModel.exists()) UserModel.updateModel();
		if (ShaftModel.exists()) ShaftModel.updateModel();
	}
	
	private void calcYPrimMatrix(CMatrix Ymatrix) {
		Complex Y , Yij;
		int i, j;
		double FreqMultiplier;
		
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		YprimFreq = ckt.getSolution().getFrequency();
		FreqMultiplier = YprimFreq / BaseFrequency;
		
		SolutionObj sol = ckt.getSolution();

		if (sol.isIsDynamicModel() || sol.isIsHarmonicModel()) {
			if (GenON) {
				Y  = Yeq;  // L-N value computed in initialization routines
			} else {
				Y = new Complex(DSSGlobals.EPSILON, 0.0);
			}

			if (Connection == 1)
				Y = Y.divide(3.0); // Convert to delta impedance
			Y = new Complex(Y.getReal(), Y.getImaginary() / FreqMultiplier);
			Yij = Y.negate();
			for (i = 0; i < nPhases; i++) {
				switch (Connection) {
				case 0:
					Ymatrix.setElement(i, i, Y);
					Ymatrix.addElement(nConds, nConds, Y);
					Ymatrix.setElemSym(i, nConds, Yij);
				case 1:  /* Delta connection */
					Ymatrix.setElement(i, i, Y);
					Ymatrix.addElement(i, i, Y);  // put it in again
					for (j = 0; j < i - 1; j++) {  // TODO Check zero based indexing
						Ymatrix.setElemSym(i, j, Yij);
					}
				}
			}

			/* **** Removed Neutral / Neutral may float

			if (Connection == 0) {  // Take care of neutral issues
				Ymatrix.addElement(nConds, nConds, YNeut);  // Add in user specified Neutral Z, if any
				// Bump up neutral-ground in case neutral ends up floating
				Ymatrix.setElement(nConds, nConds, Ymatrix.getElement(nConds, nConds).multiply(1.000001));
			}

			 */
			
		} else {
			// Regular power flow generator model

			/* Yeq is always expected as the equivalent line-neutral admittance */

			Y = Yeq.negate();  // negate for generation    Yeq is L-N quantity

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
			case 1:  // Delta  or L-L
				Y    = Y.divide(3.0); // Convert to delta impedance
				Yij  = Y.negate();
				for (i = 0; i < nPhases; i++) {
					j = i + 1;
					if (j > nConds) j = 0;  // wrap around for closed connections
					Ymatrix.addElement(i, i, Y);
					Ymatrix.addElement(j, j, Y);
					Ymatrix.addElemSym(i, j, Yij);
				}
			}
		}
	}
	
	@Override
	public void calcYPrim() {

		// Build only shunt Yprim
		// Build a dummy Yprim Series so that CalcV does not fail
		if (isYprimInvalid()) {
			if (YPrim_Shunt != null) YPrim_Shunt = null;
			YPrim_Shunt = new CMatrixImpl(Yorder);
			if (YPrim_Series != null) YPrim_Series = null;
			YPrim_Series = new CMatrixImpl(Yorder);
			if (YPrim != null) YPrim = null;
			YPrim = new CMatrixImpl(Yorder);
		} else {
			YPrim_Shunt.clear();
			YPrim_Series.clear();
			YPrim.clear();
		}

		if (DSSGlobals.getInstance().getActiveCircuit().getSolution().getLoadModel() == DSSGlobals.POWERFLOW) {
		
			// 12-7-99 we'll start with Yeq in system matrix
			setNominalGeneration();
			calcYPrimMatrix(YPrim_Shunt);

		} else {

			// ADMITTANCE model wanted
			setNominalGeneration();
			calcYPrimMatrix(YPrim_Shunt);

		}

		// Set YPrim_Series based on diagonals of YPrim_shunt  so that CalcVoltages doesn't fail
		for (int i = 0; i < Yorder; i++)
			YPrim_Series.setElement(i, i, YPrim_Shunt.getElement(i, i).multiply(1.0e-10));
		
		YPrim.copyFrom(YPrim_Shunt);

		// Account for Open Conductors
		super.calcYPrim();
	}
	
	/**
	 * Add the current into the proper location according to connection.
	 * 
	 * Reverse of similar routine in load  (complex negates are switched).
	 */
	private void stickCurrInTerminalArray(Complex[] TermArray, Complex Curr, int i) {
		switch (Connection) {
		case 0:  // Wye
			TermArray[i] = TermArray[i].add(Curr);
			TermArray[nConds] = TermArray[nConds].add(Curr.negate());  // Neutral

		case 1:  // DELTA
			TermArray[i] = TermArray[i].add(Curr);
			int j = i + 1;
			if (j > nConds) j = 0;
			TermArray[j] = TermArray[j].add(Curr.negate());
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
					TraceBuffer.write(getIterminal()[i].abs() + ", ");
				for (i = 0; i < nPhases; i++)
					TraceBuffer.write(getVterminal()[i].abs() + ", ");
				TraceBuffer.write(VThevMag + ", " + GenVars.Theta * 180.0 / Math.PI);
				TraceBuffer.newLine();
				TraceBuffer.close();
				TraceStream.close();
			}
		} catch (Exception e) {
			
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
		
		// Treat this just like the Load moVdel

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		zeroITerminal();

		/* Tried this but couldn't get it to work
		switch (nPhases) {
		case 3:  // Use Symmetrical Components
			phase2SymComp(Vterminal, V012);   // Vterminal is L-N voltages here
			// Phase2SymComp(InjCurrent, @I012);   // Vterminal is L-G voltages here
			V = V012[0]; // Positive sequence L-N voltage
			Vmag = V012[0].abs();

//			if (VMag <= VBase95) {
//				Curr = Yeq95.multiply(V).negate();  // Below 95% (Vminpu) use an impedance model
//			} else {
//				if (Vmag > VBase105)
//					Curr = Yeq105.multiply(V).negate();  // above 105% (Vmaxpu) use an impedance model
//			}
			if ((Vmag <= VBase95) || (Vmag > VBase105)) {
				Curr = CurrentLimit.divide(V.divide(-Vmag)).conjugate();
			} else {
				Curr = new Complex(-GenVars.Pnominalperphase, -GenVars.Qnominalperphase).divide(V).conjugate();    // Current into pos seq model
			}

			I012[0] = Curr;  // Pos sequence current into the terminal

			if (Connection == 1) {
				I012[0] = Complex.ZERO;
			} else {
				I012[0] = V012[0].divide(new Complex(0.0, xdpp));
			}
			I012[1] = V012[1].divide(new Complex(0.0, xdpp));

			// Negative and Zero Sequence Contributions
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
				ITerminal[i] = Iabc[i];  // Put into Terminal array directly because we have computed line current above
				Caccum(getInjCurrent()[i], Iabc[i].negate()));  // subtract in
				if (Connection == 0) {
					Caccum(getIterminal()[nConds], Iabc[i].negate());  // Neutral
					Caccum(getInjCurrent()[nConds], Iabc[i]);  // Neutral
				}
			}
			IterminalUpdated = true;  // so that we con't have to recompute for a report
		}
		*/


		calcVTerminalPhase();  // get actual voltage across each phase of the load
		for (i = 0; i < nPhases; i++) {
			V    = Vterminal[i];
			Vmag = V.abs();

			switch (Connection) {
			case 0:  /* Wye */
				if (Vmag <= VBase95) {
					Curr = Yeq95.multiply(V);  // Below 95% use an impedance model
				} else if (Vmag > VBase105) {
					Curr = Yeq105.multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(V).conjugate();  // Between 95% -105%, constant PQ
				}
			case 1:  /* Delta */
				Vmag = Vmag / DSSGlobals.SQRT3;  // L-N magnitude
				if (Vmag <= VBase95) {
					Curr = Yeq95.divide(3.0).multiply(V);  // Below 95% use an impedance model
				} else if (Vmag > VBase105) {
					Curr = Yeq105.divide(3.0).multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(V).conjugate();  // Between 95% -105%, constant PQ
				}
			}

			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}
	
	private void doConstantZGen() {
		int i;
		Complex Curr, Yeq2;

		// Assume Yeq is kept up to date
		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase();  // get actual voltage across each phase of the load
		zeroITerminal();
		if (Connection == 0) {
			Yeq2 = Yeq;
		} else {
			Yeq2 = Yeq.divide(3.0);
		}

		for (i = 0; i < nPhases; i++) {
			Curr = Yeq2.multiply(Vterminal[i]);   // Yeq is always line to neutral
			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}
	
	/**
	 * Compute total terminal current for Constant P, |V|.
	 */
	private void doPVTypeGen() {
		int i;
		double DQ;
		Complex Curr;

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase(); // get actual voltage across each phase of the generator
		zeroITerminal();

		// Guess at a new var output value
		V_Avg = 0.0;
		for (i = 0; i < nPhases; i++)
			V_Avg = V_Avg + Vterminal[i].abs();

		if (Connection == 1) {
			V_Avg = V_Avg / (DSSGlobals.SQRT3 * nPhases);
		} else {
			V_Avg = V_Avg / nPhases;
		}

		// 12-9-99 added empirical 0.7 factor to improve iteration
		// 12-17-99 changed to 0.1 because first guess was consistently too high
		DQ =  PVFactor * dQdV * (VTarget - V_Avg);   // Vtarget is L-N
		if (Math.abs(DQ) > DeltaQMax)
			if (DQ < 0.0) {
				DQ = -DeltaQMax;
			} else {
				DQ = DeltaQMax;
			}
		GenVars.Qnominalperphase = GenVars.Qnominalperphase + DQ;

		/* Test Limits */
		if (GenVars.Qnominalperphase > varMax) {
			GenVars.Qnominalperphase = varMax;
		} else if (GenVars.Qnominalperphase < varMin) {
			GenVars.Qnominalperphase = varMin;
		}

		// Compute injection currents using W and var values
		// Do not use comstant Z models outside normal range
		// Presumably the var source will take care of the voltage problems
		for (i = 0; i < nPhases; i++) {
			Curr = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(Vterminal[i]).conjugate();
			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}
	
	/**
	 * Compute total terminal current for Fixed Q.
	 * Constant P, Fixed Q  Q is always kvarBase.
	 */
	private void doFixedQGen() {
		int i;
		Complex Curr = null, V;
		double Vmag;

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = Vterminal[i];
			Vmag = V.abs();

			switch (Connection) {
			case 0:
				if (Vmag <= VBase95) {
					Curr = new Complex(Yeq95.getReal(), YQFixed).multiply(V);  // Below 95% use an impedance model
				} else if (Vmag > VBase105) {
					Curr = new Complex(Yeq105.getReal(), YQFixed).multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, varBase).divide(V).conjugate();
				}
			case 1:
				Vmag = Vmag / DSSGlobals.SQRT3;  // Convert to L-N for test
				if (Vmag <= VBase95) {
					Curr = new Complex(Yeq95.getReal() / 3.0, YQFixed / 3.0).multiply(V);  // Below 95% use an impedance model
				} else if(Vmag > VBase105) {
					Curr = new Complex(Yeq105.getReal() / 3.0, YQFixed / 3.0).multiply(V);  // above 105% use an impedance model
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, varBase).divide(V).conjugate();
				}
			}
			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
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

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = Vterminal[i];
			Vmag = V.abs();

			switch (Connection) {
			case 0:
				if (Vmag <= VBase95) {
					Curr = new Complex(Yeq95.getReal(), YQFixed).multiply(V);  // Below 95% use an impedance model
				} else if (Vmag > VBase105) {
					Curr = new Complex(Yeq105.getReal(), YQFixed).multiply(V);
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, 0.0).divide(V).conjugate(); // P component of current
					Curr = Curr.add(new Complex(0.0, YQFixed).multiply(V));  // add in Q component of current
				}
			case 1:
				Vmag = Vmag / DSSGlobals.SQRT3;  // Convert to L-N for test
				if (Vmag <= VBase95) {
					Curr = new Complex(Yeq95.getReal() / 3.0, YQFixed / 3.0).multiply(V);  // Below 95% use an impedance model
				} else  if (Vmag > VBase105) {
					Curr = new Complex(Yeq105.getReal() / 3.0, YQFixed / 3.0).multiply(V);
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, 0.0).divide(V).conjugate(); // P component of current
					Curr = Curr.add(new Complex(0.0, YQFixed / 3.0).multiply(V));  // add in Q component of current
				}
			}

			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}
	
	/**
	 * Compute total terminal current from User-written model.
	 */
	private void doUserModel() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array

		if (UserModel.exists()) {  // Check automatically selects the usermodel if true
			//appendToEventLog("Wnominal=", String.format("%-.5g", Pnominalperphase));
			UserModel.fCalc(Vterminal, Iterminal);
			setITerminalUpdated(true);
			SolutionObj sol = Globals.getActiveCircuit().getSolution();
			// Negate currents from user model for power flow generator model
			for (int i = 0; i < nConds; i++) 
				getInjCurrent()[i] = getInjCurrent()[i].add( getIterminal()[i].negate() );
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

		// Treat this just like the load model

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array
		calcVTerminalPhase(); // get actual voltage across each phase of the load
		zeroITerminal();

		for (i = 0; i < nPhases; i++) {
			V    = Vterminal[i];
			Vmag = V.abs();

			switch (Connection) {
			case 0:
				if ((Vmag <= VBase95) || (Vmag > VBase105)) {  // limit the current magnitude when voltage drops outside normal range
					Curr = CurrentLimit.divide( V.divide(Vmag) ).conjugate();  // Current limit expression
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(V).conjugate();  // Above VminPU, constant PQ
				}
			case 1:
				VmagLN = Vmag / DSSGlobals.SQRT3;
				if ((VmagLN <= VBase95) || (VmagLN > VBase105)) {  // limit the current magnitude when voltage drops outside normal range
					Curr = CurrentLimit.divide( V.divide(Vmag) ).conjugate();  // Current limit expression
				} else {
					Curr = new Complex(GenVars.Pnominalperphase, GenVars.Qnominalperphase).divide(V).conjugate();  // Above Vminpu, constant PQ
				}
			}

			stickCurrInTerminalArray(getIterminal(), Curr.negate(), i);  // Put into Terminal array taking into account connection
			setITerminalUpdated(true);
			stickCurrInTerminalArray(getInjCurrent(), Curr, i);  // Put into Terminal array taking into account connection
		}
	}
	
	/**
	 * Compute total current and add into InjTemp.
	 */
	private void doDynamicMode() {
		int i;
		Complex[] V012 = new Complex[2];
		Complex[] I012 = new Complex[2];

		calcYPrimContribution(getInjCurrent());  // Init InjCurrent Array

		/* Inj = -Itotal (in) - Yprim * Vtemp */
		if ((GenModel = 6) && UserModel.exists()) {  // auto selects model
			/* We have total currents in Itemp */
			UserModel.fCalc(Vterminal, Iterminal);  // returns terminal currents in Iterminal
		} else {
			/* No user model, use default Thevinen equivalent */
			switch (nPhases) {
			case 1:
				calcVThevDyn();  // Update for latest phase angle

				getIterminal()[0] = getVterminal()[0].subtract(Vthev).subtract(getVterminal()[1]).divide(new Complex(0.0, GenVars.Xdp));
				getIterminal()[1] = getIterminal()[0].negate();

			case 3: 
				MathUtil.phase2SymComp(Vterminal, V012);

				// Positive sequence contribution to Iterminal
				calcVThevDyn();  // Update for latest phase angle

				// Positive Sequence Contribution to Iterminal
				I012[0] = V012[0].subtract(Vthev).divide(new Complex(0.0, GenVars.Xdp));
				I012[1] = V012[1].divide(new Complex(0.0, GenVars.Xdpp));
				if (Connection == 1) {
					I012[0] = Complex.ZERO;  // TODO Check zero based indexing
				} else {
					I012[0] = V012[0].divide(new Complex(0.0, GenVars.Xdpp));
				}
				MathUtil.symComp2Phase(getIterminal(), I012);  // Convert back to phase components

				// Neutral current
				if (Connection == 0)
					getIterminal()[nConds] = I012[0].multiply(3.0).negate();
			default:
				DSSGlobals.getInstance().doSimpleMsg(String.format("Dynamics mode is implemented only for 1- or 3-phase generators. Generator."+getName()+" has %d phases.", nPhases), 5671);
				DSSGlobals.getInstance().setSolutionAbort(true);
			}
		}

		setITerminalUpdated(true);

		/* Add it into inj current array */
		for (i = 0; i < nConds; i++)
			getInjCurrent()[i] = getInjCurrent()[i].add( getIterminal()[i].negate() );

		/* Take Care of any shaft model calcs */
		if ((GenModel == 6) && ShaftModel.exists()) {  // auto selects model
			// Compute Mech Power to shaft
			ShaftModel.fCalc(getVterminal(), getIterminal());  // Returns pshaft at least
		}
	}
	
	/**
	 * Compute Injection Current Only when in harmonics mode.
	 * 
	 * Assumes spectrum is a voltage source behind subtransient reactance and YPrim has been built.
	 * Vd is the fundamental frequency voltage behind Xd" for phase 1.
	 */
	private void doHarmonicMode() {
		int i;
		Complex E;
		double GenHarmonic;

		computeVterminal();

		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
		GenHarmonic = sol.getFrequency() / GenFundamental;
		E = getSpectrumObj().getMult(GenHarmonic).multiply(VThevHarm); // Get base harmonic magnitude
		Utilities.rotatePhasorRad(E, GenHarmonic, ThetaHarm);  // Time shift by fundamental frequency phase shift
		for (i = 0; i < nPhases; i++) {
			cBuffer[i] = E;
			if (i < nPhases)
				Utilities.rotatePhasorDeg(E, GenHarmonic, -120.0);  // Assume 3-phase generator
		}

		/* Handle wye connection */
		if (Connection == 0)
			cBuffer[nConds] = getVterminal()[nConds];  // assume no neutral injection voltage

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
				getVterminal()[i] = sol.vDiff(NodeRef[i], NodeRef[nConds]);

		case 1:
			for (i = 0; i < nPhases; i++) {
				j = i + 1;
				if (j > nConds) j = 0;
				getVterminal()[i] = sol.vDiff(NodeRef[i], NodeRef[j]);
			}
		}

		GeneratorSolutionCount = sol.getSolutionCount();	
	}
	
	/**
	 * Put terminal voltages in an array.
	 */
	private void calcVterminal() {

		computeVterminal();

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
		if (sol.isIsDynamicModel()) {
			doDynamicMode();
		} else if (sol.isIsHarmonicModel() && (sol.getFrequency() != ckt.getFundamental())) {
			doHarmonicMode();
		} else {
			// compute currents and put into InjTemp array
			switch (GenModel) {
			case 1:
				doConstantPQGen();
			case 2:
				doConstantZGen();
			case 3:
				doPVTypeGen();  // Constant P, |V|
			case 4:
				doFixedQGen();
			case 5:
				doFixedQZGen();
			case 6:
				doUserModel();
			case 7:
				doCurrentLimitedPQ();
			default:
				doConstantPQGen();  // for now, until we implement the other models.
			}
		}
		/* When this is done, ITerminal is up to date */
	}
	
	@Override
	public int injCurrents() {
		return 0;
	}
	
	@Override
	public void getInjCurrents(Complex[] Curr) {
		
	}
	
	@Override
	public int numVariables() {
		return 0;
	}
	
	@Override
	public void getAllVariables(double[] States) {
		
	}
	
	@Override
	public double getVariable(int i) {
		return 0.0;
	}
	
	@Override
	public void setVariable(int i, double Value) {
		
	}
	
	@Override
	public String variableName(int i) {
		return null;
	}
	
	public void resetRegisters() {
		
	}
	
	public void takeSample() {
		
	}
	
	/* Procedures for setting the DQDV used by the Solution Object */
	public void initDQDVCalc() {
		
	}
	
	public void bumpUpQ() {
		
	}
	
	public void rememberQV() {
		
	}
	
	public void calcDQDV() {
		
	}
	
	public void resetStartPoint() {
		
	}
	
	/* Support for Dynamics Mode */
	
	@Override
	public void initStateVars() {
		
	}
	
	@Override
	public void integrateStates() {
		
	}
	
	/* Support for Harmonics Mode */
	
	@Override
	public void initHarmonics() {
		
	}
	
	/* Make a positive Sequence Model */
	@Override
	public void makePosSequence() {
		
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override 
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}
	
	@Override
	public String getPropertyValue(int Index) {
		return null;
	}
	
	private void calcInjCurrentArray() {
		
	}
	
	/* 3-phase Voltage behind transient reactance */
	private void calcVThevDyn() {
		
	}

	private void integrate(int Reg, double Deriv, double Interval) {
		
	}
	
	private void setDragHandRegister(int Reg, double Value) {
		
	}

	private void syncUpPowerQuantities() {
		
	}

	public double getPresentkW() {
		return 0.0;
	}
	
	public double getPresentKVar() {
		return 0.0;
	}
	
	public double getPresentKV() {
		return 0.0;
	}
	
	public void setPresentKV(double Value) {
		
	}
	
	public void setPresentKVar(double Value) {
		
	}
	
	public void setPresentKW(double Value) {
		
	}
	
	public boolean isForcedON() {
		return ForcedON;
	}

	public void setForcedON(boolean forcedON) {
		ForcedON = forcedON;
	}

	public void setPowerFactor(double Value) {
		
	}
	
	public double getPowerFactor() {
		return PFNominal;
	}

	private void setKwKVar(double PkW, double QkVar) {
		
	}
	
	@Override
	public void setConductorClosed(int Index, boolean Value) {
		
	}
	
	@Override
	protected void getTerminalCurrents(Complex[] Curr) {
		
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

	public double getKvarBase() {
		return kvarBase;
	}

	public void setKvarBase(double kvarBase) {
		this.kvarBase = kvarBase;
	}

	public double getKvarMax() {
		return kvarMax;
	}

	public void setKvarMax(double kvarMax) {
		this.kvarMax = kvarMax;
	}

	public double getKvarMin() {
		return kvarMin;
	}

	public void setKvarMin(double kvarMin) {
		this.kvarMin = kvarMin;
	}

	public double getkWBase() {
		return kWBase;
	}

	public void setkWBase(double kWBase) {
		this.kWBase = kWBase;
	}

	public double getPFNominal() {
		return PFNominal;
	}

	public void setPFNominal(double pFNominal) {
		PFNominal = pFNominal;
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

	public double getdQdV() {
		return dQdV;
	}

	public void setdQdV(double dQdV) {
		this.dQdV = dQdV;
	}

	public double getdQdVSaved() {
		return dQdVSaved;
	}

	public void setdQdVSaved(double dQdVSaved) {
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

	public boolean isGenON() {
		return GenON;
	}

	public void setGenON(boolean genON) {
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

	public int getReg_Hours() {
		return Reg_Hours;
	}

	public void setReg_Hours(int reg_Hours) {
		Reg_Hours = reg_Hours;
	}

	public int getReg_kvarh() {
		return Reg_kvarh;
	}

	public void setReg_kvarh(int reg_kvarh) {
		Reg_kvarh = reg_kvarh;
	}

	public int getReg_kWh() {
		return Reg_kWh;
	}

	public void setReg_kWh(int reg_kWh) {
		Reg_kWh = reg_kWh;
	}

	public int getReg_MaxkVA() {
		return Reg_MaxkVA;
	}

	public void setReg_MaxkVA(int reg_MaxkVA) {
		Reg_MaxkVA = reg_MaxkVA;
	}

	public int getReg_MaxkW() {
		return Reg_MaxkW;
	}

	public void setReg_MaxkW(int reg_MaxkW) {
		Reg_MaxkW = reg_MaxkW;
	}

	public int getReg_Price() {
		return Reg_Price;
	}

	public void setReg_Price(int reg_Price) {
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

	public double getV_Remembered() {
		return V_Remembered;
	}

	public void setV_Remembered(double v_Remembered) {
		V_Remembered = v_Remembered;
	}

	public double getVar_Remembered() {
		return var_Remembered;
	}

	public void setVar_Remembered(double var_Remembered) {
		this.var_Remembered = var_Remembered;
	}

	public double getVarBase() {
		return varBase;
	}

	public void setVarBase(double varBase) {
		this.varBase = varBase;
	}

	public double getVarMax() {
		return varMax;
	}

	public void setVarMax(double varMax) {
		this.varMax = varMax;
	}

	public double getVarMin() {
		return varMin;
	}

	public void setVarMin(double varMin) {
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

	public Complex getVthev() {
		return Vthev;
	}

	public void setVthev(Complex vthev) {
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

}
