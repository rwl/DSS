package com.epri.dss.common;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.general.NamedObject;

public interface Bus extends NamedObject {

	double[] getZsc0();

	double[] getZsc1();

	DComplexMatrix1D getVBus();

	void setVBus(DComplexMatrix1D vBus);

	DComplexMatrix1D getBusCurrent();

	void setBusCurrent(DComplexMatrix1D busCurrent);

	DComplexMatrix2D getZsc();

	void setZsc(DComplexMatrix2D zsc);

	DComplexMatrix2D getYsc();

	void setYsc(DComplexMatrix2D ysc);

	double getX();

	void setX(double x);

	double getY();

	void setY(double y);

	double getkVBase();

	void setkVBase(double kVBase);

	double getDistFromMeter();

	void setDistFromMeter(double distFromMeter);

	boolean isCoordDefined();

	void setCoordDefined(boolean coordDefined);

	boolean isBusChecked();

	void setBusChecked(boolean busChecked);

	boolean isKeep();

	void setKeep(boolean keep);

	boolean isIsRadialBus();

	void setIsRadialBus(boolean isRadialBus);

}
