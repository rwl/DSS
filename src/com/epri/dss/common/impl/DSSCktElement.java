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

	protected int nTerms;
	/* no. conductors per terminal */
	protected int nConds;
	protected int nPhases;

	protected DComplexMatrix1D ComplexBuffer;

	protected int IterminalSolutionCount;

	protected int BusIndex;

	protected DComplexMatrix2D YPrim_Series;
	protected DComplexMatrix2D YPrim_Shunt;
	protected DComplexMatrix2D YPrim;  // Order will be NTerms * Ncond

	/* Frequency at which YPrim has been computed */
	protected double YprimFreq;

	/* Total Noderef array for element */
	protected int[] NodeRef;
	protected int Yorder;
	/* Flag used in tree searches */
	protected int LastTerminalChecked;

	protected boolean Checked, HasEnergyMeter, HasSensorObj, IsIsolated,
    	HasControl, IsPartofFeeder;

	protected DSSCktElement ControlElement;

	protected DComplexMatrix1D Iterminal;
	protected DComplexMatrix1D Vterminal;

	protected double BaseFrequency;

	protected PowerTerminal[] Terminals;

	protected PowerTerminal ActiveTerminal;

	public DSSCktElement(DSSClassImpl ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	public int[] getNodeRef() {
		return NodeRef;
	}

	public void setNodeRef(int[] nodeRef) {
		NodeRef = nodeRef;
	}

	public int getYorder() {
		return Yorder;
	}

	public void setYorder(int yorder) {
		Yorder = yorder;
	}

	public int getLastTerminalChecked() {
		return LastTerminalChecked;
	}

	public void setLastTerminalChecked(int lastTerminalChecked) {
		LastTerminalChecked = lastTerminalChecked;
	}

	public boolean isChecked() {
		return Checked;
	}

	public void setChecked(boolean checked) {
		Checked = checked;
	}

	public boolean isHasEnergyMeter() {
		return HasEnergyMeter;
	}

	public void setHasEnergyMeter(boolean hasEnergyMeter) {
		HasEnergyMeter = hasEnergyMeter;
	}

	public boolean isHasSensorObj() {
		return HasSensorObj;
	}

	public void setHasSensorObj(boolean hasSensorObj) {
		HasSensorObj = hasSensorObj;
	}

	public boolean isIsIsolated() {
		return IsIsolated;
	}

	public void setIsIsolated(boolean isIsolated) {
		IsIsolated = isIsolated;
	}

	public boolean isHasControl() {
		return HasControl;
	}

	public void setHasControl(boolean hasControl) {
		HasControl = hasControl;
	}

	public boolean isIsPartofFeeder() {
		return IsPartofFeeder;
	}

	public void setIsPartofFeeder(boolean isPartofFeeder) {
		IsPartofFeeder = isPartofFeeder;
	}

	public DSSCktElement getControlElement() {
		return ControlElement;
	}

	public void setControlElement(DSSCktElement controlElement) {
		ControlElement = controlElement;
	}

	public DComplexMatrix1D getIterminal() {
		return Iterminal;
	}

	public void setIterminal(DComplexMatrix1D iterminal) {
		Iterminal = iterminal;
	}

	public DComplexMatrix1D getVterminal() {
		return Vterminal;
	}

	public void setVterminal(DComplexMatrix1D vterminal) {
		Vterminal = vterminal;
	}

	public double getBaseFrequency() {
		return BaseFrequency;
	}

	public void setBaseFrequency(double baseFrequency) {
		BaseFrequency = baseFrequency;
	}

	public PowerTerminal[] getTerminals() {
		return Terminals;
	}

	public void setTerminals(PowerTerminal[] terminals) {
		Terminals = terminals;
	}

	public void setActiveTerminal(PowerTerminal activeTerminal) {
		ActiveTerminal = activeTerminal;
	}

	/* set freq and recompute YPrim. */
	private void setFreq(double value) {

	}

	public void setYprimFreq(double Value) {
		setFreq(Value);
	}

	public double getYprimFreq() {
		return this.YprimFreq;
	}

	public void setNConds(int Value) {

	}

	public int getNConds() {
		return this.nConds;
	}

	public void setNPhases(int Value) {

	}

	public int getNPhases() {
		return this.nPhases;
	}

	public void setEnabled(boolean Value) {

	}

	public boolean getEnabled() {
		return this.Enabled;
	}

	public void setActiveTerminal(int Value) {

	}

	public int getActiveTerminal() {
		return this.ActiveTerminalIdx;
	}

	public boolean getConductorClosed(int Index) {
		return false;
	}

	public void setYprimInvalid(boolean Value) {

	}

	public boolean isYprimInvalid() {
		return this.YPrimInvalid;
	}

	public String getFirstBus() {
		return null;
	}

	public String getNextBus() {
		return null;
	}

	public double[] getLosses() {
		return new double[] {0.0, 0.0};
	}

	/* Get total complex power in active terminal */
	public double[] getPower(int idxTerm) {
		return new double[] {0.0, 0.0};
	}

	private void DoYprimCalcs(DComplexMatrix2D Ymatrix) {

	}

	public void setConductorClosed(int Index, boolean Value) {

	}

	public void setNTerms(int Value) {

	}

	public int getNTerms() {
		return this.nTerms;
	}

	public void setHandle(int Value) {

	}

	public int getHandle() {
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
