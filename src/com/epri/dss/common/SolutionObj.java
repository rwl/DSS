package com.epri.dss.common;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.general.DSSObject;

public interface SolutionObj extends DSSObject {

	public void setFrequency(double Value);

	public double getFrequency();

	public void setMode(int Value);

	public int getMode();

	public void setYear(int Value);

	public int getYear();

	public int getAlgorithm();

	public void setAlgorithm(int algorithm);

	public DComplexMatrix1D getAuxCurrents();

	public void setAuxCurrents(DComplexMatrix1D auxCurrents);

	public boolean isControlActionsDone();

	public void setControlActionsDone(boolean controlActionsDone);

	public int getControlIteration();

	public void setControlIteration(int controlIteration);

	public int getControlMode();

	public void setControlMode(int controlMode);

	public double getConvergenceTolerance();

	public void setConvergenceTolerance(double convergenceTolerance);

	public boolean isConvergedFlag();

	public void setConvergedFlag(boolean convergedFlag);

	public int getDefaultControlMode();

	public void setDefaultControlMode(int defaultControlMode);

	public int getDefaultLoadModel();

	public void setDefaultLoadModel(int defaultLoadModel);

	public boolean isDoAllHarmonics();

	public void setDoAllHarmonics(boolean doAllHarmonics);

	public boolean isDynamicsAllowed();

	public void setDynamicsAllowed(boolean dynamicsAllowed);

	public DynamicsRec getDynaVars();

	public void setDynaVars(DynamicsRec dynaVars);

	public double[] getErrorSaved();

	public void setErrorSaved(double[] errorSaved);

	public boolean isFirstIteration();

	public void setFirstIteration(boolean firstIteration);

	public boolean isFrequencyChanged();

	public void setFrequencyChanged(boolean frequencyChanged);

	public double getHarmonic();

	public void setHarmonic(double harmonic);

	public double[] getHarmonicList();

	public void setHarmonicList(double[] harmonicList);

	public int getHarmonicListSize();

	public void setHarmonicListSize(int harmonicListSize);

	public int getIntHour();

	public void setIntHour(int intHour);

	public double getDblHour();

	public void setDblHour(double dblHour);

	public DComplexMatrix2D getYsystem();

	public void setYsystem(DComplexMatrix2D ysystem);

	public DComplexMatrix2D getYseries();

	public void setYseries(DComplexMatrix2D yseries);

	public DComplexMatrix2D getY();

	public void setY(DComplexMatrix2D y);

	public double getIntervalHrs();

	public void setIntervalHrs(double intervalHrs);

	public boolean isIsDynamicModel();

	public void setIsDynamicModel(boolean isDynamicModel);

	public boolean isIsHarmonicModel();

	public void setIsHarmonicModel(boolean isHarmonicModel);

	public int getIteration();

	public void setIteration(int iteration);

	public int getLoadModel();

	public void setLoadModel(int loadModel);

	public boolean isLastSolutionWasDirect();

	public void setLastSolutionWasDirect(boolean lastSolutionWasDirect);

	public boolean isLoadsNeedUpdating();

	public void setLoadsNeedUpdating(boolean loadsNeedUpdating);

	public int getMaxControlIterations();

	public void setMaxControlIterations(int maxControlIterations);

	public double getMaxError();

	public void setMaxError(double maxError);

	public int getMaxIterations();

	public void setMaxIterations(int maxIterations);

	public int getMostIterationsDone();

	public void setMostIterationsDone(int mostIterationsDone);

	public double[] getNodeVbase();

	public void setNodeVbase(double[] nodeVbase);

	public int getNumberOfTimes();

	public void setNumberOfTimes(int numberOfTimes);

	public boolean isPreserveNodeVoltages();

	public void setPreserveNodeVoltages(boolean preserveNodeVoltages);

	public int getRandomType();

	public void setRandomType(int randomType);

	public boolean isSeriesYInvalid();

	public void setSeriesYInvalid(boolean seriesYInvalid);

	public int getSolutionCount();

	public void setSolutionCount(int solutionCount);

	public boolean isSolutionInitialized();

	public void setSolutionInitialized(boolean solutionInitialized);

	public boolean isSystemYChanged();

	public void setSystemYChanged(boolean systemYChanged);

	public boolean isUseAuxCurrents();

	public void setUseAuxCurrents(boolean useAuxCurrents);

	public double[] getVmagSaved();

	public void setVmagSaved(double[] vmagSaved);

	public boolean isVoltageBaseChanged();

	public void setVoltageBaseChanged(boolean voltageBaseChanged);

	public DComplexMatrix1D getNodeV();

	public void setNodeV(DComplexMatrix1D nodeV);

	public DComplexMatrix1D getCurrents();

	public void setCurrents(DComplexMatrix1D currents);

	public void zeroAuxCurrents();

	public int solveZeroLoadSnapShot();

	public void doPFLOWsolution();

	/* Main Solution dispatch */
	public void solve();

	public void snapShotInit();

	/* solve for now once */
	public int solveSnap();

	/* solve for now once, direct solution */
	public int solveDirect();

	/* Similar to SolveDirect; used for initialization */
	public int solveYDirect();

	/* SolveSnap sans control iteration */
	public int solveCircuit();

	/* Snapshot checks with matrix rebuild */
	public void checkControls();

	public void sampleControlDevices();

	public void doControlActions();

	/* Sample and Do */
	public void sample_DoControlActions();

	public void checkFaultStatus();

	public void setGeneratorDispRef();

	public void setVoltageBases();

	public void saveVoltages();

	/* Updates voltages for each bus from NodeV */
	public void updateVBus();

	/* opposite of updateVBus() */
	public void restoreNodeVfromVbus();

	/* Difference between two node voltages */
	public double[] vDiff(int i, int j);

	public void initPropertyValues(int ArrayOffset);

	public void dumpProperties(PrintStream F, boolean Complete);

	public void writeConvergenceReport(String fName);

	public void updateDblHour();

	public void incrementTime();
}
