package com.ncond.dss.common;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.conversion.ISourceObj;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.conversion.VSourceObj;
import com.ncond.dss.delivery.Fault;
import com.ncond.dss.delivery.FaultObj;
import com.ncond.dss.general.SpectrumObj;
import com.ncond.dss.shared.MathUtil;

/**
 * Solution algorithms.
 *
 */
public class SolutionAlgs {

	private static int progressCount;

	private SolutionAlgs() {}

	private static void show10PctProgress(int i, int n) {
		if (DSS.noFormsAllowed) return;

		if ((i * 10) / n > progressCount) {
			progressCount += 1;
			DSS.forms.showPctProgress(progressCount * 10);
		}
	}

	/**
	 * Solve following yearly cycle.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws SolverProblem
	 */
	public static int solveYearly() throws SolverError, ControlProblem, SolverProblem {
		int twoPct;

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		DSS.forms.progressCaption("Solving Year " + String.valueOf(sol.getYear()));
		progressCount = 0;
		DSS.forms.showPctProgress(progressCount);

		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage elements
			if (!DSS.DIFilesAreOpen)
				DSS.energyMeterClass.openAllDIFiles();  // open demand interval files, if desired, creates DI_Totals
			twoPct = Math.max(sol.getNumberOfTimes() / 50, 1);
			for (int n = 0; n < sol.getNumberOfTimes(); n++) {
				if (!DSS.solutionAbort) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultYearlyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal(ckt.getPriceCurveObj().getPrice(sol.getDblHour()));
					sol.solveSnap();
					DSS.monitorClass.sampleAll();  // make all monitors take a sample
					DSS.energyMeterClass.sampleAll();  // make all energy meters take a sample
					DSS.storageClass.updateAll();
					if (n % twoPct == 0) {
						DSS.forms.showPctProgress((n * 100) / sol.getNumberOfTimes());
					}
				}
			}
		} finally {
			DSS.forms.progressHide();
			DSS.monitorClass.saveAll();
			//DSS.energyMeterClass.closeAllDIFiles();  // save demand interval files, see DIFilesAreOpen logic
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
	 * @throws SolverProblem
	 */
	public static int solveDaily() throws SolverError, ControlProblem, SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		//t = 0.0;
		//DSS.getMonitorClass().resetAll();
		//DSS.energyMeterClass.resetAll();

		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters
			if (!DSS.DIFilesAreOpen)
				DSS.energyMeterClass.openAllDIFiles();  // append demand interval files, if desired

			for (int n = 0; n < sol.getNumberOfTimes(); n++) {
				if (!DSS.solutionAbort) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal(ckt.getPriceCurveObj().getPrice(sol.getDblHour()));
					sol.solveSnap();
					DSS.monitorClass.sampleAll();  // make all monitors take a sample
					DSS.energyMeterClass.sampleAll(); // make all energy meters take a sample
					DSS.storageClass.updateAll();
				}
			}
		} finally {
			DSS.monitorClass.saveAll();
			DSS.energyMeterClass.closeAllDIFiles();  // save demand interval files
		}
		return 0;
	}

	/**
	 * Solve following daily cycle at peak load.
	 *
	 * Takes the given load kW and assumes it represents the peak value.
	 * Load is modified by daily load curve and growth factor for the year.
	 * 'h' defaults to 3600 (1 hr) but can be reset to anything.
	 * Differs from daily mode in that the global load multiplier is ignored.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws SolverProblem
	 *
	 */
	public static int solvePeakDay() throws SolverError, ControlProblem, SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		sol.getDynaVars().t = 0.0;

		DSS.monitorClass.resetAll();
		DSS.energyMeterClass.resetAll();
		try {
			sol.setIntHour(0);
			sol.setDblHour(0.0);
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			if (!DSS.DIFilesAreOpen)
				DSS.energyMeterClass.openAllDIFiles();  // open demand interval files, if desired

			for (int n = 0; n < sol.getNumberOfTimes(); n++) {
				if (!DSS.solutionAbort) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					if (ckt.getPriceCurveObj() != null)
						ckt.setPriceSignal(ckt.getPriceCurveObj().getPrice(sol.getDblHour()));
					sol.solveSnap();
					DSS.monitorClass.sampleAll();  // make all monitors take a sample
					DSS.energyMeterClass.sampleAll();  // make all energy meters take a sample
					DSS.storageClass.updateAll();
				}
			}
		} finally {
			DSS.monitorClass.saveAll();
			DSS.energyMeterClass.closeAllDIFiles();  // save demand interval files
		}

		return 0;
	}

	/**
	 * Solve following duty cycle.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws SolverProblem
	 */
	public static int solveDuty() throws SolverError, ControlProblem, SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		DSS.forms.progressCaption("Duty Cycle Solution");
		progressCount = 0;
		DSS.forms.showPctProgress(0);

		//t = 0.0;
		//DSS.getMonitorClass().resetAll();
		int twoPct = Math.max(1, sol.getNumberOfTimes() / 50);
		try {
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			for (int n = 0; n < sol.getNumberOfTimes(); n++)
				if (!DSS.solutionAbort) {
					sol.incrementTime();
					ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
					// assume price signal stays constant for dutycycle calcs
					sol.solveSnap();
					DSS.monitorClass.sampleAll();  // make all monitors take a sample
					DSS.storageClass.updateAll();

					if (n % twoPct == 0) {
						DSS.forms.showPctProgress((n * 100) / sol.getNumberOfTimes());
					}
				}
		} finally {
			DSS.monitorClass.saveAll();
			DSS.forms.progressHide();
		}

		return 0;
	}

	/**
	 * For rolling your own solution modes.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws SolverProblem
	 */
	public static int solveGeneralTime() throws SolverError, ControlProblem, SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices

		for (int n = 0; n < sol.getNumberOfTimes(); n++) {
			if (!DSS.solutionAbort) {
				/* Compute basic multiplier from default load shape to use in generator dispatch, if any */
				ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));

				sol.solveSnap();
				DSS.monitorClass.sampleAll();  // make all monitors take a sample
				DSS.storageClass.updateAll();
				sol.incrementTime();
			}
		}
		return 0;
	}

	/**
	 * Integrate states in all PC elements. At present, only PC elements
	 * can have dynamic states.
	 */
	public static void integratePCStates() {
		for (PCElement elem : DSS.activeCircuit.getPCElements())
			elem.integrateStates();
	}

	/**
	 * Solve dynamics.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws SolverProblem
	 */
	public static int solveDynamic() throws SolverError, ControlProblem, SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			sol.setSolutionInitialized(true);  // if we're in dynamics mode, no need to re-initialize.
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			for (int n = 0; n < sol.getNumberOfTimes(); n++)
				if (!DSS.solutionAbort) {
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
					DSS.monitorClass.sampleAll();  // make all monitors take a sample
					DSS.storageClass.updateAll();
				}
		} finally {
			DSS.monitorClass.saveAll();
		}

		return 0;
	}

	/**
	 * Solve Monte Carlo solution.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws SolverProblem
	 */
	public static int solveMonte1() throws SolverError, ControlProblem, SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			ckt.setLoadMultiplier(1.0);   // always set with prop in case matrix must be rebuilt
			sol.setIntervalHrs(1.0);      // needed for energy meters and storage devices
			sol.setIntHour(0);
			sol.setDblHour(0.0);          // use hour to denote case number
			sol.getDynaVars().t = 0.0;

			//DSS.getMonitorClass().resetAll();
			//DSS.energyMeterClass.resetAll();

			DSS.forms.progressCaption("Monte Carlo Mode 1, " +
					sol.getNumberOfTimes() + " Random Loads.");
			progressCount = 0;

			for (int n = 0; n < sol.getNumberOfTimes(); n++) {
				if (!DSS.solutionAbort) {
					sol.setIntHour(sol.getIntHour() + 1);
					sol.solveSnap();
					DSS.monitorClass.sampleAll();  // make all monitors take a sample
					DSS.energyMeterClass.sampleAll();  // make all meters take a sample
					show10PctProgress(n, sol.getNumberOfTimes());
				} else {
					DSS.errorNumber = DSS.SOLUTION_ABORT;
					DSS.cmdResult = DSS.errorNumber;
					DSS.globalResult = "Solution Aborted";
					break;
				}
			}
		} finally {
			DSS.monitorClass.saveAll();
			DSS.forms.progressHide();
		}

		return 0;
	}

	/**
	 * Solve Monte Carlo solution.
	 * Do a daily load solution for several random days.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws SolverProblem
	 */
	public static int solveMonte2() throws SolverError, ControlProblem, SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			sol.getDynaVars().t = 0.0;
			sol.setIntHour(0);
			sol.setDblHour(0.0);
			//DSS.getMonitorClass().resetAll();
			//DSS.energyMeterClass.resetAll();
			sol.setIntervalHrs(sol.getDynaVars().h / 3600.0);  // needed for energy meters and storage devices
			int nDaily = (int) Math.round(24.0 / sol.getIntervalHrs());

			if (!DSS.DIFilesAreOpen)
				DSS.energyMeterClass.openAllDIFiles();  // open demand interval files, if desired

			DSS.forms.progressCaption("Monte Carlo Mode 2, " +
					sol.getNumberOfTimes() + " Days.");
			progressCount = 0;

			for (int n = 0; n < sol.getNumberOfTimes(); n++) {
				if (!DSS.solutionAbort) {  // number of days

					// always set loadMultiplier with prop in case matrix must be rebuilt
					switch (sol.getRandomType()) {
					case DSS.UNIFORM:
						ckt.setLoadMultiplier(Math.random());  // number between 0 and 1
						break;
					case DSS.GAUSSIAN:
						ckt.setLoadMultiplier(MathUtil.gauss(ckt.getDefaultDailyShapeObj().getMean(),
								ckt.getDefaultDailyShapeObj().getStdDev()));
						break;
					}

					for (int i = 0; i < nDaily; i++) {
						sol.incrementTime();
						ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
						sol.solveSnap();

						DSS.monitorClass.sampleAll();  // make all monitors take a sample
						DSS.energyMeterClass.sampleAll();  // make all meters take a sample
						DSS.storageClass.updateAll();
					}

					show10PctProgress(n, sol.getNumberOfTimes());
				} else {
					DSS.errorNumber = DSS.SOLUTION_ABORT;
					DSS.cmdResult = DSS.errorNumber;
					DSS.globalResult = "Solution Aborted.";
					break;
				}
			}
		} finally {
			DSS.monitorClass.saveAll();
			DSS.energyMeterClass.closeAllDIFiles();  // save demand interval files
			DSS.forms.progressHide();
		}

		return 0;
	}

	/**
	 * Solve Monte Carlo solution.
	 *
	 * Hold time fixed and just vary the global load multiplier.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws SolverProblem
	 */
	public static int solveMonte3() throws SolverError, ControlProblem, SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		// time must be set beFore entering this routine
		try {
			//DSS.getMonitorClass().resetAll();
			//DSS.energyMeterClass.resetAll();
			sol.setIntervalHrs(1.0);  // just get per unit energy and multiply result as necessary

			if (!DSS.DIFilesAreOpen)
				DSS.energyMeterClass.openAllDIFiles();  // open demand interval files, if desired

			DSS.forms.progressCaption("Monte Carlo Mode 3, " +
					sol.getNumberOfTimes() + " Different Load Levels.");
			progressCount = 0;

			ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
			if (ckt.getPriceCurveObj() != null)
				ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(sol.getDblHour()) );

			for (int n = 0; n < sol.getNumberOfTimes(); n++) {
				if (!DSS.solutionAbort) {
					// always set loadMultiplier with prop in case matrix must be rebuilt
					switch (sol.getRandomType()) {
					case DSS.UNIFORM:
						ckt.setLoadMultiplier(Math.random());  // number between 0 and 1
						break;
					case DSS.GAUSSIAN:
						ckt.setLoadMultiplier(MathUtil.gauss(ckt.getDefaultDailyShapeObj().getMean(),
								ckt.getDefaultDailyShapeObj().getStdDev()));
						break;
					case DSS.LOGNORMAL:
						ckt.setLoadMultiplier(MathUtil.quasiLognormal(ckt.getDefaultDailyShapeObj().getMean()));
						break;
					}

					sol.solveSnap();

					DSS.monitorClass.sampleAll();  // make all monitors take a sample
					DSS.energyMeterClass.sampleAll();  // make all meters take a sample

					show10PctProgress(n, sol.getNumberOfTimes());
				} else {
					DSS.cmdResult = DSS.SOLUTION_ABORT;
					DSS.errorNumber = DSS.cmdResult;
					DSS.globalResult = "Solution Aborted";
					break;
				}
			}
		} finally {
			DSS.monitorClass.saveAll();
			DSS.energyMeterClass.closeAllDIFiles();  // save demand interval files
			DSS.forms.progressHide();
		}

		return 0;
	}

	/**
	 * Solve Load-Duration Curve, 1.
	 *
	 * Do a daily simulation based on a load duration curve.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws SolverProblem
	 */
	public static int solveLD1() throws SolverError, ControlProblem, SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			if (ckt.getLoadDurCurveObj() == null) {
				DSS.doSimpleMsg("Load duration curve not defined (set LDCurve=... command). Cannot perform solution.", 470);
				return 0;
			}

			// time must be set before entering this routine

			//DSS.getMonitorClass().resetAll();
			//DSS.energyMeterClass.resetAll();

			int nDaily = (int) Math.round(24.0 / sol.getDynaVars().h * 3600.0);

			if (!DSS.DIFilesAreOpen)
				DSS.energyMeterClass.openAllDIFiles();  // open demand interval files, if desired

			DSS.forms.progressCaption("Load-Duration Mode 1 Solution.");

			// (set in solve method) ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (sol.getYear() - 1)));

			sol.setIntHour(0);
			for (int i = 0; i < nDaily; i++) {
				// set the time
				sol.incrementTime();

				ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));

				if (!DSS.solutionAbort) {
					for (int n = 0; n < ckt.getLoadDurCurveObj().getNumPoints(); n++) {
						// always set loadMultiplier with prop in case matrix must be rebuilt
						ckt.setLoadMultiplier(ckt.getLoadDurCurveObj().mult(n));

						// adjust meter interval to interval on value of present load-duration curve
						sol.setIntervalHrs(ckt.getLoadDurCurveObj().getInterval());

						// price curve must correspond to load-duration curve
						if (ckt.getPriceCurveObj() != null)
							ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(n) );

						sol.solveSnap();

						DSS.monitorClass.sampleAll();  // make all monitors take a sample
						DSS.energyMeterClass.sampleAll();  // make all meters take a sample
						DSS.storageClass.updateAll();
					}
					DSS.forms.showPctProgress((i * 100) / nDaily);
				} else {
					DSS.cmdResult = DSS.SOLUTION_ABORT;
					DSS.errorNumber = DSS.cmdResult;
					DSS.globalResult = "Solution Aborted";
					break;
				}
			}
		} finally {
			DSS.monitorClass.saveAll();
			DSS.energyMeterClass.closeAllDIFiles();  // save demand interval files
			DSS.forms.progressHide();
		}

		return 0;
	}

	/**
	 * Solve Load-Duration Curve, 2.
	 *
	 * Hold time fixed and just vary the global load multiplier according to
	 * the global load-duration curve.
	 *
	 * @throws ControlProblem
	 * @throws SolverError
	 * @throws SolverProblem
	 */
	public static int solveLD2() throws SolverError, ControlProblem, SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		if (ckt.getLoadDurCurveObj() == null) {
			DSS.doSimpleMsg("Load duration curve not defined (set ldcurve=... command). Can not perform solution.", 471);
			return 0;
		}

		// time must be set before entering this routine

		//DSS.getMonitorClass().resetAll();
		//DSS.energyMeterClass.resetAll();

		ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()));
		if (!DSS.DIFilesAreOpen)
			DSS.energyMeterClass.openAllDIFiles();  // open demand interval files, if desired

		// (set in solve method) ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (sol.getYear() - 1)));

		try {
			if (DSS.solutionAbort) {
				DSS.cmdResult = DSS.SOLUTION_ABORT;
				DSS.errorNumber = DSS.cmdResult;
				DSS.globalResult = "Solution aborted.";
				return 0;
			}

			for (int n = 0; n < ckt.getLoadDurCurveObj().getNumPoints(); n++) {
				// adjust meter interval to interval on value of present load-duration curve
				// always set loadMultiplier with prop in case matrix must be rebuilt
				ckt.setLoadMultiplier(ckt.getLoadDurCurveObj().mult(n));
				sol.setIntervalHrs(ckt.getLoadDurCurveObj().getInterval());

				// price curve must correspond to load-duration curve
				if (ckt.getPriceCurveObj() != null)
					ckt.setPriceSignal( ckt.getPriceCurveObj().getPrice(n) );

				sol.solveSnap();

				DSS.monitorClass.sampleAll();  // make all monitors take a sample
				DSS.energyMeterClass.sampleAll();  // make all meters take a sample
				DSS.storageClass.updateAll();
			}
		} finally {
			DSS.monitorClass.saveAll();
			DSS.energyMeterClass.closeAllDIFiles();  // save demand interval files
		}

		return 0;
	}

	/**
	 * Enable one of the faults in the circuit. Disable the rest.
	 */
	private static void pickAFault() {
		int whichOne;
		FaultObj faultObj;
		Circuit ckt = DSS.activeCircuit;

		int numFaults = ckt.getFaults().size();
		whichOne = ((int) Math.random() * numFaults) + 1;
		if (whichOne > numFaults) whichOne = numFaults;

		for (int i = 0; i < numFaults; i++) {
			faultObj = ckt.getFaults().get(i);
			if (i == whichOne) {
				Fault.activeFaultObj = faultObj;  // in fault unit
				faultObj.setEnabled(true);
			} else {
				faultObj.setEnabled(false);
			}
		}
	}

	/**
	 * Solve Monte Carlo fault study.
	 *
	 * @throws SolverProblem
	 */
	public static int solveMonteFault() throws SolverProblem {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			sol.setLoadModel(DSS.ADMITTANCE);   // all direct solution
			ckt.setLoadMultiplier(1.0);  // always set loadMultiplier with prop in case matrix must be rebuilt
			sol.setIntHour(0);
			sol.setDblHour(0.0);  // use hour to denote case number
			sol.getDynaVars().t = 0.0;

			//DSS.getMonitorClass().resetAll();

			DSS.forms.progressCaption("Monte Carlo Fault Study: " +
					sol.getNumberOfTimes() + " different faults.");
			progressCount = 0;

			sol.setGeneratorDispRef();

			for (int n = 0; n < sol.getNumberOfTimes(); n++) {
				if (!DSS.solutionAbort) {
					sol.setIntHour(sol.getIntHour() + 1);
					pickAFault();  // randomly enable one of the faults
					Fault.activeFaultObj.randomize();  // randomize the fault resistance
					sol.solveDirect();
					DSS.monitorClass.sampleAll();  // make all monitors take a sample

					show10PctProgress(n, sol.getNumberOfTimes());
				}
			}
		} finally {
			DSS.monitorClass.saveAll();
			DSS.forms.progressHide();
		}

		return 0;
	}

	private static void allocateAllSCParms() {
		Circuit ckt = DSS.activeCircuit;
		for (int i = 0; i < ckt.getNumBuses(); i++)
			ckt.getBus(i).allocateBusQuantities();
	}

	/**
	 * Compute Isc at all buses for current values of Voc and Ysc.
	 */
	private static void computeIsc() {
		Bus bus;
		Circuit ckt = DSS.activeCircuit;

		for (int i = 0; i < ckt.getNumBuses(); i++) {
			bus = ckt.getBus(i);
			bus.getYsc().vMult(bus.getBusCurrent(), bus.getVBus());
		}
	}

	/**
	 * Compute Ysc for i-th bus.
	 * Assume injCurr is zeroed.
	 *
	 * @throws SolverProblem
	 */
	public static void computeYsc(int iBus) throws SolverProblem {
		int ref1;
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		Bus bus = ckt.getBus(iBus);

		bus.getZsc().clear();
		for (int i = 0; i < bus.getNumNodesThisBus(); i++) {
			ref1 = bus.getRef(i);
			if (ref1 > 0) {
				sol.setCurrent(ref1, Complex.ONE);

				/* SparseSet expects 1st element of voltage array, not 0-th element */
				if (YMatrix.solveSparseSet(sol.getYSystem(), sol.getNodeV(), 1, sol.getCurrents(), 1) < 1)
					throw new SolverProblem("Error solving system Y matrix in computeYsc. " +
							"Problem with sparse matrix solver.");

				/* Extract voltage vector = column of Zsc */
				for (int j = 0; j < bus.getNumNodesThisBus(); j++)
					bus.getZsc().set(j, i, sol.getNodeV(bus.getRef(j)));

				sol.setCurrent(ref1, Complex.ZERO);
			}
		}
		bus.getYsc().copyFrom(bus.getZsc());
		bus.getYsc().invert();  /* Save as admittance */
	}

	public static void computeAllYsc() {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		for (int j = 1; j <= ckt.getNumNodes(); j++)
			sol.setCurrent(j, Complex.ZERO);

		progressCount = 0;

		for (int iBus = 0; iBus < ckt.getNumBuses(); iBus++) {
			try {
				computeYsc(iBus);  // compute YSC for iB-th Bus
			} catch (SolverProblem e) {
				DSS.doSimpleMsg(e.getMessage(), -1);
				break;
			}
			if ((iBus * 10) / ckt.getNumBuses() > progressCount) {
				progressCount += 1;
				DSS.forms.showPctProgress(30 + progressCount * 5);
			}
		}
	}

	private static void disableAllFaults() {
		Circuit ckt = DSS.activeCircuit;

		for (FaultObj fault : ckt.getFaults()) {
			Fault.activeFaultObj = fault;
			Fault.activeFaultObj.setEnabled(false);
		}
	}

	/**
	 * Full fault study
	 *
	 * @throws SolverProblem
	 */
	public static int solveFaultStudy() throws SolverProblem {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		DSS.forms.showPctProgress(0);
		DSS.forms.progressCaption("Computing open-circuit voltages");

		sol.setLoadModel(DSS.ADMITTANCE);
		disableAllFaults();
		sol.solveDirect();  // this gets the open circuit voltages and bus lists corrected

		allocateAllSCParms();  // reallocate bus quantities
		sol.updateVBus();  // put present solution Vsc's in bus quantities

		DSS.forms.progressCaption("Computing Ysc matrices for each bus");
		DSS.forms.showPctProgress(30);
		computeAllYsc();

		DSS.forms.progressCaption("Computing short-circuit currents.");
		DSS.forms.showPctProgress(80);
		computeIsc();

		DSS.forms.showPctProgress(100);
		DSS.forms.progressCaption("Done.");
		DSS.forms.progressHide();
		// now should have all we need to make a short circuit report

		return 0;
	}

	/**
	 * Add unique frequency, F to list in ascending order, reallocating if necessary.
	 */
	private static void addFrequency(double[] freqList, int[] numFreq, int maxFreq, double f) {
		/* See if F is in list */

		for (int i = 0; i < numFreq[0]; i++) {
			/* Allow a little tolerance (0.1 hz) for the frequency for round off error */
			if (Math.abs(f - freqList[i]) < 0.1)
				return;  // already in list, nothing to do
		}

		/* OK, it's not in list, so let's add it */
		numFreq[0]++;
		if (numFreq[0] > maxFreq) {
			maxFreq += 20;
			freqList = Util.resizeArray(freqList, maxFreq);
		}

		/* let's add it in ascending order */
		for (int i = 0; i < numFreq[0] - 1; i++) {
			if (f < freqList[i]) {
				/* push down array and insert it */
				for (int j = numFreq[0] - 2; j >= i; j--)
					freqList[j + 1] = freqList[j];
				freqList[i] = f;
				return;
			}
		}

		/* If we fall through, tack it on to the end */
		freqList[numFreq[0]] = f;
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

	private static void collectAllFrequencies(double[] freqList, int[] numFreq) {
		boolean[] spectrumInUse;
		int maxFreq, j;
		SpectrumObj spectrum;
		double f;

		/* Make a list of all frequencies in use */

		/* Accumulate all unique frequencies */
		maxFreq = 20;    // initial list size
		numFreq[0] = 0;
		freqList = Util.resizeArray(freqList, maxFreq);

		Circuit ckt = DSS.activeCircuit;

		/* Check sources -- each could have a different base frequency */
		for (PCElement pc : ckt.getSources()) {
			if (pc.isEnabled()) {
				if (DSS.spectrumClass.find(pc.getSpectrum()) != null) {
					spectrum = (SpectrumObj) DSS.spectrumClass.getActiveObj();
					f = getSourceFrequency(pc);
					for (j = 0; j < spectrum.getNumHarm(); j++) {
						addFrequency(freqList, numFreq, maxFreq,
							spectrum.getHarmonic(j) * f);
					}
				}
			}
		}

		/* Mark spectra being used */

		/* Check loads and generators - these are assumed to be at fundamental frequency */
		spectrumInUse = new boolean[DSS.spectrumClass.getElementCount()];  // allocate and zero

		for (PCElement pc : ckt.getPCElements()) {
			if (pc.isEnabled()) {
				if (DSS.spectrumClass.find(pc.getSpectrum()) != null)
					spectrumInUse[DSS.spectrumClass.getActiveElement()] = true;
			}
		}

		/* Add marked spectra to list */
		for (int i = 0; i < DSS.spectrumClass.getElementCount(); i++) {
			if (spectrumInUse[i]) {
				DSS.spectrumClass.setActiveElement(i);
				spectrum = (SpectrumObj) DSS.spectrumClass.getActiveObj();
				for (j = 0; j < spectrum.getNumHarm(); j++) {
					addFrequency(freqList, numFreq, maxFreq,
						spectrum.getHarmonic(j) * ckt.getFundamental());
				}
			}
		}
	}

	public static int solveHarmonic() throws SolverProblem {
		double[] frequencyList = new double[0];
		int[] nFreq = new int[1];

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		DSS.forms.showPctProgress(0);
		DSS.forms.progressCaption("Performing Harmonic Solution");

		try {
			if (sol.getFrequency() != ckt.getFundamental()) {  // last solution was something other than fundamental
				sol.setFrequency(ckt.getFundamental());
				if (!Util.retrieveSavedVoltages())
					return 0;  /* Get saved fundamental frequency solution */
			}

			DSS.monitorClass.sampleAll();  // store the fundamental frequency in the monitors

			/* Get the list of harmonic frequencies to solve at */
			if (sol.isDoAllHarmonics()) {
				collectAllFrequencies(frequencyList, nFreq);  // allocates frequencyList
			} else {
				frequencyList = Util.resizeArray(frequencyList, sol.getHarmonicListSize());
				nFreq[0] = sol.getHarmonicListSize();
				for (int i = 0; i < nFreq[0]; i++) {
					frequencyList[i] = ckt.getFundamental() * sol.getHarmonic(i);
				}
			}

			for (int i = 0; i < nFreq[0]; i++) {
				sol.setFrequency(frequencyList[i]);
				if (Math.abs(sol.getHarmonic() - 1.0) > DSS.EPSILON) {  // Skip fundamental
					DSS.forms.progressCaption("Solving at frequency = " +
							String.format("%-g", sol.getFrequency()));
					DSS.forms.showPctProgress((int) Math.round((100.0 * i) / nFreq[0]));
					sol.solveDirect();
					DSS.monitorClass.sampleAll();
					// storage devices are assumed to stay the same since there is no time variation in this mode
				}
			}

			DSS.forms.showPctProgress(100);
			DSS.forms.progressCaption("Done.");
		} finally {
			DSS.forms.progressHide();
			DSS.monitorClass.saveAll();
			frequencyList = null;
		}
		// now should have all we need to make a short circuit report

		return 0;
	}

}
