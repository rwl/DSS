package com.epri.dss.common;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.PowerTerminal;
import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.CMatrix;

public interface CktElement extends DSSObject {

	int[] getNodeRef();

	void setNodeRef(int[] nodeRef);

	int getYorder();

	void setYorder(int yorder);

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

	DSSCktElement getControlElement();

	void setControlElement(DSSCktElement controlElement);

	Complex[] getITerminal();

	void setITerminal(Complex[] iterminal);

	Complex[] getVTerminal();

	void setVTerminal(Complex[] vterminal);

	double getBaseFrequency();

	void setBaseFrequency(double baseFrequency);

	PowerTerminal[] getTerminals();

	void setTerminals(PowerTerminal[] terminals);

	void setActiveTerminal(PowerTerminal activeTerminal);

	PowerTerminal getActiveTerminal();

	void setYPrimFreq(double Value);

	double getYPrimFreq();

	void setNConds(int Value);

	int getNConds();

	void setNPhases(int Value);

	int getNPhases();

	void setEnabled(boolean Value);

	boolean isEnabled();

	void setActiveTerminalIdx(int Value);

	int getActiveTerminalIdx();

	boolean getConductorClosed(int Index);

	void setYPrimInvalid(boolean Value);

	boolean isYprimInvalid();

	String getFirstBus();

	String getNextBus();

	Complex getLosses();

	/** Get total complex power in active terminal */
	Complex getPower(int idxTerm);

	void setConductorClosed(int Index, boolean Value);

	void setNTerms(int Value);

	int getNTerms();

	void setHandle(int Value);

	int getHandle();

	CMatrix getYPrim(int Opt);

	Complex[] getYPrimValues(int Opt);

	/** Max of ITerminal 1 phase currents */
	double maxTerminalOneIMag();

	/** Computes ITerminal for this device */
	void computeITerminal();

	void computeVTerminal();

	void zeroITerminal();

	/** Get present value of terminal current for reports */
	void getCurrents(Complex[] curr);

	/** Returns injection currents */
	void getInjCurrents(Complex[] curr);

	/** Applies to PC elements puts straight into solution array */
	int injCurrents();

	/** Get bus name by index */
	String getBus(int i);

	/** Set bus name by index */
	void setBus(int i, String s);

	/** Set NodeRef array for fast solution with intrinsics */
	void setNodeRef(int iTerm, int[] nodeRefArray);

	void recalcElementData();

	void calcYPrim();

	/** Make a positive sequence model */
	void makePosSequence();

	void getTermVoltages(int iTerm, Complex[] VBuffer);

	void getPhasePower(Complex[] PowerBuffer);

	void getPhaseLosses(int numPhases, Complex[] lossBuffer);

	void getLosses(double[] totalLosses, double[] loadLosses,
			double[] noLoadLosses);

	void getSeqLosses(double[] posSeqLosses, double[] negSeqLosses,
			double[] zeroModeLosses);

	String getPropertyValue(int index);

	void initPropertyValues(int arrayOffset);

	void dumpProperties(PrintStream f, boolean complete);

	void sumCurrents();

}
