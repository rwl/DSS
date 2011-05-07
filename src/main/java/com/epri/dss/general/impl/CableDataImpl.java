package com.epri.dss.general.impl;

import com.epri.dss.general.CableData;
import com.epri.dss.general.DSSObject;

public class CableDataImpl extends ConductorDataImpl implements CableData {

	private int NumCableClassProps;

	protected void countProperties() {

	}

	protected void defineProperties() {

	}

	protected int classEdit(DSSObject ActiveObj, int ParamPointer) {
		return 0;
	}

	protected void classMakeLike(DSSObject OtherObj) {

	}

	public void setNumCableClassProps(int numCableClassProps) {
		NumCableClassProps = numCableClassProps;
	}

	public int getNumCableClassProps() {
		return NumCableClassProps;
	}

}
