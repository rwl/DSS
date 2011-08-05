package com.epri.dss.common.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.lang.mutable.MutableDouble;
import org.apache.commons.lang.mutable.MutableInt;

import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.DynamicsRec;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.Solution;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSBus.NodeBus;
import com.epri.dss.control.ControlElem;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.FaultObj;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.Dynamics;

public class SolutionObjImpl extends DSSObjectImpl implements SolutionObj {

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

		Globals.getDSSForms().initProgressForm(); // initialize Progress Form;

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
		} catch (SolverError e) {
			// TODO Handle solver error
		} catch (ControlProblem e) {
			// TODO Handle control problem
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

	private void setGeneratordQdV() throws SolverError, Esolv32Problem {
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
						solveSystem(NodeV);
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
						solveSystem(NodeV);
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
			throw new SolverError("Aborting");
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
	 * @throws Esolv32Problem
	 */
	private void doNormalSolution() throws Esolv32Problem {
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
			solveSystem(NodeV);
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
	 * For Generators, Termcurr = -(Sgen/V)
	 * @throws Esolv32Problem *
	 */
	private void doNewtonSolution() throws Esolv32Problem {
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
			solveSystem(dV);

			LoadsNeedUpdating = false;

			// Compute new guess at voltages
			for (int i = 0; i < ckt.getNumNodes(); i++) {  // 0 node is always 0
				NodeV[i] = new Complex(
						NodeV[i].getReal() - dV[i].getReal(),
						NodeV[i].getImaginary() - dV[i].getImaginary());
			}
		}
	}

	public void doPFlowSolution() throws SolverError, Esolv32Problem {
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
				throw new SolverError("Aborting");
			}
			if (Globals.isSolutionAbort())
				return;  // Initialization can result in abort

			try {
				setGeneratordQdV();  // Set dQdV for Model 3 generators
			} catch (Esolv32Problem e) {
				Globals.doSimpleMsg("From doPFlowSolution.setGeneratordQdV(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7073);
				throw new SolverError("Aborting");
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
	 * @throws Esolv32Problem
	 */
	public int solveZeroLoadSnapShot() throws Esolv32Problem {

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

		solveSystem(NodeV);  // also sets voltages in radial part of the circuit if radial solution

		/* Reset the main system Y as the solution matrix */
		if ((Ysystem != null) && !DSSGlobals.getInstance().isSolutionAbort())
			Y = Ysystem;

		return 0;
	}

	/**
	 * Set voltage bases using voltage at first node (phase) of a bus.
	 * @throws SolverError
	 */
	public void setVoltageBases() throws SolverError {
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
			throw new SolverError("Aborting");
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
	 * @throws ControlProblem
	 * @throws Esolv32Problem
	 */
	public void checkControls() throws ControlProblem, Esolv32Problem {
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

	/**
	 * Solve for now once.
	 * @throws SolverError
	 * @throws ControlProblem
	 * @throws Esolv32Problem
	 */
	public int solveSnap() throws SolverError, ControlProblem, Esolv32Problem {
		int Result = 0;
		int TotalIterations = 0;
		DSSGlobals Globals = DSSGlobals.getInstance();

		snapShotInit();

		while (!ControlActionsDone && (ControlIteration < MaxControlIterations)) {
			ControlIteration += 1;

			Result = solveCircuit();  // Do circuit solution w/o checking controls

			/* Now Check controls */
			/* TODO $IFDEF DLL_ENGINE */
//			fire_checkControls();
			/* TODO $ENDIF */
			checkControls();

			/* For reporting max iterations per control iteration */
			if (Iteration > MostIterationsDone)
				MostIterationsDone = Iteration;

			TotalIterations = TotalIterations + Iteration;

		}

		if (!ControlActionsDone && (ControlIteration >= MaxControlIterations)) {
			Globals.doSimpleMsg("Warning Max Control Iterations Exceeded. " + DSSGlobals.CRLF + "Tip: Show Eventlog to debug control settings.", 485);
			Globals.setSolutionAbort(true);  // this will stop this message in dynamic power flow modes
		}

		if (Globals.getActiveCircuit().isLogEvents())
			Utilities.logThisEvent("Solution Done");

		/* TODO $IFDEF DLL_ENGINE */
//		fire_StepControls();
		/* $ENDIF */

		Iteration = TotalIterations;  /* so that it reports a more interesting number */

		return Result;
	}

	/**
	 * Solve for now once, direct solution.
	 * @throws Esolv32Problem
	 */
	public int solveDirect() throws Esolv32Problem {
		LoadsNeedUpdating = true;  // Force possible update of loads and generators

		if (SystemYChanged)
			YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, true);   // Side Effect: Allocates V

		SolutionCount += 1;  // Unique number for this solution

		zeroInjCurr();  // Side Effect: Allocates InjCurr
		getSourceInjCurrents();
		getMachineInjCurrents();  // Need this in dynamics mode to pick up injections

		if (solveSystem(NodeV) == 1) {  // Solve with zero injection current
			DSSGlobals.getInstance().getActiveCircuit().setIsSolved(true);
			ConvergedFlag = true;
		}

		Iteration = 1;
		LastSolutionWasDirect = true;

		return 0;
	}

	/**
	 * SolveSnap sans control iteration.
	 * @throws SolverError
	 */
	public int solveCircuit() throws SolverError {
		if (LoadModel == DSSGlobals.ADMITTANCE) {
			try {
				solveDirect();  // no sense horsing around when it's all admittance
			} catch (Esolv32Problem e) {
				DSSGlobals.getInstance().doSimpleMsg("From solveSnap().solveDirect(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7075);
				throw new SolverError("Aborting");
			}
		} else {
			try {
				if (SystemYChanged)
					YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, true);  // Side Effect: Allocates V
				doPFlowSolution();
			} catch (Esolv32Problem e) {
				DSSGlobals.getInstance().doSimpleMsg("From solveSnap().doPFlowSolution(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7074);
				throw new SolverError("Aborting");
			}
		}
		return 0;
	}

	private void zeroInjCurr() {
		for (int i = 0; i < DSSGlobals.getInstance().getActiveCircuit().getNumNodes(); i++)
			Currents[i] = Complex.ZERO;
	}

	/**
	 * Get inj currents from all enabled PC devices.
	 */
	private void getPCInjCurr() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (PCElement pElem : ckt.getPCElements())
			if (pElem.isEnabled())
				pElem.injCurrents();  // uses NodeRef to add current into InjCurr Array;
	}

	public void dumpProperties(PrintStream F, boolean Complete) {
		// TODO Translate this method
	}

	/**
	 * Difference between two node voltages.
	 */
	public Complex vDiff(int i, int j) {
		return NodeV[i].subtract(NodeV[j]);  // V1-V2;
	}

	public void writeConvergenceReport(String fileName) {
		FileWriter fw;
		PrintWriter F;

		try {
			fw = new FileWriter(fileName);
			F = new PrintWriter(fw);

			F.println();
			F.println("-------------------");
			F.println("Convergence Report:");
			F.println("-------------------");
			F.println("\"Bus.Node\", \"Error\", \"|V|\",\"Vbase\"");

			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

			for (int i = 0; i < ckt.getNumNodes(); i++) {
				NodeBus nb = ckt.getMapNodeToBus()[i];
				F.print("\"" + Utilities.pad((ckt.getBusList().get(nb.BusRef)+"."+String.valueOf(nb.NodeNum)+"\""), 18) );
				F.printf(", %10.5s", ErrorSaved[i]);
				F.printf(", %14s", VmagSaved[i]);
				F.printf(", %14s", NodeVbase[i]);  // TODO Check text padding
				F.println();
			}

			F.println();
			F.printf("Max Error = %10.5s", MaxError);
			F.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utilities.fireOffEditor(fileName);
		}
	}

	private void sumAllCurrents() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (CktElement pElem : ckt.getCktElements())
			pElem.sumCurrents();  // sum terminal currents into system currents array
	}

	public void doControlActions() {
		MutableInt xHour = new MutableInt();
		MutableDouble xSec = new MutableDouble();

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		switch (ControlMode) {
		// Execute the nearest set of control actions time-wise.
		case DSSGlobals.CTRLSTATIC:
			if (ckt.getControlQueue().isEmpty()) {
				ControlActionsDone = true;
			} else {
				ckt.getControlQueue().doNearestActions(xHour, xSec); // ignore time advancement
			}
		case DSSGlobals.EVENTDRIVEN:
			MutableInt mHour = new MutableInt();
			MutableDouble mSec = new MutableDouble();
			boolean succ = ckt.getControlQueue().doNearestActions(mHour, mSec);  // advances time
			intHour = mHour.intValue();
			DynaVars.t = mSec.doubleValue();
			if (!succ)
				ControlActionsDone = true;
		case DSSGlobals.TIMEDRIVEN:
			if (!ckt.getControlQueue().doActions(intHour, DynaVars.t))
				ControlActionsDone = true;
		}
	}

	public void sampleControlDevices() throws ControlProblem {
		ControlElem ControlDevice = null;
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		try {
			// Sample all controls and set action times in control queue.
			for (int i = 0; i < ckt.getDSSControls().size(); i++) {
				ControlDevice = ckt.getDSSControls().get(i);
				if (ControlDevice.isEnabled())
					ControlDevice.sample();
			}
		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg("Error Sampling Control Device \""+ControlDevice.getName()+"\""+DSSGlobals.CRLF+"Error = "+e.getMessage(), 484);
			throw new ControlProblem("Solution aborted.");
		}
	}

	/**
	 * Sample and do.
	 * @throws ControlProblem
	 */
	public void sample_DoControlActions() throws ControlProblem {
		if (ControlMode == DSSGlobals.CONTROLSOFF) {
			ControlActionsDone = true;
		} else {
			sampleControlDevices();
			doControlActions();

			/* This variable lets control devices know the bus list has changed */
			DSSGlobals.getInstance().getActiveCircuit().setControl_BusNameRedefined(false);  // Reset until next change
		}
	}

	public void setMode(int Value) {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		intHour    = 0;
		DynaVars.t = 0.0;
		updateDblHour();

		ckt.setTrapezoidalIntegration(false);

		if (!oKForDynamics(Value))
			return;
		if (!oKForHarmonics(Value))
			return;

		DynaVars.SolutionMode = Value;

		ControlMode = DefaultControlMode;   // Revert to default mode
		LoadModel   = DefaultLoadModel;

		IsDynamicModel  = false;
		IsHarmonicModel = false;

		SolutionInitialized  = false;   // reinitialize solution when mode set (except dynamics)
		PreserveNodeVoltages = false;   // don't do this unless we have to

		// Reset defaults for solution modes
		switch (DynaVars.SolutionMode) {
		case Dynamics.PEAKDAY:
			DynaVars.h    = 3600.0;
			NumberOfTimes = 24;
		case Dynamics.DAILYMODE:
			DynaVars.h    = 3600.0;
			NumberOfTimes = 24;
		case Dynamics.SNAPSHOT:
			IntervalHrs   = 1.0;
			NumberOfTimes = 1;
		case Dynamics.YEARLYMODE:
			IntervalHrs   = 1.0;
			DynaVars.h    = 3600.0;
			NumberOfTimes = 8760;
		case Dynamics.DUTYCYCLE:
			DynaVars.h  = 1.0;
			ControlMode = DSSGlobals.TIMEDRIVEN;
		case Dynamics.DYNAMICMODE:
			DynaVars.h     = 0.001;
			ControlMode    = DSSGlobals.TIMEDRIVEN;
			IsDynamicModel = true;
			PreserveNodeVoltages = true;  // need to do this in case Y changes during this mode
		case Dynamics.GENERALTIME:
			IntervalHrs   = 1.0;
			DynaVars.h    = 3600.0;
			NumberOfTimes = 1;  // just one time step per Solve call expected
		case Dynamics.MONTECARLO1:
			IntervalHrs    = 1.0;
		case Dynamics.MONTECARLO2:
			DynaVars.h     = 3600.0;
		case Dynamics.MONTECARLO3:
			IntervalHrs    = 1.0;
		case Dynamics.MONTEFAULT:
			IsDynamicModel = true;
		case Dynamics.FAULTSTUDY:
			IsDynamicModel = true;
		case Dynamics.LOADDURATION1:
			DynaVars.h = 3600.0;
			ckt.setTrapezoidalIntegration(true);
		case Dynamics.LOADDURATION2:
			intHour = 1;
			ckt.setTrapezoidalIntegration(true);
		case Dynamics.AUTOADDFLAG:
			IntervalHrs = 1.0;
			ckt.getAutoAddObj().setModeChanged(true);
		case Dynamics.HARMONICMODE:
			ControlMode     = DSSGlobals.CONTROLSOFF;
			IsHarmonicModel = true;
			LoadModel       = DSSGlobals.ADMITTANCE;
			PreserveNodeVoltages = true;  // need to do this in case Y changes during this mode
		}

		/* Moved here 9-8-2007 so that mode is changed before reseting monitors, etc. */

		// Reset Meters and Monitors
		DSSGlobals.getInstance().getMonitorClass().resetAll();
		DSSGlobals.getInstance().getEnergyMeterClass().resetAll();
		Utilities.doResetFaults();
		Utilities.doResetControls();
	}

	private void addInAuxCurrents(int solveType) {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

//		for (int i = 0; i < ckt.getNumNodes(); i++)
//			Currents[i] = Currents[i].add(AuxCurrents[i]);
		// For Now, only AutoAddObj uses this.

		if (DynaVars.SolutionMode == Dynamics.AUTOADDFLAG)
			ckt.getAutoAddObj().addCurrents(solveType);
	}

	public void zeroAuxCurrents() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		for (int i = 0; i < ckt.getNumNodes(); i++)
			AuxCurrents[i] = Complex.ZERO;
	}

	public void checkFaultStatus() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		for (FaultObj pFault : ckt.getFaults())
			pFault.checkStatus(ControlMode);
	}

	/**
	 * This procedure is called for Solve Direct and any other solution method
	 * that does not get the injection currents for PC elements normally. In
	 * Dynamics mode, Generators are voltage sources ...
	 */
	private void getMachineInjCurrents() {
		// do machines in Dynamics Mode
		if (IsDynamicModel) {
			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
			for (GeneratorObj pElem : ckt.getGenerators())
				if (pElem.isEnabled())
					pElem.injCurrents();  // uses NodeRef to add current into InjCurr Array;
		}
	}

	private boolean oKForDynamics(int Value) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		boolean ValueIsDynamic;
		boolean Result = true;

		switch (Value) {
		case Dynamics.MONTEFAULT:
			ValueIsDynamic = true;
		case Dynamics.DYNAMICMODE:
			ValueIsDynamic = true;
		case Dynamics.FAULTSTUDY:
			ValueIsDynamic = true;
		default:
			ValueIsDynamic = false;
		}

		/* When we go in and out of Dynamics mode, we have to do some special things */
		if (IsDynamicModel && !ValueIsDynamic)
			Utilities.invalidateAllMachines();  // Force Recomp of YPrims when we leave Dynamics mode

		if (!IsDynamicModel && ValueIsDynamic) {  // see if conditions right for going into dynamics

			if (Globals.getActiveCircuit().isSolved()) {
				Utilities.calcInitialMachineStates();  // set state variables for machines (loads and generators)
			} else {
				/* Raise error message if not solved */
				Globals.doSimpleMsg("Circuit must be solved in a non-dynamic mode before entering Dynamics or Fault study modes!" + DSSGlobals.CRLF +
						"If you attempted to solve, then the solution has not yet converged.", 486);
				if (Globals.isIn_Redirect())
					Globals.setRedirect_Abort(true);
				Result = false;
			}
		}
		return Result;
	}

	/**
	 * When we go in and out of Harmonics mode, we have to do some special things.
	 */
	private boolean oKForHarmonics(int Value) {
		boolean Result = true;
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (IsHarmonicModel && !(Value == Dynamics.HARMONICMODE)) {
			Utilities.invalidateAllMachines();  // Force Recomp of YPrims when we leave Harmonics mode
			Frequency = ckt.getFundamental();  // Resets everything to norm
		}

		if (!IsHarmonicModel && (Value == Dynamics.HARMONICMODE)) {  // see if conditions right for going into Harmonics

			if (ckt.isSolved() && (Frequency == ckt.getFundamental())) {
				if (!Utilities.initializeForHarmonics()) {  // set state variables for machines (loads and generators) and sources
					Result = false;
					if (DSSGlobals.getInstance().isIn_Redirect())
						DSSGlobals.getInstance().setRedirect_Abort(true);
				}
			} else {
				DSSGlobals.getInstance().doSimpleMsg("Circuit must be solved in a fundamental frequency power flow or direct mode before entering Harmonics mode!", 487);
				if (DSSGlobals.getInstance().isIn_Redirect())
					DSSGlobals.getInstance().setRedirect_Abort(true);
				Result = false;
			}
		}
		return Result;
	}

	public void setFrequency(double Value) {
		if (Frequency != Value) {
			FrequencyChanged = true;  // Force Rebuild of all Y Primitives
			SystemYChanged = true;  // Force rebuild of System Y
		}

		Frequency = Value;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		if (ckt != null)
			Harmonic = Frequency / ckt.getFundamental();  // Make sure harmonic stays in synch
	}

	public void incrementTime() {
		DynaVars.t = DynaVars.t + DynaVars.h;
		while (DynaVars.t >= 3600.0) {
			intHour += 1;
			DynaVars.t = DynaVars.t - 3600.0;
		}
		updateDblHour();
	}

	public void initPropertyValues(int ArrayOffset) {
		PropertyValue[0] = "";  // TODO Check zero based indexing

		super.initPropertyValues(SolutionImpl.NumPropsThisClass);
	}

	public void setYear(int Value) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		if (Globals.isDIFilesAreOpen())
			Globals.getEnergyMeterClass().closeAllDIFiles();

		year = Value;
		intHour = 0;  /* Change year, start over */
		DynaVars.t = 0.0;
		updateDblHour();

		Globals.getEnergyMeterClass().resetAll();  // force any previous year data to complete
	}

	public void saveVoltages() {
		FileWriter FD;
		PrintWriter F;
		Complex Volts;
		String BusName;

		try {
			FD = new FileWriter(DSSGlobals.getInstance().getCircuitName_() + "SavedVoltages.txt");
			F = new PrintWriter(FD);

			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

			for (int i = 0; i < ckt.getNumBuses(); i++) {
				BusName = ckt.getBusList().get(i);
				for (int j = 0; j < ckt.getBuses()[i].getNumNodesThisBus(); j++) {
					Volts = NodeV[ckt.getBuses()[i].getRef(j)];
					F.println(BusName + ", " + ckt.getBuses()[i].getNum(j) + String.format(", %-.7g, %-.7g", Volts.abs(), Volts.degArg()));
				}
			}

			F.close();
			DSSGlobals.getInstance().setGlobalResult(DSSGlobals.getInstance().getCircuitName_() + "SavedVoltages.txt");

		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg("Error opening Saved Voltages File: "+e.getMessage(), 488);
			return;
		}
	}

	/**
	 * *************  MAIN SOLVER CALL
	 * @throws Esolv32Problem *************
	 */
	private int solveSystem(Complex[] V) throws Esolv32Problem {
		int RetCode;
		long iRes = 0;
		double dRes = 0;

		/* Note: NodeV[0] = 0 + j0 always. Therefore, pass the address of the element 1 of the array. */
		try {
			// new function to log KLUSolve.DLL function calls
			YMatrix.setLogFile("KLU_Log.txt", 1);
			RetCode = YMatrix.solveSparseSet(Y, V[1], Currents[1]);  // Solve for present InjCurr
			// new information functions
			//YMatrix.getFlops(Y, dRes);
			//YMatrix.getRGrowth(Y, dRes);
			YMatrix.getRCond(Y, dRes);
			//YMatrix.getCondEst(Y, dRes); // this can be expensive
			//YMatrix.getSize(Y, iRes);
			YMatrix.getNNZ(Y, iRes);
			YMatrix.getSparseNNZ(Y, iRes);
			//YMatrix.getSingularCol(Y, iRes);
		} catch (Exception e) {
			throw new Esolv32Problem("Error Solving System Y Matrix. Sparse matrix solver reports numerical error: "+e.getMessage());
		}

		return RetCode;
	}

	public void updateDblHour() {
		dblHour = intHour + DynaVars.t / 3600.0;
	}

	/**
	 * Updates voltages for each bus from NodeV.
	 */
	public void updateVBus() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (int i = 0; i < ckt.getNumBuses(); i++) {
			Bus bus = ckt.getBuses()[i];
			if (bus.getVBus() != null)
				for (int j = 0; j < bus.getNumNodesThisBus(); j++)
					bus.getVBus()[j] = NodeV[bus.getRef(j)];
		}
	}

	/**
	 * Opposite of updateVBus().
	 */
	public void restoreNodeVfromVbus() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (int i = 0; i < ckt.getNumBuses(); i++) {
			Bus bus = ckt.getBuses()[i];
			if (bus.getVBus() != null)
				for (int j = 0; j < bus.getNumNodesThisBus(); j++)
					NodeV[bus.getRef(j)] = bus.getVBus()[j];
		}
	}

	/**
	 * Similar to solveDirect(); used for initialization.
	 *
	 * Solves present Y matrix with no injection sources except voltage and current sources.
	 * @throws Esolv32Problem
	 */
	public int solveYDirect() throws Esolv32Problem {
		zeroInjCurr();   // Side Effect: Allocates InjCurr
		getSourceInjCurrents();
		getMachineInjCurrents();  // Need this in dynamics mode to pick up injections

		solveSystem(NodeV); // Solve with Zero injection current
		return 0;
	}

	public double getFrequency() {
		return Frequency;
	}

	public int getMode() {
		return DynaVars.SolutionMode;
	}

	public int getYear() {
		return year;
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

}
