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

	void setEEN_Factor(double eEN_Factor);

	String getGrowthShape();

	void setGrowthShape(String growthShape);

	GrowthShapeObj getGrowthShapeObj();

	void setGrowthShapeObj(GrowthShapeObj growthShapeObj);

	Boolean getHasBeenAllocated();

	void setHasBeenAllocated(Boolean hasBeenAllocated);

	double getkWBase();

	void setkWBase(double kWBase);

	double getkVABase();

	void setkVABase(double kVABase);

	double getKvarBase();

	void setKvarBase(double kvarBase);

	double getkVLoadBase();

	void setkVLoadBase(double kVLoadBase);

	int getLoadClass();

	void setLoadClass(int loadClass);

	int getNumCustomers();

	void setNumCustomers(int numCustomers);

	int getLoadSpecType();

	void setLoadSpecType(int loadSpecType);

	double getPFNominal();

	void setPFNominal(double pFNominal);

	double getRneut();

	void setRneut(double rneut);

	double getUE_Factor();

	void setUE_Factor(double uE_Factor);

	double getXneut();

	void setXneut(double xneut);

	String getYearlyShape();

	void setYearlyShape(String yearlyShape);

	LoadShapeObj getYearlyShapeObj();

	void setYearlyShapeObj(LoadShapeObj yearlyShapeObj);

	String getCVRshape();

	void setCVRshape(String cVRshape);

	LoadShapeObj getCVRShapeObj();

	void setCVRShapeObj(LoadShapeObj cVRShapeObj);

	int getLoadModel();

	void setLoadModel(int loadModel);

	double getPuMean();

	double getPuStdDev();

	double getCVRwattFactor();

	double getCVRvarFactor();

	double getVmaxpu();

	double getVminEmerg();

	double getVminNormal();

	double getVminpu();

	boolean isExemptFromLDCurve();

	boolean isFixed();

	boolean getUnserved();

	boolean getExceedsNormal();

	/* Allocate load from connected kva or kWh billing */
	void setkVAAllocationFactor(double Value);

	double getkVAAllocationFactor();

	void setConnectedkVA(double Value);

	double getConnectedkVA();

	/** Set kWh properties ... */

	void setCFactor(double Value);

	double getCFactor();

	void setKWh(double Value);

	double getKWh();

	void setKWhDays(double Value);

	double getKWhDays();

	/* AllocationFactor adjusts either connected kVA allocation factor
	 * or kWh CFactor
	 */
	void setAllocationFactor(double Value);

	double getAllocationFactor();

	void recalcElementData();

	void calcYPrim();

	int injCurrents();

	void getInjCurrents(Complex[] Curr);

	void initHarmonics();

	/* Make a positive Sequence Model */
	void makePosSequence();

	void setNominalLoad();

	/*
	 * 0 = reset to 1.0
	 * 1 = Gaussian around mean and std Dev
	 * 2 = uniform
	 */
	void randomize(int Opt);

	String getPropertyValue(int Index);

	void initPropertyValues(int ArrayOffset);

	void dumpProperties(PrintStream F, boolean Complete);

	void updateVoltageBases();
	
	// FIXME Private members in OpenDSS.

	public boolean isPFChanged();

	public void setPFChanged(boolean pFChanged);

	public double getkWh();

	public void setkWh(double kWh);

	public double getkWhDays();

	public void setkWhDays(double kWhDays);

	public double getAvgkW();

	public void setAvgkW(double avgkW);

	public Complex getHarmAng();

	public void setHarmAng(Complex harmAng);

	public Complex getHarmMag();

	public void setHarmMag(Complex harmMag);

	public double getLastGrowthFactor();

	public void setLastGrowthFactor(double lastGrowthFactor);

	public int getLastYear();

	public void setLastYear(int lastYear);

	public double getLoadFundamental();

	public void setLoadFundamental(double loadFundamental);

	public int getLoadSolutionCount();

	public void setLoadSolutionCount(int loadSolutionCount);

	public int getOpenLoadSolutionCount();

	public void setOpenLoadSolutionCount(int openLoadSolutionCount);

	public double getRandomMult();

	public void setRandomMult(double randomMult);

	public Complex getShapeFactor();

	public void setShapeFactor(Complex shapeFactor);

	public double getVarBase();

	public void setVarBase(double varBase);

	public double getVarNominal();

	public void setVarNominal(double varNominal);

	public double getVBase();

	public void setVBase(double vBase);

	public double getVBase105();

	public void setVBase105(double vBase105);

	public double getVBase95();

	public void setVBase95(double vBase95);

	public double getWNominal();

	public void setWNominal(double wNominal);

	public Complex getYeq();

	public void setYeq(Complex yeq);

	public Complex getYeq105();

	public void setYeq105(Complex yeq105);

	public Complex getYeq95();

	public void setYeq95(Complex yeq95);

	public Complex getYneut();

	public void setYneut(Complex yneut);

	public CMatrix getYPrimOpenCond();

	public void setYPrimOpenCond(CMatrix yPrimOpenCond);

	public double getYQFixed();

	public void setYQFixed(double yQFixed);

	public void setPuMean(double puMean);

	public void setPuStdDev(double puStdDev);

	public void setCVRwattFactor(double cVRwattFactor);

	public void setCVRvarFactor(double cVRvarFactor);

	public void setVmaxpu(double vmaxpu);

	public void setVminEmerg(double vminEmerg);

	public void setVminNormal(double vminNormal);

	public void setVminpu(double vminpu);

	public void setExemptFromLDCurve(boolean exemptFromLDCurve);

	public void setFixed(boolean fixed);
}
