package com.epri.dss.conversion;

import com.epri.dss.shared.CMatrix;

public interface GICLineObj extends PCElement {

	CMatrix getZ();

	void setZ(CMatrix z);

	CMatrix getZinv();

	void setZinv(CMatrix zinv);

	// FIXME: Private members in OpenDSS

	double getAngle();

	void setAngle(double angle);

	double getVolts();

	void setVolts(double volts);

	double getVmag();

	void setVmag(double vmag);

	double getSrcFrequency();

	void setSrcFrequency(double srcFrequency);

	double getR();

	void setR(double r);

	double getX();

	void setX(double x);

	double getC();

	void setC(double c);

	int getScanType();

	void setScanType(int scanType);

	int getSequenceType();

	void setSequenceType(int sequenceType);

}
