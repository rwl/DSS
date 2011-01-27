package com.epri.dss.common;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.general.NamedObject;

public interface Bus extends NamedObject {

	public double[] getZsc0();

	public double[] getZsc1();

	public DComplexMatrix1D getVBus();

	public void setVBus(DComplexMatrix1D vBus);

	public DComplexMatrix1D getBusCurrent();

	public void setBusCurrent(DComplexMatrix1D busCurrent);

	public DComplexMatrix2D getZsc();

	public void setZsc(DComplexMatrix2D zsc);

	public DComplexMatrix2D getYsc();

	public void setYsc(DComplexMatrix2D ysc);

	public double getX();

	public void setX(double x);

	public double getY();

	public void setY(double y);

	public double getkVBase();

	public void setkVBase(double kVBase);

	public double getDistFromMeter();

	public void setDistFromMeter(double distFromMeter);

	public boolean isCoordDefined();

	public void setCoordDefined(boolean coordDefined);

	public boolean isBusChecked();

	public void setBusChecked(boolean busChecked);

	public boolean isKeep();

	public void setKeep(boolean keep);

	public boolean isIsRadialBus();

	public void setIsRadialBus(boolean isRadialBus);
}
