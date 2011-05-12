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

	private static int ProgressCount;

	private SolutionAlgs() {
	}

	private static void show10PctProgress(int i, int n) {
		if (DSSGlobals.getInstance().isNoFormsAllowed())
			return;

		if (((i * 10) / n) > ProgressCount) {
			ProgressCount += 1;
			DSSForms.showPctProgress(ProgressCount * 10);
		}
	}

	/**
	 * Solve following yearly cycle.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveYearly() throws SolverError, ControlProblem, Esolv32Problem {
		int TwoPct;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		DSSForms.progressCaption("Solving Year " + String.valueOf(sol.getYear()));
		ProgressCount = 0;
		DSSForms.showPctProgress(ProgressCount);

		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage elements
			if (!Globals.isDIFilesAreOpen())
				Globals.getEnergyMeterClass().openAllDIFiles();   // Open Demand Interval Files, if desired   Creates DI_Totals
			TwoPct = Math.max(sol.getNumberOfTimes() / 50, 1);
			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!Globals.isSolutionAbort()) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultYearlyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );
					sol.solveSnap();
					Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
					Globals.getEnergyMeterClass().sampleAll(); // Make all Energy Meters take a sample
					Globals.getStorageClass().updateAll();
					if ((N % TwoPct) == 0)
						DSSForms.showPctProgress((N * 100) / sol.getNumberOfTimes());
				}
			}
		} finally {
			DSSForms.progressHide();
			Globals.getMonitorClass().saveAll();
			//Globals.getEnergyMeterClass().closeAllDIFiles();   // Save Demand interval Files    See DIFilesAreOpen Logic
		}
		return 0;
	}

	/**
	 * Solve following daily cycle.
	 *
	 * Stepsize defaults to 1 hr and number of times = 24.
	 * Load is modified by yearly growth, time of day, and global load multiplier.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveDaily() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		//t = 0.0;
		//Globals.getMonitorClass().resetAll();
		//Globals.getEnergyMeterClass().resetAll();

		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters
			ckt.setDefaultDailyShapeObj((LoadShapeObj) Globals.getLoadShapeClass().find("default"));
			if (!Globals.isDIFilesAreOpen())
				Globals.getEnergyMeterClass().openAllDIFiles();  // Append Demand Interval Files, if desired

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!Globals.isSolutionAbort()) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );
					sol.solveSnap();
					Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
					Globals.getEnergyMeterClass().sampleAll(); // Make all Energy Meters take a sample
					Globals.getStorageClass().updateAll();
				}
			}
		} finally {
			Globals.getMonitorClass().saveAll();
			Globals.getEnergyMeterClass().closeAllDIFiles();  // Save Demand interval Files
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
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 *
	 */
	public static int solvePeakDay() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		sol.getDynaVars().t = 0.0;

		Globals.getMonitorClass().resetAll();
		Globals.getEnergyMeterClass().resetAll();
		try {
			sol.setIntHour(0);
			sol.setDblHour(0.0);
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			ckt.setDefaultDailyShapeObj((LoadShapeObj) Globals.getLoadShapeClass().find("default"));
			if (!Globals.isDIFilesAreOpen())
				Globals.getEnergyMeterClass().openAllDIFiles();  // Open Demand Interval Files, if desired

			for (int N = 0; N < sol.getNumberOfTimes(); N++)
				if (!Globals.isSolutionAbort()) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );
					sol.solveSnap();
					Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
					Globals.getEnergyMeterClass().sampleAll(); // Make all Energy Meters take a sample
					Globals.getStorageClass().updateAll();
				}
		} finally {
			Globals.getMonitorClass().saveAll();
			Globals.getEnergyMeterClass().closeAllDIFiles();  // Save Demand interval Files
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
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		DSSForms.progressCaption("Duty Cycle Solution");
		ProgressCount = 0;
		DSSForms.showPctProgress(0);

		//t = 0.0;
		//Globals.getMonitorClass().resetAll();
		int TwoPct = Math.max(1, sol.getNumberOfTimes() / 50);
		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			for (int N = 0; N < sol.getNumberOfTimes(); N++)
				if (!Globals.isSolutionAbort()) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					// Assume price signal stays constant for dutycycle calcs
					sol.solveSnap();
					Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
					Globals.getStorageClass().updateAll();

					if (N % TwoPct == 0)
						DSSForms.showPctProgress((N * 100) / sol.getNumberOfTimes());
				}
		} finally {
			Globals.getMonitorClass().saveAll();
			DSSForms.progressHide();
		}

		return 0;
	}

	/**
	 * For rolling your own solution modes.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveGeneralTime() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices

		for (int N = 0; N < sol.getNumberOfTimes(); N++)
			if (!Globals.isSolutionAbort()) {
				/* Compute basic multiplier from Default loadshape to use in generator dispatch, if any */
				ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));

				sol.solveSnap();
				Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
				Globals.getStorageClass().updateAll();
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
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			sol.setSolutionInitialized(true);  // If we're in dynamics mode, no need to re-initialize.
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			for (int N = 0; N < sol.getNumberOfTimes(); N++)
				if (!Globals.isSolutionAbort()) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					// Assume price signal stays constant for dynamic calcs
					/* Predictor */
					sol.getDynaVars().IterationFlag = 0;
					integratePCStates();
					sol.solveSnap();
					/* Corrector */
					sol.getDynaVars().IterationFlag = 1;
					integratePCStates();
					sol.solveSnap();
					Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
					Globals.getStorageClass().updateAll();
				}
		} finally {
			Globals.getMonitorClass().saveAll();
		}

		return 0;
	}

	/**
	 * Solve Monte Carlo solution.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveMonte1() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			ckt.setLoadMultiplier(1.0);   // Always set with prop in case matrix must be rebuilt
			sol.setIntervalHrs(1.0);      // needed for energy meters and storage devices
			sol.setIntHour(0);
			sol.setDblHour(0.0);          // Use hour to denote Case number
			sol.getDynaVars().t = 0.0;

			//Globals.getMonitorClass().resetAll();
			//Globals.getEnergyMeterClass().resetAll();

			DSSForms.progressCaption("Monte Carlo Mode 1, " + String.valueOf(sol.getNumberOfTimes()) + " Random Loads.");
			ProgressCount = 0;

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!Globals.isSolutionAbort()) {
					sol.setIntHour(sol.getIntHour() + 1);
					sol.solveSnap();
					Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
					Globals.getEnergyMeterClass().sampleAll();  // Make all meters take a sample
					show10PctProgress(N, sol.getNumberOfTimes());
				} else {
					Globals.setErrorNumber(DSSGlobals.SOLUTION_ABORT);
					Globals.setCmdResult(Globals.getErrorNumber());
					Globals.setGlobalResult("Solution Aborted");
					break;
				}
			}
		} finally {
			Globals.getMonitorClass().saveAll();
			DSSForms.progressHide();
		}

		return 0;
	}

	/**
	 * Solve Monte Carlo solution.
	 *
	 * Do a daily load solution for several random days.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveMonte2() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			sol.getDynaVars().t = 0.0;
			sol.setIntHour(0);
			sol.setDblHour(0.0);
			//Globals.getMonitorClass().resetAll();
			//Globals.getEnergyMeterClass().resetAll();
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			int nDaily = (int) Math.round(24.0 / sol.getIntervalHrs());

			if (!Globals.isDIFilesAreOpen())
				Globals.getEnergyMeterClass().openAllDIFiles();  // Open Demand Interval files, if desired

			DSSForms.progressCaption("Monte Carlo Mode 2, " + String.valueOf(sol.getNumberOfTimes()) + " Days.");
			ProgressCount = 0;

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!Globals.isSolutionAbort()) {  // Number of Days

					// Always set LoadMultiplier WITH prop in case matrix must be rebuilt
					switch (sol.getRandomType()) {
					case DSSGlobals.UNIFORM:
						ckt.setLoadMultiplier(Math.random());  // number between 0 and 1
					case DSSGlobals.GAUSSIAN:
						ckt.setLoadMultiplier(MathUtil.gauss(ckt.getDefaultDailyShapeObj().getMean(), ckt.getDefaultDailyShapeObj().getStdDev()));
					}

					for (int i = 0; i < nDaily; i++) {
						sol.incrementTime();
						ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
						sol.solveSnap();

						Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
						Globals.getEnergyMeterClass().sampleAll();  // Make all meters take a sample
						Globals.getStorageClass().updateAll();
					}

					show10PctProgress(N, sol.getNumberOfTimes());
				} else {
					Globals.setErrorNumber(DSSGlobals.SOLUTION_ABORT);
					Globals.setCmdResult(Globals.getErrorNumber());
					Globals.setGlobalResult("Solution Aborted.");
					break;
				}
			}
		} finally {
			Globals.getMonitorClass().saveAll();
			Globals.getEnergyMeterClass().closeAllDIFiles();  // Save Demand Interval files
			DSSForms.progressHide();
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
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		// Time must be set beFore entering this routine
		try {
			//Globals.getMonitorClass().resetAll();
			//Globals.getEnergyMeterClass().resetAll();
			sol.setIntervalHrs(1.0);  // just get per unit energy and multiply result as necessary

			if (!Globals.isDIFilesAreOpen())
				Globals.getEnergyMeterClass().openAllDIFiles();  // Open Demand Interval files, if desired

			DSSForms.progressCaption("Monte Carlo Mode 3, " + String.valueOf(sol.getNumberOfTimes()) + " Different Load Levels.");
			ProgressCount = 0;

			ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
			if (ckt.getPriceCurveObj() != null)
				ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!Globals.isSolutionAbort()) {
					// Always set LoadMultiplier WITH prop in case matrix must be rebuilt
					switch (sol.getRandomType()) {
					case DSSGlobals.UNIFORM:
						ckt.setLoadMultiplier(Math.random());  // number between 0 and 1
					case DSSGlobals.GAUSSIAN:
						ckt.setLoadMultiplier(MathUtil.gauss(ckt.getDefaultDailyShapeObj().getMean(), ckt.getDefaultDailyShapeObj().getStdDev()));
					case DSSGlobals.LOGNORMAL:
						ckt.setLoadMultiplier(MathUtil.quasiLognormal(ckt.getDefaultDailyShapeObj().getMean()));
					}

					sol.solveSnap();

					Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
					Globals.getEnergyMeterClass().sampleAll();  // Make all meters take a sample

					show10PctProgress(N, sol.getNumberOfTimes());
				} else {
					Globals.setCmdResult(DSSGlobals.SOLUTION_ABORT);
					Globals.setErrorNumber(Globals.getCmdResult());
					Globals.setGlobalResult("Solution Aborted");
					break;
				}
			}
		} finally {
			Globals.getMonitorClass().saveAll();
			Globals.getEnergyMeterClass().closeAllDIFiles();  // Save Demand Interval files
			DSSForms.progressHide();
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
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			if (ckt.getLoadDurCurveObj() == null) {
				Globals.doSimpleMsg("Load Duration Curve Not Defined (Set LDCurve=... command). Cannot perform solution.", 470);
				return 0;
			}

			// Time must be set before entering this routine

			//Globals.getMonitorClass().resetAll();
			//Globals.getEnergyMeterClass().resetAll();

			int nDaily = (int) Math.round(24.0 / sol.getDynaVars().h * 3600.0);

			if (!Globals.isDIFilesAreOpen())
				Globals.getEnergyMeterClass().openAllDIFiles();  // Open Demand Interval files, if desired

			DSSForms.progressCaption("Load-Duration Mode 1 Solution.");

			// (set in Solve method) ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (sol.getYear() - 1)));

			sol.setIntHour(0);
			for (int i = 0; i < nDaily; i++) {
				// Set the time
				sol.incrementTime();

				ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));

				if (!Globals.isSolutionAbort()) {
					for (int N = 0; N < ckt.getLoadDurCurveObj().getNumPoints(); N++) {
						ckt.setLoadMultiplier(ckt.getLoadDurCurveObj().mult(N));  // Always set LoadMultiplier with prop in case matrix must be rebuilt
						// Adjust meter interval to interval on value of present Load-Duration Curve
						sol.setIntervalHrs(ckt.getLoadDurCurveObj().getInterval());

						// Price curve must correspond to load-duration curve
						if (ckt.getPriceCurveObj() != null)
							ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(N) );

						sol.solveSnap();

						Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
						Globals.getEnergyMeterClass().sampleAll();  // Make all meters take a sample
						Globals.getStorageClass().updateAll();
					}
					DSSForms.showPctProgress((i * 100) / nDaily);
				} else {
					Globals.setCmdResult(DSSGlobals.SOLUTION_ABORT);
					Globals.setErrorNumber(Globals.getCmdResult());
					Globals.setGlobalResult("Solution Aborted");
					break;
				}
			}
		} finally {
			Globals.getMonitorClass().saveAll();
			Globals.getEnergyMeterClass().closeAllDIFiles();  // Save Demand Interval files
			DSSForms.progressHide();
		}

		return 0;
	}

	/**
	 * Solve Load-Duration Curve, 2.
	 *
	 * Hold time fixed and just vary the global load multiplier according to
	 * the global Load-Duration Curve.
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws Esolv32Problem
	 */
	public static int solveLD2() throws SolverError, ControlProblem, Esolv32Problem {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		if (ckt.getLoadDurCurveObj() == null) {
			Globals.doSimpleMsg("Load Duration Curve Not Defined (Set LDCurve=... command). Cannot perForm solution.", 471);
			return 0;
		}

		// Time must be set before entering this routine

		//Globals.getMonitorClass().resetAll();
		//Globals.getEnergyMeterClass().resetAll();

		ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
		if (!Globals.isDIFilesAreOpen())
			Globals.getEnergyMeterClass().openAllDIFiles();  // Open Demand Interval files, if desired

		// (set in Solve method) ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (sol.getYear() - 1)));

		try {
			if (Globals.isSolutionAbort()) {
				Globals.setCmdResult(DSSGlobals.SOLUTION_ABORT);
				Globals.setErrorNumber(Globals.getCmdResult());
				Globals.setGlobalResult("Solution Aborted.");
				return 0;
			}

			for (int N = 0; N < ckt.getLoadDurCurveObj().getNumPoints(); N++) {
				// Adjust meter interval to interval on value of present Load-Duration Curve
				ckt.setLoadMultiplier(ckt.getLoadDurCurveObj().mult(N));  // Always set LoadMultiplier with prop in case matrix must be rebuilt
				sol.setIntervalHrs(ckt.getLoadDurCurveObj().getInterval());

				// Price curve must correspond to load-duration curve
				if (ckt.getPriceCurveObj() != null)
					ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(N) );

				sol.solveSnap();

				Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample
				Globals.getEnergyMeterClass().sampleAll();  // Make all meters take a sample
				Globals.getStorageClass().updateAll();
			}
		} finally {
			Globals.getMonitorClass().saveAll();
			Globals.getEnergyMeterClass().closeAllDIFiles();  // Save Demand Interval files
		}

		return 0;
	}

	/**
	 * Enable one of the faults in the circuit. Disable the rest.
	 */
	private static void pickAFault() {
		int WhichOne;
		FaultObj FaultObj;
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		int NumFaults = ckt.getFaults().size();
		WhichOne = (int) (Math.random() * NumFaults) + 1;  // TODO Check zero based indexing
		if (WhichOne > NumFaults)
			WhichOne = NumFaults;

		for (int i = 0; i < NumFaults; i++) {
			FaultObj = ckt.getFaults().get(i);
			if (i == WhichOne) {
				FaultImpl.setActiveFaultObj(FaultObj);  // in Fault Unit
				FaultObj.setEnabled(true);
			} else {
				FaultObj.setEnabled(false);
			}
		}
	}

	/**
	 * Solve Monte Carlo Fault Study.
	 * @throws Esolv32Problem
	 */
	public static int solveMonteFault() throws Esolv32Problem {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			sol.setLoadModel(DSSGlobals.ADMITTANCE);   // All Direct solution
			ckt.setLoadMultiplier(1.0);  // Always set LoadMultiplier with prop in case matrix must be rebuilt
			sol.setIntHour(0);
			sol.setDblHour(0.0);  // Use hour to denote Case number
			sol.getDynaVars().t = 0.0;

			//Globals.getMonitorClass().resetAll();

			DSSForms.progressCaption("Monte Carlo Fault Study: " + String.valueOf(sol.getNumberOfTimes()) + " Different Faults.");
			ProgressCount = 0;

			sol.setGeneratorDispRef();

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!Globals.isSolutionAbort()) {
					sol.setIntHour(sol.getIntHour() + 1);
					pickAFault();  // Randomly enable one of the faults
					FaultImpl.getActiveFaultObj().randomize();  // Randomize the fault resistance
					sol.solveDirect();
					Globals.getMonitorClass().sampleAll();  // Make all monitors take a sample

					show10PctProgress(N, sol.getNumberOfTimes());
				}
			}
		} finally {
			Globals.getMonitorClass().saveAll();
			DSSForms.progressHide();
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
	 *
	 * Assume InjCurr is zeroed.
	 * @throws Esolv32Problem
	 */
	public static void computeYsc(int iB) throws Esolv32Problem {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();
		Bus bus = ckt.getBuses()[iB];

		int ref1;

		bus.getZsc().clear();
		for (int i = 0; i < bus.getNumNodesThisBus(); i++) {
			ref1 = bus.getRef(i);
			if (ref1 > 0) {  // TODO Check zero based indexing
				sol.getCurrents()[ref1] = Complex.ONE;
				/* SparseSet expects 1st element of voltage array, not 0-th element */
				if (YMatrix.solveSparseSet(sol.getYsystem(), sol.getNodeV()[1], sol.getCurrents()[1]) < 1)
					throw new Esolv32Problem("Error Solving System Y Matrix in ComputeYsc. Problem with Sparse matrix solver.");
				/* Extract Voltage Vector = column of Zsc */
				for (int j = 0; j < bus.getNumNodesThisBus(); j++)
					bus.getZsc().setElement(j ,i, sol.getNodeV()[bus.getRef(j)]);
				sol.getCurrents()[ref1] = Complex.ZERO;
			}
		}
		bus.getYsc().copyFrom(bus.getZsc());
		bus.getYsc().invert();  /* Save as admittance */
	}

	public static void computeAllYsc() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		for (int j = 0; j < ckt.getNumNodes(); j++)
			sol.getCurrents()[j] = Complex.ZERO;

		ProgressCount = 0;

		for (int iB = 0; iB < ckt.getNumBuses(); iB++) {
			try {
				computeYsc(iB);  // Compute YSC for iB-th Bus
			} catch (Esolv32Problem e) {
				// TODO Auto-generated catch block
			}
			if (((iB * 10) / ckt.getNumBuses()) > ProgressCount) {
				ProgressCount += 1;
				DSSForms.showPctProgress(30 + ProgressCount * 5);
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
	 * Full Fault Study
	 * @throws Esolv32Problem
	 */
	public static int solveFaultStudy() throws Esolv32Problem {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		DSSForms.showPctProgress(0);
		DSSForms.progressCaption("Computing Open-Circuit Voltages");

		sol.setLoadModel(DSSGlobals.ADMITTANCE);
		disableAllFaults();
		sol.solveDirect();  // This gets the open circuit voltages and bus lists corrected

		allocateAllSCParms();   // Reallocate bus quantities
		sol.updateVBus();  // Put present solution Voc's in bus quantities

		DSSForms.progressCaption("Computing Ysc Matrices for each Bus");
		DSSForms.showPctProgress(30);
		computeAllYsc();

		DSSForms.progressCaption("Computing short-circuit currents.");
		DSSForms.showPctProgress(80);
		computeIsc();

		DSSForms.showPctProgress(100);
		DSSForms.progressCaption("Done.");
		DSSForms.progressHide();
		// Now should have all we need to make a short circuit report

		return 0;
	}

	/**
	 * Add unique Frequency, F to list in ascending order, reallocating if necessary.
	 */
	private static void addFrequency(double[] FreqList, int NumFreq, int MaxFreq, double F) {
		/* See if F is in list */

		for (int i = 0; i < NumFreq; i++) {
			/* Allow a little tolerance (0.1 hz) for the Frequency for round off error */
			if (Math.abs(F - FreqList[i]) < 0.1)
				return;  // Already in list, nothing to do
		}

		/* OK, it's not in list, so let's add it */
		NumFreq += 1;
		if (NumFreq > MaxFreq) {  // Let's make a little more room
			MaxFreq += 20;
			FreqList = (double[]) Utilities.resizeArray(FreqList, MaxFreq);
		}

		/* Let's add it in ascending order */
		for (int i = 0; i < NumFreq - 1; i++) {
			if (F < FreqList[i]) {
				/* Push down array and insert it */
				for (int j = NumFreq - 1; j >= i; j--)  // TODO Check count down logic
					FreqList[j + 1] = FreqList[j];
				FreqList[i] = F;
				return;  // We're done!
			}
		}

		/* If we fall through, tack it on to the end */
		FreqList[NumFreq] = F;
	}

	private static double getSourceFrequency(PCElement pc) {
		VSourceObj pVsrc;
		ISourceObj pIsrc;

		if (pc.getDSSClassName().equals("vsource")) {
			pVsrc = (VSourceObj) pc;
			return pVsrc.getSrcFrequency();
		} else {
			pIsrc = (ISourceObj) pc;
			return pIsrc.getSrcFrequency();
		}
	}

	private static void collectAllFrequencies(double[] FreqList, int NumFreq) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		int[] SpectrumInUse;
		int MaxFreq;
		SpectrumObj pSpectrum;
		double f;

		/* Make a ist of all frequencies in use */

		/* accumulate all unique frequencies */
		MaxFreq = 20;    // Initial List size
		NumFreq = 0;
		FreqList = (double[]) Utilities.resizeArray(FreqList, MaxFreq);

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		/* Check Sources -- each could have a different base frequency */
		for (PCElement p : ckt.getSources()) {
			if (p.isEnabled()) {
				if (Globals.getSpectrumClass().find(p.getSpectrum()) != null) {
					pSpectrum = (SpectrumObj) Globals.getSpectrumClass().getActiveObj();
					f = getSourceFrequency(p);
					for (int j = 0; j < pSpectrum.getNumHarm(); j++) {
						addFrequency(FreqList, NumFreq, MaxFreq, pSpectrum.getHarmArray()[j] * f);
					}
				}
			}
		}

		/* Mark Spectra being used */

		/* Check loads and generators - these are assumed to be at fundamental frequency */
		SpectrumInUse = new int[Globals.getSpectrumClass().getElementCount()];  //Allocate and zero
		for (PCElement p : ckt.getPCElements()) {
			if (p.isEnabled()) {
				if (Globals.getSpectrumClass().find(p.getSpectrum()) != null) {
					SpectrumInUse[Globals.getSpectrumClass().getActive()] = 1;
				}
			}
		}

		/* Add marked spectra to list */
		for (int i = 0; i < Globals.getSpectrumClass().getElementCount(); i++) {
			if (SpectrumInUse[i] == 1) {
				Globals.getSpectrumClass().setActive(i);
				pSpectrum = (SpectrumObj) Globals.getSpectrumClass().getActiveObj();
				for (int j = 0; j < pSpectrum.getNumHarm(); j++) {
					addFrequency(FreqList, NumFreq, MaxFreq, pSpectrum.getHarmArray()[j] * ckt.getFundamental());
				}
			}
		}

		SpectrumInUse = null;
	}

	public static int solveHarmonic() throws Esolv32Problem {
		double[] FrequencyList = new double[0];
		int nFreq = 0;

		DSSForms.showPctProgress(0);
		DSSForms.progressCaption("Performing Harmonic Solution");

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		try {
			if (sol.getFrequency() != ckt.getFundamental()) {  // Last solution was something other than fundamental
				sol.setFrequency(ckt.getFundamental());
				if (!Utilities.retrieveSavedVoltages())
					return 0;  /* Get Saved fundamental frequency solution */
			}

			Globals.getMonitorClass().sampleAll();  // Store the fundamental frequency in the monitors

			/* Get the list of Harmonic Frequencies to solve at */
			if (sol.isDoAllHarmonics()) {
				collectAllFrequencies(FrequencyList, nFreq);   // Allocates FrequencyList  TODO Check allocation
			} else {
				FrequencyList = (double[]) Utilities.resizeArray(FrequencyList, sol.getHarmonicListSize());
				nFreq = sol.getHarmonicListSize();
				for (int i = 0; i < nFreq; i++) {
					FrequencyList[i] = ckt.getFundamental() * sol.getHarmonicList()[i];
				}
			}

			for (int i = 0; i < nFreq; i++) {
				sol.setFrequency(FrequencyList[i]);
				if (Math.abs(sol.getHarmonic() - 1.0) > DSSGlobals.EPSILON) {  // Skip fundamental
					DSSForms.progressCaption("Solving at Frequency = " + String.format("%-g", sol.getFrequency()));
					DSSForms.showPctProgress((int) Math.round((100.0 * i) / nFreq));
					sol.solveDirect();
					Globals.getMonitorClass().sampleAll();
					// Storage devices are assumed to stay the same since there is no time variation in this mode
				}
			}

			DSSForms.showPctProgress(100);
			DSSForms.progressCaption("Done.");
		} finally {
			DSSForms.progressHide();
			Globals.getMonitorClass().saveAll();
			FrequencyList = null;
		}
		// Now should have all we need to make a short circuit report

		return 0;
	}

	public static int getProgressCount() {
		return ProgressCount;
	}

	public static void setProgressCount(int progressCount) {
		ProgressCount = progressCount;
	}

}
