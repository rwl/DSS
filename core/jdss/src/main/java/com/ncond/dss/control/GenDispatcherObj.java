package com.ncond.dss.control;

import java.util.List;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.conversion.GeneratorObj;

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

	double getKWLimit();

	void setKWLimit(double kWLimit);

	double getKWBand();

	void setKWBand(double kWBand);

	double getHalfKWBand();

	void setHalfKWBand(double halfKWBand);

	double getKVArLimit();

	void setKVArLimit(double kVArLimit);

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
