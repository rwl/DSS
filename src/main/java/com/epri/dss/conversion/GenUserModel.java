package com.epri.dss.conversion;

import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.GeneratorVars;

public interface GenUserModel {

	/**
	 * Select active instance.
	 */
	int select(int x);

	String getName();

	void setName(String Value);

	void edit(String Value);

	boolean exists();

	/**
	 * Send string to user model to handle
	 */
	void edit(int s, int Maxlen);

	/**
	 * For dynamics
	 */
	void init(Complex[] V, Complex[] I);

	/**
	 * Returns currents or sets Pshaft.
	 */
	void calc(Complex[] V, Complex[] I);

	/**
	 * Integrates any state vars
	 */
	void integrate();

	/**
	 * Called when props of generator updated.
	 */
	void updateModel();

	GeneratorVars getActiveGeneratorVars();

	void setActiveGeneratorVars(GeneratorVars activeGeneratorVars);

	void save();

	void restore();

	/* Monitoring functions */

	int numVars();

	void getAllVars(double[] Vars);

	void getAllVars(double d);

	double getVariable(int I);

	void setVariable(int i, double value);

	void getVarName(int VarNum, int VarName, int maxlen);

}
