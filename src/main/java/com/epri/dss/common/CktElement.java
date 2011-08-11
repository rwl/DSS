package com.epri.dss.common;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

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

	void setIsIsolated(boolean isIsolated);

	boolean hasControl();

	void setHasControl(boolean hasControl);

	boolean isPartofFeeder();

	void setIsPartofFeeder(boolean isPartofFeeder);

	DSSCktElement getControlElement();

	void setControlElement(DSSCktElement controlElement);

	Complex[] getIterminal();

	void setIterminal(Complex[] iterminal);

	Complex[] getVterminal();

	void setVterminal(Complex[] vterminal);

	double getBaseFrequency();

	void setBaseFrequency(double baseFrequency);

	PowerTerminal[] getTerminals();

	void setTerminals(PowerTerminal[] terminals);

	void setActiveTerminal(PowerTerminal activeTerminal);

	PowerTerminal getActiveTerminal();

	void setYprimFreq(double Value);

	double getYprimFreq();

	void setNConds(int Value);

	int getNConds();

	void setNPhases(int Value);

	int getNPhases();

	void setEnabled(boolean Value);

	boolean isEnabled();

	void setActiveTerminalIdx(int Value);

	int getActiveTerminalIdx();

	boolean getConductorClosed(int Index);

	void setYprimInvalid(boolean Value);

	boolean isYprimInvalid();

	String getFirstBus();

	String getNextBus();

	Complex getLosses();

	/* Get total complex power in active terminal */
	Complex getPower(int idxTerm);

	void setConductorClosed(int Index, boolean Value);

	void setNTerms(int Value);

	int getNTerms();

	void setHandle(int Value);

	int getHandle();

	int getYPrim(CMatrix Ymatrix, int Opt);

	Complex[] getYPrimValues(int Opt);

	/** Max of ITerminal 1 phase currents */
	double maxTerminalOneIMag();

	/** Computes ITerminal for this device */
	void computeIterminal();

	void computeVterminal();

	void zeroITerminal();

	/** Get present value of terminal current for reports */
	void getCurrents(Complex[] Curr);

	/** Returns injection currents */
	void getInjCurrents(Complex[] Curr);

	/** Applies to PC elements puts straight into solution array */
	int injCurrents();


	/** Get bus name by index */
	String getBus(int i);

	/** Set bus name by index */
	void setBus(int i, String s);

	/** Set NodeRef array for fast solution with intrinsics */
	void setNodeRef(int iTerm, int[] NodeRefArray);

	void recalcElementData();

	void calcYPrim();

	/** Make a positive sequence model */
	void makePosSequence();

	void getTermVoltages(int iTerm, Complex[] VBuffer);

	void getPhasePower(Complex[] PowerBuffer);

	void getPhaseLosses(int Num_Phases, Complex[] LossBuffer);

	void getLosses(double[] TotalLosses, double[] LoadLosses,
			double[] NoLoadLosses);

	void getSeqLosses(double[] PosSeqLosses, double[] NegSeqLosses,
			double[] ZeroModeLosses);

	String getPropertyValue(int Index);

	void initPropertyValues(int ArrayOffset);

	void dumpProperties(PrintStream F, boolean Complete);

	void sumCurrents();

}
