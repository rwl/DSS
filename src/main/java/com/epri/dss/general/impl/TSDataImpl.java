package com.epri.dss.general.impl;

import com.epri.dss.general.TSData;

public class TSDataImpl extends CableDataImpl implements TSData {

	public TSDataImpl() {
		// TODO Auto-generated constructor stub
	}

	public String getCode() {
		return null;
	}

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
