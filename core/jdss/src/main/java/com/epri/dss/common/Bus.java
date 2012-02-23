package com.epri.dss.common;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.general.NamedObject;
import com.epri.dss.shared.CMatrix;

public interface Bus extends NamedObject {

	Complex getZsc0();

	Complex getZsc1();

	Complex[] getVBus();

	void setVBus(Complex[] vBus);

	Complex[] getBusCurrent();

	void setBusCurrent(Complex[] busCurrent);

	CMatrix getZsc();

	void setZsc(CMatrix zsc);

	CMatrix getYsc();

	void setYsc(CMatrix ysc);

	double getX();

	void setX(double x);

	double getY();

	void setY(double y);

	double getKVBase();

	void setKVBase(double kVBase);

	double getDistFromMeter();

	void setDistFromMeter(double distFromMeter);

	boolean isCoordDefined();

	void setCoordDefined(boolean coordDefined);

	boolean isBusChecked();

	void setBusChecked(boolean busChecked);

	boolean isKeep();

	void setKeep(boolean keep);

	boolean isRadialBus();

	void setRadialBus(boolean isRadialBus);

	int getNumNodesThisBus();

	void allocateBusQuantities();

	void allocateBusVoltages();

	void allocateBusCurrents();

	int add(int NodeNum);

	/** Returns reference num for node by node number */
	int find(int NodeNum);

	/** Returns index of node by node number */
	int findIdx(int NodeNum);

	/** Returns reference num for node by node index */
	int getRef(int NodeIndex);

	/** Returns i-th node number designation */
	int getNum(int NodeIndex);

}
