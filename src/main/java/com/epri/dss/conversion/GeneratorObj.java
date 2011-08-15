package com.epri.dss.conversion;

import java.io.File;

import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.shared.CMatrix;
import org.apache.commons.math.complex.Complex;
import com.epri.dss.shared.impl.GeneratorVars;

/**
 * The generator is essentially a negative load that can be dispatched.
 *
 * If the dispatch value (dispValue) is 0, the generator always follows the
 * appropriate dispatch curve, which are simply load curves. If dispValue>0
 * then the generator only comes on when the global circuit load multiplier
 * exceeds dispValue.  When the generator is on, it always follows the dispatch
 * curve appropriate for the type of solution being performed.
 *
 * If you want to model a generator that is fully on whenever it is dispatched
 * on, simply designate "status=Fixed".  The default is "status=Variable"
 * (i.e., it follows a dispatch curve.  You could also define a dispatch curve
 * that is always 1.0.
 *
 * Generators have their own energy meters that record:
 *   1. Total kwh
 *   2. Total kvarh
 *   3. Max kW
 *   4. Max kVA
 *   5. Hours in operation
 *   6. Price * kwH
 *
 * Generator meters reset with the circuit energy meters and take a sample
 * with the circuit energy meters as well. The Energy meters also used
 * trapezoidal integration so that they are compatible with Load-Duration
 * simulations.
 *
 * Generator models are:
 *   1. Constant P, Q  (* dispatch curve, if appropriate).
 *   2. Constant Z  (For simple solution)
 *   3. Constant P, |V|  like a standard power flow  [not implemented]
 *   4. Constant P, Fixed Q  (vars)
 *   5. Constant P, Fixed Q  (reactance)
 *
 * Most of the time you will use #1 for planning studies.
 *
 * The Generator is assumed balanced over the no. of phases defined
 *
 * If you do not specify load shapes defaults are:
 *   Yearly:  Defaults to No variation (i.e. multiplier = 1.0 always)
 *   Daily:   Defaults to No variation
 *   Dutycycle: Defaults to Daily shape
 *
 */
public interface GeneratorObj extends PCElement {

	double getPresentKW();

	double getPresentKVAr();

	double getPresentKV();

	void setPresentKV(double value);

	void setPresentKVAr(double value);

	void setPresentKW(double value);

	boolean isForcedOn();

	void setForcedOn(boolean forcedOn);

	void setPowerFactor(double value);

	double getPowerFactor();

	int getConnection();

	void setConnection(int connection);

	String getDailyDispShape();

	void setDailyDispShape(String dailyDispShape);

	LoadShapeObj getDailyDispShapeObj();

	void setDailyDispShapeObj(LoadShapeObj dailyDispShapeObj);

	String getDutyShape();

	void setDutyShape(String dutyShape);

	LoadShapeObj getDutyShapeObj();

	void setDutyShapeObj(LoadShapeObj dutyShapeObj);

	int getGenClass();

	void setGenClass(int genClass);

	int getGenModel();

	void setGenModel(int genModel);

	GeneratorVars getGenVars();

	void setGenVars(GeneratorVars genVars);

	double getKVArBase();

	void setKVArBase(double kvarBase);

	double getKVArMax();

	void setKVArMax(double kvarMax);

	double getKVArMin();

	void setKVArMin(double kvarMin);

	double getKWBase();

	void setKWBase(double kWBase);

	double getVpu();

	void setVpu(double vpu);

	double getVTarget();

	void setVTarget(double vTarget);

	String getYearlyShape();

	void setYearlyShape(String yearlyShape);

	LoadShapeObj getYearlyShapeObj();

	void setYearlyShapeObj(LoadShapeObj yearlyShapeObj);

	double[] getRegisters();

	void setRegisters(double[] registers);

	double[] getDerivatives();

	void setDerivatives(double[] derivatives);

	void setNominalGeneration();

	/** 0 = reset to 1.0; 1 = Gaussian around mean and std Dev; 2 = uniform */
	void randomize(int opt);

	void resetRegisters();

	void takeSample();

	/** Procedures for setting the dQdV used by the solution object */
	void initDQDVCalc();

	void bumpUpQ();

	void rememberQV();

	void calcDQDV();

	void resetStartPoint();

	/** Make a positive sequence model */
	void makePosSequence();

	// FIXME Private method in OpenDSS
	void syncUpPowerQuantities();

	// FIXME Private method in OpenDSS
	void setKwKVAr(double PkW, double QkVAr);


	// FIXME Private members in OpenDSS

	Complex getYeq();

	void setYeq(Complex yeq);

	Complex getYeq95();

	void setYeq95(Complex yeq95);

	Complex getYeq105();

	void setYeq105(Complex yeq105);

	Complex getCurrentLimit();

	void setCurrentLimit(Complex currentLimit);

	boolean isDebugTrace();

	void setDebugTrace(boolean debugTrace);

	double getDeltaQMax();

	void setDeltaQMax(double deltaQMax);

	int getDispatchMode();

	void setDispatchMode(int dispatchMode);

	double getDispatchValue();

	void setDispatchValue(double dispatchValue);

	double getDQDV();

	void setDQDV(double dQdV);

	double getDQDVSaved();

	void setDQDVSaved(double dQdVSaved);

	boolean isFirstSampleAfterReset();

	void setFirstSampleAfterReset(boolean firstSampleAfterReset);

	boolean isFixed();

	void setFixed(boolean fixed);

	int getGeneratorSolutionCount();

	void setGeneratorSolutionCount(int generatorSolutionCount);

	double getGenFundamental();

	void setGenFundamental(double genFundamental);

	boolean isGenOn();

	void setGenOn(boolean genOn);

	boolean isGenSwitchOpen();

	void setGenSwitchOpen(boolean genSwitchOpen);

	boolean iskVANotSet();

	void setkVANotSet(boolean kVANotSet);

	double getLastGrowthFactor();

	void setLastGrowthFactor(double lastGrowthFactor);

	int getLastYear();

	void setLastYear(int lastYear);

	int getOpenGeneratorSolutionCount();

	void setOpenGeneratorSolutionCount(int openGeneratorSolutionCount);

	double getPVFactor();

	void setPVFactor(double PVFactor);

	double getRandomMult();

	void setRandomMult(double randomMult);

	int getRegHours();

	void setRegHours(int regHours);

	int getRegKVArh();

	void setRegKVArh(int regKVArh);

	int getRegKWh();

	void setRegKWh(int regKWh);

	int getRegMaxKVA();

	void setReg_MaxkVA(int reg_MaxkVA);

	int getReg_MaxkW();

	void setRegMaxKW(int regMaxKW);

	int getRegPrice();

	void setRegPrice(int regPrice);

	Complex getShapeFactor();

	void setShapeFactor(Complex shapeFactor);

	double getThetaHarm();

	void setThetaHarm(double thetaHarm);

	File getTraceFile();

	void setTraceFile(File traceFile);

	double getV_Avg();

	void setV_Avg(double vAvg);

	double getVRemembered();

	void setVRemembered(double vRemembered);

	double getVArRemembered();

	void setVArRemembered(double varRemembered);

	double getVArBase();

	void setVArBase(double varBase);

	double getVArMax();

	void setVArMax(double varMax);

	double getVArMin();

	void setVArMin(double varMin);

	double getVBase();

	void setVBase(double vBase);

	double getVBase105();

	void setVBase105(double vBase105);

	double getVBase95();

	void setVBase95(double vBase95);

	double getVMaxPU();

	void setVMaxPU(double vMaxPU);

	double getVMinPU();

	void setVMinPU(double vMinPU);

	Complex getVThev();

	void setVThev(Complex vthev);

	double getVThevHarm();

	void setVThevHarm(double vThevHarm);

	double getVThevMag();

	void setVThevMag(double vThevMag);

	CMatrix getYPrimOpenCond();

	void setYPrimOpenCond(CMatrix yPrimOpenCond);

	double getYQFixed();

	void setYQFixed(double yQFixed);

	boolean isShapeIsActual();

	void setShapeIsActual(boolean shapeIsActual);

	GenUserModel getUserModel();

	void setUserModel(GenUserModel userModel);

	GenUserModel getShaftModel();

	void setShaftModel(GenUserModel shaftModel);

}
