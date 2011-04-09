package com.epri.dss.conversion.impl;

import java.io.File;
import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.DynamicsImpl.GeneratorVars;

public class GeneratorObjImpl extends PCElementImpl implements GeneratorObj {
	
	/* Number of energy meter registers */
	private static final int NumGenRegisters = 6;    
	private static final int NumGenVariables = 6;
	
	private Complex Yeq;     // at nominal
	private Complex Yeq95;   // at 95%
	private Complex Yeq105;  // at 105%

	private Complex CurrentLimit;
	private boolean DebugTrace;
	/* Max allowable var change on Model=3 per iteration */
	private double DeltaQMax;  
	private int DispatchMode;
	private double DispatchValue;
	private double dQdV;
	private double dQdVSaved;
	private boolean ForcedON;
	private boolean FirstSampleAfterReset;
	private boolean Fixed;   // if Fixed, always at base value
	private int GeneratorSolutionCount;
	/* Thevenin equivalent voltage mag and angle reference for Harmonic model */
	private double GenFundamental; 
	/* Indicates whether generator is currently on */
	private boolean GenON;           
	private boolean GenSwitchOpen;
	private boolean kVANotSet;
	private double LastGrowthFactor;
	/* Added for speedup so we don't have to search for growth factor a lot */
	private int LastYear;   
	private int OpenGeneratorSolutionCount;
	/* Deceleration Factor for computing vars for PV generators */
	private double PVFactor;  
	private double RandomMult;
	private int Reg_Hours;
	private int Reg_kvarh;
	private int Reg_kWh;
	private int Reg_MaxkVA;
	private int Reg_MaxkW;
	private int Reg_Price;
	private Complex ShapeFactor;
	/* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private double ThetaHarm;  
	private File TraceFile;
	/* User-Written Models */
//	private GenUserModel UserModel, ShaftModel;   
	private double V_Avg;
	private double V_Remembered;
	private double var_Remembered;
	/* Base vars per phase */
	private double varBase; 
	private double varMax;
	private double varMin;
	/* Base volts suitable for computing currents */
	private double VBase;  
	private double VBase105;
	private double VBase95;
	private double VMaxPU;
	private double VMinPU;
	/* Thevinen equivalent voltage (complex) for dynamic model */
	private Complex Vthev;  
	/* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private double VThevHarm;  
	/* Thevinen equivalent voltage for dynamic model */
	private double VThevMag;    
	/*
	 * To handle cases where one conductor of load is open;
	 * We revert to admittance for inj currents
	 */
	private CMatrix YPrimOpenCond;
	/* Fixed value of y for type 7 load */
	private double YQFixed;  
	private boolean ShapeIsActual;
	
	/* 0 = line-neutral; 1 = Delta */
	protected int Connection; 
	/* Daily (24 HR) Generator shape */
	protected String DailyDispShape;  
	/* Daily Generator Shape for this load */
	protected LoadShapeObj DailyDispShapeObj;
	/* Duty cycle load shape for changes typically less than one hour */
	protected String DutyShape;  
	/* Shape for this generator */
	protected LoadShapeObj DutyShapeObj;  
	protected int GenClass;
	/* Variation with voltage */
	protected int GenModel;   
	/* State Variables */
	protected GeneratorVars GenVars; 
	protected double kvarBase;
	protected double kvarMax;
	protected double kvarMin;
	protected double kWBase;
	protected double PFNominal;
	/* Per unit Target voltage for generator with voltage control */
	protected double Vpu;       
	/* Target voltage for generator with voltage control */
	protected double VTarget;  
	/* ='fixed' means no variation  on all the time */
	protected String YearlyShape;  
	/* Shape for this Generator */
	protected LoadShapeObj YearlyShapeObj;  

	protected double[] Registers = new double[NumGenRegisters];
	protected double[] Derivatives = new double[NumGenRegisters];

	public GeneratorObjImpl(DSSClassImpl ParClass, String GeneratorName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	private void calcDailyMult(double Hr) {
		
	}
	
	private void calcDutyMult(double Hr) {
		
	}
	
	private void calcGenModelContribution() {
		
	}
	
	private void calcInjCurrentArray() {
		
	}
	
	private void calcVterminal() {
		
	}
	
	private void calcVTerminalPhase() {
		
	}
	
	/* 3-phase Voltage behind transient reactance */
	private void calcVThevDyn() {
		
	}
	
	private void calcYearlyMult(double Hr) {
		
	}
	
	private void calcYPrimMatrix(CMatrix Ymatrix) {
		
	}

	private void doConstantPQGen() {
		
	}
	
	private void doConstantZGen() {
		
	}
	
	private void doCurrentLimitedPQ() {
		
	}
	
	private void doDynamicMode() {
		
	}
	
	private void doFixedQGen() {
		
	}
	
	private void doFixedQZGen() {
		
	}
	
	private void doHarmonicMode() {
		
	}
	
	private void doPVTypeGen() {
		
	}
	
	private void doUserModel() {
		
	}

	private void integrate(int Reg, double Deriv, double Interval) {
		
	}
	
	private void setDragHandRegister(int Reg, double Value) {
		
	}
	
	private void stickCurrInTerminalArray(Complex[] TermArray,
			Complex Curr, int i) {
		
	}

	private void writeTraceRecord(String s) {
		
	}

	private void syncUpPowerQuantities() {
		
	}

	public double getPresentkW() {
		return 0.0;
	}
	
	public double getPresentKVar() {
		return 0.0;
	}
	
	public double getPresentKV() {
		return 0.0;
	}
	
	public void setPresentKV(double Value) {
		
	}
	
	public void setPresentKVar(double Value) {
		
	}
	
	public void setPresentKW(double Value) {
		
	}
	
	public boolean isForcedON() {
		return ForcedON;
	}

	public void setForcedON(boolean forcedON) {
		ForcedON = forcedON;
	}

	public void setPowerFactor(double Value) {
		
	}
	
	public double getPowerFactor() {
		return PFNominal;
	}

	private void setKwKVar(double PkW, double QkVar) {
		
	}
	
	@Override
	public void setConductorClosed(int Index, boolean Value) {
		
	}
	
	@Override
	protected void getTerminalCurrents(Complex[] Curr) {
		
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}

	public String getDailyDispShape() {
		return DailyDispShape;
	}

	public void setDailyDispShape(String dailyDispShape) {
		DailyDispShape = dailyDispShape;
	}

	public LoadShapeObj getDailyDispShapeObj() {
		return DailyDispShapeObj;
	}

	public void setDailyDispShapeObj(LoadShapeObj dailyDispShapeObj) {
		DailyDispShapeObj = dailyDispShapeObj;
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

	public int getGenClass() {
		return GenClass;
	}

	public void setGenClass(int genClass) {
		GenClass = genClass;
	}

	public int getGenModel() {
		return GenModel;
	}

	public void setGenModel(int genModel) {
		GenModel = genModel;
	}

	public GeneratorVars getGenVars() {
		return GenVars;
	}

	public void setGenVars(GeneratorVars genVars) {
		GenVars = genVars;
	}

	public double getKvarBase() {
		return kvarBase;
	}

	public void setKvarBase(double kvarBase) {
		this.kvarBase = kvarBase;
	}

	public double getKvarMax() {
		return kvarMax;
	}

	public void setKvarMax(double kvarMax) {
		this.kvarMax = kvarMax;
	}

	public double getKvarMin() {
		return kvarMin;
	}

	public void setKvarMin(double kvarMin) {
		this.kvarMin = kvarMin;
	}

	public double getkWBase() {
		return kWBase;
	}

	public void setkWBase(double kWBase) {
		this.kWBase = kWBase;
	}

	public double getPFNominal() {
		return PFNominal;
	}

	public void setPFNominal(double pFNominal) {
		PFNominal = pFNominal;
	}

	public double getVpu() {
		return Vpu;
	}

	public void setVpu(double vpu) {
		Vpu = vpu;
	}

	public double getVTarget() {
		return VTarget;
	}

	public void setVTarget(double vTarget) {
		VTarget = vTarget;
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

	public double[] getRegisters() {
		return Registers;
	}

	public void setRegisters(double[] registers) {
		Registers = registers;
	}

	public double[] getDerivatives() {
		return Derivatives;
	}

	public void setDerivatives(double[] derivatives) {
		Derivatives = derivatives;
	}
	
	@Override
	public void recalcElementData() {
		
	}
	
	@Override
	public void calcYPrim() {
		
	}
	
	@Override
	public int injCurrents() {
		return 0;
	}
	
	@Override
	public void getInjCurrents(Complex[] Curr) {
		
	}
	
	@Override
	public int numVariables() {
		return 0;
	}
	
	@Override
	public void getAllVariables(double[] States) {
		
	}
	
	@Override
	public double getVariable(int i) {
		return 0.0;
	}
	
	@Override
	public void setVariable(int i, double Value) {
		
	}
	
	@Override
	public String variableName(int i) {
		return null;
	}
	
	public void setNominalGeneration() {
		
	}
	
	/* 0 = reset to 1.0; 1 = Gaussian around mean and std Dev; 2 = uniform */
	public void randomize(int Opt) {
		
	}
	
	public void resetRegisters() {
		
	}
	
	public void takeSample() {
		
	}
	
	/* Procedures for setting the DQDV used by the Solution Object */
	public void initDQDVCalc() {
		
	}
	
	public void bumpUpQ() {
		
	}
	
	public void rememberQV() {
		
	}
	
	public void calcDQDV() {
		
	}
	
	public void resetStartPoint() {
		
	}
	
	/* Support for Dynamics Mode */
	
	@Override
	public void initStateVars() {
		
	}
	
	@Override
	public void integrateStates() {
		
	}
	
	/* Support for Harmonics Mode */
	
	@Override
	public void initHarmonics() {
		
	}
	
	/* Make a positive Sequence Model */
	@Override
	public void makePosSequence() {
		
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override 
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}
	
	@Override
	public String getPropertyValue(int Index) {
		return null;
	}
	
	// FIXME Private members in OpenDSS

	public Complex getYeq() {
		return Yeq;
	}

	public void setYeq(Complex yeq) {
		Yeq = yeq;
	}

	public Complex getYeq95() {
		return Yeq95;
	}

	public void setYeq95(Complex yeq95) {
		Yeq95 = yeq95;
	}

	public Complex getYeq105() {
		return Yeq105;
	}

	public void setYeq105(Complex yeq105) {
		Yeq105 = yeq105;
	}

	public Complex getCurrentLimit() {
		return CurrentLimit;
	}

	public void setCurrentLimit(Complex currentLimit) {
		CurrentLimit = currentLimit;
	}

	public boolean isDebugTrace() {
		return DebugTrace;
	}

	public void setDebugTrace(boolean debugTrace) {
		DebugTrace = debugTrace;
	}

	public double getDeltaQMax() {
		return DeltaQMax;
	}

	public void setDeltaQMax(double deltaQMax) {
		DeltaQMax = deltaQMax;
	}

	public int getDispatchMode() {
		return DispatchMode;
	}

	public void setDispatchMode(int dispatchMode) {
		DispatchMode = dispatchMode;
	}

	public double getDispatchValue() {
		return DispatchValue;
	}

	public void setDispatchValue(double dispatchValue) {
		DispatchValue = dispatchValue;
	}

	public double getdQdV() {
		return dQdV;
	}

	public void setdQdV(double dQdV) {
		this.dQdV = dQdV;
	}

	public double getdQdVSaved() {
		return dQdVSaved;
	}

	public void setdQdVSaved(double dQdVSaved) {
		this.dQdVSaved = dQdVSaved;
	}

	public boolean isFirstSampleAfterReset() {
		return FirstSampleAfterReset;
	}

	public void setFirstSampleAfterReset(boolean firstSampleAfterReset) {
		FirstSampleAfterReset = firstSampleAfterReset;
	}

	public boolean isFixed() {
		return Fixed;
	}

	public void setFixed(boolean fixed) {
		Fixed = fixed;
	}

	public int getGeneratorSolutionCount() {
		return GeneratorSolutionCount;
	}

	public void setGeneratorSolutionCount(int generatorSolutionCount) {
		GeneratorSolutionCount = generatorSolutionCount;
	}

	public double getGenFundamental() {
		return GenFundamental;
	}

	public void setGenFundamental(double genFundamental) {
		GenFundamental = genFundamental;
	}

	public boolean isGenON() {
		return GenON;
	}

	public void setGenON(boolean genON) {
		GenON = genON;
	}

	public boolean isGenSwitchOpen() {
		return GenSwitchOpen;
	}

	public void setGenSwitchOpen(boolean genSwitchOpen) {
		GenSwitchOpen = genSwitchOpen;
	}

	public boolean iskVANotSet() {
		return kVANotSet;
	}

	public void setkVANotSet(boolean kVANotSet) {
		this.kVANotSet = kVANotSet;
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

	public int getOpenGeneratorSolutionCount() {
		return OpenGeneratorSolutionCount;
	}

	public void setOpenGeneratorSolutionCount(int openGeneratorSolutionCount) {
		OpenGeneratorSolutionCount = openGeneratorSolutionCount;
	}

	public double getPVFactor() {
		return PVFactor;
	}

	public void setPVFactor(double pVFactor) {
		PVFactor = pVFactor;
	}

	public double getRandomMult() {
		return RandomMult;
	}

	public void setRandomMult(double randomMult) {
		RandomMult = randomMult;
	}

	public int getReg_Hours() {
		return Reg_Hours;
	}

	public void setReg_Hours(int reg_Hours) {
		Reg_Hours = reg_Hours;
	}

	public int getReg_kvarh() {
		return Reg_kvarh;
	}

	public void setReg_kvarh(int reg_kvarh) {
		Reg_kvarh = reg_kvarh;
	}

	public int getReg_kWh() {
		return Reg_kWh;
	}

	public void setReg_kWh(int reg_kWh) {
		Reg_kWh = reg_kWh;
	}

	public int getReg_MaxkVA() {
		return Reg_MaxkVA;
	}

	public void setReg_MaxkVA(int reg_MaxkVA) {
		Reg_MaxkVA = reg_MaxkVA;
	}

	public int getReg_MaxkW() {
		return Reg_MaxkW;
	}

	public void setReg_MaxkW(int reg_MaxkW) {
		Reg_MaxkW = reg_MaxkW;
	}

	public int getReg_Price() {
		return Reg_Price;
	}

	public void setReg_Price(int reg_Price) {
		Reg_Price = reg_Price;
	}

	public Complex getShapeFactor() {
		return ShapeFactor;
	}

	public void setShapeFactor(Complex shapeFactor) {
		ShapeFactor = shapeFactor;
	}

	public double getThetaHarm() {
		return ThetaHarm;
	}

	public void setThetaHarm(double thetaHarm) {
		ThetaHarm = thetaHarm;
	}

	public File getTraceFile() {
		return TraceFile;
	}

	public void setTraceFile(File traceFile) {
		TraceFile = traceFile;
	}

	public double getV_Avg() {
		return V_Avg;
	}

	public void setV_Avg(double v_Avg) {
		V_Avg = v_Avg;
	}

	public double getV_Remembered() {
		return V_Remembered;
	}

	public void setV_Remembered(double v_Remembered) {
		V_Remembered = v_Remembered;
	}

	public double getVar_Remembered() {
		return var_Remembered;
	}

	public void setVar_Remembered(double var_Remembered) {
		this.var_Remembered = var_Remembered;
	}

	public double getVarBase() {
		return varBase;
	}

	public void setVarBase(double varBase) {
		this.varBase = varBase;
	}

	public double getVarMax() {
		return varMax;
	}

	public void setVarMax(double varMax) {
		this.varMax = varMax;
	}

	public double getVarMin() {
		return varMin;
	}

	public void setVarMin(double varMin) {
		this.varMin = varMin;
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

	public double getVMaxPU() {
		return VMaxPU;
	}

	public void setVMaxPU(double vMaxPU) {
		VMaxPU = vMaxPU;
	}

	public double getVMinPU() {
		return VMinPU;
	}

	public void setVMinPU(double vMinPU) {
		VMinPU = vMinPU;
	}

	public Complex getVthev() {
		return Vthev;
	}

	public void setVthev(Complex vthev) {
		Vthev = vthev;
	}

	public double getVThevHarm() {
		return VThevHarm;
	}

	public void setVThevHarm(double vThevHarm) {
		VThevHarm = vThevHarm;
	}

	public double getVThevMag() {
		return VThevMag;
	}

	public void setVThevMag(double vThevMag) {
		VThevMag = vThevMag;
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

	public boolean isShapeIsActual() {
		return ShapeIsActual;
	}

	public void setShapeIsActual(boolean shapeIsActual) {
		ShapeIsActual = shapeIsActual;
	}

}
