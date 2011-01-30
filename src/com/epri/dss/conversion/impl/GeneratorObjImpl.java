package com.epri.dss.conversion.impl;

import java.io.File;
import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.shared.impl.DynamicsImpl.GeneratorVars;

public class GeneratorObjImpl extends PCElementImpl implements GeneratorObj {
	
	/* Number of energy meter registers */
	private static final int NumGenRegisters = 6;    
	private static final int NumGenVariables = 6;
	
	private double[] Yeq;     // at nominal
	private double[] Yeq95;   // at 95%
	private double[] Yeq105;  // at 105%

	private double[] CurrentLimit;
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
	private double[] ShapeFactor;
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
	private double[] Vthev;  
	/* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private double VThevHarm;  
	/* Thevinen equivalent voltage for dynamic model */
	private double VThevMag;    
	/*
	 * To handle cases where one conductor of load is open;
	 * We revert to admittance for inj currents
	 */
	private DComplexMatrix2D YPrimOpenCond;
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
	
	private void calcYPrimMatrix(DComplexMatrix2D Ymatrix) {
		
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
	
	private void stickCurrInTerminalArray(DComplexMatrix1D TermArray,
			double[] Curr, int i) {
		
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
	protected void getTerminalCurrents(DComplexMatrix1D Curr) {
		
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
	public void getInjCurrents(DComplexMatrix1D Curr) {
		
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

}
