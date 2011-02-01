package com.epri.dss.delivery.impl;

import com.epri.dss.control.impl.ControlClassImpl;
import com.epri.dss.delivery.Fuse;

public class FuseImpl extends ControlClassImpl implements Fuse {

	public FuseImpl() {
		// TODO Auto-generated constructor stub
	}
	
	protected void defineProperties() {
		
	}
	
	@Override
	protected int makeLike(String FuseName) {
		return 0;
	}
	
	@Override
	public int edit() {
		return 0;
	}
	
	@Override
	public int newObject(String ObjName) {
		return 0;
	}

}
