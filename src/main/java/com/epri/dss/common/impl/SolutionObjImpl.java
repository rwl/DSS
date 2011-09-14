package com.epri.dss.common.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.lang.mutable.MutableDouble;
import org.apache.commons.lang.mutable.MutableInt;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.shared.impl.ComplexUtil;
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
	private double frequency;

	protected int algorithm;           // NORMALSOLVE or NEWTONSOLVE
	protected Complex[] auxCurrents;   // for injections like AutoAdd
	protected boolean controlActionsDone;
	protected int controlIteration;
	protected int controlMode;         // EVENTDRIVEN, TIMEDRIVEN
	protected double convergenceTolerance;
	protected boolean convergedFlag;
	protected int defaultControlMode;  // EVENTDRIVEN, TIMEDRIVEN
	protected int defaultLoadModel;    // 1=POWERFLOW  2=ADMITTANCE
	protected boolean doAllHarmonics;
	protected boolean dynamicsAllowed;
	protected DynamicsRec dynaVars = new DynamicsRec();
	protected double[] errorSaved;
	protected boolean firstIteration;
	/* Flag set to true if something has altered the frequency */
	protected boolean frequencyChanged;
	protected int year;
	protected double harmonic;
	protected double[] harmonicList;
	protected int harmonicListSize;
	protected int intHour;
	protected double dblHour;
	/* Main (system) Y matrix */
	protected CMatrix YSystem;
	/* Series Y matrix */
	protected CMatrix YSeries;
	/* Either Ysystem or Yseries */
	protected CMatrix Y;
	protected double intervalHrs;   // solution interval since last solution, hrs.
	protected boolean isDynamicModel;
	protected boolean isHarmonicModel;
	protected int iteration;
	protected int loadModel;        // 1=POWERFLOW  2=ADMITTANCE
	protected boolean lastSolutionWasDirect;
	protected boolean loadsNeedUpdating;
	protected int maxControlIterations;
	protected double maxError;
	protected int maxIterations;
	protected int mostIterationsDone;
	protected double[] nodeVBase;
	protected int numberOfTimes;    // number of times to solve
	protected boolean preserveNodeVoltages;
	protected int randomType;       // 0 = NONE; 1 = GAUSSIAN; 2 = UNIFORM
	protected boolean seriesYInvalid;
	protected int solutionCount;    // counter incremented for each solution
	protected boolean solutionInitialized;
	protected boolean systemYChanged;
	protected boolean useAuxCurrents;
	protected double[] VMagSaved;
	protected boolean voltageBaseChanged;

	/* Main system voltage array */
	protected Complex[] nodeV;
	/* Main system currents array */
	protected Complex[] currents;

	public SolutionObjImpl(DSSClass parClass, String solutionName) {
		super(parClass);
		setName(solutionName.toLowerCase());

		year = 0;
		intHour = 0;
		dynaVars.t = 0.0;
		dblHour = 0.0;
		dynaVars.tstart = 0.0;
		dynaVars.tstop = 0.0;
		//duration = 0.0;
		dynaVars.h = 0.001;  // default for dynasolve

		loadsNeedUpdating = true;
		voltageBaseChanged = true;  // forces building of convergence check arrays

		maxIterations = 15;
		maxControlIterations = 10;
		convergenceTolerance = 0.0001;
		convergedFlag = false;

		isDynamicModel  = false;
		isHarmonicModel = false;

		setFrequency( DSSGlobals.defaultBaseFreq );
		/*Fundamental = 60.0; moved to circuit and used as default base frequency*/
		harmonic = 1.0;

		frequencyChanged = true;  // force building of YPrim matrices
		doAllHarmonics   = true;
		firstIteration   = true;
		dynamicsAllowed  = false;
		systemYChanged   = true;
		seriesYInvalid   = true;

		/* Define default harmonic list */
		harmonicListSize = 5;
		harmonicList = new double[harmonicListSize];
		harmonicList[0] = 1.0;
		harmonicList[1] = 5.0;
		harmonicList[2] = 7.0;
		harmonicList[3] = 11.0;
		harmonicList[4] = 13.0;

		solutionInitialized = false;
		loadModel = DSSGlobals.POWERFLOW;
		defaultLoadModel = loadModel;
		lastSolutionWasDirect = false;

		YSeries = null;
		YSystem = null;
		Y = null;

		nodeV      = null;
		dV         = null;
		currents   = null;
		auxCurrents= null;
		VMagSaved  = null;
		errorSaved = null;
		nodeVBase  = null;

		useAuxCurrents = false;

		solutionCount = 0;

		dynaVars.solutionMode = Dynamics.SNAPSHOT;
		controlMode           = DSSGlobals.CTRLSTATIC;
		defaultControlMode    = controlMode;
		algorithm             = Solution.NORMALSOLVE;

		randomType    = DSSGlobals.GAUSSIAN;  // default to Gaussian
		numberOfTimes = 100;
		intervalHrs   = 1.0;

		initPropertyValues(0);
	}

	/**
	 * Main solution dispatch.
	 */
	public void solve() {

		DSSGlobals.activeCircuit.setIsSolved(false);
		DSSGlobals.solutionWasAttempted = true;

		DSSGlobals.DSSForms.initProgressForm();  // initialize progress form;

		/* Check of some special conditions that must be met before executing solutions */

		if (DSSGlobals.activeCircuit.getEmergMinVolts() >= DSSGlobals.activeCircuit.getNormalMinVolts()) {
			DSSGlobals.doSimpleMsg("Error: Emergency Min Voltage Must Be Less Than Normal Min Voltage!" +
					DSSGlobals.CRLF + "Solution Not Executed.", 480);
			return;
		}

		if (DSSGlobals.solutionAbort) {
			DSSGlobals.globalResult = "Solution aborted.";
			DSSGlobals.cmdResult = DSSGlobals.SOLUTION_ABORT;
			DSSGlobals.errorNumber = DSSGlobals.cmdResult;
			return;
		}

		try {

			/* Main solution algorithm dispatcher */
			Circuit ckt = DSSGlobals.activeCircuit;

			switch (year) {
			case 0:
				ckt.setDefaultGrowthFactor(1.0);
				break;
			default:
				ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (year - 1)));
				break;
			}

//			fireInitControls();

			/* checkFaultStatus;  ???? needed here?? */

			switch (dynaVars.solutionMode) {
			case Dynamics.SNAPSHOT:
				solveSnap();
				break;
			case Dynamics.YEARLYMODE:
				SolutionAlgs.solveYearly();
				break;
			case Dynamics.DAILYMODE:
				SolutionAlgs.solveDaily();
				break;
			case Dynamics.DUTYCYCLE:
				SolutionAlgs.solveDuty();
				break;
			case Dynamics.DYNAMICMODE:
				SolutionAlgs.solveDynamic();
				break;
			case Dynamics.MONTECARLO1:
				SolutionAlgs.solveMonte1();
				break;
			case Dynamics.MONTECARLO2:
				SolutionAlgs.solveMonte2();
				break;
			case Dynamics.MONTECARLO3:
				SolutionAlgs.solveMonte3();
				break;
			case Dynamics.PEAKDAY:
				SolutionAlgs.solvePeakDay();
				break;
			case Dynamics.LOADDURATION1:
				SolutionAlgs.solveLD1();
				break;
			case Dynamics.LOADDURATION2:
				SolutionAlgs.solveLD2();
				break;
			case Dynamics.DIRECT:
				solveDirect();
				break;
			case Dynamics.MONTEFAULT:
				SolutionAlgs.solveMonteFault();  // Monte Carlo fault cases
				break;
			case Dynamics.FAULTSTUDY:
				SolutionAlgs.solveFaultStudy();
				break;
			case Dynamics.AUTOADDFLAG:
				ckt.getAutoAddObj().solve();
				break;
			case Dynamics.HARMONICMODE:
				SolutionAlgs.solveHarmonic();
				break;
			case Dynamics.GENERALTIME:
				SolutionAlgs.solveGeneralTime();
				break;
			default:
				DSSGlobals.doSimpleMsg("Unknown solution mode.", 481);
				break;
			}
		} catch (Esolv32Problem e) {
			DSSGlobals.doSimpleMsg("Error encountered in Solve: " + e.getMessage(), 482);
			DSSGlobals.solutionAbort = true;
		} catch (SolverError e) {
			// TODO Handle solver error
		} catch (ControlProblem e) {
			// TODO Handle control problem
		}
	}

	private boolean converged() {
		boolean result;
		double VMag;
		Circuit ckt = DSSGlobals.activeCircuit;

		// base convergence on voltage magnitude

		maxError = 0.0;
		for (int i = 0; i < ckt.getNumNodes(); i++) {
			VMag = nodeV[i].abs();

			/* If base specified, use it; otherwise go on present magnitude */
			if (nodeVBase[i] > 0.0) {
				errorSaved[i] = Math.abs(VMag - VMagSaved[i]) / nodeVBase[i];
			} else {
				if (VMag != 0.0)
					errorSaved[i] = Math.abs(1.0 - VMagSaved[i] / VMag);
			}

			VMagSaved[i] = VMag;  // for next go-'round

			maxError = Math.max(maxError, errorSaved[i]);  // update max error
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

		if (maxError <= convergenceTolerance) {
			result = true;
		} else {
			result = false;
		}

		convergedFlag = result;

		return result;
	}

	/**
	 * Add in the contributions of all source type elements to the global
	 * solution vector InjCurr.
	 */
	private void getSourceInjCurrents() {
		Circuit ckt = DSSGlobals.activeCircuit;

		for (CktElement pElem : ckt.getSources())
			if (pElem.isEnabled())
				pElem.injCurrents();  // uses nodeRef to add current into injCurr Array;

		if (isHarmonicModel) {  // pick up generators and loads, too

			for (GeneratorObj pElem : ckt.getGenerators())
				if (pElem.isEnabled())
					pElem.injCurrents();  // uses nodeRef to add current into injCurr array;

			for (LoadObj pElem : ckt.getLoads())
				if (pElem.isEnabled())
					pElem.injCurrents();  // uses nodeRef to add current into injCurr array;
		}
	}

	/**
	 * Set the global generator dispatch reference.
	 */
	public void setGeneratorDispRef() {
		Circuit ckt = DSSGlobals.activeCircuit;

		switch (dynaVars.solutionMode) {
		case Dynamics.SNAPSHOT:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor());
			break;
		case Dynamics.YEARLYMODE:
			ckt.setGeneratorDispatchReference(ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
			break;
		case Dynamics.DAILYMODE:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
			break;
		case Dynamics.DUTYCYCLE:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
			break;
		case Dynamics.GENERALTIME:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
			break;
		case Dynamics.DYNAMICMODE:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor());
			break;
		case Dynamics.HARMONICMODE:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor());
			break;
		case Dynamics.MONTECARLO1:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor());
			break;
		case Dynamics.MONTECARLO2:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
			break;
		case Dynamics.MONTECARLO3:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
			break;
		case Dynamics.PEAKDAY:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
			break;
		case Dynamics.LOADDURATION1:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
			break;
		case Dynamics.LOADDURATION2:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal());
			break;
		case Dynamics.DIRECT:
			ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor());
			break;
		case Dynamics.MONTEFAULT:
			ckt.setGeneratorDispatchReference(1.0);  // Monte Carlo fault cases solve at peak load only base case
			break;
		case Dynamics.FAULTSTUDY:
			ckt.setGeneratorDispatchReference(1.0);
			break;
		case Dynamics.AUTOADDFLAG:
			ckt.setGeneratorDispatchReference(ckt.getDefaultGrowthFactor());  // peak load only
			break;
		default:
			DSSGlobals.doSimpleMsg("Unknown solution mode.", 483);
			break;
		}
	}

	private void setGenerator_dQdV() throws SolverError, Esolv32Problem {
		boolean didOne = false;
		double genDispSave;

		Circuit ckt = DSSGlobals.activeCircuit;

		// save the generator dispatch level and set on high enough to turn all generators on
		genDispSave = ckt.getGeneratorDispatchReference();
		ckt.setGeneratorDispatchReference(1000.0);

		for (GeneratorObj pGen : ckt.getGenerators()) {
			if (pGen.isEnabled()) {

				// for PV generator models only ...
				if (pGen.getGenModel() == 3) {

					pGen.initDQDVCalc();

					// solve at base var setting
					iteration = 0;
					while (!converged() && (iteration < maxIterations)) {
						iteration += 1;
						zeroInjCurr();
						getSourceInjCurrents();
						pGen.injCurrents();  // get generator currents with nominal vars
						solveSystem(nodeV);
					}

					pGen.rememberQV();  // remember Q and V
					pGen.bumpUpQ();

					// solve after changing vars
					iteration = 0;
					while (!converged() && iteration < maxIterations) {
						iteration += 1;
						zeroInjCurr();
						getSourceInjCurrents();
						pGen.injCurrents();  // get generator currents with nominal vars
						solveSystem(nodeV);
					}

					pGen.calcDQDV(); // bssed on remembered Q and V and present values of same
					pGen.resetStartPoint();

					didOne = true;
				}
			}
		}

		// restore generator dispatch reference
		ckt.setGeneratorDispatchReference(genDispSave);
		try {
			if (didOne)  // reset initial solution
				solveZeroLoadSnapShot();
		} catch (Esolv32Problem e) {
			DSSGlobals.doSimpleMsg("From setGenerator dQdV, solveZeroLoadSnapShot: " + DSSGlobals.CRLF + e.getMessage()  + YMatrix.checkYMatrixforZeroes(), 7071);
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
	 * InjCurr are the current injected into the node (need to reverse
	 * current direction for loads).
	 *
	 * @throws Esolv32Problem
	 */
	private void doNormalSolution() throws Esolv32Problem {
		iteration = 0;

		Circuit ckt = DSSGlobals.activeCircuit;

		/* **** Main iteration loop **** */
		while ((!converged() || iteration <= 1) && (iteration < maxIterations)) {
			iteration += 1;

			if (ckt.isLogEvents())
				Utilities.logThisEvent("Solution Iteration " + String.valueOf(iteration));

			/* Get injcurrents for all PC devices */
			zeroInjCurr();
			getSourceInjCurrents();  // sources
			getPCInjCurr();  // get the injection currents from all the power conversion devices and feeders

			// the above call could change the primitive Y matrix, so have to check
			if (systemYChanged)
				YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, false);  // does not realloc V, I

			if (useAuxCurrents)
				addInAuxCurrents(Solution.NORMALSOLVE);

			// solve for voltages      /* Note: nodeV[0] = 0 + j0 always */
			if (ckt.isLogEvents())
				Utilities.logThisEvent("Solve sparse set doNormalSolution ...");
			solveSystem(nodeV);
			loadsNeedUpdating = false;
		}
	}

	/**
	 * Newton iteration.
	 *
	 *   Vn+1 =  Vn - [Y]-1 Termcurr
	 *
	 * Where termCurr includes currents from all elements and we are
	 * attempting to get the  currents to sum to zero at all nodes.
	 *
	 * TermCurr is the sum of all currents going into the terminals of
	 * the elements.
	 *
	 * For PD Elements, termCurr = Yprim*V
	 *
	 * For Loads, termCurr = (Sload / V)*
	 * For Generators, termCurr = -(Sgen / V)
	 *
	 * @throws Esolv32Problem *
	 */
	private void doNewtonSolution() throws Esolv32Problem {
		Circuit ckt = DSSGlobals.activeCircuit;

		dV = Utilities.resizeArray(dV, ckt.getNumNodes() + 1);  // make sure this is always big enough

		if (controlIteration == 1)
			getPCInjCurr();  // update the load multipliers for this solution

		iteration = 0;
		while ((!converged() || (iteration <= 1)) && (iteration < maxIterations)) {
			iteration += 1;
			solutionCount += 1;  // sumAllCurrents uses ITerminal, so must force a recalc

			// get sum of currents at all nodes for all devices
			zeroInjCurr();
			sumAllCurrents();

			// call to current calc could change YPrim for some devices
			if (systemYChanged)
				YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, false);  // does not realloc V, I

			if (useAuxCurrents)
				addInAuxCurrents(Solution.NEWTONSOLVE);

			// solve for change in voltages
			solveSystem(dV);

			loadsNeedUpdating = false;

			// compute new guess at voltages
			for (int i = 0; i < ckt.getNumNodes(); i++) {  // 0 node is always 0
				nodeV[i] = new Complex(nodeV[i].getReal() - dV[i].getReal(),
						nodeV[i].getImaginary() - dV[i].getImaginary());
			}
		}
	}

	public void doPFlowSolution() throws SolverError, Esolv32Problem {

		solutionCount += 1;  // unique number for this solution

		if (voltageBaseChanged)
			YMatrix.initializeNodeVbase();  // for convergence test

		if (!solutionInitialized) {

			if (DSSGlobals.activeCircuit.isLogEvents())
				Utilities.logThisEvent("Initializing Solution");
			try {
				//solveZeroLoadSnapShot();
				solveYDirect();  // 8-14-06 this should give a better answer than zero load snapshot
			} catch (Esolv32Problem e) {
				DSSGlobals.doSimpleMsg("From doPFlowSolution().solveYDirect(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7072);
				throw new SolverError("Aborting");
			}
			if (DSSGlobals.solutionAbort)
				return;  // initialization can result in abort

			try {
				setGenerator_dQdV();  // set dQdV for model 3 generators
			} catch (Esolv32Problem e) {
				DSSGlobals.doSimpleMsg("From doPFlowSolution.setGeneratordQdV(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7073);
				throw new SolverError("Aborting");
			}

			/* The above resets the active sparse set to hY */
			solutionInitialized = true;
		}

		switch (algorithm) {
		case Solution.NORMALSOLVE:
			doNormalSolution();
			break;
		case Solution.NEWTONSOLVE:
			doNewtonSolution();
			break;
		}

		DSSGlobals.activeCircuit.setIsSolved(convergedFlag);
		lastSolutionWasDirect = false;
	}

	/**
	 * Solve without load for initialization purposes.
	 *
	 * @throws Esolv32Problem
	 */
	public int solveZeroLoadSnapShot() throws Esolv32Problem {

		if (systemYChanged || seriesYInvalid)
			YMatrix.buildYMatrix(YMatrix.SERIESONLY, true);  // side effect: allocates V

		solutionCount += 1;  // unique number for this solution

		zeroInjCurr();  // Side effect: allocates InjCurr
		getSourceInjCurrents();  // Vsource and Isource only

		/* Make the series Y matrix the active matrix */
		if (YSeries == null)
			throw new Esolv32Problem("Series Y matrix not built yet in solveZeroLoadSnapshot().");
		Y = YSeries;

		if (DSSGlobals.activeCircuit.isLogEvents())
			Utilities.logThisEvent("Solve Sparse Set ZeroLoadSnapshot ...");

		solveSystem(nodeV);  // also sets voltages in radial part of the circuit if radial solution

		/* Reset the main system Y as the solution matrix */
		if ((YSystem != null) && !DSSGlobals.solutionAbort)
			Y = YSystem;

		return 0;
	}

	/**
	 * Set voltage bases using voltage at first node (phase) of a bus.
	 *
	 * @throws SolverError
	 */
	public void setVoltageBases() throws SolverError {
		boolean bZoneCalc, bZoneLock;
		Circuit ckt = DSSGlobals.activeCircuit;

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
				bus.setKVBase( Utilities.nearestBasekV( nodeV[ bus.getRef(0) ].abs() * 0.001732 ) / DSSGlobals.SQRT3);  // l-n base kV
			}

			YMatrix.initializeNodeVbase();  // for convergence test

			ckt.setIsSolved(true);

			// now build the meter zones
			ckt.setMeterZonesComputed(bZoneCalc);
			ckt.setZonesLocked(bZoneLock);
			ckt.doResetMeterZones();

		} catch (Esolv32Problem e) {
			DSSGlobals.doSimpleMsg("From setVoltageBases().solveZeroLoadSnapShot(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7075);
			throw new SolverError("Aborting");
		}
	}

	public void snapShotInit() {
		setGeneratorDispRef();
		controlIteration   = 0;
		controlActionsDone = false;
		mostIterationsDone = 0;
		loadsNeedUpdating  = true;  // force the loads to update at least once
	}

	/**
	 * Snapshot checks with matrix rebuild.
	 *
	 * @throws ControlProblem
	 * @throws Esolv32Problem
	 */
	public void checkControls() throws ControlProblem, Esolv32Problem {
		if (controlIteration < maxControlIterations) {
			if (convergedFlag) {
				if (DSSGlobals.activeCircuit.isLogEvents())
					Utilities.logThisEvent("Control Iteration " + String.valueOf(controlIteration));
				sampleDoControlActions();
				checkFaultStatus();
			} else {
				controlActionsDone = true;  // stop solution process if failure to converge
			}
		}

		if (systemYChanged)
			YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, false);  // rebuild Y matrix, but V stays same
	}

	/**
	 * Solve for now once.
	 *
	 * @throws SolverError
	 * @throws ControlProblem
	 * @throws Esolv32Problem
	 */
	public int solveSnap() throws SolverError, ControlProblem, Esolv32Problem {
		int result = 0;
		int totalIterations = 0;

		snapShotInit();

		while (!controlActionsDone && (controlIteration < maxControlIterations)) {
			controlIteration += 1;

			result = solveCircuit();  // do circuit solution w/o checking controls

			/* Now check controls */
			/* TODO $IFDEF DLL_ENGINE */
//			fire_checkControls();
			/* TODO $ENDIF */
			checkControls();

			/* For reporting max iterations per control iteration */
			if (iteration > mostIterationsDone)
				mostIterationsDone = iteration;

			totalIterations = totalIterations + iteration;

		}

		if (!controlActionsDone && (controlIteration >= maxControlIterations)) {
			DSSGlobals.doSimpleMsg("Warning: max control iterations exceeded. " + DSSGlobals.CRLF + "Tip: Show eventLog to debug control settings.", 485);
			DSSGlobals.solutionAbort = true;  // this will stop this message in dynamic power flow modes
		}

		if (DSSGlobals.activeCircuit.isLogEvents())
			Utilities.logThisEvent("Solution done");

		/* TODO $IFDEF DLL_ENGINE */
//		fire_StepControls();
		/* $ENDIF */

		iteration = totalIterations;  /* so that it reports a more interesting number */

		return result;
	}

	/**
	 * Solve for now once, direct solution.
	 *
	 * @throws Esolv32Problem
	 */
	public int solveDirect() throws Esolv32Problem {
		loadsNeedUpdating = true;  // force possible update of loads and generators

		if (systemYChanged)
			YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, true);  // side effect: allocates V

		solutionCount += 1;  // unique number for this solution

		zeroInjCurr();  // side effect: allocates injCurr
		getSourceInjCurrents();
		getMachineInjCurrents();  // need this in dynamics mode to pick up injections

		if (solveSystem(nodeV) == 1) {  // solve with zero injection current
			DSSGlobals.activeCircuit.setIsSolved(true);
			convergedFlag = true;
		}

		iteration = 1;
		lastSolutionWasDirect = true;

		return 0;
	}

	/**
	 * SolveSnap sans control iteration.
	 *
	 * @throws SolverError
	 */
	public int solveCircuit() throws SolverError {
		if (loadModel == DSSGlobals.ADMITTANCE) {
			try {
				solveDirect();  // no sense horsing around when it's all admittance
			} catch (Esolv32Problem e) {
				DSSGlobals.doSimpleMsg("From solveSnap().solveDirect(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7075);
				throw new SolverError("Aborting");
			}
		} else {
			try {
				if (systemYChanged)
					YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, true);  // Side effect: allocates V
				doPFlowSolution();
			} catch (Esolv32Problem e) {
				DSSGlobals.doSimpleMsg("From solveSnap().doPFlowSolution(): " + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7074);
				throw new SolverError("Aborting");
			}
		}
		return 0;
	}

	private void zeroInjCurr() {
		for (int i = 0; i < DSSGlobals.activeCircuit.getNumNodes(); i++)
			currents[i] = Complex.ZERO;
	}

	/**
	 * Get inj currents from all enabled PC devices.
	 */
	private void getPCInjCurr() {
		Circuit ckt = DSSGlobals.activeCircuit;
		for (PCElement pElem : ckt.getPCElements())
			if (pElem.isEnabled())
				pElem.injCurrents();  // uses nodeRef to add current into injCurr array
	}

	public void dumpProperties(PrintStream F, boolean Complete) {
		// TODO Translate this method
		throw new UnsupportedOperationException();
	}

	/**
	 * Difference between two node voltages.
	 */
	public Complex vDiff(int i, int j) {
		return nodeV[i].subtract(nodeV[j]);  // V1-V2;
	}

	public void writeConvergenceReport(String fileName) {
		FileWriter fw;
		PrintWriter f;

		try {
			fw = new FileWriter(fileName);
			f = new PrintWriter(fw);

			f.println();
			f.println("-------------------");
			f.println("Convergence Report:");
			f.println("-------------------");
			f.println("\"Bus.Node\", \"Error\", \"|V|\",\"Vbase\"");

			Circuit ckt = DSSGlobals.activeCircuit;

			for (int i = 0; i < ckt.getNumNodes(); i++) {
				NodeBus nb = ckt.getMapNodeToBus()[i];
				f.print("\"" + Utilities.pad((ckt.getBusList().get(nb.busRef)+"."+String.valueOf(nb.nodeNum)+"\""), 18) );
				f.printf(", %10.5s", errorSaved[i]);
				f.printf(", %14s", VMagSaved[i]);
				f.printf(", %14s", nodeVBase[i]);  // TODO Check text padding
				f.println();
			}

			f.println();
			f.printf("Max Error = %10.5s", maxError);
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utilities.fireOffEditor(fileName);
		}
	}

	private void sumAllCurrents() {
		Circuit ckt = DSSGlobals.activeCircuit;
		for (CktElement pElem : ckt.getCktElements())
			pElem.sumCurrents();  // sum terminal currents into system currents array
	}

	public void doControlActions() {
		boolean succ;
		MutableInt mHour, xHour = new MutableInt();
		MutableDouble mSec, xSec = new MutableDouble();

		Circuit ckt = DSSGlobals.activeCircuit;

		switch (controlMode) {
		// execute the nearest set of control actions time-wise.
		case DSSGlobals.CTRLSTATIC:
			if (ckt.getControlQueue().isEmpty()) {
				controlActionsDone = true;
			} else {
				ckt.getControlQueue().doNearestActions(xHour, xSec);  // ignore time advancement
			}
			break;
		case DSSGlobals.EVENTDRIVEN:
			mHour = new MutableInt();
			mSec = new MutableDouble();
			succ = ckt.getControlQueue().doNearestActions(mHour, mSec);  // advances time
			intHour = mHour.intValue();
			dynaVars.t = mSec.doubleValue();
			if (!succ)
				controlActionsDone = true;
			break;
		case DSSGlobals.TIMEDRIVEN:
			if (!ckt.getControlQueue().doActions(intHour, dynaVars.t))
				controlActionsDone = true;
			break;
		}
	}

	public void sampleControlDevices() throws ControlProblem {
		ControlElem controlDevice = null;
		Circuit ckt = DSSGlobals.activeCircuit;
		try {
			// sample all controls and set action times in control queue.
			for (int i = 0; i < ckt.getDSSControls().size(); i++) {
				controlDevice = ckt.getDSSControls().get(i);
				if (controlDevice.isEnabled())
					controlDevice.sample();
			}
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error sampling control device \""+controlDevice.getName()+"\""+DSSGlobals.CRLF+"Error = "+e.getMessage(), 484);
			throw new ControlProblem("Solution aborted.");
		}
	}

	/**
	 * Sample and do.
	 *
	 * @throws ControlProblem
	 */
	public void sampleDoControlActions() throws ControlProblem {
		if (controlMode == DSSGlobals.CONTROLSOFF) {
			controlActionsDone = true;
		} else {
			sampleControlDevices();
			doControlActions();

			/* This variable lets control devices know the bus list has changed */
			DSSGlobals.activeCircuit.setControlBusNameRedefined(false);  // reset until next change
		}
	}

	public void setMode(int value) {
		Circuit ckt = DSSGlobals.activeCircuit;

		intHour    = 0;
		dynaVars.t = 0.0;
		updateDblHour();

		ckt.setTrapezoidalIntegration(false);

		if (!okForDynamics(value))
			return;
		if (!okForHarmonics(value))
			return;

		dynaVars.solutionMode = value;

		controlMode = defaultControlMode;  // revert to default mode
		loadModel   = defaultLoadModel;

		isDynamicModel  = false;
		isHarmonicModel = false;

		solutionInitialized  = false;  // reinitialize solution when mode set (except dynamics)
		preserveNodeVoltages = false;  // don't do this unless we have to

		// reset defaults for solution modes
		switch (dynaVars.solutionMode) {
		case Dynamics.PEAKDAY:
			dynaVars.h    = 3600.0;
			numberOfTimes = 24;
			break;
		case Dynamics.DAILYMODE:
			dynaVars.h    = 3600.0;
			numberOfTimes = 24;
			break;
		case Dynamics.SNAPSHOT:
			intervalHrs   = 1.0;
			numberOfTimes = 1;
			break;
		case Dynamics.YEARLYMODE:
			intervalHrs   = 1.0;
			dynaVars.h    = 3600.0;
			numberOfTimes = 8760;
			break;
		case Dynamics.DUTYCYCLE:
			dynaVars.h  = 1.0;
			controlMode = DSSGlobals.TIMEDRIVEN;
			break;
		case Dynamics.DYNAMICMODE:
			dynaVars.h     = 0.001;
			controlMode    = DSSGlobals.TIMEDRIVEN;
			isDynamicModel = true;
			preserveNodeVoltages = true;  // need to do this in case Y changes during this mode
			break;
		case Dynamics.GENERALTIME:
			intervalHrs   = 1.0;
			dynaVars.h    = 3600.0;
			numberOfTimes = 1;  // just one time step per solve call expected
			break;
		case Dynamics.MONTECARLO1:
			intervalHrs    = 1.0;
			break;
		case Dynamics.MONTECARLO2:
			dynaVars.h     = 3600.0;
			break;
		case Dynamics.MONTECARLO3:
			intervalHrs    = 1.0;
			break;
		case Dynamics.MONTEFAULT:
			isDynamicModel = true;
			break;
		case Dynamics.FAULTSTUDY:
			isDynamicModel = true;
			break;
		case Dynamics.LOADDURATION1:
			dynaVars.h = 3600.0;
			ckt.setTrapezoidalIntegration(true);
			break;
		case Dynamics.LOADDURATION2:
			intHour = 1;
			ckt.setTrapezoidalIntegration(true);
			break;
		case Dynamics.AUTOADDFLAG:
			intervalHrs = 1.0;
			ckt.getAutoAddObj().setModeChanged(true);
			break;
		case Dynamics.HARMONICMODE:
			controlMode     = DSSGlobals.CONTROLSOFF;
			isHarmonicModel = true;
			loadModel       = DSSGlobals.ADMITTANCE;
			preserveNodeVoltages = true;  // need to do this in case Y changes during this mode
			break;
		}

		/* Moved here 9-8-2007 so that mode is changed before reseting monitors, etc. */

		// reset meters and monitors
		DSSGlobals.monitorClass.resetAll();
		DSSGlobals.energyMeterClass.resetAll();
		Utilities.doResetFaults();
		Utilities.doResetControls();
	}

	private void addInAuxCurrents(int solveType) {
		Circuit ckt = DSSGlobals.activeCircuit;

		//for (int i = 0; i < ckt.getNumNodes(); i++)
		//	currents[i] = currents[i].add(auxCurrents[i]);
		// for now, only AutoAddObj uses this.

		if (dynaVars.solutionMode == Dynamics.AUTOADDFLAG)
			ckt.getAutoAddObj().addCurrents(solveType);
	}

	public void zeroAuxCurrents() {
		Circuit ckt = DSSGlobals.activeCircuit;

		for (int i = 0; i < ckt.getNumNodes(); i++)
			auxCurrents[i] = Complex.ZERO;
	}

	public void checkFaultStatus() {
		Circuit ckt = DSSGlobals.activeCircuit;

		for (FaultObj pFault : ckt.getFaults())
			pFault.checkStatus(controlMode);
	}

	/**
	 * This procedure is called for solveDirect and any other solution method
	 * that does not get the injection currents for PC elements normally. In
	 * dynamics mode, generators are voltage sources ...
	 */
	private void getMachineInjCurrents() {
		// do machines in dynamics mode
		if (isDynamicModel) {
			Circuit ckt = DSSGlobals.activeCircuit;
			for (GeneratorObj pElem : ckt.getGenerators())
				if (pElem.isEnabled())
					pElem.injCurrents();  // uses nodeRef to add current into injCurr array
		}
	}

	private boolean okForDynamics(int value) {
		boolean valueIsDynamic;
		boolean result = true;

		switch (value) {
		case Dynamics.MONTEFAULT:
			valueIsDynamic = true;
			break;
		case Dynamics.DYNAMICMODE:
			valueIsDynamic = true;
			break;
		case Dynamics.FAULTSTUDY:
			valueIsDynamic = true;
			break;
		default:
			valueIsDynamic = false;
			break;
		}

		/* When we go in and out of dynamics mode, we have to do some special things */
		if (isDynamicModel && !valueIsDynamic)
			Utilities.invalidateAllMachines();  // force recomp of YPrims when we leave dynamics mode

		if (!isDynamicModel && valueIsDynamic) {  // see if conditions right for going into dynamics

			if (DSSGlobals.activeCircuit.isSolved()) {
				Utilities.calcInitialMachineStates();  // set state variables for machines (loads and generators)
			} else {
				/* Raise error message if not solved */
				DSSGlobals.doSimpleMsg("Circuit must be solved in a non-dynamic mode before entering dynamics or fault study modes!" + DSSGlobals.CRLF +
						"If you attempted to solve, then the solution has not yet converged.", 486);
				if (DSSGlobals.inRedirect)
					DSSGlobals.redirectAbort = true;
				result = false;
			}
		}
		return result;
	}

	/**
	 * When we go in and out of harmonics mode, we have to do some special things.
	 */
	private boolean okForHarmonics(int value) {
		boolean result = true;
		Circuit ckt = DSSGlobals.activeCircuit;

		if (isHarmonicModel && !(value == Dynamics.HARMONICMODE)) {
			Utilities.invalidateAllMachines();  // force recomp of YPrims when we leave harmonics mode
			frequency = ckt.getFundamental();   // resets everything to norm
		}

		if (!isHarmonicModel && (value == Dynamics.HARMONICMODE)) {  // see if conditions right for going into harmonics

			if (ckt.isSolved() && (frequency == ckt.getFundamental())) {
				if (!Utilities.initializeForHarmonics()) {  // set state variables for machines (loads and generators) and sources
					result = false;
					if (DSSGlobals.inRedirect)
						DSSGlobals.redirectAbort = true;
				}
			} else {
				DSSGlobals.doSimpleMsg("Circuit must be solved in a fundamental frequency power flow or direct mode before entering harmonics mode!", 487);
				if (DSSGlobals.inRedirect)
					DSSGlobals.redirectAbort = true;
				result = false;
			}
		}
		return result;
	}

	public void setFrequency(double value) {
		if (frequency != value) {
			frequencyChanged = true;  // force rebuild of all Y primitives
			systemYChanged = true;    // force rebuild of system Y
		}

		frequency = value;

		Circuit ckt = DSSGlobals.activeCircuit;
		if (ckt != null)
			harmonic = frequency / ckt.getFundamental();  // make sure harmonic stays in synch
	}

	public void incrementTime() {
		dynaVars.t = dynaVars.t + dynaVars.h;
		while (dynaVars.t >= 3600.0) {
			intHour += 1;
			dynaVars.t = dynaVars.t - 3600.0;
		}
		updateDblHour();
	}

	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "");

		super.initPropertyValues(SolutionImpl.NumPropsThisClass - 1);
	}

	public void setYear(int value) {

		if (DSSGlobals.DIFilesAreOpen)
			DSSGlobals.energyMeterClass.closeAllDIFiles();

		year = value;
		intHour = 0;  /* Change year, start over */
		dynaVars.t = 0.0;
		updateDblHour();

		DSSGlobals.energyMeterClass.resetAll();  // force any previous year data to complete
	}

	public void saveVoltages() {
		FileWriter fd;
		PrintWriter f;
		Complex volts;
		String busName;

		try {
			fd = new FileWriter(DSSGlobals.circuitName_ + "SavedVoltages.txt");
			f = new PrintWriter(fd);

			Circuit ckt = DSSGlobals.activeCircuit;

			for (int i = 0; i < ckt.getNumBuses(); i++) {
				busName = ckt.getBusList().get(i);
				for (int j = 0; j < ckt.getBuses()[i].getNumNodesThisBus(); j++) {
					volts = nodeV[ckt.getBuses()[i].getRef(j)];
					f.println(busName + ", " + ckt.getBuses()[i].getNum(j) + String.format(", %-.7g, %-.7g", volts.abs(), ComplexUtil.degArg(volts)));
				}
			}

			f.close();
			DSSGlobals.globalResult = DSSGlobals.circuitName_ + "SavedVoltages.txt";

		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error opening saved voltages file: "+e.getMessage(), 488);
			return;
		}
	}

	/**
	 * *************  MAIN SOLVER CALL *************
	 *
	 * @throws Esolv32Problem
	 */
	private int solveSystem(Complex[] V) throws Esolv32Problem {
		int retCode;
		long iRes = 0;
		double dRes = 0;

		/* Note: NodeV[0] = 0 + j0 always. Therefore, pass the address of the element 1 of the array. */
		try {
			// new function to log KLUSolve.DLL function calls
			YMatrix.setLogFile("KLU_Log.txt", 1);
			retCode = YMatrix.solveSparseSet(Y, V[1], currents[1]);  // solve for present injCurr
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
			throw new Esolv32Problem("Error solving system Y matrix. Sparse matrix solver reports numerical error: "+e.getMessage());
		}

		return retCode;
	}

	public void updateDblHour() {
		dblHour = intHour + dynaVars.t / 3600.0;
	}

	/**
	 * Updates voltages for each bus from NodeV.
	 */
	public void updateVBus() {
		Bus bus;
		Circuit ckt = DSSGlobals.activeCircuit;

		for (int i = 0; i < ckt.getNumBuses(); i++) {
			bus = ckt.getBuses()[i];
			if (bus.getVBus() != null)
				for (int j = 0; j < bus.getNumNodesThisBus(); j++)
					bus.getVBus()[j] = nodeV[bus.getRef(j)];
		}
	}

	/**
	 * Opposite of updateVBus().
	 */
	public void restoreNodeVFromVbus() {
		Bus bus;
		Circuit ckt = DSSGlobals.activeCircuit;

		for (int i = 0; i < ckt.getNumBuses(); i++) {
			bus = ckt.getBuses()[i];
			if (bus.getVBus() != null)
				for (int j = 0; j < bus.getNumNodesThisBus(); j++)
					nodeV[bus.getRef(j)] = bus.getVBus()[j];
		}
	}

	/**
	 * Similar to solveDirect(); used for initialization.
	 * Solves present Y matrix with no injection sources except voltage and current sources.
	 *
	 * @throws Esolv32Problem
	 */
	public int solveYDirect() throws Esolv32Problem {
		zeroInjCurr();  // side effect: allocates injCurr
		getSourceInjCurrents();
		getMachineInjCurrents();  // need this in dynamics mode to pick up injections

		solveSystem(nodeV);  // solve with zero injection current
		return 0;
	}

	public double getFrequency() {
		return frequency;
	}

	public int getMode() {
		return dynaVars.solutionMode;
	}

	public int getYear() {
		return year;
	}

	public int getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(int alg) {
		algorithm = alg;
	}

	public Complex[] getAuxCurrents() {
		return auxCurrents;
	}

	public void setAuxCurrents(Complex[] value) {
		auxCurrents = value;
	}

	public boolean isControlActionsDone() {
		return controlActionsDone;
	}

	public void setControlActionsDone(boolean value) {
		controlActionsDone = value;
	}

	public int getControlIteration() {
		return controlIteration;
	}

	public void setControlIteration(int iteration) {
		controlIteration = iteration;
	}

	public int getControlMode() {
		return controlMode;
	}

	public void setControlMode(int mode) {
		controlMode = mode;
	}

	public double getConvergenceTolerance() {
		return convergenceTolerance;
	}

	public void setConvergenceTolerance(double tolerance) {
		convergenceTolerance = tolerance;
	}

	public boolean isConvergedFlag() {
		return convergedFlag;
	}

	public void setConvergedFlag(boolean flag) {
		convergedFlag = flag;
	}

	public int getDefaultControlMode() {
		return defaultControlMode;
	}

	public void setDefaultControlMode(int controlMode) {
		defaultControlMode = controlMode;
	}

	public int getDefaultLoadModel() {
		return defaultLoadModel;
	}

	public void setDefaultLoadModel(int loadModel) {
		defaultLoadModel = loadModel;
	}

	public boolean isDoAllHarmonics() {
		return doAllHarmonics;
	}

	public void setDoAllHarmonics(boolean value) {
		doAllHarmonics = value;
	}

	public boolean isDynamicsAllowed() {
		return dynamicsAllowed;
	}

	public void setDynamicsAllowed(boolean allowed) {
		dynamicsAllowed = allowed;
	}

	public DynamicsRec getDynaVars() {
		return dynaVars;
	}

	public void setDynaVars(DynamicsRec vars) {
		dynaVars = vars;
	}

	public double[] getErrorSaved() {
		return errorSaved;
	}

	public void setErrorSaved(double[] value) {
		errorSaved = value;
	}

	public boolean isFirstIteration() {
		return firstIteration;
	}

	public void setFirstIteration(boolean iteration) {
		firstIteration = iteration;
	}

	public boolean isFrequencyChanged() {
		return frequencyChanged;
	}

	public void setFrequencyChanged(boolean value) {
		frequencyChanged = value;
	}

	public double getHarmonic() {
		return harmonic;
	}

	public void setHarmonic(double value) {
		harmonic = value;
	}

	public double[] getHarmonicList() {
		return harmonicList;
	}

	public void setHarmonicList(double[] value) {
		harmonicList = value;
	}

	public int getHarmonicListSize() {
		return harmonicListSize;
	}

	public void setHarmonicListSize(int size) {
		harmonicListSize = size;
	}

	public int getIntHour() {
		return intHour;
	}

	public void setIntHour(int hour) {
		this.intHour = hour;
	}

	public double getDblHour() {
		return dblHour;
	}

	public void setDblHour(double hour) {
		this.dblHour = hour;
	}

	public CMatrix getYSystem() {
		return YSystem;
	}

	public void setYSystem(CMatrix value) {
		YSystem = value;
	}

	public CMatrix getYSeries() {
		return YSeries;
	}

	public void setYSeries(CMatrix value) {
		YSeries = value;
	}

	public CMatrix getY() {
		return Y;
	}

	public void setY(CMatrix y) {
		Y = y;
	}

	public double getIntervalHrs() {
		return intervalHrs;
	}

	public void setIntervalHrs(double interval) {
		intervalHrs = interval;
	}

	public boolean isDynamicModel() {
		return isDynamicModel;
	}

	public void setDynamicModel(boolean isDynamic) {
		isDynamicModel = isDynamic;
	}

	public boolean isHarmonicModel() {
		return isHarmonicModel;
	}

	public void setHarmonicModel(boolean isHarmonic) {
		isHarmonicModel = isHarmonic;
	}

	public int getIteration() {
		return iteration;
	}

	public void setIteration(int iter) {
		iteration = iter;
	}

	public int getLoadModel() {
		return loadModel;
	}

	public void setLoadModel(int model) {
		loadModel = model;
	}

	public boolean lastSolutionWasDirect() {
		return lastSolutionWasDirect;
	}

	public void setLastSolutionWasDirect(boolean value) {
		lastSolutionWasDirect = value;
	}

	public boolean loadsNeedUpdating() {
		return loadsNeedUpdating;
	}

	public void setLoadsNeedUpdating(boolean value) {
		loadsNeedUpdating = value;
	}

	public int getMaxControlIterations() {
		return maxControlIterations;
	}

	public void setMaxControlIterations(int iterations) {
		maxControlIterations = iterations;
	}

	public double getMaxError() {
		return maxError;
	}

	public void setMaxError(double error) {
		maxError = error;
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(int iterations) {
		maxIterations = iterations;
	}

	public int getMostIterationsDone() {
		return mostIterationsDone;
	}

	public void setMostIterationsDone(int value) {
		mostIterationsDone = value;
	}

	public double[] getNodeVBase() {
		return nodeVBase;
	}

	public void setNodeVBase(double[] base) {
		nodeVBase = base;
	}

	public int getNumberOfTimes() {
		return numberOfTimes;
	}

	public void setNumberOfTimes(int number) {
		numberOfTimes = number;
	}

	public boolean isPreserveNodeVoltages() {
		return preserveNodeVoltages;
	}

	public void setPreserveNodeVoltages(boolean preserve) {
		preserveNodeVoltages = preserve;
	}

	public int getRandomType() {
		return randomType;
	}

	public void setRandomType(int type) {
		randomType = type;
	}

	public boolean isSeriesYInvalid() {
		return seriesYInvalid;
	}

	public void setSeriesYInvalid(boolean invalid) {
		seriesYInvalid = invalid;
	}

	public int getSolutionCount() {
		return solutionCount;
	}

	public void setSolutionCount(int count) {
		solutionCount = count;
	}

	public boolean isSolutionInitialized() {
		return solutionInitialized;
	}

	public void setSolutionInitialized(boolean value) {
		solutionInitialized = value;
	}

	public boolean isSystemYChanged() {
		return systemYChanged;
	}

	public void setSystemYChanged(boolean value) {
		systemYChanged = value;
	}

	public boolean useAuxCurrents() {
		return useAuxCurrents;
	}

	public void setUseAuxCurrents(boolean value) {
		useAuxCurrents = value;
	}

	public double[] getVMagSaved() {
		return VMagSaved;
	}

	public void setVMagSaved(double[] value) {
		VMagSaved = value;
	}

	public boolean isVoltageBaseChanged() {
		return voltageBaseChanged;
	}

	public void setVoltageBaseChanged(boolean value) {
		voltageBaseChanged = value;
	}

	public Complex[] getNodeV() {
		return nodeV;
	}

	public void setNodeV(Complex[] value) {
		nodeV = value;
	}

	public Complex[] getCurrents() {
		return currents;
	}

	public void setCurrents(Complex[] value) {
		currents = value;
	}

}
