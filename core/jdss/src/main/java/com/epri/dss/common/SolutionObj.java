package com.epri.dss.common;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;
import com.epri.dss.shared.impl.DynamicsRec;

import com.epri.dss.common.impl.ControlProblem;
import com.epri.dss.common.impl.Esolv32Problem;
import com.epri.dss.common.impl.SolverError;
import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.CMatrix;

public interface SolutionObj extends DSSObject {

	void setFrequency(double value);

	double getFrequency();

	void setMode(int value);

	int getMode();

	void setYear(int alue);

	int getYear();

	int getAlgorithm();

	void setAlgorithm(int algorithm);

	Complex[] getAuxCurrents();

	void setAuxCurrents(Complex[] auxCurrents);

	boolean isControlActionsDone();

	void setControlActionsDone(boolean controlActionsDone);

	int getControlIteration();

	void setControlIteration(int controlIteration);

	int getControlMode();

	void setControlMode(int controlMode);

	double getConvergenceTolerance();

	void setConvergenceTolerance(double convergenceTolerance);

	boolean isConvergedFlag();

	void setConvergedFlag(boolean convergedFlag);

	int getDefaultControlMode();

	void setDefaultControlMode(int defaultControlMode);

	int getDefaultLoadModel();

	void setDefaultLoadModel(int defaultLoadModel);

	boolean isDoAllHarmonics();

	void setDoAllHarmonics(boolean doAllHarmonics);

	boolean isDynamicsAllowed();

	void setDynamicsAllowed(boolean dynamicsAllowed);

	DynamicsRec getDynaVars();

	void setDynaVars(DynamicsRec dynaVars);

	double[] getErrorSaved();

	void setErrorSaved(double[] errorSaved);

	boolean isFirstIteration();

	void setFirstIteration(boolean firstIteration);

	boolean isFrequencyChanged();

	void setFrequencyChanged(boolean frequencyChanged);

	double getHarmonic();

	void setHarmonic(double harmonic);

	double[] getHarmonicList();

	void setHarmonicList(double[] harmonicList);

	int getHarmonicListSize();

	void setHarmonicListSize(int harmonicListSize);

	int getIntHour();

	void setIntHour(int intHour);

	double getDblHour();

	void setDblHour(double dblHour);

	CMatrix getYSystem();

	void setYSystem(CMatrix ySystem);

	CMatrix getYSeries();

	void setYSeries(CMatrix ySeries);

	CMatrix getY();

	void setY(CMatrix y);

	double getIntervalHrs();

	void setIntervalHrs(double intervalHrs);

	boolean isDynamicModel();

	void setDynamicModel(boolean isDynamicModel);

	boolean isHarmonicModel();

	void setHarmonicModel(boolean isHarmonicModel);

	int getIteration();

	void setIteration(int iteration);

	int getLoadModel();

	void setLoadModel(int loadModel);

	boolean lastSolutionWasDirect();

	void setLastSolutionWasDirect(boolean lastSolutionWasDirect);

	boolean loadsNeedUpdating();

	void setLoadsNeedUpdating(boolean loadsNeedUpdating);

	int getMaxControlIterations();

	void setMaxControlIterations(int maxControlIterations);

	double getMaxError();

	void setMaxError(double maxError);

	int getMaxIterations();

	void setMaxIterations(int maxIterations);

	int getMostIterationsDone();

	void setMostIterationsDone(int mostIterationsDone);

	double[] getNodeVBase();

	void setNodeVBase(double[] nodeVbase);

	int getNumberOfTimes();

	void setNumberOfTimes(int numberOfTimes);

	boolean isPreserveNodeVoltages();

	void setPreserveNodeVoltages(boolean preserveNodeVoltages);

	int getRandomType();

	void setRandomType(int randomType);

	boolean isSeriesYInvalid();

	void setSeriesYInvalid(boolean seriesYInvalid);

	int getSolutionCount();

	void setSolutionCount(int solutionCount);

	boolean isSolutionInitialized();

	void setSolutionInitialized(boolean solutionInitialized);

	boolean isSystemYChanged();

	void setSystemYChanged(boolean systemYChanged);

	boolean useAuxCurrents();

	void setUseAuxCurrents(boolean useAuxCurrents);

	double[] getVMagSaved();

	void setVMagSaved(double[] vmagSaved);

	boolean isVoltageBaseChanged();

	void setVoltageBaseChanged(boolean voltageBaseChanged);

	Complex[] getNodeV();

	void setNodeV(Complex[] nodeV);

	Complex[] getCurrents();

	void setCurrents(Complex[] currents);

	Complex getCurrent(int idx);

	void setCurrent(int idx, Complex current);

	void zeroAuxCurrents();

	int solveZeroLoadSnapShot() throws Esolv32Problem;

	void doPFlowSolution() throws SolverError, Esolv32Problem;

	/** Main solution dispatch */
	void solve();

	void snapShotInit();

	/** Solve for now once */
	int solveSnap() throws SolverError, ControlProblem, Esolv32Problem;

	/** Solve for now once, direct solution */
	int solveDirect() throws Esolv32Problem;

	/** Similar to solveDirect; used for initialization */
	int solveYDirect() throws Esolv32Problem;

	/** SolveSnap sans control iteration */
	int solveCircuit() throws SolverError;

	/** Snapshot checks with matrix rebuild */
	void checkControls() throws ControlProblem, Esolv32Problem;

	void sampleControlDevices() throws ControlProblem;

	void doControlActions();

	/** Sample and do */
	void sampleDoControlActions() throws ControlProblem;

	void checkFaultStatus();

	void setGeneratorDispRef();

	void setVoltageBases() throws SolverError;

	void saveVoltages();

	/** Updates voltages for each bus from nodeV */
	void updateVBus();

	/** opposite of updateVBus() */
	void restoreNodeVFromVbus();

	/** Difference between two node voltages */
	Complex vDiff(int i, int j);

	void initPropertyValues(int ArrayOffset);

	void dumpProperties(PrintStream F, boolean Complete);

	void writeConvergenceReport(String fName);

	void updateDblHour();

	void incrementTime();

}
