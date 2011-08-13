package com.epri.dss.conversion.impl;

import com.epri.dss.common.impl.DSSCallBacks;
import com.epri.dss.conversion.StoreUserModel;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.GeneratorVars;

public class StoreUserModelImpl implements StoreUserModel {

	private int handle;  // handle to DLL containing user model
	private int id;      // id of this instance of the user model
	private String name; // name of the DLL file containing user model
	private boolean funcError;

	public StoreUserModelImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Make a new instance.
	 */
	private int makeNew(GeneratorVars genVars, DSSCallBacks callBacks) {
		return 0;
	}

	/**
	 * Deletes specified instance.
	 */
	private void delete(int x) {

	}

	/**
	 * Select active instance.
	 */
	public int select(int x) {
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String Value) {
	}

	private Object checkFuncError(Object addr, String funcName) {
		return null;
	}

	public void edit(String value) {

	}

	public boolean exists() {
		return false;
	}

	/**
	 * Send string to user model to handle.
	 */
	public void edit(int s, int maxlen) {
	}

	/**
	 * For dynamics.
	 */
	public void init(Complex[] V, Complex[] I) {

	}

	/**
	 * Returns currents or sets pShaft.
	 */
	public void calc(Complex[] V, Complex[] I) {

	}

	/**
	 * Integrates any state vars.
	 */
	public void integrate() {

	}

	/**
	 * Called when props of generator updated.
	 */
	public void updateModel() {

	}

	public void save() {

	}

	public void restore() {

	}

	/* Monitoring functions */

	public int numVars() {
		return 0;
	}

	public void getAllVars(double[] vars) {

	}

	public void getAllVars(double var) {

	}

	public double getVariable(int i) {
		return 0.0;
	}

	public void setVariable(int i, double value) {

	}

	public void getVarName(int varNum, int varName, int maxlen) {

	}

}
