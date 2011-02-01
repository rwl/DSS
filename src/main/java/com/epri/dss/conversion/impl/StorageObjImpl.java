package com.epri.dss.conversion.impl;

import java.io.File;
import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.conversion.StorageObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.shared.CMatrix;

public class StorageObjImpl extends PCElementImpl implements StorageObj {

	private static final int NumStorageRegisters = 6;    
	private static final int NumStorageVariables = 4;
	
	private static final int STORE_CHARGING = -1;
	private static final int STORE_IDLING =  0;
	private static final int STORE_DISCHARGING = 1;
	
	private static final int STORE_DEFAULT = 0;
	private static final int STORE_LOADMODE = 1;
	private static final int STORE_PRICEMODE = 2;
	private static final int STORE_EXTERNALMODE = 3;
	
	private Complex Yeq;         // at nominal
	private Complex Yeq95;       // at 95%
	private Complex Yeq105;      // at 105%
	private Complex YeqIdling;   // in shunt representing idle impedance

	private boolean DebugTrace;
	private int State;
	private boolean StateChanged;
	private boolean FirstSampleAfterReset;
	private int StorageSolutionCount;
	/* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private double StorageFundamental;  
	private boolean StorageObjSwitchOpen;

	private boolean kVANotSet;
	private double kVArating;
	private double kVStorageBase;
	private double kvar_out;
	private double kW_out;
	private double kvarRequested;
	private double pctIdlekW;
	private double pctIdlekvar;
	private double pctChargeEff;
	private double pctDischargeEff;
	private double ChargeEff;
	private double DischargeEff;
	private double DischargeTrigger;
	private double ChargeTrigger;
	private double ChargeTime;

	private double pctR;
	private double pctX;

	private int OpenStorageSolutionCount;
	private double PNominalPerPhase;
	private double QNominalPerPhase ;
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
//	private StoreUserModel UserModel;   

	private double varBase; // Base vars per phase
	private double VBase;  // Base volts suitable for computing currents
	private double VBase105;
	private double VBase95;
	private double Vmaxpu;
	private double Vminpu;
	/* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private double VThevhH; 
	private CMatrix YPrimOpenCond;
	private double RThev;
	private double XThev;
	
	/* 0 = line-neutral; 1 = Delta */
	protected int Connection;  
	/* Daily (24 HR) Storage element shape */
	protected String DailyShape;  
	/* Daily Storage element Shape for this load */
	protected LoadShapeObj DailyShapeObj;
	/* Duty cycle load shape for changes typically less than one hour */
	protected String DutyShape;  
	/* Shape for this Storage element */
	protected LoadShapeObj DutyShapeObj;  
	protected int StorageClass;
	/* Variation with voltage */
	protected int VoltageModel;   
	protected double PFNominal;
	/* ='fixed' means no variation  on all the time */
	protected String YearlyShape;  
	/* Shape for this Storage element */
	protected LoadShapeObj YearlyShapeObj;  

	protected double kWrating;
	protected double kWhRating;
	protected double kWhStored;
	protected double kWhReserve;
	/* percent of kW rated output currently dispatched */
	protected double pctKWout;   
	protected double pctKVarout;
	protected double pctKWin;
	protected double pctReserve;
	protected int DispatchMode;

	protected double[] Registers = new double[NumStorageRegisters];
	protected double[] Derivatives = new double[NumStorageRegisters];

	public StorageObjImpl(DSSClassImpl ParClass, String StorageName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	private void calcDailyMult(double Hr) {
		
	}
	
	private void CalcDutyMult(double Hr) {
		
	}
	
	private void calcStorageModelContribution() {
		
	}
	
	private void calcInjCurrentArray() {
		
	}
	
//    private void calcVterminal() {
//    	
//    }
	
	private void calcVTerminalPhase() {
		
	}
	
	private void calcYearlyMult(double Hr) {
		
	}
	
	private void calcYPrimMatrix(CMatrix Ymatrix) {
		
	}

	private void doConstantPQStorageObj() {
		
	}
	
	private void doConstantZStorageObj() {
		
	}
	
	private void doDynamicMode() {
		
	}
	
	private void doHarmonicMode() {
		
	}
	
	private void doUserModel() {
		
	}

	private void integrate(int Reg, double Deriv, double Interval) {
		
	}
	
	private void setDragHandRegister(int Reg, double Value) {
		
	}
	
	private void stickCurrInTerminalArray(Complex[] TermArray,
			double[] Curr, int i) {
		
	}

	private void writeTraceRecord(String S) {
		
	}

	private void syncUpPowerQuantities() {
		
	}
	
	private void setKWandKvarOut() {
		
	}
	
	private void checkStateTriggerLevel(double Level) {
		
	}
	
	/* Update Storage elements based on present kW and IntervalHrs variable */
	private void updateStorage() {
		
	}
	
	private double normalizeToTOD(int h, double sec) {
		return 0.0;
	}

	private int interpretState(String S) {
		return 0;
	}
	
	private String decodeState() {
		return null;
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
	
	public void setPowerFactor(double Value) {
		
	}
	
	public double getPowerFactor() {
		return PFNominal;
	}
	
	public void setState(int Value) {
		
	}
	
	public int getState() {
		return State;
	}
	
	public void setPctKVarOut(double Value) {
		
	}
	
	public double getPctKVarOut() {
		return pctKVarout;
	}
	
	public void setPctKWOut(double Value) {
		
	}
	
	public double getPctKWOut() {
		return pctKWout;
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

	public int getStorageClass() {
		return StorageClass;
	}

	public void setStorageClass(int storageClass) {
		StorageClass = storageClass;
	}

	public int getVoltageModel() {
		return VoltageModel;
	}

	public void setVoltageModel(int voltageModel) {
		VoltageModel = voltageModel;
	}

	public double getPFNominal() {
		return PFNominal;
	}

	public void setPFNominal(double pFNominal) {
		PFNominal = pFNominal;
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

	public double getkWrating() {
		return kWrating;
	}

	public void setkWrating(double kWrating) {
		this.kWrating = kWrating;
	}

	public double getkWhRating() {
		return kWhRating;
	}

	public void setkWhRating(double kWhRating) {
		this.kWhRating = kWhRating;
	}

	public double getkWhStored() {
		return kWhStored;
	}

	public void setkWhStored(double kWhStored) {
		this.kWhStored = kWhStored;
	}

	public double getkWhReserve() {
		return kWhReserve;
	}

	public void setkWhReserve(double kWhReserve) {
		this.kWhReserve = kWhReserve;
	}

	public double getPctKWout() {
		return pctKWout;
	}

	public void setPctKWout(double pctKWout) {
		this.pctKWout = pctKWout;
	}

	public double getPctKVarout() {
		return pctKVarout;
	}

	public void setPctKVarout(double pctKVarout) {
		this.pctKVarout = pctKVarout;
	}

	public double getPctKWin() {
		return pctKWin;
	}

	public void setPctKWin(double pctKWin) {
		this.pctKWin = pctKWin;
	}

	public double getPctReserve() {
		return pctReserve;
	}

	public void setPctReserve(double pctReserve) {
		this.pctReserve = pctReserve;
	}

	public int getDispatchMode() {
		return DispatchMode;
	}

	public void setDispatchMode(int dispatchMode) {
		DispatchMode = dispatchMode;
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
	public void setConductorClosed(int Index, boolean Value) {
		
	}
	
	@Override
	protected void getTerminalCurrents(Complex[] Curr) {
		
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
	
	public void setNominalStorageOuput() {
		
	}
	
	/* 0 = reset to 1.0; 1 = Gaussian around mean and std Dev; 2 = uniform */
	public void randomize(int Opt) {
		
	}
	
	public void resetRegisters() {
		
	}
	
	public void takeSample() {
		
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
