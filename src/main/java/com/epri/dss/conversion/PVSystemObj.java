package com.epri.dss.conversion;

import java.io.File;

import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.TShapeObj;
import com.epri.dss.general.XYCurveObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.Complex;

/**
 * The PVsystem element is essentially a generator that consists of a PV panel and an inverter.
 *
 * The PVsystem element can also produce or absorb vars within the kVA rating of the inverter.
 *
 * The PVsystem element is assumed balanced over the no. of phases defined.
 *
 * TODO:
 *   Make connection to User model
 *   Yprim for various modes
 *   Define state vars and dynamics mode behavior
 *   Complete Harmonics mode algorithm (generator mode is implemented)
 *
 */
public interface PVSystemObj extends PCElement {

	void setNominalPVSystemOuput();

	/**
	 * 0 = reset to 1.0; 1 = Gaussian around mean and std Dev; 2 = uniform
	 */
	void randomize(int Opt);

	void resetRegisters();

	void takeSample();

	int getConnection();

	void setConnection(int connection);

	String getDailyShape();

	void setDailyShape(String dailyShape);

	LoadShapeObj getDailyShapeObj();

	void setDailyShapeObj(LoadShapeObj dailyShapeObj);

	String getDutyShape();

	void setDutyShape(String dutyShape);

	LoadShapeObj getDutyShapeObj();

	void setDutyShapeObj(LoadShapeObj dutyShapeObj);

	String getYearlyShape();

	void setYearlyShape(String yearlyShape);

	LoadShapeObj getYearlyShapeObj();

	void setYearlyShapeObj(LoadShapeObj yearlyShapeObj);

	String getDailyTShape();

	void setDailyTShape(String dailyTShape);

	TShapeObj getDailyTShapeObj();

	void setDailyTShapeObj(TShapeObj dailyTShapeObj);

	String getDutyTShape();

	void setDutyTShape(String dutyTShape);

	TShapeObj getDutyTShapeObj();

	void setDutyTShapeObj(TShapeObj dutyTShapeObj);

	String getYearlyTShape();

	void setYearlyTShape(String yearlyTShape);

	TShapeObj getYearlyTShapeObj();

	void setYearlyTShapeObj(TShapeObj yearlyTShapeObj);

	String getInverterCurve();

	void setInverterCurve(String inverterCurve);

	XYCurveObj getInverterCurveObj();

	void setInverterCurveObj(XYCurveObj inverterCurveObj);

	String getPower_TempCurve();

	void setPower_TempCurve(String power_TempCurve);

	XYCurveObj getPower_TempCurveObj();

	void setPower_TempCurveObj(XYCurveObj power_TempCurveObj);

	int getFClass();

	void setFClass(int fClass);

	int getVoltageModel();

	void setVoltageModel(int voltageModel);

	double getPresentkW();

	double getPresentkvar();

	double getPresentkV();

	double getPresentIrradiance();

	void setPresentkV(double Value);

	void setPresentkvar(double Value);

	void setPowerFactor(double Value);

	double getPowerFactor();  // FIXME Check interface for properties

	public void setPresentIrradiance(double Value);

	double[] getRegisters();

	void setRegisters(double[] registers);

	double[] getDerivatives();

	void setDerivatives(double[] derivatives);

	// FIXME Private methods in OpenDSS
	void updatePVSystem();


	// FIXME Private members in OpenDSS.

	Complex getYEQ();

	void setYEQ(Complex yEQ);

	Complex getYEQ95();

	void setYEQ95(Complex yEQ95);

	Complex getYEQ105();

	void setYEQ105(Complex yEQ105);

	boolean isDebugTrace();

	void setDebugTrace(boolean debugTrace);

	int getPVSystemSolutionCount();

	void setPVSystemSolutionCount(int pVSystemSolutionCount);

	double getPVSystemFundamental();

	void setPVSystemFundamental(double pVSystemFundamental);

	boolean isPVsystemObjSwitchOpen();

	void setPVsystemObjSwitchOpen(boolean pVsystemObjSwitchOpen);

	boolean isFirstSampleAfterReset();

	void setFirstSampleAfterReset(boolean firstSampleAfterReset);

	boolean isPFSpecified();

	void setPFSpecified(boolean pFSpecified);

	boolean isKvarSpecified();

	void setKvarSpecified(boolean kvarSpecified);

	double getkVArating();

	void setkVArating(double kVArating);

	double getkVPVSystemBase();

	void setkVPVSystemBase(double kVPVSystemBase);

	double getKvar_out();

	void setKvar_out(double kvar_out);

	double getkW_out();

	void setkW_out(double kW_out);

	double getPanelkW();

	void setPanelkW(double panelkW);

	double getIrradiance();

	void setIrradiance(double irradiance);

	double getKvarRequested();

	void setKvarRequested(double kvarRequested);

	double getTemperature();

	void setTemperature(double temperature);

	double getPmpp();

	void setPmpp(double pmpp);

	double getEffFactor();

	void setEffFactor(double effFactor);

	double getTempFactor();

	void setTempFactor(double tempFactor);

	boolean isInverterON();

	void setInverterON(boolean inverterON);

	double getPctCutIn();

	void setPctCutIn(double pctCutIn);

	double getPctCutOut();

	void setPctCutOut(double pctCutOut);

	double getCutInkW();

	void setCutInkW(double cutInkW);

	double getCutOutkW();

	void setCutOutkW(double cutOutkW);

	double getPctR();

	void setPctR(double pctR);

	double getPctX();

	void setPctX(double pctX);

	int getOpenPVSystemSolutionCount();

	void setOpenPVSystemSolutionCount(int openPVSystemSolutionCount);

	double getPnominalperphase();

	void setPnominalperphase(double pnominalperphase);

	double getQnominalperphase();

	void setQnominalperphase(double qnominalperphase);

	double getRandomMult();

	void setRandomMult(double randomMult);

	int getReg_Hours();

	void setReg_Hours(int reg_Hours);

	int getReg_kvarh();

	void setReg_kvarh(int reg_kvarh);

	int getReg_kWh();

	void setReg_kWh(int reg_kWh);

	int getReg_MaxkVA();

	void setReg_MaxkVA(int reg_MaxkVA);

	int getReg_MaxkW();

	void setReg_MaxkW(int reg_MaxkW);

	int getReg_Price();

	void setReg_Price(int reg_Price);

	Complex getShapeFactor();

	void setShapeFactor(Complex shapeFactor);

	double getTShapeValue();

	void setTShapeValue(double tShapeValue);

	double getThetaharm();

	void setThetaharm(double thetaharm);

	File getTracefile();

	void setTracefile(File tracefile);

	PVSystemUserModel getUserModel();

	void setUserModel(PVSystemUserModel userModel);

	double getVarBase();

	void setVarBase(double varBase);

	double getVBase();

	void setVBase(double vBase);

	double getVBase105();

	void setVBase105(double vBase105);

	double getVBase95();

	void setVBase95(double vBase95);

	double getVmaxpu();

	void setVmaxpu(double vmaxpu);

	double getVminpu();

	void setVminpu(double vminpu);

	double getVthevharm();

	void setVthevharm(double vthevharm);

	CMatrix getYPrimOpenCond();

	void setYPrimOpenCond(CMatrix yPrimOpenCond);

	double getRThev();

	void setRThev(double rThev);

	double getXThev();

	void setXThev(double xThev);

}
