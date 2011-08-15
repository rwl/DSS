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
import org.apache.commons.math.complex.Complex;
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

		if (DSSGlobals.noFormsAllowed)
			return;

		if (((i * 10) / n) > progressCount) {
			progressCount += 1;
			DSSGlobals.DSSForms.showPctProgress(progressCount * 10);
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

		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		DSSGlobals.DSSForms.progressCaption("Solving Year " + String.valueOf(sol.getYear()));
		progressCount = 0;
		DSSGlobals.DSSForms.showPctProgress(progressCount);

		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage elements
			if (!DSSGlobals.DIFilesAreOpen)
				DSSGlobals.energyMeterClass.openAllDIFiles();   // open demand interval files, if desired, creates DI_Totals
			twoPct = Math.max(sol.getNumberOfTimes() / 50, 1);
			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!DSSGlobals.solutionAbort) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultYearlyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );
					sol.solveSnap();
					DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
					DSSGlobals.energyMeterClass.sampleAll();  // make all energy meters take a sample
					DSSGlobals.storageClass.updateAll();
					if ((N % twoPct) == 0)
						DSSGlobals.DSSForms.showPctProgress((N * 100) / sol.getNumberOfTimes());
				}
			}
		} finally {
			DSSGlobals.DSSForms.progressHide();
			DSSGlobals.monitorClass.saveAll();
			//DSSGlobals.energyMeterClass.closeAllDIFiles();  // save demand interval files, see DIFilesAreOpen logic
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		//t = 0.0;
		//Globals.getMonitorClass().resetAll();
		//DSSGlobals.energyMeterClass.resetAll();

		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters
			ckt.setDefaultDailyShapeObj((LoadShapeObj) DSSGlobals.loadShapeClass.find("default"));
			if (!DSSGlobals.DIFilesAreOpen)
				DSSGlobals.energyMeterClass.openAllDIFiles();  // append demand interval files, if desired

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!DSSGlobals.solutionAbort) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );
					sol.solveSnap();
					DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
					DSSGlobals.energyMeterClass.sampleAll(); // make all energy meters take a sample
					DSSGlobals.storageClass.updateAll();
				}
			}
		} finally {
			DSSGlobals.monitorClass.saveAll();
			DSSGlobals.energyMeterClass.closeAllDIFiles();  // save demand interval files
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		sol.getDynaVars().t = 0.0;

		DSSGlobals.monitorClass.resetAll();
		DSSGlobals.energyMeterClass.resetAll();
		try {
			sol.setIntHour(0);
			sol.setDblHour(0.0);
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			ckt.setDefaultDailyShapeObj((LoadShapeObj) DSSGlobals.loadShapeClass.find("default"));
			if (!DSSGlobals.DIFilesAreOpen)
				DSSGlobals.energyMeterClass.openAllDIFiles();  // open demand interval files, if desired

			for (int N = 0; N < sol.getNumberOfTimes(); N++)
				if (!DSSGlobals.solutionAbort) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );
					sol.solveSnap();
					DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
					DSSGlobals.energyMeterClass.sampleAll(); // make all energy meters take a sample
					DSSGlobals.storageClass.updateAll();
				}
		} finally {
			DSSGlobals.monitorClass.saveAll();
			DSSGlobals.energyMeterClass.closeAllDIFiles();  // save demand interval files
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		DSSGlobals.DSSForms.progressCaption("Duty Cycle Solution");
		progressCount = 0;
		DSSGlobals.DSSForms.showPctProgress(0);

		//t = 0.0;
		//Globals.getMonitorClass().resetAll();
		int TwoPct = Math.max(1, sol.getNumberOfTimes() / 50);
		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			for (int N = 0; N < sol.getNumberOfTimes(); N++)
				if (!DSSGlobals.solutionAbort) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					// assume price signal stays constant for dutycycle calcs
					sol.solveSnap();
					DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
					DSSGlobals.storageClass.updateAll();

					if (N % TwoPct == 0)
						DSSGlobals.DSSForms.showPctProgress((N * 100) / sol.getNumberOfTimes());
				}
		} finally {
			DSSGlobals.monitorClass.saveAll();
			DSSGlobals.DSSForms.progressHide();
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices

		for (int N = 0; N < sol.getNumberOfTimes(); N++)
			if (!DSSGlobals.solutionAbort) {
				/* Compute basic multiplier from default load shape to use in generator dispatch, if any */
				ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));

				sol.solveSnap();
				DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
				DSSGlobals.storageClass.updateAll();
				sol.incrementTime();
			}

		return 0;
	}

	/**
	 * Integrate states in all PC Elements.  At present, only PC Elements
	 * can have dynamic states.
	 */
	public static void integratePCStates() {
		Circuit ckt = DSSGlobals.activeCircuit;
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			sol.setSolutionInitialized(true);  // if we're in dynamics mode, no need to re-initialize.
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			for (int N = 0; N < sol.getNumberOfTimes(); N++)
				if (!DSSGlobals.solutionAbort) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					// assume price signal stays constant for dynamic calcs
					/* Predictor */
					sol.getDynaVars().iterationFlag = 0;
					integratePCStates();
					sol.solveSnap();
					/* Corrector */
					sol.getDynaVars().iterationFlag = 1;
					integratePCStates();
					sol.solveSnap();
					DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
					DSSGlobals.storageClass.updateAll();
				}
		} finally {
			DSSGlobals.monitorClass.saveAll();
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			ckt.setLoadMultiplier(1.0);   // always set with prop in case matrix must be rebuilt
			sol.setIntervalHrs(1.0);      // needed for energy meters and storage devices
			sol.setIntHour(0);
			sol.setDblHour(0.0);          // use hour to denote case number
			sol.getDynaVars().t = 0.0;

			//Globals.getMonitorClass().resetAll();
			//DSSGlobals.energyMeterClass.resetAll();

			DSSGlobals.DSSForms.progressCaption("Monte Carlo Mode 1, " + String.valueOf(sol.getNumberOfTimes()) + " Random Loads.");
			progressCount = 0;

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!DSSGlobals.solutionAbort) {
					sol.setIntHour(sol.getIntHour() + 1);
					sol.solveSnap();
					DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
					DSSGlobals.energyMeterClass.sampleAll();  // make all meters take a sample
					show10PctProgress(N, sol.getNumberOfTimes());
				} else {
					DSSGlobals.errorNumber = DSSGlobals.SOLUTION_ABORT;
					DSSGlobals.cmdResult = DSSGlobals.errorNumber;
					DSSGlobals.globalResult = "Solution Aborted";
					break;
				}
			}
		} finally {
			DSSGlobals.monitorClass.saveAll();
			DSSGlobals.DSSForms.progressHide();
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			sol.getDynaVars().t = 0.0;
			sol.setIntHour(0);
			sol.setDblHour(0.0);
			//Globals.getMonitorClass().resetAll();
			//DSSGlobals.energyMeterClass.resetAll();
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			int nDaily = (int) Math.round(24.0 / sol.getIntervalHrs());

			if (!DSSGlobals.DIFilesAreOpen)
				DSSGlobals.energyMeterClass.openAllDIFiles();  // open demand interval files, if desired

			DSSGlobals.DSSForms.progressCaption("Monte Carlo Mode 2, " + String.valueOf(sol.getNumberOfTimes()) + " Days.");
			progressCount = 0;

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!DSSGlobals.solutionAbort) {  // number of days

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

						DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
						DSSGlobals.energyMeterClass.sampleAll();  // make all meters take a sample
						DSSGlobals.storageClass.updateAll();
					}

					show10PctProgress(N, sol.getNumberOfTimes());
				} else {
					DSSGlobals.errorNumber = DSSGlobals.SOLUTION_ABORT;
					DSSGlobals.cmdResult = DSSGlobals.errorNumber;
					DSSGlobals.globalResult = "Solution Aborted.";
					break;
				}
			}
		} finally {
			DSSGlobals.monitorClass.saveAll();
			DSSGlobals.energyMeterClass.closeAllDIFiles();  // save demand interval files
			DSSGlobals.DSSForms.progressHide();
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		// time must be set beFore entering this routine
		try {
			//Globals.getMonitorClass().resetAll();
			//DSSGlobals.energyMeterClass.resetAll();
			sol.setIntervalHrs(1.0);  // just get per unit energy and multiply result as necessary

			if (!DSSGlobals.DIFilesAreOpen)
				DSSGlobals.energyMeterClass.openAllDIFiles();  // Open Demand Interval files, if desired

			DSSGlobals.DSSForms.progressCaption("Monte Carlo Mode 3, " + String.valueOf(sol.getNumberOfTimes()) + " Different Load Levels.");
			progressCount = 0;

			ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
			if (ckt.getPriceCurveObj() != null)
				ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!DSSGlobals.solutionAbort) {
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

					DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
					DSSGlobals.energyMeterClass.sampleAll();  // make all meters take a sample

					show10PctProgress(N, sol.getNumberOfTimes());
				} else {
					DSSGlobals.cmdResult = DSSGlobals.SOLUTION_ABORT;
					DSSGlobals.errorNumber = DSSGlobals.cmdResult;
					DSSGlobals.globalResult = "Solution Aborted";
					break;
				}
			}
		} finally {
			DSSGlobals.monitorClass.saveAll();
			DSSGlobals.energyMeterClass.closeAllDIFiles();  // save demand interval files
			DSSGlobals.DSSForms.progressHide();
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			if (ckt.getLoadDurCurveObj() == null) {
				DSSGlobals.doSimpleMsg("Load Duration Curve Not Defined (Set LDCurve=... command). Cannot perform solution.", 470);
				return 0;
			}

			// time must be set before entering this routine

			//Globals.getMonitorClass().resetAll();
			//DSSGlobals.energyMeterClass.resetAll();

			int nDaily = (int) Math.round(24.0 / sol.getDynaVars().h * 3600.0);

			if (!DSSGlobals.DIFilesAreOpen)
				DSSGlobals.energyMeterClass.openAllDIFiles();  // open demand interval files, if desired

			DSSGlobals.DSSForms.progressCaption("Load-Duration Mode 1 Solution.");

			// (set in solve method) ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (sol.getYear() - 1)));

			sol.setIntHour(0);
			for (int i = 0; i < nDaily; i++) {
				// set the time
				sol.incrementTime();

				ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));

				if (!DSSGlobals.solutionAbort) {
					for (int N = 0; N < ckt.getLoadDurCurveObj().getNumPoints(); N++) {
						// always set loadMultiplier with prop in case matrix must be rebuilt
						ckt.setLoadMultiplier(ckt.getLoadDurCurveObj().mult(N));
						// adjust meter interval to interval on value of present load-duration curve
						sol.setIntervalHrs(ckt.getLoadDurCurveObj().getInterval());

						// price curve must correspond to load-duration curve
						if (ckt.getPriceCurveObj() != null)
							ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(N) );

						sol.solveSnap();

						DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
						DSSGlobals.energyMeterClass.sampleAll();  // make all meters take a sample
						DSSGlobals.storageClass.updateAll();
					}
					DSSGlobals.DSSForms.showPctProgress((i * 100) / nDaily);
				} else {
					DSSGlobals.cmdResult = DSSGlobals.SOLUTION_ABORT;
					DSSGlobals.errorNumber = DSSGlobals.cmdResult;
					DSSGlobals.globalResult = "Solution Aborted";
					break;
				}
			}
		} finally {
			DSSGlobals.monitorClass.saveAll();
			DSSGlobals.energyMeterClass.closeAllDIFiles();  // save demand interval files
			DSSGlobals.DSSForms.progressHide();
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		if (ckt.getLoadDurCurveObj() == null) {
			DSSGlobals.doSimpleMsg("Load duration curve not defined (set ldcurve=... command). Can not perform solution.", 471);
			return 0;
		}

		// time must be set before entering this routine

		//Globals.getMonitorClass().resetAll();
		//DSSGlobals.energyMeterClass.resetAll();

		ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
		if (!DSSGlobals.DIFilesAreOpen)
			DSSGlobals.energyMeterClass.openAllDIFiles();  // open demand interval files, if desired

		// (set in solve method) ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (sol.getYear() - 1)));

		try {
			if (DSSGlobals.solutionAbort) {
				DSSGlobals.cmdResult = DSSGlobals.SOLUTION_ABORT;
				DSSGlobals.errorNumber = DSSGlobals.cmdResult;
				DSSGlobals.globalResult = "Solution aborted.";
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

				DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample
				DSSGlobals.energyMeterClass.sampleAll();  // make all meters take a sample
				DSSGlobals.storageClass.updateAll();
			}
		} finally {
			DSSGlobals.monitorClass.saveAll();
			DSSGlobals.energyMeterClass.closeAllDIFiles();  // save demand interval files
		}

		return 0;
	}

	/**
	 * Enable one of the faults in the circuit. Disable the rest.
	 */
	private static void pickAFault() {
		int whichOne;
		FaultObj faultObj;
		Circuit ckt = DSSGlobals.activeCircuit;

		int NumFaults = ckt.getFaults().size();
		whichOne = (int) (Math.random() * NumFaults) + 1;  // TODO Check zero based indexing
		if (whichOne > NumFaults)
			whichOne = NumFaults;

		for (int i = 0; i < NumFaults; i++) {
			faultObj = ckt.getFaults().get(i);
			if (i == whichOne) {
				FaultImpl.activeFaultObj = faultObj;  // in fault unit
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
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			sol.setLoadModel(DSSGlobals.ADMITTANCE);   // all direct solution
			ckt.setLoadMultiplier(1.0);  // always set loadMultiplier with prop in case matrix must be rebuilt
			sol.setIntHour(0);
			sol.setDblHour(0.0);  // use hour to denote case number
			sol.getDynaVars().t = 0.0;

			//Globals.getMonitorClass().resetAll();

			DSSGlobals.DSSForms.progressCaption("Monte Carlo Fault Study: " + String.valueOf(sol.getNumberOfTimes()) + " different faults.");
			progressCount = 0;

			sol.setGeneratorDispRef();

			for (int N = 0; N < sol.getNumberOfTimes(); N++) {
				if (!DSSGlobals.solutionAbort) {
					sol.setIntHour(sol.getIntHour() + 1);
					pickAFault();  // randomly enable one of the faults
					FaultImpl.activeFaultObj.randomize();  // randomize the fault resistance
					sol.solveDirect();
					DSSGlobals.monitorClass.sampleAll();  // make all monitors take a sample

					show10PctProgress(N, sol.getNumberOfTimes());
				}
			}
		} finally {
			DSSGlobals.monitorClass.saveAll();
			DSSGlobals.DSSForms.progressHide();
		}

		return 0;
	}

	private static void allocateAllSCParms() {
		Circuit ckt = DSSGlobals.activeCircuit;
		for (int i = 0; i < ckt.getNumBuses(); i++)
			ckt.getBuses()[i].allocateBusQuantities();
	}

	/**
	 * Compute Isc at all buses for current values of Voc and Ysc.
	 */
	private static void computeIsc() {
		Circuit ckt = DSSGlobals.activeCircuit;
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
		Circuit ckt = DSSGlobals.activeCircuit;
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
		Circuit ckt = DSSGlobals.activeCircuit;
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
				DSSGlobals.DSSForms.showPctProgress(30 + progressCount * 5);
			}
		}
	}

	private static void disableAllFaults() {
		Circuit ckt = DSSGlobals.activeCircuit;

		for (FaultObj fault : ckt.getFaults()) {
			FaultImpl.activeFaultObj = fault;
			FaultImpl.activeFaultObj.setEnabled(false);
		}
	}

	/**
	 * Full fault study
	 *
	 * @throws Esolv32Problem
	 */
	public static int solveFaultStudy() throws Esolv32Problem {
		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

		DSSGlobals.DSSForms.showPctProgress(0);
		DSSGlobals.DSSForms.progressCaption("Computing Open-Circuit Voltages");

		sol.setLoadModel(DSSGlobals.ADMITTANCE);
		disableAllFaults();
		sol.solveDirect();  // this gets the open circuit voltages and bus lists corrected

		allocateAllSCParms();  // reallocate bus quantities
		sol.updateVBus();  // put present solution Voc's in bus quantities

		DSSGlobals.DSSForms.progressCaption("Computing Ysc Matrices for each bus");
		DSSGlobals.DSSForms.showPctProgress(30);
		computeAllYsc();

		DSSGlobals.DSSForms.progressCaption("Computing short-circuit currents.");
		DSSGlobals.DSSForms.showPctProgress(80);
		computeIsc();

		DSSGlobals.DSSForms.showPctProgress(100);
		DSSGlobals.DSSForms.progressCaption("Done.");
		DSSGlobals.DSSForms.progressHide();
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

		int[] spectrumInUse;
		int maxFreq;
		SpectrumObj pSpectrum;
		double f;

		/* Make a list of all frequencies in use */

		/* Accumulate all unique frequencies */
		maxFreq = 20;    // Initial List size
		numFreq = 0;
		freqList = (double[]) Utilities.resizeArray(freqList, maxFreq);

		Circuit ckt = DSSGlobals.activeCircuit;

		/* Check sources -- each could have a different base frequency */
		for (PCElement p : ckt.getSources()) {
			if (p.isEnabled()) {
				if (DSSGlobals.spectrumClass.find(p.getSpectrum()) != null) {
					pSpectrum = (SpectrumObj) DSSGlobals.spectrumClass.getActiveObj();
					f = getSourceFrequency(p);
					for (int j = 0; j < pSpectrum.getNumHarm(); j++) {
						addFrequency(freqList, numFreq, maxFreq, pSpectrum.getHarmArray()[j] * f);
					}
				}
			}
		}

		/* Mark spectra being used */

		/* Check loads and generators - these are assumed to be at fundamental frequency */
		spectrumInUse = new int[DSSGlobals.spectrumClass.getElementCount()];  // allocate and zero
		for (PCElement p : ckt.getPCElements()) {
			if (p.isEnabled()) {
				if (DSSGlobals.spectrumClass.find(p.getSpectrum()) != null) {
					spectrumInUse[DSSGlobals.spectrumClass.getActiveElement()] = 1;
				}
			}
		}

		/* Add marked spectra to list */
		for (int i = 0; i < DSSGlobals.spectrumClass.getElementCount(); i++) {
			if (spectrumInUse[i] == 1) {
				DSSGlobals.spectrumClass.setActiveElement(i);
				pSpectrum = (SpectrumObj) DSSGlobals.spectrumClass.getActiveObj();
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

		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		DSSGlobals.DSSForms.showPctProgress(0);
		DSSGlobals.DSSForms.progressCaption("Performing Harmonic Solution");

		try {
			if (sol.getFrequency() != ckt.getFundamental()) {  // Last solution was something other than fundamental
				sol.setFrequency(ckt.getFundamental());
				if (!Utilities.retrieveSavedVoltages())
					return 0;  /* Get saved fundamental frequency solution */
			}

			DSSGlobals.monitorClass.sampleAll();  // store the fundamental frequency in the monitors

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
					DSSGlobals.DSSForms.progressCaption("Solving at frequency = " + String.format("%-g", sol.getFrequency()));
					DSSGlobals.DSSForms.showPctProgress((int) Math.round((100.0 * i) / nFreq));
					sol.solveDirect();
					DSSGlobals.monitorClass.sampleAll();
					// storage devices are assumed to stay the same since there is no time variation in this mode
				}
			}

			DSSGlobals.DSSForms.showPctProgress(100);
			DSSGlobals.DSSForms.progressCaption("Done.");
		} finally {
			DSSGlobals.DSSForms.progressHide();
			DSSGlobals.monitorClass.saveAll();
			frequencyList = null;
		}
		// now should have all we need to make a short circuit report

		return 0;
	}

}
