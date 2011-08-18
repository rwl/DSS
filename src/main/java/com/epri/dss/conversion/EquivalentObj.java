package com.epri.dss.conversion;

import com.epri.dss.shared.ComplexMatrix;

public interface EquivalentObj extends PCElement {

	ComplexMatrix getZ();

	void setZ(ComplexMatrix z);

	ComplexMatrix getZinv();

	void setZInv(ComplexMatrix zinv);

	// Private method in OpenDSS
	int doTerminalsDef(int n);

	// Private method in OpenDSS
	void parseDblMatrix(double[] Mat);


	// FIXME Private members in OpenDSS

	double getKVBase();

	void setKVBase(double kVBase);

	double getVMag();

	void setVMag(double vMag);

	double getPerUnit();

	void setPerUnit(double perUnit);

	double getAngle();

	void setAngle(double angle);

	double getEquivFrequency();

	void setEquivFrequency(double equivFrequency);

	double[] getR1();

	void setR1(double[] r1);

	double[] getX1();

	void setX1(double[] x1);

	double[] getR0();

	void setR0(double[] r0);

	double[] getX0();

	void setX0(double[] x0);

	boolean isNeedToDoRecalc();

	void setNeedToDoRecalc(boolean needToDoRecalc);

}
