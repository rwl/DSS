package com.epri.dss.general.impl;

import com.epri.dss.general.CNData;

public class CNDataImpl extends CableDataImpl implements CNData {

	/**
	 * Returns active line code string.
	 */
	public String getCode() {
		return null;
	}

	/**
	 * Sets the  active CNData.
	 */
	public void setCode(String Value) {

	}

	protected void defineProperties() {

	}

	@Override
	protected int makeLike(String CNName) {
		return 0;
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		return 0;
	}

	@Override
	public int init(int Handle) {
		return 0;
	}

	@Override
	public int newObject(String ObjName) {
		return 0;
	}

}
