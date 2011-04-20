package com.epri.dss.control;

import com.epri.dss.common.CktElement;

/**
 * A GenDispatcher is a control element that is connected to a terminal of
 * another circuit element and sends dispatch kW signals to a set of generators
 * it controls.
 * 
 * A GenDispatcher is defined by a New command:
 * 
 * New GenDispatcher.Name=myname Element=devclass.name terminal=[ 1|2|...] CapacitorList = (gen1  gen2 ...)
 *
 */
public interface GenDispatcherObj extends ControlElem {
	
	boolean makeGenList();
	
	// FIXME Private members in OpenDSS

	double getkWLimit();

	void setkWLimit(double kWLimit);

	double getkWBand();

	void setkWBand(double kWBand);

	double getHalfkWBand();

	void setHalfkWBand(double halfkWBand);

	double getKvarLimit();

	void setKvarLimit(double kvarLimit);

	double getTotalWeight();

	void setTotalWeight(double totalWeight);

	int getListSize();

	void setListSize(int listSize);

	String[] getGeneratorNameList();

	void setGeneratorNameList(String[] generatorNameList);

	Object[] getGenPointerList();

	void setGenPointerList(Object[] genPointerList);

	double[] getWeights();

	void setWeights(double[] weights);

	CktElement getMonitoredElement();

	void setMonitoredElement(CktElement monitoredElement);

}
