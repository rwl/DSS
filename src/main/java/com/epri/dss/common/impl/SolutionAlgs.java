package com.epri.dss.common.impl;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.shared.Dynamics;

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
	 */
	public static int solveYearly() {
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
						ckt.setPriceSignal(ckt.getPriceCurveObj().getMult(sol.getDblHour()).getReal());
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
	 */
	public static int solveDaily() {
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
						ckt.setPriceSignal(ckt.getPriceCurveObj().getMult(sol.getDblHour()).getReal());
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
	 * 
	 */
	public static int solvePeakDay() {
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
						ckt.setPriceSignal(ckt.getPriceCurveObj().getMult(sol.getDblHour()).getReal());
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
	 */
	public static int solveDuty() {
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
	 */
	public static int solveGeneralTime() {
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
	 */
	public static int solveDynamic() {
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
					Dynamics.IterationFlag = 0;
					integratePCStates();
					sol.solveSnap();
					/* Corrector */
					Dynamics.IterationFlag = 1;
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
	
	/* Solve Monte Carlo Solution */
	public static int solveMonte1() {
		return 0;
	}

	/* Solve Monte Carlo Solution */
	public static int solveMonte2() {
		return 0;
	}

	/* Solve Monte Carlo Solution */
	public static int solveMonte3() {
		return 0;
	}

	/* Solve Monte Carlo Fault Study */
	public static int solveMonteFault() {
		return 0;
	}

	/* Full Fault Study */
	public static int solveFaultStudy() {
		return 0;
	}

	/* Solve Load-Duration Curve, 1 */
	public static int solveLD1() {
		return 0;
	}
	
	/* Solve Load-Duration Curve, 2 */
	public static int solveLD2() {
		return 0;
	}
	
	public static int solveHarmonic() {
		return 0;
	}

	public static void computeYsc(int iB) {
		
	}
	
	public static void computeAllYsc() {
		
	}

	public static int getProgressCount() {
		return ProgressCount;
	}

	public static void setProgressCount(int progressCount) {
		ProgressCount = progressCount;
	}

}
