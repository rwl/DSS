package com.epri.dss.conversion;

import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

public interface VSourceObj extends PCElement {
	
	DComplexMatrix2D getZ();

	void setZ(DComplexMatrix2D z);

	DComplexMatrix2D getZinv();

	void setZinv(DComplexMatrix2D zinv);

	double getVMag();

	void setVMag(double vMag);

	double getkVBase();

	void setkVBase(double kVBase);

	double getPerUnit();

	void setPerUnit(double perUnit);

	double getAngle();

	void setAngle(double angle);

	double getSrcFrequency();

	void setSrcFrequency(double srcFrequency);

}
