package com.epri.dss.common.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.DynamicsImpl.DynamicsRec;

public class SolutionObjImpl extends DSSObjectImpl implements SolutionObj {

	/* Array of delta V for Newton iteration */
	private Complex[] dV;
	private double Frequency;

	protected int Algorithm;      // NORMALSOLVE or NEWTONSOLVE
	protected Complex[] AuxCurrents;  // For injections like AutoAdd
	protected boolean ControlActionsDone;
	protected int ControlIteration;
	protected int ControlMode;     // EVENTDRIVEN, TIMEDRIVEN
	protected double ConvergenceTolerance;
	protected boolean ConvergedFlag;
	protected int DefaultControlMode;    // EVENTDRIVEN, TIMEDRIVEN
	protected int DefaultLoadModel;     // 1=POWERFLOW  2=ADMITTANCE
	protected boolean DoAllHarmonics;
	protected boolean DynamicsAllowed;
	protected DynamicsRec DynaVars;
	protected double[] ErrorSaved;
	protected boolean FirstIteration;
	/* Flag set to true if something has altered the frequency */
	protected boolean FrequencyChanged;
	protected int year;
	protected double Harmonic;
	protected double[] HarmonicList;
	protected int HarmonicListSize;
	protected int intHour;
	protected double dblHour;
	/* Main (system) Y matrix */
	protected CMatrix Ysystem;
	/* Series Y matrix */
	protected CMatrix Yseries;
	/* either Ysystem or Yseries */
	protected CMatrix Y;
	protected double IntervalHrs;   // Solution interval since last solution, hrs.
	protected boolean IsDynamicModel;
	protected boolean IsHarmonicModel;
	protected int Iteration;
	protected int LoadModel;        // 1=POWERFLOW  2=ADMITTANCE
	protected boolean LastSolutionWasDirect;
	protected boolean LoadsNeedUpdating;
	protected int MaxControlIterations;
	protected double MaxError;
	protected int MaxIterations;
	protected int MostIterationsDone;
	protected double[] NodeVbase;
	protected int NumberOfTimes;  // Number of times to solve
	protected boolean PreserveNodeVoltages;
	protected int RandomType;     //0 = none; 1 = gaussian; 2 = UNIFORM
	protected boolean SeriesYInvalid;
	protected int SolutionCount;  // Counter incremented for each solution
	protected boolean SolutionInitialized;
	protected boolean SystemYChanged;
	protected boolean UseAuxCurrents;
	protected double[] VmagSaved;
	protected boolean VoltageBaseChanged;

	/* Main System Voltage Array */
	protected Complex[] NodeV;
	/* Main System Currents Array */
	protected Complex[] Currents;

	public SolutionObjImpl(DSSClass parClass, String solutionName) {
		super(parClass);
		// TODO Auto-generated constructor stub
	}

	private boolean converged() {
		return false;
	}

	private boolean oKForDynamics(int Value) {
		return false;
	}

	private boolean oKForHarmonics(int Value) {
		return false;
	}

	private int SolveSystem(Complex[] V) {
		return 0;
	}

	private void addInAuxCurrents(int SolveType) {

	}

	private void doNewtonSolution() {

	}

	private void doNormalSolution() {

	}

	private void getMachineInjCurrents() {

	}

	private void getPCInjCurr() {

	}

	private void getSourceInjCurrents() {

	}

	public void setFrequency(double Value) {

	}

	public double getFrequency() {
		return Frequency;
	}

	public void setMode(int Value) {

	}

	public int getMode() {
		return DynaVars.SolutionMode;
	}

	public void setYear(int Value) {

	}

	public int getYear() {
		return year;
	}

	private void setGeneratordQdV() {

	}

	private void sumAllCurrents() {

	}

	private void zeroInjCurr() {

	}


	public int getAlgorithm() {
		return Algorithm;
	}

	public void setAlgorithm(int algorithm) {
		Algorithm = algorithm;
	}

	public Complex[] getAuxCurrents() {
		return AuxCurrents;
	}

	public void setAuxCurrents(Complex[] auxCurrents) {
		AuxCurrents = auxCurrents;
	}

	public boolean isControlActionsDone() {
		return ControlActionsDone;
	}

	public void setControlActionsDone(boolean controlActionsDone) {
		ControlActionsDone = controlActionsDone;
	}

	public int getControlIteration() {
		return ControlIteration;
	}

	public void setControlIteration(int controlIteration) {
		ControlIteration = controlIteration;
	}

	public int getControlMode() {
		return ControlMode;
	}

	public void setControlMode(int controlMode) {
		ControlMode = controlMode;
	}

	public double getConvergenceTolerance() {
		return ConvergenceTolerance;
	}

	public void setConvergenceTolerance(double convergenceTolerance) {
		ConvergenceTolerance = convergenceTolerance;
	}

	public boolean isConvergedFlag() {
		return ConvergedFlag;
	}

	public void setConvergedFlag(boolean convergedFlag) {
		ConvergedFlag = convergedFlag;
	}

	public int getDefaultControlMode() {
		return DefaultControlMode;
	}

	public void setDefaultControlMode(int defaultControlMode) {
		DefaultControlMode = defaultControlMode;
	}

	public int getDefaultLoadModel() {
		return DefaultLoadModel;
	}

	public void setDefaultLoadModel(int defaultLoadModel) {
		DefaultLoadModel = defaultLoadModel;
	}

	public boolean isDoAllHarmonics() {
		return DoAllHarmonics;
	}

	public void setDoAllHarmonics(boolean doAllHarmonics) {
		DoAllHarmonics = doAllHarmonics;
	}

	public boolean isDynamicsAllowed() {
		return DynamicsAllowed;
	}

	public void setDynamicsAllowed(boolean dynamicsAllowed) {
		DynamicsAllowed = dynamicsAllowed;
	}

	public DynamicsRec getDynaVars() {
		return DynaVars;
	}

	public void setDynaVars(DynamicsRec dynaVars) {
		DynaVars = dynaVars;
	}

	public double[] getErrorSaved() {
		return ErrorSaved;
	}

	public void setErrorSaved(double[] errorSaved) {
		ErrorSaved = errorSaved;
	}

	public boolean isFirstIteration() {
		return FirstIteration;
	}

	public void setFirstIteration(boolean firstIteration) {
		FirstIteration = firstIteration;
	}

	public boolean isFrequencyChanged() {
		return FrequencyChanged;
	}

	public void setFrequencyChanged(boolean frequencyChanged) {
		FrequencyChanged = frequencyChanged;
	}

	public double getHarmonic() {
		return Harmonic;
	}

	public void setHarmonic(double harmonic) {
		Harmonic = harmonic;
	}

	public double[] getHarmonicList() {
		return HarmonicList;
	}

	public void setHarmonicList(double[] harmonicList) {
		HarmonicList = harmonicList;
	}

	public int getHarmonicListSize() {
		return HarmonicListSize;
	}

	public void setHarmonicListSize(int harmonicListSize) {
		HarmonicListSize = harmonicListSize;
	}

	public int getIntHour() {
		return intHour;
	}

	public void setIntHour(int intHour) {
		this.intHour = intHour;
	}

	public double getDblHour() {
		return dblHour;
	}

	public void setDblHour(double dblHour) {
		this.dblHour = dblHour;
	}

	public CMatrix getYsystem() {
		return Ysystem;
	}

	public void setYsystem(CMatrix ysystem) {
		Ysystem = ysystem;
	}

	public CMatrix getYseries() {
		return Yseries;
	}

	public void setYseries(CMatrix yseries) {
		Yseries = yseries;
	}

	public CMatrix getY() {
		return Y;
	}

	public void setY(CMatrix y) {
		Y = y;
	}

	public double getIntervalHrs() {
		return IntervalHrs;
	}

	public void setIntervalHrs(double intervalHrs) {
		IntervalHrs = intervalHrs;
	}

	public boolean isIsDynamicModel() {
		return IsDynamicModel;
	}

	public void setIsDynamicModel(boolean isDynamicModel) {
		IsDynamicModel = isDynamicModel;
	}

	public boolean isIsHarmonicModel() {
		return IsHarmonicModel;
	}

	public void setIsHarmonicModel(boolean isHarmonicModel) {
		IsHarmonicModel = isHarmonicModel;
	}

	public int getIteration() {
		return Iteration;
	}

	public void setIteration(int iteration) {
		Iteration = iteration;
	}

	public int getLoadModel() {
		return LoadModel;
	}

	public void setLoadModel(int loadModel) {
		LoadModel = loadModel;
	}

	public boolean isLastSolutionWasDirect() {
		return LastSolutionWasDirect;
	}

	public void setLastSolutionWasDirect(boolean lastSolutionWasDirect) {
		LastSolutionWasDirect = lastSolutionWasDirect;
	}

	public boolean isLoadsNeedUpdating() {
		return LoadsNeedUpdating;
	}

	public void setLoadsNeedUpdating(boolean loadsNeedUpdating) {
		LoadsNeedUpdating = loadsNeedUpdating;
	}

	public int getMaxControlIterations() {
		return MaxControlIterations;
	}

	public void setMaxControlIterations(int maxControlIterations) {
		MaxControlIterations = maxControlIterations;
	}

	public double getMaxError() {
		return MaxError;
	}

	public void setMaxError(double maxError) {
		MaxError = maxError;
	}

	public int getMaxIterations() {
		return MaxIterations;
	}

	public void setMaxIterations(int maxIterations) {
		MaxIterations = maxIterations;
	}

	public int getMostIterationsDone() {
		return MostIterationsDone;
	}

	public void setMostIterationsDone(int mostIterationsDone) {
		MostIterationsDone = mostIterationsDone;
	}

	public double[] getNodeVbase() {
		return NodeVbase;
	}

	public void setNodeVbase(double[] nodeVbase) {
		NodeVbase = nodeVbase;
	}

	public int getNumberOfTimes() {
		return NumberOfTimes;
	}

	public void setNumberOfTimes(int numberOfTimes) {
		NumberOfTimes = numberOfTimes;
	}

	public boolean isPreserveNodeVoltages() {
		return PreserveNodeVoltages;
	}

	public void setPreserveNodeVoltages(boolean preserveNodeVoltages) {
		PreserveNodeVoltages = preserveNodeVoltages;
	}

	public int getRandomType() {
		return RandomType;
	}

	public void setRandomType(int randomType) {
		RandomType = randomType;
	}

	public boolean isSeriesYInvalid() {
		return SeriesYInvalid;
	}

	public void setSeriesYInvalid(boolean seriesYInvalid) {
		SeriesYInvalid = seriesYInvalid;
	}

	public int getSolutionCount() {
		return SolutionCount;
	}

	public void setSolutionCount(int solutionCount) {
		SolutionCount = solutionCount;
	}

	public boolean isSolutionInitialized() {
		return SolutionInitialized;
	}

	public void setSolutionInitialized(boolean solutionInitialized) {
		SolutionInitialized = solutionInitialized;
	}

	public boolean isSystemYChanged() {
		return SystemYChanged;
	}

	public void setSystemYChanged(boolean systemYChanged) {
		SystemYChanged = systemYChanged;
	}

	public boolean isUseAuxCurrents() {
		return UseAuxCurrents;
	}

	public void setUseAuxCurrents(boolean useAuxCurrents) {
		UseAuxCurrents = useAuxCurrents;
	}

	public double[] getVmagSaved() {
		return VmagSaved;
	}

	public void setVmagSaved(double[] vmagSaved) {
		VmagSaved = vmagSaved;
	}

	public boolean isVoltageBaseChanged() {
		return VoltageBaseChanged;
	}

	public void setVoltageBaseChanged(boolean voltageBaseChanged) {
		VoltageBaseChanged = voltageBaseChanged;
	}

	public Complex[] getNodeV() {
		return NodeV;
	}

	public void setNodeV(Complex[] nodeV) {
		NodeV = nodeV;
	}

	public Complex[] getCurrents() {
		return Currents;
	}

	public void setCurrents(Complex[] currents) {
		Currents = currents;
	}

	public void zeroAuxCurrents() {

	}

	public int solveZeroLoadSnapShot() {
		return 0;
	}

	public void doPFLOWsolution() {

	}

	/* Main Solution dispatch */
	public void solve() {

	}

	public void snapShotInit() {

	}

	/* solve for now once */
	public int solveSnap() {
		return 0;
	}

	/* solve for now once, direct solution */
	public int solveDirect() {
		return 0;
	}

	/* Similar to SolveDirect; used for initialization */
	public int solveYDirect() {
		return 0;
	}

	/* SolveSnap sans control iteration */
	public int solveCircuit() {
		return 0;
	}

	/* Snapshot checks with matrix rebuild */
	public void checkControls() {

	}

	public void sampleControlDevices() {

	}

	public void doControlActions() {

	}

	/* Sample and Do */
	public void sample_DoControlActions() {

	}

	public void checkFaultStatus() {

	}

	public void setGeneratorDispRef() {

	}

	public void setVoltageBases() {

	}

	public void saveVoltages() {

	}

	/* Updates voltages for each bus from NodeV */
	public void updateVBus() {

	}

	/* opposite of updateVBus() */
	public void restoreNodeVfromVbus() {

	}

	/* Difference between two node voltages */
	public double[] vDiff(int i, int j) {
		return null;
	}

	public void initPropertyValues(int ArrayOffset) {

	}

	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	public void writeConvergenceReport(String fName) {

	}

	public void updateDblHour() {

	}

	public void incrementTime() {

	}

}
