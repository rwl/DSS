package com.epri.dss.common;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.PowerTerminal;
import com.epri.dss.general.DSSObject;

public interface CktElement extends DSSObject {

	public int[] getNodeRef();

	public void setNodeRef(int[] nodeRef);

	public int getYorder();

	public void setYorder(int yorder);

	public int getLastTerminalChecked();

	public void setLastTerminalChecked(int lastTerminalChecked);

	public boolean isChecked();

	public void setChecked(boolean checked);

	public boolean isHasEnergyMeter();

	public void setHasEnergyMeter(boolean hasEnergyMeter);

	public boolean isHasSensorObj();

	public void setHasSensorObj(boolean hasSensorObj);

	public boolean isIsIsolated();

	public void setIsIsolated(boolean isIsolated);

	public boolean isHasControl();

	public void setHasControl(boolean hasControl);

	public boolean isIsPartofFeeder();

	public void setIsPartofFeeder(boolean isPartofFeeder);

	public DSSCktElement getControlElement();

	public void setControlElement(DSSCktElement controlElement);

	public DComplexMatrix1D getIterminal();

	public void setIterminal(DComplexMatrix1D iterminal);

	public DComplexMatrix1D getVterminal();

	public void setVterminal(DComplexMatrix1D vterminal);

	public double getBaseFrequency();

	public void setBaseFrequency(double baseFrequency);

	public PowerTerminal[] getTerminals();

	public void setTerminals(PowerTerminal[] terminals);

	public void setActiveTerminal(PowerTerminal activeTerminal);

	public void setYprimFreq(double Value);

	public double getYprimFreq();

	public void setNConds(int Value);

	public int getNConds();

	public void setNPhases(int Value);

	public int getNPhases();

	public void setEnabled(boolean Value);

	public boolean getEnabled();

	public void setActiveTerminal(int Value);

	public int getActiveTerminal();

	public boolean getConductorClosed(int Index);

	public void setYprimInvalid(boolean Value);

	public boolean isYprimInvalid();

	public String getFirstBus();

	public String getNextBus();

	public double[] getLosses();

	/* Get total complex power in active terminal */
	public double[] getPower(int idxTerm);

	public void setConductorClosed(int Index, boolean Value);

	public void setNTerms(int Value);

	public int getNTerms();

	public void setHandle(int Value);

	public int getHandle();

	public int GetYPrim(DComplexMatrix2D Ymatrix, int Opt);

	public DComplexMatrix1D GetYPrimValues(int Opt);

	/* Max of Iterminal 1 phase currents */
	public double MaxTerminalOneIMag();

	/* Computes Iterminal for this device */
	public void ComputeIterminal();

	public void ComputeVterminal();

	public void ZeroITerminal();

	/* Get present value of terminal Curr for reports */
	public void GetCurrents(DComplexMatrix1D Curr);

	/* Returns Injextion currents */
	public void GetInjCurrents(DComplexMatrix1D Curr);

	/* Applies to PC Elements Puts straight into Solution Array */
	public int InjCurrents();


	/* Get bus name by index */
	public String GetBus(int i);

	/* Set bus name by index */
	public void SetBus(int i, String s);

	/* Set NodeRef Array for fast solution with intrinsics */
	public void SetNodeRef(int iTerm, int[] NodeRefArray);

	public void RecalcElementData();

	public void CalcYPrim();

	/* Make a positive Sequence Model */
	public void MakePosSequence();

	public void GetTermVoltages(int iTerm, DComplexMatrix1D VBuffer);

	public void GetPhasePower(DComplexMatrix1D PowerBuffer);

	public void GetPhaseLosses(int Num_Phases, DComplexMatrix1D LossBuffer);

	public void GetLosses(double[] TotalLosses, double[] LoadLosses,
			double[] NoLoadLosses);

	public void GetSeqLosses(double[] PosSeqLosses, double[] NegSeqLosses,
			double[] ZeroModeLosses);

	public String GetPropertyValue(int Index);

	public void InitPropertyValues(int ArrayOffset);

	public void DumpProperties(PrintStream F, boolean Complete);

	public void SumCurrents();
}
