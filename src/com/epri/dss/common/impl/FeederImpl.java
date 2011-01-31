package com.epri.dss.common.impl;

import com.epri.dss.common.Feeder;
import com.epri.dss.conversion.impl.PCClassImpl;

public class FeederImpl extends PCClassImpl implements Feeder {

	public FeederImpl() {
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
	public int init(int Handle) {
		return 0;
	}
	
	@Override
	public int newObject(String ObjName) {
		return 0;
	}

}
