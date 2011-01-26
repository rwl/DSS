package com.epri.dss.common.impl;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.common.CktElement;
import com.epri.dss.general.impl.DSSObjectImpl;

public class DSSCktElement extends DSSObjectImpl implements CktElement {

	private String[] BusNames;
	private boolean Enabled;
	private int EnabledProperty;
	private int ActiveTerminalIdx;
	private boolean YPrimInvalid;
	private int Handle;

	protected int nterms;
	/* no. conductors per terminal */
	protected int nconds;
	protected int nphases;

	protected DComplexMatrix1D ComplexBuffer;

	protected int IterminalSolutionCount;

	protected int BusIndex;

	protected DComplexMatrix2D YPrim_Series;
	protected DComplexMatrix2D YPrim_Shunt;
	protected DComplexMatrix2D YPrim;  // Order will be NTerms * Ncond

	/* Frequency at which YPrim has been computed */
	protected double YprimFreq;

	/* Total Noderef array for element */
	public int[] NodeRef;
	public int Yorder;
	/* Flag used in tree searches */
	public int LastTerminalChecked;

	public boolean Checked, HasEnergyMeter, HasSensorObj, IsIsolated,
    	HasControl, IsPartofFeeder;

	public DSSCktElement ControlElement;

	public DComplexMatrix1D Iterminal;
	public DComplexMatrix1D Vterminal;

	public double BaseFrequency;

	public PowerTerminal[] Terminals;

	public PowerTerminal ActiveTerminal;

	public DSSCktElement(DSSClassImpl ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	/* set freq and recompute YPrim. */
	private void Set_Freq(double value) {

	}

	public void Set_YprimFreq(double Value) {
		Set_Freq(Value);
	}

	public double Get_YprimFreq() {
		return this.YprimFreq;
	}

	public void Set_Nconds(int Value) {

	}

	public int Get_Nconds() {
		return this.nconds;
	}

	public void Set_NPhases(int Value) {

	}

	public int Get_NPhases() {
		return this.nphases;
	}

	public void Set_Enabled(boolean Value) {

	}

	public boolean Get_Enabled() {
		return this.Enabled;
	}

	public void Set_ActiveTerminal(int Value) {

	}

	public int Get_ActiveTerminal() {
		return this.ActiveTerminalIdx;
	}

	public boolean Get_ConductorClosed(int Index) {
		return false;
	}

	public void Set_YprimInvalid(boolean Value) {

	}

	public boolean Is_YprimInvalid() {
		return this.YPrimInvalid;
	}

	public String Get_FirstBus() {
		return null;
	}

	public String Get_NextBus() {
		return null;
	}

	public double[] Get_Losses() {
		return new double[] {0.0, 0.0};
	}

	/* Get total complex power in active terminal */
	public double[] Get_Power(int idxTerm) {
		return new double[] {0.0, 0.0};
	}

	private void DoYprimCalcs(DComplexMatrix2D Ymatrix) {

	}

	public void Set_ConductorClosed(int Index, boolean Value) {

	}

	public void Set_NTerms(int Value) {

	}

	public int Get_NTerms() {
		return this.nterms;
	}

	public void Set_Handle(int Value) {

	}

	public int Get_Handle() {
		return 0;
	}

	public int GetYPrim(DComplexMatrix2D Ymatrix, int Opt) {
		return 0;
	}

	public DComplexMatrix1D GetYPrimValues(int Opt) {
		return null;
	}

	/* Max of Iterminal 1 phase currents */
	public double MaxTerminalOneIMag() {
		return 0.0;
	}

	/* Computes Iterminal for this device */
	public void ComputeIterminal() {

	}

	public void ComputeVterminal() {

	}

	public void ZeroITerminal() {

	}

	/* Get present value of terminal Curr for reports */
	public void GetCurrents(DComplexMatrix1D Curr) {

	}

	/* Returns Injextion currents */
	public void GetInjCurrents(DComplexMatrix1D Curr) {

	}

	/* Applies to PC Elements Puts straight into Solution Array */
	public int InjCurrents() {
		return 0;
	}


	/* Get bus name by index */
	public String GetBus(int i) {
		return null;
	}

	/* Set bus name by index */
	public void SetBus(int i, String s) {

	}

	/* Set NodeRef Array for fast solution with intrinsics */
	public void SetNodeRef(int iTerm, int[] NodeRefArray) {

	}

	public void RecalcElementData() {

	}

	public void CalcYPrim() {

	}

	/* Make a positive Sequence Model */
	public void MakePosSequence() {

	}

	public void GetTermVoltages(int iTerm, DComplexMatrix1D VBuffer) {

	}

	public void GetPhasePower(DComplexMatrix1D PowerBuffer) {

	}

	public void GetPhaseLosses(int Num_Phases, DComplexMatrix1D LossBuffer) {

	}

	public void GetLosses(double[] TotalLosses, double[] LoadLosses,
			double[] NoLoadLosses) {

	}

	public void GetSeqLosses(double[] PosSeqLosses, double[] NegSeqLosses,
			double[] ZeroModeLosses) {

	}

	public String GetPropertyValue(int Index) {
		return null;
	}

	public void InitPropertyValues(int ArrayOffset) {

	}

	public void DumpProperties(PrintStream F, boolean Complete) {

	}

	public void SumCurrents() {

	}

}
