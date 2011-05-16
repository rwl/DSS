package com.epri.dss.conversion.impl;

import java.io.FileWriter;
import java.io.PrintStream;
import com.epri.dss.common.DSSClass;
import com.epri.dss.conversion.PVSystem;
import com.epri.dss.conversion.PVSystemObj;
import com.epri.dss.conversion.PVSystemUserModel;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.TShapeObj;
import com.epri.dss.general.XYCurveObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.Complex;

public class PVSystemObjImpl extends PCElementImpl implements PVSystemObj {

	private Complex YEQ;     // at nominal
	private Complex YEQ95;   // at Vmin
	private Complex YEQ105;  // at VMax

	private boolean DebugTrace;
	private int PVSystemSolutionCount;
	private double PVSystemFundamental;  /* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private boolean PVsystemObjSwitchOpen;
	private boolean FirstSampleAfterReset;
	private boolean PFSpecified;
	private boolean kvarSpecified;

	private double kVArating;
	private double kVPVSystemBase;
	private double kvar_out;
	private double kW_out;
	private double PanelkW;  // computed
	private double Irradiance;
	private double kvarRequested;
	private double Temperature;
	private double Pmpp;

	private double EffFactor;
	private double TempFactor;

	private boolean InverterON;
	private double pctCutIn;
	private double pctCutOut;
	private double CutInkW;
	private double CutOutkW;

	private double pctR;
	private double pctX;

	private int OpenPVSystemSolutionCount;
	private double Pnominalperphase;
	private double Qnominalperphase;
	private double RandomMult;

	private int Reg_Hours;
	private int Reg_kvarh;
	private int Reg_kWh;
	private int Reg_MaxkVA;
	private int Reg_MaxkW;
	private int Reg_Price;
	private Complex ShapeFactor;
	private double TShapeValue;
	private double Thetaharm;  /* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private FileWriter Tracefile;
	private PVSystemUserModel UserModel;  /* User-Written Models */

	private double varBase;  // Base vars per phase
	private double VBase;    // Base volts suitable for computing currents
	private double VBase105;
	private double VBase95;
	private double Vmaxpu;
	private double Vminpu;
	private double Vthevharm;  /* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	private CMatrix YPrimOpenCond;
	private double RThev;
	private double XThev;

	// public members
	private int Connection;  /* 0 = line-neutral; 1 = Delta */
	private String DailyShape;  // Daily (24 HR) PVSystem element irradiance shape
	private LoadShapeObj DailyShapeObj;  // Daily PVSystem element irradianceShape for this load
	private String DutyShape;  // Duty cycle irradiance shape for changes typically less than one hour
	private LoadShapeObj DutyShapeObj;  // irradiance Shape for this PVSystem element
	private String YearlyShape;
	private LoadShapeObj YearlyShapeObj;  // Yearly irradiance Shape for this PVSystem element

	private String DailyTShape;
	private TShapeObj DailyTShapeObj;
	private String DutyTShape;
	private TShapeObj DutyTShapeObj;
	private String YearlyTShape;
	private TShapeObj YearlyTShapeObj;

	private String InverterCurve;
	private XYCurveObj InverterCurveObj;
	private String Power_TempCurve;
	private XYCurveObj Power_TempCurveObj;

	private int FClass;
	private int VoltageModel;  // Variation with voltage
	private double PFnominal;

	private double[] Registers = new double[PVSystem.NumPVSystemRegisters];
	private double[] Derivatives = new double[PVSystem.NumPVSystemRegisters];

	public PVSystemObjImpl(DSSClass ParClass, String SourceName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	private void calcDailyMult(double Hr) {

	}

	private void calcDutyMult(double Hr) {

	}

	private void calcYearlyMult(double Hr) {

	}

	private void calcDailyTemperature(double Hr) {

	}

	private void calcDutyTemperature(double Hr) {

	}

	private void calcYearlyTemperature(double Hr) {

	}

	private void computePanelPower() {

	}

	private void computeInverterPower() {

	}

	private void computekWkvar() {

	}

	private void calcPVSystemModelContribution() {
		// This is where the power gets computed
	}

	private void calcInjCurrentArray() {

	}

	/*private void calcVterminal()*/
	private void calcVTerminalPhase() {

	}

	private void calcYPrimMatrix(CMatrix Ymatrix) {

	}

	private void doConstantPQPVsystemObj() {

	}

	private void doConstantZPVsystemObj() {

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

	private void stickCurrInTerminalArray(Complex[] TermArray, Complex Curr, int i) {

	}

	private void writeTraceRecord(String s) {

	}

	// private void setKWandKvarOut()
	// FIXME Private method in OpenDSS
	public void updatePVSystem() {
		// Update PVSystem elements based on present kW and IntervalHrs variable
	}

	public double getPresentkW() {
		return 0;
	}

	public double getPresentkvar() {
		return 0;
	}

	public double getPresentkV() {
		return 0;
	}

	public double getPresentIrradiance() {
		return 0;
	}

	public void setPresentkV(double Value) {

	}

	public void setPresentkvar(double Value) {

	}

	public void setPowerFactor(double Value) {

	}

	public double getPowerFactor() {
		return PFnominal;
	}

	public void setPresentIrradiance(double Value) {

	}

	@Override
	public void setConductorClosed(int Index, boolean Value) {

	}

	@Override
	public void getTerminalCurrents(Complex[] Curr) {

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

	public void setNominalPVSystemOuput() {

	}

	public void randomize(int Opt) {
		// 0 = reset to 1.0; 1 = Gaussian around mean and std Dev  ;  // 2 = uniform
	}

	public void resetRegisters() {

	}

	public void takeSample() {

	}

	@Override
	public void initStateVars() {

	}

	@Override
	public void integrateStates() {

	}

	@Override
	public void initHarmonics() {

	}

	@Override
	public void makePosSequence() {
		// Make a positive Sequence Model
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

	public String getDailyTShape() {
		return DailyTShape;
	}

	public void setDailyTShape(String dailyTShape) {
		DailyTShape = dailyTShape;
	}

	public TShapeObj getDailyTShapeObj() {
		return DailyTShapeObj;
	}

	public void setDailyTShapeObj(TShapeObj dailyTShapeObj) {
		DailyTShapeObj = dailyTShapeObj;
	}

	public String getDutyTShape() {
		return DutyTShape;
	}

	public void setDutyTShape(String dutyTShape) {
		DutyTShape = dutyTShape;
	}

	public TShapeObj getDutyTShapeObj() {
		return DutyTShapeObj;
	}

	public void setDutyTShapeObj(TShapeObj dutyTShapeObj) {
		DutyTShapeObj = dutyTShapeObj;
	}

	public String getYearlyTShape() {
		return YearlyTShape;
	}

	public void setYearlyTShape(String yearlyTShape) {
		YearlyTShape = yearlyTShape;
	}

	public TShapeObj getYearlyTShapeObj() {
		return YearlyTShapeObj;
	}

	public void setYearlyTShapeObj(TShapeObj yearlyTShapeObj) {
		YearlyTShapeObj = yearlyTShapeObj;
	}

	public String getInverterCurve() {
		return InverterCurve;
	}

	public void setInverterCurve(String inverterCurve) {
		InverterCurve = inverterCurve;
	}

	public XYCurveObj getInverterCurveObj() {
		return InverterCurveObj;
	}

	public void setInverterCurveObj(XYCurveObj inverterCurveObj) {
		InverterCurveObj = inverterCurveObj;
	}

	public String getPower_TempCurve() {
		return Power_TempCurve;
	}

	public void setPower_TempCurve(String power_TempCurve) {
		Power_TempCurve = power_TempCurve;
	}

	public XYCurveObj getPower_TempCurveObj() {
		return Power_TempCurveObj;
	}

	public void setPower_TempCurveObj(XYCurveObj power_TempCurveObj) {
		Power_TempCurveObj = power_TempCurveObj;
	}

	public int getFClass() {
		return FClass;
	}

	public void setFClass(int fClass) {
		FClass = fClass;
	}

	public int getVoltageModel() {
		return VoltageModel;
	}

	public void setVoltageModel(int voltageModel) {
		VoltageModel = voltageModel;
	}

	public double getPFnominal() {
		return PFnominal;
	}

	public void setPFnominal(double pFnominal) {
		PFnominal = pFnominal;
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


	// FIXME Private members in OpenDSS.

	public Complex getYEQ() {
		return YEQ;
	}

	public void setYEQ(Complex yEQ) {
		YEQ = yEQ;
	}

	public Complex getYEQ95() {
		return YEQ95;
	}

	public void setYEQ95(Complex yEQ95) {
		YEQ95 = yEQ95;
	}

	public Complex getYEQ105() {
		return YEQ105;
	}

	public void setYEQ105(Complex yEQ105) {
		YEQ105 = yEQ105;
	}

	public boolean isDebugTrace() {
		return DebugTrace;
	}

	public void setDebugTrace(boolean debugTrace) {
		DebugTrace = debugTrace;
	}

	public int getPVSystemSolutionCount() {
		return PVSystemSolutionCount;
	}

	public void setPVSystemSolutionCount(int pVSystemSolutionCount) {
		PVSystemSolutionCount = pVSystemSolutionCount;
	}

	public double getPVSystemFundamental() {
		return PVSystemFundamental;
	}

	public void setPVSystemFundamental(double pVSystemFundamental) {
		PVSystemFundamental = pVSystemFundamental;
	}

	public boolean isPVsystemObjSwitchOpen() {
		return PVsystemObjSwitchOpen;
	}

	public void setPVsystemObjSwitchOpen(boolean pVsystemObjSwitchOpen) {
		PVsystemObjSwitchOpen = pVsystemObjSwitchOpen;
	}

	public boolean isFirstSampleAfterReset() {
		return FirstSampleAfterReset;
	}

	public void setFirstSampleAfterReset(boolean firstSampleAfterReset) {
		FirstSampleAfterReset = firstSampleAfterReset;
	}

	public boolean isPFSpecified() {
		return PFSpecified;
	}

	public void setPFSpecified(boolean pFSpecified) {
		PFSpecified = pFSpecified;
	}

	public boolean isKvarSpecified() {
		return kvarSpecified;
	}

	public void setKvarSpecified(boolean kvarSpecified) {
		this.kvarSpecified = kvarSpecified;
	}

	public double getkVArating() {
		return kVArating;
	}

	public void setkVArating(double kVArating) {
		this.kVArating = kVArating;
	}

	public double getkVPVSystemBase() {
		return kVPVSystemBase;
	}

	public void setkVPVSystemBase(double kVPVSystemBase) {
		this.kVPVSystemBase = kVPVSystemBase;
	}

	public double getKvar_out() {
		return kvar_out;
	}

	public void setKvar_out(double kvar_out) {
		this.kvar_out = kvar_out;
	}

	public double getkW_out() {
		return kW_out;
	}

	public void setkW_out(double kW_out) {
		this.kW_out = kW_out;
	}

	public double getPanelkW() {
		return PanelkW;
	}

	public void setPanelkW(double panelkW) {
		PanelkW = panelkW;
	}

	public double getIrradiance() {
		return Irradiance;
	}

	public void setIrradiance(double irradiance) {
		Irradiance = irradiance;
	}

	public double getKvarRequested() {
		return kvarRequested;
	}

	public void setKvarRequested(double kvarRequested) {
		this.kvarRequested = kvarRequested;
	}

	public double getTemperature() {
		return Temperature;
	}

	public void setTemperature(double temperature) {
		Temperature = temperature;
	}

	public double getPmpp() {
		return Pmpp;
	}

	public void setPmpp(double pmpp) {
		Pmpp = pmpp;
	}

	public double getEffFactor() {
		return EffFactor;
	}

	public void setEffFactor(double effFactor) {
		EffFactor = effFactor;
	}

	public double getTempFactor() {
		return TempFactor;
	}

	public void setTempFactor(double tempFactor) {
		TempFactor = tempFactor;
	}

	public boolean isInverterON() {
		return InverterON;
	}

	public void setInverterON(boolean inverterON) {
		InverterON = inverterON;
	}

	public double getPctCutIn() {
		return pctCutIn;
	}

	public void setPctCutIn(double pctCutIn) {
		this.pctCutIn = pctCutIn;
	}

	public double getPctCutOut() {
		return pctCutOut;
	}

	public void setPctCutOut(double pctCutOut) {
		this.pctCutOut = pctCutOut;
	}

	public double getCutInkW() {
		return CutInkW;
	}

	public void setCutInkW(double cutInkW) {
		CutInkW = cutInkW;
	}

	public double getCutOutkW() {
		return CutOutkW;
	}

	public void setCutOutkW(double cutOutkW) {
		CutOutkW = cutOutkW;
	}

	public double getPctR() {
		return pctR;
	}

	public void setPctR(double pctR) {
		this.pctR = pctR;
	}

	public double getPctX() {
		return pctX;
	}

	public void setPctX(double pctX) {
		this.pctX = pctX;
	}

	public int getOpenPVSystemSolutionCount() {
		return OpenPVSystemSolutionCount;
	}

	public void setOpenPVSystemSolutionCount(int openPVSystemSolutionCount) {
		OpenPVSystemSolutionCount = openPVSystemSolutionCount;
	}

	public double getPnominalperphase() {
		return Pnominalperphase;
	}

	public void setPnominalperphase(double pnominalperphase) {
		Pnominalperphase = pnominalperphase;
	}

	public double getQnominalperphase() {
		return Qnominalperphase;
	}

	public void setQnominalperphase(double qnominalperphase) {
		Qnominalperphase = qnominalperphase;
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

	public double getTShapeValue() {
		return TShapeValue;
	}

	public void setTShapeValue(double tShapeValue) {
		TShapeValue = tShapeValue;
	}

	public double getThetaharm() {
		return Thetaharm;
	}

	public void setThetaharm(double thetaharm) {
		Thetaharm = thetaharm;
	}

	public FileWriter getTracefile() {
		return Tracefile;
	}

	public void setTracefile(FileWriter tracefile) {
		Tracefile = tracefile;
	}

	public PVSystemUserModel getUserModel() {
		return UserModel;
	}

	public void setUserModel(PVSystemUserModel userModel) {
		UserModel = userModel;
	}

	public double getVarBase() {
		return varBase;
	}

	public void setVarBase(double varBase) {
		this.varBase = varBase;
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

	public double getVmaxpu() {
		return Vmaxpu;
	}

	public void setVmaxpu(double vmaxpu) {
		Vmaxpu = vmaxpu;
	}

	public double getVminpu() {
		return Vminpu;
	}

	public void setVminpu(double vminpu) {
		Vminpu = vminpu;
	}

	public double getVthevharm() {
		return Vthevharm;
	}

	public void setVthevharm(double vthevharm) {
		Vthevharm = vthevharm;
	}

	public CMatrix getYPrimOpenCond() {
		return YPrimOpenCond;
	}

	public void setYPrimOpenCond(CMatrix yPrimOpenCond) {
		YPrimOpenCond = yPrimOpenCond;
	}

	public double getRThev() {
		return RThev;
	}

	public void setRThev(double rThev) {
		RThev = rThev;
	}

	public double getXThev() {
		return XThev;
	}

	public void setXThev(double xThev) {
		XThev = xThev;
	}

}
