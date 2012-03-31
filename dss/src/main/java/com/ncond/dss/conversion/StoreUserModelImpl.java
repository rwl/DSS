/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

import com.ncond.dss.shared.Complex;

import com.ncond.dss.common.DSSCallBacks;
import com.ncond.dss.shared.GeneratorVars;

@SuppressWarnings("unused")
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

	public void getVarName(int varNum, String[]  varName) {

	}

}
