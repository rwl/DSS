package com.epri.dss.conversion;

import java.io.File;

import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.TShapeObj;
import com.epri.dss.general.XYCurveObj;
import com.epri.dss.shared.CMatrix;
import org.apache.commons.math.complex.Complex;

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
	void randomize(int opt);

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

	String getPowerTempCurve();

	void setPowerTempCurve(String powerTempCurve);

	XYCurveObj getPowerTempCurveObj();

	void setPowerTempCurveObj(XYCurveObj powerTempCurveObj);

	int getFClass();

	void setFClass(int fClass);

	int getVoltageModel();

	void setVoltageModel(int voltageModel);

	double getPresentKW();

	double getPresentKVAr();

	double getPresentKV();

	double getPresentIrradiance();

	void setPresentKV(double value);

	void setPresentKVAr(double value);

	void setPowerFactor(double value);

	double getPowerFactor();  // FIXME Check interface for properties

	public void setPresentIrradiance(double value);

	double[] getRegisters();

	void setRegisters(double[] registers);

	double[] getDerivatives();

	void setDerivatives(double[] derivatives);

	// FIXME Private methods in OpenDSS
	void updatePVSystem();


	// FIXME Private members in OpenDSS.

	Complex getYeq();

	void setYeq(Complex yeq);

	Complex getYeq95();

	void setYeq95(Complex yeq95);

	Complex getYeq105();

	void setYeq105(Complex yeq105);

	boolean isDebugTrace();

	void setDebugTrace(boolean debugTrace);

	int getPVSystemSolutionCount();

	void setPVSystemSolutionCount(int PVSystemSolutionCount);

	double getPVSystemFundamental();

	void setPVSystemFundamental(double PVSystemFundamental);

	boolean isPVSystemObjSwitchOpen();

	void setPVSystemObjSwitchOpen(boolean PVSystemObjSwitchOpen);

	boolean isFirstSampleAfterReset();

	void setFirstSampleAfterReset(boolean firstSampleAfterReset);

	boolean isPFSpecified();

	void setPFSpecified(boolean PFSpecified);

	boolean isKVArSpecified();

	void setKVArSpecified(boolean kvarSpecified);

	double getKVARating();

	void setKVArating(double kVArating);

	double getKVPVSystemBase();

	void setKVPVSystemBase(double kVPVSystemBase);

	double getKVArOut();

	void setKVArOut(double kvar_out);

	double getKWOut();

	void setKWOut(double kwout);

	double getPanelKW();

	void setPanelKW(double panelKW);

	double getIrradiance();

	void setIrradiance(double irradiance);

	double getKVArRequested();

	void setKVArRequested(double kvarRequested);

	double getTemperature();

	void setTemperature(double temperature);

	double getPmpp();

	void setPmpp(double pmpp);

	double getEffFactor();

	void setEffFactor(double effFactor);

	double getTempFactor();

	void setTempFactor(double tempFactor);

	boolean isInverterOn();

	void setInverterOn(boolean inverterOn);

	double getPctCutIn();

	void setPctCutIn(double pctCutIn);

	double getPctCutOut();

	void setPctCutOut(double pctCutOut);

	double getCutInKW();

	void setCutInKW(double cutInKW);

	double getCutOutKW();

	void setCutOutKW(double cutOutKW);

	double getPctR();

	void setPctR(double pctR);

	double getPctX();

	void setPctX(double pctX);

	int getOpenPVSystemSolutionCount();

	void setOpenPVSystemSolutionCount(int openPVSystemSolutionCount);

	double getPNominalPerPhase();

	void setPNominalPerPhase(double pnominalperphase);

	double getQNominalPerPhase();

	void setQNominalPerPhase(double qnominalperphase);

	double getRandomMult();

	void setRandomMult(double randomMult);

	int getRegHours();

	void setRegHours(int regHours);

	int getRegKVArh();

	void setRegKVArh(int reg_kvarh);

	int getRegKWh();

	void setRegKWh(int reg_kWh);

	int getRegMaxKVA();

	void setRegMaxKVA(int reg_MaxkVA);

	int getRegMaxKW();

	void setRegMaxKW(int regMaxKW);

	int getRegPrice();

	void setRegPrice(int regPrice);

	Complex getShapeFactor();

	void setShapeFactor(Complex shapeFactor);

	double getTShapeValue();

	void setTShapeValue(double tShapeValue);

	double getThetaHarm();

	void setThetaHarm(double thetaharm);

	File getTraceFile();

	void setTraceFile(File tracefile);

	PVSystemUserModel getUserModel();

	void setUserModel(PVSystemUserModel userModel);

	double getVArBase();

	void setVArBase(double varBase);

	double getVBase();

	void setVBase(double vBase);

	double getVBase105();

	void setVBase105(double vBase105);

	double getVBase95();

	void setVBase95(double vBase95);

	double getVMaxPU();

	void setVMaxPU(double vmaxpu);

	double getVMinPU();

	void setVMinPU(double vminpu);

	double getVThevHarm();

	void setVThevHarm(double vthevharm);

	CMatrix getYPrimOpenCond();

	void setYPrimOpenCond(CMatrix yPrimOpenCond);

	double getRThev();

	void setRThev(double rThev);

	double getXThev();

	void setXThev(double xThev);

}
