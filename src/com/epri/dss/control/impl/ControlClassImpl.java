package com.epri.dss.control.impl;

import com.epri.dss.common.impl.CktElementClassImpl;
import com.epri.dss.control.ControlClass;

public class ControlClassImpl extends CktElementClassImpl implements ControlClass {
	
	private int NumControlClassProps;

	public ControlClassImpl() {
		// TODO Auto-generated constructor stub
	}
	
	protected int classEdit(Object ActiveControlObj, int ParamPointer) {
		return 0;
	}
	
	protected void classMakeLike(Object OtherObj) {
		
	}
	
	protected void countProperties() {
		
	}
	
	protected void defineProperties() {
		
	}

	public int getNumControlClassProps() {
		return NumControlClassProps;
	}

	public void setNumControlClassProps(int numControlClassProps) {
		NumControlClassProps = numControlClassProps;
	}

}
