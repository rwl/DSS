package com.epri.dss.common;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.general.DSSObject;

public interface CktElement extends DSSObject {

	public void Set_YprimFreq(double Value);

	public double Get_YprimFreq();

	public void Set_Nconds(int Value);

	public int Get_Nconds();

	public void Set_NPhases(int Value);

	public int Get_NPhases();

	public void Set_Enabled(boolean Value);

	public boolean Get_Enabled();

	public void Set_ActiveTerminal(int Value);

	public int Get_ActiveTerminal();

	public boolean Get_ConductorClosed(int Index);

	public void Set_YprimInvalid(boolean Value);

	public boolean Is_YprimInvalid();

	public String Get_FirstBus();

	public String Get_NextBus();

	public double[] Get_Losses();

	/* Get total complex power in active terminal */
	public double[] Get_Power(int idxTerm);

	public void Set_ConductorClosed(int Index, boolean Value);

	public void Set_NTerms(int Value);

	public int Get_NTerms();

	public void Set_Handle(int Value);

	public int Get_Handle();

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
