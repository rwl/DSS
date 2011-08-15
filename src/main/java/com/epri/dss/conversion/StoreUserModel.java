package com.epri.dss.conversion;

import org.apache.commons.math.complex.Complex;

public interface StoreUserModel {

	/**
	 * Select active instance.
	 */
	int select(int x);

	String getName();

	void setName(String value);

	void edit(String value);

	boolean exists();

	/**
	 * Send string to user model to handle.
	 */
	void edit(int s, int maxlen);

	/**
	 * For dynamics.
	 */
	void init(Complex[] V, Complex[] I);

	/**
	 * Returns currents or sets pShaft.
	 */
	void calc(Complex[] V, Complex[] I);

	/**
	 * Integrates any state vars.
	 */
	void integrate();

	/**
	 * Called when props of generator updated.
	 */
	void updateModel();

	void save();

	void restore();

	/* Monitoring functions */

	int numVars();

	void getAllVars(double[] vars);

	void getAllVars(double d);

	double getVariable(int I);

	void setVariable(int i, double value);

	void getVarName(int varNum, int varName, int maxlen);

}
