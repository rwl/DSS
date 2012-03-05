package com.ncond.dss.common;

import java.io.OutputStream;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.general.DSSObject;
import com.ncond.dss.shared.CMatrix;

public interface CktElement extends DSSObject {

	int[] getNodeRef();

	void setNodeRef(int[] nodeRef);

	int getYorder();

	void setYOrder(int yorder);

	int getLastTerminalChecked();

	void setLastTerminalChecked(int lastTerminalChecked);

	boolean isChecked();

	void setChecked(boolean checked);

	boolean hasEnergyMeter();

	void setHasEnergyMeter(boolean hasEnergyMeter);

	boolean hasSensorObj();

	void setHasSensorObj(boolean hasSensorObj);

	boolean isIsolated();

	void setIsolated(boolean isIsolated);

	boolean hasControl();

	void setHasControl(boolean hasControl);

	boolean isPartofFeeder();

	void setPartofFeeder(boolean isPartofFeeder);

	CktElement getControlElement();

	void setControlElement(CktElement controlElement);

	Complex[] getITerminal();

	void setITerminal(Complex[] iterminal);

	Complex[] getVTerminal();

	void setVTerminal(Complex[] vterminal);

	double getBaseFrequency();

	void setBaseFrequency(double baseFrequency);

	Terminal[] getTerminals();

	Terminal getTerminal(int idx);

	void setTerminals(Terminal[] terminals);

	void setActiveTerminal(Terminal activeTerminal);

	Terminal getActiveTerminal();

	void setYPrimFreq(double Value);

	double getYPrimFreq();

	void setNumConds(int Value);

	int getNumConds();

	void setNumPhases(int Value);

	int getNumPhases();

	void setEnabled(boolean Value);

	boolean isEnabled();

	void setActiveTerminalIdx(int Value);

	int getActiveTerminalIdx();

	/**
	 * @param index if index=-1 return true if all phases closed, else false
	 * @return the state of the selected conductor
	 */
	boolean isConductorClosed(int index);

	void setYPrimInvalid(boolean Value);

	boolean isYprimInvalid();

	String getFirstBus();

	String getNextBus();

	/**
	 * Get total losses in circuit element, all phases, all terminals.
	 * Returns complex losses (watts, vars).
	 */
	Complex getLosses();

	/**
	 * Get total complex power in active terminal
	 */
	Complex getPower(int idxTerm);

	void setConductorClosed(int Index, boolean Value);

	void setNumTerms(int Value);

	int getNumTerms();

	void setHandle(int Value);

	int getHandle();

	CMatrix getYPrim(int opt);

	/**
	 * Returns the storage arrays for fast access.
	 */
	Complex[] getYPrimValues(int opt);

	/** Max of ITerminal 1 phase currents */
	double maxTerminalOneIMag();

	/**
	 * Computes ITerminal for this device
	 */
	void computeITerminal();

	void computeVTerminal();

	void zeroITerminal();

	/**
	 * Get present value of terminal current for reports
	 */
	void getCurrents(Complex[] curr);

	/**
	 * Returns injection currents
	 */
	void getInjCurrents(Complex[] curr);

	/**
	 * Applies to PC elements puts straight into solution array
	 */
	int injCurrents();

	/**
	 * Get bus name by index
	 */
	String getBus(int i);

	/**
	 * Set bus name by index
	 */
	void setBus(int i, String s);

	/**
	 * Set NodeRef array for fast solution with intrinsics.
	 * Also allocates VTemp & ITemp.
	 */
	void setNodeRef(int iTerm, int[] nodeRefArray);

	void recalcElementData();

	void calcYPrim();

	/**
	 * Make a positive sequence model
	 */
	void makePosSequence();

	void getTermVoltages(int iTerm, Complex[] VBuffer);

	/**
	 * Get the power in each phase (complex losses) of active terminal
	 * neutral conductors are ignored by this routine.
	 */
	void getPhasePower(Complex[] powerBuffer);

	/**
	 * Get the losses in each phase (complex losses);  Power difference coming out
	 * each phase. Note: This can be misleading if the nodeV voltage is greatly unbalanced.
	 *
	 * Neutral conductors are ignored by this routine.
	 */
	void getPhaseLosses(int[] numPhases, Complex[] lossBuffer);

	void getLosses(Complex[] totalLosses, Complex[] loadLosses,
			Complex[] noLoadLosses);

	void getSeqLosses(Complex[] posSeqLosses, Complex[] negSeqLosses,
			Complex[] zeroModeLosses);

	String getPropertyValue(int index);

	void initPropertyValues(int arrayOffset);

	void dumpProperties(OutputStream f, boolean complete);

	void sumCurrents();

}
