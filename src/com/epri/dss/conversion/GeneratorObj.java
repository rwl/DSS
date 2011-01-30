package com.epri.dss.conversion;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

import com.epri.dss.general.LoadShapeObj;


/**
 * The generator is essentially a negative load that can be dispatched.
 * 
 * If the dispatch value (DispValue) is 0, the generator always follows the
 * appropriate dispatch curve, which are simply load curves. If DispValue>0
 * then the generator only comes on when the global circuit load multiplier
 * exceeds DispValue.  When the generator is on, it always follows the dispatch
 * curve appropriate for the type of solution being performed.
 * 
 * If you want to model a generator that is fully on whenever it is dispatched
 * on, simply designate "Status=Fixed".  The default is "Status=Variable"
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
 *   Generator meters reset with the circuit energy meters and take a sample
 *   with the circuit energy meters as well. The Energy meters also used
 *   trapezoidal integration so that they are compatible with Load-Duration
 *   simulations.
 *   
 *   Generator models are:
 *     1. Constant P, Q  (* dispatch curve, if appropriate).
 *     2. Constant Z  (For simple solution)
 *     3. Constant P, |V|  like a standard power flow  [not implemented]
 *     4. Constant P, Fixed Q  (vars)
 *     5. Constant P, Fixed Q  (reactance)
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

	double getPresentkW();
	
	double getPresentKVar();
	
	double getPresentKV();
	
	void setPresentKV(double Value);
	
	void setPresentKVar(double Value);
	
	void setPresentKW(double Value);
	
	boolean isForcedON();

	void setForcedON(boolean forcedON);

	void setPowerFactor(double Value);
	
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

	double getKvarBase();

	void setKvarBase(double kvarBase);

	double getKvarMax();

	void setKvarMax(double kvarMax);

	double getKvarMin();

	void setKvarMin(double kvarMin);

	double getkWBase();

	void setkWBase(double kWBase);

	double getPFNominal();

	void setPFNominal(double pFNominal);

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
	
	/* 0 = reset to 1.0; 1 = Gaussian around mean and std Dev; 2 = uniform */
	void randomize(int Opt);
	
	void resetRegisters();
	
	void takeSample();
	
	/* Procedures for setting the DQDV used by the Solution Object */
	void initDQDVCalc();
	
	void bumpUpQ();
	
	void rememberQV();
	
	void calcDQDV();
	
	void resetStartPoint();
	
	/* Make a positive Sequence Model */
	void makePosSequence();

}
