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
 * The storage element can also produce or absorb VArs within the kVA rating
 * of the inverter.
 * That is, a StorageController object requests kvar and the storage element
 * provides them if it has any capacity left. The storage element can
 * produce/absorb kvar while idling.
 *
 * The Storage element is assumed balanced over the no. of phases defined.
 *
 * TODO: Make connection to user model
 * TODO: Yprim for various modes
 * TODO: Define state vars and dynamics mode behaviour
 * TODO: Complete harmonics mode algorithm (generator mode is implemented)
 *
 */
public interface StorageObj extends PCElement {

	double getKWChargeLosses();

	double getKWIdlingLosses();

	double getPresentKW();

	double getPresentKVAr();

	double getPresentKV();

	void setPresentKV(double value);

	void setPresentKVAr(double value);

	void setPresentKW(double value);

	void setPowerFactor(double calue);

	double getPowerFactor();

	void setState(int value);

	int getState();

	void setPctKVArOut(double value);

	double getPctKVArOut();

	void setPctKWOut(double value);

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

	String getYearlyShape();

	void setYearlyShape(String yearlyShape);

	LoadShapeObj getYearlyShapeObj();

	void setYearlyShapeObj(LoadShapeObj yearlyShapeObj);

	double getKWRating();

	void setKWRating(double kWRating);

	double getKWhRating();

	void setKWhRating(double kWhRating);

	double getKWhStored();

	void setKWhStored(double kWhStored);

	double getKWhReserve();

	void setKWhReserve(double kWhReserve);

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

	/** 0 = reset to 1.0; 1 = Gaussian around mean and std dev; 2 = uniform */
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

	boolean isKVANotSet();

	void setKVANotSet(boolean kVANotSet);

	double getKVArating();

	void setKVA_Rating(double kVArating);

	double getKVStorageBase();

	void setKVStorageBase(double kVStorageBase);

	double getKVArOut();

	void setKVArOut(double kvar_out);

	double getKWOut();

	void setKWOut(double kW_out);

	double getKVArRequested();

	void setKVArRequested(double kvarRequested);

	double getPctIdleKW();

	void setPctIdleKW(double pctIdlekW);

	double getPctIdleKVAr();

	void setPctIdleKVAr(double pctIdlekvar);

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

	int getRegHours();

	void setRegHours(int regHours);

	int getRegKVArh();

	void setRegKVArh(int reg_kvarh);

	int getRegKWh();

	void setRegKWh(int regkWh);

	int getRegMaxKVA();

	void setRegMaxKVA(int reg_MaxkVA);

	int getRegMaxKW();

	void setRegMaxKW(int reg_MaxkW);

	int getRegPrice();

	void setRegPrice(int regPrice);

	Complex getShapeFactor();

	void setShapeFactor(Complex shapeFactor);

	double getThetaHarm();

	void setThetaHarm(double thetaHarm);

	File getTraceFile();

	void setTraceFile(File traceFile);

	double getKVArBase();

	void setKVArBase(double kvarBase);

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
