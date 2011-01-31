package com.epri.dss.control.impl;

import com.epri.dss.control.Relay;
import com.epri.dss.general.TCC_CurveObj;

public class RelayImpl extends ControlClassImpl implements Relay {

	public RelayImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected int makeLike(String Name) {
		return 0;
	}
	
	protected void defineProperties() {
		
	}
	
	@Override
	public int edit() {
		return 0;
	}
	
	@Override
	public int newObject(String ObjName) {
		return 0;
	}
	
	public TCC_CurveObj getTccCurve(String CurveName) {
		return null;
	}

}
