package com.epri.dss.conversion;

import com.epri.dss.shared.CMatrix;

public interface VSourceObj extends PCElement {
	
	CMatrix getZ();

	void setZ(CMatrix z);

	CMatrix getZinv();

	void setZinv(CMatrix zinv);

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
