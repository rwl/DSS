package com.epri.dss.conversion;

import com.epri.dss.general.LoadShapeObj;

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

}
