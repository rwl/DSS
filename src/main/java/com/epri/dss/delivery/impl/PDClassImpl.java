package com.epri.dss.delivery.impl;

import com.epri.dss.common.impl.CktElementClassImpl;
import com.epri.dss.delivery.PDClass;

public class PDClassImpl extends CktElementClassImpl implements PDClass {
	
	private int NumPDClassProps;

	public PDClassImpl() {
		// TODO Auto-generated constructor stub
	}
	
	protected int classEdit(Object ActivePDObj, int ParamPointer) {
		return 0;
	}
	
	protected void classMakeLike(Object OtherObj) {
		
	}
	
	protected void countProperties() {
		
	}
	
	protected void defineProperties() {
		
	}

	public int getNumPDClassProps() {
		return NumPDClassProps;
	}

	public void setNumPDClassProps(int numPDClassProps) {
		NumPDClassProps = numPDClassProps;
	}

}
