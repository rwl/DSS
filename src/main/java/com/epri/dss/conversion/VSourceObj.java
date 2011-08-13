package com.epri.dss.conversion;

import com.epri.dss.shared.CMatrix;

public interface VSourceObj extends PCElement {

	CMatrix getZ();

	void setZ(CMatrix z);

	CMatrix getZinv();

	void setZinv(CMatrix zinv);

	double getVMag();

	void setVMag(double vMag);

	double getKVBase();

	void setKVBase(double kVBase);

	double getPerUnit();

	void setPerUnit(double perUnit);

	double getAngle();

	void setAngle(double angle);

	double getSrcFrequency();

	void setSrcFrequency(double srcFrequency);

	// FIXME Private members in OpenDSS

	double getMVAsc3();

	void setMVAsc3(double mVAsc3);

	double getMVAsc1();

	void setMVAsc1(double mVAsc1);

	double getIsc3();

	void setIsc3(double isc3);

	double getIsc1();

	void setIsc1(double isc1);

	int getZSpecType();

	void setZSpecType(int zSpecType);

	double getR1();

	void setR1(double r1);

	double getX1();

	void setX1(double x1);

	double getR0();

	void setR0(double r0);

	double getX0();

	void setX0(double x0);

	double getX1R1();

	void setX1R1(double x1r1);

	double getX0R0();

	void setX0R0(double x0r0);

	int getScanType();

	void setScanType(int scanType);

	int getSequenceType();

	void setSequenceType(int sequenceType);

}
