package com.ncond.dss.control.impl;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.impl.CktElementClassImpl;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.control.ControlClass;

public abstract class ControlClassImpl extends CktElementClassImpl implements ControlClass {

	private int numControlClassProps;

	public ControlClassImpl() {
		super();
		numControlClassProps = 0;
		classType = DSSClassDefs.CTRL_ELEMENT;
	}

	protected void countProperties() {
		numProperties = numProperties + numControlClassProps;
		super.countProperties();
	}

	protected void defineProperties() {
		activeProperty = activeProperty + numControlClassProps;

		super.defineProperties();
	}

	protected int classEdit(Object activeControlObj, int paramPointer) {

		if (paramPointer >= 0)
			super.classEdit(activeControlObj, paramPointer - numControlClassProps);

		return 0;
	}

	protected void classMakeLike(DSSClass otherObj) {
		new ControlElemImpl(otherObj);
	}

	public int getNumControlClassProps() {
		return numControlClassProps;
	}

	public void setNumControlClassProps(int num) {
		numControlClassProps = num;
	}

}
