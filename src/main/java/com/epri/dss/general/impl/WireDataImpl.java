package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.general.WireData;

public class WireDataImpl extends DSSClassImpl implements WireData {

	public WireDataImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCode() {
		return null;
	}
	
	public void setCode(String Value) {
		
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
	public int init(int Handle) {
		return 0;
	}
	
	@Override
	public int newObject(String ObjName) {
		return 0;
	}

}
