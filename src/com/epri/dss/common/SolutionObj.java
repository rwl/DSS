package com.epri.dss.common;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.impl.DynamicsImpl.DynamicsRec;

public interface SolutionObj extends DSSObject {

	void setFrequency(double Value);

	double getFrequency();

	void setMode(int Value);

	int getMode();

	void setYear(int Value);

	int getYear();

	int getAlgorithm();

	void setAlgorithm(int algorithm);

	DComplexMatrix1D getAuxCurrents();

	void setAuxCurrents(DComplexMatrix1D auxCurrents);

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

	DComplexMatrix2D getYsystem();

	void setYsystem(DComplexMatrix2D ysystem);

	DComplexMatrix2D getYseries();

	void setYseries(DComplexMatrix2D yseries);

	DComplexMatrix2D getY();

	void setY(DComplexMatrix2D y);

	double getIntervalHrs();

	void setIntervalHrs(double intervalHrs);

	boolean isIsDynamicModel();

	void setIsDynamicModel(boolean isDynamicModel);

	boolean isIsHarmonicModel();

	void setIsHarmonicModel(boolean isHarmonicModel);

	int getIteration();

	void setIteration(int iteration);

	int getLoadModel();

	void setLoadModel(int loadModel);

	boolean isLastSolutionWasDirect();

	void setLastSolutionWasDirect(boolean lastSolutionWasDirect);

	boolean isLoadsNeedUpdating();

	void setLoadsNeedUpdating(boolean loadsNeedUpdating);

	int getMaxControlIterations();

	void setMaxControlIterations(int maxControlIterations);

	double getMaxError();

	void setMaxError(double maxError);

	int getMaxIterations();

	void setMaxIterations(int maxIterations);

	int getMostIterationsDone();

	void setMostIterationsDone(int mostIterationsDone);

	double[] getNodeVbase();

	void setNodeVbase(double[] nodeVbase);

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

	boolean isUseAuxCurrents();

	void setUseAuxCurrents(boolean useAuxCurrents);

	double[] getVmagSaved();

	void setVmagSaved(double[] vmagSaved);

	boolean isVoltageBaseChanged();

	void setVoltageBaseChanged(boolean voltageBaseChanged);

	DComplexMatrix1D getNodeV();

	void setNodeV(DComplexMatrix1D nodeV);

	DComplexMatrix1D getCurrents();

	void setCurrents(DComplexMatrix1D currents);

	void zeroAuxCurrents();

	int solveZeroLoadSnapShot();

	void doPFLOWsolution();

	/* Main Solution dispatch */
	void solve();

	void snapShotInit();

	/* solve for now once */
	int solveSnap();

	/* solve for now once, direct solution */
	int solveDirect();

	/* Similar to SolveDirect; used for initialization */
	int solveYDirect();

	/* SolveSnap sans control iteration */
	int solveCircuit();

	/* Snapshot checks with matrix rebuild */
	void checkControls();

	void sampleControlDevices();

	void doControlActions();

	/* Sample and Do */
	void sample_DoControlActions();

	void checkFaultStatus();

	void setGeneratorDispRef();

	void setVoltageBases();

	void saveVoltages();

	/* Updates voltages for each bus from NodeV */
	void updateVBus();

	/* opposite of updateVBus() */
	void restoreNodeVfromVbus();

	/* Difference between two node voltages */
	double[] vDiff(int i, int j);

	void initPropertyValues(int ArrayOffset);

	void dumpProperties(PrintStream F, boolean Complete);

	void writeConvergenceReport(String fName);

	void updateDblHour();

	void incrementTime();
}
