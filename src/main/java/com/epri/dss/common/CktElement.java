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

	Complex[] getIterminal();

	void setIterminal(Complex[] iterminal);

	Complex[] getVterminal();

	void setVterminal(Complex[] vterminal);

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

	boolean isEnabled();

	void setActiveTerminal(int Value);

	int getActiveTerminal();

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

	/* Max of Iterminal 1 phase currents */
	double maxTerminalOneIMag();

	/* Computes Iterminal for this device */
	void computeIterminal();

	void computeVterminal();

	void zeroITerminal();

	/* Get present value of terminal Curr for reports */
	void getCurrents(Complex[] Curr);

	/* Returns Injextion currents */
	void getInjCurrents(Complex[] Curr);

	/* Applies to PC Elements Puts straight into Solution Array */
	int injCurrents();


	/* Get bus name by index */
	String getBus(int i);

	/* Set bus name by index */
	void setBus(int i, String s);

	/* Set NodeRef Array for fast solution with intrinsics */
	void setNodeRef(int iTerm, int[] NodeRefArray);

	void recalcElementData();

	void calcYPrim();

	/* Make a positive Sequence Model */
	void makePosSequence();

	void getTermVoltages(int iTerm, Complex[] VBuffer);

	void getPhasePower(Complex[] PowerBuffer);

	void getPhaseLosses(int Num_Phases, Complex[] LossBuffer);

	void getLosses(Complex TotalLosses, Complex LoadLosses,
			Complex NoLoadLosses);

	void getSeqLosses(Complex PosSeqLosses, Complex NegSeqLosses,
			Complex ZeroModeLosses);

	String getPropertyValue(int Index);

	void initPropertyValues(int ArrayOffset);

	void dumpProperties(PrintStream F, boolean Complete);

	void sumCurrents();

}
