package com.epri.dss.common.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.shared.CMatrix;

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

	protected Complex[] ComplexBuffer;

	protected int IterminalSolutionCount;

	protected int BusIndex;

	protected CMatrix YPrim_Series;
	protected CMatrix YPrim_Shunt;
	protected CMatrix YPrim;  // Order will be NTerms * Ncond

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

	protected Complex[] Iterminal;
	protected Complex[] Vterminal;

	protected double BaseFrequency;

	protected PowerTerminal[] Terminals;

	protected PowerTerminal ActiveTerminal;

	public DSSCktElement(DSSClass ParClass) {
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

	public Complex[] getIterminal() {
		return Iterminal;
	}

	public void setIterminal(Complex[] iterminal) {
		Iterminal = iterminal;
	}

	public Complex[] getVterminal() {
		return Vterminal;
	}

	public void setVterminal(Complex[] vterminal) {
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

	public Complex getLosses() {
		return new Complex(0, 0);
	}

	/* Get total complex power in active terminal */
	public Complex getPower(int idxTerm) {
		return new Complex(0.0, 0.0);
	}

	private void DoYprimCalcs(CMatrix Ymatrix) {

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

	public int getYPrim(CMatrix Ymatrix, int Opt) {
		return 0;
	}

	public Complex[] getYPrimValues(int Opt) {
		return null;
	}

	/* Max of Iterminal 1 phase currents */
	public double maxTerminalOneIMag() {
		return 0.0;
	}

	/* Computes Iterminal for this device */
	public void computeIterminal() {

	}

	public void computeVterminal() {

	}

	public void zeroITerminal() {

	}

	/* Get present value of terminal Curr for reports */
	public void getCurrents(Complex[] Curr) {

	}

	/* Returns Injextion currents */
	public void getInjCurrents(Complex[] Curr) {

	}

	/* Applies to PC Elements Puts straight into Solution Array */
	public int injCurrents() {
		return 0;
	}


	/* Get bus name by index */
	public String getBus(int i) {
		return null;
	}

	/* Set bus name by index */
	public void setBus(int i, String s) {

	}

	/* Set NodeRef Array for fast solution with intrinsics */
	public void setNodeRef(int iTerm, int[] NodeRefArray) {

	}

	public void recalcElementData() {

	}

	public void calcYPrim() {

	}

	/* Make a positive Sequence Model */
	public void makePosSequence() {

	}

	public void getTermVoltages(int iTerm, Complex[] VBuffer) {

	}

	public void getPhasePower(Complex[] PowerBuffer) {

	}

	public void getPhaseLosses(int Num_Phases, Complex[] LossBuffer) {

	}

	public void getLosses(Complex TotalLosses, Complex LoadLosses,
			Complex NoLoadLosses) {

	}

	public void getSeqLosses(Complex PosSeqLosses, Complex NegSeqLosses,
			Complex ZeroModeLosses) {

	}

	public String getPropertyValue(int Index) {
		return null;
	}

	public void initPropertyValues(int ArrayOffset) {

	}

	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	public void sumCurrents() {

	}

}
