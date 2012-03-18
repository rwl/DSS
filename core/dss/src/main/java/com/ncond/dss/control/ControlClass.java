/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import com.ncond.dss.common.CktElementClass;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.general.DSSObject;

public abstract class ControlClass extends CktElementClass {

	private int numControlClassProps;

	public ControlClass() {
		super();
		numControlClassProps = 0;
		classType = DSSClassDefs.CTRL_ELEMENT;
	}

	@Override
	protected void countProperties() {
		numProperties = numProperties + numControlClassProps;
		super.countProperties();
	}

	@Override
	protected void defineProperties() {
		activeProperty = activeProperty + numControlClassProps;

		super.defineProperties();
	}

	@Override
	protected int classEdit(DSSObject activeControlObj, int paramPointer) {
		if (paramPointer >= 0)
			super.classEdit(activeControlObj, paramPointer - numControlClassProps);
		return 0;
	}

	protected void classMakeLike(DSSClass otherObj) {
//		new ControlElemImpl(otherObj);
	}

	public int getNumControlClassProps() {
		return numControlClassProps;
	}

	public void setNumControlClassProps(int num) {
		numControlClassProps = num;
	}

}
