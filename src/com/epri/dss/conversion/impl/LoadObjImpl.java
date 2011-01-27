package com.epri.dss.conversion.impl;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.conversion.LoadObj;

public class LoadObjImpl extends PCElementImpl implements LoadObj {

	private boolean PFChanged;
	/* For all types of allocation */
    private double AllocationFactor;
    /* for connected kVA specification */
    private double kVAAllocationFactor;
    private double ConnectedkVA;
    private double kWh;
    private double kWhDays;
    /* For kWh billed spec */
    private double CFactor;
    private double AvgkW;
    /* References for Harmonics mode */
    private double[] HarmAng;
    private double[] HarmMag;
    private double LastGrowthFactor;
    /* added FOR speedup so we don't have to search FOR growth factor a lot */
    private int LastYear;
    private double LoadFundamental;
    private int LoadSolutionCount;
    private int OpenLoadSolutionCount;
    private double RandomMult;
    private double[] ShapeFactor;
    /* Base vars per phase */
    private double varBase;
    private double varNominal;
    /* Base volts suitable for computing currents */
    private double VBase;
    private double VBase105;
    private double VBase95;
    /* Nominal Watts per phase */
    private double WNominal;
    /* at nominal */
    private double[] Yeq;
    private double[] Yeq105;
    private double[] Yeq95;
    private double[] Yneut;
    /* To handle cases where one conductor of load is open */
    private DComplexMatrix2D YPrimOpenCond;
    /* Fixed value of y FOR type 7 load */
    private double YQFixed;

    // formerly private, now read-only properties for COM access
    private double puMean;
    private double puStdDev;
    private double CVRwattFactor;
    private double CVRvarFactor;
    private double Vmaxpu;
    private double VminEmerg;  // overrides system settings IF <> 0.0
    private double VminNormal;
    private double Vminpu;
    private boolean ExemptFromLDCurve;
    private boolean Fixed;   // If Fixed, always at base value

	public LoadObjImpl(DSSClassImpl ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	private boolean allTerminalsClosed() {
		return false;
	}

	private void calcDailyMult(double Hr) {

	}

	private void calcDutyMult(double Hr) {

	}

	private void calcInjCurrentArray() {

	}

	private void calcLoadModelContribution() {

	}

	private void calcVTerminalPhase() {

	}

	private void calcYearlyMult(double Hr) {

	}

	private void calcCVRMult(double Hr) {

	}

	private void calcYPrimMatrix(DComplexMatrix2D Ymatrix) {

	}

	private void doConstantILoad() {

	}

	private void doConstantPQLoad() {

	}

	private void doConstantZLoad() {

	}

	private void doFixedQ() {

	}

	private void doFixedQZ() {

	}

	private void doHarmonicMode() {

	}

	private void doCVRModel() {

	}

	private void doMotorTypeLoad() {

	}

	private double growthFactor(int Year) {

	}

	private void stickCurrInTerminalArray(DComplexMatrix1D TermArray,
			double[] Curr, int i) {

	}

	private boolean getUnserved() {
		return false;
	}

	private boolean getExceedsNormal() {
		return false;
	}

	private void setkVAAllocationFactor(double Value) {

	}

	private void setConnectedkVA(double Value) {

	}

	private void computeAllocatedLoad() {

	}

	/** Set kWh properties ... */

	private void setCFactor(double Value) {

	}

	private void setKWh(double Value) {

	}

	private void setKWhDays(double Value) {

	}

	private void setAllocationFactor(double Value) {

	}

}
