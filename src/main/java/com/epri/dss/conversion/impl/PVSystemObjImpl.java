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

	Complex YEQ;     // at nominal
	Complex YEQ95;   // at Vmin
	Complex YEQ105;  // at VMax

	boolean DebugTrace;
	int PVSystemSolutionCount;
	double PVSystemFundamental;  /* Thevinen equivalent voltage mag and angle reference for Harmonic model */
	boolean PVsystemObjSwitchOpen;
	boolean FirstSampleAfterReset;
	boolean PFSpecified;
	boolean kvarSpecified;

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
	private void updatePVSystem() {
		// Update PVSystem elements based on present kW and IntervalHrs variable
	}

	private double getPresentkW() {
		return 0;
	}

	private double getPresentkvar() {
		return 0;
	}

	private double getPresentkV() {
		return 0;
	}

	private double getPresentIrradiance() {
		return 0;
	}

	private void setPresentkV(double Value) {

	}

	private void setPresentkvar(double Value) {

	}

	private void setPowerFactor(double Value) {

	}

	private void setPresentIrradiance(double Value) {

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

}
