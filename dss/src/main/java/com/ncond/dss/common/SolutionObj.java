/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus.NodeBus;
import com.ncond.dss.common.exceptions.ControlProblem;
import com.ncond.dss.common.exceptions.SolverError;
import com.ncond.dss.common.exceptions.SolverProblem;
import com.ncond.dss.common.types.Algorithm;
import com.ncond.dss.common.types.BuildOption;
import com.ncond.dss.common.types.ControlMode;
import com.ncond.dss.common.types.SolutionLoadModel;
import com.ncond.dss.common.types.Randomization;
import com.ncond.dss.common.types.SolutionAlgs;
import com.ncond.dss.common.types.SolutionMode;
import com.ncond.dss.control.ControlElem;
import com.ncond.dss.conversion.GeneratorModel;
import com.ncond.dss.conversion.GeneratorObj;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.FaultObj;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.shared.ComplexUtil;
import com.ncond.dss.shared.DynamicsRec;

public class SolutionObj extends DSSObject {

	/* Array of delta V for Newton iteration */
	private Complex[] dV;
	private double frequency;

	protected Algorithm algorithm;
	protected Complex[] auxCurrents;   // for injections like AutoAdd
	protected boolean controlActionsDone;
	protected int controlIteration;
	protected ControlMode controlMode;
	protected double convergenceTolerance;
	protected boolean convergedFlag;
	protected ControlMode defaultControlMode;
	protected SolutionLoadModel defaultLoadModel;
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
	protected UUID YSystem;
	/* Series Y matrix */
	protected UUID YSeries;
	/* Either Ysystem or Yseries */
	protected UUID Y;
	protected double intervalHrs;   // solution interval since last solution, hrs.
	protected boolean isDynamicModel;
	protected boolean isHarmonicModel;
	protected int iteration;
	protected SolutionLoadModel loadModel;
	protected boolean lastSolutionWasDirect;
	protected boolean loadsNeedUpdating;
	protected int maxControlIterations;
	protected double maxError;
	protected int maxIterations;
	protected int mostIterationsDone;
	protected double[] nodeVBase;
	protected int numberOfTimes;    // number of times to solve
	protected boolean preserveNodeVoltages;
	protected Randomization randomType;
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

	public SolutionObj(DSSClass parClass, String solutionName) {
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

		setFrequency( DSS.defaultBaseFreq );
		/*Fundamental = 60.0; moved to circuit and used as default base frequency*/
		harmonic = 1.0;

		frequencyChanged = true;  // force building of YPrim matrices
		doAllHarmonics = true;
		firstIteration = true;
		dynamicsAllowed = false;
		systemYChanged = true;
		seriesYInvalid = true;

		/* Define default harmonic list */
		harmonicListSize = 5;
		harmonicList = new double[harmonicListSize];
		harmonicList[0] = 1.0;
		harmonicList[1] = 5.0;
		harmonicList[2] = 7.0;
		harmonicList[3] = 11.0;
		harmonicList[4] = 13.0;

		solutionInitialized = false;
		loadModel = SolutionLoadModel.POWERFLOW;
		defaultLoadModel = loadModel;
		lastSolutionWasDirect = false;

		YSeries = null;
		YSystem = null;
		Y = null;

		nodeV = null;
		dV = null;
		currents = null;
		auxCurrents = null;
		VMagSaved = null;
		errorSaved = null;
		nodeVBase = null;

		useAuxCurrents = false;

		solutionCount = 0;

		dynaVars.solutionMode = SolutionMode.SNAPSHOT;
		controlMode = ControlMode.CTRLSTATIC;
		defaultControlMode = controlMode;
		algorithm = Algorithm.NORMAL;

		randomType = Randomization.GAUSSIAN;  // default to Gaussian
		numberOfTimes = 100;
		intervalHrs = 1.0;

		initPropertyValues(0);
	}

	/**
	 * Main solution dispatch.
	 */
	public void solve() {
		double rate;
		Circuit ckt = DSS.activeCircuit;

		ckt.setSolved(false);
		DSS.solutionWasAttempted = true;

		DSS.forms.initProgressForm();  // initialize progress form;

		/* Check of some special conditions that must be met before executing solutions */

		if (ckt.getEmergMinVolts() >= ckt.getNormalMinVolts()) {
			DSS.doSimpleMsg("Emergency min voltage must be less than normal min voltage!" +
					DSS.CRLF + "Solution not executed.", 480);
			return;
		}

		if (DSS.solutionAbort) {
			DSS.globalResult = "Solution aborted.";
			DSS.cmdResult = DSS.SOLUTION_ABORT;
			DSS.errorNumber = DSS.cmdResult;
			return;
		}

		try {
			/* Main solution algorithm dispatcher */

			switch (year) {
			case 0:
				rate = 1.0;
				break;
			default:
				rate = Math.pow(ckt.getDefaultGrowthRate(), (year - 1));
				break;
			}
			ckt.setDefaultGrowthFactor(rate);

			/* checkFaultStatus();  ???? needed here?? */

			switch (dynaVars.solutionMode) {
			case SNAPSHOT:
				solveSnap();
				break;
			case YEARLYMODE:
				SolutionAlgs.solveYearly();
				break;
			case DAILYMODE:
				SolutionAlgs.solveDaily();
				break;
			case DUTYCYCLE:
				SolutionAlgs.solveDuty();
				break;
			case DYNAMICMODE:
				SolutionAlgs.solveDynamic();
				break;
			case MONTECARLO1:
				SolutionAlgs.solveMonte1();
				break;
			case MONTECARLO2:
				SolutionAlgs.solveMonte2();
				break;
			case MONTECARLO3:
				SolutionAlgs.solveMonte3();
				break;
			case PEAKDAY:
				SolutionAlgs.solvePeakDay();
				break;
			case LOADDURATION1:
				SolutionAlgs.solveLD1();
				break;
			case LOADDURATION2:
				SolutionAlgs.solveLD2();
				break;
			case DIRECT:
				solveDirect();
				break;
			case MONTEFAULT:
				SolutionAlgs.solveMonteFault();  // Monte Carlo fault cases
				break;
			case FAULTSTUDY:
				SolutionAlgs.solveFaultStudy();
				break;
			case AUTOADDFLAG:
				ckt.getAutoAddObj().solve();
				break;
			case HARMONICMODE:
				SolutionAlgs.solveHarmonic();
				break;
			case GENERALTIME:
				SolutionAlgs.solveGeneralTime();
				break;
			default:
				DSS.doSimpleMsg("Unknown solution mode.", 481);
				break;
			}
		} catch (SolverProblem e) {
			DSS.doSimpleMsg("Solver problem encountered: " + e.getMessage(), 482);
			DSS.solutionAbort = true;
		} catch (SolverError e) {
			DSS.doSimpleMsg("Solver error encountered: " + e.getMessage(), 483);
			DSS.solutionAbort = true;
		} catch (ControlProblem e) {
			DSS.doSimpleMsg("Control problem encountered: " + e.getMessage(), 484);
			DSS.solutionAbort = true;
		}
	}

	private boolean converged() {
		double Vmag;
		Circuit ckt = DSS.activeCircuit;

		// base convergence on voltage magnitude

		maxError = 0.0;
		for (int i = 0; i < ckt.getNumNodes(); i++) {
			Vmag = nodeV[i].abs();

			/* If base specified, use it; otherwise go on present magnitude */
			if (nodeVBase[i] > 0.0) {
				errorSaved[i] = Math.abs(Vmag - VMagSaved[i]) / nodeVBase[i];
			} else {
				if (Vmag != 0.0)
					errorSaved[i] = Math.abs(1.0 - VMagSaved[i] / Vmag);
			}

			VMagSaved[i] = Vmag;  // for next go-'round

			maxError = Math.max(maxError, errorSaved[i]);  // update max error
		}

		/* $IFDEF debugtrace
		FileWriter fw = new FileWriter("DebugTrace.csv", true);
		PrintWriter pw = new PrintWriter(fw);
		if (iteration == 0) {
			pw.print("Iter");
			for (int i = 1; i <= ckt.getNumNodes(); i++)
				pw.print(", " + ckt.getBusList().get(ckt.getMapNodeToBus(i).busRef) + "." + ckt.getMapNodeToBus(i).nodeNum);
			pw.println();
		}
		// FIXME format number widths
		pw.print(iteration);
		for (int i = 0; i < ckt.getNumNodes(); i++)
			pw.print(", " + VMagSaved[i]);
		pw.println();
		pw.print("Err");
		for (int i = 0; i < ckt.getNumNodes(); i++)
			pw.printf(", %-.5g" , errorSaved[i]);
		pw.println();
		pw.print("Curr");
		for (int i = 0; i < ckt.getNumNodes(); i++)
			pw.print(", " + currents[i].abs());
		pw.println();
		pw.close();
		fw.close();
		$ENDIF */

		convergedFlag = maxError <= convergenceTolerance;

		return convergedFlag;
	}

	/**
	 * Add in the contributions of all source type elements to the global
	 * solution vector InjCurr.
	 */
	private void getSourceInjCurrents() {
		Circuit ckt = DSS.activeCircuit;

		for (CktElement pElem : ckt.getSources())
			if (pElem.isEnabled())
				pElem.injCurrents();  // uses nodeRef to add current into injCurr Array;
	}

	/**
	 * Set the global generator dispatch reference.
	 */
	public void setGeneratorDispRef() {
		double ref;
		Circuit ckt = DSS.activeCircuit;

		switch (dynaVars.solutionMode) {
		case SNAPSHOT:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor();
			break;
		case YEARLYMODE:
			ref = ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal();
			break;
		case DAILYMODE:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal();
			break;
		case DUTYCYCLE:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal();
			break;
		case GENERALTIME:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal();
			break;
		case DYNAMICMODE:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor();
			break;
		case HARMONICMODE:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor();
			break;
		case MONTECARLO1:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor();
			break;
		case MONTECARLO2:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal();
			break;
		case MONTECARLO3:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal();
			break;
		case PEAKDAY:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal();
			break;
		case LOADDURATION1:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal();
			break;
		case LOADDURATION2:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal();
			break;
		case DIRECT:
			ref = ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor();
			break;
		case MONTEFAULT:
			ref = 1.0;  // Monte Carlo fault cases solve at peak load only base case
			break;
		case FAULTSTUDY:
			ref = 1.0;
			break;
		case AUTOADDFLAG:
			ref = ckt.getDefaultGrowthFactor();  // peak load only
			break;
		default:
			DSS.doSimpleMsg("Unknown solution mode.", 483);
			return;
		}
		ckt.setGeneratorDispatchReference(ref);
	}

	private void setGenerator_dQdV() throws SolverError, SolverProblem {
		boolean didOne = false;
		double genDispSave;

		Circuit ckt = DSS.activeCircuit;

		// save the generator dispatch level and set on high enough to turn all generators on
		genDispSave = ckt.getGeneratorDispatchReference();
		ckt.setGeneratorDispatchReference(1000.0);

		for (GeneratorObj gen : ckt.getGenerators()) {
			if (gen.isEnabled()) {
				// for PV generator models only ...
				if (gen.getGenModel() == GeneratorModel.CONSTANT_PV) {

					gen.initDQDVCalc();

					// solve at base var setting
					iteration = 0;
					while (!converged() && (iteration < maxIterations)) {
						iteration += 1;
						zeroInjCurr();
						getSourceInjCurrents();
						gen.injCurrents();  // get generator currents with nominal vars
						solveSystem(nodeV);
					}

					gen.rememberQV();  // remember Q and V
					gen.bumpUpQ();

					// solve after changing vars
					iteration = 0;
					while (!converged() && iteration < maxIterations) {
						iteration += 1;
						zeroInjCurr();
						getSourceInjCurrents();
						gen.injCurrents();  // get generator currents with nominal vars
						solveSystem(nodeV);
					}

					gen.calcDQDV(); // bssed on remembered Q and V and present values of same
					gen.resetStartPoint();

					didOne = true;
				}
			}
		}

		ckt.setGeneratorDispatchReference(genDispSave);  // restore generator dispatch reference
		try {
			if (didOne) solveZeroLoadSnapShot();  // reset initial solution
		} catch (SolverProblem e) {
			DSS.doSimpleMsg("From setGenerator_dQdV, solveZeroLoadSnapShot: " + DSS.CRLF +
					e.getMessage()  + YMatrix.checkYMatrixforZeroes(), 7071);
			throw new SolverError("Aborting");
		}
	}

	/**
	 * Normal fixed-point solution.
	 *
	 *   Vn+1 = [Y]-1 injCurr
	 *
	 * Where injCurr includes only PC elements (loads, generators, etc.)
	 * i.e., the shunt elements.
	 *
	 * InjCurr are the current injected into the node (need to reverse
	 * current direction for loads).
	 *
	 * @throws SolverProblem
	 */
	private void doNormalSolution() throws SolverProblem {
		Circuit ckt = DSS.activeCircuit;

		iteration = 0;

		/* **** Main iteration loop **** */
		while ((!converged() || iteration <= 1) && (iteration < maxIterations)) {
			iteration += 1;

			if (ckt.isLogEvents())
				Util.logThisEvent("Solution iteration: " + iteration);

			/* Get inj currents for all PC devices */
			zeroInjCurr();
			getSourceInjCurrents();  // sources
			getPCInjCurr();  // get the injection currents from all the power conversion devices and feeders

			// the above call could change the primitive Y matrix, so have to check
			if (systemYChanged)
				YMatrix.buildYMatrix(BuildOption.WHOLEMATRIX, false);  // does not realloc V, I

			if (useAuxCurrents)
				addInAuxCurrents(Algorithm.NORMAL);

			if (ckt.isLogEvents())
				Util.logThisEvent("Solve sparse set doNormalSolution ...");

			solveSystem(nodeV);  // solve for voltages

			loadsNeedUpdating = false;
		}
	}

	/**
	 * Newton iteration.
	 *
	 *   Vn+1 =  Vn - [Y]-1 termCurr
	 *
	 * Where termCurr includes currents from all elements and we are
	 * attempting to get the  currents to sum to zero at all nodes.
	 *
	 * TermCurr is the sum of all currents going into the terminals of
	 * the elements.
	 *
	 * For PD elements, termCurr = Yprim*V
	 *
	 * For loads, termCurr = (Sload / V)*
	 * For generators, termCurr = -(Sgen / V)
	 *
	 * @throws SolverProblem *
	 */
	private void doNewtonSolution() throws SolverProblem {
		Circuit ckt = DSS.activeCircuit;

		dV = Util.resizeArray(dV, ckt.getNumNodes() + 1);  // make sure this is always big enough

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
				YMatrix.buildYMatrix(BuildOption.WHOLEMATRIX, false);  // does not realloc V, I

			if (useAuxCurrents)
				addInAuxCurrents(Algorithm.NEWTON);

			// solve for change in voltages
			solveSystem(dV);

			loadsNeedUpdating = false;

			// compute new guess at voltages
			for (int i = 0; i < ckt.getNumNodes(); i++) {  // 0 node is always 0
				nodeV[i] = new Complex(
					nodeV[i].getReal() - dV[i].getReal(),
					nodeV[i].getImaginary() - dV[i].getImaginary()
				);
			}
		}
	}

	public void doPFlowSolution() throws SolverError, SolverProblem {
		Circuit ckt = DSS.activeCircuit;

		solutionCount += 1;  // unique number for this solution

		if (voltageBaseChanged)
			YMatrix.initializeNodeVBase();  // for convergence test

		if (!solutionInitialized) {
			if (ckt.isLogEvents())
				Util.logThisEvent("Initializing solution");

			try {
				//solveZeroLoadSnapShot();
				solveYDirect();  // this should give a better answer than zero load snapshot
			} catch (SolverProblem e) {
				DSS.doSimpleMsg("From doPFlowSolution(), solveYDirect(): " + DSS.CRLF +
						e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7072);
				throw new SolverError("Aborting");
			}

			if (DSS.solutionAbort)
				return;  // initialization can result in abort

			try {
				setGenerator_dQdV();  // set dQdV for model 3 generators
			} catch (SolverProblem e) {
				DSS.doSimpleMsg("From doPFlowSolution(), setGeneratordQdV(): " + DSS.CRLF +
						e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7073);
				throw new SolverError("Aborting");
			}

			/* The above resets the active sparse set to hY */
			solutionInitialized = true;
		}

		switch (algorithm) {
		case NORMAL:
			doNormalSolution();
			break;
		case NEWTON:
			doNewtonSolution();
			break;
		}

		ckt.setSolved(convergedFlag);
		lastSolutionWasDirect = false;
	}

	/**
	 * Solve without load for initialization purposes.
	 *
	 * @throws SolverProblem
	 */
	public int solveZeroLoadSnapShot() throws SolverProblem {

		if (systemYChanged || seriesYInvalid)
			YMatrix.buildYMatrix(BuildOption.SERIESONLY, true);  // side effect: allocates V

		solutionCount += 1;  // unique number for this solution

		zeroInjCurr();  // side effect: allocates injCurr
		getSourceInjCurrents();  // Vsource and Isource only

		/* Make the series Y matrix the active matrix */
		if (YSeries == null)
			throw new SolverProblem("Series Y matrix not built yet in solveZeroLoadSnapshot().");
		Y = YSeries;

		if (DSS.activeCircuit.isLogEvents())
			Util.logThisEvent("Solve sparse set zeroLoadSnapshot ...");

		solveSystem(nodeV);  // also sets voltages in radial part of the circuit if radial solution

		/* Reset the main system Y as the solution matrix */
		if ((YSystem != null) && !DSS.solutionAbort) Y = YSystem;

		return 0;
	}

	/**
	 * Set voltage bases using voltage at first node (phase) of a bus.
	 *
	 * @throws SolverError
	 */
	public void setVoltageBases() throws SolverError {
		boolean bZoneCalc, bZoneLock;
		double kVBase;
		Bus bus;
		Circuit ckt = DSS.activeCircuit;

		try {
			// don't allow the meter zones to auto-build in this load flow
			// solution, because the voltage bases are not available yet

			bZoneCalc = ckt.isMeterZonesComputed();
			bZoneLock = ckt.isZonesLocked();
			ckt.setMeterZonesComputed(true);
			ckt.setZonesLocked(true);

			solveZeroLoadSnapShot();

			for (int i = 0; i < ckt.getNumBuses(); i++) {
				bus = ckt.getBus(i);
				kVBase = Util.nearestBasekV( getNodeV(bus.getRef(0)).abs() * 0.001732 ) / DSS.SQRT3;
				bus.setKVBase(kVBase);  // l-n base kV
			}

			YMatrix.initializeNodeVBase();  // for convergence test

			ckt.setSolved(true);

			// now build the meter zones
			ckt.setMeterZonesComputed(bZoneCalc);
			ckt.setZonesLocked(bZoneLock);
			ckt.doResetMeterZones();

		} catch (SolverProblem e) {
			DSS.doSimpleMsg("From setVoltageBases(), solveZeroLoadSnapShot(): " + DSS.CRLF +
					e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7075);
			throw new SolverError("Aborting");
		}
	}

	public void snapShotInit() {
		setGeneratorDispRef();
		controlIteration = 0;
		controlActionsDone = false;
		mostIterationsDone = 0;
		loadsNeedUpdating = true;  // force the loads to update at least once
	}

	/**
	 * Snapshot checks with matrix rebuild.
	 *
	 * @throws ControlProblem
	 * @throws SolverProblem
	 */
	public void checkControls() throws ControlProblem, SolverProblem {
		if (controlIteration < maxControlIterations) {
			if (convergedFlag) {
				if (DSS.activeCircuit.isLogEvents())
					Util.logThisEvent("Control iteration: " + controlIteration);

				sampleDoControlActions();
				checkFaultStatus();
			} else {
				// stop solution process if failure to converge
				controlActionsDone = true;
			}
		}

		if (systemYChanged) {
			// rebuild Y matrix, but V stays same
			YMatrix.buildYMatrix(BuildOption.WHOLEMATRIX, false);
		}
	}

	/**
	 * Solve for now once.
	 *
	 * @throws SolverError
	 * @throws ControlProblem
	 * @throws SolverProblem
	 */
	public int solveSnap() throws SolverError, ControlProblem, SolverProblem {
		int result = 0;
		int totalIterations = 0;

		snapShotInit();

		while (!controlActionsDone && (controlIteration < maxControlIterations)) {
			controlIteration += 1;

			result = solveCircuit();  // do circuit solution w/o checking controls

			checkControls();

			/* For reporting max iterations per control iteration */
			if (iteration > mostIterationsDone)
				mostIterationsDone = iteration;

			totalIterations = totalIterations + iteration;
		}

		if (!controlActionsDone && (controlIteration >= maxControlIterations)) {
			DSS.doSimpleMsg("Warning: max control iterations exceeded. " + DSS.CRLF +
					"Tip: Show eventLog to debug control settings.", 485);
			// this will stop this message in dynamic power flow modes
			DSS.solutionAbort = true;
		}

		if (DSS.activeCircuit.isLogEvents())
			Util.logThisEvent("Solution done");

		iteration = totalIterations;  /* so that it reports a more interesting number */

		return result;
	}

	/**
	 * Solve for now once, direct solution.
	 *
	 * @throws SolverProblem
	 */
	public int solveDirect() throws SolverProblem {
		loadsNeedUpdating = true;  // force possible update of loads and generators

		if (systemYChanged)
			YMatrix.buildYMatrix(BuildOption.WHOLEMATRIX, true);  // side effect: allocates V

		solutionCount += 1;  // unique number for this solution

		zeroInjCurr();  // side effect: allocates injCurr
		getSourceInjCurrents();

		// Pick up PCElement injections for Harmonics mode and Dynamics mode
		// Ignore these injections for powerflow; Use only admittance in Y matrix
		if (isDynamicModel || isHarmonicModel)
			getPCInjCurr();

		if (solveSystem(nodeV) == 1) {  // solve with zero injection current
			DSS.activeCircuit.setSolved(true);
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
		if (loadModel == SolutionLoadModel.ADMITTANCE) {
			try {
				solveDirect();  // no sense horsing around when it's all admittance
			} catch (SolverProblem e) {
				DSS.doSimpleMsg("From solveSnap(), solveDirect(): " + DSS.CRLF +
						e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7075);
				throw new SolverError("Aborting");
			}
		} else {
			try {
				if (systemYChanged)
					YMatrix.buildYMatrix(BuildOption.WHOLEMATRIX, true);  // side effect: allocates V
				doPFlowSolution();
			} catch (SolverProblem e) {
				DSS.doSimpleMsg("From solveSnap().doPFlowSolution(): " + DSS.CRLF +
						e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7074);
				throw new SolverError("Aborting");
			}
		}
		return 0;
	}

	private void zeroInjCurr() {
		for (int i = 0; i < DSS.activeCircuit.getNumNodes(); i++)
			currents[i] = Complex.ZERO;
	}

	/**
	 * Get inj currents from all enabled PC devices.
	 */
	private void getPCInjCurr() {
		Circuit ckt = DSS.activeCircuit;

		for (PCElement elem : ckt.getPCElements())
			if (elem.isEnabled())
				elem.injCurrents();  // uses nodeRef to add current into injCurr array
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		int i, j;
		DSSClass pc;
		AutoAdd autoAdd;

		// for dumping the matrix in compressed columns
		int p, nBus, nNZ;
		UUID Y;
		int[] ColPtr, RowIdx, ip = new int[1];
		Complex[] cVals;

		Circuit ckt = DSS.activeCircuit;
		PrintWriter pw = new PrintWriter(out);

		pw.println( "! Options");

		//super.dumpProperties(out, complete);

		pw.println( "! numNodes = " + ckt.getNumNodes());

		pc = getParentClass();
		for (i = 0; i < pc.getNumProperties(); i++) {
			pw.println("set " + pc.getPropertyName(i) + "=" + getPropertyValue(i));
		}

		pw.println("set mode=" + Util.getSolutionModeID());
		pw.println("set controlMode=" + Util.getControlModeID());
		pw.println("set random=" + Util.getRandomModeID());
		pw.println("set hour=" + intHour);
		pw.println("set sec=" + String.format("%g", dynaVars.t));
		pw.println("set year=" + year);
		pw.println("set frequency=" + String.format("%g", frequency));
		pw.println("set stepsize=" + String.format("%g", dynaVars.h));
		pw.println("set number=" + numberOfTimes);
		pw.println("set circuit=" + ckt.getName());
		pw.println("set editor=" + DSS.defaultEditor);
		pw.println("set tolerance=" + String.format("%g", convergenceTolerance));
		pw.println("set maxiter=" + maxIterations);
		pw.println("set loadmodel=" + getLoadModel());

		pw.println("set loadmult=" + String.format("%g", ckt.getLoadMultiplier()));
		pw.println("set normVminPU=" + String.format("%g", ckt.getNormalMinVolts()));
		pw.println("set normVmaxPU=" + String.format("%g", ckt.getNormalMaxVolts()));
		pw.println("set emergVminPU=" + String.format("%g", ckt.getEmergMinVolts()));
		pw.println("set emergVmaxPU=" + String.format("%g", ckt.getEmergMaxVolts()));
		pw.println("set %mean=" + String.format("%-.4g", ckt.getDefaultDailyShapeObj().getMean() * 100.0));
		pw.println("set %stddev=" + String.format("%-.4g", ckt.getDefaultDailyShapeObj().getStdDev() * 100.0));
		pw.println("set LDCurve=" + ckt.getLoadDurCurve());  // load duration curve
		pw.println("set %growth=" + String.format("%-.4g", ((ckt.getDefaultGrowthRate() - 1.0) * 100.0)));  // default growth rate
		autoAdd = ckt.getAutoAddObj();
		pw.println("set genkw=" + String.format("%g", autoAdd.getGenKW()));
		pw.println("set genpf=" + String.format("%g", autoAdd.getGenPF()));
		pw.println("set capkvar=" + String.format("%g", autoAdd.getCapKVAr()));
		pw.print("set addtype=");
		switch (autoAdd.getAddType()) {
		case GEN:
			pw.println("generator");
			break;
		default:
			pw.println("capacitor");
			break;
		}
		pw.print("set allowduplicates=");
		pw.println(ckt.isDuplicatesAllowed() ? "yes" : "no");
		pw.print("set zonelock=");
		pw.println(ckt.isZonesLocked() ? "yes" : "no");
		pw.println("set ueweight=" + ckt.getUEWeight());
		pw.println("set lossweight=" + ckt.getLossWeight());
		pw.println("set ueregs=" + Util.intArrayToString(ckt.getUERegs(), ckt.getNumUERegs()));
		pw.println("set lossregs=" + Util.intArrayToString(ckt.getLossRegs(), ckt.getNumLossRegs()));
		pw.print("set voltageBases=(");  //  changes the default voltage base rules
		i = 0;
		while (ckt.getLegalVoltageBase(i) > 0.0) {
			pw.print(ckt.getLegalVoltageBase(i));
			i++;
		}
		pw.println(")");
		switch (algorithm) {
		case NORMAL:
			pw.println("set algorithm=normal");
			break;
		default:
			pw.println("set algorithm=newton");
			break;
		}
		pw.print("set trapezoidal=");
		pw.println(ckt.isTrapezoidalIntegration() ? "yes" : "no");
		pw.println("set genmult=" + String.format("%g", ckt.getGenMultiplier()));

		pw.println("set basefrequency=" + String.format("%g", ckt.getFundamental()));

		pw.print("set harmonics=(");  // changes the default voltage base rules
		if (doAllHarmonics) {
			pw.print("all");
		} else {
			for (i = 0; i < harmonicListSize; i++) {
				pw.printf("%g, ", harmonicList[i]);
			}
		}
		pw.println(")");
		pw.println("set maxControlIter=" + maxControlIterations);
		pw.println();

		if (complete) {
			Y = ckt.getSolution().getY();

			// get the compressed columns out of KLU
			YMatrix.factorSparseMatrix(Y);  // no extra work if already done
			YMatrix.getNNZ(Y, ip);
			nNZ = ip[0];
			YMatrix.getSize(Y, ip);
			nBus = ip[0];
			ColPtr = new int[nBus + 1];
			RowIdx = new int[nNZ];
			cVals = new Complex[nNZ];
			YMatrix.getCompressedMatrix(Y, nBus + 1, nNZ, ColPtr, RowIdx, cVals);

			pw.println("System Y matrix (lower triangle by columns)");
			pw.println();
			pw.println("  row  col               G               B");
			pw.println();

			// traverse the compressed column format
			for (j = 0; j < nBus; j++) {
				for (p = ColPtr[j]; p < ColPtr[j + 1]; p++) {
					i = RowIdx[p];  // the zero-based row
					pw.printf("[%4d,%4d] = %12.5g + j%12.5g", i, j,
						cVals[p].getReal(), cVals[p].getImaginary());
					pw.println();
				}
			}
		}
	}

	/**
	 * Difference between two node voltages.
	 *
	 * @param iref one based node ref
	 * @param jref one based node ref
	 * @return
	 */
	public Complex vDiff(int iref, int jref) {
		return nodeV[iref - 1].subtract(nodeV[jref - 1]);  // V1-V2;
	}

	public void writeConvergenceReport(String fileName) {
		int i;
		NodeBus nb;
		FileWriter fw;
		PrintWriter f;

		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(fileName);
			f = new PrintWriter(fw);

			f.println();
			f.println("-------------------");
			f.println("Convergence Report:");
			f.println("-------------------");
			f.println("\"Bus.node\", \"Error\", \"|V|\",\"Vbase\"");

			for (i = 0; i < ckt.getNumNodes(); i++) {
				nb = ckt.getMapNodeToBus(i + 1);
				f.print("\"" + Util.pad((ckt.getBusList().get(nb.busRef - 1) + "." + nb.nodeNum + "\""), 18));
				f.printf(", %10.5s", errorSaved[i]);
				f.printf(", %14s", VMagSaved[i]);
				f.printf(", %14s", nodeVBase[i]);
				f.println();
			}

			f.println();
			f.printf("Max error = %10.5s", maxError);
			f.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered writing convergence report: " + e.getMessage(), -1);
		} finally {
			Util.fireOffEditor(fileName);
		}
	}

	private void sumAllCurrents() {
		for (CktElement elem : DSS.activeCircuit.getCktElements())
			elem.sumCurrents();  // sum terminal currents into system currents array
	}

	public void doControlActions() {
		boolean succ;
		int[] xHour = new int[1];
		double[] xSec = new double[1];

		Circuit ckt = DSS.activeCircuit;

		switch (controlMode) {
		// execute the nearest set of control actions time-wise.
		case CTRLSTATIC:
			if (ckt.getControlQueue().isEmpty()) {
				controlActionsDone = true;
			} else {
				ckt.getControlQueue().doNearestActions(xHour, xSec);  // ignore time advancement
			}
			break;
		case EVENTDRIVEN:
			succ = ckt.getControlQueue().doNearestActions(xHour, xSec);  // advances time
			intHour = xHour[0];
			dynaVars.t = xSec[0];
			controlActionsDone = !succ;
			break;
		case TIMEDRIVEN:
			if (!ckt.getControlQueue().doActions(intHour, dynaVars.t))
				controlActionsDone = true;
			break;
		}
	}

	public void sampleControlDevices() throws ControlProblem {
		ControlElem controlDevice;
		Circuit ckt = DSS.activeCircuit;

		try {
			// sample all controls and set action times in control queue.
			for (int i = 0; i < ckt.getControls().size(); i++) {
				controlDevice = ckt.getControls().get(i);
				if (controlDevice.isEnabled()) controlDevice.sample();
			}
		} catch (Exception e) {
			DSS.doSimpleMsg("Error encountered sampling control device: " + e.getMessage(), 484);
			throw new ControlProblem("Solution aborted.");
		}
	}

	/**
	 * Sample and do.
	 *
	 * @throws ControlProblem
	 */
	public void sampleDoControlActions() throws ControlProblem {
		if (controlMode == ControlMode.CONTROLSOFF) {
			controlActionsDone = true;
		} else {
			sampleControlDevices();
			doControlActions();

			/* This variable lets control devices know the bus list has changed */
			DSS.activeCircuit.setControlBusNameRedefined(false);  // reset until next change
		}
	}

	public void setMode(SolutionMode value) {
		Circuit ckt = DSS.activeCircuit;

		intHour = 0;
		dynaVars.t = 0.0;
		updateDblHour();

		ckt.setTrapezoidalIntegration(false);

		if (!okForDynamics(value)) return;
		if (!okForHarmonics(value)) return;

		dynaVars.solutionMode = value;

		controlMode = defaultControlMode;  // revert to default mode
		loadModel = defaultLoadModel;

		isDynamicModel = false;
		isHarmonicModel = false;

		solutionInitialized = false;  // reinitialize solution when mode set (except dynamics)
		preserveNodeVoltages = false;  // don't do this unless we have to

		// reset defaults for solution modes
		switch (dynaVars.solutionMode) {
		case PEAKDAY:
			dynaVars.h = 3600.0;
			numberOfTimes = 24;
			break;
		case DAILYMODE:
			dynaVars.h = 3600.0;
			numberOfTimes = 24;
			break;
		case SNAPSHOT:
			intervalHrs = 1.0;
			numberOfTimes = 1;
			break;
		case YEARLYMODE:
			intervalHrs = 1.0;
			dynaVars.h = 3600.0;
			numberOfTimes = 8760;
			break;
		case DUTYCYCLE:
			dynaVars.h = 1.0;
			controlMode = ControlMode.TIMEDRIVEN;
			break;
		case DYNAMICMODE:
			dynaVars.h = 0.001;
			controlMode = ControlMode.TIMEDRIVEN;
			isDynamicModel = true;
			preserveNodeVoltages = true;  // need to do this in case Y changes during this mode
			break;
		case GENERALTIME:
			intervalHrs = 1.0;
			dynaVars.h = 3600.0;
			numberOfTimes = 1;  // just one time step per solve call expected
			break;
		case MONTECARLO1:
			intervalHrs = 1.0;
			break;
		case MONTECARLO2:
			dynaVars.h = 3600.0;
			break;
		case MONTECARLO3:
			intervalHrs = 1.0;
			break;
		case MONTEFAULT:
			isDynamicModel = true;
			break;
		case FAULTSTUDY:
			isDynamicModel = true;
			break;
		case LOADDURATION1:
			dynaVars.h = 3600.0;
			ckt.setTrapezoidalIntegration(true);
			break;
		case LOADDURATION2:
			intHour = 1;
			ckt.setTrapezoidalIntegration(true);
			break;
		case AUTOADDFLAG:
			intervalHrs = 1.0;
			ckt.getAutoAddObj().setModeChanged(true);
			break;
		case HARMONICMODE:
			controlMode = ControlMode.CONTROLSOFF;
			isHarmonicModel = true;
			loadModel = SolutionLoadModel.ADMITTANCE;
			preserveNodeVoltages = true;  // need to do this in case Y changes during this mode
			break;
		}

		/* Moved here so that mode is changed before reseting monitors, etc. */

		// reset meters and monitors
		DSS.monitorClass.resetAll();
		DSS.energyMeterClass.resetAll();
		Util.doResetFaults();
		Util.doResetControls();
	}

	private void addInAuxCurrents(Algorithm solveType) {
		Circuit ckt = DSS.activeCircuit;

		//for (int i = 0; i < ckt.getNumNodes(); i++)
		//	currents[i] = currents[i].add(auxCurrents[i]);

		// for now, only AutoAddObj uses this.
		if (dynaVars.solutionMode == SolutionMode.AUTOADDFLAG)
			ckt.getAutoAddObj().addCurrents(solveType);
	}

	public void zeroAuxCurrents() {
		for (int i = 0; i < DSS.activeCircuit.getNumNodes(); i++)
			auxCurrents[i] = Complex.ZERO;
	}

	public void checkFaultStatus() {
		for (FaultObj fault : DSS.activeCircuit.getFaults())
			fault.checkStatus(controlMode);
	}

	/**
	 * This procedure is called for solveDirect and any other solution method
	 * that does not get the injection currents for PC elements normally. In
	 * dynamics mode, generators are voltage sources ...
	 */
//	private void getMachineInjCurrents() {
//		// do machines in dynamics mode
//		if (isDynamicModel) {
//			for (GeneratorObj elem : DSS.activeCircuit.getGenerators())
//				if (elem.isEnabled())
//					elem.injCurrents();  // uses nodeRef to add current into injCurr array
//		}
//	}

	private boolean okForDynamics(SolutionMode value) {
		boolean valueIsDynamic;
		boolean ok = true;

		switch (value) {
		case MONTEFAULT:
			valueIsDynamic = true;
			break;
		case DYNAMICMODE:
			valueIsDynamic = true;
			break;
		case FAULTSTUDY:
			valueIsDynamic = true;
			break;
		default:
			valueIsDynamic = false;
			break;
		}

		/* When we go in and out of dynamics mode, we have to do some special things */
		if (isDynamicModel && !valueIsDynamic)
			Util.invalidateAllPCElements();  // force recomp of YPrims when we leave dynamics mode

		if (!isDynamicModel && valueIsDynamic) {  // see if conditions right for going into dynamics
			if (DSS.activeCircuit.isSolved()) {
				Util.calcInitialMachineStates();  // set state variables for machines (loads and generators)
			} else {
				/* Raise error message if not solved */
				DSS.doSimpleMsg("Circuit must be solved in a non-dynamic mode before entering dynamics or fault study modes!" + DSS.CRLF +
						"If you attempted to solve, then the solution has not yet converged.", 486);
				if (DSS.inRedirect) DSS.redirectAbort = true;
				ok = false;
			}
		}
		return ok;
	}

	/**
	 * When we go in and out of harmonics mode, we have to do some special things.
	 */
	private boolean okForHarmonics(SolutionMode value) {
		boolean ok = true;
		Circuit ckt = DSS.activeCircuit;

		if (isHarmonicModel && !(value == SolutionMode.HARMONICMODE)) {
			Util.invalidateAllPCElements();  // force recomp of YPrims when we leave harmonics mode
			frequency = ckt.getFundamental();   // resets everything to norm
		}

		if (!isHarmonicModel && (value == SolutionMode.HARMONICMODE)) {  // see if conditions right for going into harmonics
			if (ckt.isSolved() && (frequency == ckt.getFundamental())) {
				if (!Util.initializeForHarmonics()) {  // set state variables for machines (loads and generators) and sources
					ok = false;
					if (DSS.inRedirect) DSS.redirectAbort = true;
				}
			} else {
				DSS.doSimpleMsg("Circuit must be solved in a fundamental frequency power flow or direct mode before entering harmonics mode!", 487);
				if (DSS.inRedirect) DSS.redirectAbort = true;
				ok = false;
			}
		}
		return ok;
	}

	public void setFrequency(double value) {
		Circuit ckt = DSS.activeCircuit;

		if (frequency != value) {
			frequencyChanged = true;  // force rebuild of all Y primitives
			systemYChanged = true;    // force rebuild of system Y
		}

		frequency = value;

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

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "");

		super.initPropertyValues(Solution.NumPropsThisClass - 1);
	}

	public void setYear(int value) {
		if (DSS.DIFilesAreOpen) DSS.energyMeterClass.closeAllDIFiles();

		year = value;
		intHour = 0;  /* Change year, start over */
		dynaVars.t = 0.0;
		updateDblHour();

		DSS.energyMeterClass.resetAll();  // force any previous year data to complete
	}

	public void saveVoltages() {
		int i, j;
		FileWriter fw;
		PrintWriter pw;
		Complex V;
		String busName;
		Circuit ckt = DSS.activeCircuit;

		try {
			fw = new FileWriter(DSS.circuitName_ + "SavedVoltages.txt");
			pw = new PrintWriter(fw);

			for (i = 0; i < ckt.getNumBuses(); i++) {
				busName = ckt.getBusList().get(i);
				for (j = 0; j < ckt.getBus(i).getNumNodesThisBus(); j++) {
					V = getNodeV(ckt.getBus(i).getRef(j));
					pw.println(busName + ", " + ckt.getBus(i).getNum(j) +
						String.format(", %-.7g, %-.7g", V.abs(), ComplexUtil.degArg(V)));
				}
			}

			pw.close();
			DSS.globalResult = DSS.circuitName_ + "SavedVoltages.txt";

		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening saved voltages file: " + e.getMessage(), 488);
			return;
		}
	}

	/**
	 * *************  MAIN SOLVER CALL *************
	 *
	 * @throws SolverProblem
	 */
	private int solveSystem(Complex[] V) throws SolverProblem {
		int retCode;
		//double[] dp = new double[1];
		//int[] ip = new int[1];

		/* Note: NodeV[0] = 0 + j0 always. Therefore, pass the address of the element 1 of the array. */
		try {
			/* log KLUSolve function calls */
			YMatrix.setLogFile("KLU_Log.txt", 1);

			retCode = YMatrix.solveSparseSet(Y, V, 1, currents, 1);  // solve for present injCurr

			/* information functions */
			//YMatrix.getFlops(Y, dp);
			//YMatrix.getRGrowth(Y, dp);
			//YMatrix.getRCond(Y, dp);
			//YMatrix.getCondEst(Y, dp);  // this can be expensive
			//YMatrix.getSize(Y, ip);
			//YMatrix.getNNZ(Y, ip);
			//YMatrix.getSparseNNZ(Y, ip);
			//YMatrix.getSingularCol(Y, ip);
		} catch (Exception e) {
			throw new SolverProblem("Error solving system Y matrix. Sparse matrix solver reports numerical error: " +
					e.getMessage());
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
		Circuit ckt = DSS.activeCircuit;

		for (int i = 0; i < ckt.getNumBuses(); i++) {
			bus = ckt.getBus(i);
			if (bus.getVBus() != null) {
				for (int j = 0; j < bus.getNumNodesThisBus(); j++)
					bus.getVBus()[j] = getNodeV(bus.getRef(j));
			}
		}
	}

	/**
	 * Opposite of updateVBus().
	 */
	public void restoreNodeVFromVbus() {
		Bus bus;
		Circuit ckt = DSS.activeCircuit;

		for (int i = 0; i < ckt.getNumBuses(); i++) {
			bus = ckt.getBus(i);
			if (bus.getVBus() != null) {
				for (int j = 0; j < bus.getNumNodesThisBus(); j++)
					nodeV[bus.getRef(j)] = bus.getVBus(j);
			}
		}
	}

	/**
	 * Similar to solveDirect(); used for initialization.
	 * Solves present Y matrix with no injection sources except voltage and current sources.
	 *
	 * @throws SolverProblem
	 */
	public int solveYDirect() throws SolverProblem {
		zeroInjCurr();  // side effect: allocates injCurr
		getSourceInjCurrents();
		if (isDynamicModel) getPCInjCurr();  // Need this in dynamics mode to pick up additional injections

		solveSystem(nodeV);  // solve with zero injection current
		return 0;
	}

	/**
	 * Get the complex node voltage.
	 *
	 * @param nref one based node reference
	 * @return complex node voltage
	 */
	public Complex getNodeV(int nref) {
		return nodeV[nref - 1];
	}

	public Complex[] getNodeV() {
		return nodeV;
	}

	/**
	 * @param nref one based node reference
	 * @return complex current
	 */
	public Complex getCurrent(int nref) {
		return currents[nref - 1];
	}

	/**
	 * @param nref one based node reference
	 * @param current complex current
	 */
	public void setCurrent(int nref, Complex current) {
		currents[nref - 1] = current;
	}

	public SolutionMode getMode() {
		return dynaVars.solutionMode;
	}

	public boolean lastSolutionWasDirect() {
		return lastSolutionWasDirect;
	}

	public boolean loadsNeedUpdating() {
		return loadsNeedUpdating;
	}

	public double getHarmonic(int idx) {
		return harmonicList[idx];
	}

	public double getHarmonic() {
		return harmonic;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public Complex[] getAuxCurrents() {
		return auxCurrents;
	}

	public int getControlIteration() {
		return controlIteration;
	}

	public ControlMode getControlMode() {
		return controlMode;
	}

	public double getConvergenceTolerance() {
		return convergenceTolerance;
	}

	public double getDblHour() {
		return dblHour;
	}

	public Complex[] getCurrents() {
		return currents;
	}

	public SolutionLoadModel getDefaultLoadModel() {
		return defaultLoadModel;
	}

	public DynamicsRec getDynaVars() {
		return dynaVars;
	}

	public double getFrequency() {
		return frequency;
	}

	public double[] getHarmonicList() {
		return harmonicList;
	}

	public int getHarmonicListSize() {
		return harmonicListSize;
	}

	public int getIntHour() {
		return intHour;
	}

	public double getIntervalHrs() {
		return intervalHrs;
	}

	public int getIteration() {
		return iteration;
	}

	public SolutionLoadModel getLoadModel() {
		return loadModel;
	}

	public int getMaxControlIterations() {
		return maxControlIterations;
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public int getMostIterationsDone() {
		return mostIterationsDone;
	}

	public double[] getNodeVBase() {
		return nodeVBase;
	}

	public int getNumberOfTimes() {
		return numberOfTimes;
	}

	public int getYear() {
		return year;
	}

	public UUID getY() {
		return Y;
	}

	public Randomization getRandomType() {
		return randomType;
	}

	public int getSolutionCount() {
		return solutionCount;
	}

	public boolean isDynamicModel() {
		return isDynamicModel;
	}

	public boolean isHarmonicModel() {
		return isHarmonicModel;
	}

	public boolean isPreserveNodeVoltages() {
		return preserveNodeVoltages;
	}

	public boolean isSystemYChanged() {
		return systemYChanged;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public void setAuxCurrents(Complex[] auxCurrents) {
		this.auxCurrents = auxCurrents;
	}

	public void setControlMode(ControlMode controlMode) {
		this.controlMode = controlMode;
	}

	public void setConvergenceTolerance(double convergenceTolerance) {
		this.convergenceTolerance = convergenceTolerance;
	}

	public void setDefaultControlMode(ControlMode defaultControlMode) {
		this.defaultControlMode = defaultControlMode;
	}

	public void setDefaultLoadModel(SolutionLoadModel defaultLoadModel) {
		this.defaultLoadModel = defaultLoadModel;
	}

	public void setDblHour(double dblHour) {
		this.dblHour = dblHour;
	}

	public void setCurrents(Complex[] currents) {
		this.currents = currents;
	}

	public boolean isDoAllHarmonics() {
		return doAllHarmonics;
	}

	public void setDoAllHarmonics(boolean doAllHarmonics) {
		this.doAllHarmonics = doAllHarmonics;
	}

	public boolean isFrequencyChanged() {
		return frequencyChanged;
	}

	public void setFrequencyChanged(boolean frequencyChanged) {
		this.frequencyChanged = frequencyChanged;
	}

	public UUID getYSystem() {
		return YSystem;
	}

	public UUID getYSeries() {
		return YSeries;
	}

	public void setErrorSaved(double[] errorSaved) {
		this.errorSaved = errorSaved;
	}

	public void setHarmonicList(double[] harmonicList) {
		this.harmonicList = harmonicList;
	}

	public void setHarmonicListSize(int harmonicListSize) {
		this.harmonicListSize = harmonicListSize;
	}

	public void setIntHour(int intHour) {
		this.intHour = intHour;
	}

	public void setIntervalHrs(double intervalHrs) {
		this.intervalHrs = intervalHrs;
	}

	public void setLoadModel(SolutionLoadModel loadModel) {
		this.loadModel = loadModel;
	}

	public void setLoadsNeedUpdating(boolean loadsNeedUpdating) {
		this.loadsNeedUpdating = loadsNeedUpdating;
	}

	public void setMaxControlIterations(int maxControlIterations) {
		this.maxControlIterations = maxControlIterations;
	}

	public void setYSystem(UUID ySystem) {
		YSystem = ySystem;
	}

	public void setYSeries(UUID ySeries) {
		YSeries = ySeries;
	}

	public void setY(UUID y) {
		Y = y;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	public void setNumberOfTimes(int numberOfTimes) {
		this.numberOfTimes = numberOfTimes;
	}

	public void setRandomType(Randomization randomType) {
		this.randomType = randomType;
	}

	public void setSeriesYInvalid(boolean seriesYInvalid) {
		this.seriesYInvalid = seriesYInvalid;
	}

	public void setSolutionInitialized(boolean solutionInitialized) {
		this.solutionInitialized = solutionInitialized;
	}

	public void setSystemYChanged(boolean systemYChanged) {
		this.systemYChanged = systemYChanged;
	}

	public void setUseAuxCurrents(boolean useAuxCurrents) {
		this.useAuxCurrents = useAuxCurrents;
	}

	public void setVMagSaved(double[] vMagSaved) {
		VMagSaved = vMagSaved;
	}

	public void setVoltageBaseChanged(boolean voltageBaseChanged) {
		this.voltageBaseChanged = voltageBaseChanged;
	}

	public void setNodeV(Complex[] nodeV) {
		this.nodeV = nodeV;
	}

	public void setNodeVBase(double[] nodeVBase) {
		this.nodeVBase = nodeVBase;
	}

}
