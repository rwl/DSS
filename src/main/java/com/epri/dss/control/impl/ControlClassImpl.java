package com.epri.dss.control.impl;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.CktElementClassImpl;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.control.ControlClass;

public abstract class ControlClassImpl extends CktElementClassImpl implements ControlClass {

	private int numControlClassProps;

	public ControlClassImpl() {
		super();
		this.numControlClassProps = 0;
		this.classType = DSSClassDefs.CTRL_ELEMENT;
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
