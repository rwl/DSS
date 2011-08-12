package com.epri.dss.common.impl;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.conversion.ISourceObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.conversion.VSourceObj;
import com.epri.dss.delivery.FaultObj;
import com.epri.dss.delivery.impl.FaultImpl;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.SpectrumObj;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

/**
 * Solution algorithms.
 *
 */
public class SolutionAlgs {

	private static int progressCount;

	private SolutionAlgs() {
	}

	private static void show10PctProgress(int i, int n) {
		DSSGlobals globals = DSSGlobals.getInstance();

		if (globals.isNoFormsAllowed())
			return;

		if (((i * 10) / n) > progressCount) {
			progressCount += 1;
			globals.getDSSForms().showPctProgress(progressCount * 10);
		}
	}

	/**
	 * Solve following yearly cycle.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveYearly() throws SolverError, ControlProblem, Esolv32Problem {
		int twoPct;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		globals.getDSSForms().progressCaption("Solving Year " + String.valueOf(sol.getYear()));
		progressCount = 0;
		globals.getDSSForms().showPctProgress(progressCount);

		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage elements
			if (!globals.isDIFilesAreOpen())
				globals.getEnergyMeterClass().openAllDIFiles();   // open demand interval files, if desired, creates DI_Totals
			twoPct = Math.max(sol.getNumberOfTimes() / 50, 1);
			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!globals.isSolutionAbort()) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultYearlyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );
					sol.solveSnap();
					globals.getMonitorClass().sampleAll();  // make all monitors take a sample
					globals.getEnergyMeterClass().sampleAll();  // make all energy meters take a sample
					globals.getStorageClass().updateAll();
					if ((N % twoPct) == 0)
						globals.getDSSForms().showPctProgress((N * 100) / sol.getNumberOfTimes());
				}
			}
		} finally {
			globals.getDSSForms().progressHide();
			globals.getMonitorClass().saveAll();
			//Globals.getEnergyMeterClass().closeAllDIFiles();  // save demand interval files, see DIFilesAreOpen logic
		}
		return 0;
	}

	/**
	 * Solve following daily cycle.
	 *
	 * Stepsize defaults to 1 hr and number of times = 24.
	 * Load is modified by yearly growth, time of day, and global load multiplier.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveDaily() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		//t = 0.0;
		//Globals.getMonitorClass().resetAll();
		//Globals.getEnergyMeterClass().resetAll();

		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters
			ckt.setDefaultDailyShapeObj((LoadShapeObj) globals.getLoadShapeClass().find("default"));
			if (!globals.isDIFilesAreOpen())
				globals.getEnergyMeterClass().openAllDIFiles();  // append demand interval files, if desired

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!globals.isSolutionAbort()) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );
					sol.solveSnap();
					globals.getMonitorClass().sampleAll();  // make all monitors take a sample
					globals.getEnergyMeterClass().sampleAll(); // make all energy meters take a sample
					globals.getStorageClass().updateAll();
				}
			}
		} finally {
			globals.getMonitorClass().saveAll();
			globals.getEnergyMeterClass().closeAllDIFiles();  // save demand interval files
		}
		return 0;
	}

	/**
	 * Solve following daily cycle at peak load.
	 *
	 * Takes the given load kW and assumes it represents the peak value.
	 * Load is modified by daily load curve and growth factor for the year.
	 * 'h' defaults to 3600 (1 hr) but can be reset to anything.
	 * Differs from Daily mode in that the global load multiplier is ignored.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 *
	 */
	public static int solvePeakDay() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		sol.getDynaVars().t = 0.0;

		globals.getMonitorClass().resetAll();
		globals.getEnergyMeterClass().resetAll();
		try {
			sol.setIntHour(0);
			sol.setDblHour(0.0);
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			ckt.setDefaultDailyShapeObj((LoadShapeObj) globals.getLoadShapeClass().find("default"));
			if (!globals.isDIFilesAreOpen())
				globals.getEnergyMeterClass().openAllDIFiles();  // open demand interval files, if desired

			for (int N = 0; N < sol.getNumberOfTimes(); N++)
				if (!globals.isSolutionAbort()) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );
					sol.solveSnap();
					globals.getMonitorClass().sampleAll();  // make all monitors take a sample
					globals.getEnergyMeterClass().sampleAll(); // make all energy meters take a sample
					globals.getStorageClass().updateAll();
				}
		} finally {
			globals.getMonitorClass().saveAll();
			globals.getEnergyMeterClass().closeAllDIFiles();  // save demand interval files
		}

		return 0;
	}

	/**
	 * Solve following duty cycle.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveDuty() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		globals.getDSSForms().progressCaption("Duty Cycle Solution");
		progressCount = 0;
		globals.getDSSForms().showPctProgress(0);

		//t = 0.0;
		//Globals.getMonitorClass().resetAll();
		int TwoPct = Math.max(1, sol.getNumberOfTimes() / 50);
		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			for (int N = 0; N < sol.getNumberOfTimes(); N++)
				if (!globals.isSolutionAbort()) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					// assume price signal stays constant for dutycycle calcs
					sol.solveSnap();
					globals.getMonitorClass().sampleAll();  // make all monitors take a sample
					globals.getStorageClass().updateAll();

					if (N % TwoPct == 0)
						globals.getDSSForms().showPctProgress((N * 100) / sol.getNumberOfTimes());
				}
		} finally {
			globals.getMonitorClass().saveAll();
			globals.getDSSForms().progressHide();
		}

		return 0;
	}

	/**
	 * For rolling your own solution modes.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveGeneralTime() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices

		for (int N = 0; N < sol.getNumberOfTimes(); N++)
			if (!globals.isSolutionAbort()) {
				/* Compute basic multiplier from default load shape to use in generator dispatch, if any */
				ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));

				sol.solveSnap();
				globals.getMonitorClass().sampleAll();  // make all monitors take a sample
				globals.getStorageClass().updateAll();
				sol.incrementTime();
			}

		return 0;
	}

	/**
	 * Integrate states in all PC Elements.  At present, only PC Elements
	 * can have dynamic states.
	 */
	public static void integratePCStates() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (PCElement pcElem : ckt.getPCElements())
			pcElem.integrateStates();
	}

	/**
	 * Solve dynamics.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveDynamic() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			sol.setSolutionInitialized(true);  // if we're in dynamics mode, no need to re-initialize.
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			for (int N = 0; N < sol.getNumberOfTimes(); N++)
				if (!globals.isSolutionAbort()) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					// assume price signal stays constant for dynamic calcs
					/* Predictor */
					sol.getDynaVars().IterationFlag = 0;
					integratePCStates();
					sol.solveSnap();
					/* Corrector */
					sol.getDynaVars().IterationFlag = 1;
					integratePCStates();
					sol.solveSnap();
					globals.getMonitorClass().sampleAll();  // make all monitors take a sample
					globals.getStorageClass().updateAll();
				}
		} finally {
			globals.getMonitorClass().saveAll();
		}

		return 0;
	}

	/**
	 * Solve Monte Carlo solution.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveMonte1() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			ckt.setLoadMultiplier(1.0);   // always set with prop in case matrix must be rebuilt
			sol.setIntervalHrs(1.0);      // needed for energy meters and storage devices
			sol.setIntHour(0);
			sol.setDblHour(0.0);          // use hour to denote case number
			sol.getDynaVars().t = 0.0;

			//Globals.getMonitorClass().resetAll();
			//Globals.getEnergyMeterClass().resetAll();

			globals.getDSSForms().progressCaption("Monte Carlo Mode 1, " + String.valueOf(sol.getNumberOfTimes()) + " Random Loads.");
			progressCount = 0;

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!globals.isSolutionAbort()) {
					sol.setIntHour(sol.getIntHour() + 1);
					sol.solveSnap();
					globals.getMonitorClass().sampleAll();  // make all monitors take a sample
					globals.getEnergyMeterClass().sampleAll();  // make all meters take a sample
					show10PctProgress(N, sol.getNumberOfTimes());
				} else {
					globals.setErrorNumber(DSSGlobals.SOLUTION_ABORT);
					globals.setCmdResult(globals.getErrorNumber());
					globals.setGlobalResult("Solution Aborted");
					break;
				}
			}
		} finally {
			globals.getMonitorClass().saveAll();
			globals.getDSSForms().progressHide();
		}

		return 0;
	}

	/**
	 * Solve Monte Carlo solution.
	 * Do a daily load solution for several random days.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveMonte2() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			sol.getDynaVars().t = 0.0;
			sol.setIntHour(0);
			sol.setDblHour(0.0);
			//Globals.getMonitorClass().resetAll();
			//Globals.getEnergyMeterClass().resetAll();
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			int nDaily = (int) Math.round(24.0 / sol.getIntervalHrs());

			if (!globals.isDIFilesAreOpen())
				globals.getEnergyMeterClass().openAllDIFiles();  // open demand interval files, if desired

			globals.getDSSForms().progressCaption("Monte Carlo Mode 2, " + String.valueOf(sol.getNumberOfTimes()) + " Days.");
			progressCount = 0;

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!globals.isSolutionAbort()) {  // number of days

					// always set loadMultiplier with prop in case matrix must be rebuilt
					switch (sol.getRandomType()) {
					case DSSGlobals.UNIFORM:
						ckt.setLoadMultiplier(Math.random());  // number between 0 and 1
						break;
					case DSSGlobals.GAUSSIAN:
						ckt.setLoadMultiplier(MathUtil.gauss(ckt.getDefaultDailyShapeObj().getMean(), ckt.getDefaultDailyShapeObj().getStdDev()));
						break;
					}

					for (int i = 0; i < nDaily; i++) {
						sol.incrementTime();
						ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
						sol.solveSnap();

						globals.getMonitorClass().sampleAll();  // make all monitors take a sample
						globals.getEnergyMeterClass().sampleAll();  // make all meters take a sample
						globals.getStorageClass().updateAll();
					}

					show10PctProgress(N, sol.getNumberOfTimes());
				} else {
					globals.setErrorNumber(DSSGlobals.SOLUTION_ABORT);
					globals.setCmdResult(globals.getErrorNumber());
					globals.setGlobalResult("Solution Aborted.");
					break;
				}
			}
		} finally {
			globals.getMonitorClass().saveAll();
			globals.getEnergyMeterClass().closeAllDIFiles();  // save demand interval files
			globals.getDSSForms().progressHide();
		}

		return 0;
	}

	/**
	 * Solve Monte Carlo solution.
	 *
	 * Hold time fixed and just vary the global load multiplier.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveMonte3() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		// time must be set beFore entering this routine
		try {
			//Globals.getMonitorClass().resetAll();
			//Globals.getEnergyMeterClass().resetAll();
			sol.setIntervalHrs(1.0);  // just get per unit energy and multiply result as necessary

			if (!globals.isDIFilesAreOpen())
				globals.getEnergyMeterClass().openAllDIFiles();  // Open Demand Interval files, if desired

			globals.getDSSForms().progressCaption("Monte Carlo Mode 3, " + String.valueOf(sol.getNumberOfTimes()) + " Different Load Levels.");
			progressCount = 0;

			ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
			if (ckt.getPriceCurveObj() != null)
				ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!globals.isSolutionAbort()) {
					// always set loadMultiplier with prop in case matrix must be rebuilt
					switch (sol.getRandomType()) {
					case DSSGlobals.UNIFORM:
						ckt.setLoadMultiplier(Math.random());  // number between 0 and 1
						break;
					case DSSGlobals.GAUSSIAN:
						ckt.setLoadMultiplier(MathUtil.gauss(ckt.getDefaultDailyShapeObj().getMean(), ckt.getDefaultDailyShapeObj().getStdDev()));
						break;
					case DSSGlobals.LOGNORMAL:
						ckt.setLoadMultiplier(MathUtil.quasiLognormal(ckt.getDefaultDailyShapeObj().getMean()));
						break;
					}

					sol.solveSnap();

					globals.getMonitorClass().sampleAll();  // make all monitors take a sample
					globals.getEnergyMeterClass().sampleAll();  // make all meters take a sample

					show10PctProgress(N, sol.getNumberOfTimes());
				} else {
					globals.setCmdResult(DSSGlobals.SOLUTION_ABORT);
					globals.setErrorNumber(globals.getCmdResult());
					globals.setGlobalResult("Solution Aborted");
					break;
				}
			}
		} finally {
			globals.getMonitorClass().saveAll();
			globals.getEnergyMeterClass().closeAllDIFiles();  // save demand interval files
			globals.getDSSForms().progressHide();
		}

		return 0;
	}

	/**
	 * Solve Load-Duration Curve, 1.
	 *
	 * Do a Daily Simulation based on a load duration curve.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveLD1() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			if (ckt.getLoadDurCurveObj() == null) {
				globals.doSimpleMsg("Load Duration Curve Not Defined (Set LDCurve=... command). Cannot perform solution.", 470);
				return 0;
			}

			// time must be set before entering this routine

			//Globals.getMonitorClass().resetAll();
			//Globals.getEnergyMeterClass().resetAll();

			int nDaily = (int) Math.round(24.0 / sol.getDynaVars().h * 3600.0);

			if (!globals.isDIFilesAreOpen())
				globals.getEnergyMeterClass().openAllDIFiles();  // open demand interval files, if desired

			globals.getDSSForms().progressCaption("Load-Duration Mode 1 Solution.");

			// (set in solve method) ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (sol.getYear() - 1)));

			sol.setIntHour(0);
			for (int i = 0; i < nDaily; i++) {
				// set the time
				sol.incrementTime();

				ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));

				if (!globals.isSolutionAbort()) {
					for (int N = 0; N < ckt.getLoadDurCurveObj().getNumPoints(); N++) {
						// always set loadMultiplier with prop in case matrix must be rebuilt
						ckt.setLoadMultiplier(ckt.getLoadDurCurveObj().mult(N));
						// adjust meter interval to interval on value of present load-duration curve
						sol.setIntervalHrs(ckt.getLoadDurCurveObj().getInterval());

						// price curve must correspond to load-duration curve
						if (ckt.getPriceCurveObj() != null)
							ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(N) );

						sol.solveSnap();

						globals.getMonitorClass().sampleAll();  // make all monitors take a sample
						globals.getEnergyMeterClass().sampleAll();  // make all meters take a sample
						globals.getStorageClass().updateAll();
					}
					globals.getDSSForms().showPctProgress((i * 100) / nDaily);
				} else {
					globals.setCmdResult(DSSGlobals.SOLUTION_ABORT);
					globals.setErrorNumber(globals.getCmdResult());
					globals.setGlobalResult("Solution Aborted");
					break;
				}
			}
		} finally {
			globals.getMonitorClass().saveAll();
			globals.getEnergyMeterClass().closeAllDIFiles();  // save demand interval files
			globals.getDSSForms().progressHide();
		}

		return 0;
	}

	/**
	 * Solve Load-Duration Curve, 2.
	 * Hold time fixed and just vary the global load multiplier according to
	 * the global Load-Duration Curve.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveLD2() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		if (ckt.getLoadDurCurveObj() == null) {
			globals.doSimpleMsg("Load duration curve not defined (set ldcurve=... command). Can not perform solution.", 471);
			return 0;
		}

		// time must be set before entering this routine

		//Globals.getMonitorClass().resetAll();
		//Globals.getEnergyMeterClass().resetAll();

		ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
		if (!globals.isDIFilesAreOpen())
			globals.getEnergyMeterClass().openAllDIFiles();  // open demand interval files, if desired

		// (set in solve method) ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (sol.getYear() - 1)));

		try {
			if (globals.isSolutionAbort()) {
				globals.setCmdResult(DSSGlobals.SOLUTION_ABORT);
				globals.setErrorNumber(globals.getCmdResult());
				globals.setGlobalResult("Solution aborted.");
				return 0;
			}

			for (int N = 0; N < ckt.getLoadDurCurveObj().getNumPoints(); N++) {
				// adjust meter interval to interval on value of present load-duration curve
				// always set loadMultiplier with prop in case matrix must be rebuilt
				ckt.setLoadMultiplier(ckt.getLoadDurCurveObj().mult(N));
				sol.setIntervalHrs(ckt.getLoadDurCurveObj().getInterval());

				// price curve must correspond to load-duration curve
				if (ckt.getPriceCurveObj() != null)
					ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(N) );

				sol.solveSnap();

				globals.getMonitorClass().sampleAll();  // make all monitors take a sample
				globals.getEnergyMeterClass().sampleAll();  // make all meters take a sample
				globals.getStorageClass().updateAll();
			}
		} finally {
			globals.getMonitorClass().saveAll();
			globals.getEnergyMeterClass().closeAllDIFiles();  // save demand interval files
		}

		return 0;
	}

	/**
	 * Enable one of the faults in the circuit. Disable the rest.
	 */
	private static void pickAFault() {
		int whichOne;
		FaultObj faultObj;
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		int NumFaults = ckt.getFaults().size();
		whichOne = (int) (Math.random() * NumFaults) + 1;  // TODO Check zero based indexing
		if (whichOne > NumFaults)
			whichOne = NumFaults;

		for (int i = 0; i < NumFaults; i++) {
			faultObj = ckt.getFaults().get(i);
			if (i == whichOne) {
				FaultImpl.setActiveFaultObj(faultObj);  // in fault unit
				faultObj.setEnabled(true);
			} else {
				faultObj.setEnabled(false);
			}
		}
	}

	/**
	 * Solve Monte Carlo Fault Study.
	 *
	 * @throws Esolv32Problem
	 */
	public static int solveMonteFault() throws Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			sol.setLoadModel(DSSGlobals.ADMITTANCE);   // all direct solution
			ckt.setLoadMultiplier(1.0);  // always set loadMultiplier with prop in case matrix must be rebuilt
			sol.setIntHour(0);
			sol.setDblHour(0.0);  // use hour to denote case number
			sol.getDynaVars().t = 0.0;

			//Globals.getMonitorClass().resetAll();

			globals.getDSSForms().progressCaption("Monte Carlo Fault Study: " + String.valueOf(sol.getNumberOfTimes()) + " different faults.");
			progressCount = 0;

			sol.setGeneratorDispRef();

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!globals.isSolutionAbort()) {
					sol.setIntHour(sol.getIntHour() + 1);
					pickAFault();  // randomly enable one of the faults
					FaultImpl.getActiveFaultObj().randomize();  // randomize the fault resistance
					sol.solveDirect();
					globals.getMonitorClass().sampleAll();  // make all monitors take a sample

					show10PctProgress(N, sol.getNumberOfTimes());
				}
			}
		} finally {
			globals.getMonitorClass().saveAll();
			globals.getDSSForms().progressHide();
		}

		return 0;
	}

	private static void allocateAllSCParms() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (int i = 0; i < ckt.getNumBuses(); i++)
			ckt.getBuses()[i].allocateBusQuantities();
	}

	/**
	 * Compute Isc at all buses for current values of Voc and Ysc.
	 */
	private static void computeIsc() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (int i = 0; i < ckt.getNumBuses(); i++) {
			Bus bus = ckt.getBuses()[i];
			bus.getYsc().MVMult(bus.getBusCurrent(), bus.getVBus());
		}
	}

	/**
	 * Compute YSC for I-th bus.
	 * Assume InjCurr is zeroed.
	 *
	 * @throws Esolv32Problem
	 */
	public static void computeYsc(int iB) throws Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();
		Bus bus = ckt.getBuses()[iB];

		int ref1;

		bus.getZsc().clear();
		for (int i = 0; i < bus.getNumNodesThisBus(); i++) {
			ref1 = bus.getRef(i);
			if (ref1 > 0) {  // TODO Check zero based indexing
				sol.getCurrents()[ref1] = Complex.ONE;
				/* SparseSet expects 1st element of voltage array, not 0-th element */
				if (YMatrix.solveSparseSet(sol.getYSystem(), sol.getNodeV()[1], sol.getCurrents()[1]) < 1)
					throw new Esolv32Problem("Error solving system Y matrix in computeYsc. Problem with sparse matrix solver.");
				/* Extract voltage vector = column of Zsc */
				for (int j = 0; j < bus.getNumNodesThisBus(); j++)
					bus.getZsc().setElement(j ,i, sol.getNodeV()[bus.getRef(j)]);
				sol.getCurrents()[ref1] = Complex.ZERO;
			}
		}
		bus.getYsc().copyFrom(bus.getZsc());
		bus.getYsc().invert();  /* Save as admittance */
	}

	public static void computeAllYsc() {
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		for (int j = 0; j < ckt.getNumNodes(); j++)
			sol.getCurrents()[j] = Complex.ZERO;

		progressCount = 0;

		for (int iB = 0; iB < ckt.getNumBuses(); iB++) {
			try {
				computeYsc(iB);  // compute YSC for iB-th Bus
			} catch (Esolv32Problem e) {
				// TODO Auto-generated catch block
			}
			if (((iB * 10) / ckt.getNumBuses()) > progressCount) {
				progressCount += 1;
				globals.getDSSForms().showPctProgress(30 + progressCount * 5);
			}
		}
	}

	private static void disableAllFaults() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		for (FaultObj fault : ckt.getFaults()) {
			FaultImpl.setActiveFaultObj(fault);
			FaultImpl.getActiveFaultObj().setEnabled(false);
		}
	}

	/**
	 * Full fault study
	 *
	 * @throws Esolv32Problem
	 */
	public static int solveFaultStudy() throws Esolv32Problem {
		DSSGlobals globals = DSSGlobals.getInstance();
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		globals.getDSSForms().showPctProgress(0);
		globals.getDSSForms().progressCaption("Computing Open-Circuit Voltages");

		sol.setLoadModel(DSSGlobals.ADMITTANCE);
		disableAllFaults();
		sol.solveDirect();  // this gets the open circuit voltages and bus lists corrected

		allocateAllSCParms();  // reallocate bus quantities
		sol.updateVBus();  // put present solution Voc's in bus quantities

		globals.getDSSForms().progressCaption("Computing Ysc Matrices for each bus");
		globals.getDSSForms().showPctProgress(30);
		computeAllYsc();

		globals.getDSSForms().progressCaption("Computing short-circuit currents.");
		globals.getDSSForms().showPctProgress(80);
		computeIsc();

		globals.getDSSForms().showPctProgress(100);
		globals.getDSSForms().progressCaption("Done.");
		globals.getDSSForms().progressHide();
		// now should have all we need to make a short circuit report

		return 0;
	}

	/**
	 * Add unique frequency, F to list in ascending order, reallocating if necessary.
	 */
	private static void addFrequency(double[] freqList, int numFreq, int maxFreq, double f) {
		/* See if F is in list */

		for (int i = 0; i < numFreq; i++) {
			/* Allow a little tolerance (0.1 hz) for the frequency for round off error */
			if (Math.abs(f - freqList[i]) < 0.1)
				return;  // already in list, nothing to do
		}

		/* OK, it's not in list, so let's add it */
		numFreq += 1;
		if (numFreq > maxFreq) {
			maxFreq += 20;
			freqList = (double[]) Utilities.resizeArray(freqList, maxFreq);
		}

		/* let's add it in ascending order */
		for (int i = 0; i < numFreq - 1; i++) {
			if (f < freqList[i]) {
				/* push down array and insert it */
				for (int j = numFreq - 1; j >= i; j--)  // TODO Check count down logic
					freqList[j + 1] = freqList[j];
				freqList[i] = f;
				return;
			}
		}

		/* If we fall through, tack it on to the end */
		freqList[numFreq] = f;
	}

	private static double getSourceFrequency(PCElement pc) {
		VSourceObj pVsrc;
		ISourceObj pIsrc;

		if (pc.getDSSClassName().equalsIgnoreCase("vsource")) {
			pVsrc = (VSourceObj) pc;
			return pVsrc.getSrcFrequency();
		} else {
			pIsrc = (ISourceObj) pc;
			return pIsrc.getSrcFrequency();
		}
	}

	private static void collectAllFrequencies(double[] freqList, int numFreq) {
		DSSGlobals globals = DSSGlobals.getInstance();

		int[] spectrumInUse;
		int maxFreq;
		SpectrumObj pSpectrum;
		double f;

		/* Make a list of all frequencies in use */

		/* Accumulate all unique frequencies */
		maxFreq = 20;    // Initial List size
		numFreq = 0;
		freqList = (double[]) Utilities.resizeArray(freqList, maxFreq);

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		/* Check sources -- each could have a different base frequency */
		for (PCElement p : ckt.getSources()) {
			if (p.isEnabled()) {
				if (globals.getSpectrumClass().find(p.getSpectrum()) != null) {
					pSpectrum = (SpectrumObj) globals.getSpectrumClass().getActiveObj();
					f = getSourceFrequency(p);
					for (int j = 0; j < pSpectrum.getNumHarm(); j++) {
						addFrequency(freqList, numFreq, maxFreq, pSpectrum.getHarmArray()[j] * f);
					}
				}
			}
		}

		/* Mark spectra being used */

		/* Check loads and generators - these are assumed to be at fundamental frequency */
		spectrumInUse = new int[globals.getSpectrumClass().getElementCount()];  // allocate and zero
		for (PCElement p : ckt.getPCElements()) {
			if (p.isEnabled()) {
				if (globals.getSpectrumClass().find(p.getSpectrum()) != null) {
					spectrumInUse[globals.getSpectrumClass().getActiveElement()] = 1;
				}
			}
		}

		/* Add marked spectra to list */
		for (int i = 0; i < globals.getSpectrumClass().getElementCount(); i++) {
			if (spectrumInUse[i] == 1) {
				globals.getSpectrumClass().setActiveElement(i);
				pSpectrum = (SpectrumObj) globals.getSpectrumClass().getActiveObj();
				for (int j = 0; j < pSpectrum.getNumHarm(); j++) {
					addFrequency(freqList, numFreq, maxFreq, pSpectrum.getHarmArray()[j] * ckt.getFundamental());
				}
			}
		}

		spectrumInUse = null;
	}

	public static int solveHarmonic() throws Esolv32Problem {
		double[] frequencyList = new double[0];
		int nFreq = 0;

		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt = globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		globals.getDSSForms().showPctProgress(0);
		globals.getDSSForms().progressCaption("Performing Harmonic Solution");

		try {
			if (sol.getFrequency() != ckt.getFundamental()) {  // Last solution was something other than fundamental
				sol.setFrequency(ckt.getFundamental());
				if (!Utilities.retrieveSavedVoltages())
					return 0;  /* Get saved fundamental frequency solution */
			}

			globals.getMonitorClass().sampleAll();  // store the fundamental frequency in the monitors

			/* Get the list of harmonic frequencies to solve at */
			if (sol.isDoAllHarmonics()) {
				collectAllFrequencies(frequencyList, nFreq);  // allocates frequencyList  TODO Check allocation
			} else {
				frequencyList = (double[]) Utilities.resizeArray(frequencyList, sol.getHarmonicListSize());
				nFreq = sol.getHarmonicListSize();
				for (int i = 0; i < nFreq; i++) {
					frequencyList[i] = ckt.getFundamental() * sol.getHarmonicList()[i];
				}
			}

			for (int i = 0; i < nFreq; i++) {
				sol.setFrequency(frequencyList[i]);
				if (Math.abs(sol.getHarmonic() - 1.0) > DSSGlobals.EPSILON) {  // Skip fundamental
					globals.getDSSForms().progressCaption("Solving at frequency = " + String.format("%-g", sol.getFrequency()));
					globals.getDSSForms().showPctProgress((int) Math.round((100.0 * i) / nFreq));
					sol.solveDirect();
					globals.getMonitorClass().sampleAll();
					// storage devices are assumed to stay the same since there is no time variation in this mode
				}
			}

			globals.getDSSForms().showPctProgress(100);
			globals.getDSSForms().progressCaption("Done.");
		} finally {
			globals.getDSSForms().progressHide();
			globals.getMonitorClass().saveAll();
			frequencyList = null;
		}
		// now should have all we need to make a short circuit report

		return 0;
	}

	public static int getProgressCount() {
		return progressCount;
	}

	public static void setProgressCount(int count) {
		progressCount = count;
	}

}
