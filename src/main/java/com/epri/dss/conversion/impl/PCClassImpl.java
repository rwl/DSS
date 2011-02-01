package com.epri.dss.conversion.impl;

import com.epri.dss.common.impl.CktElementClassImpl;
import com.epri.dss.conversion.PCClass;

public class PCClassImpl extends CktElementClassImpl implements PCClass {

	public PCClassImpl() {
		// TODO Auto-generated constructor stub
	}

	protected int classEdit(Object ActivePCObj, int ParamPointer) {
		return 0;
	}

	protected void classMakeLike(Object OtherObj) {

	}

	/* Add no. of intrinsic properties */
	protected void countProperties() {

	}

	/* Add Properties of this class to propName */
	protected void defineProperties() {

	}

	public int NumPCClassProps() {
		return 0;
	}

}
