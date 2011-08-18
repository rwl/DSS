package com.epri.dss.conversion;

import com.epri.dss.shared.ComplexMatrix;

public interface GICLineObj extends PCElement {

	ComplexMatrix getZ();

	void setZ(ComplexMatrix z);

	ComplexMatrix getZInv();

	void setZInv(ComplexMatrix zinv);

	// FIXME: Private members in OpenDSS

	double getAngle();

	void setAngle(double angle);

	double getVolts();

	void setVolts(double volts);

	double getVMag();

	void setVMag(double vmag);

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
