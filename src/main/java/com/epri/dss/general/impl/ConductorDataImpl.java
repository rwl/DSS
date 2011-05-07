package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.general.ConductorData;
import com.epri.dss.general.ConductorDataObj;
import com.epri.dss.general.DSSObject;

public class ConductorDataImpl extends DSSClassImpl implements ConductorData {

	private static ConductorDataObj ActiveConductorDataObj;

	private static ConductorChoice[] ConductorChoiceArray = new ConductorChoice[100];

	private int NumConductorClassProps;

	public ConductorDataImpl() {

	}

	protected void countProperties() {

	}

	protected void defineProperties() {

	}

	protected int classEdit(DSSObject ActiveObj, int ParamPointer) {
		return 0;
	}

	protected void classMakeLike(DSSObject OtherObj) {

	}

	public void setNumConductorClassProps(int numConductorClassProps) {
		NumConductorClassProps = numConductorClassProps;
	}

	public int getNumConductorClassProps() {
		return NumConductorClassProps;
	}

	public static void setActiveConductorDataObj(ConductorDataObj activeConductorDataObj) {
		ActiveConductorDataObj = activeConductorDataObj;
	}

	public static ConductorDataObj getActiveConductorDataObj() {
		return ActiveConductorDataObj;
	}

}
