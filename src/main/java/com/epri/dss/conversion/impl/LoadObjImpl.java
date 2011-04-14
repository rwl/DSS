package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.GrowthShapeObj;
import com.epri.dss.shared.CMatrix;

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
	private Complex HarmAng;
	private Complex HarmMag;
	private double LastGrowthFactor;
	/* added FOR speedup so we don't have to search FOR growth factor a lot */
	private int LastYear;
	private double LoadFundamental;
	private int LoadSolutionCount;
	private int OpenLoadSolutionCount;
	private double RandomMult;
	private Complex ShapeFactor;
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
	private Complex Yeq;
	private Complex Yeq105;
	private Complex Yeq95;
	private Complex Yneut;
	/* To handle cases where one conductor of load is open */
	private CMatrix YPrimOpenCond;
	/* Fixed value of y FOR type 7 load */
	private double YQFixed;

	// formerly private, now read-only properties for COM access
	protected double puMean;
	protected double puStdDev;
	protected double CVRwattFactor;
	protected double CVRvarFactor;
	protected double Vmaxpu;
	protected double VminEmerg;  // overrides system settings IF <> 0.0
	protected double VminNormal;
	protected double Vminpu;
	protected boolean ExemptFromLDCurve;
	protected boolean Fixed;   // If Fixed, always at base value

	/* 0 = line-neutral; 1 = Delta */
	protected int Connection;
	/* Daily (24 HR) load shape */
	protected String DailyShape;
	/* Daily load Shape FOR this load */
	protected LoadShapeObj DailyShapeObj;
	/* Duty cycle load shape FOR changes typically less than one hour */
	protected String DutyShape;
	/* Shape for this load */
	protected LoadShapeObj DutyShapeObj;
	/* is overloaded  Factor is the amount of overload */
	protected double EEN_Factor;
	/* (year, Multiplier from previous year) */
	protected String GrowthShape;
	/* Shape for this Growth  Curve */
	protected GrowthShapeObj GrowthShapeObj;
	protected Boolean HasBeenAllocated;
	protected double kWBase;
	protected double kVABase;
	protected double kvarBase;
	protected double kVLoadBase;
	protected int LoadClass;
	protected int NumCustomers;
	/* 0 = kW, PF;  1 = kw, kvar;  2 = kva, PF */
	protected int LoadSpecType;
	protected double PFNominal;
	protected double Rneut;
	/* These are set to > 0 IF a line in the critical path */
	protected double UE_Factor;
	/* Neutral impedance */
	protected double Xneut;
	/* ='fixed' means no variation  exempt from variation */
	protected String YearlyShape;
	/* Shape for this load */
	protected LoadShapeObj YearlyShapeObj;
	protected String CVRshape;
	protected LoadShapeObj CVRShapeObj;

	/*
	 * Variation with voltage
	 *
	 * 1 = Constant kVA (P,Q always in same ratio)
	 * 2 = Constant impedance
	 * 3 = Constant P, Quadratic Q (Mostly motor)
	 * 4 = Linear P, Quadratic Q  (Mixed motor/resistive Use this for CVR studies
	 * 5 = Constant |I|
	 * 6 = Constant P (Variable); Q is fixed value (not variable)
	 * 7 = Constant P (Variable); Q is fixed Z (not variable)
	 */
	protected int LoadModel;


	public LoadObjImpl(DSSClassImpl ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}

	public String getDailyShape() {
		return DailyShape;
	}

	public void setDailyShape(String dailyShape) {
		DailyShape = dailyShape;
	}

	public LoadShapeObj getDailyShapeObj() {
		return DailyShapeObj;
	}

	public void setDailyShapeObj(LoadShapeObj dailyShapeObj) {
		DailyShapeObj = dailyShapeObj;
	}

	public String getDutyShape() {
		return DutyShape;
	}

	public void setDutyShape(String dutyShape) {
		DutyShape = dutyShape;
	}

	public LoadShapeObj getDutyShapeObj() {
		return DutyShapeObj;
	}

	public void setDutyShapeObj(LoadShapeObj dutyShapeObj) {
		DutyShapeObj = dutyShapeObj;
	}

	public double getEEN_Factor() {
		return EEN_Factor;
	}

	public void setEEN_Factor(double eEN_Factor) {
		EEN_Factor = eEN_Factor;
	}

	public String getGrowthShape() {
		return GrowthShape;
	}

	public void setGrowthShape(String growthShape) {
		GrowthShape = growthShape;
	}

	public GrowthShapeObj getGrowthShapeObj() {
		return GrowthShapeObj;
	}

	public void setGrowthShapeObj(GrowthShapeObj growthShapeObj) {
		GrowthShapeObj = growthShapeObj;
	}

	public Boolean getHasBeenAllocated() {
		return HasBeenAllocated;
	}

	public void setHasBeenAllocated(Boolean hasBeenAllocated) {
		HasBeenAllocated = hasBeenAllocated;
	}

	public double getkWBase() {
		return kWBase;
	}

	public void setkWBase(double kWBase) {
		this.kWBase = kWBase;
	}

	public double getkVABase() {
		return kVABase;
	}

	public void setkVABase(double kVABase) {
		this.kVABase = kVABase;
	}

	public double getKvarBase() {
		return kvarBase;
	}

	public void setKvarBase(double kvarBase) {
		this.kvarBase = kvarBase;
	}

	public double getkVLoadBase() {
		return kVLoadBase;
	}

	public void setkVLoadBase(double kVLoadBase) {
		this.kVLoadBase = kVLoadBase;
	}

	public int getLoadClass() {
		return LoadClass;
	}

	public void setLoadClass(int loadClass) {
		LoadClass = loadClass;
	}

	public int getNumCustomers() {
		return NumCustomers;
	}

	public void setNumCustomers(int numCustomers) {
		NumCustomers = numCustomers;
	}

	public int getLoadSpecType() {
		return LoadSpecType;
	}

	public void setLoadSpecType(int loadSpecType) {
		LoadSpecType = loadSpecType;
	}

	public double getPFNominal() {
		return PFNominal;
	}

	public void setPFNominal(double pFNominal) {
		PFNominal = pFNominal;
	}

	public double getRneut() {
		return Rneut;
	}

	public void setRneut(double rneut) {
		Rneut = rneut;
	}

	public double getUE_Factor() {
		return UE_Factor;
	}

	public void setUE_Factor(double uE_Factor) {
		UE_Factor = uE_Factor;
	}

	public double getXneut() {
		return Xneut;
	}

	public void setXneut(double xneut) {
		Xneut = xneut;
	}

	public String getYearlyShape() {
		return YearlyShape;
	}

	public void setYearlyShape(String yearlyShape) {
		YearlyShape = yearlyShape;
	}

	public LoadShapeObj getYearlyShapeObj() {
		return YearlyShapeObj;
	}

	public void setYearlyShapeObj(LoadShapeObj yearlyShapeObj) {
		YearlyShapeObj = yearlyShapeObj;
	}

	public String getCVRshape() {
		return CVRshape;
	}

	public void setCVRshape(String cVRshape) {
		CVRshape = cVRshape;
	}

	public LoadShapeObj getCVRShapeObj() {
		return CVRShapeObj;
	}

	public void setCVRShapeObj(LoadShapeObj cVRShapeObj) {
		CVRShapeObj = cVRShapeObj;
	}

	public int getLoadModel() {
		return LoadModel;
	}

	public void setLoadModel(int loadModel) {
		LoadModel = loadModel;
	}

	public double getPuMean() {
		return puMean;
	}

	public double getPuStdDev() {
		return puStdDev;
	}

	public double getCVRwattFactor() {
		return CVRwattFactor;
	}

	public double getCVRvarFactor() {
		return CVRvarFactor;
	}

	public double getVmaxpu() {
		return Vmaxpu;
	}

	public double getVminEmerg() {
		return VminEmerg;
	}

	public double getVminNormal() {
		return VminNormal;
	}

	public double getVminpu() {
		return Vminpu;
	}

	public boolean isExemptFromLDCurve() {
		return ExemptFromLDCurve;
	}

	public boolean isFixed() {
		return Fixed;
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

	private void calcYPrimMatrix(CMatrix Ymatrix) {

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
		return 0.0;
	}

	private void stickCurrInTerminalArray(Complex[] TermArray,
			Complex Curr, int i) {

	}

	public boolean getUnserved() {
		return false;
	}

	public boolean getExceedsNormal() {
		return false;
	}

	/* Allocate load from connected kva or kWh billing */
	public void setkVAAllocationFactor(double Value) {

	}

	public double getkVAAllocationFactor() {
		return 0.0;
	}

	public void setConnectedkVA(double Value) {

	}

	public double getConnectedkVA() {
		return 0.0;
	}

	private void computeAllocatedLoad() {

	}

	/** Set kWh properties ... */

	public void setCFactor(double Value) {

	}

	public double getCFactor() {
		return 0.0;
	}

	public void setKWh(double Value) {

	}

	public double getKWh() {
		return 0.0;
	}

	public void setKWhDays(double Value) {

	}

	public double getKWhDays() {
		return 0.0;
	}

	/* AllocationFactor adjusts either connected kVA allocation factor
	 * or kWh CFactor
	 */
	public void setAllocationFactor(double Value) {

	}

	public double getAllocationFactor() {
		return 0.0;
	}

	protected void getTerminalCurrents(Complex[] Curr) {

	}

	public void recalcElementData() {

	}

	public void calcYPrim() {

	}

	public int injCurrents() {
		return 0;
	}

	public void getInjCurrents(Complex[] Curr) {

	}

	public void initHarmonics() {

	}

	/* Make a positive Sequence Model */
	public void makePosSequence() {

	}

	public void setNominalLoad() {

	}

	/*
	 * 0 = reset to 1.0
	 * 1 = Gaussian around mean and std Dev
	 * 2 = uniform
	 */
	public void randomize(int Opt) {

	}

	public String getPropertyValue(int Index) {
		return null;
	}

	public void initPropertyValues(int ArrayOffset) {

	}

	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	public void updateVoltageBases() {

	}
	
	// FIXME Private members in OpenDSS.

	public boolean isPFChanged() {
		return PFChanged;
	}

	public void setPFChanged(boolean pFChanged) {
		PFChanged = pFChanged;
	}

	public double getkWh() {
		return kWh;
	}

	public void setkWh(double kWh) {
		this.kWh = kWh;
	}

	public double getkWhDays() {
		return kWhDays;
	}

	public void setkWhDays(double kWhDays) {
		this.kWhDays = kWhDays;
	}

	public double getAvgkW() {
		return AvgkW;
	}

	public void setAvgkW(double avgkW) {
		AvgkW = avgkW;
	}

	public Complex getHarmAng() {
		return HarmAng;
	}

	public void setHarmAng(Complex harmAng) {
		HarmAng = harmAng;
	}

	public Complex getHarmMag() {
		return HarmMag;
	}

	public void setHarmMag(Complex harmMag) {
		HarmMag = harmMag;
	}

	public double getLastGrowthFactor() {
		return LastGrowthFactor;
	}

	public void setLastGrowthFactor(double lastGrowthFactor) {
		LastGrowthFactor = lastGrowthFactor;
	}

	public int getLastYear() {
		return LastYear;
	}

	public void setLastYear(int lastYear) {
		LastYear = lastYear;
	}

	public double getLoadFundamental() {
		return LoadFundamental;
	}

	public void setLoadFundamental(double loadFundamental) {
		LoadFundamental = loadFundamental;
	}

	public int getLoadSolutionCount() {
		return LoadSolutionCount;
	}

	public void setLoadSolutionCount(int loadSolutionCount) {
		LoadSolutionCount = loadSolutionCount;
	}

	public int getOpenLoadSolutionCount() {
		return OpenLoadSolutionCount;
	}

	public void setOpenLoadSolutionCount(int openLoadSolutionCount) {
		OpenLoadSolutionCount = openLoadSolutionCount;
	}

	public double getRandomMult() {
		return RandomMult;
	}

	public void setRandomMult(double randomMult) {
		RandomMult = randomMult;
	}

	public Complex getShapeFactor() {
		return ShapeFactor;
	}

	public void setShapeFactor(Complex shapeFactor) {
		ShapeFactor = shapeFactor;
	}

	public double getVarBase() {
		return varBase;
	}

	public void setVarBase(double varBase) {
		this.varBase = varBase;
	}

	public double getVarNominal() {
		return varNominal;
	}

	public void setVarNominal(double varNominal) {
		this.varNominal = varNominal;
	}

	public double getVBase() {
		return VBase;
	}

	public void setVBase(double vBase) {
		VBase = vBase;
	}

	public double getVBase105() {
		return VBase105;
	}

	public void setVBase105(double vBase105) {
		VBase105 = vBase105;
	}

	public double getVBase95() {
		return VBase95;
	}

	public void setVBase95(double vBase95) {
		VBase95 = vBase95;
	}

	public double getWNominal() {
		return WNominal;
	}

	public void setWNominal(double wNominal) {
		WNominal = wNominal;
	}

	public Complex getYeq() {
		return Yeq;
	}

	public void setYeq(Complex yeq) {
		Yeq = yeq;
	}

	public Complex getYeq105() {
		return Yeq105;
	}

	public void setYeq105(Complex yeq105) {
		Yeq105 = yeq105;
	}

	public Complex getYeq95() {
		return Yeq95;
	}

	public void setYeq95(Complex yeq95) {
		Yeq95 = yeq95;
	}

	public Complex getYneut() {
		return Yneut;
	}

	public void setYneut(Complex yneut) {
		Yneut = yneut;
	}

	public CMatrix getYPrimOpenCond() {
		return YPrimOpenCond;
	}

	public void setYPrimOpenCond(CMatrix yPrimOpenCond) {
		YPrimOpenCond = yPrimOpenCond;
	}

	public double getYQFixed() {
		return YQFixed;
	}

	public void setYQFixed(double yQFixed) {
		YQFixed = yQFixed;
	}

	public void setPuMean(double puMean) {
		this.puMean = puMean;
	}

	public void setPuStdDev(double puStdDev) {
		this.puStdDev = puStdDev;
	}

	public void setCVRwattFactor(double cVRwattFactor) {
		CVRwattFactor = cVRwattFactor;
	}

	public void setCVRvarFactor(double cVRvarFactor) {
		CVRvarFactor = cVRvarFactor;
	}

	public void setVmaxpu(double vmaxpu) {
		Vmaxpu = vmaxpu;
	}

	public void setVminEmerg(double vminEmerg) {
		VminEmerg = vminEmerg;
	}

	public void setVminNormal(double vminNormal) {
		VminNormal = vminNormal;
	}

	public void setVminpu(double vminpu) {
		Vminpu = vminpu;
	}

	public void setExemptFromLDCurve(boolean exemptFromLDCurve) {
		ExemptFromLDCurve = exemptFromLDCurve;
	}

	public void setFixed(boolean fixed) {
		Fixed = fixed;
	}

}
