package com.epri.dss.common;

import java.io.PrintStream;

import com.epri.dss.delivery.PDElement;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

public interface Utilities {

//	int compareTextShortest(String S1, String S2);
//	void fireOffEditor(String FileNm);
//	void doDOSCmd(String CmdString);
//	String stripExtension(String S);
//	/* Returns only element name sans class. */
//	String stripClassName(String S);  
//	String pad(String S, int Width);
//	String padDots(String S, int Width);
//	String padTrunc(String S, int Width);
//	String intArrayToString(int[] iarray, int count);
//	String encloseQuotes(String s);
//	void showMessageBeep(String s);
//	String fullName(CktElement pElem);
//
//	/* Parsing Utilities */
//	void parseObjectClassandName(String FullObjName, String ClassName,
//			String ObjName);
//	void parseIntArray(int[] iarray, int count, String s);
//	int interpretSolveMode(String s);
//	int interpretControlMode(String s);
//	int interpretLoadModel(String s);
//	boolean interpretYesNo(String s);
//	int interpretRandom(String s);
//	int interpretAddType(String s);
//	int interpretConnection(String s);
//	int interpretSolveAlg(String s);
//	boolean interpretCktModel(String s);
//	void initDblArray(int NumValues, double[] Xarray, double Value);
//	void initIntArray(int NumValues, int[] Xarray, int Value);
//	int interpretDblArray(String s, int MaxValues, double[] ResultArray);
//	int interpretIntArray(String s, int MaxValues, int[] ResultArray);
//	void interpretAndAllocStrArray(String s, int Size, String[] ResultArray);
//	void interpretTStringListArray(String s, String[] ResultList);
//	double interpretTimeStepSize(String s);
//	int interpretLoadShapeClass(String s);
//	int interpretEarthModel(String s);
//	int interpretColorName(String s);
//
//	String getSolutionModeID();
//	String getSolutionModeIDName(int idx);
//	String getControlModeID();
//	String getRandomModeID();
//	String getLoadModel();
//	String getActiveLoadShapeClass();
//	String getDSSArray_Real(int n, double[] dbls);
//	String getDSSArray_Integer(int n, int[] ints);
//	String getEarthModel(int n);
//
//
//	/* misc functions */
//	int doExecutiveCommand(String s);
//	int getCktElementIndex(String fullObjName);
//	boolean isShuntElement(CktElement Elem);
//	boolean isLineElement(CktElement Elem);
//	boolean isTransformerElement(CktElement Elem);
//	boolean isStubLine(CktElement Elem);
//	boolean checkParallel(CktElement Line1, CktElement Line2);
//	boolean allTerminalsClosed(CktElement thisElement);
//	String strReal(double Value, int NumDecimals);
//	void dumpAllDSSCommands(String Filename);
//	void dumpAllocationFactors(String Filename);
//	void dumpComplexMatrix(PrintStream F, DComplexMatrix2D AMatrix);
//	double nearestBasekV(double kV);
//	double presentTimeInSec();
//	int doResetFaults();
//	int doResetControls();
//	void doResetKeepList();
//	int getNodeNum(int NodeRef);
//	void initStringToNull(String S);
//	/* Multiply only imaginary part by a real */
//	double[] CmulReal_im(double[] a, double Mult);  
//	//boolean isValidNumericField(Edit NumberField);
//	double maxDblArrayValue(int npts, double[] dbls);
//	int iMaxAbsdblArrayValue(int npts, double[] dbls);
//
//
//	/* Save Function Helper */
//	boolean writeClassFile(DSSClass DSS_Class, String FileName,
//			boolean isCktElement);
//	boolean writeVsourceClassFile(DSSClass DSS_Class, boolean isCktElement);
//	void writeActiveDSSObject(PrintStream F, String NewOrEdit);
//	String checkForBlanks(String S);
//	boolean rewriteAlignedFile(String FileName);
//
//	/* Event Log */
//	void ClearEventLog();
//	void appendToEventLog(String opdev, String action);
//	void logThisEvent(String EventName);
//
//	/* Routines for doing common things to complex numbers */
//	void rotatePhasorDeg(double[] Phasor, double h, double AngleDeg);
//	void rotatePhasorRad(double[] Phasor, double h, double AngleRad);
//	void convertComplexArrayToPolar(DComplexMatrix1D Buffer, int N);
//	void convertComplexArrayToPowerandPF(DComplexMatrix1D Buffer, int N);
//	double[] residual(Object p, int Nph);
//	double[] residualPolar(Object p, int Nph);
//	double powerFactor(double[] S);
//	double convertPFToPFRange2(double value);
//	double convertPFRange2ToPF(double value);
//	/* Multiply a complex array times a double */
//	void CmulArray(DComplexMatrix1D pc, double Multiplier, int size);  
//
//	/* Support for going in and out of Dynamics Mode and Harmonics Mode */
//	void calcInitialMachineStates();
//	void invalidateAllMachines();
//	boolean initializeForHarmonics();
//	boolean savePresentVoltages();
//	boolean retrieveSavedVoltages();
//
//	double getMaxPUVoltage();
//	double getMinPUVoltage(boolean ignoreNeutrals);
//	double[] getTotalPowerFromSources();
//	int getMaxCktElementSize();
//	int getUniqueNodeNumber(String sBusName, int StartNode);
//
//	/* TraceBack Functions */
//	boolean isPathBetween(PDElement FromLine, PDElement ToLine);
//	void traceAndEdit(PDElement FromLine, PDElement ToLine, String EditStr);
//	void goForwardAndRephase(PDElement FromLine, String PhaseString,
//			String EditStr, String ScriptFileName, boolean TransStop);
//
//	void makeDistributedGenerators(double kW, double PF, String How, int Skip,
//			String Fname);
//
//	/* Feeder Utilities */ // not currently used
//	void enableFeeders();
//	void disableFeeders();
//	void initializeFeeders();
//	void forwardSweepAllFeeders();
//	void backwardSweepAllFeeders();
	
}
