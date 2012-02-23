package com.epri.dss.delivery;

/**
 * One or more faults can be placed across any two buses in the circuit.
 * Like the capacitor, the second bus defaults to the ground node of the
 * same bus that bus1 is connected to.
 *
 * The fault is basically an uncoupled, multiphase resistance branch. However,
 * you may also specify it as nodal conductance (G) matrix, which will give you
 * complete control of a complex fault situation.
 *
 * To eliminate a fault from the system after it has been defined, disable it.
 *
 * In Monte Carlo Fault mode, the fault resistance is varied by the % std dev
 * specified if %Stddev is specified as zero (default), the resistance is
 * varied uniformly.
 *
 * Fault may have its "on" time specified (defaults to 0). When time (t)
 * exceeds this value, the fault will be enabled, else it is disabled.
 *
 * Fault may be designated as temporary. That is, after it is enabled, it will
 * disable itself if the fault current drops below the minAmps value.
 */
public interface FaultObj extends PDElement {

	void randomize();

	void checkStatus(int controlMode);

	void reset();

	double getMinAmps();

	void setMinAmps(double minAmps);

	boolean isTemporary();

	void setTemporary(boolean temporary);

	boolean isCleared();

	void setCleared(boolean cleared);

	boolean isOn();

	void setOn(boolean on);

	double getOnTime();

	void setOnTime(double onTime);

	double getRandomMult();

	void setRandomMult(double randomMult);

	double getG();

	void setG(double g);

	double[] getGMatrix();

	void setGMatrix(double[] gmatrix);

	double getStdDev();

	void setStdDev(double stddev);

	int getSpecType();

	void setSpecType(int specType);

}
