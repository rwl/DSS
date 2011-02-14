package com.epri.dss.common.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.Solution;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.YMatrix.Esolv32Problem;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.conversion.Load;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.Dynamics;
import com.epri.dss.shared.impl.DynamicsImpl.DynamicsRec;

public class SolutionObjImpl extends DSSObjectImpl implements SolutionObj {

	public class ControlProblem extends Exception {

	}

	/** Raised when solution aborted */
	public class SolveError extends Exception {

	}

	/* Array of delta V for Newton iteration */
	private Complex[] dV;
	private double Frequency;

	protected int Algorithm;      // NORMALSOLVE or NEWTONSOLVE
	protected Complex[] AuxCurrents;  // For injections like AutoAdd
	protected boolean ControlActionsDone;
	protected int ControlIteration;
	protected int ControlMode;     // EVENTDRIVEN, TIMEDRIVEN
	protected double ConvergenceTolerance;
	protected boolean ConvergedFlag;
	protected int DefaultControlMode;    // EVENTDRIVEN, TIMEDRIVEN
	protected int DefaultLoadModel;     // 1=POWERFLOW  2=ADMITTANCE
	protected boolean DoAllHarmonics;
	protected boolean DynamicsAllowed;
	protected DynamicsRec DynaVars;
	protected double[] ErrorSaved;
	protected boolean FirstIteration;
	/* Flag set to true if something has altered the frequency */
	protected boolean FrequencyChanged;
	protected int year;
	protected double Harmonic;
	protected double[] HarmonicList;
	protected int HarmonicListSize;
	protected int intHour;
	protected double dblHour;
	/* Main (system) Y matrix */
	protected CMatrix Ysystem;
	/* Series Y matrix */
	protected CMatrix Yseries;
	/* either Ysystem or Yseries */
	protected CMatrix Y;
	protected double IntervalHrs;   // Solution interval since last solution, hrs.
	protected boolean IsDynamicModel;
	protected boolean IsHarmonicModel;
	protected int Iteration;
	protected int LoadModel;        // 1=POWERFLOW  2=ADMITTANCE
	protected boolean LastSolutionWasDirect;
	protected boolean LoadsNeedUpdating;
	protected int MaxControlIterations;
	protected double MaxError;
	protected int MaxIterations;
	protected int MostIterationsDone;
	protected double[] NodeVbase;
	protected int NumberOfTimes;  // Number of times to solve
	protected boolean PreserveNodeVoltages;
	protected int RandomType;     //0 = none; 1 = gaussian; 2 = UNIFORM
	protected boolean SeriesYInvalid;
	protected int SolutionCount;  // Counter incremented for each solution
	protected boolean SolutionInitialized;
	protected boolean SystemYChanged;
	protected boolean UseAuxCurrents;
	protected double[] VmagSaved;
	protected boolean VoltageBaseChanged;

	/* Main System Voltage Array */
	protected Complex[] NodeV;
	/* Main System Currents Array */
	protected Complex[] Currents;

	public SolutionObjImpl(DSSClass parClass, String solutionName) {
		super(parClass);
		setName(solutionName.toLowerCase());

		this.year    = 0;
		this.intHour = 0;
		this.DynaVars.t = 0.0;
		this.dblHour = 0.0;
		this.DynaVars.tstart = 0.0;
		this.DynaVars.tstop = 0.0;
		//duration = 0.0;
		this.DynaVars.h = 0.001;  // default for dynasolve

		this.LoadsNeedUpdating = true;
		this.VoltageBaseChanged = true;  // Forces Building of convergence check arrays

		this.MaxIterations = 15;
		this.MaxControlIterations = 10;
		this.ConvergenceTolerance = 0.0001;
		this.ConvergedFlag = false;

		this.IsDynamicModel  = false;
		this.IsHarmonicModel = false;

		this.Frequency = DSSGlobals.getInstance().getDefaultBaseFreq();
		/*this.Fundamental = 60.0; Moved to Circuit and used as default base frequency*/
		this.Harmonic = 1.0;
		
		this.FrequencyChanged = true;  // Force Building of YPrim matrices
		this.DoAllHarmonics   = true;
		this.FirstIteration   = true;
		this.DynamicsAllowed  = false;
		this.SystemYChanged   = true;
		this.SeriesYInvalid   = true;

		/* Define default harmonic list */
		this.HarmonicListSize = 5;
		this.HarmonicList = new double[this.HarmonicListSize];
		this.HarmonicList[0] = 1.0;
		this.HarmonicList[1] = 5.0;
		this.HarmonicList[2] = 7.0;
		this.HarmonicList[3] = 11.0;
		this.HarmonicList[4] = 13.0;

		this.SolutionInitialized = false;
		this.LoadModel = DSSGlobals.POWERFLOW;
		this.DefaultLoadModel = LoadModel;
		this.LastSolutionWasDirect = false;

		this.Yseries = null;
		this.Ysystem = null;
		this.Y = null;

		this.NodeV      = null;
		this.dV         = null;
		this.Currents   = null;
		this.AuxCurrents= null;
		this.VmagSaved  = null;
		this.ErrorSaved = null;
		this.NodeVbase  = null;

		this.UseAuxCurrents = false;

		this.SolutionCount = 0;

		this.DynaVars.SolutionMode = Dynamics.SNAPSHOT;
		this.ControlMode           = DSSGlobals.CTRLSTATIC;
		this.DefaultControlMode    = ControlMode;
		this.Algorithm             = Solution.NORMALSOLVE;

		this.RandomType    = DSSGlobals.GAUSSIAN;  // default to gaussian
		this.NumberOfTimes = 100;
		this.IntervalHrs   = 1.0;

		initPropertyValues(0);
	}

	/**
	 * Main solution dispatch.
	 */
	public void solve() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setIsSolved(false);
		Globals.setSolutionWasAttempted(true);

		DSSForms.initProgressForm(); // initialize Progress Form;

		/* Check of some special conditions that must be met before executing solutions */

		if (Globals.getActiveCircuit().getEmergMinVolts() >= Globals.getActiveCircuit().getNormalMinVolts()) {
			Globals.doSimpleMsg("Error: Emergency Min Voltage Must Be Less Than Normal Min Voltage!" +
					DSSGlobals.CRLF + "Solution Not Executed.", 480);
			return;
		}

		if (Globals.isSolutionAbort()) {
			Globals.setGlobalResult("Solution aborted.");
			Globals.setCmdResult(DSSGlobals.SOLUTION_ABORT);
			Globals.setErrorNumber(Globals.getCmdResult());
			return;
		}

		try {

			/* Main solution algorithm dispatcher */
			Circuit ckt = Globals.getActiveCircuit();
		
			switch (year) {
			case 0:  // TODO Check zero based indexing
				ckt.setDefaultGrowthFactor(1.0);
			default:
				ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (year - 1)));
			}
			
//			fire_InitControls();

			/* CheckFaultStatus;  ???? needed here?? */
			
			switch (DynaVars.SolutionMode) {
			case Dynamics.SNAPSHOT:
				solveSnap();
			case Dynamics.YEARLYMODE:
				SolutionAlgs.solveYearly();
			case Dynamics.DAILYMODE:
				SolutionAlgs.solveDaily();
			case Dynamics.DUTYCYCLE:
				SolutionAlgs.solveDuty();
			case Dynamics.DYNAMICMODE:
				SolutionAlgs.solveDynamic();
			case Dynamics.MONTECARLO1:
				SolutionAlgs.solveMonte1();
			case Dynamics.MONTECARLO2:
				SolutionAlgs.solveMonte2();
			case Dynamics.MONTECARLO3:
				SolutionAlgs.solveMonte3();
			case Dynamics.PEAKDAY:
				SolutionAlgs.solvePeakDay();
			case Dynamics.LOADDURATION1:
				SolutionAlgs.solveLD1();
			case Dynamics.LOADDURATION2:
				SolutionAlgs.solveLD2();
			case Dynamics.DIRECT:
				solveDirect();
			case Dynamics.MONTEFAULT:
				SolutionAlgs.solveMonteFault();  // Monte Carlo Fault Cases
			case Dynamics.FAULTSTUDY:
				SolutionAlgs.solveFaultStudy();
			case Dynamics.AUTOADDFLAG:
				ckt.getAutoAddObj().solve();
			case Dynamics.HARMONICMODE:
				SolutionAlgs.solveHarmonic();
			case Dynamics.GENERALTIME:
				SolutionAlgs.solveGeneralTime();
			default:
				Globals.doSimpleMsg("Unknown solution mode.", 481);
			}
		} catch (Esolv32Problem e) {
			Globals.doSimpleMsg("Error Encountered in Solve: " + e.getMessage(), 482);
			Globals.setSolutionAbort(true);
		}
	}

	private boolean converged() {
		boolean Result;
		double VMag;
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		// base convergence on voltage magnitude

		MaxError = 0.0;
		for (int i = 0; i < ckt.getNumNodes(); i++) {
			VMag = NodeV[i].abs();

			/* If base specified, use it; otherwise go on present magnitude */
			if (NodeVbase[i] > 0.0) {
				ErrorSaved[i] = Math.abs(VMag - VmagSaved[i]) / NodeVbase[i];
			} else {
				if (VMag != 0.0)
					ErrorSaved[i] = Math.abs(1.0 - VmagSaved[i] / VMag);
			}

			VmagSaved[i] = VMag;  // for next go-'round

			MaxError = Math.max(MaxError, ErrorSaved[i]);  // update max error
		}

		/* $IFDEF debugtrace */
//		FileWriter F = new FileWriter("DebugTrace.csv", true);
//		BufferedWriter FDebug = new BufferedWriter(F);
//		if (Iteration == 1) {
//			FDebug.write("Iter");
//			for (int i = 0; i < ckt.getNumNodes(); i++) 
//				FDebug.write(", " + ckt.getBusList().get(ckt.getMapNodeToBus()[i].BusRef) + "." + ckt.getMapNodeToBus()[i].NodeNum);  // TODO Implement colon syntax
//			FDebug.newLine();
//		}
//		/* ***** */  // FIXME Format number widths
//		FDebug.write(Iteration);
//		for (int i = 0; i < ckt.getNumNodes(); i++) 
//			FDebug.write(", " + VmagSaved[i]);
//		FDebug.newLine();
//		FDebug.write("Err");
//		for (int i = 0; i < ckt.getNumNodes(); i++) 
//			FDebug.write(", " + String.format("%-.5g", ErrorSaved[i]));
//		FDebug.newLine();
//		FDebug.write("Curr");
//		for (int i = 0; i < ckt.getNumNodes(); i++) 
//			FDebug.write(", " + Currents[i].abs());
//		FDebug.newLine();
//		/* ***** */
//		FDebug.close();
		/* $ENDIF */

		if (MaxError <= ConvergenceTolerance) {
			Result = true;
		} else {
			Result = false;
		}

		ConvergedFlag = Result;
		
		return Result;
	}

	/**
	 * Add in the contributions of all source type elements to the global
	 * solution vector InjCurr.
	 */
	private void getSourceInjCurrents() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		
		for (CktElement pElem : ckt.getSources()) 
			if (pElem.isEnabled())
				pElem.injCurrents();  // uses NodeRef to add current into InjCurr Array;

		if (IsHarmonicModel) {  // Pick up generators and Loads, too

			for (GeneratorObj pElem : ckt.getGenerators()) 
				if (pElem.isEnabled())
					pElem.injCurrents();  // uses NodeRef to add current into InjCurr Array;
				
			for (LoadObj pElem : ckt.getLoads()) 
				if (pElem.isEnabled())
					pElem.injCurrents();  // uses NodeRef to add current into InjCurr Array;
		}
	}

	/**
	 * Set the global generator dispatch reference.
	 */
	public void setGeneratorDispRef() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		
		switch (DynaVars.SolutionMode) {
		case Dynamics.SNAPSHOT:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor());
		case Dynamics.YEARLYMODE:
			ckt.setGeneratorDispatchReference(ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
		case Dynamics.DAILYMODE:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
		case Dynamics.DUTYCYCLE:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
		case Dynamics.GENERALTIME:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
		case Dynamics.DYNAMICMODE: 
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor());
		case Dynamics.HARMONICMODE:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor());
		case Dynamics.MONTECARLO1:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor());
		case Dynamics.MONTECARLO2:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
		case Dynamics.MONTECARLO3:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
		case Dynamics.PEAKDAY:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
		case Dynamics.LOADDURATION1:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
		case Dynamics.LOADDURATION2:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
		case Dynamics.DIRECT:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor());
		case Dynamics.MONTEFAULT:
			ckt.setGeneratorDispatchReference(1.0);  // Monte Carlo Fault Cases solve  at peak load only base case
		case Dynamics.FAULTSTUDY:
			ckt.setGeneratorDispatchReference(1.0);
		case Dynamics.AUTOADDFLAG:
			ckt.setGeneratorDispatchReference(ckt.getDefaultGrowthFactor());   // peak load only
		default:
			DSSGlobals.getInstance().doSimpleMsg("Unknown solution mode.", 483);
		}
	}

	private void setGeneratordQdV() {
		boolean Did_One = false;
		double GenDispSave;
		
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		// Save the generator dispatch level and set on high enough to
		// turn all generators on
		GenDispSave = ckt.getGeneratorDispatchReference();
		ckt.setGeneratorDispatchReference(1000.0);
		
		for (GeneratorObj pGen : ckt.getGenerators()) {
			if (pGen.isEnabled()) {

				// for PV generator models only ...
				if (pGen.getGenModel() == 3) {
				
					pGen.initDQDVCalc();

					// solve at base var setting
					Iteration = 0;
					while (!converged() && (Iteration < MaxIterations)) {
						Iteration += 1;
						zeroInjCurr();
						getSourceInjCurrents();
						pGen.injCurrents();  // get generator currents with nominal vars
						SolveSystem(NodeV);
					}

					pGen.rememberQV();  // Remember Q and V
					pGen.bumpUpQ();

					// solve after changing vars
					Iteration = 0;
					while (!converged() && (Iteration < MaxIterations)) {
						Iteration += 1;
						zeroInjCurr();
						getSourceInjCurrents();
						pGen.injCurrents();  // get generator currents with nominal vars
						SolveSystem(NodeV);
					}

					pGen.calcDQDV(); // bssed on remembered Q and V and present values of same
					pGen.resetStartPoint();

					Did_One = true;
				}
			}
		}
		
		// Restore generator dispatch reference
		ckt.setGeneratorDispatchReference(GenDispSave);
		try {
			if (Did_One)  // Reset Initial Solution
				solveZeroLoadSnapShot();
		} catch (Esolv32Problem e) {
			DSSGlobals.getInstance().doSimpleMsg("From SetGenerator dQdV, SolveZeroLoadSnapShot: " + DSSGlobals.CRLF + e.getMessage()  + YMatrix.checkYMatrixforZeroes(), 7071);
			throw new SolveError("Aborting");
		}
	}

	/**
	 * Normal fixed-point solution.
	 * 
	 *   Vn+1 = [Y]-1 Injcurr
	 *   
	 * Where Injcurr includes only PC elements (loads, generators, etc.)
	 * i.e., the shunt elements.
	 * 
	 * Injcurr are the current injected into the node (need to reverse
	 * current direction for loads).
	 */
	private void doNormalSolution() {
		Iteration = 0;
		
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		/* **** Main iteration loop **** */
		while ((!converged() || (Iteration <= 1)) && (Iteration < MaxIterations)) {  // TODO Double-check reverse logic
			Iteration += 1;

			if (ckt.isLogEvents())
				Utilities.logThisEvent("Solution Iteration " + String.valueOf(Iteration));

			/* Get injcurrents for all PC devices */
			zeroInjCurr();
			getSourceInjCurrents();  // sources
			getPCInjCurr();  // Get the injection currents from all the power conversion devices and feeders

			// The above call could change the primitive Y matrix, so have to check
			if (SystemYChanged)
				YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, false);  // Does not realloc V, I

			if (UseAuxCurrents)
				addInAuxCurrents(Solution.NORMALSOLVE);

			// Solve for voltages          /* Note: NodeV[0] = 0 + j0 always */
			if (ckt.isLogEvents())
				Utilities.logThisEvent("Solve Sparse Set doNormalSolution ...");
			SolveSystem(NodeV);
			LoadsNeedUpdating = false;
		}
	}

	/**
	 * Newton iteration.
	 *   
	 *   Vn+1 =  Vn - [Y]-1 Termcurr
	 *   
	 * Where Termcurr includes currents from all elements and we are
	 * attempting to get the  currents to sum to zero at all nodes.
	 * 
	 * Termcurr is the sum of all currents going into the terminals of
	 * the elements.
	 * 
	 * For PD Elements, Termcurr = Yprim*V
	 * 
	 * For Loads, Termcurr = (Sload/V)*
	 * For Generators, Termcurr = -(Sgen/V)*
	 */
	private void doNewtonSolution() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		
		dV = (Complex[]) Utilities.resizeArray(dV, ckt.getNumNodes() + 1); // Make sure this is always big enough

		if (ControlIteration == 1)
			getPCInjCurr();  // Update the load multipliers for this solution

		Iteration = 0;
		while ((!converged() || (Iteration <= 1)) && (Iteration < MaxIterations)) {  // TODO Double-check reverse logic
			Iteration += 1;
			SolutionCount += 1;  // SumAllCurrents Uses ITerminal  So must force a recalc

			// Get sum of currents at all nodes for all  devices
			zeroInjCurr();
			sumAllCurrents();

			// Call to current calc could change YPrim for some devices
			if (SystemYChanged)
				YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, false);  // Does not realloc V, I

			if (UseAuxCurrents) 
				addInAuxCurrents(Solution.NEWTONSOLVE);

			// Solve for change in voltages
			SolveSystem(dV);

			LoadsNeedUpdating = false;

			// Compute new guess at voltages
			for (int i = 0; i < ckt.getNumNodes(); i++) {  // 0 node is always 0
				NodeV[i] = new Complex(
						NodeV[i].getReal() - dV[i].getReal(),
						NodeV[i].getImaginary() - dV[i].getImaginary());
			}
		}
	}

	public void doPFlowSolution() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		SolutionCount += 1;    // Unique number for this solution

		if (VoltageBaseChanged)
			YMatrix.initializeNodeVbase(); // for convergence test

		if (!SolutionInitialized) {
			
			if (Globals.getActiveCircuit().isLogEvents())
				Utilities.logThisEvent("Initializing Solution");
			try {
				//solveZeroLoadSnapShot();
				solveYDirect();  // 8-14-06 This should give a better answer than zero load snapshot
			} catch (Esolv32Problem e) {
				Globals.doSimpleMsg("From doPFlowSolution().solveYDirect(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7072);
				throw new SolveError("Aborting");
			}
			if (Globals.isSolutionAbort())
				return;  // Initialization can result in abort

			try {
				setGeneratordQdV();  // Set dQdV for Model 3 generators
			} catch (Esolv32Problem e) { 
				Globals.doSimpleMsg("From doPFlowSolution.setGeneratordQdV(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7073);
				throw new SolveError("Aborting");
			}

			/* The above resets the active sparse set to hY */
			SolutionInitialized = true;
		}

		switch (Algorithm) {
		case Solution.NORMALSOLVE:
			doNormalSolution();
		case Solution.NEWTONSOLVE:
			doNewtonSolution();
		}

		Globals.getActiveCircuit().setIsSolved(ConvergedFlag);
		LastSolutionWasDirect = false;
	}

	/**
	 * Solve without load for initialization purposes.
	 */
	public int solveZeroLoadSnapShot() {
		
		if (SystemYChanged || SeriesYInvalid)
			YMatrix.buildYMatrix(YMatrix.SERIESONLY, true);  // Side Effect: Allocates V

		SolutionCount += 1;    //Unique number for this solution

		zeroInjCurr();   // Side Effect: Allocates InjCurr
		getSourceInjCurrents();    // Vsource and Isource only

		/* Make the series Y matrix the active matrix */
		if (Yseries == null)
			throw new Esolv32Problem("Series Y matrix not built yet in solveZeroLoadSnapshot().");
		Y = Yseries;

		if (DSSGlobals.getInstance().getActiveCircuit().isLogEvents())
			Utilities.logThisEvent("Solve Sparse Set ZeroLoadSnapshot ...");

		SolveSystem(NodeV);  // also sets voltages in radial part of the circuit if radial solution

		/* Reset the main system Y as the solution matrix */
		if ((Ysystem != null) && !DSSGlobals.getInstance().isSolutionAbort())
			Y = Ysystem;
			
		return 0;
	}

	/**
	 * Set voltage bases using voltage at first node (phase) of a bus.
	 */
	public void setVoltageBases() {
		boolean bZoneCalc, bZoneLock;
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			// don't allow the meter zones to auto-build in this load flow
			// solution, because the voltage bases are not available yet

			bZoneCalc = ckt.isMeterZonesComputed();
			bZoneLock = ckt.isZonesLocked();
			ckt.setMeterZonesComputed(true);
			ckt.setZonesLocked(true);

			solveZeroLoadSnapShot();

			for (int i = 0; i < ckt.getNumBuses(); i++) {
				Bus bus = ckt.getBuses()[i];
				bus.setkVBase( Utilities.nearestBasekV( NodeV[bus.getRef(0)].abs() * 0.001732 ) / DSSGlobals.SQRT3);  // l-n base kV  TODO Check zero based indexing
			}

			YMatrix.initializeNodeVbase();  // for convergence test

			ckt.setIsSolved(true);

			// now build the meter zones
			ckt.setMeterZonesComputed(bZoneCalc);
			ckt.setZonesLocked(bZoneLock);
			ckt.doResetMeterZones();

		} catch (Esolv32Problem e) {
			DSSGlobals.getInstance().doSimpleMsg("From setVoltageBases().solveZeroLoadSnapShot(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7075);
			throw new SolveError("Aborting");
		}
	}

	public void snapShotInit() {
		setGeneratorDispRef();
		ControlIteration   = 0;
		ControlActionsDone = false;
		MostIterationsDone = 0;
		LoadsNeedUpdating  = true;  // Force the loads to update at least once
	}

	/**
	 * Snapshot checks with matrix rebuild.
	 */
	public void checkControls() {
		if (ControlIteration < MaxControlIterations) {
			if (ConvergedFlag) {
				if (DSSGlobals.getInstance().getActiveCircuit().isLogEvents())
					Utilities.logThisEvent("Control Iteration " + String.valueOf(ControlIteration));
				sample_DoControlActions();
				checkFaultStatus();
			} else {
				ControlActionsDone = true;  // Stop solution process if failure to converge
			}
		}

		if (SystemYChanged)
			YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, false);  // Rebuild Y matrix, but V stays same
	}

	private boolean oKForDynamics(int Value) {
		return false;
	}

	private boolean oKForHarmonics(int Value) {
		return false;
	}

	private int SolveSystem(Complex[] V) {
		return 0;
	}

	private void addInAuxCurrents(int SolveType) {

	}

	private void getMachineInjCurrents() {

	}

	private void getPCInjCurr() {

	}

	public void setFrequency(double Value) {

	}

	public double getFrequency() {
		return Frequency;
	}

	public void setMode(int Value) {

	}

	public int getMode() {
		return DynaVars.SolutionMode;
	}

	public void setYear(int Value) {

	}

	public int getYear() {
		return year;
	}

	private void sumAllCurrents() {

	}

	private void zeroInjCurr() {

	}


	public int getAlgorithm() {
		return Algorithm;
	}

	public void setAlgorithm(int algorithm) {
		Algorithm = algorithm;
	}

	public Complex[] getAuxCurrents() {
		return AuxCurrents;
	}

	public void setAuxCurrents(Complex[] auxCurrents) {
		AuxCurrents = auxCurrents;
	}

	public boolean isControlActionsDone() {
		return ControlActionsDone;
	}

	public void setControlActionsDone(boolean controlActionsDone) {
		ControlActionsDone = controlActionsDone;
	}

	public int getControlIteration() {
		return ControlIteration;
	}

	public void setControlIteration(int controlIteration) {
		ControlIteration = controlIteration;
	}

	public int getControlMode() {
		return ControlMode;
	}

	public void setControlMode(int controlMode) {
		ControlMode = controlMode;
	}

	public double getConvergenceTolerance() {
		return ConvergenceTolerance;
	}

	public void setConvergenceTolerance(double convergenceTolerance) {
		ConvergenceTolerance = convergenceTolerance;
	}

	public boolean isConvergedFlag() {
		return ConvergedFlag;
	}

	public void setConvergedFlag(boolean convergedFlag) {
		ConvergedFlag = convergedFlag;
	}

	public int getDefaultControlMode() {
		return DefaultControlMode;
	}

	public void setDefaultControlMode(int defaultControlMode) {
		DefaultControlMode = defaultControlMode;
	}

	public int getDefaultLoadModel() {
		return DefaultLoadModel;
	}

	public void setDefaultLoadModel(int defaultLoadModel) {
		DefaultLoadModel = defaultLoadModel;
	}

	public boolean isDoAllHarmonics() {
		return DoAllHarmonics;
	}

	public void setDoAllHarmonics(boolean doAllHarmonics) {
		DoAllHarmonics = doAllHarmonics;
	}

	public boolean isDynamicsAllowed() {
		return DynamicsAllowed;
	}

	public void setDynamicsAllowed(boolean dynamicsAllowed) {
		DynamicsAllowed = dynamicsAllowed;
	}

	public DynamicsRec getDynaVars() {
		return DynaVars;
	}

	public void setDynaVars(DynamicsRec dynaVars) {
		DynaVars = dynaVars;
	}

	public double[] getErrorSaved() {
		return ErrorSaved;
	}

	public void setErrorSaved(double[] errorSaved) {
		ErrorSaved = errorSaved;
	}

	public boolean isFirstIteration() {
		return FirstIteration;
	}

	public void setFirstIteration(boolean firstIteration) {
		FirstIteration = firstIteration;
	}

	public boolean isFrequencyChanged() {
		return FrequencyChanged;
	}

	public void setFrequencyChanged(boolean frequencyChanged) {
		FrequencyChanged = frequencyChanged;
	}

	public double getHarmonic() {
		return Harmonic;
	}

	public void setHarmonic(double harmonic) {
		Harmonic = harmonic;
	}

	public double[] getHarmonicList() {
		return HarmonicList;
	}

	public void setHarmonicList(double[] harmonicList) {
		HarmonicList = harmonicList;
	}

	public int getHarmonicListSize() {
		return HarmonicListSize;
	}

	public void setHarmonicListSize(int harmonicListSize) {
		HarmonicListSize = harmonicListSize;
	}

	public int getIntHour() {
		return intHour;
	}

	public void setIntHour(int intHour) {
		this.intHour = intHour;
	}

	public double getDblHour() {
		return dblHour;
	}

	public void setDblHour(double dblHour) {
		this.dblHour = dblHour;
	}

	public CMatrix getYsystem() {
		return Ysystem;
	}

	public void setYsystem(CMatrix ysystem) {
		Ysystem = ysystem;
	}

	public CMatrix getYseries() {
		return Yseries;
	}

	public void setYseries(CMatrix yseries) {
		Yseries = yseries;
	}

	public CMatrix getY() {
		return Y;
	}

	public void setY(CMatrix y) {
		Y = y;
	}

	public double getIntervalHrs() {
		return IntervalHrs;
	}

	public void setIntervalHrs(double intervalHrs) {
		IntervalHrs = intervalHrs;
	}

	public boolean isIsDynamicModel() {
		return IsDynamicModel;
	}

	public void setIsDynamicModel(boolean isDynamicModel) {
		IsDynamicModel = isDynamicModel;
	}

	public boolean isIsHarmonicModel() {
		return IsHarmonicModel;
	}

	public void setIsHarmonicModel(boolean isHarmonicModel) {
		IsHarmonicModel = isHarmonicModel;
	}

	public int getIteration() {
		return Iteration;
	}

	public void setIteration(int iteration) {
		Iteration = iteration;
	}

	public int getLoadModel() {
		return LoadModel;
	}

	public void setLoadModel(int loadModel) {
		LoadModel = loadModel;
	}

	public boolean isLastSolutionWasDirect() {
		return LastSolutionWasDirect;
	}

	public void setLastSolutionWasDirect(boolean lastSolutionWasDirect) {
		LastSolutionWasDirect = lastSolutionWasDirect;
	}

	public boolean isLoadsNeedUpdating() {
		return LoadsNeedUpdating;
	}

	public void setLoadsNeedUpdating(boolean loadsNeedUpdating) {
		LoadsNeedUpdating = loadsNeedUpdating;
	}

	public int getMaxControlIterations() {
		return MaxControlIterations;
	}

	public void setMaxControlIterations(int maxControlIterations) {
		MaxControlIterations = maxControlIterations;
	}

	public double getMaxError() {
		return MaxError;
	}

	public void setMaxError(double maxError) {
		MaxError = maxError;
	}

	public int getMaxIterations() {
		return MaxIterations;
	}

	public void setMaxIterations(int maxIterations) {
		MaxIterations = maxIterations;
	}

	public int getMostIterationsDone() {
		return MostIterationsDone;
	}

	public void setMostIterationsDone(int mostIterationsDone) {
		MostIterationsDone = mostIterationsDone;
	}

	public double[] getNodeVbase() {
		return NodeVbase;
	}

	public void setNodeVbase(double[] nodeVbase) {
		NodeVbase = nodeVbase;
	}

	public int getNumberOfTimes() {
		return NumberOfTimes;
	}

	public void setNumberOfTimes(int numberOfTimes) {
		NumberOfTimes = numberOfTimes;
	}

	public boolean isPreserveNodeVoltages() {
		return PreserveNodeVoltages;
	}

	public void setPreserveNodeVoltages(boolean preserveNodeVoltages) {
		PreserveNodeVoltages = preserveNodeVoltages;
	}

	public int getRandomType() {
		return RandomType;
	}

	public void setRandomType(int randomType) {
		RandomType = randomType;
	}

	public boolean isSeriesYInvalid() {
		return SeriesYInvalid;
	}

	public void setSeriesYInvalid(boolean seriesYInvalid) {
		SeriesYInvalid = seriesYInvalid;
	}

	public int getSolutionCount() {
		return SolutionCount;
	}

	public void setSolutionCount(int solutionCount) {
		SolutionCount = solutionCount;
	}

	public boolean isSolutionInitialized() {
		return SolutionInitialized;
	}

	public void setSolutionInitialized(boolean solutionInitialized) {
		SolutionInitialized = solutionInitialized;
	}

	public boolean isSystemYChanged() {
		return SystemYChanged;
	}

	public void setSystemYChanged(boolean systemYChanged) {
		SystemYChanged = systemYChanged;
	}

	public boolean isUseAuxCurrents() {
		return UseAuxCurrents;
	}

	public void setUseAuxCurrents(boolean useAuxCurrents) {
		UseAuxCurrents = useAuxCurrents;
	}

	public double[] getVmagSaved() {
		return VmagSaved;
	}

	public void setVmagSaved(double[] vmagSaved) {
		VmagSaved = vmagSaved;
	}

	public boolean isVoltageBaseChanged() {
		return VoltageBaseChanged;
	}

	public void setVoltageBaseChanged(boolean voltageBaseChanged) {
		VoltageBaseChanged = voltageBaseChanged;
	}

	public Complex[] getNodeV() {
		return NodeV;
	}

	public void setNodeV(Complex[] nodeV) {
		NodeV = nodeV;
	}

	public Complex[] getCurrents() {
		return Currents;
	}

	public void setCurrents(Complex[] currents) {
		Currents = currents;
	}

	public void zeroAuxCurrents() {

	}

	/* solve for now once */
	public int solveSnap() {
		return 0;
	}

	/* solve for now once, direct solution */
	public int solveDirect() {
		return 0;
	}

	/* Similar to SolveDirect; used for initialization */
	public int solveYDirect() {
		return 0;
	}

	/* SolveSnap sans control iteration */
	public int solveCircuit() {
		return 0;
	}

	public void sampleControlDevices() {

	}

	public void doControlActions() {

	}

	/* Sample and Do */
	public void sample_DoControlActions() {

	}

	public void checkFaultStatus() {

	}

	public void saveVoltages() {

	}

	/* Updates voltages for each bus from NodeV */
	public void updateVBus() {

	}

	/* opposite of updateVBus() */
	public void restoreNodeVfromVbus() {

	}

	/* Difference between two node voltages */
	public double[] vDiff(int i, int j) {
		return null;
	}

	public void initPropertyValues(int ArrayOffset) {

	}

	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	public void writeConvergenceReport(String fName) {

	}

	public void updateDblHour() {

	}

	public void incrementTime() {

	}

}
