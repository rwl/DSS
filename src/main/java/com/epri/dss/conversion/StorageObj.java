package com.epri.dss.conversion;

import java.io.File;

import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.Complex;

/**
 * The storage element is essentially a generator that can be dispatched
 * to either produce power or consume power commensurate with rating and
 * amount of stored energy.
 *
 * The storage element can also produce or absorb vars within the kVA rating
 * of the inverter.
 * That is, a StorageController object requests kvar and the storage element
 * provides them if it has any capacity left. The storage element can
 * produce/absorb kvar while idling.
 *
 * The Storage element is assumed balanced over the no. of phases defined.
 *
 * TODO: Make connection to User model
 * TODO: Yprim for various modes
 * TODO: Define state vars and dynamics mode behavior
 * TODO: Complete Harmonics mode algorithm (generator mode is implemented)
 *
 */
public interface StorageObj extends PCElement {

	double getkWChargeLosses();

	double getkWIdlingLosses();

	double getPresentkW();

	double getPresentKVar();

	double getPresentKV();

	void setPresentKV(double Value);

	void setPresentKVar(double Value);

	void setPresentKW(double Value);

	void setPowerFactor(double Value);

	double getPowerFactor();

	void setState(int Value);

	int getState();

	void setPctKVarOut(double Value);

	double getPctKVarOut();

	void setPctKWOut(double Value);

	double getPctKWOut();

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

	int getStorageClass();

	void setStorageClass(int storageClass);

	int getVoltageModel();

	void setVoltageModel(int voltageModel);

	double getPFNominal();

	void setPFNominal(double pFNominal);

	String getYearlyShape();

	void setYearlyShape(String yearlyShape);

	LoadShapeObj getYearlyShapeObj();

	void setYearlyShapeObj(LoadShapeObj yearlyShapeObj);

	double getkWrating();

	void setkWrating(double kWrating);

	double getkWhRating();

	void setkWhRating(double kWhRating);

	double getkWhStored();

	void setkWhStored(double kWhStored);

	double getkWhReserve();

	void setkWhReserve(double kWhReserve);

	double getPctKWout();

	void setPctKWout(double pctKWout);

	double getPctKVarout();

	void setPctKVarout(double pctKVarout);

	double getPctKWin();

	void setPctKWin(double pctKWin);

	double getPctReserve();

	void setPctReserve(double pctReserve);

	int getDispatchMode();

	void setDispatchMode(int dispatchMode);

	double[] getRegisters();

	void setRegisters(double[] registers);

	double[] getDerivatives();

	void setDerivatives(double[] derivatives);

	void setNominalStorageOuput();

	/* 0 = reset to 1.0; 1 = Gaussian around mean and std Dev; 2 = uniform */
	void randomize(int Opt);

	void resetRegisters();

	void takeSample();

	// FIXME Private method in OpenDSS
	void updateStorage();

	// FIXME Private method in OpenDSS
	int interpretState(String S);

	// FIXME Private method in OpenDSS
	void syncUpPowerQuantities();


	// FIXME Private members in OpenDSS

	Complex getYeq();

	void setYeq(Complex yeq);

	Complex getYeq95();

	void setYeq95(Complex yeq95);

	Complex getYeq105();

	void setYeq105(Complex yeq105);

	Complex getYeqIdling();

	void setYeqIdling(Complex yeqIdling);

	boolean isDebugTrace();

	void setDebugTrace(boolean debugTrace);

	boolean isStateChanged();

	void setStateChanged(boolean stateChanged);

	boolean isFirstSampleAfterReset();

	void setFirstSampleAfterReset(boolean firstSampleAfterReset);

	int getStorageSolutionCount();

	void setStorageSolutionCount(int storageSolutionCount);

	double getStorageFundamental();

	void setStorageFundamental(double storageFundamental);

	boolean isStorageObjSwitchOpen();

	void setStorageObjSwitchOpen(boolean storageObjSwitchOpen);

	boolean iskVANotSet();

	void setkVANotSet(boolean kVANotSet);

	double getkVArating();

	void setkVArating(double kVArating);

	double getkVStorageBase();

	void setkVStorageBase(double kVStorageBase);

	double getKvar_out();

	void setKvar_out(double kvar_out);

	double getkW_out();

	void setkW_out(double kW_out);

	double getKvarRequested();

	void setKvarRequested(double kvarRequested);

	double getPctIdlekW();

	void setPctIdlekW(double pctIdlekW);

	double getPctIdlekvar();

	void setPctIdlekvar(double pctIdlekvar);

	double getPctChargeEff();

	void setPctChargeEff(double pctChargeEff);

	double getPctDischargeEff();

	void setPctDischargeEff(double pctDischargeEff);

	double getChargeEff();

	void setChargeEff(double chargeEff);

	double getDischargeEff();

	void setDischargeEff(double dischargeEff);

	double getDischargeTrigger();

	void setDischargeTrigger(double dischargeTrigger);

	double getChargeTrigger();

	void setChargeTrigger(double chargeTrigger);

	double getChargeTime();

	void setChargeTime(double chargeTime);

	double getPctR();

	void setPctR(double pctR);

	double getPctX();

	void setPctX(double pctX);

	int getOpenStorageSolutionCount();

	void setOpenStorageSolutionCount(int openStorageSolutionCount);

	double getPNominalPerPhase();

	void setPNominalPerPhase(double pNominalPerPhase);

	double getQNominalPerPhase();

	void setQNominalPerPhase(double qNominalPerPhase);

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

	double getThetaHarm();

	void setThetaHarm(double thetaHarm);

	File getTraceFile();

	void setTraceFile(File traceFile);

	double getkVarBase();

	void setkVarBase(double kvarBase);

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

	double getVThevhH();

	void setVThevhH(double vThevhH);

	CMatrix getYPrimOpenCond();

	void setYPrimOpenCond(CMatrix yPrimOpenCond);

	double getRThev();

	void setRThev(double rThev);

	double getXThev();

	void setXThev(double xThev);

	StoreUserModel getUserModel();

	void setUserModel(StoreUserModel userModel);

}
