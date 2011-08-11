package com.epri.dss.control;

import java.util.List;

import com.epri.dss.common.CktElement;
import com.epri.dss.conversion.GeneratorObj;

/**
 * A control element that is connected to a terminal of
 * another circuit element and sends dispatch kW signals to a set of generators
 * it controls.
 *
 *   new genDispatcher.name=myName element=devClass.name terminal=[ 1|2|...] capacitorList=(gen1 gen2 ...)
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

	List<String> getGeneratorNameList();

	void setGeneratorNameList(List<String> generatorNameList);

	List<GeneratorObj> getGenPointerList();

	void setGenPointerList(List<GeneratorObj> genPointerList);

	double[] getWeights();

	void setWeights(double[] weights);

	CktElement getMonitoredElement();

	void setMonitoredElement(CktElement monitoredElement);

}
