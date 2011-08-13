package com.epri.dss.conversion;

import java.io.PrintStream;

import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.Complex;

import com.epri.dss.general.GrowthShapeObj;
import com.epri.dss.general.LoadShapeObj;

public interface LoadObj extends PCElement {

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

	double getEEN_Factor();

	void setEEN_Factor(double EEN_Factor);

	String getGrowthShape();

	void setGrowthShape(String growthShape);

	GrowthShapeObj getGrowthShapeObj();

	void setGrowthShapeObj(GrowthShapeObj growthShapeObj);

	Boolean getHasBeenAllocated();

	void setHasBeenAllocated(Boolean hasBeenAllocated);

	double getKWBase();

	void setKWBase(double kWBase);

	double getKVABase();

	void setKVABase(double kVABase);

	double getKVArBase();

	void setKVArBase(double kvarBase);

	double getKVLoadBase();

	void setKVLoadBase(double kVLoadBase);

	int getLoadClass();

	void setLoadClass(int loadClass);

	int getNumCustomers();

	void setNumCustomers(int numCustomers);

	int getLoadSpecType();

	void setLoadSpecType(int loadSpecType);

	double getPFNominal();

	void setPFNominal(double PFNominal);

	double getRNeut();

	void setRNeut(double rneut);

	double getUE_Factor();

	void setUE_Factor(double UE_Factor);

	double getXNeut();

	void setXNeut(double xneut);

	String getYearlyShape();

	void setYearlyShape(String yearlyShape);

	LoadShapeObj getYearlyShapeObj();

	void setYearlyShapeObj(LoadShapeObj yearlyShapeObj);

	String getCVRShape();

	void setCVRShape(String CVRshape);

	LoadShapeObj getCVRShapeObj();

	void setCVRShapeObj(LoadShapeObj CVRShapeObj);

	int getLoadModel();

	void setLoadModel(int loadModel);

	double getPUMean();

	double getPUStdDev();

	double getCVRWattFactor();

	double getCVRVArFactor();

	double getVMaxPU();

	double getVMinEmerg();

	double getVMinNormal();

	double getVMinPU();

	boolean isExemptFromLDCurve();

	boolean isFixed();

	boolean getUnserved();

	boolean getExceedsNormal();

	/* Allocate load from connected kva or kWh billing */
	void setKVAAllocationFactor(double value);

	double getKVAAllocationFactor();

	void setConnectedKVA(double value);

	double getConnectedKVA();

	/** Set kWh properties ... */

	void setCFactor(double value);

	double getCFactor();

	void setKWh(double value);

	double getKWh();

	void setKWhDays(double value);

	double getKWhDays();

	/* AllocationFactor adjusts either connected kVA allocation factor
	 * or kWh CFactor
	 */
	void setAllocationFactor(double value);

	double getAllocationFactor();

	void setKW_KVAr(double PkW, double QkVAr);

	void recalcElementData();

	void calcYPrim();

	int injCurrents();

	void getInjCurrents(Complex[] curr);

	void initHarmonics();

	/* Make a positive Sequence Model */
	void makePosSequence();

	void setNominalLoad();

	/*
	 * 0 = reset to 1.0
	 * 1 = Gaussian around mean and std Dev
	 * 2 = uniform
	 */
	void randomize(int opt);

	String getPropertyValue(int index);

	void initPropertyValues(int arrayOffset);

	void dumpProperties(PrintStream f, boolean complete);

	void updateVoltageBases();

	// FIXME Private method in OpenDSS
	void setZIPVSize(int n);

	// FIXME Private members in OpenDSS.

	boolean isPFChanged();

	void setPFChanged(boolean PFChanged);

	double getAvgKW();

	void setAvgKW(double avgKW);

	double[] getHarmAng();

	void setHarmAng(double[] harmAng);

	double[] getHarmMag();

	void setHarmMag(double[] harmMag);

	double getLastGrowthFactor();

	void setLastGrowthFactor(double lastGrowthFactor);

	int getLastYear();

	void setLastYear(int lastYear);

	double getLoadFundamental();

	void setLoadFundamental(double loadFundamental);

	int getLoadSolutionCount();

	void setLoadSolutionCount(int loadSolutionCount);

	int getOpenLoadSolutionCount();

	void setOpenLoadSolutionCount(int openLoadSolutionCount);

	double getRandomMult();

	void setRandomMult(double randomMult);

	Complex getShapeFactor();

	void setShapeFactor(Complex shapeFactor);

	double getVArBase();

	void setVArBase(double varBase);

	double getVArNominal();

	void setVArNominal(double varNominal);

	double getVBase();

	void setVBase(double vBase);

	double getVBase105();

	void setVBase105(double vBase105);

	double getVBase95();

	void setVBase95(double vBase95);

	double getWNominal();

	void setWNominal(double wNominal);

	Complex getYeq();

	void setYeq(Complex yeq);

	Complex getYeq105();

	void setYeq105(Complex yeq105);

	Complex getYeq95();

	void setYeq95(Complex yeq95);

	Complex getYneut();

	void setYneut(Complex yneut);

	CMatrix getYPrimOpenCond();

	void setYPrimOpenCond(CMatrix YPrimOpenCond);

	double getYQFixed();

	void setYQFixed(double yQFixed);

	void setPUMean(double puMean);

	void setPUStdDev(double puStdDev);

	void setCVRWattFactor(double CVRwattFactor);

	void setCVRVArFactor(double cVRvarFactor);

	void setVMaxPU(double vmaxpu);

	void setVMinEmerg(double vminEmerg);

	void setVMinNormal(double vminNormal);

	void setVMinPU(double vminpu);

	void setExemptFromLDCurve(boolean exemptFromLDCurve);

	void setFixed(boolean fixed);

	boolean shapeIsActual();

	void setShapeIsActual(boolean shapeIsActual);

	double[] getZIPV();

	void setZIPV(double[] zIPV);

	void setNZIPV(int NZIPV);

	int getNZIPV();

}
