package com.epri.dss.general;

import com.epri.dss.shared.CMatrix;

/**
 * A general DSS object used by all circuits as a reference for obtaining line
 * impedances.
 *
 * The values are set by the normal "new" and "edit" procedures for any DSS object.
 *
 * The values are retrieved by setting the code property in the LineCode class.
 * This sets the active LineCode object to be the one referenced by the code property;
 *
 * Then the values of that code can be retrieved via the public variables.
 *
 */
public interface LineCodeObj extends DSSObject {

	void setNPhases(int Value);

	int getNPhases();

	void calcMatricesFromZ1Z0();

	boolean isSymComponentsModel();

	void setSymComponentsModel(boolean symComponentsModel);

	boolean isReduceByKron();

	void setReduceByKron(boolean reduceByKron);

	CMatrix getZ();

	void setZ(CMatrix z);

	CMatrix getZinv();

	void setZinv(CMatrix zinv);

	CMatrix getYC();

	void setYC(CMatrix yC);

	double getBaseFrequency();

	void setBaseFrequency(double baseFrequency);

	double getR1();

	void setR1(double r1);

	double getX1();

	void setX1(double x1);

	double getR0();

	void setR0(double r0);

	double getX0();

	void setX0(double x0);

	double getC1();

	void setC1(double c1);

	double getC0();

	void setC0(double c0);

	double getNormAmps();

	void setNormAmps(double normAmps);

	double getEmergAmps();

	void setEmergAmps(double emergAmps);

	double getFaultRate();

	void setFaultRate(double faultRate);

	double getPctPerm();

	void setPctPerm(double pctPerm);

	double getHrsToRepair();

	void setHrsToRepair(double hrsToRepair);

	double getRg();

	void setRg(double rg);

	double getXg();

	void setXg(double xg);

	double getRho();

	void setRho(double rho);

	int getUnits();

	void setUnits(int units);


	// FIXME Private method in OpenDSS
	void doKronReduction();


	// FIXME Private members in OpenDSS

	int getNeutralConductor();

	void setNeutralConductor(int neutralConductor);
}
