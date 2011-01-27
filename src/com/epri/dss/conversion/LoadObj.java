package com.epri.dss.conversion;

import java.io.PrintStream;

import com.epri.dss.general.GrowthShapeObj;
import com.epri.dss.general.LoadShapeObj;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

public interface LoadObj extends PCElement {

	public int getConnection();

	public void setConnection(int connection);

	public String getDailyShape();

	public void setDailyShape(String dailyShape);

	public LoadShapeObj getDailyShapeObj();

	public void setDailyShapeObj(LoadShapeObj dailyShapeObj);

	public String getDutyShape();

	public void setDutyShape(String dutyShape);

	public LoadShapeObj getDutyShapeObj();

	public void setDutyShapeObj(LoadShapeObj dutyShapeObj);

	public double getEEN_Factor();

	public void setEEN_Factor(double eEN_Factor);

	public String getGrowthShape();

	public void setGrowthShape(String growthShape);

	public GrowthShapeObj getGrowthShapeObj();

	public void setGrowthShapeObj(GrowthShapeObj growthShapeObj);

	public Boolean getHasBeenAllocated();

	public void setHasBeenAllocated(Boolean hasBeenAllocated);

	public double getkWBase();

	public void setkWBase(double kWBase);

	public double getkVABase();

	public void setkVABase(double kVABase);

	public double getKvarBase();

	public void setKvarBase(double kvarBase);

	public double getkVLoadBase();

	public void setkVLoadBase(double kVLoadBase);

	public int getLoadClass();

	public void setLoadClass(int loadClass);

	public int getNumCustomers();

	public void setNumCustomers(int numCustomers);

	public int getLoadSpecType();

	public void setLoadSpecType(int loadSpecType);

	public double getPFNominal();

	public void setPFNominal(double pFNominal);

	public double getRneut();

	public void setRneut(double rneut);

	public double getUE_Factor();

	public void setUE_Factor(double uE_Factor);

	public double getXneut();

	public void setXneut(double xneut);

	public String getYearlyShape();

	public void setYearlyShape(String yearlyShape);

	public LoadShapeObj getYearlyShapeObj();

	public void setYearlyShapeObj(LoadShapeObj yearlyShapeObj);

	public String getCVRshape();

	public void setCVRshape(String cVRshape);

	public LoadShapeObj getCVRShapeObj();

	public void setCVRShapeObj(LoadShapeObj cVRShapeObj);

	public int getLoadModel();

	public void setLoadModel(int loadModel);

	public double getPuMean();

	public double getPuStdDev();

	public double getCVRwattFactor();

	public double getCVRvarFactor();

	public double getVmaxpu();

	public double getVminEmerg();

	public double getVminNormal();

	public double getVminpu();

	public boolean isExemptFromLDCurve();

	public boolean isFixed();

	public boolean getUnserved();

	public boolean getExceedsNormal();

	/* Allocate load from connected kva or kWh billing */
	public void setkVAAllocationFactor(double Value);

	public double getkVAAllocationFactor();

	public void setConnectedkVA(double Value);

	public double getConnectedkVA();

	/** Set kWh properties ... */

	public void setCFactor(double Value);

	public double getCFactor();

	public void setKWh(double Value);

	public double getKWh();

	public void setKWhDays(double Value);

	public double getKWhDays();

	/* AllocationFactor adjusts either connected kVA allocation factor
	 * or kWh CFactor
	 */
	public void setAllocationFactor(double Value);

	public double getAllocationFactor();

	public void recalcElementData();

	public void calcYPrim();

	public int injCurrents();

	public void getInjCurrents(DComplexMatrix1D Curr);

	public void initHarmonics();

	/* Make a positive Sequence Model */
	public void makePosSequence();

	public void setNominalLoad();

	/*
	 * 0 = reset to 1.0
	 * 1 = Gaussian around mean and std Dev
	 * 2 = uniform
	 */
	public void randomize(int Opt);

	public String getPropertyValue(int Index);

	public void initPropertyValues(int ArrayOffset);

	public void dumpProperties(PrintStream F, boolean Complete);

	public void updateVoltageBases();
}
