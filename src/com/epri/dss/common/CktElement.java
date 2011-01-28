package com.epri.dss.common;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.PowerTerminal;
import com.epri.dss.general.DSSObject;

public interface CktElement extends DSSObject {

	int[] getNodeRef();

	void setNodeRef(int[] nodeRef);

	int getYorder();

	void setYorder(int yorder);

	int getLastTerminalChecked();

	void setLastTerminalChecked(int lastTerminalChecked);

	boolean isChecked();

	void setChecked(boolean checked);

	boolean isHasEnergyMeter();

	void setHasEnergyMeter(boolean hasEnergyMeter);

	boolean isHasSensorObj();

	void setHasSensorObj(boolean hasSensorObj);

	boolean isIsIsolated();

	void setIsIsolated(boolean isIsolated);

	boolean isHasControl();

	void setHasControl(boolean hasControl);

	boolean isIsPartofFeeder();

	void setIsPartofFeeder(boolean isPartofFeeder);

	DSSCktElement getControlElement();

	void setControlElement(DSSCktElement controlElement);

	DComplexMatrix1D getIterminal();

	void setIterminal(DComplexMatrix1D iterminal);

	DComplexMatrix1D getVterminal();

	void setVterminal(DComplexMatrix1D vterminal);

	double getBaseFrequency();

	void setBaseFrequency(double baseFrequency);

	PowerTerminal[] getTerminals();

	void setTerminals(PowerTerminal[] terminals);

	void setActiveTerminal(PowerTerminal activeTerminal);

	void setYprimFreq(double Value);

	double getYprimFreq();

	void setNConds(int Value);

	int getNConds();

	void setNPhases(int Value);

	int getNPhases();

	void setEnabled(boolean Value);

	boolean getEnabled();

	void setActiveTerminal(int Value);

	int getActiveTerminal();

	boolean getConductorClosed(int Index);

	void setYprimInvalid(boolean Value);

	boolean isYprimInvalid();

	String getFirstBus();

	String getNextBus();

	double[] getLosses();

	/* Get total complex power in active terminal */
	double[] getPower(int idxTerm);

	void setConductorClosed(int Index, boolean Value);

	void setNTerms(int Value);

	int getNTerms();

	void setHandle(int Value);

	int getHandle();

	int GetYPrim(DComplexMatrix2D Ymatrix, int Opt);

	DComplexMatrix1D GetYPrimValues(int Opt);

	/* Max of Iterminal 1 phase currents */
	double MaxTerminalOneIMag();

	/* Computes Iterminal for this device */
	void ComputeIterminal();

	void ComputeVterminal();

	void ZeroITerminal();

	/* Get present value of terminal Curr for reports */
	void GetCurrents(DComplexMatrix1D Curr);

	/* Returns Injextion currents */
	void GetInjCurrents(DComplexMatrix1D Curr);

	/* Applies to PC Elements Puts straight into Solution Array */
	int InjCurrents();


	/* Get bus name by index */
	String GetBus(int i);

	/* Set bus name by index */
	void SetBus(int i, String s);

	/* Set NodeRef Array for fast solution with intrinsics */
	void SetNodeRef(int iTerm, int[] NodeRefArray);

	void RecalcElementData();

	void CalcYPrim();

	/* Make a positive Sequence Model */
	void MakePosSequence();

	void GetTermVoltages(int iTerm, DComplexMatrix1D VBuffer);

	void GetPhasePower(DComplexMatrix1D PowerBuffer);

	void GetPhaseLosses(int Num_Phases, DComplexMatrix1D LossBuffer);

	void GetLosses(double[] TotalLosses, double[] LoadLosses,
			double[] NoLoadLosses);

	void GetSeqLosses(double[] PosSeqLosses, double[] NegSeqLosses,
			double[] ZeroModeLosses);

	String GetPropertyValue(int Index);

	void InitPropertyValues(int ArrayOffset);

	void DumpProperties(PrintStream F, boolean Complete);

	void SumCurrents();

}
