package com.epri.dss.control.impl;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.CktElementClassImpl;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.control.ControlClass;

public abstract class ControlClassImpl extends CktElementClassImpl implements ControlClass {
	
	private int NumControlClassProps;

	public ControlClassImpl() {
		super();
		this.NumControlClassProps = 0;
		this.DSSClassType = DSSClassDefs.CTRL_ELEMENT;
	}
	
	protected void countProperties() {
		NumProperties = NumProperties + NumControlClassProps;
		super.countProperties();	
	}
	
	protected void defineProperties() {
		ActiveProperty = ActiveProperty + NumControlClassProps;

		super.defineProperties();	
	}
	
	protected int classEdit(Object ActiveControlObj, int ParamPointer) {

		if (ParamPointer >= 0)
			super.classEdit(ActiveControlObj, ParamPointer - NumControlClassProps);

		return 0;
	}
	
	protected void classMakeLike(DSSClass OtherObj) {
		new ControlElemImpl(OtherObj);
	}

	public int getNumControlClassProps() {
		return NumControlClassProps;
	}

	public void setNumControlClassProps(int numControlClassProps) {
		NumControlClassProps = numControlClassProps;
	}

}
